package fr.karmaowner.erthilia.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.EnumPushReaction;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;

public class ErthiliaAntiPistonBlock extends Block {

	public ErthiliaAntiPistonBlock(int harvestLevel, Material materialIn) 
	{
		super(materialIn);
		
		this.setHarvestLevel("pickaxe", harvestLevel);
		setSoundType(SoundType.STONE);
	}
	
    @Deprecated
    public EnumPushReaction getMobilityFlag(IBlockState state)
    {
        return EnumPushReaction.BLOCK;
    }

}
