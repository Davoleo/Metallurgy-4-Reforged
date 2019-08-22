/*
 * -------------------------------------------------------------------------------------------------------
 * Class: CommonProxy
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2019.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.proxy;

import it.hurts.metallurgy_reforged.Metallurgy;
import it.hurts.metallurgy_reforged.config.ArmorConfig;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig.Type;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

//@Mod.EventBusSubscriber
public class CommonProxy {
	
//	public static Configuration config;
//	public static Configuration armorConfig;
//	public static Configuration toolConfig;

    public void preInit(FMLCommonSetupEvent e) {
    	
    	//ModLoadingContext.get().registerConfig(Type.COMMON, new ForgeConfigSpec.Builder().build(), Metallurgy.MODID + "/tool.toml");
    	
//        File directory = e.getModConfigurationDirectory();
//        
//        armorConfig = new Configuration(new File(directory.getPath(), "metallurgy_reforged\\armor.cfg"));
//        ArmorConfig.readConfig(armorConfig);
//        
//        toolConfig = new Configuration(new File(directory.getPath(), "metallurgy_reforged\\tool.cfg"));
//        ToolConfig.readConfig(toolConfig);
    }
    
    public void postInit() {
//        if(armorConfig.hasChanged())
//        	armorConfig.save();
    }
}
