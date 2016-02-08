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

public class GroupConfig extends ConfigModule
{
	public GroupConfig(String fileName)
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
		getConfig().addDefault("Groups", "");
		getConfig().addComment("Groups", "#There MUST be a default group.");
		
		getConfig().addDefault("Ranking.Method", "TIME");
		getConfig().addDefault("Ranking.Ranks", "");
		getConfig().addComment("Ranking.Method", "#Different methods coming. (Default=TIME)");
		getConfig().addComment("Ranking", " ");
		getConfig().addComment("Ranking.Ranks",
				"#Time in seconds. (New method will include cost in $). To not allow a player to rankup to a group just don't include the rank in this list.",
				"#Example: Member: 50");
	}
}