package fr.karmaowner.erthilia.utils;

import java.awt.Color;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.lwjgl.opengl.GL11;

public class ColorUtils {

	
	public static int[] convertHexToRGB(String hex)
	{
	    hex = hex.replace("#", "");
	    if(hex.length() == 6)
	    {
	    	int[] rgb = new int[3];
		    rgb[0] = Integer.valueOf(hex.substring(0, 2), 16);
		    rgb[1] = Integer.valueOf(hex.substring(2, 4), 16);
		    rgb[2] = Integer.valueOf(hex.substring(4, 6), 16);
		    return rgb;
	    }
	    else if(hex.length() == 8)
	    {
	    	int[] rgb = new int[4];
		    rgb[0] = Integer.valueOf(hex.substring(0, 2), 16);
		    rgb[1] = Integer.valueOf(hex.substring(2, 4), 16);
		    rgb[2] = Integer.valueOf(hex.substring(4, 6), 16);
		    rgb[3] = Integer.valueOf(hex.substring(6, 8), 16);
		    return rgb;
	    }
	    return null;
	}
	
	public static String convertRGBToHex(int r, int g, int b)
	{
		return String.format("#%02x%02x%02x", r, g, b);  
	}
	
	public static int colorToInt(Color color)
	{
		return color.getRGB();
	}
	
	public static Color intToColor(int color)
	{
		return new Color(color);
	}
	
    public static void setGlColor(int color) {
        float alpha = (color >> 24 & 0xFF) / 255.0f;
        float red = (color >> 16 & 0xFF) / 255.0f;
        float green = (color >> 8 & 0xFF) / 255.0f;
        float blue = (color & 0xFF) / 255.0f;

        GL11.glColor4f(red, green, blue, alpha);
    }
	
    public static boolean isHexColor(String input) {
    	String HEX_PATTERN = "^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$";
    	Pattern pattern = Pattern.compile(HEX_PATTERN);
    	Matcher matcher = pattern.matcher(input);
    	
    	return matcher.matches();
    }

	
}
