package fr.karmaowner.erthilia.entity.monster;

import net.minecraft.world.World;

public class EntityDragonBlack extends EntityDragon {
  
    
    public EntityDragonBlack(World world)
    {
        super(world);
    }    
    
    @Override
    protected void applyEntityAttributes()
    {	 
        super.applyEntityAttributes();
    }

    @Override
    protected void entityInit()
    {
        super.entityInit();
    }
    
    @Override
    public boolean canDespawn()
    {
		return super.canDespawn();	
    }
    
    @Override
    public void onLivingUpdate()
    {
        super.onLivingUpdate();
    }

}
