package fr.karmaowner.erthilia.items;

import fr.karmaowner.client.creativetab.ErthiliaCreativeTabs;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class ErthiliaArmor extends ItemArmor {

	public ErthiliaArmor(ArmorMaterial material, int renderId, EntityEquipmentSlot type) {
		super(material, renderId, type);
		this.setCreativeTab(ErthiliaCreativeTabs.instance);

	}
	
    
    public boolean getIsRepairable(ItemStack input, ItemStack repair)
    {
        return isRepairable() ? repair.getItem() == ErthiliaItem.getItem(this.getUnlocalizedName()) : false;
    }

}
