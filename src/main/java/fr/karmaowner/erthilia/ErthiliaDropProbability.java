package fr.karmaowner.erthilia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Random;

import fr.karmaowner.erthilia.items.ErthiliaItems;
import net.minecraft.item.Item;

public class ErthiliaDropProbability {
	
	private static HashMap<ErthiliaDropProbability,Integer> oreProbability = new HashMap<ErthiliaDropProbability, Integer>();

	public static final ErthiliaDropProbability defaultDropProbability = new ErthiliaDropProbability(ErthiliaItems.JADE,1);
	
	
	private Item item;
	
	private int dropQuantity;
	
	public ErthiliaDropProbability(Item item, int quantity)
	{
		this.item = item;
		this.dropQuantity = quantity;
	}
	
	public int getDropQuantity() { return this.dropQuantity; }
	
	public Item getDropItem() { return this.item; }
	
	private static Random rand = new Random();
    public static ErthiliaDropProbability getRandomDropItem()
    {
		final int chance = rand.nextInt(100 - 1 + 1) + 1;
		final int intervalMiddle = 50;	
		
		ArrayList<ErthiliaDropProbability> itemsInInterval = new ArrayList<ErthiliaDropProbability>();
		for(Entry<ErthiliaDropProbability, Integer> entry : oreProbability.entrySet()) {
			
			ErthiliaDropProbability key = entry.getKey();
		    int value = entry.getValue();

		    if(chance >= intervalMiddle-value/2 && chance <= intervalMiddle+value/2)
		    {
		    	itemsInInterval.add(key);
		    }
		}
		
		ErthiliaDropProbability edp = ErthiliaDropProbability.defaultDropProbability;
		
		if(itemsInInterval.size() > 0) edp = itemsInInterval.get(rand.nextInt(itemsInInterval.size()-1 - 0 + 1) + 0);
		
		return edp;
    }
    
    public static void addItemRandomOre(Item item, int quantity, int dropChance)
    {
    	oreProbability.put(new ErthiliaDropProbability(item,quantity), dropChance);
    }
	
	
}
