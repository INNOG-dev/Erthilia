package fr.karmaowner.erthilia.items;

import java.util.List;

import javax.annotation.Nullable;

import fr.karmaowner.client.creativetab.ErthiliaCreativeTabs;
import fr.karmaowner.client.renderer.tileentity.ExtendedTileEntityItemStackRenderer;
import fr.karmaowner.erthilia.blocks.IErthiliaCustomModel;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionType;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SuppressWarnings("deprecation")
public class ErthiliaSyringe extends ItemPotion implements IErthiliaCustomModel
{
		
	public ErthiliaSyringe()
	{
		setMaxStackSize(1);
		this.setCreativeTab(ErthiliaCreativeTabs.instance);
	}
	
    @SideOnly(Side.CLIENT)
    public ItemStack getDefaultInstance()
    {
        return PotionUtils.addPotionToItemStack(new ItemStack(this), PotionTypes.WATER);
    }
	
	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving)
    {
        EntityPlayer entityplayer = entityLiving instanceof EntityPlayer ? (EntityPlayer)entityLiving : null;
       

        if (!worldIn.isRemote)
        {
            for (PotionEffect potioneffect : PotionUtils.getEffectsFromStack(stack))
            {
                if (potioneffect.getPotion().isInstant())
                {
                    potioneffect.getPotion().affectEntity(entityplayer, entityplayer, entityLiving, potioneffect.getAmplifier(), 1.0D);
                }
                else
                {
                    entityLiving.addPotionEffect(new PotionEffect(potioneffect));
                } 
            }
        }

        if (entityplayer == null || !entityplayer.capabilities.isCreativeMode)
        {

	    	PotionType type = PotionUtils.getPotionTypeFromNBT(stack.getTagCompound());
	    	if(type != PotionTypes.AWKWARD && type != PotionTypes.THICK && type != PotionTypes.MUNDANE)
	    	{
            	stack.shrink(1);
	    	}
	        else
	        {
	        
	        	if(!stack.hasTagCompound())
	        	{
	        		stack.setTagCompound(new NBTTagCompound());
	            	stack.getTagCompound().setInteger("EffectDurability", 4);
	        	}
	        	else if(!stack.getTagCompound().hasKey("EffectDurability"))
	        	{
	            	stack.getTagCompound().setInteger("EffectDurability", 4);
	        	}
	        	
	        	stack.getTagCompound().setInteger("EffectDurability", stack.getTagCompound().getInteger("EffectDurability") - 1);
	        	
	            if(stack.getTagCompound().getInteger("EffectDurability") <= 0)
	            {
	            	stack.shrink(1);
	            	
	                if(entityplayer != null) entityplayer.inventory.addItemStackToInventory(new ItemStack(ErthiliaItems.EMPTY_SYRINGE));
	            }
	        }
        	
        }

        return stack;
    }
	

	@Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
		return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    
	@Override
    public int getMaxItemUseDuration(ItemStack p_77626_1_)
    {
        return 20;
    }
    
	@Override
    public EnumAction getItemUseAction(ItemStack p_77661_1_)
    {
        return EnumAction.NONE;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
    	
        PotionUtils.addPotionTooltip(stack, tooltip, 1.0F);

        
        if(stack.hasTagCompound())
        {
	        if(stack.getTagCompound().hasKey("EffectDurability"))
	        {
	        	int effect = stack.getTagCompound().getInteger("EffectDurability");
	    		tooltip.add("§6Utilisation restant : " + effect + "/4");
	
	        }
        }
    }
    

    @Override
	public String getItemStackDisplayName(ItemStack stack)
	{
        return I18n.translateToLocal(PotionUtils.getPotionFromItem(stack).getNamePrefixed("syringe.effect."));
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items)
    {
    	if (this.isInCreativeTab(tab))
        {
    		items.add(PotionUtils.addPotionToItemStack(new ItemStack(this), PotionTypes.EMPTY));
    		items.add(PotionUtils.addPotionToItemStack(new ItemStack(this), PotionTypes.AWKWARD));
    		items.add(PotionUtils.addPotionToItemStack(new ItemStack(this), PotionTypes.MUNDANE));
    		items.add(PotionUtils.addPotionToItemStack(new ItemStack(this), PotionTypes.THICK));
    		items.add(PotionUtils.addPotionToItemStack(new ItemStack(this), PotionTypes.FIRE_RESISTANCE));
    		items.add(PotionUtils.addPotionToItemStack(new ItemStack(this), PotionTypes.LONG_FIRE_RESISTANCE));
    		items.add(PotionUtils.addPotionToItemStack(new ItemStack(this), PotionTypes.SWIFTNESS));
    		items.add(PotionUtils.addPotionToItemStack(new ItemStack(this), PotionTypes.LONG_SWIFTNESS));
    		items.add(PotionUtils.addPotionToItemStack(new ItemStack(this), PotionTypes.STRONG_SWIFTNESS));
    		items.add(PotionUtils.addPotionToItemStack(new ItemStack(this), PotionTypes.HEALING));
    		items.add(PotionUtils.addPotionToItemStack(new ItemStack(this), PotionTypes.STRONG_HEALING));
    		items.add(PotionUtils.addPotionToItemStack(new ItemStack(this), PotionTypes.REGENERATION));
    		items.add(PotionUtils.addPotionToItemStack(new ItemStack(this), PotionTypes.LONG_REGENERATION));
    		items.add(PotionUtils.addPotionToItemStack(new ItemStack(this), PotionTypes.STRONG_REGENERATION));
    		items.add(PotionUtils.addPotionToItemStack(new ItemStack(this), PotionTypes.STRENGTH));
    		items.add(PotionUtils.addPotionToItemStack(new ItemStack(this), PotionTypes.LONG_STRENGTH));
        }
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack stack)
    {
        return super.hasEffect(stack) || !PotionUtils.getEffectsFromStack(stack).isEmpty();
    }

	@Override
	public void registerCustomRender(Item item) {
		item.setTileEntityItemStackRenderer(new ExtendedTileEntityItemStackRenderer());
	}
    

	


	

	
}
