#priority 90
#modloaded metallurgy thermalexpansion crafttweaker modtweaker
import mods.thermalexpansion.Pulverizer;
import crafttweaker.item.IItemStack;
import crafttweaker.liquid.ILiquidStack;
import crafttweaker.oredict.IOreDictEntry;
import crafttweaker.oredict.IOreDict;
print("---------------------------------------------------------------------------------------------");
print("This script for now will allow you to Potash fertilizer using Thermal Expansion's Pulverizer!");
print("Make sure to check the recipe book or JEI to know the recipes!");
print("All rights reserved unless stated otherwise.");
print("---------------------------------------------------------------------------------------------");
print("Proceeding to add Pulverizer recipes...");
{
	mods.thermalexpansion.Pulverizer.addRecipe(<metallurgy:potash_fertilizer>, <ore:dustPotash>.firstItem, 4000);
}
print("Success!");
print("Proceeding to next script...");
