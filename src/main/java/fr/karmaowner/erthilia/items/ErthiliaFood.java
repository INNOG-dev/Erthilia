package fr.karmaowner.erthilia.items;

import java.util.ArrayList;
import java.util.List;

import fr.karmaowner.client.creativetab.ErthiliaCreativeTabs;
import fr.karmaowner.erthilia.Main;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class ErthiliaFood extends ItemFood {
	
	protected boolean alwaysEdible = false;
	
	private int eatDuration;
	
	private EnumAction actionForFood;
	
	private List<PotionEffect> effects = new ArrayList<PotionEffect>();

	public ErthiliaFood(int foodGiven, float saturationModifier, boolean wolfMeal, int eatDuration) {
		this(EnumAction.EAT, foodGiven, saturationModifier,wolfMeal,eatDuration);
	}
	
	public ErthiliaFood(EnumAction actionForFood, int foodGiven, float saturationModifier, boolean wolfMeal, int eatDuration) {
		super(foodGiven, saturationModifier, wolfMeal);
		this.eatDuration = eatDuration;
		this.actionForFood = actionForFood;
		this.setCreativeTab(ErthiliaCreativeTabs.instance);

	}
	
	
	
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving)
    {
        if (entityLiving instanceof EntityPlayer)
        {
            EntityPlayer entityplayer = (EntityPlayer)entityLiving;
            entityplayer.getFoodStats().addStats(this, stack);
            
            if(this.actionForFood == EnumAction.EAT) worldIn.playSound((EntityPlayer)null, entityplayer.posX, entityplayer.posY, entityplayer.posZ, SoundEvents.ENTITY_PLAYER_BURP, SoundCategory.PLAYERS, 0.5F, worldIn.rand.nextFloat() * 0.1F + 0.9F);
            else if(stack.getItem().getRegistryName().getResourcePath().equals("join")) worldIn.playSound((EntityPlayer)null, entityplayer.posX, entityplayer.posY, entityplayer.posZ,new SoundEvent(new ResourceLocation(Main.MODID + ":smoking_state_1")), SoundCategory.PLAYERS, 0.5F, worldIn.rand.nextFloat() * 0.1F + 0.9F);

            this.onFoodEaten(stack, worldIn, entityplayer);
            entityplayer.addStat(StatList.getObjectUseStats(this));

            if (entityplayer instanceof EntityPlayerMP)
            {
                CriteriaTriggers.CONSUME_ITEM.trigger((EntityPlayerMP)entityplayer, stack);
            }
        }

        stack.shrink(1);
        return stack;
    }
	

	@Override
    protected void onFoodEaten(ItemStack is, World world, EntityPlayer player)
    {	
		if(world.isRemote)
		{
			if(is.getItem().getRegistryName().getResourcePath().equals("join"))
			{
				world.spawnParticle(EnumParticleTypes.CLOUD, player.posX + player.getLookVec().x, player.posY + player.getEyeHeight() / 2 + player.getLookVec().y, player.posZ + player.getLookVec().z, player.getLookVec().x * 0.3f , 0.6+player.getLookVec().y,player.getLookVec().z* 0.3f);
				world.spawnParticle(EnumParticleTypes.CLOUD, player.posX + player.getLookVec().y-0.5, player.posY + player.getEyeHeight() / 2 + player.getLookVec().y, player.posZ + player.getLookVec().z, player.getLookVec().x * 0.3f, 0.6+player.getLookVec().y, player.getLookVec().z* 0.3f);
				world.spawnParticle(EnumParticleTypes.CLOUD, player.posX + player.getLookVec().z+0.5, player.posY + player.getEyeHeight() / 2 + player.getLookVec().y, player.posZ + player.getLookVec().z, player.getLookVec().x * 0.3f, 0.6+player.getLookVec().y, player.getLookVec().z* 0.3f);

			}
		}
		
		for(PotionEffect effect : effects)
		{
			player.addPotionEffect(new PotionEffect(effect));
		}
    }
	
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand handIn)
    {
		return super.onItemRightClick(world, player, handIn);
    }

   
    public int getMaxItemUseDuration(ItemStack p_77626_1_)
    {
        return this.eatDuration;
    }
    
    public EnumAction getItemUseAction(ItemStack p_77661_1_)
    {
        return actionForFood;
    }
    
    public ErthiliaFood addEffect(PotionEffect effect)
    {
    	effects.add(effect);
    	return this;
    }
	


	

	
}
