package fr.karmaowner.erthilia.cosmetics;

import java.util.Arrays;
import java.util.List;

import fr.karmaowner.erthilia.Main;
import fr.karmaowner.erthilia.capability.playercapability.ErthiliaPlayer;
import fr.karmaowner.erthilia.capability.playercapability.ErthiliaPlayerProvider;
import fr.karmaowner.erthilia.network.PacketCosmetic;
import fr.karmaowner.erthilia.utils.ItemStackUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;

public class CompanionObject extends CosmeticObject {
		
	private int attribuatedPotionIndex = -1;
	
	private int timeUsedCompanion; //In Seconds
			
	public CompanionObject(String name, boolean unlockedDefault, byte type, int id)
	{
		super(name,unlockedDefault,type,id);
	}
	
	public void writeToNBT(NBTTagCompound compound)
	{ 
		super.writeToNBT(compound);
		
		if(attribuatedPotionIndex != -1) compound.setInteger("AttribuatedPotionIndex", attribuatedPotionIndex);
		
		compound.setInteger("TimeUsedCompanion", timeUsedCompanion);
	}
	
    public void loadNBTData(NBTTagCompound compound) 
    {
    	super.loadNBTData(compound);
    	
    	attribuatedPotionIndex = compound.getInteger("AttribuatedPotionIndex");
    	
    	timeUsedCompanion = compound.getInteger("TimeUsedCompanion");
    }
    
	public static boolean equipCompanion(EntityPlayer player, int selectedEffectIndex, int id)
	{
		CosmeticObject cosmetic = ((ErthiliaPlayer)player.getCapability(ErthiliaPlayerProvider.PLAYER_CAP, null)).getCosmeticById(id);
		
		if(cosmetic instanceof CompanionObject)
		{
			CompanionObject companion = (CompanionObject) cosmetic;
			

			
			if(companion.getIsEquipped())
			{
				return false;
			}
			
			if(companion.getIsLocked())
			{
				return false;
			}
			
			if(!player.world.isRemote)
			{
				if(companion.companionNeedFood())
				{
					 Main.getPacketHandler().sendTo(PacketCosmetic.displayDialogBox((byte)0), (EntityPlayerMP)player);
					
					return false;
				}
				companion.setEquipped(true);
				companion.attribuatedPotionIndex = selectedEffectIndex;
			}
			
						
			 
			if(player.world.isRemote)
			{
 				Main.getPacketHandler().sendToServer(PacketCosmetic.equipCompanion(id, selectedEffectIndex));
			}
			else
			{
				Main.getCosmeticsManager().updateCompanion(player);

				player.closeScreen();
			}
						
			return true;
			
		}
		else
		{
			return CosmeticObject.equipCosmetic(player, id);
		}
	}
	
	public static boolean unequipCompanion(EntityPlayer player, int id)
	{
		CosmeticObject cosmetic = ((ErthiliaPlayer)player.getCapability(ErthiliaPlayerProvider.PLAYER_CAP, null)).getCosmeticById(id);
		if(cosmetic instanceof CompanionObject)
		{
			CompanionObject companion = (CompanionObject) cosmetic;
			
			if(!companion.getIsEquipped())
			{
				return false;
			}
		
		
			companion.setEquipped(false);
		
			if(player.world.isRemote)
			{
				Main.getPacketHandler().sendToServer(PacketCosmetic.unequipCompanion(id));
			}
			else
			{
				Main.getCosmeticsManager();
				PotionEffect potionEffect = CosmeticManager.getCompanionEffects().get(companion.getSelectedEffectIndex());
				
				player.removePotionEffect(potionEffect.getPotion());
			}
		}
		else
		{
			return CosmeticObject.unequipCosmetic(player, id);
		}
		
		return true;
	}
	
	public boolean companionNeedFood()
	{
		if(timeUsedCompanion >= CosmeticManager.companionMaxUseTime)
		{
			return true;
		}
		return false;
	}
	
	public static void feedCompanion(EntityPlayer player, int id)
	{
		CosmeticObject cosmetic = ((ErthiliaPlayer)player.getCapability(ErthiliaPlayerProvider.PLAYER_CAP, null)).getCosmeticById(id);
		
		if(cosmetic instanceof CompanionObject)
		{
			CompanionObject companion = (CompanionObject) cosmetic;
			
			List<ItemStack> needFoods = Arrays.asList(Main.getCosmeticsManager().companionFood);
						
			if(ItemStackUtils.playerHaveItemsInInventory(needFoods, player))
			{
				System.out.println("test");
				ItemStackUtils.removeItemsFromInventory(needFoods, player);
				companion.timeUsedCompanion = 0;
			}
			else
			{
				System.out.println("test1");

				//not have necessary Items
				Main.getPacketHandler().sendTo(PacketCosmetic.displayDialogBox((byte)1), (EntityPlayerMP)player);
			}
		}
	}
	
	public int getSelectedEffectIndex()
	{
		return attribuatedPotionIndex;
	}
	
	public void addTimeUsed(int seconds)
	{
		this.timeUsedCompanion += seconds;
	}
	
	public int getTimeUsed()
	{
		return timeUsedCompanion;
	}


}
