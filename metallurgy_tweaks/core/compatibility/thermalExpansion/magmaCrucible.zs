#priority 92
#modloaded metallurgy thermalexpansion crafttweaker modtweaker
import mods.thermalexpansion.Crucible;
import crafttweaker.item.IItemStack;
import crafttweaker.liquid.ILiquidStack;
import crafttweaker.oredict.IOreDictEntry;
import crafttweaker.oredict.IOreDict;
print("------------------------------------------------------------------------------------------------------");
print("This script will create new recipes for the Magma Crucible to melt metallurgy's blocks!");
print("Special thanks to democat in the BlameJared server, for helping me with the loops used in this script!");
print("Make sure to check the recipe book or JEI to know the recipes!");
print("All rights reserved unless stated otherwise.");
print("------------------------------------------------------------------------------------------------------");
print("Proceeding to create new Magma Crucible recipes...");
val nuggetArray = scripts.MetallurgyTweaks.metallurgyTweaksGlobalVariables.nuggets;
val ingotArray = scripts.MetallurgyTweaks.metallurgyTweaksGlobalVariables.ingots;
val blockArray = scripts.MetallurgyTweaks.metallurgyTweaksGlobalVariables.blocks;
val oreArray = scripts.MetallurgyTweaks.metallurgyTweaksGlobalVariables.ores;
val liquidArray = scripts.MetallurgyTweaks.metallurgyTweaksGlobalVariables.liquids;

for i in 0 .. nuggetArray.length
{
	mods.thermalexpansion.Crucible.addRecipe(liquidArray[i] * 16, nuggetArray[i], 500); 
	mods.thermalexpansion.Crucible.addRecipe(liquidArray[i] * 144, ingotArray[i], 4000);
	mods.thermalexpansion.Crucible.addRecipe(liquidArray[i] * 288, oreArray[i], 8000);
	for block in blockArray[i].items
	{
		mods.thermalexpansion.Crucible.addRecipe(liquidArray[i] * 576, block, 16000);	
	}
}
print("Success!");
print("Proceeding to the next thermal expansion script...");
