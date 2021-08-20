#priority 101
/*
-------------------------------------------------------------------------------------------
This is MetallurgyTweak's Config file.
Please read throughly the following options to customize your gameplay Experience.
-------------------------------------------------------------------------------------------
*/
/*-----------------------------------------------------------------------------------------------------------
|Activate Thermal optional scripts																		    |
|Setting this to true will activate the scripts bellow that handle Thermal Foundation related configuration.|
|deactivating it will deactivate the configs that start with "thermal"									    |
-------------------------------------------------------------------------------------------------------------*/
static activateThermalOptionalScripts as bool = true;
/*-----------------------------------------------------------------------------------------------------------------------------------------------------------------------
|Armor & Tool crafting recipe removal.																																	  |
|Setting this to "true" will activate a script that'll remove the ability to craft tools, weapons and armors from thermal foundation, made from the following materials...|
|Copper, silver, platinum, steel, electrum & bronze																														  |
|It's perfect to guarantee you can always craft the metallurgy version of these armors.																					  |
-------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
static thermalRemoveConflictingThermalArmorToolRecipes as bool = true;