package fr.karmaowner.erthilia.tiles;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityReversedEnchantingTable extends TileEntity  {
	
	    
	    private String name;

	    @Override
	    public NBTTagCompound writeToNBT(NBTTagCompound compound)
	    {
	        super.writeToNBT(compound);

	        if (this.hasCustomName())
	        {
	            compound.setString("CustomName", name);
	        }

	        return compound;
	    }
	    
	    @Override
	    public void readFromNBT(NBTTagCompound compound)
	    {
	        super.readFromNBT(compound);

	        if (compound.hasKey("CustomName", 8))
	        {
	            this.name = compound.getString("CustomName");
	        }
	    }

	    public String getName()
	    {
	        return this.hasCustomName() ? this.name : "container.reversedenchantingtable";
	    }
	    
	    public boolean hasCustomName()
	    {
	        return this.name != null && !this.name.isEmpty();
	    }

	    public void setName(String name)
	    {
	        this.name = name;
	    }


}
