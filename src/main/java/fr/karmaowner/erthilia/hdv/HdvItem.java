package fr.karmaowner.erthilia.hdv;

import fr.karmaowner.erthilia.network.INetworkElement;
import fr.karmaowner.erthilia.utils.ISaveHandler;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.ByteBufUtils;

public class HdvItem implements ISaveHandler, INetworkElement {

	private String ownerName;
	
	private ItemStack itemstack;
	
	private float sellPrice;
	
	private long itemSellStartTime;
	
	private long id;
	
	public HdvItem() { }
	
	public HdvItem(long id) 
	{
		this.id = id;
	}
	
	public HdvItem(String ownerName, ItemStack itemstack,  float sellPrice, long id)
	{
		this.ownerName = ownerName;
		this.itemstack = itemstack;
		this.sellPrice = sellPrice;
		itemSellStartTime = System.currentTimeMillis();
		this.id = id;
	}
	
	public String getOwnerName()
	{
		return ownerName;
	}


	public ItemStack getItemstack()
	{
		return itemstack;
	}


	public float getSellPrice() 
	{
		return sellPrice;
	}

	public long getHdvId()
	{
		return id;
	}

	public void setSellPrice(float sellPrice)
	{
		this.sellPrice = sellPrice;
	}
	
	public long getTimeSinceStartSell()
	{
		return itemSellStartTime;
	}
	
	@Override
	public void writeToNbt(NBTTagCompound compound)
	{
		compound.setString("OwnerName", ownerName);
		itemstack.writeToNBT(compound);
		compound.setFloat("SellPrice", sellPrice);
		compound.setLong("SellStartTime", itemSellStartTime);
	}

	@Override
	public void readFromNbt(NBTTagCompound compound) {
		ownerName = compound.getString("OwnerName");
		itemstack = new ItemStack(compound);
		sellPrice = compound.getFloat("SellPrice");
		itemSellStartTime = compound.getLong("SellStartTime");
	}

	@Override
	public void encodeInto(ChannelHandlerContext ctx, ByteBuf data) 
	{
		ByteBufUtils.writeUTF8String(data, ownerName);
		ByteBufUtils.writeItemStack(data, itemstack);
		data.writeFloat(sellPrice);
		data.writeLong(itemSellStartTime);
		data.writeLong(id);
	}

	@Override
	public void decodeInto(ChannelHandlerContext ctx, ByteBuf data)
	{
		ownerName = ByteBufUtils.readUTF8String(data);
		itemstack = ByteBufUtils.readItemStack(data);
		sellPrice = data.readFloat();
		itemSellStartTime = data.readLong();
		id = data.readLong();
	}
	
	
	
}
