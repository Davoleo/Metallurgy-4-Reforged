package it.hurts.metallurgy_reforged.proxy;

import java.io.File;

import it.hurts.metallurgy_reforged.config.ArmorConfig;
import it.hurts.metallurgy_reforged.config.GeneralConfig;
import net.minecraft.item.Item;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/***************************
*
* Author : ItHurtsLikeHell
* Project: Metallurgy-5
* Date   : 28 ago 2018
* Time   : 18:24:07
*
***************************/
@Mod.EventBusSubscriber
public class CommonProxy {
	
	public static Configuration config;
	public static Configuration armorConfig;

    public void registerItemRenderer(Item item, int meta, String id){
    	
    }

    public void registerItemRenderer(Item item, int meta, String id, String subdirectory){

    }
    
    public void preInit(FMLPreInitializationEvent e) {
        File directory = e.getModConfigurationDirectory();
        config = new Configuration(new File(directory.getPath(), "metallurgy_reforged\\general.cfg"));
        GeneralConfig.readConfig(config);
        
        armorConfig = new Configuration(new File(directory.getPath(), "metallurgy_reforged\\armor.cfg"));
        ArmorConfig.readConfig(armorConfig);
    }
    
    public void postInit(FMLPostInitializationEvent e) {
        if (config.hasChanged())
            config.save();
        if(armorConfig.hasChanged())
        	armorConfig.save();
    }
}
