package fr.karmaowner.erthilia.scepter;

import java.util.ArrayList;
import java.util.List;

import fr.karmaowner.erthilia.utils.PlayerUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;

public class RemoveEffectScepter extends ScepterEffect {

	public RemoveEffectScepter() 
	{
		
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
		return 0;
	}
	
	public int getEffectDuration()
	{
		return 0;
	}
	
	public PotionEffect getEffect()
	{
		return null;
	}
	
	
	@Override
	public void performEffect(EntityPlayer player)
	{
		PlayerUtils.removeAllBadEffects(player);
	}
	
	public List<String> addEffectDescription()
	{
		List<String> descriptions = new ArrayList<String>();
		descriptions.add("§cSceptre : ");
		descriptions.add("§dEnléve les effets!");
		return descriptions;
	}
	
}
