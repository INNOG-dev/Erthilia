package fr.karmaowner.erthilia.model;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class ModelFlamingoHat extends ModelBaseHead {

	private final ModelRenderer bone;
	private final ModelRenderer bone2;
	private final ModelRenderer bone3;
	private final ModelRenderer bone4;
	private final ModelRenderer bone7;
	private final ModelRenderer bone8;
	private final ModelRenderer bone5;
	private final ModelRenderer bone6;
	private final ModelRenderer bone9;
	private final ModelRenderer bone10;
	private final ModelRenderer bone11;
	private final ModelRenderer bone12;
	private final ModelRenderer bone13;
	
	private final ResourceLocation texture = new ResourceLocation("erthilia","textures/cosmetics/flamingo_hat.png");

	public ModelFlamingoHat() {
		textureWidth = 32;
		textureHeight = 32;

		bone = new ModelRenderer(this);
		bone.setRotationPoint(-3.0F, -7.0F, 0.0F);
		

		bone2 = new ModelRenderer(this);
		bone2.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone.addChild(bone2);
		bone2.cubeList.add(new ModelBox(bone2, 0, 0, 6.0F, -3.0F, -5.0F, 2, 3, 10, 0.0F, false));
		bone2.cubeList.add(new ModelBox(bone2, 0, 0, -2.0F, -3.0F, -5.0F, 2, 3, 10, 0.0F, true));
		bone2.cubeList.add(new ModelBox(bone2, 10, 13, 7.5F, -3.5F, -2.0F, 1, 2, 3, 0.0F, false));
		bone2.cubeList.add(new ModelBox(bone2, 10, 13, -2.5F, -3.5F, -2.0F, 1, 2, 3, 0.0F, true));

		bone3 = new ModelRenderer(this);
		bone3.setRotationPoint(1.0F, 0.0F, -3.0F);
		bone.addChild(bone3);
		setRotationAngle(bone3, 0.0F, -1.5708F, 0.0F);
		bone3.cubeList.add(new ModelBox(bone3, 0, 13, -2.0F, -3.0F, -5.0F, 2, 3, 6, 0.0F, false));

		bone4 = new ModelRenderer(this);
		bone4.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone3.addChild(bone4);
		bone4.cubeList.add(new ModelBox(bone4, 0, 13, 6.0F, -3.0F, -5.0F, 2, 3, 6, 0.0F, false));

		bone7 = new ModelRenderer(this);
		bone7.setRotationPoint(-9.0F, -5.0F, 4.5F);
		setRotationAngle(bone7, -0.9163F, 0.0F, 0.0F);
		bone7.cubeList.add(new ModelBox(bone7, 0, 6, 6.5F, -2.0F, 0.0F, 2, 3, 1, 0.0F, false));

		bone8 = new ModelRenderer(this);
		bone8.setRotationPoint(0.5F, 0.0F, -1.0F);
		bone7.addChild(bone8);
		bone8.cubeList.add(new ModelBox(bone8, 16, 16, 6.5F, -2.0F, -1.0F, 1, 1, 2, 0.0F, false));

		bone5 = new ModelRenderer(this);
		bone5.setRotationPoint(-6.0F, -5.0F, 4.5F);
		setRotationAngle(bone5, -0.9163F, 0.0F, 0.0F);
		bone5.cubeList.add(new ModelBox(bone5, 14, 5, 6.5F, -2.0F, 0.0F, 2, 3, 1, 0.0F, false));

		bone6 = new ModelRenderer(this);
		bone6.setRotationPoint(0.5F, 0.0F, -1.0F);
		bone5.addChild(bone6);
		bone6.cubeList.add(new ModelBox(bone6, 18, 7, 6.5F, -2.0F, -1.0F, 1, 1, 2, 0.0F, false));

		bone9 = new ModelRenderer(this);
		bone9.setRotationPoint(0.0F, -8.0F, -6.0F);
		bone9.cubeList.add(new ModelBox(bone9, 14, 0, -1.0F, -3.0F, 0.0F, 2, 3, 2, 0.0F, false));

		bone10 = new ModelRenderer(this);
		bone10.setRotationPoint(0.5F, -2.0F, 0.25F);
		bone9.addChild(bone10);
		setRotationAngle(bone10, -0.2182F, 0.0F, 0.0F);
		bone10.cubeList.add(new ModelBox(bone10, 6, 6, -1.0F, -3.0F, 0.0F, 1, 3, 1, 0.0F, false));

		bone11 = new ModelRenderer(this);
		bone11.setRotationPoint(0.025F, 0.0F, 2.0F);
		bone10.addChild(bone11);
		setRotationAngle(bone11, 0.6545F, 0.0F, 0.0F);
		bone11.cubeList.add(new ModelBox(bone11, 16, 19, -1.0F, -5.0F, 0.0F, 1, 2, 1, 0.0F, false));

		bone12 = new ModelRenderer(this);
		bone12.setRotationPoint(-0.5F, -4.0F, 2.5F);
		bone11.addChild(bone12);
		setRotationAngle(bone12, 1.4399F, 0.0F, 0.0F);
		bone12.cubeList.add(new ModelBox(bone12, 0, 0, -1.0F, -5.0F, 0.0F, 2, 4, 2, 0.0F, false));

		bone13 = new ModelRenderer(this);
		bone13.setRotationPoint(0.0F, 0.0F, 2.0F);
		bone12.addChild(bone13);
		setRotationAngle(bone13, 0.3491F, 0.0F, 0.0F);
		bone13.cubeList.add(new ModelBox(bone13, 0, 13, -0.5F, -6.75F, -1.0F, 1, 3, 2, 0.0F, false));
		bone13.cubeList.add(new ModelBox(bone13, 15, 13, -0.5F, -7.75F, -2.0F, 1, 1, 2, 0.0F, false));
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
		
		 GL11.glTranslatef(0, 0.10F, 0.01F);	
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
		bone7.render(0.01F);
		bone5.render(0.01F);
		bone9.render(0.01F);
	}

	@Override
	public ResourceLocation getTexture() 
	{
		return texture;
	}

}
