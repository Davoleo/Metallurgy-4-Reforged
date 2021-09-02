#priority 98
#modloaded metallurgy crafttweaker

import crafttweaker.item.IItemStack;
import crafttweaker.oredict.IOreDictEntry;
import crafttweaker.oredict.IOreDict;

print("-----------------------------------------------------------------------------------------------------------------");
print("Proceeding to modify the ore Dictionary!");
print("These scripts are licensed under GNU GPLv3");
print("-----------------------------------------------------------------------------------------------------------------");

//Values
val oreDictArray = [<ore:blockAmordrine>,<ore:blockHaderoth>,<ore:blockAlduorite>,<ore:blockPlatinum>,<ore:blockVulcanite>,<ore:blockTin>,<ore:blockIgnatius>,<ore:blockZinc>,<ore:blockEtherium>,<ore:blockQuicksilver>,<ore:blockBrass>,<ore:blockAstralSilver>,<ore:blockHepatizon>,<ore:blockBronze>,<ore:blockLemurite>,<ore:blockSanguinite>,<ore:blockEximite>,<ore:blockSilver>,<ore:blockDesichalkos>,<ore:blockCelenegil>,<ore:blockSteel>,<ore:blockShadowIron>,<ore:blockCarmot>,<ore:blockMithril>,<ore:blockCeruclase>,<ore:blockDeepIron>,<ore:blockAngmallen>,<ore:blockManganese>,<ore:blockKalendrite>,<ore:blockDamascusSteel>,<ore:blockPrometheum>,<ore:blockCopper>,<ore:blockAdamantine>,<ore:blockElectrum>,<ore:blockTartarite>,<ore:blockAtlarus>,<ore:blockBlackSteel>,<ore:blockVyroxeres>,<ore:blockLutetium>,<ore:blockOsmium>,<ore:blockOureclase>,<ore:blockInolashite>,<ore:blockMeutoite>,<ore:blockOrichalcum>,<ore:blockInfuscolium>,<ore:blockMidasium>,<ore:blockShadowSteel>,<ore:blockKrik>,<ore:blockRubracium>] as IOreDictEntry[];
val metalBlocksArray = [<metallurgy:amordrine_block>,<metallurgy:haderoth_block>,<metallurgy:alduorite_block>,<metallurgy:platinum_block>,<metallurgy:vulcanite_block>,<metallurgy:tin_block>,<metallurgy:ignatius_block>,<metallurgy:zinc_block>,<metallurgy:etherium_block>,<metallurgy:quicksilver_block>,<metallurgy:brass_block>,<metallurgy:astral_silver_block>,<metallurgy:hepatizon_block>,<metallurgy:bronze_block>,<metallurgy:lemurite_block>,<metallurgy:sanguinite_block>,<metallurgy:eximite_block>,<metallurgy:silver_block>,<metallurgy:desichalkos_block>,<metallurgy:celenegil_block>,<metallurgy:steel_block>,<metallurgy:shadow_iron_block>,<metallurgy:carmot_block>,<metallurgy:mithril_block>,<metallurgy:ceruclase_block>,<metallurgy:deep_iron_block>,<metallurgy:angmallen_block>,<metallurgy:manganese_block>,<metallurgy:kalendrite_block>,<metallurgy:damascus_steel_block>,<metallurgy:prometheum_block>,<metallurgy:copper_block>,<metallurgy:adamantine_block>,<metallurgy:electrum_block>,<metallurgy:tartarite_block>,<metallurgy:atlarus_block>,<metallurgy:black_steel_block>,<metallurgy:vyroxeres_block>,<metallurgy:lutetium_block>,<metallurgy:osmium_block>,<metallurgy:oureclase_block>,<metallurgy:inolashite_block>,<metallurgy:meutoite_block>,<metallurgy:orichalcum_block>,<metallurgy:infuscolium_block>,<metallurgy:midasium_block>,<metallurgy:shadow_steel_block>,<metallurgy:rubracium_block>] as IItemStack[];

val decoOreDict = scripts.metallurgy_tweaks.metallurgy_tweaks_global.decoBlocks;
val decoMap as IItemStack[][string] = scripts.metallurgy_tweaks.metallurgy_tweaks_global.decoMap;

val ironArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global.ironDecorBlocks;
val goldArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global.goldDecorBlocks;

print("Removing ore dictionary entries...");
{//Ore dict removal
	for i in 0 .. metalBlocksArray.length {
		oreDictArray[i].remove(metalBlocksArray[i]);
	}
}
print("Finished!");
print("Creating decor ore dictionary entries...");

//oreDict Creation
for metal in decoMap.keys {
    val ore = oreDict.get("decor" + metal);
    for deco in decoMap[metal] {
        ore.add(deco);
    }
}

for ironBlocks in ironArray {
	<ore:decorIron>.add(ironBlocks);
}
for goldBlocks in goldArray {
    <ore:decorGold>.add(goldBlocks);
}
print("Success!");

print("adding Bitumen to Thermal expansion's bitumen ore dictionary entry.");
{
	<ore:dustBitumen>.addAll(<ore:clathrateOil>);
	<ore:clathrateOil>.mirror(<ore:dustBitumen>);
}
print("Success!");