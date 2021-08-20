#priority 97
#modloaded metallurgy !chisel crafttweaker
import crafttweaker.item.IItemStack;
import crafttweaker.oredict.IOreDictEntry;
print("-------------------------------------------------------------------------------------------------------------------------------------------");
print("Since chisel was not found in your load order, this script is running, unless that is intentional, make sure chisel is in your mods folder!");
print("Make sure to check the recipe book or JEI to know how to craft the blocks!");
print("All rights reserved unless stated otherwise.");
print("-------------------------------------------------------------------------------------------------------------------------------------------");
print("Proceeding to create new crafting recipes for metallurgy's metal blocks...");
{//amordrine
	recipes.addShapeless("amordrineB2B_1",<metallurgy:amordrine_engraved_block>*2,[<metallurgy:amordrine_block>,<metallurgy:amordrine_block>]);
	recipes.addShapeless("amordrineB2B_2",<metallurgy:amordrine_large_bricks>*2,[<metallurgy:amordrine_engraved_block>,<metallurgy:amordrine_engraved_block>]);
	recipes.addShapeless("amordrineB2B_3",<metallurgy:amordrine_bricks>*2,[<metallurgy:amordrine_large_bricks>,<metallurgy:amordrine_large_bricks>]);
	recipes.addShapeless("amordrineB2B_4",<metallurgy:amordrine_crystals>*2,[<metallurgy:amordrine_bricks>,<metallurgy:amordrine_bricks>]);
	recipes.addShapeless("amordrineB2B_5",<metallurgy:amordrine_hazard_block>*2,[<metallurgy:amordrine_crystals>,<metallurgy:amordrine_crystals>]);
	recipes.addShapeless("amordrineB2B_6",<metallurgy:amordrine_reinforced_glass>*2,[<metallurgy:amordrine_hazard_block>,<metallurgy:amordrine_hazard_block>]);
	recipes.addShapeless("amordrineB2B_7",<metallurgy:amordrine_block>*2,[<metallurgy:amordrine_reinforced_glass>,<metallurgy:amordrine_reinforced_glass>]);
	recipes.addShapeless("amordrineB2I",<metallurgy:amordrine_ingot>*4,[<ore:decorAmordrine>]);
	
	recipes.addShapedMirrored("amordrineItB", <metallurgy:amordrine_block>,
	[[<ore:ingotAmordrine>, <ore:ingotAmordrine>, null],
	 [<ore:ingotAmordrine>, <ore:ingotAmordrine>, null],
	 [null, null, null]]);
}
{//haderoth
	recipes.addShapeless("haderothB2B_1",<metallurgy:haderoth_engraved_block>*2,[<metallurgy:haderoth_block>,<metallurgy:haderoth_block>]);
	recipes.addShapeless("haderothB2B_2",<metallurgy:haderoth_large_bricks>*2,[<metallurgy:haderoth_engraved_block>,<metallurgy:haderoth_engraved_block>]);
	recipes.addShapeless("haderothB2B_3",<metallurgy:haderoth_bricks>*2,[<metallurgy:haderoth_large_bricks>,<metallurgy:haderoth_large_bricks>]);
	recipes.addShapeless("haderothB2B_4",<metallurgy:haderoth_crystals>*2,[<metallurgy:haderoth_bricks>,<metallurgy:haderoth_bricks>]);
	recipes.addShapeless("haderothB2B_5",<metallurgy:haderoth_hazard_block>*2,[<metallurgy:haderoth_crystals>,<metallurgy:haderoth_crystals>]);
	recipes.addShapeless("haderothB2B_6",<metallurgy:haderoth_reinforced_glass>*2,[<metallurgy:haderoth_hazard_block>,<metallurgy:haderoth_hazard_block>]);
	recipes.addShapeless("haderothB2B_7",<metallurgy:haderoth_block>*2,[<metallurgy:haderoth_reinforced_glass>,<metallurgy:haderoth_reinforced_glass>]);
	recipes.addShapeless("haderothB2I",<metallurgy:haderoth_ingot>*4,[<ore:decorHaderoth>]);
	
	recipes.addShapedMirrored("haderothItB", <metallurgy:haderoth_block>,
	[[<ore:ingotHaderoth>, <ore:ingotHaderoth>, null],
	 [<ore:ingotHaderoth>, <ore:ingotHaderoth>, null],
	 [null, null, null]]);
}
{//alduorite
	recipes.addShapeless("alduoriteB2B_1",<metallurgy:alduorite_engraved_block>*2,[<metallurgy:alduorite_block>,<metallurgy:alduorite_block>]);
	recipes.addShapeless("alduoriteB2B_2",<metallurgy:alduorite_large_bricks>*2,[<metallurgy:alduorite_engraved_block>,<metallurgy:alduorite_engraved_block>]);
	recipes.addShapeless("alduoriteB2B_3",<metallurgy:alduorite_bricks>*2,[<metallurgy:alduorite_large_bricks>,<metallurgy:alduorite_large_bricks>]);
	recipes.addShapeless("alduoriteB2B_4",<metallurgy:alduorite_crystals>*2,[<metallurgy:alduorite_bricks>,<metallurgy:alduorite_bricks>]);
	recipes.addShapeless("alduoriteB2B_5",<metallurgy:alduorite_hazard_block>*2,[<metallurgy:alduorite_crystals>,<metallurgy:alduorite_crystals>]);
	recipes.addShapeless("alduoriteB2B_6",<metallurgy:alduorite_reinforced_glass>*2,[<metallurgy:alduorite_hazard_block>,<metallurgy:alduorite_hazard_block>]);
	recipes.addShapeless("alduoriteB2B_7",<metallurgy:alduorite_block>*2,[<metallurgy:alduorite_reinforced_glass>,<metallurgy:alduorite_reinforced_glass>]);
	recipes.addShapeless("alduoriteB2I",<metallurgy:alduorite_ingot>*4,[<ore:decorAlduorite>]);
	
	recipes.addShapedMirrored("alduoriteItB", <metallurgy:alduorite_block>,
	[[<ore:ingotAlduorite>, <ore:ingotAlduorite>, null],
	 [<ore:ingotAlduorite>, <ore:ingotAlduorite>, null],
	 [null, null, null]]);
}
{//platinum
	recipes.addShapeless("platinumB2B_1",<metallurgy:platinum_engraved_block>*2,[<metallurgy:platinum_block>,<metallurgy:platinum_block>]);
	recipes.addShapeless("platinumB2B_2",<metallurgy:platinum_large_bricks>*2,[<metallurgy:platinum_engraved_block>,<metallurgy:platinum_engraved_block>]);
	recipes.addShapeless("platinumB2B_3",<metallurgy:platinum_bricks>*2,[<metallurgy:platinum_large_bricks>,<metallurgy:platinum_large_bricks>]);
	recipes.addShapeless("platinumB2B_4",<metallurgy:platinum_crystals>*2,[<metallurgy:platinum_bricks>,<metallurgy:platinum_bricks>]);
	recipes.addShapeless("platinumB2B_5",<metallurgy:platinum_hazard_block>*2,[<metallurgy:platinum_crystals>,<metallurgy:platinum_crystals>]);
	recipes.addShapeless("platinumB2B_6",<metallurgy:platinum_reinforced_glass>*2,[<metallurgy:platinum_hazard_block>,<metallurgy:platinum_hazard_block>]);
	recipes.addShapeless("platinumB2B_7",<metallurgy:platinum_block>*2,[<metallurgy:platinum_reinforced_glass>,<metallurgy:platinum_reinforced_glass>]);
	recipes.addShapeless("platinumB2I",<metallurgy:platinum_ingot>*4,[<ore:decorPlatinum>]);
	
	recipes.addShapedMirrored("platinumItB", <metallurgy:platinum_block>,
	[[<ore:ingotPlatinum>, <ore:ingotPlatinum>, null],
	 [<ore:ingotPlatinum>, <ore:ingotPlatinum>, null],
	 [null, null, null]]);
}
{//vulcanite
	recipes.addShapeless("vulcaniteB2B_1",<metallurgy:vulcanite_engraved_block>*2,[<metallurgy:vulcanite_block>,<metallurgy:vulcanite_block>]);
	recipes.addShapeless("vulcaniteB2B_2",<metallurgy:vulcanite_large_bricks>*2,[<metallurgy:vulcanite_engraved_block>,<metallurgy:vulcanite_engraved_block>]);
	recipes.addShapeless("vulcaniteB2B_3",<metallurgy:vulcanite_bricks>*2,[<metallurgy:vulcanite_large_bricks>,<metallurgy:vulcanite_large_bricks>]);
	recipes.addShapeless("vulcaniteB2B_4",<metallurgy:vulcanite_crystals>*2,[<metallurgy:vulcanite_bricks>,<metallurgy:vulcanite_bricks>]);
	recipes.addShapeless("vulcaniteB2B_5",<metallurgy:vulcanite_hazard_block>*2,[<metallurgy:vulcanite_crystals>,<metallurgy:vulcanite_crystals>]);
	recipes.addShapeless("vulcaniteB2B_6",<metallurgy:vulcanite_reinforced_glass>*2,[<metallurgy:vulcanite_hazard_block>,<metallurgy:vulcanite_hazard_block>]);
	recipes.addShapeless("vulcaniteB2B_7",<metallurgy:vulcanite_block>*2,[<metallurgy:vulcanite_reinforced_glass>,<metallurgy:vulcanite_reinforced_glass>]);
	recipes.addShapeless("vulcaniteB2I",<metallurgy:vulcanite_ingot>*4,[<ore:decorVulcanite>]);
	
	recipes.addShapedMirrored("vulcaniteItB", <metallurgy:vulcanite_block>,
	[[<ore:ingotVulcanite>, <ore:ingotVulcanite>, null],
	 [<ore:ingotVulcanite>, <ore:ingotVulcanite>, null],
	 [null, null, null]]);
}
{//tin
	recipes.addShapeless("tinB2B_1",<metallurgy:tin_engraved_block>*2,[<metallurgy:tin_block>,<metallurgy:tin_block>]);
	recipes.addShapeless("tinB2B_2",<metallurgy:tin_large_bricks>*2,[<metallurgy:tin_engraved_block>,<metallurgy:tin_engraved_block>]);
	recipes.addShapeless("tinB2B_3",<metallurgy:tin_bricks>*2,[<metallurgy:tin_large_bricks>,<metallurgy:tin_large_bricks>]);
	recipes.addShapeless("tinB2B_4",<metallurgy:tin_crystals>*2,[<metallurgy:tin_bricks>,<metallurgy:tin_bricks>]);
	recipes.addShapeless("tinB2B_5",<metallurgy:tin_hazard_block>*2,[<metallurgy:tin_crystals>,<metallurgy:tin_crystals>]);
	recipes.addShapeless("tinB2B_6",<metallurgy:tin_reinforced_glass>*2,[<metallurgy:tin_hazard_block>,<metallurgy:tin_hazard_block>]);
	recipes.addShapeless("tinB2B_7",<metallurgy:tin_block>*2,[<metallurgy:tin_reinforced_glass>,<metallurgy:tin_reinforced_glass>]);
	recipes.addShapeless("tinB2I",<metallurgy:tin_ingot>*4,[<ore:decorTin>]);
	
	recipes.addShapedMirrored("tinItB", <metallurgy:tin_block>,
	[[<ore:ingotTin>, <ore:ingotTin>, null],
	 [<ore:ingotTin>, <ore:ingotTin>, null],
	 [null, null, null]]);
}
{//ignatius
	recipes.addShapeless("ignatiusB2B_1",<metallurgy:ignatius_engraved_block>*2,[<metallurgy:ignatius_block>,<metallurgy:ignatius_block>]);
	recipes.addShapeless("ignatiusB2B_2",<metallurgy:ignatius_large_bricks>*2,[<metallurgy:ignatius_engraved_block>,<metallurgy:ignatius_engraved_block>]);
	recipes.addShapeless("ignatiusB2B_3",<metallurgy:ignatius_bricks>*2,[<metallurgy:ignatius_large_bricks>,<metallurgy:ignatius_large_bricks>]);
	recipes.addShapeless("ignatiusB2B_4",<metallurgy:ignatius_crystals>*2,[<metallurgy:ignatius_bricks>,<metallurgy:ignatius_bricks>]);
	recipes.addShapeless("ignatiusB2B_5",<metallurgy:ignatius_hazard_block>*2,[<metallurgy:ignatius_crystals>,<metallurgy:ignatius_crystals>]);
	recipes.addShapeless("ignatiusB2B_6",<metallurgy:ignatius_reinforced_glass>*2,[<metallurgy:ignatius_hazard_block>,<metallurgy:ignatius_hazard_block>]);
	recipes.addShapeless("ignatiusB2B_7",<metallurgy:ignatius_block>*2,[<metallurgy:ignatius_reinforced_glass>,<metallurgy:ignatius_reinforced_glass>]);
	recipes.addShapeless("ignatiusB2I",<metallurgy:ignatius_ingot>*4,[<ore:decorIgnatius>]);
	
	recipes.addShapedMirrored("ignatiusItB", <metallurgy:ignatius_block>,
	[[<ore:ingotIgnatius>, <ore:ingotIgnatius>, null],
	 [<ore:ingotIgnatius>, <ore:ingotIgnatius>, null],
	 [null, null, null]]);
}
{//zinc
	recipes.addShapeless("zincB2B_1",<metallurgy:zinc_engraved_block>*2,[<metallurgy:zinc_block>,<metallurgy:zinc_block>]);
	recipes.addShapeless("zincB2B_2",<metallurgy:zinc_large_bricks>*2,[<metallurgy:zinc_engraved_block>,<metallurgy:zinc_engraved_block>]);
	recipes.addShapeless("zincB2B_3",<metallurgy:zinc_bricks>*2,[<metallurgy:zinc_large_bricks>,<metallurgy:zinc_large_bricks>]);
	recipes.addShapeless("zincB2B_4",<metallurgy:zinc_crystals>*2,[<metallurgy:zinc_bricks>,<metallurgy:zinc_bricks>]);
	recipes.addShapeless("zincB2B_5",<metallurgy:zinc_hazard_block>*2,[<metallurgy:zinc_crystals>,<metallurgy:zinc_crystals>]);
	recipes.addShapeless("zincB2B_6",<metallurgy:zinc_reinforced_glass>*2,[<metallurgy:zinc_hazard_block>,<metallurgy:zinc_hazard_block>]);
	recipes.addShapeless("zincB2B_7",<metallurgy:zinc_block>*2,[<metallurgy:zinc_reinforced_glass>,<metallurgy:zinc_reinforced_glass>]);
	recipes.addShapeless("zincB2I",<metallurgy:zinc_ingot>*4,[<ore:decorZinc>]);
	
	recipes.addShapedMirrored("zincItB", <metallurgy:zinc_block>,
	[[<ore:ingotZinc>, <ore:ingotZinc>, null],
	 [<ore:ingotZinc>, <ore:ingotZinc>, null],
	 [null, null, null]]);
}
{//etherium
	recipes.addShapeless("etheriumB2B_1",<metallurgy:etherium_engraved_block>*2,[<metallurgy:etherium_block>,<metallurgy:etherium_block>]);
	recipes.addShapeless("etheriumB2B_2",<metallurgy:etherium_large_bricks>*2,[<metallurgy:etherium_engraved_block>,<metallurgy:etherium_engraved_block>]);
	recipes.addShapeless("etheriumB2B_3",<metallurgy:etherium_bricks>*2,[<metallurgy:etherium_large_bricks>,<metallurgy:etherium_large_bricks>]);
	recipes.addShapeless("etheriumB2B_4",<metallurgy:etherium_crystals>*2,[<metallurgy:etherium_bricks>,<metallurgy:etherium_bricks>]);
	recipes.addShapeless("etheriumB2B_5",<metallurgy:etherium_hazard_block>*2,[<metallurgy:etherium_crystals>,<metallurgy:etherium_crystals>]);
	recipes.addShapeless("etheriumB2B_6",<metallurgy:etherium_reinforced_glass>*2,[<metallurgy:etherium_hazard_block>,<metallurgy:etherium_hazard_block>]);
	recipes.addShapeless("etheriumB2B_7",<metallurgy:etherium_block>*2,[<metallurgy:etherium_reinforced_glass>,<metallurgy:etherium_reinforced_glass>]);
	recipes.addShapeless("etheriumB2I",<metallurgy:etherium_ingot>*4,[<ore:decorEtherium>]);
	
	recipes.addShapedMirrored("etheriumItB", <metallurgy:etherium_block>,
	[[<ore:ingotEtherium>, <ore:ingotEtherium>, null],
	 [<ore:ingotEtherium>, <ore:ingotEtherium>, null],
	 [null, null, null]]);
}
{//quicksilver
	recipes.addShapeless("quicksilverB2B_1",<metallurgy:quicksilver_engraved_block>*2,[<metallurgy:quicksilver_block>,<metallurgy:quicksilver_block>]);
	recipes.addShapeless("quicksilverB2B_2",<metallurgy:quicksilver_large_bricks>*2,[<metallurgy:quicksilver_engraved_block>,<metallurgy:quicksilver_engraved_block>]);
	recipes.addShapeless("quicksilverB2B_3",<metallurgy:quicksilver_bricks>*2,[<metallurgy:quicksilver_large_bricks>,<metallurgy:quicksilver_large_bricks>]);
	recipes.addShapeless("quicksilverB2B_4",<metallurgy:quicksilver_crystals>*2,[<metallurgy:quicksilver_bricks>,<metallurgy:quicksilver_bricks>]);
	recipes.addShapeless("quicksilverB2B_5",<metallurgy:quicksilver_hazard_block>*2,[<metallurgy:quicksilver_crystals>,<metallurgy:quicksilver_crystals>]);
	recipes.addShapeless("quicksilverB2B_6",<metallurgy:quicksilver_reinforced_glass>*2,[<metallurgy:quicksilver_hazard_block>,<metallurgy:quicksilver_hazard_block>]);
	recipes.addShapeless("quicksilverB2B_7",<metallurgy:quicksilver_block>*2,[<metallurgy:quicksilver_reinforced_glass>,<metallurgy:quicksilver_reinforced_glass>]);
	recipes.addShapeless("quicksilverB2I",<metallurgy:quicksilver_ingot>*4,[<ore:decorQuicksilver>]);
	
	recipes.addShapedMirrored("quicksilverItB", <metallurgy:quicksilver_block>,
	[[<ore:ingotQuicksilver>, <ore:ingotQuicksilver>, null],
	 [<ore:ingotQuicksilver>, <ore:ingotQuicksilver>, null],
	 [null, null, null]]);
}
{//brass
	recipes.addShapeless("brassB2B_1",<metallurgy:brass_engraved_block>*2,[<metallurgy:brass_block>,<metallurgy:brass_block>]);
	recipes.addShapeless("brassB2B_2",<metallurgy:brass_large_bricks>*2,[<metallurgy:brass_engraved_block>,<metallurgy:brass_engraved_block>]);
	recipes.addShapeless("brassB2B_3",<metallurgy:brass_bricks>*2,[<metallurgy:brass_large_bricks>,<metallurgy:brass_large_bricks>]);
	recipes.addShapeless("brassB2B_4",<metallurgy:brass_crystals>*2,[<metallurgy:brass_bricks>,<metallurgy:brass_bricks>]);
	recipes.addShapeless("brassB2B_5",<metallurgy:brass_hazard_block>*2,[<metallurgy:brass_crystals>,<metallurgy:brass_crystals>]);
	recipes.addShapeless("brassB2B_6",<metallurgy:brass_reinforced_glass>*2,[<metallurgy:brass_hazard_block>,<metallurgy:brass_hazard_block>]);
	recipes.addShapeless("brassB2B_7",<metallurgy:brass_block>*2,[<metallurgy:brass_reinforced_glass>,<metallurgy:brass_reinforced_glass>]);
	recipes.addShapeless("brassB2I",<metallurgy:brass_ingot>*4,[<ore:decorBrass>]);
	
	recipes.addShapedMirrored("brassItB", <metallurgy:brass_block>,
	[[<ore:ingotBrass>, <ore:ingotBrass>, null],
	 [<ore:ingotBrass>, <ore:ingotBrass>, null],
	 [null, null, null]]);
}
{//astral_silver
	recipes.addShapeless("astral_silverB2B_1",<metallurgy:astral_silver_engraved_block>*2,[<metallurgy:astral_silver_block>,<metallurgy:astral_silver_block>]);
	recipes.addShapeless("astral_silverB2B_2",<metallurgy:astral_silver_large_bricks>*2,[<metallurgy:astral_silver_engraved_block>,<metallurgy:astral_silver_engraved_block>]);
	recipes.addShapeless("astral_silverB2B_3",<metallurgy:astral_silver_bricks>*2,[<metallurgy:astral_silver_large_bricks>,<metallurgy:astral_silver_large_bricks>]);
	recipes.addShapeless("astral_silverB2B_4",<metallurgy:astral_silver_crystals>*2,[<metallurgy:astral_silver_bricks>,<metallurgy:astral_silver_bricks>]);
	recipes.addShapeless("astral_silverB2B_5",<metallurgy:astral_silver_hazard_block>*2,[<metallurgy:astral_silver_crystals>,<metallurgy:astral_silver_crystals>]);
	recipes.addShapeless("astral_silverB2B_6",<metallurgy:astral_silver_reinforced_glass>*2,[<metallurgy:astral_silver_hazard_block>,<metallurgy:astral_silver_hazard_block>]);
	recipes.addShapeless("astral_silverB2B_7",<metallurgy:astral_silver_block>*2,[<metallurgy:astral_silver_reinforced_glass>,<metallurgy:astral_silver_reinforced_glass>]);
	recipes.addShapeless("astral_silverB2I",<metallurgy:astral_silver_ingot>*4,[<ore:decorAstralSilver>]);
	
	recipes.addShapedMirrored("astral_silverItB", <metallurgy:astral_silver_block>,
	[[<ore:ingotAstralSilver>, <ore:ingotAstralSilver>, null],
	 [<ore:ingotAstralSilver>, <ore:ingotAstralSilver>, null],
	 [null, null, null]]);
}
{//hepatizon
	recipes.addShapeless("hepatizonB2B_1",<metallurgy:hepatizon_engraved_block>*2,[<metallurgy:hepatizon_block>,<metallurgy:hepatizon_block>]);
	recipes.addShapeless("hepatizonB2B_2",<metallurgy:hepatizon_large_bricks>*2,[<metallurgy:hepatizon_engraved_block>,<metallurgy:hepatizon_engraved_block>]);
	recipes.addShapeless("hepatizonB2B_3",<metallurgy:hepatizon_bricks>*2,[<metallurgy:hepatizon_large_bricks>,<metallurgy:hepatizon_large_bricks>]);
	recipes.addShapeless("hepatizonB2B_4",<metallurgy:hepatizon_crystals>*2,[<metallurgy:hepatizon_bricks>,<metallurgy:hepatizon_bricks>]);
	recipes.addShapeless("hepatizonB2B_5",<metallurgy:hepatizon_hazard_block>*2,[<metallurgy:hepatizon_crystals>,<metallurgy:hepatizon_crystals>]);
	recipes.addShapeless("hepatizonB2B_6",<metallurgy:hepatizon_reinforced_glass>*2,[<metallurgy:hepatizon_hazard_block>,<metallurgy:hepatizon_hazard_block>]);
	recipes.addShapeless("hepatizonB2B_7",<metallurgy:hepatizon_block>*2,[<metallurgy:hepatizon_reinforced_glass>,<metallurgy:hepatizon_reinforced_glass>]);
	recipes.addShapeless("hepatizonB2I",<metallurgy:hepatizon_ingot>*4,[<ore:decorHepatizon>]);
	
	recipes.addShapedMirrored("hepatizonItB", <metallurgy:hepatizon_block>,
	[[<ore:ingotHepatizon>, <ore:ingotHepatizon>, null],
	 [<ore:ingotHepatizon>, <ore:ingotHepatizon>, null],
	 [null, null, null]]);
}
{//bronze
	recipes.addShapeless("bronzeB2B_1",<metallurgy:bronze_engraved_block>*2,[<metallurgy:bronze_block>,<metallurgy:bronze_block>]);
	recipes.addShapeless("bronzeB2B_2",<metallurgy:bronze_large_bricks>*2,[<metallurgy:bronze_engraved_block>,<metallurgy:bronze_engraved_block>]);
	recipes.addShapeless("bronzeB2B_3",<metallurgy:bronze_bricks>*2,[<metallurgy:bronze_large_bricks>,<metallurgy:bronze_large_bricks>]);
	recipes.addShapeless("bronzeB2B_4",<metallurgy:bronze_crystals>*2,[<metallurgy:bronze_bricks>,<metallurgy:bronze_bricks>]);
	recipes.addShapeless("bronzeB2B_5",<metallurgy:bronze_hazard_block>*2,[<metallurgy:bronze_crystals>,<metallurgy:bronze_crystals>]);
	recipes.addShapeless("bronzeB2B_6",<metallurgy:bronze_reinforced_glass>*2,[<metallurgy:bronze_hazard_block>,<metallurgy:bronze_hazard_block>]);
	recipes.addShapeless("bronzeB2B_7",<metallurgy:bronze_block>*2,[<metallurgy:bronze_reinforced_glass>,<metallurgy:bronze_reinforced_glass>]);
	recipes.addShapeless("bronzeB2I",<metallurgy:bronze_ingot>*4,[<ore:decorBronze>]);
	
	recipes.addShapedMirrored("bronzeItB", <metallurgy:bronze_block>,
	[[<ore:ingotBronze>, <ore:ingotBronze>, null],
	 [<ore:ingotBronze>, <ore:ingotBronze>, null],
	 [null, null, null]]);
}
{//lemurite
	recipes.addShapeless("lemuriteB2B_1",<metallurgy:lemurite_engraved_block>*2,[<metallurgy:lemurite_block>,<metallurgy:lemurite_block>]);
	recipes.addShapeless("lemuriteB2B_2",<metallurgy:lemurite_large_bricks>*2,[<metallurgy:lemurite_engraved_block>,<metallurgy:lemurite_engraved_block>]);
	recipes.addShapeless("lemuriteB2B_3",<metallurgy:lemurite_bricks>*2,[<metallurgy:lemurite_large_bricks>,<metallurgy:lemurite_large_bricks>]);
	recipes.addShapeless("lemuriteB2B_4",<metallurgy:lemurite_crystals>*2,[<metallurgy:lemurite_bricks>,<metallurgy:lemurite_bricks>]);
	recipes.addShapeless("lemuriteB2B_5",<metallurgy:lemurite_hazard_block>*2,[<metallurgy:lemurite_crystals>,<metallurgy:lemurite_crystals>]);
	recipes.addShapeless("lemuriteB2B_6",<metallurgy:lemurite_reinforced_glass>*2,[<metallurgy:lemurite_hazard_block>,<metallurgy:lemurite_hazard_block>]);
	recipes.addShapeless("lemuriteB2B_7",<metallurgy:lemurite_block>*2,[<metallurgy:lemurite_reinforced_glass>,<metallurgy:lemurite_reinforced_glass>]);
	recipes.addShapeless("lemuriteB2I",<metallurgy:lemurite_ingot>*4,[<ore:decorLemurite>]);
	
	recipes.addShapedMirrored("lemuriteItB", <metallurgy:lemurite_block>,
	[[<ore:ingotLemurite>, <ore:ingotLemurite>, null],
	 [<ore:ingotLemurite>, <ore:ingotLemurite>, null],
	 [null, null, null]]);
}
{//sanguinite
	recipes.addShapeless("sanguiniteB2B_1",<metallurgy:sanguinite_engraved_block>*2,[<metallurgy:sanguinite_block>,<metallurgy:sanguinite_block>]);
	recipes.addShapeless("sanguiniteB2B_2",<metallurgy:sanguinite_large_bricks>*2,[<metallurgy:sanguinite_engraved_block>,<metallurgy:sanguinite_engraved_block>]);
	recipes.addShapeless("sanguiniteB2B_3",<metallurgy:sanguinite_bricks>*2,[<metallurgy:sanguinite_large_bricks>,<metallurgy:sanguinite_large_bricks>]);
	recipes.addShapeless("sanguiniteB2B_4",<metallurgy:sanguinite_crystals>*2,[<metallurgy:sanguinite_bricks>,<metallurgy:sanguinite_bricks>]);
	recipes.addShapeless("sanguiniteB2B_5",<metallurgy:sanguinite_hazard_block>*2,[<metallurgy:sanguinite_crystals>,<metallurgy:sanguinite_crystals>]);
	recipes.addShapeless("sanguiniteB2B_6",<metallurgy:sanguinite_reinforced_glass>*2,[<metallurgy:sanguinite_hazard_block>,<metallurgy:sanguinite_hazard_block>]);
	recipes.addShapeless("sanguiniteB2B_7",<metallurgy:sanguinite_block>*2,[<metallurgy:sanguinite_reinforced_glass>,<metallurgy:sanguinite_reinforced_glass>]);
	recipes.addShapeless("sanguiniteB2I",<metallurgy:sanguinite_ingot>*4,[<ore:decorSanguinite>]);
	
	recipes.addShapedMirrored("sanguiniteItB", <metallurgy:sanguinite_block>,
	[[<ore:ingotSanguinite>, <ore:ingotSanguinite>, null],
	 [<ore:ingotSanguinite>, <ore:ingotSanguinite>, null],
	 [null, null, null]]);
}
{//eximite
	recipes.addShapeless("eximiteB2B_1",<metallurgy:eximite_engraved_block>*2,[<metallurgy:eximite_block>,<metallurgy:eximite_block>]);
	recipes.addShapeless("eximiteB2B_2",<metallurgy:eximite_large_bricks>*2,[<metallurgy:eximite_engraved_block>,<metallurgy:eximite_engraved_block>]);
	recipes.addShapeless("eximiteB2B_3",<metallurgy:eximite_bricks>*2,[<metallurgy:eximite_large_bricks>,<metallurgy:eximite_large_bricks>]);
	recipes.addShapeless("eximiteB2B_4",<metallurgy:eximite_crystals>*2,[<metallurgy:eximite_bricks>,<metallurgy:eximite_bricks>]);
	recipes.addShapeless("eximiteB2B_5",<metallurgy:eximite_hazard_block>*2,[<metallurgy:eximite_crystals>,<metallurgy:eximite_crystals>]);
	recipes.addShapeless("eximiteB2B_6",<metallurgy:eximite_reinforced_glass>*2,[<metallurgy:eximite_hazard_block>,<metallurgy:eximite_hazard_block>]);
	recipes.addShapeless("eximiteB2B_7",<metallurgy:eximite_block>*2,[<metallurgy:eximite_reinforced_glass>,<metallurgy:eximite_reinforced_glass>]);
	recipes.addShapeless("eximiteB2I",<metallurgy:eximite_ingot>*4,[<ore:decorEximite>]);
	
	recipes.addShapedMirrored("eximiteItB", <metallurgy:eximite_block>,
	[[<ore:ingotEximite>, <ore:ingotEximite>, null],
	 [<ore:ingotEximite>, <ore:ingotEximite>, null],
	 [null, null, null]]);
}
{//silver
	recipes.addShapeless("silverB2B_1",<metallurgy:silver_engraved_block>*2,[<metallurgy:silver_block>,<metallurgy:silver_block>]);
	recipes.addShapeless("silverB2B_2",<metallurgy:silver_large_bricks>*2,[<metallurgy:silver_engraved_block>,<metallurgy:silver_engraved_block>]);
	recipes.addShapeless("silverB2B_3",<metallurgy:silver_bricks>*2,[<metallurgy:silver_large_bricks>,<metallurgy:silver_large_bricks>]);
	recipes.addShapeless("silverB2B_4",<metallurgy:silver_crystals>*2,[<metallurgy:silver_bricks>,<metallurgy:silver_bricks>]);
	recipes.addShapeless("silverB2B_5",<metallurgy:silver_hazard_block>*2,[<metallurgy:silver_crystals>,<metallurgy:silver_crystals>]);
	recipes.addShapeless("silverB2B_6",<metallurgy:silver_reinforced_glass>*2,[<metallurgy:silver_hazard_block>,<metallurgy:silver_hazard_block>]);
	recipes.addShapeless("silverB2B_7",<metallurgy:silver_block>*2,[<metallurgy:silver_reinforced_glass>,<metallurgy:silver_reinforced_glass>]);
	recipes.addShapeless("silverB2I",<metallurgy:silver_ingot>*4,[<ore:decorSilver>]);
	
	recipes.addShapedMirrored("silverItB", <metallurgy:silver_block>,
	[[<ore:ingotSilver>, <ore:ingotSilver>, null],
	 [<ore:ingotSilver>, <ore:ingotSilver>, null],
	 [null, null, null]]);
}
{//desichalkos
	recipes.addShapeless("desichalkosB2B_1",<metallurgy:desichalkos_engraved_block>*2,[<metallurgy:desichalkos_block>,<metallurgy:desichalkos_block>]);
	recipes.addShapeless("desichalkosB2B_2",<metallurgy:desichalkos_large_bricks>*2,[<metallurgy:desichalkos_engraved_block>,<metallurgy:desichalkos_engraved_block>]);
	recipes.addShapeless("desichalkosB2B_3",<metallurgy:desichalkos_bricks>*2,[<metallurgy:desichalkos_large_bricks>,<metallurgy:desichalkos_large_bricks>]);
	recipes.addShapeless("desichalkosB2B_4",<metallurgy:desichalkos_crystals>*2,[<metallurgy:desichalkos_bricks>,<metallurgy:desichalkos_bricks>]);
	recipes.addShapeless("desichalkosB2B_5",<metallurgy:desichalkos_hazard_block>*2,[<metallurgy:desichalkos_crystals>,<metallurgy:desichalkos_crystals>]);
	recipes.addShapeless("desichalkosB2B_6",<metallurgy:desichalkos_reinforced_glass>*2,[<metallurgy:desichalkos_hazard_block>,<metallurgy:desichalkos_hazard_block>]);
	recipes.addShapeless("desichalkosB2B_7",<metallurgy:desichalkos_block>*2,[<metallurgy:desichalkos_reinforced_glass>,<metallurgy:desichalkos_reinforced_glass>]);
	recipes.addShapeless("desichalkosB2I",<metallurgy:desichalkos_ingot>*4,[<ore:decorDesichalkos>]);
	
	recipes.addShapedMirrored("desichalkosItB", <metallurgy:desichalkos_block>,
	[[<ore:ingotDesichalkos>, <ore:ingotDesichalkos>, null],
	 [<ore:ingotDesichalkos>, <ore:ingotDesichalkos>, null],
	 [null, null, null]]);
}
{//celenegil
	recipes.addShapeless("celenegilB2B_1",<metallurgy:celenegil_engraved_block>*2,[<metallurgy:celenegil_block>,<metallurgy:celenegil_block>]);
	recipes.addShapeless("celenegilB2B_2",<metallurgy:celenegil_large_bricks>*2,[<metallurgy:celenegil_engraved_block>,<metallurgy:celenegil_engraved_block>]);
	recipes.addShapeless("celenegilB2B_3",<metallurgy:celenegil_bricks>*2,[<metallurgy:celenegil_large_bricks>,<metallurgy:celenegil_large_bricks>]);
	recipes.addShapeless("celenegilB2B_4",<metallurgy:celenegil_crystals>*2,[<metallurgy:celenegil_bricks>,<metallurgy:celenegil_bricks>]);
	recipes.addShapeless("celenegilB2B_5",<metallurgy:celenegil_hazard_block>*2,[<metallurgy:celenegil_crystals>,<metallurgy:celenegil_crystals>]);
	recipes.addShapeless("celenegilB2B_6",<metallurgy:celenegil_reinforced_glass>*2,[<metallurgy:celenegil_hazard_block>,<metallurgy:celenegil_hazard_block>]);
	recipes.addShapeless("celenegilB2B_7",<metallurgy:celenegil_block>*2,[<metallurgy:celenegil_reinforced_glass>,<metallurgy:celenegil_reinforced_glass>]);
	recipes.addShapeless("celenegilB2I",<metallurgy:celenegil_ingot>*4,[<ore:decorCelenegil>]);
	
	recipes.addShapedMirrored("celenegilItB", <metallurgy:celenegil_block>,
	[[<ore:ingotCelenegil>, <ore:ingotCelenegil>, null],
	 [<ore:ingotCelenegil>, <ore:ingotCelenegil>, null],
	 [null, null, null]]);
}
{//steel
	recipes.addShapeless("steelB2B_1",<metallurgy:steel_engraved_block>*2,[<metallurgy:steel_block>,<metallurgy:steel_block>]);
	recipes.addShapeless("steelB2B_2",<metallurgy:steel_large_bricks>*2,[<metallurgy:steel_engraved_block>,<metallurgy:steel_engraved_block>]);
	recipes.addShapeless("steelB2B_3",<metallurgy:steel_bricks>*2,[<metallurgy:steel_large_bricks>,<metallurgy:steel_large_bricks>]);
	recipes.addShapeless("steelB2B_4",<metallurgy:steel_crystals>*2,[<metallurgy:steel_bricks>,<metallurgy:steel_bricks>]);
	recipes.addShapeless("steelB2B_5",<metallurgy:steel_hazard_block>*2,[<metallurgy:steel_crystals>,<metallurgy:steel_crystals>]);
	recipes.addShapeless("steelB2B_6",<metallurgy:steel_reinforced_glass>*2,[<metallurgy:steel_hazard_block>,<metallurgy:steel_hazard_block>]);
	recipes.addShapeless("steelB2B_7",<metallurgy:steel_block>*2,[<metallurgy:steel_reinforced_glass>,<metallurgy:steel_reinforced_glass>]);
	recipes.addShapeless("steelB2I",<metallurgy:steel_ingot>*4,[<ore:decorSteel>]);
	
	recipes.addShapedMirrored("steelItB", <metallurgy:steel_block>,
	[[<ore:ingotSteel>, <ore:ingotSteel>, null],
	 [<ore:ingotSteel>, <ore:ingotSteel>, null],
	 [null, null, null]]);
}
{//shadow_iron
	recipes.addShapeless("shadow_ironB2B_1",<metallurgy:shadow_iron_engraved_block>*2,[<metallurgy:shadow_iron_block>,<metallurgy:shadow_iron_block>]);
	recipes.addShapeless("shadow_ironB2B_2",<metallurgy:shadow_iron_large_bricks>*2,[<metallurgy:shadow_iron_engraved_block>,<metallurgy:shadow_iron_engraved_block>]);
	recipes.addShapeless("shadow_ironB2B_3",<metallurgy:shadow_iron_bricks>*2,[<metallurgy:shadow_iron_large_bricks>,<metallurgy:shadow_iron_large_bricks>]);
	recipes.addShapeless("shadow_ironB2B_4",<metallurgy:shadow_iron_crystals>*2,[<metallurgy:shadow_iron_bricks>,<metallurgy:shadow_iron_bricks>]);
	recipes.addShapeless("shadow_ironB2B_5",<metallurgy:shadow_iron_hazard_block>*2,[<metallurgy:shadow_iron_crystals>,<metallurgy:shadow_iron_crystals>]);
	recipes.addShapeless("shadow_ironB2B_6",<metallurgy:shadow_iron_reinforced_glass>*2,[<metallurgy:shadow_iron_hazard_block>,<metallurgy:shadow_iron_hazard_block>]);
	recipes.addShapeless("shadow_ironB2B_7",<metallurgy:shadow_iron_block>*2,[<metallurgy:shadow_iron_reinforced_glass>,<metallurgy:shadow_iron_reinforced_glass>]);
	recipes.addShapeless("shadow_ironB2I",<metallurgy:shadow_iron_ingot>*4,[<ore:decorShadowIron>]);
	
	recipes.addShapedMirrored("shadow_ironItB", <metallurgy:shadow_iron_block>,
	[[<ore:ingotShadowIron>, <ore:ingotShadowIron>, null],
	 [<ore:ingotShadowIron>, <ore:ingotShadowIron>, null],
	 [null, null, null]]);
}
{//carmot
	recipes.addShapeless("carmotB2B_1",<metallurgy:carmot_engraved_block>*2,[<metallurgy:carmot_block>,<metallurgy:carmot_block>]);
	recipes.addShapeless("carmotB2B_2",<metallurgy:carmot_large_bricks>*2,[<metallurgy:carmot_engraved_block>,<metallurgy:carmot_engraved_block>]);
	recipes.addShapeless("carmotB2B_3",<metallurgy:carmot_bricks>*2,[<metallurgy:carmot_large_bricks>,<metallurgy:carmot_large_bricks>]);
	recipes.addShapeless("carmotB2B_4",<metallurgy:carmot_crystals>*2,[<metallurgy:carmot_bricks>,<metallurgy:carmot_bricks>]);
	recipes.addShapeless("carmotB2B_5",<metallurgy:carmot_hazard_block>*2,[<metallurgy:carmot_crystals>,<metallurgy:carmot_crystals>]);
	recipes.addShapeless("carmotB2B_6",<metallurgy:carmot_reinforced_glass>*2,[<metallurgy:carmot_hazard_block>,<metallurgy:carmot_hazard_block>]);
	recipes.addShapeless("carmotB2B_7",<metallurgy:carmot_block>*2,[<metallurgy:carmot_reinforced_glass>,<metallurgy:carmot_reinforced_glass>]);
	recipes.addShapeless("carmotB2I",<metallurgy:carmot_ingot>*4,[<ore:decorCarmot>]);
	
	recipes.addShapedMirrored("carmotItB", <metallurgy:carmot_block>,
	[[<ore:ingotCarmot>, <ore:ingotCarmot>, null],
	 [<ore:ingotCarmot>, <ore:ingotCarmot>, null],
	 [null, null, null]]);
}
{//mithril
	recipes.addShapeless("mithrilB2B_1",<metallurgy:mithril_engraved_block>*2,[<metallurgy:mithril_block>,<metallurgy:mithril_block>]);
	recipes.addShapeless("mithrilB2B_2",<metallurgy:mithril_large_bricks>*2,[<metallurgy:mithril_engraved_block>,<metallurgy:mithril_engraved_block>]);
	recipes.addShapeless("mithrilB2B_3",<metallurgy:mithril_bricks>*2,[<metallurgy:mithril_large_bricks>,<metallurgy:mithril_large_bricks>]);
	recipes.addShapeless("mithrilB2B_4",<metallurgy:mithril_crystals>*2,[<metallurgy:mithril_bricks>,<metallurgy:mithril_bricks>]);
	recipes.addShapeless("mithrilB2B_5",<metallurgy:mithril_hazard_block>*2,[<metallurgy:mithril_crystals>,<metallurgy:mithril_crystals>]);
	recipes.addShapeless("mithrilB2B_6",<metallurgy:mithril_reinforced_glass>*2,[<metallurgy:mithril_hazard_block>,<metallurgy:mithril_hazard_block>]);
	recipes.addShapeless("mithrilB2B_7",<metallurgy:mithril_block>*2,[<metallurgy:mithril_reinforced_glass>,<metallurgy:mithril_reinforced_glass>]);
	recipes.addShapeless("mithrilB2I",<metallurgy:mithril_ingot>*4,[<ore:decorMithril>]);
	
	recipes.addShapedMirrored("mithrilItB", <metallurgy:mithril_block>,
	[[<ore:ingotMithril>, <ore:ingotMithril>, null],
	 [<ore:ingotMithril>, <ore:ingotMithril>, null],
	 [null, null, null]]);
}
{//ceruclase
	recipes.addShapeless("ceruclaseB2B_1",<metallurgy:ceruclase_engraved_block>*2,[<metallurgy:ceruclase_block>,<metallurgy:ceruclase_block>]);
	recipes.addShapeless("ceruclaseB2B_2",<metallurgy:ceruclase_large_bricks>*2,[<metallurgy:ceruclase_engraved_block>,<metallurgy:ceruclase_engraved_block>]);
	recipes.addShapeless("ceruclaseB2B_3",<metallurgy:ceruclase_bricks>*2,[<metallurgy:ceruclase_large_bricks>,<metallurgy:ceruclase_large_bricks>]);
	recipes.addShapeless("ceruclaseB2B_4",<metallurgy:ceruclase_crystals>*2,[<metallurgy:ceruclase_bricks>,<metallurgy:ceruclase_bricks>]);
	recipes.addShapeless("ceruclaseB2B_5",<metallurgy:ceruclase_hazard_block>*2,[<metallurgy:ceruclase_crystals>,<metallurgy:ceruclase_crystals>]);
	recipes.addShapeless("ceruclaseB2B_6",<metallurgy:ceruclase_reinforced_glass>*2,[<metallurgy:ceruclase_hazard_block>,<metallurgy:ceruclase_hazard_block>]);
	recipes.addShapeless("ceruclaseB2B_7",<metallurgy:ceruclase_block>*2,[<metallurgy:ceruclase_reinforced_glass>,<metallurgy:ceruclase_reinforced_glass>]);
	recipes.addShapeless("ceruclaseB2I",<metallurgy:ceruclase_ingot>*4,[<ore:decorCeruclase>]);
	
	recipes.addShapedMirrored("ceruclaseItB", <metallurgy:ceruclase_block>,
	[[<ore:ingotCeruclase>, <ore:ingotCeruclase>, null],
	 [<ore:ingotCeruclase>, <ore:ingotCeruclase>, null],
	 [null, null, null]]);
}
{//deep_iron
	recipes.addShapeless("deep_ironB2B_1",<metallurgy:deep_iron_engraved_block>*2,[<metallurgy:deep_iron_block>,<metallurgy:deep_iron_block>]);
	recipes.addShapeless("deep_ironB2B_2",<metallurgy:deep_iron_large_bricks>*2,[<metallurgy:deep_iron_engraved_block>,<metallurgy:deep_iron_engraved_block>]);
	recipes.addShapeless("deep_ironB2B_3",<metallurgy:deep_iron_bricks>*2,[<metallurgy:deep_iron_large_bricks>,<metallurgy:deep_iron_large_bricks>]);
	recipes.addShapeless("deep_ironB2B_4",<metallurgy:deep_iron_crystals>*2,[<metallurgy:deep_iron_bricks>,<metallurgy:deep_iron_bricks>]);
	recipes.addShapeless("deep_ironB2B_5",<metallurgy:deep_iron_hazard_block>*2,[<metallurgy:deep_iron_crystals>,<metallurgy:deep_iron_crystals>]);
	recipes.addShapeless("deep_ironB2B_6",<metallurgy:deep_iron_reinforced_glass>*2,[<metallurgy:deep_iron_hazard_block>,<metallurgy:deep_iron_hazard_block>]);
	recipes.addShapeless("deep_ironB2B_7",<metallurgy:deep_iron_block>*2,[<metallurgy:deep_iron_reinforced_glass>,<metallurgy:deep_iron_reinforced_glass>]);
	recipes.addShapeless("deep_ironB2I",<metallurgy:deep_iron_ingot>*4,[<ore:decorDeepIron>]);
	
	recipes.addShapedMirrored("deep_ironItB", <metallurgy:deep_iron_block>,
	[[<ore:ingotDeepIron>, <ore:ingotDeepIron>, null],
	 [<ore:ingotDeepIron>, <ore:ingotDeepIron>, null],
	 [null, null, null]]);
}
{//angmallen
	recipes.addShapeless("angmallenB2B_1",<metallurgy:angmallen_engraved_block>*2,[<metallurgy:angmallen_block>,<metallurgy:angmallen_block>]);
	recipes.addShapeless("angmallenB2B_2",<metallurgy:angmallen_large_bricks>*2,[<metallurgy:angmallen_engraved_block>,<metallurgy:angmallen_engraved_block>]);
	recipes.addShapeless("angmallenB2B_3",<metallurgy:angmallen_bricks>*2,[<metallurgy:angmallen_large_bricks>,<metallurgy:angmallen_large_bricks>]);
	recipes.addShapeless("angmallenB2B_4",<metallurgy:angmallen_crystals>*2,[<metallurgy:angmallen_bricks>,<metallurgy:angmallen_bricks>]);
	recipes.addShapeless("angmallenB2B_5",<metallurgy:angmallen_hazard_block>*2,[<metallurgy:angmallen_crystals>,<metallurgy:angmallen_crystals>]);
	recipes.addShapeless("angmallenB2B_6",<metallurgy:angmallen_reinforced_glass>*2,[<metallurgy:angmallen_hazard_block>,<metallurgy:angmallen_hazard_block>]);
	recipes.addShapeless("angmallenB2B_7",<metallurgy:angmallen_block>*2,[<metallurgy:angmallen_reinforced_glass>,<metallurgy:angmallen_reinforced_glass>]);
	recipes.addShapeless("angmallenB2I",<metallurgy:angmallen_ingot>*4,[<ore:decorAngmallen>]);
	
	recipes.addShapedMirrored("angmallenItB", <metallurgy:angmallen_block>,
	[[<ore:ingotAngmallen>, <ore:ingotAngmallen>, null],
	 [<ore:ingotAngmallen>, <ore:ingotAngmallen>, null],
	 [null, null, null]]);
}
{//manganese
	recipes.addShapeless("manganeseB2B_1",<metallurgy:manganese_engraved_block>*2,[<metallurgy:manganese_block>,<metallurgy:manganese_block>]);
	recipes.addShapeless("manganeseB2B_2",<metallurgy:manganese_large_bricks>*2,[<metallurgy:manganese_engraved_block>,<metallurgy:manganese_engraved_block>]);
	recipes.addShapeless("manganeseB2B_3",<metallurgy:manganese_bricks>*2,[<metallurgy:manganese_large_bricks>,<metallurgy:manganese_large_bricks>]);
	recipes.addShapeless("manganeseB2B_4",<metallurgy:manganese_crystals>*2,[<metallurgy:manganese_bricks>,<metallurgy:manganese_bricks>]);
	recipes.addShapeless("manganeseB2B_5",<metallurgy:manganese_hazard_block>*2,[<metallurgy:manganese_crystals>,<metallurgy:manganese_crystals>]);
	recipes.addShapeless("manganeseB2B_6",<metallurgy:manganese_reinforced_glass>*2,[<metallurgy:manganese_hazard_block>,<metallurgy:manganese_hazard_block>]);
	recipes.addShapeless("manganeseB2B_7",<metallurgy:manganese_block>*2,[<metallurgy:manganese_reinforced_glass>,<metallurgy:manganese_reinforced_glass>]);
	recipes.addShapeless("manganeseB2I",<metallurgy:manganese_ingot>*4,[<ore:decorManganese>]);
	
	recipes.addShapedMirrored("manganeseItB", <metallurgy:manganese_block>,
	[[<ore:ingotManganese>, <ore:ingotManganese>, null],
	 [<ore:ingotManganese>, <ore:ingotManganese>, null],
	 [null, null, null]]);
}
{//kalendrite
	recipes.addShapeless("kalendriteB2B_1",<metallurgy:kalendrite_engraved_block>*2,[<metallurgy:kalendrite_block>,<metallurgy:kalendrite_block>]);
	recipes.addShapeless("kalendriteB2B_2",<metallurgy:kalendrite_large_bricks>*2,[<metallurgy:kalendrite_engraved_block>,<metallurgy:kalendrite_engraved_block>]);
	recipes.addShapeless("kalendriteB2B_3",<metallurgy:kalendrite_bricks>*2,[<metallurgy:kalendrite_large_bricks>,<metallurgy:kalendrite_large_bricks>]);
	recipes.addShapeless("kalendriteB2B_4",<metallurgy:kalendrite_crystals>*2,[<metallurgy:kalendrite_bricks>,<metallurgy:kalendrite_bricks>]);
	recipes.addShapeless("kalendriteB2B_5",<metallurgy:kalendrite_hazard_block>*2,[<metallurgy:kalendrite_crystals>,<metallurgy:kalendrite_crystals>]);
	recipes.addShapeless("kalendriteB2B_6",<metallurgy:kalendrite_reinforced_glass>*2,[<metallurgy:kalendrite_hazard_block>,<metallurgy:kalendrite_hazard_block>]);
	recipes.addShapeless("kalendriteB2B_7",<metallurgy:kalendrite_block>*2,[<metallurgy:kalendrite_reinforced_glass>,<metallurgy:kalendrite_reinforced_glass>]);
	recipes.addShapeless("kalendriteB2I",<metallurgy:kalendrite_ingot>*4,[<ore:decorKalendrite>]);
	
	recipes.addShapedMirrored("kalendriteItB", <metallurgy:kalendrite_block>,
	[[<ore:ingotKalendrite>, <ore:ingotKalendrite>, null],
	 [<ore:ingotKalendrite>, <ore:ingotKalendrite>, null],
	 [null, null, null]]);
}
{//damascus_steel
	recipes.addShapeless("damascus_steelB2B_1",<metallurgy:damascus_steel_engraved_block>*2,[<metallurgy:damascus_steel_block>,<metallurgy:damascus_steel_block>]);
	recipes.addShapeless("damascus_steelB2B_2",<metallurgy:damascus_steel_large_bricks>*2,[<metallurgy:damascus_steel_engraved_block>,<metallurgy:damascus_steel_engraved_block>]);
	recipes.addShapeless("damascus_steelB2B_3",<metallurgy:damascus_steel_bricks>*2,[<metallurgy:damascus_steel_large_bricks>,<metallurgy:damascus_steel_large_bricks>]);
	recipes.addShapeless("damascus_steelB2B_4",<metallurgy:damascus_steel_crystals>*2,[<metallurgy:damascus_steel_bricks>,<metallurgy:damascus_steel_bricks>]);
	recipes.addShapeless("damascus_steelB2B_5",<metallurgy:damascus_steel_hazard_block>*2,[<metallurgy:damascus_steel_crystals>,<metallurgy:damascus_steel_crystals>]);
	recipes.addShapeless("damascus_steelB2B_6",<metallurgy:damascus_steel_reinforced_glass>*2,[<metallurgy:damascus_steel_hazard_block>,<metallurgy:damascus_steel_hazard_block>]);
	recipes.addShapeless("damascus_steelB2B_7",<metallurgy:damascus_steel_block>*2,[<metallurgy:damascus_steel_reinforced_glass>,<metallurgy:damascus_steel_reinforced_glass>]);
	recipes.addShapeless("damascus_steelB2I",<metallurgy:damascus_steel_ingot>*4,[<ore:decorDamascusSteel>]);
	
	recipes.addShapedMirrored("damascus_steelItB", <metallurgy:damascus_steel_block>,
	[[<ore:ingotDamascusSteel>, <ore:ingotDamascusSteel>, null],
	 [<ore:ingotDamascusSteel>, <ore:ingotDamascusSteel>, null],
	 [null, null, null]]);
}
{//prometheum
	recipes.addShapeless("prometheumB2B_1",<metallurgy:prometheum_engraved_block>*2,[<metallurgy:prometheum_block>,<metallurgy:prometheum_block>]);
	recipes.addShapeless("prometheumB2B_2",<metallurgy:prometheum_large_bricks>*2,[<metallurgy:prometheum_engraved_block>,<metallurgy:prometheum_engraved_block>]);
	recipes.addShapeless("prometheumB2B_3",<metallurgy:prometheum_bricks>*2,[<metallurgy:prometheum_large_bricks>,<metallurgy:prometheum_large_bricks>]);
	recipes.addShapeless("prometheumB2B_4",<metallurgy:prometheum_crystals>*2,[<metallurgy:prometheum_bricks>,<metallurgy:prometheum_bricks>]);
	recipes.addShapeless("prometheumB2B_5",<metallurgy:prometheum_hazard_block>*2,[<metallurgy:prometheum_crystals>,<metallurgy:prometheum_crystals>]);
	recipes.addShapeless("prometheumB2B_6",<metallurgy:prometheum_reinforced_glass>*2,[<metallurgy:prometheum_hazard_block>,<metallurgy:prometheum_hazard_block>]);
	recipes.addShapeless("prometheumB2B_7",<metallurgy:prometheum_block>*2,[<metallurgy:prometheum_reinforced_glass>,<metallurgy:prometheum_reinforced_glass>]);
	recipes.addShapeless("prometheumB2I",<metallurgy:prometheum_ingot>*4,[<ore:decorPrometheum>]);
	
	recipes.addShapedMirrored("prometheumItB", <metallurgy:prometheum_block>,
	[[<ore:ingotPrometheum>, <ore:ingotPrometheum>, null],
	 [<ore:ingotPrometheum>, <ore:ingotPrometheum>, null],
	 [null, null, null]]);
}
{//copper
	recipes.addShapeless("copperB2B_1",<metallurgy:copper_engraved_block>*2,[<metallurgy:copper_block>,<metallurgy:copper_block>]);
	recipes.addShapeless("copperB2B_2",<metallurgy:copper_large_bricks>*2,[<metallurgy:copper_engraved_block>,<metallurgy:copper_engraved_block>]);
	recipes.addShapeless("copperB2B_3",<metallurgy:copper_bricks>*2,[<metallurgy:copper_large_bricks>,<metallurgy:copper_large_bricks>]);
	recipes.addShapeless("copperB2B_4",<metallurgy:copper_crystals>*2,[<metallurgy:copper_bricks>,<metallurgy:copper_bricks>]);
	recipes.addShapeless("copperB2B_5",<metallurgy:copper_hazard_block>*2,[<metallurgy:copper_crystals>,<metallurgy:copper_crystals>]);
	recipes.addShapeless("copperB2B_6",<metallurgy:copper_reinforced_glass>*2,[<metallurgy:copper_hazard_block>,<metallurgy:copper_hazard_block>]);
	recipes.addShapeless("copperB2B_7",<metallurgy:copper_block>*2,[<metallurgy:copper_reinforced_glass>,<metallurgy:copper_reinforced_glass>]);
	recipes.addShapeless("copperB2I",<metallurgy:copper_ingot>*4,[<ore:decorCopper>]);
	
	recipes.addShapedMirrored("copperItB", <metallurgy:copper_block>,
	[[<ore:ingotCopper>, <ore:ingotCopper>, null],
	 [<ore:ingotCopper>, <ore:ingotCopper>, null],
	 [null, null, null]]);
}
{//adamantine
	recipes.addShapeless("adamantineB2B_1",<metallurgy:adamantine_engraved_block>*2,[<metallurgy:adamantine_block>,<metallurgy:adamantine_block>]);
	recipes.addShapeless("adamantineB2B_2",<metallurgy:adamantine_large_bricks>*2,[<metallurgy:adamantine_engraved_block>,<metallurgy:adamantine_engraved_block>]);
	recipes.addShapeless("adamantineB2B_3",<metallurgy:adamantine_bricks>*2,[<metallurgy:adamantine_large_bricks>,<metallurgy:adamantine_large_bricks>]);
	recipes.addShapeless("adamantineB2B_4",<metallurgy:adamantine_crystals>*2,[<metallurgy:adamantine_bricks>,<metallurgy:adamantine_bricks>]);
	recipes.addShapeless("adamantineB2B_5",<metallurgy:adamantine_hazard_block>*2,[<metallurgy:adamantine_crystals>,<metallurgy:adamantine_crystals>]);
	recipes.addShapeless("adamantineB2B_6",<metallurgy:adamantine_reinforced_glass>*2,[<metallurgy:adamantine_hazard_block>,<metallurgy:adamantine_hazard_block>]);
	recipes.addShapeless("adamantineB2B_7",<metallurgy:adamantine_block>*2,[<metallurgy:adamantine_reinforced_glass>,<metallurgy:adamantine_reinforced_glass>]);
	recipes.addShapeless("adamantineB2I",<metallurgy:adamantine_ingot>*4,[<ore:decorAdamantine>]);
	
	recipes.addShapedMirrored("adamantineItB", <metallurgy:adamantine_block>,
	[[<ore:ingotAdamantine>, <ore:ingotAdamantine>, null],
	 [<ore:ingotAdamantine>, <ore:ingotAdamantine>, null],
	 [null, null, null]]);
}
{//electrum
	recipes.addShapeless("electrumB2B_1",<metallurgy:electrum_engraved_block>*2,[<metallurgy:electrum_block>,<metallurgy:electrum_block>]);
	recipes.addShapeless("electrumB2B_2",<metallurgy:electrum_large_bricks>*2,[<metallurgy:electrum_engraved_block>,<metallurgy:electrum_engraved_block>]);
	recipes.addShapeless("electrumB2B_3",<metallurgy:electrum_bricks>*2,[<metallurgy:electrum_large_bricks>,<metallurgy:electrum_large_bricks>]);
	recipes.addShapeless("electrumB2B_4",<metallurgy:electrum_crystals>*2,[<metallurgy:electrum_bricks>,<metallurgy:electrum_bricks>]);
	recipes.addShapeless("electrumB2B_5",<metallurgy:electrum_hazard_block>*2,[<metallurgy:electrum_crystals>,<metallurgy:electrum_crystals>]);
	recipes.addShapeless("electrumB2B_6",<metallurgy:electrum_reinforced_glass>*2,[<metallurgy:electrum_hazard_block>,<metallurgy:electrum_hazard_block>]);
	recipes.addShapeless("electrumB2B_7",<metallurgy:electrum_block>*2,[<metallurgy:electrum_reinforced_glass>,<metallurgy:electrum_reinforced_glass>]);
	recipes.addShapeless("electrumB2I",<metallurgy:electrum_ingot>*4,[<ore:decorElectrum>]);
	
	recipes.addShapedMirrored("electrumItB", <metallurgy:electrum_block>,
	[[<ore:ingotElectrum>, <ore:ingotElectrum>, null],
	 [<ore:ingotElectrum>, <ore:ingotElectrum>, null],
	 [null, null, null]]);
}
{//tartarite
	recipes.addShapeless("tartariteB2B_1",<metallurgy:tartarite_engraved_block>*2,[<metallurgy:tartarite_block>,<metallurgy:tartarite_block>]);
	recipes.addShapeless("tartariteB2B_2",<metallurgy:tartarite_large_bricks>*2,[<metallurgy:tartarite_engraved_block>,<metallurgy:tartarite_engraved_block>]);
	recipes.addShapeless("tartariteB2B_3",<metallurgy:tartarite_bricks>*2,[<metallurgy:tartarite_large_bricks>,<metallurgy:tartarite_large_bricks>]);
	recipes.addShapeless("tartariteB2B_4",<metallurgy:tartarite_crystals>*2,[<metallurgy:tartarite_bricks>,<metallurgy:tartarite_bricks>]);
	recipes.addShapeless("tartariteB2B_5",<metallurgy:tartarite_hazard_block>*2,[<metallurgy:tartarite_crystals>,<metallurgy:tartarite_crystals>]);
	recipes.addShapeless("tartariteB2B_6",<metallurgy:tartarite_reinforced_glass>*2,[<metallurgy:tartarite_hazard_block>,<metallurgy:tartarite_hazard_block>]);
	recipes.addShapeless("tartariteB2B_7",<metallurgy:tartarite_block>*2,[<metallurgy:tartarite_reinforced_glass>,<metallurgy:tartarite_reinforced_glass>]);
	recipes.addShapeless("tartariteB2I",<metallurgy:tartarite_ingot>*4,[<ore:decorTartarite>]);
	
	recipes.addShapedMirrored("tartariteItB", <metallurgy:tartarite_block>,
	[[<ore:ingotTartarite>, <ore:ingotTartarite>, null],
	 [<ore:ingotTartarite>, <ore:ingotTartarite>, null],
	 [null, null, null]]);
}
{//atlarus
	recipes.addShapeless("atlarusB2B_1",<metallurgy:atlarus_engraved_block>*2,[<metallurgy:atlarus_block>,<metallurgy:atlarus_block>]);
	recipes.addShapeless("atlarusB2B_2",<metallurgy:atlarus_large_bricks>*2,[<metallurgy:atlarus_engraved_block>,<metallurgy:atlarus_engraved_block>]);
	recipes.addShapeless("atlarusB2B_3",<metallurgy:atlarus_bricks>*2,[<metallurgy:atlarus_large_bricks>,<metallurgy:atlarus_large_bricks>]);
	recipes.addShapeless("atlarusB2B_4",<metallurgy:atlarus_crystals>*2,[<metallurgy:atlarus_bricks>,<metallurgy:atlarus_bricks>]);
	recipes.addShapeless("atlarusB2B_5",<metallurgy:atlarus_hazard_block>*2,[<metallurgy:atlarus_crystals>,<metallurgy:atlarus_crystals>]);
	recipes.addShapeless("atlarusB2B_6",<metallurgy:atlarus_reinforced_glass>*2,[<metallurgy:atlarus_hazard_block>,<metallurgy:atlarus_hazard_block>]);
	recipes.addShapeless("atlarusB2B_7",<metallurgy:atlarus_block>*2,[<metallurgy:atlarus_reinforced_glass>,<metallurgy:atlarus_reinforced_glass>]);
	recipes.addShapeless("atlarusB2I",<metallurgy:atlarus_ingot>*4,[<ore:decorAtlarus>]);
	
	recipes.addShapedMirrored("atlarusItB", <metallurgy:atlarus_block>,
	[[<ore:ingotAtlarus>, <ore:ingotAtlarus>, null],
	 [<ore:ingotAtlarus>, <ore:ingotAtlarus>, null],
	 [null, null, null]]);
}
{//black_steel
	recipes.addShapeless("black_steelB2B_1",<metallurgy:black_steel_engraved_block>*2,[<metallurgy:black_steel_block>,<metallurgy:black_steel_block>]);
	recipes.addShapeless("black_steelB2B_2",<metallurgy:black_steel_large_bricks>*2,[<metallurgy:black_steel_engraved_block>,<metallurgy:black_steel_engraved_block>]);
	recipes.addShapeless("black_steelB2B_3",<metallurgy:black_steel_bricks>*2,[<metallurgy:black_steel_large_bricks>,<metallurgy:black_steel_large_bricks>]);
	recipes.addShapeless("black_steelB2B_4",<metallurgy:black_steel_crystals>*2,[<metallurgy:black_steel_bricks>,<metallurgy:black_steel_bricks>]);
	recipes.addShapeless("black_steelB2B_5",<metallurgy:black_steel_hazard_block>*2,[<metallurgy:black_steel_crystals>,<metallurgy:black_steel_crystals>]);
	recipes.addShapeless("black_steelB2B_6",<metallurgy:black_steel_reinforced_glass>*2,[<metallurgy:black_steel_hazard_block>,<metallurgy:black_steel_hazard_block>]);
	recipes.addShapeless("black_steelB2B_7",<metallurgy:black_steel_block>*2,[<metallurgy:black_steel_reinforced_glass>,<metallurgy:black_steel_reinforced_glass>]);
	recipes.addShapeless("black_steelB2I",<metallurgy:black_steel_ingot>*4,[<ore:decorBlackSteel>]);
	
	recipes.addShapedMirrored("black_steelItB", <metallurgy:black_steel_block>,
	[[<ore:ingotBlackSteel>, <ore:ingotBlackSteel>, null],
	 [<ore:ingotBlackSteel>, <ore:ingotBlackSteel>, null],
	 [null, null, null]]);
}
{//vyroxeres
	recipes.addShapeless("vyroxeresB2B_1",<metallurgy:vyroxeres_engraved_block>*2,[<metallurgy:vyroxeres_block>,<metallurgy:vyroxeres_block>]);
	recipes.addShapeless("vyroxeresB2B_2",<metallurgy:vyroxeres_large_bricks>*2,[<metallurgy:vyroxeres_engraved_block>,<metallurgy:vyroxeres_engraved_block>]);
	recipes.addShapeless("vyroxeresB2B_3",<metallurgy:vyroxeres_bricks>*2,[<metallurgy:vyroxeres_large_bricks>,<metallurgy:vyroxeres_large_bricks>]);
	recipes.addShapeless("vyroxeresB2B_4",<metallurgy:vyroxeres_crystals>*2,[<metallurgy:vyroxeres_bricks>,<metallurgy:vyroxeres_bricks>]);
	recipes.addShapeless("vyroxeresB2B_5",<metallurgy:vyroxeres_hazard_block>*2,[<metallurgy:vyroxeres_crystals>,<metallurgy:vyroxeres_crystals>]);
	recipes.addShapeless("vyroxeresB2B_6",<metallurgy:vyroxeres_reinforced_glass>*2,[<metallurgy:vyroxeres_hazard_block>,<metallurgy:vyroxeres_hazard_block>]);
	recipes.addShapeless("vyroxeresB2B_7",<metallurgy:vyroxeres_block>*2,[<metallurgy:vyroxeres_reinforced_glass>,<metallurgy:vyroxeres_reinforced_glass>]);
	recipes.addShapeless("vyroxeresB2I",<metallurgy:vyroxeres_ingot>*4,[<ore:decorVyroxeres>]);
	
	recipes.addShapedMirrored("vyroxeresItB", <metallurgy:vyroxeres_block>,
	[[<ore:ingotVyroxeres>, <ore:ingotVyroxeres>, null],
	 [<ore:ingotVyroxeres>, <ore:ingotVyroxeres>, null],
	 [null, null, null]]);
}
{//lutetium
	recipes.addShapeless("lutetiumB2B_1",<metallurgy:lutetium_engraved_block>*2,[<metallurgy:lutetium_block>,<metallurgy:lutetium_block>]);
	recipes.addShapeless("lutetiumB2B_2",<metallurgy:lutetium_large_bricks>*2,[<metallurgy:lutetium_engraved_block>,<metallurgy:lutetium_engraved_block>]);
	recipes.addShapeless("lutetiumB2B_3",<metallurgy:lutetium_bricks>*2,[<metallurgy:lutetium_large_bricks>,<metallurgy:lutetium_large_bricks>]);
	recipes.addShapeless("lutetiumB2B_4",<metallurgy:lutetium_crystals>*2,[<metallurgy:lutetium_bricks>,<metallurgy:lutetium_bricks>]);
	recipes.addShapeless("lutetiumB2B_5",<metallurgy:lutetium_hazard_block>*2,[<metallurgy:lutetium_crystals>,<metallurgy:lutetium_crystals>]);
	recipes.addShapeless("lutetiumB2B_6",<metallurgy:lutetium_reinforced_glass>*2,[<metallurgy:lutetium_hazard_block>,<metallurgy:lutetium_hazard_block>]);
	recipes.addShapeless("lutetiumB2B_7",<metallurgy:lutetium_block>*2,[<metallurgy:lutetium_reinforced_glass>,<metallurgy:lutetium_reinforced_glass>]);
	recipes.addShapeless("lutetiumB2I",<metallurgy:lutetium_ingot>*4,[<ore:decorLutetium>]);
	
	recipes.addShapedMirrored("lutetiumItB", <metallurgy:lutetium_block>,
	[[<ore:ingotLutetium>, <ore:ingotLutetium>, null],
	 [<ore:ingotLutetium>, <ore:ingotLutetium>, null],
	 [null, null, null]]);
}
{//osmium
	recipes.addShapeless("osmiumB2B_1",<metallurgy:osmium_engraved_block>*2,[<metallurgy:osmium_block>,<metallurgy:osmium_block>]);
	recipes.addShapeless("osmiumB2B_2",<metallurgy:osmium_large_bricks>*2,[<metallurgy:osmium_engraved_block>,<metallurgy:osmium_engraved_block>]);
	recipes.addShapeless("osmiumB2B_3",<metallurgy:osmium_bricks>*2,[<metallurgy:osmium_large_bricks>,<metallurgy:osmium_large_bricks>]);
	recipes.addShapeless("osmiumB2B_4",<metallurgy:osmium_crystals>*2,[<metallurgy:osmium_bricks>,<metallurgy:osmium_bricks>]);
	recipes.addShapeless("osmiumB2B_5",<metallurgy:osmium_hazard_block>*2,[<metallurgy:osmium_crystals>,<metallurgy:osmium_crystals>]);
	recipes.addShapeless("osmiumB2B_6",<metallurgy:osmium_reinforced_glass>*2,[<metallurgy:osmium_hazard_block>,<metallurgy:osmium_hazard_block>]);
	recipes.addShapeless("osmiumB2B_7",<metallurgy:osmium_block>*2,[<metallurgy:osmium_reinforced_glass>,<metallurgy:osmium_reinforced_glass>]);
	recipes.addShapeless("osmiumB2I",<metallurgy:osmium_ingot>*4,[<ore:decorOsmium>]);
	
	recipes.addShapedMirrored("osmiumItB", <metallurgy:osmium_block>,
	[[<ore:ingotOsmium>, <ore:ingotOsmium>, null],
	 [<ore:ingotOsmium>, <ore:ingotOsmium>, null],
	 [null, null, null]]);
}
{//oureclase
	recipes.addShapeless("oureclaseB2B_1",<metallurgy:oureclase_engraved_block>*2,[<metallurgy:oureclase_block>,<metallurgy:oureclase_block>]);
	recipes.addShapeless("oureclaseB2B_2",<metallurgy:oureclase_large_bricks>*2,[<metallurgy:oureclase_engraved_block>,<metallurgy:oureclase_engraved_block>]);
	recipes.addShapeless("oureclaseB2B_3",<metallurgy:oureclase_bricks>*2,[<metallurgy:oureclase_large_bricks>,<metallurgy:oureclase_large_bricks>]);
	recipes.addShapeless("oureclaseB2B_4",<metallurgy:oureclase_crystals>*2,[<metallurgy:oureclase_bricks>,<metallurgy:oureclase_bricks>]);
	recipes.addShapeless("oureclaseB2B_5",<metallurgy:oureclase_hazard_block>*2,[<metallurgy:oureclase_crystals>,<metallurgy:oureclase_crystals>]);
	recipes.addShapeless("oureclaseB2B_6",<metallurgy:oureclase_reinforced_glass>*2,[<metallurgy:oureclase_hazard_block>,<metallurgy:oureclase_hazard_block>]);
	recipes.addShapeless("oureclaseB2B_7",<metallurgy:oureclase_block>*2,[<metallurgy:oureclase_reinforced_glass>,<metallurgy:oureclase_reinforced_glass>]);
	recipes.addShapeless("oureclaseB2I",<metallurgy:oureclase_ingot>*4,[<ore:decorOureclase>]);
	
	recipes.addShapedMirrored("oureclaseItB", <metallurgy:oureclase_block>,
	[[<ore:ingotOureclase>, <ore:ingotOureclase>, null],
	 [<ore:ingotOureclase>, <ore:ingotOureclase>, null],
	 [null, null, null]]);
}
{//inolashite
	recipes.addShapeless("inolashiteB2B_1",<metallurgy:inolashite_engraved_block>*2,[<metallurgy:inolashite_block>,<metallurgy:inolashite_block>]);
	recipes.addShapeless("inolashiteB2B_2",<metallurgy:inolashite_large_bricks>*2,[<metallurgy:inolashite_engraved_block>,<metallurgy:inolashite_engraved_block>]);
	recipes.addShapeless("inolashiteB2B_3",<metallurgy:inolashite_bricks>*2,[<metallurgy:inolashite_large_bricks>,<metallurgy:inolashite_large_bricks>]);
	recipes.addShapeless("inolashiteB2B_4",<metallurgy:inolashite_crystals>*2,[<metallurgy:inolashite_bricks>,<metallurgy:inolashite_bricks>]);
	recipes.addShapeless("inolashiteB2B_5",<metallurgy:inolashite_hazard_block>*2,[<metallurgy:inolashite_crystals>,<metallurgy:inolashite_crystals>]);
	recipes.addShapeless("inolashiteB2B_6",<metallurgy:inolashite_reinforced_glass>*2,[<metallurgy:inolashite_hazard_block>,<metallurgy:inolashite_hazard_block>]);
	recipes.addShapeless("inolashiteB2B_7",<metallurgy:inolashite_block>*2,[<metallurgy:inolashite_reinforced_glass>,<metallurgy:inolashite_reinforced_glass>]);
	recipes.addShapeless("inolashiteB2I",<metallurgy:inolashite_ingot>*4,[<ore:decorInolashite>]);
	
	recipes.addShapedMirrored("inolashiteItB", <metallurgy:inolashite_block>,
	[[<ore:ingotInolashite>, <ore:ingotInolashite>, null],
	 [<ore:ingotInolashite>, <ore:ingotInolashite>, null],
	 [null, null, null]]);
}
{//meutoite
	recipes.addShapeless("meutoiteB2B_1",<metallurgy:meutoite_engraved_block>*2,[<metallurgy:meutoite_block>,<metallurgy:meutoite_block>]);
	recipes.addShapeless("meutoiteB2B_2",<metallurgy:meutoite_large_bricks>*2,[<metallurgy:meutoite_engraved_block>,<metallurgy:meutoite_engraved_block>]);
	recipes.addShapeless("meutoiteB2B_3",<metallurgy:meutoite_bricks>*2,[<metallurgy:meutoite_large_bricks>,<metallurgy:meutoite_large_bricks>]);
	recipes.addShapeless("meutoiteB2B_4",<metallurgy:meutoite_crystals>*2,[<metallurgy:meutoite_bricks>,<metallurgy:meutoite_bricks>]);
	recipes.addShapeless("meutoiteB2B_5",<metallurgy:meutoite_hazard_block>*2,[<metallurgy:meutoite_crystals>,<metallurgy:meutoite_crystals>]);
	recipes.addShapeless("meutoiteB2B_6",<metallurgy:meutoite_reinforced_glass>*2,[<metallurgy:meutoite_hazard_block>,<metallurgy:meutoite_hazard_block>]);
	recipes.addShapeless("meutoiteB2B_7",<metallurgy:meutoite_block>*2,[<metallurgy:meutoite_reinforced_glass>,<metallurgy:meutoite_reinforced_glass>]);
	recipes.addShapeless("meutoiteB2I",<metallurgy:meutoite_ingot>*4,[<ore:decorMeutoite>]);
	
	recipes.addShapedMirrored("meutoiteItB", <metallurgy:meutoite_block>,
	[[<ore:ingotMeutoite>, <ore:ingotMeutoite>, null],
	 [<ore:ingotMeutoite>, <ore:ingotMeutoite>, null],
	 [null, null, null]]);
}
{//orichalcum
	recipes.addShapeless("orichalcumB2B_1",<metallurgy:orichalcum_engraved_block>*2,[<metallurgy:orichalcum_block>,<metallurgy:orichalcum_block>]);
	recipes.addShapeless("orichalcumB2B_2",<metallurgy:orichalcum_large_bricks>*2,[<metallurgy:orichalcum_engraved_block>,<metallurgy:orichalcum_engraved_block>]);
	recipes.addShapeless("orichalcumB2B_3",<metallurgy:orichalcum_bricks>*2,[<metallurgy:orichalcum_large_bricks>,<metallurgy:orichalcum_large_bricks>]);
	recipes.addShapeless("orichalcumB2B_4",<metallurgy:orichalcum_crystals>*2,[<metallurgy:orichalcum_bricks>,<metallurgy:orichalcum_bricks>]);
	recipes.addShapeless("orichalcumB2B_5",<metallurgy:orichalcum_hazard_block>*2,[<metallurgy:orichalcum_crystals>,<metallurgy:orichalcum_crystals>]);
	recipes.addShapeless("orichalcumB2B_6",<metallurgy:orichalcum_reinforced_glass>*2,[<metallurgy:orichalcum_hazard_block>,<metallurgy:orichalcum_hazard_block>]);
	recipes.addShapeless("orichalcumB2B_7",<metallurgy:orichalcum_block>*2,[<metallurgy:orichalcum_reinforced_glass>,<metallurgy:orichalcum_reinforced_glass>]);
	recipes.addShapeless("orichalcumB2I",<metallurgy:orichalcum_ingot>*4,[<ore:decorOrichalcum>]);
	
	recipes.addShapedMirrored("orichalcumItB", <metallurgy:orichalcum_block>,
	[[<ore:ingotOrichalcum>, <ore:ingotOrichalcum>, null],
	 [<ore:ingotOrichalcum>, <ore:ingotOrichalcum>, null],
	 [null, null, null]]);
}
{//infuscolium
	recipes.addShapeless("infuscoliumB2B_1",<metallurgy:infuscolium_engraved_block>*2,[<metallurgy:infuscolium_block>,<metallurgy:infuscolium_block>]);
	recipes.addShapeless("infuscoliumB2B_2",<metallurgy:infuscolium_large_bricks>*2,[<metallurgy:infuscolium_engraved_block>,<metallurgy:infuscolium_engraved_block>]);
	recipes.addShapeless("infuscoliumB2B_3",<metallurgy:infuscolium_bricks>*2,[<metallurgy:infuscolium_large_bricks>,<metallurgy:infuscolium_large_bricks>]);
	recipes.addShapeless("infuscoliumB2B_4",<metallurgy:infuscolium_crystals>*2,[<metallurgy:infuscolium_bricks>,<metallurgy:infuscolium_bricks>]);
	recipes.addShapeless("infuscoliumB2B_5",<metallurgy:infuscolium_hazard_block>*2,[<metallurgy:infuscolium_crystals>,<metallurgy:infuscolium_crystals>]);
	recipes.addShapeless("infuscoliumB2B_6",<metallurgy:infuscolium_reinforced_glass>*2,[<metallurgy:infuscolium_hazard_block>,<metallurgy:infuscolium_hazard_block>]);
	recipes.addShapeless("infuscoliumB2B_7",<metallurgy:infuscolium_block>*2,[<metallurgy:infuscolium_reinforced_glass>,<metallurgy:infuscolium_reinforced_glass>]);
	recipes.addShapeless("infuscoliumB2I",<metallurgy:infuscolium_ingot>*4,[<ore:decorInfuscolium>]);
	
	recipes.addShapedMirrored("infuscoliumItB", <metallurgy:infuscolium_block>,
	[[<ore:ingotInfuscolium>, <ore:ingotInfuscolium>, null],
	 [<ore:ingotInfuscolium>, <ore:ingotInfuscolium>, null],
	 [null, null, null]]);
}
{//midasium
	recipes.addShapeless("midasiumB2B_1",<metallurgy:midasium_engraved_block>*2,[<metallurgy:midasium_block>,<metallurgy:midasium_block>]);
	recipes.addShapeless("midasiumB2B_2",<metallurgy:midasium_large_bricks>*2,[<metallurgy:midasium_engraved_block>,<metallurgy:midasium_engraved_block>]);
	recipes.addShapeless("midasiumB2B_3",<metallurgy:midasium_bricks>*2,[<metallurgy:midasium_large_bricks>,<metallurgy:midasium_large_bricks>]);
	recipes.addShapeless("midasiumB2B_4",<metallurgy:midasium_crystals>*2,[<metallurgy:midasium_bricks>,<metallurgy:midasium_bricks>]);
	recipes.addShapeless("midasiumB2B_5",<metallurgy:midasium_hazard_block>*2,[<metallurgy:midasium_crystals>,<metallurgy:midasium_crystals>]);
	recipes.addShapeless("midasiumB2B_6",<metallurgy:midasium_reinforced_glass>*2,[<metallurgy:midasium_hazard_block>,<metallurgy:midasium_hazard_block>]);
	recipes.addShapeless("midasiumB2B_7",<metallurgy:midasium_block>*2,[<metallurgy:midasium_reinforced_glass>,<metallurgy:midasium_reinforced_glass>]);
	recipes.addShapeless("midasiumB2I",<metallurgy:midasium_ingot>*4,[<ore:decorMidasium>]);
	
	recipes.addShapedMirrored("midasiumItB", <metallurgy:midasium_block>,
	[[<ore:ingotMidasium>, <ore:ingotMidasium>, null],
	 [<ore:ingotMidasium>, <ore:ingotMidasium>, null],
	 [null, null, null]]);
}
{//shadow_steel
	recipes.addShapeless("shadow_steelB2B_1",<metallurgy:shadow_steel_engraved_block>*2,[<metallurgy:shadow_steel_block>,<metallurgy:shadow_steel_block>]);
	recipes.addShapeless("shadow_steelB2B_2",<metallurgy:shadow_steel_large_bricks>*2,[<metallurgy:shadow_steel_engraved_block>,<metallurgy:shadow_steel_engraved_block>]);
	recipes.addShapeless("shadow_steelB2B_3",<metallurgy:shadow_steel_bricks>*2,[<metallurgy:shadow_steel_large_bricks>,<metallurgy:shadow_steel_large_bricks>]);
	recipes.addShapeless("shadow_steelB2B_4",<metallurgy:shadow_steel_crystals>*2,[<metallurgy:shadow_steel_bricks>,<metallurgy:shadow_steel_bricks>]);
	recipes.addShapeless("shadow_steelB2B_5",<metallurgy:shadow_steel_hazard_block>*2,[<metallurgy:shadow_steel_crystals>,<metallurgy:shadow_steel_crystals>]);
	recipes.addShapeless("shadow_steelB2B_6",<metallurgy:shadow_steel_reinforced_glass>*2,[<metallurgy:shadow_steel_hazard_block>,<metallurgy:shadow_steel_hazard_block>]);
	recipes.addShapeless("shadow_steelB2B_7",<metallurgy:shadow_steel_block>*2,[<metallurgy:shadow_steel_reinforced_glass>,<metallurgy:shadow_steel_reinforced_glass>]);
	recipes.addShapeless("shadow_steelB2I",<metallurgy:shadow_steel_ingot>*4,[<ore:decorShadowSteel>]);
	
	recipes.addShapedMirrored("shadow_steelItB", <metallurgy:shadow_steel_block>,
	[[<ore:ingotShadowSteel>, <ore:ingotShadowSteel>, null],
	 [<ore:ingotShadowSteel>, <ore:ingotShadowSteel>, null],
	 [null, null, null]]);
}
{//krik
	recipes.addShapeless("krikB2B_1",<metallurgy:krik_engraved_block>*2,[<metallurgy:krik_block>,<metallurgy:krik_block>]);
	recipes.addShapeless("krikB2B_2",<metallurgy:krik_large_bricks>*2,[<metallurgy:krik_engraved_block>,<metallurgy:krik_engraved_block>]);
	recipes.addShapeless("krikB2B_3",<metallurgy:krik_bricks>*2,[<metallurgy:krik_large_bricks>,<metallurgy:krik_large_bricks>]);
	recipes.addShapeless("krikB2B_4",<metallurgy:krik_crystals>*2,[<metallurgy:krik_bricks>,<metallurgy:krik_bricks>]);
	recipes.addShapeless("krikB2B_5",<metallurgy:krik_hazard_block>*2,[<metallurgy:krik_crystals>,<metallurgy:krik_crystals>]);
	recipes.addShapeless("krikB2B_6",<metallurgy:krik_reinforced_glass>*2,[<metallurgy:krik_hazard_block>,<metallurgy:krik_hazard_block>]);
	recipes.addShapeless("krikB2B_7",<metallurgy:krik_block>*2,[<metallurgy:krik_reinforced_glass>,<metallurgy:krik_reinforced_glass>]);
	recipes.addShapeless("krikB2I",<metallurgy:krik_ingot>*4,[<ore:decorKrik>]);
	
	recipes.addShapedMirrored("krikItB", <metallurgy:krik_block>,
	[[<ore:ingotKrik>, <ore:ingotKrik>, null],
	 [<ore:ingotKrik>, <ore:ingotKrik>, null],
	 [null, null, null]]);
}
{//rubracium
	recipes.addShapeless("rubraciumB2B_1",<metallurgy:rubracium_engraved_block>*2,[<metallurgy:rubracium_block>,<metallurgy:rubracium_block>]);
	recipes.addShapeless("rubraciumB2B_2",<metallurgy:rubracium_large_bricks>*2,[<metallurgy:rubracium_engraved_block>,<metallurgy:rubracium_engraved_block>]);
	recipes.addShapeless("rubraciumB2B_3",<metallurgy:rubracium_bricks>*2,[<metallurgy:rubracium_large_bricks>,<metallurgy:rubracium_large_bricks>]);
	recipes.addShapeless("rubraciumB2B_4",<metallurgy:rubracium_crystals>*2,[<metallurgy:rubracium_bricks>,<metallurgy:rubracium_bricks>]);
	recipes.addShapeless("rubraciumB2B_5",<metallurgy:rubracium_hazard_block>*2,[<metallurgy:rubracium_crystals>,<metallurgy:rubracium_crystals>]);
	recipes.addShapeless("rubraciumB2B_6",<metallurgy:rubracium_reinforced_glass>*2,[<metallurgy:rubracium_hazard_block>,<metallurgy:rubracium_hazard_block>]);
	recipes.addShapeless("rubraciumB2B_7",<metallurgy:rubracium_block>*2,[<metallurgy:rubracium_reinforced_glass>,<metallurgy:rubracium_reinforced_glass>]);
	recipes.addShapeless("rubraciumB2I",<metallurgy:rubracium_ingot>*4,[<ore:decorRubracium>]);
	
	recipes.addShapedMirrored("rubraciumItB", <metallurgy:rubracium_block>,
	[[<ore:ingotRubracium>, <ore:ingotRubracium>, null],
	 [<ore:ingotRubracium>, <ore:ingotRubracium>, null],
	 [null, null, null]]);
}
{//iron
	recipes.addShapeless("ironB2B_1",<metallurgy:iron_large_bricks>*2,[<metallurgy:iron_engraved_block>,<metallurgy:iron_engraved_block>]);
	recipes.addShapeless("ironB2B_2",<metallurgy:iron_bricks>*2,[<metallurgy:iron_large_bricks>,<metallurgy:iron_large_bricks>]);
	recipes.addShapeless("ironB2B_3",<metallurgy:iron_crystals>*2,[<metallurgy:iron_bricks>,<metallurgy:iron_bricks>]);
	recipes.addShapeless("ironB2B_4",<metallurgy:iron_hazard_block>*2,[<metallurgy:iron_crystals>,<metallurgy:iron_crystals>]);
	recipes.addShapeless("ironB2B_5",<metallurgy:iron_reinforced_glass>*2,[<metallurgy:iron_hazard_block>,<metallurgy:iron_hazard_block>]);
	recipes.addShapeless("ironB2B_6",<metallurgy:iron_engraved_block>*2,[<metallurgy:iron_reinforced_glass>,<metallurgy:iron_reinforced_glass>]);
	recipes.addShapeless("ironB2I",<minecraft:iron_ingot> * 4,[<ore:decorIron>]);

	recipes.addShapedMirrored("ironItB", <metallurgy:iron_engraved_block> * 2,
	[[<ore:ingotIron>, <ore:ingotIron>, <ore:ingotIron>],
	 [<ore:ingotIron>, null, <ore:ingotIron>],
	 [<ore:ingotIron>, <ore:ingotIron>, <ore:ingotIron>]]);
}
{//gold
	recipes.addShapeless("goldB2B_1",<metallurgy:gold_large_bricks>*2,[<metallurgy:gold_engraved_block>,<metallurgy:gold_engraved_block>]);
	recipes.addShapeless("goldB2B_2",<metallurgy:gold_bricks>*2,[<metallurgy:gold_large_bricks>,<metallurgy:gold_large_bricks>]);
	recipes.addShapeless("goldB2B_3",<metallurgy:gold_crystals>*2,[<metallurgy:gold_bricks>,<metallurgy:gold_bricks>]);
	recipes.addShapeless("goldB2B_4",<metallurgy:gold_hazard_block>*2,[<metallurgy:gold_crystals>,<metallurgy:gold_crystals>]);
	recipes.addShapeless("goldB2B_5",<metallurgy:gold_reinforced_glass>*2,[<metallurgy:gold_hazard_block>,<metallurgy:gold_hazard_block>]);
	recipes.addShapeless("goldB2B_6",<metallurgy:gold_engraved_block>*2,[<metallurgy:gold_reinforced_glass>,<metallurgy:gold_reinforced_glass>]);
	recipes.addShapeless("goldB2I",<minecraft:gold_ingot>*4,[<ore:decorGold>]);

	recipes.addShapedMirrored("goldItB", <metallurgy:gold_engraved_block> * 2,
	[[<ore:ingotGold>, <ore:ingotGold>, <ore:ingotGold>],
	 [<ore:ingotGold>, null, <ore:ingotGold>],
	 [<ore:ingotGold>, <ore:ingotGold>, <ore:ingotGold>]]);
}
print("Success!");
print("Since ModTweaker is not installed, no further scripts will be loaded. Have fun & enjoy the mod!");


