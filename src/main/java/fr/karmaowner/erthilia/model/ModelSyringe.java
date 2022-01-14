package fr.karmaowner.erthilia.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelSyringe extends ModelBase {

	private final ModelRenderer bone;

	public ModelSyringe() {
		textureWidth = 128;
		textureHeight = 128;

		bone = new ModelRenderer(this);
		bone.setRotationPoint(0.0F, 12.0F, 0.0F);
		bone.cubeList.add(new ModelBox(bone, 0, 102, -3.0F, -23.0F, -2.0F, 5, 21, 5, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 14, -3.0F, -22.4F, -2.0F, 5, 21, 5, -0.2F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 79, -2.0F, -1.0F, -1.0F, 3, 8, 3, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 91, -5.0F, -2.0F, -4.0F, 9, 2, 9, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 70, -4.0F, 7.0F, -3.0F, 7, 2, 7, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 64, -2.5F, -24.4F, -1.5F, 4, 2, 4, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 44, -1.0F, -40.4F, 0.0F, 1, 16, 1, 0.0F, false));
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
