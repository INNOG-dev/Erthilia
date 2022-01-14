package fr.karmaowner.erthilia.gui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.lwjgl.opengl.GL11;

import fr.karmaowner.erthilia.Main;
import fr.karmaowner.erthilia.capability.playercapability.IPlayer;
import fr.karmaowner.erthilia.cosmetics.CompanionObject;
import fr.karmaowner.erthilia.cosmetics.CosmeticManager;
import fr.karmaowner.erthilia.cosmetics.CosmeticObject;
import fr.karmaowner.erthilia.guicomponent.GraphicObject;
import fr.karmaowner.erthilia.guicomponent.UIButton;
import fr.karmaowner.erthilia.guicomponent.UIButton.Type;
import fr.karmaowner.erthilia.guicomponent.UIColor;
import fr.karmaowner.erthilia.guicomponent.UIImage;
import fr.karmaowner.erthilia.guicomponent.UIRect;
import fr.karmaowner.erthilia.guicomponent.UIScrollbarVertical;
import fr.karmaowner.erthilia.guicomponent.UIText;
import fr.karmaowner.erthilia.guicomponent.UITextField;
import fr.karmaowner.erthilia.network.PacketCosmetic;
import fr.karmaowner.erthilia.potion.PotionButton;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;

public class CompanionUI extends GuiGridView {
		
	private String searchedTxt = "";
		
	private List<CompanionSlot> slots = new ArrayList<CompanionSlot>();
	
	public static IPlayer pe;
	
	public static EntityPlayer player;
	
	public CosmeticObject selectedCompanion;
	
	public CompanionUI(IPlayer playerData)
	{
		super();
		pe = playerData;
		
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
		
		getChild(0).setVisible(false);
	}

	
	@Override
	public void initializeComponent() 
	{ 
		this.contentRect = new UIRect(new UIColor(0,0,0,0));
		this.viewport = new UIRect(new UIColor(0,0,0,0));
		
		guiRect.color = new UIColor(0,0,0,0);
		guiRect.contourColor = new UIColor(0,0,0,0);
		
	
		scrollBarVertical = new UIScrollbarVertical(new ResourceLocation("erthilia","gui/cosmetique/bar.png"),new ResourceLocation("erthilia","gui/cosmetique/bar_hover.png"));
		selectedScrollBar = scrollBarVertical;
			
		alwaysDisplayScrollBars = true;

		setScrollViewPosition(guiRect.getX() + (guiRect.getWidth() - 180) / 2, guiRect.getY()+79, 180, 67);
		parameterVerticalScrollbar(guiRect.getX2()-30,guiRect.getY()+79,7,67);

		addComponent(new UIImage(new ResourceLocation("erthilia","gui/companion/background.png")).setPosition(getWindowPosX(), getWindowPosY(), getWindowWidth(), getWindowHeight()));
		addComponent(new UITextField(new UIRect(new UIColor(0,0,0,0)), 1f, UITextField.Type.TEXT).setPosition((getWindowPosX() + getWindowWidth() / 2) - 42, getWindowPosY() + 47, 81, 16));
		
		updateScrollviewContents();
	}
	
	@Override
	public void registerChilds()
	{
		addChild(new EffectSelectBox());
		super.registerChilds();
	}
	
	
	@Override
    protected void keyTyped(char p_73869_1_, int p_73869_2_)
    {
        if (p_73869_2_ == 1)
        {
        	if(dialogBoxActive())
        	{
        		destroyDialogBox(0);
        	}
        	else if(getChild(0).IsVisible())
        	{
        		getChild(0).setVisible(false);
        	}
        	else
        	{
	            this.mc.displayGuiScreen((GuiScreen)null);
	            this.mc.setIngameFocus();
        	}
	        return;
        }
        super.keyTyped(p_73869_1_, p_73869_2_);
    }
	
	
	@Override
	public void drawScreen(int x, int y, float partialTicks)
	{		
		
		super.drawScreen(x, y, partialTicks);

		boolean playerIsCreative = mc.player.capabilities.allowEdit;
		
		if(getChild(0).IsVisible())
		{
			return;
		}
		
		for(GraphicObject component : contentRect.visibleChilds)
		{
			if(component.isHover(x, y))
			{
				CompanionSlot slot = (CompanionSlot) component;
					
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
	
	@Override
    protected void mouseClicked(int x, int y, int mouseBtn)
    {
    	if(this.getChild(0).IsVisible())
    	{
    		if(dialogBoxActive())
    		{
    			super.mouseClicked(x, y, mouseBtn);
    			return;
    		}
    		this.getChild(0).mouseClicked(x, y, mouseBtn);
    	}
    	else
    	{
    		super.mouseClicked(x, y, mouseBtn);
    	}
    }
	
	
	
	public void updateScrollviewContents()
	{
		slots.clear();
		
		List<CosmeticObject> cosmetics = new ArrayList<CosmeticObject> ();
		
		cosmetics = getCompanions();
				
		if(!searchedTxt.isEmpty())
		{
			cosmetics = applyCompanionsByKeyword(cosmetics);
		}
				
		for(CosmeticObject co : cosmetics)
		{
				CompanionSlot slot = new CompanionSlot(co,Type.SQUARE,"",new ResourceLocation("erthilia","gui/cosmetique/slot.png"), new ResourceLocation("erthilia","gui/cosmetique/slot_hover.png"),false, new UIButton.CallBackObject()
				{
					@Override
					public void call()
					{
						selectedCompanion = co;

						if(co.getIsEquipped())
						{
							CosmeticObject.unequipCosmetic(player, co.getId());
						}
						else
						{
							if(!co.getIsLocked())
							{
								List<CosmeticObject> toUnEquip = CosmeticObject.getEquippedCosmeticFromSameType(player, co.getType());
								for(CosmeticObject obj : toUnEquip)
								{
									CosmeticObject.unequipCosmetic(player, obj.getId());
								}
									
								displaySelectEffectDialogBox();
							}
						}
					}				
				});
				slots.add(slot);
		}
		int needSlot = needSlotToFill();
		for(int i = 0; i < needSlot; i++)
		{
			CompanionSlot slot = new CompanionSlot(Type.SQUARE,"",new ResourceLocation("erthilia","gui/cosmetique/slot.png"), new ResourceLocation("erthilia","gui/cosmetique/slot_hover.png"),false, null);
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
	
	public void displaySelectEffectDialogBox() 
	{
		EffectSelectBox dialogBox = (EffectSelectBox) getChild(0);

		dialogBox.setVisible(true);
		dialogBox.initGui();
	}
	
	public void dispayDialogBox(byte type)
	{
		if(type == 0)
		{
			displayDialogBox("Votre compagnon à faim donnez \n 10 pyrites pour le nourrir", 
			new UIButton.CallBackObject()
			{
				public void call()
				{
					destroyDialogBox(0);
					Main.getPacketHandler().sendToServer(PacketCosmetic.feedCompanion(selectedCompanion.getId()));
				}
			}, 
			new UIButton.CallBackObject()
			{
				public void call()
				{
					destroyDialogBox(0);
				}
			});
		}
		else
		{
			displayDialogBox("Vous n'avez pas les items nécessaire\npour nourrir votre compagnon", 
					new UIButton.CallBackObject()
					{
						public void call()
						{
							destroyDialogBox(0);
						}
					}, 
					null);
		}
	}
	
	public List<CosmeticObject> getCompanions()
	{
		return pe.getCosmeticsDatas();
	}
	
	public List<CosmeticObject> applyCompanionsByKeyword(List<CosmeticObject> filter)
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
		if(contentRect.getChilds().size() == 0) 
	    {
			lastElementposX = object.localPosX += lastElementposX + spacing;
			lastElementposY = object.localPosY += 1;
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
	
	 @Override
	 public void updateScreen()
	 {
		 UITextField textField = (UITextField) this.getComponent(1);
		 if(this.searchedTxt != textField.getText())
		 {
			 this.searchedTxt = textField.getText();
			 this.updateScrollviewContents();
		 }
		 
		 super.updateScreen();
	
	 }
	 
	 @Override
	 public void onGuiClosed() 
	 {
		 pe.updateRenderer();
	 }
	 
	 class EffectSelectBox extends GuiScrollableView
	 {
	 	
	 	public PotionButton selectedEffect;
	 	 	
	 	
	 	@Override
	 	public void initGui() 
	 	{
	 		setWindowSize(190, 130);
	 		setWindowPosition((parent.width-190) / 2, (parent.height - 130)/2);
	 		super.initGui();
	 	}
	 	
	 	@Override
	 	public void drawScreen(int x, int y, float pt)
	 	{
	 		GL11.glPushMatrix();
	 		GL11.glTranslatef(0, 0, 500f);
	 		super.drawScreen(x, y, pt);
	 		GL11.glPopMatrix();
	 	}

	 	
	 	@Override
	 	public void initializeComponent() 
	 	{ 
	 		contentRect = new UIRect(new UIColor(0,0,0,0));
	 		viewport = new UIRect(new UIColor(255,255,255,0));
	 		
	 		
	 		scrollBarVertical = new UIScrollbarVertical(new ResourceLocation("erthilia","gui/cosmetique/bar.png"),new ResourceLocation("erthilia","gui/cosmetique/bar_hover.png"));
	 		selectedScrollBar = scrollBarVertical;
	 			
	 		alwaysDisplayScrollBars = true;

	 		setScrollViewPosition(getWindowPosX() + (getWindowWidth() - 145) / 2, getWindowPosY()+ 23, 140, 70);
	 		parameterVerticalScrollbar(getWindowPosX() + getWindowWidth() - 24,getWindowPosY()+ 24,6,68);

	 		
	 		addComponent(new UIRect(new UIColor(0,0,0,100)).setPosition(0, 0, parent.width, parent.height));
	 		addComponent(new UIImage(new ResourceLocation("erthilia","gui/dialog_box.png")).setPosition(getWindowPosX(), getWindowPosY(), getWindowWidth(), getWindowHeight()));		
	 		addComponent(new UIRect(new UIColor(0,0,0,60)).setPosition(getWindowPosX() + getWindowWidth() - 25, getWindowPosY()+ 23, 8, 70));
	 		addComponent(new UIText("Selectionnez l'effet",new UIColor(255,255,255),1f).setTextCenter(true).setPosition(getWindowPosX() + getWindowWidth() / 2, getWindowPosY() + 10));
	 		addComponent(new UIButton(Type.SQUARE,"Valider",new ResourceLocation("erthilia","gui/pausemenu/empty_button.png"),new ResourceLocation("erthilia","gui/pausemenu/empty_button_hover.png"), true, new UIButton.CallBackObject()
	 		{
	 			
	 			public void call()
	 			{
	 				
	 				if(selectedEffect == null) return;
	 				
	 				CompanionObject.equipCompanion(parent.mc.player, CosmeticManager.getCompanionEffects().indexOf(selectedEffect.information.getPotionEffect()), selectedCompanion.getId());
	 			}
	 			
	 		}).setPosition(getWindowPosX() + (getWindowWidth() - 70) / 2, getWindowPosY() + getWindowHeight() - 32,70,20));

	 		//addComponent(new UIImage(new ResourceLocation("erthilia","gui/companion/background.png")).setPosition(getWindowPosX(), getWindowPosY(), getWindowWidth(), getWindowHeight()));		
	 		updateScrollviewContents();
	 	}
	 	
		@Override
		public void onChildClose() 
		{
			selectedEffect = null;
			selectedCompanion = null;
		}
	 	
	 	public void updateScrollviewContents()
	 	{
	 		resetContentLayout();
	 		contentRect.childs.clear();
	 				
	 		for(PotionEffect potion : CosmeticManager.getCompanionEffects())
	 		{
	 			PotionButton potionButton = new PotionButton(potion,Type.SQUARE,"", new ResourceLocation("erthilia","gui/pausemenu/empty_button.png"), new ResourceLocation("erthilia","gui/pausemenu/empty_button_hover.png"), false, null);
	 			
	 			potionButton.callback = new UIButton.CallBackObject()
	 			{
	 				
	 				@Override
	 				public void call()
	 				{
	 					if(selectedEffect != null)
	 					{
	 						selectedEffect.setEnabled(true);
	 					}
	 					
	 					selectedEffect = potionButton;
	 					selectedEffect.setEnabled(false);
	 				}
	 				
	 			};
	 			
	 			addToContainer(potionButton.setPosition(0, 0, 80,18));
	 		}
	 		
	 	}
	 }

}

class CompanionSlot extends UIButton
{
	private CosmeticObject attribuatedCompanion;
	
	private final static ResourceLocation lockedTexture = new ResourceLocation("erthilia","gui/cosmetique/locked.png");
	private final static ResourceLocation selectedTexture = new ResourceLocation("erthilia","gui/cosmetique/selected.png");
	
	
	private UIImage lockedImage = new UIImage(lockedTexture);
	private UIImage selectedImage = new UIImage(selectedTexture);

	//Empty Slot
	public CompanionSlot(Type type, String text, ResourceLocation resource,ResourceLocation hoverTexture, boolean displayText, CallBackObject callback) 
	{
		super(type, text, resource,hoverTexture, displayText, callback);
		selectedImage.color = new UIColor("#5ee609");
	}
	
	public CompanionSlot(CosmeticObject co,Type type, String text, ResourceLocation resource,ResourceLocation hoverTexture, boolean displayText, CallBackObject callback) 
	{
		super(type, text, resource,hoverTexture, displayText, callback);
		selectedImage.color = new UIColor("#5ee609");
		this.attribuatedCompanion = co;
	}
	
	public CompanionSlot(CosmeticObject co,Type type , UIRect rect, String text, UIRect hoverRect, CallBackObject callback) 
	{
		super(type, rect, text, hoverRect, callback);
		lockedImage.color = new UIColor(255,215,0,255);
		selectedImage.color = new UIColor("#5ee609");
		this.attribuatedCompanion = co;
	}
	
	public CosmeticObject getCosmeticObj()
	{
		return attribuatedCompanion;
	}
	
	public void setCosmeticObj(CosmeticObject co)
	{
		attribuatedCompanion = co;
	}
	
	public boolean isLocked()
	{
		return attribuatedCompanion.getIsLocked();
	}
	
	public boolean isEquipped()
	{
		return attribuatedCompanion.getIsEquipped();
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
		if(attribuatedCompanion != null)
		{
			GL11.glPushMatrix();
			GL11.glColor4f(1f, 1f, 1f, 1f);
			
			attribuatedCompanion.renderModelInGui(posX, posY, 0);
			
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
				
				
				if(isLocked())
				{
					GL11.glTranslatef(0, 0, 400);
					lockedImage.draw(x, y, partialTicks);
				}
			}
			GL11.glPopMatrix();
		}

	}
	
	

}


	

