package fr.karmaowner.erthilia.cosmetics;

import java.util.List;
import java.util.stream.Collectors;

import org.lwjgl.opengl.GL11;

import fr.karmaowner.erthilia.Main;
import fr.karmaowner.erthilia.capability.playercapability.ErthiliaPlayer;
import fr.karmaowner.erthilia.capability.playercapability.ErthiliaPlayerProvider;
import fr.karmaowner.erthilia.capability.playercapability.IPlayer;
import fr.karmaowner.erthilia.model.ErthiliaModelBase;
import fr.karmaowner.erthilia.network.PacketCosmetic;
import fr.karmaowner.erthilia.utils.PlayerUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class CosmeticObject {
		
	private boolean isEquipped;
	
	private boolean isLocked;
	
	private String cosmeticName;
	
	private int id;
	
	
	private ErthiliaModelBase renderModel;
	
	private ICosmeticSetup cosmeticGuiSetup;
		
	/*
	 * 0:hat
	 * 1:face
	 * 2:body
	 * 3:companion
	 */
	private byte type;
	
	public CosmeticObject(String name, boolean unlockedDefault, byte type, int id)
	{
		this.cosmeticName = name;
		this.isLocked = unlockedDefault;
		this.type = type;
		this.id = id;
	}
	
	@SideOnly(Side.CLIENT)
	public CosmeticObject setupRender(ICosmeticSetup setup, ErthiliaModelBase model)
	{
		this.cosmeticGuiSetup = setup;

		renderModel = model;
		return this;
	} 
	
	@SideOnly(Side.CLIENT)
	public ICosmeticSetup getCosmeticRenderSetup()
	{
		return this.cosmeticGuiSetup;
	}
	

	@SideOnly(Side.CLIENT)
	public ErthiliaModelBase getModel()
	{
		return renderModel;
	}
	
	public boolean getIsLocked()
	{
		return this.isLocked;
	}
	
	public boolean getIsEquipped()
	{
		return this.isEquipped;
	}
	
	public boolean isCompanion()
	{
		return type == 3;
	}

	
	public void setEquipped(boolean equipped)
	{
		this.isEquipped = equipped;
	}
	
	public void setLocked(boolean locked)
	{
		this.isLocked = locked;
	}
	
	public String getName()
	{
		return cosmeticName;
	}
	
	public byte getType()
	{
		return type;
	}
	
	public int getId()
	{
		return this.id;
	}

	
	public void writeToNBT(NBTTagCompound compound)
	{
		compound.setBoolean("Equip", isEquipped);
		compound.setBoolean("isLocked", isLocked);
	}
	
    public void loadNBTData(NBTTagCompound compound) 
    {
    	if(compound.hasKey("Equip"))
    	{
        	isEquipped = compound.getBoolean("Equip");
    	}
    	if(compound.hasKey("isLocked"))
    	{
        	isLocked = compound.getBoolean("isLocked");
    	}
    }
    
    public static void setCosmetiqueUnlocked(EntityPlayer player, int id)
	{
		CosmeticObject cosmetic = ((ErthiliaPlayer)player.getCapability(ErthiliaPlayerProvider.PLAYER_CAP, null)).getCosmeticById(id);
		cosmetic.setLocked(false);
		PlayerUtils.sendMessage(player,"§6" + cosmetic.getName() + " §aunlocked!");
	}
	
	public static boolean equipCosmetic(EntityPlayer player, int id)
	{
				
		CosmeticObject cosmetic = ((ErthiliaPlayer)player.getCapability(ErthiliaPlayerProvider.PLAYER_CAP, null)).getCosmeticById(id);
		
		
		if(cosmetic.isEquipped)
		{
			return false;
		}
		
		if(cosmetic.isLocked)
		{
			return false;
		}
		

		
		 cosmetic.isEquipped = true;
		
		 
		if(player.world.isRemote)
		{
			Main.getPacketHandler().sendToServer(new PacketCosmetic((byte)0,id));
		}
		
		return true;
		
	}
	
	public static List<CosmeticObject> getEquippedCosmeticFromSameType(EntityPlayer player, byte type)
	{
		IPlayer ep = (IPlayer) player.getCapability(ErthiliaPlayerProvider.PLAYER_CAP, null);
		return ep.getEquippedCosmetics().stream().filter(x -> x.type == type).collect(Collectors.toList());
	}

	
	public static boolean unequipCosmetic(EntityPlayer player, int id)
	{
		CosmeticObject cosmetic = ((ErthiliaPlayer)player.getCapability(ErthiliaPlayerProvider.PLAYER_CAP, null)).getCosmeticById(id);

		
		if(!cosmetic.isEquipped)
		{

			return false;
		}
		


		cosmetic.isEquipped = false;
		
		if(player.world.isRemote)
		{

			Main.getPacketHandler().sendToServer(new PacketCosmetic((byte)1,id));
		}
		
		return true;
	}

	@SideOnly(Side.CLIENT)
	public void setModel(ErthiliaModelBase model) 
	{
		this.renderModel = model;
	}
	
	@SideOnly(Side.CLIENT)
	public void renderModelInGui(int posX, int posY, float rotation)
	{

		GL11.glPushMatrix();
			
		GL11.glTranslatef(posX + 18, posY + 4, 100);
		GL11.glScalef(-1, -1, -1);
		GL11.glPushMatrix();

		this.getCosmeticRenderSetup().setupCosmeticGuiDisplay();
		this.getModel().render();
		GL11.glPopMatrix();
		GL11.glPopMatrix();
	}
	
	
	


}
