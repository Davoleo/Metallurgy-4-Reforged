package it.hurts.metallurgy_5.item;

import it.hurts.metallurgy_5.util.MetallurgyTabs;
import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.ArrayList;
import java.util.List;

/***************************
*
* Author : ItHurtsLikeHell
* Project: Metallurgy-5
* Date   : 28 ago 2018
* Time   : 18:24:07
*
***************************/

public class ModItems {
	public static List<ItemBase> itemList = new ArrayList<>();

	public static ItemOreDict dustGold = new ItemOreDict("gold_dust", "dustGold").setCreativeTab(MetallurgyTabs.tabDust);
	public static ItemOreDict dustIron = new ItemOreDict("iron_dust", "dustIron").setCreativeTab(MetallurgyTabs.tabDust);

	public static ItemOreDict dustBitumen = new ItemOreDict("bitumen","dustBitumen").setCreativeTab(MetallurgyTabs.tabDust);
	public static ItemOreDict tar = new ItemOreDict("tar","oreslimeball").setCreativeTab(MetallurgyTabs.tabDust);
	public static ItemOreDict dustPotash = new ItemOreDict("potash","dustPotash").setCreativeTab(MetallurgyTabs.tabDust);
	public static ItemOreDict dustSulfur = new ItemOreDict("sulfur_dust","dustSulfur").setCreativeTab(MetallurgyTabs.tabDust);
	public static ItemOreDict dustPhosphorus = new ItemOreDict("phosphorus", "dustPhosphorus").setCreativeTab(MetallurgyTabs.tabDust);

//	public static ItemOreDict  = new ItemOreDict ("");

	public static void register(IForgeRegistry<Item> registry) {
		registry.registerAll(dustGold, dustIron, dustBitumen, tar, dustPotash, dustSulfur, dustPhosphorus);
	}
	
	public static void registerModels() {
		dustGold.registerItemModel();
		dustIron.registerItemModel();

		dustBitumen.registerItemModel();
		tar.registerItemModel();
		dustPotash.registerItemModel();
		dustSulfur.registerItemModel();
		dustPhosphorus.registerItemModel();
	}
}