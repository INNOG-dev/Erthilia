package fr.karmaowner.erthilia.items;

import fr.karmaowner.client.creativetab.ErthiliaCreativeTabs;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;

public class ErthiliaHoe extends ItemHoe {

	public ErthiliaHoe(ToolMaterial material) {
		super(material);
		this.setCreativeTab(ErthiliaCreativeTabs.instance);

	}
	
    public boolean getIsRepairable(ItemStack input, ItemStack repair)
    {
        return isRepairable() ? repair.getItem() == ErthiliaItem.getItem(this.getUnlocalizedName()) : false;
    }

}
