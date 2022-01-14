package fr.karmaowner.erthilia.blocks;

import java.util.Random;

import fr.karmaowner.client.creativetab.ErthiliaCreativeTabs;
import fr.karmaowner.erthilia.items.ErthiliaItems;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;


public class ErthiliaBlockOre extends Block {
	
	
	
	public ErthiliaBlockOre(int harvestLevel)
	{
		super(Material.ROCK);
		this.setCreativeTab(ErthiliaCreativeTabs.instance);
		this.setHarvestLevel("pickaxe", harvestLevel);
	}

	@Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return this == ErthiliaBlocks.JADE_ORE ? ErthiliaItems.JADE : this == ErthiliaBlocks.CYANITE_ORE ? ErthiliaItems.CYANITE : this == ErthiliaBlocks.MORGANITE_ORE ? ErthiliaItems.MORGANITE : this == ErthiliaBlocks.PYRITE_ORE ? ErthiliaItems.PYRITE : this == ErthiliaBlocks.CITRINE_ORE ? ErthiliaItems.CITRINE : Item.getItemFromBlock(this);
    }
	
	@Override
    public int quantityDropped(Random rand)
    {
		if(this == ErthiliaBlocks.RANDOM_ORE)
		{
			return 0;
		}
        return 1;
    }
	
    private Random rand = new Random();
	
    @Override
    public int getExpDrop(IBlockState state, IBlockAccess world, BlockPos pos, int fortune)
    {
        if (getItemDropped(state, rand, fortune) != Item.getItemFromBlock(this))
        {
            int j1 = 0;
            if (this == ErthiliaBlocks.JADE_ORE)
            {
                j1 = MathHelper.getInt(rand, 2, 4);
            }
            else if (this == ErthiliaBlocks.CYANITE_ORE)
            {
                j1 = MathHelper.getInt(rand, 2, 4);
            }
            else if (this == ErthiliaBlocks.MORGANITE_ORE)
            {
                j1 = MathHelper.getInt(rand, 2, 4);
            }
            else if (this == ErthiliaBlocks.PYRITE_ORE)
            {
                j1 = MathHelper.getInt(rand, 2, 4);
            }
            else if (this == ErthiliaBlocks.CITRINE_ORE)
            {
                j1 = MathHelper.getInt(rand, 2, 4);
            }
            return j1;
        }
        return 0;
    }
    
	
}
