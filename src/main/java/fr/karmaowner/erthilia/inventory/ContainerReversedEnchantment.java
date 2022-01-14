package fr.karmaowner.erthilia.inventory;

import fr.karmaowner.erthilia.blocks.ErthiliaBlocks;
import fr.karmaowner.erthilia.utils.ItemStackUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ContainerReversedEnchantment extends Container {

	public IInventory tableInventory;
    
    private World worldPointer;
    private final BlockPos position;

    public int xpCount;
    
    private EntityPlayer user;
    

    public ContainerReversedEnchantment(InventoryPlayer playerInv, World worldIn, BlockPos pos)
    {
    	user = playerInv.player;
        this.worldPointer = worldIn;
        position = pos;
        
        tableInventory = new InventoryBasic("Enchant", true, 1)
        {
            public int getInventoryStackLimit()
            {
                return 1;
            }
    
            
            public void markDirty()
            {
                super.markDirty();
                ContainerReversedEnchantment.this.onCraftMatrixChanged(this);
            }
            
        };
                
        this.addSlotToContainer(new Slot(this.tableInventory, 0, 25, 47)
        {
            public boolean isItemValid(ItemStack p_75214_1_)
            {
                return true;
            }
            
            public int getSlotStackLimit()
            {
                return 1;
            }
        });
        
        int l;

        for (l = 0; l < 3; ++l)
        {
            for (int i1 = 0; i1 < 9; ++i1)
            {
                this.addSlotToContainer(new Slot(playerInv, i1 + l * 9 + 9, 8 + i1 * 18, 84 + l * 18));
            }
        }

        for (l = 0; l < 9; ++l)
        {
            this.addSlotToContainer(new Slot(playerInv, l, 8 + l * 18, 142));
        }
    }


    @Override
    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void updateProgressBar(int p_75137_1_, int p_75137_2_)
    {
        super.updateProgressBar(p_75137_1_, p_75137_2_);
    }

 
    @Override
    public void onCraftMatrixChanged(IInventory inventoryIn)
    {
        if (inventoryIn == this.tableInventory)
        {
            ItemStack itemstack = inventoryIn.getStackInSlot(0);
                        

            if(itemstack == null) return;
            
  
            if (ItemStackUtils.isItemTools(itemstack) && itemstack.isItemEnchanted())
            {
            	this.calculateXp();
                if (!this.worldPointer.isRemote)
                {
                    this.detectAndSendChanges();
                }
            }
            else
            {
            	this.xpCount = -1;
            }
        }
    }

    
    @Override
    public void onContainerClosed(EntityPlayer playerIn)
    {
        super.onContainerClosed(playerIn);

        if (!this.worldPointer.isRemote)
        {
            this.clearContainer(playerIn, playerIn.world, this.tableInventory);
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn)
    {
        if (this.worldPointer.getBlockState(this.position).getBlock() != ErthiliaBlocks.REVERSE_ENCHANTING_TABLE)
        {
            return false;
        }
        else
        {
            return playerIn.getDistanceSq((double)this.position.getX() + 0.5D, (double)this.position.getY() + 0.5D, (double)this.position.getZ() + 0.5D) <= 64.0D;
        }
    }

    
    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index)
    {
    	ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);

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

                if (itemstack1.hasTagCompound())// Forge: Fix MC-17431
                {
                    ((Slot)this.inventorySlots.get(0)).putStack(itemstack1.splitStack(1));
                }
                else if (!itemstack1.isEmpty())
                {
                    ((Slot)this.inventorySlots.get(0)).putStack(new ItemStack(itemstack1.getItem(), 1, itemstack1.getMetadata()));
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
    	return user.experienceLevel >= this.xpCount || user.capabilities.isCreativeMode;
    }
    
    public void calculateXp()
    {
    	int xpCount = 0;
    	ItemStack is = tableInventory.getStackInSlot(0);
    	if(is != null)
    	{
    		for(int i = 0; i < is.getEnchantmentTagList().tagCount(); i++)
    		{
    			xpCount += this.getXpFromEnchantmentLevel(is.getEnchantmentTagList().getCompoundTagAt(i).getShort("lvl"));
    		}
    	}
    	this.xpCount = xpCount;
    }
    
    public int getXpFromEnchantmentLevel(short lvl)
    {
    	if(lvl == 1)
    	{
    		return 3;
    	}
    	else if(lvl == 2)
    	{
    		return 7;
    	}
    	else if(lvl == 3)
    	{
    		return 15;
    	}
    	else
    	{
    		return 30;
    	}
    }
    
    public boolean clearEnchantment()
    {
    	ItemStack is = tableInventory.getStackInSlot(0);
    	
    	if(is == ItemStack.EMPTY) return false;

    	is.getTagCompound().removeTag("ench");

    	onCraftMatrixChanged(tableInventory);
    	return true;
    }
    
}
