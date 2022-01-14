package fr.karmaowner.erthilia.items;

import fr.karmaowner.client.creativetab.ErthiliaCreativeTabs;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;

public class ErthiliaAxe extends ItemAxe {

	public ErthiliaAxe(ToolMaterial material,float damage, float speed)
	{
		super(material,damage, speed);
		this.setCreativeTab(ErthiliaCreativeTabs.instance);

	}
	
    public boolean getIsRepairable(ItemStack input, ItemStack repair)
    {
        return isRepairable() ? repair.getItem() == ErthiliaItem.getItem(this.getUnlocalizedName()) : false;
    }

}
