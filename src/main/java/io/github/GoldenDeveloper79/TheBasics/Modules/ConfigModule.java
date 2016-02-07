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
package io.github.GoldenDeveloper79.TheBasics.Modules;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;

import io.github.GoldenDeveloper79.TheBasics.TheBasics;
import io.github.GoldenDeveloper79.TheBasics.Config.CommentedConfiguration;

public abstract class ConfigModule 
{
	private File file;
	private CommentedConfiguration config;
	
	public ConfigModule(String fileName)
	{
		this.file = new File(TheBasics.getMainDir(), fileName);
		
		if(!file.exists())
		{
			try 
			{
				file.createNewFile();
			}catch(IOException e) 
			{
				TheBasics.getLog().severe("Could not create " + file.getName() + "!");
			}
		}
		
		config = new CommentedConfiguration(file);
	}
	
	public abstract void loadDefaults();
	
	public void load()
	{
		try
		{
			config.load(file);
		}catch(IOException | InvalidConfigurationException e)
		{
			TheBasics.getLog().severe("Could not load the file " + file.getName() + "!");
		}
	}
	
	/*
	 * Sets a value to the specified key.
	 */
	public void set(String key, Object value)
	{
		config.set(key, value);
		config.save();
	}

	/*
	 * Sets a value to the specified key when the value does not exist.
	 */
	public void update(String key, Object value)
	{
		if(!contains(key) || get(key) != value)
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
	 * Gets a string from a specified key with a default.
	 */
	public String getString(String key, String def)
	{
		return config.getString(key, def);
	}
	
	/*
	 * Gets an integer from a specified key.
	 */
	public int getInt(String key)
	{
		return config.getInt(key);
	}

	/*
	 * Gets an integer from a specified key with a default.
	 */
	public int getInt(String key, int def)
	{
		return config.getInt(key, def);
	}
	
	/*
	 * Gets a double from a specified key.
	 */
	public double getDouble(String key)
	{
		return config.getDouble(key);
	}
	
	/*
	 * Gets a long from a specified key.
	 */
	public long getLong(String key)
	{
		return config.getLong(key);
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
	 * Gets a configuration section from a specified key.
	 */
	public ConfigurationSection getConfigurationSection(String key)
	{
		return config.getConfigurationSection(key);
	}
	
	/*
	 * Gets the keys of the file.
	 */
	public Set<String> getKey(boolean value)
	{
		return config.getKeys(value);
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
	public CommentedConfiguration getConfig() 
	{
		return config;
	}
}
