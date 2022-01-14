package fr.karmaowner.erthilia.guicomponent;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import fr.karmaowner.erthilia.gui.IGuiEmplacement;
import fr.karmaowner.erthilia.utils.GuiUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderItem;

public class GraphicObject extends Gui implements IGuiEmplacement {
	
	protected GraphicObject parent;
	
	public List<GraphicObject> childs = new ArrayList<GraphicObject>();
	
	public List<GraphicObject> visibleChilds = new ArrayList<GraphicObject>();
	
	private Minecraft mc;
	
	private RenderItem itemRender;
	
	public  UIColor color = new UIColor(255,255,255);
	
	public UIColor contourColor;

	protected int posX;

	protected int posY;
	
	protected int modificatorX;
	protected int modificatorY;
	protected int modificatorWidth;
	protected int modificatorHeight;
	
	public int localPosX;
	
	public int localPosY;

	protected int width;

	protected int height;
	
	protected float scale = 1f;
	
	protected boolean visible = true;
	
	protected boolean enabled = true;
	
	protected int zIndex = 0;
	
	
	public int getX() {
		return this.posX;
	}
	
	public int getY() {
		return this.posY;
	}
	
	public int getX2()
	{
		return posX + width;
	}
	
	public int getY2()
	{
		return posY + height;
	}
	
	public int getWidth()
	{
		return this.width;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public Minecraft getMinecraft()
	{
		if(mc == null)
		{
			mc = Minecraft.getMinecraft();
		}
		
		return mc;
	}
	
	public RenderItem getRenderItem()
	{
		if(itemRender == null)
		{
			itemRender = getMinecraft().getRenderItem();
		}
		
		return itemRender;
	}
	
	
	public GraphicObject setPosition(int posX, int posY, int width, int height) {
		this.posX = posX;
		this.posY = posY;
		this.width = width;
		this.height = height;
		return this;
	}
	
	public GraphicObject setHitboxModificator(int modificatorX, int modificatorY, int modificatorWidth, int modificatorHeight) {
		this.modificatorX = modificatorX;
		this.modificatorY = modificatorY;
		this.modificatorWidth = modificatorWidth;
		this.modificatorHeight = modificatorHeight;
		return this;
	}
	
	
	public void setWidth(int width)
	{
		setPosition(posX,posY,width,height);
	}
	
	public void setHeight(int height)
	{
		setPosition(posX,posY,width,height);
	}
	
	public void setX(int x)
	{
		setPosition(x,posY,width,height);
		for(GraphicObject child : childs)
		{
			child.setX(x + child.localPosX);
		}
	}
	
	public void setY(int y)
	{
		setPosition(posX,y,width,height);
		for(GraphicObject child : childs)
		{
			child.setY(y + child.localPosY);
		}
	}
	
	public GraphicObject setScale(float value)
	{
		this.scale = value;
		return this;
	}
	
	public float getScale()
	{
		return this.scale;
	}
	
	public GraphicObject setZIndex(int zIndex)
	{
		this.zIndex = zIndex;
		return this;
	}
	
	public int getZIndex()
	{
		return zIndex;
	}
	

	public boolean isHover(int x, int y)
	{
		if(x >= (posX + modificatorX) * scale && x <= (posX + width + modificatorWidth) * scale && y >= (posY + modificatorY) * scale && y <= (posY + height + modificatorHeight) * scale)
		{
			return true;
		}
		return false;
	}
	
	public boolean isOnRect(GraphicObject object)
	{
		if(posX + width >= object.posX && posY + height >= object.posY && posX <= object.posX + object.width && posY <= object.posY + object.height)
		{
			return true;
		}
		return false;
	}
	
	public void draw(int x, int y, float partialTicks)
	{
		if(!visible) return;
		
		for(GraphicObject element : visibleChilds)
		{
			GlStateManager.resetColor();
			element.draw(x, y, partialTicks);
		}
		if(contourColor != null)
		{
			int colorInt = contourColor.toRGB();
			float alpha = (float)contourColor.getAlpha() / (float)255;
			GuiUtils.drawRect(posX-1, posY, 1, height,(int)zLevel, colorInt, alpha);
			GuiUtils.drawRect(posX-1,posY-1, width+2, 1,(int)zLevel, colorInt, alpha);
			GuiUtils.drawRect(posX-1, posY+height, width+2, 1,(int)zLevel, colorInt, alpha);
			GuiUtils.drawRect(posX+width, posY, 1, height,(int)zLevel, colorInt, alpha);
		}
	}

	@Override
	public int toCenterX() {
		return posX + width / 2;
	}

	@Override
	public int toCenterY() {
		return posY + height / 2;
	}

	@Override
	public int toRight() {
		return 0;
	}

	@Override
	public int toBottom() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public void addChild(GraphicObject object)
	{
		if(childs.contains(object)) return;
		
		childs.add(object);
	}
	
	public List<GraphicObject> getChilds()
	{
		return this.childs;
	}
	
	public List<GraphicObject> updateChildsOnRect(GraphicObject object)
	{
		return visibleChilds = this.childs.stream().filter(x -> x.isOnRect(object)).collect(Collectors.toList());
	}
	
	public GraphicObject setEnabled(boolean state)
	{
		this.enabled = state;
		return this;
	}
	
	public boolean isEnabled()
	{
		return this.enabled;
	}
	
	public void setVisible(boolean state)
	{
		this.visible = state;
	}
	
	public boolean isVisible()
	{
		return this.visible;
	}
	
	public GraphicObject setContourColor(UIColor color)
	{
		this.contourColor = color;
		return this;
	}
	
	public GraphicObject setColor(UIColor color)
	{
		this.color = color;
		return this;
	}


	
	
}
