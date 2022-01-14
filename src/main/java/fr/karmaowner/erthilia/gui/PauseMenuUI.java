package fr.karmaowner.erthilia.gui;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import fr.karmaowner.erthilia.Main;
import fr.karmaowner.erthilia.guicomponent.UIButton;
import fr.karmaowner.erthilia.guicomponent.UIButton.Type;
import fr.karmaowner.erthilia.guicomponent.UIColor;
import fr.karmaowner.erthilia.guicomponent.UIImage;
import fr.karmaowner.erthilia.guicomponent.UIRect;
import fr.karmaowner.erthilia.utils.GuiUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.util.ResourceLocation;

public class PauseMenuUI extends GuiScreen {

	
	//private UIImage menuContainer = new UIImage(new ResourceLocation(Main.MODID,"gui/pausemenu/container.png"));
	

	private UIImage logo = new UIImage(new ResourceLocation(Main.MODID,"gui/logo.png"));
	
	private UIButton[] buttons = new UIButton[6];
	
	private UIRect rect = new UIRect(new UIColor(0,0,0,125));
	private UIRect containerRect = new UIRect(new UIColor(255,255,255));

	//private String splashText;
	
	private URI websiteUrl;
	
	@Override
	public void initGui()
	{	
		try 
		{
			websiteUrl = new URI("http://www.google.fr");
		} 
		catch (URISyntaxException e) 
		{
			e.printStackTrace();
		}
		
		containerRect.setPosition((width - 300) / 2, (height - 200) / 2, 300, 200);
		
		buttons[0] = new UIButton(Type.SQUARE,null, new ResourceLocation(Main.MODID,"gui/pausemenu/site.png"),new ResourceLocation(Main.MODID,"gui/pausemenu/site_hover.png"),false,new UIButton.CallBackObject()
		{
			@Override
			public void call()
			{
	    		try 
	    		{
					java.awt.Desktop.getDesktop().browse(websiteUrl);
				} 
	    		catch (IOException e) 
	    		{
					e.printStackTrace();
				}
			}
			
		});
		buttons[1] = new UIButton(Type.SQUARE,null, new ResourceLocation(Main.MODID,"gui/pausemenu/ts.png"),new ResourceLocation(Main.MODID,"gui/pausemenu/ts_hover.png"),false,null);
		
		buttons[2] =  new UIButton(Type.SQUARE,null, new ResourceLocation(Main.MODID,"gui/pausemenu/discord.png"),new ResourceLocation(Main.MODID,"gui/pausemenu/discord_hover.png"),false,null);
		
		buttons[3] = new UIButton(Type.SQUARE,null, new ResourceLocation(Main.MODID,"gui/pausemenu/settings.png"),new ResourceLocation(Main.MODID,"gui/pausemenu/settings_hover.png"),false,new UIButton.CallBackObject()
		{
			@Override
			public void call()
			{
				Minecraft mc = Minecraft.getMinecraft();
	    		mc.displayGuiScreen(new GuiOptions(PauseMenuUI.this, mc.gameSettings));
			}
			
		});
		
		buttons[4] = new UIButton(Type.SQUARE,null, new ResourceLocation(Main.MODID,"gui/pausemenu/back_ig.png"),new ResourceLocation(Main.MODID,"gui/pausemenu/back_ig_hover.png"),false,new UIButton.CallBackObject()
		{
			@Override
			public void call()
			{
				Minecraft mc = Minecraft.getMinecraft();
				mc.displayGuiScreen(null);
				mc.setIngameFocus();
			}
		});
		
		buttons[5] = new UIButton(Type.SQUARE,null, new ResourceLocation(Main.MODID,"gui/pausemenu/qt.png"),new ResourceLocation(Main.MODID,"gui/pausemenu/qt_hover.png"),false,new UIButton.CallBackObject()
		{
			@Override
			public void call()
			{
				 Minecraft mc = Minecraft.getMinecraft();
	             mc.world.sendQuittingDisconnectingPacket();
	             mc.loadWorld((WorldClient)null);
	             mc.displayGuiScreen(new MainMenuUI());
	             //this.isDisconnecting = !this.isDisconnecting;
	             //this.quitBtn.setDisplayText("Déconnexion");
		    	//this.disconnectPressedTime = System.currentTimeMillis() + (this.timeBeforeDisconnection * 1000);	
			}
		});
		
		//bar = new UIProgressBar();
		//splashText = mc.player.getCommandSenderName();
		
		//menuContainer.color = new UIColor(menuContainer.color.getRed(),menuContainer.color.getGreen(),menuContainer.color.getBlue(),(int)(0.6f*255));
		//menuContainer.setPosition(0, 0, 115, height);
		
		//int logoPosX = menuContainer.getX() + (menuContainer.getWidth()-128) / 2;
		//int logoPosY = menuContainer.getY() + (menuContainer.getHeight() - (55 + 15 + (buttons.length * (18+3)))) / 2;
		
		int logoPosX = containerRect.getX() + (containerRect.getWidth() - 160) / 2;
		int logoPosY = containerRect.getY();
		
		logo.setPosition(logoPosX, logoPosY, 160, 85);
		
		buttons[0].setPosition((width-145) / 2, logo.getY2() + 10, 145, 18);
		buttons[1].setPosition((width-145) / 2, logo.getY2() + 32, 70, 18);
		buttons[2].setPosition(buttons[0].getX2() - 70, logo.getY2() + 32, 70, 18);
		buttons[3].setPosition((width-145) / 2, logo.getY2() + 54, 145, 18);
		buttons[4].setPosition((width-145) / 2, logo.getY2() + 76, 70, 18);
		buttons[5].setPosition(buttons[0].getX2() - 70, logo.getY2() + 76, 70, 18);
		
		rect.setPosition(10, height - 25, 70, 15);
	}
	
    public void drawScreen(int x, int y, float partialTicks)
    {
    	new UIRect(new UIColor(0,0,0,50)).setPosition(0, 0, width, height).draw(x, y, partialTicks);
    	drawBackground(x,y, partialTicks);
    	
    
    	    	
		for(int i = 0; i < buttons.length; i++)
		{
			buttons[i].draw(x, y, partialTicks);
		}
		
		rect.draw(x, y, partialTicks);
		GuiUtils.renderCenteredText("§6Erthilia §8- §cV" + Main.VERSION, rect.getX() + rect.getWidth() / 2,(rect.getY() + rect.getHeight() / 2) - 2 , 0.8f);
		
    	

        /*Tessellator tessellator = Tessellator.instance;
        tessellator.setColorOpaque_I(-1);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)(this.width / 2 + 39 / 2), ((teamspeakBtn.getY() + quitBtn.getY() + 40 * 2) / 2)-75, 100.0F);
        GL11.glRotatef(0F, 0.0F, 0.0F, 1.0F);
        float f1 = 1.8F - MathHelper.abs(MathHelper.sin((float)(Minecraft.getSystemTime() % 1000L) / 1000.0F * (float)Math.PI * 2.0F) * 0.1F);
        f1 = f1 * 50.0F / (float)(this.fontRendererObj.getStringWidth(this.splashText) + 32);
        GL11.glScalef(f1, f1, f1);
        GuiUtils.renderCenteredText(this.splashText, -20, -15, -256);
        GL11.glPopMatrix();*/

    	//if(mc.thePlayer != null) GuiUtils.drawPlayer(this.width / 2, (teamspeakBtn.getY() + quitBtn.getY() + 40 * 2) / 2, 40, (float)(this.width/2) - x, (float)((teamspeakBtn.getY() + quitBtn.getY() - 20 * 2) / 2) - y,(EntityLivingBase)mc.thePlayer);
    	    	

    	/*if(this.isDisconnecting)
    	{
	    	long now = System.currentTimeMillis();
	    	float timeLeft = (this.disconnectPressedTime - now) / 1000;
	    	bar.setValue((float)(timeLeft / this.timeBeforeDisconnection));
	    	if(timeLeft <= 0)
	    	{
	    		this.isDisconnecting = false;
                this.mc.theWorld.sendQuittingDisconnectingPacket();
                this.mc.loadWorld((WorldClient)null);
                this.mc.displayGuiScreen(new MainMenuUI());
	    	}
    	}*/
    	
    	/*if(this.isDisconnecting)
    	{
    		bar.setPosition(12, quitBtn.getY2() - 2, 50, 1);
    		bar.draw(x, y);
    	}
    	else
    	{
    		quitBtn.setDisplayText("Quitter");
    	}*/
    	
    	//quitBtn.setPosition(12, 180, 50, 15);
    	
    	
    	

    }
    
    @Override
    protected void mouseClicked(int x, int y, int mouseBtn)
    {
    	if(mouseBtn == 0)
    	{
    		
    		for(int i = 0; i < buttons.length; i++)
    		{
    			if(buttons[i].isClicked(x, y))
    			{
    				buttons[i].callback.call();
    			}
    		}
    	}
    }
    
    public void drawBackground(int x, int y, float partialTicks)
    {
    	logo.draw(x, y, partialTicks);
    }
    
    @Override
    public void updateScreen()
    {
    	/*ticks++;
    	if(this.isDisconnecting)
    	{
    		if(ticks % 10 == 0)
    		{
    			if(state == 0)
    			{
    				quitBtn.setDisplayText("Déconnexion");
    			}
    			else if(state == 1)
    			{
    				quitBtn.setDisplayText("Déconnexion.");
    			}
    			else if(state == 2)
    			{
    				quitBtn.setDisplayText("Déconnexion..");
    			}
    			else if(state == 3)
    			{
    				quitBtn.setDisplayText("Déconnexion...");
    			}
    			if(state++ > 3) state = 0;
    		}
    	}*/
    }
    

    
}
