package fr.karmaowner.erthilia.entity.power;

import fr.karmaowner.erthilia.utils.PlayerUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;

public class ScreamPower extends Power {

	public ScreamPower(MagicController controller) {
		super(controller);
	}

	protected float needManaCount()
	{
		return 200.0F;
	}
	
	public void applyPower()
	{
		super.applyPower();
		Thread t = new Thread()
		{
  	      	public void run() {
  	    	 
  	    	while((System.currentTimeMillis() - startedTimer) / 1000 <= powerDurationInSeconds())
  	    	{
  	    		  try {
						Thread.sleep(100);
				  } catch (InterruptedException e) {
						e.printStackTrace();
				  }
  	    		  if(entity.world.isRemote)
  	    		  {
	    	    		  for(int i = 0; i < 360; i+= 30)
	    	      		  {
	    	    			  for(int j = 0; j < 360; j+=30)
	    	  		    	  {
	    	    				  double distance = 6F;
	    	    				  double x = entity.posX + distance * Math.sin(i) * Math.cos(j);
	    	    				  double y = entity.posY + entity.height / 2 + distance * Math.sin(j);
	    	    				  double z = entity.posZ + distance * Math.cos(i) * Math.cos(j);
		    	    			 
	    	    				  entity.world.spawnParticle(EnumParticleTypes.CLOUD, x, y, z, Math.sin(i) * Math.cos(j),0.2, Math.cos(i) * Math.cos(j));
	    	  		    	  }
	    	      		  }
  	    		  }
  	    		  eject(16);
  	    	  }
  	          stopPower();
  	      }
		};	
		t.start();
	}
	
	public void eject(int attackRange)
	{
	    AxisAlignedBB boundingBox = new AxisAlignedBB(entity.posX-attackRange, entity.posY-attackRange, entity.posZ-attackRange, entity.posX+attackRange, entity.posY + attackRange, entity.posZ + attackRange);
	    	
	    double d0 = (boundingBox.minX + boundingBox.maxX) / 2.0D;
	    double d1 = (boundingBox.minZ + boundingBox.maxZ) / 2.0D;
	    	
	    for(int i = 0; i < this.entity.world.loadedEntityList.size(); i++)
	    {
	    		Object obj  = this.entity.world.loadedEntityList.get(i);
	    		if(obj == controller.getAttribuatedEntity()) continue;
	    		
	    		if(obj instanceof EntityPlayer)
	    		{
	    			EntityPlayer player = (EntityPlayer) obj;
	        		if(boundingBox.intersects(player.getEntityBoundingBox()))
	        		{
	        			boolean isBlocking = PlayerUtils.isBlockingWithSword(player);
	        			double d2 = player.posX - d0;
	                    double d3 = player.posZ - d1;
	                    
	                    d2 /= isBlocking ? 16 : 8;
	                    d1 = isBlocking ? 0 : 0.3; 
	                    d3 /= isBlocking ? 16 : 8;
	                    
	                    
	                    player.addVelocity(d2, d1, d3);
	                    player.attackEntityFrom(DamageSource.MAGIC, (float) (d1 * 10));
	        		}
	    		}
	    		else
	    		{
	        		Entity entity = (Entity) obj;
	        		if(boundingBox.intersects(entity.getEntityBoundingBox()))
	        		{
	        			double d2 = entity.posX - d0;
	                    double d3 = entity.posZ - d1;
	                    d2 /= 4;
	                    d1 = 0.1; 
	                    d3 /= 4;
	                    entity.addVelocity(d2, d1, d3);
	                    entity.attackEntityFrom(DamageSource.MAGIC, (float) (d1 * 5));
	        		}
	    		}

	    }
	}
	
	public boolean canUsePower()
	{
		return super.canUsePower() && !(controller.getActiveExternPower().size() >= 1);
	}

	@Override
	public PowerType getPowerType() {
		return PowerType.EXTERN;
	}

	@Override
	public int powerDurationInSeconds() {
		return 5;
	}

	@Override
	protected int priorityCheckConditions() {
		return 0;
	}

	@Override
	public String getName() {
		return "DragonScream";
	}

	@Override
	public int getAnimationId() {
		return 0;
	}

	@Override
	protected String powerSound() {
		return "dragon_scream";
	}

	
	
}
