#priority 100
print("-----------------------------------------------------------------------------------------------------");
print("Metallurgy Tweaks is now loading!");
print("This first script will detect which mods are installed and activate the respective addons...");
print("If you find a download link in a site other than curseforge, please notify us on the discord server or report the uploader.");
print("These scripts are licensed under GNU GPLv3");
print("-----------------------------------------------------------------------------------------------------");

var metallurgy = false as bool;
var modTweaker = false as bool;
var chisel = false as bool;
var thermalExpansion = false as bool;
var tinkersConstruct = false as bool;
var projectE = false as bool;
var mekanism = false as bool;
var activateThermalOptionalScripts = scripts.metallurgyTweaksConfig.activateThermalOptionalScripts;

if(loadedMods has "metallurgy")
{
	print("Metallurgy has been detected!");
	metallurgy = true;
}
else
{
	print("-------------------------------------------------------------------------------------");
	print("									FATAL ERROR");
	print("Metallurgy has not been detected, please verify that its JAR is in your mods folder!");
	print("no further scripts will be loaded...");
	print("-------------------------------------------------------------------------------------");
	metallurgy = false;
}
if(loadedMods has "modtweaker" & metallurgy == true)
{
	print("ModTweaker has been detected! Mod support for the following detected mods will be active.");
	modTweaker = true;
}
else if(metallurgy == true)
{
	print("------------------------------------------------------------------------------------");
	print("										WARNING");
	print("ModTweaker has not been detected, please verify that it's JAR is in your mods folder");
	print("No mod support will be active, except for ProjectE if found");
	print("If this is intentional, ignore this message.");
	print("------------------------------------------------------------------------------------");
	modTweaker = false;
}
if(loadedMods has "chisel" & modTweaker == true)
{
	print("Chisel has been detected! compatibility script will load.");
	chisel = true;
}
else if(metallurgy == true & modTweaker == true)
{
	print("---------------------------------------------------------------------");
	print("Chisel has not been detected! compatibility script won't be loaded.");
	print("---------------------------------------------------------------------");
	chisel = false;
}
if(loadedMods has "thermalexpansion" & modTweaker == true)
{
	print("Thermal Expansion has been detected! it's mod support script will activate.");
	print("checking ''metallurgyTweaksConfig'' for user configuration...");
	thermalExpansion = true;
	if(activateThermalOptionalScripts == true)
	{
		print("activateThermalOptionalScripts is set to true!");
		print("''thermalConfigRelatedScript'' script will activate.");
	}
	else if(activateThermalOptionalScripts == false)
	{
		print("activateThermalOptionalScripts is set to false!");
		print("''thermalConfigRelatedScript'' script won't activate.");
	}
}
else if(metallurgy == true & modTweaker == true)
{
	print("--------------------------------------------------------------------------------");
	print("Thermal Expansion has not been detected! compatibility script won't be loaded.");
	print("--------------------------------------------------------------------------------");
	thermalExpansion = false;
}
if(loadedMods has "tconstruct" & modTweaker == true)
{
	print("Tinkers' Construct has been detected! compatibility script will load.");
	tinkersConstruct = true;
}
else if(metallurgy == true & modTweaker == true)
{
	print("---------------------------------------------------------------------------------");
	print("Tinkers' Construct has not been detected! compatibility script won't be loaded.");
	print("---------------------------------------------------------------------------------");
	tinkersConstruct = false;
}
if(loadedMods has "projecte" & modTweaker == true)
{
	print("ProjectE has been detected!");
	print("At the moment, there are no projectE scripts, however, make sure to download the Optional Config file in the curseforge site! or else EMC values won't match!");
	projectE = true;
}
else if(metallurgy == true)
{
	print("---------------------------------------------------------------------------------");
	print("ProjectE has not been detected! compatibility script won't be loaded.");
	print("---------------------------------------------------------------------------------");
}
if(loadedMods has "mekanism")
{
	print("Mekanism has been detected! compatibility script will load.");
	mekanism = true;
}
else if(metallurgy == true)
{
	print("---------------------------------------------------------------------------------");
	print("Mekanism has not been detected! compatibility script won't be loaded.");
	print("---------------------------------------------------------------------------------");
}
if(modTweaker == true & metallurgy == true)
{
	print("Mod detection finished, please check above to see which scripts will be loaded and which won't.");
	print("Next script: metallurgyTweaksGlobalVariables.zs");
}
else if(modTweaker == false & metallurgy == false)
{
	print("There has been a fatal error during the mod detection, please read above for troubleshooting.");
}