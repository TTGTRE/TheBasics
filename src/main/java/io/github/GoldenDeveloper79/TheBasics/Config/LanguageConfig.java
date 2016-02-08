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
	public LanguageConfig(String fileName)
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
		getConfig().addDefault("NoPermission", "&cYou do not have enough permission to perform this command!");
		getConfig().addDefault("PlayerOffline", "&cThat player is not online!");
		getConfig().addDefault("PlayerNeverPlayed", "&cThat player has never played before!");
		getConfig().addDefault("Usage", "&cUsage: &7%u");
		getConfig().addDefault("InvalidValue", "&cPlease specify a valid value!");
		getConfig().addDefault("PlayerCommand", "&cYou must be a player to perform this command!");
		getConfig().addDefault("BackNoLocation", "&cYou have no previous location to teleport to.");
		getConfig().addDefault("BalanceGet", "&6Your balance is &7$%a&6.");
		getConfig().addDefault("BalanceGetOthers", "&6The balance of &7%p &6is &7$%a&6.");
		getConfig().addDefault("BalanceSetSender", "&6You have set the balance for &7%p &6to &7$%a&6.");
		getConfig().addDefault("BalanceSetReceiver", "&6Your balance has been set to &7$%a &6by &7%p&6.");
		getConfig().addDefault("BalanceGaveSender", "&6You gave &7%p $%a &6.");
		getConfig().addDefault("BalanceGaveReceiver", "&6You received &7%a &6from &7%p&6.");
		getConfig().addDefault("BalanceTakeSender", "&6You took &7$%a &6from &7%p&6.");
		getConfig().addDefault("BalanceTakeReceiver", "&6You lost &7%a from &6%p&6.");
		getConfig().addDefault("BalanceLackOfFunds", "&cThat player does not have enough money!");
		getConfig().addDefault("BanDefault", "&cYou have been banned by &7%p&c.");
		getConfig().addDefault("BanSender", "&6You have banned &7%p");
		getConfig().addDefault("BanNotify", "&6The player &7%p &6has banned the player &7%s &6for &7%r&6.");
		getConfig().addDefault("ClearInventory", "&6You have cleared your inventory.");
		getConfig().addDefault("ClearInventorySender", "&6You have cleared the inventory for &7%p&6.");
		getConfig().addDefault("ClearInventoryReceiver", "&6Your inventory has been cleared by &7%p&6.");
		getConfig().addDefault("CombatTagged", "&6You have been &7combat tagged.");
		getConfig().addDefault("UnCombatTagged", "&6You have been &7uncombat tagged.");
		getConfig().addDefault("CombatTagNoTeleport", "&cYou are combat tagged and cannot teleport!");
		getConfig().addDefault("DeleteHome", "&6You have deleted the home &7%a&6.");
		getConfig().addDefault("Enchant", "&6You have enchanted the item in your hand with &7%a&6.");
		getConfig().addDefault("EnchantInvalidItem", "&cThat item is not enchantable!");
		getConfig().addDefault("EnchantInvalid", "&cPlease specify a valid enchantment and value!");
		getConfig().addDefault("HomeExist", "&cThat home does not exist!");
		getConfig().addDefault("DefaultHome", "&cYou cannot delete your default home!");
		getConfig().addDefault("Feed", "&6You have fed yourself.");
		getConfig().addDefault("FeedSender", "&6You have fed the player &7%p&6.");
		getConfig().addDefault("FeedReceiver", "&6You have been fed by &7%p&6.");
		getConfig().addDefault("Fly", "&6You have &7%a &6flying for yourself."); 
		getConfig().addDefault("FlySender", "&6You have &7%a &6flying for &7%p&6."); 
		getConfig().addDefault("FlyReceiver", "&6Flying has been &7%a &6by &7%p&6.");
		getConfig().addDefault("FlySpeedChange", "&6You have changed your flying speed to &7%a&6.");
		getConfig().addDefault("FlySpeedChangeSender", "&6You have changed the flying speed for &7%p &6to &7%a&6.");
		getConfig().addDefault("FlySpeedChangeReceiver", "&6Your flying speed has been changed to &7%a &6by &7%p&6.");
		getConfig().addDefault("Gamemode", "&6You have changed your gamemode to &7%a&6.");
		getConfig().addDefault("GamemodeSender", "&6You have changed the gamemode for &7%p &6to &7%a&6.");
		getConfig().addDefault("GamemodeReceiver", "&6Your gamemode has been changed to &7%a &6by &7%p&6.");
		getConfig().addDefault("Give", "&6You have gave yourself &7%a2x &6of &7%a&6.");
		getConfig().addDefault("GiveSender", "&6You gave &7%p %a2x &6of &7%a &6.");
		getConfig().addDefault("GiveReceiver", "&6You received &7%a2x &6of &7%a &6from &7%p&6.");
		getConfig().addDefault("GroupSetSender", "&6You changed &7%p &6group to &7%a&6.");
		getConfig().addDefault("GroupSetReceiver", "&6Your group has been changed to &7%a &6by &7%p&6.");
		getConfig().addDefault("GroupGet", "&6The player &7%p &6group is &7%a&6.");
		getConfig().addDefault("GroupCreate", "&6You have created the group called &7%a&6.");
		getConfig().addDefault("GroupExist", "&cThat group does not exist!");
		getConfig().addDefault("GroupExistTwo", "&cThat group already exist!");
		getConfig().addDefault("GroupPrefix", "&6You changed the prefix for the group &7%a &6to &7%p&6.");
		getConfig().addDefault("GroupAddPerm", "&6You have addded the permission &7%p &6to the group &7%a&6.");
		getConfig().addDefault("GroupRemovePerm", "&6You have removed the permission &7%p &6from the group &7%a&6.");
		getConfig().addDefault("GroupContainsPermission", "&cThat group does not have that permission!");
		getConfig().addDefault("Heal", "&6You have healed yourself.");
		getConfig().addDefault("HealSender", "&6You have healed the player &7%p&6.");
		getConfig().addDefault("HealReceiver", "&6You have been healed by &7%p&6.");
		getConfig().addDefault("HelpPage", "&cPlease specify a valid page!");
		getConfig().addDefault("HelpSender", "&6You have sent help pages to &7%p&6.");
		getConfig().addDefault("Homes", "&6Homes: &7%a&6.");
		getConfig().addDefault("IgnoredAt", "&cThat player is ignoring you!");
		getConfig().addDefault("IgnoredTo", "&cYou are ignoring that player!");
		getConfig().addDefault("IgnoredSender", "&6You have ignored the player &7%p&6.");
		getConfig().addDefault("AlreadyIgnored", "&cYou have already ignored that player!");
		getConfig().addDefault("IgnoreSelf", "&cYou cannot ignore yourself!");
		getConfig().addDefault("KickDefault", "&cYou have been kicked by &7%p&c.");
		getConfig().addDefault("KickSender", "&6You have kicked the player &7%p&6."); 
		getConfig().addDefault("KickNotify", "&6The player &7%p &6has kicked the player &7%s &6for &7%r&6.");
		getConfig().addDefault("KitUse", "&6You have used the kit &7%a&6.");
		getConfig().addDefault("KitExist", "&cThat kit does not exist!");
		getConfig().addDefault("KitTime", "&cYou cannot use that kit for another &7%a&c.");
		getConfig().addDefault("KitList", "&6Kits: &7%a&6.");
		getConfig().addDefault("KitError", "&cThere has been an error giving you the kit. Please contact an admin.");
		getConfig().addDefault("MuteDefault", "&cYou have been muted by &7%p&c.");
		getConfig().addDefault("MuteSender", "&6You have muted &7%p&6.");
		getConfig().addDefault("MuteReceiver", "&6You have been muted by &7%p &6for &7%r&6.");
		getConfig().addDefault("MuteNotify", "&6The player &7%p &6has muted the player &7%s &6for &7%r&6.");
		getConfig().addDefault("MuteInvalidTime", "&cPlease specify a valid time in years, weeks, days, hours, minutes, or seconds.");
		getConfig().addDefault("Muted", "&cYou are muted and cannot talk till &7%a&c!");
		getConfig().addDefault("MuteAlready", "&cThat player is already muted!");
		getConfig().addDefault("MuteNot", "&cThat player is not muted!");
		getConfig().addDefault("Nick", "&6You have changed your nick to &7%a&6.");
		getConfig().addDefault("NickInvalid", "&cPlease specify a valid nickname!");
		getConfig().addDefault("NickDisabled", "&cChanging your nickname is disabled on this server!");
		getConfig().addDefault("PaySender", "&6You paid &7%p $%a&6.");
		getConfig().addDefault("PayReceiver", "&6You received &7$%a &6from &7%p&6.");
		getConfig().addDefault("PayLackOfFunds", "&cYou do not have enough money!");
		getConfig().addDefault("PlayTimeGet", "&6Your playtime is &7%a&6.");
		getConfig().addDefault("PlayTimeGetOther", "&7%p &6playtime is &7%a&6.");
		getConfig().addDefault("PlayTimeRankup", "&6You have ranked up to &7%a&6.");
		getConfig().addDefault("Repair", "&6You have repaired the item in your hand.");
		getConfig().addDefault("RepairInvalidItem", "&cPlease be holding an item to perform this command!");
		getConfig().addDefault("RepairInvalid", "&cThat item is not repairable!");
		getConfig().addDefault("NoReply", "&cYou have nobody to reply to.");
		getConfig().addDefault("RulesPage", "&cPlease specify a valid page!");
		getConfig().addDefault("RulesSender", "&6You have sent rule pages to &7%p&6.");
		getConfig().addDefault("SetHome", "&6You have set the home &7%a &6at your location.");
		getConfig().addDefault("SetHomeMaxHome", "&cYou have reached your max sethome limit!");
		getConfig().addDefault("SetSpawn", "&6You have set spawn at your location.");
		getConfig().addDefault("SetWarp", "&6You have set the warp &7%a &6at your location.");
		getConfig().addDefault("SpeedChange", "&6You have changed your walking speed to &7%a&6.");
		getConfig().addDefault("SpeedChangeSender", "&6You have changed the walking speed for &7%p &6to &7%a&6.");
		getConfig().addDefault("SpeedChangeReceiver", "&6Your waling speed has been changed to &7%a &6by &7%p&6.");
		getConfig().addDefault("TeleportToLocation", "&6You have teleported to &7%a&6.");
		getConfig().addDefault("TeleportInitialize", "&6You will teleport in &7%ts&6.");
		getConfig().addDefault("TeleportTimeRemaing", "&7%ts&6...");
		getConfig().addDefault("TeleportRequestSender", "&6You have sent a request to teleport to &7%p&6.");
		getConfig().addDefault("TeleportRequestReceiver", "&6The player &7%p &6has requested to teleport to you. Do /trdeny or /traccept.");
		getConfig().addDefault("TeleportNoRequest", "&cYou have no teleport request!");
		getConfig().addDefault("TeleportRequestAcceptReceiver", "&6The player &7%p &6has accepted your teleport request.");
		getConfig().addDefault("TeleportRequestDenySender", "&6You have denied the teleport request from &7%p&6.");
		getConfig().addDefault("TeleportRequestDenyReceiver", "&6The player &7%p &6has denied your teleport request.");
		getConfig().addDefault("TeleportRequestTimeout", "&cYou teleport request has timed out.");
		getConfig().addDefault("TempBanDefault", "&cYou have been tempbanned by &7%p&c.");
		getConfig().addDefault("TempBanSender", "&6You have tempbanend &7%p&6.");
		getConfig().addDefault("TempBanNotify", "&6The player &7%p &6has tempbanned the player &7%s &6for &7%r&6.");
		getConfig().addDefault("TempBanInvalidTime", "&cPlease specify a valid time in years, weeks, days, hours, minutes, or seconds.");
		getConfig().addDefault("TimeGet", "&6The time is &7%a&6.");
		getConfig().addDefault("TimeSet", "&6You set the time to &7%a&6.");
		getConfig().addDefault("TimeInvalid", "&cPlease specify a valid time.");
		getConfig().addDefault("UnbanSender", "&6You have unbanned the player &7%p&6.");
		getConfig().addDefault("UnbanNotify", "&6The player &7%p &6has unbanned the player &7%s&6.");
		getConfig().addDefault("UnmuteReceiver", "&6You have been unmuted.");
		getConfig().addDefault("UnmuteSender", "&6You have unmuted the player &7%p&6.");
		getConfig().addDefault("UnmuteNotify", "&6The player &7%p &6has been unmuted.");
		getConfig().addDefault("PlayerNotBanned", "&cThat player is not banned!");
		getConfig().addDefault("Vanish", "&6You have &7%a&6.");
		getConfig().addDefault("VanishSender", "&6You have %a the player &7%p&6.");
		getConfig().addDefault("VanishReceiver", "&6You have been %a by &7%p&6.");
		getConfig().addDefault("WarpExist", "&cThat warp does not exist!");
		getConfig().addDefault("WarnDefault", "&cYou have been warned by &7%p&c.");
		getConfig().addDefault("WarnReceiver", "&6You have been warned because of &7%r&6.");
		getConfig().addDefault("WarnSender", "&6You have warned &7%p");
		getConfig().addDefault("WarnNotify", "&6The player &7%p &6has warned the player &7%s &6for &7%r&6.");
		getConfig().addDefault("WeatherSender", "&6You have set the weather to &7%a&6.");
		getConfig().addDefault("WeatherInvalid", "&cPlease specify a valid weather.");
			
		getConfig().addComment("NoPermission", 
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
		getConfig().addComment("BackNoLocation", " ", "#Per Command Stuff");
	}
}