package io.github.GoldenDeveloper79.TheBasics;

import org.bukkit.OfflinePlayer;

import io.github.GoldenDeveloper79.TheBasics.API.BasicEconomy;

public class Economy implements BasicEconomy
{
	/*
	 * (non-Javadoc)
	 * @see io.github.GoldenDeveloper79.TheBasics.API.BasicEconomy#getBalance(org.bukkit.OfflinePlayer)
	 */
	public double getBalance(OfflinePlayer player)
	{
		return BasicUtils.getConfig(player.getName()).getDouble("Balance");
	}

	/*
	 * (non-Javadoc)
	 * @see io.github.GoldenDeveloper79.TheBasics.API.BasicEconomy#getStartingBalance()
	 */
	public double getStartingBalance() 
	{
		return TheBasics.getGeneralConfig().getDouble("StartingBalance");
	}

	/*
	 * (non-Javadoc)
	 * @see io.github.GoldenDeveloper79.TheBasics.API.BasicEconomy#getMaxLoanAmount()
	 */
	public double getMaxLoanAmount()
	{
		return TheBasics.getGeneralConfig().getDouble("Loaning.MaxAmount");
	}
	
	/*
	 * (non-Javadoc)
	 * @see io.github.GoldenDeveloper79.TheBasics.API.BasicEconomy#setBalance(org.bukkit.OfflinePlayer, double)
	 */
	public void setBalance(OfflinePlayer player, double amount) 
	{
		if(player.isOnline())
		{
			BasicUtils.getData(player.getName()).set("Balance", amount);
		}else
		{
			BasicUtils.getConfig(player.getName()).set("Balance", amount);
			BasicUtils.saveFile(player.getName());
		}
	}

	/*
	 * (non-Javadoc)
	 * @see io.github.GoldenDeveloper79.TheBasics.API.BasicEconomy#resetBalance(org.bukkit.OfflinePlayer)
	 */
	public void resetBalance(OfflinePlayer player) 
	{
		if(player.isOnline())
		{
			BasicUtils.getData(player.getName()).set("Balance", getStartingBalance());
		}else
		{
			BasicUtils.getConfig(player.getName()).set("Balance", getStartingBalance());
			BasicUtils.saveFile(player.getName());
		}
	}

	/*
	 * (non-Javadoc)
	 * @see io.github.GoldenDeveloper79.TheBasics.API.BasicEconomy#depositBalance(org.bukkit.OfflinePlayer, double)
	 */
	public void depositBalance(OfflinePlayer player, double amount)
	{
		if(player.isOnline())
		{
			BasicUtils.getData(player.getName()).set("Balance", getBalance(player) + amount);
		}else
		{
			BasicUtils.getConfig(player.getName()).set("Balance", getBalance(player) + amount);
			BasicUtils.saveFile(player.getName());
		}
	}

	/*
	 * (non-Javadoc)
	 * @see io.github.GoldenDeveloper79.TheBasics.API.BasicEconomy#withdrawBalance(org.bukkit.OfflinePlayer, double)
	 */
	public boolean withdrawBalance(OfflinePlayer player, double amount) 
	{
		if(hasBalance(player, amount))
		{
			if(player.isOnline())
			{
				BasicUtils.getData(player.getName()).set("Balance", getBalance(player) - amount);
			}else
			{
				BasicUtils.getConfig(player.getName()).set("Balance", getBalance(player) - amount);
				BasicUtils.saveFile(player.getName());
			}
			
			return true;
		}else if(canLoan() && amount <= getMaxLoanAmount())
		{
			if(player.isOnline())
			{
				BasicUtils.getData(player.getName()).set("Balance", getBalance(player) - amount);
			}else
			{
				BasicUtils.getConfig(player.getName()).set("Balance", getBalance(player) - amount);
				BasicUtils.saveFile(player.getName());
			}
			
			return true;
		}
		
		return false;
	}

	/*
	 * (non-Javadoc)
	 * @see io.github.GoldenDeveloper79.TheBasics.API.BasicEconomy#hasBalance(org.bukkit.OfflinePlayer, double)
	 */
	public boolean hasBalance(OfflinePlayer player, double amount) 
	{
		if(canLoan())
		{
			if((getBalance(player) - amount) >= getMaxLoanAmount())
			{
				return true;
			}
		}else 
		{
			if((getBalance(player) - amount) >= 0)
			{
				return true;
			}
		}
		
		return false;
	}

	/*
	 * (non-Javadoc)
	 * @see io.github.GoldenDeveloper79.TheBasics.API.BasicEconomy#loanAmount(org.bukkit.OfflinePlayer, double)
	 */
	public boolean loanAmount(OfflinePlayer player, double amount)
	{
		return withdrawBalance(player, amount);
	}
	
	/*
	 * (non-Javadoc)
	 * @see io.github.GoldenDeveloper79.TheBasics.API.BasicEconomy#canLoan()
	 */
	public boolean canLoan()
	{	
		return TheBasics.getGeneralConfig().getBoolean("Loaning.Enabled");
	}
	
}
