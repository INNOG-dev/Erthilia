package fr.karmaowner.erthilia.network;

import java.util.List;

import fr.karmaowner.erthilia.Main;
import fr.karmaowner.erthilia.data.WorldDataManager;
import fr.karmaowner.erthilia.gui.HdvUI;
import fr.karmaowner.erthilia.hdv.HdvItem;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PacketHdv extends PacketPageTransmission<HdvItem> {
	
	/*
	 *  0: ask Items
	 *  1: send Items to clients
	 *  2: ask Items by keyword
	 *  3: ask Player items
	 *  4: ask Player items and keyword
	 *  5: select item
	 *  6: take item from hdv
	 */
	
	private int pageCount;
		
	private long itemId;
	
	private String keyWord;
	
	private String playerName;
	
	public static PacketHdv getItemsForPage(int page)
	{
		PacketHdv packet = new PacketHdv();
		packet.action = 0;
		packet.askedPage = page;
		return packet;
	}
	
	public static PacketHdv getItemsForPageByKeyWord(String keyWord, int page)
	{
		PacketHdv packet = new PacketHdv();
		packet.action = 2;
		packet.askedPage = page;
		packet.keyWord = keyWord.toLowerCase();
		return packet;
	}
	
	public static PacketHdv getItemsForPageOfPlayer(EntityPlayer player, int page)
	{
		PacketHdv packet = new PacketHdv();
		packet.action = 3;
		packet.askedPage = page;
		packet.playerName = player.getName();
		return packet;
	}
	
	public static PacketHdv getItemsForPageByPlayerNameAndKeyword(EntityPlayer player, String keyword, int page)
	{
		PacketHdv packet = new PacketHdv();
		packet.action = 4;
		packet.askedPage = page;
		packet.playerName = player.getName();
		packet.keyWord = keyword.toLowerCase();
		return packet;
	}
	

	//Server Side only
	public static PacketHdv displayHdv(List<HdvItem> items, int currentPage, int pageCount)
	{
		PacketHdv packet = new PacketHdv();
		packet.elements = items;
		packet.askedPage = currentPage;
		packet.pageCount = pageCount;
		packet.action = 1;
		return packet;
	}
	
	public static PacketHdv selectHdvItem(HdvItem item)
	{
		PacketHdv packet = new PacketHdv();
		packet.itemId = item.getHdvId();
		packet.action = 5;
		return packet;
	}
	
	public static PacketHdv removeItemFromHdv(HdvItem item)
	{
		PacketHdv packet = new PacketHdv();
		packet.itemId = item.getHdvId();
		packet.action = 6;
		return packet;
	}
	
	@Override
	public void encodeInto(ChannelHandlerContext ctx, ByteBuf data)
	{
		super.encodeInto(ctx, data);
		
		if(action == 1)
		{
			data.writeInt(pageCount);
		}
		else if(action == 2)
		{
			ByteBufUtils.writeUTF8String(data, keyWord);
		}
		else if(action == 3)
		{
			ByteBufUtils.writeUTF8String(data, playerName);
		}
		else if(action == 4)
		{
			ByteBufUtils.writeUTF8String(data, keyWord);
			ByteBufUtils.writeUTF8String(data, playerName);
		}
		else if(action == 5 || action == 6)
		{
			data.writeLong(itemId);
		}
	}

	@Override
	public void decodeInto(ChannelHandlerContext ctx, ByteBuf data) {
		super.decodeInto(ctx, data);
		
		if(action == 1)
		{
			pageCount = data.readInt();
		}
		else if(action == 2)
		{
			keyWord = ByteBufUtils.readUTF8String(data);
		}
		else if(action == 3)
		{
			playerName = ByteBufUtils.readUTF8String(data);
		}
		else if(action == 4)
		{
			keyWord = ByteBufUtils.readUTF8String(data);
			playerName = ByteBufUtils.readUTF8String(data);
		}
		else if(action == 5 || action == 6)
		{
			itemId = data.readLong();
		}
	}
	

	@Override
	public void handleServerSide(EntityPlayerMP playerEntity) 
	{ 
		WorldDataManager worldData = WorldDataManager.get(FMLCommonHandler.instance().getMinecraftServerInstance().getEntityWorld());
				
		if(action == 0)
		{
			List<HdvItem> items = worldData.getHdvManager().getItemsForPage(askedPage);
						
			int pageCount = worldData.getHdvManager().getPageCount();
						
			Main.getPacketHandler().sendTo(displayHdv(items, askedPage, pageCount), playerEntity);
		}
		else if(action == 2)
		{
			List<HdvItem> searchedItems = worldData.getHdvManager().getItems(x -> x.getItemstack().getDisplayName().toLowerCase().contains(keyWord));

		
			
			List<HdvItem> items = worldData.getHdvManager().getItemsForPage(askedPage, searchedItems);
			
			int pageCount = worldData.getHdvManager().getPageCount(searchedItems);
			
			Main.getPacketHandler().sendTo(displayHdv(items, askedPage, pageCount), playerEntity);
		}
		else if(action == 3)
		{
			List<HdvItem> searchedItems = worldData.getHdvManager().getItemsAndExpiredItems(x -> x.getOwnerName().equals(playerName));
			
			List<HdvItem> items = worldData.getHdvManager().getItemsForPage(askedPage, searchedItems);
			
			int pageCount = worldData.getHdvManager().getPageCount(searchedItems);
			
			Main.getPacketHandler().sendTo(displayHdv(items, askedPage, pageCount), playerEntity);
		}
		else if(action == 4)
		{
			List<HdvItem> searchedItems = worldData.getHdvManager().getItemsAndExpiredItems(x -> x.getOwnerName().equals(playerName) && x.getItemstack().getDisplayName().toLowerCase().contains(keyWord));
			
			List<HdvItem> items = worldData.getHdvManager().getItemsForPage(askedPage, searchedItems);
			
			int pageCount = worldData.getHdvManager().getPageCount(searchedItems);
			
			Main.getPacketHandler().sendTo(displayHdv(items, askedPage, pageCount), playerEntity);
		}
		else if(action == 5)
		{
			 worldData.getHdvManager().selectHdvItem(itemId, playerEntity);
		}
		else if(action == 6)
		{
			 worldData.getHdvManager().removeItemFromHdv(itemId, playerEntity);
		}
		


	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void handleClientSide(EntityPlayer clientPlayer) 
	{ 
		GuiScreen screen = Minecraft.getMinecraft().currentScreen;
		
		if(screen instanceof HdvUI)
		{
			HdvUI hdvUI = (HdvUI) screen;
			
			if(action == 1)
			{
				hdvUI.maxPage = pageCount;
				
				if(askedPage > pageCount)
				{
					hdvUI.currentPage = pageCount;
				}
				else
				{
					hdvUI.currentPage = askedPage;
				}
				
				if(hdvUI.maxPage > 0 && hdvUI.currentPage <= 0)
				{
					hdvUI.currentPage = 1;
				}
				
				hdvUI.updateScrollviewContents(elements);
			}
		}
	}
	
}
