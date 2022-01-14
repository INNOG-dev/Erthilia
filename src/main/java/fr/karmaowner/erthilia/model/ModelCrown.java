package fr.karmaowner.erthilia.model;

import org.lwjgl.opengl.GL11;

import fr.karmaowner.erthilia.Main;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public class ModelCrown extends ModelBaseHead 
{
	
	private final ModelRenderer bone;
	private final ModelRenderer bone4;
	private final ModelRenderer bone3;
	private final ModelRenderer bone2;
	private final ModelRenderer head;
	private final ModelRenderer body;
	private final ModelRenderer leftArm;
	private final ModelRenderer rightArm;
	private final ModelRenderer leftLeg;
	private final ModelRenderer rightLeg;
	
	public ModelCrown() {
		textureWidth = 32;
		textureHeight = 32;

		bone = new ModelRenderer(this);
		bone.setRotationPoint(0.0F, 24.0F, 0.0F);
		bone.cubeList.add(new ModelBox(bone, 14, 9, -4.0F, -33.0F, -5.0F, 8, 1, 1, 0.0F));
		bone.cubeList.add(new ModelBox(bone, 0, 0, 3.0F, -34.0F, -5.0F, 1, 1, 1, 0.0F));
		bone.cubeList.add(new ModelBox(bone, 0, 0, 3.0F, -34.0F, 4.0F, 1, 1, 1, 0.0F));
		bone.cubeList.add(new ModelBox(bone, 0, 0, -4.0F, -34.0F, 4.0F, 1, 1, 1, 0.0F));
		bone.cubeList.add(new ModelBox(bone, 0, 0, -4.0F, -34.0F, -5.0F, 1, 1, 1, 0.0F));
		bone.cubeList.add(new ModelBox(bone, 0, 7, -2.0F, -34.0F, -5.0F, 4, 1, 1, 0.0F));
		bone.cubeList.add(new ModelBox(bone, 26, 13, -1.0F, -35.0F, -5.0F, 2, 1, 1, 0.0F));
		bone.cubeList.add(new ModelBox(bone, 0, 5, -1.0F, -34.0F, -5.5F, 2, 1, 1, 0.0F));
		bone.cubeList.add(new ModelBox(bone, 18, 0, -0.5F, -35.0F, -5.5F, 1, 1, 1, 0.0F));
		bone.cubeList.add(new ModelBox(bone, 20, 11, -1.5F, -36.0F, -5.0F, 3, 1, 1, 0.0F));
		bone.cubeList.add(new ModelBox(bone, 28, 11, -0.5F, -37.0F, -5.0F, 1, 1, 1, 0.0F));
		bone.cubeList.add(new ModelBox(bone, 2, 11, -4.0F, -33.0F, 4.0F, 8, 1, 1, 0.0F));
		bone.cubeList.add(new ModelBox(bone, 0, 7, -2.0F, -34.0F, 4.5F, 4, 1, 1, 0.0F));
		bone.cubeList.add(new ModelBox(bone, 26, 0, -1.0F, -35.0F, 4.5F, 2, 1, 1, 0.0F));
		bone.cubeList.add(new ModelBox(bone, 14, 0, 4.0F, -33.0F, -4.0F, 1, 1, 8, 0.0F));
		bone.cubeList.add(new ModelBox(bone, 14, 0, -5.0F, -33.0F, -4.0F, 1, 1, 8, 0.0F));

		bone4 = new ModelRenderer(this);
		bone4.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone.addChild(bone4);
		bone4.cubeList.add(new ModelBox(bone4, 0, 0, -5.5F, -34.0F, -2.0F, 1, 1, 4, 0.0F));
		bone4.cubeList.add(new ModelBox(bone4, 26, 2, -5.5F, -35.0F, -1.0F, 1, 1, 2, 0.0F));

		bone3 = new ModelRenderer(this);
		bone3.setRotationPoint(-4.5F, -34.0F, 0.0F);
		setRotationAngle(bone3, 0.0F, -1.5708F, 0.0F);
		bone.addChild(bone3);

		bone2 = new ModelRenderer(this);
		bone2.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone.addChild(bone2);
		bone2.cubeList.add(new ModelBox(bone2, 0, 0, 4.5F, -34.0F, -2.0F, 1, 1, 4, 0.0F));
		bone2.cubeList.add(new ModelBox(bone2, 26, 2, 4.5F, -35.0F, -1.0F, 1, 1, 2, 0.0F));
		bone2.cubeList.add(new ModelBox(bone2, 28, 5, 4.5F, -36.0F, -0.5F, 1, 1, 1, 0.0F));
		bone2.cubeList.add(new ModelBox(bone2, 18, 2, -5.5F, -36.0F, -0.5F, 1, 1, 1, 0.0F));
		bone2.cubeList.add(new ModelBox(bone2, 18, 4, -0.5F, -36.0F, 4.5F, 1, 1, 1, 0.0F));

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, 0.0F, 0.0F);

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 0.0F, 0.0F);

		leftArm = new ModelRenderer(this);
		leftArm.setRotationPoint(-5.0F, 2.0F, 0.0F);

		rightArm = new ModelRenderer(this);
		rightArm.setRotationPoint(5.0F, 2.0F, 0.0F);

		leftLeg = new ModelRenderer(this);
		leftLeg.setRotationPoint(-1.9F, 12.0F, 0.0F);

		rightLeg = new ModelRenderer(this);
		rightLeg.setRotationPoint(1.9F, 12.0F, 0.0F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		 if(Minecraft.getMinecraft().player.isInvisible()) return;
		 
		 GL11.glPushMatrix();
		 GL11.glColor4f(1f, 1f, 1f, 1f);
		 super.render(entity, f, f1, f2, f3, f4, f5);
		 this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		 GL11.glScalef(0.5f, 0.5f, 0.5f);
		 render();
		 GL11.glPopMatrix();
	}
	
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
	{
		EntityPlayer player = (EntityPlayer) entity;

		GL11.glRotatef(entity.rotationPitch, 1, 0, 0);
		

		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		
		GL11.glTranslatef(0, 0.05f, -0.08f);

		if(!player.inventory.armorItemInSlot(3).isEmpty())
		{				  
			GL11.glTranslatef(0, -0.1f, 0);   
		}
		
		

			
	}
	
	 @Override
	 public void render()
	 {
		Minecraft.getMinecraft().getTextureManager().bindTexture(getTexture());
		bone.render(0.1F);
		head.render(0.1F);
		body.render(0.1F);
		leftArm.render(0.1F);
		rightArm.render(0.1F);
		leftLeg.render(0.1F);
		rightLeg.render(0.1F);
	 }
	 
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	@Override
	public ResourceLocation getTexture() 
	{
		return new ResourceLocation(Main.MODID + ":textures/cosmetics/crown.png");
	}

	
}
