package fr.karmaowner.erthilia.data;

import fr.karmaowner.erthilia.Main;
import fr.karmaowner.erthilia.hdv.HdvManager;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.storage.WorldSavedData;

public class WorldDataManager extends WorldSavedData {
	
	
	private static final String DATA_NAME = Main.MODID + "_Erthilia";

	private World worldObj;
	
	private HdvManager hdvManager = new HdvManager();
	
	public WorldDataManager() {
		super(DATA_NAME);
	}
	
	public WorldDataManager(String s) {
		super(s);
	}

	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		NBTTagCompound hdvTag = (NBTTagCompound) compound.getTag("HdvTag");
		hdvManager.readFromNbt(hdvTag);		
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) 
	{
		NBTTagCompound hdvTag = new NBTTagCompound();
		hdvManager.writeToNbt(hdvTag);
		
		compound.setTag("HdvTag", hdvTag);
		
		return compound;
	}
	
	public HdvManager getHdvManager()
	{
		return hdvManager;
	}
	
	
	public static WorldDataManager get(World world) {

		  WorldDataManager instance = (WorldDataManager) world.loadData(WorldDataManager.class, DATA_NAME);

		  if (instance == null) {
		     instance = new WorldDataManager();
			 instance.setWorldObj(world);

		     world.setData(DATA_NAME, instance);
		  }
		  else
		  {
			  instance.setWorldObj(world);
		  }
		  
		  return instance;
	}

	public World getWorldObj() {
		return worldObj;
	}

	public void setWorldObj(World worldObj) {
		this.worldObj = worldObj;
	}

}
