/*
 * -------------------------------------------------------------------------------------------------------
 * Class: ModItems
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2019.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.item;

import it.hurts.metallurgy_reforged.item.gadgets.ItemIgnatiusLighter;
import it.hurts.metallurgy_reforged.item.gadgets.ItemInvisibilityShield;
import it.hurts.metallurgy_reforged.item.gadgets.ItemVulcaniteLighter;
import it.hurts.metallurgy_reforged.item.gadgets.gauntlet.ItemGauntlet;
import it.hurts.metallurgy_reforged.util.Constants;
import it.hurts.metallurgy_reforged.util.IHasModel;
import it.hurts.metallurgy_reforged.util.MetallurgyTabs;
import it.hurts.metallurgy_reforged.util.Utils;
import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.ArrayList;
import java.util.List;

public class ModItems {
	public static List<Item> itemList = new ArrayList<>();

//	Vanilla dust
	public static ItemOreDict dustGold = new ItemOreDict("gold_dust", "dustGold").setCreativeTab(MetallurgyTabs.tabDust);
	public static ItemOreDict dustIron = new ItemOreDict("iron_dust", "dustIron").setCreativeTab(MetallurgyTabs.tabDust);
	
//	Metallurgy dust
	public static ItemOreDict dustBitumen = new ItemOreDict("bitumen","dustBitumen").setCreativeTab(MetallurgyTabs.tabDust).setTooltip(Constants.BITUMEN);
	public static ItemOreDict tar = new ItemOreDict("tar","slimeball").setCreativeTab(MetallurgyTabs.tabDust);
	public static ItemOreDict dustPotash = new ItemOreDict("potash","dustPotash").setCreativeTab(MetallurgyTabs.tabDust);
	public static ItemOreDict dustSulfur = new ItemOreDict("sulfur_dust","dustSulfur").setCreativeTab(MetallurgyTabs.tabDust);
	public static ItemOreDict dustThermite = new ItemOreDict("thermite_dust", "dustThermite").setCreativeTab(MetallurgyTabs.tabDust);
	public static ItemOreDict dustPhosphorus = new ItemOreDict("phosphorus", "dustPhosphorus").setCreativeTab(MetallurgyTabs.tabDust);
  
//	Gadgets
	public static ItemIgnatiusLighter flintAndIgnatius = new ItemIgnatiusLighter("flint_and_ignatius");
	public static ItemVulcaniteLighter flintAndVulcanite = new ItemVulcaniteLighter("flint_and_vulcanite");
	public static ItemGauntlet gauntlet = new ItemGauntlet("rubracium_gauntlet");
	public static ItemInvisibilityShield invisibilityShield = new ItemInvisibilityShield();

	public static void register(IForgeRegistry<Item> registry) {
		for (Item item : itemList)
			registry.register(item);
	}

	@SideOnly(Side.CLIENT)
	public static void registerModels() {

		for (Item item : itemList)
		{
			if (item instanceof IHasModel)
				Utils.registerCustomItemModel(item, 0, ((IHasModel) item).getCategory());
		}
	}
}