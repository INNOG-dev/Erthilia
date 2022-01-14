package fr.karmaowner.erthilia.handler;

import java.util.Arrays;

import net.minecraft.client.Minecraft;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EntityDamageSource;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ParticlesHandler {
	
		@SubscribeEvent
	    public void onAttack(AttackEntityEvent event) {
			
			if(!event.getEntityPlayer().world.isRemote) return;
	        // Create a variable for Configuration#getMultiplier()
	        int multiplier = this.getMultiplier();
	        // Create a variable for the entity.

	        EntityPlayer player = Minecraft.getMinecraft().player;
	
	        if(multiplier == 1) return;
	        
	        if(event.getEntityPlayer() != player) return;
	        
	        if(!this.isMultiplyOnAnimals())
	        {
		        if(!(event.getTarget() instanceof EntityPlayer)) return;	  
	        }
	        
	        boolean critical = player.fallDistance > 0.0f && !player.onGround && !player.isOnLadder() && !player.isInWater() && !player.isPotionActive(MobEffects.BLINDNESS) && player.getRidingEntity() == null;
	       
	        if(!this.isMultiplyWithoutCrits())
	        {
	        	if(!critical) return;
	        }
	        
	        Iterable<ItemStack> itemstacks = Arrays.asList(new ItemStack[]{player.getHeldItemMainhand()});
	        
	        
	        // Create a float to get the EMD (Enchantment Modifier Damage).
	        float enchantment = EnchantmentHelper.getEnchantmentModifierDamage(itemstacks, new EntityDamageSource("player", player));
	        // Create a for statement which loops for the length of the multiplier and runs the code inside.
	        if(this.isEnabled())
	        {
		        for (int i = 1; i < multiplier; ++i) {
		        	if (critical || this.MULTIPLY_WITHOUT_CRITS) Minecraft.getMinecraft().player.onCriticalHit(event.getTarget());
		        	if (enchantment > 0.0f) Minecraft.getMinecraft().player.onEnchantmentCritical(event.getTarget()); 
		        }
	        }
	    }
		
		private boolean enabled = true;


		public int multiplier = 1;


		// Create a boolean for enabling/disabling the "Multiply On Animals" option.
		private boolean MULITPLY_ON_ANIMALS = false;


		// Create a boolean for enabling/disabling the "Multiply without Critical Hits" option.
		private boolean MULTIPLY_WITHOUT_CRITS = false;


		public int getMultiplier() {
			return multiplier;
		}

		 public boolean isMultiplyWithoutCrits() {
			 return MULTIPLY_WITHOUT_CRITS;
		 }

		 public void setMultiplyWithoutCrits(boolean b) {
			 this.MULTIPLY_WITHOUT_CRITS = b;
		 }

		 public boolean isEnabled() {
			 return enabled;
		 }

		 public void setEnabled(boolean enabled) {
			 this.enabled = enabled;
		 }


		 public boolean isMultiplyOnAnimals() {
			 return MULITPLY_ON_ANIMALS;
		 }

		 public void setMultiplyOnAnimals(boolean b) {
			 this.MULITPLY_ON_ANIMALS = b;
		 }
	
}
