package fr.karmaowner.erthilia.capability.playercapability;

import java.util.ArrayList;
import java.util.List;

import fr.karmaowner.erthilia.Main;
import fr.karmaowner.erthilia.cosmetics.CosmeticManager;
import fr.karmaowner.erthilia.cosmetics.CosmeticObject;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class ErthiliaPlayerStorage  implements IStorage<IPlayer> 
{

	@Override
	public NBTBase writeNBT(Capability<IPlayer> capability, IPlayer instance, EnumFacing side) {
		NBTTagCompound compound = new NBTTagCompound();
		
    	NBTTagList cosmeticTags = new NBTTagList();
    	
    	for(CosmeticObject obj : instance.getCosmeticsDatas())
    	{  	
    		NBTTagCompound cosmeticTag = new NBTTagCompound();
    		obj.writeToNBT(cosmeticTag);
    		cosmeticTags.appendTag(cosmeticTag);
    	}
    	
    	compound.setTag("Cosmetics", cosmeticTags);
    			
		return compound;
	}

	@Override
	public void readNBT(Capability<IPlayer> capability, IPlayer instance, EnumFacing side, NBTBase nbt) 
	{
		NBTTagCompound compound = (NBTTagCompound) nbt;
		
		NBTTagList cosmeticTags = (NBTTagList) compound.getTag("Cosmetics");
		
		List<CosmeticObject> cosmetics = new ArrayList<CosmeticObject>();
		
		int j = 0;
		CosmeticManager manager = Main.getCosmeticsManager();
		for(CosmeticObject cosmeticObj : manager.getCosmetics())
		{
			CosmeticObject copy = manager.getCopy(cosmeticObj);
			cosmetics.add(copy);
			
    		NBTTagCompound tag = cosmeticTags.getCompoundTagAt(j);
    		copy.loadNBTData(tag); 
    		
    		++j;
		}
		
		instance.setCosmeticsData(cosmetics);
	}


	
}