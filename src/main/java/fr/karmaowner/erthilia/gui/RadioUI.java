package fr.karmaowner.erthilia.gui;


import java.io.IOException;

import fr.karmaowner.erthilia.ClientProxy;
import fr.karmaowner.erthilia.Main;
import fr.karmaowner.erthilia.guicomponent.UIButton;
import fr.karmaowner.erthilia.guicomponent.UIButton.CallBackObject;
import fr.karmaowner.erthilia.guicomponent.UIButton.Type;
import fr.karmaowner.erthilia.guicomponent.UIImage;
import fr.karmaowner.erthilia.utils.GuiUtils;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;

public class RadioUI extends GuiBase {
	
	private boolean isPlaying = false;
	
	@Override
	public void initGui()
	{
		mc.gameSettings.showDebugInfo = false;
		this.setWindowSize(250, 200);
		this.setWindowPosition((this.width-250) / 2, (this.height-200) / 2);
		super.initGui();
	}
	
	@Override
	public void initializeComponent() 
	{ 
		isPlaying = ClientProxy.getRadioHandler().getCurrentRadio() != null;
		
		this.addComponent(new UIImage(new ResourceLocation(Main.MODID,"gui/radio/background.png")).setPosition(this.guiRect.getX(), this.guiRect.getY(), this.guiRect.getWidth(), this.guiRect.getHeight()));
		
		this.addComponent(new UIButton(Type.SQUARE,null, new ResourceLocation(Main.MODID,"gui/radio/on.png"),new ResourceLocation(Main.MODID,"gui/radio/on_hover.png"),false,
				new CallBackObject()
				{
					@Override
					public void call()
					{
						try {
							ClientProxy.getRadioHandler().previousRadio();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
		).setPosition(guiRect.getX()+(int)(guiRect.getWidth()*0.205), guiRect.getY() + (int)(guiRect.getHeight() * 0.54), 25, 50));
		
		ResourceLocation buttonTexture = isPlaying ? new ResourceLocation(Main.MODID,"gui/radio/on.png") : new ResourceLocation(Main.MODID,"gui/radio/off.png");
		ResourceLocation buttonHoverTexture = isPlaying ? new ResourceLocation(Main.MODID,"gui/radio/on_hover.png") : new ResourceLocation(Main.MODID,"gui/radio/off_hover.png");

		
		this.addComponent(new UIButton(Type.SQUARE,null, buttonTexture,buttonHoverTexture,false,
				new CallBackObject()
				{
					@Override
					public void call()
					{
						if(isPlaying)
						{
							isPlaying = false;
							UIButton button = (UIButton)getComponent(2);
							button.texture.texture = new ResourceLocation(Main.MODID,"gui/radio/off.png");
							button.hoverTexture.texture = new ResourceLocation(Main.MODID,"gui/radio/off_hover.png");
							ClientProxy.getRadioHandler().stop();
						}
						else
						{
							isPlaying = true;
							UIButton button = (UIButton)getComponent(2);
							button.texture.texture = new ResourceLocation(Main.MODID,"gui/radio/on.png");
							button.hoverTexture.texture = new ResourceLocation(Main.MODID,"gui/radio/on_hover.png");
							ClientProxy.getRadioHandler().playRadio();
						}
					}
				}
		).setPosition(guiRect.getX()+(int)(guiRect.getWidth()*0.455), guiRect.getY() + (int)(guiRect.getHeight() * 0.54), 25, 50));
		
		this.addComponent(new UIButton(Type.SQUARE,null, new ResourceLocation(Main.MODID,"gui/radio/on.png"),new ResourceLocation(Main.MODID,"gui/radio/on_hover.png"),false,
				new CallBackObject()
				{
					@Override
					public void call()
					{
						try {
							ClientProxy.getRadioHandler().nextRadio();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
		).setPosition(guiRect.getX()+(int)(guiRect.getWidth()*0.7), guiRect.getY() + (int)(guiRect.getHeight() * 0.54), 25, 50));
		
		super.initializeComponent();
	}
	
	@Override
	public void drawScreen(int x, int y, float p_73863_3_)
	{	
		super.drawScreen(x, y, p_73863_3_);

		GuiUtils.renderCenteredText("Radio en cours : " + (ClientProxy.getRadioHandler().getCurrentRadio() == null ? "Aucun" : ClientProxy.getRadioHandler().getCurrentRadio().getTittle()), guiRect.getX() + guiRect.getWidth() / 2, guiRect.getY() + (int)(guiRect.getHeight() * 0.35),1.2f);
	}
	
	@Override
	public void updateScreen()
	{
		super.updateScreen();
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
	

	 

	    	
	
}
