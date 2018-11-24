package it.hurts.metallurgy_5.proxy;

import it.hurts.metallurgy_5.Metallurgy_5;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.resources.I18n;
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

@SuppressWarnings("unused")
public class ClientProxy extends CommonProxy{
	
	@Override
    public void registerItemRenderer(Item item, int meta, String id){
    	ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(Metallurgy_5.MODID + ":" + id, "inventory"));
    }
    
}
