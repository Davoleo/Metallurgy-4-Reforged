package it.hurts.metallurgy_reforged.item;

import it.hurts.metallurgy_reforged.Metallurgy;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;

import javax.annotation.Nonnull;

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

	public ItemBase(String name)
    {
		this.name = name;
		setTranslationKey(name);
		setRegistryName(name);
		ModItems.itemList.add(this);
	}
	
	public void registerItemModel()
    {
		Metallurgy.proxy.registerItemRenderer(this, 0, name);
	}

	public void registerItemModel(Item item, int meta, String subdirectory)
	{
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(Metallurgy.MODID + ":" + subdirectory + "/" + name, "inventory"));
	}

	@Nonnull
	@Override
	public ItemBase setCreativeTab(@Nonnull CreativeTabs tab)
    {
		super.setCreativeTab(tab);
		return this;
	}
	
//	@Mod.EventHandler
//	public void preInit(FMLPreInitializationEvent event) {
//	}
	
}
