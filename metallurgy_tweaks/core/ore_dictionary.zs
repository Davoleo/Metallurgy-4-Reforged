#priority 98
#modloaded metallurgy crafttweaker
import crafttweaker.item.IItemStack;
import crafttweaker.oredict.IOreDictEntry;
import crafttweaker.oredict.IOreDict;
print("-----------------------------------------------------------------------------------------------------------------");
print("Proceeding to modify the ore Dictionary!");
print("Thanks to ''Eutro'' from the BlameJared server for helping me with detecting a mod using a conditional statement!");
print("These scripts are licensed under GNU GPLv3");
print("-----------------------------------------------------------------------------------------------------------------");
//Values
	val oreDictArray = [<ore:blockAmordrine>,<ore:blockHaderoth>,<ore:blockAlduorite>,<ore:blockPlatinum>,<ore:blockVulcanite>,<ore:blockTin>,<ore:blockIgnatius>,<ore:blockZinc>,<ore:blockEtherium>,<ore:blockQuicksilver>,<ore:blockBrass>,<ore:blockAstralSilver>,<ore:blockHepatizon>,<ore:blockBronze>,<ore:blockLemurite>,<ore:blockSanguinite>,<ore:blockEximite>,<ore:blockSilver>,<ore:blockDesichalkos>,<ore:blockCelenegil>,<ore:blockSteel>,<ore:blockShadowIron>,<ore:blockCarmot>,<ore:blockMithril>,<ore:blockCeruclase>,<ore:blockDeepIron>,<ore:blockAngmallen>,<ore:blockManganese>,<ore:blockKalendrite>,<ore:blockDamascusSteel>,<ore:blockPrometheum>,<ore:blockCopper>,<ore:blockAdamantine>,<ore:blockElectrum>,<ore:blockTartarite>,<ore:blockAtlarus>,<ore:blockBlackSteel>,<ore:blockVyroxeres>,<ore:blockLutetium>,<ore:blockOsmium>,<ore:blockOureclase>,<ore:blockInolashite>,<ore:blockMeutoite>,<ore:blockOrichalcum>,<ore:blockInfuscolium>,<ore:blockMidasium>,<ore:blockShadowSteel>,<ore:blockKrik>,<ore:blockRubracium>] as IOreDictEntry[];
	val metalBlocksArray = [<metallurgy:amordrine_block>,<metallurgy:haderoth_block>,<metallurgy:alduorite_block>,<metallurgy:platinum_block>,<metallurgy:vulcanite_block>,<metallurgy:tin_block>,<metallurgy:ignatius_block>,<metallurgy:zinc_block>,<metallurgy:etherium_block>,<metallurgy:quicksilver_block>,<metallurgy:brass_block>,<metallurgy:astral_silver_block>,<metallurgy:hepatizon_block>,<metallurgy:bronze_block>,<metallurgy:lemurite_block>,<metallurgy:sanguinite_block>,<metallurgy:eximite_block>,<metallurgy:silver_block>,<metallurgy:desichalkos_block>,<metallurgy:celenegil_block>,<metallurgy:steel_block>,<metallurgy:shadow_iron_block>,<metallurgy:carmot_block>,<metallurgy:mithril_block>,<metallurgy:ceruclase_block>,<metallurgy:deep_iron_block>,<metallurgy:angmallen_block>,<metallurgy:manganese_block>,<metallurgy:kalendrite_block>,<metallurgy:damascus_steel_block>,<metallurgy:prometheum_block>,<metallurgy:copper_block>,<metallurgy:adamantine_block>,<metallurgy:electrum_block>,<metallurgy:tartarite_block>,<metallurgy:atlarus_block>,<metallurgy:black_steel_block>,<metallurgy:vyroxeres_block>,<metallurgy:lutetium_block>,<metallurgy:osmium_block>,<metallurgy:oureclase_block>,<metallurgy:inolashite_block>,<metallurgy:meutoite_block>,<metallurgy:orichalcum_block>,<metallurgy:infuscolium_block>,<metallurgy:midasium_block>,<metallurgy:shadow_steel_block>,<metallurgy:rubracium_block>] as IItemStack[];
	val amordrineArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global.amordrineBlocks;
	val haderothArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global.haderothBlocks;
	val alduoriteArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global.alduoriteBlocks;
	val platinumArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global.platinumBlocks;
	val vulcaniteArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global.vulcaniteBlocks;
	val tinArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global.tinBlocks;
	val ignatiusArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global.ignatiusBlocks;
	val zincArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global.zincBlocks;
	val etheriumArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global.etheriumBlocks;
	val quicksilverArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global.quicksilverBlocks;
	val brassArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global.brassBlocks;
	val astralSilverArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global.astralSilverBlocks;
	val hepatizonArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global.hepatizonBlocks;
	val bronzeArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global.bronzeBlocks;
	val lemuriteArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global.lemuriteBlocks;
	val sanguiniteArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global.sanguiniteBlocks;
	val eximiteArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global.eximiteBlocks;
	val silverArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global.silverBlocks;
	val desichalkosArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global.desichalkosBlocks;
	val celenegilArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global.celenegilBlocks;
	val steelArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global.steelBlocks;
	val shadowIronArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global.shadowIronBlocks;
	val carmotArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global.carmotBlocks;
	val mithrilArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global.mithrilBlocks;
	val ceruclaseArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global.ceruclaseBlocks;
	val deepIronArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global.deepIronBlocks;
	val angmallenArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global.angmallenBlocks;
	val manganeseArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global.manganeseBlocks;
	val kalendriteArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global.kalendriteBlocks;
	val damascusSteelArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global.damascusSteelBlocks;
	val prometheumArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global.prometheumBlocks;
	val copperArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global.copperBlocks;
	val adamantineArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global.adamantineBlocks;
	val electrumArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global.electrumBlocks;
	val tartariteArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global.tartariteBlocks;
	val atlarusArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global.atlarusBlocks;
	val blackSteelArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global.blackSteelBlocks;
	val vyroxeresArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global.vyroxeresBlocks;
	val lutetiumArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global.lutetiumBlocks;
	val osmiumArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global.osmiumBlocks;
	val oureclaseArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global.oureclaseBlocks;
	val inolashiteArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global.inolashiteBlocks;
	val meutoiteArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global.meutoiteBlocks;
	val orichalcumArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global.orichalcumBlocks;
	val infuscoliumArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global.infuscoliumBlocks;
	val midasiumArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global.midasiumBlocks;
	val shadowSteelArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global.shadowSteelBlocks;
	val krikArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global.krikBlocks;
	val rubraciumArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global.rubraciumBlocks;
	
	val ironArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global.ironDecorBlocks;
	val goldArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global.goldDecorBlocks;

print("Removing ore dictionary entries...");
{//Ore dict removal
	for i in 0 .. metalBlocksArray.length
	{
		oreDictArray[i].remove(metalBlocksArray[i]);
	}
}
print("Finished!");
print("Creating decor ore dictionary entries...");
{//ore dict creation
	for amordrineBlocks in amordrineArray
	{
		<ore:decorAmordrine>.add(amordrineBlocks);
	}
	for haderothBlocks in haderothArray
	{
		<ore:decorHaderoth>.add(haderothBlocks);
	}
	for alduoriteBlocks in alduoriteArray
	{
		<ore:decorAlduorite>.add(alduoriteBlocks);
	}
	for platinumBlocks in platinumArray
	{
		<ore:decorPlatinum>.add(platinumBlocks);
	}
	for vulcaniteBlocks in vulcaniteArray
	{
		<ore:decorVulcanite>.add(vulcaniteBlocks);
	}
	for tinBlocks in tinArray
	{
		<ore:decorTin>.add(tinBlocks);
	}
	for ignatiusBlocks in ignatiusArray
	{
		<ore:decorIgnatius>.add(ignatiusBlocks);
	}
	for zincBlocks in zincArray
	{
		<ore:decorZinc>.add(zincBlocks);
	}
	for etheriumBlocks in etheriumArray
	{
		<ore:decorEtherium>.add(etheriumBlocks);
	}
	for quicksilverBlocks in quicksilverArray
	{
		<ore:decorQuicksilver>.add(quicksilverBlocks);
	}
	for brassBlocks in brassArray
	{
		<ore:decorBrass>.add(brassBlocks);
	}
	for astralSilverBlocks in astralSilverArray
	{
		<ore:decorAstralSilver>.add(astralSilverBlocks);
	}
	for hepatizonBlocks in hepatizonArray
	{
		<ore:decorHepatizon>.add(hepatizonBlocks);
	}
	for bronzeBlocks in bronzeArray
	{
		<ore:decorBronze>.add(bronzeBlocks);
	}
	for lemuriteBlocks in lemuriteArray
	{
		<ore:decorLemurite>.add(lemuriteBlocks);
	}
	for sanguiniteBlocks in sanguiniteArray
	{
		<ore:decorSanguinite>.add(sanguiniteBlocks);
	}
	for eximiteBlocks in eximiteArray
	{
		<ore:decorEximite>.add(eximiteBlocks);
	}
	for silverBlocks in silverArray
	{
		<ore:decorSilver>.add(silverBlocks);
	}
	for desichalkosBlocks in desichalkosArray
	{
		<ore:decorDesichalkos>.add(desichalkosBlocks);
	}
	for celenegilBlocks in celenegilArray
	{
		<ore:decorCelenegil>.add(celenegilBlocks);
	}
	for steelBlocks in steelArray
	{
		<ore:decorSteel>.add(steelBlocks);
	}
	for shadowIronBlocks in shadowIronArray
	{
		<ore:decorShadowIron>.add(shadowIronBlocks);
	}
	for carmotBlocks in carmotArray
	{
		<ore:decorCarmot>.add(carmotBlocks);
	}
	for mithrilBlocks in mithrilArray
	{
		<ore:decorMithril>.add(mithrilBlocks);
	}
	for ceruclaseBlocks in ceruclaseArray
	{
		<ore:decorCeruclase>.add(ceruclaseBlocks);
	}
	for deepIronBlocks in deepIronArray
	{
		<ore:decorDeepIron>.add(deepIronBlocks);
	}
	for angmallenBlocks in angmallenArray
	{
		<ore:decorAngmallen>.add(angmallenBlocks);
	}
	for manganeseBlocks in manganeseArray
	{
		<ore:decorManganese>.add(manganeseBlocks);
	}
	for kalendriteBlocks in kalendriteArray
	{
		<ore:decorKalendrite>.add(kalendriteBlocks);
	}
	for damascusSteelBlocks in damascusSteelArray
	{
		<ore:decorDamascusSteel>.add(damascusSteelBlocks);
	}
	for prometheumBlocks in prometheumArray
	{
		<ore:decorPrometheum>.add(prometheumBlocks);
	}
	for copperBlocks in copperArray
	{
		<ore:decorCopper>.add(copperBlocks);
	}
	for adamantineBlocks in adamantineArray
	{
		<ore:decorAdamantine>.add(adamantineBlocks);
	}
	for electrumBlocks in electrumArray
	{
		<ore:decorElectrum>.add(electrumBlocks);
	}
	for tartariteBlocks in tartariteArray
	{
		<ore:decorTartarite>.add(tartariteBlocks);
	}
	for atlarusBlocks in atlarusArray
	{
		<ore:decorAtlarus>.add(atlarusBlocks);
	}
	for blackSteelBlocks in blackSteelArray
	{
		<ore:decorBlackSteel>.add(blackSteelBlocks);
	}
		for vyroxeresBlocks in vyroxeresArray
	{
		<ore:decorVyroxeres>.add(vyroxeresBlocks);
	}
	for lutetiumBlocks in lutetiumArray
	{
		<ore:decorLutetium>.add(lutetiumBlocks);
	}
	for osmiumBlocks in osmiumArray
	{
		<ore:decorOsmium>.add(osmiumBlocks);
	}
	for oureclaseBlocks in oureclaseArray
	{
		<ore:decorOureclase>.add(oureclaseBlocks);
	}
	for inolashiteBlocks in inolashiteArray
	{
		<ore:decorInolashite>.add(inolashiteBlocks);
	}
	for meutoiteBlocks in meutoiteArray
	{
		<ore:decorMeutoite>.add(meutoiteBlocks);
	}
	for orichalcumBlocks in orichalcumArray
	{
		<ore:decorOrichalcum>.add(orichalcumBlocks);
	}
	for infuscoliumBlocks in infuscoliumArray
	{
		<ore:decorInfuscolium>.add(infuscoliumBlocks);
	}
	for midasiumBlocks in midasiumArray
	{
		<ore:decorMidasium>.add(midasiumBlocks);
	}
	for shadowSteelBlocks in shadowSteelArray
	{
		<ore:decorShadowSteel>.add(shadowSteelBlocks);
	}
	for krikBlocks in krikArray
	{
		<ore:decorKrik>.add(krikBlocks);
	}
	for rubraciumBlocks in rubraciumArray
	{
		<ore:decorRubracium>.add(rubraciumBlocks);
	}
	for ironBlocks in ironArray
	{
		<ore:decorIron>.add(ironBlocks);
	}
	for goldBlocks in goldArray
	{
		<ore:decorGold>.add(goldBlocks);
	}
}
print("Success!");
print("adding Bitumen to Thermal expansion's bitumen ore dictionary entry.");
{
	<ore:dustBitumen>.addAll(<ore:clathrateOil>);
	<ore:clathrateOil>.mirror(<ore:dustBitumen>);
}
print("Success!");