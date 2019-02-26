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

import java.io.File;

import it.hurts.metallurgy_reforged.config.ArmorConfig;
import it.hurts.metallurgy_reforged.config.ToolConfig;
import net.minecraft.item.Item;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod.EventBusSubscriber
public class CommonProxy {
	
	public static Configuration config;
	public static Configuration armorConfig;
	public static Configuration toolConfig;

    public void registerItemRenderer(Item item, int meta, String id){
    	
    }

    public void registerItemRenderer(Item item, int meta, String id, String subdirectory){

    }
    
    public void preInit(FMLPreInitializationEvent e) {
        File directory = e.getModConfigurationDirectory();
        
        armorConfig = new Configuration(new File(directory.getPath(), "metallurgy_reforged\\armor.cfg"));
        ArmorConfig.readConfig(armorConfig);
        
        toolConfig = new Configuration(new File(directory.getPath(), "metallurgy_reforged\\tool.cfg"));
        ToolConfig.readConfig(toolConfig);
    }
    
    public void postInit(FMLPostInitializationEvent e) {
        if(armorConfig.hasChanged())
        	armorConfig.save();
    }
}
