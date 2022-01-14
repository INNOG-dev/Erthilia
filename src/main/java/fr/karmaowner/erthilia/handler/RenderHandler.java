package fr.karmaowner.erthilia.handler;

import java.util.ArrayList;
import java.util.List;

import fr.karmaowner.erthilia.blocks.ErthiliaBlock;
import fr.karmaowner.erthilia.capability.playercapability.ErthiliaPlayer;
import fr.karmaowner.erthilia.capability.playercapability.ErthiliaPlayerProvider;
import fr.karmaowner.erthilia.capability.playercapability.IPlayer;
import fr.karmaowner.erthilia.client.event.ModelPlayerEvent;
import fr.karmaowner.erthilia.client.event.RenderFirstPersonEvent;
import fr.karmaowner.erthilia.cosmetics.CosmeticCachedData;
import fr.karmaowner.erthilia.cosmetics.CosmeticObject;
import fr.karmaowner.erthilia.items.ErthiliaItem;
import fr.karmaowner.erthilia.model.ModelBaseBody;
import fr.karmaowner.erthilia.model.ModelBaseHead;
import fr.karmaowner.erthilia.utils.PlayerUtils;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemMap;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.storage.MapData;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.client.event.RenderSpecificHandEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerRespawnEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


public class RenderHandler {
	
    private static final ResourceLocation RES_MAP_BACKGROUND = new ResourceLocation("textures/map/map_background.png");
		
	private long handOldTime;
	private long handNewTime;
	
	private float prevEquippedProgress;
	
	private ItemStack itemToRender = ItemStack.EMPTY;
	
	public static long modelDeltaTime;
	
	private int equippedItemSlot;

	private float equippedProgress;
	
	
	private long modelOldTime;
	private long modelNewTime;

	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void onRender(RenderPlayerEvent.Pre event)
	{
		modelOldTime = modelNewTime;
		modelNewTime = Minecraft.getSystemTime();
		modelDeltaTime = modelNewTime - modelOldTime;
						
		List<CosmeticObject> data = new ArrayList<CosmeticObject>();
		
		if(event.getEntityPlayer() == Minecraft.getMinecraft().player)
		{
			IPlayer capability = event.getEntityPlayer().getCapability(ErthiliaPlayerProvider.PLAYER_CAP, null);
			
			if(capability == null) return;
			
			data = capability.getCosmeticsDatas();
		}
		else
		{			
			CosmeticCachedData cachedData = CosmeticCachedData.getData(event.getEntityPlayer().getEntityId());
			
			
			if(cachedData.cosmeticsData != null) data = cachedData.cosmeticsData;
		}
		
		
		for(int i = 0; i < data.size(); i++)
		{
			CosmeticObject obj = data.get(i);
						
			if(!obj.getIsEquipped()) continue;
			
			if(obj.getModel() instanceof ModelBaseBody)
			{
				ModelBaseBody model = (ModelBaseBody) obj.getModel();
				model.render(event.getEntityPlayer(), event.getEntityPlayer().limbSwing, event.getEntityPlayer().limbSwingAmount, event.getRenderer().getMainModel().bipedHead.rotateAngleY,  event.getRenderer().getMainModel().bipedHead.rotateAngleY,  event.getRenderer().getMainModel().bipedHead.rotateAngleX, event.getPartialRenderTick());
			}
			else
			{
				ModelBaseHead model = (ModelBaseHead) obj.getModel();
				
				model.render(event.getEntityPlayer(), event.getEntityPlayer().limbSwing, event.getEntityPlayer().limbSwingAmount, event.getRenderer().getMainModel().bipedHead.rotateAngleY,  event.getRenderer().getMainModel().bipedHead.rotateAngleY,  event.getRenderer().getMainModel().bipedHead.rotateAngleX, event.getPartialRenderTick());
			}
		} 
	}
	
	@SubscribeEvent
	public void onPlayerTick(PlayerTickEvent event)
	{
		if(event.phase == Phase.START)
		{
			if(event.side == Side.SERVER)
			{
				ErthiliaPlayer playerData = (ErthiliaPlayer)event.player.getCapability(ErthiliaPlayerProvider.PLAYER_CAP, null);
				if(playerData != null) playerData.getCosmeticsRenderer().update();
			}
		}
	}
 
	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void onClientTick(TickEvent.ClientTickEvent event)
	{
		Minecraft mc = Minecraft.getMinecraft();
		
		if(mc.player == null) return;
		
        this.prevEquippedProgress = this.equippedProgress;
        EntityPlayer entityclientplayermp = mc.player;
        ItemStack itemstack = entityclientplayermp.inventory.getCurrentItem();
        boolean flag = this.equippedItemSlot == entityclientplayermp.inventory.currentItem && itemstack == this.itemToRender;

        if (this.itemToRender == null && itemstack == null)
        {
            flag = true;
        }

        if (itemstack != null && this.itemToRender != null && itemstack != this.itemToRender && itemstack.getItem() == this.itemToRender.getItem() && itemstack.getItemDamage() == this.itemToRender.getItemDamage())
        {
            this.itemToRender = itemstack;
            flag = true;
        }

        float f = 0.4F;
        float f1 = flag ? 0.0F : 1.0F;
        float f2 = f1 - equippedProgress;

        if (f2 < -f)
        {
            f2 = -f;
        }

        if (f2 > f)
        {
            f2 = f;
        }

        this.equippedProgress += f2;

        if (this.equippedProgress > 0.9F)
        {
            this.itemToRender = itemstack;
            this.equippedItemSlot = entityclientplayermp.inventory.currentItem;
        }
	}
	
	@SubscribeEvent
	public void onRespawn(PlayerRespawnEvent event)
	{
		IPlayer capability = event.player.getCapability(ErthiliaPlayerProvider.PLAYER_CAP, null);
		if(capability != null) capability.updateRenderer();
	}
	


	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void renderSpecificHand(RenderSpecificHandEvent event)
	{	
			event.setCanceled(true);
						
            if(event.getHand() == EnumHand.OFF_HAND) return;
            
            float equipP = this.prevEquippedProgress + (this.equippedProgress - this.prevEquippedProgress) * event.getPartialTicks();
			
			Minecraft mc = Minecraft.getMinecraft();
			
			EntityPlayer player = mc.player;
			
            ItemStack stack = itemToRender;
                       
			if(MinecraftForge.EVENT_BUS.post(new RenderFirstPersonEvent(event.getPartialTicks(), event.getInterpolatedPitch(), event.getSwingProgress(), event.getEquipProgress(), stack))) return;
            
			GlStateManager.pushMatrix();

	        if (stack.isEmpty())
	        {
	            if (!player.isInvisible())
	            {
	                renderArmFirstPerson(event.getEquipProgress(), event.getSwingProgress());
	            }
	        }
	        else if (stack.getItem() instanceof ItemMap)
	        {
	        	renderMapFirstPerson(event.getInterpolatedPitch(), event.getEquipProgress(), event.getSwingProgress(), stack);       
	        }
	        else
	        {
	        	
	            if (player.isHandActive() && player.getItemInUseCount() > 0 && !(stack.getItem() instanceof ItemSword))
	            {
	                int j = 1;
	                
	                switch (stack.getItemUseAction())
	                {
	                    case NONE:
	                        this.transformSideFirstPerson(event.getEquipProgress());
	                        break;
	                    case EAT:
	                    case DRINK:
	                        this.transformEatFirstPerson(event.getPartialTicks(), stack);
	                        this.transformSideFirstPerson(event.getEquipProgress());
	                        break;
	                    case BLOCK:
	                        this.transformSideFirstPerson(event.getEquipProgress());
	                        break;
	                    case BOW:
	                        this.transformSideFirstPerson(event.getEquipProgress());
	                        GlStateManager.translate((float)j * -0.2785682F, 0.18344387F, 0.15731531F);
	                        GlStateManager.rotate(-13.935F, 1.0F, 0.0F, 0.0F);
	                        GlStateManager.rotate((float)j * 35.3F, 0.0F, 1.0F, 0.0F);
	                        GlStateManager.rotate((float)j * -9.785F, 0.0F, 0.0F, 1.0F);
	                        float f5 = (float)stack.getMaxItemUseDuration() - ((float)mc.player.getItemInUseCount() - event.getPartialTicks() + 1.0F);
	                        float f6 = f5 / 20.0F;
	                        f6 = (f6 * f6 + f6 * 2.0F) / 3.0F;

	                        if (f6 > 1.0F)
	                        {
	                            f6 = 1.0F;
	                        }

	                        if (f6 > 0.1F)
	                        {
	                            float f7 = MathHelper.sin((f5 - 0.1F) * 1.3F);
	                            float f3 = f6 - 0.1F;
	                            float f4 = f7 * f3;
	                            GlStateManager.translate(f4 * 0.0F, f4 * 0.004F, f4 * 0.0F);
	                        }

	                        GlStateManager.translate(f6 * 0.0F, f6 * 0.0F, f6 * 0.04F);
	                        GlStateManager.scale(1.0F, 1.0F, 1.0F + f6 * 0.2F);
	                        GlStateManager.rotate((float)j * 45.0F, 0.0F, -1.0F, 0.0F);
	                }
	            }
	            else
	            {
	    		    
	 
		    		
	            	
	                float f = -0.4F * MathHelper.sin(MathHelper.sqrt(event.getSwingProgress()) * (float)Math.PI);
	                float f1 = 0.2F * MathHelper.sin(MathHelper.sqrt(event.getSwingProgress()) * ((float)Math.PI * 2F));
	                float f2 = -0.2F * MathHelper.sin(event.getSwingProgress() * (float)Math.PI);
	                int i = 1;
	               	               

	                if(PlayerUtils.isBlockingWithSword(player))
	                {
		    		    GlStateManager.translate(0.56F, -0.52F + equipP * -0.6F, -0.72F);
		    		    GlStateManager.translate(-0.14142136F, 0.15F, 0.14142136F);
		    		    GlStateManager.rotate(-102.25F, 1.0F, 0.0F, 0.0F);
		    			GlStateManager.rotate(13.365F, 0.0F, 1.0F, 0.0F);
		    			GlStateManager.rotate(78.05F, 0.0F, 0.0F, 1.0F);
	                }
	                else
	                {
		                GlStateManager.translate((float)i * f, f1, f2);

	                    this.transformSideFirstPerson(equipP);
	                    
	                }
	                
	                this.transformFirstPerson(event.getSwingProgress());
	       
	            }
	            
	            this.renderItemSide(player, stack, ItemCameraTransforms.TransformType.FIRST_PERSON_RIGHT_HAND, false);
	        }

	        GlStateManager.popMatrix();
	}
	
	@SideOnly(Side.CLIENT)
    public void renderItemSide(EntityLivingBase entitylivingbaseIn, ItemStack heldStack, ItemCameraTransforms.TransformType transform, boolean leftHanded)
    {
		Minecraft mc = Minecraft.getMinecraft();
        if (!heldStack.isEmpty())
        {
            Item item = heldStack.getItem();
            Block block = Block.getBlockFromItem(item);
            GlStateManager.pushMatrix();
            boolean flag = mc.getRenderItem().shouldRenderItemIn3D(heldStack) && block.getBlockLayer() == BlockRenderLayer.TRANSLUCENT;

            if (flag)
            {
                GlStateManager.depthMask(false);
            }

            mc.getRenderItem().renderItem(heldStack, entitylivingbaseIn, transform, leftHanded);

            if (flag)
            {
                GlStateManager.depthMask(true);
            }

            GlStateManager.popMatrix();
        }
    }
	
	@SideOnly(Side.CLIENT)
    private void transformFirstPerson(float p_187453_2_)
    {
        int i = 1;
        float f = MathHelper.sin(p_187453_2_ * p_187453_2_ * (float)Math.PI);
        GlStateManager.rotate((float)i * (45.0F + f * -20.0F), 0.0F, 1.0F, 0.0F);
        float f1 = MathHelper.sin(MathHelper.sqrt(p_187453_2_) * (float)Math.PI);
        GlStateManager.rotate((float)i * f1 * -20.0F, 0.0F, 0.0F, 1.0F);
        GlStateManager.rotate(f1 * -80.0F, 1.0F, 0.0F, 0.0F);
        GlStateManager.rotate((float)i * -45.0F, 0.0F, 1.0F, 0.0F);
    }
	
	@SideOnly(Side.CLIENT)
    private void transformEatFirstPerson(float p_187454_1_, ItemStack stack)
    {
		Minecraft mc = Minecraft.getMinecraft();
        float f = (float)mc.player.getItemInUseCount() - p_187454_1_ + 1.0F;
        float f1 = f / (float)stack.getMaxItemUseDuration();

        if (f1 < 0.8F)
        {
            float f2 = MathHelper.abs(MathHelper.cos(f / 4.0F * (float)Math.PI) * 0.1F);
            GlStateManager.translate(0.0F, f2, 0.0F);
        }

        float f3 = 1.0F - (float)Math.pow((double)f1, 27.0D);
        int i = 1;
        GlStateManager.translate(f3 * 0.6F * (float)i, f3 * -0.5F, f3 * 0.0F);
        GlStateManager.rotate((float)i * f3 * 90.0F, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(f3 * 10.0F, 1.0F, 0.0F, 0.0F);
        GlStateManager.rotate((float)i * f3 * 30.0F, 0.0F, 0.0F, 1.0F);
    }

	
	@SideOnly(Side.CLIENT)
    private void transformSideFirstPerson(float p_187459_2_)
    {
        int i = 1;
        GlStateManager.translate((float)i * 0.56F, -0.52F + p_187459_2_ * -0.6F, -0.72F);
    }
	
    @SideOnly(Side.CLIENT)
    private float getMapAngleFromPitch(float pitch)
    {
        float f = 1.0F - pitch / 45.0F + 0.1F;
        f = MathHelper.clamp(f, 0.0F, 1.0F);
        f = -MathHelper.cos(f * (float)Math.PI) * 0.5F + 0.5F;
        return f;
    }
    
    @SideOnly(Side.CLIENT)
    private void renderArms()
    {
    	Minecraft mc = Minecraft.getMinecraft();
        if (!mc.player.isInvisible())
        {
            GlStateManager.disableCull();
            GlStateManager.pushMatrix();
            GlStateManager.rotate(90.0F, 0.0F, 1.0F, 0.0F);
            this.renderArm();
            GlStateManager.popMatrix();
            GlStateManager.enableCull();
        }
    }
    
    @SideOnly(Side.CLIENT)
    private void renderArm()
    {
    	Minecraft mc = Minecraft.getMinecraft();
        mc.getTextureManager().bindTexture(mc.player.getLocationSkin());
        Render<AbstractClientPlayer> render = mc.getRenderManager().<AbstractClientPlayer>getEntityRenderObject(mc.player);
        RenderPlayer renderplayer = (RenderPlayer)render;
        GlStateManager.pushMatrix();
        float f = 1.0F;
        GlStateManager.rotate(92.0F, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(45.0F, 1.0F, 0.0F, 0.0F);
        GlStateManager.rotate(f * -41.0F, 0.0F, 0.0F, 1.0F);
        GlStateManager.translate(f * 0.3F, -1.1F, 0.45F);

   
        renderplayer.renderRightArm(mc.player);
       
        GlStateManager.popMatrix();
    }

	
	@SideOnly(Side.CLIENT)
    private void renderMapFirstPerson(float p_187463_1_, float p_187463_2_, float p_187463_3_, ItemStack is)
    {
        float f = MathHelper.sqrt(p_187463_3_);
        float f1 = -0.2F * MathHelper.sin(p_187463_3_ * (float)Math.PI);
        float f2 = -0.4F * MathHelper.sin(f * (float)Math.PI);
        GlStateManager.translate(0.0F, -f1 / 2.0F, f2);
        float f3 = this.getMapAngleFromPitch(p_187463_1_);
        GlStateManager.translate(0.0F, 0.04F + p_187463_2_ * -1.2F + f3 * -0.5F, -0.72F);
        GlStateManager.rotate(f3 * -85.0F, 1.0F, 0.0F, 0.0F);
        this.renderArms();
        float f4 = MathHelper.sin(f * (float)Math.PI);
        GlStateManager.rotate(f4 * 20.0F, 1.0F, 0.0F, 0.0F);
        GlStateManager.scale(2.0F, 2.0F, 2.0F);
        this.renderMapFirstPerson(is);
    }
	
	@SideOnly(Side.CLIENT)
    private void renderMapFirstPerson(ItemStack stack)
    {
		Minecraft mc = Minecraft.getMinecraft();
        GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
        GlStateManager.scale(0.38F, 0.38F, 0.38F);
        GlStateManager.disableLighting();
        mc.getTextureManager().bindTexture(RES_MAP_BACKGROUND);
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        GlStateManager.translate(-0.5F, -0.5F, 0.0F);
        GlStateManager.scale(0.0078125F, 0.0078125F, 0.0078125F);
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
        bufferbuilder.pos(-7.0D, 135.0D, 0.0D).tex(0.0D, 1.0D).endVertex();
        bufferbuilder.pos(135.0D, 135.0D, 0.0D).tex(1.0D, 1.0D).endVertex();
        bufferbuilder.pos(135.0D, -7.0D, 0.0D).tex(1.0D, 0.0D).endVertex();
        bufferbuilder.pos(-7.0D, -7.0D, 0.0D).tex(0.0D, 0.0D).endVertex();
        tessellator.draw();
        MapData mapdata = ((ItemMap) stack.getItem()).getMapData(stack, mc.world);

        if (mapdata != null)
        {
            mc.entityRenderer.getMapItemRenderer().renderMap(mapdata, false);
        }

        GlStateManager.enableLighting();
    }

	
	@SideOnly(Side.CLIENT)
    private void renderArmFirstPerson(float equipProgress, float swingProgress)
    {
    	Minecraft mc = Minecraft.getMinecraft();
        float f = 1.0F;
        float f1 = MathHelper.sqrt(swingProgress);
        float f2 = -0.3F * MathHelper.sin(f1 * 0.80f * (float)Math.PI);
        float f3 = 0.4F * MathHelper.sin(f1 * 0.80f * ((float)Math.PI * 2F));
        float f4 = -0.4F * MathHelper.sin(swingProgress * (float)Math.PI);
        GlStateManager.translate(f * (f2 + 0.64000005F), f3 + -0.6F, f4 + -0.71999997F);
        GlStateManager.rotate(f * 45.0F, 0.0F, 1.0F, 0.0F);
        float f5 = MathHelper.sin(swingProgress * swingProgress * (float)Math.PI);
        float f6 = MathHelper.sin(f1 * (float)Math.PI);
        GlStateManager.rotate(f * f6 * 70.0F, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(f * f5 * -20.0F, 0.0F, 0.0F, 1.0F);
        AbstractClientPlayer abstractclientplayer = mc.player;
        mc.getTextureManager().bindTexture(abstractclientplayer.getLocationSkin());
        GlStateManager.translate(f * -1.0F, 3.6F, 3.5F);
        GlStateManager.rotate(f * 120.0F, 0.0F, 0.0F, 1.0F);
        GlStateManager.rotate(200.0F, 1.0F, 0.0F, 0.0F);
        GlStateManager.rotate(f * -135.0F, 0.0F, 1.0F, 0.0F);
        GlStateManager.translate(f * 5.6F, 0.0F, 0.0F);
        RenderPlayer renderplayer = (RenderPlayer)mc.getRenderManager().<AbstractClientPlayer>getEntityRenderObject(abstractclientplayer);
        GlStateManager.disableCull();

      
        renderplayer.renderRightArm(abstractclientplayer);

        GlStateManager.enableCull();
    }
	
	
	
	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void modelPlayerEvent(ModelPlayerEvent.SetupAngles.Post event)
	{
		
		EntityPlayer thePlayer = event.getEntityPlayer();

			
		ItemStack is = thePlayer.getHeldItemMainhand();
		
		if(ErthiliaItem.getErthiliaItem(is.getItem().getRegistryName().toString()) != null)
		{
			ErthiliaItem item = ErthiliaItem.getErthiliaItem(is.getItem().getRegistryName().toString());
			if(item.hasScriptRendering())
			{
				item.getRenderer().setupRenderPlayerHeldItem(thePlayer, event.getModelPlayer(), is, modelDeltaTime);
			}			
		}
		else if(ErthiliaBlock.getErthiliaBlock(is.getItem().getRegistryName().toString()) != null)
		{
			ErthiliaBlock block = ErthiliaBlock.getErthiliaBlock(is.getItem().getRegistryName().toString());
			if(block.hasScriptRendering())
			{
				block.getRenderer().setupRenderPlayerHeldItem(thePlayer, event.getModelPlayer(), is, modelDeltaTime);
			}
		}
		
	}
	
	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void renderHandEvent(RenderHandEvent event)
	{		       
		
		Minecraft mc = Minecraft.getMinecraft();

		EntityPlayer thePlayer = mc.player;
		
		handOldTime = handNewTime;
		handNewTime = Minecraft.getSystemTime();

		long deltaFrame = handNewTime - handOldTime;

		ItemStack is = thePlayer.getHeldItemMainhand();


		if(ErthiliaItem.getErthiliaItem(is.getItem().getRegistryName().toString()) != null)
		{
			ErthiliaItem item = ErthiliaItem.getErthiliaItem(is.getItem().getRegistryName().toString());
			if(item.hasScriptRendering())
			{
				item.getRenderer().renderItemFirstPerson(thePlayer, is, deltaFrame);
			}			
		}
		else if(ErthiliaBlock.getErthiliaBlock(is.getItem().getRegistryName().toString()) != null)
		{
			ErthiliaBlock block = ErthiliaBlock.getErthiliaBlock(is.getItem().getRegistryName().toString());
			if(block.hasScriptRendering())
			{
				block.getRenderer().renderItemFirstPerson(thePlayer, is, deltaFrame);
			}
		}
			
	}
	
	
}
