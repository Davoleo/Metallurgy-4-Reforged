package it.hurts.metallurgy_5.proxy;

import net.minecraft.client.resources.I18n;
import net.minecraft.item.Item;

/***************************
*
* Author : ItHurtsLikeHell
* Project: Metallurgy-5
* Date   : 28 ago 2018
* Time   : 18:24:07
*
***************************/

public class CommonProxy {

	public String localize(String unlocalized, Object... args){
        return I18n.format(unlocalized, args);
    }

    public void registerItemRenderer(Item item, int meta, String id){
    	
    }
}
