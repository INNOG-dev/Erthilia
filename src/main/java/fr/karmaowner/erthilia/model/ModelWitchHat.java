package fr.karmaowner.erthilia.model;

import org.lwjgl.opengl.GL11;

import fr.karmaowner.erthilia.Main;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class ModelWitchHat extends ModelBaseHead
{
	public final ModelRenderer head;
	private final ModelRenderer bone;
	private final ModelRenderer bone2;
	private final ModelRenderer bone3;
	private final ModelRenderer bone4;
			
	public ModelWitchHat() {
		textureWidth = 64;
		textureHeight = 64;
	
		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, 0.0F, 0.0F);
		head.cubeList.add(new ModelBox(head, 0, 50, -6.0F, -10.0F, -6.0F, 12, 2, 12, 0.0F));
		head.cubeList.add(new ModelBox(head, 0, 27, -4.0F, -14.0F, -4.0F, 8, 4, 8, 0.0F));
		head.cubeList.add(new ModelBox(head, 0, 39, -4.5F, -12.5F, -4.5F, 9, 2, 9, 0.0F));
		head.cubeList.add(new ModelBox(head, 0, 16, -1.5F, -13.0F, -5.0F, 3, 3, 1, 0.0F));
	
		bone = new ModelRenderer(this);
		bone.setRotationPoint(0.0F, -14.5F, 0.0F);
		setRotationAngle(bone, -0.1745F, 0.0F, 0.0F);
		head.addChild(bone);
		bone.cubeList.add(new ModelBox(bone, 36, 53, -3.0F, -1.5F, -3.0F, 6, 3, 6, 0.0F));
	
		bone2 = new ModelRenderer(this);
		bone2.setRotationPoint(0.0F, -14.5F, 0.0F);
		setRotationAngle(bone2, -0.4363F, 0.0F, 0.0F);
		head.addChild(bone2);
		bone2.cubeList.add(new ModelBox(bone2, 48, 45, -2.0F, -3.5F, -2.0F, 4, 4, 4, 0.0F));
	
		bone3 = new ModelRenderer(this);
		bone3.setRotationPoint(0.0F, -19.0F, 0.5F);
		setRotationAngle(bone3, -0.8727F, 0.0F, 0.0F);
		head.addChild(bone3);
		bone3.cubeList.add(new ModelBox(bone3, 54, 38, -1.0F, -2.0F, 0.5F, 2, 4, 3, 0.0F));
	
		bone4 = new ModelRenderer(this);
		bone4.setRotationPoint(0.0F, -21.0F, 3.0F);
		setRotationAngle(bone4, -1.309F, 0.0F, 0.0F);
		head.addChild(bone4);
		bone4.cubeList.add(new ModelBox(bone4, 27, 0, -0.5F, -3.0F, 1.5F, 1, 4, 2, 0.0F));
	}
	
	 //Render in Entity
	 @Override
	 public void render(Entity entity, float limbSwing, float limbSwingAmount, float rotFloat, float rotYaw, float rotPitch, float partTicks) {		 
		 if(Minecraft.getMinecraft().player.isInvisible()) return;
		 
		 GL11.glPushMatrix();
		 GL11.glColor4f(1f, 1f, 1f, 1f);
		 super.render(entity, limbSwing, limbSwingAmount, rotFloat, rotYaw, rotPitch, partTicks);
		 this.setRotationAngles(limbSwing, limbSwingAmount, rotFloat, rotYaw, rotPitch, partTicks, entity);
		 GL11.glScalef(0.5f, 0.5f, 0.5f);
		 render();
		 GL11.glPopMatrix();
	 }
	 

	 
	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float rotFloat, float rotYaw, float rotPitch, float partTicks, Entity entity)
	{ 
			
		EntityPlayer player = (EntityPlayer) entity;

	
			
		GL11.glRotatef(entity.rotationPitch, 1, 0, 0);
		if(player.inventory.armorItemInSlot(3) != ItemStack.EMPTY)
		{
			GL11.glTranslatef(0, 0f, 0);

		}
		else
		{
			GL11.glTranslatef(0, 0.05f, 0);
		}
			
		super.setRotationAngles(limbSwing, limbSwingAmount, rotFloat, rotYaw, rotPitch, partTicks, entity);
		
	    
	}
	 
	@Override
	public void render()
	{
		 Minecraft.getMinecraft().getTextureManager().bindTexture(getTexture());

		 head.render(0.1F);
	}
	
	 public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	 }

	@Override
	public ResourceLocation getTexture() {
		return new ResourceLocation(Main.MODID + ":textures/cosmetics/witch_hat.png");
	}

}
