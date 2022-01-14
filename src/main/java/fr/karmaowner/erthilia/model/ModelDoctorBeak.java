package fr.karmaowner.erthilia.model;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class ModelDoctorBeak extends ModelBaseHead {

	private final ModelRenderer bone;
	private final ModelRenderer bone2;
	private final ModelRenderer bone8;
	private final ModelRenderer bone7;
	private final ModelRenderer bone3;
	private final ModelRenderer bone4;
	private final ModelRenderer cube_r1;
	private final ModelRenderer bone5;
	private final ModelRenderer cube_r2;
	private final ModelRenderer bone6;
	private final ModelRenderer cube_r3;

	
	private final ResourceLocation texture = new ResourceLocation("erthilia","textures/cosmetics/doctor_beak.png");

	public ModelDoctorBeak() {
		textureWidth = 64;
		textureHeight = 64;

		bone = new ModelRenderer(this);
		bone.setRotationPoint(-0.5F, -7.5F, -0.5F);
		bone.cubeList.add(new ModelBox(bone, 0, 0, -5.0F, -1.0F, -5.0F, 11, 1, 11, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 12, -4.0F, -5.0F, -4.0F, 9, 4, 9, 0.0F, false));

		bone2 = new ModelRenderer(this);
		bone2.setRotationPoint(0.0F, -7.5F, 0.0F);
		

		bone8 = new ModelRenderer(this);
		bone8.setRotationPoint(-0.75F, 0.0F, 0.5F);
		bone2.addChild(bone8);
		setRotationAngle(bone8, -0.0873F, 0.0F, -0.1309F);
		bone8.cubeList.add(new ModelBox(bone8, 0, 0, -3.25F, 1.0F, -5.0F, 3, 3, 1, 0.0F, false));

		bone7 = new ModelRenderer(this);
		bone7.setRotationPoint(0.75F, 0.0F, 0.5F);
		bone2.addChild(bone7);
		setRotationAngle(bone7, -0.0873F, 0.0F, 0.1309F);
		bone7.cubeList.add(new ModelBox(bone7, 0, 0, 0.25F, 1.0F, -5.0F, 3, 3, 1, 0.0F, false));

		bone3 = new ModelRenderer(this);
		bone3.setRotationPoint(3.0F, 4.0F, 0.5F);
		bone2.addChild(bone3);
		setRotationAngle(bone3, 0.1745F, -0.1745F, 0.7854F);
		bone3.cubeList.add(new ModelBox(bone3, 14, 25, -4.25F, 0.0F, -7.0F, 4, 4, 3, 0.0F, false));

		bone4 = new ModelRenderer(this);
		bone4.setRotationPoint(3.0F, -3.5F, 0.5F);
		setRotationAngle(bone4, 0.2618F, -0.3927F, 0.7854F);
		

		cube_r1 = new ModelRenderer(this);
		cube_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone4.addChild(cube_r1);
		setRotationAngle(cube_r1, 0.0873F, 0.0F, 0.0F);
		cube_r1.cubeList.add(new ModelBox(cube_r1, 0, 25, -5.0F, -0.5F, -10.0F, 3, 3, 4, 0.0F, false));

		bone5 = new ModelRenderer(this);
		bone5.setRotationPoint(3.5F, -4.25F, -3.5F);
		setRotationAngle(bone5, 0.5672F, -0.48F, 0.5672F);
		

		cube_r2 = new ModelRenderer(this);
		cube_r2.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone5.addChild(cube_r2);
		setRotationAngle(cube_r2, 0.0873F, 0.0F, 0.0F);
		cube_r2.cubeList.add(new ModelBox(cube_r2, 27, 12, -4.0F, 0.5F, -10.0F, 2, 2, 4, 0.0F, false));

		bone6 = new ModelRenderer(this);
		bone6.setRotationPoint(3.3F, -4.5F, -7.0F);
		setRotationAngle(bone6, 0.829F, -0.8727F, 0.5672F);
		

		cube_r3 = new ModelRenderer(this);
		cube_r3.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone6.addChild(cube_r3);
		setRotationAngle(cube_r3, 0.0873F, 0.0F, 0.0F);
		cube_r3.cubeList.add(new ModelBox(cube_r3, 0, 4, -3.0F, 1.5F, -10.0F, 1, 1, 3, 0.0F, false));
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
		
		 GL11.glTranslatef(0, 0.10F, 0F);	
		 GL11.glRotatef(entity.rotationPitch, 1, 0, 0);		 

		 if(entity.isSneaking())
		 {
			 GL11.glRotatef(5, 1, 0, 0);		
		 }
		 
		 GL11.glPushMatrix();

		 GL11.glScalef(6f, 6f, 6f);
		 
		 
		 render();
		 
		 
		 GL11.glPopMatrix();
		 GL11.glPopMatrix();
	 }
	
	@Override
	public void render() {
		Minecraft.getMinecraft().getTextureManager().bindTexture(getTexture());

		bone.render(0.01F);
		bone2.render(0.01F);
		bone4.render(0.01F);
		bone5.render(0.01F);
		bone6.render(0.01F);
	}

	@Override
	public ResourceLocation getTexture() {
		return texture;
	}

}
