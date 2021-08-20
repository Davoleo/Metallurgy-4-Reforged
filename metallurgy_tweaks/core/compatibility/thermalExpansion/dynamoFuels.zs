#priority 91
#modloaded metallurgy thermalexpansion crafttweaker modtweaker
import mods.thermalexpansion.SteamDynamo;
import mods.thermalexpansion.MagmaticDynamo;
import crafttweaker.item.IItemStack;
import crafttweaker.liquid.ILiquidStack;
import crafttweaker.oredict.IOreDictEntry;
import crafttweaker.oredict.IOreDict;
print("------------------------------------------------------------------------------------------------------");
print("This script will allow you to use metallurgy's Bitumen, Thermite dust & Molten thermite in Thermal Expansion's Dynamos!");
print("Make sure to check the recipe book or JEI to know the recipes!");
print("All rights reserved unless stated otherwise.");
print("------------------------------------------------------------------------------------------------------");
print("Proceeding to add Bitumen & Thermite dust as Steam Dynamo Fuels...");
{
	mods.thermalexpansion.SteamDynamo.removeFuel(<ore:dustThermite>.firstItem);
	mods.thermalexpansion.SteamDynamo.addFuel(<ore:dustThermite>.firstItem, 24000);
	furnace.setFuel(<metallurgy:bitumen>, 3200);
}
print("Success!");
print("Proceeding to add Molten Thermite as Magmatic Dynamo fuel...");
{
	mods.thermalexpansion.MagmaticDynamo.addFuel(<liquid:molten_thermite>, 295440);
}
print("Success!");
print("Proceeding to the next thermal expansion script...");