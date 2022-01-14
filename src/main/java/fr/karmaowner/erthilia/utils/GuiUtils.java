package fr.karmaowner.erthilia.utils;

import java.awt.Color;
import java.util.ArrayList;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiUtils {

    public static int gameColor = 0xF3F3F3;

    public static void drawScissorBox(int x, int y, int width, int height) {
        GL11.glEnable(GL11.GL_SCISSOR_TEST);
        GL11.glScissor(x, y, width, height);
    }

    public static void disableScissorBox() {
        GL11.glDisable(GL11.GL_SCISSOR_TEST);
    }

    public static void renderCenteredText(String text, int posX, int posY) {
        renderCenteredText(text, posX, posY, gameColor);
    }

    public static void renderGradientRect(int x, int y, int width, int height,int zLevel, int startColor, int endColor)
    {
    	
        float f = (float)(startColor >> 24 & 255) / 255.0F;
        float f1 = (float)(startColor >> 16 & 255) / 255.0F;
        float f2 = (float)(startColor >> 8 & 255) / 255.0F;
        float f3 = (float)(startColor & 255) / 255.0F;
        float f4 = (float)(endColor >> 24 & 255) / 255.0F;
        float f5 = (float)(endColor >> 16 & 255) / 255.0F;
        float f6 = (float)(endColor >> 8 & 255) / 255.0F;
        float f7 = (float)(endColor & 255) / 255.0F;
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.shadeModel(7425);
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_COLOR);
        bufferbuilder.pos((double)(x+width), (double)y, (double)zLevel).color(f1, f2, f3, f).endVertex();
        bufferbuilder.pos((double)x, (double)y, (double)zLevel).color(f1, f2, f3, f).endVertex();
        bufferbuilder.pos((double)x, (double)(y+height), (double)zLevel).color(f5, f6, f7, f4).endVertex();
        bufferbuilder.pos((double)(x+width), (double)(y+height), (double)zLevel).color(f5, f6, f7, f4).endVertex();
        tessellator.draw();
        GlStateManager.shadeModel(7424);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
    }
    
    
    
    public static void setClippingRegion(int x, int y, int width, int height)
	{
    	Minecraft mc = Minecraft.getMinecraft();
    	
    	if(mc.currentScreen == null) return;
    	
    	
	    float rx = (float)mc.displayWidth  / (float)mc.currentScreen.width;
	    float ry = (float)mc.displayHeight / (float)mc.currentScreen.height;
	    
	    
		GL11.glEnable(GL11.GL_SCISSOR_TEST);

		GL11.glScissor((int)(x * rx), (int)(mc.displayHeight - (y+height) * ry), (int)(width * rx), (int) (height * ry));
	}
    
    

	public static void clearClippingRegion()
	{
		GL11.glDisable(GL11.GL_SCISSOR_TEST);
	}
    
    public static void renderCenteredText(String text, int posX, int posY, float scale) {
    	GL11.glPushMatrix();
    	GL11.glScalef(scale, scale, scale);
    	renderCenteredText(text, (int) (posX / scale), (int) (posY / scale), gameColor);
    	GL11.glPopMatrix();
    }
    
    public static void renderCenteredText(String text, int posX, int posY, float scale, int color) {
    	GL11.glPushMatrix();
    	GL11.glScalef(scale, scale, scale);
    	renderCenteredText(text, (int) (posX / scale), (int) (posY / scale), color);
    	GL11.glPopMatrix();
    }

    public static void renderCenteredTextWithShadow(String text, int posX, int posY) {
        renderCenteredTextWithShadow(text, posX, posY, gameColor);
    }

    public static void renderCenteredText(String text, int posX, int posY, int par4) {
		GlStateManager.resetColor();
    	Minecraft mc = Minecraft.getMinecraft();
        mc.fontRenderer.drawString(text, posX - mc.fontRenderer.getStringWidth(text) / 2, posY, par4);
    }

    public static void renderCenteredTextWithShadow(String text, int posX, int posY, int par4) {
		GlStateManager.resetColor();
    	Minecraft mc = Minecraft.getMinecraft();
        mc.fontRenderer.drawStringWithShadow(text, posX - mc.fontRenderer.getStringWidth(text) / 2, posY, par4);
    }

    public static void renderText(String text, int posX, int posY) {
        renderText(text, posX, posY, gameColor);
    }

    public static void renderTextWithShadow(String text, int posX, int posY) {
        renderTextWithShadow(text, posX, posY, gameColor);
    }
    
    public static void renderTextWithShadow(String text, int posX, int posY, int color, float font_scale) {
    	GL11.glPushMatrix();
    	GL11.glScalef(font_scale, font_scale, font_scale);
        renderTextWithShadow(text, (int) (posX / font_scale), (int) (posY / font_scale), color);
        GL11.glPopMatrix();
    }

    public static void renderText(String text, int posX, int posY, int color) {
		GlStateManager.resetColor();
    	Minecraft mc = Minecraft.getMinecraft();
        mc.fontRenderer.drawString(text, posX, posY, color);
    }
    
    public static void renderText(String text, int posX, int posY, int color, float scale) {
		GlStateManager.resetColor();
    	Minecraft mc = Minecraft.getMinecraft();
        GL11.glPushMatrix();
        GL11.glScalef(scale, scale, scale);
        mc.fontRenderer.drawString(text, (int) (posX / scale), (int) (posY / scale), color);
        GL11.glPopMatrix();
    }

    public static void renderTextWithShadow(String text, int posX, int posY, int color) {
		GlStateManager.resetColor();
    	Minecraft mc = Minecraft.getMinecraft();
        mc.fontRenderer.drawStringWithShadow(text, posX, posY, color);
    }

    public static void renderItemStackIntoGUI(ItemStack itemstack, int posX, int posY, String text) {
    	GL11.glPushMatrix();
        GL11.glTranslatef(0, 0, -100);
    	RenderItem itemRenderer = Minecraft.getMinecraft().getRenderItem();
        RenderHelper.disableStandardItemLighting();
        itemRenderer.renderItemIntoGUI(itemstack, posX, posY);
        GL11.glDisable(2896);
        
        GL11.glPopMatrix();

        
        GL11.glPushMatrix();
        GL11.glTranslatef(0, 0, 999);
        GuiUtils.renderText(text, posX+14, posY+12,GuiUtils.gameColor,0.8f);
        GL11.glPopMatrix();

    }
    
    public static void renderItemStackIntoGUI(ItemStack itemstack, int posX, int posY, String text, RenderItem renderer) {
		GL11.glEnable(GL11.GL_BLEND);

    	RenderItem itemRenderer = Minecraft.getMinecraft().getRenderItem();
    	RenderHelper.enableGUIStandardItemLighting();
    	itemRenderer.renderItemAndEffectIntoGUI(itemstack, posX, posY);
        GL11.glDisable(2896);
        
		GL11.glDisable(GL11.GL_BLEND);

        
        GL11.glPushMatrix();
        GL11.glTranslatef(0, 0, 999);
        GuiUtils.renderText(text, posX+14, posY+12,GuiUtils.gameColor,0.8f);
        GL11.glPopMatrix();
    }

    public static void renderColor(int par1) {
        Color color = Color.decode("" + par1);
        float red = color.getRed() / 255.0F;
        float green = color.getGreen() / 255.0F;
        float blue = color.getBlue() / 255.0F;
        GL11.glColor3f(red, green, blue);
    }
    
    
    public static void drawImage(double x, double y, ResourceLocation image, double textureWidth, double textureHeight, int zLevel)
    {
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        Minecraft.getMinecraft().getTextureManager().bindTexture(image);
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
        bufferbuilder.pos(x, y + textureHeight, zLevel).tex(0.0D,1.0D).endVertex();
        bufferbuilder.pos(x + textureWidth, y + textureHeight,zLevel).tex(1.0D,1.0D).endVertex();
        bufferbuilder.pos(x + textureWidth, y, zLevel).tex(1.0D,0.0D).endVertex();
        bufferbuilder.pos(x, y, zLevel).tex(0.0D,0.0D).endVertex();
        tessellator.draw();
    }
    
    
    public static void drawImageWithTransparency(double xCoord, double yCoord, ResourceLocation image, double width, double height, int zLevel) 
    {
    	GL11.glEnable(GL11.GL_BLEND);
    	GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
    	drawImage(xCoord, yCoord, image, width,height,zLevel);
    	GL11.glDisable(GL11.GL_BLEND);
    }
    
    public static void drawRect(double x, double y, double width, double height,int zLevel, int color, float alpha)
    {
        float f3 = alpha;
        float f = (float)(color >> 16 & 255) / 255.0F;
        float f1 = (float)(color >> 8 & 255) / 255.0F;
        float f2 = (float)(color & 255) / 255.0F;
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.color(f, f1, f2, f3);
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION);
        bufferbuilder.pos(x, y+height, zLevel).endVertex();
        bufferbuilder.pos(x+width, y+height, zLevel).endVertex();
        bufferbuilder.pos(x+width, y, zLevel).endVertex();
        bufferbuilder.pos(x, y, zLevel).endVertex();
        tessellator.draw();
    	GlStateManager.resetColor();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();

      
    }

    public static void drawRectWithShadow(int x, int y, int width, int height,int zLevel, int color, float alpha) {
        drawRect(x - 1, y - 1, width + 2, height + 2, zLevel, color, 0.2F);
        drawRect(x, y, width, height, zLevel, color, alpha);
    }

    public static void renderSplitedText(String text, int posX , int posY, int maxWidth)
    {
    	ArrayList<String> display = SplitText(text,posX,maxWidth);
    	for(int i = 0; i < display.size(); i++)
    	{
    		GuiUtils.renderCenteredText(display.get(i), posX, posY + ((i + 1) * 5));
    	}
    }
    
    public static ArrayList<String> SplitText(String text, int posX, int maxWidth)
    {
    	ArrayList<String> Split = new ArrayList<String>();
    	int pos = posX;
    	int i;
    	int index = 0;
    	for(i = 0; i < text.length(); i++)
    	{
    		if((pos += Minecraft.getMinecraft().fontRenderer.getCharWidth(text.charAt(i))) >= maxWidth)
    		{
    			Split.add(text.substring(index, i));
    			index = i;
    		}
    	}
    	
    	if(Split.size() < 1)
    		Split.add(text);
    	
    	return Split;
    }
    
    public static void drawEntity(int posX, int posY, int scale, float mouseX, float mouseY, EntityLivingBase ent)
    {
        GlStateManager.enableColorMaterial();
        GlStateManager.pushMatrix();
        GlStateManager.translate((float)posX, (float)posY, 50.0F);
        GlStateManager.scale((float)(-scale), (float)scale, (float)scale);
        GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
        float f = ent.renderYawOffset;
        float f1 = ent.rotationYaw;
        float f2 = ent.rotationPitch;
        float f3 = ent.prevRotationYawHead;
        float f4 = ent.rotationYawHead;
        GlStateManager.rotate(135.0F, 0.0F, 1.0F, 0.0F);
        RenderHelper.enableStandardItemLighting();
        GlStateManager.rotate(-135.0F, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(-((float)Math.atan((double)(mouseY / 40.0F))) * 20.0F, 1.0F, 0.0F, 0.0F);
        ent.renderYawOffset = (float)Math.atan((double)(mouseX / 40.0F)) * 20.0F;
        ent.rotationYaw = (float)Math.atan((double)(mouseX / 40.0F)) * 40.0F;
        ent.rotationPitch = -((float)Math.atan((double)(mouseY / 40.0F))) * 20.0F;
        ent.rotationYawHead = ent.rotationYaw;
        ent.prevRotationYawHead = ent.rotationYaw;
        GlStateManager.translate(0.0F, 0.0F, 0.0F);
        RenderManager rendermanager = Minecraft.getMinecraft().getRenderManager();
        rendermanager.setPlayerViewY(180.0F);
        rendermanager.setRenderShadow(false);
        rendermanager.renderEntity(ent, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F, false);
        rendermanager.setRenderShadow(true);
        ent.renderYawOffset = f;
        ent.rotationYaw = f1;
        ent.rotationPitch = f2;
        ent.prevRotationYawHead = f3;
        ent.rotationYawHead = f4;
        GlStateManager.popMatrix();
        RenderHelper.disableStandardItemLighting();
        GlStateManager.disableRescaleNormal();
        GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
        GlStateManager.disableTexture2D();
        GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
    }
    
    public static int getStringWidth(Minecraft mc, String string, float scale)
    {
    	return (int)(mc.fontRenderer.getStringWidth(string) * scale)+4;
    }
    
    public static void StartRotation(int drawX, int drawY, int drawWidth, int drawHeight , int rotateAngle)
    {
    	GL11.glPushMatrix();
    	GL11.glTranslatef(drawX + drawWidth / 2, drawY + drawHeight / 2, 0);
    	GL11.glRotatef(rotateAngle, 0, 0, 1);
    	GL11.glTranslatef(-(drawX + drawWidth / 2), -(drawY + drawHeight / 2), 0);
    }
    
    public static void StopRotation()
    {
    	GL11.glPopMatrix();
    }
    
    
}
