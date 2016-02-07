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

public class LanguageConfig extends ConfigModule
{
	private CommentedConfiguration config;
	
	public LanguageConfig(String fileName)
	{
		super(fileName);
		
		config = getConfig();
		
		loadDefaults();
		load();
		config.options().copyDefaults(true);
		config.save();
		load();
	}

	public void loadDefaults()
	{
		config.addDefault("NoPermission", "&cYou do not have enough permission to perform this command!");
		config.addDefault("PlayerOffline", "&cThat player is not online!");
		config.addDefault("PlayerNeverPlayed", "&cThat player has never played before!");
		config.addDefault("Usage", "&cUsage: &7%u");
		config.addDefault("InvalidValue", "&cPlease specify a valid value!");
		config.addDefault("PlayerCommand", "&cYou must be a player to perform this command!");
		
		config.addDefault("BackNoLocation", "&cYou have no previous location to teleport to.");
		config.addDefault("BalanceGet", "&6Your balance is &7$%a&6.");
		config.addDefault("BalanceGetOthers", "&6The balance of &7%p &6is &7$%a&6.");
		config.addDefault("BalanceSetSender", "&6You have set the balance for &7%p &6to &7$%a&6.");
		config.addDefault("BalanceSetReceiver", "&6Your balance has been set to &7$%a &6by &7%p&6.");
		config.addDefault("BalanceGaveSender", "&6You gave &7%p $%a &6.");
		config.addDefault("BalanceGaveReceiver", "&6You received &7%a &6from &7%p&6.");
		config.addDefault("BalanceTakeSender", "&6You took &7$%a &6from &7%p&6.");
		config.addDefault("BalanceTakeReceiver", "&6You lost &7%a from &6%p&6.");
		config.addDefault("BalanceLackOfFunds", "&cThat player does not have enough money!");
		config.addDefault("BanDefault", "&cYou have been banned by &7%p&c.");
		config.addDefault("BanSender", "&6You have banned &7%p");
		config.addDefault("BanNotify", "&6The player &7%p &6has banned the player &7%s &6for &7%r&6.");
		config.addDefault("ClearInventory", "&6You have cleared your inventory.");
		config.addDefault("ClearInventorySender", "&6You have cleared the inventory for &7%p&6.");
		config.addDefault("ClearInventoryReceiver", "&6Your inventory has been cleared by &7%p&6.");
		config.addDefault("CombatTagged", "&6You have been &7combat tagged.");
		config.addDefault("UnCombatTagged", "&6You have been &7uncombat tagged.");
		config.addDefault("CombatTagNoTeleport", "&cYou are combat tagged and cannot teleport!");
		config.addDefault("DeleteHome", "&6You have deleted the home &7%a&6.");
		config.addDefault("Enchant", "&6You have enchanted the item in your hand with &7%a&6.");
		config.addDefault("EnchantInvalidItem", "&cThat item is not enchantable!");
		config.addDefault("EnchantInvalid", "&cPlease specify a valid enchantment and value!");
		config.addDefault("HomeExist", "&cThat home does not exist!");
		config.addDefault("DefaultHome", "&cYou cannot delete your default home!");
		config.addDefault("Feed", "&6You have fed yourself.");
		config.addDefault("FeedSender", "&6You have fed the player &7%p&6.");
		config.addDefault("FeedReceiver", "&6You have been fed by &7%p&6.");
		config.addDefault("Fly", "&6You have &7%a &6flying for yourself."); 
		config.addDefault("FlySender", "&6You have &7%a &6flying for &7%p&6."); 
		config.addDefault("FlyReceiver", "&6Flying has been &7%a &6by &7%p&6.");
		config.addDefault("Gamemode", "&6You have changed your gamemode to &7%a&6.");
		config.addDefault("GamemodeSender", "&6You have changed the gamemode for &7%p &6to &7%a&6.");
		config.addDefault("GamemodeReceiver", "&6Your gamemode has been changed to &7%a &6by &7%p&6.");
		config.addDefault("Give", "&6You have gave yourself &7%a2x &6of &7%a&6.");
		config.addDefault("GiveSender", "&6You gave &7%p %a2x &6of &7%a &6.");
		config.addDefault("GiveReceiver", "&6You received &7%a2x &6of &7%a &6from &7%p&6.");
		config.addDefault("GroupSetSender", "&6You changed &7%p &6group to &7%a&6.");
		config.addDefault("GroupSetReceiver", "&6Your group has been changed to &7%a &6by &7%p&6.");
		config.addDefault("GroupGet", "&6The player &7%p &6group is &7%a&6.");
		config.addDefault("GroupCreate", "&6You have created the group called &7%a&6.");
		config.addDefault("GroupExist", "&cThat group does not exist!");
		config.addDefault("GroupExistTwo", "&cThat group already exist!");
		config.addDefault("GroupPrefix", "&6You changed the prefix for the group &7%a &6to &7%p&6.");
		config.addDefault("GroupAddPerm", "&6You have addded the permission &7%p &6to the group &7%a&6.");
		config.addDefault("GroupRemovePerm", "&6You have removed the permission &7%p &6from the group &7%a&6.");
		config.addDefault("GroupContainsPermission", "&cThat group does not have that permission!");
		config.addDefault("Heal", "&6You have healed yourself.");
		config.addDefault("HealSender", "&6You have healed the player &7%p&6.");
		config.addDefault("HealReceiver", "&6You have been healed by &7%p&6.");
		config.addDefault("HelpPage", "&cPlease specify a valid page!");
		config.addDefault("HelpSender", "&6You have sent help pages to &7%p&6.");
		config.addDefault("Homes", "&6Homes: &7%a&6.");
		config.addDefault("IgnoredAt", "&cThat player is ignoring you!");
		config.addDefault("IgnoredTo", "&cYou are ignoring that player!");
		config.addDefault("IgnoredSender", "&6You have ignored the player &7%p&6.");
		config.addDefault("AlreadyIgnored", "&cYou have already ignored that player!");
		config.addDefault("IgnoreSelf", "&cYou cannot ignore yourself!");
		config.addDefault("KickDefault", "&cYou have been kicked by &7%p&c.");
		config.addDefault("KickSender", "&6You have kicked the player &7%p&6."); 
		config.addDefault("KickNotify", "&6The player &7%p &6has kicked the player &7%s &6for &7%r&6.");
		config.addDefault("KitUse", "&6You have used the kit &7%a&6.");
		config.addDefault("KitExist", "&cThat kit does not exist!");
		config.addDefault("KitTime", "&cYou cannot use that kit for another &7%a&c.");
		config.addDefault("KitList", "&6Kits: &7%a&6.");
		config.addDefault("KitError", "&cThere has been an error giving you the kit. Please contact an admin.");
		config.addDefault("MuteDefault", "&cYou have been muted by &7%p&c.");
		config.addDefault("MuteSender", "&6You have muted &7%p&6.");
		config.addDefault("MuteReceiver", "&6You have been muted by &7%p &6for &7%r&6.");
		config.addDefault("MuteNotify", "&6The player &7%p &6has muted the player &7%s &6for &7%r&6.");
		config.addDefault("MuteInvalidTime", "&cPlease specify a valid time in years, weeks, days, hours, minutes, or seconds.");
		config.addDefault("Muted", "&cYou are muted and cannot talk till &7%a&c!");
		config.addDefault("MuteAlready", "&cThat player is already muted!");
		config.addDefault("MuteNot", "&cThat player is not muted!");
		config.addDefault("Nick", "&6You have changed your nick to &7%a&6.");
		config.addDefault("NickInvalid", "&cPlease specify a valid nickname!");
		config.addDefault("NickDisabled", "&cChanging your nickname is disabled on this server!");
		config.addDefault("PaySender", "&6You paid &7%p $%a&6.");
		config.addDefault("PayReceiver", "&6You received &7$%a &6from &7%p&6.");
		config.addDefault("PayLackOfFunds", "&cYou do not have enough money!");
		config.addDefault("PlayTimeGet", "&6Your playtime is &7%a&6.");
		config.addDefault("PlayTimeGetOther", "&7%p &6playtime is &7%a&6.");
		config.addDefault("PlayTimeRankup", "&6You have ranked up to &7%a&6.");
		config.addDefault("Repair", "&6You have repaired the item in your hand.");
		config.addDefault("RepairInvalidItem", "&cPlease be holding an item to perform this command!");
		config.addDefault("RepairInvalid", "&cThat item is not repairable!");
		config.addDefault("NoReply", "&cYou have nobody to reply to.");
		config.addDefault("RulesPage", "&cPlease specify a valid page!");
		config.addDefault("RulesSender", "&6You have sent rule pages to &7%p&6.");
		config.addDefault("SetHome", "&6You have set the home &7%a &6at your location.");
		config.addDefault("SetHomeMaxHome", "&cYou have reached your max sethome limit!");
		config.addDefault("SetSpawn", "&6You have set spawn at your location.");
		config.addDefault("SetWarp", "&6You have set the warp &7%a &6at your location.");
		config.addDefault("TeleportToLocation", "&6You have teleported to &7%a&6.");
		config.addDefault("TeleportInitialize", "&6You will teleport in &7%ts&6.");
		config.addDefault("TeleportTimeRemaing", "&7%ts&6...");
		config.addDefault("TeleportRequestSender", "&6You have sent a request to teleport to &7%p&6.");
		config.addDefault("TeleportRequestReceiver", "&6The player &7%p &6has requested to teleport to you. Do /trdeny or /traccept.");
		config.addDefault("TeleportNoRequest", "&cYou have no teleport request!");
		config.addDefault("TeleportRequestAcceptReceiver", "&6The player &7%p &6has accepted your teleport request.");
		config.addDefault("TeleportRequestDenySender", "&6You have denied the teleport request from &7%p&6.");
		config.addDefault("TeleportRequestDenyReceiver", "&6The player &7%p &6has denied your teleport request.");
		config.addDefault("TeleportRequestTimeout", "&cYou teleport request has timed out.");
		config.addDefault("TempBanDefault", "&cYou have been tempbanned by &7%p&c.");
		config.addDefault("TempBanSender", "&6You have tempbanend &7%p&6.");
		config.addDefault("TempBanNotify", "&6The player &7%p &6has tempbanned the player &7%s &6for &7%r&6.");
		config.addDefault("TempBanInvalidTime", "&cPlease specify a valid time in years, weeks, days, hours, minutes, or seconds.");
		config.addDefault("TimeGet", "&6The time is &7%a&6.");
		config.addDefault("TimeSet", "&6You set the time to &7%a&6.");
		config.addDefault("TimeInvalid", "&cPlease specify a valid time.");
		config.addDefault("UnbanSender", "&6You have unbanned the player &7%p&6.");
		config.addDefault("UnbanNotify", "&6The player &7%p &6has unbanned the player &7%s&6.");
		config.addDefault("UnmuteReceiver", "&6You have been unmuted.");
		config.addDefault("UnmuteSender", "&6You have unmuted the player &7%p&6.");
		config.addDefault("UnmuteNotify", "&6The player &7%p &6has been unmuted.");
		config.addDefault("PlayerNotBanned", "&cThat player is not banned!");
		config.addDefault("Vanish", "&6You have &7%a&6.");
		config.addDefault("VanishSender", "&6You have %a the player &7%p&6.");
		config.addDefault("VanishReceiver", "&6You have been %a by &7%p&6.");
		config.addDefault("WarpExist", "&cThat warp does not exist!");
		config.addDefault("WarnDefault", "&cYou have been warned by &7%p&c.");
		config.addDefault("WarnReceiver", "&6You have been warned because of &7%r&6.");
		config.addDefault("WarnSender", "&6You have warned &7%p");
		config.addDefault("WarnNotify", "&6The player &7%p &6has warned the player &7%s &6for &7%r&6.");
		config.addDefault("WeatherSender", "&6You have set the weather to &7%a&6.");
		config.addDefault("WeatherInvalid", "&cPlease specify a valid weather.");
			
		config.addComment("NoPermission", 
				"#Any questions please ask on spigot or bukkit.",
				"#PS. Not all of these variables can be used for every message.",
				"#I have put in the maximum amount of variables possible.",
				"",
				"#Variables:",
				"#%p = player that is being discussed, prefix, or permission.",
				"#%s = an additional player that is being discussed.",
				"#%r = reason for punishment",
				"#%t = time for world time or punishment time.",
				"#%a = amount/name, can be for balance, name of homes, names of items, modes for flying/creative, etc.",
				"#%a1 = amount/name 2, pretty much just used for item amount.",
				"#%u = usage for a command.",
				"#%t = time for teleporting.",
				"",
				"#General Command Stuff");
		config.addComment("BackNoLocation", " ", "#Per Command Stuff");
	}
}