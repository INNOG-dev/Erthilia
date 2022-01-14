package fr.karmaowner.erthilia.renderer.entity;

import org.lwjgl.opengl.GL11;

import fr.karmaowner.erthilia.Main;
import fr.karmaowner.erthilia.animations.AtronachAnimation;
import fr.karmaowner.erthilia.entity.monster.EntityFireAtronach;
import fr.karmaowner.erthilia.entity.monster.EntityMana;
import fr.karmaowner.erthilia.entity.power.Power;
import fr.karmaowner.erthilia.model.ModelFireAtronach;
import fr.karmaowner.erthilia.utils.MathsUtils;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class RenderFireAtronach extends RenderLiving<EntityFireAtronach> {
		
	private AtronachAnimation animator;

	private float renderRotationYaw;
	
	public RenderFireAtronach(final RenderManager renderManagerIn) {
		super(renderManagerIn,new ModelFireAtronach(), 1.5f);
		
		animator = new AtronachAnimation(this.mainModel);
	}
	
	 
    protected void applyRotations(EntityFireAtronach entityLiving, float p_77043_2_, float rotationYaw, float partialTicks)
    {
    	renderRotationYaw = this.interpolateRotation(renderRotationYaw, entityLiving.rotationYaw, partialTicks);
    	float renderRotationPitch = this.interpolateRotation(entityLiving.prevRotationPitch, entityLiving.rotationPitch, partialTicks);

    	
    	GL11.glRotatef(-(renderRotationYaw), 0.0F, 1.0F, 0.0F);

        GL11.glRotatef(renderRotationPitch, 1.0F, 0.0F, 0.0F);
        
        
        if(entityLiving.getAttackTarget() == null)
        {
        	//entityLiving.previousRenderYaw = entityLiving.renderYaw;
        	//entityLiving.renderYaw = (entityLiving.rotationYaw - entityLiving.prevRotationYaw);
	
	        
	        /*if(entityLiving.renderYaw > 0)
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
	        
	        if(entityLiving.renderYaw < -15) entityLiving.renderYaw = -15;*/
	        
	        
	        
	        //GL11.glRotatef(entityLiving.renderYaw, 0.0F, 0.0F, -1.0F);
        }

        
        GL11.glTranslatef(0.0F, 0.0F, 1.5F);

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
        
		if(entityLiving instanceof EntityMana)
		{
			EntityMana mana = (EntityMana) entityLiving;	
			
			if(mana.entityMagicController.getActivePowerWithAnimation().size() > 0)
			{
				for(Power power : mana.entityMagicController.getActivePowerWithAnimation())
				{
					float elapsedTime = ((float)(System.currentTimeMillis() - power.startedTimer) / 1000f);
					animator.playAnimation(AtronachAnimation.Animation.values()[power.getAnimationId()], elapsedTime, power.powerDurationInSeconds());
				}
			}
			else
			{
				ModelFireAtronach model = (ModelFireAtronach) mainModel;
				model.head.rotateAngleX = MathsUtils.Lerp(model.head.rotateAngleX,(float) Math.toRadians(0), 0.1f);;
				animator.animationIsPlaying = false;
			}
		}
    }
	

	@Override
	protected ResourceLocation getEntityTexture(EntityFireAtronach entity) 
	{
		return new ResourceLocation(Main.MODID + ":textures/entity/atronach/fire_atronach.png");
	}
	
	@Override
	protected void renderModel(EntityFireAtronach entitylivingbaseIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor)
    {
        this.bindEntityTexture(entitylivingbaseIn);
        GL11.glPushMatrix();
        GL11.glTranslatef(0, 0.5f, -1.5f);
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
        GL11.glPopMatrix();
    }

	
}
