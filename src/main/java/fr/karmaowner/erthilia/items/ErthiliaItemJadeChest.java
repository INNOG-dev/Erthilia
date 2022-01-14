package fr.karmaowner.erthilia.items;

import javax.annotation.Nullable;

import fr.karmaowner.client.renderer.tileentity.ExtendedTileEntityItemStackRenderer;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ErthiliaItemJadeChest extends ItemBlock {

	public ErthiliaItemJadeChest(Block block) {
		super(block);
	}
	
    @SideOnly(Side.CLIENT)
    public void setTileEntityItemStackRenderer(@Nullable net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer teisr)
    {
    	teisr = new ExtendedTileEntityItemStackRenderer();
    }

}
