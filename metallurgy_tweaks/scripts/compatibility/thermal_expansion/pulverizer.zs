#priority 86
#modloaded metallurgy thermalexpansion crafttweaker modtweaker
import mods.thermalexpansion.Pulverizer;
import crafttweaker.item.IItemStack;
import crafttweaker.liquid.ILiquidStack;
import crafttweaker.oredict.IOreDictEntry;
import crafttweaker.oredict.IOreDict;
print("----------------------------------------------------------------------------------------------------");
print("This script for now will allow you to create Potash fertilizer using Thermal Expansion's Pulverizer!");
print("These scripts are licensed under GNU GPLv3");
print("----------------------------------------------------------------------------------------------------");
print("Proceeding to add Pulverizer recipes...");
{
	mods.thermalexpansion.Pulverizer.addRecipe(<metallurgy:potash_fertilizer>, <ore:dustPotash>.firstItem, 4000);
}
print("Success!");
