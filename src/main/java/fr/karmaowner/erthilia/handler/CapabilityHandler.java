package fr.karmaowner.erthilia.handler;

import fr.karmaowner.erthilia.Main;
import fr.karmaowner.erthilia.capability.playercapability.ErthiliaPlayerProvider;
import fr.karmaowner.erthilia.capability.playercapability.IPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CapabilityHandler 
{
	public static final ResourceLocation CAP_ID = new ResourceLocation(Main.MODID, "player_data");
	
	 @SubscribeEvent
	 public void attachCapability(AttachCapabilitiesEvent<Entity> event)
	 {
		 if (!(event.getObject() instanceof EntityPlayer)) return;


		 event.addCapability(CAP_ID, new ErthiliaPlayerProvider((EntityPlayer)event.getObject()));
	 }
	 
	 @SubscribeEvent
	 public void onPlayerClone(PlayerEvent.Clone event)
	 {
		  EntityPlayer player = event.getEntityPlayer();
		  
		  if(event.getOriginal().hasCapability(ErthiliaPlayerProvider.PLAYER_CAP, null))
		  {
			  IPlayer newPlayerdata = player.getCapability(ErthiliaPlayerProvider.PLAYER_CAP, null);
			  IPlayer oldPlayerdata = event.getOriginal().getCapability(ErthiliaPlayerProvider.PLAYER_CAP, null);
			  newPlayerdata.permute(oldPlayerdata);
		  }
	 }
	 
	 

	 
}
