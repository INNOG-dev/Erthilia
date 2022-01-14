package fr.karmaowner.erthilia.blocks;

import javax.annotation.Nullable;

import fr.karmaowner.erthilia.tiles.TileEntityObsidian;
import net.minecraft.block.BlockObsidian;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ErthiliaObsidian extends BlockObsidian {
	
	public ErthiliaObsidian() 
	{
		super();
		setSoundType(SoundType.STONE);
	}
	
    @Override
    public TileEntity createTileEntity(World world, IBlockState state)
    {
        return new TileEntityObsidian();
    }
    
    @Override
    public boolean hasTileEntity(IBlockState state)
    {
        return true;
    }


}
