package fr.karmaowner.erthilia.guicomponent;

import org.lwjgl.opengl.GL11;

import fr.karmaowner.erthilia.utils.GuiUtils;
import net.minecraft.util.ResourceLocation;

public class UIImage extends GraphicObject {
	
	public ResourceLocation texture;
	
	private boolean transparency;

	public UIImage()
	{
		
	}
	
	
	public UIImage(ResourceLocation texture)
	{
		this.texture = texture;
	}
	
	@Override
	public void draw(int x, int y, float partialTicks)
	{
		if(this.texture != null) this.transparency = texture.getResourcePath().contains(".png");
		GL11.glColor4f((float)color.getRed() / 255f,(float)color.getGreen() / 255f,(float)color.getBlue()/255f,color.getNormalizedAlpha());
		if(this.texture != null && this.transparency)
		{
			GuiUtils.drawImageWithTransparency(posX, posY, texture, width, height, (int)zLevel);
		}
		else
		{
			GuiUtils.drawImage(posX, posY, texture, width, height,(int) zLevel);
		}
		

		super.draw(x, y,partialTicks);
	}
	
	public void setTexture(ResourceLocation texture)
	{
		this.texture = texture;
	}
	
	

}
