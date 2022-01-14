package fr.karmaowner.erthilia.items;

import fr.karmaowner.client.creativetab.ErthiliaCreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;

public class ErthiliaSword extends ItemSword {

	public ErthiliaSword(ToolMaterial p_i45356_1_) {
		super(p_i45356_1_);
		this.setCreativeTab(ErthiliaCreativeTabs.instance);
	}
	
    public boolean getIsRepairable(ItemStack input, ItemStack repair)
    {
        return isRepairable() ? repair.getItem() == ErthiliaItem.getItem(this.getUnlocalizedName()) : false;
    }

}
