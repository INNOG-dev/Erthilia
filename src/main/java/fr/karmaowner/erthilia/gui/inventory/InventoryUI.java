package fr.karmaowner.erthilia.gui.inventory;

import java.io.IOException;

import org.lwjgl.opengl.GL11;

import fr.karmaowner.erthilia.Main;
import fr.karmaowner.erthilia.gui.CustomInventoryEffectRenderer;
import fr.karmaowner.erthilia.gui.EditionUI;
import fr.karmaowner.erthilia.gui.HdvUI;
import fr.karmaowner.erthilia.gui.PlanningUI;
import fr.karmaowner.erthilia.gui.WikiUI;
import fr.karmaowner.erthilia.guicomponent.UIButton;
import fr.karmaowner.erthilia.guicomponent.UIButton.Type;
import fr.karmaowner.erthilia.guicomponent.UIColor;
import fr.karmaowner.erthilia.network.PacketCosmetic;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.Slot;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class InventoryUI extends CustomInventoryEffectRenderer 
{
    private float oldMouseX;
    private float oldMouseY;
    
	private UIButton[] buttons = new UIButton[7];


    public InventoryUI(EntityPlayer player)
    {
        super(player.inventoryContainer);
        this.allowUserInput = true;
    }

    public void updateScreen()
    {
        if (this.mc.playerController.isInCreativeMode())
        {
            this.mc.displayGuiScreen(new GuiContainerCreative(this.mc.player));
        }

    }

    public void initGui()
    {
        this.buttonList.clear();

        if (this.mc.playerController.isInCreativeMode())
        {
            this.mc.displayGuiScreen(new GuiContainerCreative(this.mc.player));
        }
        else
        {
            super.initGui();
        }

        
        buttons[0] = new UIButton(Type.SQUARE,"Wiki",new ResourceLocation("erthilia","gui/pausemenu/wiki.png"),new ResourceLocation("erthilia","gui/pausemenu/wiki_hover.png"), false, new UIButton.CallBackObject()
        {
        	@Override
        	public void call()
        	{
	    		mc.displayGuiScreen(new WikiUI());
        	}
        });
        
        buttons[1] = new UIButton(Type.SQUARE,"Options Erthilia",new ResourceLocation("erthilia","gui/pausemenu/settings_erthilia.png"),new ResourceLocation("erthilia","gui/pausemenu/settings_erthilia_hover.png"), false, new UIButton.CallBackObject()
        {
        	@Override
        	public void call()
        	{
	    		mc.displayGuiScreen(new EditionUI());
        	}
        });
        
        buttons[2] = new UIButton(Type.SQUARE,"Planning",new ResourceLocation("erthilia","gui/pausemenu/planning.png"),new ResourceLocation("erthilia","gui/pausemenu/planning_hover.png"), false, new UIButton.CallBackObject()
        {
        	@Override
        	public void call()
        	{
    			mc.displayGuiScreen(new PlanningUI());
        	}
        });
        
        buttons[3] = new UIButton(Type.SQUARE,"Cosmétique",new ResourceLocation("erthilia","gui/pausemenu/cosmetics.png"),new ResourceLocation("erthilia","gui/pausemenu/cosmetics_hover.png"), false, new UIButton.CallBackObject()
        {
        	@Override
        	public void call()
        	{
	    		Minecraft.getMinecraft().displayGuiScreen(null);
	    		Main.getPacketHandler().sendToServer(PacketCosmetic.openInterface((byte)0));
        	}
        });
        
        buttons[4] = new UIButton(Type.SQUARE,"Compagnon",new ResourceLocation("erthilia","gui/pausemenu/companion.png"),new ResourceLocation("erthilia","gui/pausemenu/companion_hover.png"), false, new UIButton.CallBackObject()
        {
        	@Override
        	public void call()
        	{
	    		Minecraft.getMinecraft().displayGuiScreen(null);
	    		Main.getPacketHandler().sendToServer(PacketCosmetic.openInterface((byte)1));	    		
        	}
        });
        
        buttons[5] = new UIButton(Type.SQUARE,"Hdv",new ResourceLocation("erthilia","gui/pausemenu/hdv.png"),new ResourceLocation("erthilia","gui/pausemenu/hdv_hover.png"), false, new UIButton.CallBackObject()
        {
        	@Override
        	public void call()
        	{
	    		Minecraft.getMinecraft().displayGuiScreen(null);
	    		Minecraft.getMinecraft().displayGuiScreen(new HdvUI());
        	}
        });
        
        buttons[6] = new UIButton(Type.SQUARE,"Trash",new ResourceLocation("erthilia","gui/pausemenu/trash.png"),new ResourceLocation("erthilia","gui/pausemenu/trash_hover.png"), false, new UIButton.CallBackObject()
        {
        	@Override
        	public void call()
        	{
                Minecraft.getMinecraft().player.sendChatMessage("/poubelle");
        	}
        });
        
        int btnsX = guiLeft+xSize+4;
        int btnsY = guiTop+(ySize-(21*buttons.length))/2;
    	
		for(int i = 0; i < buttons.length; i++)
		{
			int btnPosY = btnsY + i * (19+3);
			buttons[i].texture.color = new UIColor(buttons[i].color.getRed(),buttons[i].color.getGreen(),buttons[i].color.getBlue(),255);
			buttons[i].hoverTexture.color = new UIColor(buttons[i].color.getRed(),buttons[i].color.getGreen(),buttons[i].color.getBlue(),255);
			buttons[i].setPosition(btnsX, btnPosY, 20, 19);
			buttons[i].color = new UIColor(255,255,255,255);
		}
    }

    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        this.fontRenderer.drawString(I18n.format("container.crafting"), 97, 8, 4210752);
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        this.drawDefaultBackground();

      
        super.drawScreen(mouseX, mouseY, partialTicks);
    

        this.renderHoveredToolTip(mouseX, mouseY);

        this.oldMouseX = (float)mouseX;
        this.oldMouseY = (float)mouseY;
    }

    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
    	GL11.glPushMatrix();
    	GL11.glTranslatef(0, 0, 1);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(INVENTORY_BACKGROUND);
        int i = this.guiLeft;
        int j = this.guiTop;
        this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize);
        drawEntityOnScreen(i + 51, j + 75, 30, (float)(i + 51) - this.oldMouseX, (float)(j + 75 - 50) - this.oldMouseY, this.mc.player);
    
        for(UIButton btn : buttons)
        {
        	btn.draw(mouseX, mouseY,partialTicks);
        }
        GL11.glPopMatrix();
    }

    public static void drawEntityOnScreen(int posX, int posY, int scale, float mouseX, float mouseY, EntityLivingBase ent)
    {
        GlStateManager.enableColorMaterial();
        GlStateManager.pushMatrix();
        GlStateManager.translate((float)posX, (float)posY, 50.0F);
        GlStateManager.scale((float)(-scale), (float)scale, (float)scale);
        GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
        float f = ent.renderYawOffset;
        float f1 = ent.rotationYaw;
        float f2 = ent.rotationPitch;
        float f3 = ent.prevRotationYawHead;
        float f4 = ent.rotationYawHead;
        GlStateManager.rotate(135.0F, 0.0F, 1.0F, 0.0F);
        RenderHelper.enableStandardItemLighting();
        GlStateManager.rotate(-135.0F, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(-((float)Math.atan((double)(mouseY / 40.0F))) * 20.0F, 1.0F, 0.0F, 0.0F);
        ent.renderYawOffset = (float)Math.atan((double)(mouseX / 40.0F)) * 20.0F;
        ent.rotationYaw = (float)Math.atan((double)(mouseX / 40.0F)) * 40.0F;
        ent.rotationPitch = -((float)Math.atan((double)(mouseY / 40.0F))) * 20.0F;
        ent.rotationYawHead = ent.rotationYaw;
        ent.prevRotationYawHead = ent.rotationYaw;
        GlStateManager.translate(0.0F, 0.0F, 0.0F);
        RenderManager rendermanager = Minecraft.getMinecraft().getRenderManager();
        rendermanager.setPlayerViewY(180.0F);
        rendermanager.setRenderShadow(false);
        rendermanager.renderEntity(ent, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F, false);
        rendermanager.setRenderShadow(true);
        ent.renderYawOffset = f;
        ent.rotationYaw = f1;
        ent.rotationPitch = f2;
        ent.prevRotationYawHead = f3;
        ent.rotationYawHead = f4;
        GlStateManager.popMatrix();
        RenderHelper.disableStandardItemLighting();
        GlStateManager.disableRescaleNormal();
        GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
        GlStateManager.disableTexture2D();
        GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
    }


    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException
    {
    	if(mouseButton == 0)
    	{
	        for(UIButton button : buttons)
	        {
	        	if(button.onLeftClick(mouseX, mouseY)) return;
	        }
    	}
    
    	super.mouseClicked(mouseX, mouseY, mouseButton);

    }

    protected void mouseReleased(int mouseX, int mouseY, int state)
    {

          super.mouseReleased(mouseX, mouseY, state);
        
    }


    protected void handleMouseClick(Slot slotIn, int slotId, int mouseButton, ClickType type)
    {
        super.handleMouseClick(slotIn, slotId, mouseButton, type);
    }


    public void onGuiClosed()
    {
        super.onGuiClosed();
    }

}
