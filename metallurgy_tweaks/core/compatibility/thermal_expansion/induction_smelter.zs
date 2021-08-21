#priority 84
#modloaded metallurgy thermalexpansion crafttweaker modtweaker

import InductionSmelter;
import crafttweaker.item.IItemStack;
import crafttweaker.item.WeightedItemStack;
import crafttweaker.liquid.ILiquidStack;
import crafttweaker.oredict.IOreDictEntry;
import crafttweaker.oredict.IOreDict;

print("-----------------------------------------------------------------------------------------------------------");
print("This script allows you to use the Induction Smelter to salvage your tools and armors into their ingot form!");
print("These scripts are licensed under GNU GPLv3.");
print("-----------------------------------------------------------------------------------------------------------");
print("Proceeding to add Induction Smelter Recipes...");

val sand = <ore:sand>.firstItem;
val slag = <thermalfoundation:material:864>;
val amordrineTools = [<metallurgy:amordrine_axe>,<metallurgy:amordrine_hoe>,<metallurgy:amordrine_pickaxe>,<metallurgy:amordrine_shovel>,<metallurgy:amordrine_sword>] as IItemStack[];
val haderothTools = [<metallurgy:haderoth_axe>,<metallurgy:haderoth_hoe>,<metallurgy:haderoth_pickaxe>,<metallurgy:haderoth_shovel>,<metallurgy:haderoth_sword>] as IItemStack[];
val platinumTools = [<metallurgy:platinum_axe>,<metallurgy:platinum_hoe>,<metallurgy:platinum_pickaxe>,<metallurgy:platinum_shovel>,<metallurgy:platinum_sword>] as IItemStack[];
val vulcaniteTools = [<metallurgy:vulcanite_axe>,<metallurgy:vulcanite_hoe>,<metallurgy:vulcanite_pickaxe>,<metallurgy:vulcanite_shovel>,<metallurgy:vulcanite_sword>] as IItemStack[];
val ignatiusTools = [<metallurgy:ignatius_axe>,<metallurgy:ignatius_hoe>,<metallurgy:ignatius_pickaxe>,<metallurgy:ignatius_shovel>,<metallurgy:ignatius_sword>] as IItemStack[];
val etheriumTools = [<metallurgy:etherium_axe>,<metallurgy:etherium_hoe>,<metallurgy:etherium_pickaxe>,<metallurgy:etherium_shovel>,<metallurgy:etherium_sword>] as IItemStack[];
val quicksilverTools = [<metallurgy:quicksilver_axe>,<metallurgy:quicksilver_hoe>,<metallurgy:quicksilver_pickaxe>,<metallurgy:quicksilver_shovel>,<metallurgy:quicksilver_sword>] as IItemStack[];
val brassTools = [<metallurgy:brass_axe>,<metallurgy:brass_hoe>,<metallurgy:brass_pickaxe>,<metallurgy:brass_shovel>,<metallurgy:brass_sword>] as IItemStack[];
val astralSilverTools = [<metallurgy:astral_silver_axe>,<metallurgy:astral_silver_hoe>,<metallurgy:astral_silver_pickaxe>,<metallurgy:astral_silver_shovel>,<metallurgy:astral_silver_sword>] as IItemStack[];
val hepatizonTools = [<metallurgy:hepatizon_axe>,<metallurgy:hepatizon_hoe>,<metallurgy:hepatizon_pickaxe>,<metallurgy:hepatizon_shovel>,<metallurgy:hepatizon_sword>] as IItemStack[];
val bronzeTools = [<metallurgy:bronze_axe>,<metallurgy:bronze_hoe>,<metallurgy:bronze_pickaxe>,<metallurgy:bronze_shovel>,<metallurgy:bronze_sword>] as IItemStack[];
val sanguiniteTools = [<metallurgy:sanguinite_axe>,<metallurgy:sanguinite_hoe>,<metallurgy:sanguinite_pickaxe>,<metallurgy:sanguinite_shovel>,<metallurgy:sanguinite_sword>] as IItemStack[];
val eximiteTools = [<metallurgy:eximite_axe>,<metallurgy:eximite_hoe>,<metallurgy:eximite_pickaxe>,<metallurgy:eximite_shovel>,<metallurgy:eximite_sword>] as IItemStack[];
val silverTools = [<metallurgy:silver_axe>,<metallurgy:silver_hoe>,<metallurgy:silver_pickaxe>,<metallurgy:silver_shovel>,<metallurgy:silver_sword>] as IItemStack[];
val desichalkosTools = [<metallurgy:desichalkos_axe>,<metallurgy:desichalkos_hoe>,<metallurgy:desichalkos_pickaxe>,<metallurgy:desichalkos_shovel>,<metallurgy:desichalkos_sword>] as IItemStack[];
val celenegilTools = [<metallurgy:celenegil_axe>,<metallurgy:celenegil_hoe>,<metallurgy:celenegil_pickaxe>,<metallurgy:celenegil_shovel>,<metallurgy:celenegil_sword>] as IItemStack[];
val shadowIronTools = [<metallurgy:shadow_iron_axe>,<metallurgy:shadow_iron_hoe>,<metallurgy:shadow_iron_pickaxe>,<metallurgy:shadow_iron_shovel>,<metallurgy:shadow_iron_sword>] as IItemStack[];
val steelTools = [<metallurgy:steel_axe>,<metallurgy:steel_hoe>,<metallurgy:steel_pickaxe>,<metallurgy:steel_shovel>,<metallurgy:steel_sword>] as IItemStack[];
val carmotTools = [<metallurgy:carmot_axe>,<metallurgy:carmot_hoe>,<metallurgy:carmot_pickaxe>,<metallurgy:carmot_shovel>,<metallurgy:carmot_sword>] as IItemStack[];
val mithrilTools = [<metallurgy:mithril_axe>,<metallurgy:mithril_hoe>,<metallurgy:mithril_pickaxe>,<metallurgy:mithril_shovel>,<metallurgy:mithril_sword>] as IItemStack[];
val ceruclaseTools = [<metallurgy:ceruclase_axe>,<metallurgy:ceruclase_hoe>,<metallurgy:ceruclase_pickaxe>,<metallurgy:ceruclase_shovel>,<metallurgy:ceruclase_sword>] as IItemStack[];
val deepIronTools = [<metallurgy:deep_iron_axe>,<metallurgy:deep_iron_hoe>,<metallurgy:deep_iron_pickaxe>,<metallurgy:deep_iron_shovel>,<metallurgy:deep_iron_sword>] as IItemStack[];
val angmallenTools = [<metallurgy:angmallen_axe>,<metallurgy:angmallen_hoe>,<metallurgy:angmallen_pickaxe>,<metallurgy:angmallen_shovel>,<metallurgy:angmallen_sword>] as IItemStack[];
val kalendriteTools = [<metallurgy:kalendrite_axe>,<metallurgy:kalendrite_hoe>,<metallurgy:kalendrite_pickaxe>,<metallurgy:kalendrite_shovel>,<metallurgy:kalendrite_sword>] as IItemStack[];
val damascusSteelTools = [<metallurgy:damascus_steel_axe>,<metallurgy:damascus_steel_hoe>,<metallurgy:damascus_steel_pickaxe>,<metallurgy:damascus_steel_shovel>,<metallurgy:damascus_steel_sword>] as IItemStack[];
val prometheumTools = [<metallurgy:prometheum_axe>,<metallurgy:prometheum_hoe>,<metallurgy:prometheum_pickaxe>,<metallurgy:prometheum_shovel>,<metallurgy:prometheum_sword>] as IItemStack[];
val copperTools = [<metallurgy:copper_axe>,<metallurgy:copper_hoe>,<metallurgy:copper_pickaxe>,<metallurgy:copper_shovel>,<metallurgy:copper_sword>] as IItemStack[];
val adamantineTools = [<metallurgy:adamantine_axe>,<metallurgy:adamantine_hoe>,<metallurgy:adamantine_pickaxe>,<metallurgy:adamantine_shovel>,<metallurgy:adamantine_sword>] as IItemStack[];
val electrumTools = [<metallurgy:electrum_axe>,<metallurgy:electrum_hoe>,<metallurgy:electrum_pickaxe>,<metallurgy:electrum_shovel>,<metallurgy:electrum_sword>] as IItemStack[];
val tartariteTools = [<metallurgy:tartarite_axe>,<metallurgy:tartarite_hoe>,<metallurgy:tartarite_pickaxe>,<metallurgy:tartarite_shovel>,<metallurgy:tartarite_sword>] as IItemStack[];
val atlarusTools = [<metallurgy:atlarus_axe>,<metallurgy:atlarus_hoe>,<metallurgy:atlarus_pickaxe>,<metallurgy:atlarus_shovel>,<metallurgy:atlarus_sword>] as IItemStack[];
val blackSteelTools = [<metallurgy:black_steel_axe>,<metallurgy:black_steel_hoe>,<metallurgy:black_steel_pickaxe>,<metallurgy:black_steel_shovel>,<metallurgy:black_steel_sword>] as IItemStack[];
val vyroxeresTools = [<metallurgy:vyroxeres_axe>,<metallurgy:vyroxeres_hoe>,<metallurgy:vyroxeres_pickaxe>,<metallurgy:vyroxeres_shovel>,<metallurgy:vyroxeres_sword>] as IItemStack[];
val oureclaseTools = [<metallurgy:oureclase_axe>,<metallurgy:oureclase_hoe>,<metallurgy:oureclase_pickaxe>,<metallurgy:oureclase_shovel>,<metallurgy:oureclase_sword>] as IItemStack[];
val inolashiteTools = [<metallurgy:inolashite_axe>,<metallurgy:inolashite_hoe>,<metallurgy:inolashite_pickaxe>,<metallurgy:inolashite_shovel>,<metallurgy:inolashite_sword>] as IItemStack[];
val orichalcumTools = [<metallurgy:orichalcum_axe>,<metallurgy:orichalcum_hoe>,<metallurgy:orichalcum_pickaxe>,<metallurgy:orichalcum_shovel>,<metallurgy:orichalcum_sword>] as IItemStack[];
val shadowSteelTools = [<metallurgy:shadow_steel_axe>,<metallurgy:shadow_steel_hoe>,<metallurgy:shadow_steel_pickaxe>,<metallurgy:shadow_steel_shovel>,<metallurgy:shadow_steel_sword>] as IItemStack[];
val midasiumTools = [<metallurgy:midasium_axe>,<metallurgy:midasium_hoe>,<metallurgy:midasium_pickaxe>,<metallurgy:midasium_shovel>,<metallurgy:midasium_sword>] as IItemStack[];
val krikTools = [<metallurgy:krik_axe>,<metallurgy:krik_hoe>,<metallurgy:krik_pickaxe>,<metallurgy:krik_shovel>,<metallurgy:krik_sword>] as IItemStack[];

{//amordrine
	for entries in amordrineTools
	{
		InductionSmelter.addRecipe(<metallurgy:amordrine_ingot>, sand, entries, 6000, slag, 10);
	}
	//Armor
	{
		InductionSmelter.addRecipe(<metallurgy:amordrine_ingot> * 2, sand, <metallurgy:amordrine_helmet>, 6000, slag, 15);
		InductionSmelter.addRecipe(<metallurgy:amordrine_ingot> * 4, sand, <metallurgy:amordrine_chestplate>, 6000, slag, 25);
		InductionSmelter.addRecipe(<metallurgy:amordrine_ingot> * 3, sand, <metallurgy:amordrine_leggings>, 6000, slag, 20);
		InductionSmelter.addRecipe(<metallurgy:amordrine_ingot> * 1, sand, <metallurgy:amordrine_boots>, 6000, slag, 15);
	}
}
{//haderoth
	for entries in haderothTools
	{
		InductionSmelter.addRecipe(<metallurgy:haderoth_ingot>, sand, entries, 6000, slag, 10);
	}
	//Armor
	{
		InductionSmelter.addRecipe(<metallurgy:haderoth_ingot> * 2, sand, <metallurgy:haderoth_helmet>, 6000, slag, 15);
		InductionSmelter.addRecipe(<metallurgy:haderoth_ingot> * 4, sand, <metallurgy:haderoth_chestplate>, 6000, slag, 25);
		InductionSmelter.addRecipe(<metallurgy:haderoth_ingot> * 3, sand, <metallurgy:haderoth_leggings>, 6000, slag, 20);
		InductionSmelter.addRecipe(<metallurgy:haderoth_ingot> * 1, sand, <metallurgy:haderoth_boots>, 6000, slag, 15);
	}
}

{//platinum
	for entries in platinumTools
	{
		InductionSmelter.addRecipe(<metallurgy:platinum_ingot>, sand, entries, 6000, slag, 10);
	}
	//Armor
	{
		InductionSmelter.addRecipe(<metallurgy:platinum_ingot> * 2, sand, <metallurgy:platinum_helmet>, 6000, slag, 15);
		InductionSmelter.addRecipe(<metallurgy:platinum_ingot> * 4, sand, <metallurgy:platinum_chestplate>, 6000, slag, 25);
		InductionSmelter.addRecipe(<metallurgy:platinum_ingot> * 3, sand, <metallurgy:platinum_leggings>, 6000, slag, 20);
		InductionSmelter.addRecipe(<metallurgy:platinum_ingot> * 1, sand, <metallurgy:platinum_boots>, 6000, slag, 15);
	}
}
{//vulcanite
	for entries in vulcaniteTools
	{
		InductionSmelter.addRecipe(<metallurgy:vulcanite_ingot>, sand, entries, 6000, slag, 10);
	}
	//Armor
	{
		InductionSmelter.addRecipe(<metallurgy:vulcanite_ingot> * 2, sand, <metallurgy:vulcanite_helmet>, 6000, slag, 15);
		InductionSmelter.addRecipe(<metallurgy:vulcanite_ingot> * 4, sand, <metallurgy:vulcanite_chestplate>, 6000, slag, 25);
		InductionSmelter.addRecipe(<metallurgy:vulcanite_ingot> * 3, sand, <metallurgy:vulcanite_leggings>, 6000, slag, 20);
		InductionSmelter.addRecipe(<metallurgy:vulcanite_ingot> * 1, sand, <metallurgy:vulcanite_boots>, 6000, slag, 15);
	}
}
{//ignatius
	for entries in ignatiusTools
	{
		InductionSmelter.addRecipe(<metallurgy:ignatius_ingot>, sand, entries, 6000, slag, 10);
	}
	//Armor
	{
		InductionSmelter.addRecipe(<metallurgy:ignatius_ingot> * 2, sand, <metallurgy:ignatius_helmet>, 6000, slag, 15);
		InductionSmelter.addRecipe(<metallurgy:ignatius_ingot> * 4, sand, <metallurgy:ignatius_chestplate>, 6000, slag, 25);
		InductionSmelter.addRecipe(<metallurgy:ignatius_ingot> * 3, sand, <metallurgy:ignatius_leggings>, 6000, slag, 20);
		InductionSmelter.addRecipe(<metallurgy:ignatius_ingot> * 1, sand, <metallurgy:ignatius_boots>, 6000, slag, 15);
	}
}
{//etherium
	for entries in etheriumTools
	{
		InductionSmelter.addRecipe(<metallurgy:etherium_ingot>, sand, entries, 6000, slag, 10);
	}
	//Armor
	{
		InductionSmelter.addRecipe(<metallurgy:etherium_ingot> * 2, sand, <metallurgy:etherium_helmet>, 6000, slag, 15);
		InductionSmelter.addRecipe(<metallurgy:etherium_ingot> * 4, sand, <metallurgy:etherium_chestplate>, 6000, slag, 25);
		InductionSmelter.addRecipe(<metallurgy:etherium_ingot> * 3, sand, <metallurgy:etherium_leggings>, 6000, slag, 20);
		InductionSmelter.addRecipe(<metallurgy:etherium_ingot> * 1, sand, <metallurgy:etherium_boots>, 6000, slag, 15);
	}
}
{//quicksilver
	for entries in quicksilverTools
	{
		InductionSmelter.addRecipe(<metallurgy:quicksilver_ingot>, sand, entries, 6000, slag, 10);
	}
	//Armor
	{
		InductionSmelter.addRecipe(<metallurgy:quicksilver_ingot> * 2, sand, <metallurgy:quicksilver_helmet>, 6000, slag, 15);
		InductionSmelter.addRecipe(<metallurgy:quicksilver_ingot> * 4, sand, <metallurgy:quicksilver_chestplate>, 6000, slag, 25);
		InductionSmelter.addRecipe(<metallurgy:quicksilver_ingot> * 3, sand, <metallurgy:quicksilver_leggings>, 6000, slag, 20);
		InductionSmelter.addRecipe(<metallurgy:quicksilver_ingot> * 1, sand, <metallurgy:quicksilver_boots>, 6000, slag, 15);
	}
}
{//brass
	for entries in brassTools
	{
		InductionSmelter.addRecipe(<metallurgy:brass_ingot>, sand, entries, 6000, slag, 10);
	}
	//Armor
	{
		InductionSmelter.addRecipe(<metallurgy:brass_ingot> * 2, sand, <metallurgy:brass_helmet>, 6000, slag, 15);
		InductionSmelter.addRecipe(<metallurgy:brass_ingot> * 4, sand, <metallurgy:brass_chestplate>, 6000, slag, 25);
		InductionSmelter.addRecipe(<metallurgy:brass_ingot> * 3, sand, <metallurgy:brass_leggings>, 6000, slag, 20);
		InductionSmelter.addRecipe(<metallurgy:brass_ingot> * 1, sand, <metallurgy:brass_boots>, 6000, slag, 15);
	}
}
{//astral_silver
	for entries in astralSilverTools
	{
		InductionSmelter.addRecipe(<metallurgy:astral_silver_ingot>, sand, entries, 6000, slag, 10);
	}
	//Armor
	{
		InductionSmelter.addRecipe(<metallurgy:astral_silver_ingot> * 2, sand, <metallurgy:astral_silver_helmet>, 6000, slag, 15);
		InductionSmelter.addRecipe(<metallurgy:astral_silver_ingot> * 4, sand, <metallurgy:astral_silver_chestplate>, 6000, slag, 25);
		InductionSmelter.addRecipe(<metallurgy:astral_silver_ingot> * 3, sand, <metallurgy:astral_silver_leggings>, 6000, slag, 20);
		InductionSmelter.addRecipe(<metallurgy:astral_silver_ingot> * 1, sand, <metallurgy:astral_silver_boots>, 6000, slag, 15);
	}
}
{//hepatizon
	for entries in hepatizonTools
	{
		InductionSmelter.addRecipe(<metallurgy:hepatizon_ingot>, sand, entries, 6000, slag, 10);
	}
	//Armor
	{
		InductionSmelter.addRecipe(<metallurgy:hepatizon_ingot> * 2, sand, <metallurgy:hepatizon_helmet>, 6000, slag, 15);
		InductionSmelter.addRecipe(<metallurgy:hepatizon_ingot> * 4, sand, <metallurgy:hepatizon_chestplate>, 6000, slag, 25);
		InductionSmelter.addRecipe(<metallurgy:hepatizon_ingot> * 3, sand, <metallurgy:hepatizon_leggings>, 6000, slag, 20);
		InductionSmelter.addRecipe(<metallurgy:hepatizon_ingot> * 1, sand, <metallurgy:hepatizon_boots>, 6000, slag, 15);
	}
}
{//bronze
	for entries in bronzeTools
	{
		InductionSmelter.addRecipe(<metallurgy:bronze_ingot>, sand, entries, 6000, slag, 10);
	}
	//Armor
	{
		InductionSmelter.addRecipe(<metallurgy:bronze_ingot> * 2, sand, <metallurgy:bronze_helmet>, 6000, slag, 15);
		InductionSmelter.addRecipe(<metallurgy:bronze_ingot> * 4, sand, <metallurgy:bronze_chestplate>, 6000, slag, 25);
		InductionSmelter.addRecipe(<metallurgy:bronze_ingot> * 3, sand, <metallurgy:bronze_leggings>, 6000, slag, 20);
		InductionSmelter.addRecipe(<metallurgy:bronze_ingot> * 1, sand, <metallurgy:bronze_boots>, 6000, slag, 15);
	}
}
{//sanguinite
	for entries in sanguiniteTools
	{
		InductionSmelter.addRecipe(<metallurgy:sanguinite_ingot>, sand, entries, 6000, slag, 10);
	}
	//Armor
	{
		InductionSmelter.addRecipe(<metallurgy:sanguinite_ingot> * 2, sand, <metallurgy:sanguinite_helmet>, 6000, slag, 15);
		InductionSmelter.addRecipe(<metallurgy:sanguinite_ingot> * 4, sand, <metallurgy:sanguinite_chestplate>, 6000, slag, 25);
		InductionSmelter.addRecipe(<metallurgy:sanguinite_ingot> * 3, sand, <metallurgy:sanguinite_leggings>, 6000, slag, 20);
		InductionSmelter.addRecipe(<metallurgy:sanguinite_ingot> * 1, sand, <metallurgy:sanguinite_boots>, 6000, slag, 15);
	}
}
{//eximite
	for entries in eximiteTools
	{
		InductionSmelter.addRecipe(<metallurgy:eximite_ingot>, sand, entries, 6000, slag, 10);
	}
	//Armor
	{
		InductionSmelter.addRecipe(<metallurgy:eximite_ingot> * 2, sand, <metallurgy:eximite_helmet>, 6000, slag, 15);
		InductionSmelter.addRecipe(<metallurgy:eximite_ingot> * 4, sand, <metallurgy:eximite_chestplate>, 6000, slag, 25);
		InductionSmelter.addRecipe(<metallurgy:eximite_ingot> * 3, sand, <metallurgy:eximite_leggings>, 6000, slag, 20);
		InductionSmelter.addRecipe(<metallurgy:eximite_ingot> * 1, sand, <metallurgy:eximite_boots>, 6000, slag, 15);
	}
}
{//silver
	for entries in silverTools
	{
		InductionSmelter.addRecipe(<metallurgy:silver_ingot>, sand, entries, 6000, slag, 10);
	}
	//Armor
	{
		InductionSmelter.addRecipe(<metallurgy:silver_ingot> * 2, sand, <metallurgy:silver_helmet>, 6000, slag, 15);
		InductionSmelter.addRecipe(<metallurgy:silver_ingot> * 4, sand, <metallurgy:silver_chestplate>, 6000, slag, 25);
		InductionSmelter.addRecipe(<metallurgy:silver_ingot> * 3, sand, <metallurgy:silver_leggings>, 6000, slag, 20);
		InductionSmelter.addRecipe(<metallurgy:silver_ingot> * 1, sand, <metallurgy:silver_boots>, 6000, slag, 15);
	}
}
{//desichalkos
	for entries in desichalkosTools
	{
		InductionSmelter.addRecipe(<metallurgy:desichalkos_ingot>, sand, entries, 6000, slag, 10);
	}
	//Armor
	{
		InductionSmelter.addRecipe(<metallurgy:desichalkos_ingot> * 2, sand, <metallurgy:desichalkos_helmet>, 6000, slag, 15);
		InductionSmelter.addRecipe(<metallurgy:desichalkos_ingot> * 4, sand, <metallurgy:desichalkos_chestplate>, 6000, slag, 25);
		InductionSmelter.addRecipe(<metallurgy:desichalkos_ingot> * 3, sand, <metallurgy:desichalkos_leggings>, 6000, slag, 20);
		InductionSmelter.addRecipe(<metallurgy:desichalkos_ingot> * 1, sand, <metallurgy:desichalkos_boots>, 6000, slag, 15);
	}
}
{//celenegil
	for entries in celenegilTools
	{
		InductionSmelter.addRecipe(<metallurgy:celenegil_ingot>, sand, entries, 6000, slag, 10);
	}
	//Armor
	{
		InductionSmelter.addRecipe(<metallurgy:celenegil_ingot> * 2, sand, <metallurgy:celenegil_helmet>, 6000, slag, 15);
		InductionSmelter.addRecipe(<metallurgy:celenegil_ingot> * 4, sand, <metallurgy:celenegil_chestplate>, 6000, slag, 25);
		InductionSmelter.addRecipe(<metallurgy:celenegil_ingot> * 3, sand, <metallurgy:celenegil_leggings>, 6000, slag, 20);
		InductionSmelter.addRecipe(<metallurgy:celenegil_ingot> * 1, sand, <metallurgy:celenegil_boots>, 6000, slag, 15);
	}
}
{//shadow_iron
	for entries in shadowIronTools
	{
		InductionSmelter.addRecipe(<metallurgy:shadow_iron_ingot>, sand, entries, 6000, slag, 10);
	}
	//Armor
	{
		InductionSmelter.addRecipe(<metallurgy:shadow_iron_ingot> * 2, sand, <metallurgy:shadow_iron_helmet>, 6000, slag, 15);
		InductionSmelter.addRecipe(<metallurgy:shadow_iron_ingot> * 4, sand, <metallurgy:shadow_iron_chestplate>, 6000, slag, 25);
		InductionSmelter.addRecipe(<metallurgy:shadow_iron_ingot> * 3, sand, <metallurgy:shadow_iron_leggings>, 6000, slag, 20);
		InductionSmelter.addRecipe(<metallurgy:shadow_iron_ingot> * 1, sand, <metallurgy:shadow_iron_boots>, 6000, slag, 15);
	}
}
{//steel
	for entries in steelTools
	{
		InductionSmelter.addRecipe(<metallurgy:steel_ingot>, sand, entries, 6000, slag, 10);
	}
	//Armor
	{
		InductionSmelter.addRecipe(<metallurgy:steel_ingot> * 2, sand, <metallurgy:steel_helmet>, 6000, slag, 15);
		InductionSmelter.addRecipe(<metallurgy:steel_ingot> * 4, sand, <metallurgy:steel_chestplate>, 6000, slag, 25);
		InductionSmelter.addRecipe(<metallurgy:steel_ingot> * 3, sand, <metallurgy:steel_leggings>, 6000, slag, 20);
		InductionSmelter.addRecipe(<metallurgy:steel_ingot> * 1, sand, <metallurgy:steel_boots>, 6000, slag, 15);
	}
}
{//carmot
	for entries in carmotTools
	{
		InductionSmelter.addRecipe(<metallurgy:carmot_ingot>, sand, entries, 6000, slag, 10);
	}
	//Armor
	{
		InductionSmelter.addRecipe(<metallurgy:carmot_ingot> * 2, sand, <metallurgy:carmot_helmet>, 6000, slag, 15);
		InductionSmelter.addRecipe(<metallurgy:carmot_ingot> * 4, sand, <metallurgy:carmot_chestplate>, 6000, slag, 25);
		InductionSmelter.addRecipe(<metallurgy:carmot_ingot> * 3, sand, <metallurgy:carmot_leggings>, 6000, slag, 20);
		InductionSmelter.addRecipe(<metallurgy:carmot_ingot> * 1, sand, <metallurgy:carmot_boots>, 6000, slag, 15);
	}
}
{//mithril
	for entries in mithrilTools
	{
		InductionSmelter.addRecipe(<metallurgy:mithril_ingot>, sand, entries, 6000, slag, 10);
	}
	//Armor
	{
		InductionSmelter.addRecipe(<metallurgy:mithril_ingot> * 2, sand, <metallurgy:mithril_helmet>, 6000, slag, 15);
		InductionSmelter.addRecipe(<metallurgy:mithril_ingot> * 4, sand, <metallurgy:mithril_chestplate>, 6000, slag, 25);
		InductionSmelter.addRecipe(<metallurgy:mithril_ingot> * 3, sand, <metallurgy:mithril_leggings>, 6000, slag, 20);
		InductionSmelter.addRecipe(<metallurgy:mithril_ingot> * 1, sand, <metallurgy:mithril_boots>, 6000, slag, 15);
	}
}
{//ceruclase
	for entries in ceruclaseTools
	{
		InductionSmelter.addRecipe(<metallurgy:ceruclase_ingot>, sand, entries, 6000, slag, 10);
	}
	//Armor
	{
		InductionSmelter.addRecipe(<metallurgy:ceruclase_ingot> * 2, sand, <metallurgy:ceruclase_helmet>, 6000, slag, 15);
		InductionSmelter.addRecipe(<metallurgy:ceruclase_ingot> * 4, sand, <metallurgy:ceruclase_chestplate>, 6000, slag, 25);
		InductionSmelter.addRecipe(<metallurgy:ceruclase_ingot> * 3, sand, <metallurgy:ceruclase_leggings>, 6000, slag, 20);
		InductionSmelter.addRecipe(<metallurgy:ceruclase_ingot> * 1, sand, <metallurgy:ceruclase_boots>, 6000, slag, 15);
	}
}
{//deep_iron
	for entries in deepIronTools
	{
		InductionSmelter.addRecipe(<metallurgy:deep_iron_ingot>, sand, entries, 6000, slag, 10);
	}
	//Armor
	{
		InductionSmelter.addRecipe(<metallurgy:deep_iron_ingot> * 2, sand, <metallurgy:deep_iron_helmet>, 6000, slag, 15);
		InductionSmelter.addRecipe(<metallurgy:deep_iron_ingot> * 4, sand, <metallurgy:deep_iron_chestplate>, 6000, slag, 25);
		InductionSmelter.addRecipe(<metallurgy:deep_iron_ingot> * 3, sand, <metallurgy:deep_iron_leggings>, 6000, slag, 20);
		InductionSmelter.addRecipe(<metallurgy:deep_iron_ingot> * 1, sand, <metallurgy:deep_iron_boots>, 6000, slag, 15);
	}
}
{//angmallen
	for entries in angmallenTools
	{
		InductionSmelter.addRecipe(<metallurgy:angmallen_ingot>, sand, entries, 6000, slag, 10);
	}
	//Armor
	{
		InductionSmelter.addRecipe(<metallurgy:angmallen_ingot> * 2, sand, <metallurgy:angmallen_helmet>, 6000, slag, 15);
		InductionSmelter.addRecipe(<metallurgy:angmallen_ingot> * 4, sand, <metallurgy:angmallen_chestplate>, 6000, slag, 25);
		InductionSmelter.addRecipe(<metallurgy:angmallen_ingot> * 3, sand, <metallurgy:angmallen_leggings>, 6000, slag, 20);
		InductionSmelter.addRecipe(<metallurgy:angmallen_ingot> * 1, sand, <metallurgy:angmallen_boots>, 6000, slag, 15);
	}
}
{//kalendrite
	for entries in kalendriteTools
	{
		InductionSmelter.addRecipe(<metallurgy:kalendrite_ingot>, sand, entries, 6000, slag, 10);
	}
	//Armor
	{
		InductionSmelter.addRecipe(<metallurgy:kalendrite_ingot> * 2, sand, <metallurgy:kalendrite_helmet>, 6000, slag, 15);
		InductionSmelter.addRecipe(<metallurgy:kalendrite_ingot> * 4, sand, <metallurgy:kalendrite_chestplate>, 6000, slag, 25);
		InductionSmelter.addRecipe(<metallurgy:kalendrite_ingot> * 3, sand, <metallurgy:kalendrite_leggings>, 6000, slag, 20);
		InductionSmelter.addRecipe(<metallurgy:kalendrite_ingot> * 1, sand, <metallurgy:kalendrite_boots>, 6000, slag, 15);
	}
}
{//damascus_steel
	for entries in damascusSteelTools
	{
		InductionSmelter.addRecipe(<metallurgy:damascus_steel_ingot>, sand, entries, 6000, slag, 10);
	}
	//Armor
	{
		InductionSmelter.addRecipe(<metallurgy:damascus_steel_ingot> * 2, sand, <metallurgy:damascus_steel_helmet>, 6000, slag, 15);
		InductionSmelter.addRecipe(<metallurgy:damascus_steel_ingot> * 4, sand, <metallurgy:damascus_steel_chestplate>, 6000, slag, 25);
		InductionSmelter.addRecipe(<metallurgy:damascus_steel_ingot> * 3, sand, <metallurgy:damascus_steel_leggings>, 6000, slag, 20);
		InductionSmelter.addRecipe(<metallurgy:damascus_steel_ingot> * 1, sand, <metallurgy:damascus_steel_boots>, 6000, slag, 15);
	}
}
{//prometheum
	for entries in prometheumTools
	{
		InductionSmelter.addRecipe(<metallurgy:prometheum_ingot>, sand, entries, 6000, slag, 10);
	}
	//Armor
	{
		InductionSmelter.addRecipe(<metallurgy:prometheum_ingot> * 2, sand, <metallurgy:prometheum_helmet>, 6000, slag, 15);
		InductionSmelter.addRecipe(<metallurgy:prometheum_ingot> * 4, sand, <metallurgy:prometheum_chestplate>, 6000, slag, 25);
		InductionSmelter.addRecipe(<metallurgy:prometheum_ingot> * 3, sand, <metallurgy:prometheum_leggings>, 6000, slag, 20);
		InductionSmelter.addRecipe(<metallurgy:prometheum_ingot> * 1, sand, <metallurgy:prometheum_boots>, 6000, slag, 15);
	}
}
{//copper
	for entries in copperTools
	{
		InductionSmelter.addRecipe(<metallurgy:copper_ingot>, sand, entries, 6000, slag, 10);
	}
	//Armor
	{
		InductionSmelter.addRecipe(<metallurgy:copper_ingot> * 2, sand, <metallurgy:copper_helmet>, 6000, slag, 15);
		InductionSmelter.addRecipe(<metallurgy:copper_ingot> * 4, sand, <metallurgy:copper_chestplate>, 6000, slag, 25);
		InductionSmelter.addRecipe(<metallurgy:copper_ingot> * 3, sand, <metallurgy:copper_leggings>, 6000, slag, 20);
		InductionSmelter.addRecipe(<metallurgy:copper_ingot> * 1, sand, <metallurgy:copper_boots>, 6000, slag, 15);
	}
}
{//adamantine
	for entries in adamantineTools
	{
		InductionSmelter.addRecipe(<metallurgy:adamantine_ingot>, sand, entries, 6000, slag, 10);
	}
	//Armor
	{
		InductionSmelter.addRecipe(<metallurgy:adamantine_ingot> * 2, sand, <metallurgy:adamantine_helmet>, 6000, slag, 15);
		InductionSmelter.addRecipe(<metallurgy:adamantine_ingot> * 4, sand, <metallurgy:adamantine_chestplate>, 6000, slag, 25);
		InductionSmelter.addRecipe(<metallurgy:adamantine_ingot> * 3, sand, <metallurgy:adamantine_leggings>, 6000, slag, 20);
		InductionSmelter.addRecipe(<metallurgy:adamantine_ingot> * 1, sand, <metallurgy:adamantine_boots>, 6000, slag, 15);
	}
}
{//electrum
	for entries in electrumTools
	{
		InductionSmelter.addRecipe(<metallurgy:electrum_ingot>, sand, entries, 6000, slag, 10);
	}
	//Armor
	{
		InductionSmelter.addRecipe(<metallurgy:electrum_ingot> * 2, sand, <metallurgy:electrum_helmet>, 6000, slag, 15);
		InductionSmelter.addRecipe(<metallurgy:electrum_ingot> * 4, sand, <metallurgy:electrum_chestplate>, 6000, slag, 25);
		InductionSmelter.addRecipe(<metallurgy:electrum_ingot> * 3, sand, <metallurgy:electrum_leggings>, 6000, slag, 20);
		InductionSmelter.addRecipe(<metallurgy:electrum_ingot> * 1, sand, <metallurgy:electrum_boots>, 6000, slag, 15);
	}
}
{//tartarite
	for entries in tartariteTools
	{
		InductionSmelter.addRecipe(<metallurgy:tartarite_ingot>, sand, entries, 6000, slag, 10);
	}
	//Armor
	{
		InductionSmelter.addRecipe(<metallurgy:tartarite_ingot> * 2, sand, <metallurgy:tartarite_helmet>, 6000, slag, 15);
		InductionSmelter.addRecipe(<metallurgy:tartarite_ingot> * 4, sand, <metallurgy:tartarite_chestplate>, 6000, slag, 25);
		InductionSmelter.addRecipe(<metallurgy:tartarite_ingot> * 3, sand, <metallurgy:tartarite_leggings>, 6000, slag, 20);
		InductionSmelter.addRecipe(<metallurgy:tartarite_ingot> * 1, sand, <metallurgy:tartarite_boots>, 6000, slag, 15);
	}
}
{//atlarus
	for entries in atlarusTools
	{
		InductionSmelter.addRecipe(<metallurgy:atlarus_ingot>, sand, entries, 6000, slag, 10);
	}
	//Armor
	{
		InductionSmelter.addRecipe(<metallurgy:atlarus_ingot> * 2, sand, <metallurgy:atlarus_helmet>, 6000, slag, 15);
		InductionSmelter.addRecipe(<metallurgy:atlarus_ingot> * 4, sand, <metallurgy:atlarus_chestplate>, 6000, slag, 25);
		InductionSmelter.addRecipe(<metallurgy:atlarus_ingot> * 3, sand, <metallurgy:atlarus_leggings>, 6000, slag, 20);
		InductionSmelter.addRecipe(<metallurgy:atlarus_ingot> * 1, sand, <metallurgy:atlarus_boots>, 6000, slag, 15);
	}
}
{//black_steel
	for entries in blackSteelTools
	{
		InductionSmelter.addRecipe(<metallurgy:black_steel_ingot>, sand, entries, 6000, slag, 10);
	}
	//Armor
	{
		InductionSmelter.addRecipe(<metallurgy:black_steel_ingot> * 2, sand, <metallurgy:black_steel_helmet>, 6000, slag, 15);
		InductionSmelter.addRecipe(<metallurgy:black_steel_ingot> * 4, sand, <metallurgy:black_steel_chestplate>, 6000, slag, 25);
		InductionSmelter.addRecipe(<metallurgy:black_steel_ingot> * 3, sand, <metallurgy:black_steel_leggings>, 6000, slag, 20);
		InductionSmelter.addRecipe(<metallurgy:black_steel_ingot> * 1, sand, <metallurgy:black_steel_boots>, 6000, slag, 15);
	}
}
{//vyroxeres
	for entries in vyroxeresTools
	{
		InductionSmelter.addRecipe(<metallurgy:vyroxeres_ingot>, sand, entries, 6000, slag, 10);
	}
	//Armor
	{
		InductionSmelter.addRecipe(<metallurgy:vyroxeres_ingot> * 2, sand, <metallurgy:vyroxeres_helmet>, 6000, slag, 15);
		InductionSmelter.addRecipe(<metallurgy:vyroxeres_ingot> * 4, sand, <metallurgy:vyroxeres_chestplate>, 6000, slag, 25);
		InductionSmelter.addRecipe(<metallurgy:vyroxeres_ingot> * 3, sand, <metallurgy:vyroxeres_leggings>, 6000, slag, 20);
		InductionSmelter.addRecipe(<metallurgy:vyroxeres_ingot> * 1, sand, <metallurgy:vyroxeres_boots>, 6000, slag, 15);
	}
}
{//lutetium
	//Armor
	{
		InductionSmelter.addRecipe(<metallurgy:lutetium_ingot> * 2, sand, <metallurgy:lutetium_helmet>, 6000, slag, 15);
		InductionSmelter.addRecipe(<metallurgy:lutetium_ingot> * 4, sand, <metallurgy:lutetium_chestplate>, 6000, slag, 25);
		InductionSmelter.addRecipe(<metallurgy:lutetium_ingot> * 3, sand, <metallurgy:lutetium_leggings>, 6000, slag, 20);
		InductionSmelter.addRecipe(<metallurgy:lutetium_ingot> * 1, sand, <metallurgy:lutetium_boots>, 6000, slag, 15);
	}
}
{//osmium
	//Armor
	{
		InductionSmelter.addRecipe(<metallurgy:osmium_ingot> * 2, sand, <metallurgy:osmium_helmet>, 6000, slag, 15);
		InductionSmelter.addRecipe(<metallurgy:osmium_ingot> * 4, sand, <metallurgy:osmium_chestplate>, 6000, slag, 25);
		InductionSmelter.addRecipe(<metallurgy:osmium_ingot> * 3, sand, <metallurgy:osmium_leggings>, 6000, slag, 20);
		InductionSmelter.addRecipe(<metallurgy:osmium_ingot> * 1, sand, <metallurgy:osmium_boots>, 6000, slag, 15);
	}
}
{//oureclase
	for entries in oureclaseTools
	{
		InductionSmelter.addRecipe(<metallurgy:oureclase_ingot>, sand, entries, 6000, slag, 10);
	}
	//Armor
	{
		InductionSmelter.addRecipe(<metallurgy:oureclase_ingot> * 2, sand, <metallurgy:oureclase_helmet>, 6000, slag, 15);
		InductionSmelter.addRecipe(<metallurgy:oureclase_ingot> * 4, sand, <metallurgy:oureclase_chestplate>, 6000, slag, 25);
		InductionSmelter.addRecipe(<metallurgy:oureclase_ingot> * 3, sand, <metallurgy:oureclase_leggings>, 6000, slag, 20);
		InductionSmelter.addRecipe(<metallurgy:oureclase_ingot> * 1, sand, <metallurgy:oureclase_boots>, 6000, slag, 15);
	}
}
{//inolashite
	for entries in inolashiteTools
	{
		InductionSmelter.addRecipe(<metallurgy:inolashite_ingot>, sand, entries, 6000, slag, 10);
	}
	//Armor
	{
		InductionSmelter.addRecipe(<metallurgy:inolashite_ingot> * 2, sand, <metallurgy:inolashite_helmet>, 6000, slag, 15);
		InductionSmelter.addRecipe(<metallurgy:inolashite_ingot> * 4, sand, <metallurgy:inolashite_chestplate>, 6000, slag, 25);
		InductionSmelter.addRecipe(<metallurgy:inolashite_ingot> * 3, sand, <metallurgy:inolashite_leggings>, 6000, slag, 20);
		InductionSmelter.addRecipe(<metallurgy:inolashite_ingot> * 1, sand, <metallurgy:inolashite_boots>, 6000, slag, 15);
	}
}
{//orichalcum
	for entries in orichalcumTools
	{
		InductionSmelter.addRecipe(<metallurgy:orichalcum_ingot>, sand, entries, 6000, slag, 10);
	}
	//Armor
	{
		InductionSmelter.addRecipe(<metallurgy:orichalcum_ingot> * 2, sand, <metallurgy:orichalcum_helmet>, 6000, slag, 15);
		InductionSmelter.addRecipe(<metallurgy:orichalcum_ingot> * 4, sand, <metallurgy:orichalcum_chestplate>, 6000, slag, 25);
		InductionSmelter.addRecipe(<metallurgy:orichalcum_ingot> * 3, sand, <metallurgy:orichalcum_leggings>, 6000, slag, 20);
		InductionSmelter.addRecipe(<metallurgy:orichalcum_ingot> * 1, sand, <metallurgy:orichalcum_boots>, 6000, slag, 15);
	}
}
{//shadowSteel
	for entries in shadowSteelTools
	{
		InductionSmelter.addRecipe(<metallurgy:shadow_steel_ingot>, sand, entries, 6000, slag, 10);
	}
	//Armor
	{
		InductionSmelter.addRecipe(<metallurgy:shadow_steel_ingot> * 2, sand, <metallurgy:shadow_steel_helmet>, 6000, slag, 15);
		InductionSmelter.addRecipe(<metallurgy:shadow_steel_ingot> * 4, sand, <metallurgy:shadow_steel_chestplate>, 6000, slag, 25);
		InductionSmelter.addRecipe(<metallurgy:shadow_steel_ingot> * 3, sand, <metallurgy:shadow_steel_leggings>, 6000, slag, 20);
		InductionSmelter.addRecipe(<metallurgy:shadow_steel_ingot> * 1, sand, <metallurgy:shadow_steel_boots>, 6000, slag, 15);
	}
}
{//midasium
	for entries in midasiumTools
	{
		InductionSmelter.addRecipe(<metallurgy:midasium_ingot>, sand, entries, 6000, slag, 10);
	}
	//Armor
	{
		InductionSmelter.addRecipe(<metallurgy:midasium_ingot> * 2, sand, <metallurgy:midasium_helmet>, 6000, slag, 15);
		InductionSmelter.addRecipe(<metallurgy:midasium_ingot> * 4, sand, <metallurgy:midasium_chestplate>, 6000, slag, 25);
		InductionSmelter.addRecipe(<metallurgy:midasium_ingot> * 3, sand, <metallurgy:midasium_leggings>, 6000, slag, 20);
		InductionSmelter.addRecipe(<metallurgy:midasium_ingot> * 1, sand, <metallurgy:midasium_boots>, 6000, slag, 15);
	}
}
{//krik
	for entries in krikTools
	{
		InductionSmelter.addRecipe(<metallurgy:krik_ingot>, sand, entries, 6000, slag, 10);
	}
	//Armor
	{
		InductionSmelter.addRecipe(<metallurgy:krik_ingot> * 2, sand, <metallurgy:krik_helmet>, 6000, slag, 15);
		InductionSmelter.addRecipe(<metallurgy:krik_ingot> * 4, sand, <metallurgy:krik_chestplate>, 6000, slag, 25);
		InductionSmelter.addRecipe(<metallurgy:krik_ingot> * 3, sand, <metallurgy:krik_leggings>, 6000, slag, 20);
		InductionSmelter.addRecipe(<metallurgy:krik_ingot> * 1, sand, <metallurgy:krik_boots>, 6000, slag, 15);
	}
}
{//rubracium
	InductionSmelter.addRecipe(<metallurgy:rubracium_ingot> * 5, sand, <metallurgy:rubracium_gauntlet>, 6000, slag, 25);
}
{//lemurite
	InductionSmelter.addRecipe(<metallurgy:lemurite_ingot> * 3, sand, <metallurgy:lemurite_shield>, 6000, slag, 20);
}
print("Success!");