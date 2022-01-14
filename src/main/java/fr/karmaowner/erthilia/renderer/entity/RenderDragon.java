package fr.karmaowner.erthilia.renderer.entity;

import org.lwjgl.opengl.GL11;

import fr.karmaowner.erthilia.Main;
import fr.karmaowner.erthilia.animations.DragonAnimation;
import fr.karmaowner.erthilia.entity.monster.EntityDragon;
import fr.karmaowner.erthilia.entity.monster.EntityMana;
import fr.karmaowner.erthilia.entity.power.Power;
import fr.karmaowner.erthilia.model.ModelDragon;
import fr.karmaowner.erthilia.utils.MathsUtils;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class RenderDragon extends RenderLiving<EntityDragon> {
	
	private int renderType;
	
	private DragonAnimation animator;

	
	public RenderDragon(final RenderManager renderManagerIn, int type) {
		super(renderManagerIn,new ModelDragon(), 3);
		this.renderType = type;
		
		animator = new DragonAnimation(this.mainModel);
	}
	
	
    protected void applyRotations(EntityDragon entityLiving, float p_77043_2_, float rotationYaw, float partialTicks)
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
					animator.playAnimation(DragonAnimation.Animation.values()[power.getAnimationId()], elapsedTime, power.powerDurationInSeconds());
				}
			}
			else
			{
				ModelDragon model = (ModelDragon) mainModel;
				model.tete.rotateAngleX = MathsUtils.Lerp(model.tete.rotateAngleX,(float) Math.toRadians(0), 0.1f);;
				animator.animationIsPlaying = false;
				
			}
		}
    }
	

	@Override
	protected ResourceLocation getEntityTexture(EntityDragon entity) {
		
		if(renderType == 0)
		{
			return new ResourceLocation(Main.MODID + ":textures/entity/dragon/dragon.png");
		} 
		else
		{
			return new ResourceLocation(Main.MODID + ":textures/entity/dragon/dragon_black.png");
		}
	}
	
	@Override
	protected void renderModel(EntityDragon entitylivingbaseIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor)
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
