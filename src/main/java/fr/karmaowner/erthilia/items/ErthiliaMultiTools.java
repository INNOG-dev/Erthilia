package fr.karmaowner.erthilia.items;

import java.util.Set;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

import fr.karmaowner.client.creativetab.ErthiliaCreativeTabs;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ErthiliaMultiTools extends ItemTool {
	
	private static final Set<Block> field_150915_c = Sets.newHashSet(new Block[] {Blocks.COBBLESTONE, Blocks.DOUBLE_STONE_SLAB, Blocks.STONE_SLAB, Blocks.STONE, Blocks.SANDSTONE, Blocks.MOSSY_COBBLESTONE, Blocks.IRON_ORE, Blocks.IRON_BLOCK, Blocks.COAL_ORE, Blocks.GOLD_BLOCK, Blocks.GOLD_ORE, Blocks.DIAMOND_ORE, Blocks.DIAMOND_BLOCK, Blocks.ICE, Blocks.NETHERRACK, Blocks.LAPIS_ORE, Blocks.LAPIS_BLOCK, Blocks.REDSTONE_ORE, Blocks.LIT_REDSTONE_ORE, Blocks.RAIL, Blocks.DETECTOR_RAIL, Blocks.GOLDEN_RAIL, Blocks.ACTIVATOR_RAIL, Blocks.PLANKS, Blocks.BOOKSHELF, Blocks.LOG, Blocks.LOG2, Blocks.CHEST, Blocks.PUMPKIN, Blocks.LIT_PUMPKIN, Blocks.GRASS, Blocks.DIRT, Blocks.SAND, Blocks.GRAVEL, Blocks.SNOW_LAYER, Blocks.SNOW, Blocks.CLAY, Blocks.FARMLAND, Blocks.SOUL_SAND, Blocks.MYCELIUM});

	public ErthiliaMultiTools(ToolMaterial toolMaterial) {
		super(toolMaterial, field_150915_c);
		this.setCreativeTab(ErthiliaCreativeTabs.instance);

	}
	
	@Override
    public boolean getIsRepairable(ItemStack input, ItemStack repair)
    {
        return isRepairable() ? repair.getItem() == ErthiliaItem.getItem(this.getUnlocalizedName()) : false;
    }
	
	@Override
	public Set<String> getToolClasses(ItemStack stack) { return ImmutableSet.of("pickaxe", "spade", "axe");}
	
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
		
        ItemStack itemstack = player.getHeldItem(hand);

		
	    if (!player.canPlayerEdit(pos, facing, itemstack))
	    {
	    	return EnumActionResult.FAIL;
	    }
	    else
	    {
            int hook = net.minecraftforge.event.ForgeEventFactory.onHoeUse(itemstack, player, worldIn, pos);
            
            if (hook != 0) return hook > 0 ? EnumActionResult.SUCCESS : EnumActionResult.FAIL;
            
            IBlockState iblockstate = worldIn.getBlockState(pos);
            
            Block block = iblockstate.getBlock();
            
            if (facing != EnumFacing.DOWN && worldIn.isAirBlock(pos.up()))
            {
                if (block == Blocks.GRASS || block == Blocks.GRASS_PATH)
                {
                    this.setBlock(itemstack, player, worldIn, pos, Blocks.FARMLAND.getDefaultState());
                    return EnumActionResult.SUCCESS;
                }

                if (block == Blocks.DIRT)
                {
                    switch ((BlockDirt.DirtType)iblockstate.getValue(BlockDirt.VARIANT))
                    {
                        case DIRT:
                            this.setBlock(itemstack, player, worldIn, pos, Blocks.FARMLAND.getDefaultState());
                            return EnumActionResult.SUCCESS;
                        case COARSE_DIRT:
                            this.setBlock(itemstack, player, worldIn, pos, Blocks.DIRT.getDefaultState().withProperty(BlockDirt.VARIANT, BlockDirt.DirtType.DIRT));
                            return EnumActionResult.SUCCESS;
                        default:
                            return EnumActionResult.FAIL;
                    }
                }
            }
            
            return EnumActionResult.PASS;
	    }
	       
	 }
	    
	 protected void setBlock(ItemStack stack, EntityPlayer player, World worldIn, BlockPos pos, IBlockState state)
	 {
	        worldIn.playSound(player, pos, SoundEvents.ITEM_HOE_TILL, SoundCategory.BLOCKS, 1.0F, 1.0F);

	        if (!worldIn.isRemote)
	        {
	            worldIn.setBlockState(pos, state, 11);
	            stack.damageItem(1, player);
	        }
	 }
	 
	 @Override
	 public boolean canHarvestBlock(IBlockState blockIn)
	 {
		 Block block = blockIn.getBlock();

	     if (block == Blocks.OBSIDIAN)
	     {
	     	return this.toolMaterial.getHarvestLevel() == 3;
	     }
	     else if (block != Blocks.DIAMOND_BLOCK && block != Blocks.DIAMOND_ORE)
	     {
	    	 if (block != Blocks.EMERALD_ORE && block != Blocks.EMERALD_BLOCK)
	         {
	    		 if (block != Blocks.GOLD_BLOCK && block != Blocks.GOLD_ORE)
	             {
	    			 if (block != Blocks.IRON_BLOCK && block != Blocks.IRON_ORE)
	                 {
	    				 /*if(block != ErthiliaItems.jadeBlock.getBlock() && block != ErthiliaItems.jadeOre.getBlock())
	    				 {
	    					 if(block != ErthiliaItems.cyaniteBlock.getBlock() && block != ErthiliaItems.cyaniteOre.getBlock())
	    					 {
	    						 if(block != ErthiliaItems.morganiteBlock.getBlock() && block != ErthiliaItems.morganiteOre.getBlock())
	    						 {
	    							 if(block != ErthiliaItems.pyriteBlock.getBlock() && block != ErthiliaItems.pyriteOre.getBlock())
	    	    					 {
		    							 if(block != ErthiliaItems.citrineBlock.getBlock() && block != ErthiliaItems.citrineOre.getBlock())
		    	    					 {
		    								 if (block != Blocks.LAPIS_BLOCK && block != Blocks.LAPIS_ORE)
		    			                     {
		    			    					 if (block != Blocks.REDSTONE_ORE && block != Blocks.LIT_REDSTONE_ORE)
		    			                         {
		    			    						 Material material = blockIn.getMaterial();

		    			                             if (material == Material.ROCK)
		    			                             {
		    			                             	return true;
		    			                             }
		    			                             else if (material == Material.IRON)
		    			                             {
		    			                             	return true;
		    			                             }
		    			                             else
		    			                             {
		    			                            	 return material == Material.ANVIL;
		    			                             }
		    			                          }
		    			                          else
		    			                          {
		    			                        	  return this.toolMaterial.getHarvestLevel() >= 2;
		    			                          }
		    			                      }
		    			                      else
		    			                      {
		    			                    	  return this.toolMaterial.getHarvestLevel() >= 1;
		    			                      }
		    	    					 }
	    	    					 }
	    						 }
	    					 }
	    				
	    				 }
	    				 else
	    				 {
	    					 
	    				 }*/
	    				 
	    				 if (block != Blocks.LAPIS_BLOCK && block != Blocks.LAPIS_ORE)
	                     {
	    					 if (block != Blocks.REDSTONE_ORE && block != Blocks.LIT_REDSTONE_ORE)
	                         {
	    						 Material material = blockIn.getMaterial();

	                             if (material == Material.ROCK)
	                             {
	                             	return true;
	                             }
	                             else if (material == Material.IRON)
	                             {
	                             	return true;
	                             }
	                             else
	                             {
	                            	 return material == Material.ANVIL;
	                             }
	                          }
	                          else
	                          {
	                        	  return this.toolMaterial.getHarvestLevel() >= 2;
	                          }
	                      }
	                      else
	                      {
	                    	  return this.toolMaterial.getHarvestLevel() >= 1;
	                      }
	                   }
	                   else
	                   {
	                       return this.toolMaterial.getHarvestLevel() >= 1;
	                   }
	                }
	                else
	                {
	                    return this.toolMaterial.getHarvestLevel() >= 2;
	                }
	            }
	            else
	            {
	                return this.toolMaterial.getHarvestLevel() >= 2;
	            }
	        }
	        else
	        {
	            return this.toolMaterial.getHarvestLevel() >= 2;
	        }
	 }
	
	
	@Override
	public float getDestroySpeed(ItemStack stack, IBlockState state)
	{
	        Material material = state.getMaterial();
	        return material != Material.IRON && material != Material.ANVIL && material != Material.ROCK ? super.getDestroySpeed(stack, state) : this.efficiency;
	}


	

}
