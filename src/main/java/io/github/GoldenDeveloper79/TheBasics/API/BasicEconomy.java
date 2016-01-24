/*******************************************************************************
 * The MIT License (MIT)
 *
 * Copyright (c) 2016 Levi Pawlak (GoldenDeveloper79)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.on
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
