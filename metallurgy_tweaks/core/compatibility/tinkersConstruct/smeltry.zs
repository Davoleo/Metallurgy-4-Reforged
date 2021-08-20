#priority 87
#modloaded metallurgy tconstruct crafttweaker modtweaker

import mods.tconstruct.Melting as melting;
import mods.tconstruct.Casting as casting;

print("------------------------------------------------------------------------------------------------------");
print("Thank you for installing Tinkers' Construct! This script will create smeltry recipes for metallurgy!");
print("Special thanks to democat in the BlameJared server, for helping me with the loops used in this script!");
print("Make sure to check the recipe book or JEI to know the recipes!");
print("All rights reserved unless stated otherwise.");
print("------------------------------------------------------------------------------------------------------");
print("Proceeding to create new Melting and Casting Recipes...");
val blockArray = scripts.MetallurgyTweaks.metallurgyTweaksGlobalVariables.blocks;
val liquidArray = scripts.MetallurgyTweaks.metallurgyTweaksGlobalVariables.liquids;

for i in 0 .. blockArray.length
{
	for items in blockArray[i].items
	{
		melting.removeRecipe(liquidArray[i], items);
		melting.addRecipe(liquidArray[i] * 576, items);
		casting.addTableRecipe(items,null, liquidArray[i], 576, false, 176);
	}
}
print("Success!");
print("Proceeding to make Metallurgy's Iron and Gold Decoration block smeltable...");
{
	melting.addRecipe(<liquid:iron> * 576, <ore:decorIron>);
	casting.addTableRecipe(<metallurgy:iron_engraved_block>, null, <liquid:iron>, 576, false, 176);
	melting.addRecipe(<liquid:gold> * 576, <ore:decorGold>);
	casting.addTableRecipe(<metallurgy:gold_engraved_block>, null, <liquid:gold>, 576, false, 176);
}
print("Success!");
if(loadedMods has "mekanism")
{
	print("Since Mekanism is installed, the next script will modify certain aspects to create compatibilities between the two mods.");
}
else
{
	print("All scripts have been installed into your game, have fun & enjoy the mod!");
}