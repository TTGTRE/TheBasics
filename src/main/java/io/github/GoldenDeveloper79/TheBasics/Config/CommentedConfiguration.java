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

import io.github.GoldenDeveloper79.TheBasics.TheBasics;

public class CommentedConfiguration extends YamlConfiguration
{	
	private File file;
	private HashMap<String, String> comments;
	
	public CommentedConfiguration(File file)
	{
		this.file = file;
		
		comments = new HashMap<String, String>();
		
		try
		{ 
			this.load(file);
		}catch(IOException | InvalidConfigurationException e)
		{
			TheBasics.getLog().severe("Could not load " + file.getName() + "!");
		}
	}
	
	/*
	 * Saves the config, comments, and file.
	 */
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
	
	/*
	 * Adds a comment to the file.
	 */
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

	/*
	 * Converts the file to a string.
	 */
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

	/*
	 * Converts a string to a file.
	 */
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

	/*
	 * Gets the comments.
	 */
	public HashMap<String, String> getComments()
	{
		return comments;
	}
}
