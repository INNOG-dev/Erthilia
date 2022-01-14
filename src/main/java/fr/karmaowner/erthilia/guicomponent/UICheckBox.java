package fr.karmaowner.erthilia.guicomponent;

import org.lwjgl.opengl.GL11;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


@SideOnly(Side.CLIENT)
public class UICheckBox extends UIButton {
  
    public boolean checked = false;

    public UICheckBox(Type type, boolean defaultValue) {
    	super(type,"checkbox",getTexture(),null,false,null);
    	this.checked = defaultValue;
    }
   
    public UICheckBox(Type type) {
    	super(type,"checkbox",getTexture(),null,false,null);
    }

    @Override
    public void draw(int x, int y, float partialTicks) 
    {
    	GL11.glColor4f(1f, 1f, 1f, 1f);
    	if(!checked) 
    	{
    		super.texture.texture = getTexture();
        }
        else {
    		super.texture.texture = getCheckedTexture();
        }
    
    	super.draw(x, y,partialTicks);
    	GL11.glColor4f(1f, 1f, 1f, 1f);
    }

    
    public GraphicObject setPosition(int x, int y, int width, int height)
    {
    	super.setPosition(x, y, width, height);
    	return this;
    }
    
    @Override
    public boolean onLeftClick(int x, int y)
    {
    	if(isClicked(x,y))
    	{
    		checked = !checked;
        	return true;
    	}
    	return false;
    }
    
    
    public static ResourceLocation getTexture()
    {
    	return new ResourceLocation("craftyourliferp","gui/checkbox_false.png");
    }
    
    public static ResourceLocation getCheckedTexture()
    {
    	return new ResourceLocation("craftyourliferp","gui/checkbox_true.png");
    }

}

