package fr.karmaowner.erthilia.blocks;

import fr.karmaowner.client.creativetab.ErthiliaCreativeTabs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class ErthiliaBlockJump extends Block {
	
	private Vec3d velocity;

	
	public ErthiliaBlockJump(float velocityX, float velocityY, float velocityZ) 
	{
		super(Material.IRON);
		this.setCreativeTab(ErthiliaCreativeTabs.instance);
		
		
		velocity = new Vec3d(velocityX,velocityY,velocityZ);
	}
	
	@Override
    public void onEntityWalk(World world, BlockPos pos, Entity entity) 
    {

    	double motionX = entity.motionX * velocity.x;
    	double motionY = velocity.y;
    	double motionZ = entity.motionZ * velocity.z;

	    entity.addVelocity(motionX, motionY, motionZ);
    	
	    super.onEntityWalk(world, pos, entity);
    }
    
	@Override
    public void onFallenUpon(World world, BlockPos pos, Entity entity, float idk) 
    {
    	entity.fallDistance-=idk;
    }

}
