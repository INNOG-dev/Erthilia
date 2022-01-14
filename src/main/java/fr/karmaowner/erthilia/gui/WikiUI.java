package fr.karmaowner.erthilia.gui;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.lwjgl.opengl.GL11;

import fr.karmaowner.erthilia.Main;
import fr.karmaowner.erthilia.guicomponent.GraphicObject;
import fr.karmaowner.erthilia.guicomponent.UIButton;
import fr.karmaowner.erthilia.guicomponent.UIButton.CallBackObject;
import fr.karmaowner.erthilia.guicomponent.UIButton.Type;
import fr.karmaowner.erthilia.guicomponent.UIColor;
import fr.karmaowner.erthilia.guicomponent.UIImage;
import fr.karmaowner.erthilia.guicomponent.UIRect;
import fr.karmaowner.erthilia.guicomponent.UIScrollbarVertical;
import fr.karmaowner.erthilia.guicomponent.UITextField;
import fr.karmaowner.erthilia.utils.GuiUtils;
import fr.karmaowner.erthilia.wiki.WikiRecipes;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class WikiUI extends GuiGridView {
	
	private WikiRecipes displayedRecipe;
	
	private String lastSearchFilter = "";
	
	public List<WikiRecipes> recipes = null;
		
	
	public WikiUI()
	{
		this.spacing = 2;
	}
	
	@Override
	public void initGui()
	{
		this.setWindowSize(250, 225);
		this.setWindowPosition((this.width-250) / 2, (this.height-225) / 2);
		super.initGui();
		alwaysDisplayScrollBars = true;
	}
	
	@Override
    protected void keyTyped(char p_73869_1_, int p_73869_2_)
    {
        if (p_73869_2_ == 1)
        {
            this.mc.displayGuiScreen((GuiScreen)null);
            this.mc.setIngameFocus();
            return;
        }
        super.keyTyped(p_73869_1_, p_73869_2_);
    }
	
	@Override
	public void initializeComponent() 
	{ 
		this.addComponent(new UIImage(new ResourceLocation(Main.MODID,"gui/wiki/background.png")).setPosition(this.getWindowPosX(), this.getWindowPosY(), this.getWindowWidth(), this.getWindowHeight()));
		this.addComponent(new UITextField(new UIRect(new UIColor(255,255,255,255)),0.8f,UITextField.Type.TEXT).setPosition(guiRect.getX2()- 145, guiRect.getY() + 6, 110, 13).setColor(new UIColor(255,255,255,0)));
		
		this.contentRect = new UIRect(new UIColor(0,0,0,0));
				
		this.viewport = new UIRect(new UIColor(0,0,0,0));
		
		this.scrollBarVertical = new UIScrollbarVertical(new ResourceLocation(Main.MODID,"gui/wiki/bar.png"),new ResourceLocation(Main.MODID,"gui/wiki/bar_hover.png"));
		this.selectedScrollBar = scrollBarVertical;
		
		setScrollViewPosition(guiRect.getX() + (guiRect.getWidth() - 215) / 2, guiRect.getY()+22, 200, 107);
		
		parameterVerticalScrollbar(guiRect.getX() + guiRect.getWidth()-26, guiRect.getY()+22, 15, 107);		
		
		int craftMatrixSize = (elementSize + spacing+5) * 3;
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				WikiButton button = new WikiButton(ItemStack.EMPTY,Type.SQUARE,new UIRect(new UIColor(0,0,0,0)), null, null);
				this.addComponent(button.setPosition((guiRect.getX() + (guiRect.getWidth()-craftMatrixSize * 2) / 2) + j * (elementSize+spacing+1), viewport.getY2() + 16 + i * (elementSize+spacing), elementSize, elementSize-1));
			}
		}
		
		addComponent(new WikiButton(ItemStack.EMPTY,Type.SQUARE,new UIRect(new UIColor(0,0,255,0)), null, null).setPosition(guiRect.getX2()-92, guiRect.getY2()-63,32,29));
			
		
		if(recipes == null)
		{
			recipes = WikiRecipes.loadedRecipes;
			
			recipes.forEach(x -> 
			
				x.button =  new WikiButton(x.getResult(),Type.SQUARE,"",new ResourceLocation(Main.MODID,"gui/wiki/slot.png"),null,false, new CallBackObject()
				{
					@Override
					public void call()
					{
						displayedRecipe = x;
						displayRecipe();
					}
				}
			));	
		}

		
			
		updateContentElements();	
	}
	
	public void displayRecipe()
	{
		resetCraftDisplay();
				
		if(displayedRecipe == null)
		{
			return;
		}		
		
		for(int i = 2; i < 11; i++)
		{
			WikiButton button = (WikiButton) getComponent(i);
			button.setItemStack(displayedRecipe.getRecipe()[i-2]);	
			button.displayStackSize = true;
		}
		WikiButton button = (WikiButton) getComponent(11);
		button.setItemStack(displayedRecipe.getResult());
		button.displayStackSize = true;
	}
	
	
	public void resetCraftDisplay()
	{
		for(int i = 2; i < 11; i++)
		{
			WikiButton button = (WikiButton)this.getComponent(i);
			button.setItemStack(ItemStack.EMPTY);
			button.displayStackSize = false;
		}
		
		WikiButton button = (WikiButton) getComponent(11);
		button.setItemStack(ItemStack.EMPTY);
		button.displayStackSize = true;
	}
	
	
	@Override
	public void drawScreen(int x, int y, float partialTicks)
	{
		super.drawScreen(x, y, partialTicks);

		GuiUtils.renderText("Rechercher", guiRect.getX() + 45, guiRect.getY() + 9,GuiUtils.gameColor,0.9f);
		
		for(GraphicObject obj : contentRect.visibleChilds)
		{
			if(x < viewport.getX() || x > viewport.getX2() || y < viewport.getY() || y > viewport.getY2()) break;	
			
			if(obj.isHover(x, y))
			{
				WikiButton button = (WikiButton) obj;

				drawHoveringText(Arrays.asList(new String[] {button.getItemStack().getDisplayName()}),x,y,mc.fontRenderer);
				break;
			}
		}
		
		if(displayedRecipe != null)
		{
			GL11.glPushMatrix();    

			WikiButton result = (WikiButton)getComponent(11);
			if(result.isHover(x, y))
			{
                GL11.glColorMask(true, true, true, false);
                this.drawGradientRect(result.getX(), result.getY(), result.getX()+result.getWidth() ,result.getY() + result.getHeight(), -2130706433, -2130706433);
                GL11.glColorMask(true, true, true, true);
        		
                if(result.getItemStack() != null) drawHoveringText(Arrays.asList(new String[] {result.getItemStack().getDisplayName()}),x,y,mc.fontRenderer);
			}
			for(int i = 2; i < 11; i++)
			{
				WikiButton obj = (WikiButton)getComponent(i);
				if(obj.isHover(x, y))
				{
					
	                GL11.glColorMask(true, true, true, false);
	                this.drawGradientRect(obj.getX(), obj.getY(), obj.getX()+obj.getWidth() ,obj.getY() + obj.getHeight(), -2130706433, -2130706433);
	                GL11.glColorMask(true, true, true, true);
	        		GuiUtils.disableScissorBox();
	        		
	        		if(obj.getItemStack() != null) drawHoveringText(Arrays.asList(new String[] {obj.getItemStack().getDisplayName()}),x,y,mc.fontRenderer);
				}
			}
			GL11.glPopMatrix();
		}
	}
	
	
	public List<GraphicObject> getWikiContents(String searchContent)
	{
		if(searchContent.length() == 0)
		{
			return WikiRecipes.loadedRecipes.stream().map(x -> x.button).collect(Collectors.toList());
		}
		return WikiRecipes.loadedRecipes.stream().filter(x -> x.getResult().getDisplayName().toLowerCase().contains(searchContent.toLowerCase())).map(x -> x.button).collect(Collectors.toList());
	}
	
	public void updateContentElements()
	{
		contentRect.childs.clear();
		
		UITextField textField = (UITextField) getComponent(1);
		
		List<GraphicObject> elements = getWikiContents(textField.getText());
		this.resetContainerLayout();
		for(GraphicObject obj : elements)
		{
			obj.localPosX = 0;
			obj.localPosY = 0;
			addToContainer(obj.setPosition(0, 0, elementSize,elementSize));
		}
		contentRect.setHeight(contentRect.getHeight() + spacing);
	}
	
	@Override
	public void updateScreen()
	{
		super.updateScreen();
		UITextField searchTextField = (UITextField)getComponent(1);
		if(lastSearchFilter != searchTextField.getText())
		{
			this.updateContentElements();
			lastSearchFilter = searchTextField.getText();
		}
		
	}
	
	public class WikiButton extends UIButton
	{
				
		private ItemStack itemstack;
		
		public boolean displayStackSize = false;

		public WikiButton(ItemStack is,Type type, String text, ResourceLocation resource,ResourceLocation hoverTexture, boolean displayText, CallBackObject callback) {
			super(type, text, resource,null, displayText, callback);
			this.itemstack = is;
		}
		
		public WikiButton(ItemStack is,Type type , UIRect rect, String text, CallBackObject callback) {
			super(type, rect, text, null, callback);
			this.itemstack = is;
		}
		
		public ItemStack getItemStack()
		{
			return itemstack;
		}
		
		public void setItemStack(ItemStack is)
		{
			itemstack = is;
		}
		
		@Override
		public void draw(int x, int y, float partialTicks)
		{
			super.draw(x, y, partialTicks);
			if(itemstack != null)
			{
				if(getItemStack() != null)GuiUtils.renderItemStackIntoGUI(getItemStack(), posX+(width-16)/2, posY+(height-16)/2,displayStackSize ? ""+ getItemStack().getCount() : "", getRenderItem());
			}
		}

		
	}
	
	
}


