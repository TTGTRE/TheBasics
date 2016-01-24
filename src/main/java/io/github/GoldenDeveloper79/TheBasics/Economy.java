package io.github.GoldenDeveloper79.TheBasics;

import org.bukkit.OfflinePlayer;

import io.github.GoldenDeveloper79.TheBasics.API.BasicEconomy;

public class Economy implements BasicEconomy
{
	public double getBalance(OfflinePlayer player)
	{
		return BasicUtils.getConfig(player.getName()).getDouble("Balance");
	}

	public double getStartingBalance() 
	{
		return TheBasics.getGeneralConfig().getDouble("StartingBalance");
	}

	public double getMaxLoanAmount()
	{
		return TheBasics.getGeneralConfig().getDouble("Loaning.MaxAmount");
	}
	
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

	public boolean withdrawBalance(OfflinePlayer player, double amount) 
	{
		if(hasBalance(player, amount) || canLoan())
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

	public boolean loanAmount(OfflinePlayer player, double amount)
	{
		return withdrawBalance(player, amount);
	}
	
	public boolean canLoan()
	{	
		return TheBasics.getGeneralConfig().getBoolean("Loaning.Enabled");
	}
	
}
