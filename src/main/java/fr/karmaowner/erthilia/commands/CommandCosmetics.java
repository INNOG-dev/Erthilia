package fr.karmaowner.erthilia.commands;

import java.util.Arrays;
import java.util.List;

import fr.karmaowner.erthilia.cosmetics.CosmeticObject;
import fr.karmaowner.erthilia.utils.PlayerUtils;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class CommandCosmetics implements ICommand {

    	
    	public CommandCosmetics() { }


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
			return "cosmetique";
		}

		@Override
		public String getUsage(ICommandSender sender) 
		{
    		return "§c/cosmetique unlock <Username> <Id>";
		}

		@Override
		public List<String> getAliases() 
		{
			return Arrays.asList(new String[] {"cm"});
		}

		@Override
		public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException
		{
			World world = sender.getEntityWorld();
    		if(!world.isRemote) 	
    		{
    			if(args.length == 3)
    			{
    				if(args[0].equalsIgnoreCase("unlock"))
    				{
    					if(!checkPermission(server,sender))
    					{
    						PlayerUtils.sendMessage(sender,"§cPermission Error");
    						return;
    					}
    					EntityPlayer player = world.getPlayerEntityByName(args[1]);
        				if(player != null)
        				{
        					int id = 0;
        					try
        					{
        						id = Integer.parseInt(args[2]);
        					}
        					catch(Exception e)
        					{
        						e.printStackTrace();
        						return;
        					}
            				CosmeticObject.setCosmetiqueUnlocked(player, id);
        				}
        				else
        				{
        					PlayerUtils.sendMessage(sender,"§cUtilisateur introuvable!");
        				}
    				}
    			}
    			else
    			{
    				PlayerUtils.sendMessage(sender, getUsage(sender));
    			}
    		}
    
		}

		@Override
		public boolean checkPermission(MinecraftServer server, ICommandSender sender)
		{
			if(sender instanceof EntityPlayer)
			{
				return (PlayerUtils.isOp((EntityPlayer)sender));
			}
			
			return true;
		}

		@Override
		public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args,
				BlockPos targetPos)
		{
			return null;
		}
    	
}

