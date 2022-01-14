package fr.karmaowner.api;

import java.io.IOException;
import java.nio.file.FileSystemException;
import java.util.ArrayList;
import java.util.List;

import fr.karmaowner.erthilia.data.RadioFileReader;
import fr.karmaowner.erthilia.radio.Radio;

public class RadioApi {
	
	public List<Radio> radios = new ArrayList<>();

	private RadioApi()
	{
		
	}
	
	public static RadioApi getRadios() throws IOException
	{
		RadioApi api = new RadioApi();
		String[] radioList = new RadioFileReader("http://localhost/api/erthilia.radio").read().getContent();
		
		for(String radioData : radioList)
		{
			String[] radioInformations = radioData.split("\n");
			if(radioInformations.length != 4)
			{
				throw new FileSystemException("Syntax file error contact administrator");
			}
			Radio radio = new Radio(radioInformations[0], radioInformations[2], radioInformations[1],radioInformations[3]);
			api.radios.add(radio);
		}
		
		return api;
	}
	
}
