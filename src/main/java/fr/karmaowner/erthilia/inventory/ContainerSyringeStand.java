package fr.karmaowner.erthilia.inventory;

import fr.karmaowner.erthilia.items.ErthiliaItems;
import fr.karmaowner.erthilia.tiles.TileEntitySyringeStand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ContainerSyringeStand extends Container
{
    private IInventory tileSyringeStand;
   
    private final Slot theSlot;
    
    private int prevBrewTime;
    
    public ContainerSyringeStand(InventoryPlayer inventory, TileEntitySyringeStand tile)
    {
        this.tileSyringeStand = tile;
        this.addSlotToContainer(new ContainerSyringeStand.Syringe(inventory.player, tile, 0, 56, 46));
        this.addSlotToContainer(new ContainerSyringeStand.Syringe(inventory.player, tile, 1, 102, 46));
        this.theSlot = this.addSlotToContainer(new ContainerSyringeStand.Ingredient(tile, 2, 79, 17));
        int i;

        for (i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                addSlotToContainer(new Slot(inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
    
        }

        for (i = 0; i < 9; ++i)
        {
            addSlotToContainer(new Slot(inventory, i, 8 + i * 18, 142));
        }
    }
    
    public void addListener(IContainerListener listener)
    {
        super.addListener(listener);
        listener.sendAllWindowProperties(this, this.tileSyringeStand);
    }
    
    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();

        for (int i = 0; i < this.listeners.size(); ++i)
        {
            IContainerListener icontainerlistener = this.listeners.get(i);

            if (prevBrewTime != tileSyringeStand.getField(0))
            {
                icontainerlistener.sendWindowProperty(this, 0, tileSyringeStand.getField(0));
            }
        }

        prevBrewTime = tileSyringeStand.getField(0);
    }
    
    
    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int id, int data)
    {
        this.tileSyringeStand.setField(id, data);
    }
    
    public boolean canInteractWith(EntityPlayer playerIn)
    {
        return this.tileSyringeStand.isUsableByPlayer(playerIn);
    }


    public ItemStack transferStackInSlot(EntityPlayer player, int index)
    {
        ItemStack itemstack = ItemStack.EMPTY;
        
        Slot slot = (Slot)this.inventorySlots.get(index);
        
        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if ((index < 0 || index > 1) && index != 2) 
            {
                if (!theSlot.getHasStack() && theSlot.isItemValid(itemstack1))
                {
                    if (!this.mergeItemStack(itemstack1, 2, 3, false))
                    {
                        return ItemStack.EMPTY;
                    }
                }
                else if (ContainerSyringeStand.Syringe.canHoldSyringe(itemstack))
                {
                    if (!this.mergeItemStack(itemstack1, 0, 3, false))
                    {
                        return ItemStack.EMPTY;
                    }
                }
                else if (index >= 3 && index < 30)
                {
                    if (!this.mergeItemStack(itemstack1, 30, 39, false))
                    {
                        return ItemStack.EMPTY;
                    }
                }
                else if (index >= 30 && index < 39)
                {
                    if (!this.mergeItemStack(itemstack1, 3, 30, false))
                    {
                        return ItemStack.EMPTY;
                    }
                }
                else if (!this.mergeItemStack(itemstack1, 3, 39, false))
                {
                    return ItemStack.EMPTY;
                }
            }
            else
            {
                if (!this.mergeItemStack(itemstack1, 3, 39, true))
                {
                    return ItemStack.EMPTY;
                }

                slot.onSlotChange(itemstack1, itemstack);
            }

            if (itemstack1.getCount() == 0)
            {
                slot.putStack((ItemStack)ItemStack.EMPTY);
            }
            else
            {
                slot.onSlotChanged();
            }

            if (itemstack1.getCount() == itemstack.getCount())
            {
                return ItemStack.EMPTY;
            }

            slot.onTake(player, itemstack1);
        }

        return itemstack;
    }

    class Ingredient extends Slot
    {

        public Ingredient(IInventory p_i1803_2_, int p_i1803_3_, int p_i1803_4_, int p_i1803_5_)
        {
            super(p_i1803_2_, p_i1803_3_, p_i1803_4_, p_i1803_5_);
        }

        public boolean isItemValid(ItemStack itemstack)
        {
            return net.minecraftforge.common.brewing.BrewingRecipeRegistry.isValidIngredient(itemstack);
        }


        public int getSlotStackLimit()
        {
            return 64;
        }
    }

    static class Syringe extends Slot
    {
            public Syringe(EntityPlayer p_i1804_1_, IInventory p_i1804_2_, int p_i1804_3_, int p_i1804_4_, int p_i1804_5_)
            {
                super(p_i1804_2_, p_i1804_3_, p_i1804_4_, p_i1804_5_);
            }

            public boolean isItemValid(ItemStack p_75214_1_)
            {
                return canHoldSyringe(p_75214_1_);
            }

 
            public int getSlotStackLimit()
            {
                return 1;
            }


            public static boolean canHoldSyringe(ItemStack itemstack)
            {
                return itemstack != null && (itemstack.getItem() == ErthiliaItems.EMPTY_SYRINGE || itemstack.getItem() == ErthiliaItems.WATER_SYRINGE);
            }
    }
}