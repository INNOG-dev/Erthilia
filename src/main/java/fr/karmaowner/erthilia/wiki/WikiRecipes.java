package fr.karmaowner.erthilia.wiki;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import fr.karmaowner.erthilia.gui.WikiUI;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class WikiRecipes 
{
	public static List<WikiRecipes> loadedRecipes = new ArrayList<>();
	
	@SideOnly(Side.CLIENT)
	public WikiUI.WikiButton button;
		
	private ItemStack result;
	
	private ItemStack[] recipe = new ItemStack[9];

	public WikiRecipes()
	{
		
	}
	
	public ItemStack[] getRecipe()
	{
		return recipe;
	}
	
	public ItemStack getResult()
	{
		return result;
	}
	
	public void setResult(ItemStack is)
	{
		this.result = is;
	}

	public void setRecipes(ItemStack[] recipe)
	{
		this.recipe = recipe;
	}
	
	public static void loadRecipes()
	{
		Iterator<IRecipe> irecipe = CraftingManager.REGISTRY.iterator();
		
		while(irecipe.hasNext())
		{
			IRecipe recipe = irecipe.next();
			
			if(recipe.getRecipeOutput() == ItemStack.EMPTY) continue;
			
			loadedRecipes.add(setupRecipe(recipe));
		}
	}
	
	private static WikiRecipes setupRecipe(IRecipe recipe)
	{
		WikiRecipes wikiRecipes = new WikiRecipes();
		
		int i = 3;
		int j = 3;
        int k = recipe instanceof net.minecraftforge.common.crafting.IShapedRecipe ? ((net.minecraftforge.common.crafting.IShapedRecipe) recipe).getRecipeWidth() : i;
        int l = 0;
        
        ItemStack[] ingredients = new ItemStack[9];

        Iterator<Ingredient> iterator = recipe.getIngredients().iterator();

        wikiRecipes.setRecipes(ingredients);
        wikiRecipes.setResult(recipe.getRecipeOutput()); 
        
        for (int i1 = 0; i1 < j; ++i1)
        {
            for (int j1 = 0; j1 < k; ++j1)
            {
                if (!iterator.hasNext())
                {
           
                	
                    return wikiRecipes;
                }

                Ingredient ingredient = iterator.next();

                if (ingredient.getMatchingStacks().length > 0)
                {
                	ingredients[l] = ingredient.getMatchingStacks()[0];
                }

                ++l;
            }

            if (k < i)
            {
                l += i - k;
            }
        }
        
        return wikiRecipes;
	}

	
}



