/*
 * -------------------------------------------------------------------------------------------------------
 * Class: ClientProxy
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2019.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.proxy;

import it.hurts.metallurgy_reforged.Metallurgy;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;

public class ClientProxy extends CommonProxy{
	
	@Override
    public void registerItemRenderer(Item item, int meta, String id){
    	ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(Metallurgy.MODID + ":" + id, "inventory"));
    }

    @Override
    public void registerItemRenderer(Item item, int meta, String id, String subdirectory)
    {
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(Metallurgy.MODID + ":" + subdirectory + "/" + id, "inventory"));
    }
}
