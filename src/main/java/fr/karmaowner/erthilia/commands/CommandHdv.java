package fr.karmaowner.erthilia.commands;

import java.util.ArrayList;
import java.util.List;

import fr.karmaowner.erthilia.Main;
import fr.karmaowner.erthilia.capability.playercapability.ErthiliaPlayerProvider;
import fr.karmaowner.erthilia.data.WorldDataManager;
import fr.karmaowner.erthilia.network.PacketOpenGui;
import fr.karmaowner.erthilia.utils.PlayerUtils;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class CommandHdv implements ICommand {
    	    	
    	public CommandHdv() { }


    	public void displayHelp(EntityPlayer player)
    	{
    		PlayerUtils.sendMessage(player, "Liste des commandes:");
    		PlayerUtils.sendMessage(player, "§c-/Hdv add [price] [quantité] - Ajoute l'item en main en hdv");
    		PlayerUtils.sendMessage(player, "§c-/Hdv - Ouvre l'hdv");
    	}
    	

    	@Override
    	public boolean isUsernameIndex(String[] p_82358_1_, int p_82358_2_) {
    		return false;
    	}

		@Override
		public int compareTo(ICommand o) 
		{
			return 0;
		}

		@Override
		public String getName() 
		{
			return "hdv";
		}

		@Override
		public String getUsage(ICommandSender sender) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public List<String> getAliases() 
		{
			return new ArrayList<String>();
		}

		@Override
		public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
			World world = sender.getEntityWorld();
    		
    		if(!world.isRemote) 
    		{
    			if(sender instanceof EntityPlayer)
    			{
    				EntityPlayer senderPlayer = (EntityPlayer) sender;
    				
	    			if(args.length == 3)
	    			{
	    				if(args[0].matches("add"))
	    				{
	    					
	    					if(args[1].isEmpty())
	    					{
	    						PlayerUtils.sendMessage(senderPlayer, "§cVous devez entrez un prix");
	    						return;
	    					}
	    					
	    					long price;
	    					int quantity;
	    					
	    					try
	    					{
	    						price = Long.parseLong(args[1]);
	    					}
	    					catch(Exception e)
	    					{
	    						PlayerUtils.sendMessage(senderPlayer, "§cVous devez entrez un prix");
	    						return;
	    					}
	    					
	    					if(price <= 0)
	    					{
	    						PlayerUtils.sendMessage(senderPlayer, "§cVous devez entrez un prix positive");
	    						return;
	    					}
	    					
	    					if(args[2].isEmpty())
	    					{
	    						PlayerUtils.sendMessage(senderPlayer, "§cVous devez entrez une quantité");
	    						return;
	    					}
	    						    					
	    					try
	    					{
	    						quantity = Integer.parseInt(args[2]);
	    					}
	    					catch(Exception e)
	    					{
	    						PlayerUtils.sendMessage(senderPlayer, "§cVous devez entrez une quantité");
	    						return;
	    					}
	    					
	     					if(quantity <= 0)
	    					{
	    						PlayerUtils.sendMessage(senderPlayer, "§cVous devez entrez une quantité positive");
	    						return;
	    					}
	    					
	    					
	    					WorldDataManager.get(world).getHdvManager().addItemToHDV(senderPlayer, price, quantity);
	    				}
	    			}
	    			else if(args.length == 1)
	    			{
	    				if(args[0].matches("confirm"))
	    				{
	    					if(senderPlayer.getCapability(ErthiliaPlayerProvider.PLAYER_CAP, null).getSelectedHdvItem() == null)
	    					{
	    						PlayerUtils.sendMessage(senderPlayer, "§cVous n'avez pas d'item selectionné dans l'hdv");
	    						return;
	    					}
	    					
	    					WorldDataManager.get(world).getHdvManager().buyHdvSelectedItem(senderPlayer);
	    				}
	    				else if(args[0].matches("help"))
	    				{
	    					displayHelp(senderPlayer);
	    				}
	    			}
	    			else
	    			{
	    				System.out.println("/getplayerdata " + senderPlayer.getGameProfile().getName());
	    				Main.getPacketHandler().sendTo(new PacketOpenGui(5), (EntityPlayerMP)senderPlayer);
	    			}
    			}
    		}
		}

		@Override
		public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
			if(sender instanceof EntityPlayer)
			{
				return (PlayerUtils.isOp((EntityPlayer)sender));
			}
			
			return true;
		}

		@Override
		public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args,
				BlockPos targetPos) {
			// TODO Auto-generated method stub
			return null;
		}
    	
}

