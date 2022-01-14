package fr.karmaowner.erthilia.cosmetics;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import fr.karmaowner.erthilia.Main;
import fr.karmaowner.erthilia.capability.playercapability.ErthiliaPlayer;
import fr.karmaowner.erthilia.network.PacketCosmetic;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.AxisAlignedBB;


/*Chaque joueur côté serveur posséde une instance de ce type de class
 *Elle permet de transmettre ses données cosmetics à tous les clients dans un rayon de 30 blocks 
 */
public class CosmeticsRenderData {
	
	private final int renderRadius = 30;
	
	@SuppressWarnings("unused")
	private final int updateTime = 2; //In Seconds
	
	private ErthiliaPlayer ep;
	
	private List<EntityPlayer> previousPlayersInArea = new ArrayList<EntityPlayer>();

	private List<EntityPlayer> playersInArea = new ArrayList<EntityPlayer>();
	
	private long lastTime;
	
	public CosmeticsRenderData(ErthiliaPlayer ep)
	{
		this.ep = ep;
	}
	
	public void update()
	{		
		if(System.currentTimeMillis() - lastTime / 1000 >= 2)
		{
			lastTime = System.currentTimeMillis();
			EntityPlayer player = ep.getPlayer();
			previousPlayersInArea = playersInArea;
			playersInArea =  player.world.getEntitiesWithinAABB(EntityPlayer.class, new AxisAlignedBB(player.posX-renderRadius, player.posY-renderRadius, player.posZ-renderRadius, player.posX + renderRadius, player.posY + renderRadius, player.posZ+renderRadius));
		}
		updateRenderDataForPlayers(getPlayersToUpdate());
	}
	
	private List<EntityPlayer> getPlayersToUpdate()
	{
		return playersInArea.stream().filter(x -> !previousPlayersInArea.contains(x)).collect(Collectors.toList());
	}
	
	/*
	 * On récupére tous les joueurs à qui on a jamais encoré envoyé de packet ou qui n'a pas subit de changement au niveau des cosmétics
	 * Un packet qui contient tous la liste des cosmétiques équipé par le joueur maître de cette instance
	 */
	private void updateRenderDataForPlayers(List<EntityPlayer> playersToUpdate)
	{
		List<CosmeticObject> toSend = ep.getEquippedCosmetics();
		for(int i = 0; i < playersToUpdate.size(); i++)
		{

			EntityPlayer player = playersToUpdate.get(i);
			
			if(player.getGameProfile().getName().equalsIgnoreCase(ep.getPlayer().getGameProfile().getName())) continue;
									
			
			//On envoie le packet d'update de rendu à tous les joueurs dans un rayon de 30 blocks
			PacketCosmetic packet = new PacketCosmetic((byte) 4);
			packet.cosmeticsToSynchronise = toSend;
			packet.entityId = ep.getPlayer().getEntityId();
			Main.getPacketHandler().sendTo(packet,(EntityPlayerMP)player);
		}
	}
	
	public void clear()
	{
		previousPlayersInArea.clear();
		playersInArea.clear();
		List<CosmeticObject> toSend = ep.getEquippedCosmetics();
		PacketCosmetic packet = new PacketCosmetic((byte) 4);
		packet.cosmeticsToSynchronise = toSend;
		packet.entityId = ep.getPlayer().getEntityId();
		Main.getPacketHandler().sendTo(packet,(EntityPlayerMP)ep.getPlayer());
	}
	
}
