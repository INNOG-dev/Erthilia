package fr.karmaowner.erthilia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import fr.karmaowner.client.creativetab.ErthiliaCreativeTabs;
import fr.karmaowner.client.renderer.itemrenderer.IItemRenderer;
import fr.karmaowner.erthilia.blocks.ErthiliaAntiPistonBlock;
import fr.karmaowner.erthilia.blocks.ErthiliaBasicBlock;
import fr.karmaowner.erthilia.blocks.ErthiliaBlock;
import fr.karmaowner.erthilia.blocks.ErthiliaBlockJump;
import fr.karmaowner.erthilia.blocks.ErthiliaBlockLadder;
import fr.karmaowner.erthilia.blocks.ErthiliaBlockOre;
import fr.karmaowner.erthilia.blocks.ErthiliaBlockSpeed;
import fr.karmaowner.erthilia.blocks.ErthiliaBlockTabacco;
import fr.karmaowner.erthilia.blocks.ErthiliaBlockWeed;
import fr.karmaowner.erthilia.blocks.ErthiliaBlockXpPlant;
import fr.karmaowner.erthilia.blocks.ErthiliaBlocks;
import fr.karmaowner.erthilia.blocks.ErthiliaJadeChest;
import fr.karmaowner.erthilia.blocks.ErthiliaJadeFurnace;
import fr.karmaowner.erthilia.blocks.ErthiliaMysteryBlock;
import fr.karmaowner.erthilia.blocks.ErthiliaObsidian;
import fr.karmaowner.erthilia.blocks.ErthiliaPistonBase;
import fr.karmaowner.erthilia.blocks.ErthiliaReversedCraftingTable;
import fr.karmaowner.erthilia.blocks.ErthiliaReversedEnchantingTable;
import fr.karmaowner.erthilia.blocks.ErthiliaSyringeStand;
import fr.karmaowner.erthilia.capability.playercapability.ErthiliaPlayerFactory;
import fr.karmaowner.erthilia.capability.playercapability.ErthiliaPlayerStorage;
import fr.karmaowner.erthilia.capability.playercapability.IPlayer;
import fr.karmaowner.erthilia.cosmetics.CosmeticObject;
import fr.karmaowner.erthilia.cosmetics.ICosmeticSetup;
import fr.karmaowner.erthilia.data.ErthiliaEntityDrop;
import fr.karmaowner.erthilia.entity.EntitySpear;
import fr.karmaowner.erthilia.entity.monster.EntityDragon;
import fr.karmaowner.erthilia.entity.monster.EntityDragonBlack;
import fr.karmaowner.erthilia.entity.monster.EntityFireAtronach;
import fr.karmaowner.erthilia.entity.monster.EntityUnicorn;
import fr.karmaowner.erthilia.handler.GuiContainerHandler;
import fr.karmaowner.erthilia.items.ErthiliaArmor;
import fr.karmaowner.erthilia.items.ErthiliaAxe;
import fr.karmaowner.erthilia.items.ErthiliaBasicItem;
import fr.karmaowner.erthilia.items.ErthiliaBow;
import fr.karmaowner.erthilia.items.ErthiliaFood;
import fr.karmaowner.erthilia.items.ErthiliaHoe;
import fr.karmaowner.erthilia.items.ErthiliaItem;
import fr.karmaowner.erthilia.items.ErthiliaItemSeeds;
import fr.karmaowner.erthilia.items.ErthiliaItems;
import fr.karmaowner.erthilia.items.ErthiliaMultiTools;
import fr.karmaowner.erthilia.items.ErthiliaPickaxe;
import fr.karmaowner.erthilia.items.ErthiliaScepter;
import fr.karmaowner.erthilia.items.ErthiliaSpade;
import fr.karmaowner.erthilia.items.ErthiliaSpear;
import fr.karmaowner.erthilia.items.ErthiliaSword;
import fr.karmaowner.erthilia.items.ErthiliaSyringe;
import fr.karmaowner.erthilia.items.ErthiliaTools;
import fr.karmaowner.erthilia.model.ModelAfro;
import fr.karmaowner.erthilia.model.ModelBackpack;
import fr.karmaowner.erthilia.model.ModelBackpack2;
import fr.karmaowner.erthilia.model.ModelColorfulMask;
import fr.karmaowner.erthilia.model.ModelCrown;
import fr.karmaowner.erthilia.model.ModelDemonCompanion;
import fr.karmaowner.erthilia.model.ModelDemonWings;
import fr.karmaowner.erthilia.model.ModelDevilHorn;
import fr.karmaowner.erthilia.model.ModelDoctorBeak;
import fr.karmaowner.erthilia.model.ModelFairyCompanion;
import fr.karmaowner.erthilia.model.ModelFairyWings;
import fr.karmaowner.erthilia.model.ModelFlamingoHat;
import fr.karmaowner.erthilia.model.ModelMagicianHat;
import fr.karmaowner.erthilia.model.ModelMexicanHat;
import fr.karmaowner.erthilia.model.ModelRobotGlasses;
import fr.karmaowner.erthilia.model.ModelSunGlasses;
import fr.karmaowner.erthilia.model.ModelWitchHat;
import fr.karmaowner.erthilia.potion.ReduceFallDamagePotion;
import fr.karmaowner.erthilia.renderer.entity.RenderDragon;
import fr.karmaowner.erthilia.renderer.entity.RenderFireAtronach;
import fr.karmaowner.erthilia.renderer.entity.RenderSpear;
import fr.karmaowner.erthilia.renderer.entity.RenderUnicorn;
import fr.karmaowner.erthilia.scepter.HealEffectScepter;
import fr.karmaowner.erthilia.scepter.RemoveEffectScepter;
import fr.karmaowner.erthilia.scepter.ScepterEffect;
import fr.karmaowner.erthilia.tiles.TileEntityManager;
import fr.karmaowner.erthilia.utils.MathsUtils;
import fr.karmaowner.erthilia.world.ErthiliaWorldGeneration;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GameInitializer {
	
	public static KeyBinding[] keyBindings; 

	public HashMap<Integer,RenderLiving<?>> renderers = new HashMap<Integer,RenderLiving<?>>();
	
	public static HashMap<Class<?>,ErthiliaEntityDrop> dropsData = new HashMap<>();
	 
	@SubscribeEvent
	public void registerBlocks(RegistryEvent.Register<Block> event) 
	{
		for(ErthiliaBlock eblock : ErthiliaBlock.blocks.values())
		{
			event.getRegistry().register(eblock.getBlock());
		} 
 
		event.getRegistry().register(new ErthiliaPistonBase(true).setRegistryName(new ResourceLocation("minecraft","sticky_piston")).setUnlocalizedName("pistonStickyBase"));
		event.getRegistry().register(new ErthiliaPistonBase(false).setRegistryName(new ResourceLocation("minecraft","piston")).setUnlocalizedName("pistonBase"));
		event.getRegistry().register(new ErthiliaObsidian().setHardness(50.0F).setResistance(0.0F).setRegistryName(new ResourceLocation("minecraft","obsidian")).setUnlocalizedName("obsidian"));

		ErthiliaBlocks.loadBlocksInstance();
	}
	
	
	 
	@SubscribeEvent
	public void registerItems(RegistryEvent.Register<Item> event) 
	{
		for(ErthiliaItem eitem : ErthiliaItem.items.values())
		{
			event.getRegistry().register(eitem.getItem());
		}	
		

		
		ErthiliaItems.loadItemsInstance();
		
		new CustomPotionRecipes();
		PotionHelper.addContainer((ItemPotion) ErthiliaItems.WATER_SYRINGE);
	}
	
	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void registerRenderers(ModelRegistryEvent event) 
	{
		ErthiliaItem.items.forEach((k,v) -> v.registerRender());
		ErthiliaBlock.blocks.forEach((k,v) -> v.registerRender());
		
		RenderingRegistry.registerEntityRenderingHandler(EntitySpear.class, new IRenderFactory<EntitySpear>()
		{
			@Override
			public Render<? super EntitySpear> createRenderFor(RenderManager manager)
			{
				return new RenderSpear(manager);
			}
		});
		
		RenderingRegistry.registerEntityRenderingHandler(EntityDragon.class, new IRenderFactory<EntityDragon>()
		{
			@Override
			public Render<? super EntityDragon> createRenderFor(RenderManager manager)
			{
				return new RenderDragon(manager,0);
			}
		});
		
		
		RenderingRegistry.registerEntityRenderingHandler(EntityFireAtronach.class, new IRenderFactory<EntityFireAtronach>()
		{			
			@Override
			public Render<? super EntityFireAtronach> createRenderFor(RenderManager manager)
			{
				return new RenderFireAtronach(manager);
			}
		});
		
		RenderingRegistry.registerEntityRenderingHandler(EntityUnicorn.class, RenderUnicorn::new);
		
		System.out.println("registered renderers");
	}
	
	
	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public void registerPotions(RegistryEvent.Register<Potion> event) 
	{
	    event.getRegistry().register(new ReduceFallDamagePotion(false, 32767, "AntiFallDamage").setRegistryName("anti_fall_damage"));
	} 
	

	
    @SubscribeEvent
    public void registerEntities(RegistryEvent.Register<EntityEntry> event)
    {
        event.getRegistry().register(EntityEntryBuilder.create().entity(EntityDragon.class)
        .id(new ResourceLocation(Main.MODID, "dragon"), 33).name("Dragon").tracker(64, 3, true)
        .egg(0xF16060, 0x000000).build());
        
        event.getRegistry().register(EntityEntryBuilder.create().entity(EntityDragonBlack.class)
        .id(new ResourceLocation(Main.MODID, "dragon_black"), 34).name("DragonBlack").tracker(64, 3, true)
        .egg(0x3A3939, 0x000000).build());
        
        event.getRegistry().register(EntityEntryBuilder.create().entity(EntitySpear.class)
        .id(new ResourceLocation(Main.MODID, "spear"), 35).name("Spear").tracker(64, 10, true)
        .build());
        
        event.getRegistry().register(EntityEntryBuilder.create().entity(EntityUnicorn.class)
        .id(new ResourceLocation(Main.MODID, "unicorn"), 36).name("Unicorn").tracker(64, 3, true)
        .egg(0xE1C699, 0xFFFFFF).build());
        
        event.getRegistry().register(EntityEntryBuilder.create().entity(EntityFireAtronach.class)
        .id(new ResourceLocation(Main.MODID, "fire_atronach"), 37).name("FireAtronach").tracker(64, 3, true)
        .egg(0xFF8400, 0xFF0008).build());
        
        Main.log("Entities registered");
    }
	 
	public void preInit(FMLPreInitializationEvent event)
	{
    	preRegister();

		if(event.getSide().isClient())
		{
			registerKeyBinding();
			TileEntityManager.registerTileEntityRenderer();
		}
		
		registerEntityDrops();
		TileEntityManager.registerTileEntity();
	}
	
	public void init(FMLInitializationEvent event)
	{    	
		registerRecipes();


		registerCosmetics(event.getSide());
		
		CapabilityManager.INSTANCE.register(IPlayer.class, new ErthiliaPlayerStorage(), new ErthiliaPlayerFactory());

		NetworkRegistry.INSTANCE.registerGuiHandler(Main.INSTANCE, new GuiContainerHandler());
		
		GameRegistry.registerWorldGenerator(new ErthiliaWorldGeneration(), 0);
		
	}

	@SuppressWarnings({ "deprecation", "unused" })
	public void preRegister()
	{	
		
		ErthiliaCreativeTabs.instance = new ErthiliaCreativeTabs("erthilia_tabs");
		
		ErthiliaTools.registerTool("jadeTool", 3, 1800, 7.0F, 4.0F, 20);
		ErthiliaTools.registerTool("cyaniteTool", 3, 2000, 7.0F, 5.0F, 19);
		ErthiliaTools.registerTool("morganiteTool", 3, 2200, 7.0F, 6.0F, 18);
		ErthiliaTools.registerTool("pyriteTool", 3, 2400, 7.0F, 7.0F, 17);
		ErthiliaTools.registerTool("citrineTool", 3, 2600, 7.0F, 8.0F, 15);
		ErthiliaTools.registerTool("fairyTool", 3, 3200, 9.0F, 11.0F, 10);
		ErthiliaTools.registerTool("blackDemonTool", 3, 3200, 9.0F, 11.0F, 10);
		

		
		ErthiliaTools.registerArmorMaterial("erthilia:jade", 80, new int[] {3,8,6,4}, 15);
		ErthiliaTools.registerArmorMaterial("erthilia:cyanite", 92, new int[] {4,8,6,4}, 17);
		ErthiliaTools.registerArmorMaterial("erthilia:morganite", 105, new int[] {4,8,7,3}, 19);
		ErthiliaTools.registerArmorMaterial("erthilia:pyrite", 116, new int[] {3,8,8,3}, 21);
		ErthiliaTools.registerArmorMaterial("erthilia:citrine", 121, new int[] {4,8,7,4}, 25);
		ErthiliaTools.registerArmorMaterial("erthilia:fairy", 136, new int[] {4,8,8,4}, 30);
		ErthiliaTools.registerArmorMaterial("erthilia:black_demon", 136, new int[] {4,8,8,4}, 30);
		ErthiliaTools.registerArmorMaterial("erthilia:search", 5, new int[] {1,3,2,1}, 15);

		//CLOTH
		ObfuscationReflectionHelper.setPrivateValue(ItemArmor.class, Items.LEATHER_HELMET, 1, "damageReduceAmount", "field_77879_b");
		ObfuscationReflectionHelper.setPrivateValue(ItemArmor.class, Items.LEATHER_CHESTPLATE, 2, "damageReduceAmount", "field_77879_b");
		ObfuscationReflectionHelper.setPrivateValue(ItemArmor.class, Items.LEATHER_LEGGINGS, 1, "damageReduceAmount", "field_77879_b");
		ObfuscationReflectionHelper.setPrivateValue(ItemArmor.class, Items.LEATHER_BOOTS, 1, "damageReduceAmount", "field_77879_b");
		
		//CHAIN
		ObfuscationReflectionHelper.setPrivateValue(ItemArmor.class, Items.CHAINMAIL_HELMET, 1, "damageReduceAmount", "field_77879_b");
		ObfuscationReflectionHelper.setPrivateValue(ItemArmor.class, Items.CHAINMAIL_CHESTPLATE, 3, "damageReduceAmount", "field_77879_b");
		ObfuscationReflectionHelper.setPrivateValue(ItemArmor.class, Items.CHAINMAIL_LEGGINGS, 2, "damageReduceAmount", "field_77879_b");
		ObfuscationReflectionHelper.setPrivateValue(ItemArmor.class, Items.CHAINMAIL_BOOTS, 1, "damageReduceAmount", "field_77879_b");
		
		//IRON
		ObfuscationReflectionHelper.setPrivateValue(ItemArmor.class, Items.IRON_HELMET, 1, "damageReduceAmount", "field_77879_b");
		ObfuscationReflectionHelper.setPrivateValue(ItemArmor.class, Items.IRON_CHESTPLATE, 5, "damageReduceAmount", "field_77879_b");
		ObfuscationReflectionHelper.setPrivateValue(ItemArmor.class, Items.IRON_LEGGINGS, 4, "damageReduceAmount", "field_77879_b");
		ObfuscationReflectionHelper.setPrivateValue(ItemArmor.class, Items.IRON_BOOTS, 1, "damageReduceAmount", "field_77879_b");
		
		//Diamond
	    ObfuscationReflectionHelper.setPrivateValue(ItemArmor.class, Items.DIAMOND_HELMET, 2, "damageReduceAmount", "field_77879_b");
	    ObfuscationReflectionHelper.setPrivateValue(ItemArmor.class, Items.DIAMOND_CHESTPLATE, 6, "damageReduceAmount", "field_77879_b");
	    ObfuscationReflectionHelper.setPrivateValue(ItemArmor.class, Items.DIAMOND_LEGGINGS, 6, "damageReduceAmount", "field_77879_b");
	    ObfuscationReflectionHelper.setPrivateValue(ItemArmor.class, Items.DIAMOND_BOOTS, 2, "damageReduceAmount", "field_77879_b");
	    
		
		//Jade
		ErthiliaItem jade = new ErthiliaItem("jade",new ErthiliaBasicItem().setMaxStackSize(64).setNoRepair().setMaxDamage(0));
		ErthiliaItem jadeSword = new ErthiliaItem("jade_sword",new ErthiliaSword(ErthiliaTools.getToolMaterial(0)));
		ErthiliaItem jadePickaxe = new ErthiliaItem("jade_pickaxe",new ErthiliaPickaxe(ErthiliaTools.getToolMaterial(0)));
		ErthiliaItem jadeShovel = new ErthiliaItem("jade_shovel",new ErthiliaSpade(ErthiliaTools.getToolMaterial(0)));
		ErthiliaItem jadeAxe = new ErthiliaItem("jade_axe",new ErthiliaAxe(ErthiliaTools.getToolMaterial(0),8.0f,-3.0f));
		ErthiliaItem jadeHoe = new ErthiliaItem("jade_hoe",new ErthiliaHoe(ErthiliaTools.getToolMaterial(0)));
		ErthiliaItem jadeHelmet = new ErthiliaItem("jade_helmet",new ErthiliaArmor(ErthiliaTools.getArmorMaterial(7),5,EntityEquipmentSlot.HEAD));
		ErthiliaItem jadeChestplate = new ErthiliaItem("jade_chestplate",new ErthiliaArmor(ErthiliaTools.getArmorMaterial(7),5,EntityEquipmentSlot.CHEST));
		ErthiliaItem jadeLeggings = new ErthiliaItem("jade_leggings",new ErthiliaArmor(ErthiliaTools.getArmorMaterial(7),5,EntityEquipmentSlot.LEGS));
		ErthiliaItem jadeBoots = new ErthiliaItem("jade_boots",new ErthiliaArmor(ErthiliaTools.getArmorMaterial(7),5,EntityEquipmentSlot.FEET));
		ErthiliaItem jadeBow = new ErthiliaItem("jade_bow",new ErthiliaBow(400,1.02f,50000));
		ErthiliaItem jadeMultitools = new ErthiliaItem("jade_multitools",new ErthiliaMultiTools(ErthiliaTools.getToolMaterial(0)));

		
		//Cyanite
		ErthiliaItem cyanite = new ErthiliaItem("cyanite",new ErthiliaBasicItem().setMaxStackSize(64).setNoRepair().setMaxDamage(0).setCreativeTab(CreativeTabs.MATERIALS));
		ErthiliaItem cyaniteSword = new ErthiliaItem("cyanite_sword",new ErthiliaSword(ErthiliaTools.getToolMaterial(1)));
		ErthiliaItem cyanitePickaxe = new ErthiliaItem("cyanite_pickaxe",new ErthiliaPickaxe(ErthiliaTools.getToolMaterial(1)));
		ErthiliaItem cyaniteShovel = new ErthiliaItem("cyanite_shovel",new ErthiliaSpade(ErthiliaTools.getToolMaterial(1)));
		ErthiliaItem cyaniteAxe = new ErthiliaItem("cyanite_axe",new ErthiliaAxe(ErthiliaTools.getToolMaterial(1),8.0f,-3.0f));
		ErthiliaItem cyaniteHoe = new ErthiliaItem("cyanite_hoe",new ErthiliaHoe(ErthiliaTools.getToolMaterial(1)));
		ErthiliaItem cyaniteHelmet = new ErthiliaItem("cyanite_helmet",new ErthiliaArmor(ErthiliaTools.getArmorMaterial(8),6,EntityEquipmentSlot.HEAD));
		ErthiliaItem cyaniteChestplate = new ErthiliaItem("cyanite_chestplate",new ErthiliaArmor(ErthiliaTools.getArmorMaterial(8),6,EntityEquipmentSlot.CHEST));
		ErthiliaItem cyaniteLeggings = new ErthiliaItem("cyanite_leggings",new ErthiliaArmor(ErthiliaTools.getArmorMaterial(8),6,EntityEquipmentSlot.LEGS));
		ErthiliaItem cyaniteBoots = new ErthiliaItem("cyanite_boots",new ErthiliaArmor(ErthiliaTools.getArmorMaterial(8),6,EntityEquipmentSlot.FEET));
		ErthiliaItem cyaniteBow = new ErthiliaItem("cyanite_bow",new ErthiliaBow(400,1.1f,60000));
		ErthiliaItem cyaniteMultitools = new ErthiliaItem("cyanite_multitools",new ErthiliaMultiTools(ErthiliaTools.getToolMaterial(1)));
		
		//Morganite
		ErthiliaItem morganite = new ErthiliaItem("morganite",new ErthiliaBasicItem().setMaxStackSize(64).setNoRepair().setMaxDamage(0).setCreativeTab(CreativeTabs.MATERIALS));
		ErthiliaItem morganiteSword = new ErthiliaItem("morganite_sword",new ErthiliaSword(ErthiliaTools.getToolMaterial(2)));
		ErthiliaItem morganitePickaxe = new ErthiliaItem("morganite_pickaxe",new ErthiliaPickaxe(ErthiliaTools.getToolMaterial(2)));
		ErthiliaItem morganiteShovel = new ErthiliaItem("morganite_shovel",new ErthiliaSpade(ErthiliaTools.getToolMaterial(2)));
		ErthiliaItem morganiteAxe = new ErthiliaItem("morganite_axe",new ErthiliaAxe(ErthiliaTools.getToolMaterial(2),8.0f,-3.0f));
		ErthiliaItem morganiteHoe = new ErthiliaItem("morganite_hoe",new ErthiliaHoe(ErthiliaTools.getToolMaterial(2)));
		ErthiliaItem morganiteHelmet = new ErthiliaItem("morganite_helmet",new ErthiliaArmor(ErthiliaTools.getArmorMaterial(9),7, EntityEquipmentSlot.HEAD));
		ErthiliaItem morganiteChestplate = new ErthiliaItem("morganite_chestplate",new ErthiliaArmor(ErthiliaTools.getArmorMaterial(9),7,EntityEquipmentSlot.CHEST));
		ErthiliaItem morganiteLeggings = new ErthiliaItem("morganite_leggings",new ErthiliaArmor(ErthiliaTools.getArmorMaterial(9),7,EntityEquipmentSlot.LEGS));
		ErthiliaItem morganiteBoots = new ErthiliaItem("morganite_boots",new ErthiliaArmor(ErthiliaTools.getArmorMaterial(9),7,EntityEquipmentSlot.FEET));
		ErthiliaItem morganiteBow = new ErthiliaItem("morganite_bow",new ErthiliaBow(400,1.2f,70000));
		ErthiliaItem morganiteMultitools = new ErthiliaItem("morganite_multitools",new ErthiliaMultiTools(ErthiliaTools.getToolMaterial(2)));
		
		//Pyrite
		ErthiliaItem pyrite = new ErthiliaItem("pyrite",new ErthiliaBasicItem().setMaxStackSize(64).setNoRepair().setMaxDamage(0).setCreativeTab(CreativeTabs.MATERIALS));
		ErthiliaItem pyriteSword = new ErthiliaItem("pyrite_sword",new ErthiliaSword(ErthiliaTools.getToolMaterial(3)));
		ErthiliaItem pyritePickaxe = new ErthiliaItem("pyrite_pickaxe",new ErthiliaPickaxe(ErthiliaTools.getToolMaterial(3)));
		ErthiliaItem pyriteShovel = new ErthiliaItem("pyrite_shovel",new ErthiliaSpade(ErthiliaTools.getToolMaterial(3)));
		ErthiliaItem pyriteAxe = new ErthiliaItem("pyrite_axe",new ErthiliaAxe(ErthiliaTools.getToolMaterial(3),8.0f,-3.0f));
		ErthiliaItem pyriteHoe = new ErthiliaItem("pyrite_hoe",new ErthiliaHoe(ErthiliaTools.getToolMaterial(3)));
		ErthiliaItem pyriteHelmet = new ErthiliaItem("pyrite_helmet",new ErthiliaArmor(ErthiliaTools.getArmorMaterial(10),8,EntityEquipmentSlot.HEAD));
		ErthiliaItem pyriteChestplate = new ErthiliaItem("pyrite_chestplate",new ErthiliaArmor(ErthiliaTools.getArmorMaterial(10),8,EntityEquipmentSlot.CHEST));
		ErthiliaItem pyriteLeggings = new ErthiliaItem("pyrite_leggings",new ErthiliaArmor(ErthiliaTools.getArmorMaterial(10),8,EntityEquipmentSlot.LEGS));
		ErthiliaItem pyriteBoots = new ErthiliaItem("pyrite_boots",new ErthiliaArmor(ErthiliaTools.getArmorMaterial(10),8,EntityEquipmentSlot.FEET));
		ErthiliaItem pyriteBow = new ErthiliaItem("pyrite_bow",new ErthiliaBow(400,1.3f,80000));
		ErthiliaItem pyriteMultitools = new ErthiliaItem("pyrite_multitools",new ErthiliaMultiTools(ErthiliaTools.getToolMaterial(3)));
	
		//Citrine
		ErthiliaItem citrine = new ErthiliaItem("citrine",new ErthiliaBasicItem().setMaxStackSize(64).setNoRepair().setMaxDamage(0).setCreativeTab(CreativeTabs.MATERIALS));
		ErthiliaItem citrineSword = new ErthiliaItem("citrine_sword",new ErthiliaSword(ErthiliaTools.getToolMaterial(4)));
		ErthiliaItem citrinePickaxe = new ErthiliaItem("citrine_pickaxe",new ErthiliaPickaxe(ErthiliaTools.getToolMaterial(4)));
		ErthiliaItem citrineShovel = new ErthiliaItem("citrine_shovel",new ErthiliaSpade(ErthiliaTools.getToolMaterial(4)));
		ErthiliaItem citrineAxe = new ErthiliaItem("citrine_axe",new ErthiliaAxe(ErthiliaTools.getToolMaterial(4),8.0f,-3.0f));
		ErthiliaItem citrineHoe = new ErthiliaItem("citrine_hoe",new ErthiliaHoe(ErthiliaTools.getToolMaterial(4)));
		ErthiliaItem citrineHelmet = new ErthiliaItem("citrine_helmet",new ErthiliaArmor(ErthiliaTools.getArmorMaterial(11),9,EntityEquipmentSlot.HEAD));
		ErthiliaItem citrineChestplate = new ErthiliaItem("citrine_chestplate",new ErthiliaArmor(ErthiliaTools.getArmorMaterial(11),9,EntityEquipmentSlot.CHEST));
		ErthiliaItem citrineLeggings = new ErthiliaItem("citrine_leggings",new ErthiliaArmor(ErthiliaTools.getArmorMaterial(11),9, EntityEquipmentSlot.LEGS));
		ErthiliaItem citrineBoots = new ErthiliaItem("citrine_boots",new ErthiliaArmor(ErthiliaTools.getArmorMaterial(11),9, EntityEquipmentSlot.FEET));
		ErthiliaItem citrineBow = new ErthiliaItem("citrine_bow",new ErthiliaBow(400,1.4f,75000));
		ErthiliaItem citrineMultitools = new ErthiliaItem("citrine_multitools",new ErthiliaMultiTools(ErthiliaTools.getToolMaterial(4)));
	
		//Lumiére
		ErthiliaItem fairyHelmetFragment = new ErthiliaItem("fairy_helmet_fragment",new ErthiliaBasicItem().setMaxStackSize(64).setNoRepair().setMaxDamage(0).setCreativeTab(CreativeTabs.MATERIALS));
		ErthiliaItem fairyChestplateFragment = new ErthiliaItem("fairy_chestplate_fragment",new ErthiliaBasicItem().setMaxStackSize(64).setNoRepair().setMaxDamage(0).setCreativeTab(CreativeTabs.MATERIALS));
		ErthiliaItem fairyLeggingsFragment = new ErthiliaItem("fairy_leggings_fragment",new ErthiliaBasicItem().setMaxStackSize(64).setNoRepair().setMaxDamage(0).setCreativeTab(CreativeTabs.MATERIALS));
		ErthiliaItem fairyBootsFragment = new ErthiliaItem("fairy_boots_fragment",new ErthiliaBasicItem().setMaxStackSize(64).setNoRepair().setMaxDamage(0).setCreativeTab(CreativeTabs.MATERIALS));
		
		
		ErthiliaItem fairyHelmet = new ErthiliaItem("fairy_helmet",new ErthiliaArmor(ErthiliaTools.getArmorMaterial(12),10,EntityEquipmentSlot.HEAD));
		ErthiliaItem fairyChestplate = new ErthiliaItem("fairy_chestplate",new ErthiliaArmor(ErthiliaTools.getArmorMaterial(12),10,EntityEquipmentSlot.CHEST));
		ErthiliaItem fairyLeggings = new ErthiliaItem("fairy_leggings",new ErthiliaArmor(ErthiliaTools.getArmorMaterial(12),10,EntityEquipmentSlot.LEGS));
		ErthiliaItem fairyBoots = new ErthiliaItem("fairy_boots",new ErthiliaArmor(ErthiliaTools.getArmorMaterial(12),10, EntityEquipmentSlot.FEET));
		ErthiliaItem fairySword = new ErthiliaItem("fairy_sword",new ErthiliaSword(ErthiliaTools.getToolMaterial(5)));
		
		//Ombre
		ErthiliaItem blackDemonHelmetFragment = new ErthiliaItem("black_demon_helmet_fragment",new ErthiliaBasicItem().setMaxStackSize(64).setNoRepair().setMaxDamage(0).setCreativeTab(CreativeTabs.MATERIALS));
		ErthiliaItem blackDemonChestplateFragment = new ErthiliaItem("black_demon_chestplate_fragment",new ErthiliaBasicItem().setMaxStackSize(64).setNoRepair().setMaxDamage(0).setCreativeTab(CreativeTabs.MATERIALS));
		ErthiliaItem blackDemonLeggingsFragment = new ErthiliaItem("black_demon_leggings_fragment",new ErthiliaBasicItem().setMaxStackSize(64).setNoRepair().setMaxDamage(0).setCreativeTab(CreativeTabs.MATERIALS));
		ErthiliaItem blackDemonBootsFragment = new ErthiliaItem("black_demon_boots_fragment",new ErthiliaBasicItem().setMaxStackSize(64).setNoRepair().setMaxDamage(0).setCreativeTab(CreativeTabs.MATERIALS));
		ErthiliaItem blackDemonHelmet = new ErthiliaItem("black_demon_helmet",new ErthiliaArmor(ErthiliaTools.getArmorMaterial(13),11,EntityEquipmentSlot.HEAD));
		ErthiliaItem blackDemonChestplate = new ErthiliaItem("black_demon_chestplate",new ErthiliaArmor(ErthiliaTools.getArmorMaterial(13),11,EntityEquipmentSlot.CHEST));
		ErthiliaItem blackDemonLeggings = new ErthiliaItem("black_demon_leggings",new ErthiliaArmor(ErthiliaTools.getArmorMaterial(13),11,EntityEquipmentSlot.LEGS));
		ErthiliaItem blackDemonBoots = new ErthiliaItem("black_demon_boots",new ErthiliaArmor(ErthiliaTools.getArmorMaterial(13),11,EntityEquipmentSlot.FEET));
		ErthiliaItem blackDemonSword = new ErthiliaItem("black_demon_sword",new ErthiliaSword(ErthiliaTools.getToolMaterial(6)));
		
		//Recherche
		ErthiliaItem searchHelmet = new ErthiliaItem("search_helmet",new ErthiliaArmor(ErthiliaTools.getArmorMaterial(14),12,EntityEquipmentSlot.HEAD));
		ErthiliaItem searchChestplate  = new ErthiliaItem("search_chestplate",new ErthiliaArmor(ErthiliaTools.getArmorMaterial(14),12,EntityEquipmentSlot.CHEST));
		ErthiliaItem searchLeggings  = new ErthiliaItem("search_leggings",new ErthiliaArmor(ErthiliaTools.getArmorMaterial(14),12,EntityEquipmentSlot.LEGS));
		ErthiliaItem searchBoots  = new ErthiliaItem("search_boots",new ErthiliaArmor(ErthiliaTools.getArmorMaterial(14),12,EntityEquipmentSlot.FEET));
		
	
	
		ErthiliaItem jadeKey = new ErthiliaItem("jade_key",new ErthiliaBasicItem().setMaxStackSize(64).setNoRepair().setMaxDamage(0).setCreativeTab(CreativeTabs.MATERIALS));
		ErthiliaItem morganiteKey = new ErthiliaItem("morganite_key",new ErthiliaBasicItem().setMaxStackSize(64).setNoRepair().setMaxDamage(0).setCreativeTab(CreativeTabs.MATERIALS));
		ErthiliaItem citrineKey = new ErthiliaItem("citrine_key",new ErthiliaBasicItem().setMaxStackSize(64).setNoRepair().setMaxDamage(0).setCreativeTab(CreativeTabs.MATERIALS));
	
		ErthiliaDropProbability.addItemRandomOre(jade.getItem(), 1, 90);
		ErthiliaDropProbability.addItemRandomOre(jade.getItem(), 3, 80);
		ErthiliaDropProbability.addItemRandomOre(jade.getItem(), 5, 70);
		ErthiliaDropProbability.addItemRandomOre(cyanite.getItem(), 1, 75);
		ErthiliaDropProbability.addItemRandomOre(cyanite.getItem(), 3, 60);
		ErthiliaDropProbability.addItemRandomOre(morganite.getItem(), 1, 60);
		ErthiliaDropProbability.addItemRandomOre(morganite.getItem(), 2, 50);
		ErthiliaDropProbability.addItemRandomOre(pyrite.getItem(), 1, 50);
		ErthiliaDropProbability.addItemRandomOre(pyrite.getItem(), 5, 30);
		ErthiliaDropProbability.addItemRandomOre(citrine.getItem(), 1, 30);
		ErthiliaDropProbability.addItemRandomOre(citrine.getItem(), 5, 10);

		//Jade
		ErthiliaBlock jadeBlock = new ErthiliaBlock("jade_block",new ErthiliaBasicBlock(2,true));
		ErthiliaBlock jadeOre = new ErthiliaBlock("jade_ore",new ErthiliaBlockOre(2).setHardness(5.0F).setResistance(5.0F));
		ErthiliaBlock jadeFurnace = new ErthiliaBlock("jade_furnace",new ErthiliaJadeFurnace(false).setHardness(3.5F));
		ErthiliaBlock jadeFurnaceLit = new ErthiliaBlock("jade_furnace_lit",new ErthiliaJadeFurnace(true).setHardness(3.5F));
		
		ErthiliaBlock jadeChest = new ErthiliaBlock("jade_chest",new ErthiliaJadeChest(2).setHardness(2.5F));

		ErthiliaBlock jadeLadder = new ErthiliaBlock("jade_ladder",new ErthiliaBlockLadder().setHardness(0.4F));
		
		//Cyanite
		ErthiliaBlock cyaniteBlock = new ErthiliaBlock("cyanite_block",new ErthiliaBasicBlock(2,true).setHardness(5.0F).setResistance(10.0F));
		ErthiliaBlock cyaniteOre = new ErthiliaBlock("cyanite_ore",new ErthiliaBlockOre(2).setHardness(5.0F).setResistance(5.0F));
		
		//Morganite
		ErthiliaBlock morganiteBlock = new ErthiliaBlock("morganite_block",new ErthiliaBasicBlock(3,true).setHardness(5.0F).setResistance(10.0F));
		ErthiliaBlock morganiteOre = new ErthiliaBlock("morganite_ore",new ErthiliaBlockOre(3).setHardness(3.0F).setResistance(5.0F));
		
		//Pyrite
		ErthiliaBlock pyriteBlock = new ErthiliaBlock("pyrite_block",new ErthiliaBasicBlock(3,true).setHardness(5.0F).setResistance(10.0F));
		ErthiliaBlock pyriteOre = new ErthiliaBlock("pyrite_ore",new ErthiliaBlockOre(3).setHardness(3.0F).setResistance(5.0F));

		//Citrine
		ErthiliaBlock citrineBlock = new ErthiliaBlock("citrine_block",new ErthiliaBasicBlock(3,true).setHardness(5.0F).setResistance(10.0F));
		ErthiliaBlock citrineOre = new ErthiliaBlock("citrine_ore",new ErthiliaBlockOre(3).setHardness(3.0F).setResistance(5.0F));

		//XpPlant 
		ErthiliaBlock xpPlant = new ErthiliaBlock("xp_plant",new ErthiliaBlockXpPlant().setHardness(0F));
		
		//Tabac
		ErthiliaBlock tabacco = new ErthiliaBlock("tabacco",new ErthiliaBlockTabacco().setHardness(0F));
		ErthiliaItem tabaccoLeaf = new ErthiliaItem("tabacco_leaf",new ErthiliaBasicItem().setCreativeTab(CreativeTabs.FOOD));
		ErthiliaItem rollingPaper = new ErthiliaItem("rolling_paper",new ErthiliaBasicItem().setCreativeTab(CreativeTabs.MISC));

		
		
		//Weed
		ErthiliaBlock cannabis = new ErthiliaBlock("cannabis",new ErthiliaBlockWeed().setHardness(0F));
		ErthiliaItem cannabisLeaf = new ErthiliaItem("cannabis_leaf",new ErthiliaBasicItem().setCreativeTab(CreativeTabs.FOOD));
		
		
		ErthiliaItem join = new ErthiliaItem("join",new ErthiliaFood(EnumAction.NONE,4, 1.2F, false,32).addEffect(new PotionEffect(MobEffects.NAUSEA,20*8,1)).addEffect(new PotionEffect(MobEffects.HASTE,20*60,0)).addEffect(new PotionEffect(MobEffects.STRENGTH,20*60,0)).addEffect(new PotionEffect(MobEffects.REGENERATION,20*60,2)).addEffect(new PotionEffect(MobEffects.RESISTANCE,20*60,0)).addEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE,20*60,0)).setAlwaysEdible()).constructRendering(new IItemRenderer()
		{
			
			private float itemFirstPersonRotationX;
			private float itemFirstPersonTranslationY;


			@Override
			public void renderItemFirstPerson(EntityPlayer itemUser, ItemStack heldItemStack, long deltaFrame) 
			{
				if(itemUser.getItemInUseCount() > 0)
				{
					float value = (float)itemUser.getItemInUseCount() / itemUser.getItemInUseMaxCount();
					itemFirstPersonRotationX = MathsUtils.Lerp(itemFirstPersonRotationX, 70, value);
					itemFirstPersonTranslationY = MathsUtils.Lerp(itemFirstPersonTranslationY, 0.8f, value);
				}
				else
				{
					itemFirstPersonRotationX = 0;
					itemFirstPersonTranslationY = 0;
				}
				
				GL11.glRotatef(-itemFirstPersonRotationX, 1, 0, 0);
				GL11.glTranslatef(0, itemFirstPersonTranslationY, 0);
				
			
			}

			@Override
			public void setupRenderPlayerHeldItem(EntityPlayer player, ModelPlayer playerModel, ItemStack heldItemStack,long deltaFrame) { }
		});;
		
		//OtherBlock
		ErthiliaBlock randomOreBlock = new ErthiliaBlock("random_ore",new ErthiliaBlockOre(1).setHardness(5.0F).setResistance(50.0F));
		ErthiliaBlock xpBlock = new ErthiliaBlock("xp_block",new ErthiliaBasicBlock(1,true).setHardness(5.0F).setResistance(50.0F));
		ErthiliaBlock eventBlock = new ErthiliaBlock("event_block",new ErthiliaBasicBlock(1,false).setHardness(50.0F).setResistance(50.0F));
		
		ErthiliaBlock reinforced_obsidianBlock = new ErthiliaBlock("reinforced_obsidian",new ErthiliaBasicBlock(3,false).setHardness(50.0F).setResistance(2000.0F));
		ErthiliaBlock anti_piston = new ErthiliaBlock("anti_piston",new ErthiliaAntiPistonBlock(1,Material.ROCK).setHardness(10.0F).setResistance(10.0F).setCreativeTab(ErthiliaCreativeTabs.instance));

		
		
		ErthiliaBlock reverseEnchantingTable = new ErthiliaBlock("reversed_enchanting_table", new ErthiliaReversedEnchantingTable().setHardness(5.0F).setResistance(2000.0F));
		ErthiliaBlock reverseCraftingTable = new ErthiliaBlock("reversed_crafting_table", new ErthiliaReversedCraftingTable().setHardness(5.0F).setResistance(2000.0F));

		
		ErthiliaBlock blockMystery = new ErthiliaBlock("block_mystery", new ErthiliaMysteryBlock().setHardness(5.0F).setResistance(2000.0F));
		ErthiliaBlock jumpBlock = new ErthiliaBlock("block_jump", new ErthiliaBlockJump(5,2,5).setHardness(5.0F).setResistance(2000.0F));
		ErthiliaBlock speedBlock = new ErthiliaBlock("block_speed", new ErthiliaBlockSpeed(3f,3f).setHardness(5.0F).setResistance(2000.0F));

		
		ErthiliaBlock syringeStandBlock = new ErthiliaBlock("syringe_stand", new ErthiliaSyringeStand().setHardness(5.0F).setResistance(2000.0F));
	
		//Sceptre					
		ErthiliaItem antichuteISceptre = new ErthiliaItem("scepter_feather_falling",new ErthiliaScepter(new ScepterEffect("erthilia:anti_fall_damage",0,20*60*8, new ArrayList<String>()),1));
		ErthiliaItem fireSceptre = new ErthiliaItem("scepter_fire_resistance",new ErthiliaScepter(new ScepterEffect("fire_resistance",0,20*60*5,new ArrayList<String>()),1));
		ErthiliaItem hasteSceptre = new ErthiliaItem("scepter_haste",new ErthiliaScepter(new ScepterEffect("haste",1,20*60*4,new ArrayList<String>()),1));
		ErthiliaItem healSceptre = new ErthiliaItem("scepter_heal",new ErthiliaScepter(new HealEffectScepter(8),3));
		ErthiliaItem removeEffectSceptre = new ErthiliaItem("scepter_remove",new ErthiliaScepter(new RemoveEffectScepter(),1));
		ErthiliaItem speedISceptre = new ErthiliaItem("scepter_speed",new ErthiliaScepter(new ScepterEffect("speed",1,20*60*3,new ArrayList<String>()),1));

		
		
		ErthiliaItem unicornHorn = new ErthiliaItem("unicorn_horn",new ErthiliaBasicItem().setCreativeTab(CreativeTabs.MATERIALS));

		ErthiliaItem spear = new ErthiliaItem("spear",new ErthiliaSpear(1000,40000)).constructRendering(new IItemRenderer()
		{

			private float itemFirstPersonTranslationZ;
			
			@Override
			public void renderItemFirstPerson(EntityPlayer itemUser, ItemStack heldItemStack, long deltaTime)
			{
				GL11.glPopMatrix();
				GL11.glPushMatrix();
				
				Minecraft mc = Minecraft.getMinecraft();
				
				if(mc.gameSettings.viewBobbing)
				{
					applyBobbing(mc.getRenderPartialTicks());
				}
				
				if(itemUser.getItemInUseCount() > 0)
				{
					itemFirstPersonTranslationZ = MathsUtils.Lerp(itemFirstPersonTranslationZ, 0.5f, 0.00098f * deltaTime);
				}
				else
				{
					itemFirstPersonTranslationZ = 0;
				}	
				
				GL11.glTranslatef(0, 0, itemFirstPersonTranslationZ);

			}

			private float rightArmRotateAngleX = -180;

			@Override
			public void setupRenderPlayerHeldItem(EntityPlayer player, ModelPlayer playerModel,ItemStack heldItemStack, long deltaTime) 
			{
				if(player.getHeldItemMainhand().getItem() instanceof ErthiliaSpear)
				{
					if(player.getItemInUseCount() > 0)
					{
						rightArmRotateAngleX = -MathsUtils.Lerp(-rightArmRotateAngleX, 240, 0.000098f * deltaTime); //* deltaTime);
					}
					else
					{
						rightArmRotateAngleX = -180;
					}
					playerModel.bipedRightArm.rotateAngleX = MathsUtils.Deg2Rad * rightArmRotateAngleX;
				}
				else
				{
					playerModel.bipedRightArm.rotateAngleX = 0;
				}
			}
			
		    private void applyBobbing(float partialTicks)
		    {
		    	Minecraft mc = Minecraft.getMinecraft();
		        if (mc.getRenderViewEntity() instanceof EntityPlayer)
		        {
		            EntityPlayer entityplayer = (EntityPlayer)mc.getRenderViewEntity();
		            float f = entityplayer.distanceWalkedModified - entityplayer.prevDistanceWalkedModified;
		            float f1 = -(entityplayer.distanceWalkedModified + f * partialTicks);
		            float f2 = entityplayer.prevCameraYaw + (entityplayer.cameraYaw - entityplayer.prevCameraYaw) * partialTicks;
		            float f3 = entityplayer.prevCameraPitch + (entityplayer.cameraPitch - entityplayer.prevCameraPitch) * partialTicks;
		            GlStateManager.translate(MathHelper.sin(f1 * (float)Math.PI) * f2 * 0.5F, -Math.abs(MathHelper.cos(f1 * (float)Math.PI) * f2), 0.0F);
		            GlStateManager.rotate(MathHelper.sin(f1 * (float)Math.PI) * f2 * 3.0F, 0.0F, 0.0F, 1.0F);
		            GlStateManager.rotate(Math.abs(MathHelper.cos(f1 * (float)Math.PI - 0.2F) * f2) * 5.0F, 1.0F, 0.0F, 0.0F);
		            GlStateManager.rotate(f3, 1.0F, 0.0F, 0.0F);
		        }
		    }
			
		});
		
		ErthiliaItem syringeVial = new ErthiliaItem("syringe_vial",new ErthiliaBasicItem().setMaxStackSize(4).setNoRepair().setMaxDamage(0).setCreativeTab(CreativeTabs.MATERIALS));
		
		
		ErthiliaItem needle = new ErthiliaItem("needle",new ErthiliaBasicItem().setMaxStackSize(1).setNoRepair().setMaxDamage(0).setCreativeTab(CreativeTabs.MATERIALS));
		
		ErthiliaItem syringe = new ErthiliaItem("empty_syringe",new ErthiliaBasicItem().setCreativeTab(CreativeTabs.MISC).setMaxStackSize(64));	
		ErthiliaItem water_syringe = new ErthiliaItem("water_syringe",new ErthiliaSyringe().setCreativeTab(CreativeTabs.MISC)).constructRendering(new IItemRenderer()
		{
			
			private float itemFirstPersonRotationX;
			private float itemFirstPersonRotationZ;
			
			private float itemFirstPersonTranslationX;
			private float itemFirstPersonTranslationY;
			


			@Override
			public void renderItemFirstPerson(EntityPlayer itemUser, ItemStack heldItemStack, long deltaFrame) 
			{
				if(itemUser.getItemInUseCount() > 0)
				{
					float value = (float)itemUser.getItemInUseCount() / itemUser.getItemInUseMaxCount();
					
					itemFirstPersonTranslationX = MathsUtils.Lerp(itemFirstPersonTranslationX, 0.3f, value);
					itemFirstPersonTranslationY = MathsUtils.Lerp(itemFirstPersonTranslationY, -0.35f, value);
					
					itemFirstPersonRotationZ = MathsUtils.Lerp(itemFirstPersonRotationZ, 130, value);
					itemFirstPersonRotationX = MathsUtils.Lerp(itemFirstPersonRotationX, 50, value);
					
				}
				else
				{
					itemFirstPersonTranslationX = 0;
					itemFirstPersonTranslationY = 0;
					itemFirstPersonRotationZ = 0;
					itemFirstPersonRotationX = 0;
				}
				
				
				GL11.glTranslatef(itemFirstPersonTranslationX, itemFirstPersonTranslationY, 0);
				GL11.glRotatef(itemFirstPersonRotationZ, 0, 0, 1);
				GL11.glRotatef(itemFirstPersonRotationX, 1, 0, 0);

			}

			@Override
			public void setupRenderPlayerHeldItem(EntityPlayer player, ModelPlayer playerModel, ItemStack heldItemStack,long deltaFrame) { }
		});
		
		ErthiliaItem xpPlantSeeds = new ErthiliaItem("seeds_xp_plant",new ErthiliaItemSeeds(ErthiliaBlock.getBlock("erthilia:xp_plant"), Blocks.FARMLAND));

		ErthiliaItem tabaccoSeeds = new ErthiliaItem("seeds_tabacco",new ErthiliaItemSeeds(ErthiliaBlock.getBlock("erthilia:tabacco"), Blocks.FARMLAND));

		ErthiliaItem cannabisSeeds = new ErthiliaItem("seeds_cannabis",new ErthiliaItemSeeds(ErthiliaBlock.getBlock("erthilia:cannabis"), Blocks.FARMLAND));

	
		MinecraftForge.EVENT_BUS.register(this);
	    FMLCommonHandler.instance().bus().register(this);
	}
	
	public void registerRecipes()
	{	
		GameRegistry.addSmelting(ErthiliaBlocks.JADE_ORE, new ItemStack(ErthiliaItems.JADE,1), 5);
		GameRegistry.addSmelting(ErthiliaBlocks.CYANITE_ORE, new ItemStack(ErthiliaItems.CYANITE,1), 8);
		GameRegistry.addSmelting(ErthiliaBlocks.MORGANITE_ORE, new ItemStack(ErthiliaItems.MORGANITE,1), 10);
		GameRegistry.addSmelting(ErthiliaBlocks.PYRITE_ORE, new ItemStack(ErthiliaItems.PYRITE,1), 13);
		GameRegistry.addSmelting(ErthiliaBlocks.CITRINE_ORE, new ItemStack(ErthiliaItems.CITRINE,1), 15);
	}
	
	@SideOnly(Side.CLIENT)
	private void registerKeyBinding()
	{
		keyBindings = new KeyBinding[2]; 
		  
		
		keyBindings[0] = new KeyBinding("key.togglesprint", Keyboard.KEY_T, "key.erthilia");
		keyBindings[1] = new KeyBinding("key.particlesGui", Keyboard.KEY_P, "key.erthilia");
		
		for (int i = 0; i < keyBindings.length; ++i) 
		{
		    ClientRegistry.registerKeyBinding(keyBindings[i]);
		}
	}
	
	private void registerEntityDrops()
	{
		List<String> defaultDragonDrops = new ArrayList<>();
		defaultDragonDrops.add("erthilia:fairy_sword 1");
		defaultDragonDrops.add("erthilia:fairy_helmet_fragment 2");
		defaultDragonDrops.add("erthilia:fairy_chestplate_fragment 2");
		defaultDragonDrops.add("erthilia:fairy_leggings_fragment 2");
		defaultDragonDrops.add("erthilia:fairy_boots_fragment 2");
		ErthiliaEntityDrop dragonDrops = new ErthiliaEntityDrop(defaultDragonDrops);
		
		List<String> defaultBlackDragonDrops = new ArrayList<>();
		defaultBlackDragonDrops.add("erthilia:black_demon_sword 1");
		defaultBlackDragonDrops.add("erthilia:black_demon_helmet_fragment 2");
		defaultBlackDragonDrops.add("erthilia:black_demon_chestplate_fragment 2");
		defaultBlackDragonDrops.add("erthilia:black_demon_leggings_fragment 2");
		defaultBlackDragonDrops.add("erthilia:black_demon_boots_fragment 2");
		ErthiliaEntityDrop blackDragonDrops = new ErthiliaEntityDrop(defaultBlackDragonDrops);
		
		List<String> defaultFireAtronachDrops = new ArrayList<>();
		defaultFireAtronachDrops.add("erthilia:black_demon_sword 1");
		defaultFireAtronachDrops.add("erthilia:black_demon_helmet_fragment 2");
		defaultFireAtronachDrops.add("erthilia:black_demon_chestplate_fragment 2");
		defaultFireAtronachDrops.add("erthilia:black_demon_leggings_fragment 2");
		defaultFireAtronachDrops.add("erthilia:black_demon_boots_fragment 2");
		ErthiliaEntityDrop fireAtronachDrop = new ErthiliaEntityDrop(defaultFireAtronachDrops);
		
		List<String> defaultUnicornDrop = new ArrayList<>();
		defaultUnicornDrop.add("erthilia:unicorn_horn 1");
		ErthiliaEntityDrop unicornDrops = new ErthiliaEntityDrop(defaultUnicornDrop);
		
		
		
		ErthiliaEntityDrop blockMystery = new ErthiliaEntityDrop(new ArrayList<>());
		
		dropsData.put(EntityDragon.class, dragonDrops);
		dropsData.put(EntityDragonBlack.class, blackDragonDrops);
		dropsData.put(EntityUnicorn.class, unicornDrops);
		dropsData.put(EntityFireAtronach.class, fireAtronachDrop);
		dropsData.put(ErthiliaMysteryBlock.class, blockMystery);

	}
	
	private void registerCosmetics(Side side) 
	{	
		CosmeticObject backpack = Main.getCosmeticsManager().registerCosmetic("Sac", false, (byte)2,0);
		CosmeticObject demonWings = Main.getCosmeticsManager().registerCosmetic("Aile de démon", false, (byte)2,1);
		CosmeticObject fairyWings = Main.getCosmeticsManager().registerCosmetic("Aile d'ange", false, (byte)2,2);
		CosmeticObject sunGlasses = Main.getCosmeticsManager().registerCosmetic("Lunette de soleil", false, (byte)1,3);
		CosmeticObject witchHat = Main.getCosmeticsManager().registerCosmetic("Chapeau sorcière", false, (byte)0,4);
		CosmeticObject afro = Main.getCosmeticsManager().registerCosmetic("Afro", false, (byte)0,5);
		CosmeticObject crown = Main.getCosmeticsManager().registerCosmetic("Couronne", false, (byte)0,6);
		CosmeticObject mexicanHat = Main.getCosmeticsManager().registerCosmetic("Chapeau mexicain", false, (byte)0,7);
		CosmeticObject backpack2 = Main.getCosmeticsManager().registerCosmetic("Sac 2", false, (byte)2,8);
		CosmeticObject fairyCompanion = Main.getCosmeticsManager().registerCompanion("Compagnon fée", false, (byte)3,9);
		CosmeticObject demonCompanion = Main.getCosmeticsManager().registerCompanion("Compagnon démon", false, (byte)3,10);
		CosmeticObject devilHorn = Main.getCosmeticsManager().registerCosmetic("Cornes diabolique", false, (byte)0, 11);
		CosmeticObject doctorBeak = Main.getCosmeticsManager().registerCosmetic("Bec de docteur", false, (byte)0, 12);
		CosmeticObject flamingoHat = Main.getCosmeticsManager().registerCosmetic("Chapeau de flamingo", false, (byte)0, 13);
		CosmeticObject magicianHat = Main.getCosmeticsManager().registerCosmetic("Chapeau de magicien", false, (byte)0, 14);
		CosmeticObject colorfulMaskBlue = Main.getCosmeticsManager().registerCosmetic("Masque coloré", false, (byte)1, 15);
		CosmeticObject colorfulMaskBlack = Main.getCosmeticsManager().registerCosmetic("Masque coloré", false, (byte)1, 16);
		CosmeticObject colorfulMaskOrange = Main.getCosmeticsManager().registerCosmetic("Masque coloré", false, (byte)1, 17);
		CosmeticObject colorfulMaskPink = Main.getCosmeticsManager().registerCosmetic("Masque coloré", false, (byte)1, 18);
		CosmeticObject colorfulMaskYellow = Main.getCosmeticsManager().registerCosmetic("Masque coloré", false, (byte)1, 19);
		CosmeticObject colorfulMaskRed = Main.getCosmeticsManager().registerCosmetic("Masque coloré", false, (byte)1, 20);
		CosmeticObject robotGlasses = Main.getCosmeticsManager().registerCosmetic("Lunettes de robot", false, (byte)1, 21);
		
		
		if(side == Side.CLIENT)
		{
			backpack.setupRender(new ICosmeticSetup()
			{
				@Override
				public void setupCosmeticGuiDisplay() 
				{
					GL11.glScalef(10f, 10f, 10f);
					GL11.glRotatef(160, 1, 0, 0);
					GL11.glRotatef(140, 0, 1, 0);

				}
		
			}, new ModelBackpack());
			
			demonWings.setupRender(new ICosmeticSetup()
			{
				@Override
				public void setupCosmeticGuiDisplay() 
				{
					GL11.glTranslatef(0, -18, 0);
					GL11.glScalef(15f, 15f, 15f);
					GL11.glRotatef(160, 1, 0, 0);
					GL11.glRotatef(140, 0, 1, 0);
				}
				
			
			}, new ModelDemonWings());
			
			fairyWings.setupRender(new ICosmeticSetup()
			{
				@Override
				public void setupCosmeticGuiDisplay() 
				{
					GL11.glTranslatef(-1, -13, 0);
					GL11.glScalef(15f, 15f, 15f);
					GL11.glRotatef(100, 1, 0, 0);
					GL11.glRotatef(150, 0, 0, 1);

					GL11.glRotatef(180, 0, 1, 0);
				}
				
				
				
			}, new ModelFairyWings());
			
			sunGlasses.setupRender(new ICosmeticSetup()
			{
				@Override
				public void setupCosmeticGuiDisplay() 
				{
					GL11.glScalef(10f, 10f, 10f);
					GL11.glRotatef(160, 1, 0, 0);
					GL11.glRotatef(140, 0, 1, 0);
					GL11.glTranslatef(-0.5f, 1.5f, 0);
				}

				
				
			}, new ModelSunGlasses());
			
			witchHat.setupRender(new ICosmeticSetup()
			{
				@Override
				public void setupCosmeticGuiDisplay() 
				{
					GL11.glScalef(10f, 10f, 10f);
					GL11.glRotatef(160, 1, 0, 0);
					GL11.glRotatef(140, 0, 1, 0);
					GL11.glTranslatef(-0.3f, 2.5f, 0);

				}


				
			}, new ModelWitchHat());
			
			afro.setupRender(new ICosmeticSetup()
			{
				@Override
				public void setupCosmeticGuiDisplay() 
				{
					GL11.glScalef(10f, 10f, 10f);
					GL11.glRotatef(160, 1, 0, 0);
					GL11.glRotatef(140, 0, 1, 0);
					GL11.glTranslatef(-0.3f, 2.0f, 0);
				}

				
			}, new ModelAfro());
			
			crown.setupRender(new ICosmeticSetup()
			{
				@Override
				public void setupCosmeticGuiDisplay() 
				{
					GL11.glScalef(10f, 10f, 10f);
					GL11.glRotatef(160, 1, 0, 0);
					GL11.glRotatef(140, 0, 1, 0);
					GL11.glTranslatef(-0.4f, 2.2f, 0);
				}

	
			}, new ModelCrown());
			
			mexicanHat.setupRender(new ICosmeticSetup()
			{
				@Override
				public void setupCosmeticGuiDisplay() 
				{
					GL11.glScalef(10f, 10f, 10f);
					GL11.glRotatef(160, 1, 0, 0);
					GL11.glRotatef(140, 0, 1, 0);
					GL11.glTranslatef(-0.3f, 2.2f, 0);
				}


			}, new ModelMexicanHat());
			
			backpack2.setupRender(new ICosmeticSetup()
			{
				@Override
				public void setupCosmeticGuiDisplay() 
				{
					GL11.glScalef(10f, 10f, 10f);
					GL11.glRotatef(160, 1, 0, 0);
					GL11.glRotatef(-50, 0, 1, 0);
					GL11.glTranslatef(0.7f, 0.4f, 0f);
				}


			}, new ModelBackpack2());
			
			fairyCompanion.setupRender(new ICosmeticSetup()
			{
				@Override
				public void setupCosmeticGuiDisplay() 
				{
					GL11.glTranslatef(2.8f, -9, 0);
					GL11.glRotatef(300, 0, 1, 0);

					GL11.glRotatef(-90, 1, 0, 0);
					GL11.glScalef(7.8f, 7.8f, 7.8f);
					
					GL11.glRotatef(-90, 1f, 0f, 0f);
					GL11.glRotatef(-185,0f, 1f, 0f);
				}


	
			}, new ModelFairyCompanion());
			
			demonCompanion.setupRender(new ICosmeticSetup()
			{
				@Override
				public void setupCosmeticGuiDisplay() 
				{
					GL11.glTranslatef(2, -9, 0);
					GL11.glRotatef(300, 0, 1, 0);

					GL11.glRotatef(-90, 1, 0, 0);
					GL11.glScalef(40, 40, 40);
					
					GL11.glRotatef(-90, 1f, 0f, 0f);
					GL11.glRotatef(-200,0f, 1f, 0f);
					GL11.glScalef(0.02f, 0.02f, 0.02f);

				}

			}, new ModelDemonCompanion());
			
			devilHorn.setupRender(new ICosmeticSetup()
			{
				@Override
				public void setupCosmeticGuiDisplay() 
				{
					GL11.glTranslatef(1.5f, -32, 0);
					GL11.glRotatef(300, 0, 1, 0);

					GL11.glRotatef(-90, 1, 0, 0);
					GL11.glScalef(40, 40, 40);
					
					GL11.glRotatef(-90, 1f, 0f, 0f);
					GL11.glRotatef(-200,0f, 1f, 0f);
					GL11.glScalef(5, 5, 5);

				}

			}, new ModelDevilHorn());
			
			doctorBeak.setupRender(new ICosmeticSetup()
			{
				@Override
				public void setupCosmeticGuiDisplay() 
				{
					GL11.glTranslatef(2, -18, 0);
					GL11.glRotatef(300, 0, 1, 0);

					GL11.glRotatef(-90, 1, 0, 0);
					GL11.glScalef(40, 40, 40);
					
					GL11.glRotatef(-90, 1f, 0f, 0f);
					GL11.glRotatef(-200,0f, 1f, 0f);
					GL11.glScalef(3f, 3f, 3f);

				}

			}, new ModelDoctorBeak());
			
			flamingoHat.setupRender(new ICosmeticSetup()
			{
				@Override
				public void setupCosmeticGuiDisplay() 
				{
					GL11.glTranslatef(2, -25, 0);
					GL11.glRotatef(300, 0, 1, 0);

					GL11.glRotatef(-90, 1, 0, 0);
					GL11.glScalef(40, 40, 40);
					
					GL11.glRotatef(-90, 1f, 0f, 0f);
					GL11.glRotatef(-200,0f, 1f, 0f);
					GL11.glScalef(3.5f, 3.5f, 3.5f);

				}

			}, new ModelFlamingoHat());
			
			magicianHat.setupRender(new ICosmeticSetup()
			{
				@Override
				public void setupCosmeticGuiDisplay() 
				{
					GL11.glTranslatef(2, -20, 0);
					GL11.glRotatef(300, 0, 1, 0);

					GL11.glRotatef(-90, 1, 0, 0);
					GL11.glScalef(40, 40, 40);
					
					GL11.glRotatef(-90, 1f, 0f, 0f);
					GL11.glRotatef(-200,0f, 1f, 0f);
					GL11.glScalef(3f, 3f, 3f);

				}

			}, new ModelMagicianHat());
			
			colorfulMaskBlue.setupRender(new ICosmeticSetup()
			{
				@Override
				public void setupCosmeticGuiDisplay() 
				{
					GL11.glTranslatef(2, -15, 0);
					GL11.glRotatef(300, 0, 1, 0);

					GL11.glRotatef(-90, 1, 0, 0);
					GL11.glScalef(40, 40, 40);
					
					GL11.glRotatef(-90, 1f, 0f, 0f);
					GL11.glRotatef(-200,0f, 1f, 0f);
					GL11.glScalef(4f, 4f, 4f);

				}

			}, new ModelColorfulMask(new ResourceLocation("erthilia","textures/cosmetics/colorful_mask_blue.png")));
			
			colorfulMaskOrange.setupRender(new ICosmeticSetup()
			{
				@Override
				public void setupCosmeticGuiDisplay() 
				{
					GL11.glTranslatef(2, -15, 0);
					GL11.glRotatef(300, 0, 1, 0);

					GL11.glRotatef(-90, 1, 0, 0);
					GL11.glScalef(40, 40, 40);
					
					GL11.glRotatef(-90, 1f, 0f, 0f);
					GL11.glRotatef(-200,0f, 1f, 0f);
					GL11.glScalef(4f, 4f, 4f);

				}

			}, new ModelColorfulMask(new ResourceLocation("erthilia","textures/cosmetics/colorful_mask_orange.png")));
			
			colorfulMaskBlack.setupRender(new ICosmeticSetup()
			{
				@Override
				public void setupCosmeticGuiDisplay() 
				{
					GL11.glTranslatef(2, -15, 0);
					GL11.glRotatef(300, 0, 1, 0);

					GL11.glRotatef(-90, 1, 0, 0);
					GL11.glScalef(40, 40, 40);
					
					GL11.glRotatef(-90, 1f, 0f, 0f);
					GL11.glRotatef(-200,0f, 1f, 0f);
					GL11.glScalef(4f, 4f, 4f);

				}

			}, new ModelColorfulMask(new ResourceLocation("erthilia","textures/cosmetics/colorful_mask_black.png")));
			
			colorfulMaskRed.setupRender(new ICosmeticSetup()
			{
				@Override
				public void setupCosmeticGuiDisplay() 
				{
					GL11.glTranslatef(2, -15, 0);
					GL11.glRotatef(300, 0, 1, 0);

					GL11.glRotatef(-90, 1, 0, 0);
					GL11.glScalef(40, 40, 40);
					
					GL11.glRotatef(-90, 1f, 0f, 0f);
					GL11.glRotatef(-200,0f, 1f, 0f);
					GL11.glScalef(4f, 4f, 4f);

				}

			}, new ModelColorfulMask(new ResourceLocation("erthilia","textures/cosmetics/colorful_mask_red.png")));
			
			colorfulMaskPink.setupRender(new ICosmeticSetup()
			{
				@Override
				public void setupCosmeticGuiDisplay() 
				{
					GL11.glTranslatef(2, -15, 0);
					GL11.glRotatef(300, 0, 1, 0);

					GL11.glRotatef(-90, 1, 0, 0);
					GL11.glScalef(40, 40, 40);
					
					GL11.glRotatef(-90, 1f, 0f, 0f);
					GL11.glRotatef(-200,0f, 1f, 0f);
					GL11.glScalef(4f, 4f, 4f);

				}

			}, new ModelColorfulMask(new ResourceLocation("erthilia","textures/cosmetics/colorful_mask_pink.png")));
			
			colorfulMaskYellow.setupRender(new ICosmeticSetup()
			{
				@Override
				public void setupCosmeticGuiDisplay() 
				{
					GL11.glTranslatef(2, -15, 0);
					GL11.glRotatef(300, 0, 1, 0);

					GL11.glRotatef(-90, 1, 0, 0);
					GL11.glScalef(40, 40, 40);
					
					GL11.glRotatef(-90, 1f, 0f, 0f);
					GL11.glRotatef(-200,0f, 1f, 0f);
					GL11.glScalef(4f, 4f, 4f);

				}

			}, new ModelColorfulMask(new ResourceLocation("erthilia","textures/cosmetics/colorful_mask_yellow.png")));
			
			robotGlasses.setupRender(new ICosmeticSetup()
			{
				@Override
				public void setupCosmeticGuiDisplay() 
				{
					GL11.glTranslatef(1, -20, 0);
					GL11.glRotatef(300, 0, 1, 0);

					GL11.glRotatef(-90, 1, 0, 0);
					GL11.glScalef(40, 40, 40);
					
					GL11.glRotatef(-90, 1f, 0f, 0f);
					GL11.glRotatef(-200,0f, 1f, 0f);
					GL11.glScalef(4f, 4f, 4f);

				}

			}, new ModelRobotGlasses());
			
			
		}
		
	}


	

}
