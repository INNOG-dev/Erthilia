package fr.karmaowner.erthilia.model;

import org.lwjgl.opengl.GL11;

import fr.karmaowner.erthilia.Main;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public class ModelAfro extends ModelBaseHead {

	private final ModelRenderer head;
	private final ModelRenderer bone;
	private final ModelRenderer body;
	private final ModelRenderer leftArm;
	private final ModelRenderer rightArm;
	private final ModelRenderer leftLeg;
	private final ModelRenderer rightLeg;
	
	
	public ModelAfro() {
		textureWidth = 64;
		textureHeight = 64;

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, -1.0F, 0.0F);
		head.cubeList.add(new ModelBox(head, 22, 0, -5.0F, -13.0F, -5.0F, 10, 7, 11, 0.0F));
		head.cubeList.add(new ModelBox(head, 22, 19, -6.0F, -13.0F, -4.0F, 12, 7, 9, 0.0F));
		head.cubeList.add(new ModelBox(head, 0, 0, 5.0F, -6.0F, 0.0F, 1, 3, 4, 0.0F));
		head.cubeList.add(new ModelBox(head, 0, 0, -6.0F, -6.0F, 0.0F, 1, 3, 4, 0.0F));
		head.cubeList.add(new ModelBox(head, 0, 37, -4.0F, -15.0F, -4.0F, 8, 2, 9, 0.0F));
		head.cubeList.add(new ModelBox(head, 46, 52, -4.0F, -14.0F, -5.0F, 8, 1, 1, 0.0F));
		head.cubeList.add(new ModelBox(head, 46, 52, -4.0F, -14.0F, 5.0F, 8, 1, 1, 0.0F));
		head.cubeList.add(new ModelBox(head, 44, 54, 4.0F, -14.0F, -4.0F, 1, 1, 9, 0.0F));
		head.cubeList.add(new ModelBox(head, 44, 54, -5.0F, -14.0F, -4.0F, 1, 1, 9, 0.0F));
		head.cubeList.add(new ModelBox(head, 30, 9, -5.0F, -6.0F, -1.0F, 10, 3, 7, 0.0F));
		head.cubeList.add(new ModelBox(head, 30, 0, -5.0F, -3.0F, -2.0F, 10, 2, 7, 0.0F));

		bone = new ModelRenderer(this);
		bone.setRotationPoint(0.0F, 24.0F, 0.0F);
		head.addChild(bone);

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
		if(player.inventory.armorItemInSlot(3) != null)
		{
			head.offsetY = -0.2f;
		}
				
		if(entity.rotationPitch > 56.69f)
		{
			GL11.glRotatef(-1, 1, 0, 0);
		}
		else if(entity.rotationPitch < -56f)
		{
			GL11.glRotatef(1, 1, 0, 0);
		}
		GL11.glRotatef(entity.rotationPitch, 1, 0, 0);
			
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	}
	
	public void render()
	{
		Minecraft.getMinecraft().getTextureManager().bindTexture(getTexture());

		GL11.glColor4f(1f, 1f, 1f, 1f);
		
		head.render(0.1F);
	}
	
	
	
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	@Override
	public ResourceLocation getTexture() {
		return new ResourceLocation(Main.MODID + ":textures/cosmetics/afro.png");
	}
	
}
