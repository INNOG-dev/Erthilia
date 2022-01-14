package fr.karmaowner.erthilia.animations;

import fr.karmaowner.erthilia.model.ModelDragon;
import fr.karmaowner.erthilia.utils.MathsUtils;
import net.minecraft.client.model.ModelBase;

public class DragonAnimation {
	
	public enum Animation
	{
		SCREAM
	};
	
	
	private ModelDragon modelDragon;
	
	private int currentAnimState = 0;
	
	public boolean animationIsPlaying = false;
	
	
	public DragonAnimation(ModelBase model)
	{
		this.modelDragon = (ModelDragon)model;
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
				modelDragon.tete.rotateAngleX = MathsUtils.Lerp(modelDragon.tete.rotateAngleX,(float) Math.toRadians(-40), elapsedTime / (durationInSeconds / 1.5f));
			}
    	}
	
	}

	
}
