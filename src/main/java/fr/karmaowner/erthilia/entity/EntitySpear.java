package fr.karmaowner.erthilia.entity;

import fr.karmaowner.erthilia.items.ErthiliaItems;
import fr.karmaowner.erthilia.utils.MathsUtils;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;

public class EntitySpear extends EntityArrow implements IEntityAdditionalSpawnData
{

 
    public EntitySpear(World worldIn)
    {
        super(worldIn);

    }

    public EntitySpear(World worldIn, double x, double y, double z)
    {
        this(worldIn);
        this.setPosition(x, y, z);
    }

    public EntitySpear(World worldIn, EntityLivingBase shooter)
    {
        this(worldIn, shooter.posX, shooter.posY + (double)shooter.getEyeHeight() - 0.10000000149011612D, shooter.posZ);
        this.shootingEntity = shooter;

        if (shooter instanceof EntityPlayer)
        {
            this.pickupStatus = EntityArrow.PickupStatus.ALLOWED;
        }
    }

	@Override
	protected ItemStack getArrowStack() 
	{
		return new ItemStack(ErthiliaItems.SPEAR);
	}

	@Override
	public void writeSpawnData(ByteBuf buffer) {
		 buffer.writeInt(shootingEntity != null ? shootingEntity.getEntityId() : -1);
	}

	@Override
	public void readSpawnData(ByteBuf additionalData)
	{
		 Entity shooter = world.getEntityByID(additionalData.readInt());
		 if (shooter instanceof EntityLivingBase) {
			 shootingEntity = (EntityLivingBase) shooter;
		 }
	}
	
    protected void arrowHit(EntityLivingBase living)
    {
    	living.setArrowCountInEntity(living.getArrowCountInEntity() - 1);

    	if(!(living instanceof EntityPlayer))
    	{
    		return;
    	}
    	
    	EntityPlayer hitPlayer = (EntityPlayer) living;
    	
    	double hitPosY = posY - living.posY;
    	
    	if(hitPosY <= 0.5)
    	{
    		damageItemArmorInIndex(hitPlayer,0,100);
    	}
    	else if(hitPosY > 0.5 && hitPosY <= 1.25) 
    	{
    		damageItemArmorInIndex(hitPlayer,1,100);
    	}
    	else if(hitPosY > 1.25 && hitPosY <= 1.45) 
    	{
    		damageItemArmorInIndex(hitPlayer,2,100);
    	}
    	else if(hitPosY > 1.45) 
    	{
    		damageItemArmorInIndex(hitPlayer,3,100);
    	}
    }
    
    private void damageItemArmorInIndex(EntityPlayer hitEntity, int index, float damage)
    {
    	index = MathsUtils.Clamp(index, 0, hitEntity.inventory.armorInventory.size()-1);
    	
    	if(hitEntity.inventory.armorItemInSlot(index) != null)
    	{
    		hitEntity.inventory.armorItemInSlot(index).damageItem((int) damage, hitEntity);
    	}
    }
		
    

    
}
