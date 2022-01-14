package fr.karmaowner.erthilia.commands;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import fr.karmaowner.erthilia.capability.playercapability.ErthiliaPlayerProvider;
import fr.karmaowner.erthilia.capability.playercapability.IPlayer;
import fr.karmaowner.erthilia.data.SyncPlayerSpigotData;
import fr.karmaowner.erthilia.utils.PlayerUtils;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class CommandApi implements ICommand {

    	    	
    	public CommandApi()
    	{
    		
    	}
    	
		@Override
		public boolean isUsernameIndex(String[] p_82358_1_, int p_82358_2_) {
			return false;
		}

		@Override
		public int compareTo(ICommand arg0)
		{
			return 0;
		}

		@Override
		public String getName() 
		{
			return "api";
		}

		@Override
		public String getUsage(ICommandSender sender) 
		{
			return "§c/api";
		}

		@Override
		public List<String> getAliases() 
		{
			return new ArrayList<String>();
		}

		@Override
		public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException 
		{
			World world = sender.getEntityWorld();
    		
    		if(!checkPermission(server,sender))
    		{
    			PlayerUtils.sendMessage(sender,"§cPermission Error");
    			return;
    		}
    		
    		if(!world.isRemote) 
    		{
    			if(args[0].equalsIgnoreCase("getplayerdata"))
    			{
    				EntityPlayer player = world.getPlayerEntityByName(args[1]);
    				if(player != null)
    				{
    					IPlayer persistantData = (IPlayer) player.getCapability(ErthiliaPlayerProvider.PLAYER_CAP, null);
    					
    					Gson gson = new Gson();
    					
    					String json = "";
    					
    					for(int i = 2; i < args.length; i++)
    					{
    						json += args[i] + " ";
    					}
    					json = json.trim();
    					
    					if(persistantData == null)
    					{
    						return;
    					}
    					
	    					
    					persistantData.setPlayerSpigotData(gson.fromJson(json, SyncPlayerSpigotData.class));
    				}
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

