package fr.karmaowner.erthilia.handler;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import fr.karmaowner.erthilia.ClientProxy;
import fr.karmaowner.erthilia.armoreffect.ArmorEffectContainer;
import fr.karmaowner.erthilia.data.GuiOverlaySettings;
import fr.karmaowner.erthilia.gui.PauseMenuUI;
import fr.karmaowner.erthilia.gui.inventory.InventoryUI;
import fr.karmaowner.erthilia.guicomponent.UIColor;
import fr.karmaowner.erthilia.guicomponent.UIRect;
import fr.karmaowner.erthilia.keystrokes.KeyContainer;
import fr.karmaowner.erthilia.utils.GuiUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.GuiWorldSelection;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


@SideOnly(Side.CLIENT)
public class GuiHandlerClient
{
	
	private Minecraft mc = Minecraft.getMinecraft();

	public ArmorEffectContainer armorEffectContainer = new ArmorEffectContainer();

	public ToggleSprintHandler toggleSprintContainer = ClientProxy.getToggleSprintHandler();

	public KeyContainer keystrokesContainer = ClientProxy.getKeyStrokesHandler().getKeyContainer();

	public PotionHudHandler potionHudContainer = ClientProxy.getPotionHudHandler();
	
	public BossHudHandler bossHudContainer = ClientProxy.getBossHudHandler();

	
	private boolean isInitialized = false;
    
	private GuiOverlaySettings settings;
	
	private int displayWidth, displayHeight, guiScale;
	
	public static int mouseX;	
	public static int mouseY;
	
	public static long deltaTime;
	
	private static long oldTime;
	private static long newTime;

	
	public void initialize(int screenWidth, int screenHeight)
	{
		settings = new GuiOverlaySettings(screenWidth, screenHeight);
		
		armorEffectContainer.setPosition(settings.armorEffectPosX, settings.armorEffectPosY, settings.armorEffectWidth, settings.armorEffectHeight);
		armorEffectContainer.setScale(settings.armorEffectScale);
				
		toggleSprintContainer.setPosition(settings.toggleSprintPosX, settings.toggleSprintPosY, settings.toggleSprintWidth, settings.toggleSprintHeight);
		toggleSprintContainer.setScale(settings.toggleSprintScale);

		keystrokesContainer.setPosition(settings.keystrokesPosX,settings.keystrokesPosY, settings.keystrokesWidth, settings.keystrokesHeight);
		keystrokesContainer.setScale(settings.keystrokesScale);
		
		potionHudContainer.setPosition(settings.potionHudPosX, settings.potionHudPosY, settings.potionHudWidth, settings.potionHudHeight);
		potionHudContainer.setScale(settings.potionHudScale);
		
		bossHudContainer.setPosition(settings.bossHudPosX, settings.bossHudPosY, settings.bossHudWidth, settings.bossHudHeight);
		bossHudContainer.setScale(settings.bossHudScale);
	}

	
	
	@SubscribeEvent
	public void onRenderScreen(TickEvent.RenderTickEvent event)
	{
		if(!Minecraft.getMinecraft().isGamePaused())
		{
			oldTime = newTime;
			newTime = Minecraft.getSystemTime();
			deltaTime = newTime - oldTime;
		}
		else
		{
			deltaTime = 0;
		}
		
		Minecraft mc = Minecraft.getMinecraft();
		if((mc.inGameHasFocus || mc.currentScreen instanceof InventoryUI) && !Minecraft.getMinecraft().gameSettings.showDebugInfo && !Minecraft.getMinecraft().gameSettings.hideGUI)
		{
			if(this.displayWidth != mc.displayWidth || displayHeight != mc.displayHeight || guiScale != mc.gameSettings.guiScale)
			{
				this.refreshOverlay();
			}
			
			if(!this.isInitialized)
			{
				this.refreshOverlay();
			}
			
			GL11.glPushMatrix();
			
			GL11.glTranslatef(0, 0, 0f);
			armorEffectContainer.draw(0,0, 0);
			toggleSprintContainer.draw(0,0, 0);
			keystrokesContainer.draw(0,0, 0);
			potionHudContainer.draw(0,0, 0);
			bossHudContainer.draw(0, 0, 0);
			GL11.glPopMatrix();
		}
	}
	
	@SubscribeEvent
	public void onClientTicks(TickEvent.ClientTickEvent event)
	{		
		if(mc.currentScreen != null)
		{			
            if(mc.currentScreen instanceof GuiOptions || mc.currentScreen instanceof GuiMultiplayer || mc.currentScreen instanceof GuiWorldSelection)
            {
	            mouseX = Mouse.getX();
	            mouseY = Mouse.getY();    
            }
            else
            {
        		mouseX = Minecraft.getMinecraft().displayWidth / 2;
        		mouseY = Minecraft.getMinecraft().displayHeight / 2;
            }
		}
	}
	
    @SubscribeEvent
    public void openGui(GuiOpenEvent event) {

        if (event.getGui() instanceof GuiMainMenu) 
        {
        	//event.setGui(new MainMenuUI());
        }
        else if(event.getGui() instanceof GuiIngameMenu)
        {
        	event.setGui(new PauseMenuUI());
        }
        else if(event.getGui() instanceof GuiInventory)
        {
        	event.setGui(new InventoryUI(mc.player));
        }
    }
    

    @SubscribeEvent
    public void onRenderPre(RenderGameOverlayEvent.Pre event)
    {  	
    	
    	Minecraft mc = Minecraft.getMinecraft();
    	
    	if(mc.currentScreen instanceof PauseMenuUI)
    	{
    		event.setCanceled(true);
    	}
    	
    	
    	if(event.getType() == RenderGameOverlayEvent.ElementType.DEBUG)
    	{
    	    event.setCanceled(true);  
    	    UIRect serverNameRect = new UIRect(new UIColor(0,0,0,0));
    	    UIRect fpsChunkRect = new UIRect(new UIColor(0,0,0,0));
    	    UIRect coordinatesRect = new UIRect(new UIColor(0,0,0,0));
    	    UIRect directionRect = new UIRect(new UIColor(0,0,0,0));
    	    UIRect biomeRect = new UIRect(new UIColor(0,0,0,0));
    	    //UIRect javaVersionRect = new UIRect(new UIColor(0,0,0,0));
    	    //UIRect memoryRect = new UIRect(new UIColor(0,0,0,0));
    	    //UIRect allocatedRect = new UIRect(new UIColor(0,0,0,0));
    	    //UIRect componentsRect = new UIRect(new UIColor(0,0,0,0));
    	    //UIRect blockRect = new UIRect(new UIColor(0,0,0,0));
    	    
    	    serverNameRect.setPosition(2, 5, GuiUtils.getStringWidth(mc, "élErthilia 1.0.0", 0.8f), 10);
    	    serverNameRect.draw(0, 0, event.getPartialTicks());
    	    GuiUtils.renderText("§lErthilia 1.0.0", serverNameRect.getX() + 2, (serverNameRect.getY() + serverNameRect.getY2() - mc.fontRenderer.FONT_HEIGHT/2) / 2,7410316,0.8f);
    	    
    	    String[] debug = mc.debug.split(", ")[0].split(" ");
    	    fpsChunkRect.setPosition(2, 15, GuiUtils.getStringWidth(mc, debug[0] + " " + debug[1], 0.8f), 10);
    	    fpsChunkRect.draw(0, 0, event.getPartialTicks());
    	    GuiUtils.renderText(debug[0] + " " + debug[1], fpsChunkRect.getX() + 2, (fpsChunkRect.getY() + fpsChunkRect.getY2() - mc.fontRenderer.FONT_HEIGHT/2) / 2,7211397,0.8f);
    	
    	    String coordinates = "XYZ : " + (int)mc.player.posX + " / " + (int)mc.player.posY + " / " + (int)mc.player.posZ;
    	    coordinatesRect.setPosition(2, 30, GuiUtils.getStringWidth(mc, coordinates, 0.8f), 10);
    	    coordinatesRect.draw(0, 0, event.getPartialTicks());
    	    GuiUtils.renderText(coordinates, coordinatesRect.getX() + 2, (coordinatesRect.getY() + coordinatesRect.getY2() - mc.fontRenderer.FONT_HEIGHT/2) / 2,7211397,0.8f);
    	    
    	    String direction = "Direction : " + mc.player.getHorizontalFacing().toString();
    	    directionRect.setPosition(2, 40, GuiUtils.getStringWidth(mc, direction, 0.8f), 10);
    	    directionRect.draw(0, 0, event.getPartialTicks());
    	    GuiUtils.renderText(direction, directionRect.getX() + 2, (directionRect.getY() + directionRect.getY2() - mc.fontRenderer.FONT_HEIGHT/2) / 2,7211397,0.8f);
    	
    	    String biome = "Biome : " + mc.world.getBiome(new BlockPos((int)mc.player.posX ,mc.player.posY, (int)mc.player.posZ)).getBiomeName();
    	    biomeRect.setPosition(2, 55, GuiUtils.getStringWidth(mc, biome, 0.8f), 10);
    	    biomeRect.draw(0, 0, event.getPartialTicks());
    	    GuiUtils.renderText(biome, biomeRect.getX() + 2, (biomeRect.getY() + biomeRect.getY2() - mc.fontRenderer.FONT_HEIGHT/2) / 2,7211397,0.8f);
    	
    	    /*String java = "Java: " + System.getProperty("java.version");
    	    javaVersionRect.setPosition(event.resolution.getScaledWidth() - GuiUtils.getStringWidth(mc, java, 0.8f)-2, 5, GuiUtils.getStringWidth(mc, java, 0.8f), 10);
    	    javaVersionRect.draw(0, 0);
    	    GuiUtils.renderText(java, javaVersionRect.getX() + 2, (javaVersionRect.getY() + javaVersionRect.getY2() - mc.fontRenderer.FONT_HEIGHT/2) / 2,9381549,0.8f);
    	
            long i5 = Runtime.getRuntime().maxMemory();
            long j5 = Runtime.getRuntime().totalMemory();
            long k5 = Runtime.getRuntime().freeMemory();
            long l5 = j5 - k5;
            String mem = "Mem: " + l5 * 100L / i5 + "% (" + l5 / 1024L / 1024L + "/" + i5 / 1024L / 1024L + ") MB";
            
            memoryRect.setPosition(event.resolution.getScaledWidth() - GuiUtils.getStringWidth(mc, mem, 0.8f)-2, 15, GuiUtils.getStringWidth(mc, mem, 0.8f), 10);
            memoryRect.draw(0, 0);
            GuiUtils.renderText(mem, memoryRect.getX() + 2, (memoryRect.getY() + memoryRect.getY2() - mc.fontRenderer.FONT_HEIGHT/2) / 2,9381549,0.8f);
            
            String allocated = "Allocated : " + j5 * 100L / i5 + "% (" + j5 / 1024L / 1024L + "MB)";
            allocatedRect.setPosition(event.resolution.getScaledWidth() - GuiUtils.getStringWidth(mc, allocated, 0.8f)-2, 25, GuiUtils.getStringWidth(mc, allocated, 0.8f), 10);
            allocatedRect.draw(0, 0);
            GuiUtils.renderText(allocated, allocatedRect.getX() + 2, (allocatedRect.getY() + allocatedRect.getY2() - mc.fontRenderer.FONT_HEIGHT/2) / 2,9381549,0.8f);
            
    	    
            String components = GL11.glGetString(GL11.GL_RENDERER);
            componentsRect.setPosition(event.resolution.getScaledWidth() - GuiUtils.getStringWidth(mc, components, 0.8f)-2, 35, GuiUtils.getStringWidth(mc, components, 0.8f), 10);
            componentsRect.draw(0, 0);
            GuiUtils.renderText(components, componentsRect.getX() + 2, (componentsRect.getY() + componentsRect.getY2() - mc.fontRenderer.FONT_HEIGHT/2) / 2,9381549,0.8f);
            
    	    MovingObjectPosition mop = mc.thePlayer.rayTrace(5, event.partialTicks);
    	    if(mop != null)
    	    {
    	    	int blockId = Block.getIdFromBlock(mc.theWorld.getBlock(mop.blockX, mop.blockY, mop.blockZ));
    	    	if(blockId != 0)
    	    	{
	    	    	String block = "Block : " + blockId + " [" + mop.blockX + "/" + mop.blockY + "/" + mop.blockZ + "]";
	                blockRect.setPosition(event.resolution.getScaledWidth() - GuiUtils.getStringWidth(mc, block, 0.8f)-2, 60, GuiUtils.getStringWidth(mc, block, 0.8f), 10);
	        	    blockRect.draw(0, 0);
	                GuiUtils.renderText(block, blockRect.getX() + 2, (blockRect.getY() + blockRect.getY2() - mc.fontRenderer.FONT_HEIGHT/2) / 2,9381549,0.8f);
    	    	}
    	    }*/
    	}
    	else if(event.getType() == ElementType.POTION_ICONS)
    	{
    		event.setCanceled(true);
    	}
    	else if(event.getType() == ElementType.CROSSHAIRS)
    	{
    		event.setCanceled(true);
            mc.getTextureManager().bindTexture(Gui.ICONS);
            GlStateManager.enableBlend();
            GameSettings gamesettings = this.mc.gameSettings;

            if (gamesettings.thirdPersonView == 0)
            {
                if (this.mc.playerController.isSpectator() && this.mc.pointedEntity == null)
                {
                    RayTraceResult raytraceresult = this.mc.objectMouseOver;

                    if (raytraceresult == null || raytraceresult.typeOfHit != RayTraceResult.Type.BLOCK)
                    {
                        return;
                    }

                    BlockPos blockpos = raytraceresult.getBlockPos();

                    net.minecraft.block.state.IBlockState state = this.mc.world.getBlockState(blockpos);
                    if (!state.getBlock().hasTileEntity(state) || !(this.mc.world.getTileEntity(blockpos) instanceof IInventory))
                    {
                        return;
                    }
                }

                int l = event.getResolution().getScaledWidth();
                int i1 = event.getResolution().getScaledHeight();

                if (gamesettings.showDebugInfo && !gamesettings.hideGUI && !this.mc.player.hasReducedDebug() && !gamesettings.reducedDebugInfo)
                {
                    GlStateManager.pushMatrix();
                    GlStateManager.translate((float)(l / 2), (float)(i1 / 2), 1);
                    Entity entity = this.mc.getRenderViewEntity();
                    GlStateManager.rotate(entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * event.getPartialTicks(), -1.0F, 0.0F, 0.0F);
                    GlStateManager.rotate(entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw) * event.getPartialTicks(), 0.0F, 1.0F, 0.0F);
                    GlStateManager.scale(-1.0F, -1.0F, -1.0F);
                    OpenGlHelper.renderDirections(10);
                    GlStateManager.popMatrix();
                }
                else
                {
                    GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.ONE_MINUS_DST_COLOR, GlStateManager.DestFactor.ONE_MINUS_SRC_COLOR, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
                    GlStateManager.enableAlpha();
                    mc.ingameGUI.drawTexturedModalRect(l / 2 - 7, i1 / 2 - 7, 0, 0, 16, 16);
             
                }
            }
            
    	}
    }
    
    @SideOnly(Side.CLIENT)
    public void refreshOverlay()
    {
    	this.displayWidth = mc.displayWidth;
    	this.displayHeight = mc.displayHeight;
    	this.guiScale = mc.gameSettings.guiScale;
    	this.isInitialized = true;
		ScaledResolution sr = new ScaledResolution(mc);
		initialize(sr.getScaledWidth(), sr.getScaledHeight());
    }
    

}