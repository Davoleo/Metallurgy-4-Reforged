#priority 87
#modloaded metallurgy thermalexpansion crafttweaker modtweaker

import mods.thermalexpansion.SteamDynamo;
import mods.thermalexpansion.MagmaticDynamo;

print("-----------------------------------------------------------------------------------------------------------------------");
print("This script will allow you to use metallurgy's Bitumen, Thermite dust & Molten thermite in Thermal Expansion's Dynamos!");
print("These scripts are licensed under GNU GPLv3.");
print("-----------------------------------------------------------------------------------------------------------------------");

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