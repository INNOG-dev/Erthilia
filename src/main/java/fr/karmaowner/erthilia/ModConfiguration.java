package fr.karmaowner.erthilia;

import java.io.File;

import fr.karmaowner.erthilia.blocks.ErthiliaMysteryBlock;
import fr.karmaowner.erthilia.entity.monster.EntityDragon;
import fr.karmaowner.erthilia.entity.monster.EntityDragonBlack;
import fr.karmaowner.erthilia.entity.monster.EntityUnicorn;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.relauncher.Side;

public class ModConfiguration {
	
	private Configuration cfg;
	private Side networkSide;
	
	public String licenceKey;
	


	public ModConfiguration(File file, Side side)
	{
    	cfg = new Configuration(file);
    	networkSide = side;
	}
	
	public void saveConfiguration()
	{
		if(networkSide.isClient())
		{
			if(cfg.getCategory("general").get("particles_multiplier") != null) cfg.getCategory("general").get("particles_multiplier").set(ClientProxy.getParticlesHandler().multiplier);
			if(cfg.getCategory("general").get("particles_enabled") != null)	cfg.getCategory("general").get("particles_enabled").set(ClientProxy.getParticlesHandler().isEnabled());
			if(cfg.getCategory("general").get("particles_multiply_animals") != null) cfg.getCategory("general").get("particles_multiply_animals").set(ClientProxy.getParticlesHandler().isMultiplyOnAnimals());
			if(cfg.getCategory("general").get("particles_multiply_without_crits") != null) cfg.getCategory("general").get("particles_multiply_without_crits").set(ClientProxy.getParticlesHandler().isMultiplyWithoutCrits());
			if(cfg.getCategory("general").get("armoreffect_current_theme") != null) cfg.getCategory("general").get("armoreffect_current_theme").set(ClientProxy.armoreffectConfig.getCurrentTheme());
			if(cfg.getCategory("general").get("armoreffect_opacity") != null) cfg.getCategory("general").get("armoreffect_opacity").set(ClientProxy.armoreffectConfig.getOpacity());
			if(cfg.getCategory("general").get("armoreffect_enabled") != null) cfg.getCategory("general").get("armoreffect_enabled").set(ClientProxy.armoreffectConfig.getIsEnabled());
			if(cfg.getCategory("general").get("togglesprint_opacity") != null) cfg.getCategory("general").get("togglesprint_opacity").set(ClientProxy.getToggleSprintHandler().config.getOpacity());
			if(cfg.getCategory("general").get("togglesprint_enabled") != null) cfg.getCategory("general").get("togglesprint_enabled").set(ClientProxy.getToggleSprintHandler().config.getIsEnabled());
			if(cfg.getCategory("general").get("potionhud_opacity") != null) cfg.getCategory("general").get("potionhud_opacity").set(ClientProxy.getPotionHudHandler().config.getOpacity());
			if(cfg.getCategory("general").get("potionhud_enabled") != null) cfg.getCategory("general").get("potionhud_enabled").set(ClientProxy.getPotionHudHandler().config.getIsEnabled());
			if(cfg.getCategory("general").get("keystrokes_enabled") != null) cfg.getCategory("general").get("keystrokes_enabled").set(ClientProxy.getKeyStrokesHandler().config.getIsEnabled());
			if(cfg.getCategory("general").get("keystrokes_opacity") != null) cfg.getCategory("general").get("keystrokes_opacity").set(ClientProxy.getKeyStrokesHandler().config.getOpacity());
			if(cfg.getCategory("general").get("keystrokes_chrome_enabled") != null) cfg.getCategory("general").get("keystrokes_chrome_enabled").set(ClientProxy.getKeyStrokesHandler().config.getChromeColor());
			if(cfg.getCategory("general").get("keystrokes_contour_enabled") != null) cfg.getCategory("general").get("keystrokes_contour_enabled").set(ClientProxy.getKeyStrokesHandler().config.isContourActive());
			if(cfg.getCategory("general").get("keystrokes_key_color") != null) cfg.getCategory("general").get("keystrokes_key_color").set(ClientProxy.getKeyStrokesHandler().config.getKeyColor());
		}
		
		if(cfg.hasChanged())
        {
            cfg.save();
        }
	}
	
	public void loadConfiguration()
	{
		try {
			cfg.load();
						
			if(networkSide.isClient())
	        {
				ClientProxy.getParticlesHandler().multiplier = cfg.getInt("particles_multiplier", Configuration.CATEGORY_GENERAL, 1, 1,100, "Multiplicateur de particules");
				ClientProxy.getParticlesHandler().setEnabled(cfg.getBoolean("particles_enabled", Configuration.CATEGORY_GENERAL, true, "Activer le particle mod ?"));
				ClientProxy.getParticlesHandler().setMultiplyOnAnimals(cfg.getBoolean("particles_multiply_animals", Configuration.CATEGORY_GENERAL, true, "Activer le multiplicateur de particules pour les animaux ?"));
				ClientProxy.getParticlesHandler().setMultiplyWithoutCrits(cfg.getBoolean("particles_multiply_without_crits", Configuration.CATEGORY_GENERAL, false, "Activer le multiplicateur même s'il n'y a pas de coup critique"));
				ClientProxy.armoreffectConfig.setCurrentTheme(cfg.getInt("armoreffect_current_theme", Configuration.CATEGORY_GENERAL, 1, 1, ClientProxy.armoreffectConfig.getThemeCount(), "Théme armoreffect"));
				ClientProxy.armoreffectConfig.setOpacity(cfg.getInt("armoreffect_opacity", Configuration.CATEGORY_GENERAL, 100, 1, 100, "Opacité armoreffect"));
				ClientProxy.armoreffectConfig.setEnabled(cfg.getBoolean("armoreffect_enabled", Configuration.CATEGORY_GENERAL, true, "Activer l'armoreffect"));
				ClientProxy.getToggleSprintHandler().config.setOpacity(cfg.getInt("togglesprint_opacity", Configuration.CATEGORY_GENERAL, 100, 1, 100, "Opacité togglesprint"));
				ClientProxy.getToggleSprintHandler().config.setEnabled(cfg.getBoolean("togglesprint_enabled", Configuration.CATEGORY_GENERAL, true, "Activer le togglesprint"));
				ClientProxy.getPotionHudHandler().config.setOpacity(cfg.getInt("potionhud_opacity", Configuration.CATEGORY_GENERAL, 100, 1, 100, "Opacité potionhud"));
				ClientProxy.getPotionHudHandler().config.setEnabled(cfg.getBoolean("potionhud_enabled", Configuration.CATEGORY_GENERAL, true, "Activer le potionhud"));
				ClientProxy.getKeyStrokesHandler().config.setEnabled(cfg.getBoolean("keystrokes_enabled", Configuration.CATEGORY_GENERAL, true, "Activer le keystrokes"));
				ClientProxy.getKeyStrokesHandler().config.setOpacity(cfg.getInt("keystrokes_opacity", Configuration.CATEGORY_GENERAL, 100, 1, 100, "Opacité keystrokes"));
				ClientProxy.getKeyStrokesHandler().config.setChromeColor(cfg.getBoolean("keystrokes_chrome_enabled", Configuration.CATEGORY_GENERAL, false, "Couleur chrome activé"));
				ClientProxy.getKeyStrokesHandler().config.setContour(cfg.getBoolean("keystrokes_contour_enabled", Configuration.CATEGORY_GENERAL, false, "Contour keystrokes activé"));
				ClientProxy.getKeyStrokesHandler().config.setKeyColor(cfg.getString("keystrokes_key_color", Configuration.CATEGORY_GENERAL, "#FFFFFF", "Couleur touche keystrokes"));
				
				GameInitializer.dropsData.get(EntityDragon.class).setDrop(cfg.getStringList("dragon_drops", Configuration.CATEGORY_GENERAL, GameInitializer.dropsData.get(EntityDragon.class).getDefaultDropsToArray(), "Drops du dragon rouge"));
				GameInitializer.dropsData.get(EntityDragonBlack.class).setDrop(cfg.getStringList("blackdragon_drops", Configuration.CATEGORY_GENERAL, GameInitializer.dropsData.get(EntityDragonBlack.class).getDefaultDropsToArray(), "Drops du dragon noir"));
				GameInitializer.dropsData.get(EntityUnicorn.class).setDrop(cfg.getStringList("unicorn_drops", Configuration.CATEGORY_GENERAL, GameInitializer.dropsData.get(EntityUnicorn.class).getDefaultDropsToArray(), "Drops du licorne"));
				GameInitializer.dropsData.get(ErthiliaMysteryBlock.class).setDrop(cfg.getStringList("blockmystery_drops", Configuration.CATEGORY_GENERAL, GameInitializer.dropsData.get(ErthiliaMysteryBlock.class).getDefaultDropsToArray(), "Drops du block mystère"));

				
				GameInitializer.dropsData.get(EntityDragon.class).loadDatas();
				GameInitializer.dropsData.get(EntityDragonBlack.class).loadDatas();
				GameInitializer.dropsData.get(EntityUnicorn.class).loadDatas();
				GameInitializer.dropsData.get(ErthiliaMysteryBlock.class).loadDatas();

	        }
			else
			{
				
	       	 	licenceKey = cfg.getString("LicenceKey", Configuration.CATEGORY_GENERAL, "entrez ici la clée", "Entrez la clee de licence pour verifier que vous avez bien paye le produit");
	   	 	}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
}
