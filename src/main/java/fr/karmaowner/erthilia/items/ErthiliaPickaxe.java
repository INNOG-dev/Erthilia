package fr.karmaowner.erthilia.items;

import fr.karmaowner.client.creativetab.ErthiliaCreativeTabs;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;

public class ErthiliaPickaxe extends ItemPickaxe {

	public ErthiliaPickaxe(ToolMaterial material) {
		super(material);
		this.setCreativeTab(ErthiliaCreativeTabs.instance);

	}
	
    public boolean getIsRepairable(ItemStack input, ItemStack repair)
    {
        return isRepairable() ? repair.getItem() == ErthiliaItem.getItem(this.getUnlocalizedName()) : false;
    }

}
