package fr.karmaowner.erthilia.guicomponent;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;

import fr.karmaowner.erthilia.Main;
import fr.karmaowner.erthilia.gui.IGuiClickableElement;
import fr.karmaowner.erthilia.utils.GuiUtils;
import net.minecraft.util.ResourceLocation;

public class UIDropdown extends GraphicObject implements IGuiClickableElement
{
	
	private UIImage arrow = new UIImage(new ResourceLocation(Main.MODID,"gui/dropdown_btn.png"));
		
	private List<String> elements = new ArrayList<String>();
	
	private int elementHeight;
		
	public boolean wasClicked = false;
	
		
	public UIDropdown(int elementHeight, List<String> elements, UIColor rectColor)
	{
		this.elementHeight = elementHeight;
		this.elements = elements;
		this.color = rectColor;
		
		if(elements.size() == 0) elements.add("aucun élément");
		
	}
	
	public GraphicObject setPosition(int x, int y, int width, int height)
	{
		super.setPosition(x, y, width, height);
		arrow.setPosition(x+width-8, y+(height-8)/2, 8, 8);
		return this;
	}
	
	public void draw(int x, int y, float partialTicks)
	{	
		GL11.glPushMatrix();
		GL11.glTranslatef(0, 0, 1000);
		
		GuiUtils.drawRect(this.posX, this.posY, this.width, this.height,(int) zLevel, color.toRGB(), color.getNormalizedAlpha());
		String selectedElement = this.getSelectedElement();

		GuiUtils.renderText(selectedElement.length() > 20 ? selectedElement.substring(0, 20) + "..." : selectedElement, this.posX + 3,(this.posY + (this.height-(int)(getMinecraft().fontRenderer.FONT_HEIGHT*0.8f)) / 2) + 1,GuiUtils.gameColor, 0.8f);
		
		if(!this.wasClicked)
		{
			arrow.draw(x, y,partialTicks);
		}
		
		if(this.wasClicked)
		{
			GuiUtils.StartRotation(arrow.posX, arrow.posY, arrow.width, arrow.height, 180);
			arrow.draw(x, y,partialTicks);
			GuiUtils.StopRotation();
			
			int posY = this.posY + this.height;
			for(String str : elements)
			{
				
				if(elements.indexOf(str) != 0)
				{
					GuiUtils.drawRect(this.posX, posY, this.width, this.elementHeight+2, (int) zLevel,color.toRGB(), 1f);
					GuiUtils.renderText(str.length() > 20 ? str.substring(0,20) + "..." : str, this.posX + 3 , posY + this.elementHeight / 2 - 1,GuiUtils.gameColor,0.8f);
					posY+= this.elementHeight+2;
				}
			}
			GuiUtils.drawRect(posX, posY, width, 0.25D,(int)zLevel, GuiUtils.gameColor, 0.4f);
		}
		GL11.glPopMatrix();
	}
	
	
	public String getSelectedElement()
	{
		return this.elements.get(0);
	}
	
	public int getClickedIndex(int y)
	{	
		return (y - this.posY) /  (this.elementHeight+2);
	}
	
	public void setElement(int index)
	{
		String temp = this.elements.get(index);
		
		this.elements.set(index, this.elements.get(0));
		this.elements.set(0, temp);
	}
		
	public void setElementByName(String name)
	{
		int index = 0;
		for(String el : elements)
		{
			if(el.equalsIgnoreCase(name))
			{
				this.setElement(index);
				return;
			}
			index++;
		}
	}

	@Override
	public boolean onRightClick(int x, int y) {
		return false;
	}
	


	@Override
	public boolean onLeftClick(int x, int y) {
		if(x > this.posX && x < this.posX + this.width && y > this.posY && y < this.posY + this.height)
		{
			if(!wasClicked) 
				this.wasClicked = true;
			else
				this.wasClicked = false;
			return true;
		}
		else if(this.wasClicked && x > this.posX && x < this.posX + this.width && y > this.posY + this.height && y < this.posY + this.height + (this.elements.size()) * this.elementHeight)
		{
			this.setElement(this.getClickedIndex(y));
			this.wasClicked = false;
			return true;
		}
		else
		{
			this.wasClicked = false;
			return false;
		}
	}

	@Override
	public boolean onWheelClick(int x, int y) {
		return false;
	}
}
