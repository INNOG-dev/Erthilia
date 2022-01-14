package fr.karmaowner.erthilia.handler;

import fr.karmaowner.erthilia.ClientProxy;
import fr.karmaowner.erthilia.Main;
import fr.karmaowner.erthilia.cosmetics.CosmeticManager;
import fr.karmaowner.erthilia.data.WorldDataManager;
import fr.karmaowner.erthilia.hdv.HdvManager;
import fr.karmaowner.erthilia.utils.PlayerUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ContainerPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TicksHandler {

    public static int elapsedTicks;
        
    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void tickEnd(TickEvent.ClientTickEvent event)
    {
        if (event.phase != TickEvent.Phase.END) 
        {
            return;
        }
                
        ++elapsedTicks;
        
        if(Minecraft.getMinecraft().inGameHasFocus)
        {
	        EntityPlayer player = Minecraft.getMinecraft().player;
	        
	        if(elapsedTicks % 20 == 0)
	        {
	        	ClientProxy.getBossHudHandler().setEntity(PlayerUtils.getClosestEntityToPlayer(player));
	        }
        }
	        
        ClientProxy.getRadioHandler().setVolume((int)(Minecraft.getMinecraft().gameSettings.getSoundLevel(SoundCategory.MUSIC) * 100));

    }
    
    @SubscribeEvent
    public void serverTick(TickEvent.ServerTickEvent event) {
    	if(event.side == Side.SERVER)
    	{
	    	if (event.phase == TickEvent.Phase.END) 
	    	{
	            return;
	        }
	    	
	    	MinecraftServer server = FMLCommonHandler.instance().getMinecraftServerInstance();
	    	
	    	if(server.getEntityWorld().getTotalWorldTime() % (20 * HdvManager.hdvUpdateTime) == 0)
	    	{
	    		WorldDataManager.get(server.getEntityWorld()).getHdvManager().updateHdv();
	    	}
    	}
    
    }
    
    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent event)
    {
    	if(event.side == Side.SERVER)
    	{
    		
    		if(event.phase == TickEvent.Phase.END)
    		{
    			if(event.player.inventory.offHandInventory.get(0) != ItemStack.EMPTY)
    			{
    				int i = event.player.inventory.getFirstEmptyStack();
    				
    				if(i == -1)
    				{
    					EntityItem item = new EntityItem(event.player.world, event.player.posX, event.player.posY, event.player.posZ, event.player.inventory.offHandInventory.get(0));
    					event.player.world.spawnEntity(item);
    					event.player.inventory.offHandInventory.set(0, ItemStack.EMPTY);
    				}
    				else
    				{
    					event.player.inventory.setInventorySlotContents(i, event.player.inventory.offHandInventory.get(0));
    					event.player.inventory.offHandInventory.set(0, ItemStack.EMPTY);
    				}
    			}
    			return;
    		}
    		
    		if(event.player.ticksExisted % (20 * CosmeticManager.companionUpdate) == 0)
    		{
    			Main.getCosmeticsManager().updateCompanion(event.player);
    		}
    		
    	}
    	
	   	EntityPlayer player = (EntityPlayer) event.player;

	   	if(player.openContainer instanceof ContainerPlayer)
		{
	   		if(player.inventoryContainer.inventorySlots.size() == 46) player.inventoryContainer.inventorySlots.remove(45);
		
	    	player.inventoryContainer.inventorySlots.get(0).xPos = 144;
        	player.inventoryContainer.inventorySlots.get(0).yPos = 36;
        	
	        for (int i = 0; i < 2; ++i)
	        {
	            for (int j = 0; j < 2; ++j)
	            {
	            	player.inventoryContainer.inventorySlots.get(1 + j + i * 2).xPos = 88 + j * 18;
	            	player.inventoryContainer.inventorySlots.get(1 + j + i * 2).yPos = 26 + i * 18;
	            }
	        }
		}
	   	
	   	
    }
    
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void tick(TickEvent.ServerTickEvent event)
    {
    	switch(event.phase)
    	{
    		case START:
    		{
    			Main.getPacketHandler().handleServerPackets();
    			break;
    		}
    		case END:
    		{
    			break;
    		}
    	}
    	
    }
    
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    @SideOnly(Side.CLIENT)
    public void tick(TickEvent.ClientTickEvent event)
    {
    	switch(event.phase)
    	{
			case START:
			{
				Main.getPacketHandler().handleClientPackets();
				break;
			}
			case END:
			{
				break;
			}
    	}
    }
	
}
