/*******************************************************************************
 *  Copyright (C) 2016  Levi P. (GoldenDeveloper69)
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *******************************************************************************/
package io.github.GoldenDeveloper79.TheBasics;

import java.io.File;
import java.net.URL;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import io.github.GoldenDeveloper79.TheBasics.Commands.BalanceCMD;
import io.github.GoldenDeveloper79.TheBasics.Commands.BanCMD;
import io.github.GoldenDeveloper79.TheBasics.Commands.BasicCommandExecutor;
import io.github.GoldenDeveloper79.TheBasics.Commands.ClearInventoryCMD;
import io.github.GoldenDeveloper79.TheBasics.Commands.DeleteHomeCMD;
import io.github.GoldenDeveloper79.TheBasics.Commands.FeedCMD;
import io.github.GoldenDeveloper79.TheBasics.Commands.FlyCMD;
import io.github.GoldenDeveloper79.TheBasics.Commands.GamemodeCMD;
import io.github.GoldenDeveloper79.TheBasics.Commands.GiveCMD;
import io.github.GoldenDeveloper79.TheBasics.Commands.GroupCMD;
import io.github.GoldenDeveloper79.TheBasics.Commands.HealCMD;
import io.github.GoldenDeveloper79.TheBasics.Commands.HelpCMD;
import io.github.GoldenDeveloper79.TheBasics.Commands.HomeCMD;
import io.github.GoldenDeveloper79.TheBasics.Commands.KickCMD;
import io.github.GoldenDeveloper79.TheBasics.Commands.NickCMD;
import io.github.GoldenDeveloper79.TheBasics.Commands.PayCMD;
import io.github.GoldenDeveloper79.TheBasics.Commands.PlayTimeCMD;
import io.github.GoldenDeveloper79.TheBasics.Commands.RulesCMD;
import io.github.GoldenDeveloper79.TheBasics.Commands.SetHomeCMD;
import io.github.GoldenDeveloper79.TheBasics.Commands.SetSpawnCMD;
import io.github.GoldenDeveloper79.TheBasics.Commands.SetWarpCMD;
import io.github.GoldenDeveloper79.TheBasics.Commands.SpawnCMD;
import io.github.GoldenDeveloper79.TheBasics.Commands.TeleportCMD;
import io.github.GoldenDeveloper79.TheBasics.Commands.TeleportRequestAcceptCMD;
import io.github.GoldenDeveloper79.TheBasics.Commands.TeleportRequestCMD;
import io.github.GoldenDeveloper79.TheBasics.Commands.TeleportRequestDenyCMD;
import io.github.GoldenDeveloper79.TheBasics.Commands.TimeCMD;
import io.github.GoldenDeveloper79.TheBasics.Commands.WarpCMD;
import io.github.GoldenDeveloper79.TheBasics.Commands.WeatherCMD;
import io.github.GoldenDeveloper79.TheBasics.Events.BasicPlayerChatEvent;
import io.github.GoldenDeveloper79.TheBasics.Events.BasicPlayerJoinEvent;
import io.github.GoldenDeveloper79.TheBasics.Events.BasicPlayerQuitEvent;
import io.github.GoldenDeveloper79.TheBasics.Events.BasicServerPingEvent;
import io.github.GoldenDeveloper79.TheBasics.Modules.ConfigModule;
import io.github.GoldenDeveloper79.TheBasics.Modules.GroupModule;
import io.github.GoldenDeveloper79.TheBasics.Player.PlayerData;

public class TheBasics extends JavaPlugin
{
	//Basics
	private static TheBasics plugin;
	private static Logger log = Logger.getLogger("TheBasics");
	
	//Commands
	private static BasicCommandExecutor cmdExecutor;
	
	//Configurations & Files
	private static File mainDir;
	private static File playerDir;
	private static ConfigModule generalConfig;
	private static ConfigModule dataConfig;
	private static ConfigModule groupConfig;
	private static ConfigModule textConfig;
	
	//Economy
	private static Economy economy;
	
	//Permissions
	private static Permissions permissions;
	
	public void onEnable()
	{
		plugin = this;
		
		loadConfigs();
		loadCommands();
		loadEvents();
		loadGroups();

		if(TheBasics.getGeneralConfig().getBoolean("AutoUpdating"))
		{
			//new Updater(this, 0, this.getFile(), UpdateType.DEFAULT, false);
		}
		
		economy = new Economy();
		permissions = new Permissions();
		
		loadPlayers();
	}
	
	public void onDisable()
	{
		for(PlayerData data : Registery.players.values())
		{
			data.quit();
		}
		
		Registery.players.clear();
		Registery.groups.clear();
		Registery.commands.clear();
		Registery.teleportRequest.clear();
		Registery.teleportQue.clear();
		
		plugin = null;
		economy = null;
		permissions = null;
	}

	/*
	 * Loads all the configs, files, and directories.
	 */
	private void loadConfigs()
	{
		mainDir = new File("plugins/TheBasics/");
		playerDir = new File("plugins/TheBasics/Players");
		
		if(!mainDir.exists()) mainDir.mkdirs();
		if(!playerDir.exists()) playerDir.mkdirs();
		
		generalConfig = new ConfigModule(new File(mainDir, "config.yml"));
		dataConfig = new ConfigModule(new File(mainDir, "data.yml"));
		groupConfig = new ConfigModule(new File(mainDir, "groups.yml"));
		textConfig = new ConfigModule(new File(mainDir, "text.yml"));
	}
	
	/*
	 * Loads all the commands and the executor.
	 */
	private void loadCommands()
	{
		cmdExecutor = new BasicCommandExecutor();
		
		new BalanceCMD();
		new BanCMD();
		new ClearInventoryCMD();
		new DeleteHomeCMD();
		new FeedCMD();
		new FlyCMD();
		new GamemodeCMD();
		new GiveCMD();
		new GroupCMD();
		new HealCMD();
		new HelpCMD();
		new HomeCMD();
		new KickCMD();
		new NickCMD();
		new PayCMD();
		new PlayTimeCMD();
		new RulesCMD();
		new SetHomeCMD();
		new SetSpawnCMD();
		new SetWarpCMD();
		new SpawnCMD();
		new TeleportCMD();
		new TeleportRequestAcceptCMD();
		new TeleportRequestCMD();
		new TeleportRequestDenyCMD();
		new TimeCMD();
		new WarpCMD();
		new WeatherCMD();
	}

	/*
	 * Loads all the events, listeners, and registers them.
	 */
	private void loadEvents()
	{
		PluginManager pm = Bukkit.getPluginManager();
		
		pm.registerEvents(new BasicPlayerJoinEvent(), this);
		pm.registerEvents(new BasicPlayerQuitEvent(), this);
		pm.registerEvents(new BasicPlayerChatEvent(), this);
		pm.registerEvents(new BasicServerPingEvent(), this);
	}
	
	/*
	 * Loads all the groups.
	 */
	private void loadGroups()
	{
		//Loads the groups up.
		for(String groupNames : groupConfig.getConfig().getConfigurationSection("Groups").getKeys(false))
		{
			new GroupModule(groupNames);
		}
		
		//Loads the inheritances.
		for(GroupModule mod : Registery.groups.values())
		{
			mod.loadInheritance();
		}
	}
	
	/*
	 * Loads all the players on the server. In case someone does /reload -.-
	 */
	private void loadPlayers()
	{
		for(Player player : Bukkit.getOnlinePlayers())
		{
			new PlayerData(player);
		}
		
		//Every 5 minutes
		new BukkitRunnable()
		{
			public void run()
			{
				for(PlayerData player : Registery.players.values())
				{
					if(!player.isAfk())
					{
						player.set("PlayTime", player.getDouble("PlayTime") + 5);
						
						if(TheBasics.getGroupConfig().getString("Ranking.Method").equalsIgnoreCase("TIME"))
						{
							String groupName = null;
							
							for(String group : TheBasics.getGroupConfig().getConfig().getConfigurationSection("Ranking.Ranks").getKeys(false))
							{
								if(permissions.groupExist(group))
								{
									if(TheBasics.getGroupConfig().getDouble("Ranking.Ranks." + group) <= player.getDouble("PlayTime"))
									{
										groupName = group;
									}
								}
							}
							
							if(groupName != null && !permissions.getPlayerGroup(player.getPlayer()).getGroupName().equalsIgnoreCase(groupName))
							{
								GroupModule group = TheBasics.getPermissions().getGroup(groupName);
								permissions.addPlayerToGroup(player.getPlayer(), group);
								
								BasicUtils.sendMessage(player.getPlayer(), "&6You have ranked up to &7" + groupName + "&6.");	
							}
						}
					}
				}
			}
		}.runTaskTimerAsynchronously(this, 6000L, 6000L);
	}
	
	/*
	 * Get a url by its filename.
	 */
	public static URL getResourceURL(String fileName)
	{
		return plugin.getClassLoader().getResource(fileName); 
	}
	
	/*
	 * Gets the instance of the plugin.
	 */
	public static TheBasics getPlugin()
	{
		return plugin;
	}
	
	/*
	 * Gets the logger for the plugin.
	 */
	public static Logger getLog()
	{
		return log;
	}
	
	/*
	 * Gets the command executor for the plugin.
	 */
	public static BasicCommandExecutor getCommandExecutor()
	{
		return cmdExecutor;
	}

	/*
	 * Gets the general config for the plugin. Includes general settings. (config.yml)
	 */
	public static ConfigModule getGeneralConfig() 
	{
		return generalConfig;
	}
	
	/*
	 * Gets the data config for the plugin. Includes things like offline players. (data.yml)
	 */
	public static ConfigModule getDataConfig()
	{
		return dataConfig;
	}

	/*
	 * Gets the group config for the plugin. Includes groups, permissions, and ranking system. (groups.yml)
	 */
	public static ConfigModule getGroupConfig()
	{
		return groupConfig;
	}
	
	/*
	 * Gets the text config for the plugin. Includes Motd/Rules. (text.yml).
	 */
	public static ConfigModule getTextConfig() 
	{
		return textConfig;
	}

	public static Economy getEconomy() 
	{
		return economy;
	}

	public static Permissions getPermissions() 
	{
		return permissions;
	}	
}
