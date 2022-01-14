package fr.karmaowner.client.creativetab;

import java.util.Comparator;

import fr.karmaowner.erthilia.blocks.ErthiliaBlock;
import fr.karmaowner.erthilia.items.ErthiliaItem;
import fr.karmaowner.erthilia.items.ErthiliaItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ErthiliaCreativeTabs extends CreativeTabs {

	public static CreativeTabs instance;

	
	public ErthiliaCreativeTabs(String label) {
		super(label);
	}

	@Override
	public ItemStack getTabIconItem() {
		return new ItemStack(ErthiliaItems.JADE);
	}
	
    @SideOnly(Side.CLIENT)
    public void displayAllRelevantItems(NonNullList<ItemStack> p_78018_1_)
    {
    	super.displayAllRelevantItems(p_78018_1_);
    	
    	
    	p_78018_1_.sort(new Comparator<ItemStack>() {

			@Override
			public int compare(ItemStack o1, ItemStack o2) {
				return Long.compare(getTime(o1),getTime(o2));
			}
			
			public long getTime(ItemStack o1)
			{
				for(ErthiliaItem item : ErthiliaItem.items.values())
				{
					if(o1.getItem() == item.getItem())
					{
						return item.registeredTime;
					}
				}
				
				for(ErthiliaBlock block : ErthiliaBlock.blocks.values())
				{
					if(o1.getItem() == Item.getItemFromBlock(block.getBlock()))
					{
						return block.registeredTime;
					}
				}
				
				return Long.MAX_VALUE;
			}
			
			
			
		});
    }


}
