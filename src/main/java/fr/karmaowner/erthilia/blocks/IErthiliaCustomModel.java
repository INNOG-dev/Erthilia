package fr.karmaowner.erthilia.blocks;

import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface IErthiliaCustomModel 
{
	
	@SideOnly(Side.CLIENT)
	public void registerCustomRender(Item item);

}
