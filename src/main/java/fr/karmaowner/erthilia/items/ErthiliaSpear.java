package fr.karmaowner.erthilia.items;

import fr.karmaowner.client.creativetab.ErthiliaCreativeTabs;
import fr.karmaowner.client.renderer.tileentity.ExtendedTileEntityItemStackRenderer;
import fr.karmaowner.erthilia.blocks.IErthiliaCustomModel;
import fr.karmaowner.erthilia.entity.EntitySpear;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class ErthiliaSpear extends Item implements IErthiliaCustomModel {
	
            
    private int maxItemUseDuration;
    
	
	public ErthiliaSpear(int maxDamage, int maxItemUseDuration)
	{
		super();
		this.setCreativeTab(ErthiliaCreativeTabs.instance);
		this.setMaxDamage(maxDamage);
		this.maxItemUseDuration = maxItemUseDuration;		
		this.maxStackSize = 1;
	}
	
	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft)
    {
        if (entityLiving instanceof EntityPlayer)
        {
            EntityPlayer entityplayer = (EntityPlayer)entityLiving;
            boolean flag = entityplayer.capabilities.isCreativeMode;
            ItemStack itemstack = stack;

            int i = this.getMaxItemUseDuration(stack) - timeLeft;
                        
            if (i < 0) return;

            if (!itemstack.isEmpty() || flag)
            {
                if (itemstack.isEmpty())
                {
                    itemstack = new ItemStack(ErthiliaItems.SPEAR);
                }

                float f = getSpearVelocity(i);

                if ((double)f >= 0.1D)
                {

                    if (!worldIn.isRemote)
                    {
                    	EntitySpear entitySpear = new EntitySpear(worldIn,entityplayer);
                        entitySpear.shoot(entityplayer, entityplayer.rotationPitch, entityplayer.rotationYaw, 0.0F, f * 3.0F, 1.0F);

                        if (f == 1.0F)
                        {
                        	entitySpear.setIsCritical(true);
                        }

                        
                        if (entityplayer.capabilities.isCreativeMode && (itemstack.getItem() == Items.SPECTRAL_ARROW || itemstack.getItem() == Items.TIPPED_ARROW))
                        {
                        	entitySpear.pickupStatus = EntitySpear.PickupStatus.CREATIVE_ONLY;
                        }
                        worldIn.spawnEntity(entitySpear);
                    }

                    worldIn.playSound((EntityPlayer)null, entityplayer.posX, entityplayer.posY, entityplayer.posZ, SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);

                    if (!entityplayer.capabilities.isCreativeMode)
                    {
                        itemstack.shrink(1);

                        if (itemstack.isEmpty())
                        {
                            entityplayer.inventory.deleteStack(itemstack);
                        }
                    }

                }
            }
        }
    }

	
    public static float getSpearVelocity(int charge)
    {
        float f = (float)charge / 20.0F;
        f = (f * f + f * 2.0F) / 3.0F;

        if (f > 1.0F)
        {
            f = 1.0F;
        }

        return f;
    }
	
    
	
	@Override
    public EnumAction getItemUseAction(ItemStack is)
    {
        return EnumAction.NONE;
    }
	
    /**
     * How long it takes to use or consume an item
     */
    public int getMaxItemUseDuration(ItemStack is)
    {
        return this.maxItemUseDuration;
    }


    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
         playerIn.setActiveHand(handIn);
         return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
    }

    /**
     * Return the enchantability factor of the item, most of the time is based on material.
     */
    public int getItemEnchantability()
    {
        return 0;
    }
    
    public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack)
    {
        return false;
    }
    
    public boolean canItemEditBlocks()
    {
        return false;
    }

    
    public boolean isBookEnchantable(ItemStack stack, ItemStack book)
    {
        return false;
    }
    
    public EntitySpear customizeSpear(EntitySpear spear)
    {
        return spear;
    }

	@Override
	public void registerCustomRender(Item item)
	{
		item.setTileEntityItemStackRenderer(new ExtendedTileEntityItemStackRenderer());
	}
	

}
