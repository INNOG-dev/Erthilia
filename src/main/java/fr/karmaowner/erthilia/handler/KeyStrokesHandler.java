package fr.karmaowner.erthilia.handler;

import org.lwjgl.input.Mouse;

import com.google.common.base.Preconditions;

import fr.karmaowner.erthilia.keystrokes.FillerKey;
import fr.karmaowner.erthilia.keystrokes.Key;
import fr.karmaowner.erthilia.keystrokes.KeyContainer;
import fr.karmaowner.erthilia.keystrokes.utils.ClickCounter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;

public class KeyStrokesHandler {

    private final ClickCounter leftClickCounter = new ClickCounter();
    private final ClickCounter rightClickCounter = new ClickCounter();
    
    public KeystrokesConfig config;
    
    private KeyContainer keyContainer;
    
    public KeyStrokesHandler()
    {
    	config = new KeystrokesConfig();
    	buildKeyHolder();
    }
	
    @SubscribeEvent
    public void onMouseInput(InputEvent.MouseInputEvent event) {
        if (!Mouse.getEventButtonState()) {
            return;
        }

        switch (Mouse.getEventButton()) {
            case 0:
                leftClickCounter.onClick();
                return;
            case 1:
                rightClickCounter.onClick();
                return;
            default:
        }
    }
    
    public void buildKeyHolder() {
        GameSettings gameSettings = Minecraft.getMinecraft().gameSettings;

        // the key layout is currently not configurable
        Key none = new FillerKey();
        Key keyW = new Key(gameSettings.keyBindForward);
        Key keyA = new Key(gameSettings.keyBindLeft);
        Key keyS = new Key(gameSettings.keyBindBack);
        Key keyD = new Key(gameSettings.keyBindRight);
        Key leftMouse = new Key(gameSettings.keyBindAttack).setLeftMouse();
        Key rightMouse = new Key(gameSettings.keyBindUseItem).setRightMouse();
        Key keySpaceBar = new Key(gameSettings.keyBindJump).setSpaceBar();

        keyContainer = new KeyContainer.Builder(new KeyContainer())
                .setWidth(52)
                .setGapSize(2)
                .addRow(none, keyW, none)
                .addRow(keyA, keyS, keyD)
                .addRow(leftMouse, rightMouse)
                .addRow(keySpaceBar)
                .build();
    }
    
    public ClickCounter getLeftClickCounter()
    {
    	return this.leftClickCounter;
    }
    
    public ClickCounter getRightClickCounter()
    {
    	return this.rightClickCounter;
    }
	
    public KeyContainer getKeyContainer()
    {
    	return this.keyContainer;
    }
    
	public class KeystrokesConfig
	{
		private boolean isEnabled = true;
		
		private int opacity;
		
		private boolean chromeColor;
		
		private String keyColor;
		
		private boolean contourActive;
		
		public boolean getIsEnabled()
		{
			return this.isEnabled;
		}
		
		public void setEnabled(boolean state)
		{
			this.isEnabled = state;
		}
		
		public int getOpacity()
		{
			return this.opacity;
		}
		
		public void setOpacity(int opacity)
		{
			Preconditions.checkArgument(opacity > 0 || opacity < 255);
			this.opacity = opacity;
		}
		
		public void setChromeColor(boolean state)
		{
			this.chromeColor = state;
		}
		
		public boolean getChromeColor()
		{
			return this.chromeColor;
		}
		
		public void setKeyColor(String hex)
		{
			this.keyColor = hex;
		}
		
		public String getKeyColor()
		{
			return keyColor;
		}
		
		public boolean isContourActive()
		{
			return this.contourActive;
		}
		
		public void setContour(boolean state)
		{
			this.contourActive = state;
		}
		
		
	}
	
}
