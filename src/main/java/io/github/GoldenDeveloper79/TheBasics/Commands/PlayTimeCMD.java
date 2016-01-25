package io.github.GoldenDeveloper79.TheBasics.Commands;

import java.math.BigDecimal;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import io.github.GoldenDeveloper79.TheBasics.BasicUtils;
import io.github.GoldenDeveloper79.TheBasics.Enums.MultiPlayer;
import io.github.GoldenDeveloper79.TheBasics.Modules.CommandModule;

public class PlayTimeCMD extends CommandModule
{
	public PlayTimeCMD() 
	{
		super(new String[] {"playtime", "ptime"}, 0, 1, MultiPlayer.SOMETIMES);
	}

	public void performCommand(Player player, String[] args) 
	{
		if(args.length < 1)
		{
			String formatTime;
			double time = BasicUtils.getData(player).getDouble("PlayTime");
			
			if(time < 60)
			{
				formatTime = String.valueOf(time) + "m";
			}else
			{
				BigDecimal bd = new BigDecimal(Double.toString(time));
				bd = bd.setScale(1, BigDecimal.ROUND_HALF_UP);
				
				formatTime = String.valueOf(bd.doubleValue()) + "h";
			}
			
			BasicUtils.sendMessage(player, "&6You have played on the server for " + formatTime + ".");
		}else
		{
			Player player2 = Bukkit.getPlayer(args[0]);
			String formatTime;
			double time = BasicUtils.getData(player2).getDouble("PlayTime");
			
			if(time < 60)
			{
				formatTime = String.valueOf(time) + "m";
			}else
			{
				BigDecimal bd = new BigDecimal(Double.toString(time));
				bd = bd.setScale(1, BigDecimal.ROUND_HALF_UP);
				
				formatTime = String.valueOf(bd.doubleValue()) + "h";
			}
			
			BasicUtils.sendMessage(player, "&6The player " + args[0] + " has played on the server for " + formatTime + ".");
		}
	}

	public void performCommand(ConsoleCommandSender console, String[] args) 
	{
		Player player = Bukkit.getPlayer(args[0]);
		String formatTime;
		double time = BasicUtils.getData(player).getDouble("PlayTime");
		
		if(time < 60)
		{
			formatTime = String.valueOf(time) + "m";
		}else
		{
			BigDecimal bd = new BigDecimal(Double.toString(time));
			bd = bd.setScale(1, BigDecimal.ROUND_HALF_UP);
			
			formatTime = String.valueOf(bd.doubleValue()) + "h";
		}
		
		BasicUtils.sendMessage(console, "The player " + args[0] + " has played on the server for " + formatTime + ".");
	}
}
