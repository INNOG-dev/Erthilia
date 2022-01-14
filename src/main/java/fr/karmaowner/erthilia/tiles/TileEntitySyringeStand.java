package fr.karmaowner.erthilia.tiles;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.PotionTypes;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerBrewingStand;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionHelper;
import net.minecraft.potion.PotionType;
import net.minecraft.potion.PotionUtils;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;

public class TileEntitySyringeStand extends TileEntityLockable implements ISidedInventory, ITickable
{

    private static final int[] OUTPUT_SLOTS = new int[] {0, 1};
    
	public BlockPos masterTilePos;
	
	public boolean isActiveTile;

    private NonNullList<ItemStack> standItemStacks = NonNullList.<ItemStack>withSize(3, ItemStack.EMPTY);

    private int brewTime;
    private Item ingredientID;
    private String customName;


    public String getName()
    {
        return this.hasCustomName() ? this.customName : "container.syringe_stand";
    }

    public boolean hasCustomName()
    {
        return this.customName != null && !this.customName.isEmpty();
    }
    
    public void setName(String name)
    {
        this.customName = name;
    }
    
    public int getSizeInventory()
    {
        return this.standItemStacks.size();
    }
    
    public boolean isEmpty()
    {
        for (ItemStack itemstack : this.standItemStacks)
        {
            if (!itemstack.isEmpty())
            {
                return false;
            }
        }

        return true;
    }
    
	@Override
	public void update()
	{
		 if(!isActiveTile)
		 {
			 return;
		 }
		 
		 boolean flag  = canBrew();
	     boolean flag1 = brewTime > 0;
	     ItemStack itemstack1 = standItemStacks.get(2);
	     
	   
	     
	     if (flag1)
	     {
	    	 --brewTime;
	    	 
	         boolean flag2 = brewTime == 0;

	         if (flag2 && flag)
	         {
	              brewSyringes();
	              markDirty();
	         }
	         else if (!flag)
	         {
	              brewTime = 0;
	              markDirty();
	         }
	         else if (ingredientID != itemstack1.getItem())
	         {
	              brewTime = 0;
	              markDirty();
	         }
	    }
	    else if (flag)
	    {
	        brewTime = 200;
	        ingredientID = itemstack1.getItem();
	        markDirty();
	    }	 
	}
	
	 private void brewSyringes()
	 {
	     if (net.minecraftforge.event.ForgeEventFactory.onPotionAttemptBrew(standItemStacks)) return;
	     
	     ItemStack itemstack = this.standItemStacks.get(2);

	     net.minecraftforge.common.brewing.BrewingRecipeRegistry.brewPotions(standItemStacks, standItemStacks.get(2), OUTPUT_SLOTS);

	     itemstack.shrink(4);
	     
	     BlockPos blockpos = this.getPos();

	     if (itemstack.getItem().hasContainerItem(itemstack))
	     {
	    	 ItemStack itemstack1 = itemstack.getItem().getContainerItem(itemstack);

	         if (itemstack.isEmpty()) 
	         {
	        	 itemstack = itemstack1;
	         }
	         else
	         {
	             InventoryHelper.spawnItemStack(this.world, (double)blockpos.getX(), (double)blockpos.getY(), (double)blockpos.getZ(), itemstack1);
	         }
	     }
	     

	     
	     this.standItemStacks.set(2, itemstack);
	     

	      for(int index : OUTPUT_SLOTS)
	      {
	    	  ItemStack is = standItemStacks.get(index);
	    	  PotionType type = PotionUtils.getPotionTypeFromNBT(is.getTagCompound());
	    	  if(type != PotionTypes.AWKWARD && type != PotionTypes.THICK && type != PotionTypes.MUNDANE)
	    	  {
		    	  if(is.isEmpty()) continue;
		    	  
		    	  if(!is.hasTagCompound())
		    	  {
		    		  is.setTagCompound(new NBTTagCompound());
		    	  }
		    	  
		    	  is.getTagCompound().setInteger("EffectDurability", 4);
	    	  }
	      }
	     
	     this.world.playEvent(1035, blockpos, 0);
	     net.minecraftforge.event.ForgeEventFactory.onPotionBrewed(standItemStacks);

	 }
	 
	 private boolean canBrew()
	 {
	     ItemStack itemstack = this.standItemStacks.get(2);

	     if (itemstack.isEmpty() || itemstack.getCount() < 4)
	     {
	   		return false;
	     }
	     else if (!PotionHelper.isReagent(itemstack))
	     {
	    	 return false;
	     }
	     else
	     {
	    	 for (int i = 0; i < 2; ++i)
	         {
	    		 ItemStack itemstack1 = this.standItemStacks.get(i);

	    		 if (!itemstack1.isEmpty() && PotionHelper.hasConversions(itemstack1, itemstack))
	    		 {
	    			 return true;
	             }
	         }
	         return false;
	     }
	  }
	

    @Override
    public void readFromNBT(NBTTagCompound compound)
    {      
    	super.readFromNBT(compound);
    	
        if(compound.hasKey("PosX"))
        {
        	masterTilePos = new BlockPos(compound.getInteger("PosX"),compound.getInteger("PosY"),compound.getInteger("PosZ"));
        }
        
        
        isActiveTile = compound.getBoolean("ActiveTile");
        
        if(isActiveTile)
        { 
            brewTime = compound.getShort("BrewTime");
            
            standItemStacks = NonNullList.<ItemStack>withSize(this.getSizeInventory(), ItemStack.EMPTY);
            ItemStackHelper.loadAllItems(compound, this.standItemStacks);

            
            if (compound.hasKey("CustomName"))
            {
                customName = compound.getString("CustomName");
            }
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {  
        super.writeToNBT(compound);

        if(masterTilePos != null)
        {
	        compound.setInteger("PosX", (int) masterTilePos.getX());
	        compound.setInteger("PosY", (int) masterTilePos.getY());
	        compound.setInteger("PosZ", (int) masterTilePos.getZ());
        }


        if(isActiveTile)
        {
        	compound.setBoolean("ActiveTile", true);
            compound.setShort("BrewTime", (short)brewTime);
           
            ItemStackHelper.saveAllItems(compound, standItemStacks);
 	        
 	        if (hasCustomName())
 	        {
 	            compound.setString("CustomName", customName);
 	        }
 	        
        }
        
        return compound;

    }
    

    public ItemStack getStackInSlot(int index)
    {
        return index >= 0 && index < this.standItemStacks.size() ? (ItemStack)this.standItemStacks.get(index) : ItemStack.EMPTY;
    }
    
    public ItemStack decrStackSize(int index, int count)
    {
        return ItemStackHelper.getAndSplit(this.standItemStacks, index, count);
    }

    public ItemStack removeStackFromSlot(int index)
    {
        return ItemStackHelper.getAndRemove(this.standItemStacks, index);
    }
    
    public void setInventorySlotContents(int index, ItemStack stack)
    {
        if (index >= 0 && index < this.standItemStacks.size())
        {
            this.standItemStacks.set(index, stack);
        }
    }

    public int getInventoryStackLimit()
    {
        return 64;
    }
    
    public boolean isUsableByPlayer(EntityPlayer player)
    {
        if (this.world.getTileEntity(this.pos) != this)
        {
            return false;
        }
        else
        {
            return player.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D;
        }
    }

    public void openInventory(EntityPlayer player) { }

    public void closeInventory(EntityPlayer player) { }
    
    public boolean isItemValidForSlot(int index, ItemStack stack)
    {
        if (index == 2)
        {
            return net.minecraftforge.common.brewing.BrewingRecipeRegistry.isValidIngredient(stack);
        }
        else
        {
            return net.minecraftforge.common.brewing.BrewingRecipeRegistry.isValidInput(stack) && this.getStackInSlot(index).isEmpty();
        }
    }

    public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction)
    {
        return this.isItemValidForSlot(index, itemStackIn);
    }

    public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction)
    {
        if (index == 3)
        {
            return stack.getItem() == Items.GLASS_BOTTLE;
        }
        else
        {
            return true;
        }
    }
    
    public String getGuiID()
    {
        return "erthilia:syringe_stand";
    }
	
	public boolean isMasterTile()
	{
		return masterTilePos == null;
	}
	
	public TileEntitySyringeStand getMasterTile()
	{
		if(masterTilePos == null)
		{
			return null;
		}
		
		return (TileEntitySyringeStand) world.getTileEntity(masterTilePos);
	}
	
    public int getField(int id)
    {
        switch (id)
        {
            case 0:
                return this.brewTime;
            default:
                return 0;
        }
    }

    public void setField(int id, int value)
    {
        switch (id)
        {
            case 0:
                this.brewTime = value;
                break;
        }
       
    }
    


    public int getFieldCount()
    {
        return 1;
    }

    public void clear()
    {
        this.standItemStacks.clear();
    }

	@Override
	public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) 
	{
        return new ContainerBrewingStand(playerInventory, this);
	}

	@Override
	public int[] getSlotsForFace(EnumFacing side)
	{
		return null;
	}
	

}
