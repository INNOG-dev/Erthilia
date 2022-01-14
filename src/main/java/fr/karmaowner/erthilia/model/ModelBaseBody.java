package fr.karmaowner.erthilia.model;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public abstract class ModelBaseBody extends ErthiliaModelBase {

	
	 public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	 }
	 
	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float rotFloat, float rotYaw, float rotPitch, float partTicks, Entity entity)
	{
		super.setRotationAngles(limbSwing, limbSwingAmount, rotFloat, rotYaw, rotPitch, partTicks, entity);
	}
	 
	 @Override
	 public void render(Entity entity, float limbSwing, float limbSwingAmount, float rotFloat, float rotYaw, float rotPitch, float partTicks) {
		 	super.setRotationAngles(limbSwing, limbSwingAmount, rotFloat, rotYaw, rotPitch, partTicks, entity);
		 
	        
	        EntityPlayer player = (EntityPlayer) entity;
	        
	        float yawOffset = player.prevRenderYawOffset + (player.renderYawOffset - player.prevRenderYawOffset) * partTicks;
	        EntityPlayer localPlayer = Minecraft.getMinecraft().player;
	        EntityPlayer renderTarget = player;
	        
	        double tarX1 = renderTarget.prevPosX + (renderTarget.posX - renderTarget.prevPosX) * partTicks;
	        double tarX2 = localPlayer.prevPosX + (localPlayer.posX - localPlayer.prevPosX) * partTicks;
	        
	        double tarY1 = renderTarget.prevPosY + (renderTarget.posY - renderTarget.prevPosY) * partTicks;
	        double tarY2 = localPlayer.prevPosY + (localPlayer.posY - localPlayer.prevPosY) * partTicks;
	        
	        double tarZ1 = renderTarget.prevPosZ + (renderTarget.posZ - renderTarget.prevPosZ) * partTicks;
	        double tarZ2 = localPlayer.prevPosZ + (localPlayer.posZ - localPlayer.prevPosZ) * partTicks;
	        
	        double x = tarX1 - tarX2;
	        double y = tarY1 - tarY2;
	        double z = tarZ1 - tarZ2;
	        

	        y += 20 * 0.0625F;
	        
	        
	        GL11.glTranslated(x, y+1f, z);
	        
	        GL11.glScalef(1, -1,-1);
	        GL11.glRotatef(yawOffset, 0, 1, 0);
	        
	        
	        GL11.glTranslatef(0, 3F * 0.0625F, 0F);
	       
	        
	        GL11.glTranslatef(0, 0.6F * 0.0625F, 0F);
	        
	        GL11.glTranslatef(0, 6 * 0.0625F, 0F);

	 }

	public abstract void render();
	 
	
}
