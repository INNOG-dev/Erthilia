package fr.karmaowner.erthilia.gui;

import org.lwjgl.opengl.GL11;

import fr.karmaowner.erthilia.ClientProxy;
import fr.karmaowner.erthilia.Main;
import fr.karmaowner.erthilia.guicomponent.GraphicObject;
import fr.karmaowner.erthilia.guicomponent.UIButton;
import fr.karmaowner.erthilia.guicomponent.UIButton.Type;
import fr.karmaowner.erthilia.guicomponent.UIColor;
import fr.karmaowner.erthilia.guicomponent.UIRect;
import fr.karmaowner.erthilia.guicomponent.UISlider;
import fr.karmaowner.erthilia.utils.ColorUtils;
import fr.karmaowner.erthilia.utils.GuiUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;

public class SettingsWindow extends GraphicObject {

	private int zIndex;
	
	private UIButton closeBtn = new UIButton(Type.SQUARE,"closeBtn",new ResourceLocation(Main.MODID,"gui/close_ico.png"),null,false,null);
	
	private UIRect windowBackground = new UIRect(new UIColor(31,30,30,255));
	private UIRect tittleRect = new UIRect(new UIColor(22,23,23,255));
	
	
	private int id;
	
	public boolean panelActive;
	
	private SettingsObject[] components;
	
	private SettingsWindow()
	{
		
	}
	
	public static SettingsWindow BuildPanel(int width, int height, int zIndex, int id)
	{
		SettingsWindow window = new SettingsWindow();
		window.id = id;
		window.width = width;
		window.height = height;
		window.zIndex = zIndex;
		
		int screenWidth = Minecraft.getMinecraft().displayWidth;
		int screenHeight = Minecraft.getMinecraft().displayHeight;
		
		ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
		
		screenWidth = sr.getScaledWidth();
		screenHeight = sr.getScaledHeight();
				
		window.posX = (screenWidth - width) / 2;
		window.posY = (screenHeight - height) / 2;
		
		window.closeBtn.setPosition(window.posX+width-10, window.posY, 10, 10);

		window.windowBackground.setPosition(window.posX, window.posY, window.width, window.height);
		window.tittleRect.setPosition(window.posX, window.posY, window.width, 20);
		
		window.setScale(0.8f);
		
		return window;
	}
	
	public void closeWindow()
	{
		this.panelActive = false;
	}
	
	public void initializeComponent()
	{
		if(id == 0)
		{
			components = new SettingsObject[2];
			
			UIRect enableBtnRect = new UIRect(new UIColor(0,0,0,180));
			enableBtnRect.contourColor = new UIColor(255, 255, 255, 255);
			
			UIButton enabledBtn = new UIButton(Type.SQUARE, enableBtnRect, ""+ ClientProxy.getToggleSprintHandler().config.getIsEnabled(),null,null);
			enabledBtn.setTextColor(new UIColor(255,255,255,255));
			
			UISlider opacitySlider = new UISlider(1,100,ClientProxy.getToggleSprintHandler().config.getOpacity());
			
			enabledBtn.setPosition(posX + (width - 80) / 2, tittleRect.getY2() + 15, 80, 12);
			opacitySlider.setPosition(posX + (width - 99) / 2, enabledBtn.getY2() + 20, 99, 12);
			
			SettingsObject enabledSetting = new SettingsObject(enabledBtn,"Enabled");		
			SettingsObject opacitySetting = new SettingsObject(opacitySlider,"Opacity");

			components[0] = enabledSetting;
			components[1] = opacitySetting;
		}
		else if(id == 1)
		{
			components = new SettingsObject[3];

			UIButton enabledBtn = new UIButton(Type.SQUARE, new UIRect(new UIColor(0,0,0,180), new UIColor(255, 255, 255, 255)), ""+ClientProxy.armoreffectConfig.getIsEnabled(), null,null);
			enabledBtn.setTextColor(new UIColor(255,255,255,255));
			
			UISlider opacitySlider = new UISlider(1,100,ClientProxy.armoreffectConfig.getOpacity());
			
			UIButton themeBtn = new UIButton(Type.SQUARE, new UIRect(new UIColor(0,0,0,180), new UIColor(255, 255, 255, 255)), ""+ClientProxy.armoreffectConfig.getCurrentTheme(), null,null);
			enabledBtn.setTextColor(new UIColor(255,255,255,255));
			
			enabledBtn.setPosition(posX + (width - 80) / 2, tittleRect.getY2() + 15, 80, 12);
			opacitySlider.setPosition(posX + (width - 100) / 2, enabledBtn.getY2() + 20, 100, 12);
			themeBtn.setPosition(posX + (width - 80) / 2, opacitySlider.getY2() + 20, 80, 12);
			
			SettingsObject enabledSetting = new SettingsObject(enabledBtn,"Enabled");		
			SettingsObject opacitySetting = new SettingsObject(opacitySlider,"Opacity");
			SettingsObject themeSetting = new SettingsObject(themeBtn,"Current theme");
			
			components[0] = enabledSetting;
			components[1] = opacitySetting;
			components[2] = themeSetting;
		}
		else if(id == 2)
		{
			components = new SettingsObject[5];

			UIButton enabledBtn = new UIButton(Type.SQUARE, new UIRect(new UIColor(0,0,0,180), new UIColor(255, 255, 255, 255)), ""+ ClientProxy.getKeyStrokesHandler().config.getIsEnabled(), null,null);
			enabledBtn.setTextColor(new UIColor(255,255,255,255));
			
			UISlider opacitySlider = new UISlider(1,100,ClientProxy.getKeyStrokesHandler().config.getOpacity());
			
			UIButton chromeBtn = new UIButton(Type.SQUARE, new UIRect(new UIColor(0,0,0,180), new UIColor(255, 255, 255, 255)), ""+ ClientProxy.getKeyStrokesHandler().config.getChromeColor(), null,null);
			chromeBtn.setTextColor(new UIColor(255,255,255,255));
			
			GuiTextField keyColorTextField = new GuiTextField(0,getMinecraft().fontRenderer, 0, 0, 0, 0);
			keyColorTextField.setText(ClientProxy.getKeyStrokesHandler().config.getKeyColor());
			
			UIButton keyContourActive = new UIButton(Type.SQUARE, new UIRect(new UIColor(0,0,0,180), new UIColor(255, 255, 255, 255)), ""+ ClientProxy.getKeyStrokesHandler().config.isContourActive(), null,null);
			keyContourActive.setTextColor(new UIColor(255,255,255,255));
			
			
			enabledBtn.setPosition(posX + (width - 80) / 2, tittleRect.getY2() + 15, 80, 12);
			opacitySlider.setPosition(posX + (width - 100) / 2, enabledBtn.getY2() + 20, 100, 12);
			chromeBtn.setPosition(posX + (width - 80) / 2, opacitySlider.getY2() + 20, 80, 12);
			keyColorTextField.x = posX + (width - 80) / 2;
			keyColorTextField.y = chromeBtn.getY2() + 20;
			keyColorTextField.width = 80;
			keyColorTextField.height = 12;
			keyContourActive.setPosition(posX + (width - 80) / 2, keyColorTextField.y + keyColorTextField.height + 20, 80, 12);
			
			SettingsObject enabledSetting = new SettingsObject(enabledBtn,"Enabled");		
			SettingsObject opacitySetting = new SettingsObject(opacitySlider,"Opacity");
			SettingsObject chromeSetting = new SettingsObject(chromeBtn,"Chrome active");
			SettingsObject keyColorSetting = new SettingsObject(keyColorTextField,"Key color");
			SettingsObject keyContourSetting = new SettingsObject(keyContourActive,"Key contour");
			
			components[0] = enabledSetting;
			components[1] = opacitySetting;
			components[2] = chromeSetting;
			components[3] = keyColorSetting;
			components[4] = keyContourSetting;

		}
		else if(id == 3)
		{
			components = new SettingsObject[2];
			
			UIButton enabledBtn = new UIButton(Type.SQUARE, new UIRect(new UIColor(0,0,0,180), new UIColor(255, 255, 255, 255)), ""+ ClientProxy.getPotionHudHandler().config.getIsEnabled(), null,null);
			enabledBtn.setTextColor(new UIColor(255,255,255,255)); 
			
			UISlider opacitySlider = new UISlider(1,100,ClientProxy.getPotionHudHandler().config.getOpacity());
			
			enabledBtn.setPosition(posX + (width - 80) / 2, tittleRect.getY2() + 15, 80, 12);
			opacitySlider.setPosition(posX + (width - 100) / 2, enabledBtn.getY2() + 20, 100, 12);
			
			SettingsObject enabledSetting = new SettingsObject(enabledBtn,"Enabled");		
			SettingsObject opacitySetting = new SettingsObject(opacitySlider,"Opacity");

			components[0] = enabledSetting;
			components[1] = opacitySetting;
		}
		else if(id == 4)
		{
			components = new SettingsObject[1];
			
			UIButton enabledBtn = new UIButton(Type.SQUARE, new UIRect(new UIColor(0,0,0,180), new UIColor(255, 255, 255, 255)), ""+ ClientProxy.getPotionHudHandler().config.getIsEnabled(), null,null);
			enabledBtn.setTextColor(new UIColor(255,255,255,255)); 
			
			enabledBtn.setPosition(posX + (width - 80) / 2, tittleRect.getY2() + 15, 80, 12);
			
			SettingsObject enabledSetting = new SettingsObject(enabledBtn,"Enabled");		

			components[0] = enabledSetting;
		}
	}
	
	public void drawPanel(int x, int y, float partialTicks)
	{
		if(!panelActive) return;
			
		this.drawForground(x, y, partialTicks);
		
		GL11.glPushMatrix();
		GL11.glTranslatef(0, 0, zIndex);
		if(id == 0)
		{
			UIButton enabledBtn = (UIButton) components[0].getComponent();
			UISlider opacitySlider = (UISlider) components[1].getComponent();
			
			GuiUtils.renderCenteredText(components[0].getSettingName(), enabledBtn.getX() + enabledBtn.getWidth() / 2, enabledBtn.getY()-10,0.8f);
			enabledBtn.draw(x, y, partialTicks);
			GuiUtils.renderCenteredText(components[1].getSettingName(), opacitySlider.getX() + opacitySlider.getWidth() / 2, opacitySlider.getY()-10,0.8f);
			opacitySlider.draw(x, y, partialTicks);
		}
		else if(id == 1)
		{

			UIButton enabledBtn = (UIButton) components[0].getComponent();
			UISlider opacitySlider = (UISlider) components[1].getComponent();
			UIButton themeBtn = (UIButton) components[2].getComponent();

			
			GuiUtils.renderCenteredText(components[0].getSettingName(), enabledBtn.getX() + enabledBtn.getWidth() / 2, enabledBtn.getY()-10,0.8f);
			enabledBtn.draw(x, y, partialTicks);
			GuiUtils.renderCenteredText(components[1].getSettingName(), opacitySlider.getX() + opacitySlider.getWidth() / 2, opacitySlider.getY()-10,0.8f);
			opacitySlider.draw(x, y, partialTicks);
			GuiUtils.renderCenteredText(components[2].getSettingName(), themeBtn.getX() + themeBtn.getWidth() / 2, themeBtn.getY()-10,0.8f);
			themeBtn.draw(x, y, partialTicks);

		}
		else if(id == 2)
		{
			UIButton enabledBtn = (UIButton) components[0].getComponent();
			UISlider opacitySlider = (UISlider) components[1].getComponent();
			UIButton chromeBtn = (UIButton) components[2].getComponent();
			GuiTextField keyColorTextField = (GuiTextField) components[3].getComponent();
			UIButton keyContourBtn = (UIButton) components[4].getComponent();
			
			GuiUtils.renderCenteredText(components[0].getSettingName(), enabledBtn.getX() + enabledBtn.getWidth() / 2, enabledBtn.getY()-10,0.8f);
			enabledBtn.draw(x, y, partialTicks);
			GuiUtils.renderCenteredText(components[1].getSettingName(), opacitySlider.getX() + opacitySlider.getWidth() / 2, opacitySlider.getY()-10,0.8f);
			opacitySlider.draw(x, y, partialTicks);
			GuiUtils.renderCenteredText(components[2].getSettingName(), chromeBtn.getX() + chromeBtn.getWidth() / 2, chromeBtn.getY()-10,0.8f);
			chromeBtn.draw(x, y, partialTicks);
			GuiUtils.renderCenteredText(components[3].getSettingName(), keyColorTextField.x + keyColorTextField.width / 2, keyColorTextField.y-10,0.8f);
			keyColorTextField.drawTextBox();
			GuiUtils.renderCenteredText(components[4].getSettingName(), keyContourBtn.getX() + keyContourBtn.getWidth() / 2, keyContourBtn.getY()-10,0.8f);
			keyContourBtn.draw(x, y, partialTicks);
			
			
		}
		else if(id == 3)
		{
			UIButton enabledBtn = (UIButton) components[0].getComponent();
			UISlider opacitySlider = (UISlider) components[1].getComponent();
			
			GuiUtils.renderCenteredText(components[0].getSettingName(), enabledBtn.getX() + enabledBtn.getWidth() / 2, enabledBtn.getY()-10,0.8f);
			enabledBtn.draw(x, y, partialTicks);
			GuiUtils.renderCenteredText(components[1].getSettingName(), opacitySlider.getX() + opacitySlider.getWidth() / 2, opacitySlider.getY()-10,0.8f);
			opacitySlider.draw(x, y, partialTicks);
		}
		else if(id == 4)
		{
			UIButton enabledBtn = (UIButton) components[0].getComponent();
			
			GuiUtils.renderCenteredText(components[0].getSettingName(), enabledBtn.getX() + enabledBtn.getWidth() / 2, enabledBtn.getY()-10,0.8f);
			enabledBtn.draw(x, y, partialTicks);
		}
		
		GL11.glPopMatrix();
	}
	
	public void drawForground(int x, int y, float partialTicks)
	{
		GL11.glPushMatrix();
		GL11.glTranslatef(0, 0, zIndex);
		windowBackground.draw(x, y, partialTicks);
		tittleRect.draw(x, y, partialTicks);
		GuiUtils.renderText("Paramétres", posX + 2, posY + (20-getMinecraft().fontRenderer.FONT_HEIGHT) / 2);
		closeBtn.draw(x, y, partialTicks);
		GL11.glPopMatrix();
	}
	
	
	public int getId()
	{
		return this.id;
	}
	
	public boolean textboxKeyTyped(char character, int key)
	{
		if(id == 2)
		{
			GuiTextField keyColorTextField = (GuiTextField) components[3].getComponent();
			keyColorTextField.textboxKeyTyped(character, key);
			return true;
		}
		return false;
	}
	
    public void updateCursorCounter()
    {
    	if(id == 2)
		{
			GuiTextField keyColorTextField = (GuiTextField) components[3].getComponent();
			keyColorTextField.updateCursorCounter();
		}
    }
    
    public void update()
    {
    	if(id == 2)
    	{
			GuiTextField keyColorField = (GuiTextField) components[3].getComponent();
			
			if(keyColorField.isFocused()) return;
			
			String hexColor = keyColorField.getText();
			if(hexColor != null)
			{
				if(ColorUtils.isHexColor(hexColor))
				{
					ClientProxy.getKeyStrokesHandler().config.setKeyColor(hexColor);
					keyColorField.setText(hexColor);
					ClientProxy.getKeyStrokesHandler().getKeyContainer().color.setColor(hexColor);
				}
				else
				{
					keyColorField.setText(ClientProxy.getKeyStrokesHandler().config.getKeyColor());
				}
			}
    	}
    }
	

    public void mouseClicked(int x, int y, int mouseBtn)
    {
		if(id == 0)
		{
			UIButton enabledBtn = (UIButton) components[0].getComponent();
			UISlider opacitySlider = (UISlider) components[1].getComponent();
					
			if(mouseBtn == 0)
			{
				
				if(enabledBtn.isClicked(x, y))
				{
					ClientProxy.getToggleSprintHandler().config.setEnabled(!ClientProxy.getToggleSprintHandler().config.getIsEnabled());
					enabledBtn.setDisplayText("" + ClientProxy.getToggleSprintHandler().config.getIsEnabled());
					return;
				}
				else if(opacitySlider.onLeftClick(x, y));
			}
			
		}
		else if(id == 1)
		{

			UIButton enabledBtn = (UIButton) components[0].getComponent();
			UISlider opacitySlider = (UISlider) components[1].getComponent();
			UIButton themeBtn = (UIButton) components[2].getComponent();

			
			if(mouseBtn == 0)
			{
				
				if(enabledBtn.isClicked(x, y))
				{
					ClientProxy.armoreffectConfig.setEnabled(!ClientProxy.armoreffectConfig.getIsEnabled());
					enabledBtn.setDisplayText("" + ClientProxy.armoreffectConfig.getIsEnabled());
					return;
				}
				else if(themeBtn.isClicked(x, y))
				{
					ClientProxy.armoreffectConfig.setCurrentTheme(ClientProxy.armoreffectConfig.getCurrentTheme() + 1);
					themeBtn.setDisplayText("" + ClientProxy.armoreffectConfig.getCurrentTheme());
					return;
				}
				else if(opacitySlider.onLeftClick(x, y));
			}

		}
		else if(id == 2)
		{
			UIButton enabledBtn = (UIButton) components[0].getComponent();
			UISlider opacitySlider = (UISlider) components[1].getComponent();
			UIButton chromeBtn = (UIButton) components[2].getComponent();
			GuiTextField keyColorField = (GuiTextField) components[3].getComponent();
			UIButton keyContourBtn = (UIButton) components[4].getComponent();
			
			if(enabledBtn.isClicked(x, y))
			{
				ClientProxy.getKeyStrokesHandler().config.setEnabled(!ClientProxy.getKeyStrokesHandler().config.getIsEnabled());
				enabledBtn.setDisplayText("" + ClientProxy.getKeyStrokesHandler().config.getIsEnabled());
				return;
			}
			else if(opacitySlider.onLeftClick(x, y))
			{
				
				return;
			}
			else if(chromeBtn.isClicked(x, y))
			{
				ClientProxy.getKeyStrokesHandler().config.setChromeColor(!ClientProxy.getKeyStrokesHandler().config.getChromeColor());
				chromeBtn.setDisplayText("" + ClientProxy.getKeyStrokesHandler().config.getChromeColor());
				return;
			}		
			else if(keyContourBtn.isClicked(x, y))
			{
				ClientProxy.getKeyStrokesHandler().config.setContour(!ClientProxy.getKeyStrokesHandler().config.isContourActive());
				keyContourBtn.setDisplayText("" + ClientProxy.getKeyStrokesHandler().config.isContourActive());
				return;
			}
			
			keyColorField.mouseClicked(x, y, mouseBtn);
		}
		else if(id == 3)
		{
			UIButton enabledBtn = (UIButton) components[0].getComponent();
			UISlider opacitySlider = (UISlider) components[1].getComponent();
			
			if(enabledBtn.isClicked(x, y))
			{
				ClientProxy.getPotionHudHandler().config.setEnabled(!ClientProxy.getPotionHudHandler().config.getIsEnabled());
				enabledBtn.setDisplayText("" + ClientProxy.getPotionHudHandler().config.getIsEnabled());
				return;
			}
			else if(opacitySlider.onLeftClick(x, y));
		}
		else if(id == 4)
		{
			UIButton enabledBtn = (UIButton) components[0].getComponent();
			
			if(enabledBtn.isClicked(x, y))
			{
				ClientProxy.getBossHudHandler().config.setEnabled(!ClientProxy.getBossHudHandler().config.getIsEnabled());
				enabledBtn.setDisplayText("" + ClientProxy.getBossHudHandler().config.getIsEnabled());
				return;
			}
		}
		
		
		if(closeBtn.isClicked(x, y))
		{
			panelActive = false;
		}
    }
    
    public void mouseReleased(int x, int y)
    {
		if(id == 0)
		{
			UISlider opacitySlider = (UISlider) components[1].getComponent();	
			opacitySlider.mouseReleased(x, y);
			ClientProxy.getToggleSprintHandler().config.setOpacity(opacitySlider.denormalize());
		}
		else if(id == 1)
		{
			UISlider opacitySlider = (UISlider) components[1].getComponent();
			opacitySlider.mouseReleased(x, y);
			ClientProxy.armoreffectConfig.setOpacity(opacitySlider.denormalize());
		}
		else if(id == 2)
		{
			UISlider opacitySlider = (UISlider) components[1].getComponent();
			opacitySlider.mouseReleased(x, y);
			ClientProxy.getKeyStrokesHandler().config.setOpacity(opacitySlider.denormalize());
		}
		else if(id == 3)
		{
			UISlider opacitySlider = (UISlider) components[1].getComponent();
			opacitySlider.mouseReleased(x, y);
			ClientProxy.getPotionHudHandler().config.setOpacity(opacitySlider.denormalize());
		}
    }
	
}

class SettingsObject 
{
	
	private Object component;
	
	private String settingName;
	
	public SettingsObject(Object component, String settingName)
	{
		this.component = component;
		this.settingName = settingName;
	}
	
	public Object getComponent()
	{
		return this.component;
	}
	
	public String getSettingName()
	{
		return this.settingName;
	}
	
}
	
