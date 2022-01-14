package fr.karmaowner.erthilia.model;

import org.lwjgl.opengl.GL11;

import fr.karmaowner.erthilia.Main;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public class ModelMexicanHat extends ModelBaseHead {
	
	private final ModelRenderer head;
	private final ModelRenderer body;
	private final ModelRenderer leftArm;
	private final ModelRenderer rightArm;
	private final ModelRenderer leftLeg;
	private final ModelRenderer rightLeg;
	

	public ModelMexicanHat() {
		textureWidth = 64;
		textureHeight = 64;

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, 0.0F, 0.0F);
		head.cubeList.add(new ModelBox(head, 0, 17, -8.0F, -9.0F, -8.0F, 16, 2, 16, 0.0F));
		head.cubeList.add(new ModelBox(head, 0, 0, -3.5F, -13.0F, -3.5F, 7, 4, 7, 0.0F));
		head.cubeList.add(new ModelBox(head, 44, 0, -2.5F, -15.0F, -2.5F, 5, 2, 5, 0.0F));

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

		if(player.inventory.armorItemInSlot(3).isEmpty())
		{
			GL11.glTranslatef(0, 0, 0);
		}
		else
		{
			GL11.glTranslatef(0, -0.1f, 0);
		}
			
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		
		
	}
	
	 @Override
	 public void render()
	 {
		Minecraft.getMinecraft().getTextureManager().bindTexture(getTexture());
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
		return new ResourceLocation(Main.MODID + ":textures/cosmetics/mexican_hat.png");
	}

}
