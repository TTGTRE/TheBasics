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
package io.github.GoldenDeveloper79.TheBasics.Enums;

import org.bukkit.enchantments.Enchantment;

//Since bukkit's naming is not the conventional naming.
public enum EnchantType
{
	PROTECTION(Enchantment.PROTECTION_ENVIRONMENTAL),
	FIRE_PROTECTION(Enchantment.PROTECTION_FIRE),
	FEATHER_FALLING(Enchantment.PROTECTION_FALL),
	BLAST_PROTECTION(Enchantment.PROTECTION_EXPLOSIONS),
	PROJECTILE_PROTECTION(Enchantment.PROTECTION_PROJECTILE),
	RESPIRATION(Enchantment.OXYGEN),
	AQUA_INFINITY(Enchantment.WATER_WORKER),
	THORNS(Enchantment.THORNS),
	DEPTH_STRIDER(Enchantment.DEPTH_STRIDER),
	SHARPNESS(Enchantment.DAMAGE_ALL),
	SMITE(Enchantment.DAMAGE_UNDEAD),
	BANE_OF_ARTHROPODS(Enchantment.DAMAGE_ARTHROPODS),
	KNOCKBACK(Enchantment.KNOCKBACK),
	FIRE_ASPECT(Enchantment.FIRE_ASPECT),
	LOOTING(Enchantment.LOOT_BONUS_MOBS),
	EFFICIENCY(Enchantment.DIG_SPEED),
	SILK_TOUCH(Enchantment.SILK_TOUCH),
	UNBREAKING(Enchantment.DURABILITY),
	FORTUNE(Enchantment.LOOT_BONUS_BLOCKS),
	POWER(Enchantment.ARROW_DAMAGE),
	PUNCH(Enchantment.ARROW_KNOCKBACK),
	FLAME(Enchantment.ARROW_FIRE),
	INFINITY(Enchantment.ARROW_INFINITE),
	LUCK_OF_THE_SEA(Enchantment.LUCK),
	LURE(Enchantment.LURE);
	
	private Enchantment enchantment;
	
	EnchantType(Enchantment enchantment)
	{
		this.enchantment = enchantment;
	}
	
	public Enchantment getEnchantment()
	{
		return enchantment;
	}
}
