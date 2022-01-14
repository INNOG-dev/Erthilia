package fr.karmaowner.erthilia.gui.inventory;

import java.io.IOException;
import java.util.List;

import org.lwjgl.opengl.GL11;

import fr.karmaowner.erthilia.Main;
import fr.karmaowner.erthilia.guicomponent.UIButton;
import fr.karmaowner.erthilia.guicomponent.UIButton.Type;
import fr.karmaowner.erthilia.inventory.ContainerReversedCraftingTable;
import fr.karmaowner.erthilia.network.PacketUncraftItem;
import fr.karmaowner.erthilia.utils.GuiUtils;
import fr.karmaowner.erthilia.utils.PlayerUtils;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ReversedCraftingTableUI extends GuiContainer
{

	    private static final ResourceLocation GUI_TEXTURE = new ResourceLocation(Main.MODID + ":gui/container/reversed_enchanting_table.png");
	    
	    private static final ResourceLocation XP_ICO = new ResourceLocation(Main.MODID + ":gui/xp.png");
	    
	    private static final UIButton confirmBtn = new UIButton(Type.SQUARE,"Confirmer",new ResourceLocation(Main.MODID + ":gui/btn.png"),null,true,null);
	    
	    private ContainerReversedCraftingTable container;
	    
	    private String inventoryName;

	    public ReversedCraftingTableUI(InventoryPlayer playerInventory, World world, BlockPos pos, String str)
	    {
	        super(new ContainerReversedCraftingTable(playerInventory, world, pos));
	        container = (ContainerReversedCraftingTable) this.inventorySlots;	
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
	    
	    public void drawScreen(int mouseX, int mouseY, float partialTicks)
	    {
	        partialTicks = this.mc.getTickLength();
	        this.drawDefaultBackground();
	        super.drawScreen(mouseX, mouseY, partialTicks);
	        this.renderHoveredToolTip(mouseX, mouseY);
	    }

	    @Override
	    protected void drawGuiContainerForegroundLayer(int x, int y)
	    {
	        this.fontRenderer.drawString(inventoryName == null ? "undefined" : I18n.format(this.inventoryName, new Object[0]), 12, 5, 4210752);

        	    		
	        if(container.tableInventory.getStackInSlot(0) != null)
	        {
	        	ItemStack is = container.tableInventory.getStackInSlot(0);

	        	String price = "Prix : " + container.xpCount;
	        	
		        GuiUtils.renderText(price , (this.xSize  / 2) + 2, this.ySize / 5,3750201,0.8f);
		        
		        GL11.glColor3f(1, 1, 1);
		        
		        GuiUtils.drawImage((this.xSize  / 2) + 2 + (mc.fontRenderer.getStringWidth(price) * 0.8f) + 2, this.ySize / 5-3,XP_ICO,10, 10,(int) zLevel);
		        
		        List<ItemStack> itemstacks = null;
		        
		
			    itemstacks = container.getRecipesInversed(is);
		        
		    	confirmBtn.setEnabled(false);
		        
		        if(itemstacks == null)
		        {
	        		GuiUtils.renderCenteredText("§cPlacez un objet", (this.xSize/2) + 25, (this.ySize / 4)+5,0.6f);
		        	return;
		        }
	        	
		        if(!container.playerHaveXp())
		        {
		        	GuiUtils.renderCenteredText("§cvous n'avez pas l'xp nécessaire", (this.xSize/2) + 25, (this.ySize / 4)+5,0.6f);
		        }
		        else if(is.isItemEnchanted())
		        {
		        	GuiUtils.renderCenteredText("§cCette item est enchanté", (this.xSize/2) + 25, (this.ySize / 4)+5,0.6f);
		        }
		        else if(is.isItemDamaged())
		        {
		        	GuiUtils.renderCenteredText("§cCette item est cassé", (this.xSize/2) + 25, (this.ySize / 4)+5,0.6f);
		        }
		        else if(!PlayerUtils.inventoryHavePlace(container.getPlayer(), itemstacks))
		        {
		        	GuiUtils.renderCenteredText("§cVous n'avez pas de place dans votre inventaire", (this.xSize/2) + 25, (this.ySize / 4)+5,0.6f);
		        }
		        else
		        {
		        	GuiUtils.renderCenteredText("§2voulez-vous détruire l'objet ?", (this.xSize/2) + 25, (this.ySize / 4)+5,0.6f);
		        	confirmBtn.setEnabled(true);
		        }
	        }
	    }
	    
	    @Override
	    protected void drawGuiContainerBackgroundLayer(float idk, int x, int y)
	    {
	    	 GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	         this.mc.getTextureManager().bindTexture(GUI_TEXTURE);
	         int k = (this.width - this.xSize) / 2;
	         int l = (this.height - this.ySize) / 2;
	         this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
	         
		     confirmBtn.draw(x, y, 0);

	    }


	    public void updateScreen()
	    {
	        super.updateScreen();	        
	    }

	    @Override
	    protected void mouseClicked(int x, int y, int btn) throws IOException
	    {
	        super.mouseClicked(x, y, btn);
	        
	        if(confirmBtn.isHover(x, y))
	        {
	        	PacketUncraftItem packet = new PacketUncraftItem();
	        	packet.containerId = container.windowId;
	        	Main.getPacketHandler().sendToServer(packet);
	        }
	    }

	 
}

