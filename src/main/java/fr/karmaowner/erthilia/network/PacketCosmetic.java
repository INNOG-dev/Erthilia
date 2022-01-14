package fr.karmaowner.erthilia.network;

import java.util.ArrayList;
import java.util.List;

import fr.karmaowner.erthilia.Main;
import fr.karmaowner.erthilia.capability.playercapability.ErthiliaPlayer;
import fr.karmaowner.erthilia.capability.playercapability.ErthiliaPlayerProvider;
import fr.karmaowner.erthilia.capability.playercapability.IPlayer;
import fr.karmaowner.erthilia.cosmetics.CompanionObject;
import fr.karmaowner.erthilia.cosmetics.CosmeticCachedData;
import fr.karmaowner.erthilia.cosmetics.CosmeticObject;
import fr.karmaowner.erthilia.gui.CompanionUI;
import fr.karmaowner.erthilia.gui.CosmetiqueUI;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PacketCosmetic extends PacketBase {
	
	/*
	 * type = 0 equipCosmetic
	 * type = 1 unequipCosmetic
	 * type = 2 unlock cosmetic
	 * type = 3 open interface
	 * type = 4 update renders
	 * type = 5 display dialogBox (only for companionUI)
	 * type = 6 feed companion
	 */
	public byte type;
	
	public byte interfaceId;
	
	public int cosmeticId;
	
	//Only for companion
	public int selectedEffectIndex = -1;
	
	public int entityId;
	
	public byte dialogBoxType;
	
	
	public List<CosmeticObject> cosmeticsToSynchronise = new ArrayList<CosmeticObject>();
	
	
	public PacketCosmetic()
	{
				
	}		
	
			
	public PacketCosmetic(byte type, int id)
	{
		this.type = type;
		this.cosmeticId = id;
	}
	
	public PacketCosmetic(byte type)
	{
		this.type = type;
	}
	
	public static PacketCosmetic openInterface(byte interfaceId)
	{
		PacketCosmetic packet = new PacketCosmetic((byte)3);
		packet.interfaceId = interfaceId;
		return packet;
	}
	
	public static PacketCosmetic equipCompanion(int companionId, int selectedEffectIndex)
	{
		PacketCosmetic packet = new PacketCosmetic((byte)0);
		packet.cosmeticId = companionId;
		packet.selectedEffectIndex = selectedEffectIndex;
		
		return packet;
	}
	
	public static PacketCosmetic unequipCompanion(int companionId)
	{
		PacketCosmetic packet = new PacketCosmetic((byte)1);
		packet.cosmeticId = companionId;
		
		return packet;
	}
	
	public static PacketCosmetic displayDialogBox(byte type)
	{
		PacketCosmetic packet = new PacketCosmetic((byte)5);
		packet.dialogBoxType = type;		
		return packet;
	}
	
	public static PacketCosmetic feedCompanion(int companionId)
	{
		PacketCosmetic packet = new PacketCosmetic((byte)6);
		packet.cosmeticId = companionId;		
		return packet;
	}
	
	public PacketCosmetic putList(List<CosmeticObject> list)
	{
		this.cosmeticsToSynchronise = list;
		return this;
	}

	@Override
	public void encodeInto(ChannelHandlerContext ctx, ByteBuf data) {
		data.writeByte(type);		
		if(type == 3)
		{
			data.writeByte(interfaceId);
			data.writeInt(cosmeticsToSynchronise.size());
			for(CosmeticObject obj : cosmeticsToSynchronise)
			{
				data.writeInt(obj.getId());
				data.writeBoolean(obj.getIsLocked());
				data.writeBoolean(obj.getIsEquipped());
			}
		}
		else if(type == 4)
		{
			data.writeInt(entityId);
			data.writeInt(cosmeticsToSynchronise.size());
			for(CosmeticObject obj : cosmeticsToSynchronise)
			{
				data.writeInt(obj.getId());
				data.writeBoolean(obj.getIsEquipped());
			}
		}
		else if(type == 5)
		{
			data.writeByte(dialogBoxType);
		}
		else if(type == 6)
		{
			data.writeInt(cosmeticId);
		}
		else
		{
			data.writeInt(cosmeticId);
			data.writeInt(selectedEffectIndex);
		}
	}

	@Override
	public void decodeInto(ChannelHandlerContext ctx, ByteBuf data) {
		type = data.readByte();
		if(type == 3)
		{
			interfaceId = data.readByte();
			int cosmeticCount = data.readInt();
			for(int i = 0; i < cosmeticCount; i++)
			{
				int id = data.readInt();
				boolean locked = data.readBoolean();
				boolean equipped = data.readBoolean();
				CosmeticObject obj = Main.getCosmeticsManager().getCopy(id);
				obj.setLocked(locked);
				obj.setEquipped(equipped);
				cosmeticsToSynchronise.add(obj);
			}
		}
		else if(type == 4)
		{
			entityId = data.readInt();
			int cosmeticCount = data.readInt();
			for(int i = 0; i < cosmeticCount; i++)
			{
				int id = data.readInt();
				boolean equipped = data.readBoolean();
				CosmeticObject obj = Main.getCosmeticsManager().getCopy(id);
				obj.setEquipped(equipped);
				cosmeticsToSynchronise.add(obj);
			}
		}
		else if(type == 5)
		{
			dialogBoxType = data.readByte();
		}
		else if(type == 6)
		{
			cosmeticId = data.readInt();
		}
		else
		{
			cosmeticId = data.readInt();
			selectedEffectIndex = data.readInt();
		}
	}

	@Override
	public void handleServerSide(EntityPlayerMP playerEntity) {
		if(type == 0) 
		{
			CompanionObject.equipCompanion(playerEntity,selectedEffectIndex, cosmeticId);
		}
		else if(type == 1)
		{
			CompanionObject.unequipCompanion(playerEntity, cosmeticId);
		}
		else if(type == 3)
		{
			if(interfaceId == 0)
			{
				((ErthiliaPlayer)playerEntity.getCapability(ErthiliaPlayerProvider.PLAYER_CAP, null)).syncCosmetics();
			}
			else
			{
				((ErthiliaPlayer)playerEntity.getCapability(ErthiliaPlayerProvider.PLAYER_CAP, null)).syncCompanion();
			}
		}
		else if(type == 4)
		{
			playerEntity.getCapability(ErthiliaPlayerProvider.PLAYER_CAP, null).updateRenderer();
		}
		else if(type == 6)
		{
			CompanionObject.feedCompanion(playerEntity, cosmeticId);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void handleClientSide(EntityPlayer clientPlayer) {
		if(type == 0)
		{
			CompanionObject.equipCompanion(clientPlayer,selectedEffectIndex, cosmeticId);
		}
		else if(type == 1)
		{
			CompanionObject.unequipCompanion(clientPlayer, cosmeticId);
		}
		else if(type == 2)
		{
			CosmeticObject.setCosmetiqueUnlocked(clientPlayer,cosmeticId);
		}
		else if(type == 3)
		{
			IPlayer ep = (IPlayer) clientPlayer.getCapability(ErthiliaPlayerProvider.PLAYER_CAP, null);
			ep.setCosmeticsData(cosmeticsToSynchronise);;
			
			if(interfaceId == 0)
			{
				Minecraft.getMinecraft().displayGuiScreen(new CosmetiqueUI(ep));
			}
			else
			{
				Minecraft.getMinecraft().displayGuiScreen(new CompanionUI(ep));
			}
		}
		else if(type == 4)
		{
			
			if(clientPlayer.getEntityId() == entityId)
			{
				clientPlayer.getCapability(ErthiliaPlayerProvider.PLAYER_CAP, null).setCosmeticsData(cosmeticsToSynchronise);
				return;
			}
			
			CosmeticCachedData data = CosmeticCachedData.getData(entityId);
			data.cosmeticsData = cosmeticsToSynchronise;
		}
		else if(type == 5)
		{
			if(Minecraft.getMinecraft().currentScreen instanceof CompanionUI)
			{
				CompanionUI companionUI = (CompanionUI) Minecraft.getMinecraft().currentScreen;
				companionUI.dispayDialogBox(dialogBoxType);
			}
		}
	}
	
}