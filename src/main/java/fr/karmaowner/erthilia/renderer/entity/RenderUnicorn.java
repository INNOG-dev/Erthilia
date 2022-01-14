package fr.karmaowner.erthilia.renderer.entity;

import org.lwjgl.opengl.GL11;

import fr.karmaowner.erthilia.Main;
import fr.karmaowner.erthilia.entity.monster.EntityUnicorn;
import fr.karmaowner.erthilia.model.ModelUnicorn;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class RenderUnicorn extends RenderLiving<EntityUnicorn> {
	

	public RenderUnicorn(RenderManager manager) {
		super(manager,new ModelUnicorn(), 0.2f);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityUnicorn entity) 
	{
		return new ResourceLocation(Main.MODID, "textures/entity/unicorn/unicorn.png");
	}
	
	protected void applyRotations(EntityUnicorn entityLiving, float p_77043_2_, float rotationYaw, float partialTicks)
    {
	 	float renderRotationYaw = this.interpolateRotation(entityLiving.prevRotationYaw, entityLiving.rotationYaw, partialTicks);
    	float renderRotationPitch = this.interpolateRotation(entityLiving.prevRotationPitch, entityLiving.rotationPitch, partialTicks);


        GL11.glRotatef(-(renderRotationYaw), 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(renderRotationPitch, 1.0F, 0.0F, 0.0F);
        
        
        if(entityLiving.getAttackTarget() == null)
        {
        	entityLiving.previousRenderYaw = entityLiving.renderYaw;
        	entityLiving.renderYaw = (entityLiving.rotationYaw - entityLiving.prevRotationYaw);
	
	        
	        if(entityLiving.renderYaw > 0)
	        {
	        	entityLiving.renderYaw = 10;
	        }
	        else if(entityLiving.renderYaw < 0)
	        {
	        	entityLiving.renderYaw = -10;
	        }
	        else
	        {
	        	entityLiving.renderYaw = entityLiving.previousRenderYaw;

	        }
	        
	        entityLiving.renderYaw  = this.interpolateRotation(entityLiving.previousRenderYaw, entityLiving.renderYaw, partialTicks);
	        
	        if(entityLiving.renderYaw > 15) entityLiving.renderYaw = 15;
	        
	        if(entityLiving.renderYaw < -15) entityLiving.renderYaw = -15;
	        
	        
	        
	        GL11.glRotatef(entityLiving.renderYaw, 0.0F, 0.0F, -1.0F);
        }

        
        if (entityLiving.deathTime > 0)
        {
            float f5 = ((float)entityLiving.deathTime + partialTicks - 1.0F) / 20.0F * 1.6F;
            f5 = MathHelper.sqrt(f5);

            if (f5 > 1.0F)
            {
                f5 = 1.0F;
            }

            GL11.glRotatef(f5 * this.getDeathMaxRotation(entityLiving), 0.0F, 0.0F, 1.0F);
        }
    }
	
	@Override
	protected void renderModel(EntityUnicorn entitylivingbaseIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor)
    {
        this.bindEntityTexture(entitylivingbaseIn);
        this.mainModel.render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);

        if (entitylivingbaseIn.hurtTime > 0)
        {
            GL11.glDepthFunc(GL11.GL_EQUAL);
            GL11.glDisable(GL11.GL_TEXTURE_2D);
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            GL11.glColor4f(1.0F, 0.0F, 0.0F, 0.5F);
            this.mainModel.render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
            GL11.glEnable(GL11.GL_TEXTURE_2D);
            GL11.glDisable(GL11.GL_BLEND);
            GL11.glDepthFunc(GL11.GL_LEQUAL);
            
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        }
    }
	
}
