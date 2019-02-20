package it.hurts.metallurgy_reforged.item;

import it.hurts.metallurgy_reforged.item.gadgets.ItemIgnatiusLighter;
import it.hurts.metallurgy_reforged.item.gadgets.ItemVulcaniteLighter;
import it.hurts.metallurgy_reforged.item.gadgets.gauntlet.ItemGauntlet;
import it.hurts.metallurgy_reforged.util.MetallurgyTabs;
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

//	Vanilla dust
	public static ItemOreDict dustGold = new ItemOreDict("gold_dust", "dustGold").setCreativeTab(MetallurgyTabs.tabDust);
	public static ItemOreDict dustIron = new ItemOreDict("iron_dust", "dustIron").setCreativeTab(MetallurgyTabs.tabDust);
	
//	Metallurgy dust
	public static ItemOreDict dustBitumen = new ItemOreDict("bitumen","dustBitumen").setCreativeTab(MetallurgyTabs.tabDust);
	public static ItemOreDict tar = new ItemOreDict("tar","slimeball").setCreativeTab(MetallurgyTabs.tabDust);
	public static ItemOreDict dustPotash = new ItemOreDict("potash","dustPotash").setCreativeTab(MetallurgyTabs.tabDust);
	public static ItemOreDict dustSulfur = new ItemOreDict("sulfur_dust","dustSulfur").setCreativeTab(MetallurgyTabs.tabDust);
	public static ItemOreDict dustThermite = new ItemOreDict("thermite_dust", "dustThermite").setCreativeTab(MetallurgyTabs.tabDust);
	public static ItemOreDict dustPhosphorus = new ItemOreDict("phosphorus", "dustPhosphorus").setCreativeTab(MetallurgyTabs.tabDust);
  
//	Lightner
	public static ItemIgnatiusLighter flintAndIgnatius = new ItemIgnatiusLighter("flint_and_ignatius");
	public static ItemVulcaniteLighter flintAndVulcanite = new ItemVulcaniteLighter("flint_and_vulcanite");
	
//	Modded Items
	public static ItemGauntlet gauntlet = new ItemGauntlet("rubracium_gauntlet");

	

//	public static ItemOreDict  = new ItemOreDict ("");

	public static void register(IForgeRegistry<Item> registry) {
		registry.registerAll(dustGold, dustIron, dustBitumen, tar, dustPotash, dustSulfur, 
				dustPhosphorus, flintAndIgnatius, flintAndVulcanite, gauntlet, dustThermite);
	}
	
	public static void registerModels() {
		
//		Vanilla dust
		dustGold.registerItemModel();
		dustIron.registerItemModel();

//		Metallurgy dust
		dustBitumen.registerItemModel();
		tar.registerItemModel();
		dustPotash.registerItemModel();
		dustSulfur.registerItemModel();
		dustThermite.registerItemModel();
		dustPhosphorus.registerItemModel();
		
//		Lightner
		flintAndIgnatius.registerItemModel();
		flintAndVulcanite.registerItemModel();
		
//		Modded Items
		gauntlet.registerItemModel();
	}
}