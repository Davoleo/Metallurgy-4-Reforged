#priority 97
#modloaded metallurgy chisel crafttweaker modtweaker
import crafttweaker.item.IItemStack;
import crafttweaker.oredict.IOreDictEntry;
import mods.chisel.Carving as chisel;
print("----------------------------------------------------------------------------------------------------------------------");
print("Thank you for installing Chisel! This script will create new chisel variants to create metallurgy's decoration blocks.");
print("These scripts are licensed under GNU GPLv3.");
print("-----------------------------------------------------------------------------------------------------------------------");
print("Proceeding to create the new chisel variants for metallurgy's metal blocks...");
{ //Amordrine
	val amordrineArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global.amordrineBlocks;
	chisel.addGroup("amordrine");
	for amordrineBlocks in amordrineArray
	{
		chisel.addVariation("amordrine", amordrineBlocks);
	}
}
{ //Haderoth
	val haderothArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global.haderothBlocks;
	chisel.addGroup("haderoth");
	for haderothBlocks in haderothArray
	{
		chisel.addVariation("haderoth", haderothBlocks);
	}
}
{ //Alduorite
	val alduoriteArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global.alduoriteBlocks;
	chisel.addGroup("alduorite");
	for alduoriteBlocks in alduoriteArray
	{
		chisel.addVariation("alduorite", alduoriteBlocks);
	}
}
{ //Platinum
	val platinumArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global.platinumBlocks;
	chisel.addGroup("platinum");
	for platinumBlocks in platinumArray
	{
		chisel.addVariation("platinum", platinumBlocks);
	}
}
{ //Vulcanite
	val vulcaniteArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global.vulcaniteBlocks;
	chisel.addGroup("vulcanite");
	for vulcaniteBlocks in vulcaniteArray
	{
		chisel.addVariation("vulcanite", vulcaniteBlocks);
	}
}
{ //Tin
	val tinArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global.tinBlocks;
	chisel.addGroup("tin");
	for tinBlocks in tinArray
	{
		chisel.addVariation("tin", tinBlocks);
	}
}
{ //Ignatius
	val ignatiusArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global.ignatiusBlocks;
	chisel.addGroup("ignatius");
	for ignatiusBlocks in ignatiusArray
	{
		chisel.addVariation("ignatius", ignatiusBlocks);
	}
}
{ //Zinc
	val zincArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global.zincBlocks;
	chisel.addGroup("zinc");
	for zincBlocks in zincArray
	{
		chisel.addVariation("zinc", zincBlocks);
	}
}
{ //Etherium
	val etheriumArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global.etheriumBlocks;
	chisel.addGroup("etherium");
	for etheriumBlocks in etheriumArray
	{
		chisel.addVariation("etherium", etheriumBlocks);
	}
}
{ //Quicksilver
	val quicksilverArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global.quicksilverBlocks;
	chisel.addGroup("quicksilver");
	for quicksilverBlocks in quicksilverArray
	{
		chisel.addVariation("quicksilver", quicksilverBlocks);
	}
}
{ //Brass
	val brassArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global.brassBlocks;
	chisel.addGroup("brass");
	for brassBlocks in brassArray
	{
		chisel.addVariation("brass", brassBlocks);
	}
}
{ //AstralSilver
	val astralSilverArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global.astralSilverBlocks;
	chisel.addGroup("astralSilver");
	for astralSilverBlocks in astralSilverArray
	{
		chisel.addVariation("astralSilver", astralSilverBlocks);
	}
}
{ //Hepatizon
	val hepatizonArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global.hepatizonBlocks;
	chisel.addGroup("hepatizon");
	for hepatizonBlocks in hepatizonArray
	{
		chisel.addVariation("hepatizon", hepatizonBlocks);
	}
}
{ //Bronze
	val bronzeArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global.bronzeBlocks;
	chisel.addGroup("bronze");
	for bronzeBlocks in bronzeArray
	{
		chisel.addVariation("bronze", bronzeBlocks);
	}
}
{ //Lemurite
	val lemuriteArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global.lemuriteBlocks;
	chisel.addGroup("lemurite");
	for lemuriteBlocks in lemuriteArray
	{
		chisel.addVariation("lemurite", lemuriteBlocks);
	}
}
{ //Sanguinite
	val sanguiniteArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global.sanguiniteBlocks;
	chisel.addGroup("sanguinite");
	for sanguiniteBlocks in sanguiniteArray
	{
		chisel.addVariation("sanguinite", sanguiniteBlocks);
	}
}
{ //Eximite
	val eximiteArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global.eximiteBlocks;
	chisel.addGroup("eximite");
	for eximiteBlocks in eximiteArray
	{
		chisel.addVariation("eximite", eximiteBlocks);
	}
}
{ //Silver
	val silverArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global.silverBlocks;
	chisel.addGroup("silver");
	for silverBlocks in silverArray
	{
		chisel.addVariation("silver", silverBlocks);
	}
}
{ //Desichalkos
	val desichalkosArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global.desichalkosBlocks;
	chisel.addGroup("desichalkos");
	for desichalkosBlocks in desichalkosArray
	{
		chisel.addVariation("desichalkos", desichalkosBlocks);
	}
}
{ //Celenegil
	val celenegilArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global.celenegilBlocks;
	chisel.addGroup("celenegil");
	for celenegilBlocks in celenegilArray
	{
		chisel.addVariation("celenegil", celenegilBlocks);
	}
}
{ //Steel
	val steelArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global.steelBlocks;
	chisel.addGroup("steel");
	for steelBlocks in steelArray
	{
		chisel.addVariation("steel", steelBlocks);
	}
}
{ //ShadowIron
	val shadowIronArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global.shadowIronBlocks;
	chisel.addGroup("shadowIron");
	for shadowIronBlocks in shadowIronArray
	{
		chisel.addVariation("shadowIron", shadowIronBlocks);
	}
}
{ //Carmot
	val carmotArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global.carmotBlocks;
	chisel.addGroup("carmot");
	for carmotBlocks in carmotArray
	{
		chisel.addVariation("carmot", carmotBlocks);
	}
}
{ //Mithril
	val mithrilArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global.mithrilBlocks;
	chisel.addGroup("mithril");
	for mithrilBlocks in mithrilArray
	{
		chisel.addVariation("mithril", mithrilBlocks);
	}
}
{ //Ceruclase
	val ceruclaseArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global.ceruclaseBlocks;
	chisel.addGroup("ceruclase");
	for ceruclaseBlocks in ceruclaseArray
	{
		chisel.addVariation("ceruclase", ceruclaseBlocks);
	}
}
{ //DeepIron
	val deepIronArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global.deepIronBlocks;
	chisel.addGroup("deepIron");
	for deepIronBlocks in deepIronArray
	{
		chisel.addVariation("deepIron", deepIronBlocks);
	}
}
{ //Angmallen
	val angmallenArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global.angmallenBlocks;
	chisel.addGroup("angmallen");
	for angmallenBlocks in angmallenArray
	{
		chisel.addVariation("angmallen", angmallenBlocks);
	}
}
{ //Manganese
	val manganeseArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global.manganeseBlocks;
	chisel.addGroup("manganese");
	for manganeseBlocks in manganeseArray
	{
		chisel.addVariation("manganese", manganeseBlocks);
	}
}
{ //Kalendrite
	val kalendriteArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global.kalendriteBlocks;
	chisel.addGroup("kalendrite");
	for kalendriteBlocks in kalendriteArray
	{
		chisel.addVariation("kalendrite", kalendriteBlocks);
	}
}
{ //DamascusSteel
	val damascusSteelArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global.damascusSteelBlocks;
	chisel.addGroup("damascusSteel");
	for damascusSteelBlocks in damascusSteelArray
	{
		chisel.addVariation("damascusSteel", damascusSteelBlocks);
	}
}
{ //Prometheum
	val prometheumArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global.prometheumBlocks;
	chisel.addGroup("prometheum");
	for prometheumBlocks in prometheumArray
	{
		chisel.addVariation("prometheum", prometheumBlocks);
	}
}
{ //Copper
	val copperArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global.copperBlocks;
	chisel.addGroup("copper");
	for copperBlocks in copperArray
	{
		chisel.addVariation("copper", copperBlocks);
	}
}
{ //Adamantine
	val adamantineArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global.adamantineBlocks;
	chisel.addGroup("adamantine");
	for adamantineBlocks in adamantineArray
	{
		chisel.addVariation("adamantine", adamantineBlocks);
	}
}
{ //Electrum
	val electrumArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global.electrumBlocks;
	chisel.addGroup("electrum");
	for electrumBlocks in electrumArray
	{
		chisel.addVariation("electrum", electrumBlocks);
	}
}
{ //Tartarite
	val tartariteArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global.tartariteBlocks;
	chisel.addGroup("tartarite");
	for tartariteBlocks in tartariteArray
	{
		chisel.addVariation("tartarite", tartariteBlocks);
	}
}
{ //Atlarus
	val atlarusArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global.atlarusBlocks;
	chisel.addGroup("atlarus");
	for atlarusBlocks in atlarusArray
	{
		chisel.addVariation("atlarus", atlarusBlocks);
	}
}
{ //BlackSteel
	val blackSteelArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global.blackSteelBlocks;
	chisel.addGroup("blackSteel");
	for blackSteelBlocks in blackSteelArray
	{
		chisel.addVariation("blackSteel", blackSteelBlocks);
	}
}
{ //Vyroxeres
	val vyroxeresArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global.vyroxeresBlocks;
	chisel.addGroup("vyroxeres");
	for vyroxeresBlocks in vyroxeresArray
	{
		chisel.addVariation("vyroxeres", vyroxeresBlocks);
	}
}
{ //Lutetium
	val lutetiumArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global.lutetiumBlocks;
	chisel.addGroup("lutetium");
	for lutetiumBlocks in lutetiumArray
	{
		chisel.addVariation("lutetium", lutetiumBlocks);
	}
}
{ //Osmium
	val osmiumArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global.osmiumBlocks;
	chisel.addGroup("osmium");
	for osmiumBlocks in osmiumArray
	{
		chisel.addVariation("osmium", osmiumBlocks);
	}
}
{ //Oureclase
	val oureclaseArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global.oureclaseBlocks;
	chisel.addGroup("oureclase");
	for oureclaseBlocks in oureclaseArray
	{
		chisel.addVariation("oureclase", oureclaseBlocks);
	}
}
{ //Inolashite
	val inolashiteArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global.inolashiteBlocks;
	chisel.addGroup("inolashite");
	for inolashiteBlocks in inolashiteArray
	{
		chisel.addVariation("inolashite", inolashiteBlocks);
	}
}
{ //Meutoite
	val meutoiteArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global.meutoiteBlocks;
	chisel.addGroup("meutoite");
	for meutoiteBlocks in meutoiteArray
	{
		chisel.addVariation("meutoite", meutoiteBlocks);
	}
}
{ //Orichalcum
	val orichalcumArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global.orichalcumBlocks;
	chisel.addGroup("orichalcum");
	for orichalcumBlocks in orichalcumArray
	{
		chisel.addVariation("orichalcum", orichalcumBlocks);
	}
}
{ //Infuscolium
	val infuscoliumArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global.infuscoliumBlocks;
	chisel.addGroup("infuscolium");
	for infuscoliumBlocks in infuscoliumArray
	{
		chisel.addVariation("infuscolium", infuscoliumBlocks);
	}
}
{ //Midasium
	val midasiumArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global.midasiumBlocks;
	chisel.addGroup("midasium");
	for midasiumBlocks in midasiumArray
	{
		chisel.addVariation("midasium", midasiumBlocks);
	}
}
{ //ShadowSteel
	val shadowSteelArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global.shadowSteelBlocks;
	chisel.addGroup("shadowSteel");
	for shadowSteelBlocks in shadowSteelArray
	{
		chisel.addVariation("shadowSteel", shadowSteelBlocks);
	}
}
{ //Krik
	val krikArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global.krikBlocks;
	chisel.addGroup("krik");
	for krikBlocks in krikArray
	{
		chisel.addVariation("krik", krikBlocks);
	}
}
{ //Rubracium
	val rubraciumArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global.rubraciumBlocks;
	chisel.addGroup("rubracium");
	for rubraciumBlocks in rubraciumArray
	{
		chisel.addVariation("rubracium", rubraciumBlocks);
	}
}
{ //Iron
	val ironArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global.ironDecorBlocks;
	chisel.addGroup("iron_decor");
	for ironBlocks in ironArray
	{
		chisel.addVariation("iron_decor", ironBlocks);
	}
}
{ //Gold
	val goldArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global.goldDecorBlocks;
	chisel.addGroup("gold_decor");
	for goldBlocks in goldArray
	{
		chisel.addVariation("gold_decor", goldBlocks);
	}
}
print("Success!");