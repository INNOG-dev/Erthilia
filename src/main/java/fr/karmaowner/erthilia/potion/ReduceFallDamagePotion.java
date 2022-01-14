package fr.karmaowner.erthilia.potion;

import net.minecraft.potion.Potion;

public class ReduceFallDamagePotion extends Potion {
	
	public static Potion FALL_DAMAGE_REDUCE;
		
	public ReduceFallDamagePotion(boolean isBadEffect, int color, String name) {
		super(isBadEffect, color);
		this.setPotionName("potion." + name); 
		this.setIconIndex(0, 0);
		FALL_DAMAGE_REDUCE = this;
	}
	
	@Override
	public ReduceFallDamagePotion setIconIndex(int x, int y) 
	{
	    super.setIconIndex(x, y);
	    return this;
	}
	
	@Override
    public boolean isInstant()
    {
        return false;
    }
    
    @Override
    public boolean isReady(int duration, int amplifier)
    {
        return true;
    }





	
	

}
