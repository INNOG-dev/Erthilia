package fr.karmaowner.erthilia.blocks;

import javax.annotation.Nullable;

import fr.karmaowner.client.creativetab.ErthiliaCreativeTabs;
import fr.karmaowner.client.renderer.tileentity.ExtendedTileEntityItemStackRenderer;
import fr.karmaowner.erthilia.Main;
import fr.karmaowner.erthilia.tiles.TileEntityReversedCraftingTable;
import fr.karmaowner.erthilia.tiles.TileEntitySyringeStand;
import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ErthiliaReversedCraftingTable extends Block implements IErthiliaCustomModel  {
	
    	public static final PropertyDirection FACING = BlockHorizontal.FACING;
	    public static final PropertyBool RENDERMODEL = PropertyBool.create("render_model");

		protected static final AxisAlignedBB AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0F, 1.0F, 1.0F);
	 
	    public ErthiliaReversedCraftingTable()
	    {
	        super(Material.ROCK);   
	        this.setLightOpacity(0);
	        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(RENDERMODEL, true));
			this.setCreativeTab(ErthiliaCreativeTabs.instance);
	    } 
	    
	    @Override
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
	    
	    @SideOnly(Side.CLIENT)
	    @Override
	    public BlockRenderLayer getBlockLayer()
	    {
	        return BlockRenderLayer.SOLID;
	    }
	    

	    @Nullable
	    @Override
	    public TileEntity createTileEntity(World world, IBlockState state)
	    {
	        return new TileEntityReversedCraftingTable();
	    }
	    
	    @Override
	    public boolean hasTileEntity(IBlockState state)
	    {
	        return true;
	    }

	    @Override
	    public EnumBlockRenderType getRenderType(IBlockState state)
	    {
	        return EnumBlockRenderType.ENTITYBLOCK_ANIMATED;
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

				playerIn.openGui(Main.INSTANCE, 3, worldIn, pos.getX(), pos.getY(), pos.getZ());

	        }
	        
	        return false;
	    }
	    
	    @Override
	    public boolean canPlaceBlockAt(World world, BlockPos pos)
	    {			
			if(world.getBlockState(new BlockPos(pos.getX(), pos.getY(), pos.getZ())).getBlock() != Blocks.AIR)
			{
				return false;
			}
			
			int i = 0;
			
			if(world.getBlockState(new BlockPos(pos.getX(), pos.getY(), pos.getZ()-1)).getBlock() != Blocks.AIR)
			{
				i++;
			}
			if(world.getBlockState(new BlockPos(pos.getX(), pos.getY(), pos.getZ()+1)).getBlock() != Blocks.AIR)
			{
				i++;
			}
			if(world.getBlockState(new BlockPos(pos.getX()+1, pos.getY(), pos.getZ())).getBlock() != Blocks.AIR)
			{
				i++;
			}
			if(world.getBlockState(new BlockPos(pos.getX()-1, pos.getY(), pos.getZ())).getBlock() != Blocks.AIR)
			{
				i++;
			}
			if(world.getBlockState(new BlockPos(pos.getX()-1, pos.getY(), pos.getZ()-1)).getBlock() != Blocks.AIR)
			{
				i++;
			}
			if(world.getBlockState(new BlockPos(pos.getX()-1, pos.getY(), pos.getZ()+1)).getBlock() != Blocks.AIR)
			{
				i++;
			}
			if(world.getBlockState(new BlockPos(pos.getX()+1, pos.getY(), pos.getZ()+1)).getBlock() != Blocks.AIR)
			{
				i++;
			}
			if(world.getBlockState(new BlockPos(pos.getX()+1, pos.getY(), pos.getZ()-1)).getBlock() != Blocks.AIR)
			{
				i++;
			}
			
			if(i == 0)
			{
				return true;
			}
	    	
	    	return false;
	    }

	    
	    @Override
	    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
	    {
	        worldIn.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing().getOpposite()).withProperty(RENDERMODEL, true), 2);
	        
	        EnumFacing facing = placer.getHorizontalFacing().getOpposite();

	        if (stack.hasDisplayName())
	        {
	            TileEntity tileentity = worldIn.getTileEntity(pos);

	            if (tileentity instanceof TileEntityReversedCraftingTable)
	            {	            
	                ((TileEntityReversedCraftingTable)tileentity).setName(stack.getDisplayName());
	            }
	        }
	        
	     	       	        	        
			TileEntity tileentity = (TileEntityReversedCraftingTable) worldIn.getTileEntity(pos);

			if(tileentity instanceof TileEntityReversedCraftingTable)
			{
				TileEntityReversedCraftingTable tileAlambic = (TileEntityReversedCraftingTable) tileentity;
				
				tileAlambic.isActiveTile = true;
				
				
				TileEntityReversedCraftingTable tileAlambic1 = null;
				TileEntityReversedCraftingTable tileAlambic2 = null;
				TileEntityReversedCraftingTable tileAlambic3 = null;

				if(facing == EnumFacing.NORTH)
				{
					worldIn.setBlockState(new BlockPos(pos.getX(), pos.getY(), pos.getZ()+1), getDefaultState().withProperty(RENDERMODEL, false),2);
					tileAlambic1 = (TileEntityReversedCraftingTable) worldIn.getTileEntity(new BlockPos(pos.getX(), pos.getY(), pos.getZ()+1));
					
					worldIn.setBlockState(new BlockPos(pos.getX()-1, pos.getY(), pos.getZ()+1), getDefaultState().withProperty(RENDERMODEL, false), 2);
					tileAlambic2 = (TileEntityReversedCraftingTable) worldIn.getTileEntity(new BlockPos(pos.getX()-1, pos.getY(), pos.getZ()+1));
					
					worldIn.setBlockState(new BlockPos(pos.getX()-1, pos.getY(), pos.getZ()), getDefaultState().withProperty(RENDERMODEL, false), 2);
					tileAlambic3 = (TileEntityReversedCraftingTable) worldIn.getTileEntity(new BlockPos(pos.getX()-1, pos.getY(), pos.getZ()));
					
					tileAlambic3.isActiveTile = true;
				} 
				else if(facing == EnumFacing.EAST)
				{
					worldIn.setBlockState(new BlockPos(pos.getX(), pos.getY(), pos.getZ()-1), getDefaultState().withProperty(RENDERMODEL, false), 2);
					tileAlambic1 = (TileEntityReversedCraftingTable) worldIn.getTileEntity(new BlockPos(pos.getX(), pos.getY(), pos.getZ()-1));
					tileAlambic1.isActiveTile = true;
					
					worldIn.setBlockState(new BlockPos(pos.getX()-1, pos.getY(), pos.getZ()), getDefaultState().withProperty(RENDERMODEL, false), 2);
					tileAlambic2 = (TileEntityReversedCraftingTable) worldIn.getTileEntity(new BlockPos(pos.getX()-1, pos.getY(), pos.getZ()));
					
					worldIn.setBlockState(new BlockPos(pos.getX()-1, pos.getY(), pos.getZ()-1), getDefaultState().withProperty(RENDERMODEL, false), 2);
					tileAlambic3 = (TileEntityReversedCraftingTable) worldIn.getTileEntity(new BlockPos(pos.getX()-1, pos.getY(), pos.getZ()-1));
				}
				else if(facing == EnumFacing.SOUTH)
				{
					worldIn.setBlockState(new BlockPos(pos.getX()+1, pos.getY(), pos.getZ()), getDefaultState().withProperty(RENDERMODEL, false), 2);
					tileAlambic1 = (TileEntityReversedCraftingTable) worldIn.getTileEntity(new BlockPos(pos.getX()+1, pos.getY(), pos.getZ()));
					
					worldIn.setBlockState(new BlockPos(pos.getX(), pos.getY(), pos.getZ()-1), getDefaultState().withProperty(RENDERMODEL, false), 2);
					tileAlambic2 = (TileEntityReversedCraftingTable) worldIn.getTileEntity(new BlockPos(pos.getX(), pos.getY(), pos.getZ()-1));
					
					worldIn.setBlockState(new BlockPos(pos.getX()+1, pos.getY(), pos.getZ()-1), getDefaultState().withProperty(RENDERMODEL, false), 2);
					tileAlambic3 = (TileEntityReversedCraftingTable) worldIn.getTileEntity(new BlockPos(pos.getX()+1, pos.getY(), pos.getZ()-1));
					
					tileAlambic1.isActiveTile = true;
				}
				else if(facing == EnumFacing.WEST)
				{
					worldIn.setBlockState(new BlockPos(pos.getX(), pos.getY(), pos.getZ()+1), getDefaultState().withProperty(RENDERMODEL, false), 2);
					tileAlambic1 = (TileEntityReversedCraftingTable) worldIn.getTileEntity(new BlockPos(pos.getX(), pos.getY(), pos.getZ()+1));
					tileAlambic1.isActiveTile = true;
					
					worldIn.setBlockState(new BlockPos(pos.getX()+1, pos.getY(), pos.getZ()+1), getDefaultState().withProperty(RENDERMODEL, false), 2);
					tileAlambic2 = (TileEntityReversedCraftingTable) worldIn.getTileEntity(new BlockPos(pos.getX()+1, pos.getY(), pos.getZ()+1));
					
					worldIn.setBlockState(new BlockPos(pos.getX()+1, pos.getY(), pos.getZ()), getDefaultState().withProperty(RENDERMODEL, false), 2);
					tileAlambic3 = (TileEntityReversedCraftingTable) worldIn.getTileEntity(new BlockPos(pos.getX()+1, pos.getY(), pos.getZ()));
				}
				
				tileAlambic1.masterTilePos = tileAlambic.getPos();

				tileAlambic2.masterTilePos = tileAlambic.getPos();

				tileAlambic3.masterTilePos = tileAlambic.getPos();

			}
	    }
	    
	    @Override
	    public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
	    {
	    	TileEntityReversedCraftingTable tile = (TileEntityReversedCraftingTable) worldIn.getTileEntity(pos);
	    	
	    	EnumFacing facing = state.getValue(FACING);
	    	
	        if(!tile.isMasterTile())
	        {
	        	tile = (TileEntityReversedCraftingTable)worldIn.getTileEntity(tile.masterTilePos);
	        }
	        
		    BlockPos block1coordinates = null;
		    BlockPos block2coordinates = null;
		    BlockPos block3coordinates = null;
		        
			if(facing == EnumFacing.NORTH)
			{
				block1coordinates = new BlockPos(tile.getPos().getX(), tile.getPos().getY(), tile.getPos().getZ()+1);
				block2coordinates = new BlockPos(tile.getPos().getX()-1, tile.getPos().getY(), tile.getPos().getZ()+1);
				block3coordinates = new BlockPos(tile.getPos().getX()-1, tile.getPos().getY(), tile.getPos().getZ());
			}
			else if(facing == EnumFacing.EAST)
			{
				block1coordinates = new BlockPos(tile.getPos().getX()-1, tile.getPos().getY(), tile.getPos().getZ());
				block2coordinates = new BlockPos(tile.getPos().getX()-1, tile.getPos().getY(), tile.getPos().getZ()-1);
				block3coordinates = new BlockPos(tile.getPos().getX(), tile.getPos().getY(), tile.getPos().getZ()-1);
			}
			else if(facing == EnumFacing.SOUTH)
			{
				block1coordinates = new BlockPos(tile.getPos().getX(), tile.getPos().getY(), tile.getPos().getZ()-1);
				block2coordinates = new BlockPos(tile.getPos().getX()+1, tile.getPos().getY(), tile.getPos().getZ()-1);
				block3coordinates = new BlockPos(tile.getPos().getX()+1, tile.getPos().getY(), tile.getPos().getZ());
			} 
			else if(facing == EnumFacing.WEST)
			{
				block1coordinates = new BlockPos(tile.getPos().getX()+1, tile.getPos().getY(), tile.getPos().getZ());
				block2coordinates = new BlockPos(tile.getPos().getX()+1, tile.getPos().getY(), tile.getPos().getZ()+1);
				block3coordinates = new BlockPos(tile.getPos().getX(), tile.getPos().getY(), tile.getPos().getZ()+1);
			}

			destroyBlock(worldIn,block1coordinates);
			destroyBlock(worldIn,block2coordinates);
			destroyBlock(worldIn,block3coordinates);
			destroyBlock(worldIn,tile.getPos());
	    }
	    
	    private void destroyBlock(World world, BlockPos pos)
	    {
	    	TileEntity tile = world.getTileEntity(pos);
	    	
            if (tile instanceof IInventory)
            {
                InventoryHelper.dropInventoryItems(world, pos, (IInventory)tile);
            }
	    	
	    	world.setTileEntity(pos, null);
	    	world.setBlockState(pos, Blocks.AIR.getDefaultState(),2);
	    }
	    
	    @Override
	    public IBlockState getStateFromMeta(int meta)
	    {
	        boolean flag = false;
	        
	        if(meta >= 10)
	        {
	           meta -= 10;
	           flag = true;
	        }
	        
	        EnumFacing enumfacing = EnumFacing.getFront(meta);

	        if (enumfacing.getAxis() == EnumFacing.Axis.Y)
	        {
	            enumfacing = EnumFacing.NORTH;
	        }

	        return this.getDefaultState().withProperty(RENDERMODEL, flag).withProperty(FACING, enumfacing);
	    }
	    
	    @Override
	    public int getMetaFromState(IBlockState state)
	    {
	    	int meta = 0;
	    	
	    	if(state.getValue(RENDERMODEL))
	    	{
	    		meta = 10;
	    	}
	    	
	        return meta + ((EnumFacing)state.getValue(FACING)).getIndex();
	    }

	    @Override
	    public IBlockState withRotation(IBlockState state, Rotation rot)
	    {
	        return state.withProperty(FACING, rot.rotate((EnumFacing)state.getValue(FACING)));
	    }

	    @Override
	    public IBlockState withMirror(IBlockState state, Mirror mirrorIn)
	    {
	        return state.withRotation(mirrorIn.toRotation((EnumFacing)state.getValue(FACING)));
	    }

	    @Override
	    protected BlockStateContainer createBlockState()
	    {
	        return new BlockStateContainer(this, new IProperty[] {RENDERMODEL,FACING});
	    }
	    
		@Override
		public void registerCustomRender(Item item) {
			item.setTileEntityItemStackRenderer(new ExtendedTileEntityItemStackRenderer());
		}
	    

}
