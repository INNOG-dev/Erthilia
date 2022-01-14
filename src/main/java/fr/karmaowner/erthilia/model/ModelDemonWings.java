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

public class ModelDemonWings extends ModelBaseBody {

	
	private IModelCustom model;
	

	public ModelDemonWings() {
		model = (IModelCustom)AdvancedModelLoader.loadModel(new ResourceLocation("erthilia","models/cosmetics/demon_wings.obj"));
	}


	 public void render(Entity entity, float limbSwing, float limbSwingAmount, float rotFloat, float rotYaw, float rotPitch, float partTicks) {
		
		 if(Minecraft.getMinecraft().player.isInvisible()) return;
		
		 
		 float baseRot = 210;
		 float animation = (float) Math.abs(Math.sin((TicksHandler.elapsedTicks+partTicks) / 20));
				
			
		 float aile1Rotation = baseRot - animation* -40;

		 baseRot = -10;
		 float aile2Rotation = -baseRot - animation * -40;
		 
		 
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
		 GL11.glTranslatef(0.01f, 0.4f, 0.15f);
		 GL11.glRotatef(100, 0, 1, 0);
		 GL11.glRotatef(90, 1, 0, 0);
		 GL11.glRotatef(aile1Rotation, 0, 0, 1);
		 render();

		 GL11.glPopMatrix();
		 
		 GL11.glPushMatrix();
		 GL11.glTranslatef(-0.01f, 0.4f, 0.15f);
		 GL11.glRotatef(100, 0, 1, 0);
		 GL11.glRotatef(90, 1, 0, 0);
		 GL11.glRotatef(-aile2Rotation, 0, 0, 1);
		 render();
		 GL11.glPopMatrix();
		 
		 
		 GL11.glPopMatrix();

	}
	 
	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float rotFloat, float rotYaw, float rotPitch, float partTicks, Entity entity)
	{
		super.setRotationAngles(limbSwing, limbSwingAmount, rotFloat, rotYaw, rotPitch, partTicks, entity);
	}
	
	
	
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
	
	@Override
	public void render()
	{
		Minecraft.getMinecraft().getTextureManager().bindTexture(getTexture());
		model.renderAll();
	}


	@Override
	public ResourceLocation getTexture() {
		return new ResourceLocation(Main.MODID,"models/cosmetics/demon_wings.png");
	}
	
}
