#priority 89
#modloaded metallurgy thermalexpansion crafttweaker modtweaker

import mods.thermalexpansion.Centrifuge;
import crafttweaker.item.IItemStack;
import crafttweaker.item.WeightedItemStack;
import crafttweaker.liquid.ILiquidStack;
import crafttweaker.oredict.IOreDictEntry;
import crafttweaker.oredict.IOreDict;

print("---------------------------------------------------------------------------------------------");
print("This script allows you to use the Centrifugal Separator to separate Metallurgy's metal dusts!");
print("Make sure to check the recipe book or JEI to know the recipes!");
print("All rights reserved unless stated otherwise.");
print("---------------------------------------------------------------------------------------------");
print("Proceeding to add Centrifugal Separator Recipes...");

val dustArray2 = [<ore:dustAmordrine>.firstItem,<ore:dustEtherium>.firstItem,<ore:dustHepatizon>.firstItem,<ore:dustDesichalkos>.firstItem,<ore:dustCelenegil>.firstItem,<ore:dustAngmallen>.firstItem,<ore:dustTartarite>.firstItem,<ore:dustInolashite>.firstItem,<ore:dustKrik>.firstItem,<ore:dustQuicksilver>.firstItem] as IItemStack[];
val dustArray2Component1 = [<metallurgy:platinum_dust>,<metallurgy:sanguinite_dust>,<metallurgy:infuscolium_dust>,<metallurgy:eximite_dust>,<metallurgy:orichalcum_dust>,<metallurgy:gold_dust>,<metallurgy:atlarus_dust>,<metallurgy:alduorite_dust>,<metallurgy:lutetium_dust>,<metallurgy:silver_dust>] as WeightedItemStack[];
val dustArray2Component2 = [<metallurgy:kalendrite_dust>,<metallurgy:carmot_dust>,<metallurgy:steel_dust>,<metallurgy:meutoite_dust>,<metallurgy:platinum_dust>,<metallurgy:iron_dust>,<metallurgy:adamantine_dust>,<metallurgy:ceruclase_dust>,<metallurgy:osmium_dust>,<metallurgy:mithril_dust>] as WeightedItemStack[];

val dustArray3 = [<ore:dustHaderoth>.firstItem,<ore:dustDamascusSteel>.firstItem,<ore:dustShadowSteel>.firstItem] as IItemStack[];
val dustArray3Component1 = [<metallurgy:rubracium_dust>,<metallurgy:bronze_dust>,<metallurgy:shadow_iron_dust>] as IItemStack[];
val dustArray3Component2 = [<metallurgy:mithril_dust>,<metallurgy:iron_dust>,<metallurgy:lemurite_dust>] as IItemStack[];

val dustArray4 = [<ore:dustSteel>.firstItem,<ore:dustBlackSteel>.firstItem] as IItemStack[];
val dustArray4Component1 = [<metallurgy:iron_dust>,<metallurgy:infuscolium_dust>] as IItemStack[];
val dustArray4Component2 = [<metallurgy:manganese_dust>,<metallurgy:deep_iron_dust>] as IItemStack[];

for i in 0 .. dustArray2.length
{
	mods.thermalexpansion.Centrifuge.addRecipe([(dustArray2Component1[i]), dustArray2Component2[i]], dustArray2[i], null, 4000);
}
for i in 0 .. dustArray3.length
{
	mods.thermalexpansion.Centrifuge.addRecipe([(dustArray3Component1[i] * 2), dustArray3Component2[i]], dustArray3[i], null, 6000);
}
for i in 0 .. dustArray4.length
{
	mods.thermalexpansion.Centrifuge.addRecipe([(dustArray4Component1[i]), dustArray4Component2[i] * 3], dustArray4[i], null, 8000);
}
print("Success!");
print("Proceeding to next script...");



