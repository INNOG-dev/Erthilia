package fr.karmaowner.erthilia.model;

import org.lwjgl.opengl.GL11;

import fr.karmaowner.erthilia.Main;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class ModelSunGlasses extends ModelBaseHead 
{

	private final ModelRenderer head;
	private final ModelRenderer bone3;
	private final ModelRenderer bone;
	private final ModelRenderer bone2;
		

	public ModelSunGlasses() {
		textureWidth = 32;
		textureHeight = 32;

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, 0.0F, 0.0F);
		head.cubeList.add(new ModelBox(head, 23, 6, -1.0F, -5.5F, -5.0F, 2, 2, 0, 0.0F));
		head.cubeList.add(new ModelBox(head, 2, 0, 4.0F, -4.5F, -4.5F, 1, 1, 5, 0.0F));
		head.cubeList.add(new ModelBox(head, 2, 0, -5.0F, -4.5F, -4.5F, 1, 1, 5, 0.0F));
		head.cubeList.add(new ModelBox(head, 24, 0, -4.0F, -5.0F, -5.0F, 3, 2, 1, 0.0F));

		bone3 = new ModelRenderer(this);
		bone3.setRotationPoint(0.0F, 0.0F, 0.0F);
		head.addChild(bone3);
		bone3.cubeList.add(new ModelBox(bone3, 24, 0, 1.0F, -5.0F, -5.0F, 3, 2, 1, 0.0F));

		bone = new ModelRenderer(this);
		bone.setRotationPoint(-4.5F, -4.0F, 1.0F);
		setRotationAngle(bone, -0.7854F, 0.0F, 0.0F);
		head.addChild(bone);
		bone.cubeList.add(new ModelBox(bone, 10, 0, -0.5F, 0.0F, -0.7F, 1, 1, 3, 0.0F));

		bone2 = new ModelRenderer(this);
		bone2.setRotationPoint(-4.5F, -4.0F, 1.0F);
		setRotationAngle(bone2, -0.7854F, 0.0F, 0.0F);
		head.addChild(bone2);
		bone2.cubeList.add(new ModelBox(bone2, 10, 0, 8.5F, 0.0F, -0.7F, 1, 1, 3, 0.0F));
	}

	@Override
	public void render()
	{
		Minecraft.getMinecraft().getTextureManager().bindTexture(getTexture());
		head.render(0.1F);
	}
	
	public void render(Entity entity, float limbSwing, float limbSwingAmount, float rotFloat, float rotYaw, float rotPitch, float partTicks) {
		 if(Minecraft.getMinecraft().player.isInvisible()) return;
		 
		 GL11.glPushMatrix();
		 GL11.glColor4f(1f, 1f, 1f, 1f);
		 super.render(entity, limbSwing, limbSwingAmount, rotFloat, rotYaw, rotPitch, partTicks);
		 this.setRotationAngles(limbSwing, limbSwingAmount, rotFloat, rotYaw, rotPitch, partTicks, entity);
		 GL11.glScalef(0.6f, 0.6f, 0.6f);
		 render();
		 GL11.glPopMatrix();
	}
	

	
	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float rotFloat, float rotYaw, float rotPitch, float partTicks, Entity entity)
	{
	    super.setRotationAngles(limbSwing, limbSwingAmount, rotFloat, rotYaw, rotPitch, partTicks, entity);

	    
	    GL11.glRotatef(entity.rotationPitch, 1, 0, 0);
	    GL11.glTranslatef(0, 0.068f, -0.05f);
	    
	}

	
	
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	@Override
	public ResourceLocation getTexture() {
		return new ResourceLocation(Main.MODID + ":textures/cosmetics/sun_glasses.png");
	}
	
}
