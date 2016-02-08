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
package io.github.GoldenDeveloper79.TheBasics.Config;

import java.util.ArrayList;

import io.github.GoldenDeveloper79.TheBasics.Modules.ConfigModule;

public class DataConfig extends ConfigModule
{
	public DataConfig(String fileName)
	{
		super(fileName);
		
		loadDefaults();
		load();
		getConfig().options().copyDefaults(true);
		getConfig().save();
		load();
	}

	public void loadDefaults()
	{
		getConfig().addDefault("Server", new ArrayList<String>());
		getConfig().addDefault("Players", new ArrayList<String>());
		getConfig().addDefault("IpBans", new ArrayList<String>());
		
		getConfig().addComment("Server", "#Please do not modify this file. It will just mess things up...", " ");
	}
}