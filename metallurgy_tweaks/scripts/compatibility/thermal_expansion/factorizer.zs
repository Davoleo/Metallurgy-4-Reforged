#priority 89
#modloaded metallurgy thermalexpansion crafttweaker modtweaker
import mods.thermalexpansion.Factorizer;
import crafttweaker.item.IItemStack;
import crafttweaker.oredict.IOreDictEntry;
import crafttweaker.oredict.IOreDict;
print("------------------------------------------------------------------------------------------------------------------------------------------------------------------");
print("Thank you for installing Thermal Expansion! This script will create new recipes for the Factorizer to combine metallurgy's ingots into blocks and split them back!");
print("Special thanks to democat in the BlameJared server, for helping me with the loops used in this script!");
print("These scripts are licensed under GNU GPLv3.");
print("------------------------------------------------------------------------------------------------------------------------------------------------------------------");
print("Proceeding to create new factorizer combine & split recipes...");
val ingotArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global.ingots;
val blockArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global.metalBlocks;

for i in 0 .. ingotArray.length
{
	for items in blockArray[i].items
	{
		Factorizer.addRecipeCombine(ingotArray[i] * 9, items);
	}
}
print("Combine Recipes: Success!");

for i in 0 .. ingotArray.length
{
	for block in blockArray[i].items
	{
		mods.thermalexpansion.Factorizer.addRecipeSplit(block, ingotArray[i] * 4);
	}
}
print("Split Recipes: Success!");