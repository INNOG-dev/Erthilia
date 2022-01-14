package fr.karmaowner.erthilia.keystrokes;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import fr.karmaowner.erthilia.ClientProxy;
import fr.karmaowner.erthilia.utils.ColorUtils;
import fr.karmaowner.erthilia.utils.GuiUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.settings.KeyBinding;

public class Key {

	    private final KeyBinding keyBinding;
	    
	    public Key(KeyBinding keyBinding)
	    {
	    	this.keyBinding = keyBinding;
	    }

	    private KeyContainer parent;
	    public void setParent(KeyContainer parent)
	    {
	    	this.parent = parent;
	    }
	    
	    private double height = 12.0;
	    public double getHeight()
	    {
	    	return height;
	    }
	    
	    private Type type = Type.NORMAL;
	    private boolean wasPressed;
	    private long pressTime;


	    public Key setSpaceBar() {
	        height = 7.0;
	        type = Type.SPACE_BAR;
	        return this;
	    }


	    public Key setLeftMouse() {
	        height = 10.0;
	        type = Type.LEFT_MOUSE;
	        return this;
	    }


	    public Key setRightMouse() {
	        height = 10.0;
	        type = Type.RIGHT_MOUSE;
	        return this;
	    }

	    private boolean isPressed() {
	        int keyCode = keyBinding.getKeyCode();
	        boolean pressed = keyCode < 0 ? Mouse.isButtonDown(keyCode + 100) : Keyboard.isKeyDown(keyCode);

	        if (wasPressed != pressed) {
	            pressTime = System.currentTimeMillis();
	        }

	        wasPressed = pressed;
	        return pressed;
	    }

	    /**
	     * Draws the key to the screen.
	     *
	     * @param width  The key width
	     * @param height The key height
	     */
	    public void draw(double width, double height) 
	    {
	        boolean pressed = isPressed();
	        float pressModifier = Math.min(1.0f, (System.currentTimeMillis() - pressTime) / 100.0f);
	        float brightness = (pressed ? pressModifier : (1.0f - pressModifier)) * 0.8f;

	        // draw key background
	        GL11.glColor4f(brightness, brightness, brightness, 0.6f);

	        GL11.glEnable(GL11.GL_BLEND);
	        GL11.glDisable(GL11.GL_TEXTURE_2D);
	        GL11.glBegin(GL11.GL_QUADS);
	        GL11.glVertex3d(parent.getX() + 0.0, parent.getY() + height, 0.0);
	        GL11.glVertex3d(parent.getX() +width,parent.getY() + height, 0.0);
	        GL11.glVertex3d(parent.getX() +width, parent.getY() + 0.0, 0.0);
	        GL11.glVertex3d(parent.getX() +0.0, parent.getY() + 0.0, 0.0);
	        GL11.glEnd();
	        GL11.glDisable(GL11.GL_BLEND);
	        GL11.glEnable(GL11.GL_TEXTURE_2D);
	        
	        
	        
	        
	        

	        
	        if (ClientProxy.getKeyStrokesHandler().config.isContourActive()) {
	            drawColoredRect(parent.getX() + 0.0, parent.getY() + 0.0, parent.getX() + width, parent.getY() + 1.0, pressed);
	            drawColoredRect(parent.getX() + width - 1.0, parent.getY() + 0.0, parent.getX() + width,parent.getY()+ height, pressed);
	            drawColoredRect(parent.getX() + width, parent.getY() + height, parent.getX() + 0.0,parent.getY()+ height - 1.0, pressed);
	            drawColoredRect(parent.getX() + 1.0, parent.getY() + height, parent.getX() +  0.0,parent.getY()+ 0.0, pressed);
	        }

	        
	        switch (type) {
	            case NORMAL:
	                drawKeyText(Keyboard.getKeyName(keyBinding.getKeyCode()), height, width, pressed);
	                return;
	            case SPACE_BAR:
	                drawSpaceBar(height, width, pressed);
	                return;
	            case LEFT_MOUSE:
	                drawKeyText(getMouseText(true), height, width, pressed);
	                return;
	            case RIGHT_MOUSE:
	                drawKeyText(getMouseText(false), height, width, pressed);
	        }
	        

	    }

	    private void drawKeyText(String text, double keyHeight, double keyWidth, boolean pressed) {
	        FontRenderer fontRenderer = Minecraft.getMinecraft().fontRenderer;
	        int textWidth = (int)(fontRenderer.getStringWidth(text) * 0.7f);
	        int x = parent.getX() + ((int) keyWidth - textWidth) / 2;
	        int y = parent.getY() + ((int) keyHeight - (int)(fontRenderer.FONT_HEIGHT * 0.7f)) / 2 + 1;

	        GuiUtils.renderText(text, x, y, parent.getColor(x, pressed),0.7f);
	    }

	    private void drawSpaceBar(double keyHeight, double keyWidth, boolean pressed) {
	        drawColoredRect(parent.getX() + keyWidth * 0.25, parent.getY() + keyHeight / 2.0 - 1.0, parent.getX() + keyWidth * 0.75,
	               parent.getY() + keyHeight / 2.0 + 1.0, pressed);
	    }

	    private String getMouseText(boolean left) {
	        int cps = (left ? ClientProxy.getKeyStrokesHandler().getLeftClickCounter() : ClientProxy.getKeyStrokesHandler().getRightClickCounter()).getCps();

	        if (cps == 0) {
	            return left ? "LMB" : "RMB";
	        } 
	        else {
	            return cps + " CPS";
	        }
	    }

	    private void drawColoredRect(double x1, double y1, double x2, double y2, boolean invertColor) {
	        GL11.glEnable(GL11.GL_BLEND);
	        GL11.glDisable(GL11.GL_TEXTURE_2D);
	        GL11.glShadeModel(GL11.GL_SMOOTH);
	        GL11.glBegin(GL11.GL_QUADS);
	        ColorUtils.setGlColor(parent.getColor(x1, invertColor));
	        GL11.glVertex3d(x1, y2, 0.0);
	        ColorUtils.setGlColor(parent.getColor(x2, invertColor));
	        GL11.glVertex3d(x2, y2, 0.0);
	        GL11.glVertex3d(x2, y1, 0.0);
	        ColorUtils.setGlColor(parent.getColor(x1, invertColor));
	        GL11.glVertex3d(x1, y1, 0.0);
	        GL11.glEnd();
	        GL11.glDisable(GL11.GL_BLEND);
	        GL11.glEnable(GL11.GL_TEXTURE_2D);
	    }

	    private enum Type {
	        NORMAL, SPACE_BAR, LEFT_MOUSE, RIGHT_MOUSE
	    }
}
