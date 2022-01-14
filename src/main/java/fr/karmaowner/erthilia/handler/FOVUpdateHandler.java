package fr.karmaowner.erthilia.handler;

import fr.karmaowner.erthilia.items.ErthiliaBow;
import fr.karmaowner.erthilia.items.ErthiliaSpear;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.FOVUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class FOVUpdateHandler {

	   @SubscribeEvent
	   public void FOVUpdate(FOVUpdateEvent event)
	   {
		   float f = 1.0f;
		   EntityPlayer player = event.getEntity();

	       if (player.isHandActive() && player.getActiveItemStack().getItem() instanceof ErthiliaBow || player.getActiveItemStack().getItem() instanceof ErthiliaSpear)
	       {
	            int i = player.getItemInUseMaxCount();
	            float f1 = (float)i / 20.0F;

	            if (f1 > 1.0F)
	            {
	                f1 = 1.0F;
	            }
	            else
	            {
	                f1 = f1 * 1.2f;
	            }

	            f *= 1.0F - f1 * 0.250F;
	            
	            event.setNewfov(f);
	       }
	   }
	
}
