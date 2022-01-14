package fr.karmaowner.erthilia.handler;

import java.util.List;
import java.util.stream.Collectors;

import fr.karmaowner.erthilia.entity.monster.EntityDragon;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

public class DragonHandler {

	
	@SubscribeEvent //tester en jeu
	public void onDragonKillPlayer(LivingDeathEvent event)
	{
		if(event.getSource().getTrueSource() instanceof EntityDragon && event.getEntity() instanceof EntityPlayer)
		{
			EntityDragon dragon = (EntityDragon)event.getSource().getTrueSource();
			EntityPlayer victim = (EntityPlayer) event.getEntity();
			
			if(dragon.damagedBy.containsKey(victim.getPersistentID()))
			{
				dragon.damagedBy.remove(victim.getPersistentID());
			}
		}
	}
	
	@SubscribeEvent
	public void onPlayerDisconnect(PlayerEvent.PlayerLoggedOutEvent event)
	{
		EntityPlayer player = event.player;
		List<Entity> list = event.player.world.loadedEntityList;
		List<EntityDragon> entities = list.stream().filter(x -> x instanceof EntityDragon).map(sc -> (EntityDragon)sc).collect(Collectors.toList());
		for(EntityDragon dragon : entities)
		{
			if(dragon.damagedBy.containsKey(player.getPersistentID()))
			{
				dragon.damagedBy.remove(player.getPersistentID());
			}
		}
	}
	
}
