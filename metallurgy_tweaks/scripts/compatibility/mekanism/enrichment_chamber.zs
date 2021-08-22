#priority 79
#modloaded metallurgy mekanism crafttweaker modtweaker

import mods.mekanism.enrichment as enrichment;

print("----------------------------------------------------------------------------------------------------------------------------");
print("Thank you for installing Mekanism! This script will fix an inconsistency with the enrichment chamber regarding lutetium ore.");
print("These scripts are licensed under GNU GPLv3.");
print("----------------------------------------------------------------------------------------------------------------------------");
print("Proceeding to fix lutetium inconsistency...");
enrichment.addRecipe(<metallurgy:lutetium_ore>, <metallurgy:lutetium_dust> * 2);
print("Success!");