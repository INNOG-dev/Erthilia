package fr.karmaowner.erthilia.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import fr.karmaowner.erthilia.Main;
import fr.karmaowner.erthilia.utils.ItemStackUtils;
import fr.karmaowner.erthilia.utils.PlayerUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;

public class ErthiliaEntityDrop {

	private final List<String> defaultItems;
	
	private List<String> items = new ArrayList<String>();
	
	private List<ItemStack> itemstacks = new ArrayList<ItemStack>();
	
	
	public ErthiliaEntityDrop(List<String> defaultItems)
	{
		this.defaultItems = defaultItems;
	}
	
	public void loadDatas()
	{
		itemstacks = toItemStackArray(items.size() == 0 ? defaultItems : items);
	} 
	
	public ItemStack getRandomItemStack()
	{
		if(itemstacks.size() > 0)
		{
			Random random = new Random();
			return itemstacks.get(MathHelper.getInt(random, 0, itemstacks.size()-1));
		}
		return null;
	}
	
	public List<ItemStack> toItemStackArray(List<String> items)
	{
		List<ItemStack> itemstacks = new ArrayList<ItemStack>();
		for(String itemData : items)
		{
			String[] data = itemData.split(" ");
			
			String itemId = data[0];
			int quantity = Integer.parseInt(data[1]);
			
			
			ItemStack is = null;
			Object[] info = isNumeric(itemId);
						
			if((boolean)info[0] == true)
			{
				is = new ItemStack(Item.getItemById((int)info[1]),quantity);
			}
			else
			{
				String[] splt = itemId.split(":");
				
				int id;
				int meta;
				try
				{
					id = Integer.parseInt(splt[0]);
					meta = Integer.parseInt(splt[1]);
					is = new ItemStack(Item.getItemById(id),quantity, meta);
				}
				catch(NumberFormatException e)
				{
					is = new ItemStack(Item.getByNameOrId(itemId),quantity);
				}
				
				
			}
						
			for(int i = 2; i < data.length; i++)	
			{
				if(data[i].startsWith("lore:"))
				{
					data[i] = data[i].replaceAll("&", "§").replaceAll("_", " ").replaceAll("lore:", "");

					String[] l = data[i].split("\\|");

					List<String> lores = new ArrayList<String>();
					for(String str : l)
					{
						lores.add(str);
					}
					ItemStackUtils.setLore(is, lores);
				}
				else if(data[i].startsWith("displayname:"))
				{
					data[i] = data[i].replaceAll("&", "§");
					is.setStackDisplayName(data[i].split(":")[1]);
				}
				else
				{
					String[] enchantDatas = data[i].split(":");
					if(!ItemStackUtils.addEnchantment(is, enchantDatas[0],Short.parseShort(enchantDatas[1])))
					{
						Main.log("Enchant not added : enchantment name not exist");
					}
				}
			}
			itemstacks.add(is);
		}
		return itemstacks;
	}
	
	public List<String> getDefaultDrops()
	{
		return this.defaultItems;
	}
	
	public void setDrop(String[] drops)
	{
		for(String str : drops)
		{
			items.add(str);
		}
	}
	
	public void displayDrops(EntityPlayer player)
	{
		for(ItemStack is : itemstacks)
		{
			PlayerUtils.sendMessage(player, "" + is);
		}
	}
	
	public String[] getDefaultDropsToArray()
	{
		String[] drops = new String[this.defaultItems.size()];
		int index = 0;
		for(String drop : this.defaultItems)
		{
			drops[index] = drop;
			index++;
		}
		return drops;
	}
	
	public Object[] isNumeric(String str)
	{
		Object[] datas = new Object[2];
		
		if(str == null)
		{
			datas[0] = false;
			datas[1] = null;
		}
		
		try
		{
			datas[0] = true;
			datas[1] = Integer.parseInt(str);
		}
		catch(NumberFormatException e)
		{
			datas[0] = false;
			datas[1] = null;
			return datas;
		}
		return datas;
	}
	
}
