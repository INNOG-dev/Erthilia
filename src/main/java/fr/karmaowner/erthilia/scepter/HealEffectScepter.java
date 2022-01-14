package fr.karmaowner.erthilia.scepter;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class HealEffectScepter extends ScepterEffect {
	
	private int healQuantity;
	
	public HealEffectScepter(int healQuantity)
	{
		this.healQuantity = healQuantity;
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
		player.setHealth(player.getHealth() + this.healQuantity);
		player.addPotionEffect(new PotionEffect(Potion.getPotionById(10),1,20*5));
	}
	
	@Override
	public List<String> addEffectDescription()
	{
		List<String> descriptions = new ArrayList<String>();
		descriptions.add("§cSceptre : ");
		descriptions.add("§dSoigne!");
		return descriptions;
	}

}
