package fr.karmaowner.erthilia;

import fr.karmaowner.erthilia.cosmetics.CosmeticManager;
import fr.karmaowner.erthilia.items.ErthiliaItems;
import fr.karmaowner.erthilia.network.PacketHandler;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

@Mod(modid = Main.MODID, name=Main.MODID,version = Main.VERSION)
public class Main
{
	    	
	public static Main INSTANCE;
	
    public static final String MODID = "erthilia";
    public static final String VERSION = "1.0.0";
    
    public static GameInitializer gameInitializer = new GameInitializer();
            
    private static final PacketHandler packetHandler = new PacketHandler();
    
    private static CosmeticManager cosmeticManager;
    
    public static ModConfiguration modConfig;
   
    
	@SidedProxy(clientSide = "fr.karmaowner.erthilia.ClientProxy", serverSide = "fr.karmaowner.erthilia.ServerProxy")
   	public static IProxy proxy;
    
   
    @EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{	    
		INSTANCE = this;
		
    	modConfig = new ModConfiguration(event.getSuggestedConfigurationFile(), event.getSide());


    	proxy.preInit(event);
    	
    	cosmeticManager = new CosmeticManager();
    
	}
	

	@EventHandler
	public void init(FMLInitializationEvent event)
	{		
		proxy.init(event);
	}
	

	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
    	modConfig.loadConfiguration();
    	modConfig.saveConfiguration();
    	
    	
		proxy.postInit(event);

		
		
		cosmeticManager.companionFood = new ItemStack[] { new ItemStack(ErthiliaItems.PYRITE,10) };
	}
	

    @EventHandler  
    public void serverStarting(FMLServerStartingEvent event)
    {
        System.out.println("Server starting");
        
        proxy.serverStarting(event);
    }


	public static PacketHandler getPacketHandler()
	{
		return packetHandler;
	}
	
	public static CosmeticManager getCosmeticsManager()
	{
		return cosmeticManager;
	}
	
	public static void log(Object msg)
	{
		System.out.println("[" + Main.MODID + "] " + msg);
	}


}