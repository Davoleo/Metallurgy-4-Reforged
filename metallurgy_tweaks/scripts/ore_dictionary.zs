#priority 98
#modloaded metallurgy crafttweaker
#ikwid

import crafttweaker.item.IItemStack;
import crafttweaker.oredict.IOreDictEntry;
import crafttweaker.oredict.IOreDict;

print("-----------------------------------------------------------------------------------------------------------------");
print("Proceeding to modify the ore Dictionary!");
print("These scripts are licensed under GNU GPLv3");
print("-----------------------------------------------------------------------------------------------------------------");

val decoOreDict = scripts.metallurgy_tweaks.metallurgy_tweaks_global.decoBlocks;
val decoMap as IItemStack[][string] = scripts.metallurgy_tweaks.metallurgy_tweaks_global.decoMap;

val ironArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global.ironDecorBlocks;
val goldArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global.goldDecorBlocks;

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