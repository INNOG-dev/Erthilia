package fr.karmaowner.erthilia.items;

import fr.karmaowner.client.creativetab.ErthiliaCreativeTabs;
import fr.karmaowner.client.renderer.tileentity.ExtendedTileEntityItemStackRenderer;
import fr.karmaowner.erthilia.blocks.IErthiliaCustomModel;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.PotionTypes;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class ErthiliaBasicItem extends Item implements IErthiliaCustomModel {
	
	public ErthiliaBasicItem()
	{
		this.setCreativeTab(ErthiliaCreativeTabs.instance);
	}
	
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand handIn)
    {
    	ItemStack itemstack = player.getHeldItem(handIn);
    	
    	if(itemstack.getItem() == ErthiliaItems.EMPTY_SYRINGE)
		{
    		RayTraceResult raytraceResult = this.rayTrace(world, player, true);
	
		    if (raytraceResult == null)
		    {
		    	return new ActionResult<ItemStack>(EnumActionResult.PASS, itemstack);
		    }
		    else
		    {
		    	if (raytraceResult.typeOfHit == RayTraceResult.Type.BLOCK)
		    	{
		                BlockPos blockpos = raytraceResult.getBlockPos();
	
		     
		                if (!world.isBlockModifiable(player,blockpos) || !player.canPlayerEdit(blockpos.offset(raytraceResult.sideHit), raytraceResult.sideHit, itemstack))
		                {
		                    return new ActionResult<ItemStack>(EnumActionResult.PASS, itemstack);
		                }
	
		                if (world.getBlockState(blockpos).getMaterial() == Material.WATER)
		                {
	                        world.playSound(player, player.posX, player.posY, player.posZ, SoundEvents.ITEM_BOTTLE_FILL, SoundCategory.NEUTRAL, 1.0F, 1.0F);
	                        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, turnSyringeIntoItem(itemstack, player, PotionUtils.addPotionToItemStack(new ItemStack(ErthiliaItems.WATER_SYRINGE), PotionTypes.WATER)));

		               }
		    	}
		    }
	        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
		}
        return new ActionResult<ItemStack>(EnumActionResult.PASS, itemstack);
    }
    
    protected ItemStack turnSyringeIntoItem(ItemStack emptySyringeIs, EntityPlayer player, ItemStack stack)
    {
    	emptySyringeIs.shrink(1);

        if (emptySyringeIs.isEmpty())
        {
            return stack;
        }
        else
        {
            if (!player.inventory.addItemStackToInventory(stack))
            {
                player.dropItem(stack, false);
            }

            return emptySyringeIs;
        }
    }
	

	
	@Override
    public boolean isBeaconPayment(ItemStack stack)
    {
        return this == Items.EMERALD || this == Items.DIAMOND || this == Items.GOLD_INGOT || this == Items.IRON_INGOT || this == ErthiliaItems.JADE || this == ErthiliaItems.CYANITE || this == ErthiliaItems.MORGANITE || this == ErthiliaItems.PYRITE || this == ErthiliaItems.CITRINE;
    }

	@Override
	public void registerCustomRender(Item item) {
		if(item.getRegistryName().getResourcePath().equalsIgnoreCase("empty_syringe"))
		{
			item.setTileEntityItemStackRenderer(new ExtendedTileEntityItemStackRenderer());
		}
	}

}
