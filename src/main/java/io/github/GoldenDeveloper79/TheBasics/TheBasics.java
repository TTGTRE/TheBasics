package io.github.GoldenDeveloper79.TheBasics;

import java.io.File;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import io.github.GoldenDeveloper79.TheBasics.Commands.BanCMD;
import io.github.GoldenDeveloper79.TheBasics.Commands.BasicCommandExecutor;
import io.github.GoldenDeveloper79.TheBasics.Events.BasicPlayerJoinEvent;
import io.github.GoldenDeveloper79.TheBasics.Events.BasicPlayerQuitEvent;
import io.github.GoldenDeveloper79.TheBasics.Modules.ConfigModule;
import io.github.GoldenDeveloper79.TheBasics.Modules.GroupModule;

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
	private static ConfigModule groupConfig;
	private static ConfigModule textConfig;
	
	public void onEnable()
	{
		plugin = this;
		
		loadCommands();
		loadConfigs();
		loadEvents();
		loadGroups();
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
		if(generalConfig.getBoolean("Modules.Commands"))
		{
			cmdExecutor = new BasicCommandExecutor();
			
			new BanCMD();
		}
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
	}
	
	/*
	 * Loads all the groups.
	 */
	private void loadGroups()
	{
		for(String groupNames : groupConfig.getConfig().getConfigurationSection("Groups").getKeys(false))
		{
			new GroupModule(groupNames);
		}
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
	 * Gets the group config for the plugin. Includes groups, permissions, and ranking system.
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
}
