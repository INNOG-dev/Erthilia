package fr.karmaowner.erthilia.gui.inventory;

import java.io.IOException;

import org.lwjgl.opengl.GL11;

import fr.karmaowner.erthilia.Main;
import fr.karmaowner.erthilia.guicomponent.UIButton;
import fr.karmaowner.erthilia.guicomponent.UIButton.Type;
import fr.karmaowner.erthilia.inventory.ContainerReversedEnchantment;
import fr.karmaowner.erthilia.network.PacketSupressEnchantment;
import fr.karmaowner.erthilia.utils.GuiUtils;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ReverseEnchantmentUI extends GuiContainer{

    	private static final ResourceLocation ENCHANTMENT_TABLE_GUI_TEXTURE = new ResourceLocation(Main.MODID + ":gui/container/reversed_enchanting_table.png");
	    private static final ResourceLocation BOTTLE_XP_TEXTURE = new ResourceLocation(Main.MODID, "gui/xp.png");
	    
	    private static final UIButton confirmBtn = new UIButton(Type.SQUARE,"Confirmer",new ResourceLocation(Main.MODID,"gui/btn.png"),null,true,null);
	    	    
	    private final ContainerReversedEnchantment container;
	    
	    private String inventoryName;
	    
	    
	    public ReverseEnchantmentUI(InventoryPlayer inventory, World worldIn, BlockPos pos, String str)
	    {
	        super(new ContainerReversedEnchantment(inventory, worldIn, pos));
	        this.container = (ContainerReversedEnchantment)this.inventorySlots;	        
	        inventoryName = str;		

	    }
	    
	    @Override
	    public void initGui()
	    {
	    	super.initGui();
	    	int posX = (guiLeft + xSize / 2);
	    	int posY = guiTop + ySize / 3;
        	confirmBtn.setPosition(posX, posY, 50, 10);
	    }


	    @Override
	    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
	    {
	        this.fontRenderer.drawString(inventoryName == null ? "undefined" : I18n.format(this.inventoryName, new Object[0]), 12, 5, 4210752);
	        
    		confirmBtn.setEnabled(false);

    		if(container.tableInventory.getStackInSlot(0) != null)
	        {
	        	if(!container.tableInventory.getStackInSlot(0).isItemEnchanted())
	        	{
		        	GuiUtils.renderCenteredText("Item non enchanté", (this.xSize / 2) + 27, this.ySize / 4,0.8f,3750201);
	        	}
	        	else
	        	{

	        		String price = "Prix : " + container.xpCount;
		        	GuiUtils.renderCenteredText(price , (this.xSize / 2)+20, this.ySize / 5,0.8f,3750201);
		        	GL11.glColor3f(1, 1, 1);
		        	GuiUtils.drawImage((this.xSize / 2)+ mc.fontRenderer.getStringWidth(price), this.ySize / 5-3, BOTTLE_XP_TEXTURE, 10, 10,(int) zLevel);



		        	if(!container.playerHaveXp())
		        	{
		        		GuiUtils.renderCenteredText("§cvous n'avez pas l'xp nécessaire", (this.xSize/2) + 25, (this.ySize / 4)+5,0.6f);
		        	}
		        	else
		        	{
		        		GuiUtils.renderCenteredText("§2voulez-vous reset les effets ?", (this.xSize/2) + 25, (this.ySize / 4)+5,0.6f);
		        		confirmBtn.setEnabled(true);
		        	}
	        	}
	        }
	    }
	    

	    @Override
	    public void updateScreen()
	    {
	        super.updateScreen();	        
	    }

	    @Override
	    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException
	    {
	        super.mouseClicked(mouseX, mouseY, mouseButton);
	        
	        if(confirmBtn.isHover(mouseX, mouseY))
	        {
	        	PacketSupressEnchantment packet = new PacketSupressEnchantment();
	        	packet.containerId = container.windowId;
	        	Main.getPacketHandler().sendToServer(packet);
	        }
	    }

	   
	    @Override
	    public void drawScreen(int mouseX, int mouseY, float partialTicks)
	    {
	        partialTicks = this.mc.getTickLength();
	        this.drawDefaultBackground();
	        super.drawScreen(mouseX, mouseY, partialTicks);
	        
	        confirmBtn.draw(mouseX, mouseY, 0);
	        this.renderHoveredToolTip(mouseX, mouseY);
	        
	    }


		@Override
		protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
		{
	        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
	        this.mc.getTextureManager().bindTexture(ENCHANTMENT_TABLE_GUI_TEXTURE);
	        int i = (this.width - this.xSize) / 2;
	        int j = (this.height - this.ySize) / 2;
	        this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize);
		}
}

