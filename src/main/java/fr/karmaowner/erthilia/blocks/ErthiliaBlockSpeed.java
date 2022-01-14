package fr.karmaowner.erthilia.blocks;

import fr.karmaowner.client.creativetab.ErthiliaCreativeTabs;
import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class ErthiliaBlockSpeed extends Block {

    public static final PropertyDirection FACING = BlockHorizontal.FACING;
	
	private Vec3d velocity;
	
	public ErthiliaBlockSpeed(float velocityX, float velocityZ) {
		super(Material.IRON);
		this.setCreativeTab(ErthiliaCreativeTabs.instance);
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
		
		velocity = new Vec3d(velocityX,0,velocityZ);
	}
	
	
	@Override
    public void onEntityWalk(World world, BlockPos pos, Entity entity) 
    {
		int state = this.getMetaFromState(world.getBlockState(pos));
    	
		double motionX = 0D;
		double motionZ = 0D;
		
		if(state == 5)
    	{
    		motionX = entity.motionX + (1 * velocity.x); 
    	}
    	else if(state == 3)
    	{
    		motionZ = entity.motionZ + (1 * velocity.z);
    	}
    	else if(state == 4)
    	{
    		motionX = entity.motionX + (-1 * velocity.x); 
    	}
    	else if(state == 2)
    	{
    		motionZ = entity.motionZ + (-1 * velocity.z); 
    	}

	    entity.addVelocity(motionX, 0, motionZ);
    }
	
	@Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
    {
        worldIn.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);
    }
    
	@Override
    public IBlockState getStateFromMeta(int meta)
    {
        EnumFacing enumfacing = EnumFacing.getFront(meta);

        if (enumfacing.getAxis() == EnumFacing.Axis.Y)
        {
            enumfacing = EnumFacing.NORTH;
        }

        return this.getDefaultState().withProperty(FACING, enumfacing);
    }
	
    @Override
    public int getMetaFromState(IBlockState state)
    {
        return ((EnumFacing)state.getValue(FACING)).getIndex();
    }
    
	
	@Override
    public IBlockState withRotation(IBlockState state, Rotation rot)
    {
        return state.withProperty(FACING, rot.rotate((EnumFacing)state.getValue(FACING)));
    }

	@Override
    public IBlockState withMirror(IBlockState state, Mirror mirrorIn)
    {
        return state.withRotation(mirrorIn.toRotation((EnumFacing)state.getValue(FACING)));
    }
	
	@Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {FACING});
    }




	
	

}
