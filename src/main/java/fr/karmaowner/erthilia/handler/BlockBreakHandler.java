package fr.karmaowner.erthilia.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import fr.karmaowner.erthilia.ErthiliaDropProbability;
import fr.karmaowner.erthilia.blocks.ErthiliaBlocks;
import fr.karmaowner.erthilia.tiles.TileEntityObsidian;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.event.world.ExplosionEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class BlockBreakHandler {

	private Random random = new Random();
	
	private static Block randomOreBlock;
	
    @SubscribeEvent
    public void onBreakEvent(BlockEvent.BreakEvent event) 
    {
    	if(randomOreBlock == null) randomOreBlock = ErthiliaBlocks.RANDOM_ORE;
    	
    	Block block = event.getState().getBlock();    	
    	    	
    	if(event.getPlayer().capabilities.isCreativeMode && !event.getPlayer().canHarvestBlock(event.getState()))
    	{
    		return;
    	}
    	
    	if(block == randomOreBlock)
    	{
    		ErthiliaDropProbability edp = ErthiliaDropProbability.getRandomDropItem();
    		EntityItem item = new EntityItem(event.getWorld(), event.getPos().getX(),  event.getPos().getY(),  event.getPos().getZ(), new ItemStack(edp.getDropItem(), edp.getDropQuantity()));
			float f3 = 0.025F;
			item.motionX = (double) ((float) this.random.nextGaussian() * f3);
			item.motionY = (double) ((float) this.random.nextGaussian() * f3 + 0.1F);
			item.motionZ = (double) ((float) this.random.nextGaussian() * f3);
    		
			event.getWorld().spawnEntity(item);
    	}
    }
     
    @SubscribeEvent
    public void onExplosionEvent(ExplosionEvent.Detonate event)
    {
    	World world = event.getWorld();
    	List<BlockPos> toRemove = new ArrayList<BlockPos>();
    	for(BlockPos pos : event.getAffectedBlocks())
    	{
    		TileEntity tile = world.getTileEntity(pos);
        	
    		if(tile instanceof TileEntityObsidian)
    		{
    			TileEntityObsidian tileObsidian = (TileEntityObsidian) tile;
    			tileObsidian.addExplodedCount(1);
    			
    			if(tileObsidian.getExplodedCount() < 2)
    			{
    				toRemove.add(pos);
    			}
    		}
   
    	}
    	
    	
    	for(BlockPos pos : toRemove)
    	{
        	event.getAffectedBlocks().remove(pos);
    	}

    }
	
}
