package fr.karmaowner.erthilia.hdv;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import fr.karmaowner.erthilia.capability.playercapability.ErthiliaPlayerProvider;
import fr.karmaowner.erthilia.capability.playercapability.IPlayer;
import fr.karmaowner.erthilia.data.WorldDataManager;
import fr.karmaowner.erthilia.utils.ISaveHandler;
import fr.karmaowner.erthilia.utils.MathsUtils;
import fr.karmaowner.erthilia.utils.PlayerUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class HdvManager implements ISaveHandler
{
	public final static int elementsPerPage = 9;
	
	public final static int maxItemPerPlayer = 5;
	
	public final static int hdvUpdateTime = 5; // In Seconds
	
	public final static int itemMaxTimeInHdv = 86400; //in seconds
	
	public final static double serverMaxMoney = 1*10E13;
	
	private static long nextId = 0;
	
	public List<HdvItem> items = new ArrayList<HdvItem>();
	
	private List<HdvItem> expiredItems = new ArrayList<HdvItem>();
	
	
	public void addItemToHDV(EntityPlayer owner, float price, int quantity)
	{
		ItemStack heldItem = owner.getHeldItemMainhand();
		
		IPlayer playerData = (IPlayer) owner.getCapability(ErthiliaPlayerProvider.PLAYER_CAP, null);
		
		if(heldItem == null)
		{
			PlayerUtils.sendMessage(owner, "§cVous devez avoir un item en main");
			return;
		}
		
		if(getPlayerItemInHdv(owner) >= maxItemPerPlayer)
		{
			PlayerUtils.sendMessage(owner, "§cLimite maximale de 5 items!");
			return;
		}
		
		if(playerData.getSyncSpigotData().getPlayerMoney() + getTotalHdvSellPrice(owner) + price > serverMaxMoney)
		{
			PlayerUtils.sendMessage(owner, "§cVous ne pouvez pas ventre cette objet à ce prix vous atteindrez la limite max du serveur");
			return;
		}
		
		ItemStack hdvItem = heldItem.copy();
		hdvItem.setCount(quantity);
		
		if(heldItem.getCount() >= quantity)
		{
			if(heldItem.getCount()  - quantity == 0)
			{
				owner.inventory.setInventorySlotContents(owner.inventory.currentItem, null);
			}
			else
			{
				heldItem.setCount(heldItem.getCount() - quantity);
			}
		}
		else
		{
			PlayerUtils.sendMessage(owner, "§cVous n'avez pas la quantité nécessaire");
			return;
		}
		
		items.add(new HdvItem(owner.getName(), hdvItem, price, nextId()));
		PlayerUtils.sendMessage(owner, "§ax" + quantity + " (§6"  + hdvItem.getDisplayName() + "§a) §amis en vente!");
		WorldDataManager.get(owner.world).markDirty();
	}
	
	public long nextId()
	{
		return nextId++;
	}
	
	public void buyHdvSelectedItem(EntityPlayer player)
	{
		IPlayer persistantData = (IPlayer)player.getCapability(ErthiliaPlayerProvider.PLAYER_CAP, null);
		
		ItemStack copyOfBoughtItem = persistantData.getSelectedHdvItem().getItemstack().copy();
		
		if(persistantData.getSelectedHdvItem().getOwnerName().equals(player.getName()))
		{
			PlayerUtils.sendMessage(player, "§cVous ne pouvez pas acheter votre objet");
			return;
		}
		
		if(getHdvItemById(persistantData.getSelectedHdvItem().getHdvId()) == null)
		{
			PlayerUtils.sendMessage(player, "§cL'item a expiré!");
			return;
		}
		
		if(persistantData.getSyncSpigotData().getPlayerMoney() < persistantData.getSelectedHdvItem().getSellPrice())
		{
			PlayerUtils.sendMessage(player, "§cVous n'avez pas l'argent nécessaire");
			return;
		}
		
		if(player.inventory.addItemStackToInventory(copyOfBoughtItem))
		{
			PlayerUtils.sendMessage(player, "§6x" + persistantData.getSelectedHdvItem().getItemstack().getCount() + " §a" + copyOfBoughtItem.getDisplayName() + " acheté!");
		}
		else
		{
			PlayerUtils.sendMessage(player, "§cVous n'avez pas de place dans votre inventaire");
			PlayerUtils.sendMessage(player, "§cL'item a directement était placé dans vos items expiré");
			HdvItem item = new HdvItem(player.getName(), copyOfBoughtItem, 0, nextId());
			expiredItems.add(item);
		}
		
		String price = String.format("%.0f",persistantData.getSelectedHdvItem().getSellPrice());
		
		System.out.println("/eco give " + persistantData.getSelectedHdvItem().getOwnerName() + " " + price);
		System.out.println("/eco take " + player.getName() + " " + price);
		
		items.remove(persistantData.getSelectedHdvItem());

	}
	
	public int getPlayerItemInHdv(EntityPlayer player)
	{
		if(player == null) return 0;
		
		String playerName = player.getName();
			
		return (int)items.stream().filter(x -> x.getOwnerName().equals(playerName)).count();
	}
	
	public double getTotalHdvSellPrice(EntityPlayer player)
	{
		float price = 0f;
		List<HdvItem> items = getItems(x -> x.getOwnerName().equals(player.getName()));
		for(HdvItem hdvit : items)
		{
			price += hdvit.getSellPrice();
		}
		return price;
	}
	
	public int getPageCount()
	{
		return getPageCount(items);
	}
	
	public int getPageCount(List<HdvItem> items)
	{
		return (int) Math.ceil((float)items.size() / elementsPerPage);
	}
	
	public int getElementsCountInPage(int page, List<HdvItem> items)
	{
		if(items.size() == 0)
		{
			return 0;
		}
		
		int pageFirstElementPos = (page * elementsPerPage - elementsPerPage);
		int leftTotalElement = items.size() - pageFirstElementPos;
		if(leftTotalElement - elementsPerPage >= 0)
		{
			return elementsPerPage;
		}
		else
		{
			return elementsPerPage - (elementsPerPage - leftTotalElement);
		}
	}
	
	public List<HdvItem> getItemsForPage(int page)
	{
		return getItemsForPage(page, items);
	}
	
	public List<HdvItem> getItemsAndExpiredItems(Predicate<? super HdvItem> predicate)
	{
		List<HdvItem> newItems = new ArrayList<HdvItem>();
		newItems.addAll(items.stream().filter(predicate).collect(Collectors.toList()));
		newItems.addAll(expiredItems.stream().filter(predicate).collect(Collectors.toList()));
		return newItems;
	}
	
	public List<HdvItem> getItems(Predicate<? super HdvItem> predicate)
	{
		return items.stream().filter(predicate).collect(Collectors.toList());
	}

	
	public List<HdvItem> getItemsForPage(int page, List<HdvItem> items)
	{
		page = MathsUtils.Clamp(page, 1, Math.max(1,getPageCount(items)));
						
		List<HdvItem> itemsToTransmit = new ArrayList<HdvItem>();
		
		
		for(int i = 0; i < getElementsCountInPage(page, items); i++)
		{
			int index = (page * elementsPerPage) - elementsPerPage + i;
			itemsToTransmit.add(items.get(index));
		}
		
		return itemsToTransmit;
	}
	
	public int getItemsCount()
	{
		return items.size();
	}
	
	public void updateHdv()
	{
		List<HdvItem> itemsToRemove = new ArrayList<HdvItem>();
		
		for(HdvItem item : items)
		{
						
			if((System.currentTimeMillis() - item.getTimeSinceStartSell()) / 1000 >= itemMaxTimeInHdv)
			{
				itemsToRemove.add(item);
				
				expiredItems.add(item);
			}
		}
		
		if(itemsToRemove.size() > 0)
		{
			WorldDataManager.get(FMLCommonHandler.instance().getMinecraftServerInstance().getEntityWorld()).markDirty();
		}
		
		items.removeAll(itemsToRemove);
	}
	
	public HdvItem getHdvItemById(long itemHdvId)
	{
		for(int i = 0; i < items.size(); i++)
		{
			HdvItem item = items.get(i);
			
			if(item.getHdvId() == itemHdvId)
			{
				return item;
			}
		}
		return null;
	}
	
	public HdvItem getExpiredHdvItemById(long itemHdvId)
	{
		for(int i = 0; i < expiredItems.size(); i++)
		{
			HdvItem item = expiredItems.get(i);
			
			if(item.getHdvId() == itemHdvId)
			{
				return item;
			}
		}
		return null;
	}
	
	public void selectHdvItem(long itemHdvId, EntityPlayer player)
	{
		HdvItem item = getHdvItemById(itemHdvId);
			
		if(item == null)
		{
			PlayerUtils.sendMessage(player, "§cL'item a expiré!");
			return;
		}
		else
		{
			IPlayer persistantData = (IPlayer) player.getCapability(ErthiliaPlayerProvider.PLAYER_CAP, null);
			persistantData.setSelectedHdvItem(item);
			PlayerUtils.sendMessage(player, "§6/hdv confirm - §apour confirmer l'achat!");
		}
	}
	
	public void removeItemFromHdv(long itemId, EntityPlayerMP player) 
	{
		HdvItem item = getHdvItemById(itemId);
		
		if(item == null)
		{
			item = getExpiredHdvItemById(itemId);
			if(item == null)
			{
				PlayerUtils.sendMessage(player, "§cUne erreur s'est produite");
				return;
			}
			else
			{
				if(!player.inventory.addItemStackToInventory(item.getItemstack().copy()))
				{
					PlayerUtils.sendMessage(player, "§cVotre inventaire est plein");
				}
				else
				{
					PlayerUtils.sendMessage(player, "§ax" + item.getItemstack().getCount() + " (§6"  + item.getItemstack().getDisplayName() + "§a) §arécupéré!");
					expiredItems.remove(item);
				}
			}
		}
		else
		{
			if(!player.inventory.addItemStackToInventory(item.getItemstack().copy()))
			{
				PlayerUtils.sendMessage(player, "§cVotre inventaire est plein");
			}
			else
			{
				PlayerUtils.sendMessage(player, "§ax" + item.getItemstack().getCount() + " (§6"  + item.getItemstack().getDisplayName() + "§a) §aretiré de la vente!");
				items.remove(item);
			}
		}
	}

	@Override
	public void writeToNbt(NBTTagCompound compound) 
	{
		NBTTagList hdvItemsTag = new NBTTagList();
		
		for(int i = 0; i < items.size(); i++)
		{
			HdvItem hdvItem = items.get(i);
			
			NBTTagCompound itemTag = new NBTTagCompound();
			
			hdvItem.writeToNbt(itemTag);
			
			hdvItemsTag.appendTag(itemTag);
		}
		compound.setTag("Items", hdvItemsTag);
		
		hdvItemsTag = new NBTTagList();
		
		for(int i = 0; i < expiredItems.size(); i++)
		{
			HdvItem hdvItem = expiredItems.get(i);
			
			NBTTagCompound itemTag = new NBTTagCompound();
			
			hdvItem.writeToNbt(itemTag);
			
			hdvItemsTag.appendTag(itemTag);
		}
		compound.setTag("ExpiredItems", hdvItemsTag);

		System.out.println("Hdv items saved!");
	}


	@Override
	public void readFromNbt(NBTTagCompound compound)
	{
		NBTTagList hdvItemsTag = (NBTTagList) compound.getTag("Items");
		
		if(hdvItemsTag != null)
		{
			for(int i = 0; i < hdvItemsTag.tagCount(); i++)
			{
				NBTTagCompound itemTag = hdvItemsTag.getCompoundTagAt(i);
				HdvItem hdvItem = new HdvItem(nextId());
				hdvItem.readFromNbt(itemTag);
				items.add(hdvItem);
			}	
		}
		
		hdvItemsTag = (NBTTagList) compound.getTag("ExpiredItems");
		
		if(hdvItemsTag != null)
		{
			for(int i = 0; i < hdvItemsTag.tagCount(); i++)
			{
				NBTTagCompound itemTag = hdvItemsTag.getCompoundTagAt(i);
				HdvItem hdvItem = new HdvItem(nextId());
				hdvItem.readFromNbt(itemTag);
				expiredItems.add(hdvItem);
			}	
		}
		
		System.out.println("Hdv items loaded!");
	}

	
	
}
