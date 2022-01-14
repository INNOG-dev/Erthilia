package fr.karmaowner.erthilia.entity.monster;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.annotation.Nullable;

import fr.karmaowner.erthilia.GameInitializer;
import fr.karmaowner.erthilia.data.ErthiliaEntityDrop;
import fr.karmaowner.erthilia.entity.power.Power;
import fr.karmaowner.erthilia.entity.power.RegenerationPower;
import fr.karmaowner.erthilia.entity.power.ScreamPower;
import fr.karmaowner.erthilia.utils.MathsUtils;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityMultiPart;
import net.minecraft.entity.MultiPartEntityPart;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityFireAtronach extends EntityMana implements IMob {
	
	public HashMap<UUID,EntityPlayer> damagedBy = new HashMap<>();
	
    private static final DataParameter<Integer> TARGETATTACK = EntityDataManager.<Integer>createKey(EntityFireAtronach.class, DataSerializers.VARINT);

	private Vec3d target;
	
	private Vec3d rotatePoint;
	
	private float currentSpeed;
	
	public float renderYaw;
	public float previousRenderYaw;
	
    protected double rangeActive = 16;

	
	private final int radius = 50;
	
	private int waypointCount;
	
	private double altitude = 5.0D;
	
	private int attackTick = 0;
		
	private int stopMovementTick = 0;
	
    protected List<EntityPlayer> playersInArea = new ArrayList<EntityPlayer>();

	
      
    public EntityFireAtronach(World world)
    {
        super(world);

        this.setSize(2F, 6F);
    }    
    
    @Override
    protected void applyEntityAttributes()
    {	 
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(2500.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3F);
        this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(1F);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(5F);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(32F);
    }

    @Override
    protected void entityInit()
    {
        super.entityInit();
        
		dataManager.register(TARGETATTACK, 0);

    }
    
    @Nullable
    @Override
    public EntityLivingBase getAttackTarget()
    {
        if(world.isRemote)
        {
        	int entityId = dataManager.get(TARGETATTACK);
        	if(entityId == -1)
        	{
        		return null;
        	}
        	
        	return (EntityLivingBase) world.getEntityByID(entityId);
        }
        
        return super.getAttackTarget();
    }
    
    @Nullable
    @Override
    public void setAttackTarget(EntityLivingBase entity)
    {
    	
        if(entity != null && entity instanceof EntityPlayer && ((EntityPlayer)entity).capabilities.isCreativeMode)
        {
        	return;
        }
    	
    	
    	if(!world.isRemote)
    	{
    		if(entity == null)
    		{
    			super.setAttackTarget(null);
    			dataManager.set(TARGETATTACK, -1);
    		}
    		else
    		{
    			super.setAttackTarget(entity);
    			dataManager.set(TARGETATTACK, entity.getEntityId());
    		}
    	}
    	else
    	{
    		super.setAttackTarget(entity);
    	}
    }
    
    @Override
    public boolean canDespawn()
    {
		return false;	
    }
    
    
    @Override
    public void onLivingUpdate()
    {  
    	
        if (!this.world.isRemote && this.world.getDifficulty() == EnumDifficulty.PEACEFUL)
        {
            this.setDead();
        }
        
        fallDistance = 0f;
         
        prevDistanceWalkedModified = this.distanceWalkedModified;

        prevRotationPitch = rotationPitch;
        prevPosX = posX;
        prevPosY = posY;
        prevPosZ = posZ;
        renderYawOffset = 0;
        randomYawVelocity = 0;
        prevRenderYawOffset = renderYawOffset;
        
    
        
        if(entityMagicController.getActivePower().size() > 0 || stopMovementTick > 0)
    	{
        	if(stopMovementTick > 0)
        	{
        		--stopMovementTick;
        	}
        	
        	if(playersInArea.size() > 0)
        	{
        		setAttackTarget(this.playersInArea.get(MathHelper.getInt(this.rand, 0, playersInArea.size()-1)));
        	}
        	
    		motionX = 0f;
    		motionZ = 0f;
    	}
        else
        {
    		if(!world.isRemote)
    		{
	        	BlockPos pos = world.getTopSolidOrLiquidBlock(new BlockPos(posX,0,posY));

	        	if(rotatePoint == null)
	        	{
	        		rotatePoint = new Vec3d(posX + this.rand.nextDouble() * radius, 0.0D, posZ + this.rand.nextDouble() * radius);
	        	}
	        	else if(getAttackTarget() == null)
	        	{
	        			
		             if(ticksExisted % (20*20) == 0)
			         {
		            	 rotatePoint = new Vec3d(posX + rand.nextDouble() * (radius * 0.5D), 0.0D, posZ + rand.nextDouble() *  (radius * 0.5D));
			             waypointCount++;	              
			         }
			            
		             int inverse = waypointCount % 2 == 0 ? 1 : -1;
			            		
			         if(this.ticksExisted % (20 * (MathHelper.getInt(rand, 1, 60))) == 0)
			         {
			        	 altitude = MathHelper.nextDouble(rand, (int) (altitude / 2f), altitude);
			         }
			            		
			         target = new Vec3d(rotatePoint.x + Math.cos(inverse * ticksExisted * 0.02) * radius, pos.getY() + altitude, rotatePoint.z + Math.sin(inverse * ticksExisted * 0.02) * radius);    	  	        	
	        	}
	    		else
	    		{
			         target = new Vec3d(getAttackTarget().posX, 0, getAttackTarget().posZ);   		         
	    		}
	        	
	          
	        }

	        	
    		
		    if(target != null)
		    {
		       Vec3d target = new Vec3d(this.target.x - posX, this.target.y - posY, this.target.z - posZ).normalize();
		       
		       currentSpeed = (float) getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue();
		       
		       
		       if(getAttackTarget() != null)
		       {
		    	   if(posY - getAttackTarget().posY > 5D)
		    	   {
		    		   rotationPitch = -40;
		    	   }
		    	   else
		    	   {
				       rotationPitch = 0;
		    	   }
		       }
		       else
		       {
		    	   currentSpeed /= 1.2f;

			       rotationPitch = 0;
		       }
		       
		       prevRotationYaw = rotationYaw;
		       rotationYaw = (float)(Math.atan2(target.z,target.x) * MathsUtils.Rad2Deg) + 90;
		       		       
		       motionX = target.x * currentSpeed;
		       motionZ = target.z * currentSpeed;
		         
		       
		       motionY = target.y / motionX * motionX + motionZ * motionZ;	
		  
		    }  	    
		    
	        if(this.getAttackTarget() != null && this.checkObstacle() && this.jumpTicks <= 0)
	        {
	        	this.jump();
		    	this.jumpTicks = 20;
		    }
	        
		    if(this.isInWater() && this.jumpTicks == 0)
		    {
		    	this.jump();
		    	this.jumpTicks = 20;
		    }
        }
	      
    	
        
        attackTick = Math.max(attackTick - 1, 0);

        
        if(this.attackTick == 0)
        {
        	attackTick = 20;
        	List<Entity> entities = world.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox().grow(3D, 3D, 3D));
        	
        	for(Entity entity : entities) this.attackEntityAsMob(entity);
        }
  
        
     
    	super.onLivingUpdate();
    	
    	playersInArea = world.getEntitiesWithinAABB(EntityPlayer.class, new AxisAlignedBB(posX-rangeActive, posY, posZ-rangeActive, posX+rangeActive, posY+rangeActive, posZ+rangeActive));

    	        
        HashMap<UUID,EntityPlayer> removePlayer = new HashMap<UUID,EntityPlayer>();
        
        for(EntityPlayer player : damagedBy.values())
        {
        	if(!this.playersInArea.contains(player))
        	{
        		removePlayer.put(player.getUniqueID(), player);
        	}
        }
        
        damagedBy.keySet().removeAll(removePlayer.keySet());
    	
        
       
        if(this.getAttackTarget() != null && (this.getAttackTarget().isDead || !this.getAttackTarget().canBeAttackedWithItem()))
        {
        	this.setAttackTarget(null);
        }

        if(this.getAttackTarget() != null && this.getAttackTarget() instanceof EntityPlayer && ((EntityPlayer)getAttackTarget()).capabilities.isCreativeMode)
        {
        	this.setAttackTarget(null);
        }
    }
    
    @Override
    protected void initPowers()
    {
		super.initPowers();

		entityMagicController.registerPower(new ScreamPower(entityMagicController));
		entityMagicController.registerPower(new RegenerationPower(entityMagicController,4f));
		
    }
    
    
    public List<EntityPlayer> getPlayersInArea()
    {
    	return playersInArea;
    }
    

    
    public boolean attackEntityAsMob(Entity entityIn)
    {
       	 float f = (float)this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue();
    	 
         int i = 0;

         if (entityIn instanceof EntityLivingBase)
         {
             f += EnchantmentHelper.getModifierForCreature(this.getHeldItemMainhand(), ((EntityLivingBase)entityIn).getCreatureAttribute());
             i += EnchantmentHelper.getKnockbackModifier(this);
         }

         boolean flag = entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), f);
         
         
         if (flag)
         {
             if (i > 0 && entityIn instanceof EntityLivingBase)
             {
                 ((EntityLivingBase)entityIn).knockBack(this, (float)i * 100F, (double)MathHelper.sin(this.rotationYaw * 0.017453292F), (double)(-MathHelper.cos(this.rotationYaw * 0.017453292F)));
                 this.motionX *= 0.6D;
                 this.motionZ *= 0.6D;
             }

             int j = EnchantmentHelper.getFireAspectModifier(this);

             if (j > 0)
             {
                 entityIn.setFire(j * 4);
             }

             if (entityIn instanceof EntityPlayer)
             {
                 EntityPlayer entityplayer = (EntityPlayer)entityIn;
                 ItemStack itemstack = this.getHeldItemMainhand();
                 ItemStack itemstack1 = entityplayer.isHandActive() ? entityplayer.getActiveItemStack() : ItemStack.EMPTY;

                 if (!itemstack.isEmpty() && !itemstack1.isEmpty() && itemstack.getItem().canDisableShield(itemstack, itemstack1, entityplayer, this) && itemstack1.getItem().isShield(itemstack1, entityplayer))
                 {
                     float f1 = 0.25F + (float)EnchantmentHelper.getEfficiencyModifier(this) * 0.05F;

                     if (this.rand.nextFloat() < f1)
                     {
                         entityplayer.getCooldownTracker().setCooldown(itemstack1.getItem(), 100);
                         this.world.setEntityState(entityplayer, (byte)30);
                     }
                 }
             }

             this.applyEnchantments(this, entityIn);
         }

         return flag;
    }
    
    private boolean checkObstacle()
    {
    	//world.get
    	Vec3d dir = getDirectionnalPoint();
    	
    	for(int x = (int)(dir.x - this.getSize()); x < dir.x + this.getSize(); x++)
    	{
        	for(int z = (int) (dir.z - this.getSize()); z < dir.z + this.getSize(); z++)
        	{
        		IBlockState blockstate = world.getBlockState(new BlockPos(x,dir.y,z));
        		
        		if(blockstate.isNormalCube())
        		{
        			return true;
        		}
        	}
    	}
    		
    	return false;
    }
    
    private Vec3d getDirectionnalPoint()
    {    	
    	float f3 = (rotationYaw-90) * MathsUtils.Deg2Rad;

    	float size = getSize();	

    	float sin = MathHelper.sin(f3);
        float cos = MathHelper.cos(f3);
    	
    	return new Vec3d(posX + (cos * size),posY,posZ + (sin * size));
    }
    
    protected float getJumpUpwardsMotion()
    {
        return 0.6F;
    }
 
   
    
    protected SoundEvent getHurtSound()
    {
        return new SoundEvent(new ResourceLocation("erthilia","dragon_hurt"));
    }


    @Override
    protected SoundEvent getDeathSound()
    {
        return new SoundEvent(new ResourceLocation("erthilia","dragon_death"));
    }
    
    @Override
    public boolean attackEntityFrom(DamageSource source, float damage)
    {
        if (this.getIsInvulnerable())
        {
            return false;
        }
        else if (super.attackEntityFrom(source, damage))
        {
            Entity entity = source.getTrueSource();

            if(entity instanceof EntityPlayer)
            {

            	EntityPlayer player = (EntityPlayer)entity;
            	damagedBy.put(player.getPersistentID(), player);
                setAttackTarget((EntityLivingBase) entity);
            }
            
            stopMovementTick = 5;   
            return true;
        }
        else
        {
            return false;
        }
    }
    
    @Override
    public void onDeath(DamageSource p_70645_1_)
    {
    	if(!world.isRemote)
    	{
	    	ErthiliaEntityDrop drops  = null;
	    	
	    		
	    	drops = GameInitializer.dropsData.get(EntityUnicorn.class);
	    	
	    	
	    	if(drops == null) return;    	
	    	
	    	int size = damagedBy.size() == 1 ? 1 : damagedBy.size() / 2;
	    		    	
	    	Object[] values = this.damagedBy.values().toArray();
	    	
			for(int i = 0; i < size; i++)
			{
				EntityPlayer player = (EntityPlayer)values[MathHelper.getInt(rand, 0, damagedBy.size()-1)];
				ItemStack is = drops.getRandomItemStack();
				if(!player.inventory.addItemStackToInventory(is))
				{
					EntityItem item = new EntityItem(world, player.posX, player.posY, player.posZ, is);
					world.spawnEntity(item);
				}
			}	
    	}
    }
    
    public float getSize()
    {
    	float size = 1.05f;   
    	
    	return size;
    }
	

    protected boolean func_82195_e(DamageSource p_82195_1_, float p_82195_2_)
    {
       return attackEntityFrom(p_82195_1_, p_82195_2_);
    }
    
    public boolean canBeCollidedWith()
    {
        return true;
    }
    

    protected void despawnEntity() {}

	
	@Override
    protected void collideWithEntity(Entity p_82167_1_) { }
	
	@Override
    public void applyEntityCollision(Entity p_70108_1_) { }


	
	@Override
    public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound compound)
    {
        super.readEntityFromNBT(compound);       
    }

}
