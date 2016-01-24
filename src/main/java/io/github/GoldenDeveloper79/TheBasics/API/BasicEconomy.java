package io.github.GoldenDeveloper79.TheBasics.API;

import org.bukkit.OfflinePlayer;

public interface BasicEconomy 
{
	/*
	 * Gets the balance of a player.
	 */
	double getBalance(OfflinePlayer player);
	
	/*
	 * Gets the starting balance of the server.
	 */
	double getStartingBalance();
	
	/*
	 * Gets the max loan amount of the server.
	 */
	double getMaxLoanAmount();
	
	/*
	 * Sets the balance of a player.
	 */
	void setBalance(OfflinePlayer player, double amount);
	
	/*
	 * Resets the balance of a player.
	 */
	void resetBalance(OfflinePlayer player);
	
	
	/*
	 * Deposits an amount from the player.
	 */
	void depositBalance(OfflinePlayer player, double amount);
	
	/*
	 * Withdraws an amount from the player.
	 * 
	 * Returns if successful.
	 */
	boolean withdrawBalance(OfflinePlayer player, double amount);
	
	/*
	 * Checks if a player has a certain balance.
	 */
	boolean hasBalance(OfflinePlayer player, double amount);
	
	/*
	 * Loans money from the server bank.
	 */
	boolean  loanAmount(OfflinePlayer player, double amount);
	
	/*
	 * If players are allowed to loan.
	 */
	boolean canLoan();
}
