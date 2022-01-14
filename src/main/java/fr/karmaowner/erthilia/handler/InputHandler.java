package fr.karmaowner.erthilia.handler;

import fr.karmaowner.erthilia.ClientProxy;
import fr.karmaowner.erthilia.GameInitializer;
import fr.karmaowner.erthilia.gui.ParticlesGui;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;

public class InputHandler {
	
	@SubscribeEvent
	public void onKeyPressed(KeyInputEvent event)
	{
		
		if(Minecraft.getMinecraft().gameSettings.keyBindSwapHands.isPressed())
		{
			KeyBinding.setKeyBindState(Minecraft.getMinecraft().gameSettings.keyBindSwapHands.getKeyCode(), false);
		}
		
		if(GameInitializer.keyBindings[0].isPressed())
		{
			ClientProxy.getToggleSprintHandler().setToggleSprint(!ClientProxy.getToggleSprintHandler().toggleSprint);
		}
		else if(GameInitializer.keyBindings[1].isPressed())
		{
			Minecraft.getMinecraft().displayGuiScreen(new ParticlesGui());
		}
	}
	
	
}
