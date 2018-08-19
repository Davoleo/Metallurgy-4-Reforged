package hurts.metallurgy_5.item;

import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;

public class ModItems {
	
	//Other resources
	public static ItemOre dustBitumen = new ItemOre ("dust_bitumen","dustBitumen");
	public static ItemOre oreslimeball = new ItemOre ("tar","oreslimeball");	 
	public static ItemOre dustPotash = new ItemOre ("dust_potash","dustPotash");
	public static ItemOre dustSulfur = new ItemOre ("dust_sulfur","dustSulfur");	 
		 
	
	//Ingot
	public static ItemOre ingotAdamantine = new ItemOre ("ingot_adamantine","ingotAdamantine");
	public static ItemOre ingotAlduorite = new ItemOre ("ingot_alduorite","ingotAlduorite");
	public static ItemOre ingotAmordrine = new ItemOre ("ingot_amordrine","ingotAmordrine");
	public static ItemOre ingotAngmallen = new ItemOre ("ingot_angmallen","ingotAngmallen");
	public static ItemOre ingotAstralSilver = new ItemOre ("ingot_astral_silver","ingotAstralSilver");
	public static ItemOre ingotAtlarus = new ItemOre ("ingot_atlarus","ingotAtlarus");
	public static ItemOre ingotBlackSteel = new ItemOre ("ingot_black_steel","ingotBlackSteel");
	public static ItemOre ingotCarmot = new ItemOre ("ingot_carmot","ingotCarmot");
	public static ItemOre ingotCelenegil = new ItemOre ("ingot_celenegil","ingotCelenegil");
	public static ItemOre ingotCeruclase = new ItemOre ("ingot_ceruclase","ingotCeruclase");
	public static ItemOre ingotCopper = new ItemOre ("ingot_copper","ingotCopper");	
	public static ItemOre ingotDamascusSteel = new ItemOre ("ingot_damascus_steel","ingotDamascusSteel");
	public static ItemOre ingotDeepIron = new ItemOre ("ingot_deep_iron","ingotDeepIron");
	public static ItemOre ingotDesichalkos = new ItemOre ("ingot_desichalkos","ingotDesichalkos");
	public static ItemOre ingotEximite = new ItemOre ("ingot_eximite","ingotEximite");
	public static ItemOre ingotHaderoth = new ItemOre ("ingot_haderoth","ingotHaderoth");
	public static ItemOre ingotHepatizon = new ItemOre ("ingot_hepatizon","ingotHepatizon");
	public static ItemOre ingotIgnatius = new ItemOre ("ingot_ignatius","ingotIgnatius");
	public static ItemOre ingotInfuscolium = new ItemOre ("ingot_infuscolium","ingotInfuscolium");
	public static ItemOre ingotInolashite = new ItemOre ("ingot_inolashite","ingotInolashite");
	public static ItemOre ingotKalendrite = new ItemOre ("ingot_kalendrite","ingotKalendrite");
	public static ItemOre ingotLemurite = new ItemOre ("ingot_lemurite","ingotLemurite");
//	public static ItemOre ingotLetutium = new ItemOre ("ingot_letutium","ingotLetutium");
	public static ItemOre ingotManganese = new ItemOre ("ingot_manganese","ingotManganese");
	public static ItemOre ingotMeutoite = new ItemOre ("ingot_meutoite","ingotMeutoite");
	public static ItemOre ingotMidasium = new ItemOre ("ingot_midasium","ingotMidasium");
	public static ItemOre ingotMithril = new ItemOre ("ingot_mithril","ingotMithril");
	public static ItemOre ingotOrichalcum = new ItemOre ("ingot_orichalcum","ingotOrichalcum");
//	public static ItemOre ingotOsmium = new ItemOre ("ingot_osmium","ingotOsmium");
	public static ItemOre ingotOureclase = new ItemOre ("ingot_oureclase","ingotOureclase");
	public static ItemOre ingotPlatinum = new ItemOre ("ingot_platinum","ingotPlatinum");
	public static ItemOre ingotPrometheum = new ItemOre ("ingot_prometheum","ingotPrometheum");
	public static ItemOre ingotQuickSilver = new ItemOre ("ingot_quicksilver","ingotQuickSilver");
	public static ItemOre ingotRubracacium = new ItemOre ("ingot_rubracacium","ingotRubracacium");
	public static ItemOre ingotSanguinite = new ItemOre ("ingot_sanguinite","ingotSanguinite");
	public static ItemOre ingotShadowIron = new ItemOre ("ingot_shadow_iron","ingotShadowIron");
	public static ItemOre ingotShadowSteel = new ItemOre ("ingot_shadow_steel","ingotShadowSteel");
	public static ItemOre ingotSteel = new ItemOre ("ingot_steel","ingotSteel");
	public static ItemOre ingotTartarite = new ItemOre ("ingot_tartarite","ingotTartarite");
	public static ItemOre ingotTin = new ItemOre ("ingot_tin","ingotTin");
	public static ItemOre ingotVulcanite = new ItemOre ("ingot_vulcanite","ingotVulcanite");
	public static ItemOre ingotVyroxeres = new ItemOre ("ingot_vyroxeres","ingotVyroxeres ");
	public static ItemOre ingotZinc = new ItemOre ("ingot_zinc","ingotZinc ");	

	
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
				ingotCarmot,
				ingotCelenegil,
				ingotCeruclase,
				ingotCopper,
				ingotDamascusSteel,
				ingotDeepIron,
				ingotDesichalkos,
				ingotEximite,
				ingotHaderoth,
				ingotHepatizon,
				ingotIgnatius,
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
				ingotRubracacium,
				ingotSanguinite,
				ingotShadowIron,
				ingotShadowSteel,
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
				dustSulfur
				
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
		ingotCarmot.registerItemModel();
		ingotCelenegil.registerItemModel();
		ingotCeruclase.registerItemModel();
		ingotCopper.registerItemModel();
		ingotDamascusSteel.registerItemModel();
		ingotDeepIron.registerItemModel();
		ingotDesichalkos.registerItemModel();
		ingotEximite.registerItemModel();
		ingotHaderoth.registerItemModel();
		ingotHepatizon.registerItemModel();
		ingotIgnatius.registerItemModel();
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
		ingotRubracacium.registerItemModel();
		ingotSanguinite.registerItemModel();
		ingotShadowIron.registerItemModel();
		ingotShadowSteel.registerItemModel();
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

		
	}
	
	
}
