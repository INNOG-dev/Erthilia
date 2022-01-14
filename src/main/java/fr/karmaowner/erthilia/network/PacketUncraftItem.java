package fr.karmaowner.erthilia.network;

import fr.karmaowner.erthilia.inventory.ContainerReversedCraftingTable;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;

public class PacketUncraftItem extends PacketBase {

	public int containerId;
	
	public PacketUncraftItem()
	{
		
	}
	
	@Override
	public void encodeInto(ChannelHandlerContext ctx, ByteBuf data) {
		data.writeInt(containerId);
	}

	@Override
	public void decodeInto(ChannelHandlerContext ctx, ByteBuf data) {
		containerId = data.readInt();
	}

	@Override
	public void handleServerSide(EntityPlayerMP playerEntity)
	{
        Container container = playerEntity.openContainer;
        if(container instanceof ContainerReversedCraftingTable)
        {
        	ContainerReversedCraftingTable containerReversedCraftingTable = (ContainerReversedCraftingTable) container;
        	
    		ItemStack is = containerReversedCraftingTable.tableInventory.getStackInSlot(0);

    		if(is == null) return;
    		
            if(containerReversedCraftingTable.playerHaveXp())
            {
            	playerEntity.addExperienceLevel(-containerReversedCraftingTable.xpCount);
            	containerReversedCraftingTable.uncraftItem();
            	containerReversedCraftingTable.xpCount = 0;
            }

        }
	}

	@Override
	public void handleClientSide(EntityPlayer clientPlayer) {
		System.out.println("skip...");
	}

}
