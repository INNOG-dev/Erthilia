package fr.karmaowner.erthilia.entity.power;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.google.common.base.Preconditions;

import fr.karmaowner.erthilia.Main;
import fr.karmaowner.erthilia.entity.monster.EntityMana;
import fr.karmaowner.erthilia.entity.power.Power.PowerType;
import net.minecraft.entity.EntityTracker;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

@SuppressWarnings("deprecation")
public class MagicController {
	
	public List<Power> registeredPower = new ArrayList<Power>();
	
	public List<EntityPlayer> renderUpdateFor = new ArrayList<EntityPlayer>();
	
	private EntityMana attriubatedEntity;
	
	private int updateRenderRange;
	
	private boolean initialized = false; 
	
	public MagicController(EntityMana entity)
	{
		this.attriubatedEntity = entity;
		
		if(!entity.world.isRemote)
		{
			WorldServer worldServer = (WorldServer) entity.world;
			updateRenderRange = ReflectionHelper.getPrivateValue(EntityTracker.class, worldServer.getEntityTracker(), 4);
		}

	}

	public MagicController registerPower(Power power)
	{
		Preconditions.checkArgument(!initialized);
		if(powerAlreadyRegistered(power))
		{
			Main.log(power + " already registered!");
			return this;
		}
		
		power.id = registeredPower.size();
		Main.log("Power : " + power.getName() + " with id : " + power.id + " registered!");
		
		registeredPower.add(power);
		return this;
	}
	
	private boolean powerAlreadyRegistered(Power instance)
	{
		for(Power power : registeredPower)
		{
			if(power == instance) return true;
		}
		return false;
	}
	
	public void updateController()
	{
		initialized = true;
		renderUpdateFor = attriubatedEntity.world.getEntitiesWithinAABB(EntityPlayer.class, new AxisAlignedBB(attriubatedEntity.posX-updateRenderRange, attriubatedEntity.posY, attriubatedEntity.posZ-updateRenderRange, attriubatedEntity.posX+updateRenderRange, attriubatedEntity.posY+updateRenderRange, attriubatedEntity.posZ+updateRenderRange));
		if(attriubatedEntity.ticksExisted % 20 == 0)
		{
			for(Power power : registeredPower.stream().sorted(Comparator.comparing(Power::priorityCheckConditions)).collect(Collectors.toList()))
			{
				power.update();
			}
		}
	}
	
	public List<Power> getActiveInternPower()
	{
		return registeredPower.stream().filter(x -> x.powerActive && x.getPowerType() == PowerType.INTERN).collect(Collectors.toList());
	}
	
	public List<Power> getActivePower()
	{
		return registeredPower.stream().filter(x -> x.powerActive).collect(Collectors.toList());
	}
	
	public List<Power> getActiveExternPower()
	{
		return registeredPower.stream().filter(x -> x.powerActive && x.getPowerType() == PowerType.EXTERN).collect(Collectors.toList());
	}
	
	public List<Power> getActivePowerWithAnimation()
	{
		return registeredPower.stream().filter(x -> x.powerActive && x.getAnimationId() != -1).collect(Collectors.toList());
	}
	
	public EntityMana getAttribuatedEntity()
	{
		return this.attriubatedEntity;
	}
	
}
