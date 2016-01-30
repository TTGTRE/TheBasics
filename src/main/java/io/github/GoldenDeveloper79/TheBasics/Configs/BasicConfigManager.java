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
package io.github.GoldenDeveloper79.TheBasics.Configs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;

import io.github.GoldenDeveloper79.TheBasics.TheBasics;

public class BasicConfigManager 
{
	public BasicConfig getNewConfig(String filePath, String[] header) 
	{
		File file = getConfigFile(filePath);

		if(!file.exists())
		{
			prepareFile(filePath);

			if(header != null && header.length != 0)
			{
				setHeader(file, header);
			}
		}

		BasicConfig config = new BasicConfig(getConfigContent(filePath), file, getCommentsNum(file));

		return config;
	}

	public BasicConfig getNewConfig(String filePath) 
	{
		return getNewConfig(filePath, null);
	}
	
	private File getConfigFile(String file)
	{
		if(file.isEmpty() || file == null)
		{
			return null;
		}

		File configFile = new File(TheBasics.getMainDir(), file);

		return configFile;
	}

	public void prepareFile(String fileName)
	{
		File file = getConfigFile(fileName);

		if(file.exists()) 
		{
			return;
		}

		try
		{
			file.getParentFile().mkdirs();
			file.createNewFile();

			copyResource(TheBasics.getPlugin().getResource(fileName), file);
		}catch(IOException e) {}
	}

	public void setHeader(File file, String[] header)
	{
		if(!file.exists()) 
		{
			return;
		}

		try 
		{
			String currentLine;
			StringBuilder config = new StringBuilder("");
			BufferedReader reader = new BufferedReader(new FileReader(file));

			while((currentLine = reader.readLine()) != null)
			{
				config.append(currentLine + "\n");
			}

			reader.close();
			config.append("# +----------------------------------------------------+ #\n");

			for(String line : header)
			{

				if(line.length() > 50) 
				{
					continue;
				}

				int lenght = (50 - line.length()) / 2;
				StringBuilder finalLine = new StringBuilder(line);

				for(int i = 0; i < lenght; i++)
				{
					finalLine.append(" ");
					finalLine.reverse();
					finalLine.append(" ");
					finalLine.reverse();
				}

				if(line.length() % 2 != 0) 
				{
					finalLine.append(" ");
				}

				config.append("# < " + finalLine.toString() + " > #\n");

			}

			config.append("# +----------------------------------------------------+ #");

			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write(prepareConfigString(config.toString()));
			writer.flush();
			writer.close();

		}catch(IOException e) {}
	}

	public InputStream getConfigContent(File file) 
	{
		if(!file.exists())
		{
			return null;
		}

		try 
		{
			int commentNum = 0;

			String addLine;
			String currentLine;
			String pluginName = "TheBasics";

			StringBuilder whole = new StringBuilder("");
			BufferedReader reader = new BufferedReader(new FileReader(file));

			while((currentLine = reader.readLine()) != null)
			{

				if(currentLine.startsWith("#")) 
				{
					addLine = currentLine.replaceFirst("#", pluginName + "_COMMENT_" + commentNum + ":");
					whole.append(addLine + "\n");
					commentNum++;

				}else
				{
					whole.append(currentLine + "\n");
				}

			}

			String config = whole.toString();
			InputStream configStream = new ByteArrayInputStream(config.getBytes(Charset.forName("UTF-8")));

			reader.close();
			return configStream;

		}catch(IOException e) 
		{
			return null;
		}
	}
	
	private int getCommentsNum(File file)
	{
		if(!file.exists())
		{
			return 0;
		}

		try 
		{
			int comments = 0;
			String currentLine;

			BufferedReader reader = new BufferedReader(new FileReader(file));

			while((currentLine = reader.readLine()) != null)
			{
				if(currentLine.startsWith("#")) 
				{
					comments++;
				}
			}

			reader.close();
			return comments;
		}catch(IOException e) 
		{
			return 0;
		}

	}

	public InputStream getConfigContent(String filePath) 
	{
		return getConfigContent(getConfigFile(filePath));
	}

	private String prepareConfigString(String configString)
	{

		int lastLine = 0;
		int headerLine = 0;

		String[] lines = configString.split("\n");
		StringBuilder config = new StringBuilder("");

		for(String line : lines) 
		{

			if(line.startsWith("TheBasics_COMMENT")) 
			{
				String comment = "#" + line.trim().substring(line.indexOf(":") + 1);

				if(comment.startsWith("# +-")) 
				{

					/*
					 * If header line = 0 then it is
					 * header start, if it's equal
					 * to 1 it's the end of header
					 */

					if(headerLine == 0)
					{
						config.append(comment + "\n");

						lastLine = 0;
						headerLine = 1;

					}else if(headerLine == 1)
					{
						config.append(comment + "\n\n");

						lastLine = 0;
						headerLine = 0;

					}

				}else
				{
					String normalComment;

					if(comment.startsWith("# ' "))
					{
						normalComment = comment.substring(0, comment.length() - 1).replaceFirst("# ' ", "# ");
					}else
					{
						normalComment = comment;
					}

					if(lastLine == 0) 
					{
						config.append(normalComment + "\n");
					}else if(lastLine == 1) 
					{
						config.append("\n" + normalComment + "\n");
					}

					lastLine = 0;
				}

			}else 
			{
				config.append(line + "\n");
				lastLine = 1;
			}

		}

		return config.toString();
	}


	public void saveConfig(String configString, File file) 
	{
		String configuration = prepareConfigString(configString);

		try
		{
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write(configuration);
			writer.flush();
			writer.close();
		}catch(IOException e) {}
	}

	private void copyResource(InputStream resource, File file) 
	{
		try 
		{
			OutputStream out = new FileOutputStream(file);

			int lenght;
			byte[] buf = new byte[1024];

			while((lenght = resource.read(buf)) > 0)
			{
				out.write(buf, 0, lenght);
			}

			out.close();
			resource.close();
		}catch(Exception e) {}
	}
}