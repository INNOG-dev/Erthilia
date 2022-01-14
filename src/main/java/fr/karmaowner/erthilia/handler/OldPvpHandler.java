package fr.karmaowner.erthilia.handler;

import org.lwjgl.opengl.GL11;

import fr.karmaowner.erthilia.client.event.ModelPlayerEvent;
import fr.karmaowner.erthilia.client.event.RenderItemEvent;
import fr.karmaowner.erthilia.utils.PlayerUtils;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IEntityMultiPart;
import net.minecraft.entity.MultiPartEntityPart;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.network.play.server.SPacketEntityVelocity;
import net.minecraft.stats.StatList;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class OldPvpHandler {
 
	
	  
	  @SubscribeEvent
	  public void onPlayerAttackEntity(AttackEntityEvent event) 
	  {
		  
		  event.setCanceled(true);
		  
		  EntityPlayer player = event.getEntityPlayer();
		  
		  Entity target = event.getTarget();
		  
		  ItemStack stack = player.getHeldItemMainhand();
	     
		  if(stack.getItem().onLeftClickEntity(stack, player, target)) return;
		  
		  if(target.canBeAttackedWithItem())
		  {
	          if (!target.hitByEntity(player))
	          {
	              float f = (float)player.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue();
	              float f1;

	              if (target instanceof EntityLivingBase)
	              {
	            	  f1 = EnchantmentHelper.getModifierForCreature(player.getHeldItemMainhand(), ((EntityLivingBase)target).getCreatureAttribute());
	              }
	              else
	              {
	            	  f1 = EnchantmentHelper.getModifierForCreature(player.getHeldItemMainhand(), EnumCreatureAttribute.UNDEFINED);
	              }
	              
	              if (f > 0.0F || f1 > 0.0F)
	              {
		              int i = 0;
	                  i = i + EnchantmentHelper.getKnockbackModifier(player);

		              
		              if(player.isSprinting())
		              {
	                      player.world.playSound((EntityPlayer)null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_PLAYER_ATTACK_KNOCKBACK, player.getSoundCategory(), 1.0F, 1.0F);
		            	  ++i;
		              }
		              
		              boolean flag2 = player.fallDistance > 0.0F && !player.onGround && !player.isOnLadder() && !player.isInWater() && !player.isPotionActive(MobEffects.BLINDNESS) && !player.isRiding() && target instanceof EntityLivingBase;
		              flag2 = flag2 && !player.isSprinting();
		              
	                  net.minecraftforge.event.entity.player.CriticalHitEvent hitResult = net.minecraftforge.common.ForgeHooks.getCriticalHit(player, target, flag2, flag2 ? 1.5F : 1.0F);
	                  flag2 = hitResult != null;
	                  
	                  if(flag2)
	                  {
	                	  f *= hitResult.getDamageModifier();
	                  }
	                  
	                  f = f + f1;
	                  
	                  float f4 = 0.0F;
	                  boolean flag4 = false;
	                  
	                  int j = EnchantmentHelper.getFireAspectModifier(player);
	                  
	                  if (target instanceof EntityLivingBase)
	                  {
	                        f4 = ((EntityLivingBase)target).getHealth();

	                        if (j > 0 && !target.isBurning())
	                        {
	                            flag4 = true;
	                            target.setFire(1);
	                        }
	                  }
	                  
	                  double d1 = target.motionX;
	                  double d2 = target.motionY;
	                  double d3 = target.motionZ;
	                  
	                  boolean flag5 = target.attackEntityFrom(DamageSource.causePlayerDamage(player), f);

	                  if(flag5)
	                  {
	                	  if(i > 0)
	                	  {
	                          if (target instanceof EntityLivingBase)
	                            {
	                                ((EntityLivingBase)target).knockBack(player, (float)i * 0.5F, (double)MathHelper.sin(player.rotationYaw * 0.017453292F), (double)(-MathHelper.cos(player.rotationYaw * 0.017453292F)));
	                            }
	                            else
	                            {
	                                target.addVelocity((double)(-MathHelper.sin(player.rotationYaw * 0.017453292F) * (float)i * 0.5F), 0.1D, (double)(MathHelper.cos(player.rotationYaw * 0.017453292F) * (float)i * 0.5F));
	                            }

	                            player.motionX *= 0.6D;
	                            player.motionZ *= 0.6D;
	                            player.setSprinting(false);
	                	  }
	                	  
	 
	                      
	                      if (target instanceof EntityPlayerMP && target.velocityChanged)
	                      {
	                          ((EntityPlayerMP)target).connection.sendPacket(new SPacketEntityVelocity(target));
	                          target.velocityChanged = false;
	                          target.motionX = d1;
	                          target.motionY = d2;
	                          target.motionZ = d3;
	                      }
	                      
	                      
	                      if (flag2)
	                      {
	                          player.world.playSound((EntityPlayer)null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_PLAYER_ATTACK_CRIT, player.getSoundCategory(), 1.0F, 1.0F);
	                          player.onCriticalHit(target);
	                      }
	                      
	                      if (!flag2)
	                      {
	                          player.world.playSound((EntityPlayer)null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_PLAYER_ATTACK_STRONG, player.getSoundCategory(), 1.0F, 1.0F);
	                      }
	                      
	                      if (f1 > 0.0F)
	                      {
	                          player.onEnchantmentCritical(target);
	                      }
	                      
	                      player.setLastAttackedEntity(target);

	                      if (target instanceof EntityLivingBase)
	                      {
	                          EnchantmentHelper.applyThornEnchantments((EntityLivingBase)target, player);
	                      }
	                      
	                      EnchantmentHelper.applyArthropodEnchantments(player, target);
	                      
	                      ItemStack itemstack1 = player.getHeldItemMainhand();
	                      Entity entity = target;

	                      if (target instanceof MultiPartEntityPart)
	                      {
	                          IEntityMultiPart ientitymultipart = ((MultiPartEntityPart)target).parent;

	                          if (ientitymultipart instanceof EntityLivingBase)
	                          {
	                              entity = (EntityLivingBase)ientitymultipart;
	                          }
	                      }
	                      
	                      if (!itemstack1.isEmpty() && entity instanceof EntityLivingBase)
	                      {
	                          ItemStack beforeHitCopy = itemstack1.copy();
	                          itemstack1.hitEntity((EntityLivingBase)entity, player);

	                          if (itemstack1.isEmpty())
	                          {
	                              net.minecraftforge.event.ForgeEventFactory.onPlayerDestroyItem(player, beforeHitCopy, EnumHand.MAIN_HAND);
	                              player.setHeldItem(EnumHand.MAIN_HAND, ItemStack.EMPTY);
	                          }
	                      }
	                      
	                      if (target instanceof EntityLivingBase)
	                      {
	                          float f5 = f4 - ((EntityLivingBase)target).getHealth();
	                          player.addStat(StatList.DAMAGE_DEALT, Math.round(f5 * 10.0F));

	                          if (j > 0)
	                          {
	                              target.setFire(j * 4);
	                          }

	                      }
	                      
	                      player.addExhaustion(0.1F);
	                  }
	                  else
	                  {
	                	  player.world.playSound((EntityPlayer)null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_PLAYER_ATTACK_NODAMAGE, player.getSoundCategory(), 1.0F, 1.0F);

	                      if (flag4)
	                      {
	                    	  target.extinguish();
	                      }
	                  }
	                  
                     


	              }

	          }
		  }

	  }
	  
	  @SubscribeEvent
	  public void onRightClickItem(PlayerInteractEvent.RightClickItem evt) 
	  {
		    if (evt.getItemStack().getItem() instanceof ItemSword) 
		    {
			      ItemStack stack = evt.getEntityPlayer().getHeldItemMainhand();
			      
			      if (stack.getItem().getItemUseAction(stack) == EnumAction.NONE) 
			      {
				        evt.getEntityPlayer().setActiveHand(evt.getHand());
				        evt.setCancellationResult(EnumActionResult.SUCCESS);
				        evt.setCanceled(true);
			      } 
		    } 
	  }
	  
	  @SubscribeEvent
	  public void onItemUseStart(LivingEntityUseItemEvent.Start evt) 
	  {
	    if (evt.getItem().getItem() instanceof net.minecraft.item.ItemSword) evt.setDuration(72000); 
	  }
	  
	  
	  @SubscribeEvent
	  @SideOnly(Side.CLIENT)
	  public void onSetupPlayerModel(ModelPlayerEvent.SetupAngles.Post event)
	  {
		  	EntityPlayer player = event.getEntityPlayer();
		  	

		    if (PlayerUtils.isBlockingWithSword(player)) 
		    {
		    	event.getModelPlayer().bipedRightArm.rotateAngleX = event.getModelPlayer().bipedRightArm.rotateAngleX  * 0.5F - ((float)Math.PI / 10F) * 3f;
		    } 
	  }
	  
	  
	  @SubscribeEvent
	  @SideOnly(Side.CLIENT)
	  public void onRenderItemEvent(RenderItemEvent.Held.Pre event)
	  {
		  	if(event.getEntity() instanceof EntityPlayer)
		  	{
		  		EntityPlayer player = (EntityPlayer) event.getEntity();
		  		
		  		if (PlayerUtils.isBlockingWithSword(player)) 
			    {
	                  GL11.glTranslatef(-0.25F, -0.01F, 0.05F);
	                  GL11.glRotatef(-35.0F, 1.0F, 0.0F, 0.0F);

	                  GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
	                  GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
			    }
		  		
		  	}
		    
	  }
	  
	
}
