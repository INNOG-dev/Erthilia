package fr.karmaowner.erthilia.blocks;

import java.util.Random;

import fr.karmaowner.client.creativetab.ErthiliaCreativeTabs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;

public class ErthiliaBasicBlock extends Block {
	
	public ErthiliaBasicBlock(int harvestLevel, boolean oreBlock)
	{
		super(Material.ROCK);
		this.setHarvestLevel("pickaxe", harvestLevel);
		
		this.setCreativeTab(ErthiliaCreativeTabs.instance);

	}
	
	private Random rand = new Random();
    public int getExpDrop(IBlockState state, IBlockAccess world, BlockPos pos, int fortune)
    {
		if (this == ErthiliaBlocks.XP_BLOCK)
		{
			return MathHelper.getInt(rand, 20, 40);
		}
		return 0;
    }
	
    @Override
    public int quantityDropped(Random rand)
    {
    	if(ErthiliaBlocks.XP_BLOCK == this)
    	{
    		return 0;
    	}
        return 1;
    }
	
    
	
	
	
	
	
}
