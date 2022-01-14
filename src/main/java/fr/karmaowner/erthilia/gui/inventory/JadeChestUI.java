package fr.karmaowner.erthilia.gui.inventory;

import fr.karmaowner.erthilia.inventory.ContainerJadeChest;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

public class JadeChestUI extends GuiContainer {
	
	 	private static final ResourceLocation CHEST_GUI_TEXTURE = new ResourceLocation("textures/gui/container/generic_54.png");
	    private final IInventory chestInventory;
	    private final int inventoryRows;

	    public JadeChestUI(IInventory playerInventory, IInventory chestInventory)
	    {
	        super(new ContainerJadeChest(playerInventory,chestInventory, Minecraft.getMinecraft().player));
	        this.chestInventory = chestInventory;
	        this.allowUserInput = false;

	        this.inventoryRows = chestInventory.getSizeInventory() / 9;
	        this.ySize = 114 + this.inventoryRows * 18;
	    }

	    public void drawScreen(int mouseX, int mouseY, float partialTicks)
	    {
	        this.drawDefaultBackground();
	        super.drawScreen(mouseX, mouseY, partialTicks);
	        this.renderHoveredToolTip(mouseX, mouseY);
	    }

	    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
	    {
	        this.fontRenderer.drawString(chestInventory.getDisplayName().getUnformattedText(), 8, 6, 4210752);
	    }

	    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
	    {
	        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
	        this.mc.getTextureManager().bindTexture(CHEST_GUI_TEXTURE);
	        int i = (this.width - this.xSize) / 2;
	        int j = (this.height - this.ySize) / 2;
	        this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.inventoryRows * 18 + 17);
	        this.drawTexturedModalRect(i, j + this.inventoryRows * 18 + 17, 0, 126, this.xSize, 96);
	    }

}
