/*******************************************************************************
 * The MIT License (MIT)
 *
 * Copyright (c) 2016 Levi Pawlak (GoldenDeveloper79)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.on
 *******************************************************************************/
package io.github.GoldenDeveloper79.TheBasics;

import java.io.File;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import io.github.GoldenDeveloper79.TheBasics.Commands.BanCMD;
import io.github.GoldenDeveloper79.TheBasics.Commands.BasicCommandExecutor;
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
	
	//Configs & Files
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
		loadPlayers();
		
		economy = new Economy();
		permissions = new Permissions();
	}
	
	public void onDisable()
	{
		plugin = null;
	}

	/*
	 * Loads all the commands and the executor.
	 */
	private void loadCommands()
	{
		cmdExecutor = new BasicCommandExecutor();
		
		new BanCMD();
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
	 * Loads all the players on the server. Incase someone does /reload -.-
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
							
							for(String group : TheBasics.getGroupConfig().getStringList("Ranking.Ranks"))
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
							}
						}
					}
				}
			}
		}.runTaskTimerAsynchronously(this, 0L, 6000L);
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
