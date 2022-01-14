package fr.karmaowner.erthilia.gui;

import java.awt.image.BufferedImage;

import fr.karmaowner.erthilia.Main;
import fr.karmaowner.erthilia.guicomponent.UIImage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.ImageBufferDownload;
import net.minecraft.client.renderer.ThreadDownloadImageData;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.ResourceLocation;

public class PlanningUI extends GuiBase {
	
	private static final String planningUrl = "http://127.0.0.1/api/planning.png";
	
	private ResourceLocation texture = new ResourceLocation(Main.MODID,"gui/planning/background.png");

	
	public PlanningUI()
	{        

	}
	
	@Override
	public void initGui()
	{
		this.setWindowSize(300, 225);
		this.setWindowPosition((this.width - 300) / 2, (this.height-225) / 2);
		super.initGui();
	}
	
	@Override
	public void initializeComponent() 
	{ 
		getImage(texture);
		this.addComponent(new UIImage(texture).setPosition((this.width - 300) / 2, (this.height-225) / 2, 300, 225));
		super.initializeComponent();
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

	public static ThreadDownloadImageData getImage(ResourceLocation resourceLocationIn) {

	   TextureManager textureManager = Minecraft.getMinecraft().getTextureManager();
	   ThreadDownloadImageData threadDownloadImageData;
	   threadDownloadImageData = new ThreadDownloadImageData(null, planningUrl, resourceLocationIn, new ImageBufferDownload() {

	   @Override
	   public BufferedImage parseUserSkin(BufferedImage image)
	   {
		   return (image);
	   }
	   });

	   textureManager.loadTexture(resourceLocationIn, threadDownloadImageData);

	   return (threadDownloadImageData);
	}

}
