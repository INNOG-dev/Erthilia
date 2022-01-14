package fr.karmaowner.erthilia.blocks;

import javax.annotation.Nullable;

import fr.karmaowner.client.creativetab.ErthiliaCreativeTabs;
import fr.karmaowner.erthilia.Main;
import fr.karmaowner.erthilia.tiles.TileEntityReversedEnchantingTable;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ErthiliaReversedEnchantingTable extends Block
{
	
    	protected static final AxisAlignedBB AABB = new AxisAlignedBB(0.0F, 0.0F, 0.0F, 1.0F, 0.75F, 1.0F);

	    public ErthiliaReversedEnchantingTable()
	    {
	        super(Material.ROCK);
	        this.setLightOpacity(0);
			this.setCreativeTab(ErthiliaCreativeTabs.instance);
	    }
	    
	    @SideOnly(Side.CLIENT)
	    public BlockRenderLayer getBlockLayer()
	    {
	        return BlockRenderLayer.CUTOUT;
	    }
	    
	    
	    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
	    {
	        return AABB;
	    }
	    
	    @Override
	    public boolean isFullCube(IBlockState state)
	    {
	        return false;
	    }

	    
	    @Override
	    public boolean isOpaqueCube(IBlockState state)
	    {
	        return false;
	    }

	    
	    @Nullable
	    @Override
	    public TileEntity createTileEntity(World world, IBlockState state)
	    {
	        return new TileEntityReversedEnchantingTable();
	    }
	    
	    @Override
	    public boolean hasTileEntity(IBlockState state)
	    {
	        return true;
	    }

	    
	    @Override
	    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	    {
	        if (worldIn.isRemote)
	        {
	            return true;
	        }
	        else
	        {

	            TileEntity tileentity = worldIn.getTileEntity(pos);

	            if (tileentity instanceof TileEntityReversedEnchantingTable)
	            {

	                playerIn.openGui(Main.INSTANCE, 2, worldIn, pos.getX(), pos.getY(), pos.getZ());;
	            }

	            return true;
	        }
	    }

	    
	    @Override
	    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
	    {
	        super.onBlockPlacedBy(worldIn, pos, state, placer, stack);

	        if (stack.hasDisplayName())
	        {
	            TileEntity tileentity = worldIn.getTileEntity(pos);

	            if (tileentity instanceof TileEntityReversedEnchantingTable)
	            {
	                ((TileEntityReversedEnchantingTable)tileentity).setName(stack.getDisplayName());
	            }
	        }
	    }

	
}
