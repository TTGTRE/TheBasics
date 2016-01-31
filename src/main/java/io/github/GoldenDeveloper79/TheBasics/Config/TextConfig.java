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

import java.util.Arrays;

import io.github.GoldenDeveloper79.TheBasics.Modules.ConfigModule;

public class TextConfig extends ConfigModule
{
	private CommentedConfiguration config;
	
	public TextConfig(String fileName)
	{
		super(fileName);
		
		config = getConfig();
		
		loadDefaults();
		load();
		config.options().copyDefaults(true);
		config.save();
		load();
	}

	//The Defaults
	public void loadDefaults()
	{
		//MOTD Stuff
		config.addDefault("ListMOTD", "&6Welcome to -=Server=-. It is powered by TheBasics.");
		config.addComment("ListMOTD", "#The server motd on the server list.");
		config.addDefault("JoinMOTD", "&6Welcome back to -=Server=-!");
		config.addComment("JoinMOTD", "#The join message motd.");
		config.addDefault("FirstJoinMOTD", "&6Welcome to the -=Server=- %p!");
		config.addComment("FirstJoinMOTD", "#The join message motd for new players.");
		
		//Rules
		config.addDefault("Rules.PerPage", 3);
		config.addDefault("Rules.List", Arrays.asList(new String[] 
				{"&7Please do not swear!",
				 "&7Please do not grief!",
				 "&7Please smile more!"
				}));
		config.addDefault("Rules.Format.Top", "&6&l----------[Rules - %p/%m]----------");
		config.addDefault("Rules.Format.Bottom", "&6&l-----------------------------");
		config.addComment("Rules", " ");
		config.addComment("Rules.PerPage", "#The amount of rules to list per page. (Default = 3)");
		config.addComment("Rules.List", "#The list of actual rules.");
		config.addComment("Rules.Format", "#The formating of the rules.");
		config.addComment("Rules.Format.Top", "#What should be displayed on the top.(%p = page #, %m = maxPage)");
		config.addComment("Rules.Format.Bottom", "#What should be displayed on the bottom.");
		
		//Help
		config.addDefault("Help.PerPage", 3);
		config.addDefault("Help.Format.Top", "&6&l----------[Help - %p/%m]----------");
		config.addDefault("Help.Format.Bottom", "&6&l-----------------------------");
		config.addComment("Help", " ");
		config.addComment("Help.PerPage", "#The amount of Help to list per page. (Default = 3)");
		config.addComment("Help.Format", "#The formating of the Help.");
		config.addComment("Help.Format.Top", "#What should be displayed on the top.(%p = page #, %m = maxPage)");
		config.addComment("Help.Format.Bottom", "#What should be displayed on the bottom.");
		
		//Info
		config.addDefault("Info.List", Arrays.asList(new String[] 
				{"&6Name: &7%n",
				"&6Displayname: &7%d",
				"&6Address: &7%a",
				"&6Balance: &7%b",
				"&6Group: &7%g",
				"&6Playtime: &7%p",
				"&6LastLogin: &7%l",
				}));
		
		config.addDefault("Info.Format.Top", "&6&l----------[Info]----------");
		config.addDefault("Info.Format.Bottom", "&6&l---------------------------");
		config.addComment("Info", " ");
		config.addComment("Info.PerPage", "#The amount of Info to list per page. (Default = 3)");
		config.addComment("Info.List", "#The list of actual Info to be displayed.", "#Max variables are being used.");
		config.addComment("Info.Format", "#The formating of the Info.");
		config.addComment("Info.Format.Top", "#What should be displayed on the top.");
		config.addComment("Info.Format.Bottom", "#What should be displayed on the bottom.");
		
		//Join
		config.addDefault("Join.Message", "&6%p has joined the server!");
		config.addDefault("Join.SilentJoin", true);
		config.addComment("Join", " ");
		config.addComment("Join.Message", "#The message to be broadcasted when a player joins the server. (%p = playerName)");
		config.addComment("Join.SilentJoin", "#If players with the permission 'TheBasics.SilentJoin' should not be broadcasted on join. (Defaut = true)");
		
		//Quit
		config.addDefault("Quit.Message", "&6%p has left the server!");
		config.addDefault("Quit.SilentQuit", true);
		config.addComment("Quit", " ");
		config.addComment("Quit.Message", "#The message to be broadcasted when a player quit the server. (%p = playerName)");
		config.addComment("Quit.SilentQuit", "#If players with the permission 'TheBasics.SilentQuit' should not be broadcasted on Quit. (Defaut = true)");
	}
}
