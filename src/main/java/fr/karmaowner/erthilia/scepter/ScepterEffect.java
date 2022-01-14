package fr.karmaowner.erthilia.scepter;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class ScepterEffect {
	
	private String effectRegistryId;
	
	public int effectAmplitude;
	
	public int effectDuration;
	
	private List<String> scepterDescription;
	
	public ScepterEffect(String effectId, int effectAmplitude, int effectDuration, ArrayList<String> scepterDescription) 
	{ 
		this.scepterDescription = scepterDescription;
		this.effectRegistryId = effectId;
		this.effectAmplitude = effectAmplitude;
		this.effectDuration = effectDuration;
	} 
	
	public ScepterEffect() 
	{ 

	} 
	
	public boolean effectIsActiveInUser()
	{
		return true;
	}
	
	public float getUseDamageOnUser()
	{
		return 0f;
	}
	
	public float getDamageVsEntity()
	{
		return 0f;
	}
	
	public int getEffectAmplitude()
	{
		return this.effectAmplitude;
	}
	
	public int getEffectDuration()
	{
		return this.effectDuration;
	}
	
	public String getEffectId()
	{
		return this.effectRegistryId;
	}
	
	
	public List<String> addEffectDescription()
	{
		return this.scepterDescription;
	}
	
	public void performEffect(EntityPlayer player)
	{		
		if(player.world.isRemote)
		{
			return;
		}
		else
		{
			if(effectRegistryId != null)
			{
				if(effectIsActiveInUser())
				{
					//if(!player.isPotionActive(effect.getPotion())) player.addPotionEffect(new PotionEffect(effect));
					player.addPotionEffect(new PotionEffect(Potion.getPotionFromResourceLocation(effectRegistryId),this.effectDuration,this.effectAmplitude));
				}
			}
		}
	}
	
	
}
