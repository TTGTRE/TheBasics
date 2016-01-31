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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

public class CommentedConfiguration extends YamlConfiguration
{
	private HashMap<String, String> comments;
	private File file;

	public CommentedConfiguration(File file)
	{
		this.file = file;
		
		comments = new HashMap<>();
		
		try
		{ 
			this.load(file);
		}catch(IOException | InvalidConfigurationException e)
		{
			e.printStackTrace();
		}
	}
	
	/*
	 * Use this for local files only, since we don't need to call the save function.
	 */
	public CommentedConfiguration(Reader reader)
	{
		comments = new HashMap<>();
		
		try
		{
			this.load(reader);
		}catch(IOException | InvalidConfigurationException e)
		{
			e.printStackTrace();
		}
	}

	public boolean save()
	{
		boolean saved = true;

		try
		{
			save(file);
		}catch(IOException e)
		{
			saved = false;
		}

		if(!comments.isEmpty() && saved)
		{
			String[] yamlContents = convertFileToString(file).split("[" + System.getProperty("line.separator") + "]");

			String newContents = "";
			String currentPath = "";
			boolean commentedPath = false;
			boolean node;
			int depth = 0;

			for(String line : yamlContents)
			{
				if(line.contains(": ") || line.length() > 1 && line.charAt(line.length() - 1) == ':')
				{
					commentedPath = false;
					node = true;
					
					int index = line.indexOf(": ");
					
					if(index < 0)
					{
						index = line.length() - 1;
					}

					if(currentPath.isEmpty())
					{
						currentPath = line.substring(0, index);
					}else
					{
						int whiteSpace = 0;
						
						for(int n = 0; n < line.length(); n++)
						{
							if(line.charAt(n) == ' ')
							{
								whiteSpace++;
							} else
							{
								break;
							}
						}
						
						if(whiteSpace / 2 > depth)
						{
							currentPath += "." + line.substring(whiteSpace, index);
							depth++;
						}else if(whiteSpace / 2 < depth)
						{
							int newDepth = whiteSpace / 2;
							
							for(int i = 0; i < depth - newDepth; i++)
							{
								currentPath = currentPath.replace(currentPath.substring(currentPath.lastIndexOf('.')),
										"");
							}
							
							int lastIndex = currentPath.lastIndexOf('.');
							
							if(lastIndex < 0)
							{
								currentPath = "";
							} else
							{
								currentPath = currentPath.replace(currentPath.substring(currentPath.lastIndexOf('.')),
										"");
								currentPath += ".";
							}
				
							currentPath += line.substring(whiteSpace, index);
			
							depth = newDepth;
						} else
						{
							int lastIndex = currentPath.lastIndexOf('.');
							
							if(lastIndex < 0)
							{
								currentPath = "";
							}else
							{
								currentPath = currentPath.replace(currentPath.substring(currentPath.lastIndexOf('.')),
										"");
								currentPath += ".";
							}
							
							currentPath += line.substring(whiteSpace, index);
						}
					}
				}else
				{
					node = false;
				}
				
				if(node)
				{
					String comment = null;
					
					if(!commentedPath)
					{
						comment = comments.get(currentPath);
					}
					
					if(comment != null)
					{
						line = comment + System.getProperty("line.separator") + line + System.getProperty("line.separator");
						commentedPath = true;
					}else
					{
						
						line += System.getProperty("line.separator");
					}
				}
				
				newContents += line + (!node ? System.getProperty("line.separator") : "");
			}

			while(newContents.startsWith(System.getProperty("line.separator")))
			{
				newContents = newContents.replaceFirst(System.getProperty("line.separator"), "");
			}

			if(!stringToFile(newContents, file))
			{
				saved = false;
			}
		}
		
		return saved;
	}

	public String getComment(String path)
	{
		return comments.get(path);
	}
	
	public void addComment(String path, String... commentLines)
	{
		StringBuilder commentstring = new StringBuilder();
		String leadingSpaces = "";
		
		for(int n = 0; n < path.length(); n++)
		{
			if(path.charAt(n) == '.')
			{
				leadingSpaces += "  ";
			}
		}
		
		for(String line : commentLines)
		{
			if(!line.isEmpty())
			{
				line = leadingSpaces + line;
			}else
			{
				line = " ";
			}
			
			if(commentstring.length() > 0)
			{
				commentstring.append("\r\n");
			}
			
			commentstring.append(line);
		}
		
		comments.put(path, commentstring.toString());
	}

	public String convertFileToString(File file)
	{
		if(file != null && file.exists() && file.canRead() && !file.isDirectory())
		{
			char[] buffer = new char[1024];
			String s = "";
			
			try
			{
				Writer writer = new StringWriter(); 
				Reader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
				
				int n;
				
				while((n = reader.read(buffer)) != -1)
				{
					writer.write(buffer, 0, n);
				}
				
				s = writer.toString();
				
				writer.close();
				reader.close();
			}catch(IOException e)
			{
				return "";
			}
			
			return s;
		}
		
		return "";
	}

	public boolean stringToFile(String source, File file)
	{
		try(OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(file), "UTF-8"))
		{
			out.write(source);
			return true;
		}catch(IOException e)
		{
			return false;
		}
	}

	public HashMap<String, String> getComments()
	{
		return comments;
	}
}