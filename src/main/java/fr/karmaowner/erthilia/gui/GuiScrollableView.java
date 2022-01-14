package fr.karmaowner.erthilia.gui;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import fr.karmaowner.erthilia.guicomponent.GraphicObject;
import fr.karmaowner.erthilia.guicomponent.UIColor;
import fr.karmaowner.erthilia.guicomponent.UIDropdown;
import fr.karmaowner.erthilia.guicomponent.UIRect;
import fr.karmaowner.erthilia.guicomponent.UIScrollbarHorizontal;
import fr.karmaowner.erthilia.guicomponent.UIScrollbarVertical;
import fr.karmaowner.erthilia.guicomponent.UITextField;
import fr.karmaowner.erthilia.utils.GuiUtils;

public class GuiScrollableView extends GuiBase {

	public UIRect contentRect;
	
	public GraphicObject viewport;
	
	public UIScrollbarVertical scrollBarVertical;
	
	public UIScrollbarHorizontal scrollBarHorizontal;
	
	protected GraphicObject selectedScrollBar;
	
	protected boolean alwaysDisplayScrollBars = false;
		
	protected int spacing = 2;
	
	public GuiScrollableView()
	{

	}

	public GuiScrollableView(UIColor backgroundColor, boolean unscaledgui) {
		this(backgroundColor, null, unscaledgui);
	}
	
	public GuiScrollableView(UIColor backgroundColor, UIColor contourColor, boolean unscaledgui) {
		super(backgroundColor, contourColor);
	}
	
	@Override
	public void drawScreen(int x, int y, float partialTicks)
	{
		if(!IsVisible()) return;
		
		super.drawScreen(x, y, partialTicks);
				
		updateScrollview();
		viewport.draw(x, y, partialTicks);
		

		GuiUtils.setClippingRegion(viewport.getX(), viewport.getY(), viewport.getWidth(), viewport.getHeight());
		contentRect.draw(x, y, partialTicks);
		GuiUtils.disableScissorBox();
		

		if(scrollBarVertical != null)
		{
			if(!alwaysDisplayScrollBars)
			{
				if(needScrollVertical() > 0) scrollBarVertical.setVisible(true);
				else scrollBarVertical.setVisible(false);
			}
			else
			{
				if(needScrollVertical() == 0) scrollBarVertical.setSize(1f);
				scrollBarVertical.setVisible(true);
			}
			
			scrollBarVertical.draw(x, y, partialTicks);
		}
		

		if(scrollBarHorizontal != null)
		{
			if(!alwaysDisplayScrollBars)
			{
				if(needScrollHorizontal() > 0) scrollBarHorizontal.setVisible(true);
				else scrollBarHorizontal.setVisible(false);
			}
			else
			{
				if(needScrollHorizontal() == 0) scrollBarHorizontal.setSize(1f);
				scrollBarHorizontal.setVisible(true);
			}
			
			scrollBarHorizontal.draw(x, y, partialTicks);
		}	
	}

	@Override
	public void initializeComponent() 
	{ 
		this.contentRect = new UIRect(new UIColor(0,0,0,0));
		this.viewport = new UIRect(new UIColor(0,0,0,100));
		this.viewport.contourColor = new UIColor(0,0,255,255);
		this.scrollBarVertical = new UIScrollbarVertical(new UIColor(0,0,0,255),new UIColor(255,255,255,255));
		this.scrollBarVertical.setButtonColor(new UIColor(80,80,80));
		this.scrollBarHorizontal = new UIScrollbarHorizontal(new UIColor(0,0,0,255),new UIColor(255,255,255,255));
		this.scrollBarHorizontal.setButtonColor(new UIColor(80,80,80));

		
		this.selectedScrollBar = scrollBarVertical;
				
		this.setScrollViewPosition((width-200)/2, 10, 200, height-20);
	
		super.initializeComponent();
		
	}
	
	protected void setScrollViewPosition(int x, int y, int width, int height)
	{
		viewport.setPosition(x, y, width, height);
		contentRect.setPosition(x, y, width, 0);
			
		if(scrollBarVertical != null)   scrollBarVertical.setPosition(viewport.getX2()+1, y-1, 6, height+2);
		if(scrollBarHorizontal != null) scrollBarHorizontal.setPosition(viewport.getX()-1, viewport.getY2()+1, width+2, 6);
	}
	
	protected void parameterVerticalScrollbar(int x, int y , int width, int height)
	{
		this.scrollBarVertical.setPosition(x, y, width, height);
		
	}
	
	protected void parameterHorizontalScrollBar(int x, int y , int width, int height)
	{
		this.scrollBarHorizontal.setPosition(x, y, width, height);
	}
	
	/*private void updateScrollview()
	{
		scrollBarVertical.setSize(needScrollVertical());
		scrollBarHorizontal.setSize(needScrollHorizontal());
				
		contentRect.setY(viewport.getY() + (int) (scrollBarVertical.getValue() * (viewport.getHeight()-contentRect.getHeight())));
		contentRect.setX(viewport.getX() + (int) (scrollBarHorizontal.getValue() * (viewport.getWidth()-contentRect.getWidth())));
		
		contentRect.updateChildsOnRect(viewport);
		
		
	}*/
	
	private void updateScrollview()
	{
		if(scrollBarVertical != null)
		{
			scrollBarVertical.setSize(needScrollVertical());
			contentRect.setY(viewport.getY() + (int) (scrollBarVertical.getValue() * (viewport.getHeight()-contentRect.getHeight())));
		}
		else
		{
			contentRect.setY(viewport.getY() + 0 * (viewport.getHeight()-contentRect.getHeight()));
		}
		
		if(scrollBarHorizontal != null)
		{
			scrollBarHorizontal.setSize(needScrollHorizontal());
			contentRect.setX(viewport.getX() + (int) (scrollBarHorizontal.getValue() * (viewport.getWidth()-contentRect.getWidth())));
		}
		else
		{
			contentRect.setX(viewport.getX() + (int) (0 * (viewport.getWidth()-contentRect.getWidth())));
		}
				
		
		contentRect.updateChildsOnRect(viewport);
	}

	
	private float needScrollVertical()
	{
		if(contentRect.getHeight() <= viewport.getHeight()) return 0;
		
		return (float)viewport.getHeight() / (float)contentRect.getHeight();
	}
	
	private float needScrollHorizontal()
	{
		if(contentRect.getWidth() <= viewport.getWidth()) return 0;
		return (float)viewport.getWidth()/ (float)contentRect.getWidth();
	}
	
	@Override
    protected void mouseClicked(int x, int y, int mouseBtn)
    {
		if(dialogBoxActive())
		{
			super.mouseClicked(x, y, mouseBtn);
			return;
		}
		
		if(!IsVisible()) return;
		
		if(mouseBtn == 0)
		{
			if(needScrollVertical() > 0)
			{
				if(scrollBarVertical.onLeftClick(x, y))
				{
					System.out.println("test1");
					selectedScrollBar = scrollBarVertical;
					return;
				}
			}
			if(needScrollHorizontal() > 0)
			{
				if(scrollBarHorizontal.onLeftClick(x, y))
				{
					selectedScrollBar = scrollBarHorizontal;
					return;
				}
			}
		}
			
			
		List<IGuiClickableElement> list = contentRect.visibleChilds.stream().filter(e -> e instanceof IGuiClickableElement).map(sc -> (IGuiClickableElement)sc).collect(Collectors.toList());

		for(IGuiClickableElement element : list)
	    {
		        if(this.mouseOnDropdown(x, y) && !(element instanceof UIDropdown))
		        {
		        	continue;
		        }
		        
				if(x < viewport.getX() || x > viewport.getX2() || y < viewport.getY() || y > viewport.getY2()) break;

	    		switch(mouseBtn)
	            {
	            	case 0:
	            		if(element.onLeftClick(x, y))
	            		{
	            			return; 
	            		}
	            		break;
	            	case 1:
	            		if(element.onRightClick(x, y))
	            		{
	            			return;
	            		}
	            		break;
	            	case 2:
	            		if(element.onWheelClick(x, y))
	            		{
	            			return;
	            		}
	            		break;
	            	default:
	           			break;
	            }
	    }

		super.mouseClicked(x, y, mouseBtn);
    }
	
	 @Override
	 protected void keyTyped(char character, int keycode)
	 {	  
		  List<IGuiKeytypeElement> list = contentRect.visibleChilds.stream().filter(e -> e instanceof IGuiKeytypeElement).map(sc -> (IGuiKeytypeElement)sc).collect(Collectors.toList());
		  for(IGuiKeytypeElement element : list) element.keyTyped(character, keycode);
		  super.keyTyped(character, keycode);
	 }
	
	 @Override
	 protected void mouseReleased(int x, int y, int state)
	 {
		 if(state == 0) 
		 {
			 if(scrollBarVertical != null) scrollBarVertical.mouseReleased(x, y);
			 if(scrollBarHorizontal != null) scrollBarHorizontal.mouseReleased(x, y);
			 
			 for(GuiBase base : childs)
			 {
				 if(base instanceof GuiScrollableView)
				 {
					 GuiScrollableView child = (GuiScrollableView) base;
					 
					 child.mouseReleased(x, y, state);
				 }
			 }
		 }
		 super.mouseReleased(x, y, state);
	 }
	 
	 
	public void updateScrollviewContents()
	{
		resetContentLayout();
	}
	
	
	public void resetContentLayout()
	{
		contentRect.setHeight(0);
		if(scrollBarHorizontal != null) scrollBarHorizontal.setValue(0);
		if(scrollBarVertical != null) scrollBarVertical.setValue(0);
	}
	 
	 public GraphicObject addToContainer(GraphicObject object)
	 {
		 if(object == null)  return null;
		
		 contentRect.addChild(object);
		 
		 positionElement(object);
		 
		 return object;
	 }
	 
	 protected void positionElement(GraphicObject object)
	 {
		 object.localPosX = (contentRect.getWidth() - object.getWidth()) / 2;
		 object.localPosY = contentRect.getHeight();
		 
		 if(object.localPosY + object.getHeight() > contentRect.getHeight())
		 {
			 contentRect.setHeight(contentRect.getHeight() + object.getHeight() + spacing);
		 }
	 }
	 
	 protected void rebuildLayout()
	 {
		 resetContentLayout();
		 for(GraphicObject obj : contentRect.childs)
		 {
			 positionElement(obj);
		 }
	 }
	 
	 public GraphicObject getContentRect()
	 {
		 return this.contentRect;
	 }
	 
	 @Override
	 public void updateScreen()
	 {		 
		  List<UITextField> list = Arrays.stream(contentRect.visibleChilds.toArray()).filter(e -> e instanceof UITextField).map(sc -> (UITextField)sc).collect(Collectors.toList());
		  for(UITextField element : list) element.updateCursorCounter();
		  
		  super.updateScreen();
	 }

	 
	 @Override
	 public void onScroll(int i)
	 {
		 if(selectedScrollBar == null) return;
		 
		 if(selectedScrollBar == scrollBarVertical)
		 {
			 if(scrollBarVertical != null) scrollBarVertical.setValue(scrollBarVertical.getValue()-(i*needScrollVertical()) * 0.09f);
		 }
		 else 
		 {
			 if(scrollBarHorizontal != null) scrollBarHorizontal.setValue(scrollBarHorizontal.getValue()-(i*needScrollHorizontal()) * 0.05f);
		 }
		 super.onScroll(i);
	 }
	
	

}
