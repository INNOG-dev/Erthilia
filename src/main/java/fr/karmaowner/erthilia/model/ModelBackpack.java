package fr.karmaowner.erthilia.model;

import org.lwjgl.opengl.GL11;

import fr.karmaowner.erthilia.Main;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class ModelBackpack extends ModelBaseBody {
	
	private final ModelRenderer bone;
	private final ModelRenderer bone2;


	public ModelBackpack() {
		textureWidth = 64;
		textureHeight = 64;

		bone = new ModelRenderer(this);
		bone.setRotationPoint(0.0F, 24.0F, 0.0F);
		bone.cubeList.add(new ModelBox(bone, 0, 42, -2.75F, -9.5F, -1.0F, 5, 5, 1, 0.0F));
		bone.cubeList.add(new ModelBox(bone, 42, 0, -4.25F, -7.5F, 0.5F, 8, 3, 3, 0.0F));
		bone.cubeList.add(new ModelBox(bone, 42, 0, -4.25F, -11.25F, 0.5F, 8, 3, 3, 0.0F));
		bone.cubeList.add(new ModelBox(bone, 46, 18, 2.7F, -4.5F, 0.499F, 1, 1, 8, 0.0F));
		bone.cubeList.add(new ModelBox(bone, 60, 51, 2.7F, -15.8F, 8.125F, 1, 12, 1, 0.0F));
		bone.cubeList.add(new ModelBox(bone, 52, 29, 2.7F, -16.0F, 3.8719F, 1, 1, 5, 0.0F));
		bone.cubeList.add(new ModelBox(bone, 52, 29, -3.75F, -16.0F, 3.8719F, 1, 1, 5, 0.0F));
		bone.cubeList.add(new ModelBox(bone, 46, 18, -3.75F, -4.5F, 0.499F, 1, 1, 8, 0.0F));
		bone.cubeList.add(new ModelBox(bone, 60, 51, -3.75F, -16.1F, 8.125F, 1, 12, 1, 0.0F));

		bone2 = new ModelRenderer(this);
		bone2.setRotationPoint(-8.0F, -8.0F, 8.0F);
		bone.addChild(bone2);
		bone2.cubeList.add(new ModelBox(bone2, 0, 49, 4.5F, -7.0F, -8.0F, 7, 11, 4, 0.0F));
	}

	@Override
	public void render()
	{
		Minecraft.getMinecraft().getTextureManager().bindTexture(getTexture());
		bone.render(0.1F);
	}
	
	public void render(Entity entity, float limbSwing, float limbSwingAmount, float rotFloat, float rotYaw, float rotPitch, float partTicks) {
		
		 //if(Minecraft.getMinecraft().currentScreen instanceof MenuPauseUI) return;
		 
		 if(Minecraft.getMinecraft().player.isInvisible()) return;
		
		 GL11.glPushMatrix(); 
		 GL11.glColor4f(1f, 1f, 1f, 1f);
		 super.render(entity, limbSwing, limbSwingAmount, rotFloat, rotYaw, rotPitch, partTicks);
		 this.setRotationAngles(limbSwing, limbSwingAmount, rotFloat, rotYaw, rotPitch, partTicks, entity);
		 GL11.glScalef(0.68f, 0.6f, 0.68f);
		 GL11.glRotatef(180, 0, 1, 0);
		 GL11.glTranslatef(0, -0.4f, -0.6f);
		 render();
		 GL11.glPopMatrix();
	}
	
	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float rotFloat, float rotYaw, float rotPitch, float partTicks, Entity entity)
	{
		
		if(entity.isSneaking())
		{
			GL11.glRotatef(30, 1, 0, 0);
			GL11.glTranslatef(0, 0.25F, -0.30F);
		}
	    super.setRotationAngles(limbSwing, limbSwingAmount, rotFloat, rotYaw, rotPitch, partTicks, entity);
	   
	}
	
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	@Override
	public ResourceLocation getTexture() {
		return new ResourceLocation(Main.MODID + ":textures/cosmetics/backpack.png");
	}

}
