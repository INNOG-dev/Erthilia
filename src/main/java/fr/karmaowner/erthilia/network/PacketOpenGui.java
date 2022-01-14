package fr.karmaowner.erthilia.network;

import fr.karmaowner.erthilia.Main;
import fr.karmaowner.erthilia.gui.HdvUI;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PacketOpenGui extends PacketBase {
	
	public int guiId;
	
	public PacketOpenGui()
	{

	}
	
	public PacketOpenGui(int id)
	{
		this.guiId = id;
	}
	
	@Override
	public void encodeInto(ChannelHandlerContext ctx, ByteBuf data) {
		data.writeInt(guiId);
	}

	@Override
	public void decodeInto(ChannelHandlerContext ctx, ByteBuf data) {
		guiId = data.readInt();
	}

	@Override
	public void handleServerSide(EntityPlayerMP playerEntity) {
	   if(guiId != 4)
	   {
		   playerEntity.openGui(Main.INSTANCE, guiId, playerEntity.world, (int) playerEntity.posX, (int) playerEntity.posY, (int) playerEntity.posZ);
	   }
	   else
	   {
		   playerEntity.openContainer = playerEntity.inventoryContainer;
	   }
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void handleClientSide(EntityPlayer clientPlayer) 
	{
		if(guiId == 5)
		{
			Minecraft.getMinecraft().displayGuiScreen(new HdvUI());
		}
	}
}
