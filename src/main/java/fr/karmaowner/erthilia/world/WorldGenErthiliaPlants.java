package fr.karmaowner.erthilia.world;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenErthiliaPlants extends WorldGenerator {

	@SuppressWarnings("unused")
	private Block blockToGen;
	
	public WorldGenErthiliaPlants(Block block)
	{
		this.blockToGen = block;
	}

	@Override
	public boolean generate(World world, Random rand, BlockPos position) {
		/*Biome b = world.getBiome(new BlockPos(position.getX(),position.getY(), position.getZ()));
		if(b.getBiomeName().equals("Plains") || b.getBiomeName().equals("Forest") || b.getBiomeName().equals("Extreme Hills"))
		{
			for (int l = 0; l < 5; l++)
	        {
				int i1 = position.getX() + rand.nextInt(4) - rand.nextInt(4);
	            int j1 = position.getY() + rand.nextInt(4) - rand.nextInt(4);
	            int k1 =  position.getZ() + rand.nextInt(4) - rand.nextInt(4);
	            BlockPos genPos = new BlockPos(i1,j1,k1);
	            
	            if (world.isAirBlock(genPos) && blockToGen.canPlaceBlockAt(world, genPos))
	            {
	                if (blockToGen.canPlaceBlockOnSide(world, genPos, EnumFacing.UP))
	                {
	                	world.setBlockState(genPos, blockToGen.getDefaultState(), 2);
	                }
	                
	            }
	        }
			return true;
		}*/
		return false;
	}

}
