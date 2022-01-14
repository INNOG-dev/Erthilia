package fr.karmaowner.erthilia.entity.monster;

import java.util.ArrayList;
import java.util.List;

import fr.karmaowner.erthilia.entity.power.MagicController;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityMana extends ErthiliaLivingEntity {
	
	public final static float maxMana = 500;
	
    protected float mana = 0;
    
    
    protected double rangeActive = 16;
    
    private static final DataParameter<Float> MANA = EntityDataManager.<Float>createKey(EntityMana.class, DataSerializers.FLOAT);
        
    private boolean powerInitialized = false;
    
    protected List<EntityPlayer> playersInArea = new ArrayList<EntityPlayer>();
    
    
    public MagicController entityMagicController;
    

	public EntityMana(World world) {
		super(world);
	}
	
	@Override
	protected void entityInit() 
	{
		super.entityInit();
	
		if(!powerInitialized)
		{
			powerInitialized = true;
			entityMagicController = new MagicController(this);
			this.initPowers();
		}

		dataManager.register(MANA, 0f);
	}
	
	protected void initPowers()
	{

	}
	
	public float getManaCount()
	{
		if(world.isRemote)
		{
			return dataManager.get(MANA).floatValue();
		}
		return this.mana;
	}
	
	public void setManaCount(float mana)
	{
		if(!world.isRemote)
		{
			this.mana = mana;
			dataManager.set(MANA, mana);
		}
		this.mana = mana;
	}

	
    @Override
    public void onLivingUpdate()
    { 
    	playersInArea = world.getEntitiesWithinAABB(EntityPlayer.class, new AxisAlignedBB(posX-rangeActive, posY, posZ-rangeActive, posX+rangeActive, posY+rangeActive, posZ+rangeActive));
    	if(!playersInArea.isEmpty())
    	{
	        if(!world.isRemote)
	        {
	        	if(entityMagicController.getActivePower().size() == 0)
	        	{
			    	if(this.ticksExisted % 20 == 0)
			    	{
			            mana += MathHelper.nextDouble(rand, 0.5D, 10.0D);
			            			            
			            if(mana > maxMana)
			            {
			            	mana = maxMana;
			            }
			            setManaCount(mana);
			    	}
	        	}      	

	        }
    	}    	
    	
        if(!this.world.isRemote) if(onGround) entityMagicController.updateController();     
        
        super.onLivingUpdate();
    }
    

    
    public List<EntityPlayer> getPlayersInArea()
    {
    	return playersInArea;
    }
    

    
    public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);
        
        compound.setFloat("Mana", this.mana);
    }

    public void readEntityFromNBT(NBTTagCompound compound)
    {
        super.readEntityFromNBT(compound);
        
        this.mana = compound.getFloat("Mana");
    }

    

}
