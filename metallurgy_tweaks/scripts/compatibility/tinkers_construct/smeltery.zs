#priority 69
#modloaded metallurgy tconstruct crafttweaker modtweaker

import mods.tconstruct.Melting as melting;
import mods.tconstruct.Casting as casting;

print("------------------------------------------------------------------------------------------------------");
print("Thank you for installing Tinkers' Construct! This script will create smeltery recipes for metallurgy!");
print("Special thanks to democat in the BlameJared server, for helping me with the loops used in this script!");
print("These scripts are licensed under GNU GPLv3.");
print("------------------------------------------------------------------------------------------------------");
print("Proceeding to create new Melting and Casting Recipes...");
val blockArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global.metalBlocks;
val liquidArray = scripts.metallurgy_tweaks.metallurgy_tweaks_global.liquids;

for i in 0 .. blockArray.length
{
	for items in blockArray[i].items
	{
		melting.removeRecipe(liquidArray[i], items);
		melting.addRecipe(liquidArray[i] * 1296, items);
		casting.addBasinRecipe(items, null, liquidArray[i], 1296);
	}
}
print("Success!");