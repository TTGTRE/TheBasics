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
