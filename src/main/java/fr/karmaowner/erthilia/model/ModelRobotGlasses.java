package fr.karmaowner.erthilia.model;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class ModelRobotGlasses extends ModelBaseHead {

	private final ModelRenderer head;
	
	private final ResourceLocation texture = new ResourceLocation("erthilia","textures/cosmetics/robot_glasses.png");

	public ModelRobotGlasses() {
		textureWidth = 32;
		textureHeight = 32;
 
		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, 0.0F, 0.0F);
		head.cubeList.add(new ModelBox(head, 0, 0, -4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F, true));
		head.cubeList.add(new ModelBox(head, 0, 26, -5.0F, -7.0F, -5.0F, 10, 4, 2, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 0, 18, 3.0F, -7.0F, -3.0F, 2, 4, 4, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 0, 18, -5.0F, -7.0F, -3.0F, 2, 4, 4, 0.0F, true));
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
	
	 @Override
	 public void render(Entity entity, float limbSwing, float limbSwingAmount, float rotFloat, float rotYaw, float rotPitch, float partTicks) {		 
		 if(entity.isInvisible()) return;
		 
		 GL11.glPushMatrix();

		 GL11.glColor4f(1f, 1f, 1f, 1f);

		super.render(entity, limbSwing, limbSwingAmount, rotFloat, rotYaw, rotPitch, partTicks);
		this.setRotationAngles(limbSwing, limbSwingAmount, rotFloat, rotYaw, rotPitch, partTicks, entity);
		GL11.glRotatef(entity.rotationPitch, 1F, 0, 0);
		GL11.glPushMatrix();
		GL11.glTranslatef(0, 0.1f, -0.1f);
		GL11.glScalef(5f, 5f, 5f);

		 render();
		 GL11.glPopMatrix();
		 GL11.glPopMatrix();
	 }
	
	@Override
	public void render() 
	{
		Minecraft.getMinecraft().getTextureManager().bindTexture(getTexture());

		head.render(0.01F);
	}

	@Override
	public ResourceLocation getTexture() {
		return texture;
	}

}
