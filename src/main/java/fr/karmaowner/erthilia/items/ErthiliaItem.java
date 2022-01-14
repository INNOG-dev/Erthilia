package fr.karmaowner.erthilia.items;

import java.util.HashMap;

import fr.karmaowner.client.renderer.itemrenderer.IItemRenderer;
import fr.karmaowner.erthilia.Main;
import fr.karmaowner.erthilia.blocks.IErthiliaCustomModel;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;

public class ErthiliaItem {
	
	public static HashMap<String,ErthiliaItem> items = new HashMap<String,ErthiliaItem>();
	
	public String itemName;
	
	private Item item;
	
	private IItemRenderer scriptRendering;
	
	public long registeredTime;
	
	
	public ErthiliaItem(String itemName, Item item)
	{
		this.itemName = itemName;
		this.item = item;
		registerItem();
	}
	
	private void registerItem()
	{
		item.setRegistryName(new ResourceLocation(Main.MODID,itemName));
		item.setUnlocalizedName(itemName);
		
		items.put(Main.MODID+":"+itemName,this);
		
		
		registeredTime = System.currentTimeMillis();
		Main.log("Item named " + item.getUnlocalizedName() + " registered");
	}
	
	public ErthiliaItem constructRendering(IItemRenderer scriptRendering)
	{
		this.scriptRendering = scriptRendering;
		return this;
	}
	
	public boolean hasScriptRendering()
	{
		return this.scriptRendering != null;
	}
	
	public IItemRenderer getRenderer()
	{
		return this.scriptRendering;
	}
	
	public static ErthiliaItem getErthiliaItem(String itemName)
	{
		if(items.containsKey(itemName))
		{
			return items.get(itemName);
		}
		return null;
	}
	
	public String getItemName()
	{
		return itemName;
	}
	
	public Item getItem()
	{
		return this.item;
	}
	
	public static Item getItem(String itemName)
	{
		if(items.containsKey(itemName))
		{
			return items.get(itemName).getItem();
		}
		return null;
	}
	
	public void registerRender()
	{
		if(item instanceof IErthiliaCustomModel)
		{
			((IErthiliaCustomModel) item).registerCustomRender(item);
		}
		ModelLoader.setCustomModelResourceLocation(getItem(), 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}


}
