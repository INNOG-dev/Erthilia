package fr.karmaowner.erthilia.radio;

public class Radio {

	
	private String title;
	
	private String currentPlayedTitle;
	
	private String fluxUrl;
	
	private String icoUrl;
	
	@SuppressWarnings("unused")
	private String parameters;
	
	public Radio(String title, String fluxUrl, String icoUrl, String parameters)
	{
		this.title = title;
		this.fluxUrl = fluxUrl;
		this.icoUrl = icoUrl;
		this.parameters = parameters;
	}
	
	public String getTittle()
	{
		return this.title;
	}
	
	public void setTittle(String title)
	{
		this.title = title;
	}
	
	public void setCurrentPlayedTitle(String title)
	{
		this.currentPlayedTitle = title;
	}
	
	public String getCurrentPlayedTitle()
	{
		return this.currentPlayedTitle;
	}
	
	public String getFlux()
	{
		return this.fluxUrl;
	}
	
	public String getIcoUrl()
	{
		return this.icoUrl;
	}
	
}
