package it.hurts.metallurgy_5.item;

import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;

/***************************
*
* Author : ItHurtsLikeHell
* Project: Metallurgy-5
* Date   : 28 ago 2018
* Time   : 18:24:07
*
***************************/

public class ModItems {
	
	//Other resources
	public static ItemOre dustBitumen = new ItemOre ("bitumen_dust","dustBitumen");
	public static ItemOre oreslimeball = new ItemOre ("tar","oreslimeball");	 
	public static ItemOre dustPotash = new ItemOre ("potash_dust","dustPotash");
	public static ItemOre dustSulfur = new ItemOre ("sulfur_dust","dustSulfur");
	public static ItemOre dustPhosphorus = new ItemOre("phosphorus_dust", "dustPhosphorus");	 
	
	//Ingot
	public static ItemOre ingotAdamantine = new ItemOre ("adamantine_ingot","ingotAdamantine");
	public static ItemOre ingotAlduorite = new ItemOre ("alduorite_ingot","ingotAlduorite");
	public static ItemOre ingotAmordrine = new ItemOre ("amordrine_ingot","ingotAmordrine");
	public static ItemOre ingotAngmallen = new ItemOre ("angmallen_ingot","ingotAngmallen");
	public static ItemOre ingotAstralSilver = new ItemOre ("astral_silver_ingot","ingotAstralSilver");
	public static ItemOre ingotAtlarus = new ItemOre ("atlarus_ingot","ingotAtlarus");
	public static ItemOre ingotBlackSteel = new ItemOre ("black_steel_ingot","ingotBlackSteel");
	public static ItemOre ingotBrass = new ItemOre ("brass_ingot","ingotBrass");
	public static ItemOre ingotBronze = new ItemOre ("bronze_ingot","ingotBronze");
	public static ItemOre ingotCarmot = new ItemOre ("carmot_ingot","ingotCarmot");
	public static ItemOre ingotCelenegil = new ItemOre ("celenegil_ingot","ingotCelenegil");
	public static ItemOre ingotCeruclase = new ItemOre ("ceruclase_ingot","ingotCeruclase");
	public static ItemOre ingotCopper = new ItemOre ("copper_ingot","ingotCopper");
	public static ItemOre ingotDamascusSteel = new ItemOre ("damascus_steel_ingot","ingotDamascusSteel");
	public static ItemOre ingotDeepIron = new ItemOre ("deep_iron_ingot","ingotDeepIron");
	public static ItemOre ingotDesichalkos = new ItemOre ("desichalkos_ingot","ingotDesichalkos");
	public static ItemOre ingotElectrum = new ItemOre ("electrum_ingot","ingotElectrum");
	public static ItemOre ingotEximite = new ItemOre ("eximite_ingot","ingotEximite");
	public static ItemOre ingotHaderoth = new ItemOre ("haderoth_ingot","ingotHaderoth");
	public static ItemOre ingotHepatizon = new ItemOre ("hepatizon_ingot","ingotHepatizon");
	public static ItemOre ingotIgnatius = new ItemOre ("ignatius_ingot","ingotIgnatius");
	public static ItemOre ingotInfuscolium = new ItemOre ("infuscolium_ingot","ingotInfuscolium");
	public static ItemOre ingotInolashite = new ItemOre ("inolashite_ingot","ingotInolashite");
	public static ItemOre ingotKalendrite = new ItemOre ("kalendrite_ingot","ingotKalendrite");
	public static ItemOre ingotLemurite = new ItemOre ("lemurite_ingot","ingotLemurite");
//	public static ItemOre ingotLetutium = new ItemOre ("letutium_ingot","ingotLetutium");
	public static ItemOre ingotManganese = new ItemOre ("manganese_ingot","ingotManganese");
	public static ItemOre ingotMeutoite = new ItemOre ("meutoite_ingot","ingotMeutoite");
	public static ItemOre ingotMidasium = new ItemOre ("midasium_ingot","ingotMidasium");
	public static ItemOre ingotMithril = new ItemOre ("mithril_ingot","ingotMithril");
	public static ItemOre ingotOrichalcum = new ItemOre ("orichalcum_ingot","ingotOrichalcum");
//	public static ItemOre ingotOsmium = new ItemOre ("osmium_ingot","ingotOsmium");
	public static ItemOre ingotOureclase = new ItemOre ("oureclase_ingot","ingotOureclase");
	public static ItemOre ingotPlatinum = new ItemOre ("platinum_ingot","ingotPlatinum");
	public static ItemOre ingotPrometheum = new ItemOre ("prometheum_ingot","ingotPrometheum");
	public static ItemOre ingotQuickSilver = new ItemOre ("quicksilver_ingot","ingotQuickSilver");
	public static ItemOre ingotRubracium = new ItemOre ("rubracium_ingot","ingotRubracium");
	public static ItemOre ingotSanguinite = new ItemOre ("sanguinite_ingot","ingotSanguinite");
	public static ItemOre ingotShadowIron = new ItemOre ("shadow_iron_ingot","ingotShadowIron");
	public static ItemOre ingotShadowSteel = new ItemOre ("shadow_steel_ingot","ingotShadowSteel");
	public static ItemOre ingotSilver = new ItemOre("silver_ingot", "ingotSilver");
	public static ItemOre ingotSteel = new ItemOre ("steel_ingot","ingotSteel");
	public static ItemOre ingotTartarite = new ItemOre ("tartarite_ingot","ingotTartarite");
	public static ItemOre ingotTin = new ItemOre ("tin_ingot","ingotTin");
	public static ItemOre ingotVulcanite = new ItemOre ("vulcanite_ingot","ingotVulcanite");
	public static ItemOre ingotVyroxeres = new ItemOre ("vyroxeres_ingot","ingotVyroxeres");
	public static ItemOre ingotZinc = new ItemOre ("zinc_ingot","ingotZinc ");		
	
//	public static ItemOre  = new ItemOre ("");		

	public static void register(IForgeRegistry<Item> registry) {
		registry.registerAll(
				ingotAdamantine,
				ingotAlduorite,
				ingotAmordrine,
				ingotAngmallen,
				ingotAstralSilver,
				ingotAtlarus,
				ingotBlackSteel,
				ingotBrass,
				ingotBronze,
				ingotCarmot,
				ingotCelenegil,
				ingotCeruclase,
				ingotCopper,
				ingotDamascusSteel,
				ingotDeepIron,
				ingotDesichalkos,
				ingotElectrum,
				ingotEximite,
				ingotHaderoth,
				ingotHepatizon,
				ingotIgnatius,
				ingotInfuscolium,
				ingotInolashite,
				ingotKalendrite,
				ingotLemurite,
//				ingotLetutium,
				ingotManganese,
				ingotMeutoite,
				ingotMidasium,
				ingotMithril,
				ingotOrichalcum,
//				ingotOsmium,
				ingotOureclase,
				ingotPlatinum,
				ingotPrometheum,
				ingotQuickSilver,
				ingotRubracium,
				ingotSanguinite,
				ingotShadowIron,
				ingotShadowSteel,
				ingotSilver,
				ingotSteel,
				ingotTartarite,
				ingotTin,
				ingotVulcanite,
				ingotVyroxeres,
				ingotZinc,

//				Other Resources
				dustBitumen,
				oreslimeball,
				dustPotash,
				dustSulfur,
				dustPhosphorus
				
				);
		}
	
	public static void registerModels() {

		ingotAdamantine.registerItemModel();
		ingotAlduorite.registerItemModel();
		ingotAmordrine.registerItemModel();
		ingotAngmallen.registerItemModel();
		ingotAstralSilver.registerItemModel();
		ingotAtlarus.registerItemModel();
		ingotBlackSteel.registerItemModel();
		ingotBrass.registerItemModel();
		ingotBronze.registerItemModel();
		ingotCarmot.registerItemModel();
		ingotCelenegil.registerItemModel();
		ingotCeruclase.registerItemModel();
		ingotCopper.registerItemModel();
		ingotDamascusSteel.registerItemModel();
		ingotDeepIron.registerItemModel();
		ingotDesichalkos.registerItemModel();
		ingotElectrum.registerItemModel();
		ingotEximite.registerItemModel();
		ingotHaderoth.registerItemModel();
		ingotHepatizon.registerItemModel();
		ingotIgnatius.registerItemModel();
		ingotInfuscolium.registerItemModel();
		ingotInolashite.registerItemModel();
		ingotKalendrite.registerItemModel();
		ingotLemurite.registerItemModel();
//		ingotLetutium.registerItemModel();
		ingotManganese.registerItemModel();
		ingotMeutoite.registerItemModel();
		ingotMidasium.registerItemModel();
		ingotMithril.registerItemModel();
		ingotOrichalcum.registerItemModel();
//		ingotOsmium.registerItemModel();
		ingotOureclase.registerItemModel();
		ingotPlatinum.registerItemModel();
		ingotPrometheum.registerItemModel();
		ingotQuickSilver.registerItemModel();
		ingotRubracium.registerItemModel();
		ingotSanguinite.registerItemModel();
		ingotShadowIron.registerItemModel();
		ingotShadowSteel.registerItemModel();
		ingotSilver.registerItemModel();
		ingotSteel.registerItemModel();
		ingotTartarite.registerItemModel();
		ingotTin.registerItemModel();
		ingotVulcanite.registerItemModel();
		ingotVyroxeres.registerItemModel();
		ingotZinc.registerItemModel();
		
		dustBitumen.registerItemModel();
		oreslimeball.registerItemModel();
		dustPotash.registerItemModel();
		dustSulfur.registerItemModel();
		dustPhosphorus.registerItemModel();

		
	}
	
	
}