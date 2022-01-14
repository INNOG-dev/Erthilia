package fr.karmaowner.erthilia.armoreffect;

import java.awt.Color;
import java.util.Arrays;

import org.lwjgl.opengl.GL11;

import fr.karmaowner.erthilia.ClientProxy;
import fr.karmaowner.erthilia.Main;
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
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class ArmorEffectContainer extends GraphicObject {

	private Minecraft mc = Minecraft.getMinecraft();

	public ArmorEffectConfiguration config = ClientProxy.armoreffectConfig;
	
	public UIRect editorRect = new UIRect(new UIColor(0,0,0,100));
	public UIButton moveBtn = new UIButton(Type.ROUNDED,"MoveBtn", new ResourceLocation(Main.MODID,"gui/move_btn.png"),null,false,null);
	public UIButton editBtn = new UIButton(Type.ROUNDED,"EditBtn", new ResourceLocation(Main.MODID,"gui/edit_btn.png"),null,false,null);
		
	public void draw(int x, int y, float partialTicks)
	{
		if(!ClientProxy.armoreffectConfig.getIsEnabled()) return;
		
		GL11.glPushMatrix();
		GL11.glScalef(getScale(), getScale(), getScale());

		if(!this.shouldDisplayTittle())
		{
	    	for(int i = 0; i < mc.player.inventory.armorInventory.size(); i++)
	    	{
	    		ItemStack is = mc.player.inventory.armorInventory.get(4-(i+1));
	    		if(!is.isEmpty())
	    		{
		    		int center = (posX + getX2() - 16) / 2;
		        	int durability = is.getMaxDamage()-is.getItemDamage();
		        	
		        	if(config.getCurrentTheme() == 3)
		        	{
		        		
			        	GuiUtils.renderItemStackIntoGUI(is, center, getY() + i * 26, "");

		        		GuiUtils.renderCenteredText("" + durability, (getX() + getX2() + 2) / 2, (getY() + i * 26) + 18, 0.8f, GuiUtils.gameColor);
		        	}
		        	else if(config.getCurrentTheme() == 1)
		        	{
			        	GuiUtils.renderItemStackIntoGUI(is, getX() + 5, getY() + i * 18, "");

		        		int percentage = (int) ((float) durability / (float) is.getMaxDamage() * 100);

		        		String durabilityPercentage = (int) ((float) durability / (float) is.getMaxDamage() * 100) + "%";
		        		
		        		if(percentage >= 80) durabilityPercentage = "§2" + durabilityPercentage;
			        	else if(percentage >= 20 && percentage < 80) durabilityPercentage = "§6" + durabilityPercentage;
			        	else durabilityPercentage = "§c" + durabilityPercentage;
		        		
		        		
		        		GuiUtils.renderText("" + durabilityPercentage,  getX() + 23, (getY() + i * 18 + 10/2), GuiUtils.gameColor,0.8f);
		        	}
		        	else if(config.getCurrentTheme() == 2)
		        	{
			        	GuiUtils.renderItemStackIntoGUI(is, center, getY() + i * 26, "");

		        		UIProgressBar bar = new UIProgressBar(new UIRect(new UIColor(0,0,0,200)), new UIRect(new UIColor(0,0,0,255)));
			        	bar.setValue((float)durability / (float)is.getMaxDamage());
			        	bar.setPosition(getX() + 5, (getY() + i * 26) + 18, getWidth() - 10, 1);
			        	
			        	if(bar.getValue() >= 0.8f) bar.getBarFill().setColor(new UIColor(Color.green));
			        	else if(bar.getValue() >= 0.2f && bar.getValue() < 0.8f) bar.getBarFill().setColor(new UIColor(Color.orange));		       
			        	else bar.getBarFill().setColor(new UIColor(Color.red));
			        	
			        	
			        	bar.draw(0, 0, partialTicks);
		        	}
	    		}
	    	}
    	}
	

    	GL11.glPopMatrix();
    	
	}
	
	public void drawEditor(int x, int y, float partialTicks)
	{
		GL11.glPushMatrix();
		
		GL11.glScalef(getScale(), getScale(), getScale());
    	if(this.editorRect.isHover(x, y))
		{
    		this.editorRect.contourColor = new UIColor(0,191,255,255);
    		GL11.glPushMatrix();
    		GL11.glTranslatef(0, 0, 10);
			this.moveBtn.draw(x, y, partialTicks);
			this.editBtn.draw(x, y, partialTicks);
			GL11.glPopMatrix();
			this.moveBtn.setPosition(this.getX2() - 5, this.posY-2, 8, 8);
			this.editBtn.setPosition(this.getX2() - 15, this.posY-2, 8, 8);
			this.editBtn.setVisible(true);
			this.moveBtn.setVisible(true);
			this.moveBtn.setScale(getScale());
			this.editBtn.setScale(getScale());

		}
		else
		{
    		this.editorRect.contourColor = null;
			this.editBtn.setVisible(false);
			this.moveBtn.setVisible(false);
		}
    	
    	
    	this.editorRect.setWidth(getWidth());
    	this.editorRect.setHeight(getHeight());
    	this.editorRect.setScale(getScale());
		this.editorRect.draw(x, y, partialTicks);
		
		GL11.glPopMatrix();
		
		this.draw(x, y, partialTicks);
		
		if(editorRect.isHover(x, y))
		{ 
	        net.minecraftforge.fml.client.config.GuiUtils.drawHoveringText(Arrays.asList(new String[] {"ArmorEffect"}), x, y, width, mc.displayHeight, -1, mc.fontRenderer);
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
		this.editorRect.setPosition(posX, posY, width, height);
		return this;
	}
	
	public int getWidth()
	{
		if(config.getCurrentTheme() == 1)
		{
			this.setWidth(50);
			return 50;
		}
		else if(config.getCurrentTheme() == 2)
		{
			this.setWidth(30);
			return 30;
		}
		else
		{
			this.setWidth(30);
			return 30;
		}
	}
	
	public int getHeight()
	{
		if(config.getCurrentTheme() == 1)
		{
			this.setHeight(70);
			return 70;
		}
		else if(config.getCurrentTheme() == 2)
		{
			this.setHeight(100);
			return 100;
		}
		else
		{
			this.setHeight(105);
			return 105;
		}
	}
	
	public boolean shouldDisplayTittle()
	{
		int nullCount = 0;
		for(int i = 0; i < mc.player.inventory.armorInventory.size(); i++)
		{
			ItemStack is = mc.player.inventory.armorInventory.get(4-(i+1));
			if(is == null) nullCount++;
		}
		return nullCount == 4 ? true : false;
	}
	
	
}
