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
package io.github.GoldenDeveloper79.TheBasics.Modules;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import io.github.GoldenDeveloper79.TheBasics.BasicUtils;
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

		if(!(this instanceof PlayerData))
		{
			BasicUtils.updateConfig(config, file);
		}
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
