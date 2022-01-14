package fr.karmaowner.erthilia.items;

import java.util.ArrayList;

import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;

public class ErthiliaTools {
	
	private static ArrayList<ErthiliaTools> tools = new ArrayList<ErthiliaTools>();
	
	private ToolMaterial material;
	
	private ArmorMaterial armorMaterial;
	
	@SuppressWarnings("unused")
	private int id;
	
	@SuppressWarnings("unused")
	private String name;
	
	
	/*
	 *  Tool Material constructor
	 */
	private ErthiliaTools(ToolMaterial material, String name)
	{
		this.id = tools.size();
		this.material = material;
		this.name = name;
	}
	
	/*
	 *  Armor Material constructor
	 */
	private ErthiliaTools(ArmorMaterial material, String name)
	{
		this.id = tools.size();
		this.armorMaterial = material;
		this.name = name;
	}

	public static void registerTool(String toolName, int harvestLevel, int maxUses, float efficiency, float damage, int enchantability)
	{
		ToolMaterial material = EnumHelper.addToolMaterial(toolName, harvestLevel, maxUses, efficiency, damage, enchantability);
		tools.add(new ErthiliaTools(material, toolName));
	}
	
	public static void registerArmorMaterial(String armorName, int durability, int[] reductionAmounts, int enchantability)
	{
		ArmorMaterial material = EnumHelper.addArmorMaterial(armorName, armorName, durability, reductionAmounts, enchantability, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2);
		tools.add(new ErthiliaTools(material, armorName));
	}
	
	public static ToolMaterial getToolMaterial(int index)
	{
		return ((ErthiliaTools) tools.get(index)).material;
	}
	
	public static ArmorMaterial getArmorMaterial(int index)
	{
		return ((ErthiliaTools) tools.get(index)).armorMaterial;
	}
	


}
