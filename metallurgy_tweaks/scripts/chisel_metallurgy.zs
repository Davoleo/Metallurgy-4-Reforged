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

//Metallurgy Metals
val decoMap = scripts.metallurgy_tweaks.metallurgy_tweaks_global.decoMap;
for metal in decoMap.keys {
    chisel.addGroup(metal);
    for decoBlock in decoMap[metal] {
        chisel.addVariation(metal, decoBlock);
    }
}

//Iron
val ironArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global.ironDecorBlocks;
chisel.addGroup("IronDecor");
for ironBlocks in ironArray {
    chisel.addVariation("IronDecor", ironBlocks);
}

//Gold
val goldArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global.goldDecorBlocks;
chisel.addGroup("GoldDecor");
for goldBlocks in goldArray {
    chisel.addVariation("IronDecor", goldBlocks);
}
print("Success!");