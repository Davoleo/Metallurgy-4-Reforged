#priority 94
#modloaded metallurgy thermalexpansion crafttweaker modtweaker
import mods.thermalexpansion.Factorizer;
import crafttweaker.item.IItemStack;
import crafttweaker.oredict.IOreDictEntry;
import crafttweaker.oredict.IOreDict;
print("----------------------------------------------------------------------------------------------------------------------------------------------");
print("Thank you for installing Thermal Expansion! This script will create new recipes for the Factorizer to combine metallurgy's ingots into blocks!");
print("Special thanks to democat in the BlameJared server, for helping me with the loops used in this script!");
print("Make sure to check the recipe book or JEI to know the recipes!");
print("All rights reserved unless stated otherwise.");
print("----------------------------------------------------------------------------------------------------------------------------------------------");
print("Proceeding to create new factorizer combine recipes...");
val ingotArray = scripts.MetallurgyTweaks.metallurgyTweaksGlobalVariables.ingots;
val blockArray = scripts.MetallurgyTweaks.metallurgyTweaksGlobalVariables.blocks;

for i in 0 .. ingotArray.length
{
	for items in blockArray[i].items
	{
		mods.thermalexpansion.Factorizer.addRecipeCombine(ingotArray[i] * 4, items);
	}
}
print("Success!");
print("Proceeding to create new factorizer combine recipes for Iron and Gold Decorative Blocks...");
{
	mods.thermalexpansion.Factorizer.addRecipeCombine(<minecraft:iron_ingot> * 4, <metallurgy:iron_engraved_block>);
	mods.thermalexpansion.Factorizer.addRecipeCombine(<minecraft:gold_ingot> * 4, <metallurgy:gold_engraved_block>);
}
print("Success!");
print("Proceeding to the next thermal expansion script...");

/*for i in 0 .. ingotArray.length
{
	mods.thermalexpansion.Factorizer.addRecipeCombine(ingotArray[i] * 4, blockArray[i]);
}*/