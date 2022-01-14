package fr.karmaowner.erthilia.handler;

import java.util.Arrays;

import org.lwjgl.opengl.GL11;

import com.google.common.base.Preconditions;

import fr.karmaowner.erthilia.ClientProxy;
import fr.karmaowner.erthilia.Main;
import fr.karmaowner.erthilia.guicomponent.GraphicObject;
import fr.karmaowner.erthilia.guicomponent.UIButton;
import fr.karmaowner.erthilia.guicomponent.UIButton.Type;
import fr.karmaowner.erthilia.guicomponent.UIColor;
import fr.karmaowner.erthilia.guicomponent.UIRect;
import fr.karmaowner.erthilia.utils.GuiUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;

public class ToggleSprintHandler extends GraphicObject {
	
	public ToggleSprintConfig config;
		
	public UIRect rect = new UIRect(new UIColor(0,0,0,100));
	public UIButton moveBtn = new UIButton(Type.ROUNDED,"MoveBtn", new ResourceLocation(Main.MODID,"gui/move_btn.png"),null,false,null);
	public UIButton editBtn = new UIButton(Type.ROUNDED,"EditBtn", new ResourceLocation(Main.MODID,"gui/edit_btn.png"),null,false,null);
	
	public ToggleSprintHandler() {
		config = new ToggleSprintConfig();
	}

	public boolean toggleSprint = true;
	
	private Minecraft mc;
	
	public void setToggleSprint(boolean state)
	{
		this.toggleSprint = state;
	}

	@SubscribeEvent
	public void Update(TickEvent.ClientTickEvent event)
	{
		if(event.phase == Phase.END)
		{
			mc = Minecraft.getMinecraft();
			
			if(mc != null && mc.world != null)
			{
				if(toggleSprint && mc.gameSettings.keyBindForward.isPressed())
				{
					mc.player.setSprinting(true);
				}
			}
		}
	}
	
	public void draw(int x, int y, float partialTicks)
	{		
		rect.color.setAlpha(0);
		this.rect.contourColor = null;

		GL11.glPushMatrix();
		GL11.glColor3f(1, 1, 1);
		GL11.glScalef(getScale(), getScale(), getScale());
		String toggleSprintInfo = "§eToggleSprint : " + (ClientProxy.getToggleSprintHandler().toggleSprint ? "§a§lON" : "§c§lOFF");
		this.width = getWidth();

		this.rect.setWidth(this.width);
		this.rect.draw(x, y, partialTicks);
		GuiUtils.renderText(toggleSprintInfo, getX() + 2, getY() + (getHeight() - mc.fontRenderer.FONT_HEIGHT / 2)  / 2, GuiUtils.gameColor, 0.7f);

		GL11.glPopMatrix();
	}
	
	public void drawEditor(int x, int y, float partialTicks)
	{
		rect.color.setAlpha(100);
		GL11.glPushMatrix();
		GL11.glScalef(getScale(), getScale(), getScale());
    	if(this.rect.isHover(x, y))
		{
    		this.rect.contourColor = new UIColor(0,191,255,255);
			GL11.glPushMatrix();
			GL11.glTranslatef(0, 0, 10);
			this.moveBtn.draw(x, y, partialTicks);
			this.editBtn.draw(x, y, partialTicks);
			this.moveBtn.setPosition(this.getX2() - 5, this.posY-2, 8, 8);
			this.editBtn.setPosition(this.getX2() - 15, this.posY-2, 8, 8);
			this.editBtn.setVisible(true);
			this.moveBtn.setVisible(true);
			this.moveBtn.setScale(getScale());
			this.editBtn.setScale(getScale());
			GL11.glPopMatrix();
		}
		else
		{
			this.rect.contourColor = null;
			this.editBtn.setVisible(false);
			this.moveBtn.setVisible(false);
		}
		
	
		String toggleSprintInfo = "§eToggleSprint : " + (ClientProxy.getToggleSprintHandler().toggleSprint ? "§a§lON" : "§c§lOFF");
		this.width = getWidth();
		this.rect.setWidth(this.width);
		this.rect.setScale(getScale());
		this.rect.draw(x, y, partialTicks);
		
		GuiUtils.renderText(toggleSprintInfo, getX() + 2, getY() + (getHeight() - mc.fontRenderer.FONT_HEIGHT / 2)  / 2, GuiUtils.gameColor, 0.7f);
		

		
		if(rect.isHover(x, y))
		{
			net.minecraftforge.fml.client.config.GuiUtils.drawHoveringText(Arrays.asList(new String[] {"ToggleSprint"}), x, y, width, mc.displayHeight, -1, mc.fontRenderer);
            GlStateManager.disableLighting();
            GlStateManager.disableDepth();
            RenderHelper.disableStandardItemLighting();
            GlStateManager.disableRescaleNormal();
         }

		
		GL11.glPopMatrix();
	}
	
	
	@Override
	public int getWidth()
	{
		String toggleSprintInfo = "§eToggleSprint : " + (ClientProxy.getToggleSprintHandler().toggleSprint ? "§a§lON" : "§c§lOFF");
		return GuiUtils.getStringWidth(mc, toggleSprintInfo, 0.7f);
	}
	
	@Override
	public GraphicObject setPosition(int posX, int posY, int width, int height) {
		super.setPosition(posX, posY, width, height);
		this.rect.setPosition(posX, posY, width, height);
		return this;
	}
	
	public class ToggleSprintConfig
	{
		private boolean isEnabled = true;
		
		private int opacity;
		
		public boolean getIsEnabled()
		{
			return this.isEnabled;
		}
		
		public void setEnabled(boolean state)
		{
			this.isEnabled = state;
		}
		
		public int getOpacity()
		{
			return this.opacity;
		}
		
		public void setOpacity(int opacity)
		{
			Preconditions.checkArgument(opacity > 0 || opacity < 255);
			this.opacity = opacity;
		}
	}

	
}
