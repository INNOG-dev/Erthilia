package fr.karmaowner.erthilia.renderer.entity;

import org.lwjgl.opengl.GL11;

import fr.karmaowner.erthilia.entity.EntitySpear;
import fr.karmaowner.erthilia.model.ModelSpear;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderSpear extends Render<EntitySpear>
{
	
	public RenderSpear(final RenderManager renderManager) 
	{
		super(renderManager);
	}

	private ModelSpear model = new ModelSpear();
   
	@Override
    public void doRender(EntitySpear entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
    	float scale = 0.8f;
    	bindEntityTexture(entity);
    	
    	
        GL11.glPushMatrix();

        GL11.glTranslated(x, y, z);

        GL11.glRotatef(entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw) * partialTicks - 90.0F, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef((entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * partialTicks) + 90f, 0.0F, 0.0F, 1.0F);
        GL11.glScalef(scale, scale, scale);

        model.render();
    	GL11.glPopMatrix();	
    	super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

	@Override
    protected ResourceLocation getEntityTexture(EntitySpear entity)
    {
        return model.texture;
    }

}