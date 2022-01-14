package fr.karmaowner.erthilia.data;

import java.io.File;
import java.io.IOException;

import fr.karmaowner.erthilia.ClientProxy;
import fr.karmaowner.erthilia.utils.FileEditor;

public class GuiOverlaySettings {
	
	private File settingsFile = new File("GuiOverlaySettings.txt");
	
	private int screenWidth;
	private int screenHeight;
	
	public final int defaultArmorEffectPosX;
	public final int defaultArmorEffectPosY;
	public final int defaultArmorEffectWidth;
	public final int defaultArmorEffectHeight;
	
	public final int defaultBossHudPosX;
	public final int defaultBossHudPosY;
	public final int defaultBossHudWidth;
	public final int defaultBossHudHeight;
	
	public final int defaultToggleSprintPosX;
	public final int defaultToggleSprintPosY;
	public final int defaultToggleSprintHeight;
	
	public final int defaultPotionHudPosX;
	public final int defaultPotionHudPosY;
	
	public final int defaultKeystrokesPosX;
	public final int defaultKeystrokesPosY;
	
	public int armorEffectPosX;
	public int armorEffectPosY;
	public int armorEffectWidth;
	public int armorEffectHeight;
	public float armorEffectScale;
	
	public int toggleSprintPosX;
	public int toggleSprintPosY;
	public int toggleSprintWidth;
	public int toggleSprintHeight;
	public float toggleSprintScale;
	
	public int potionHudPosX;
	public int potionHudPosY;
	public int potionHudWidth;
	public int potionHudHeight;
	public float potionHudScale;
	
	public int keystrokesPosX;
	public int keystrokesPosY;
	public int keystrokesWidth;
	public int keystrokesHeight;
	public float keystrokesScale;
	
	public int bossHudPosX;
	public int bossHudPosY;
	public int bossHudWidth;
	public int bossHudHeight;
	public float bossHudScale;
	
	public int saveWidth;
	public int saveHeight;
	

	public GuiOverlaySettings(int width, int height)
	{
		this.screenWidth = width;
		this.screenHeight = height;
		
		this.defaultToggleSprintPosX = 0;
		this.defaultToggleSprintPosY = 0;
		this.defaultToggleSprintHeight = 10;
		
		this.defaultArmorEffectPosX = 0;
		this.defaultArmorEffectPosY = 14;
		this.defaultArmorEffectWidth = 50;
		this.defaultArmorEffectHeight = 70;
		
		this.defaultBossHudPosX = 0;
		this.defaultBossHudPosY = 14;
		this.defaultBossHudWidth = 70;
		this.defaultBossHudHeight = 70;	
		
		this.defaultPotionHudPosX = 6;
		this.defaultPotionHudPosY = 110;
		
		this.defaultKeystrokesPosX = screenWidth - 53;
		this.defaultKeystrokesPosY = 0;
		
		
		if(!settingsFile.exists())
		{
			
			try {
				settingsFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			armorEffectPosX = defaultArmorEffectPosX;
			armorEffectPosY = defaultArmorEffectPosY;
			armorEffectWidth = defaultArmorEffectWidth;
			armorEffectHeight = defaultArmorEffectHeight;
			armorEffectScale = 1f;
			
			toggleSprintPosX = defaultToggleSprintPosX;
			toggleSprintPosY = defaultToggleSprintPosY;
			toggleSprintWidth = ClientProxy.getToggleSprintHandler().getWidth();
			toggleSprintHeight = defaultToggleSprintHeight;
			toggleSprintScale = 1f;
			
			potionHudPosX = defaultPotionHudPosX;
			potionHudPosY = defaultPotionHudPosY;
			potionHudWidth = ClientProxy.getPotionHudHandler().getWidth();
			potionHudHeight = ClientProxy.getPotionHudHandler().getHeight();
			potionHudScale = 0.8f;
			
			keystrokesPosX = defaultKeystrokesPosX;
			keystrokesPosY = defaultKeystrokesPosY;
			keystrokesWidth = ClientProxy.getKeyStrokesHandler().getKeyContainer().getWidth();
			keystrokesHeight = ClientProxy.getKeyStrokesHandler().getKeyContainer().getHeight();
			keystrokesScale = 1f;
			
			bossHudPosX = defaultBossHudPosX;
			bossHudPosY = defaultBossHudPosY;
			bossHudWidth = defaultBossHudWidth;
			bossHudHeight = defaultBossHudHeight;
			bossHudScale = 1f;
			
		}
		else if(settingsFile.length() <= 0)
		{
			armorEffectPosX = defaultArmorEffectPosX;
			armorEffectPosY = defaultArmorEffectPosY;
			armorEffectWidth = defaultArmorEffectWidth;
			armorEffectHeight = defaultArmorEffectHeight;
			armorEffectScale = 1f;
			
			toggleSprintPosX = defaultToggleSprintPosX;
			toggleSprintPosY = defaultToggleSprintPosY;
			toggleSprintWidth = ClientProxy.getToggleSprintHandler().getWidth();
			toggleSprintHeight = defaultToggleSprintHeight;
			toggleSprintScale = 1f;
			
			potionHudPosX = defaultPotionHudPosX;
			potionHudPosY = defaultPotionHudPosY;
			potionHudWidth = ClientProxy.getPotionHudHandler().getWidth();
			potionHudHeight = ClientProxy.getPotionHudHandler().getHeight();
			potionHudScale = 0.8f;
			
			keystrokesPosX = defaultKeystrokesPosX;
			keystrokesPosY = defaultKeystrokesPosY;
			keystrokesWidth = ClientProxy.getKeyStrokesHandler().getKeyContainer().getWidth();
			keystrokesHeight = ClientProxy.getKeyStrokesHandler().getKeyContainer().getHeight();
			keystrokesScale = 1f;
			
			bossHudPosX = defaultBossHudPosX;
			bossHudPosY = defaultBossHudPosY;
			bossHudWidth = defaultBossHudWidth;
			bossHudHeight = defaultBossHudHeight;
			bossHudScale = 1f;
		}
		else
		{
			loadData();
		}
	}
	
	public void loadData()
	{
		try {
			FileEditor editor = new FileEditor(settingsFile);
			this.saveWidth = editor.getInt("SaveWidth");
			this.saveHeight = editor.getInt("SaveHeight");
			this.armorEffectPosX = positionXFix(editor.getInt("ArmorEffectPosX"));
			this.armorEffectPosY = positionYFix(editor.getInt("ArmorEffectPosY"));
			this.armorEffectWidth = editor.getInt("ArmorEffectWidth");
			this.armorEffectHeight = editor.getInt("ArmorEffectHeight");
			this.armorEffectScale = editor.getFloat("ArmorEffectScale");
			this.toggleSprintPosX = positionXFix(editor.getInt("ToggleSprintPosX"));
			this.toggleSprintPosY = positionYFix(editor.getInt("ToggleSprintPosY"));
			this.toggleSprintWidth = editor.getInt("ToggleSprintWidth");
			this.toggleSprintHeight = editor.getInt("ToggleSprintHeight");
			this.toggleSprintScale = editor.getFloat("ToggleSprintScale");
			this.potionHudPosX = positionXFix(editor.getInt("PotionHudPosX"));
			this.potionHudPosY = positionYFix(editor.getInt("PotionHudPosY"));
			this.potionHudWidth = editor.getInt("PotionHudWidth");
			this.potionHudHeight = editor.getInt("PotionHudHeight");
			this.potionHudScale = editor.getFloat("PotionHudScale");
			this.keystrokesPosX = positionXFix(editor.getInt("KeystrokesPosX"));
			this.keystrokesPosY = positionYFix(editor.getInt("KeystrokesPosY"));
			this.keystrokesWidth = editor.getInt("KeystrokesWidth");
			this.keystrokesHeight = editor.getInt("KeystrokesHeight");
			this.keystrokesScale = editor.getFloat("KeystrokesScale");
			this.bossHudPosX = editor.getInt("BossHudPosX");
			this.bossHudPosY = editor.getInt("BossHudPosY");
			this.bossHudWidth = editor.getInt("BossHudWidth");
			this.bossHudHeight = editor.getInt("BossHudHeight");
			this.bossHudScale = editor.getFloat("BossHudScale");
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public int positionXFix(int posX)
	{
		return (int) (posX * ((float)screenWidth / (float)saveWidth));
	}
	
	public int positionYFix(int posY)
	{
		return (int) (posY * ((float)screenHeight / (float)saveHeight));
	}
	
	public void saveData()
	{
		FileEditor editor;
		try {
			editor = new FileEditor(settingsFile);
			
			editor.reset();
			
			editor.write("ArmorEffectPosX" , armorEffectPosX);
			editor.write("ArmorEffectPosY" , armorEffectPosY);
			editor.write("ArmorEffectWidth" , armorEffectWidth);
			editor.write("ArmorEffectHeight" , armorEffectHeight);
			editor.write("ArmorEffectScale" , armorEffectScale);
			editor.write("ToggleSprintPosX" , toggleSprintPosX);
			editor.write("ToggleSprintPosY" , toggleSprintPosY);
			editor.write("ToggleSprintWidth" , toggleSprintWidth);
			editor.write("ToggleSprintHeight" , toggleSprintHeight);
			editor.write("ToggleSprintScale" , toggleSprintScale);
			editor.write("PotionHudPosX" , potionHudPosX);
			editor.write("PotionHudPosY" , potionHudPosY);
			editor.write("PotionHudWidth" , potionHudWidth);
			editor.write("PotionHudHeight" , potionHudHeight);
			editor.write("PotionHudScale" , potionHudScale);
			editor.write("KeystrokesPosX" , keystrokesPosX);
			editor.write("KeystrokesPosY" , keystrokesPosY);
			editor.write("KeystrokesWidth" , keystrokesWidth);
			editor.write("KeystrokesHeight" , keystrokesHeight);
			editor.write("KeystrokesScale" , keystrokesScale);
			editor.write("BossHudPosX" , bossHudPosX);
			editor.write("BossHudPosY" , bossHudPosY);
			editor.write("BossHudWidth" , bossHudWidth);
			editor.write("BossHudHeight" , bossHudHeight);
			editor.write("BossHudScale" , bossHudScale);
			editor.write("SaveWidth", screenWidth);
			editor.write("SaveHeight", screenHeight);
			
			editor.writeDataToFile();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void refreshData()
	{
		this.loadData();
	}
	

	public void resetData()
	{
		armorEffectPosX = defaultArmorEffectPosX;
		armorEffectPosY = defaultArmorEffectPosY;
		armorEffectWidth = defaultArmorEffectWidth;
		armorEffectHeight = defaultArmorEffectHeight;
		armorEffectScale = 1f;
		
		toggleSprintPosX = defaultToggleSprintPosX;
		toggleSprintPosY = defaultToggleSprintPosY;
		toggleSprintWidth = ClientProxy.getToggleSprintHandler().getWidth();
		toggleSprintHeight = defaultToggleSprintHeight;
		toggleSprintScale = 1f;
		
		potionHudPosX = defaultPotionHudPosX;
		potionHudPosY = defaultPotionHudPosY;
		potionHudWidth = ClientProxy.getPotionHudHandler().getWidth();
		potionHudHeight = ClientProxy.getPotionHudHandler().getHeight();
		potionHudScale = 0.8f;
		
		keystrokesPosX = defaultKeystrokesPosX;
		keystrokesPosY = defaultKeystrokesPosY;
		keystrokesWidth = ClientProxy.getKeyStrokesHandler().getKeyContainer().getWidth();
		keystrokesHeight = ClientProxy.getKeyStrokesHandler().getKeyContainer().getHeight();
		keystrokesScale = 1f;
		
		bossHudPosX = defaultBossHudPosX;
		bossHudPosY = defaultBossHudPosY;
		bossHudWidth = defaultBossHudWidth;
		bossHudHeight = defaultBossHudHeight;
		bossHudScale = 1f;
		
		saveData();
	}
	
}
