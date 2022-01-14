package fr.karmaowner.erthilia.world;

import java.util.Random;

import fr.karmaowner.erthilia.blocks.ErthiliaBlocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

public class ErthiliaWorldGeneration implements IWorldGenerator {

	private WorldGenerator jadeOreGenerator;
	private WorldGenerator cyaniteOreGenerator;
	private WorldGenerator morganiteOreGenerator;
	private WorldGenerator pyriteOreGenerator;
	private WorldGenerator citrineOreGenerator;
	private WorldGenerator randomOreGenerator;
	private WorldGenerator randomXpGenerator;
	
	public ErthiliaWorldGeneration()
	{
		jadeOreGenerator = new WorldGenMinable(ErthiliaBlocks.JADE_ORE.getDefaultState(), 4);
		cyaniteOreGenerator = new WorldGenMinable(ErthiliaBlocks.CYANITE_ORE.getDefaultState(), 3);
		morganiteOreGenerator = new WorldGenMinable(ErthiliaBlocks.MORGANITE_ORE.getDefaultState(), 3);
		pyriteOreGenerator = new WorldGenMinable(ErthiliaBlocks.PYRITE_ORE.getDefaultState(), 3);
		citrineOreGenerator = new WorldGenMinable(ErthiliaBlocks.CITRINE_ORE.getDefaultState(), 3);
		randomOreGenerator = new WorldGenMinable(ErthiliaBlocks.RANDOM_ORE.getDefaultState(), 3);
		randomXpGenerator = new WorldGenMinable(ErthiliaBlocks.XP_BLOCK.getDefaultState(), 3);
	}
	
	private void generateOre(WorldGenerator generator, World world, Random rand, int chunkX, int chunkZ, int chancesToSpawn, int minHeight, int maxHeight) {
		if (minHeight < 0 || maxHeight > 256 || minHeight > maxHeight) throw new IllegalArgumentException("Illegal Height Arguments for WorldGenerator");

	    int heightDiff = maxHeight - minHeight + 1;
	    for (int i = 0; i < chancesToSpawn; i ++) {
	        int x = chunkX * 16 + rand.nextInt(16);
	        int y = minHeight + rand.nextInt(heightDiff);
	        int z = chunkZ * 16 + rand.nextInt(16);
	        generator.generate(world, rand, new BlockPos(x, y, z));
	    }
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator,IChunkProvider chunkProvider) 
	{
		switch(world.provider.getDimension())
		{
		  case -1: //Nether
			  break;
		  case 0: // Overworld
			  this.generateOre(jadeOreGenerator, world, random, chunkX, chunkZ, 50, 0, 40);
			  this.generateOre(cyaniteOreGenerator, world, random, chunkX, chunkZ, 40, 0, 30);
			  this.generateOre(morganiteOreGenerator, world, random, chunkX, chunkZ, 35, 0, 30);
			  this.generateOre(pyriteOreGenerator, world, random, chunkX, chunkZ, 30, 0, 16);
			  this.generateOre(citrineOreGenerator, world, random, chunkX, chunkZ, 30, 0, 16);
			  this.generateOre(randomOreGenerator, world, random, chunkX, chunkZ, 10, 0, 16);
			  this.generateOre(randomXpGenerator, world, random, chunkX, chunkZ, 10, 0, 16);
			  break;
		  case 1: // End
			  break;
		  default:
			  break;
		}
	}
	

}
