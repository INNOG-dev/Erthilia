package fr.karmaowner.erthilia.renderer.tileentity;

import fr.karmaowner.erthilia.blocks.ErthiliaSyringeStand;
import fr.karmaowner.erthilia.model.ModelReversedCraftingTable;
import fr.karmaowner.erthilia.tiles.TileEntityReversedCraftingTable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.EnumFacing;

public class TileEntityReversedCraftingTableRenderer extends TileEntitySpecialRenderer<TileEntityReversedCraftingTable> 
{

	private final ModelReversedCraftingTable standModel = new ModelReversedCraftingTable();

	 public void render(TileEntityReversedCraftingTable te, double x, double y, double z, float partialTicks, int destroyStage, float alpha)
	    {
	        GlStateManager.enableDepth();
	        GlStateManager.depthFunc(515);
	        GlStateManager.depthMask(true);
	        EnumFacing facing = null;

	        if (te.hasWorld())
	        {	            
	            IBlockState state = te.getWorld().getBlockState(te.getPos());
	            
                if(!state.getValue(ErthiliaSyringeStand.RENDERMODEL)) return;

	            
                facing = state.getValue(ErthiliaSyringeStand.FACING);
	        }


	        bindTexture(standModel.getTexture());

	        GlStateManager.pushMatrix();
	        GlStateManager.enableRescaleNormal(); 

	        if (destroyStage < 0)
	        {
	        	  GlStateManager.color(1.0F, 1.0F, 1.0F, alpha);
	        }
	        else
	        {
	              bindTexture(DESTROY_STAGES[destroyStage]);
	              GlStateManager.matrixMode(5890);
	              GlStateManager.pushMatrix();
	              GlStateManager.scale(4.0F, 4.0F, 1.0F);
	              GlStateManager.translate(0.0625F, 0.0625F, 0.0625F);
	              GlStateManager.matrixMode(5888);
	         }
	          
	         GlStateManager.translate((float)x, (float)y + 1.0F, (float)z + 1.0F);
	         GlStateManager.scale(1.0F, -1.0F, -1.0F);
	         GlStateManager.translate(0.5F, 0.5F, 0.5F);
	           
	         int j = 0;

	         if (facing == EnumFacing.NORTH)
	         {
	        	 j = 180;
	         }

	         else if (facing == EnumFacing.SOUTH)
	         {
	             j = 0;
	         }

	         else if (facing == EnumFacing.EAST)
	         {
	              j = 270;
	         }

	         else if (facing == EnumFacing.WEST)
	         {
	        	 j = 90;
	         }


 	         GlStateManager.rotate((float)j, 0.0F, 1.0F, 0.0F);
	         GlStateManager.translate(0.5F, -0.9F, 0.5F);

	         GlStateManager.scale(6, 6, 6);

	          standModel.render();
	          
	          GlStateManager.disableRescaleNormal();
	          GlStateManager.popMatrix();
	          GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

	          if (destroyStage >= 0)
	          {
	              GlStateManager.matrixMode(5890);
	              GlStateManager.popMatrix();
	              GlStateManager.matrixMode(5888);
	            }
	        }
}
