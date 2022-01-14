package fr.karmaowner.erthilia.blocks;

import java.util.HashMap;

import fr.karmaowner.client.renderer.itemrenderer.IItemRenderer;
import fr.karmaowner.erthilia.Main;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ErthiliaBlock {

    public static HashMap<String,ErthiliaBlock> blocks = new HashMap<>();
	
	private String blockName;
	
	private Block block;
	
	private IItemRenderer scriptRendering;
	
	public long registeredTime;
	
	public ErthiliaBlock(String blockName, Block block)
	{
		this.blockName = blockName;
		this.block = block;
		registerBlock();
	}	
	
	private void registerBlock()
	{
		block.setRegistryName(new ResourceLocation(Main.MODID,blockName));
		block.setUnlocalizedName(blockName); 
			
		blocks.put(Main.MODID+":"+blockName, this);
			
		registeredTime = System.currentTimeMillis();
			
		Main.log("Block named " + block.getUnlocalizedName() + " registered");
	}
	
	public ErthiliaBlock constructRendering(IItemRenderer scriptRendering)
	{
		this.scriptRendering = scriptRendering;
		return this;
	}
	
	public IItemRenderer getRenderer()
	{
		return scriptRendering;
	}
	
	public boolean hasScriptRendering()
	{
		return this.scriptRendering != null;
	}
	
	public String getBlockName()
	{
		return blockName;
	}
	
	public Block getBlock()
	{
		return this.block;
	}
	
	public static Block getBlock(String blockName)
	{
		if(blocks.containsKey(blockName))
		{
			return blocks.get(blockName).getBlock();
		}
		return null;
	}
	
	public static ErthiliaBlock getErthiliaBlock(String blockName)
	{
		if(blocks.containsKey(blockName))
		{
			return blocks.get(blockName);
		}
		return null;
	}
	
	
	public void registerRender()
	{
		if(block instanceof BlockCrops || getBlockName().equals("jade_furnace_lit"))
		{
			return;
		}
		
		ItemBlock ib = new ItemBlock(block);
		
		ib.setRegistryName(block.getRegistryName());
		
		GameRegistry.findRegistry(Item.class).register(ib);
		
		Item item = Item.getItemFromBlock(block);
		
		if(block instanceof IErthiliaCustomModel)
		{
			((IErthiliaCustomModel) block).registerCustomRender(item);
		}

		
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));

	}
	

	
}
