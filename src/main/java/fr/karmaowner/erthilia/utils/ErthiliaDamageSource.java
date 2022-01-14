package fr.karmaowner.erthilia.utils;

import fr.karmaowner.erthilia.entity.EntitySpear;
import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;

public class ErthiliaDamageSource {

    public static DamageSource causeSpearDamage(EntitySpear entity, Entity damager)
    {
        return (new EntityDamageSourceIndirect("spear", entity, damager)).setProjectile();
    }
	
}
