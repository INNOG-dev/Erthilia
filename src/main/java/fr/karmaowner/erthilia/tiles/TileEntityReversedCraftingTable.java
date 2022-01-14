package fr.karmaowner.erthilia.tiles;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;

public class TileEntityReversedCraftingTable extends TileEntity 
{
	
	    private String name;
	    
		public BlockPos masterTilePos;
		
		public boolean isActiveTile;

		

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
	           	 	        
	 	        if (hasCustomName())
	 	        {
	 	            compound.setString("CustomName", this.name);
	 	        }
	        }
	         

	        
	        
	        return compound; 
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
		        if (compound.hasKey("CustomName", 8))
		        {
		            this.name = compound.getString("CustomName");
		        }
	        }
	    }
	    
	    public String getName()
	    {
	        return this.hasCustomName() ? name : "container.reversedcraft";
	    }
	    
	    public boolean hasCustomName()
	    {
	        return name != null && !name.isEmpty();
	    }

	    
	    public void setName(String name)
	    {
	    	this.name = name;
	    }
	    
		public boolean isMasterTile()
		{
			return masterTilePos == null;
		}
		
		public TileEntityReversedCraftingTable getMasterTile()
		{
			if(masterTilePos == null)
			{
				return null;
			}
			
			return (TileEntityReversedCraftingTable) world.getTileEntity(masterTilePos);
		}
		


}
