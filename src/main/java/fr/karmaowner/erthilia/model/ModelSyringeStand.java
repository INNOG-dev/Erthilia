package fr.karmaowner.erthilia.model;

import fr.karmaowner.erthilia.animations.FrameAnimator;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class ModelSyringeStand extends ErthiliaModelBase {
	
	private final ModelRenderer bone;
	private final ModelRenderer bone2;
	private final ModelRenderer bone14;
	private final ModelRenderer bone15;
	private final ModelRenderer bone3;
	private final ModelRenderer bone4;
	private final ModelRenderer bone6;
	private final ModelRenderer bone7;
	private final ModelRenderer bone5;
	private final ModelRenderer bone9;
	private final ModelRenderer bone10;
	private final ModelRenderer bone11;
	private final ModelRenderer bone12;
	private final ModelRenderer bone13;
	private final ModelRenderer bone8;
	private final ModelRenderer bone16;
	private final ModelRenderer bone17;
	private final ModelRenderer bone18;
	private final ModelRenderer bone19;
	
	private FrameAnimator animatedTextures;
	

	public ModelSyringeStand() {
		textureWidth = 128;
		textureHeight = 128;

		bone = new ModelRenderer(this);
		bone.setRotationPoint(0.0F, 8.0F, 0.0F);
		bone.cubeList.add(new ModelBox(bone, 0, 108, -16.0F, 0.0F, -8.0F, 32, 4, 16, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 80, -12.5F, 2.0F, -7.0F, 25, 14, 14, -0.2F, false));

		bone2 = new ModelRenderer(this);
		bone2.setRotationPoint(0.0F, -2.0F, 0.0F);
		bone.addChild(bone2);
		bone2.cubeList.add(new ModelBox(bone2, 0, 52, -5.0F, -1.0F, -5.0F, 10, 3, 10, 0.0F, false));
		bone2.cubeList.add(new ModelBox(bone2, 0, 43, -4.0F, -2.0F, -4.0F, 8, 1, 8, 0.0F, false));

		bone14 = new ModelRenderer(this);
		bone14.setRotationPoint(0.0F, 1.5F, -5.1F);
		bone2.addChild(bone14);
		setRotationAngle(bone14, 0.8727F, 0.0F, 0.0F);
		bone14.cubeList.add(new ModelBox(bone14, 0, 73, -5.0F, -1.5F, -2.0F, 10, 3, 4, -0.02F, false));

		bone15 = new ModelRenderer(this);
		bone15.setRotationPoint(0.0F, 1.5F, 5.1F);
		bone2.addChild(bone15);
		setRotationAngle(bone15, -0.8727F, 0.0F, 0.0F);
		bone15.cubeList.add(new ModelBox(bone15, 0, 73, -5.0F, -1.5F, -2.0F, 10, 3, 4, -0.02F, false));

		bone3 = new ModelRenderer(this);
		bone3.setRotationPoint(-10.5F, 0.0F, 0.0F);
		bone2.addChild(bone3);
		bone3.cubeList.add(new ModelBox(bone3, 0, 65, -3.5F, -4.0F, -1.0F, 8, 6, 2, 0.0F, false));
		bone3.cubeList.add(new ModelBox(bone3, 0, 0, -3.2F, -4.0F, -3.0F, 1, 3, 6, -0.02F, false));
		bone3.cubeList.add(new ModelBox(bone3, 0, 0, 3.3F, -4.0F, -3.0F, 1, 3, 6, -0.02F, false));
		bone3.cubeList.add(new ModelBox(bone3, 0, 0, -0.8F, -4.0F, -3.0F, 1, 3, 6, -0.02F, false));
		bone3.cubeList.add(new ModelBox(bone3, 0, 0, 0.7F, -4.0F, -3.0F, 1, 3, 6, -0.02F, false));

		bone4 = new ModelRenderer(this);
		bone4.setRotationPoint(12.5F, 2.0F, 4.0F);
		bone3.addChild(bone4);
		bone4.cubeList.add(new ModelBox(bone4, 0, 45, -15.0F, -7.0F, -7.0F, 2, 4, 2, -0.2F, false));
		bone4.cubeList.add(new ModelBox(bone4, 0, 87, -15.0F, -7.5F, -7.0F, 2, 1, 2, 0.0F, false));
		bone4.cubeList.add(new ModelBox(bone4, 0, 40, -15.0F, -9.2F, -7.0F, 2, 1, 2, -0.2F, false));
		bone4.cubeList.add(new ModelBox(bone4, 0, 82, -15.0F, -9.2F, -7.0F, 2, 3, 2, -0.4F, false));
		bone4.cubeList.add(new ModelBox(bone4, 0, 90, -15.0F, -4.2F, -7.0F, 2, 2, 2, -0.4F, false));
		bone4.cubeList.add(new ModelBox(bone4, 0, 114, -14.5F, -3.2F, -6.5F, 1, 3, 1, -0.3F, false));

		bone6 = new ModelRenderer(this);
		bone6.setRotationPoint(12.5F, 2.0F, 8.0F);
		bone3.addChild(bone6);
		bone6.cubeList.add(new ModelBox(bone6, 0, 45, -15.0F, -7.0F, -7.0F, 2, 4, 2, -0.2F, false));
		bone6.cubeList.add(new ModelBox(bone6, 0, 87, -15.0F, -7.5F, -7.0F, 2, 1, 2, 0.0F, false));
		bone6.cubeList.add(new ModelBox(bone6, 0, 40, -15.0F, -9.2F, -7.0F, 2, 1, 2, -0.2F, false));
		bone6.cubeList.add(new ModelBox(bone6, 0, 82, -15.0F, -9.2F, -7.0F, 2, 3, 2, -0.4F, false));
		bone6.cubeList.add(new ModelBox(bone6, 0, 90, -15.0F, -4.2F, -7.0F, 2, 2, 2, -0.4F, false));
		bone6.cubeList.add(new ModelBox(bone6, 0, 114, -14.5F, -3.2F, -6.5F, 1, 3, 1, -0.3F, false));

		bone7 = new ModelRenderer(this);
		bone7.setRotationPoint(16.5F, 2.0F, 8.0F);
		bone3.addChild(bone7);
		bone7.cubeList.add(new ModelBox(bone7, 0, 45, -15.0F, -7.0F, -7.0F, 2, 4, 2, -0.2F, false));
		bone7.cubeList.add(new ModelBox(bone7, 0, 87, -15.0F, -7.5F, -7.0F, 2, 1, 2, 0.0F, false));
		bone7.cubeList.add(new ModelBox(bone7, 0, 40, -15.0F, -9.2F, -7.0F, 2, 1, 2, -0.2F, false));
		bone7.cubeList.add(new ModelBox(bone7, 0, 82, -15.0F, -9.2F, -7.0F, 2, 3, 2, -0.4F, false));
		bone7.cubeList.add(new ModelBox(bone7, 0, 90, -15.0F, -4.2F, -7.0F, 2, 2, 2, -0.4F, false));
		bone7.cubeList.add(new ModelBox(bone7, 0, 114, -14.5F, -3.2F, -6.5F, 1, 3, 1, -0.3F, false));

		bone5 = new ModelRenderer(this);
		bone5.setRotationPoint(16.5F, 2.0F, 4.0F);
		bone3.addChild(bone5);
		bone5.cubeList.add(new ModelBox(bone5, 0, 45, -15.0F, -7.0F, -7.0F, 2, 4, 2, -0.2F, false));
		bone5.cubeList.add(new ModelBox(bone5, 0, 87, -15.0F, -7.5F, -7.0F, 2, 1, 2, 0.0F, false));
		bone5.cubeList.add(new ModelBox(bone5, 0, 40, -15.0F, -9.2F, -7.0F, 2, 1, 2, -0.2F, false));
		bone5.cubeList.add(new ModelBox(bone5, 0, 82, -15.0F, -9.2F, -7.0F, 2, 3, 2, -0.4F, false));
		bone5.cubeList.add(new ModelBox(bone5, 0, 90, -15.0F, -4.2F, -7.0F, 2, 2, 2, -0.4F, false));
		bone5.cubeList.add(new ModelBox(bone5, 0, 114, -14.5F, -3.2F, -6.5F, 1, 3, 1, -0.3F, false));

		bone9 = new ModelRenderer(this);
		bone9.setRotationPoint(10.5F, 0.0F, 0.0F);
		bone2.addChild(bone9);
		bone9.cubeList.add(new ModelBox(bone9, 0, 65, -4.5F, -4.0F, -1.0F, 8, 6, 2, 0.0F, true));
		bone9.cubeList.add(new ModelBox(bone9, 0, 0, 2.2F, -4.0F, -3.0F, 1, 3, 6, -0.02F, true));
		bone9.cubeList.add(new ModelBox(bone9, 0, 0, -4.3F, -4.0F, -3.0F, 1, 3, 6, -0.02F, true));
		bone9.cubeList.add(new ModelBox(bone9, 0, 0, -0.2F, -4.0F, -3.0F, 1, 3, 6, -0.02F, true));
		bone9.cubeList.add(new ModelBox(bone9, 0, 0, -1.7F, -4.0F, -3.0F, 1, 3, 6, -0.02F, true));

		bone10 = new ModelRenderer(this);
		bone10.setRotationPoint(-12.5F, 2.0F, 4.0F);
		bone9.addChild(bone10);
		bone10.cubeList.add(new ModelBox(bone10, 0, 45, 13.0F, -7.0F, -7.0F, 2, 4, 2, -0.2F, true));
		bone10.cubeList.add(new ModelBox(bone10, 0, 87, 13.0F, -7.5F, -7.0F, 2, 1, 2, 0.0F, true));
		bone10.cubeList.add(new ModelBox(bone10, 0, 40, 13.0F, -9.2F, -7.0F, 2, 1, 2, -0.2F, true));
		bone10.cubeList.add(new ModelBox(bone10, 0, 82, 13.0F, -9.2F, -7.0F, 2, 3, 2, -0.4F, true));
		bone10.cubeList.add(new ModelBox(bone10, 0, 90, 13.0F, -4.2F, -7.0F, 2, 2, 2, -0.4F, true));
		bone10.cubeList.add(new ModelBox(bone10, 0, 114, 13.5F, -3.2F, -6.5F, 1, 3, 1, -0.3F, true));

		bone11 = new ModelRenderer(this);
		bone11.setRotationPoint(-12.5F, 2.0F, 8.0F);
		bone9.addChild(bone11);
		bone11.cubeList.add(new ModelBox(bone11, 0, 45, 13.0F, -7.0F, -7.0F, 2, 4, 2, -0.2F, true));
		bone11.cubeList.add(new ModelBox(bone11, 0, 87, 13.0F, -7.5F, -7.0F, 2, 1, 2, 0.0F, true));
		bone11.cubeList.add(new ModelBox(bone11, 0, 40, 13.0F, -9.2F, -7.0F, 2, 1, 2, -0.2F, true));
		bone11.cubeList.add(new ModelBox(bone11, 0, 82, 13.0F, -9.2F, -7.0F, 2, 3, 2, -0.4F, true));
		bone11.cubeList.add(new ModelBox(bone11, 0, 90, 13.0F, -4.2F, -7.0F, 2, 2, 2, -0.4F, true));
		bone11.cubeList.add(new ModelBox(bone11, 0, 114, 13.5F, -3.2F, -6.5F, 1, 3, 1, -0.3F, true));

		bone12 = new ModelRenderer(this);
		bone12.setRotationPoint(-16.5F, 2.0F, 8.0F);
		bone9.addChild(bone12);
		bone12.cubeList.add(new ModelBox(bone12, 0, 45, 13.0F, -7.0F, -7.0F, 2, 4, 2, -0.2F, true));
		bone12.cubeList.add(new ModelBox(bone12, 0, 87, 13.0F, -7.5F, -7.0F, 2, 1, 2, 0.0F, true));
		bone12.cubeList.add(new ModelBox(bone12, 0, 40, 13.0F, -9.2F, -7.0F, 2, 1, 2, -0.2F, true));
		bone12.cubeList.add(new ModelBox(bone12, 0, 82, 13.0F, -9.2F, -7.0F, 2, 3, 2, -0.4F, true));
		bone12.cubeList.add(new ModelBox(bone12, 0, 90, 13.0F, -4.2F, -7.0F, 2, 2, 2, -0.4F, true));
		bone12.cubeList.add(new ModelBox(bone12, 0, 114, 13.5F, -3.2F, -6.5F, 1, 3, 1, -0.3F, true));

		bone13 = new ModelRenderer(this);
		bone13.setRotationPoint(-16.5F, 2.0F, 4.0F);
		bone9.addChild(bone13);
		bone13.cubeList.add(new ModelBox(bone13, 0, 45, 13.0F, -7.0F, -7.0F, 2, 4, 2, -0.2F, true));
		bone13.cubeList.add(new ModelBox(bone13, 0, 87, 13.0F, -7.5F, -7.0F, 2, 1, 2, 0.0F, true));
		bone13.cubeList.add(new ModelBox(bone13, 0, 40, 13.0F, -9.2F, -7.0F, 2, 1, 2, -0.2F, true));
		bone13.cubeList.add(new ModelBox(bone13, 0, 82, 13.0F, -9.2F, -7.0F, 2, 3, 2, -0.4F, true));
		bone13.cubeList.add(new ModelBox(bone13, 0, 90, 13.0F, -4.2F, -7.0F, 2, 2, 2, -0.4F, true));
		bone13.cubeList.add(new ModelBox(bone13, 0, 114, 13.5F, -3.2F, -6.5F, 1, 3, 1, -0.3F, true));

		bone8 = new ModelRenderer(this);
		bone8.setRotationPoint(0.0F, 3.05F, 0.1F);
		setRotationAngle(bone8, -0.7854F, 0.0F, -1.5708F);
		bone8.cubeList.add(new ModelBox(bone8, 0, 45, -1.0F, -1.45F, -1.0F, 2, 4, 2, -0.2F, false));
		bone8.cubeList.add(new ModelBox(bone8, 0, 87, -1.0F, -1.95F, -1.0F, 2, 1, 2, 0.0F, false));
		bone8.cubeList.add(new ModelBox(bone8, 0, 40, -1.0F, -3.65F, -1.0F, 2, 1, 2, -0.2F, false));
		bone8.cubeList.add(new ModelBox(bone8, 0, 82, -1.0F, -3.65F, -1.0F, 2, 3, 2, -0.4F, false));
		bone8.cubeList.add(new ModelBox(bone8, 0, 90, -1.0F, 1.35F, -1.0F, 2, 2, 2, -0.4F, false));
		bone8.cubeList.add(new ModelBox(bone8, 0, 114, -0.5F, 2.35F, -0.5F, 1, 3, 1, -0.3F, false));

		bone16 = new ModelRenderer(this);
		bone16.setRotationPoint(-9.9F, 7.05F, 4.4F);
		setRotationAngle(bone16, 0.6981F, 0.0F, -1.5708F);
		bone16.cubeList.add(new ModelBox(bone16, 0, 45, -1.0F, -1.45F, -1.0F, 2, 4, 2, -0.2F, false));
		bone16.cubeList.add(new ModelBox(bone16, 0, 87, -1.0F, -1.95F, -1.0F, 2, 1, 2, 0.0F, false));
		bone16.cubeList.add(new ModelBox(bone16, 0, 40, -1.0F, -3.65F, -1.0F, 2, 1, 2, -0.2F, false));
		bone16.cubeList.add(new ModelBox(bone16, 0, 82, -1.0F, -3.65F, -1.0F, 2, 3, 2, -0.4F, false));
		bone16.cubeList.add(new ModelBox(bone16, 0, 90, -1.0F, 1.35F, -1.0F, 2, 2, 2, -0.4F, false));
		bone16.cubeList.add(new ModelBox(bone16, 0, 114, -0.5F, 2.35F, -0.5F, 1, 3, 1, -0.3F, false));

		bone17 = new ModelRenderer(this);
		bone17.setRotationPoint(9.9F, 7.05F, 4.4F);
		setRotationAngle(bone17, -0.48F, 0.0F, 1.5708F);
		bone17.cubeList.add(new ModelBox(bone17, 0, 45, -1.0F, -1.45F, -1.0F, 2, 4, 2, -0.2F, true));
		bone17.cubeList.add(new ModelBox(bone17, 0, 87, -1.0F, -1.95F, -1.0F, 2, 1, 2, 0.0F, true));
		bone17.cubeList.add(new ModelBox(bone17, 0, 40, -1.0F, -3.65F, -1.0F, 2, 1, 2, -0.2F, true));
		bone17.cubeList.add(new ModelBox(bone17, 0, 82, -1.0F, -3.65F, -1.0F, 2, 3, 2, -0.4F, true));
		bone17.cubeList.add(new ModelBox(bone17, 0, 90, -1.0F, 1.35F, -1.0F, 2, 2, 2, -0.4F, true));
		bone17.cubeList.add(new ModelBox(bone17, 0, 114, -0.5F, 2.35F, -0.5F, 1, 3, 1, -0.3F, true));

		bone18 = new ModelRenderer(this);
		bone18.setRotationPoint(9.9F, 7.05F, -4.4F);
		setRotationAngle(bone18, 0.48F, 0.0F, 1.5708F);
		bone18.cubeList.add(new ModelBox(bone18, 0, 45, -1.0F, -1.45F, -1.0F, 2, 4, 2, -0.2F, true));
		bone18.cubeList.add(new ModelBox(bone18, 0, 87, -1.0F, -1.95F, -1.0F, 2, 1, 2, 0.0F, true));
		bone18.cubeList.add(new ModelBox(bone18, 0, 40, -1.0F, -3.65F, -1.0F, 2, 1, 2, -0.2F, true));
		bone18.cubeList.add(new ModelBox(bone18, 0, 82, -1.0F, -3.65F, -1.0F, 2, 3, 2, -0.4F, true));
		bone18.cubeList.add(new ModelBox(bone18, 0, 90, -1.0F, 1.35F, -1.0F, 2, 2, 2, -0.4F, true));
		bone18.cubeList.add(new ModelBox(bone18, 0, 114, -0.5F, 2.35F, -0.5F, 1, 3, 1, -0.3F, true));

		bone19 = new ModelRenderer(this);
		bone19.setRotationPoint(-9.9F, 7.05F, -4.4F);
		setRotationAngle(bone19, -0.6981F, 0.0F, -1.5708F);
		bone19.cubeList.add(new ModelBox(bone19, 0, 45, -1.0F, -1.45F, -1.0F, 2, 4, 2, -0.2F, false));
		bone19.cubeList.add(new ModelBox(bone19, 0, 87, -1.0F, -1.95F, -1.0F, 2, 1, 2, 0.0F, false));
		bone19.cubeList.add(new ModelBox(bone19, 0, 40, -1.0F, -3.65F, -1.0F, 2, 1, 2, -0.2F, false));
		bone19.cubeList.add(new ModelBox(bone19, 0, 82, -1.0F, -3.65F, -1.0F, 2, 3, 2, -0.4F, false));
		bone19.cubeList.add(new ModelBox(bone19, 0, 90, -1.0F, 1.35F, -1.0F, 2, 2, 2, -0.4F, false));
		bone19.cubeList.add(new ModelBox(bone19, 0, 114, -0.5F, 2.35F, -0.5F, 1, 3, 1, -0.3F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		bone.render(f5);
		bone8.render(f5);
		bone16.render(f5);
		bone17.render(f5);
		bone18.render(f5);
		bone19.render(f5);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	@Override
	public void render() {
		float f5 = 0.01f;
		
		bone.render(f5);
		bone8.render(f5); 
		bone16.render(f5);
		bone17.render(f5);
		bone18.render(f5);
		bone19.render(f5);
	}

	@Override
	public ResourceLocation getTexture() 
	{
		if(animatedTextures == null) animatedTextures = new FrameAnimator(9,"erthilia:textures/blocks","syringe_stand (frame)","png",2);
		
		ResourceLocation texture = animatedTextures.currentTexture;
		animatedTextures.next();
		return texture;
	}
}
