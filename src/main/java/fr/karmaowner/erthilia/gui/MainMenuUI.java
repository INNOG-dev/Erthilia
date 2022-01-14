package fr.karmaowner.erthilia.gui;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import fr.karmaowner.erthilia.guicomponent.UIAnimatedImage;
import fr.karmaowner.erthilia.guicomponent.UIButton;
import fr.karmaowner.erthilia.guicomponent.UIColor;
import fr.karmaowner.erthilia.guicomponent.UIRect;
import fr.karmaowner.erthilia.handler.GuiHandlerClient;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiWorldSelection;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class MainMenuUI extends GuiScreen {
	

	
	private UIButton[] buttons;
	
	private UIAnimatedImage background;
	private UIAnimatedImage logo;
	private UIRect uiContainerRect = new UIRect(new UIColor(0,0,0,140));
	
	public MainMenuUI() { }
	
	@Override
	public void initGui()
	{				
		background = new UIAnimatedImage(200, "erthilia:gui/mainmenu/animatedbackground", "background (frame)","png",1);
		
		logo = new UIAnimatedImage(175, "erthilia:gui/animatedlogo", "logo (frame)","png",1);

		buttons = new UIButton[4];
		buttons[0] = new UIButton("Rejoindre",new UIColor("#5cd6ff"),new UIColor("#289eed"),1f,null);
		buttons[1] = new UIButton("Solo",new UIColor("#5cd6ff"),new UIColor("#289eed"),1f,null);
		buttons[2] = new UIButton("Options",new UIColor("#5cd6ff"),new UIColor("#289eed"),1f,null);
		buttons[3] = new UIButton("Quitter",new UIColor("#5cd6ff"),new UIColor("#289eed"),1f,null);
	
		uiContainerRect.setPosition((int)((this.width-100)/2), 0, 100, height);
		
		
		logo.setPosition(uiContainerRect.getX() + (uiContainerRect.getWidth()-180) / 2, uiContainerRect.getY() + (uiContainerRect.getHeight() - (80 + 10 + (buttons.length * 30))) / 2, 180, 80);
		
		for(int i = 0; i < buttons.length; i++)
		{
	    	int btnPosX = uiContainerRect.getX() + (uiContainerRect.getWidth() - buttons[i].getTextWidth())  / 2;
	    	int btnPosY = logo.getY2() + 10 + (30*i);
	    	
	    	buttons[i].setPosition(btnPosX, btnPosY);
		}
    	
    	Mouse.setCursorPosition(GuiHandlerClient.mouseX,GuiHandlerClient.mouseY);
	}
	
	@Override
    public void drawScreen(int x, int y, float partialTicks)
    {
    	drawBackground(x, y, partialTicks);
    	uiContainerRect.draw(x, y, partialTicks);
    	
    	logo.draw(x, y, partialTicks);

    	for(int i = 0; i < buttons.length; i++)
    	{
    		buttons[i].draw(x, y, partialTicks);
    	}
    	    	    	
    	GL11.glColor4f(1, 1, 1, 1);
    	
    }
    
    @Override
    protected void mouseClicked(int x, int y, int mouseBtn)
    {
    	if(mouseBtn == 0)
    	{
	    	if(buttons[0].isClicked(x, y))
	    	{
	    		this.mc.displayGuiScreen(new GuiMultiplayer(this));
	    		
	    		return;
	    	}
    		
	    	if(buttons[1].isClicked(x, y))
	    	{
	    		this.mc.displayGuiScreen(new GuiWorldSelection(this));
	    		return;
	    	}
	    	
	    	if(buttons[2].isClicked(x, y))
	    	{
	    		this.mc.displayGuiScreen(new GuiOptions(this, this.mc.gameSettings));
	    		return;
	    	}
	    	
	    	if(buttons[3].isClicked(x, y))
	    	{
        		FMLCommonHandler.instance().exitJava(500, false);
	    		return;
	    	}
    	}
    }
    
    public void drawBackground(int x, int y, float partialTicks)
    {
    	background.setPosition(0, 0, width, height);
    	background.draw(x, y, partialTicks);
    }
    
    @Override
    protected void keyTyped(char p_73869_1_, int p_73869_2_)
    {

    }
    
    @Override
    public void onGuiClosed()
    {
		super.onGuiClosed();
    }
    
	
}
