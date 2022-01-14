package fr.karmaowner.erthilia.handler;

import java.util.Arrays;
import java.util.List;

import org.lwjgl.opengl.GL11;

import com.mojang.realmsclient.gui.ChatFormatting;

import fr.karmaowner.erthilia.Main;
import fr.karmaowner.erthilia.entity.monster.EntityMana;
import fr.karmaowner.erthilia.entity.monster.ErthiliaLivingEntity;
import fr.karmaowner.erthilia.entity.power.Power;
import fr.karmaowner.erthilia.guicomponent.GraphicObject;
import fr.karmaowner.erthilia.guicomponent.UIButton;
import fr.karmaowner.erthilia.guicomponent.UIButton.Type;
import fr.karmaowner.erthilia.guicomponent.UIColor;
import fr.karmaowner.erthilia.guicomponent.UIProgressBar;
import fr.karmaowner.erthilia.guicomponent.UIRect;
import fr.karmaowner.erthilia.utils.GuiUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;

public class BossHudHandler extends GraphicObject {
		
	private Minecraft mc = Minecraft.getMinecraft();
	
	public BossHudConfig config;
	
	private ErthiliaLivingEntity displayEntity;
	
	public UIRect editorRect = new UIRect(new UIColor(0,0,0,100));
	
	public UIButton moveBtn = new UIButton(Type.ROUNDED,"MoveBtn", new ResourceLocation(Main.MODID,"gui/move_btn.png"),null,false,null);
	public UIButton editBtn = new UIButton(Type.ROUNDED,"EditBtn", new ResourceLocation(Main.MODID,"gui/edit_btn.png"),null,false,null);

			
	public BossHudHandler()
	{
		config = new BossHudConfig();
	}

	@Override
	public void draw(int x, int y, float partialTicks)
	{
		if(!config.getIsEnabled()) return;
		
		if(displayEntity == null) return;
		
		GL11.glPushMatrix();
		GL11.glScalef(getScale(), getScale(), getScale());
		
		GuiUtils.renderCenteredText(I18n.format(displayEntity.getName(), ChatFormatting.BLACK), posX + width / 2, posY + 5,0.8f, GuiUtils.gameColor);
		
		GuiUtils.renderText("HP", posX + 2, posY + 15, GuiUtils.gameColor,0.7f);

		UIProgressBar HealthprogressBar = new UIProgressBar(new UIRect(new UIColor(0,0,0,100)), new UIRect(new UIColor(255, 15, 247)));
		HealthprogressBar.setPosition(posX + 12, posY+15, width-(2+12), 3);
		HealthprogressBar.setValue(displayEntity.getHealth() / displayEntity.getMaxHealth());
		HealthprogressBar.draw(x, y, partialTicks);
		
		if(displayEntity instanceof EntityMana)
		{
			EntityMana manaEntity = (EntityMana) displayEntity;
			GuiUtils.renderText("MP", posX + 2, posY + 23, GuiUtils.gameColor, 0.7f);
			
			UIProgressBar ManaprogressBar = new UIProgressBar(new UIRect(new UIColor(0,0,0,100)), new UIRect(new UIColor(23, 96, 255)));
			ManaprogressBar.setPosition(posX + 12, posY+23, width-(2+12), 3);
			ManaprogressBar.setValue(manaEntity.getManaCount() / EntityMana.maxMana);
			ManaprogressBar.draw(x, y, partialTicks);
			
			List<Power> powers = manaEntity.entityMagicController.getActivePower();
			
			int startPosY = posY + 34;
			int spaceBetween = 10;
			int i = 0;
			for(Power power : powers)
			{
				GuiUtils.renderText(getPowerDisplay(power), posX + 2, startPosY + (i*spaceBetween), GuiUtils.gameColor,0.7f);
				i++;
			}
		}
        GL11.glPopMatrix();
	}
	
	public String getPowerDisplay(Power power)
	{
		return power.getLeftTime() / 60 + ":" + power.getLeftTime() % 60 +  " " + power.getName();
	}
	
	public void setEntity(ErthiliaLivingEntity entity)
	{
		displayEntity = entity;
	}
	
	
	public void drawEditor(int x, int y, float partialTicks)
	{
		GL11.glPushMatrix();
		GL11.glScalef(getScale(), getScale(), getScale());
		if(this.editorRect.isHover(x, y))
		{
			GL11.glPushMatrix();
			GL11.glTranslatef(0, 0, 10);
			this.moveBtn.draw(x, y, partialTicks);
			this.editBtn.draw(x, y, partialTicks);
			GL11.glPopMatrix();
			this.moveBtn.setPosition(this.getX2() - 10, this.posY-2, 8, 8);
			this.editBtn.setPosition(this.getX2() - 20, this.posY-2, 8, 8);
			this.editBtn.setVisible(true);
			this.moveBtn.setVisible(true);
			this.editBtn.setScale(getScale());
			this.moveBtn.setScale(getScale());
    		this.editorRect.contourColor = new UIColor(0,191,255,255);
		}
		else
		{
			this.editorRect.contourColor = null;
			this.editBtn.setVisible(false);
			this.moveBtn.setVisible(false);
		}
		
		this.editorRect.setScale(getScale());
		this.editorRect.setHeight(this.getHeight());

		this.editorRect.draw(x, y, partialTicks);
				
		GL11.glPopMatrix();
		
		this.draw(x, y, partialTicks);
		
		if(editorRect.isHover(x, y))
		{		
	        net.minecraftforge.fml.client.config.GuiUtils.drawHoveringText(Arrays.asList(new String[] {"BossHud"}), x, y, width, mc.displayHeight, -1, mc.fontRenderer);
            GlStateManager.disableLighting();
            GlStateManager.disableDepth();
            RenderHelper.disableStandardItemLighting();
            GlStateManager.disableRescaleNormal();
		}
	}
	
	@Override
	public GraphicObject setPosition(int posX, int posY, int width, int height)
	{
		super.setPosition(posX, posY, width, height);
		this.editorRect.setPosition(posX, posY-2, width, height);
		return this;
	}
	
	public class BossHudConfig
	{
		private boolean isEnabled = true;
		
		
		public boolean getIsEnabled()
		{
			return this.isEnabled;
		}
		
		public void setEnabled(boolean state)
		{
			this.isEnabled = state;
		}
		
	}

}
