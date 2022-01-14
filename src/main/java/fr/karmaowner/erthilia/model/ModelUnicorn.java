package fr.karmaowner.erthilia.model;

import org.lwjgl.opengl.GL11;

import fr.karmaowner.erthilia.utils.MathsUtils;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelUnicorn extends ModelBase {

	private final ModelRenderer Body;
	private final ModelRenderer bone;
	private final ModelRenderer bone2;
	private final ModelRenderer TailA;
	private final ModelRenderer TailB;
	private final ModelRenderer TailC;
	private final ModelRenderer Leg1A;
	private final ModelRenderer Leg1B;
	private final ModelRenderer Leg1C;
	private final ModelRenderer Leg2A;
	private final ModelRenderer Leg2B;
	private final ModelRenderer Leg2C;
	private final ModelRenderer Leg3A;
	private final ModelRenderer Leg3B;
	private final ModelRenderer Leg3C;
	private final ModelRenderer Leg4A;
	private final ModelRenderer Leg4B;
	private final ModelRenderer Leg4C;
	private final ModelRenderer Head;
	private final ModelRenderer UMouth;
	private final ModelRenderer LMouth;
	private final ModelRenderer Ear1;
	private final ModelRenderer Ear2;
	private final ModelRenderer Neck;
	private final ModelRenderer Mane;

	public ModelUnicorn() {
		textureWidth = 128;
		textureHeight = 128;

		Body = new ModelRenderer(this);
		Body.setRotationPoint(0.0F, 11.0F, 9.0F);
		Body.cubeList.add(new ModelBox(Body, 12, 46, -5.0F, -8.0F, -19.0F, 10, 10, 12, 0.0F));
		Body.cubeList.add(new ModelBox(Body, 13, 46, -4.0F, -7.0F, -7.0F, 8, 9, 12, 0.0F));

		bone = new ModelRenderer(this);
		bone.setRotationPoint(4.0F, -7.0F, -6.0F);
		setRotationAngle(bone, 0.0F, 0.0F, -0.6981F);
		Body.addChild(bone);
		bone.cubeList.add(new ModelBox(bone, 7, 68, -3.4605F, 1.0126F, -6.0F, 29, 0, 14, 0.0F));

		bone2 = new ModelRenderer(this);
		bone2.setRotationPoint(-4.0F, -7.0F, -6.0F);
		setRotationAngle(bone2, 0.0F, 0.0F, 0.6981F);
		Body.addChild(bone2);
		bone2.cubeList.add(new ModelBox(bone2, 7, 68, -25.5395F, 1.0126F, -6.0F, 29, 0, 14, 0.0F));

		TailA = new ModelRenderer(this);
		TailA.setRotationPoint(0.0F, 3.0F, 14.0F);
		setRotationAngle(TailA, -1.1345F, 0.0F, 0.0F);
		TailA.cubeList.add(new ModelBox(TailA, 44, 0, -1.0F, -0.5774F, 0.9063F, 2, 2, 3, 0.0F));

		TailB = new ModelRenderer(this);
		TailB.setRotationPoint(0.0F, 3.0F, 13.0F);
		setRotationAngle(TailB, -1.1345F, 0.0F, 0.0F);
		TailB.cubeList.add(new ModelBox(TailB, 38, 7, -1.5F, -2.0F, 3.0F, 3, 4, 7, 0.0F));

		TailC = new ModelRenderer(this);
		TailC.setRotationPoint(0.0F, 3.0F, 14.0F);
		setRotationAngle(TailC, -1.4022F, 0.0F, 0.0F);
		TailC.cubeList.add(new ModelBox(TailC, 24, 3, -1.5F, -3.5142F, 8.8322F, 3, 4, 7, 0.0F));

		Leg1A = new ModelRenderer(this);
		Leg1A.setRotationPoint(-4.0F, 9.0F, 11.0F);
		Leg1A.cubeList.add(new ModelBox(Leg1A, 78, 29, -1.5F, -2.0F, -2.5F, 4, 9, 5, 0.0F));

		
		
		Leg1B = new ModelRenderer(this);
		Leg1B.setRotationPoint(-4.0F, 16.0F, 11.0F);
		Leg1B.cubeList.add(new ModelBox(Leg1B, 78, 43, -1.0F, 0.0F, -1.5F, 3, 5, 3, 0.0F));

		Leg1C = new ModelRenderer(this);
		Leg1C.setRotationPoint(-4.0F, 16.0F, 11.0F);
		Leg1C.cubeList.add(new ModelBox(Leg1C, 78, 51, -1.5F, 5.0F, -2.0F, 4, 3, 4, 0.0F));

		Leg2A = new ModelRenderer(this);
		Leg2A.setRotationPoint(4.0F, 9.0F, 11.0F);
		Leg2A.cubeList.add(new ModelBox(Leg2A, 96, 29, -2.5F, -2.0F, -2.5F, 4, 9, 5, 0.0F));

		Leg2B = new ModelRenderer(this);
		Leg2B.setRotationPoint(4.0F, 16.0F, 11.0F);
		Leg2B.cubeList.add(new ModelBox(Leg2B, 96, 43, -2.0F, 0.0F, -1.5F, 3, 5, 3, 0.0F));

		Leg2C = new ModelRenderer(this);
		Leg2C.setRotationPoint(4.0F, 16.0F, 11.0F);
		Leg2C.cubeList.add(new ModelBox(Leg2C, 96, 51, -2.5F, 5.0F, -2.0F, 4, 3, 4, 0.0F));

		Leg3A = new ModelRenderer(this);
		Leg3A.setRotationPoint(-4.0F, 9.0F, -8.0F);
		Leg3A.cubeList.add(new ModelBox(Leg3A, 44, 29, -1.1F, -1.0F, -2.1F, 3, 8, 4, 0.0F));

		Leg3B = new ModelRenderer(this);
		Leg3B.setRotationPoint(-4.0F, 16.0F, -8.0F);
		Leg3B.cubeList.add(new ModelBox(Leg3B, 44, 41, -1.1F, 0.0F, -1.6F, 3, 5, 3, 0.0F));

		Leg3C = new ModelRenderer(this);
		Leg3C.setRotationPoint(-4.0F, 16.0F, -8.0F);
		Leg3C.cubeList.add(new ModelBox(Leg3C, 44, 51, -1.6F, 5.0F, -2.1F, 4, 3, 4, 0.0F));

		Leg4A = new ModelRenderer(this);
		Leg4A.setRotationPoint(4.0F, 9.0F, -8.0F);
		Leg4A.cubeList.add(new ModelBox(Leg4A, 60, 29, -1.9F, -1.0F, -2.1F, 3, 8, 4, 0.0F));

		Leg4B = new ModelRenderer(this);
		Leg4B.setRotationPoint(4.0F, 16.0F, -8.0F);
		Leg4B.cubeList.add(new ModelBox(Leg4B, 60, 41, -1.9F, 0.0F, -1.6F, 3, 5, 3, 0.0F));

		Leg4C = new ModelRenderer(this);
		Leg4C.setRotationPoint(4.0F, 16.0F, -8.0F);
		Leg4C.cubeList.add(new ModelBox(Leg4C, 60, 51, -2.4F, 5.0F, -2.1F, 4, 3, 4, 0.0F));

		Head = new ModelRenderer(this);
		Head.setRotationPoint(0.0F, 4.0F, -10.0F);
		setRotationAngle(Head, 0.5236F, 0.0F, 0.0F);
		Head.cubeList.add(new ModelBox(Head, 0, 0, -2.5F, -10.0F, -1.5F, 5, 5, 7, 0.0F));
		Head.cubeList.add(new ModelBox(Head, 108, 0, -1.5F, -13.0F, -0.5F, 3, 4, 3, 0.0F));
		Head.cubeList.add(new ModelBox(Head, 120, 8, -1.0F, -16.0981F, 0.134F, 2, 4, 2, 0.0F));
		Head.cubeList.add(new ModelBox(Head, 124, 14, -0.5F, -19.1962F, 0.7679F, 1, 4, 1, 0.0F));

		UMouth = new ModelRenderer(this);
		UMouth.setRotationPoint(0.0F, 3.95F, -10.0F);
		setRotationAngle(UMouth, 0.5236F, 0.0F, 0.0F);
		UMouth.cubeList.add(new ModelBox(UMouth, 24, 18, -2.0F, -10.0F, -7.0F, 4, 3, 6, 0.0F));

		LMouth = new ModelRenderer(this);
		LMouth.setRotationPoint(0.0F, 4.0F, -10.0F);
		setRotationAngle(LMouth, 0.5236F, 0.0F, 0.0F);
		LMouth.cubeList.add(new ModelBox(LMouth, 24, 27, -2.0F, -7.0F, -6.5F, 4, 2, 5, 0.0F));

		Ear1 = new ModelRenderer(this);
		Ear1.setRotationPoint(0.0F, 4.0F, -10.0F);
		setRotationAngle(Ear1, 0.5236F, 0.0F, 0.0F);
		Ear1.cubeList.add(new ModelBox(Ear1, 0, 0, -2.45F, -12.0F, 4.0F, 2, 3, 1, 0.0F));

		Ear2 = new ModelRenderer(this);
		Ear2.setRotationPoint(0.0F, 4.0F, -10.0F);
		setRotationAngle(Ear2, 0.5236F, 0.0F, 0.0F);
		Ear2.cubeList.add(new ModelBox(Ear2, 0, 0, 0.45F, -12.0F, 4.0F, 2, 3, 1, 0.0F));

		Neck = new ModelRenderer(this);
		Neck.setRotationPoint(0.0F, 4.0F, -10.0F);
		setRotationAngle(Neck, 0.5236F, 0.0F, 0.0F);
		Neck.cubeList.add(new ModelBox(Neck, 0, 12, -1.95F, -9.8F, -2.0F, 4, 14, 8, 0.0F));
 
		Mane = new ModelRenderer(this);
		Mane.setRotationPoint(0.0F, 4.0F, -10.0F);
		setRotationAngle(Mane, 0.5236F, 0.0F, 0.0F);
		Mane.cubeList.add(new ModelBox(Mane, 59, 1, -1.0F, -11.5F, 5.0F, 2, 16, 3, 0.0F));
		Mane.cubeList.add(new ModelBox(Mane, 120, 0, -1.0F, -1.5359F, 7.3301F, 2, 4, 2, 0.0F));
		Mane.cubeList.add(new ModelBox(Mane, 120, 0, -1.0F, -6.1156F, 7.3978F, 2, 4, 2, 0.0F));
		Mane.cubeList.add(new ModelBox(Mane, 120, 0, -1.0F, -10.7845F, 7.311F, 2, 4, 2, 0.0F));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		
		this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);

		
		GL11.glPushMatrix();
		GL11.glTranslatef(0f, -0.25f, -0.10f);
		GL11.glScalef(1.2f, 1.2f, 1.2f);
		
		Body.render(f5);
		TailA.render(f5);
		TailB.render(f5);
		TailC.render(f5);
		Leg1A.render(f5);
		Leg1B.render(f5);
		Leg1C.render(f5);
		Leg2A.render(f5);
		Leg2B.render(f5);
		Leg2C.render(f5);
		Leg3A.render(f5);
		Leg3B.render(f5);
		Leg3C.render(f5);
		Leg4A.render(f5);
		Leg4B.render(f5);
		Leg4C.render(f5);
		Head.render(f5);
		UMouth.render(f5);
		LMouth.render(f5);
		Ear1.render(f5);
		Ear2.render(f5);
		Neck.render(f5);
		Mane.render(f5);
		
		GL11.glPopMatrix();
	}
	
	@Override
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
	{	

	}
	
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	
}
