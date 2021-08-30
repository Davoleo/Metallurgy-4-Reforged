#priority 84
#modloaded metallurgy thermalexpansion crafttweaker modtweaker

import mods.thermalexpansion.InductionSmelter;

print("-----------------------------------------------------------------------------------------------------------");
print("This script allows you to use the Induction Smelter to salvage your tools and armors into their ingot form!");
print("These scripts are licensed under GNU GPLv3.");
print("-----------------------------------------------------------------------------------------------------------");
print("Proceeding to add Induction Smelter Recipes...");

val sand = <ore:sand>.firstItem;
val slag = <thermalfoundation:material:864>;

val tools = scripts.metallurgy_tweaks.metallurgy_tweaks_global.toolsMap;

//Metallurgy Metals
for metal in tools.keys {
    val ingot = itemUtils.getItem("metallurgy:" + metal + "_ingot");

    for tool in tools[metal] {
        InductionSmelter.addRecipe(ingot, sand, tool, 6000, slag, 10);
    }
    InductionSmelter.addRecipe(ingot * 2, sand, itemUtils.getItem("metallurgy:" + metal + "_helmet"), 6000, slag, 15);
    InductionSmelter.addRecipe(ingot * 4, sand, itemUtils.getItem("metallurgy:" + metal + "_chestplate"), 6000, slag, 25);
    InductionSmelter.addRecipe(ingot * 3, sand, itemUtils.getItem("metallurgy:" + metal + "_leggings"), 6000, slag, 20);
    InductionSmelter.addRecipe(ingot * 1, sand, itemUtils.getItem("metallurgy:" + metal + "_boots"), 6000, slag, 15);
}

//Osmium and Lutetium Only have armor
{//lutetium
	{
		InductionSmelter.addRecipe(<metallurgy:lutetium_ingot> * 2, sand, <metallurgy:lutetium_helmet>, 6000, slag, 15);
		InductionSmelter.addRecipe(<metallurgy:lutetium_ingot> * 4, sand, <metallurgy:lutetium_chestplate>, 6000, slag, 25);
		InductionSmelter.addRecipe(<metallurgy:lutetium_ingot> * 3, sand, <metallurgy:lutetium_leggings>, 6000, slag, 20);
		InductionSmelter.addRecipe(<metallurgy:lutetium_ingot> * 1, sand, <metallurgy:lutetium_boots>, 6000, slag, 15);
	}
}
{//osmium
	{
		InductionSmelter.addRecipe(<metallurgy:osmium_ingot> * 2, sand, <metallurgy:osmium_helmet>, 6000, slag, 15);
		InductionSmelter.addRecipe(<metallurgy:osmium_ingot> * 4, sand, <metallurgy:osmium_chestplate>, 6000, slag, 25);
		InductionSmelter.addRecipe(<metallurgy:osmium_ingot> * 3, sand, <metallurgy:osmium_leggings>, 6000, slag, 20);
		InductionSmelter.addRecipe(<metallurgy:osmium_ingot> * 1, sand, <metallurgy:osmium_boots>, 6000, slag, 15);
	}
}

{//rubracium
	InductionSmelter.addRecipe(<metallurgy:rubracium_ingot> * 5, sand, <metallurgy:rubracium_gauntlet>, 6000, slag, 25);
}
{//lemurite
	InductionSmelter.addRecipe(<metallurgy:lemurite_ingot> * 3, sand, <metallurgy:lemurite_shield>, 6000, slag, 20);
}
print("Success!");