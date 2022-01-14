package fr.karmaowner.erthilia.gui.inventory;

import org.lwjgl.opengl.GL11;

import fr.karmaowner.erthilia.inventory.ContainerSyringeStand;
import fr.karmaowner.erthilia.tiles.TileEntitySyringeStand;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class SyringeStandUI extends GuiContainer
{
    private static final ResourceLocation alambicGuiTextures = new ResourceLocation("erthilia","gui/container/syringe_alambic.png");
    
    private TileEntitySyringeStand tileSyringeStand;

    public SyringeStandUI(InventoryPlayer inventory, TileEntitySyringeStand tile)
    {
        super(new ContainerSyringeStand(inventory, tile));
        tileSyringeStand = tile;
    }

    protected void drawGuiContainerForegroundLayer(int p_146979_1_, int p_146979_2_)
    {
        String s = this.tileSyringeStand.hasCustomName() ? this.tileSyringeStand.getName() : "SyringeStand";
        this.fontRenderer.drawString(s, this.xSize / 2 - this.fontRenderer.getStringWidth(s) / 2, 6, 4210752);
        this.fontRenderer.drawString(I18n.format("container.inventory", new Object[0]), 8, this.ySize - 96 + 2, 4210752);
    }
    
    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }

    protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(alambicGuiTextures);
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
       
        int i1 = this.tileSyringeStand.getField(0);


        if (i1 > 0)
        {
            int j1 = (int)(28.0F * (1.0F - (float)i1 / 200.0F));

            if (j1 > 0)
            {
                this.drawTexturedModalRect(k + 97, l + 16, 176, 0, 9, j1);
            }

            int k1 = i1 / 2 % 7;

            switch (k1)
            {
                case 0:
                    j1 = 29;
                    break;
                case 1:
                    j1 = 24;
                    break;
                case 2:
                    j1 = 20;
                    break;
                case 3:
                    j1 = 16;
                    break;
                case 4:
                    j1 = 11;
                    break;
                case 5:
                    j1 = 6;
                    break;
                case 6:
                    j1 = 0;
            }

            if (j1 > 0)
            {
                this.drawTexturedModalRect(k + 65, l + 14 + 29 - j1, 185, 29 - j1, 12, j1);
            }
        }
    }
}