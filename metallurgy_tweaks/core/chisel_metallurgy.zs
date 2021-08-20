#priority 95
#modloaded metallurgy chisel crafttweaker modtweaker
import crafttweaker.item.IItemStack;
import crafttweaker.oredict.IOreDictEntry;
import mods.chisel.Carving as chisel;
print("----------------------------------------------------------------------------------------------------------------------");
print("Thank you for installing Chisel! This script will create new chisel variants to create metallurgy's decoration blocks.");
print("All rights reserved unless stated otherwise.");
print("-----------------------------------------------------------------------------------------------------------------------");
print("Proceeding to create the new chisel variants for metallurgy's metal blocks...");
{ //Amordrine
	val amordrineArray = scripts.MetallurgyTweaks.metallurgyTweaksGlobalVariables.amordrineBlocks;
	chisel.addGroup("amordrine");
	for amordrineBlocks in amordrineArray
	{
		chisel.addVariation("amordrine", amordrineBlocks);
	}
}
{ //Haderoth
	val haderothArray = scripts.MetallurgyTweaks.metallurgyTweaksGlobalVariables.haderothBlocks;
	chisel.addGroup("haderoth");
	for haderothBlocks in haderothArray
	{
		chisel.addVariation("haderoth", haderothBlocks);
	}
}
{ //Alduorite
	val alduoriteArray = scripts.MetallurgyTweaks.metallurgyTweaksGlobalVariables.alduoriteBlocks;
	chisel.addGroup("alduorite");
	for alduoriteBlocks in alduoriteArray
	{
		chisel.addVariation("alduorite", alduoriteBlocks);
	}
}
{ //Platinum
	val platinumArray = scripts.MetallurgyTweaks.metallurgyTweaksGlobalVariables.platinumBlocks;
	chisel.addGroup("platinum");
	for platinumBlocks in platinumArray
	{
		chisel.addVariation("platinum", platinumBlocks);
	}
}
{ //Vulcanite
	val vulcaniteArray = scripts.MetallurgyTweaks.metallurgyTweaksGlobalVariables.vulcaniteBlocks;
	chisel.addGroup("vulcanite");
	for vulcaniteBlocks in vulcaniteArray
	{
		chisel.addVariation("vulcanite", vulcaniteBlocks);
	}
}
{ //Tin
	val tinArray = scripts.MetallurgyTweaks.metallurgyTweaksGlobalVariables.tinBlocks;
	chisel.addGroup("tin");
	for tinBlocks in tinArray
	{
		chisel.addVariation("tin", tinBlocks);
	}
}
{ //Ignatius
	val ignatiusArray = scripts.MetallurgyTweaks.metallurgyTweaksGlobalVariables.ignatiusBlocks;
	chisel.addGroup("ignatius");
	for ignatiusBlocks in ignatiusArray
	{
		chisel.addVariation("ignatius", ignatiusBlocks);
	}
}
{ //Zinc
	val zincArray = scripts.MetallurgyTweaks.metallurgyTweaksGlobalVariables.zincBlocks;
	chisel.addGroup("zinc");
	for zincBlocks in zincArray
	{
		chisel.addVariation("zinc", zincBlocks);
	}
}
{ //Etherium
	val etheriumArray = scripts.MetallurgyTweaks.metallurgyTweaksGlobalVariables.etheriumBlocks;
	chisel.addGroup("etherium");
	for etheriumBlocks in etheriumArray
	{
		chisel.addVariation("etherium", etheriumBlocks);
	}
}
{ //Quicksilver
	val quicksilverArray = scripts.MetallurgyTweaks.metallurgyTweaksGlobalVariables.quicksilverBlocks;
	chisel.addGroup("quicksilver");
	for quicksilverBlocks in quicksilverArray
	{
		chisel.addVariation("quicksilver", quicksilverBlocks);
	}
}
{ //Brass
	val brassArray = scripts.MetallurgyTweaks.metallurgyTweaksGlobalVariables.brassBlocks;
	chisel.addGroup("brass");
	for brassBlocks in brassArray
	{
		chisel.addVariation("brass", brassBlocks);
	}
}
{ //AstralSilver
	val astralSilverArray = scripts.MetallurgyTweaks.metallurgyTweaksGlobalVariables.astralSilverBlocks;
	chisel.addGroup("astralSilver");
	for astralSilverBlocks in astralSilverArray
	{
		chisel.addVariation("astralSilver", astralSilverBlocks);
	}
}
{ //Hepatizon
	val hepatizonArray = scripts.MetallurgyTweaks.metallurgyTweaksGlobalVariables.hepatizonBlocks;
	chisel.addGroup("hepatizon");
	for hepatizonBlocks in hepatizonArray
	{
		chisel.addVariation("hepatizon", hepatizonBlocks);
	}
}
{ //Bronze
	val bronzeArray = scripts.MetallurgyTweaks.metallurgyTweaksGlobalVariables.bronzeBlocks;
	chisel.addGroup("bronze");
	for bronzeBlocks in bronzeArray
	{
		chisel.addVariation("bronze", bronzeBlocks);
	}
}
{ //Lemurite
	val lemuriteArray = scripts.MetallurgyTweaks.metallurgyTweaksGlobalVariables.lemuriteBlocks;
	chisel.addGroup("lemurite");
	for lemuriteBlocks in lemuriteArray
	{
		chisel.addVariation("lemurite", lemuriteBlocks);
	}
}
{ //Sanguinite
	val sanguiniteArray = scripts.MetallurgyTweaks.metallurgyTweaksGlobalVariables.sanguiniteBlocks;
	chisel.addGroup("sanguinite");
	for sanguiniteBlocks in sanguiniteArray
	{
		chisel.addVariation("sanguinite", sanguiniteBlocks);
	}
}
{ //Eximite
	val eximiteArray = scripts.MetallurgyTweaks.metallurgyTweaksGlobalVariables.eximiteBlocks;
	chisel.addGroup("eximite");
	for eximiteBlocks in eximiteArray
	{
		chisel.addVariation("eximite", eximiteBlocks);
	}
}
{ //Silver
	val silverArray = scripts.MetallurgyTweaks.metallurgyTweaksGlobalVariables.silverBlocks;
	chisel.addGroup("silver");
	for silverBlocks in silverArray
	{
		chisel.addVariation("silver", silverBlocks);
	}
}
{ //Desichalkos
	val desichalkosArray = scripts.MetallurgyTweaks.metallurgyTweaksGlobalVariables.desichalkosBlocks;
	chisel.addGroup("desichalkos");
	for desichalkosBlocks in desichalkosArray
	{
		chisel.addVariation("desichalkos", desichalkosBlocks);
	}
}
{ //Celenegil
	val celenegilArray = scripts.MetallurgyTweaks.metallurgyTweaksGlobalVariables.celenegilBlocks;
	chisel.addGroup("celenegil");
	for celenegilBlocks in celenegilArray
	{
		chisel.addVariation("celenegil", celenegilBlocks);
	}
}
{ //Steel
	val steelArray = scripts.MetallurgyTweaks.metallurgyTweaksGlobalVariables.steelBlocks;
	chisel.addGroup("steel");
	for steelBlocks in steelArray
	{
		chisel.addVariation("steel", steelBlocks);
	}
}
{ //ShadowIron
	val shadowIronArray = scripts.MetallurgyTweaks.metallurgyTweaksGlobalVariables.shadowIronBlocks;
	chisel.addGroup("shadowIron");
	for shadowIronBlocks in shadowIronArray
	{
		chisel.addVariation("shadowIron", shadowIronBlocks);
	}
}
{ //Carmot
	val carmotArray = scripts.MetallurgyTweaks.metallurgyTweaksGlobalVariables.carmotBlocks;
	chisel.addGroup("carmot");
	for carmotBlocks in carmotArray
	{
		chisel.addVariation("carmot", carmotBlocks);
	}
}
{ //Mithril
	val mithrilArray = scripts.MetallurgyTweaks.metallurgyTweaksGlobalVariables.mithrilBlocks;
	chisel.addGroup("mithril");
	for mithrilBlocks in mithrilArray
	{
		chisel.addVariation("mithril", mithrilBlocks);
	}
}
{ //Ceruclase
	val ceruclaseArray = scripts.MetallurgyTweaks.metallurgyTweaksGlobalVariables.ceruclaseBlocks;
	chisel.addGroup("ceruclase");
	for ceruclaseBlocks in ceruclaseArray
	{
		chisel.addVariation("ceruclase", ceruclaseBlocks);
	}
}
{ //DeepIron
	val deepIronArray = scripts.MetallurgyTweaks.metallurgyTweaksGlobalVariables.deepIronBlocks;
	chisel.addGroup("deepIron");
	for deepIronBlocks in deepIronArray
	{
		chisel.addVariation("deepIron", deepIronBlocks);
	}
}
{ //Angmallen
	val angmallenArray = scripts.MetallurgyTweaks.metallurgyTweaksGlobalVariables.angmallenBlocks;
	chisel.addGroup("angmallen");
	for angmallenBlocks in angmallenArray
	{
		chisel.addVariation("angmallen", angmallenBlocks);
	}
}
{ //Manganese
	val manganeseArray = scripts.MetallurgyTweaks.metallurgyTweaksGlobalVariables.manganeseBlocks;
	chisel.addGroup("manganese");
	for manganeseBlocks in manganeseArray
	{
		chisel.addVariation("manganese", manganeseBlocks);
	}
}
{ //Kalendrite
	val kalendriteArray = scripts.MetallurgyTweaks.metallurgyTweaksGlobalVariables.kalendriteBlocks;
	chisel.addGroup("kalendrite");
	for kalendriteBlocks in kalendriteArray
	{
		chisel.addVariation("kalendrite", kalendriteBlocks);
	}
}
{ //DamascusSteel
	val damascusSteelArray = scripts.MetallurgyTweaks.metallurgyTweaksGlobalVariables.damascusSteelBlocks;
	chisel.addGroup("damascusSteel");
	for damascusSteelBlocks in damascusSteelArray
	{
		chisel.addVariation("damascusSteel", damascusSteelBlocks);
	}
}
{ //Prometheum
	val prometheumArray = scripts.MetallurgyTweaks.metallurgyTweaksGlobalVariables.prometheumBlocks;
	chisel.addGroup("prometheum");
	for prometheumBlocks in prometheumArray
	{
		chisel.addVariation("prometheum", prometheumBlocks);
	}
}
{ //Copper
	val copperArray = scripts.MetallurgyTweaks.metallurgyTweaksGlobalVariables.copperBlocks;
	chisel.addGroup("copper");
	for copperBlocks in copperArray
	{
		chisel.addVariation("copper", copperBlocks);
	}
}
{ //Adamantine
	val adamantineArray = scripts.MetallurgyTweaks.metallurgyTweaksGlobalVariables.adamantineBlocks;
	chisel.addGroup("adamantine");
	for adamantineBlocks in adamantineArray
	{
		chisel.addVariation("adamantine", adamantineBlocks);
	}
}
{ //Electrum
	val electrumArray = scripts.MetallurgyTweaks.metallurgyTweaksGlobalVariables.electrumBlocks;
	chisel.addGroup("electrum");
	for electrumBlocks in electrumArray
	{
		chisel.addVariation("electrum", electrumBlocks);
	}
}
{ //Tartarite
	val tartariteArray = scripts.MetallurgyTweaks.metallurgyTweaksGlobalVariables.tartariteBlocks;
	chisel.addGroup("tartarite");
	for tartariteBlocks in tartariteArray
	{
		chisel.addVariation("tartarite", tartariteBlocks);
	}
}
{ //Atlarus
	val atlarusArray = scripts.MetallurgyTweaks.metallurgyTweaksGlobalVariables.atlarusBlocks;
	chisel.addGroup("atlarus");
	for atlarusBlocks in atlarusArray
	{
		chisel.addVariation("atlarus", atlarusBlocks);
	}
}
{ //BlackSteel
	val blackSteelArray = scripts.MetallurgyTweaks.metallurgyTweaksGlobalVariables.blackSteelBlocks;
	chisel.addGroup("blackSteel");
	for blackSteelBlocks in blackSteelArray
	{
		chisel.addVariation("blackSteel", blackSteelBlocks);
	}
}
{ //Vyroxeres
	val vyroxeresArray = scripts.MetallurgyTweaks.metallurgyTweaksGlobalVariables.vyroxeresBlocks;
	chisel.addGroup("vyroxeres");
	for vyroxeresBlocks in vyroxeresArray
	{
		chisel.addVariation("vyroxeres", vyroxeresBlocks);
	}
}
{ //Lutetium
	val lutetiumArray = scripts.MetallurgyTweaks.metallurgyTweaksGlobalVariables.lutetiumBlocks;
	chisel.addGroup("lutetium");
	for lutetiumBlocks in lutetiumArray
	{
		chisel.addVariation("lutetium", lutetiumBlocks);
	}
}
{ //Osmium
	val osmiumArray = scripts.MetallurgyTweaks.metallurgyTweaksGlobalVariables.osmiumBlocks;
	chisel.addGroup("osmium");
	for osmiumBlocks in osmiumArray
	{
		chisel.addVariation("osmium", osmiumBlocks);
	}
}
{ //Oureclase
	val oureclaseArray = scripts.MetallurgyTweaks.metallurgyTweaksGlobalVariables.oureclaseBlocks;
	chisel.addGroup("oureclase");
	for oureclaseBlocks in oureclaseArray
	{
		chisel.addVariation("oureclase", oureclaseBlocks);
	}
}
{ //Inolashite
	val inolashiteArray = scripts.MetallurgyTweaks.metallurgyTweaksGlobalVariables.inolashiteBlocks;
	chisel.addGroup("inolashite");
	for inolashiteBlocks in inolashiteArray
	{
		chisel.addVariation("inolashite", inolashiteBlocks);
	}
}
{ //Meutoite
	val meutoiteArray = scripts.MetallurgyTweaks.metallurgyTweaksGlobalVariables.meutoiteBlocks;
	chisel.addGroup("meutoite");
	for meutoiteBlocks in meutoiteArray
	{
		chisel.addVariation("meutoite", meutoiteBlocks);
	}
}
{ //Orichalcum
	val orichalcumArray = scripts.MetallurgyTweaks.metallurgyTweaksGlobalVariables.orichalcumBlocks;
	chisel.addGroup("orichalcum");
	for orichalcumBlocks in orichalcumArray
	{
		chisel.addVariation("orichalcum", orichalcumBlocks);
	}
}
{ //Infuscolium
	val infuscoliumArray = scripts.MetallurgyTweaks.metallurgyTweaksGlobalVariables.infuscoliumBlocks;
	chisel.addGroup("infuscolium");
	for infuscoliumBlocks in infuscoliumArray
	{
		chisel.addVariation("infuscolium", infuscoliumBlocks);
	}
}
{ //Midasium
	val midasiumArray = scripts.MetallurgyTweaks.metallurgyTweaksGlobalVariables.midasiumBlocks;
	chisel.addGroup("midasium");
	for midasiumBlocks in midasiumArray
	{
		chisel.addVariation("midasium", midasiumBlocks);
	}
}
{ //ShadowSteel
	val shadowSteelArray = scripts.MetallurgyTweaks.metallurgyTweaksGlobalVariables.shadowSteelBlocks;
	chisel.addGroup("shadowSteel");
	for shadowSteelBlocks in shadowSteelArray
	{
		chisel.addVariation("shadowSteel", shadowSteelBlocks);
	}
}
{ //Krik
	val krikArray = scripts.MetallurgyTweaks.metallurgyTweaksGlobalVariables.krikBlocks;
	chisel.addGroup("krik");
	for krikBlocks in krikArray
	{
		chisel.addVariation("krik", krikBlocks);
	}
}
{ //Rubracium
	val rubraciumArray = scripts.MetallurgyTweaks.metallurgyTweaksGlobalVariables.rubraciumBlocks;
	chisel.addGroup("rubracium");
	for rubraciumBlocks in rubraciumArray
	{
		chisel.addVariation("rubracium", rubraciumBlocks);
	}
}
{ //Iron
	val ironArray = scripts.MetallurgyTweaks.metallurgyTweaksGlobalVariables.ironDecorBlocks;
	chisel.addGroup("iron_decor");
	for ironBlocks in ironArray
	{
		chisel.addVariation("iron_decor", ironBlocks);
	}
}
{ //Gold
	val goldArray = scripts.MetallurgyTweaks.metallurgyTweaksGlobalVariables.goldDecorBlocks;
	chisel.addGroup("gold_decor");
	for goldBlocks in goldArray
	{
		chisel.addVariation("gold_decor", goldBlocks);
	}
}
print("Success!");
if(loadedMods has "thermalexpansion")
{
	print("Since Thermal Expansion is installed, the next script will modify certain aspects to create compatibilities between the two mods.");
}
else if(loadedMods has "tconstruct")
{
	print("Since Tinkers' Construct is installed, the next script will modify certain aspects to create compatibilities between the two mods.");
}
