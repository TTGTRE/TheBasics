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
	public GeneralConfig(String fileName)
	{
		super(fileName);
		
		loadDefaults();
		load();
		getConfig().options().header("Please contact me on Bukkit/Spigot if you have any questions.");
		getConfig().options().copyDefaults(true);
		getConfig().save();
		load();
	}

	//The Defaults
	public void loadDefaults()
	{
		//General Settings
		getConfig().addDefault("AutoUpdating", true);
		getConfig().addComment("AutoUpdating", "",
				"##########################",
				"#    General Settings    #",
				"#(Used for general stuff)#", 
				"##########################",
				" ",
				"#If auto updating should be allowed. (Default=true)");
		getConfig().addDefault("Prefix", "&0&l[&6&lTheBasics&0&l]&6 ");
		getConfig().addComment("Prefix", " ", "#The prefix for all messaging/broadcasting. (Default='&0&l[&6&lTheBasics&0&l]&6 ')");
		getConfig().addDefault("TeleportDelay", 3);
		getConfig().addComment("TeleportDelay", " ", "#The delay for teleporting in seconds. Use the permission'TheBasics.Teleport.Override' to ignore the timer. (Default=3)");
		getConfig().addDefault("TeleportRequestTime", 60);
		getConfig().addComment("TeleportRequestTime", " ", "#The time for a player to accept a teleport request. Will cancel after that time in seconds. (Default=60)");
		getConfig().addDefault("Homes", "");
		getConfig().addComment("Homes", " ", 
				"#The max homes per a group. Must use TheBasics permissions/groups. If no value is set, its assumed unlimited.", 
				"#Can also use 'TheBasics.Home.Unlimited' for max homes.");
		getConfig().addDefault("CombatTag.Enabled", true);
		getConfig().addDefault("CombatTag.Time", 10);
		getConfig().addComment("CombatTag.Enabled", "#If combat tagging should be enabled. If a player is tagged they cannot teleport.");
		getConfig().addComment("CombatTag.Time", "#The time in seconds that tagging a player will last.");
		getConfig().addComment("CombatTag", " ");
		
		//Economy Settings
		getConfig().addDefault("StartingBalance", 250);
		getConfig().addComment("StartingBalance", 
				" ",
				"##########################", 
				"#    Economy Settings    #", 
				"#(Used for economy stuff)#", 
				"##########################",
				"",
				"#The balance that all players start with. (Default=250)");
		getConfig().addDefault("Loaning.Enabled", true);
		getConfig().addComment("Loaning.Enabled", "#If players should be allowed to loan. (Defualt=true)");
		getConfig().addDefault("Loaning.MaxAmount", 500);
		getConfig().addComment("Loaning.MaxAmount"," ", "#The maximum amount a player can loan for. (Default=500)");
		getConfig().addComment("Loaning", " ");
		
		//ChatSettings
		getConfig().addDefault("Nickname.Enabled", true);
		getConfig().addComment("Nickname.Enabled", "#If the command /nick should be allowed.(Default=true)");
		getConfig().addDefault("Nickname.MaxLength", 20);
		getConfig().addComment("Nickname.MaxLength"," ", "#The maximum length of a nick. Includes coloring/style. Ex: &6&lGoldenDeveloper is 19. (Default = 20)");
		getConfig().addDefault("Nickname.MinLength", 5);
		getConfig().addComment("Nickname.MinLength"," ", "#The minimum length of a nick. Includes coloring/style. Ex: &6&lAu_79 is 9. (Default = 8)");
		getConfig().addComment("Nickname",
				" ",
				"#######################", 
				"#    Chat Settings    #", 
				"#(Used for chat stuff)#", 
				"#######################",
				" ");
		
		getConfig().addDefault("ChatFormat", "");
	    getConfig().addComment("ChatFormat", 
	    		" ",
	    		"#The chat format for groups.",
				"#<World> = world name.",
				"#<Prefix> = prefix name (Must use TheBasics permissions).",
				"#<Name> = player name.",
				"#<Message> = message.",
				"#Example: Admin: &f[&c<World>&f] <Prefix> &c<Name> &f: &c<Message>");
	    getConfig().addDefault("MessageFormat.Sender", "&f[&6You &f-> &6%p&f] %m");
	    getConfig().addDefault("MessageFormat.Receiver", "&f[&6%p &f-> &6You&f] %m");
	    getConfig().addComment("MessageFormat", " ", "#The format for messaging players. (%p = sender/recevier, %m = message)");
	    getConfig().addComment("MessageFormat.Sender", "#What the sender will see.");
	    getConfig().addComment("MessageFormat.Receiver", " ", "#What the receiver will see.");
	    
	    getConfig().addDefault("Kits", "");
	    getConfig().addComment("Kits",
				" ",
				"#######################", 
				"#    Kit Settings    #", 
				"#(Used for kits stuff)#", 
				"#######################",
				" ",
				"#To ask questions about creating kits, please check message me on Bukkit or Spigot.",
				"#The time is in seconds.",
				"#Commands to be run during this kit don't need to include a '/'. Also the commands are ran by the console.",
				"#Enchantment format is Enchant: <Enchantment>-<LeveL>.",
				"#Item format is <ItemID:ItemData/ItemName> <Amount>",
				"#Naming format is Name: <Name>.",
				"#To create a kit, simply follow this format (It includes the maximium you can do and the variety you can do):",
				"#  Member:",
				"#    Time: 6000",
	            "#    Items: ",
	            "#    - stone_spade 1",
	            "#    - 1 6",
	            "#    - 5:1 2",
	            "#    - stone_sword 1 Name: &6Derp Sword",
	            "#    - stone_axe 1 Name: &2&lSpecial Axe Enchant: Efficiency 1",
	            "#    Commands:",
	            "#    - balance add %p 200");
	}
}
