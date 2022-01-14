package fr.karmaowner.erthilia.renderer.entity;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderPlayer;

public class RenderPlayerCustom extends RenderPlayer 
{

	public RenderPlayerCustom(RenderManager renderManager, boolean useSmallArms) {
		super(renderManager, useSmallArms);
	}
	
    @Override
    public void doRender(AbstractClientPlayer entity, double x, double y, double z, float entityYaw, float partialTicks) {

    }

}
