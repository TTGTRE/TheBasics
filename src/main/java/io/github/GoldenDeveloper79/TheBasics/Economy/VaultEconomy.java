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
package io.github.GoldenDeveloper79.TheBasics.Economy;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.ServicePriority;

import io.github.GoldenDeveloper79.TheBasics.BasicUtils;
import io.github.GoldenDeveloper79.TheBasics.TheBasics;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import net.milkbowl.vault.economy.EconomyResponse.ResponseType;

public class VaultEconomy implements Economy
{
	public VaultEconomy()
	{
		Bukkit.getServicesManager().register(Economy.class, this, Bukkit.getPluginManager().getPlugin("Vault"), ServicePriority.Highest);	
	}
	
	public EconomyResponse bankBalance(String player)
	{
		return new EconomyResponse(0, 0, ResponseType.NOT_IMPLEMENTED, "[TheBasics] Does not support bank accounts!");
	}

	public EconomyResponse bankDeposit(String arg0, double arg1)
	{
		return new EconomyResponse(0, 0, ResponseType.NOT_IMPLEMENTED, "[TheBasics] Does not support bank accounts!");
	}

	public EconomyResponse bankHas(String arg0, double arg1)
	{
		return new EconomyResponse(0, 0, ResponseType.NOT_IMPLEMENTED, "[TheBasics] Does not support bank accounts!");
	}

	public EconomyResponse bankWithdraw(String arg0, double arg1)
	{
		return new EconomyResponse(0, 0, ResponseType.NOT_IMPLEMENTED, "[TheBasics] Does not support bank accounts!");
	}

	public EconomyResponse createBank(String arg0, String arg1)
	{
		return new EconomyResponse(0, 0, ResponseType.NOT_IMPLEMENTED, "[TheBasics] Does not support bank accounts!");
	}

	public EconomyResponse createBank(String arg0, OfflinePlayer arg1)
	{
		return new EconomyResponse(0, 0, ResponseType.NOT_IMPLEMENTED, "[TheBasics] Does not support bank accounts!");
	}

	public boolean createPlayerAccount(String player)
	{
		FileConfiguration config = BasicUtils.getConfig(player);
		
		if(config != null)
		{
			config.set("Balance", TheBasics.getEconomy().getStartingBalance());
			return true;
		}else
		{
			return false;
		}
	}

	public boolean createPlayerAccount(OfflinePlayer player)
	{
		return createPlayerAccount(player.getName());
	}

	public boolean createPlayerAccount(String player, String world)
	{
		return createPlayerAccount(player);
	}

	public boolean createPlayerAccount(OfflinePlayer player, String world)
	{
		return createPlayerAccount(player.getName());
	}

	public String currencyNamePlural()
	{
		return "$";
	}

	public String currencyNameSingular()
	{
		return "$";
	}
	
	public EconomyResponse deleteBank(String arg0)
	{
		return new EconomyResponse(0, 0, ResponseType.NOT_IMPLEMENTED, "[TheBasics] Does not support bank accounts!");
	}

	public EconomyResponse depositPlayer(OfflinePlayer player, double amount)
	{
		double balance = 0;
		
		if(player != null)
		{
			balance = TheBasics.getEconomy().getBalance(player);
			TheBasics.getEconomy().depositBalance(player, amount);
			
			return new EconomyResponse(amount, balance, ResponseType.SUCCESS, null);
		}else
		{
			return new EconomyResponse(0, 0, ResponseType.FAILURE, "[TheBasics] That player has never played on server before!");
		}
	}
	
	@SuppressWarnings("deprecation")
	public EconomyResponse depositPlayer(String player, double amount)
	{
		return depositPlayer(Bukkit.getOfflinePlayer(player), amount);
	}

	@SuppressWarnings("deprecation")
	public EconomyResponse depositPlayer(String player, String world, double amount)
	{
		return depositPlayer(Bukkit.getOfflinePlayer(player), amount);
	}

	public EconomyResponse depositPlayer(OfflinePlayer player, String world, double amount)
	{
		return depositPlayer(player, amount);
	}

	public String format(double amount)
	{
		return TheBasics.getEconomy().format(amount);
	}

	public int fractionalDigits()
	{
		return -1;
	}

	public double getBalance(OfflinePlayer player)
	{
		return TheBasics.getEconomy().getBalance(player);
	}
	
	@SuppressWarnings("deprecation")
	public double getBalance(String player)
	{
		return getBalance(Bukkit.getOfflinePlayer(player));
	}


	@SuppressWarnings("deprecation")
	public double getBalance(String player, String world)
	{
		return getBalance(Bukkit.getOfflinePlayer(player));
	}

	public double getBalance(OfflinePlayer player, String world)
	{
		return getBalance(player);
	}

	public List<String> getBanks()
	{
		return new ArrayList<String>();
	}

	public String getName()
	{
		return "TheBasics";
	}

	public boolean has(OfflinePlayer player, double amount)
	{
		return TheBasics.getEconomy().hasBalance(player, amount);
	}

	@SuppressWarnings("deprecation")
	public boolean has(String player, double amount)
	{
		return has(Bukkit.getOfflinePlayer(player), amount);
	}

	@SuppressWarnings("deprecation")
	public boolean has(String player, String world, double amount)
	{
		return has(Bukkit.getOfflinePlayer(player), amount);
	}
	
	public boolean has(OfflinePlayer player, String world, double amount)
	{
		return has(player, amount);
	}

	public boolean hasAccount(String player)
	{
		return BasicUtils.getConfig(player) != null;
	}
	
	public boolean hasAccount(OfflinePlayer player)
	{
		return hasAccount(player.getName());
	}
	
	public boolean hasAccount(String player, String world)
	{
		return hasAccount(player);
	}

	public boolean hasAccount(OfflinePlayer player, String world)
	{
		return hasAccount(player.getName());
	}

	public boolean hasBankSupport()
	{
		return false;
	}

	public EconomyResponse isBankMember(String arg0, String arg1)
	{
		return new EconomyResponse(0, 0, ResponseType.NOT_IMPLEMENTED, "[TheBasics] Does not support bank accounts!");
	}

	public EconomyResponse isBankMember(String arg0, OfflinePlayer arg1)
	{
		return new EconomyResponse(0, 0, ResponseType.NOT_IMPLEMENTED, "[TheBasics] Does not support bank accounts!");
	}

	public EconomyResponse isBankOwner(String arg0, String arg1)
	{
		return new EconomyResponse(0, 0, ResponseType.NOT_IMPLEMENTED, "[TheBasics] Does not support bank accounts!");
	}

	public EconomyResponse isBankOwner(String arg0, OfflinePlayer arg1)
	{
		return new EconomyResponse(0, 0, ResponseType.NOT_IMPLEMENTED, "[TheBasics] Does not support bank accounts!");
	}

	public boolean isEnabled()
	{
		return true;
	}

	public EconomyResponse withdrawPlayer(OfflinePlayer player, double amount)
	{
		double balance = 0;
		
		if(hasAccount(player))
		{
			if(has(player, amount))
			{
				balance = TheBasics.getEconomy().getBalance(player);
				
				TheBasics.getEconomy().withdrawBalance(player, amount);
				
				return new EconomyResponse(amount, balance, ResponseType.SUCCESS, null);
			}else
			{
				return new EconomyResponse(0, balance, ResponseType.FAILURE, "[TheBasics] That player lacks the funds!");
			}
		}else
		{
			return new EconomyResponse(0, balance, ResponseType.FAILURE, "[TheBasics] Has never played on the server before!");
		}
	}
	
	@SuppressWarnings("deprecation")
	public EconomyResponse withdrawPlayer(String player, double amount)
	{
		return withdrawPlayer(Bukkit.getOfflinePlayer(player), amount);
	}

	@SuppressWarnings("deprecation")
	public EconomyResponse withdrawPlayer(String player, String world, double amount)
	{
		return withdrawPlayer(Bukkit.getOfflinePlayer(player), amount);
	}

	public EconomyResponse withdrawPlayer(OfflinePlayer player, String world, double amount)
	{
		return withdrawPlayer(player, amount);
	}
}
