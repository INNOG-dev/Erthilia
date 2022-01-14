package fr.karmaowner.erthilia.gui;

import fr.karmaowner.erthilia.guicomponent.GraphicObject;
import fr.karmaowner.erthilia.guicomponent.UIColor;
import fr.karmaowner.erthilia.guicomponent.UIRect;
import fr.karmaowner.erthilia.guicomponent.UIScrollbarHorizontal;
import fr.karmaowner.erthilia.guicomponent.UIScrollbarVertical;
import fr.karmaowner.guireader.Reader;

public class NewsUI extends GuiScrollableView {

	private Reader uiReader;
	
	public NewsUI()
	{
		super();
		spacing = 1;
		uiReader = new Reader("http://localhost/api/news.kfile");
	}
	
	
	@Override
	public void initGui() 
	{
		setWindowSize(400, 200);
		setWindowPosition((this.width-250) / 2, (this.height - 200)/2);
		super.initGui();
	}

	@Override
	public void drawScreen(int x, int y, float partialTicks)
	{
		super.drawScreen(x, y, partialTicks);		
	}
	
	@Override
	public void initializeComponent() 
	{ 
		this.contentRect = new UIRect(new UIColor(0,0,0,0));
		this.viewport = new UIRect(new UIColor("#373737"));
		
		this.scrollBarVertical = new UIScrollbarVertical(new UIColor("#3A3B37"),new UIColor("#00FF00"));
		this.scrollBarHorizontal = new UIScrollbarHorizontal(new UIColor("#3A3B37"),new UIColor("#00FF00"));
		this.selectedScrollBar = scrollBarVertical;
		
		guiRect.color = new UIColor("#2d2d2d");
		guiRect.contourColor = new UIColor("#A2A4A4");
		
		setScrollViewPosition(guiRect.getX() + (guiRect.getWidth() - 222) / 2, guiRect.getY()+35, 222, 160);
		

		updateScrollviewContents();
	}
	
	public void updateScrollviewContents()
	{
		this.updateContentElements();
	}
	
	
	public void updateContentElements()
	{
		contentRect.childs.clear();
		for(GraphicObject obj : uiReader.getComponents())
		{
			obj.localPosX = 0;
			obj.localPosY = 0;
			addToContainer(obj.setPosition(0, 0, obj.getWidth(),obj.getHeight()));
		}
		contentRect.setHeight(contentRect.getHeight() + spacing + 1);
	}
	
	 public GraphicObject addToContainer(GraphicObject object)
	 {
		 if(object == null)  return object;
		 
		 contentRect.addChild(object);
		 
		 object.localPosX = 5;
		 object.localPosY = contentRect.getHeight()+10;
		 
		 if(object.localPosY + object.getHeight() > contentRect.getHeight())
		 {
			 contentRect.setHeight(contentRect.getHeight() + object.getHeight() + spacing);
		 }
		 
		 return object;
	 }
	
	 
	 

}
	

