package fr.karmaowner.erthilia.gui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.lwjgl.opengl.GL11;

import fr.karmaowner.erthilia.capability.playercapability.IPlayer;
import fr.karmaowner.erthilia.cosmetics.CosmeticObject;
import fr.karmaowner.erthilia.guicomponent.GraphicObject;
import fr.karmaowner.erthilia.guicomponent.UIButton;
import fr.karmaowner.erthilia.guicomponent.UIButton.Type;
import fr.karmaowner.erthilia.guicomponent.UIColor;
import fr.karmaowner.erthilia.guicomponent.UIImage;
import fr.karmaowner.erthilia.guicomponent.UIRect;
import fr.karmaowner.erthilia.guicomponent.UIScrollbarVertical;
import fr.karmaowner.erthilia.guicomponent.UITextField;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public class CosmetiqueUI extends GuiGridView {
	
	public enum GuiState
	{
		SELECT_CATEGORY,
		SELECT_COSMETIQUE;
	}
	
	private final int elementWidth = 90;
	
	private final int elementHeight = 20;
	
	private final int spacingX = 9;
	 
	private final int spacingY = 9;

	private GuiState currentState = GuiState.SELECT_CATEGORY;
	
	private String selectedCategory;
	
	private String searchedTxt = "";

	private Object[][] buttons = new Object[2][];
	
	private List<CosmetiqueSlot> slots = new ArrayList<CosmetiqueSlot>();
	
	public static IPlayer pe;
	
	public static EntityPlayer player;
	
	public CosmetiqueUI(IPlayer pe)
	{
		super();
		CosmetiqueUI.pe = pe;
		player = Minecraft.getMinecraft().player;
		
		elementSize = 30;
		spacing = 5;
	}
	
	@Override
	public void initGui() 
	{
		setWindowSize(250, 180);
		setWindowPosition((this.width-250) / 2, (this.height - 180)/2);
		super.initGui();
	}

	
	@Override
	public void initializeComponent() 
	{ 
		this.contentRect = new UIRect(new UIColor(0,0,0,0));
		this.viewport = new UIRect(new UIColor(0,0,0,0));
		
		
		guiRect.color = new UIColor(0,0,0,0);
		guiRect.contourColor = new UIColor(0,0,0,0);
		
		resetContainerLayout();
		
		scrollBarHorizontal = null;
		scrollBarVertical = null;

		
		if(currentState == GuiState.SELECT_CATEGORY)
		{
			setScrollViewPosition(guiRect.getX() + (guiRect.getWidth() - 222) / 2, guiRect.getY()+45, 222, 115);
			
			addComponent(new UIImage(new ResourceLocation("erthilia","gui/cosmetique/category/background.png")).setPosition(getWindowPosX(), getWindowPosY(), getWindowWidth(), getWindowHeight()));
			buttons[0] = new String[]{"tete","visage","debloquer","corps","equipe","tout"};
			buttons[1] = new UIButton[buttons[0].length];
			
			for(int i = 0; i < buttons[0].length; i++)
			{
				buttons[1][i] = new UIButton(Type.SQUARE,(String)buttons[0][i],new ResourceLocation("erthilia","gui/cosmetique/category/butons/" + buttons[0][i] + ".png"),new ResourceLocation("erthilia","gui/cosmetique/category/butons/" + buttons[0][i] + "_hover.png"), false, null);
				final int index = i;
				UIButton button = (UIButton)buttons[1][i];
				System.out.println(contentRect.getHeight());
				addToContainer(button.setPosition(0, 0, 80, 20));
				button.callback = new UIButton.CallBackObject()
				{
					public void call()
					{
						selectedCategory = (String) buttons[0][index];
						currentState = GuiState.SELECT_COSMETIQUE;
						initGui();
					}
				};
			}
			
		}
		else
		{
			scrollBarVertical = new UIScrollbarVertical(new ResourceLocation("erthilia","gui/cosmetique/bar.png"),new ResourceLocation("erthilia","gui/cosmetique/bar_hover.png"));
			selectedScrollBar = scrollBarVertical;
			
			alwaysDisplayScrollBars = true;

			setScrollViewPosition(guiRect.getX() + (guiRect.getWidth() - 180) / 2, guiRect.getY()+79, 180, 66);
			parameterVerticalScrollbar(guiRect.getX2()-30,guiRect.getY()+79,6,65);

			addComponent(new UIImage(new ResourceLocation("erthilia","gui/cosmetique/background.png")).setPosition(getWindowPosX(), getWindowPosY(), getWindowWidth(), getWindowHeight()));
			addComponent(new UITextField(new UIRect(new UIColor(0,0,0,0)), 1f, UITextField.Type.TEXT).setPosition((getWindowPosX() + getWindowWidth() / 2) - 42, getWindowPosY() + 47, 81, 16));
		
			updateScrollviewContents();

		}
		
	}
	
	
	
	@Override
    protected void keyTyped(char p_73869_1_, int p_73869_2_)
    {
        if (p_73869_2_ == 1)
        {
        	if(currentState == GuiState.SELECT_COSMETIQUE)
        	{
        		currentState = GuiState.SELECT_CATEGORY;
        		
        		contentRect.childs.clear();

        		initGui();
        		
        		return;
        	}
            this.mc.displayGuiScreen((GuiScreen)null);
            this.mc.setIngameFocus();
            return;
        }
        super.keyTyped(p_73869_1_, p_73869_2_);
    }
	
	
	@Override
	public void drawScreen(int x, int y, float partialTicks)
	{
		super.drawScreen(x, y, partialTicks);
		
		boolean playerIsCreative = mc.player.capabilities.allowEdit;

		if(currentState == GuiState.SELECT_COSMETIQUE)
		{
			for(GraphicObject component : contentRect.visibleChilds)
			{
				if(component.isHover(x, y))
				{
					CosmetiqueSlot slot = (CosmetiqueSlot) component;
					
					if(slot.getCosmeticObj() == null) continue;
					
					if(!playerIsCreative)
					{
						drawHoveringText(Arrays.asList(new String[] {slot.getCosmeticObj().getName()}),x,y,mc.fontRenderer);
					}
					else
					{
						drawHoveringText(Arrays.asList(new String[] {slot.getCosmeticObj().getName(), " §bID : " + slot.getCosmeticObj().getId()}),x,y,mc.fontRenderer);
					}
					
				}
			}
		}
		
	}
	
	
	
	public void updateScrollviewContents()
	{
		slots.clear();
		
		List<CosmeticObject> cosmetics = new ArrayList<CosmeticObject>();
		
		cosmetics = getCosmeticsByFilter(selectedCategory);
		
		if(!searchedTxt.isEmpty())
		{
			cosmetics = applyCosmeticsByKeyword(cosmetics);
		}
		
		for(CosmeticObject co : cosmetics)
		{
				CosmetiqueSlot slot = new CosmetiqueSlot(co,Type.SQUARE,"",new ResourceLocation("erthilia","gui/cosmetique/slot.png"), new ResourceLocation("erthilia","gui/cosmetique/slot_hover.png"),false, new UIButton.CallBackObject()
				{
					@Override
					public void call()
					{
						if(co.getIsEquipped())
						{
							CosmeticObject.unequipCosmetic(player, co.getId());
						}
						else
						{
							List<CosmeticObject> toUnEquip = CosmeticObject.getEquippedCosmeticFromSameType(player, co.getType());
							for(CosmeticObject obj : toUnEquip)
							{
								CosmeticObject.unequipCosmetic(player, obj.getId());
							}
							CosmeticObject.equipCosmetic(player,co.getId());
						}
					}				
				});
				slots.add(slot);
		}
		int needSlot = needSlotToFill();
		for(int i = 0; i < needSlot; i++)
		{
			CosmetiqueSlot slot = new CosmetiqueSlot(Type.SQUARE,"",new ResourceLocation("erthilia","gui/cosmetique/slot.png"), new ResourceLocation("erthilia","gui/cosmetique/slot_hover.png"),false, null);
			slots.add(slot);
		}
		this.updateContentElements();
	}
	
	public int needSlotToFill()
	{
		if(slots.size() == 0)
		{
			return 10;
		}
		
		if(slots.size() <= 10)
		{
			if(slots.size() % 10 == 0) return 0;
			
			return 10 - slots.size() % 10;
		}
		else
		{
			if(slots.size() % 5 == 0) return 0;

			return 5 - slots.size() % 5;

		}
	}
	
	public List<CosmeticObject> getCosmeticsByFilter(String filter)
	{
		if(filter.equalsIgnoreCase("tout"))
		{
			return pe.getCosmeticsDatas();
		}
		else if(filter.equalsIgnoreCase("debloquer"))
		{
			return pe.getCosmeticsDatas().stream().filter(x -> !x.getIsLocked()).collect(Collectors.toList());
		}
		else if(filter.equalsIgnoreCase("equipe"))
		{
			return pe.getCosmeticsDatas().stream().filter(x -> x.getIsEquipped()).collect(Collectors.toList());
		}
		else if(filter.equalsIgnoreCase("tete"))
		{
			return pe.getCosmeticsDatas().stream().filter(x -> x.getType() == (byte)0).collect(Collectors.toList());
		}
		else if(filter.equalsIgnoreCase("visage"))
		{
			return pe.getCosmeticsDatas().stream().filter(x -> x.getType() == (byte)1).collect(Collectors.toList());
		}
		else if(filter.equalsIgnoreCase("corps"))
		{
			return pe.getCosmeticsDatas().stream().filter(x -> x.getType() == (byte)2).collect(Collectors.toList());
		}
		return null;
	}
	
	public List<CosmeticObject> applyCosmeticsByKeyword(List<CosmeticObject> filter)
	{
		return filter.stream().filter(x -> x.getName().toLowerCase().contains(searchedTxt.toLowerCase())).collect(Collectors.toList());
	}
	
	public void resetContainerLayout()
	{
		lastElementposX = 0;
		lastElementposY = 0;
		contentRect.setHeight(0);
		if(scrollBarVertical != null) scrollBarVertical.setValue(0f);
		if(scrollBarHorizontal != null)scrollBarHorizontal.setValue(0f);
	}
	
	public void updateContentElements()
	{
		contentRect.childs.clear();
		
		resetContainerLayout();
		for(GraphicObject obj : slots)
		{
			addToContainer(obj.setPosition(0, 0, elementSize,elementSize));
		}
	}
	
	public GraphicObject addToContainer(GraphicObject object)
	{
		 if(object == null)  return null;
		 
		 positionElement(object);
		 
		 contentRect.addChild(object);
		 
		 return object;
	 }
	
	@Override
	protected void positionElement(GraphicObject object)
	{
		if(currentState == GuiState.SELECT_CATEGORY)
		{
			 if(contentRect.getChilds().size() == 0) 
			 {
				 lastElementposX = object.localPosX += lastElementposX + 20;
				 lastElementposY = object.localPosY += spacingY + 10;
				 contentRect.setHeight(contentRect.getHeight() + elementHeight + 10 + spacingY);
			 }
			 else
			 {
				 lastElementposX = object.localPosX += lastElementposX + spacingX + elementWidth;
				 object.localPosY = lastElementposY;
			 }
			 
			 if(lastElementposX + object.getWidth() > viewport.getWidth())
			 {
				 lastElementposY = object.localPosY += spacingY + elementHeight;
				 object.localPosX = lastElementposX = 20;
				 contentRect.setHeight(contentRect.getHeight() + elementHeight + spacingY);
			 }		 
		}
		else
		{
			 if(contentRect.getChilds().size() == 0) 
			 {
				 lastElementposX = object.localPosX += lastElementposX + spacing;
				 lastElementposY = object.localPosY;
				 contentRect.setHeight(contentRect.getHeight() + elementSize);
			 }
			 else
			 {
				 lastElementposX = object.localPosX += lastElementposX + spacing + elementSize;
				 object.localPosY = lastElementposY;
			 }
			 
			 if(lastElementposX + object.getWidth() > viewport.getWidth())
			 {
				 lastElementposY = object.localPosY += spacing + elementSize;
				 object.localPosX = lastElementposX = spacing;
				 contentRect.setHeight(contentRect.getHeight() + elementSize + spacing);
			 }		 
		}
	}
	
	 @Override
	 public void updateScreen()
	 {
		 if(this.currentState == GuiState.SELECT_COSMETIQUE)
		 {
			 UITextField textField = (UITextField) this.getComponent(1);
			 if(this.searchedTxt != textField.getText())
			 {
				 this.searchedTxt = textField.getText();
				 this.updateScrollviewContents();
			 }
		 }
		 super.updateScreen();
	 }
	 
	 @Override
	 public void onGuiClosed() 
	 {
		 if(pe != null) pe.updateRenderer();
	 }

}

class CosmetiqueSlot extends UIButton
{
	CosmeticObject attribuatedCosmetic;
	
	private final static ResourceLocation lockedTexture = new ResourceLocation("erthilia","gui/cosmetique/locked.png");
	private final static ResourceLocation selectedTexture = new ResourceLocation("erthilia","gui/cosmetique/selected.png");
	
	
	private UIImage lockedImage = new UIImage(lockedTexture);
	private UIImage selectedImage = new UIImage(selectedTexture);

	//Empty Slot
	public CosmetiqueSlot(Type type, String text, ResourceLocation resource,ResourceLocation hoverTexture, boolean displayText, CallBackObject callback) 
	{
		super(type, text, resource,hoverTexture, displayText, callback);
		selectedImage.color = new UIColor("#5ee609");
	}
	
	public CosmetiqueSlot(CosmeticObject co,Type type, String text, ResourceLocation resource,ResourceLocation hoverTexture, boolean displayText, CallBackObject callback) 
	{
		super(type, text, resource,hoverTexture, displayText, callback);
		selectedImage.color = new UIColor("#5ee609");
		this.attribuatedCosmetic = co;
	}
	
	public CosmetiqueSlot(CosmeticObject co,Type type , UIRect rect, String text, UIRect hoverRect, CallBackObject callback) 
	{
		super(type, rect, text, hoverRect, callback);
		lockedImage.color = new UIColor(255,215,0,255);
		selectedImage.color = new UIColor("#5ee609");
		this.attribuatedCosmetic = co;
	}
	
	public CosmeticObject getCosmeticObj()
	{
		return attribuatedCosmetic;
	}
	
	public void setCosmeticObj(CosmeticObject co)
	{
		attribuatedCosmetic = co;
	}
	
	public boolean isLocked()
	{
		return attribuatedCosmetic.getIsLocked();
	}
	
	public boolean isEquipped()
	{
		return attribuatedCosmetic.getIsEquipped();
	}
	
	@Override
	public GraphicObject setPosition(int x, int y, int width, int height)
	{
		super.setPosition(x, y, width, height);
		lockedImage.setPosition(x+(width-10)/2, y+(height-10)/2, 10, 10);
		selectedImage.setPosition(x+width-6, y+height-6, 6, 6);
		return this;
	}

	@Override
	public void draw(int x, int y, float partialTicks)
	{
		super.draw(x, y, partialTicks);
		if(attribuatedCosmetic != null)
		{
			
			
			GL11.glPushMatrix();
						
			attribuatedCosmetic.renderModelInGui(posX, posY, 0);
			
			if(isEquipped())
			{
				selectedImage.draw(x, y, partialTicks);
			}
			
			GuiBase gui = (GuiBase) Minecraft.getMinecraft().currentScreen;
			if(gui.mouseOnDropdown(x,y))
			{
				GL11.glPopMatrix();
				return;
			}
			
			
			if(isHover(x,y))
			{
				GL11.glTranslatef(0, 0, 999);

				if(isLocked())
				{
					lockedImage.draw(x, y, partialTicks);
				}
			}
			GL11.glPopMatrix();
			


		}

	}
	

}
	

