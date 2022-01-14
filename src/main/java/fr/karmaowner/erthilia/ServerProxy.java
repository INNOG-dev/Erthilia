package fr.karmaowner.erthilia;


import java.awt.Desktop;
import java.net.URI;

import fr.karmaowner.api.KeyCheckApi;
import fr.karmaowner.erthilia.commands.CommandApi;
import fr.karmaowner.erthilia.commands.CommandCosmetics;
import fr.karmaowner.erthilia.commands.CommandHdv;
import fr.karmaowner.erthilia.handler.BlockBreakHandler;
import fr.karmaowner.erthilia.handler.CapabilityHandler;
import fr.karmaowner.erthilia.handler.DragonHandler;
import fr.karmaowner.erthilia.handler.EntityDamageHandler;
import fr.karmaowner.erthilia.handler.OldPvpHandler;
import fr.karmaowner.erthilia.handler.PlayerInteractionHandler;
import fr.karmaowner.erthilia.handler.RenderHandler;
import fr.karmaowner.erthilia.handler.TicksHandler;
import fr.karmaowner.erthilia.wiki.WikiRecipes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class ServerProxy implements IProxy 
{
	

	@Override
	public void preInit(FMLPreInitializationEvent event) 
	{
		Main.gameInitializer.preInit(event);
		
		registerHandlers();
	}

	@Override
	public void init(FMLInitializationEvent event) 
	{ 
		
		Main.gameInitializer.init(event);
		
		Main.getPacketHandler().initialise();	

	}

	@Override
	public void postInit(FMLPostInitializationEvent event) 
	{
		KeyCheckApi api = KeyCheckApi.startVerification(Main.modConfig.licenceKey);
		
    	if(!api.getResult())
    	{
    		Main.log("Vous devez payer une licence pour utiliser ce mod merci de prévenir Atonha");
    		
    		if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) 
    		{
    		    try {
					Desktop.getDesktop().browse(new URI("http://www.AtonHa.fr"));
				} catch (Exception e) {
					e.printStackTrace();
				}
    		}
    		
    		FMLCommonHandler.instance().exitJava(500, true);
    	}
		
		Main.getPacketHandler().postInitialise();
		
    	WikiRecipes.loadRecipes();
	}

	@Override
	public void serverStarting(FMLServerStartingEvent event) 
	{ 
		event.registerServerCommand(new CommandCosmetics());
		event.registerServerCommand(new CommandHdv());
		event.registerServerCommand(new CommandApi());
	}

	@SuppressWarnings("deprecation")
	public void registerHandlers()
	{		
		DragonHandler dragonHandler = new DragonHandler();
		RenderHandler renderHandler = new RenderHandler();
		PlayerInteractionHandler playerInteractionHandler = new PlayerInteractionHandler();
		TicksHandler ticksHandler = new TicksHandler();
		CapabilityHandler capabilityHandler = new CapabilityHandler();
		OldPvpHandler oldPvpHandler = new OldPvpHandler();

        MinecraftForge.EVENT_BUS.register(new BlockBreakHandler());
        MinecraftForge.EVENT_BUS.register(playerInteractionHandler);
        MinecraftForge.EVENT_BUS.register(new EntityDamageHandler());
        MinecraftForge.EVENT_BUS.register(renderHandler);
        MinecraftForge.EVENT_BUS.register(dragonHandler);
        MinecraftForge.EVENT_BUS.register(ticksHandler);
        MinecraftForge.EVENT_BUS.register(capabilityHandler);
        MinecraftForge.EVENT_BUS.register(oldPvpHandler);

       
        FMLCommonHandler.instance().bus().register(dragonHandler);
        FMLCommonHandler.instance().bus().register(renderHandler);
        FMLCommonHandler.instance().bus().register(playerInteractionHandler);
        FMLCommonHandler.instance().bus().register(ticksHandler);
        FMLCommonHandler.instance().bus().register(capabilityHandler);
        FMLCommonHandler.instance().bus().register(oldPvpHandler);
	}


	@Override
	public EntityPlayer getPlayerEntityFromContext(MessageContext ctx) 
	{
        return ctx.getServerHandler().player;
	}
	
}
