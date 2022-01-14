package fr.karmaowner.erthilia.tiles;

import fr.karmaowner.erthilia.Main;
import fr.karmaowner.erthilia.renderer.tileentity.TileEntityJadeChestRenderer;
import fr.karmaowner.erthilia.renderer.tileentity.TileEntityReversedCraftingTableRenderer;
import fr.karmaowner.erthilia.renderer.tileentity.TileEntitySyringeStandRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileEntityManager {
	
	public static void registerTileEntity() 
	{
		 GameRegistry.registerTileEntity(TileEntityJadeFurnace.class, new ResourceLocation(Main.MODID, "jade_furnace"));
		 GameRegistry.registerTileEntity(TileEntityJadeChest.class,new ResourceLocation(Main.MODID, "jade_chest"));
		 GameRegistry.registerTileEntity(TileEntityReversedEnchantingTable.class, new ResourceLocation(Main.MODID, "reversed_enchanting_table"));
		 GameRegistry.registerTileEntity(TileEntityReversedCraftingTable.class, new ResourceLocation(Main.MODID, "reversed_crafting_table"));
		 GameRegistry.registerTileEntity(TileEntitySyringeStand.class, new ResourceLocation(Main.MODID + "syringe_stand"));
		 GameRegistry.registerTileEntity(TileEntityObsidian.class, new ResourceLocation(Main.MODID + "obsidian"));
	}
	
	@SideOnly(Side.CLIENT)
	public static void registerTileEntityRenderer() 
	{ 
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityJadeChest.class, new TileEntityJadeChestRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySyringeStand.class, new TileEntitySyringeStandRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityReversedCraftingTable.class, new TileEntityReversedCraftingTableRenderer());
	}

}
