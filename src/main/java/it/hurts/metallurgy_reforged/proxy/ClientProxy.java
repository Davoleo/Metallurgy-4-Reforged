package it.hurts.metallurgy_reforged.proxy;

import it.hurts.metallurgy_reforged.Metallurgy;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;

/***************************
*
* Author : ItHurtsLikeHell
* Project: Metallurgy-5
* Date   : 28 ago 2018
* Time   : 18:24:07
*
***************************/

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
