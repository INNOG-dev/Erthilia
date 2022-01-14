package fr.karmaowner.erthilia.handler;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

import org.lwjgl.opengl.GL11;

import com.google.common.base.Preconditions;

import fr.karmaowner.erthilia.Main;
import fr.karmaowner.erthilia.guicomponent.GraphicObject;
import fr.karmaowner.erthilia.guicomponent.UIButton;
import fr.karmaowner.erthilia.guicomponent.UIButton.Type;
import fr.karmaowner.erthilia.guicomponent.UIColor;
import fr.karmaowner.erthilia.guicomponent.UIRect;
import fr.karmaowner.erthilia.potion.PotionInformations;
import fr.karmaowner.erthilia.utils.GuiUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;

public class PotionHudHandler extends GraphicObject {
	
    protected static final ResourceLocation inventoryTexture = new ResourceLocation("textures/gui/container/inventory.png");
	
	private Minecraft mc = Minecraft.getMinecraft();
	
	public PotionHudConfig config;
	
	public UIRect editorRect = new UIRect(new UIColor(0,0,0,100));
	
	public UIButton moveBtn = new UIButton(Type.ROUNDED,"MoveBtn", new ResourceLocation(Main.MODID,"gui/move_btn.png"),null,false,null);
	public UIButton editBtn = new UIButton(Type.ROUNDED,"EditBtn", new ResourceLocation(Main.MODID,"gui/edit_btn.png"),null,false,null);

	
	private long systemTime = System.currentTimeMillis();
		
	public PotionHudHandler()
	{
		config = new PotionHudConfig();
	}

	@SuppressWarnings("deprecation")
	@Override
	public void draw(int x, int y, float partialTicks)
	{
		if(!config.getIsEnabled()) return;
		
		GL11.glPushMatrix();
		GL11.glScalef(getScale(), getScale(), getScale());
				
		
		int i = (int) (this.getX());
		int j = (int) (this.getY());
		
		if(!shouldDisplayTittle())
		{
			this.mc.player.getActivePotionEffects();
		

	        GL11.glDisable(GL11.GL_LIGHTING);
			

			for(Iterator<PotionEffect> iterator = this.mc.player.getActivePotionEffects().iterator(); iterator.hasNext(); j+= 20)
			{
			
				//GL11.glPushMatrix();
				//GL11.glTranslatef(0, getYOffset(), 0);
				PotionInformations potionInfo = new PotionInformations((PotionEffect)iterator.next());

				if(shouldDraw(potionInfo))
				{
		    		this.mc.getTextureManager().bindTexture(inventoryTexture);
					
		    		GL11.glColor4f(1f, 1f, 1f, 1f);
		            if(potionInfo.hasStatusIcon())
		            {
		                int l = potionInfo.getStatusIconIndex();
		                this.drawTexturedModalRect(i , j-1, 0 + l % 8 * 18, 198 + l / 8 * 18, 18, 18);
		            }
		            
		            if(!potionInfo.shouldDrawInInventory()) continue;
		            
		            potionInfo.getPotion().renderInventoryEffect(posX + 9, j, potionInfo.getPotionEffect(), mc);
					GuiUtils.renderText("§7Niv §c" + potionInfo.getPotionAplifier(), posX + 23, j,GuiUtils.gameColor,0.9f);
					GuiUtils.renderText(potionInfo.getPotionDurationText(), posX + 23, j+8);
				}
				//GL11.glPopMatrix();
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
			net.minecraftforge.fml.client.config.GuiUtils.drawHoveringText(Arrays.asList(new String[] {"PotionHud"}), x, y, width, mc.displayHeight, -1, mc.fontRenderer); 
            GlStateManager.disableLighting();
            GlStateManager.disableDepth();
            RenderHelper.disableStandardItemLighting();
            GlStateManager.disableRescaleNormal();
		}

	}
	
	@Override
	public GraphicObject setPosition(int posX, int posY, int width, int height) {
		super.setPosition(posX, posY, width, height);
		this.editorRect.setPosition(posX, posY-2, width, height);
		return this;
	}
	
	public int getHeight()
	{
        Collection<?> collection = this.mc.player.getActivePotionEffects();
        if(collection.size() == 0)
        {
        	return 30;
        }
        return 1 + 20 * collection.size();
	}
	
	public int getYOffset()
	{
        Collection<?> collection = this.mc.player.getActivePotionEffects();
        if(collection.size() >= 5)
        {
        	int i = collection.size() - 5;
        	return -i*5;
        }
        return 0;
	}
	
	public int getWidth()
	{
		return 50;		
	}
	
	public boolean shouldDisplayTittle()
	{
        Collection<?> collection = this.mc.player.getActivePotionEffects();
        if(collection.size() == 0)
        {
        	return true;
        }
        return false;
	}
	
	public boolean shouldDraw(PotionInformations potion)
	{
		if(potion.getPotionEffect().getDuration() / 20 <= 10)
		{
			if((System.currentTimeMillis() - systemTime) / 1000 >= 1)
			{
				if((System.currentTimeMillis() - systemTime) / 1000 >= 1.1)
				{
					systemTime = System.currentTimeMillis();
				}
				return false;
			}
			return true;
		}
		return true;
	}

	public class PotionHudConfig
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
