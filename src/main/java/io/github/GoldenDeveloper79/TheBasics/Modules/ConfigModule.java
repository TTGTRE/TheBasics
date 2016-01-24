package io.github.GoldenDeveloper79.TheBasics.Modules;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import io.github.GoldenDeveloper79.TheBasics.TheBasics;
import io.github.GoldenDeveloper79.TheBasics.Player.PlayerData;

public class ConfigModule
{
	private File file;
	private FileConfiguration config;
	
	/*
	 * The constructor of the configs. Will always be called on super;
	 */
	public ConfigModule(File file)
	{
		this.file = file;
		
		if(!file.exists())
		{
			try
			{
				file.createNewFile();
				
				if(!(this instanceof PlayerData))
				{
					TheBasics.getPlugin().saveResource(file.getName(), true);
				}
			} catch (IOException e)
			{
				TheBasics.getLog().severe("Could not create the file " + file.getName() + "!");
			}
		}
		
		config = YamlConfiguration.loadConfiguration(file);
	}

	/*
	 * Sets a value to the specified key.
	 */
	public void set(String key, Object value)
	{
		config.set(key, value);
		
		try 
		{
			config.save(file);
		} catch (IOException e) 
		{
			TheBasics.getLog().severe("Could not save the config for " + file.getName() + "!");
		}
	}
	
	/*
	 * Sets a value to the specified key when the value does not equal the same in the config.
	 */
	public void update(String key, Object value)
	{
		if(!contains(key) || !get(key).equals(value))
		{
			set(key, value);
		}
	}
	
	/*
	 * Gets an object from a specified key.
	 */
	public Object get(String key)
	{
		return config.get(key);
	}
	
	/*
	 * Gets a string from a specified key.
	 */
	public String getString(String key)
	{
		return config.getString(key);
	}
	
	/*
	 * Gets an integer from a specified key.
	 */
	public int getInt(String key)
	{
		return config.getInt(key);
	}
	
	/*
	 * Gets a double from a specified key.
	 */
	public double getDouble(String key)
	{
		return config.getDouble(key);
	}
	
	/*
	 * Gets a boolean from a specified key.
	 */
	public boolean getBoolean(String key)
	{
		return config.getBoolean(key);
	}
	
	/*
	 * Checks if a path exist from a specified key.
	 */
	public boolean contains(String key)
	{
		return config.contains(key);
	}
	
	/*
	 * Gets a string list from a specified key.
	 */
	public List<String> getStringList(String key)
	{
		return config.getStringList(key);
	}
	
	/*
	 * Gets the file associated with the configuration.
	 */
	public File getFile()
	{
		return file;
	}

	/*
	 * Gets the configuration.
	 */
	public FileConfiguration getConfig() 
	{
		return config;
	}
}
