package fr.karmaowner.erthilia.handler;

import fr.karmaowner.erthilia.Main;
import fr.karmaowner.erthilia.potion.ReduceFallDamagePotion;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EntityDamageHandler {

	   @SubscribeEvent
	   public void onFallDamage(LivingFallEvent event)
	   {
		   if(event.getEntity() instanceof EntityPlayer)
		   {
			  if(event.getEntityLiving().isPotionActive(Potion.getPotionFromResourceLocation(Main.MODID + ":anti_fall_damage")))
			  {
				  PotionEffect effect = event.getEntityLiving().getActivePotionEffect(ReduceFallDamagePotion.FALL_DAMAGE_REDUCE);
				  int level = effect.getAmplifier();
  
				  
				  event.setDistance(event.getDistance() / ((level+1)*2));
			  }
		   }
	   }
	
}
