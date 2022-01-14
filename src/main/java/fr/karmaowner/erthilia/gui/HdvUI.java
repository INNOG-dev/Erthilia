package fr.karmaowner.erthilia.gui;

import java.util.List;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.mojang.realmsclient.gui.ChatFormatting;

import fr.karmaowner.erthilia.Main;
import fr.karmaowner.erthilia.guicomponent.GraphicObject;
import fr.karmaowner.erthilia.guicomponent.UIButton;
import fr.karmaowner.erthilia.guicomponent.UIButton.Type;
import fr.karmaowner.erthilia.guicomponent.UIColor;
import fr.karmaowner.erthilia.guicomponent.UIImage;
import fr.karmaowner.erthilia.guicomponent.UIRect;
import fr.karmaowner.erthilia.guicomponent.UIText;
import fr.karmaowner.erthilia.guicomponent.UITextField;
import fr.karmaowner.erthilia.hdv.HdvItem;
import fr.karmaowner.erthilia.hdv.HdvManager;
import fr.karmaowner.erthilia.network.PacketHdv;
import fr.karmaowner.erthilia.utils.GuiUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class HdvUI extends GuiGridView {

	private enum GuiState
	{
		SEARCH,
		MYITEMS,
		ITEMS;
	}
	
	GuiState guiState = GuiState.ITEMS;
	
	public int currentPage;
	
	public int maxPage;
	
	private final int elementWidth = 80;
	
	private final int elementHeight = 30;
	
	private final int spacingX = 3;
	
	private final int spacingY = 7;
	
	private String previousSearchKey = "";

	private HdvRenderItem hoverHdvItem;
	

	public HdvUI()
	{
		currentPage = 1;
		elementSize = 80;
		spacing = 1;
	}
	
	@Override
	public void initGui() 
	{
		setWindowSize(320, 200);
		setWindowPosition((width-320) / 2, (height-200) / 2);
		
		syncElements();
		
		super.initGui();
		
		
	}
	
	@Override
	public void drawScreen(int x, int y, float partialTicks)
	{
		super.drawScreen(x, y, partialTicks);
		
		if(hoverHdvItem != null)
		{
			drawHoveringText(hoverHdvItem.constructDisplay(hoverHdvItem.hdvItem.getItemstack()),x, y ,mc.fontRenderer);
		}
		hoverHdvItem = null;

	}
	
	public void initializeComponent() 
	{ 
		addComponent(new UIImage(new ResourceLocation("erthilia","gui/hdv/background.png")).setPosition(getWindowPosX(), getWindowPosY(), getWindowWidth(), getWindowHeight()));

		UIButton leftArrow = (UIButton) addComponent(new UIButton(Type.SQUARE,"<",new ResourceLocation("erthilia","gui/hdv/left_btn.png"),null,false, new UIButton.CallBackObject()
		{
			@Override
			public void call()
			{
				if(currentPage - 1 < 1)
				{
					currentPage = 1;
				}
				else
				{
					currentPage--;
				}
				
				if(!previousSearchKey.isEmpty())
				{
					syncElements(previousSearchKey);
				}
				else
				{
					syncElements(previousSearchKey);
				}
			}
		}).setTextColor(new UIColor(0,0,0)).setPosition(getWindowPosX() + 15, getWindowPosY()+getWindowHeight()-32, 15, 15));
	
		UIText pageTxt = (UIText) addComponent(new UIText("1/1",new UIColor(255,255,255), 1f).setPosition(leftArrow.getX2() + 5, (leftArrow.getY() + leftArrow.getHeight() / 2) - 3));
		
		UIButton rightArrow = (UIButton)addComponent(new UIButton(Type.SQUARE,">",new ResourceLocation("erthilia","gui/hdv/right_btn.png"),null,false, new UIButton.CallBackObject()
		{
			@Override
			public void call()
			{
				if(currentPage + 1 > maxPage)
				{
					currentPage = maxPage;
				}
				else
				{
					currentPage++;
				}
				
				if(!previousSearchKey.isEmpty())
				{
					syncElements(previousSearchKey);
				}
				else
				{
					syncElements(previousSearchKey);
				}
			}
		}).setTextColor(new UIColor(0,0,0)).setPosition(pageTxt.getX() + pageTxt.getTextWidth() + 5, leftArrow.getY(), 15, 15));
	
		UITextField searchBar = (UITextField)addComponent(new UITextField(new UIImage(new ResourceLocation("erthilia","gui/hdv/search_input.png")),0.9f,UITextField.Type.TEXT).setPosition(rightArrow.getX2() + 10, leftArrow.getY()-2, 100, 18));
		searchBar.setInputOffsetX(10);
		searchBar.setInputInsetX(18);
		
		UIButton mySellBtn = (UIButton) addComponent(new UIButton(Type.SQUARE,"Mes ventes",new ResourceLocation("erthilia","gui/hdv/sells.png"),null, true,null).setPosition(searchBar.getX2() + 10, rightArrow.getY(), 70, 15).setHitboxModificator(8, 1, -8, -2));
		mySellBtn.callback = new UIButton.CallBackObject()
		{
			@Override
			public void call()
			{
				if(guiState == GuiState.ITEMS)
				{
					guiState = GuiState.MYITEMS;
					mySellBtn.setDisplayText("Retour");
				}
				else
				{
					guiState = GuiState.ITEMS;
					mySellBtn.setDisplayText("Mes ventes");	
				}
				syncElements();
			}
		};
		
		contentRect = new UIRect(new UIColor(0,0,0,0));
		viewport = new UIRect(new UIColor(0,0,0,0));
		
		setScrollViewPosition(getWindowPosX() + (getWindowWidth() - 250) / 2, getWindowPosY() + 5 + (getWindowHeight() - 125) / 2, 250, 125);
	}
	
	
	private void syncElements()
	{
		if(guiState == GuiState.ITEMS)
		{
			Main.getPacketHandler().sendToServer(PacketHdv.getItemsForPage(currentPage));
		}
		else if(guiState == GuiState.MYITEMS)
		{
			Main.getPacketHandler().sendToServer(PacketHdv.getItemsForPageOfPlayer(mc.player, currentPage));
		}
	}
	
	private void syncElements(String keyWord)
	{
		if(guiState == GuiState.MYITEMS)
		{
			Main.getPacketHandler().sendToServer(PacketHdv.getItemsForPageByPlayerNameAndKeyword(mc.player, keyWord, currentPage));
		}
		else
		{
			Main.getPacketHandler().sendToServer(PacketHdv.getItemsForPageByKeyWord(keyWord, currentPage));
		}
	}
	
	@Override
	protected void positionElement(GraphicObject object)
	{
		 if(contentRect.getChilds().size() == 0) 
		 {
			 lastElementposX = object.localPosX += lastElementposX + 3;
			 lastElementposY = object.localPosY += spacingY;
			 contentRect.setHeight(contentRect.getHeight() + elementHeight + spacingY);
		 }
		 else
		 {
			 lastElementposX = object.localPosX += lastElementposX + spacingX + elementWidth;
			 object.localPosY = lastElementposY;
		 }
		 
		 if(lastElementposX + object.getWidth() > viewport.getWidth())
		 {
			 lastElementposY = object.localPosY += spacingY + elementHeight;
			 object.localPosX = lastElementposX = 3;
			 contentRect.setHeight(contentRect.getHeight() + elementHeight + spacingY);
		 }		 
	}
	
	public void setPageText(String text)
	{
		UIText pageTxt = (UIText) getComponent(2);
		pageTxt.setText(text);
	}
	
	@Override
	public void updateScrollviewContents()
	{
		contentRect.childs.clear();
		resetContainerLayout();		
	}
	
	private void buyItem(HdvRenderItem item)
	{
		Main.getPacketHandler().sendToServer(PacketHdv.selectHdvItem(item.hdvItem));
	}
	
	private void removeItem(HdvRenderItem item)
	{
		Main.getPacketHandler().sendToServer(PacketHdv.removeItemFromHdv(item.hdvItem));
	}
	
	@Override
	public void updateScreen()
	{
		setPageText(currentPage + "/" + maxPage);
		super.updateScreen();
		
		if(getGuiTicks() % 20 == 0)
		{
			UITextField searchBar = (UITextField) getComponent(4);
			if(!this.previousSearchKey.equalsIgnoreCase(searchBar.getText()))
			{
				previousSearchKey = searchBar.getText();
				if(previousSearchKey.isEmpty())
				{
					syncElements();
				}
				else
				{
					syncElements(previousSearchKey);
				}
			}
		}
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
        else
        {
        	super.keyTyped(p_73869_1_, p_73869_2_);
        }
    }
	
	public void updateScrollviewContents(List<HdvItem> items)
	{
		updateScrollviewContents();
		
		for(HdvItem item : items)
		{
			HdvRenderItem hdvRenderItem = new HdvRenderItem(null,item);
			hdvRenderItem.callback = new UIButton.CallBackObject()
			{
				@Override
				public void call()
				{
					if(guiState == GuiState.MYITEMS)
					{
						removeItem(hdvRenderItem);
					}
					else
					{
						buyItem(hdvRenderItem);
						Minecraft.getMinecraft().displayGuiScreen(null);
					}
				}
			};
			addToContainer(hdvRenderItem.setPosition(0, 0,elementWidth,elementHeight));
		}
	}
 
	
	public class HdvRenderItem extends UIButton
	{

		public HdvItem hdvItem;
		
		public UIText sellPriceTxt;
		public UIText quantityTxt;

				
		public HdvRenderItem(CallBackObject callBackObject, HdvItem item) 
		{
			super(Type.SQUARE,"", new ResourceLocation("erthilia","gui/pausemenu/empty_button.png"),new ResourceLocation("erthilia","gui/pausemenu/empty_button_hover.png"),false, callBackObject);
			hdvItem = item;

			sellPriceTxt = new UIText(getDisplayMoney(),new UIColor(255,255,255),0.9f);
			quantityTxt = new UIText("" + item.getItemstack().getCount(),new UIColor(255,255,255),0.8f);
		}
		
		@Override
		public GraphicObject setPosition(int x, int y, int width, int height)
		{
			super.setPosition(x, y, width, height);
			sellPriceTxt.setPosition(x+30, y + 11);
			quantityTxt.setPosition(x+12, y+16);
			return this;
		}
		
		@Override
		public void draw(int x, int y, float partialTicks)
		{
			super.draw(x, y, partialTicks);

	        GL11.glEnable(GL11.GL_LIGHTING);
	        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
	        GuiUtils.renderItemStackIntoGUI(hdvItem.getItemstack(), posX + 5, posY + (height - 16) / 2, "", this.getRenderItem());
	        GL11.glDisable(GL11.GL_LIGHTING);
	        
			sellPriceTxt.draw(x, y, partialTicks);

			
			GL11.glPushMatrix();
			GL11.glTranslatef(0, 0, 300f);
			if(hdvItem.getItemstack().getCount() > 1) quantityTxt.draw(x, y, partialTicks);
			GL11.glPopMatrix();
			
			if(isHover(x, y))
			{
				hoverHdvItem = this;
			}
			
		}
		

	    private List<String> constructDisplay(ItemStack is)
	    {
	        List<String> list = is.getTooltip(this.getMinecraft().player, this.getMinecraft().gameSettings.advancedItemTooltips ? ITooltipFlag.TooltipFlags.ADVANCED : ITooltipFlag.TooltipFlags.NORMAL);


	        for (int k = 0; k < list.size(); ++k)
	        {
	            if (k == 0)
	            {
	                list.set(k, is.getItem().getForgeRarity(is).getColor() + (String)list.get(k));
	            }
	            else
	            {
	                list.set(k, ChatFormatting.GRAY + (String)list.get(k));
	            }
	        }
	       
	        if(list.size() > 1)
	        {
		        list.add("");
		        list.add("--------------");
		        list.add("");
	        }
	        
	        list.add("§ax" + hoverHdvItem.hdvItem.getItemstack().getCount());
	        list.add("§c"+hoverHdvItem.getDisplayMoney());
	        list.add(hoverHdvItem.hdvItem.getOwnerName());
	        list.add("");
	        list.add("--------------");
	        list.add("");
	        list.add(hoverHdvItem.getLeftTimeDisplay());
	        
	        if(guiState == GuiState.MYITEMS)
	        {
		        list.add("");
		        list.add("--------------");
		        list.add("");
		        
	        	if(isExpired())
	        	{
	        		list.add("§4Expiré");
			        list.add("§3Clique pour récupérer");
	        	}
	        	else
	        	{
			        list.add("§3Clique pour retirer des ventes");
	        	}
	        }
	        else
	        {
	        	if(isExpired())
	        	{
	        		list.add("§4Expiré");
	        	}
	        }
	        
	        return list;
	    }
	    
	    public long getLeftTime()
	    {
	    	return HdvManager.itemMaxTimeInHdv * 1000 - (System.currentTimeMillis() - hdvItem.getTimeSinceStartSell()); 
	    }
		
		public String getLeftTimeDisplay()
		{
			long leftTime = getLeftTime();
			
			if(leftTime < 0)
			{
				leftTime = 0;
			}
			
			int hours = (int) (leftTime / 1000 / 60 / 60); 
			int minutes = (int) (leftTime / 1000 / 60) % 60;
			int seconds = (int)(leftTime / 1000 % 60);


			String hoursStr = hours + "";
			if(hours <= 9)
			{
				hoursStr = "0" + hours;
			}
			
			String minutesStr = minutes + "";

			if(minutes <= 9)
			{
				minutesStr = "0" + minutes;
			}

			String secondsStr = seconds + "";

			if(seconds <= 9)
			{
				secondsStr = "0" + seconds;
			}
			
			return hoursStr + ":" + minutesStr + ":" + secondsStr;

		}
		
		public String getDisplayMoney()
		{
			if(hdvItem.getSellPrice() >= 1000000000)
			{
				float billion = hdvItem.getSellPrice() / 1000000000;
				return "" + billion + "Mi $";
			}
			if(hdvItem.getSellPrice() >= 1000000)
			{
				float million = hdvItem.getSellPrice() / 1000000;
				return "" + million + "M $";
			}
			if(hdvItem.getSellPrice() >= 1000)
			{
				float kEuro = hdvItem.getSellPrice() / 1000;
				return "" + kEuro + "K $";
			}
			else
			{
				return "" + hdvItem.getSellPrice() + " $";
			}
		}
		
		public boolean isExpired()
		{
			return getLeftTime() <= 0;
		}
		
		
	}
}
