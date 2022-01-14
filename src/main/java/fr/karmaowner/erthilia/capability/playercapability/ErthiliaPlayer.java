package fr.karmaowner.erthilia.capability.playercapability;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import fr.karmaowner.erthilia.Main;
import fr.karmaowner.erthilia.cosmetics.CosmeticManager;
import fr.karmaowner.erthilia.cosmetics.CosmeticObject;
import fr.karmaowner.erthilia.cosmetics.CosmeticsRenderData;
import fr.karmaowner.erthilia.data.SyncPlayerSpigotData;
import fr.karmaowner.erthilia.hdv.HdvItem;
import fr.karmaowner.erthilia.network.PacketCosmetic;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class ErthiliaPlayer implements IPlayer 
{

    private EntityPlayer player = null;
	
    private CosmeticsRenderData cosmeticsRenderer;
    
    public SyncPlayerSpigotData spigotData;
    
    public HdvItem selectedItem;
        
    public List<CosmeticObject> cosmeticsData = new ArrayList<CosmeticObject>();
	
	public List<CosmeticObject> getCosmeticsDatas()
	{
		return cosmeticsData;
	}

	public List<CosmeticObject> getEquippedCosmetics() 
	{
		return cosmeticsData.stream().filter(x -> x.getIsEquipped()).collect(Collectors.toList());
	}

	public EntityPlayer getPlayer() 
	{
		return player;
	}

	@Override
	public void initData(Entity entity) 
	{
		player = (EntityPlayer) entity;
        cosmeticsRenderer = new CosmeticsRenderData(this);
        
		CosmeticManager manager = Main.getCosmeticsManager();
		
		for(CosmeticObject cosmeticObj : manager.getCosmetics())
		{
			CosmeticObject copy = manager.getCopy(cosmeticObj);
			cosmeticsData.add(copy);
		}
	}

	@Override
	public void setCosmeticsData(List<CosmeticObject> cosmetics) 
	{
		cosmeticsData = cosmetics;
	}
	
	@Override
	public HdvItem getSelectedHdvItem() 
	{
		return selectedItem;
	}
	
	@Override
	public void setSelectedHdvItem(HdvItem item) 
	{
		selectedItem = item;
	}


	@Override
	public void permute(IPlayer old) 
	{
		initData(old.getPlayer());
		
		cosmeticsData = old.getCosmeticsDatas();
		selectedItem = old.getSelectedHdvItem();
	}

	@Override
	public SyncPlayerSpigotData getSyncSpigotData() 
	{
		return spigotData;
	}

	@Override
	public void updateRenderer() 
	{
		if(player.world.isRemote)
		{
			Main.getPacketHandler().sendToServer(new PacketCosmetic((byte)4));
		}
		else
		{
			cosmeticsRenderer.clear();
			System.out.println("Update render");
		}
	}
	
	public void syncCosmetics() {
		PacketCosmetic packet = new PacketCosmetic((byte)3);
		packet.interfaceId = 0;
		packet.putList(cosmeticsData.stream().filter(x -> x.getType() != 3).collect(Collectors.toList()));
		Main.getPacketHandler().sendTo(packet,(EntityPlayerMP) player);
	}
	
	public void syncCompanion()
	{
		PacketCosmetic packet = new PacketCosmetic((byte)3);
		packet.interfaceId = 1;
		packet.putList(cosmeticsData.stream().filter(x -> x.getType() == 3).collect(Collectors.toList()));
		Main.getPacketHandler().sendTo(packet,(EntityPlayerMP) player);
	}
	
    public CosmeticObject getCosmeticById(int id)
    {
    	for(CosmeticObject obj : cosmeticsData)
    	{
    		if(obj.getId() == id)
    		{
    			return obj;
    		}
    	}
    	return null;
    }

	@Override
	public CosmeticsRenderData getCosmeticsRenderer() 
	{
		return cosmeticsRenderer;
	}

	@Override
	public void setPlayerSpigotData(SyncPlayerSpigotData data)
	{
		spigotData = data;
	}
    




}
