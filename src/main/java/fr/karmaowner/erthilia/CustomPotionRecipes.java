package fr.karmaowner.erthilia;

import fr.karmaowner.erthilia.items.ErthiliaItems;
import net.minecraft.init.Items;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionUtils;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;

public class CustomPotionRecipes {

	public CustomPotionRecipes()
	{
		BrewingRecipeRegistry.addRecipe(new ItemStack(ErthiliaItems.WATER_SYRINGE), new ItemStack(Items.SPECKLED_MELON,4), PotionUtils.addPotionToItemStack(new ItemStack(ErthiliaItems.WATER_SYRINGE), PotionTypes.MUNDANE));
		BrewingRecipeRegistry.addRecipe(new ItemStack(ErthiliaItems.WATER_SYRINGE), new ItemStack(Items.GHAST_TEAR,4), PotionUtils.addPotionToItemStack(new ItemStack(ErthiliaItems.WATER_SYRINGE), PotionTypes.MUNDANE));
		BrewingRecipeRegistry.addRecipe(new ItemStack(ErthiliaItems.WATER_SYRINGE), new ItemStack(Items.RABBIT_FOOT,4), PotionUtils.addPotionToItemStack(new ItemStack(ErthiliaItems.WATER_SYRINGE), PotionTypes.MUNDANE));
		BrewingRecipeRegistry.addRecipe(new ItemStack(ErthiliaItems.WATER_SYRINGE), new ItemStack(Items.BLAZE_POWDER,4), PotionUtils.addPotionToItemStack(new ItemStack(ErthiliaItems.WATER_SYRINGE), PotionTypes.MUNDANE));
		BrewingRecipeRegistry.addRecipe(new ItemStack(ErthiliaItems.WATER_SYRINGE), new ItemStack(Items.SPIDER_EYE,4), PotionUtils.addPotionToItemStack(new ItemStack(ErthiliaItems.WATER_SYRINGE), PotionTypes.MUNDANE));
		BrewingRecipeRegistry.addRecipe(new ItemStack(ErthiliaItems.WATER_SYRINGE), new ItemStack(Items.SUGAR,4), PotionUtils.addPotionToItemStack(new ItemStack(ErthiliaItems.WATER_SYRINGE), PotionTypes.MUNDANE));
		BrewingRecipeRegistry.addRecipe(new ItemStack(ErthiliaItems.WATER_SYRINGE), new ItemStack(Items.MAGMA_CREAM,4), PotionUtils.addPotionToItemStack(new ItemStack(ErthiliaItems.WATER_SYRINGE), PotionTypes.MUNDANE));
		BrewingRecipeRegistry.addRecipe(new ItemStack(ErthiliaItems.WATER_SYRINGE), new ItemStack(Items.GLOWSTONE_DUST,4), PotionUtils.addPotionToItemStack(new ItemStack(ErthiliaItems.WATER_SYRINGE), PotionTypes.THICK));
		BrewingRecipeRegistry.addRecipe(new ItemStack(ErthiliaItems.WATER_SYRINGE), new ItemStack(Items.REDSTONE,4), PotionUtils.addPotionToItemStack(new ItemStack(ErthiliaItems.WATER_SYRINGE), PotionTypes.MUNDANE));
		BrewingRecipeRegistry.addRecipe(new ItemStack(ErthiliaItems.WATER_SYRINGE), new ItemStack(Items.NETHER_WART,4), PotionUtils.addPotionToItemStack(new ItemStack(ErthiliaItems.WATER_SYRINGE), PotionTypes.AWKWARD));

		
		BrewingRecipeRegistry.addRecipe(PotionUtils.addPotionToItemStack(new ItemStack(ErthiliaItems.WATER_SYRINGE), PotionTypes.AWKWARD), new ItemStack(Items.MAGMA_CREAM,4), PotionUtils.addPotionToItemStack(new ItemStack(ErthiliaItems.WATER_SYRINGE), PotionTypes.FIRE_RESISTANCE));
		BrewingRecipeRegistry.addRecipe(PotionUtils.addPotionToItemStack(new ItemStack(ErthiliaItems.WATER_SYRINGE), PotionTypes.FIRE_RESISTANCE), new ItemStack(Items.REDSTONE,4), PotionUtils.addPotionToItemStack(new ItemStack(ErthiliaItems.WATER_SYRINGE), PotionTypes.LONG_FIRE_RESISTANCE));
		BrewingRecipeRegistry.addRecipe(PotionUtils.addPotionToItemStack(new ItemStack(ErthiliaItems.WATER_SYRINGE), PotionTypes.AWKWARD), new ItemStack(Items.SUGAR,4), PotionUtils.addPotionToItemStack(new ItemStack(ErthiliaItems.WATER_SYRINGE), PotionTypes.SWIFTNESS));
		BrewingRecipeRegistry.addRecipe(PotionUtils.addPotionToItemStack(new ItemStack(ErthiliaItems.WATER_SYRINGE), PotionTypes.SWIFTNESS), new ItemStack(Items.REDSTONE,4), PotionUtils.addPotionToItemStack(new ItemStack(ErthiliaItems.WATER_SYRINGE), PotionTypes.LONG_SWIFTNESS));
		BrewingRecipeRegistry.addRecipe(PotionUtils.addPotionToItemStack(new ItemStack(ErthiliaItems.WATER_SYRINGE), PotionTypes.SWIFTNESS), new ItemStack(Items.GLOWSTONE_DUST,4), PotionUtils.addPotionToItemStack(new ItemStack(ErthiliaItems.WATER_SYRINGE), PotionTypes.STRONG_SWIFTNESS));
		BrewingRecipeRegistry.addRecipe(PotionUtils.addPotionToItemStack(new ItemStack(ErthiliaItems.WATER_SYRINGE), PotionTypes.AWKWARD), new ItemStack(Items.SPECKLED_MELON,4), PotionUtils.addPotionToItemStack(new ItemStack(ErthiliaItems.WATER_SYRINGE), PotionTypes.HEALING));
		BrewingRecipeRegistry.addRecipe(PotionUtils.addPotionToItemStack(new ItemStack(ErthiliaItems.WATER_SYRINGE), PotionTypes.HEALING), new ItemStack(Items.GLOWSTONE_DUST,4), PotionUtils.addPotionToItemStack(new ItemStack(ErthiliaItems.WATER_SYRINGE), PotionTypes.STRONG_HEALING));
		BrewingRecipeRegistry.addRecipe(PotionUtils.addPotionToItemStack(new ItemStack(ErthiliaItems.WATER_SYRINGE), PotionTypes.AWKWARD), new ItemStack(Items.GHAST_TEAR,4), PotionUtils.addPotionToItemStack(new ItemStack(ErthiliaItems.WATER_SYRINGE), PotionTypes.REGENERATION));
		BrewingRecipeRegistry.addRecipe(PotionUtils.addPotionToItemStack(new ItemStack(ErthiliaItems.WATER_SYRINGE), PotionTypes.REGENERATION), new ItemStack(Items.REDSTONE,4), PotionUtils.addPotionToItemStack(new ItemStack(ErthiliaItems.WATER_SYRINGE), PotionTypes.LONG_REGENERATION));
		BrewingRecipeRegistry.addRecipe(PotionUtils.addPotionToItemStack(new ItemStack(ErthiliaItems.WATER_SYRINGE), PotionTypes.REGENERATION), new ItemStack(Items.GLOWSTONE_DUST,4), PotionUtils.addPotionToItemStack(new ItemStack(ErthiliaItems.WATER_SYRINGE), PotionTypes.STRONG_REGENERATION));
		BrewingRecipeRegistry.addRecipe(PotionUtils.addPotionToItemStack(new ItemStack(ErthiliaItems.WATER_SYRINGE), PotionTypes.AWKWARD), new ItemStack(Items.BLAZE_POWDER,4), PotionUtils.addPotionToItemStack(new ItemStack(ErthiliaItems.WATER_SYRINGE), PotionTypes.STRENGTH));
		BrewingRecipeRegistry.addRecipe(PotionUtils.addPotionToItemStack(new ItemStack(ErthiliaItems.WATER_SYRINGE), PotionTypes.STRENGTH), new ItemStack(Items.REDSTONE,4), PotionUtils.addPotionToItemStack(new ItemStack(ErthiliaItems.WATER_SYRINGE), PotionTypes.LONG_STRENGTH));

	}
	
}
