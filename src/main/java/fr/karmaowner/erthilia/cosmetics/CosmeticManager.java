package fr.karmaowner.erthilia.cosmetics;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import fr.karmaowner.erthilia.capability.playercapability.ErthiliaPlayerProvider;
import fr.karmaowner.erthilia.capability.playercapability.IPlayer;
import fr.karmaowner.erthilia.utils.PlayerUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;

public class CosmeticManager {

	private static List<CosmeticObject> registeredCosmetics = new ArrayList<CosmeticObject>();
		
	public final static int companionMaxUseTime = 60*10;//In seconds
	
	public ItemStack[] companionFood;
	
	public final static int companionUpdate = 10;//In seconds

	
	public CosmeticManager()
	{
    
	}

	public CosmeticObject registerCosmetic(String name, boolean unlockedDefault,byte type, int id)
	{
		CosmeticObject cosmeticObj = new CosmeticObject(name, unlockedDefault, type, id);
		registeredCosmetics.add(cosmeticObj);
		return cosmeticObj;
	}
	
	public CompanionObject registerCompanion(String name, boolean unlockedDefault,byte type, int id)
	{
		CompanionObject companionObj = new CompanionObject(name, unlockedDefault, type, id);
		registeredCosmetics.add(companionObj);
		return companionObj;
	}
	
	public CosmeticObject getCopy(int id)
	{
		Optional<CosmeticObject> optional = registeredCosmetics.stream().filter(x -> x.getId() == id).findFirst();
		CosmeticObject obj = null;
		
		if(optional.isPresent())
		{
			obj = optional.get();
		}
		else
		{
			return null;
		}
		
		return getCopy(obj);
	}
	
	public CosmeticObject getCopy(CosmeticObject obj)
	{
		CosmeticObject copy = null;
		
		if(obj instanceof CompanionObject)
		{
			copy = new CompanionObject(obj.getName(),!obj.getIsLocked(),obj.getType(), obj.getId());
		}
		else
		{
			copy = new CosmeticObject(obj.getName(),!obj.getIsLocked(),obj.getType(), obj.getId());
		}

		if(FMLCommonHandler.instance().getSide() == Side.CLIENT)
		{
			copy.setupRender(obj.getCosmeticRenderSetup(), obj.getModel());
		}
		return copy;
	}

	public void updateCompanion(EntityPlayer player)
	{
		IPlayer persistantData = (IPlayer) player.getCapability(ErthiliaPlayerProvider.PLAYER_CAP, null);
		
		if(persistantData == null) return;
				
		persistantData.getCosmeticsDatas().stream().filter(x -> x.isCompanion() && x.getIsEquipped()).forEach(action ->
		{
			CompanionObject obj = (CompanionObject)action;
						
			obj.addTimeUsed(CosmeticManager.companionUpdate);
			
			if(obj.getSelectedEffectIndex() != -1)
			{
				PotionEffect effect = getCompanionEffects().get(obj.getSelectedEffectIndex());
				
				int duration = 20 * (CosmeticManager.companionMaxUseTime - obj.getTimeUsed());
	
				player.addPotionEffect(new PotionEffect(effect.getPotion(),duration,effect.getAmplifier()));
			}
			
			if(obj.companionNeedFood())
			{
				CompanionObject.unequipCompanion(player, obj.getId());
				PlayerUtils.sendMessage(player, "§cPiouf votre compagnon retourne dans sa dimension");
				PlayerUtils.sendMessage(player, "§cEst-il épuisé ?");

				persistantData.updateRenderer();
			}
		});
	}
	
	public List<CosmeticObject> getCosmetics()
	{
		return registeredCosmetics;
	}
	
	public static List<PotionEffect> getCompanionEffects()
	{
		List<PotionEffect> potions = new ArrayList<PotionEffect>();
		
		potions.add(new PotionEffect(MobEffects.STRENGTH,20,1));
		potions.add(new PotionEffect(MobEffects.SPEED,20,1));
		potions.add(new PotionEffect(MobEffects.HASTE,20,1));
		potions.add(new PotionEffect(MobEffects.REGENERATION,20,1));

		return potions;
	}

	
}
