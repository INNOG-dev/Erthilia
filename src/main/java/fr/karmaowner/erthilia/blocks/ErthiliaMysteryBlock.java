package fr.karmaowner.erthilia.blocks;

import fr.karmaowner.client.creativetab.ErthiliaCreativeTabs;
import fr.karmaowner.erthilia.GameInitializer;
import fr.karmaowner.erthilia.utils.PlayerUtils;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ErthiliaMysteryBlock extends Block {

	public ErthiliaMysteryBlock() {
		super(Material.IRON);
		this.setCreativeTab(ErthiliaCreativeTabs.instance);
	}

	
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		if(!worldIn.isRemote)
		{
			PlayerUtils.sendMessage(playerIn, "§b[§aBlockMystery§b] §aGénération d'un item Aléatoire !");
			
			ItemStack dropIs = GameInitializer.dropsData.get(ErthiliaMysteryBlock.class).getRandomItemStack();
			
			if(dropIs != null) PlayerUtils.sendMessage(playerIn,"§b[§aBlockMystery§b] §a" + dropIs.getDisplayName() + " " + dropIs.getCount());
			
			return true;
		}
		else
		{
			return true;
		}
	}



}
