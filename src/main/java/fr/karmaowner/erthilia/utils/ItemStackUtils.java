package fr.karmaowner.erthilia.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;

public class ItemStackUtils {
    
    public static void setLore(ItemStack is, List<String> loreData)
    {
        if (!is.hasTagCompound())
        {
            is.setTagCompound(new NBTTagCompound());
        }

        if (!is.getTagCompound().hasKey("display", 10))
        {
            is.getTagCompound().setTag("display", new NBTTagCompound());
        }
        
        NBTTagList lores = new NBTTagList();
        for(String lore : loreData)
        {
        	lores.appendTag(new NBTTagString(lore));
        }

        is.getTagCompound().getCompoundTag("display").setTag("Lore", lores);
    }
    
    public static List<String> getLore(ItemStack is)
    {
    	if(!is.hasTagCompound())
    	{
    		return new ArrayList<String>();
    	}
    	
    	List<String> loresList = new ArrayList<String>();
    	
    	if (is.getTagCompound().hasKey("display", 10))
        {
    		NBTTagCompound display = (NBTTagCompound) is.getTagCompound().getTag("display");
    		
            NBTTagList lores = (NBTTagList)display.getTag("Lore");
       
            for(int i = 0; i < lores.tagCount(); i++)
            {
            	String lore = lores.getStringTagAt(i);
            	loresList.add(lore);
            }
            
            return loresList;
        }
    	
    	return new ArrayList<String>();
    }
    
    public static boolean addEnchantment(ItemStack is, String enchantmentName, short lvl)
    {
    	Iterator<Enchantment> iterator = Enchantment.REGISTRY.iterator();
    	while(iterator.hasNext())
    	{
    		Enchantment enchantment = iterator.next();
    		
    		if(enchantment.getName().equalsIgnoreCase(enchantmentName))
    		{
    			is.addEnchantment(enchantment, lvl);
    			return true;
    		}
    	
    	}
    	return false;
    }
    
    public static void removeItemsFromInventory(List<ItemStack> toRemove, EntityPlayer player)
    {
    	for(ItemStack isToRemove : toRemove)
    	{
    		for(Object obj : player.inventoryContainer.inventorySlots)
    		{
    			Slot slot = (Slot) obj;
    			ItemStack isInv = slot.getStack();
    			
    			if(isInv == ItemStack.EMPTY) continue;
    			
    			System.out.println("test2");
    			
    			if(isToRemove.getItem() == isInv.getItem() && isToRemove.getItemDamage() == isInv.getItemDamage())
    			{
        			System.out.println("test3");

    				if(isInv.getCount() >= isToRemove.getCount())
    				{
    	    			System.out.println("test4");

	    				if(isInv.getCount() - isToRemove.getCount() == 0)
	    				{
	    	    			System.out.println("test5");

	    					player.inventory.setInventorySlotContents(slot.getSlotIndex(), ItemStack.EMPTY);
	    					break;
	    				}
	    				else
	    				{
	    	    			System.out.println("test6");

	    					isInv.setCount(isInv.getCount() - isToRemove.getCount());
	    					break;
	    				}
    				}
    				else
    				{
    	    			System.out.println("test7");

    					isToRemove.setCount(isToRemove.getCount() - isInv.getCount());
    					player.inventory.setInventorySlotContents(slot.getSlotIndex(), ItemStack.EMPTY);
    				}
    			}
    			
    		}
    	}
    }
    
    public static boolean playerHaveItemsInInventory(List<ItemStack> toRemove, EntityPlayer player)
    {
    	List<ItemStack> isPlayerHave = new ArrayList<ItemStack>();
    	
    	for(ItemStack isToRemove : toRemove)
    	{
   
    		int quantity = isToRemove.getCount();
    	
    		
    		for(Object obj : player.inventoryContainer.inventorySlots)
    		{
    			Slot slot = (Slot) obj;
    			ItemStack isInv = slot.getStack();
    			
    			if(isInv == ItemStack.EMPTY) continue;
    			
    			if(isToRemove.getItem() == isInv.getItem() && isToRemove.getItemDamage() == isInv.getItemDamage())
    			{
    				if(isInv.getCount() >= quantity)
    				{
	    				if(isInv.getCount() - quantity == 0)
	    				{
	    					isPlayerHave.add(isToRemove);
	    					break;
	    				}
	    				else
	    				{
	    					isPlayerHave.add(isToRemove);
	    					break;
	    				}
    				}
    				else
    				{
    					quantity -= isInv.getCount();
    				}
    			}
    		}
    	}
    	
    	return isPlayerHave.size() == toRemove.size();
    }
    
    public static boolean isItemTools(ItemStack is)
    {
    	return is.isItemStackDamageable() && is.getMaxStackSize() == 1;
    }
	
}
