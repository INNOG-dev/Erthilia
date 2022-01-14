package fr.karmaowner.erthilia.model;

import org.lwjgl.opengl.GL11;

import fr.karmaowner.erthilia.Main;
import fr.karmaowner.erthilia.handler.RenderHandler;
import fr.karmaowner.erthilia.handler.TicksHandler;
import fr.karmaowner.erthilia.utils.MathsUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;

public class ModelFairyCompanion extends ModelBaseBody {

	private final ModelRenderer bipedHead;
	private final ModelRenderer bone;
	private final ModelRenderer bone3;
	private final ModelRenderer bone2;
	private final ModelRenderer bipedBody;
	private final ModelRenderer bipedLeftArm;
	private final ModelRenderer bipedRightLeg;
	private final ModelRenderer bipedLeftArm2;
	private final ModelRenderer bipedRightLeg2;
	private final ModelRenderer wing1;
	private final ModelRenderer wing2;
		
	private Vec3d localPosition = new Vec3d(0.0D, 0.0D, 0.0D);
	
	private Vec3d[] animations = new Vec3d[] {new Vec3d(0.0D, -0.1D, 0.0D), new Vec3d(0.0D, 0.1D, 0.0D) };
	
	private int currentAnim = 0;
	
	public ModelFairyCompanion() 
	{
		textureWidth = 64;
		textureHeight = 64;

		bipedHead = new ModelRenderer(this);
		bipedHead.setRotationPoint(0.0F, 0.5F, -0.5F);
		bipedHead.cubeList.add(new ModelBox(bipedHead, 0, 30, -4.5F, -9.0F, -4.0F, 9, 9, 9, 0.0F, false));

		bone = new ModelRenderer(this);
		bone.setRotationPoint(3.75F, -5.0F, -0.5F);
		bipedHead.addChild(bone);
		setRotationAngle(bone, 0.0F, -0.7418F, 0.0F);
		bone.cubeList.add(new ModelBox(bone, 0, 30, 0.5F, -2.0F, -0.5F, 3, 4, 1, 0.0F, false));

		bone3 = new ModelRenderer(this);
		bone3.setRotationPoint(-3.75F, -5.0F, -0.5F);
		bipedHead.addChild(bone3);
		setRotationAngle(bone3, 0.0F, 0.7418F, 0.0F);
		bone3.cubeList.add(new ModelBox(bone3, 0, 30, -3.5F, -2.0F, -0.5F, 3, 4, 1, 0.0F, true));

		bone2 = new ModelRenderer(this);
		bone2.setRotationPoint(0.0F, -11.5F, 0.5F);
		bipedHead.addChild(bone2);
		setRotationAngle(bone2, 0.0F, -0.7854F, 0.0F);
		bone2.cubeList.add(new ModelBox(bone2, 0, 0, -3.0F, -1.0F, -5.0F, 8, 2, 2, 0.0F, false));
		bone2.cubeList.add(new ModelBox(bone2, 44, 10, -5.0F, -1.0F, 3.0F, 8, 2, 2, 0.0F, false));
		bone2.cubeList.add(new ModelBox(bone2, 44, 15, 3.0F, -1.0F, -3.0F, 2, 2, 8, 0.0F, false));
		bone2.cubeList.add(new ModelBox(bone2, 44, 0, -5.0F, -1.0F, -5.0F, 2, 2, 8, 0.0F, false));

		bipedBody = new ModelRenderer(this);
		bipedBody.setRotationPoint(0.0F, 0.0F, 0.0F);
		bipedBody.cubeList.add(new ModelBox(bipedBody, 16, 16, -4.0F, 0.5F, -2.0F, 8, 9, 4, 0.0F, false));

		bipedLeftArm = new ModelRenderer(this);
		bipedLeftArm.setRotationPoint(5.0F, 0.5F, 0.0F);
		setRotationAngle(bipedLeftArm, 0.0F, 0.0F, -0.0873F);
		bipedLeftArm.cubeList.add(new ModelBox(bipedLeftArm, 32, 48, -1.0F, 0.0F, -2.0F, 3, 9, 4, 0.0F, false));

		bipedRightLeg = new ModelRenderer(this);
		bipedRightLeg.setRotationPoint(-1.9F, 10.0F, 0.0F);
		setRotationAngle(bipedRightLeg, 0.0F, 0.0F, 0.0873F);
		bipedRightLeg.cubeList.add(new ModelBox(bipedRightLeg, 0, 16, -2.0F, -0.5F, -2.0F, 4, 9, 4, 0.0F, false));

		bipedLeftArm2 = new ModelRenderer(this);
		bipedLeftArm2.setRotationPoint(-5.0F, 0.5F, 0.0F);
		setRotationAngle(bipedLeftArm2, 0.0F, 0.0F, 0.0873F);
		bipedLeftArm2.cubeList.add(new ModelBox(bipedLeftArm2, 32, 48, -2.0F, 0.0F, -2.0F, 3, 9, 4, 0.0F, true));

		bipedRightLeg2 = new ModelRenderer(this);
		bipedRightLeg2.setRotationPoint(1.9F, 10.0F, 0.0F);
		setRotationAngle(bipedRightLeg2, 0.0F, 0.0F, -0.0873F);
		bipedRightLeg2.cubeList.add(new ModelBox(bipedRightLeg2, 0, 16, -2.0F, -0.5F, -2.0F, 4, 9, 4, 0.0F, true));

		wing1 = new ModelRenderer(this);
		wing1.setRotationPoint(2.0F, 4.0F, 2.0F);
		setRotationAngle(wing1, 0.0F, 0.6109F, 0.0F);
		wing1.cubeList.add(new ModelBox(wing1, 46, 17, 0.0F, -6.0F, 0.0F, 0, 12, 9, 0.0F, false));
		wing1.cubeList.add(new ModelBox(wing1, 52, 38, -0.5F, -6.0F, 2.0F, 1, 1, 5, -0.1F, false));
		wing1.cubeList.add(new ModelBox(wing1, 52, 38, -0.5F, -5.2F, 6.8F, 1, 4, 1, -0.1F, false));
		wing1.cubeList.add(new ModelBox(wing1, 59, 45, -0.5F, -2.0F, 6.8F, 1, 4, 1, -0.1F, false));

		wing2 = new ModelRenderer(this);
		wing2.setRotationPoint(-2.0F, 4.0F, 2.0F);
		setRotationAngle(wing2, 0.0F, -0.6109F, 0.0F);
		wing2.cubeList.add(new ModelBox(wing2, 46, 17, 0.0F, -6.0F, 0.0F, 0, 12, 9, 0.0F, true));
		wing2.cubeList.add(new ModelBox(wing2, 52, 38, -0.5F, -6.0F, 2.0F, 1, 1, 5, -0.1F, true));
		wing2.cubeList.add(new ModelBox(wing2, 52, 38, -0.5F, -5.2F, 6.8F, 1, 4, 1, -0.1F, true));
		wing2.cubeList.add(new ModelBox(wing2, 59, 45, -0.5F, -2.0F, 6.8F, 1, 4, 1, -0.1F, true));
	}
	
	@Override
	public void render(Entity entity, float limbSwing, float limbSwingAmount, float rotFloat, float rotYaw, float rotPitch, float partTicks) {
			

		 
		 GL11.glPushMatrix();
		 
		 GL11.glColor4f(1f, 1f, 1f, 1f);
		 super.render(entity, limbSwing, limbSwingAmount, rotFloat, rotYaw, rotPitch, partTicks);
		 this.setRotationAngles(limbSwing, limbSwingAmount, rotFloat, rotYaw, rotPitch, partTicks, entity);
		 
		 GL11.glPushMatrix();
		 GL11.glTranslatef(-0.7f, 0.2f, 0.15f); //original Position
		 
	
		 
		 if(MathsUtils.getDistanceSq(localPosition, animations[currentAnim]) <= 0.001D)
		 {
			 currentAnim++;
			 
			 if(currentAnim > animations.length-1)
			 {
				 currentAnim = 0;
			 }
		 }
		 else
		 {
			 double x = MathsUtils.Lerp((float)localPosition.x,(float)animations[currentAnim].x, RenderHandler.modelDeltaTime * 0.00090f);
			 double y = MathsUtils.Lerp((float)localPosition.y,(float)animations[currentAnim].y, RenderHandler.modelDeltaTime * 0.00090f);
			 localPosition = new Vec3d(x,y,0);
		 }
		 
		 GL11.glTranslated(localPosition.x, localPosition.y, 0);
		 
		
		 //GL11.glRotatef(90, 0f, 1f, 0f);

		 GL11.glScalef(0.25f, 0.25f, 0.25f);
		 render();
		 GL11.glPopMatrix();

		 
		 
		 GL11.glPopMatrix();
	}
	 
	@Override
	public void render()
	{
		Minecraft.getMinecraft().getTextureManager().bindTexture(getTexture());

		float f5 = 0.1f;

		float baseRot = -20;
	    float animation = (float) Math.abs(Math.sin((TicksHandler.elapsedTicks+Minecraft.getMinecraft().getRenderPartialTicks()) / 20));
		float wingRotation = baseRot - animation * -20;
		
		bipedHead.render(f5);
		bipedBody.render(f5);
		bipedLeftArm.render(f5);
		bipedRightLeg.render(f5);
		bipedLeftArm2.render(f5);
		bipedRightLeg2.render(f5);
		
		GL11.glPushMatrix();
		GL11.glRotatef(wingRotation, 0, 1, 0);
		wing1.render(f5);
		GL11.glPopMatrix();
		
		GL11.glPushMatrix();
		GL11.glRotatef(-wingRotation, 0, 1, 0);
		wing2.render(f5);
		GL11.glPopMatrix();

	}
	 
	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float rotFloat, float rotYaw, float rotPitch, float partTicks, Entity entity)
	{
		super.setRotationAngles(limbSwing, limbSwingAmount, rotFloat, rotYaw, rotPitch, partTicks, entity);
	}
	
	
	
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z)
	{
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	@Override
	public ResourceLocation getTexture() {
		return new ResourceLocation(Main.MODID,"models/cosmetics/fairy_companion.png");
	}
	
}
