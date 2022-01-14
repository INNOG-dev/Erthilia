package fr.karmaowner.erthilia.gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import fr.karmaowner.erthilia.ClientProxy;
import fr.karmaowner.erthilia.Main;
import fr.karmaowner.erthilia.armoreffect.ArmorEffectContainer;
import fr.karmaowner.erthilia.capability.playercapability.ErthiliaPlayerProvider;
import fr.karmaowner.erthilia.data.GuiOverlaySettings;
import fr.karmaowner.erthilia.guicomponent.GraphicObject;
import fr.karmaowner.erthilia.guicomponent.UIButton;
import fr.karmaowner.erthilia.guicomponent.UIButton.CallBackObject;
import fr.karmaowner.erthilia.guicomponent.UIButton.Type;
import fr.karmaowner.erthilia.guicomponent.UIColor;
import fr.karmaowner.erthilia.guicomponent.UIRect;
import fr.karmaowner.erthilia.handler.BossHudHandler;
import fr.karmaowner.erthilia.handler.PotionHudHandler;
import fr.karmaowner.erthilia.handler.ToggleSprintHandler;
import fr.karmaowner.erthilia.keystrokes.KeyContainer;
import fr.karmaowner.erthilia.utils.GuiUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class EditionUI extends GuiScreen {
	
	private boolean inEdition = false;
	
	private UIRect background;
	
	private UIButton resetBtn;
	
	private ToggleSprintHandler togglesprintHandler = ClientProxy.getGuiHandler().toggleSprintContainer;
	private ArmorEffectContainer armorEffectContainer = ClientProxy.getGuiHandler().armorEffectContainer;
	private KeyContainer keystrokesHandler = ClientProxy.getGuiHandler().keystrokesContainer;
	private PotionHudHandler potionHudHandler = ClientProxy.getGuiHandler().potionHudContainer;
	private BossHudHandler bossHudHandler = ClientProxy.getGuiHandler().bossHudContainer;

	
	private SettingsWindow toggleSprintPanel;
	private SettingsWindow armorEffectPanel;
	private SettingsWindow keystrokesPanel;
	private SettingsWindow potionHudPanel;
	private SettingsWindow bossHudPanel;

	
	private GraphicObject clickedObject;

	private int clickedX;
	private int clickedY;
	
	private List<UIButton> buttons = new ArrayList<UIButton>();
	
	
	private GuiOverlaySettings settings;
	
	
	@Override
	public void initGui()
	{
		buttons.clear();
		super.initGui();
		
		mc.gameSettings.showDebugInfo = false;
		
		background = new UIRect(new UIColor(0,0,0,100));

		
		if(inEdition)
		{
			settings = new GuiOverlaySettings(width, height);
			
			toggleSprintPanel = SettingsWindow.BuildPanel(200, 100, 10, 0);
			armorEffectPanel = SettingsWindow.BuildPanel(200, 125, 10 , 1);
			keystrokesPanel = SettingsWindow.BuildPanel(200, 200, 10 , 2);
			potionHudPanel = SettingsWindow.BuildPanel(200, 100, 10, 3);
			bossHudPanel = SettingsWindow.BuildPanel(200, 100, 10, 4);

			
			toggleSprintPanel.initializeComponent();
			armorEffectPanel.initializeComponent();
			keystrokesPanel.initializeComponent();
			potionHudPanel.initializeComponent();
			bossHudPanel.initializeComponent();
			
			
			resetBtn = new UIButton("Reset", new UIColor(255,255,255),new UIColor(200,200,200),1f,null);
			
			togglesprintHandler.setPosition(settings.toggleSprintPosX, settings.toggleSprintPosY, settings.toggleSprintWidth, settings.toggleSprintHeight);
			armorEffectContainer.setPosition(settings.armorEffectPosX, settings.armorEffectPosY, settings.armorEffectWidth, settings.armorEffectHeight);
			keystrokesHandler.setPosition(settings.keystrokesPosX, settings.keystrokesPosY, settings.keystrokesWidth, settings.keystrokesHeight);
			potionHudHandler.setPosition(settings.potionHudPosX, settings.potionHudPosY, settings.potionHudWidth, settings.potionHudHeight);
			bossHudHandler.setPosition(settings.bossHudPosX, settings.bossHudPosY, settings.bossHudWidth, settings.bossHudHeight);

			togglesprintHandler.setScale(settings.toggleSprintScale);
			armorEffectContainer.setScale(settings.armorEffectScale);
			keystrokesHandler.setScale(settings.keystrokesScale);
			potionHudHandler.setScale(settings.potionHudScale);		
			bossHudHandler.setScale(settings.bossHudScale);
		}
		else
		{
			buttons.add(new UIButton(Type.SQUARE,"ToggleSprint", new ResourceLocation("erthilia","gui/pausemenu/empty_button.png"),new ResourceLocation("erthilia","gui/pausemenu/empty_button_hover.png"),true,new CallBackObject()
			{
				public void call()
				{
					ClientProxy.getToggleSprintHandler().toggleSprint = !ClientProxy.getToggleSprintHandler().toggleSprint;
				}
			}));
			
			buttons.add(new UIButton(Type.SQUARE,"Keystrokes", new ResourceLocation("erthilia","gui/pausemenu/empty_button.png"),new ResourceLocation("erthilia","gui/pausemenu/empty_button_hover.png"),true,new CallBackObject()
			{
				public void call()
				{
					ClientProxy.getKeyStrokesHandler().config.setEnabled(!ClientProxy.getKeyStrokesHandler().config.getIsEnabled());
				}
			}));
			
			buttons.add(new UIButton(Type.SQUARE,"ArmureHUD", new ResourceLocation("erthilia","gui/pausemenu/empty_button.png"),new ResourceLocation("erthilia","gui/pausemenu/empty_button_hover.png"),true,new CallBackObject()
			{
				public void call()
				{
					armorEffectContainer.config.setEnabled(!armorEffectContainer.config.getIsEnabled());
				}
			}));
			
			buttons.add(new UIButton(Type.SQUARE,"PotionHUD", new ResourceLocation("erthilia","gui/pausemenu/empty_button.png"),new ResourceLocation("erthilia","gui/pausemenu/empty_button_hover.png"),true,new CallBackObject()
			{
				public void call()
				{
					potionHudHandler.config.setEnabled(!potionHudHandler.config.getIsEnabled());
				}
			}));
			
			
			buttons.add(new UIButton(Type.SQUARE,"Radio", new ResourceLocation("erthilia","gui/pausemenu/empty_button.png"),new ResourceLocation("erthilia","gui/pausemenu/empty_button_hover.png"),true,new CallBackObject()
			{
				public void call()
				{
					Minecraft.getMinecraft().displayGuiScreen(new RadioUI());
				}
			}));
			
			buttons.add(new UIButton(Type.SQUARE,"Cosmétique", new ResourceLocation("erthilia","gui/pausemenu/empty_button.png"),new ResourceLocation("erthilia","gui/pausemenu/empty_button_hover.png"),true,new CallBackObject()
			{
				public void call()
				{
					Minecraft.getMinecraft().displayGuiScreen(new CosmetiqueUI(mc.player.getCapability(ErthiliaPlayerProvider.PLAYER_CAP, null)));
				}
			}));
			
			buttons.add(new UIButton(Type.SQUARE,"Compagnon", new ResourceLocation("erthilia","gui/pausemenu/empty_button.png"),new ResourceLocation("erthilia","gui/pausemenu/empty_button_hover.png"),true,new CallBackObject()
			{
				public void call()
				{
					
				}
			}));
			
			buttons.add(new UIButton(Type.SQUARE,"§bDéplacer les éléments", new ResourceLocation("erthilia","gui/pausemenu/empty_button.png"),new ResourceLocation("erthilia","gui/pausemenu/empty_button_hover.png"),true,new CallBackObject()
			{
				public void call()
				{
					inEdition = true;
					initGui();
				}
			}));
			
			buttons.add(new UIButton(Type.SQUARE,"§bFermer", new ResourceLocation("erthilia","gui/pausemenu/empty_button.png"),new ResourceLocation("erthilia","gui/pausemenu/empty_button_hover.png"),true,new CallBackObject()
			{
				public void call()
				{
					Minecraft.getMinecraft().displayGuiScreen(null);
				}
			}));
			
    		int posX = (width - 195*3) / 2;
    		int posY = (height - 200) / 2;
    		int j = 0;
    		int k = 0;
    		for(int i = 0; i < buttons.size(); i++)
    		{
    			buttons.get(i).setPosition(posX + (k+1) * 120, posY + (j+1) * 40, 100, 25);
    			k++;
    			if(k == 3)
    			{
    				j++;
    				k = 0;
    			}
    		}
    		return;
		}
	}
	
    public void drawScreen(int x, int y, float partialTicks)
    {	
    	drawBackground();
    	
    	if(!inEdition)
    	{
    		for(int i = 0; i < buttons.size(); i++)
    		{
    			buttons.get(i).draw(x, y, partialTicks);
    		}
    		return;
    	}
    	
    	GuiUtils.renderCenteredText("Edition", this.width / 2, 10, 2f);
    	
    	GlStateManager.resetColor();
    	this.togglesprintHandler.drawEditor(x, y, partialTicks);
    	GlStateManager.resetColor();
    	this.armorEffectContainer.drawEditor(x, y, partialTicks);
    	GlStateManager.resetColor();
    	this.keystrokesHandler.drawEditor(x, y, partialTicks);
    	GlStateManager.resetColor();
    	this.potionHudHandler.drawEditor(x, y, partialTicks);
    	GlStateManager.resetColor();
    	this.bossHudHandler.drawEditor(x, y, partialTicks);
    	GlStateManager.resetColor();
    	
		toggleSprintPanel.drawPanel(x, y, partialTicks);
		armorEffectPanel.drawPanel(x, y, partialTicks);
		keystrokesPanel.drawPanel(x, y, partialTicks);
		potionHudPanel.drawPanel(x, y, partialTicks);
		bossHudPanel.drawPanel(x, y, partialTicks);
    	
  
    	if(clickedObject != null)
    	{	
    		GL11.glPushMatrix();
    		GL11.glScalef(clickedObject.getScale(), clickedObject.getScale(), clickedObject.getScale());
    		int motionX = x-clickedX;
    		int motionY = y-clickedY;
    		
    		x /= clickedObject.getScale();
    		y /= clickedObject.getScale();
    		
    		clickedObject.setPosition(x - clickedObject.getWidth(), y, clickedObject.getWidth() ,clickedObject.getHeight());
    		if(clickedObject.getX() <= 0 && motionX < 0)
    		{
    			clickedObject.setPosition(0, clickedObject.getY(), clickedObject.getWidth(), clickedObject.getHeight());
    		}
    		if(clickedObject.getY2() >= this.height / clickedObject.getScale() && motionY > 0)
    		{
    			clickedObject.setPosition(clickedObject.getX(), (int) (this.height / clickedObject.getScale()-clickedObject.getHeight()), clickedObject.getWidth(), clickedObject.getHeight());
    		}
    		
    		GL11.glPopMatrix();
    	}
    	
    	resetBtn.setPosition(this.width - 30, this.height - 10);
    	resetBtn.draw(x, y, partialTicks);
    }
    
    @Override
    protected void mouseClicked(int x, int y, int mouseBtn)
    {
    	if(mouseBtn == 0)
    	{

    		if(!inEdition)
    		{
    			for(UIButton button : buttons)
    			{
    				if(button.isClicked(x, y))
    				{
    					button.callback.call(); 
    					return;
    				}
    			}
    			return;
    		}
    		
    		if(toggleSprintPanel.panelActive)
    		{
    			toggleSprintPanel.mouseClicked(x, y, mouseBtn);
    		}
    		else if(armorEffectPanel.panelActive)
    		{
    			armorEffectPanel.mouseClicked(x, y, mouseBtn);
    		}
    		else if(potionHudPanel.panelActive)
    		{
    			potionHudPanel.mouseClicked(x, y, mouseBtn);
    		}
    		else if(keystrokesPanel.panelActive)
    		{
    			keystrokesPanel.mouseClicked(x, y, mouseBtn);
    		}
    		else if(bossHudPanel.panelActive)
    		{
    			bossHudPanel.mouseClicked(x, y, mouseBtn);
    		}
    		
    		if(this.potionHudHandler.moveBtn.isClicked(x, y))
    		{
    			clickedObject = potionHudHandler;
    		}
    		else if(this.potionHudHandler.editBtn.isClicked(x, y))
    		{
    			this.closeAllWindows();
    			potionHudPanel.panelActive = true;
    		}
    		else if(this.armorEffectContainer.moveBtn.isClicked(x, y))
    		{
    			clickedObject = armorEffectContainer;
    		}
    		else if(this.armorEffectContainer.editBtn.isClicked(x, y))
    		{
    			this.closeAllWindows();
    			armorEffectPanel.panelActive = true;
    		}
    		else if(this.togglesprintHandler.moveBtn.isClicked(x, y))
    		{
    			clickedObject = togglesprintHandler;
    		}
    		else if(this.togglesprintHandler.editBtn.isClicked(x, y))
    		{
    			this.closeAllWindows();
    			toggleSprintPanel.panelActive = true;
    		}
    		else if(this.keystrokesHandler.moveBtn.isClicked(x, y))
    		{
    			clickedObject = keystrokesHandler;
    		}
    		else if(this.keystrokesHandler.editBtn.isClicked(x, y))
    		{
    			this.closeAllWindows();
    			keystrokesPanel.panelActive = true;
    		}
    		else if(this.bossHudHandler.moveBtn.isClicked(x, y))
    		{
    			clickedObject = bossHudHandler;
    		}
    		else if(this.bossHudHandler.editBtn.isClicked(x, y))
    		{
    			this.closeAllWindows();
    			bossHudPanel.panelActive = true;
    		}
    		
    		if(clickedObject != null)
    		{
	    		clickedX = x;
	    		clickedY = y;
	    		return;
    		}
    		
    		if(resetBtn.isClicked(x, y))
    		{
    			settings.resetData();
    			ClientProxy.getGuiHandler().refreshOverlay();
    			return;
    		}
    	}
    }
    

    
    @Override
    protected void keyTyped(char character, int keycode) throws IOException
    {
       super.keyTyped(character, keycode);

       if(!inEdition) return;
    	
        
 	   if(keystrokesPanel.panelActive)
 	   {
 		   keystrokesPanel.textboxKeyTyped(character, keycode);
 	   }
        
       if(this.clickedObject != null)
       {
    	
            if(keycode == 200)
            {
            	this.clickedObject.setScale((this.clickedObject.getScale()+0.1f));
            }
            else if(keycode == 208)
            {
            	this.clickedObject.setScale((this.clickedObject.getScale()-0.1f));
            }
       }
    }
    
    @Override
    public void handleMouseInput() throws IOException
    {
    	super.handleMouseInput();

    	if(!inEdition) return;

    	int i = Integer.signum(Mouse.getEventDWheel());
        if(this.clickedObject != null)
        {
     	
             if(i > 0)
             {
             	this.clickedObject.setScale((this.clickedObject.getScale()+0.01f));
             }
             else if(i < 0)
             {
             	this.clickedObject.setScale((this.clickedObject.getScale()-0.01f));
             }
        }
    	i = 0;
    }
    
    @Override
    public void updateScreen()
    {
       if(!inEdition)
       {
    	   buttons.get(0).setDisplayText("ToggleSprint " + getStateText(togglesprintHandler.toggleSprint));
    	   buttons.get(1).setDisplayText("Keystrokes " + getStateText(ClientProxy.getKeyStrokesHandler().config.getIsEnabled()));
    	   buttons.get(2).setDisplayText("ArmorHUD " + getStateText(armorEffectContainer.config.getIsEnabled()));
    	   buttons.get(3).setDisplayText("PotionHUD " + getStateText(potionHudHandler.config.getIsEnabled()));

    	   return;
       }

	   if(keystrokesPanel.panelActive)
	   {
		   keystrokesPanel.updateCursorCounter();
		   keystrokesPanel.update();
	   }
	   else if(toggleSprintPanel.panelActive)
	   {
		   toggleSprintPanel.update();
	   }
	   else if(potionHudPanel.panelActive)
	   {
		   potionHudPanel.update();
	   }
	   else if(armorEffectPanel.panelActive)
	   {
		   armorEffectPanel.update();
	   }
    }
    
    public String getStateText(boolean state)
    {
    	return state == true ? "§aON" : "§cOFF"; 
    }
    
    
    @Override
    protected void mouseReleased(int x, int y, int state)
    {
    	
    	if(!inEdition) return;

    	
        if(state == 0)
        {
        	if(this.clickedObject != null)
        	{

        		clickedObject = null;
    	        clickedX = -1;
    	        clickedY = -1; 
        	}
        	
        	if(keystrokesPanel.panelActive)
    		{
    			keystrokesPanel.mouseReleased(x, y);
    		}
    		else if(toggleSprintPanel.panelActive)
    		{
    			toggleSprintPanel.mouseReleased(x, y);
    		}
    		else if(potionHudPanel.panelActive)
    		{
    			potionHudPanel.mouseReleased(x, y);
    		}
    		else if(armorEffectPanel.panelActive)
    		{
    			armorEffectPanel.mouseReleased(x, y);
    		}
        	
        }
    }

    public void drawBackground()
    {
    	background.setPosition(0, 0, width, height);
    }

    @Override
    public void onGuiClosed() 
    {
    	if(!inEdition)
    	{
        	ClientProxy.getGuiHandler().refreshOverlay();
        	Main.modConfig.saveConfiguration();
    		return;
    	}
    	
    	settings.armorEffectPosX = this.armorEffectContainer.getX();
    	settings.armorEffectPosY = this.armorEffectContainer.getY();
    	settings.armorEffectWidth = this.armorEffectContainer.getWidth();
    	settings.armorEffectHeight = this.armorEffectContainer.getHeight();
    	settings.armorEffectScale = this.armorEffectContainer.getScale();
    	
    	settings.toggleSprintPosX = this.togglesprintHandler.getX();
    	settings.toggleSprintPosY = this.togglesprintHandler.getY();
    	settings.toggleSprintWidth = this.togglesprintHandler.getWidth();
    	settings.toggleSprintHeight = this.togglesprintHandler.getHeight();
    	settings.toggleSprintScale = this.togglesprintHandler.getScale();
    	
    	settings.potionHudPosX = this.potionHudHandler.getX();
    	settings.potionHudPosY = this.potionHudHandler.getY();
    	settings.potionHudWidth = this.potionHudHandler.getWidth();
    	settings.potionHudHeight = this.potionHudHandler.getHeight();
    	settings.potionHudScale = this.potionHudHandler.getScale();
    	
    	settings.keystrokesPosX = this.keystrokesHandler.getX();
    	settings.keystrokesPosY = this.keystrokesHandler.getY();
    	settings.keystrokesWidth = this.keystrokesHandler.getWidth();
    	settings.keystrokesHeight = this.keystrokesHandler.getHeight();
    	settings.keystrokesScale = this.keystrokesHandler.getScale();
    	
    	settings.bossHudPosX = this.bossHudHandler.getX();
    	settings.bossHudPosY = this.bossHudHandler.getY();
    	settings.bossHudWidth = this.bossHudHandler.getWidth();
    	settings.bossHudHeight = this.bossHudHandler.getHeight();
    	settings.bossHudScale = this.bossHudHandler.getScale();

    	settings.saveData();
    	Main.modConfig.saveConfiguration();
    	
    	ClientProxy.getGuiHandler().refreshOverlay();
    }
    
    private void closeAllWindows()
    {
    	this.toggleSprintPanel.panelActive = false;
    	this.armorEffectPanel.panelActive = false;
    	this.keystrokesPanel.panelActive = false;
    	this.potionHudPanel.panelActive = false;
    	this.bossHudPanel.panelActive = false;
    }
    
    
}
