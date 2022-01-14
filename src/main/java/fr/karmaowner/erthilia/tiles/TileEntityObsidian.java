package fr.karmaowner.erthilia.tiles;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityObsidian extends TileEntity {

	private int explodedCount;
	
	public int getExplodedCount()
	{
		return explodedCount;
	}
	
	public void setExplodedCount(int count)
	{
		explodedCount = count;
	}
	
	public void addExplodedCount(int count)
	{
		explodedCount += count;
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound)
	{
		super.writeToNBT(compound);
	        
		compound.setInteger("ExplodedCount", explodedCount);
	        
		return compound; 
	}

	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);
	        
		explodedCount = compound.getInteger("ExplodedCount");
	}

}
