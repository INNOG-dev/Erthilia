package fr.karmaowner.erthilia.potion;

import fr.karmaowner.erthilia.guicomponent.UIButton;
import fr.karmaowner.erthilia.utils.GuiUtils;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;

public class PotionButton extends UIButton {
	
	public PotionInformations information;
	
    protected static final ResourceLocation inventoryTexture = new ResourceLocation("textures/gui/container/inventory.png");


	public PotionButton(PotionEffect potion, Type type, String text, ResourceLocation texture, ResourceLocation hoverTexture,boolean displayText, CallBackObject callback) {
		super(type, text, texture, hoverTexture, displayText, callback);
		information = new PotionInformations(potion);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void draw(int x, int y, float partialTicks)
	{
        super.draw(x, y, partialTicks);
        
		this.getMinecraft().getTextureManager().bindTexture(inventoryTexture);
		
        if(information.hasStatusIcon())
        {
            int l = information.getStatusIconIndex();
            this.drawTexturedModalRect(posX + 4 , posY-1, 0 + l % 8 * 18, 198 + l / 8 * 18, 18, 18);
            
            information.getPotion().renderInventoryEffect(posX + 9, posY, information.getPotionEffect(), getMinecraft());

        }
        
        GuiUtils.renderText("§7Niv §c" + information.getPotionAmplifierText(), posX + 30, posY + 6,GuiUtils.gameColor,0.8f);
                
	}

}
