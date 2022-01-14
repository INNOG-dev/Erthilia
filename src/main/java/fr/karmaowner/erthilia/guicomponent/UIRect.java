package fr.karmaowner.erthilia.guicomponent;

import fr.karmaowner.erthilia.utils.GuiUtils;

public class UIRect extends GraphicObject {

	public UIButton moveBtn;
	
	public UIButton editBtn;
	

	public UIRect(UIColor color)
	{
		this(color,null);
	}
	
	public UIRect(UIColor color, UIColor contourColor)
	{
		this.color = color;
		this.contourColor = contourColor;
	}
	
	
	@Override
	public void draw(int x, int y, float partialTicks)
	{
		
		GuiUtils.drawRect(posX, posY, width, height,(int) zLevel,this.color.toRGB(), this.color.getNormalizedAlpha());
		super.draw(x, y,partialTicks);
		
	}

}
