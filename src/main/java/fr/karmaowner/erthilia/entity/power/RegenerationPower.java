package fr.karmaowner.erthilia.entity.power;

import net.minecraft.util.EnumParticleTypes;

public class RegenerationPower extends Power {
	
	private float distance;

	public RegenerationPower(MagicController controller, float distance) {
		super(controller);
		this.distance = distance;
	}
	
	protected float needManaCount()
	{
		return 60.0F;
	}
	
	public void applyPower()
	{
		super.applyPower();
		Thread t = new Thread() {	    	  
  	      public void run() {
  	    	while((System.currentTimeMillis() - startedTimer) / 1000 <= powerDurationInSeconds())
  	    	{

  	    		
  	    		if(entity.world.isRemote)
  	        	{
  	  	    		try
  	  	    		{
  						Thread.sleep(1000L);
  					} 
  	  	    		catch (InterruptedException e) 
  	  	    		{
  						e.printStackTrace();
  					}
  	    	    	for(int i = 0; i < 360; i+=1)
  	    	    	{
	  	    		    	double pposX = Math.cos(i) * distance;
	  	    		        double pposZ = Math.sin(i) * distance;
	  	    		        entity.world.spawnParticle(EnumParticleTypes.DRIP_LAVA, entity.posX + pposX, entity.posY + distance, entity.posZ + pposZ, 0, 1f, 0);	    		
  	    	    	}
  	        	}
  	    		else
  	    		{
  	  	    		try
  	  	    		{
  						Thread.sleep(100L);
  					} 
  	  	    		catch (InterruptedException e) 
  	  	    		{
  						e.printStackTrace();
  					}
      	        	entity.heal(1f);
  	    		}
  	    	}
  	    	stopPower();
  	      }
		};
		t.start();
	}
	
	
	public boolean canUsePower()
	{
		return super.canUsePower() && entity.getHealth() <= 500.0D;
	}

	@Override
	public PowerType getPowerType() {
		return PowerType.EXTERN;
	}

	@Override
	public int powerDurationInSeconds() {
		return 20;
	}

	@Override
	protected int priorityCheckConditions() {
		return 1;
	}

	@Override
	public String getName() {
		return "Regeneration";
	}

	@Override
	public int getAnimationId() {
		return -1;
	}

	@Override
	protected String powerSound() {
		return "dragon_scream";
	}
	
	
}
