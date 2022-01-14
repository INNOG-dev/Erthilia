package fr.karmaowner.erthilia.model;

import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class ModelReversedCraftingTable extends ErthiliaModelBase {
	
	private final ModelRenderer bone;
	private final ModelRenderer bone11;
	private final ModelRenderer bone12;
	private final ModelRenderer bone6;
	private final ModelRenderer bone8;
	private final ModelRenderer bone2;
	private final ModelRenderer bone3;
	private final ModelRenderer bone4;
	private final ModelRenderer bone5;
	private final ModelRenderer bougie1;
	private final ModelRenderer bougie3;
	private final ModelRenderer bougie4;
	private final ModelRenderer bougie5;
	private final ModelRenderer bougie6;
	private final ModelRenderer bone7;
	private final ModelRenderer bone9;
	private final ModelRenderer bone10;
	
	private ResourceLocation texture = new ResourceLocation("erthilia","textures/blocks/reversed_crafting_table.png");
	

	public ModelReversedCraftingTable() {
		textureWidth = 128;
		textureHeight = 128;

		bone = new ModelRenderer(this);
		bone.setRotationPoint(0.0F, 25.0F, 0.0F);
		bone.cubeList.add(new ModelBox(bone, 0, 82, -15.0F, -14.0F, -8.0F, 30, 3, 16, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 24, 63, -5.0F, -14.05F, -8.0F, 10, 0, 10, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 26, 24, -5.5F, -16.55F, -8.75F, 11, 4, 11, -1.0F, false));
		bone.cubeList.add(new ModelBox(bone, 26, 48, -5.0F, -14.05F, -8.03F, 10, 11, 0, 0.0F, false));

		bone11 = new ModelRenderer(this);
		bone11.setRotationPoint(-1.5F, -16.05F, -0.75F);
		bone.addChild(bone11);
		setRotationAngle(bone11, 0.0F, 0.5236F, 0.0F);
		bone11.cubeList.add(new ModelBox(bone11, 46, 53, -0.5F, -0.5F, -3.25F, 1, 1, 4, 0.0F, false));
		bone11.cubeList.add(new ModelBox(bone11, 45, 49, -1.5F, -0.5F, 0.75F, 3, 1, 1, 0.0F, false));

		bone12 = new ModelRenderer(this);
		bone12.setRotationPoint(4.0F, 0.0F, 0.5F);
		bone11.addChild(bone12);
		setRotationAngle(bone12, 0.0F, 0.4363F, 0.0F);
		bone12.cubeList.add(new ModelBox(bone12, 52, 45, -0.5F, -0.5F, -2.35F, 1, 1, 3, -0.2F, false));
		bone12.cubeList.add(new ModelBox(bone12, 45, 49, -0.5F, -0.5F, 0.35F, 1, 1, 1, 0.0F, false));

		bone6 = new ModelRenderer(this);
		bone6.setRotationPoint(8.0F, -14.3833F, 3.75F);
		bone.addChild(bone6);
		setRotationAngle(bone6, 0.0F, 0.7418F, 0.0F);
		bone6.cubeList.add(new ModelBox(bone6, 0, 72, -3.0F, 0.3333F, -4.0F, 6, 0, 8, 0.0F, false));
		bone6.cubeList.add(new ModelBox(bone6, 0, 68, -3.0F, -0.6667F, -4.0F, 6, 1, 1, 0.0F, false));
		bone6.cubeList.add(new ModelBox(bone6, 0, 68, -3.0F, -0.6667F, 3.0F, 6, 1, 1, 0.0F, false));

		bone8 = new ModelRenderer(this);
		bone8.setRotationPoint(-9.0F, -0.1667F, -7.8333F);
		bone6.addChild(bone8);
		setRotationAngle(bone8, 0.0F, 0.7418F, 0.0F);
		bone8.cubeList.add(new ModelBox(bone8, 0, 68, -3.0F, -0.5F, 0.0833F, 6, 1, 1, 0.0F, false));
		bone8.cubeList.add(new ModelBox(bone8, 0, 68, -3.0F, -0.5F, -0.4167F, 6, 1, 1, -0.2F, false));
		bone8.cubeList.add(new ModelBox(bone8, 0, 68, -3.0F, -0.5F, -1.1667F, 6, 1, 1, 0.0F, false));

		bone2 = new ModelRenderer(this);
		bone2.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone.addChild(bone2);
		bone2.cubeList.add(new ModelBox(bone2, 0, 85, 10.0F, -11.0F, -7.0F, 3, 10, 3, 0.0F, false));
		bone2.cubeList.add(new ModelBox(bone2, 0, 0, 11.0F, -6.0F, -4.0F, 1, 2, 8, 0.0F, false));
		bone2.cubeList.add(new ModelBox(bone2, 0, 0, -12.0F, -6.0F, -4.0F, 1, 2, 8, 0.0F, false));
		bone2.cubeList.add(new ModelBox(bone2, 45, 75, 9.0F, -2.9F, -8.0F, 5, 2, 5, 0.0F, false));

		bone3 = new ModelRenderer(this);
		bone3.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone.addChild(bone3);
		bone3.cubeList.add(new ModelBox(bone3, 0, 85, -13.0F, -11.0F, -7.0F, 3, 10, 3, 0.0F, true));
		bone3.cubeList.add(new ModelBox(bone3, 45, 75, -14.0F, -2.9F, -8.0F, 5, 2, 5, 0.0F, true));

		bone4 = new ModelRenderer(this);
		bone4.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone.addChild(bone4);
		bone4.cubeList.add(new ModelBox(bone4, 0, 85, -13.0F, -11.0F, 4.0F, 3, 10, 3, 0.0F, true));
		bone4.cubeList.add(new ModelBox(bone4, 45, 75, -14.0F, -2.9F, 3.0F, 5, 2, 5, 0.0F, true));

		bone5 = new ModelRenderer(this);
		bone5.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone.addChild(bone5);
		bone5.cubeList.add(new ModelBox(bone5, 0, 85, 10.0F, -11.0F, 4.0F, 3, 10, 3, 0.0F, false));
		bone5.cubeList.add(new ModelBox(bone5, 45, 75, 9.0F, -2.9F, 3.0F, 5, 2, 5, 0.0F, false));

		bougie1 = new ModelRenderer(this);
		bougie1.setRotationPoint(8.9583F, 9.0F, -5.9167F);
		bougie1.cubeList.add(new ModelBox(bougie1, 0, 38, -0.9583F, -3.0F, -1.0833F, 3, 5, 3, 0.0F, false));
		bougie1.cubeList.add(new ModelBox(bougie1, 0, 14, -0.9583F, 1.0F, -0.0833F, 4, 1, 3, 0.0F, false));
		bougie1.cubeList.add(new ModelBox(bougie1, 0, 24, -0.9583F, 0.0F, -2.0833F, 3, 2, 1, 0.0F, false));
		bougie1.cubeList.add(new ModelBox(bougie1, 0, 24, 1.0417F, 1.5F, -3.0833F, 1, 2, 1, 0.0F, false));
		bougie1.cubeList.add(new ModelBox(bougie1, 0, 50, -1.9583F, 1.5F, -3.0833F, 2, 1, 1, 0.0F, false));
		bougie1.cubeList.add(new ModelBox(bougie1, 0, 21, -1.9583F, 1.0F, -2.0833F, 1, 1, 2, 0.0F, false));
		bougie1.cubeList.add(new ModelBox(bougie1, 0, 18, -1.7083F, 0.0F, -0.0833F, 1, 2, 1, 0.0F, false));
		bougie1.cubeList.add(new ModelBox(bougie1, 0, 30, -0.9583F, -6.0F, 0.4167F, 3, 3, 0, 0.0F, false));

		bougie3 = new ModelRenderer(this);
		bougie3.setRotationPoint(-9.0417F, 9.0F, 6.0833F);
		setRotationAngle(bougie3, 0.0F, 3.1416F, 0.0F);
		bougie3.cubeList.add(new ModelBox(bougie3, 0, 38, -0.9583F, -3.0F, -1.0833F, 3, 5, 3, 0.0F, false));
		bougie3.cubeList.add(new ModelBox(bougie3, 0, 14, -0.9583F, 1.0F, -0.0833F, 4, 1, 3, 0.0F, false));
		bougie3.cubeList.add(new ModelBox(bougie3, 0, 24, -0.9583F, 0.0F, -2.0833F, 3, 2, 1, 0.0F, false));
		bougie3.cubeList.add(new ModelBox(bougie3, 0, 24, 1.0417F, 1.5F, -3.0833F, 1, 2, 1, 0.0F, false));
		bougie3.cubeList.add(new ModelBox(bougie3, 0, 50, -1.9583F, 1.5F, -3.0833F, 2, 1, 1, 0.0F, false));
		bougie3.cubeList.add(new ModelBox(bougie3, 0, 21, -1.9583F, 1.0F, -2.0833F, 1, 1, 2, 0.0F, false));
		bougie3.cubeList.add(new ModelBox(bougie3, 0, 18, -1.7083F, 0.0F, -0.0833F, 1, 2, 1, 0.0F, false));
		bougie3.cubeList.add(new ModelBox(bougie3, 0, 30, -0.9583F, -6.0F, 0.4167F, 3, 3, 0, 0.0F, false));

		bougie4 = new ModelRenderer(this);
		bougie4.setRotationPoint(-13.0417F, 9.0F, 5.0833F);
		bougie4.cubeList.add(new ModelBox(bougie4, 0, 33, -0.9583F, -1.0F, -1.0833F, 2, 3, 2, 0.0F, false));
		bougie4.cubeList.add(new ModelBox(bougie4, 40, 105, -1.9583F, 2.0F, -4.0833F, 5, 0, 6, 0.0F, false));
		bougie4.cubeList.add(new ModelBox(bougie4, 0, 27, -1.4583F, -4.0F, -0.0833F, 2, 3, 0, 0.0F, false));

		bougie5 = new ModelRenderer(this);
		bougie5.setRotationPoint(0.9583F, 9.0F, 5.0833F);
		bougie5.cubeList.add(new ModelBox(bougie5, 8, 32, -0.9583F, -4.0F, -1.0833F, 2, 6, 2, 0.0F, false));
		bougie5.cubeList.add(new ModelBox(bougie5, 40, 105, -1.9583F, 2.0F, -4.0833F, 5, 0, 6, 0.0F, false));
		bougie5.cubeList.add(new ModelBox(bougie5, 10, 25, -1.4583F, -7.0F, -0.0833F, 2, 3, 0, 0.0F, false));

		bougie6 = new ModelRenderer(this);
		bougie6.setRotationPoint(-1.0417F, 9.0F, 4.0833F);
		bougie6.cubeList.add(new ModelBox(bougie6, 0, 33, -0.9583F, -1.0F, -1.0833F, 2, 3, 2, 0.0F, false));
		bougie6.cubeList.add(new ModelBox(bougie6, 9, 46, 1.0417F, 1.0F, -1.0833F, 3, 1, 2, 0.0F, false));
		bougie6.cubeList.add(new ModelBox(bougie6, 9, 46, 0.0417F, 1.0F, 0.9167F, 3, 1, 2, 0.0F, false));
		bougie6.cubeList.add(new ModelBox(bougie6, 40, 105, -1.9583F, 2.0F, -4.0833F, 5, 0, 6, 0.0F, false));
		bougie6.cubeList.add(new ModelBox(bougie6, 0, 27, -1.4583F, -4.0F, -0.0833F, 2, 3, 0, 0.0F, false));

		bone7 = new ModelRenderer(this);
		bone7.setRotationPoint(-13.5F, 10.75F, 1.4F);
		setRotationAngle(bone7, 0.0F, -0.48F, 0.0F);
		

		bone9 = new ModelRenderer(this);
		bone9.setRotationPoint(-1.25F, 1.0F, -4.5F);
		bone7.addChild(bone9);
		setRotationAngle(bone9, 0.0F, 0.0F, -0.1309F);
		bone9.cubeList.add(new ModelBox(bone9, 0, 104, -2.4F, -3.0F, -3.5F, 5, 2, 7, 0.0F, false));

		bone10 = new ModelRenderer(this);
		bone10.setRotationPoint(3.25F, 1.0F, -4.5F);
		bone7.addChild(bone10);
		setRotationAngle(bone10, 0.0F, 0.0F, 0.1309F);
		bone10.cubeList.add(new ModelBox(bone10, 0, 104, -2.6F, -3.0F, -3.5F, 5, 2, 7, 0.0F, true));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		bone.render(f5);
		bougie1.render(f5);
		bougie3.render(f5);
		bougie4.render(f5);
		bougie5.render(f5);
		bougie6.render(f5);
		bone7.render(f5);
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
		bougie1.render(f5);
		bougie3.render(f5);
		bougie4.render(f5);
		bougie5.render(f5);
		bougie6.render(f5);
		bone7.render(f5);
	}

	@Override
	public ResourceLocation getTexture() 
	{
		return texture;
	}
}
