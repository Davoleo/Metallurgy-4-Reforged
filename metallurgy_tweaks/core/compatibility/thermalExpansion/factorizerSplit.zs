#priority 93
#modloaded metallurgy thermalexpansion crafttweaker modtweaker
import mods.thermalexpansion.Factorizer;
import crafttweaker.item.IItemStack;
import crafttweaker.oredict.IOreDictEntry;
import crafttweaker.oredict.IOreDict;
print("------------------------------------------------------------------------------------------------------");
print("This script will create new recipes for the Factorizer to split metallurgy's blocks into ingots!");
print("Special thanks to democat in the BlameJared server, for helping me with the loops used in this script!");
print("Make sure to check the recipe book or JEI to know the recipes!");
print("All rights reserved unless stated otherwise.");
print("------------------------------------------------------------------------------------------------------");
print("Proceeding to create new factorizer split recipes...");
val ingotArray = scripts.MetallurgyTweaks.metallurgyTweaksGlobalVariables.ingots;
val blockArray = scripts.MetallurgyTweaks.metallurgyTweaksGlobalVariables.blocks;

for i in 0 .. ingotArray.length
{
	for block in blockArray[i].items
	{
		mods.thermalexpansion.Factorizer.addRecipeSplit(block, ingotArray[i] * 4);
	}
}
print("Success!");
print("Proceeding to create new factorizer split recipes for Iron and Gold Decorative Blocks...");
{
	mods.thermalexpansion.Factorizer.addRecipeSplit(<ore:decorIron>.firstItem, <minecraft:iron_ingot> * 4);
	mods.thermalexpansion.Factorizer.addRecipeCombine(<ore:decorGold>.firstItem, <minecraft:gold_ingot> * 4);
}
print("Success!");
print("Proceeding to the next thermal expansion script...");