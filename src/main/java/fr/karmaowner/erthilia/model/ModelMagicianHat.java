package fr.karmaowner.erthilia.model;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class ModelMagicianHat extends ModelBaseHead {

	private final ModelRenderer bone;
	private final ModelRenderer bone2;
	private final ModelRenderer bone3;
	
	private final ResourceLocation texture = new ResourceLocation("erthilia","textures/cosmetics/magician_hat.png");

	public ModelMagicianHat() {
		textureWidth = 64;
		textureHeight = 64;

		bone = new ModelRenderer(this);
		bone.setRotationPoint(1.0F, -4.0F, -4.5F);
		bone.cubeList.add(new ModelBox(bone, 0, 0, -0.75F, -2.75F, 0.0F, 3, 3, 1, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 4, 0.25F, 0.25F, 0.25F, 3, 5, 0, 0.0F, false));

		bone2 = new ModelRenderer(this);
		bone2.setRotationPoint(0.0F, -1.0F, 0.5F);
		bone.addChild(bone2);
		bone2.cubeList.add(new ModelBox(bone2, 0, 0, -6.0F, -3.0F, -1.0F, 10, 1, 10, 0.0F, false));

		bone3 = new ModelRenderer(this);
		bone3.setRotationPoint(0.0F, -1.0F, 0.0F);
		bone2.addChild(bone3);
		bone3.cubeList.add(new ModelBox(bone3, 0, 11, -5.0F, -11.0F, 0.0F, 8, 9, 8, 0.0F, false));
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
		
		 GL11.glRotatef(entity.rotationPitch, 1, 0, 0);		 

		 if(entity.isSneaking())
		 {
			 GL11.glRotatef(5, 1, 0, 0);		
		 }
		 
		 GL11.glPushMatrix();

		 GL11.glTranslatef(0, 0.15F, 0F);	
		 GL11.glScalef(7f, 7f, 7f);
		 
		 
		 render();
		 
		 
		 GL11.glPopMatrix();
		 GL11.glPopMatrix();
	 }
	
	@Override
	public void render() {
		Minecraft.getMinecraft().getTextureManager().bindTexture(getTexture());

		bone.render(0.01F);
	}

	@Override
	public ResourceLocation getTexture() 
	{
		return texture;
	}

}
