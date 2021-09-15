#priority 86
#modloaded metallurgy thermalexpansion crafttweaker modtweaker
#ikwid

import mods.thermalexpansion.Pulverizer;

print("----------------------------------------------------------------------------------------------------");
print("This script for now will allow you to create Potash fertilizer using Thermal Expansion's Pulverizer!");
print("These scripts are licensed under GNU GPLv3");
print("----------------------------------------------------------------------------------------------------");

print("Proceeding to add Pulverizer recipes...");
{
	Pulverizer.addRecipe(<metallurgy:potash_fertilizer>, <ore:dustPotash>.firstItem, 4000);
}
print("Success!");
