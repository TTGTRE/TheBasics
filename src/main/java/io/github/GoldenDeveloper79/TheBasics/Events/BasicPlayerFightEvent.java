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
package io.github.GoldenDeveloper79.TheBasics.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import io.github.GoldenDeveloper79.TheBasics.BasicUtils;
import io.github.GoldenDeveloper79.TheBasics.Player.PlayerData;

public class BasicPlayerFightEvent implements Listener
{
	@EventHandler
	public void onPlayerFight(EntityDamageByEntityEvent event)
	{
		if(event.getEntity() instanceof Player && event.getDamager() instanceof Player)
		{
			PlayerData attacker = BasicUtils.getData((Player) event.getDamager());
			PlayerData defender = BasicUtils.getData((Player) event.getDamager());
			
			attacker.setCombatTagged();
			defender.setCombatTagged();
		}
	}
}
