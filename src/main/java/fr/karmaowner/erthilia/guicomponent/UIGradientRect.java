package fr.karmaowner.erthilia.guicomponent;

import fr.karmaowner.erthilia.utils.GuiUtils;

public class UIGradientRect extends UIRect {

	private UIColor color2;
	
	public UIGradientRect(UIColor color1, UIColor color2)
	{
		super(color1);
		this.color2 = color2;
	}
	
	@Override
	public void draw(int x , int y,float partialTicks)
	{
		GuiUtils.renderGradientRect(posX, posY, posX+width, posY+height,(int)zLevel, color.toRGB(), color2.toRGB());
	}
	
}
