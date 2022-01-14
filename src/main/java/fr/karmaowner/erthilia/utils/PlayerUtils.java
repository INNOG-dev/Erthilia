package fr.karmaowner.erthilia.utils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import fr.karmaowner.erthilia.entity.monster.ErthiliaLivingEntity;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class PlayerUtils {

	public static void removeAllEffects(EntityPlayer player)
	{
		Iterator<PotionEffect> iterator = player.getActivePotionEffects().iterator();
		List<Integer> activePotionsId = new ArrayList<Integer>();
		while(iterator.hasNext())
		{
			PotionEffect potionEffect = iterator.next();
						
			activePotionsId.add(Potion.getIdFromPotion(potionEffect.getPotion()));
		}
		
		for(int potionId : activePotionsId)
		{
			player.removeActivePotionEffect(Potion.getPotionById(potionId));
		}
	}
	
	public static void removeAllBadEffects(EntityPlayer player)
	{
		Iterator<PotionEffect> iterator = player.getActivePotionEffects().iterator();
		List<Integer> activePotionsId = new ArrayList<Integer>();
		
		while(iterator.hasNext())
		{
			PotionEffect potionEffect = (PotionEffect)iterator.next();
			
			Potion potion = potionEffect.getPotion();
			
			if(potion.isBadEffect()) activePotionsId.add(Potion.getIdFromPotion(potion));
		}
		
		for(int potionId : activePotionsId)
		{
			player.removeActivePotionEffect(Potion.getPotionById(potionId));
		}
	}
	
	public static ErthiliaLivingEntity getClosestEntityToPlayer(EntityPlayer player)
	{
        List<ErthiliaLivingEntity> entities = player.world.getEntitiesWithinAABB(ErthiliaLivingEntity.class, new AxisAlignedBB(player.posX-30, player.posY, player.posZ-30, player.posX+30, 255, player.posZ+30));
        if(entities.size() == 0)
        {
        	return null;
        }
        entities.sort(Comparator.comparingDouble(x -> player.getDistanceSq(x)));
        
        return entities.get(0);
	}
	
	public static boolean inventoryHavePlace(EntityPlayer player, List<ItemStack> itemstacks)
	{
		int itemStackCount = itemstacks.size();
		for(int i = 0; i < player.inventoryContainer.inventoryItemStacks.size(); i++)
		{
			ItemStack is = (ItemStack) player.inventoryContainer.inventoryItemStacks.get(i);
			
			if(is == ItemStack.EMPTY) 
			{
				itemStackCount--;
			}
			
			if(itemStackCount == 0)
			{
				return true;
			}
		}
		return false;
	}
	
	public static boolean isBlockingWithSword(EntityPlayer player)
	{
		return player.isHandActive() && player.getItemInUseCount() > 0 && player.getHeldItemMainhand().getItem() instanceof ItemSword;
	}
	
	public static void sendMessage(ICommandSender player, String message)
	{
		if(player == null)
		{
			System.out.println("Attempted to send a message to null player");
			return;
		}
		
		player.sendMessage(new TextComponentString(message));
	}
	
	public static boolean isOp(EntityPlayer player)
	{
		
		if(player == null) return false;
		
		return FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().canSendCommands(player.getGameProfile());
	}
	
}
