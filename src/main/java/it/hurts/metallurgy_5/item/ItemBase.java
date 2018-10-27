package it.hurts.metallurgy_5.item;

import it.hurts.metallurgy_5.Metallurgy_5;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

/***************************
*
* Author : ItHurtsLikeHell
* Project: Metallurgy-5
* Date   : 28 ago 2018
* Time   : 18:24:07
*
***************************/

public class ItemBase extends Item{

	protected String name;
	
	public ItemBase(String name) {
		this.name = name;
		setUnlocalizedName(name);
		setRegistryName(name);
	}
	
	public void registerItemModel() {
		Metallurgy_5.proxy.registerItemRenderer(this, 0, name);
	}

	@Override
	public ItemBase setCreativeTab(CreativeTabs tab) {
		super.setCreativeTab(tab);
		return this;
	}
	
//	@Mod.EventHandler
//	public void preInit(FMLPreInitializationEvent event) {
//	}
	
}
