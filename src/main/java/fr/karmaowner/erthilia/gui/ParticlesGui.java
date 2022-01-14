package fr.karmaowner.erthilia.gui;

import com.mojang.realmsclient.gui.ChatFormatting;

import fr.karmaowner.erthilia.ClientProxy;
import fr.karmaowner.erthilia.Main;
import fr.karmaowner.erthilia.handler.ParticlesHandler;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.client.config.GuiSlider;

public class ParticlesGui extends GuiScreen
{
	
    private ParticlesHandler config = ClientProxy.getParticlesHandler();

    private GuiSlider sliderScale;


    @Override
    public void initGui() {
    	
        this.buttonList.add(new GuiButton(1, this.getCenter() - 75, this.getRowPos(1), 150, 20, getSuffix(config.isEnabled())));
        this.buttonList.add(sliderScale = new GuiSlider(2, this.getCenter() - 75, this.getRowPos(2),150, 20, "Multiplicateur : x", "", 1.0, 100.0, config.getMultiplier(), false, true));
        this.buttonList.add(new GuiButton(3, this.getCenter() - 75, this.getRowPos(3), 150, 20, "Toujours multiplié: " + getSuffix(config.isMultiplyWithoutCrits())));
        this.buttonList.add(new GuiButton(4, this.getCenter() - 75, this.getRowPos(4), 150, 20, "Multiplié sur animaux: " + getSuffix(config.isMultiplyOnAnimals())));
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawWorldBackground(1);
        drawCenteredString(mc.fontRenderer, "Multiplicateur de particule", getCenter(), getRowPos(0), -1);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        switch (button.id) {
            case 1:
                // Set the boolean to enabled/disabled and change the text.
                config.setEnabled(!config.isEnabled());
                button.displayString = getSuffix(config.isEnabled());
                break;
            case 3:
                // Set the boolean to enabled/disabled and change the text.
                config.setMultiplyWithoutCrits(!config.isMultiplyWithoutCrits());
                button.displayString = "Toujours multiplié : " + getSuffix(config.isMultiplyWithoutCrits());
                break;
            case 4:
                // Set the boolean to enabled/disabled and change the text.
                config.setMultiplyOnAnimals(!config.isMultiplyOnAnimals());
                button.displayString = "Multiplié animaux : " + getSuffix(config.isMultiplyOnAnimals());
                break;
        }
    }

    @Override
    public void onGuiClosed() 
    {
    	System.out.println(config);
        config.multiplier = (int) this.sliderScale.getValue();
        Main.modConfig.saveConfiguration();
    }

    @Override
    public boolean doesGuiPauseGame() 
    {
        return false;
    }
    
    protected int getCenter() {
        return width / 2;
    }

    protected int getRowPos(int rowNumber)
    {
        return 55 + rowNumber * 23;
    }

    protected String getSuffix(final boolean enabled)
    {
        return enabled ? (ChatFormatting.GREEN + "ON") : (ChatFormatting.RED + "OFF");
    }
    
}
