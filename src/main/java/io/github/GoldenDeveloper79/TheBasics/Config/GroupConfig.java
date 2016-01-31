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
	private CommentedConfiguration config;
	
	public GroupConfig(String fileName)
	{
		super(fileName);
		
		config = getConfig();
		
		loadDefaults();
		load();
		config.options().copyDefaults(true);
		config.save();
		load();
	}

	public void loadDefaults()
	{
		config.addDefault("Groups", "");
		config.addComment("Groups", "#There MUST be a default group.");
		
		config.addDefault("Ranking.Method", "TIME");
		config.addDefault("Ranking.Ranks", "");
		config.addComment("Ranking.Method", "#Different methods coming. (Default=TIME)");
		config.addComment("Ranking", " ");
		config.addComment("Ranking.Ranks",
				"#Time in seconds. (New method will include cost in $). To not allow a player to rankup to a group just don't include the rank in this list.",
				"#Example: Member: 50");
	}
}