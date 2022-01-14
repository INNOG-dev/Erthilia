package fr.karmaowner.erthilia.capability.playercapability;

import java.util.List;

import fr.karmaowner.erthilia.cosmetics.CosmeticObject;
import fr.karmaowner.erthilia.cosmetics.CosmeticsRenderData;
import fr.karmaowner.erthilia.data.SyncPlayerSpigotData;
import fr.karmaowner.erthilia.hdv.HdvItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public interface IPlayer 
{


	
	public List<CosmeticObject> getCosmeticsDatas();

	public List<CosmeticObject> getEquippedCosmetics();
	
	public void setCosmeticsData(List<CosmeticObject> cosmetics);
	
	public HdvItem getSelectedHdvItem();
	
	public void setSelectedHdvItem(HdvItem item);
	
	public SyncPlayerSpigotData getSyncSpigotData();
	
	public void setPlayerSpigotData(SyncPlayerSpigotData data);

	public EntityPlayer getPlayer();
	
	public void updateRenderer();
	
	public CosmeticsRenderData getCosmeticsRenderer();

	public void initData(Entity entity);
	
	public void permute(IPlayer old);
	
}
