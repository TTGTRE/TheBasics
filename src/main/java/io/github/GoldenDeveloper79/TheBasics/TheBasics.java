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
import java.io.Reader;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import io.github.GoldenDeveloper79.TheBasics.Updater.UpdateResult;
import io.github.GoldenDeveloper79.TheBasics.Updater.UpdateType;
import io.github.GoldenDeveloper79.TheBasics.Commands.BackCMD;
import io.github.GoldenDeveloper79.TheBasics.Commands.BalanceCMD;
import io.github.GoldenDeveloper79.TheBasics.Commands.BanCMD;
import io.github.GoldenDeveloper79.TheBasics.Commands.BasicCommandExecutor;
import io.github.GoldenDeveloper79.TheBasics.Commands.ClearInventoryCMD;
import io.github.GoldenDeveloper79.TheBasics.Commands.DeleteHomeCMD;
import io.github.GoldenDeveloper79.TheBasics.Commands.EnchantCMD;
import io.github.GoldenDeveloper79.TheBasics.Commands.FeedCMD;
import io.github.GoldenDeveloper79.TheBasics.Commands.FlyCMD;
import io.github.GoldenDeveloper79.TheBasics.Commands.GamemodeCMD;
import io.github.GoldenDeveloper79.TheBasics.Commands.GiveCMD;
import io.github.GoldenDeveloper79.TheBasics.Commands.GroupCMD;
import io.github.GoldenDeveloper79.TheBasics.Commands.HealCMD;
import io.github.GoldenDeveloper79.TheBasics.Commands.HelpCMD;
import io.github.GoldenDeveloper79.TheBasics.Commands.HomeCMD;
import io.github.GoldenDeveloper79.TheBasics.Commands.IgnoreCMD;
import io.github.GoldenDeveloper79.TheBasics.Commands.InfoCMD;
import io.github.GoldenDeveloper79.TheBasics.Commands.KickCMD;
import io.github.GoldenDeveloper79.TheBasics.Commands.KitCMD;
import io.github.GoldenDeveloper79.TheBasics.Commands.ListCMD;
import io.github.GoldenDeveloper79.TheBasics.Commands.MessageCMD;
import io.github.GoldenDeveloper79.TheBasics.Commands.MuteCMD;
import io.github.GoldenDeveloper79.TheBasics.Commands.NickCMD;
import io.github.GoldenDeveloper79.TheBasics.Commands.PayCMD;
import io.github.GoldenDeveloper79.TheBasics.Commands.PlayTimeCMD;
import io.github.GoldenDeveloper79.TheBasics.Commands.RepairCMD;
import io.github.GoldenDeveloper79.TheBasics.Commands.ReplyCMD;
import io.github.GoldenDeveloper79.TheBasics.Commands.RulesCMD;
import io.github.GoldenDeveloper79.TheBasics.Commands.SetHomeCMD;
import io.github.GoldenDeveloper79.TheBasics.Commands.SetSpawnCMD;
import io.github.GoldenDeveloper79.TheBasics.Commands.SetWarpCMD;
import io.github.GoldenDeveloper79.TheBasics.Commands.SpawnCMD;
import io.github.GoldenDeveloper79.TheBasics.Commands.TeleportCMD;
import io.github.GoldenDeveloper79.TheBasics.Commands.TeleportPositionCMD;
import io.github.GoldenDeveloper79.TheBasics.Commands.TeleportRequestAcceptCMD;
import io.github.GoldenDeveloper79.TheBasics.Commands.TeleportRequestCMD;
import io.github.GoldenDeveloper79.TheBasics.Commands.TeleportRequestDenyCMD;
import io.github.GoldenDeveloper79.TheBasics.Commands.TempBanCMD;
import io.github.GoldenDeveloper79.TheBasics.Commands.TimeCMD;
import io.github.GoldenDeveloper79.TheBasics.Commands.UnbanCMD;
import io.github.GoldenDeveloper79.TheBasics.Commands.UnmuteCMD;
import io.github.GoldenDeveloper79.TheBasics.Commands.VanishCMD;
import io.github.GoldenDeveloper79.TheBasics.Commands.WarnCMD;
import io.github.GoldenDeveloper79.TheBasics.Commands.WarpCMD;
import io.github.GoldenDeveloper79.TheBasics.Commands.WeatherCMD;
import io.github.GoldenDeveloper79.TheBasics.Config.DataConfig;
import io.github.GoldenDeveloper79.TheBasics.Config.GeneralConfig;
import io.github.GoldenDeveloper79.TheBasics.Config.GroupConfig;
import io.github.GoldenDeveloper79.TheBasics.Config.LanguageConfig;
import io.github.GoldenDeveloper79.TheBasics.Config.TextConfig;
import io.github.GoldenDeveloper79.TheBasics.Events.BasicPlayerChatEvent;
import io.github.GoldenDeveloper79.TheBasics.Events.BasicPlayerFightEvent;
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
	private static ConfigModule languageConfig;
	
	//Economy
	private static Economy economy;
	
	//Permissions
	private static Permissions permissions;
	
	public void onEnable()
	{
		plugin = this;
		
		loadConfigs();
	
		if(TheBasics.getGeneralConfig().getBoolean("AutoUpdating"))
		{
			Updater updater = new Updater(this, 97487, this.getFile(), UpdateType.DEFAULT, false);
			
			if(updater.getResult().equals(UpdateResult.SUCCESS))
			{
				loadConfigs();
			}	
		}
		
		loadCommands();
		loadEvents();
		loadGroups();
		
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
		
		generalConfig = new GeneralConfig("config.yml");
		dataConfig = new DataConfig("data.yml");
		groupConfig = new GroupConfig("groups.yml");
		textConfig = new TextConfig("text.yml");
		languageConfig = new LanguageConfig("language.yml");
	}
	
	/*
	 * Loads all the commands and the executor.
	 */
	private void loadCommands()
	{
		cmdExecutor = new BasicCommandExecutor();
		
		new BackCMD();
		new BalanceCMD();
		new BanCMD();
		new ClearInventoryCMD();
		new DeleteHomeCMD();
		new EnchantCMD();
		new FeedCMD();
		new FlyCMD();
		new GamemodeCMD();
		new GiveCMD();
		new GroupCMD();
		new HealCMD();
		new HelpCMD();
		new HomeCMD();
		new IgnoreCMD();
		new InfoCMD();
		new KickCMD();
		new KitCMD();
		new ListCMD();
		new MessageCMD();
		new MuteCMD();
		new NickCMD();
		new PayCMD();
		new PlayTimeCMD();
		new RepairCMD();
		new ReplyCMD();
		new RulesCMD();
		new SetHomeCMD();
		new SetSpawnCMD();
		new SetWarpCMD();
		new SpawnCMD();
		new TeleportCMD();
		new TeleportPositionCMD();
		new TeleportRequestAcceptCMD();
		new TeleportRequestCMD();
		new TeleportRequestDenyCMD();
		new TempBanCMD();
		new TimeCMD();
		new UnbanCMD();
		new UnmuteCMD();
		new VanishCMD();
		new WarpCMD();
		new WarnCMD();
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
		pm.registerEvents(new BasicPlayerFightEvent(), this);
	}
	
	/*
	 * Loads all the groups.
	 */
	private void loadGroups()
	{
		//Loads the groups up.
		for(String groupNames : groupConfig.getConfigurationSection("Groups").getKeys(false))
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
							
							for(String group : TheBasics.getGroupConfig().getConfigurationSection("Ranking.Ranks").getKeys(false))
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
	 * Get a reader by its filename.
	 */
	public static Reader getResourceReader(String fileName)
	{
		return plugin.getTextResource(fileName);
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
	 * Gets the language config for the plugin. Includes messages. (language.yml)
	 */
	public static ConfigModule getLanguageConfig()
	{
		return languageConfig;
	}
	
	/*
	 * Gets the text config for the plugin. Includes Motd/Rules. (text.yml).
	 */
	public static ConfigModule getTextConfig() 
	{
		return textConfig;
	}

	/*
	 * Gets the economy methods.
	 */
	public static Economy getEconomy() 
	{
		return economy;
	}

	/*
	 * Gets the permission methods.
	 */
	public static Permissions getPermissions() 
	{
		return permissions;
	}	
	
	/*
	 * Gets the main file directory.
	 */
	public static File getMainDir() 
	{
		return mainDir;
	}

	/*
	 * Gets the player file directory.
	 */
	public static File getPlayerDir() 
	{
		return playerDir;
	}
}
