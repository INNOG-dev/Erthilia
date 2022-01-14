package fr.karmaowner.guireader;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import fr.karmaowner.erthilia.guicomponent.GraphicObject;
import fr.karmaowner.erthilia.guicomponent.UIColor;
import fr.karmaowner.erthilia.guicomponent.UIImage;
import fr.karmaowner.erthilia.guicomponent.UIText;
import fr.karmaowner.erthilia.utils.StringUtils;
import net.minecraft.util.ResourceLocation;

public class Reader {
	
	private String fileUrl;
	
	private String fileData = "";
	
	public Reader(String url)
	{
		this.fileUrl = url;
		try 
		{
			readFile();
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	private void readFile() throws IOException
	{
		URL url = new URL(fileUrl);
		BufferedReader read = new BufferedReader(new InputStreamReader(url.openStream()));
		String i = "";
		while ((i = read.readLine()) != null) fileData += i + "\n";
		read.close();
		
		getComponents();
	}
	

	public List<GraphicObject> getComponents()
	{
		List<GraphicObject> objects = new ArrayList<GraphicObject> ();
		for(String str : fileData.split("\\n"))
		{
			String balise = StringUtils.extractString(str, '[', ']');
			String dataAfterBalise = StringUtils.extractString(str, ']', '[');
			dataAfterBalise = dataAfterBalise.replace("[", "").replace("]", "");
			balise = balise.replace("[", "").replace("]", "");
			objects.add(getComponent(balise,dataAfterBalise));
		}
		return objects;
	}
	
	private GraphicObject getComponent(String balise,String dataAfterBalise)
	{
		String[] baliseData = balise.split(" ");
		String options = baliseData[1].substring(8);
		String[] optionsSplitted = options.split(",");
		GraphicObject object = setOptionsToObject(baliseData[0],optionsSplitted,dataAfterBalise);
		return object;
	}
	
	private GraphicObject setOptionsToObject(String balise, String[] options, String dataAfterBalise)
	{
		if(balise.equalsIgnoreCase("UIImage"))
		{
			UIImage image = new UIImage();
			for(String option : options)
			{
				String[] optionData = option.split("=");
				if(optionData[0].equalsIgnoreCase("size"))
				{
					int size = Integer.parseInt(optionData[1]);
					
					image.setPosition(0, 0, size, size);
					
				}
				image.setTexture(new ResourceLocation(dataAfterBalise));
			}
			return image;
		}
		else if(balise.equalsIgnoreCase("UIText"))
		{
			UIText text = new UIText();
			for(String option : options)
			{
				String[] optionData = option.split("=");
				text.setText(dataAfterBalise);
				if(optionData[0].equalsIgnoreCase("size"))
				{
					int size = Integer.parseInt(optionData[1]);
					text.setSize(size);
				}
				else if(optionData[0].equalsIgnoreCase("color"))
				{
					if(optionData[1].startsWith("#"))
					{
						text.setColor(new UIColor(optionData[1]));
					}
					else
					{
						int rgb = Integer.parseInt(optionData[1]);
						text.setColor(new UIColor(new Color(rgb)));
					}
				}
			}
			return text;
		}
		return null;
	}
	
	
	
}
