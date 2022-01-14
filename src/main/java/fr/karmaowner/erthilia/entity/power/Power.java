package fr.karmaowner.erthilia.entity.power;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import fr.karmaowner.erthilia.Main;
import fr.karmaowner.erthilia.entity.monster.EntityMana;
import fr.karmaowner.erthilia.network.PacketUpdateDragonState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;

public abstract class Power {
	
	public enum PowerType
	{
		EXTERN,
		INTERN
	};
	
	public int id;
	
	private HashMap<UUID,EntityPlayer> synchronizedAlready = new HashMap<UUID,EntityPlayer>();
	
	protected MagicController controller;
	
	protected EntityMana entity;

	public long startedTimer;
	
	protected boolean powerActive;
	
	protected abstract float needManaCount();
	
	public abstract int powerDurationInSeconds();

	protected abstract int priorityCheckConditions();
	
	protected abstract String powerSound();
	
	public abstract int getAnimationId();
	
	public abstract String getName();
	
	public abstract PowerType getPowerType();
	
	public Power(MagicController controller)
	{
		this.controller = controller;
		this.entity = controller.getAttribuatedEntity();
	}
	
	public void applyPower()
	{
		if(!entity.world.isRemote)
		{
			entity.setManaCount(entity.getManaCount()-needManaCount());
			startedTimer = System.currentTimeMillis();
			
			entity.playSound(new SoundEvent(new ResourceLocation("erthilia","dragon_scream")), 10.0F, 0.5F);
		}
		powerActive = true;
	}
	
	public void update()
	{
		if(!entity.world.isRemote)
		{
			if(canUsePower())
			{
				Main.log("Power used " + getName() + " id : " + id);
				applyPower();
			}
			if(powerActive)
			{
				List<EntityPlayer> needSynchronize = controller.renderUpdateFor.stream().filter(x -> !this.synchronizedAlready.containsKey(x.getPersistentID())).collect(Collectors.toList());
				for(EntityPlayer player : needSynchronize)
				{
					synchronize(player);
				}
			}
		}
	}
	
	public int getLeftTime() //InSeconds
	{
		int leftTime = (int) (powerDurationInSeconds() - (System.currentTimeMillis() - this.startedTimer) / 1000);
		if(leftTime < 0) leftTime = 0;
		return leftTime;
	}
	
	public void stopPower()
	{
		powerActive = false;
		synchronizedAlready.clear();
	}
	
	public boolean canUsePower()
	{
		return !powerActive && entity.getManaCount() >= needManaCount();
	}
	
	public void synchronize(EntityPlayer player)
	{
		if(!entity.world.isRemote)
		{
			Main.getPacketHandler().sendTo(new PacketUpdateDragonState(entity.getEntityId(),id,startedTimer), (EntityPlayerMP) player);
			synchronizedAlready.put(player.getPersistentID(),player);
		}
		else
		{
			applyPower();
		}
	}
	
	
}
