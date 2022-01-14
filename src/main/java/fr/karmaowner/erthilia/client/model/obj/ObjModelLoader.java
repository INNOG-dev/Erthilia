package fr.karmaowner.erthilia.client.model.obj;

import fr.karmaowner.client.model.IModelCustom;
import fr.karmaowner.client.model.IModelCustomLoader;
import fr.karmaowner.client.model.ModelFormatException;
import net.minecraft.util.ResourceLocation;

public class ObjModelLoader implements IModelCustomLoader
{

    @Override
    public String getType()
    {
        return "OBJ model";
    }

    private static final String[] types = { "obj" };
    @Override
    public String[] getSuffixes()
    {
        return types;
    }

    @Override
    public IModelCustom loadInstance(ResourceLocation resource) throws ModelFormatException
    {
        return new WavefrontObject(resource);
    }
	
}
