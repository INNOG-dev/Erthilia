package fr.karmaowner.erthilia.guicomponent;

import fr.karmaowner.erthilia.utils.MathsUtils;

public class UIProgressBar extends GraphicObject {
	
	private float value;
	
	private GraphicObject bar;
	
	private GraphicObject bar_fill;
	

	public UIProgressBar(GraphicObject bar, GraphicObject fill)
	{
		this.bar = bar;
		this.bar_fill = fill;
	}
	
	
	@Override
	public GraphicObject setPosition(int posX, int posY, int width, int height) {
		super.setPosition(posX, posY, width, height);
		if(bar != null)bar.setPosition(posX, posY, width, height);
		bar_fill.setPosition(posX, posY, width, height);
		return this;
	}
	
	public GraphicObject setValue(float value)
	{
		this.value = MathsUtils.Clamp(value, 0, 1);
		return this;
	}
	
	@Override
	public void draw(int x, int y,float partialTicks)
	{
		if(bar!=null) bar.draw(x, y,partialTicks);
		
		bar_fill.setWidth((int)(value * width));
		bar_fill.draw(x, y,partialTicks);
		
		super.draw(x, y,partialTicks);
	}
	
	public float getValue()
	{
		return value;
	}
	
	public GraphicObject getBar()
	{
		return bar;
	}
	
	
	public GraphicObject getBarFill()
	{
		return bar_fill;
	}
	
}
