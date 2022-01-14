package fr.karmaowner.erthilia.inventory;

import java.util.ArrayList;
import java.util.List;

import fr.karmaowner.erthilia.blocks.ErthiliaBlocks;
import fr.karmaowner.erthilia.utils.PlayerUtils;
import fr.karmaowner.erthilia.wiki.WikiRecipes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ContainerReversedCraftingTable extends Container {

	public IInventory tableInventory = new InventoryBasic("Craft", true, 1)
    {

        public int getInventoryStackLimit()
        {
            return 64;
        }

        public void markDirty()
        {
            super.markDirty();
            ContainerReversedCraftingTable.this.onCraftMatrixChanged(this);
        }
        
    };
    
    private World worldObj;
    
    private BlockPos pos;   
    
    public int xpCount;
    
    private EntityPlayer thePlayer;
    
    public ContainerReversedCraftingTable(InventoryPlayer inventoryPlayer, World world, BlockPos pos)
    {
    	thePlayer = inventoryPlayer.player;
        
    	worldObj = world;
        
        this.pos = pos;
        
        this.addSlotToContainer(new Slot(tableInventory, 0, 25, 47)
        {
            public boolean isItemValid(ItemStack is)
            {
                return true;
            }
        });
        
        int l;

        for (l = 0; l < 3; ++l)
        {
            for (int i1 = 0; i1 < 9; ++i1)
            {
                this.addSlotToContainer(new Slot(inventoryPlayer, i1 + l * 9 + 9, 8 + i1 * 18, 84 + l * 18));
            }
        }

        for (l = 0; l < 9; ++l)
        {
            this.addSlotToContainer(new Slot(inventoryPlayer, l, 8 + l * 18, 142));
        }
    }


    @Override
    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();
    }

    @Override
    public void onCraftMatrixChanged(IInventory inventory)
    {
        if (inventory == this.tableInventory)
        {
            ItemStack itemstack = inventory.getStackInSlot(0);
                        
            if(itemstack == null) return;
            
            calculateXp();
            
            if(thePlayer.inventory.getFirstEmptyStack() == -1)
            {
            	return;
            }
            
            if(!isValidItem(itemstack)) return;
            
  
            if (!worldObj.isRemote)
            {
            	detectAndSendChanges();
            }
        }
    }


    @Override
    public void onContainerClosed(EntityPlayer player)
    {
        super.onContainerClosed(player);

        if (!this.worldObj.isRemote)
        {
            this.clearContainer(player, player.world, this.tableInventory);
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer player)
    {
        if (this.worldObj.getBlockState(this.pos).getBlock() != ErthiliaBlocks.REVERSE_CRAFTING_TABLE)
        {
            return false;
        }
        else
        {
            return player.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D;
        }
        
    }


    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index)
    {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = (Slot)this.inventorySlots.get(index);
        
        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (index == 0)
            {
                if (!this.mergeItemStack(itemstack1, 1, 37, true))
                {
                    return ItemStack.EMPTY;
                }
            }
            else
            {
                if (((Slot)this.inventorySlots.get(0)).getHasStack() || !((Slot)this.inventorySlots.get(0)).isItemValid(itemstack1))
                {
                    return ItemStack.EMPTY;
                }

                if (itemstack1.hasTagCompound())
                {
                    ((Slot)this.inventorySlots.get(0)).putStack(itemstack1.splitStack(1));
                }
                else if (!itemstack1.isEmpty())
                {
                    ((Slot)this.inventorySlots.get(0)).putStack(new ItemStack(itemstack1.getItem(), 1, itemstack1.getItemDamage()));
                    itemstack1.shrink(1);
                }
            }

            if (itemstack1.isEmpty())
            {
                slot.putStack(ItemStack.EMPTY);
            }
            else
            {
                slot.onSlotChanged();
            }

            if (itemstack1.getCount() == itemstack.getCount())
            {
                return ItemStack.EMPTY;
            }

            slot.onTake(playerIn, itemstack1);
        }

        return itemstack;
    }
    
    public boolean playerHaveXp()
    {
    	return thePlayer.experienceLevel >= this.xpCount * tableInventory.getStackInSlot(0).getCount() || thePlayer.capabilities.isCreativeMode;
    }
    
    public void calculateXp()
    {
    	int xpCount = 0;
    	ItemStack is = tableInventory.getStackInSlot(0);
    	if(is != null)
    	{
    		xpCount = this.getXpFromEnchantmentLevel() * is.getCount();	
    	}
    	this.xpCount = xpCount;
    }
    
    public int getXpFromEnchantmentLevel()
    {
    	return 20;
    }
    
    public EntityPlayer getPlayer()
    {
    	return thePlayer;
    }
    
    public boolean uncraftItem()
    {
    	List<ItemStack> results = getRecipesInversed(tableInventory.getStackInSlot(0));
    	if(results != null)
    	{
    		if(tableInventory.getStackInSlot(0).isItemEnchanted())
    		{
    			return false;
    		}
    		
	        if(tableInventory.getStackInSlot(0).isItemDamaged())
	        {
	        	return false;
	        }
    		
    		if(PlayerUtils.inventoryHavePlace(thePlayer, results))
    		{
    			for(ItemStack is : results)
    			{
        			thePlayer.inventory.addItemStackToInventory(is);
    			}
    			
    			tableInventory.setInventorySlotContents(0, ItemStack.EMPTY);
    			return true;
    		}
    	}
		return false;	
    }
    
    private boolean isValidItem(ItemStack is)
    {
    	if(is.isItemEnchanted() || is.isItemDamaged()) return false;
    	
		return true;
    }
    
    
    public List<ItemStack> getRecipesInversed(ItemStack is)
    {
    	for(WikiRecipes recipe : WikiRecipes.loadedRecipes)
    	{
    		ItemStack[] results = recipe.getRecipe();

    		if(recipe.getResult() != null && recipe.getResult().getItem() == is.getItem() && recipe.getResult().getCount() == is.getCount())
    		{
    			List<ItemStack> items = new ArrayList<>();
    			
    			for(int i = 0; i < results.length; i++)
    			{
    				if(results[i] != null)
    				{
    					ItemStack giveIs = results[i].copy();
    					giveIs.setCount(is.getCount());
    					items.add(giveIs);
    				}
    			}
    			
    			if(items.size() > 0)
    			{
    				return items;
    			}
    		}
    	}
    	return null;
    }

}
