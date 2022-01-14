package fr.karmaowner.erthilia.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.util.ResourceLocation;

public abstract class ErthiliaModelBase extends ModelBase {

	public abstract void render();
	
	public abstract ResourceLocation getTexture();
	
	public float field_74209_t;
	
	public float field_74208_u;
	
}
