#priority 88
#modloaded metallurgy thermalexpansion crafttweaker modtweaker
import mods.thermalexpansion.Crucible;
import crafttweaker.item.IItemStack;
import crafttweaker.liquid.ILiquidStack;
import crafttweaker.oredict.IOreDictEntry;
import crafttweaker.oredict.IOreDict;
print("------------------------------------------------------------------------------------------------------");
print("This script will create new recipes for the Magma Crucible to melt metallurgy's blocks!");
print("Special thanks to democat in the BlameJared server, for helping me with the loops used in this script!");
print("These scripts are licensed under GNU GPLv3.");
print("------------------------------------------------------------------------------------------------------");
print("Proceeding to create new Magma Crucible recipes...");
val nuggetArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global.nuggets;
val ingotArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global.ingots;
val blockArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global.metalBlocks;
val oreArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global.ores;
val liquidArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global.liquids;

for i in 0 .. nuggetArray.length
{
	mods.thermalexpansion.Crucible.addRecipe(liquidArray[i] * 16, nuggetArray[i], 500); 
	mods.thermalexpansion.Crucible.addRecipe(liquidArray[i] * 144, ingotArray[i], 4000);
	mods.thermalexpansion.Crucible.addRecipe(liquidArray[i] * 288, oreArray[i], 8000);
	for block in blockArray[i].items
	{
		mods.thermalexpansion.Crucible.addRecipe(liquidArray[i] * 1296, block, 36000);
	}
}
print("Success!");
