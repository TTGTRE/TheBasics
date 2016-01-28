package io.github.GoldenDeveloper79.TheBasics.Commands;

import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import io.github.GoldenDeveloper79.TheBasics.BasicUtils;
import io.github.GoldenDeveloper79.TheBasics.Registery;
import io.github.GoldenDeveloper79.TheBasics.Enums.MultiPlayer;
import io.github.GoldenDeveloper79.TheBasics.Modules.CommandModule;

public class TeleportRequestAcceptCMD extends CommandModule
{
	public TeleportRequestAcceptCMD()
	{
		super(new String[] {"teleportrequestaccept", "traccept"}, 0, 0, MultiPlayer.OTHER);
	}

	public void performCommand(Player player, String[] args)
	{
		if(Registery.teleportRequest.containsValue(player.getName()))
		{
			String playerName = null;
			
			for(Entry<String, String> players : Registery.teleportRequest.entrySet())
			{
				if(players.getValue().equals(player.getName()))
				{
					playerName = players.getKey();
					break;
				}
			}
			
			Player player2 = Bukkit.getPlayer(playerName);
			
			if(player2 != null)
			{
				Registery.teleportRequest.remove(playerName);
				
				BasicUtils.sendMessage(player2, "&6The player &7" + player.getName() + "&6 has accepted your request.");
				BasicUtils.getData(player2).initTeleport(player.getLocation(), "&7" + player.getName());
;
			}else
			{
				BasicUtils.sendMessage(player, "&cThere is no teleport request to you.");
			}
		}else
		{
			BasicUtils.sendMessage(player, "&cThere is no teleport request to you.");
		}
	}

	public void performCommand(ConsoleCommandSender console, String[] args) 
	{
		BasicUtils.sendMessage(console, "&cYou must be a player to perform this command!");
	}
}