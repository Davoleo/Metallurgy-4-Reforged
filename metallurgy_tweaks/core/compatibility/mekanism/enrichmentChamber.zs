#priority 86
#modloaded metallurgy mekanism crafttweaker modtweaker

import mods.mekanism.enrichment as enrichment;

print("----------------------------------------------------------------------------------------------------------------------------");
print("Thank you for installing Mekanism! This script will fix an inconsistency with the enrichment chamber regarding lutetium ore.");
print("Make sure to check the recipe book or JEI to know the recipes!");
print("All rights reserved unless stated otherwise.");
print("----------------------------------------------------------------------------------------------------------------------------");
print("Proceeding to fix lutetium inconsistency...");
enrichment.addRecipe(<metallurgy:lutetium_ore>, <metallurgy:lutetium_dust> * 2);
print("Success!");
print("All scripts have been installed into your game, have fun & enjoy the mod!");