package fr.karmaowner.erthilia.guicomponent;

import fr.karmaowner.erthilia.animations.FrameAnimator;

public class UIAnimatedImage extends UIImage {

	
	private FrameAnimator animator;
	
	
	public UIAnimatedImage(int framesCount,String locationPath, String textureName,String textureFormat, int frameUpdateTicks)
	{
		animator = new FrameAnimator(framesCount,locationPath,textureName,textureFormat,frameUpdateTicks);
	}
	
	@Override
	public void draw(int x, int y, float partialTicks)
	{
		this.texture = animator.currentTexture;
		super.draw(x, y, partialTicks);
		animator.next();
	}
	
	
}
