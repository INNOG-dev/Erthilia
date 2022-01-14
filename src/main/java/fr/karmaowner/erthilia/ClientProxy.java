package fr.karmaowner.erthilia;

import java.util.List;
import java.util.Map;

import org.lwjgl.opengl.Display;

import fr.karmaowner.erthilia.armoreffect.ArmorEffectConfiguration;
import fr.karmaowner.erthilia.commands.CommandApi;
import fr.karmaowner.erthilia.commands.CommandCosmetics;
import fr.karmaowner.erthilia.commands.CommandHdv;
import fr.karmaowner.erthilia.handler.BlockBreakHandler;
import fr.karmaowner.erthilia.handler.BossHudHandler;
import fr.karmaowner.erthilia.handler.CapabilityHandler;
import fr.karmaowner.erthilia.handler.DragonHandler;
import fr.karmaowner.erthilia.handler.EntityDamageHandler;
import fr.karmaowner.erthilia.handler.FOVUpdateHandler;
import fr.karmaowner.erthilia.handler.GuiHandlerClient;
import fr.karmaowner.erthilia.handler.InputHandler;
import fr.karmaowner.erthilia.handler.KeyStrokesHandler;
import fr.karmaowner.erthilia.handler.OldPvpHandler;
import fr.karmaowner.erthilia.handler.ParticlesHandler;
import fr.karmaowner.erthilia.handler.PlayerInteractionHandler;
import fr.karmaowner.erthilia.handler.PotionHudHandler;
import fr.karmaowner.erthilia.handler.RenderHandler;
import fr.karmaowner.erthilia.handler.TicksHandler;
import fr.karmaowner.erthilia.handler.ToggleSprintHandler;
import fr.karmaowner.erthilia.model.ModelBipedArmor;
import fr.karmaowner.erthilia.model.ModelPlayerErthilia;
import fr.karmaowner.erthilia.model.layer.LayerCustomHeldItem;
import fr.karmaowner.erthilia.radio.RadioHandler;
import fr.karmaowner.erthilia.wiki.WikiRecipes;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.layers.LayerArmorBase;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.client.renderer.entity.layers.LayerCustomHead;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class ClientProxy implements IProxy {

    public static ArmorEffectConfiguration armoreffectConfig = new ArmorEffectConfiguration();
	
	private static ToggleSprintHandler toggleSprintHandler = new ToggleSprintHandler();

	private static ParticlesHandler particlesHandler = new ParticlesHandler();

	private static KeyStrokesHandler keyStrokesHandler = new KeyStrokesHandler();

	private static PotionHudHandler potionHudHandler = new PotionHudHandler();
	
	private static BossHudHandler bossHudHandler = new BossHudHandler();

	private static RadioHandler radioHandler = new RadioHandler();
	
	private static GuiHandlerClient guiHandler = new GuiHandlerClient();
	 
	
	@Override
	public void preInit(FMLPreInitializationEvent event) 
	{
    	OBJLoader.INSTANCE.addDomain("erthilia");
    	
    	Display.setTitle("Erthilia - " + Main.VERSION);		

    	Main.gameInitializer.preInit(event);
    	
		registerHandlers();		
		
	}

	@Override
	public void init(FMLInitializationEvent event) 
	{
		Main.gameInitializer.init(event);
		
        Render<AbstractClientPlayer> render = Minecraft.getMinecraft().getRenderManager().getEntityClassRenderObject(AbstractClientPlayer.class);
        Map<String, RenderPlayer> skinMap = render.getRenderManager().getSkinMap();
        this.overridePlayerRender(skinMap.get("default"), false);
        this.overridePlayerRender(skinMap.get("slim"), true);
		
		Main.getPacketHandler().initialise();	
	} 
	

	@Override
	public void postInit(FMLPostInitializationEvent event) 
	{
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
	private void overridePlayerRender(RenderPlayer player, boolean smallArms)
    {
        ModelBiped model = new ModelPlayerErthilia(0.0F, smallArms);
        //List<LayerRenderer<EntityLivingBase>> layers = ObfuscationReflectionHelper.getPrivateValue(RenderLivingBase.class, player, "layerRenderers");
        
        List<LayerRenderer<EntityLivingBase>> layers = ObfuscationReflectionHelper.getPrivateValue(RenderLivingBase.class, player, 4);

        
        if(layers != null)
        {
            layers.removeIf(layerRenderer -> layerRenderer instanceof LayerHeldItem || layerRenderer instanceof LayerCustomHead);
            layers.add(new LayerCustomHeldItem(player));
            layers.add(new LayerCustomHead(model.bipedHead));
            layers.forEach(layerRenderer ->
            {
                if(layerRenderer instanceof LayerBipedArmor)
                {
                    this.overrideArmor((LayerBipedArmor) layerRenderer, model);
                }
            });
        }
        //ObfuscationReflectionHelper.setPrivateValue(RenderLivingBase.class, player, model, "mainModel");
        ObfuscationReflectionHelper.setPrivateValue(RenderLivingBase.class, player, model, 2);
    }
	
    @SuppressWarnings("deprecation")
	private void overrideArmor(LayerBipedArmor layerBipedArmor, ModelBiped source)
    {
        /*ObfuscationReflectionHelper.setPrivateValue(LayerArmorBase.class, layerBipedArmor, new ModelBipedArmor(source, 1.0F), "modelArmor");
        ObfuscationReflectionHelper.setPrivateValue(LayerArmorBase.class, layerBipedArmor, new ModelBipedArmor(source, 0.5F), "modelLeggings");*/
    	
    	ObfuscationReflectionHelper.setPrivateValue(LayerArmorBase.class, layerBipedArmor, new ModelBipedArmor(source, 1.0F), 2);
        ObfuscationReflectionHelper.setPrivateValue(LayerArmorBase.class, layerBipedArmor, new ModelBipedArmor(source, 0.5F), 1);
    }

	
	@SuppressWarnings("deprecation")
	public void registerHandlers()
	{

		 DragonHandler dragonHandler = new DragonHandler();
		 RenderHandler renderHandler = new RenderHandler();
		 PlayerInteractionHandler playerInteractionHandler = new PlayerInteractionHandler();
		 CapabilityHandler capabilityHandler = new CapabilityHandler();
		 OldPvpHandler oldPvpHandler = new OldPvpHandler();
		
		 MinecraftForge.EVENT_BUS.register(getParticlesHandler());
	     MinecraftForge.EVENT_BUS.register(getGuiHandler());
	     MinecraftForge.EVENT_BUS.register(new FOVUpdateHandler());
	     MinecraftForge.EVENT_BUS.register(new BlockBreakHandler());
	     MinecraftForge.EVENT_BUS.register(playerInteractionHandler);
	     MinecraftForge.EVENT_BUS.register(new EntityDamageHandler());
	     MinecraftForge.EVENT_BUS.register(renderHandler);
	     MinecraftForge.EVENT_BUS.register(dragonHandler);
	     MinecraftForge.EVENT_BUS.register(capabilityHandler);
	     MinecraftForge.EVENT_BUS.register(oldPvpHandler);


	     FMLCommonHandler.instance().bus().register(new InputHandler());
	     FMLCommonHandler.instance().bus().register(new TicksHandler());
	     FMLCommonHandler.instance().bus().register(getToggleSprintHandler());
	     FMLCommonHandler.instance().bus().register(getKeyStrokesHandler());
	     FMLCommonHandler.instance().bus().register(getGuiHandler());
	     FMLCommonHandler.instance().bus().register(getPotionHudHandler());
	     FMLCommonHandler.instance().bus().register(dragonHandler);
	     FMLCommonHandler.instance().bus().register(renderHandler);
	     FMLCommonHandler.instance().bus().register(playerInteractionHandler);	     
	     FMLCommonHandler.instance().bus().register(capabilityHandler);	     
	     FMLCommonHandler.instance().bus().register(oldPvpHandler);	     

	
	}

	
	public static ToggleSprintHandler getToggleSprintHandler()
	{
		return toggleSprintHandler;
	}
	
	public static ParticlesHandler getParticlesHandler()
	{
		return particlesHandler;
	}
	
	public static KeyStrokesHandler getKeyStrokesHandler()
	{
		return keyStrokesHandler;
	}
	
	public static PotionHudHandler getPotionHudHandler()
	{
		return potionHudHandler;
	}
	
	public static BossHudHandler getBossHudHandler()
	{
		return bossHudHandler;
	}
	
	public static RadioHandler getRadioHandler()
	{
		return radioHandler;
	}
	
	public static GuiHandlerClient getGuiHandler()
	{
		return guiHandler;
	}


	@Override
	public EntityPlayer getPlayerEntityFromContext(MessageContext ctx)
	{
        return (ctx.side.isClient() ? Minecraft.getMinecraft().player : Main.proxy.getPlayerEntityFromContext(ctx));
	}

	
}
