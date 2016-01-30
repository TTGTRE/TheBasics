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
package io.github.GoldenDeveloper79.TheBasics.Configs;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Set;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import io.github.GoldenDeveloper79.TheBasics.TheBasics;

public class BasicConfig 
{
	private int comments;
	private BasicConfigManager manager;

	private File file;
	private FileConfiguration config;

	@SuppressWarnings("deprecation")
	public BasicConfig(InputStream configStream, File configFile, int comments)
	{
		this.comments = comments;
		manager = new BasicConfigManager();

		file = configFile;
		
		FileConfiguration localConfig = YamlConfiguration.loadConfiguration(TheBasics.getPlugin().getResource(configFile.getName()));
		config = YamlConfiguration.loadConfiguration(configFile);
		
		for(String key : localConfig.getKeys(false))
		{
			if(!config.contains(key))
			{
				set(key, localConfig.get(key));
			}
		}
	}

	public Object get(String path)
	{
		return config.get(path);
	}

	public Object get(String path, Object def) 
	{
		return config.get(path, def);
	}

	public String getString(String path)
	{
		return config.getString(path);
	}

	public String getString(String path, String def)
	{
		return config.getString(path, def);
	}

	public int getInt(String path)
	{
		return config.getInt(path);
	}

	public int getInt(String path, int def)
	{
		return config.getInt(path, def);
	}

	public boolean getBoolean(String path) 
	{
		return config.getBoolean(path);
	}

	public boolean getBoolean(String path, boolean def)
	{
		return config.getBoolean(path, def);
	}

	public void createSection(String path)
	{
		config.createSection(path);
	}

	public ConfigurationSection getConfigurationSection(String path)
	{
		return config.getConfigurationSection(path);
	}

	public double getDouble(String path) 
	{
		return config.getDouble(path);
	}

	public double getDouble(String path, double def) 
	{
		return config.getDouble(path, def);
	}
	
	public List<String> getStringList(String path)
	{
		return config.getStringList(path);
	}
	
	public List<?> getList(String path) 
	{
		return config.getList(path);
	}

	public List<?> getList(String path, List<?> def) 
	{
		return config.getList(path, def);
	}

	public boolean contains(String path) 
	{
		return config.contains(path);
	}

	public void removeKey(String path) 
	{
		config.set(path, null);
	}

	public void set(String path, Object value) 
	{
		config.set(path, value);
		saveConfig();
	}

	public void update(String path, Object value)
	{
		if(!contains(path) || !get(path).equals(value))
		{
			set(path, value);
		}
	}
	
	public void set(String path, Object value, String comment) 
	{
		if(!config.contains(path)) 
		{
			config.set("TheBasics_COMMENT_" + comments, " " + comment);
			comments++;
		}

		config.set(path, value);
		saveConfig();
	}

	public void set(String path, Object value, String[] comment) 
	{
		for(String comm : comment)
		{
			if(!config.contains(path)) 
			{
				config.set("TheBasics_COMMENT_" + comments, " " + comm);
				comments++;
			}
		}

		config.set(path, value);
		saveConfig();
	}

	public void setHeader(String[] header) 
	{
		manager.setHeader(file, header);
		comments = header.length + 2;
		reloadConfig();
	}

	@SuppressWarnings("deprecation")
	public void reloadConfig() 
	{
		config = YamlConfiguration.loadConfiguration(manager.getConfigContent(file));
	}

	public void saveConfig() 
	{
		String configToString = config.saveToString();
		manager.saveConfig(configToString, file);
	}

	public Set<String> getKeys()
	{
		return config.getKeys(false);
	}
}
