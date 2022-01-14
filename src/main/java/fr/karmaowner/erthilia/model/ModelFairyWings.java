package fr.karmaowner.erthilia.model;

import org.lwjgl.opengl.GL11;

import fr.karmaowner.client.model.AdvancedModelLoader;
import fr.karmaowner.client.model.IModelCustom;
import fr.karmaowner.erthilia.Main;
import fr.karmaowner.erthilia.handler.TicksHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class ModelFairyWings extends ModelBaseBody {
	

	public IModelCustom model;

	
	public ModelFairyWings() 
	{
		model = (IModelCustom)AdvancedModelLoader.loadModel(new ResourceLocation("erthilia","models/cosmetics/fairy_wings.obj"));
	}
	

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
 
	public void render(Entity entity, float limbSwing, float limbSwingAmount, float rotFloat, float rotYaw, float rotPitch, float partTicks) {
		 if(Minecraft.getMinecraft().player.isInvisible()) return;
		
		 float baseRot = 20;
		 float animation = (float) Math.abs(Math.sin((TicksHandler.elapsedTicks+partTicks) / 20));
				
			
		 float aile1Rotation = baseRot - animation* -40;

		 baseRot = 70;
		 float aile2Rotation = baseRot + animation * -40;
		 
		 GL11.glPushMatrix();
		 
		 GL11.glColor4f(1f, 1f, 1f, 1f);
		 super.render(entity, limbSwing, limbSwingAmount, rotFloat, rotYaw, rotPitch, partTicks);
		 this.setRotationAngles(limbSwing, limbSwingAmount, rotFloat, rotYaw, rotPitch, partTicks, entity);
		 
		 if(entity.isSneaking())
		 {
			 GL11.glRotatef(20, 1, 0, 0);
			 GL11.glTranslatef(0, 0.25f, -0.2f);
		 }
		 
		 GL11.glPushMatrix();
		 GL11.glTranslatef(0.05f, 0.5f, 0.2f);

		 
		 GL11.glRotatef(100, 1, 0, 0);
		 GL11.glRotatef(aile1Rotation, 0, 0, 1);
		 render();
		 GL11.glPopMatrix();
		 
		 
		 GL11.glPushMatrix();
		 GL11.glTranslatef(-0.05f, 0.5f, 0.2f);

		 GL11.glRotatef(100, 1, 0, 0);
		 GL11.glRotatef(aile2Rotation, 0, 0, 1);
		 GL11.glPushMatrix();
		 GL11.glRotatef(90, 0, 0, 1);
		 render();
		 GL11.glPopMatrix();
		 GL11.glPopMatrix();
		 

		 GL11.glPopMatrix();
	}
	
	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float rotFloat, float rotYaw, float rotPitch, float partTicks, Entity entity)
	{
	    super.setRotationAngles(limbSwing, limbSwingAmount, rotFloat, rotYaw, rotPitch, partTicks, entity);
	}
	

	@Override
	public void render()
	{
		Minecraft.getMinecraft().getTextureManager().bindTexture(getTexture());
		model.renderAll();
	}


	@Override
	public ResourceLocation getTexture() {
		return new ResourceLocation(Main.MODID,"models/cosmetics/fairy_wings.png");
	}
	

}
