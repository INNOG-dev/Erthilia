package fr.karmaowner.erthilia.animations;

import fr.karmaowner.erthilia.model.ModelDragon;
import fr.karmaowner.erthilia.model.ModelFireAtronach;
import fr.karmaowner.erthilia.utils.MathsUtils;
import net.minecraft.client.model.ModelBase;

public class AtronachAnimation {
	
	public enum Animation
	{
		SCREAM
	};
	
	
	private ModelFireAtronach modelAtronach;
	
	private int currentAnimState = 0;
	
	public boolean animationIsPlaying = false;
	
	
	public AtronachAnimation(ModelBase model)
	{
		this.modelAtronach = (ModelFireAtronach)model;
	}
	
	public void playAnimation(Animation anim, float elapsedTime, float durationInSeconds)
	{
		if(!animationIsPlaying)
		{
			animationIsPlaying = true;
			currentAnimState = 0;
		}
		
		if(anim == Animation.SCREAM)
    	{
			if(currentAnimState == 0)
			{
				modelAtronach.head.rotateAngleX = MathsUtils.Lerp(modelAtronach.head.rotateAngleX,(float) Math.toRadians(-40), elapsedTime / (durationInSeconds / 1.5f));
			}
    	}
	
	}

	
}
