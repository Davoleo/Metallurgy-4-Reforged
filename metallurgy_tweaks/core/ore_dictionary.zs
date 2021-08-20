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
	val amordrineArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global_vars.amordrineBlocks;
	val haderothArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global_vars.haderothBlocks;
	val alduoriteArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global_vars.alduoriteBlocks;
	val platinumArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global_vars.platinumBlocks;
	val vulcaniteArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global_vars.vulcaniteBlocks;
	val tinArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global_vars.tinBlocks;
	val ignatiusArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global_vars.ignatiusBlocks;
	val zincArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global_vars.zincBlocks;
	val etheriumArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global_vars.etheriumBlocks;
	val quicksilverArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global_vars.quicksilverBlocks;
	val brassArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global_vars.brassBlocks;
	val astralSilverArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global_vars.astralSilverBlocks;
	val hepatizonArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global_vars.hepatizonBlocks;
	val bronzeArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global_vars.bronzeBlocks;
	val lemuriteArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global_vars.lemuriteBlocks;
	val sanguiniteArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global_vars.sanguiniteBlocks;
	val eximiteArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global_vars.eximiteBlocks;
	val silverArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global_vars.silverBlocks;
	val desichalkosArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global_vars.desichalkosBlocks;
	val celenegilArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global_vars.celenegilBlocks;
	val steelArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global_vars.steelBlocks;
	val shadowIronArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global_vars.shadowIronBlocks;
	val carmotArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global_vars.carmotBlocks;
	val mithrilArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global_vars.mithrilBlocks;
	val ceruclaseArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global_vars.ceruclaseBlocks;
	val deepIronArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global_vars.deepIronBlocks;
	val angmallenArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global_vars.angmallenBlocks;
	val manganeseArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global_vars.manganeseBlocks;
	val kalendriteArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global_vars.kalendriteBlocks;
	val damascusSteelArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global_vars.damascusSteelBlocks;
	val prometheumArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global_vars.prometheumBlocks;
	val copperArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global_vars.copperBlocks;
	val adamantineArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global_vars.adamantineBlocks;
	val electrumArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global_vars.electrumBlocks;
	val tartariteArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global_vars.tartariteBlocks;
	val atlarusArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global_vars.atlarusBlocks;
	val blackSteelArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global_vars.blackSteelBlocks;
	val vyroxeresArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global_vars.vyroxeresBlocks;
	val lutetiumArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global_vars.lutetiumBlocks;
	val osmiumArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global_vars.osmiumBlocks;
	val oureclaseArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global_vars.oureclaseBlocks;
	val inolashiteArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global_vars.inolashiteBlocks;
	val meutoiteArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global_vars.meutoiteBlocks;
	val orichalcumArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global_vars.orichalcumBlocks;
	val infuscoliumArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global_vars.infuscoliumBlocks;
	val midasiumArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global_vars.midasiumBlocks;
	val shadowSteelArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global_vars.shadowSteelBlocks;
	val krikArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global_vars.krikBlocks;
	val rubraciumArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global_vars.rubraciumBlocks;
	
	val ironArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global_vars.ironDecorBlocks;
	val goldArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global_vars.goldDecorBlocks;

print("Removing ore dictionary entries...");
{//Ore dict removal
	for i in 0 .. metalBlocksArray.length
	{
		oreDictArray[i].remove(metalBlocksArray[i]);
	}
}
print("Finished!");
print("Creating new ore dictionary entries & removing crafting recipes for metallurgy's metal blocks...");
{//ore dict creation
	for amordrineBlocks in amordrineArray
	{
		recipes.remove(amordrineBlocks);
		<ore:decorAmordrine>.add(amordrineBlocks);
	}
	for haderothBlocks in haderothArray
	{
		recipes.remove(haderothBlocks);
		<ore:decorHaderoth>.add(haderothBlocks);
	}
	for alduoriteBlocks in alduoriteArray
	{
		recipes.remove(alduoriteBlocks);
		<ore:decorAlduorite>.add(alduoriteBlocks);
	}
	for platinumBlocks in platinumArray
	{
		recipes.remove(platinumBlocks);
		<ore:decorPlatinum>.add(platinumBlocks);
	}
	for vulcaniteBlocks in vulcaniteArray
	{
		recipes.remove(vulcaniteBlocks);
		<ore:decorVulcanite>.add(vulcaniteBlocks);
	}
	for tinBlocks in tinArray
	{
		recipes.remove(tinBlocks);
		<ore:decorTin>.add(tinBlocks);
	}
	for ignatiusBlocks in ignatiusArray
	{
		recipes.remove(ignatiusBlocks);
		<ore:decorIgnatius>.add(ignatiusBlocks);
	}
	for zincBlocks in zincArray
	{
		recipes.remove(zincBlocks);
		<ore:decorZinc>.add(zincBlocks);
	}
	for etheriumBlocks in etheriumArray
	{
		recipes.remove(etheriumBlocks);
		<ore:decorEtherium>.add(etheriumBlocks);
	}
	for quicksilverBlocks in quicksilverArray
	{
		recipes.remove(quicksilverBlocks);
		<ore:decorQuicksilver>.add(quicksilverBlocks);
	}
	for brassBlocks in brassArray
	{
		recipes.remove(brassBlocks);
		<ore:decorBrass>.add(brassBlocks);
	}
	for astralSilverBlocks in astralSilverArray
	{
		recipes.remove(astralSilverBlocks);
		<ore:decorAstralSilver>.add(astralSilverBlocks);
	}
	for hepatizonBlocks in hepatizonArray
	{
		recipes.remove(hepatizonBlocks);
		<ore:decorHepatizon>.add(hepatizonBlocks);
	}
	for bronzeBlocks in bronzeArray
	{
		recipes.remove(bronzeBlocks);
		<ore:decorBronze>.add(bronzeBlocks);
	}
	for lemuriteBlocks in lemuriteArray
	{
		recipes.remove(lemuriteBlocks);
		<ore:decorLemurite>.add(lemuriteBlocks);
	}
	for sanguiniteBlocks in sanguiniteArray
	{
		recipes.remove(sanguiniteBlocks);
		<ore:decorSanguinite>.add(sanguiniteBlocks);
	}
	for eximiteBlocks in eximiteArray
	{
		recipes.remove(eximiteBlocks);
		<ore:decorEximite>.add(eximiteBlocks);
	}
	for silverBlocks in silverArray
	{
		recipes.remove(silverBlocks);
		<ore:decorSilver>.add(silverBlocks);
	}
	for desichalkosBlocks in desichalkosArray
	{
		recipes.remove(desichalkosBlocks);
		<ore:decorDesichalkos>.add(desichalkosBlocks);
	}
	for celenegilBlocks in celenegilArray
	{
		recipes.remove(celenegilBlocks);
		<ore:decorCelenegil>.add(celenegilBlocks);
	}
	for steelBlocks in steelArray
	{
		recipes.remove(steelBlocks);
		<ore:decorSteel>.add(steelBlocks);
	}
	for shadowIronBlocks in shadowIronArray
	{
		recipes.remove(shadowIronBlocks);
		<ore:decorShadowIron>.add(shadowIronBlocks);
	}
	for carmotBlocks in carmotArray
	{
		recipes.remove(carmotBlocks);
		<ore:decorCarmot>.add(carmotBlocks);
	}
	for mithrilBlocks in mithrilArray
	{
		recipes.remove(mithrilBlocks);
		<ore:decorMithril>.add(mithrilBlocks);
	}
	for ceruclaseBlocks in ceruclaseArray
	{
		recipes.remove(ceruclaseBlocks);
		<ore:decorCeruclase>.add(ceruclaseBlocks);
	}
	for deepIronBlocks in deepIronArray
	{
		recipes.remove(deepIronBlocks);
		<ore:decorDeepIron>.add(deepIronBlocks);
	}
	for angmallenBlocks in angmallenArray
	{
		recipes.remove(angmallenBlocks);
		<ore:decorAngmallen>.add(angmallenBlocks);
	}
	for manganeseBlocks in manganeseArray
	{
		recipes.remove(manganeseBlocks);
		<ore:decorManganese>.add(manganeseBlocks);
	}
	for kalendriteBlocks in kalendriteArray
	{
		recipes.remove(kalendriteBlocks);
		<ore:decorKalendrite>.add(kalendriteBlocks);
	}
	for damascusSteelBlocks in damascusSteelArray
	{
		recipes.remove(damascusSteelBlocks);
		<ore:decorDamascusSteel>.add(damascusSteelBlocks);
	}
	for prometheumBlocks in prometheumArray
	{
		recipes.remove(prometheumBlocks);
		<ore:decorPrometheum>.add(prometheumBlocks);
	}
	for copperBlocks in copperArray
	{
		recipes.remove(copperBlocks);
		<ore:decorCopper>.add(copperBlocks);
	}
	for adamantineBlocks in adamantineArray
	{
		recipes.remove(adamantineBlocks);
		<ore:decorAdamantine>.add(adamantineBlocks);
	}
	for electrumBlocks in electrumArray
	{
		recipes.remove(electrumBlocks);
		<ore:decorElectrum>.add(electrumBlocks);
	}
	for tartariteBlocks in tartariteArray
	{
		recipes.remove(tartariteBlocks);
		<ore:decorTartarite>.add(tartariteBlocks);
	}
	for atlarusBlocks in atlarusArray
	{
		recipes.remove(atlarusBlocks);
		<ore:decorAtlarus>.add(atlarusBlocks);
	}
	for blackSteelBlocks in blackSteelArray
	{
		recipes.remove(blackSteelBlocks);
		<ore:decorBlackSteel>.add(blackSteelBlocks);
	}
		for vyroxeresBlocks in vyroxeresArray
	{
		recipes.remove(vyroxeresBlocks);
		<ore:decorVyroxeres>.add(vyroxeresBlocks);
	}
	for lutetiumBlocks in lutetiumArray
	{
		recipes.remove(lutetiumBlocks);
		<ore:decorLutetium>.add(lutetiumBlocks);
	}
	for osmiumBlocks in osmiumArray
	{
		recipes.remove(osmiumBlocks);
		<ore:decorOsmium>.add(osmiumBlocks);
	}
	for oureclaseBlocks in oureclaseArray
	{
		recipes.remove(oureclaseBlocks);
		<ore:decorOureclase>.add(oureclaseBlocks);
	}
	for inolashiteBlocks in inolashiteArray
	{
		recipes.remove(inolashiteBlocks);
		<ore:decorInolashite>.add(inolashiteBlocks);
	}
	for meutoiteBlocks in meutoiteArray
	{
		recipes.remove(meutoiteBlocks);
		<ore:decorMeutoite>.add(meutoiteBlocks);
	}
	for orichalcumBlocks in orichalcumArray
	{
		recipes.remove(orichalcumBlocks);
		<ore:decorOrichalcum>.add(orichalcumBlocks);
	}
	for infuscoliumBlocks in infuscoliumArray
	{
		recipes.remove(infuscoliumBlocks);
		<ore:decorInfuscolium>.add(infuscoliumBlocks);
	}
	for midasiumBlocks in midasiumArray
	{
		recipes.remove(midasiumBlocks);
		<ore:decorMidasium>.add(midasiumBlocks);
	}
	for shadowSteelBlocks in shadowSteelArray
	{
		recipes.remove(shadowSteelBlocks);
		<ore:decorShadowSteel>.add(shadowSteelBlocks);
	}
	for krikBlocks in krikArray
	{
		recipes.remove(krikBlocks);
		<ore:decorKrik>.add(krikBlocks);
	}
	for rubraciumBlocks in rubraciumArray
	{
		recipes.remove(rubraciumBlocks);
		<ore:decorRubracium>.add(rubraciumBlocks);
	}
	for ironBlocks in ironArray
	{
		recipes.remove(ironBlocks);
		<ore:decorIron>.add(ironBlocks);
	}
	for goldBlocks in goldArray
	{
		recipes.remove(goldBlocks);
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
print("Proceeding to next script...");
if(loadedMods has "chisel")
{
	print("Since Chisel is installed, the next script will create the chisel recipes for the metal blocks...");
}
else
{
	print("Chisel has not been detected! if this is intentional, no further action is required. proceeding to create the new recipes for metal blocks...");
}