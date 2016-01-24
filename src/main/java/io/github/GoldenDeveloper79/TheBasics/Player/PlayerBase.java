package io.github.GoldenDeveloper79.TheBasics.Player;

import java.io.File;

import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import io.github.GoldenDeveloper79.TheBasics.Registery;
import io.github.GoldenDeveloper79.TheBasics.TheBasics;
import io.github.GoldenDeveloper79.TheBasics.API.BasicEconomy;
import io.github.GoldenDeveloper79.TheBasics.API.BasicPermissions;
import io.github.GoldenDeveloper79.TheBasics.Modules.ConfigModule;
import io.github.GoldenDeveloper79.TheBasics.Modules.GroupModule;

public abstract class PlayerBase extends ConfigModule implements BasicEconomy, BasicPermissions
{
	private Player player;
	
	public PlayerBase(Player player)
	{
		super(new File("plugins/TheBasics/Players/" + player.getUniqueId().toString() + ".yml"));
		
		this.player = player;
	}
	
	public boolean homeExist(String name)
	{
		if(contains("Home." + name))
		{
			return true;
		}
		
		return false;
	}
	
	public boolean createHome(String name)
	{
		if(!homeExist(name))
		{
			Location loc = player.getLocation();
			
			set("Home."+ name + ".World", loc.getWorld().getName());
			set("Home."+ name + ".X", loc.getX());
			set("Home."+ name + ".Y", loc.getY());
			set("Home."+ name + ".Z", loc.getZ());
			set("Home."+ name + ".Yaw", loc.getYaw());
			set("Home."+ name + ".Pitch", loc.getPitch());
			
			return true;
		}
		
		return false;
	}
	
	public double getBalance(OfflinePlayer player)
	{
		return getDouble("Balance");
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
		set("Balance", amount);
	}

	public void resetBalance(OfflinePlayer player) 
	{
		set("Balance", getStartingBalance());
	}

	public void depositBalance(OfflinePlayer player, double amount)
	{
		set("Balance", getBalance(player) + amount);
	}

	public boolean withdrawBalance(OfflinePlayer player, double amount) 
	{
		if(hasBalance(player, amount))
		{
			set("Balance", getBalance(player) - amount);
			
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
	
	public GroupModule getGroup(String groupName) 
	{
		if(Registery.groups.containsKey(groupName))
		{
			return Registery.groups.get(groupName);
		}
		
		return null;
	}

	public GroupModule getPlayerGroup(Player player)
	{
		return getGroup(getString("Group"));
	}
	
	public GroupModule getDefaultGroup()
	{
		for(GroupModule group : Registery.groups.values())
		{
			if(group.isDefault())
			{
				return group;
			}
		}
		
		return null;
	}
	
	public boolean addPlayerToGroup(Player player, GroupModule group) 
	{
		if(!playerInGroup(player, group))
		{
			group.getPlayers().add(player.getName());
			
			return true;
		}
		
		return false;
	}

	public boolean removePlayerFromGroup(Player player, GroupModule group) 
	{
		if(playerInGroup(player, group))
		{
			group.getPlayers().remove(player.getName());
			
			return true;
		}
		
		return false;
	}

	public boolean playerInGroup(Player player, GroupModule group) 
	{
		return group.getPlayers().contains(player.getName());
	}
}
