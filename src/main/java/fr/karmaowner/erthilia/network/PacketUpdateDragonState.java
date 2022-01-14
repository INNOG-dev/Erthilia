package fr.karmaowner.erthilia.network;

import fr.karmaowner.erthilia.Main;
import fr.karmaowner.erthilia.entity.monster.EntityMana;
import fr.karmaowner.erthilia.entity.power.Power;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class PacketUpdateDragonState extends PacketBase {

	public int entityId;
	public int attack;
	public long startTime;
	
	public PacketUpdateDragonState()
	{

	}
	
	public PacketUpdateDragonState(int entityId, int attack, long startTime)
	{
		this.attack = attack;
		this.entityId = entityId;
		this.startTime = startTime;
	}
	
	public PacketUpdateDragonState(int entityId)
	{
		this.entityId = entityId;
	}
	
	@Override
	public void encodeInto(ChannelHandlerContext ctx, ByteBuf data) {
			data.writeInt(attack);
			data.writeInt(entityId);
			data.writeLong(startTime);
	}

	@Override
	public void decodeInto(ChannelHandlerContext ctx, ByteBuf data) {
			attack = data.readInt();
			entityId = data.readInt();
			startTime = data.readLong();
	}

	@Override
	public void handleServerSide(EntityPlayerMP playerEntity) {
		Main.log("skip...");
	}

	@Override
	public void handleClientSide(EntityPlayer clientPlayer) {
		Entity entity = Minecraft.getMinecraft().world.getEntityByID(entityId);
	
		if(entity instanceof EntityMana)
		{
			EntityMana mana = (EntityMana) entity;
			Power power = mana.entityMagicController.registeredPower.get(attack);
			Main.log(power);
			power.startedTimer = startTime;
			power.synchronize(clientPlayer);
		}
	}

}
