package fr.karmaowner.erthilia.network;

import fr.karmaowner.erthilia.inventory.ContainerReversedEnchantment;
import fr.karmaowner.erthilia.utils.ItemStackUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;

public class PacketSupressEnchantment extends PacketBase {

	public int containerId;
	
	public PacketSupressEnchantment()
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
	public void handleServerSide(EntityPlayerMP playerEntity) {
        Container container = playerEntity.openContainer;
        if(container instanceof ContainerReversedEnchantment)
        {
        	ContainerReversedEnchantment containerEnchant = (ContainerReversedEnchantment) container;
        	
    		ItemStack is = containerEnchant.tableInventory.getStackInSlot(0);

    		if(is == null) return;
    		
        	if(ItemStackUtils.isItemTools(is) && !is.isItemEnchanted()) return;

            if(containerEnchant.playerHaveXp())
            {
            	playerEntity.addExperienceLevel(-containerEnchant.xpCount);
            	containerEnchant.clearEnchantment();
            	containerEnchant.xpCount = 0;
            }

        }
	}

	@Override
	public void handleClientSide(EntityPlayer clientPlayer) {
		System.out.println("skip...");
	}

}
