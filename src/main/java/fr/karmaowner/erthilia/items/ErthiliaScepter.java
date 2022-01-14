package fr.karmaowner.erthilia.items;

import java.util.List;

import javax.annotation.Nullable;

import fr.karmaowner.client.creativetab.ErthiliaCreativeTabs;
import fr.karmaowner.erthilia.scepter.ScepterEffect;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


public class ErthiliaScepter extends Item {
	
	private ScepterEffect effect;
	
	public ErthiliaScepter(ScepterEffect scepterEffect, int maxUse)
	{
		this.effect = scepterEffect;
		this.maxStackSize = 1;
		this.setFull3D();
		this.setMaxDamage(maxUse);
		this.setCreativeTab(ErthiliaCreativeTabs.instance);
	}
	
    public boolean onScepterUse(ItemStack is, World world, EntityPlayer player)
    {
		Item item = is.getItem();
		if(item instanceof ErthiliaScepter)
		{
			effect.performEffect(player);
			
			is.damageItem(1, player);
			/*if(is.getItemDamage() == is.getMaxDamage())
			{
				player.inventory.setInventorySlotContents(player.inventory.currentItem, ItemStack.EMPTY);
			}*/
			return true;
		}
		return false;
	}
    
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
    	for(String line : effect.addEffectDescription())
		{
    		tooltip.add(line);
		}
    }

	

	 @Override
	 public boolean isBookEnchantable(ItemStack stack, ItemStack book)
	 {
	    return false;
	 }
	
	
	
	


}
