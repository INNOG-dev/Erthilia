package fr.karmaowner.erthilia.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockObsidian;
import net.minecraft.block.BlockPistonBase;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;

public class ErthiliaBlocks
{
	
	public static Block JADE_BLOCK;
	public static Block JADE_ORE;
	public static Block JADE_FURNACE;
	public static Block JADE_FURNACE_LIT;
	public static Block JADE_CHEST;
	public static Block JADE_LADDER;
	public static Block CYANITE_BLOCK;
	public static Block CYANITE_ORE;
	public static Block MORGANITE_BLOCK;
	public static Block MORGANITE_ORE;
	public static Block PYRITE_BLOCK;
	public static Block PYRITE_ORE;
	public static Block CITRINE_BLOCK;
	public static Block CITRINE_ORE;
	public static Block XP_PLANT; 
	public static Block TABACCO;
	public static Block CANNABIS;
	public static Block RANDOM_ORE; 
	public static Block XP_BLOCK;
	public static Block EVENT_BLOCK;
	public static Block REVERSE_ENCHANTING_TABLE;
	public static Block REVERSE_CRAFTING_TABLE;
	public static Block MYSTERY_BLOCK;
	public static Block JUMP_BLOCK;
	public static Block SPEED_BLOCK;
	public static Block SYRINGE_STAND_BLOCK;
	
    @ObjectHolder("minecraft:sticky_piston")
    public static final BlockPistonBase STICKY_PISTON = null;

    @ObjectHolder("minecraft:piston")
    public static final BlockPistonBase PISTON = null;
     
    @ObjectHolder("minecraft:obsidian")
    public static final BlockObsidian OBSIDIAN = null;
	
	public static void loadBlocksInstance()
	{
		JADE_BLOCK = ErthiliaBlock.getBlock("erthilia:jade_block");
		JADE_ORE = ErthiliaBlock.getBlock("erthilia:jade_ore");
		JADE_FURNACE = ErthiliaBlock.getBlock("erthilia:jade_furnace");
		JADE_FURNACE_LIT = ErthiliaBlock.getBlock("erthilia:jade_furnace_lit");
		JADE_CHEST = ErthiliaBlock.getBlock("erthilia:jade_chest");
		JADE_LADDER = ErthiliaBlock.getBlock("erthilia:jade_ladder");
		CYANITE_BLOCK = ErthiliaBlock.getBlock("erthilia:cyanite_block");
		CYANITE_ORE = ErthiliaBlock.getBlock("erthilia:cyanite_ore");
		MORGANITE_BLOCK = ErthiliaBlock.getBlock("erthilia:morganite_block");
		MORGANITE_ORE = ErthiliaBlock.getBlock("erthilia:morganite_ore");
		PYRITE_BLOCK = ErthiliaBlock.getBlock("erthilia:pyrite_block");
		PYRITE_ORE = ErthiliaBlock.getBlock("erthilia:pyrite_ore");
		CITRINE_BLOCK = ErthiliaBlock.getBlock("erthilia:citrine_block");
		CITRINE_ORE = ErthiliaBlock.getBlock("erthilia:citrine_ore");
		XP_PLANT = ErthiliaBlock.getBlock("erthilia:xp_plant");
		TABACCO = ErthiliaBlock.getBlock("erthilia:tabacco");
		CANNABIS = ErthiliaBlock.getBlock("erthilia:cannabis");
		RANDOM_ORE = ErthiliaBlock.getBlock("erthilia:random_ore");
		XP_BLOCK = ErthiliaBlock.getBlock("erthilia:xp_block");
		EVENT_BLOCK = ErthiliaBlock.getBlock("erthilia:event_block");
		MYSTERY_BLOCK = ErthiliaBlock.getBlock("erthilia:block_mystery");
		REVERSE_ENCHANTING_TABLE = ErthiliaBlock.getBlock("erthilia:reversed_enchanting_table");
		REVERSE_CRAFTING_TABLE = ErthiliaBlock.getBlock("erthilia:reversed_crafting_table");
		JUMP_BLOCK = ErthiliaBlock.getBlock("erthilia:block_jump");
		SPEED_BLOCK = ErthiliaBlock.getBlock("erthilia:block_speed");
		SYRINGE_STAND_BLOCK = ErthiliaBlock.getBlock("erthilia:syringe_stand");
	}

}
