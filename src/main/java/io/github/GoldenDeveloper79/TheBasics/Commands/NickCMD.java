package io.github.GoldenDeveloper79.TheBasics.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import io.github.GoldenDeveloper79.TheBasics.BasicUtils;
import io.github.GoldenDeveloper79.TheBasics.TheBasics;
import io.github.GoldenDeveloper79.TheBasics.Enums.MultiPlayer;
import io.github.GoldenDeveloper79.TheBasics.Modules.CommandModule;

public class NickCMD extends CommandModule
{
	public NickCMD() 
	{
		super(new String[] {"nick"}, 1, 1, MultiPlayer.OTHER);
	}

	public void performCommand(Player player, String[] args)
	{
		if(TheBasics.getGeneralConfig().getBoolean("Nickname.Enabled"))
		{
			String name = args[0];
			
			if(name.length() <= TheBasics.getGeneralConfig().getDouble("Nickname.MaxLength") && TheBasics.getGeneralConfig().getDouble("Nickname.MinLength") >= name.length())
			{
				player.setDisplayName(ChatColor.translateAlternateColorCodes('&', args[0]));
				BasicUtils.sendMessage(player, "&6You changed your displayname to " + args[0] + "&6.");
			}else
			{
				BasicUtils.sendMessage(player, "&cPlease specify a valid nickname!");
			}
		}else
		{
			BasicUtils.sendMessage(player, "&cChanging your nickname is disabled on this server!");
		}
	}

	public void performCommand(ConsoleCommandSender console, String[] args) 
	{
		BasicUtils.sendMessage(console, "&cYou must be a player to perform this command!");
	}
}
