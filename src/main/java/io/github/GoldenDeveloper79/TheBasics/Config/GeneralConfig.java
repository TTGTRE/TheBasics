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

import io.github.GoldenDeveloper79.TheBasics.Modules.ConfigModule;

public class GeneralConfig extends ConfigModule
{
	private CommentedConfiguration config;
	
	public GeneralConfig(String fileName)
	{
		super(fileName);
		
		config = getConfig();
		
		loadDefaults();
		load();
		config.options().header("Please contact me on Bukkit/Spigot if you have any questions.");
		config.options().copyDefaults(true);
		config.save();
		load();
	}

	//The Defaults
	public void loadDefaults()
	{
		//General Settings
		config.addDefault("AutoUpdating", true);
		config.addComment("AutoUpdating", "",
				"##########################",
				"#    General Settings    #",
				"#(Used for general stuff)#", 
				"##########################",
				" ",
				"#If auto updating should be allowed. (Default=true)");
		config.addDefault("Prefix", "&0&l[&6&lTheBasics&0&l]&6 ");
		config.addComment("Prefix", " ", "#The prefix for all messaging/broadcasting. (Default='&0&l[&6&lTheBasics&0&l]&6 ')");
		config.addDefault("TeleportDelay", 3);
		config.addComment("TeleportDelay", " ", "#The delay for teleporting in seconds. Use the permission'TheBasics.Teleport.Override' to ignore the timer. (Default=3)");
		config.addDefault("TeleportRequestTime", 60);
		config.addComment("TeleportRequestTime", " ", "#The time for a player to accept a teleport request. Will cancel after that time in seconds. (Default=60)");
		config.addDefault("Homes", "");
		config.addComment("Homes", " ", 
				"#The max homes per a group. Must use TheBasics permissions/groups. If no value is set, its assumed unlimited.", 
				"#Can also use 'TheBasics.Home.Unlimited' for max homes.");
		
		//Economy Settings
		config.addDefault("StartingBalance", 250);
		config.addComment("StartingBalance", 
				" ",
				"##########################", 
				"#    Economy Settings    #", 
				"#(Used for economy stuff)#", 
				"##########################",
				"",
				"#The balance that all players start with. (Default=250)");
		config.addDefault("Loaning.Enabled", true);
		config.addComment("Loaning.Enabled", "#If players should be allowed to loan. (Defualt=true)");
		config.addDefault("Loaning.MaxAmount", 500);
		config.addComment("Loaning.MaxAmount"," ", "#The maximum amount a player can loan for. (Default=500)");
		config.addComment("Loaning", " ");
		
		//ChatSettings
		config.addDefault("Nickname.Enabled", true);
		config.addComment("Nickname.Enabled", "#If the command /nick should be allowed.(Default=true)");
		config.addDefault("Nickname.MaxLength", 20);
		config.addComment("Nickname.MaxLength"," ", "#The maximum length of a nick. Includes coloring/style. Ex: &6&lGoldenDeveloper is 19. (Default = 20)");
		config.addDefault("Nickname.MinLength", 5);
		config.addComment("Nickname.MinLength"," ", "#The minimum length of a nick. Includes coloring/style. Ex: &6&lAu_79 is 9. (Default = 8)");
		config.addComment("Nickname",
				" ",
				"#######################", 
				"#    Chat Settings    #", 
				"#(Used for chat stuff)#", 
				"#######################",
				" ");
		
		config.addDefault("ChatFormat", "");
	    config.addComment("ChatFormat", 
	    		" ",
	    		"#The chat format for groups.",
				"#<World> = world name.",
				"#<Prefix> = prefix name (Must use TheBasics permissions).",
				"#<Name> = player name.",
				"#<Message> = message.",
				"#Example: Admin: &f[&c<World>&f] <Prefix> &c<Name> &f: &c<Message>");
	    config.addDefault("MessageFormat.Sender", "&f[&6You &f-> &6%p&f] %m");
	    config.addDefault("MessageFormat.Receiver", "&f[&6%p &f-> &6You&f] %m");
	    config.addComment("MessageFormat", " ", "#The format for messaging players. (%p = sender/recevier, %m = message)");
	    config.addComment("MessageFormat.Sender", "#What the sender will see.");
	    config.addComment("MessageFormat.Receiver", " ", "#What the receiver will see.");
	}
}
