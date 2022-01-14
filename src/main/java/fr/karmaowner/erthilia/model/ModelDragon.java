package fr.karmaowner.erthilia.model;

import org.lwjgl.opengl.GL11;

import fr.karmaowner.erthilia.utils.MathsUtils;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelDragon extends ModelBase {
	
	public final ModelRenderer et8;
	public final ModelRenderer et9;
	public final ModelRenderer et12;
	public final ModelRenderer et14;
	public final ModelRenderer et16;
	public final ModelRenderer et19;
	public final ModelRenderer et22;
	public final ModelRenderer et25;
	public final ModelRenderer et26;
	public final ModelRenderer et28;
	public final ModelRenderer et31;
	public final ModelRenderer et34;
	public final ModelRenderer et37;
	public final ModelRenderer et40;
	public final ModelRenderer et43;
	public final ModelRenderer et46;
	public final ModelRenderer et53;
	public final ModelRenderer tete;
	public final ModelRenderer ailea1;
	public final ModelRenderer ailea2;
	public final ModelRenderer patte_avant1;
	public final ModelRenderer frontleg3;
	public final ModelRenderer frontleg4;
	public final ModelRenderer patte_avant2;
	public final ModelRenderer frontleg5;
	public final ModelRenderer frontleg6;
	public final ModelRenderer frontleg7;
	public final ModelRenderer ailea3;
	public final ModelRenderer ailea4;
	public final ModelRenderer patte_avant3;
	public final ModelRenderer frontleg8;
	public final ModelRenderer frontleg9;
	public final ModelRenderer frontleg10;
	public final ModelRenderer patte_avant4;
	public final ModelRenderer frontleg11;
	public final ModelRenderer frontleg12;
	public final ModelRenderer frontleg13;
		
	public ModelDragon() {	
		
		
		this.textureWidth = 512;
		this.textureHeight = 512;
		
		
		
		et8 = new ModelRenderer(this);
		et9 = new ModelRenderer(this);
		et12 = new ModelRenderer(this);
		et14 = new ModelRenderer(this);
		et16 = new ModelRenderer(this);
		et19 = new ModelRenderer(this);
		et22 = new ModelRenderer(this);
		et25 = new ModelRenderer(this);
		et26 = new ModelRenderer(this);
		et28 = new ModelRenderer(this);
		et31 = new ModelRenderer(this);
		et34 = new ModelRenderer(this);
		et37 = new ModelRenderer(this);
		et40 = new ModelRenderer(this);
		et43 = new ModelRenderer(this);
		et46 = new ModelRenderer(this);
		et53 = new ModelRenderer(this);
		tete = new ModelRenderer(this);
		ailea1 = new ModelRenderer(this);
		ailea2 = new ModelRenderer(this);
		patte_avant1 = new ModelRenderer(this);
		frontleg3 = new ModelRenderer(this);
		frontleg4 = new ModelRenderer(this);
		patte_avant2 = new ModelRenderer(this);
		frontleg5 = new ModelRenderer(this);
		frontleg6 = new ModelRenderer(this);
		frontleg7 = new ModelRenderer(this);
		ailea3 = new ModelRenderer(this);
		ailea4 = new ModelRenderer(this);
		patte_avant3 = new ModelRenderer(this);
		frontleg8 = new ModelRenderer(this);
		frontleg9 = new ModelRenderer(this);
		frontleg10 = new ModelRenderer(this);
		patte_avant4 = new ModelRenderer(this);
		frontleg11 = new ModelRenderer(this);
		frontleg12 = new ModelRenderer(this);
		frontleg13 = new ModelRenderer(this);
		
		
		
		et8.setRotationPoint(-0.5F, -6.0F, 5.5F);
		setRotationAngle(et8, -0.2618F, 0.0F, 0.0F);
		et9.addChild(et8);
		et8.cubeList.add(new ModelBox(et8, 157, 0, -2.5F, -8.0F, -0.5F, 3, 12, 3, 0.0F));

		et9.setRotationPoint(6.0F, -31.0F, -39.0F);
		setRotationAngle(et9, -0.8727F, 0.0F, 0.0F);
		tete.addChild(et9);
		et9.cubeList.add(new ModelBox(et9, 171, 0, -3.0F, -6.1F, 4.0F, 4, 12, 4, 0.0F));

		et12.setRotationPoint(-4.0F, -31.0F, -39.0F);
		setRotationAngle(et12, -0.8727F, 0.0F, 0.0F);
		tete.addChild(et12);
		et12.cubeList.add(new ModelBox(et12, 171, 0, -3.0F, -6.1F, 4.0F, 4, 12, 4, 0.0F));

		et14.setRotationPoint(-0.5F, -6.0F, 5.5F);
		setRotationAngle(et14, -0.2618F, 0.0F, 0.0F);
		et12.addChild(et14);
		et14.cubeList.add(new ModelBox(et14, 157, 0, -2.5F, -8.0F, -0.5F, 3, 12, 3, 0.0F));

		et16.setRotationPoint(0.0F, -16.5F, -18.0F);
		setRotationAngle(et16, -0.3491F, 0.0F, 0.0F);
		tete.addChild(et16);
		et16.cubeList.add(new ModelBox(et16, 68, 229, -5.0F, -1.5F, -10.0F, 10, 11, 16, 0.0F));
		et16.cubeList.add(new ModelBox(et16, 463, 68, 0.0F, -8.5F, -10.0F, 0, 7, 16, 0.0F));

		et19.setRotationPoint(0.0F, -12.5F, -7.0F);
		setRotationAngle(et19, -0.8727F, 0.0F, 0.0F);
		tete.addChild(et19);
		et19.cubeList.add(new ModelBox(et19, 68, 195, -5.0F, 3.5F, -9.0F, 10, 11, 16, 0.0F));
		et19.cubeList.add(new ModelBox(et19, 471, 25, 0.0F, -3.5F, -9.0F, 0, 7, 17, 0.0F));

		et22.setRotationPoint(0.0F, -3.5F, -8.0F);
		setRotationAngle(et22, -1.309F, 0.0F, 0.0F);
		tete.addChild(et22);
		et22.cubeList.add(new ModelBox(et22, 68, 229, -5.0F, -1.5F, -3.0F, 10, 11, 16, 0.0F));

		et25.setRotationPoint(0.0F, 0.0F, 0.0F);
		tete.addChild(et25);

		et26.setRotationPoint(0.0F, 4.0F, 8.0F);
		et26.cubeList.add(new ModelBox(et26, 0, 424, -15.0F, -2.0F, -22.0F, 30, 25, 64, 0.0F));
		et26.cubeList.add(new ModelBox(et26, 196, 431, -8.0F, -5.0F, -21.0F, 16, 18, 64, 0.0F));
		et26.cubeList.add(new ModelBox(et26, 395, 390, -19.0F, -1.0F, -14.0F, 38, 19, 21, 0.0F));
		et26.cubeList.add(new ModelBox(et26, 399, 0, 0.0F, -14.0F, -15.0F, 0, 9, 57, 0.0F));

		et28.setRotationPoint(1.0F, -10.0F, -8.0F);
		setRotationAngle(et28, 0.0F, 0.0F, 3.1416F);
		et26.addChild(et28);
		et28.cubeList.add(new ModelBox(et28, 393, 83, -6.0F, -22.0F, 50.0F, 14, 14, 17, 0.0F));
		et28.cubeList.add(new ModelBox(et28, 475, 144, 1.0F, -8.0F, 50.0F, 0, 7, 17, 0.0F));

		et31.setRotationPoint(1.0F, -15.0F, 75.5F);
		setRotationAngle(et31, 0.2618F, 0.0F, 0.0F);
		et28.addChild(et31);
		et31.cubeList.add(new ModelBox(et31, 396, 116, -6.5F, -9.0F, -10.5F, 13, 13, 17, 0.0F));
		et31.cubeList.add(new ModelBox(et31, 467, 126, 0.0F, 4.0F, -10.5F, 0, 7, 17, 0.0F));

		et34.setRotationPoint(1.0F, -15.0F, 75.5F);
		setRotationAngle(et34, 0.5236F, 0.0F, 0.0F);
		et28.addChild(et34);
		et34.cubeList.add(new ModelBox(et34, 396, 148, -6.0F, -7.0F, 3.5F, 12, 12, 17, 0.0F));
		et34.cubeList.add(new ModelBox(et34, 467, 149, 0.0F, 4.0F, 5.5F, 0, 6, 15, 0.0F));

		et37.setRotationPoint(1.0F, -15.0F, 104.5F);
		setRotationAngle(et37, 0.8727F, 0.0F, 0.0F);
		et28.addChild(et37);
		et37.cubeList.add(new ModelBox(et37, 396, 179, -5.0F, -22.0F, -0.5F, 10, 10, 17, 0.0F));
		et37.cubeList.add(new ModelBox(et37, 474, 174, 0.0F, -13.0F, -0.5F, 0, 7, 16, 0.0F));

		et40.setRotationPoint(1.0F, -28.0F, 112.5F);
		setRotationAngle(et40, -2.4435F, 0.0F, 0.0F);
		et28.addChild(et40);
		et40.cubeList.add(new ModelBox(et40, 402, 208, -4.0F, 11.5F, -9.5F, 8, 8, 12, 0.0F));
		et40.cubeList.add(new ModelBox(et40, 478, 73, 0.0F, 6.5F, -8.5F, 0, 7, 9, 0.0F));

		et43.setRotationPoint(1.0F, -28.0F, 112.5F);
		setRotationAngle(et43, -2.7053F, 0.0F, 0.0F);
		et28.addChild(et43);
		et43.cubeList.add(new ModelBox(et43, 402, 235, -3.0F, 15.0F, -14.5F, 6, 6, 11, 0.0F));
		et43.cubeList.add(new ModelBox(et43, 473, 75, 0.0F, 11.0F, -13.5F, 0, 5, 7, 0.0F));

		et46.setRotationPoint(1.0F, -10.0F, 93.5F);
		setRotationAngle(et46, -2.8798F, 0.0F, 0.0F);
		et28.addChild(et46);
		et46.cubeList.add(new ModelBox(et46, 403, 262, -2.0F, 31.0F, -44.5F, 4, 4, 11, 0.0F));
		et46.cubeList.add(new ModelBox(et46, 497, 239, 0.0F, 28.0F, -42.5F, 0, 4, 8, 0.0F));

		et53.setRotationPoint(6.0F, 26.0F, 0.0F);
		setRotationAngle(et53, 0.9599F, 0.0F, 0.0F);
		patte_avant1.addChild(et53);
		et53.cubeList.add(new ModelBox(et53, 0, 44, -5.0F, -9.0F, -3.0F, 8, 16, 8, 0.0F));

		tete.setRotationPoint(0.0F, 0.0F, -3.0F);
		tete.cubeList.add(new ModelBox(tete, 297, 0, -6.0F, -18.0F, -55.0F, 12, 5, 16, 0.0F));
		tete.cubeList.add(new ModelBox(tete, 294, 70, -3.0F, -19.0F, -57.0F, 6, 6, 16, 0.0F));
		tete.cubeList.add(new ModelBox(tete, 225, 82, -5.0F, -20.0F, -54.0F, 2, 4, 7, 0.0F));
		tete.cubeList.add(new ModelBox(tete, 225, 82, 3.0F, -20.0F, -54.0F, 2, 4, 7, 0.0F));
		tete.cubeList.add(new ModelBox(tete, 289, 32, -3.0F, -13.0F, -56.0F, 6, 6, 26, 0.0F));
		tete.cubeList.add(new ModelBox(tete, 114, 32, -8.0F, -25.0F, -41.0F, 16, 16, 16, 0.0F));
		tete.cubeList.add(new ModelBox(tete, 468, 69, 0.0F, -30.0F, -40.0F, 0, 14, 15, 0.0F));
		tete.cubeList.add(new ModelBox(tete, 297, 0, -6.0F, -13.0F, -55.0F, 12, 4, 16, 0.0F));

		ailea1.setRotationPoint(11.0F, 5.0F, 2.0F);
		setRotationAngle(ailea1, 0.0F, 0.0F, -0.9599F);
		ailea1.cubeList.add(new ModelBox(ailea1, 192, 414, 1.0F, -4.0F, -4.0F, 41, 8, 8, 0.0F));
		ailea1.cubeList.add(new ModelBox(ailea1, 0, 367, 1.0F, 0.0F, 2.0F, 41, 0, 56, 0.0F));

		ailea2.setRotationPoint(35.0F, -29.0F, 0.0F);
		setRotationAngle(ailea2, 0.0F, 0.0F, 0.8727F);
		ailea2.cubeList.add(new ModelBox(ailea2, 112, 136, -0.6914F, -2.5656F, -2.0F, 56, 4, 4, 0.0F));
		ailea2.cubeList.add(new ModelBox(ailea2, 289, 436, -0.6914F, -0.5656F, 2.0F, 56, 0, 56, 0.0F));

		patte_avant1.setRotationPoint(17.0F, 10.0F, 11.0F);
		setRotationAngle(patte_avant1, -0.5236F, 0.0F, 0.0F);
		patte_avant1.cubeList.add(new ModelBox(patte_avant1, 0, 0, 0.0F, -1.0F, -13.0F, 10, 24, 17, 0.0F));

		frontleg3.setRotationPoint(6.0F, 26.0F, 0.0F);
		setRotationAngle(frontleg3, 0.2618F, 0.0F, 0.0F);
		patte_avant1.addChild(frontleg3);
		frontleg3.cubeList.add(new ModelBox(frontleg3, 0, 79, -5.0F, 2.0F, 0.0F, 8, 16, 8, 0.0F));

		frontleg4.setRotationPoint(6.0F, 26.0F, 0.0F);
		setRotationAngle(frontleg4, 0.5236F, 0.0F, 0.0F);
		patte_avant1.addChild(frontleg4);
		frontleg4.cubeList.add(new ModelBox(frontleg4, 112, 104, -5.0F, 16.0F, -9.0F, 8, 4, 13, 0.0F));

		patte_avant2.setRotationPoint(-16.0F, 10.0F, 11.0F);
		setRotationAngle(patte_avant2, -0.5236F, 0.0F, 0.0F);
		patte_avant2.cubeList.add(new ModelBox(patte_avant2, 0, 0, -10.0F, -1.0F, -13.0F, 10, 24, 17, 0.0F));

		frontleg5.setRotationPoint(-6.0F, 26.0F, 0.0F);
		setRotationAngle(frontleg5, 0.9599F, 0.0F, 0.0F);
		patte_avant2.addChild(frontleg5);
		frontleg5.cubeList.add(new ModelBox(frontleg5, 0, 44, -3.0F, -9.0F, -3.0F, 8, 16, 8, 0.0F));

		frontleg6.setRotationPoint(-6.0F, 26.0F, 0.0F);
		setRotationAngle(frontleg6, 0.2618F, 0.0F, 0.0F);
		patte_avant2.addChild(frontleg6);
		frontleg6.cubeList.add(new ModelBox(frontleg6, 0, 79, -3.0F, 2.0F, 0.0F, 8, 16, 8, 0.0F));

		frontleg7.setRotationPoint(-6.0F, 26.0F, 0.0F);
		setRotationAngle(frontleg7, 0.5236F, 0.0F, 0.0F);
		patte_avant2.addChild(frontleg7);
		frontleg7.cubeList.add(new ModelBox(frontleg7, 112, 104, -3.0F, 16.0F, -9.0F, 8, 4, 13, 0.0F));

		ailea3.setRotationPoint(-11.0F, 5.0F, 2.0F);
		setRotationAngle(ailea3, 0.0F, 0.0F, 0.9599F);
		ailea3.cubeList.add(new ModelBox(ailea3, 192, 414, -42.0F, -4.0F, -4.0F, 41, 8, 8, 0.0F));
		ailea3.cubeList.add(new ModelBox(ailea3, 0, 368, -42.0F, 0.0F, 2.0F, 41, 0, 56, 0.0F));

		ailea4.setRotationPoint(-35.0F, -29.0F, 0.0F);
		setRotationAngle(ailea4, 0.0F, 0.0F, -0.8727F);
		ailea4.cubeList.add(new ModelBox(ailea4, 112, 136, -55.3086F, -2.5656F, -2.0F, 56, 4, 4, 0.0F));
		ailea4.cubeList.add(new ModelBox(ailea4, 289, 436, -55.3086F, -0.5656F, 2.0F, 56, 0, 56, 0.0F));

		patte_avant3.setRotationPoint(-15.0F, 10.0F, 48.0F);
		setRotationAngle(patte_avant3, -0.5236F, 0.0F, 0.0F);
		patte_avant3.cubeList.add(new ModelBox(patte_avant3, 0, 0, -10.0F, -1.0F, -13.0F, 10, 24, 17, 0.0F));

		frontleg8.setRotationPoint(-6.0F, 26.0F, 0.0F);
		setRotationAngle(frontleg8, 0.9599F, 0.0F, 0.0F);
		patte_avant3.addChild(frontleg8);
		frontleg8.cubeList.add(new ModelBox(frontleg8, 0, 44, -3.0F, -9.0F, -3.0F, 8, 16, 8, 0.0F));

		frontleg9.setRotationPoint(-6.0F, 26.0F, 0.0F);
		setRotationAngle(frontleg9, 0.2618F, 0.0F, 0.0F);
		patte_avant3.addChild(frontleg9);
		frontleg9.cubeList.add(new ModelBox(frontleg9, 0, 77, -3.0F, 2.0F, 0.0F, 8, 16, 8, 0.0F));

		frontleg10.setRotationPoint(-6.0F, 26.0F, 0.0F);
		setRotationAngle(frontleg10, 0.5236F, 0.0F, 0.0F);
		patte_avant3.addChild(frontleg10);
		frontleg10.cubeList.add(new ModelBox(frontleg10, 112, 104, -3.0F, 16.0F, -9.0F, 8, 4, 13, 0.0F));

		patte_avant4.setRotationPoint(25.0F, 10.0F, 48.0F);
		setRotationAngle(patte_avant4, -0.5236F, 0.0F, 0.0F);
		patte_avant4.cubeList.add(new ModelBox(patte_avant4, 0, 0, -10.0F, -1.0F, -13.0F, 10, 24, 17, 0.0F));

		frontleg11.setRotationPoint(-6.0F, 26.0F, 0.0F);
		setRotationAngle(frontleg11, 0.9599F, 0.0F, 0.0F);
		patte_avant4.addChild(frontleg11);
		frontleg11.cubeList.add(new ModelBox(frontleg11, 0, 44, -3.0F, -9.0F, -3.0F, 8, 16, 8, 0.0F));

		frontleg12.setRotationPoint(-6.0F, 26.0F, 0.0F);
		setRotationAngle(frontleg12, 0.2618F, 0.0F, 0.0F);
		patte_avant4.addChild(frontleg12);
		frontleg12.cubeList.add(new ModelBox(frontleg12, 0, 79, -3.0F, 2.0F, 0.0F, 8, 16, 8, 0.0F));

		frontleg13.setRotationPoint(-6.0F, 26.0F, 0.0F);
		setRotationAngle(frontleg13, 0.5236F, 0.0F, 0.0F);
		patte_avant4.addChild(frontleg13);
		frontleg13.cubeList.add(new ModelBox(frontleg13, 112, 104, -3.0F, 16.0F, -9.0F, 8, 4, 13, 0.0F));
		
		
		ailea1.addChild(ailea2);
		ailea3.addChild(ailea4);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		
		this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		
		GL11.glPushMatrix();
		GL11.glScalef(1.5f, 1.5f, 1.5f);
		GL11.glTranslated(0, -2.3f, -1.9f);
		et26.render(f5);
		tete.render(f5);
		ailea1.render(f5);
		ailea2.offsetY = 2.0f;
		ailea2.offsetX = 0.4f;
		ailea4.offsetZ = 0.2f;
		ailea4.offsetY = 1.8f;
		ailea4.offsetX = -0.3f;
		patte_avant1.render(f5);
		patte_avant2.render(f5);
		ailea3.render(f5);
		patte_avant3.render(f5);
		patte_avant4.render(f5);
		GL11.glPopMatrix();
	}
	
	@Override
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
	{
		if(!entity.onGround)
		{
			this.patte_avant1.rotateAngleX = MathsUtils.Deg2Rad * -40;
			this.patte_avant2.rotateAngleX = MathsUtils.Deg2Rad * -40;
			this.patte_avant3.rotateAngleX = MathsUtils.Deg2Rad * 10;
			this.patte_avant4.rotateAngleX = MathsUtils.Deg2Rad * 0;

		}
		else
		{
		    this.patte_avant1.rotateAngleX = -0.5236F + (MathHelper.cos(f * 0.65559F + 2.0F) * 0.2F * f1);
		    this.patte_avant2.rotateAngleX = -0.5236F + (MathHelper.cos(f * 0.65559F + 3.141593F) * 0.2F * f1);
		    this.patte_avant3.rotateAngleX = -0.5236F + (MathHelper.cos(f * 0.65559F + 3.141593F + 2.0F) * 0.2F * f1);
		    this.patte_avant4.rotateAngleX = -0.5236F + (MathHelper.cos(f * 0.65559F + 2.0F) * 0.2F * f1);
		}

	    float baseRot = -0.9599F;
		@SuppressWarnings("unused")
		float baseRotOut = 0.8727F;
		float wingsMotion = (float) (entity.motionX * entity.motionX + entity.motionZ * entity.motionZ);
		float animation = (float) Math.sin((entity.ticksExisted + Math.pow(2, wingsMotion)) / 8D);
				
		
		ailea1.rotateAngleZ = baseRot - (animation*0.25f);
		ailea2.rotateAngleZ = 110 * MathsUtils.Deg2Rad;
		ailea3.rotateAngleZ = (-baseRot) + (animation*0.25f);
		ailea4.rotateAngleZ = -110 * MathsUtils.Deg2Rad;

	  }
	
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
	

}
