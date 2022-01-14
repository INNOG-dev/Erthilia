package fr.karmaowner.erthilia.potion;

import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.translation.I18n;

@SuppressWarnings("deprecation")
public class PotionInformations {

	
	private final PotionEffect effect;
	
	public long systemTime;
	
	public PotionInformations(final PotionEffect potionEffect)
	{
		this.effect = potionEffect;
	}
	
	public PotionEffect getPotionEffect()
	{
		return this.effect;
	}
	
	public String getPotionAmplifierText()
	{
		if (effect.getAmplifier() == 1) {
			return I18n.translateToLocal("enchantment.level.2");
		} else if (effect.getAmplifier() == 2) {
			return I18n.translateToLocal("enchantment.level.3");
		} else if (effect.getAmplifier() == 3) {
			return I18n.translateToLocal("enchantment.level.4");
		}
		return "";
	}
	
	public int getPotionAplifier()
	{
		return effect.getAmplifier() + 1;
	}
	
	public String getPotionName()
	{
		return getPotion().getName();
	}
	
	public String getPotionDurationText()
	{
		return Potion.getPotionDurationString(effect, 1.0F);
	}
	
	public boolean hasStatusIcon() {
		return getPotion().hasStatusIcon();
	}

	public int getStatusIconIndex() {
		return getPotion().getStatusIconIndex();
	}
	
	public boolean shouldDrawInInventory()
	{
		return getPotion().shouldRenderInvText(effect);
	}
	
	public Potion getPotion()
	{
		return effect.getPotion();
	}

	
	
}
