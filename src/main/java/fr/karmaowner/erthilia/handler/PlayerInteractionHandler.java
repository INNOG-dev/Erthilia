package fr.karmaowner.erthilia.handler;

import fr.karmaowner.erthilia.Main;
import fr.karmaowner.erthilia.capability.playercapability.ErthiliaPlayerProvider;
import fr.karmaowner.erthilia.capability.playercapability.IPlayer;
import fr.karmaowner.erthilia.items.ErthiliaItems;
import fr.karmaowner.erthilia.items.ErthiliaScepter;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.EnumHand;
import net.minecraftforge.client.event.InputUpdateEvent;
import net.minecraftforge.event.brewing.PotionBrewEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerRespawnEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;

public class PlayerInteractionHandler {
	
	
	@SubscribeEvent
	public void onRightClickBlock(PlayerInteractEvent.RightClickBlock event)
	{	
		if(event.getHand() == EnumHand.MAIN_HAND)
		{
			Item item = event.getEntityPlayer().getHeldItemMainhand() != null ? event.getEntityPlayer().getHeldItemMainhand().getItem() : null;
			if(item != null)
			{
				if(item instanceof ErthiliaScepter)
				{
					ErthiliaScepter scepter = (ErthiliaScepter) event.getEntityPlayer().getHeldItemMainhand().getItem();
					scepter.onScepterUse(event.getEntityPlayer().getHeldItemMainhand(), event.getWorld(), event.getEntityPlayer());
					return;
				}
			}
		} 
	}
	
	@SubscribeEvent
	public void onRightClickItem(PlayerInteractEvent.RightClickItem event)
	{	
		if(event.getHand() == EnumHand.MAIN_HAND)
		{
			Item item = event.getEntityPlayer().getHeldItemMainhand() != null ? event.getEntityPlayer().getHeldItemMainhand().getItem() : null;
			if(item != null)
			{
				
				if(item instanceof ErthiliaScepter)
				{
					ErthiliaScepter scepter = (ErthiliaScepter) event.getEntityPlayer().getHeldItemMainhand().getItem();
					scepter.onScepterUse(event.getEntityPlayer().getHeldItemMainhand(), event.getWorld(), event.getEntityPlayer());
					return;
				}
			}
		}
	}
	
	
	@SubscribeEvent
	public void onPlayerTick(PlayerTickEvent event)
	{
		if(event.phase == Phase.START)
		{
				EntityPlayer player = event.player;
				
				if(player.inventory.armorItemInSlot(3)== null || player.inventory.armorItemInSlot(3).getItem() != ErthiliaItems.SEARCH_HELMET)
				{
					return;
				}
				if(player.inventory.armorItemInSlot(2) == null || player.inventory.armorItemInSlot(2).getItem() != ErthiliaItems.SEARCH_CHESTPLATE)
				{
					return;
				}
				if(player.inventory.armorItemInSlot(1) == null || player.inventory.armorItemInSlot(1).getItem() != ErthiliaItems.SEARCH_LEGGINGS) 
				{
					return;
				}
				if(player.inventory.armorItemInSlot(0) == null || player.inventory.armorItemInSlot(0).getItem() != ErthiliaItems.SEARCH_BOOTS)
				{
					return;
				}
				
				
				player.addPotionEffect(new PotionEffect(MobEffects.SPEED,20*20,2));
				player.addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST,20*20,1));
				player.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE,20*20,1));
				player.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE,20*20,1));
				player.addPotionEffect(new PotionEffect(MobEffects.HASTE,20*20,1));
	
		}
	}
	
	@SubscribeEvent
	public void onPlayerJoin(PlayerLoggedInEvent event)
	{
		IPlayer playerData = (IPlayer)event.player.getCapability(ErthiliaPlayerProvider.PLAYER_CAP, null);
		if(playerData != null) playerData.updateRenderer();
	}
	
	@SubscribeEvent
	public void onBrewing(PotionBrewEvent.Pre event)
	{
		if(event.getItem(event.getLength()-1).getItem() == Items.GLOWSTONE_DUST)
		{
			for(int i = 0; i < event.getLength()-1; i++)
			{
				ItemStack is = event.getItem(i);
				if(PotionUtils.getPotionFromItem(is) == PotionTypes.STRENGTH)
				{
					event.setCanceled(true);
				}
			}
		}
	}
	
	@SubscribeEvent
	public void onInputUpdate(InputUpdateEvent event)
	{
		if(event.getEntityPlayer().isHandActive())
		{
			event.getMovementInput().moveForward /= 0.2F;
			event.getMovementInput().moveStrafe /= 0.2F;
		}
	}
	
	@SubscribeEvent
	public void onPlayerRespawn(PlayerRespawnEvent event)
	{
		Main.getCosmeticsManager().updateCompanion(event.player);
	}
	


	
	

	
}
