package fr.karmaowner.erthilia.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class ModelSpear extends ModelBase
{

	public final ResourceLocation texture = new ResourceLocation("erthilia","textures/items/spear.png");
	
	
	private final ModelRenderer bone;
	private final ModelRenderer bone3;
	private final ModelRenderer bone2;
	
	public ModelSpear() {
		textureWidth = 128;
		textureHeight = 64;

		bone = new ModelRenderer(this);
		bone.setRotationPoint(0.0F, 22.9F, 0.0F);
		bone.cubeList.add(new ModelBox(bone, 0, 36, -1.0F, -25.5F, -1.0F, 2, 25, 2, -0.4F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 24, -1.0F, -24.0F, -1.0F, 2, 4, 2, -0.2F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 30, -2.0F, -2.7F, -1.5F, 4, 3, 3, -0.5F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 11, -2.0F, -32.7F, -1.0F, 4, 6, 2, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 19, -1.5F, -27.7F, -1.0F, 3, 3, 2, -0.1F, false));

		bone3 = new ModelRenderer(this);
		bone3.setRotationPoint(0.5F, -22.8F, 0.0F);
		bone.addChild(bone3);
		setRotationAngle(bone3, 0.0F, 0.0F, -0.48F);
		bone3.cubeList.add(new ModelBox(bone3, 11, 0, -1.0F, -0.5F, 0.0F, 2, 7, 0, 0.0F, false));

		bone2 = new ModelRenderer(this);
		bone2.setRotationPoint(0.0F, -32.7F, 0.0F);
		bone.addChild(bone2);
		setRotationAngle(bone2, 0.0F, 0.0F, 0.7854F);
		bone2.cubeList.add(new ModelBox(bone2, 0, 0, -1.5F, -1.5F, -1.0F, 3, 3, 2, -0.1F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		bone.render(f5);
	}
	
	public void render()
	{
		bone.render(0.065F);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
	
}
