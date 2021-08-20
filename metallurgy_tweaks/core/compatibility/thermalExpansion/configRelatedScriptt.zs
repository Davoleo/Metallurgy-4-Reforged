#priority 87
#modloaded metallurgy thermalexpansion crafttweaker modtweaker

//do not modify anything in here, if you want to disable some of these, be sure to check "metallurgyTweaksConfig.zs!"
import crafttweaker.item.IItemStack;

val activateScript = scripts.metallurgyTweaksConfig.activateThermalOptionalScripts;
val removeThermalToolsArmor = scripts.metallurgyTweaksConfig.thermalRemoveConflictingThermalArmorToolRecipes;



if (activateScript == true)
{
	print("------------------------------------------------------------------------------------------------------------------------------");
	print("This special script handles optional tweaks for Thermal Expansion, you can find these tweaks in ''metallurgyTweaksConfig.zs''!");
	print("All rights reserved unless stated otherwise.");
	print("------------------------------------------------------------------------------------------------------------------------------");
	print("Proceeding to check Config vaules...");
	if (removeThermalToolsArmor == true)
	{
		print("thermalRemoveConflictingThermalArmorToolRecipes is set to True! removing recipes...");
		val toolsArmorsSwords = [<thermalfoundation:armor.helmet_copper>,<thermalfoundation:armor.plate_copper>,<thermalfoundation:armor.legs_copper>,<thermalfoundation:armor.boots_copper>,<thermalfoundation:tool.sword_copper>,<thermalfoundation:tool.shovel_copper>,<thermalfoundation:tool.pickaxe_copper>,<thermalfoundation:tool.axe_copper>,<thermalfoundation:tool.hoe_copper>,<thermalfoundation:armor.helmet_silver>,<thermalfoundation:armor.plate_silver>,<thermalfoundation:armor.legs_silver>,<thermalfoundation:armor.boots_silver>,<thermalfoundation:tool.sword_silver>,<thermalfoundation:tool.shovel_silver>,<thermalfoundation:tool.pickaxe_silver>,<thermalfoundation:tool.axe_silver>,<thermalfoundation:tool.hoe_silver>,<thermalfoundation:armor.helmet_platinum>,<thermalfoundation:armor.plate_platinum>,<thermalfoundation:armor.legs_platinum>,<thermalfoundation:armor.boots_platinum>,<thermalfoundation:tool.sword_platinum>,<thermalfoundation:tool.shovel_platinum>,<thermalfoundation:tool.pickaxe_platinum>,<thermalfoundation:tool.axe_platinum>,<thermalfoundation:tool.hoe_platinum>,<thermalfoundation:armor.helmet_steel>,<thermalfoundation:armor.plate_steel>,<thermalfoundation:armor.legs_steel>,<thermalfoundation:armor.boots_steel>,<thermalfoundation:tool.sword_steel>,<thermalfoundation:tool.shovel_steel>,<thermalfoundation:tool.pickaxe_steel>,<thermalfoundation:tool.axe_steel>,<thermalfoundation:tool.hoe_steel>,<thermalfoundation:armor.helmet_electrum>,<thermalfoundation:armor.plate_electrum>,<thermalfoundation:armor.legs_electrum>,<thermalfoundation:armor.boots_electrum>,<thermalfoundation:tool.sword_electrum>,<thermalfoundation:tool.shovel_electrum>,<thermalfoundation:tool.pickaxe_electrum>,<thermalfoundation:tool.axe_electrum>,<thermalfoundation:tool.hoe_electrum>,<thermalfoundation:armor.helmet_bronze>,<thermalfoundation:armor.plate_bronze>,<thermalfoundation:armor.legs_bronze>,<thermalfoundation:armor.boots_bronze>,<thermalfoundation:tool.sword_bronze>,<thermalfoundation:tool.shovel_bronze>,<thermalfoundation:tool.pickaxe_bronze>,<thermalfoundation:tool.axe_bronze>,<thermalfoundation:tool.hoe_bronze>] as IItemStack[];
		for entries in toolsArmorsSwords
		{
			recipes.remove(entries);
		}
		print("Removed recipes successfully!");
	}
	else
	{
		print("thermalRemoveConflictingThermalArmorToolRecipes is set to false, no recipes will be removed...");
	}
	print("Finished tweaking.");
	if(loadedMods has "tconstruct")
	{
		print("Since Tinkers' Construct is installed, the next script will modify certain aspects to create compatibilities between the two mods.");
	}
	else if(loadedMods has "mekanism")
	{
		print("Since Mekanism is installed, the next script will modify certain aspects to create compatibilities between the two mods.");
	}
	else
	{
		print("All scripts have been installed into your game, have fun & enjoy the mod!");
	}
}