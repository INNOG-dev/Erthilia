package fr.karmaowner.erthilia.handler;

import fr.karmaowner.erthilia.gui.inventory.InventoryUI;
import fr.karmaowner.erthilia.gui.inventory.JadeChestUI;
import fr.karmaowner.erthilia.gui.inventory.JadeFurnaceUI;
import fr.karmaowner.erthilia.gui.inventory.ReverseEnchantmentUI;
import fr.karmaowner.erthilia.gui.inventory.ReversedCraftingTableUI;
import fr.karmaowner.erthilia.gui.inventory.SyringeStandUI;
import fr.karmaowner.erthilia.inventory.ContainerJadeChest;
import fr.karmaowner.erthilia.inventory.ContainerJadeFurnace;
import fr.karmaowner.erthilia.inventory.ContainerReversedCraftingTable;
import fr.karmaowner.erthilia.inventory.ContainerReversedEnchantment;
import fr.karmaowner.erthilia.inventory.ContainerSyringeStand;
import fr.karmaowner.erthilia.tiles.TileEntityJadeChest;
import fr.karmaowner.erthilia.tiles.TileEntityJadeFurnace;
import fr.karmaowner.erthilia.tiles.TileEntityReversedCraftingTable;
import fr.karmaowner.erthilia.tiles.TileEntityReversedEnchantingTable;
import fr.karmaowner.erthilia.tiles.TileEntitySyringeStand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GuiContainerHandler implements IGuiHandler 
{

	@Override
	@SideOnly(Side.CLIENT)
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if(ID == 0)
		{
			TileEntityJadeFurnace tileEntityContainer = (TileEntityJadeFurnace) world.getTileEntity(new BlockPos(x, y, z));
			return new JadeFurnaceUI(player.inventory, tileEntityContainer);
		}
		else if(ID == 1)
		{
			TileEntityJadeChest tileEntityContainer = (TileEntityJadeChest) world.getTileEntity(new BlockPos(x, y, z));
			return new JadeChestUI(player.inventory, tileEntityContainer);
		}
		else if(ID == 2)
		{
			TileEntityReversedEnchantingTable tileEntityContainer = (TileEntityReversedEnchantingTable) world.getTileEntity(new BlockPos(x, y, z));
			
			return new ReverseEnchantmentUI(player.inventory, world, new BlockPos(x, y, z), tileEntityContainer.getName());
		}
		else if(ID == 3)
		{
			
			TileEntityReversedCraftingTable tileEntityContainer = (TileEntityReversedCraftingTable) world.getTileEntity(new BlockPos(x, y, z));
						
			return new ReversedCraftingTableUI(player.inventory, world, new BlockPos(x, y, z), tileEntityContainer.getName());
		}
		else if(ID == 4)
		{
			TileEntitySyringeStand tileEntityContainer = (TileEntitySyringeStand) world.getTileEntity(new BlockPos(x, y, z));
			
			return new SyringeStandUI(player.inventory,tileEntityContainer);
		}
		else if(ID == 5)
		{
			return new InventoryUI(player);
		}
		return null;
	}
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if(ID == 0){
			TileEntityJadeFurnace tileEntityFurnace = (TileEntityJadeFurnace) world.getTileEntity(new BlockPos(x, y, z));
			return new ContainerJadeFurnace(player.inventory, tileEntityFurnace);
		}
		else if(ID == 1)
		{
			TileEntityJadeChest tileEntityChest = (TileEntityJadeChest) world.getTileEntity(new BlockPos(x, y, z));
			return new ContainerJadeChest(player.inventory, tileEntityChest,player);
		}
		else if(ID == 2)
		{
			return new ContainerReversedEnchantment(player.inventory,world,new BlockPos(x,y,z));
		}
		else if(ID == 3)
		{
			return new ContainerReversedCraftingTable(player.inventory, world, new BlockPos(x, y, z));
		}
		else if(ID == 4)
		{

			TileEntitySyringeStand tileEntityContainer = (TileEntitySyringeStand) world.getTileEntity(new BlockPos(x, y, z));
			
			return new ContainerSyringeStand(player.inventory, tileEntityContainer);
		}
		return null;
	}
	
}
