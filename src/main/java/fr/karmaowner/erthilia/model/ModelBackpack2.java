package fr.karmaowner.erthilia.model;

import org.lwjgl.opengl.GL11;

import fr.karmaowner.erthilia.Main;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class ModelBackpack2 extends ModelBaseBody {

	private final ModelRenderer bone;
	
	
	public ModelBackpack2()
	{
		textureWidth = 128;
		textureHeight = 128;

		bone = new ModelRenderer(this);
		bone.setRotationPoint(-0.0115F, 5.1231F, 2.1147F);
		setRotationAngle(bone, 0.0F, 3.1416F, 0.0F);
		bone.cubeList.add(new ModelBox(bone, 0, 49, -3.4885F, -4.1231F, -4.1147F, 7, 11, 4, 0.0F));
		bone.cubeList.add(new ModelBox(bone, 0, 49, 1.5115F, -6.1231F, -2.1147F, 1, 2, 1, 0.0F));
		bone.cubeList.add(new ModelBox(bone, 0, 49, -2.4885F, -6.1231F, -2.1147F, 1, 2, 1, 0.0F));
		bone.cubeList.add(new ModelBox(bone, 0, 2, -1.4885F, -6.1231F, -2.1147F, 3, 1, 1, 0.0F));
		bone.cubeList.add(new ModelBox(bone, 0, 42, -2.7385F, 1.3769F, -5.1147F, 5, 5, 1, 0.0F));
		bone.cubeList.add(new ModelBox(bone, 42, 0, -4.2385F, 3.3769F, -3.6147F, 8, 3, 3, 0.0F));
		bone.cubeList.add(new ModelBox(bone, 42, 0, -4.2385F, -0.3731F, -3.6147F, 8, 3, 3, 0.0F));
		bone.cubeList.add(new ModelBox(bone, 46, 18, 2.7115F, 6.3769F, -3.6158F, 1, 1, 8, 0.0F));
		bone.cubeList.add(new ModelBox(bone, 60, 51, 2.7115F, -4.9231F, 4.0103F, 1, 12, 1, 0.0F));
		bone.cubeList.add(new ModelBox(bone, 52, 29, 2.7115F, -5.1231F, -0.2429F, 1, 1, 5, 0.0F));
		bone.cubeList.add(new ModelBox(bone, 52, 29, -3.7385F, -5.1231F, -0.2429F, 1, 1, 5, 0.0F));
		bone.cubeList.add(new ModelBox(bone, 46, 18, -3.7385F, 6.3769F, -3.6158F, 1, 1, 8, 0.0F));
		bone.cubeList.add(new ModelBox(bone, 60, 51, -3.7385F, -5.2231F, 4.0103F, 1, 12, 1, 0.0F));
	}
	
	public void render(Entity entity, float limbSwing, float limbSwingAmount, float rotFloat, float rotYaw, float rotPitch, float partTicks) {
		
		 //if(Minecraft.getMinecraft().currentScreen instanceof MenuPauseUI) return;
		 
		 if(Minecraft.getMinecraft().player.isInvisible()) return;
		
		 GL11.glPushMatrix();
		 GL11.glColor4f(1f, 1f, 1f, 1f);
		 super.render(entity, limbSwing, limbSwingAmount, rotFloat, rotYaw, rotPitch, partTicks);
		 this.setRotationAngles(limbSwing, limbSwingAmount, rotFloat, rotYaw, rotPitch, partTicks, entity);
		 GL11.glScalef(0.68f, 0.6f, 0.68f);
		 GL11.glTranslatef(0, 0.3F, 0);
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
	
	@Override
	public void render()
	{
		Minecraft.getMinecraft().getTextureManager().bindTexture(getTexture());
		bone.render(0.1F);
	}
	
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) 
	{
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	@Override
	public ResourceLocation getTexture() {
		return new ResourceLocation(Main.MODID + ":textures/cosmetics/backpack_2.png");
	}
	
}
