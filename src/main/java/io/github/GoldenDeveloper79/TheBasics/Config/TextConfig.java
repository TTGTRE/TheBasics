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
	public TextConfig(String fileName)
	{
		super(fileName);

		loadDefaults();
		load();
		getConfig().options().copyDefaults(true);
		getConfig().save();
		load();
	}

	//The Defaults
	public void loadDefaults()
	{
		//MOTD Stuff
		getConfig().addDefault("ListMOTD", "&6Welcome to -=Server=-. It is powered by TheBasics.");
		getConfig().addComment("ListMOTD", "#The server motd on the server list.");
		getConfig().addDefault("JoinMOTD", "&6Welcome back to -=Server=-!");
		getConfig().addComment("JoinMOTD", "#The join message motd.");
		getConfig().addDefault("FirstJoinMOTD", "&6Welcome to the -=Server=- %p!");
		getConfig().addComment("FirstJoinMOTD", "#The join message motd for new players.");
		
		//Rules
		getConfig().addDefault("Rules.PerPage", 3);
		getConfig().addDefault("Rules.List", Arrays.asList(new String[] 
				{"&7Please do not swear!",
				 "&7Please do not grief!",
				 "&7Please smile more!"
				}));
		getConfig().addDefault("Rules.Format.Top", "&6&l----------[Rules - %p/%m]----------");
		getConfig().addDefault("Rules.Format.Bottom", "&6&l-----------------------------");
		getConfig().addComment("Rules", " ");
		getConfig().addComment("Rules.PerPage", "#The amount of rules to list per page. (Default = 3)");
		getConfig().addComment("Rules.List", "#The list of actual rules.");
		getConfig().addComment("Rules.Format", "#The formating of the rules.");
		getConfig().addComment("Rules.Format.Top", "#What should be displayed on the top.(%p = page #, %m = maxPage)");
		getConfig().addComment("Rules.Format.Bottom", "#What should be displayed on the bottom.");
		
		//Help
		getConfig().addDefault("Help.PerPage", 3);
		getConfig().addDefault("Help.Format.Top", "&6&l----------[Help - %p/%m]----------");
		getConfig().addDefault("Help.Format.Bottom", "&6&l-----------------------------");
		getConfig().addComment("Help", " ");
		getConfig().addComment("Help.PerPage", "#The amount of Help to list per page. (Default = 3)");
		getConfig().addComment("Help.Format", "#The formating of the Help.");
		getConfig().addComment("Help.Format.Top", "#What should be displayed on the top.(%p = page #, %m = maxPage)");
		getConfig().addComment("Help.Format.Bottom", "#What should be displayed on the bottom.");
		
		//Info
		getConfig().addDefault("Info.List", Arrays.asList(new String[] 
				{"&6Name: &7%n",
				"&6Displayname: &7%d",
				"&6Address: &7%a",
				"&6Balance: &7%b",
				"&6Group: &7%g",
				"&6Playtime: &7%p",
				"&6LastLogin: &7%l",
				}));
		
		getConfig().addDefault("Info.Format.Top", "&6&l----------[Info]----------");
		getConfig().addDefault("Info.Format.Bottom", "&6&l---------------------------");
		getConfig().addComment("Info", " ");
		getConfig().addComment("Info.List", "#The list of actual Info to be displayed.", "#Max variables are being used.");
		getConfig().addComment("Info.Format", "#The formating of the Info.");
		getConfig().addComment("Info.Format.Top", "#What should be displayed on the top.");
		getConfig().addComment("Info.Format.Bottom", "#What should be displayed on the bottom.");
		
		//List
		getConfig().addDefault("List.PerPage", 15);
		getConfig().addDefault("List.Format.Top", "&6&l----------[List - %p]----------");
		getConfig().addDefault("List.Format.Bottom", "&6&l---------------------------");
		getConfig().addComment("List", " ");
		getConfig().addComment("List.PerPage", "#The amount of players to list per page. (Default = 15)");
		getConfig().addComment("List.Format", "#The formating of the List.");
		getConfig().addComment("List.Format.Top", "#What should be displayed on the top. (%p = page number)");
		getConfig().addComment("List.Format.Bottom", "#What should be displayed on the bottom");
		
		//Join
		getConfig().addDefault("Join.Message", "&6%p has joined the server!");
		getConfig().addDefault("Join.SilentJoin", true);
		getConfig().addComment("Join", " ");
		getConfig().addComment("Join.Message", "#The message to be broadcasted when a player joins the server. (%p = playerName)");
		getConfig().addComment("Join.SilentJoin", "#If players with the permission 'TheBasics.SilentJoin' should not be broadcasted on join. (Defaut = true)");
		
		//Quit
		getConfig().addDefault("Quit.Message", "&6%p has left the server!");
		getConfig().addDefault("Quit.SilentQuit", true);
		getConfig().addComment("Quit", " ");
		getConfig().addComment("Quit.Message", "#The message to be broadcasted when a player quit the server. (%p = playerName)");
		getConfig().addComment("Quit.SilentQuit", "#If players with the permission 'TheBasics.SilentQuit' should not be broadcasted on Quit. (Defaut = true)");
	}
}
