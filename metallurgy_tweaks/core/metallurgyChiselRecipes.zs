#priority 96
#modloaded metallurgy chisel crafttweaker
print("---------------------------------------------------------------------------------------------------------------------------------------------");
print("Thank you for installing Chisel! This script will create new recipes for the creation of ingots to metal blocks, and metal blocks to ingots..");
print("Make sure to check the recipe book or JEI to know how to craft the blocks!");
print("All rights reserved unless stated otherwise.");
print("---------------------------------------------------------------------------------------------------------------------------------------------");
print("Proceeding to create new recipes for metallurgy block to ingot & ingot to block conversions...");
{//Amordrine
	{//recipe removal
		//vanilla ingot to block is removed in "chiselMetallurgy.zs" using an array
		recipes.removeByRecipeName("metallurgy:ingot_shapeless_amordrine");
	}
	{//recipe creation
		recipes.addShapedMirrored("amordrineItB", <metallurgy:amordrine_block>,
		[[<ore:ingotAmordrine>, <ore:ingotAmordrine>, null],
		 [<ore:ingotAmordrine>, <ore:ingotAmordrine>, null],
		 [null, null, null]]);
		recipes.addShapeless("amordrineBtI", <metallurgy:amordrine_ingot>*4, [<ore:decorAmordrine>]);
	}
}
{//Haderoth
	{//recipe removal
		//vanilla ingot to block is removed in "chiselMetallurgy.zs" using an array
		recipes.removeByRecipeName("metallurgy:ingot_shapeless_haderoth");
	}
	{//recipe creation
		recipes.addShapedMirrored("haderothItB", <metallurgy:haderoth_block>,
		[[<ore:ingotHaderoth>, <ore:ingotHaderoth>, null],
		 [<ore:ingotHaderoth>, <ore:ingotHaderoth>, null],
		 [null, null, null]]);
		recipes.addShapeless("haderothBtI", <metallurgy:haderoth_ingot>*4, [<ore:decorHaderoth>]);
	}
}
{//Alduorite
	{//recipe removal
		//vanilla ingot to block is removed in "chiselMetallurgy.zs" using an array
		recipes.removeByRecipeName("metallurgy:ingot_shapeless_alduorite");
	}
	{//recipe creation
		recipes.addShapedMirrored("alduoriteItB", <metallurgy:alduorite_block>,
		[[<ore:ingotAlduorite>, <ore:ingotAlduorite>, null],
		 [<ore:ingotAlduorite>, <ore:ingotAlduorite>, null],
		 [null, null, null]]);
		recipes.addShapeless("alduoriteBtI", <metallurgy:alduorite_ingot>*4, [<ore:decorAlduorite>]);
	}
}
{//Platinum
	{//recipe removal
		//vanilla ingot to block is removed in "chiselMetallurgy.zs" using an array
		recipes.removeByRecipeName("metallurgy:ingot_shapeless_platinum");
	}
	{//recipe creation
		recipes.addShapedMirrored("platinumItB", <metallurgy:platinum_block>,
		[[<ore:ingotPlatinum>, <ore:ingotPlatinum>, null],
		 [<ore:ingotPlatinum>, <ore:ingotPlatinum>, null],
		 [null, null, null]]);
		recipes.addShapeless("platinumBtI", <metallurgy:platinum_ingot>*4, [<ore:decorPlatinum>]);
	}
}
{//Vulcanite
	{//recipe removal
		//vanilla ingot to block is removed in "chiselMetallurgy.zs" using an array
		recipes.removeByRecipeName("metallurgy:ingot_shapeless_vulcanite");
	}
	{//recipe creation
		recipes.addShapedMirrored("vulcaniteItB", <metallurgy:vulcanite_block>,
		[[<ore:ingotVulcanite>, <ore:ingotVulcanite>, null],
		 [<ore:ingotVulcanite>, <ore:ingotVulcanite>, null],
		 [null, null, null]]);
		recipes.addShapeless("vulcaniteBtI", <metallurgy:vulcanite_ingot>*4, [<ore:decorVulcanite>]);
	}
}
{//Tin
	{//recipe removal
		//vanilla ingot to block is removed in "chiselMetallurgy.zs" using an array
		recipes.removeByRecipeName("metallurgy:ingot_shapeless_tin");
	}
	{//recipe creation
		recipes.addShapedMirrored("tinItB", <metallurgy:tin_block>,
		[[<ore:ingotTin>, <ore:ingotTin>, null],
		 [<ore:ingotTin>, <ore:ingotTin>, null],
		 [null, null, null]]);
		recipes.addShapeless("tinBtI", <metallurgy:tin_ingot>*4, [<ore:decorTin>]);
	}
}
{//Ignatius
	{//recipe removal
		//vanilla ingot to block is removed in "chiselMetallurgy.zs" using an array
		recipes.removeByRecipeName("metallurgy:ingot_shapeless_ignatius");
	}
	{//recipe creation
		recipes.addShapedMirrored("ignatiusItB", <metallurgy:ignatius_block>,
		[[<ore:ingotIgnatius>, <ore:ingotIgnatius>, null],
		 [<ore:ingotIgnatius>, <ore:ingotIgnatius>, null],
		 [null, null, null]]);
		recipes.addShapeless("ignatiusBtI", <metallurgy:ignatius_ingot>*4, [<ore:decorIgnatius>]);
	}
}
{//Zinc
	{//recipe removal
		//vanilla ingot to block is removed in "chiselMetallurgy.zs" using an array
		recipes.removeByRecipeName("metallurgy:ingot_shapeless_zinc");
	}
	{//recipe creation
		recipes.addShapedMirrored("zincItB", <metallurgy:zinc_block>,
		[[<ore:ingotZinc>, <ore:ingotZinc>, null],
		 [<ore:ingotZinc>, <ore:ingotZinc>, null],
		 [null, null, null]]);
		recipes.addShapeless("zincBtI", <metallurgy:zinc_ingot>*4, [<ore:decorZinc>]);
	}
}
{//Etherium
	{//recipe removal
		//vanilla ingot to block is removed in "chiselMetallurgy.zs" using an array
		recipes.removeByRecipeName("metallurgy:ingot_shapeless_etherium");
	}
	{//recipe creation
		recipes.addShapedMirrored("etheriumItB", <metallurgy:etherium_block>,
		[[<ore:ingotEtherium>, <ore:ingotEtherium>, null],
		 [<ore:ingotEtherium>, <ore:ingotEtherium>, null],
		 [null, null, null]]);
		recipes.addShapeless("etheriumBtI", <metallurgy:etherium_ingot>*4, [<ore:decorEtherium>]);
	}
}
{//Quicksilver
	{//recipe removal
		//vanilla ingot to block is removed in "chiselMetallurgy.zs" using an array
		recipes.removeByRecipeName("metallurgy:ingot_shapeless_quicksilver");
	}
	{//recipe creation
		recipes.addShapedMirrored("quicksilverItB", <metallurgy:quicksilver_block>,
		[[<ore:ingotQuicksilver>, <ore:ingotQuicksilver>, null],
		 [<ore:ingotQuicksilver>, <ore:ingotQuicksilver>, null],
		 [null, null, null]]);
		recipes.addShapeless("quicksilverBtI", <metallurgy:quicksilver_ingot>*4, [<ore:decorQuicksilver>]);
	}
}
{//Brass
	{//recipe removal
		//vanilla ingot to block is removed in "chiselMetallurgy.zs" using an array
		recipes.removeByRecipeName("metallurgy:ingot_shapeless_brass");
	}
	{//recipe creation
		recipes.addShapedMirrored("brassItB", <metallurgy:brass_block>,
		[[<ore:ingotBrass>, <ore:ingotBrass>, null],
		 [<ore:ingotBrass>, <ore:ingotBrass>, null],
		 [null, null, null]]);
		recipes.addShapeless("brassBtI", <metallurgy:brass_ingot>*4, [<ore:decorBrass>]);
	}
}
{//AstralSilver
	{//recipe removal
		//vanilla ingot to block is removed in "chiselMetallurgy.zs" using an array
		recipes.removeByRecipeName("metallurgy:ingot_shapeless_astralSilver");
	}
	{//recipe creation
		recipes.addShapedMirrored("astralSilverItB", <metallurgy:astral_silver_block>,
		[[<ore:ingotAstralSilver>, <ore:ingotAstralSilver>, null],
		 [<ore:ingotAstralSilver>, <ore:ingotAstralSilver>, null],
		 [null, null, null]]);
		recipes.addShapeless("astralSilverBtI", <metallurgy:astral_silver_ingot>*4, [<ore:decorAstralSilver>]);
	}
}
{//Hepatizon
	{//recipe removal
		//vanilla ingot to block is removed in "chiselMetallurgy.zs" using an array
		recipes.removeByRecipeName("metallurgy:ingot_shapeless_hepatizon");
	}
	{//recipe creation
		recipes.addShapedMirrored("hepatizonItB", <metallurgy:hepatizon_block>,
		[[<ore:ingotHepatizon>, <ore:ingotHepatizon>, null],
		 [<ore:ingotHepatizon>, <ore:ingotHepatizon>, null],
		 [null, null, null]]);
		recipes.addShapeless("hepatizonBtI", <metallurgy:hepatizon_ingot>*4, [<ore:decorHepatizon>]);
	}
}
{//Bronze
	{//recipe removal
		//vanilla ingot to block is removed in "chiselMetallurgy.zs" using an array
		recipes.removeByRecipeName("metallurgy:ingot_shapeless_bronze");
	}
	{//recipe creation
		recipes.addShapedMirrored("bronzeItB", <metallurgy:bronze_block>,
		[[<ore:ingotBronze>, <ore:ingotBronze>, null],
		 [<ore:ingotBronze>, <ore:ingotBronze>, null],
		 [null, null, null]]);
		recipes.addShapeless("bronzeBtI", <metallurgy:bronze_ingot>*4, [<ore:decorBronze>]);
	}
}
{//Lemurite
	{//recipe removal
		//vanilla ingot to block is removed in "chiselMetallurgy.zs" using an array
		recipes.removeByRecipeName("metallurgy:ingot_shapeless_lemurite");
	}
	{//recipe creation
		recipes.addShapedMirrored("lemuriteItB", <metallurgy:lemurite_block>,
		[[<ore:ingotLemurite>, <ore:ingotLemurite>, null],
		 [<ore:ingotLemurite>, <ore:ingotLemurite>, null],
		 [null, null, null]]);
		recipes.addShapeless("lemuriteBtI", <metallurgy:lemurite_ingot>*4, [<ore:decorLemurite>]);
	}
}
{//Sanguinite
	{//recipe removal
		//vanilla ingot to block is removed in "chiselMetallurgy.zs" using an array
		recipes.removeByRecipeName("metallurgy:ingot_shapeless_sanguinite");
	}
	{//recipe creation
		recipes.addShapedMirrored("sanguiniteItB", <metallurgy:sanguinite_block>,
		[[<ore:ingotSanguinite>, <ore:ingotSanguinite>, null],
		 [<ore:ingotSanguinite>, <ore:ingotSanguinite>, null],
		 [null, null, null]]);
		recipes.addShapeless("sanguiniteBtI", <metallurgy:sanguinite_ingot>*4, [<ore:decorSanguinite>]);
	}
}
{//Eximite
	{//recipe removal
		//vanilla ingot to block is removed in "chiselMetallurgy.zs" using an array
		recipes.removeByRecipeName("metallurgy:ingot_shapeless_eximite");
	}
	{//recipe creation
		recipes.addShapedMirrored("eximiteItB", <metallurgy:eximite_block>,
		[[<ore:ingotEximite>, <ore:ingotEximite>, null],
		 [<ore:ingotEximite>, <ore:ingotEximite>, null],
		 [null, null, null]]);
		recipes.addShapeless("eximiteBtI", <metallurgy:eximite_ingot>*4, [<ore:decorEximite>]);
	}
}
{//Silver
	{//recipe removal
		//vanilla ingot to block is removed in "chiselMetallurgy.zs" using an array
		recipes.removeByRecipeName("metallurgy:ingot_shapeless_silver");
	}
	{//recipe creation
		recipes.addShapedMirrored("silverItB", <metallurgy:silver_block>,
		[[<ore:ingotSilver>, <ore:ingotSilver>, null],
		 [<ore:ingotSilver>, <ore:ingotSilver>, null],
		 [null, null, null]]);
		recipes.addShapeless("silverBtI", <metallurgy:silver_ingot>*4, [<ore:decorSilver>]);
	}
}
{//Desichalkos
	{//recipe removal
		//vanilla ingot to block is removed in "chiselMetallurgy.zs" using an array
		recipes.removeByRecipeName("metallurgy:ingot_shapeless_desichalkos");
	}
	{//recipe creation
		recipes.addShapedMirrored("desichalkosItB", <metallurgy:desichalkos_block>,
		[[<ore:ingotDesichalkos>, <ore:ingotDesichalkos>, null],
		 [<ore:ingotDesichalkos>, <ore:ingotDesichalkos>, null],
		 [null, null, null]]);
		recipes.addShapeless("desichalkosBtI", <metallurgy:desichalkos_ingot>*4, [<ore:decorDesichalkos>]);
	}
}
{//Celenegil
	{//recipe removal
		//vanilla ingot to block is removed in "chiselMetallurgy.zs" using an array
		recipes.removeByRecipeName("metallurgy:ingot_shapeless_celenegil");
	}
	{//recipe creation
		recipes.addShapedMirrored("celenegilItB", <metallurgy:celenegil_block>,
		[[<ore:ingotCelenegil>, <ore:ingotCelenegil>, null],
		 [<ore:ingotCelenegil>, <ore:ingotCelenegil>, null],
		 [null, null, null]]);
		recipes.addShapeless("celenegilBtI", <metallurgy:celenegil_ingot>*4, [<ore:decorCelenegil>]);
	}
}
{//Steel
	{//recipe removal
		//vanilla ingot to block is removed in "chiselMetallurgy.zs" using an array
		recipes.removeByRecipeName("metallurgy:ingot_shapeless_steel");
	}
	{//recipe creation
		recipes.addShapedMirrored("steelItB", <metallurgy:steel_block>,
		[[<ore:ingotSteel>, <ore:ingotSteel>, null],
		 [<ore:ingotSteel>, <ore:ingotSteel>, null],
		 [null, null, null]]);
		recipes.addShapeless("steelBtI", <metallurgy:steel_ingot>*4, [<ore:decorSteel>]);
	}
}
{//ShadowIron
	{//recipe removal
		//vanilla ingot to block is removed in "chiselMetallurgy.zs" using an array
		recipes.removeByRecipeName("metallurgy:ingot_shapeless_shadowIron");
	}
	{//recipe creation
		recipes.addShapedMirrored("shadowIronItB", <metallurgy:shadow_iron_block>,
		[[<ore:ingotShadowIron>, <ore:ingotShadowIron>, null],
		 [<ore:ingotShadowIron>, <ore:ingotShadowIron>, null],
		 [null, null, null]]);
		recipes.addShapeless("shadowIronBtI", <metallurgy:shadow_iron_ingot>*4, [<ore:decorShadowIron>]);
	}
}
{//Carmot
	{//recipe removal
		//vanilla ingot to block is removed in "chiselMetallurgy.zs" using an array
		recipes.removeByRecipeName("metallurgy:ingot_shapeless_carmot");
	}
	{//recipe creation
		recipes.addShapedMirrored("carmotItB", <metallurgy:carmot_block>,
		[[<ore:ingotCarmot>, <ore:ingotCarmot>, null],
		 [<ore:ingotCarmot>, <ore:ingotCarmot>, null],
		 [null, null, null]]);
		recipes.addShapeless("carmotBtI", <metallurgy:carmot_ingot>*4, [<ore:decorCarmot>]);
	}
}
{//Mithril
	{//recipe removal
		//vanilla ingot to block is removed in "chiselMetallurgy.zs" using an array
		recipes.removeByRecipeName("metallurgy:ingot_shapeless_mithril");
	}
	{//recipe creation
		recipes.addShapedMirrored("mithrilItB", <metallurgy:mithril_block>,
		[[<ore:ingotMithril>, <ore:ingotMithril>, null],
		 [<ore:ingotMithril>, <ore:ingotMithril>, null],
		 [null, null, null]]);
		recipes.addShapeless("mithrilBtI", <metallurgy:mithril_ingot>*4, [<ore:decorMithril>]);
	}
}
{//Ceruclase
	{//recipe removal
		//vanilla ingot to block is removed in "chiselMetallurgy.zs" using an array
		recipes.removeByRecipeName("metallurgy:ingot_shapeless_ceruclase");
	}
	{//recipe creation
		recipes.addShapedMirrored("ceruclaseItB", <metallurgy:ceruclase_block>,
		[[<ore:ingotCeruclase>, <ore:ingotCeruclase>, null],
		 [<ore:ingotCeruclase>, <ore:ingotCeruclase>, null],
		 [null, null, null]]);
		recipes.addShapeless("ceruclaseBtI", <metallurgy:ceruclase_ingot>*4, [<ore:decorCeruclase>]);
	}
}
{//DeepIron
	{//recipe removal
		//vanilla ingot to block is removed in "chiselMetallurgy.zs" using an array
		recipes.removeByRecipeName("metallurgy:ingot_shapeless_deepIron");
	}
	{//recipe creation
		recipes.addShapedMirrored("deepIronItB", <metallurgy:deep_iron_block>,
		[[<ore:ingotDeepIron>, <ore:ingotDeepIron>, null],
		 [<ore:ingotDeepIron>, <ore:ingotDeepIron>, null],
		 [null, null, null]]);
		recipes.addShapeless("deepIronBtI", <metallurgy:deep_iron_ingot>*4, [<ore:decorDeepIron>]);
	}
}
{//Angmallen
	{//recipe removal
		//vanilla ingot to block is removed in "chiselMetallurgy.zs" using an array
		recipes.removeByRecipeName("metallurgy:ingot_shapeless_angmallen");
	}
	{//recipe creation
		recipes.addShapedMirrored("angmallenItB", <metallurgy:angmallen_block>,
		[[<ore:ingotAngmallen>, <ore:ingotAngmallen>, null],
		 [<ore:ingotAngmallen>, <ore:ingotAngmallen>, null],
		 [null, null, null]]);
		recipes.addShapeless("angmallenBtI", <metallurgy:angmallen_ingot>*4, [<ore:decorAngmallen>]);
	}
}
{//Manganese
	{//recipe removal
		//vanilla ingot to block is removed in "chiselMetallurgy.zs" using an array
		recipes.removeByRecipeName("metallurgy:ingot_shapeless_manganese");
	}
	{//recipe creation
		recipes.addShapedMirrored("manganeseItB", <metallurgy:manganese_block>,
		[[<ore:ingotManganese>, <ore:ingotManganese>, null],
		 [<ore:ingotManganese>, <ore:ingotManganese>, null],
		 [null, null, null]]);
		recipes.addShapeless("manganeseBtI", <metallurgy:manganese_ingot>*4, [<ore:decorManganese>]);
	}
}
{//Kalendrite
	{//recipe removal
		//vanilla ingot to block is removed in "chiselMetallurgy.zs" using an array
		recipes.removeByRecipeName("metallurgy:ingot_shapeless_kalendrite");
	}
	{//recipe creation
		recipes.addShapedMirrored("kalendriteItB", <metallurgy:kalendrite_block>,
		[[<ore:ingotKalendrite>, <ore:ingotKalendrite>, null],
		 [<ore:ingotKalendrite>, <ore:ingotKalendrite>, null],
		 [null, null, null]]);
		recipes.addShapeless("kalendriteBtI", <metallurgy:kalendrite_ingot>*4, [<ore:decorKalendrite>]);
	}
}
{//DamascusSteel
	{//recipe removal
		//vanilla ingot to block is removed in "chiselMetallurgy.zs" using an array
		recipes.removeByRecipeName("metallurgy:ingot_shapeless_damascusSteel");
	}
	{//recipe creation
		recipes.addShapedMirrored("damascusSteelItB", <metallurgy:damascus_steel_block>,
		[[<ore:ingotDamascusSteel>, <ore:ingotDamascusSteel>, null],
		 [<ore:ingotDamascusSteel>, <ore:ingotDamascusSteel>, null],
		 [null, null, null]]);
		recipes.addShapeless("damascusSteelBtI", <metallurgy:damascus_steel_ingot>*4, [<ore:decorDamascusSteel>]);
	}
}
{//Prometheum
	{//recipe removal
		//vanilla ingot to block is removed in "chiselMetallurgy.zs" using an array
		recipes.removeByRecipeName("metallurgy:ingot_shapeless_prometheum");
	}
	{//recipe creation
		recipes.addShapedMirrored("prometheumItB", <metallurgy:prometheum_block>,
		[[<ore:ingotPrometheum>, <ore:ingotPrometheum>, null],
		 [<ore:ingotPrometheum>, <ore:ingotPrometheum>, null],
		 [null, null, null]]);
		recipes.addShapeless("prometheumBtI", <metallurgy:prometheum_ingot>*4, [<ore:decorPrometheum>]);
	}
}
{//Copper
	{//recipe removal
		//vanilla ingot to block is removed in "chiselMetallurgy.zs" using an array
		recipes.removeByRecipeName("metallurgy:ingot_shapeless_copper");
	}
	{//recipe creation
		recipes.addShapedMirrored("copperItB", <metallurgy:copper_block>,
		[[<ore:ingotCopper>, <ore:ingotCopper>, null],
		 [<ore:ingotCopper>, <ore:ingotCopper>, null],
		 [null, null, null]]);
		recipes.addShapeless("copperBtI", <metallurgy:copper_ingot>*4, [<ore:decorCopper>]);
	}
}
{//Adamantine
	{//recipe removal
		//vanilla ingot to block is removed in "chiselMetallurgy.zs" using an array
		recipes.removeByRecipeName("metallurgy:ingot_shapeless_adamantine");
	}
	{//recipe creation
		recipes.addShapedMirrored("adamantineItB", <metallurgy:adamantine_block>,
		[[<ore:ingotAdamantine>, <ore:ingotAdamantine>, null],
		 [<ore:ingotAdamantine>, <ore:ingotAdamantine>, null],
		 [null, null, null]]);
		recipes.addShapeless("adamantineBtI", <metallurgy:adamantine_ingot>*4, [<ore:decorAdamantine>]);
	}
}
{//Electrum
	{//recipe removal
		//vanilla ingot to block is removed in "chiselMetallurgy.zs" using an array
		recipes.removeByRecipeName("metallurgy:ingot_shapeless_electrum");
	}
	{//recipe creation
		recipes.addShapedMirrored("electrumItB", <metallurgy:electrum_block>,
		[[<ore:ingotElectrum>, <ore:ingotElectrum>, null],
		 [<ore:ingotElectrum>, <ore:ingotElectrum>, null],
		 [null, null, null]]);
		recipes.addShapeless("electrumBtI", <metallurgy:electrum_ingot>*4, [<ore:decorElectrum>]);
	}
}
{//Tartarite
	{//recipe removal
		//vanilla ingot to block is removed in "chiselMetallurgy.zs" using an array
		recipes.removeByRecipeName("metallurgy:ingot_shapeless_tartarite");
	}
	{//recipe creation
		recipes.addShapedMirrored("tartariteItB", <metallurgy:tartarite_block>,
		[[<ore:ingotTartarite>, <ore:ingotTartarite>, null],
		 [<ore:ingotTartarite>, <ore:ingotTartarite>, null],
		 [null, null, null]]);
		recipes.addShapeless("tartariteBtI", <metallurgy:tartarite_ingot>*4, [<ore:decorTartarite>]);
	}
}
{//Atlarus
	{//recipe removal
		//vanilla ingot to block is removed in "chiselMetallurgy.zs" using an array
		recipes.removeByRecipeName("metallurgy:ingot_shapeless_atlarus");
	}
	{//recipe creation
		recipes.addShapedMirrored("atlarusItB", <metallurgy:atlarus_block>,
		[[<ore:ingotAtlarus>, <ore:ingotAtlarus>, null],
		 [<ore:ingotAtlarus>, <ore:ingotAtlarus>, null],
		 [null, null, null]]);
		recipes.addShapeless("atlarusBtI", <metallurgy:atlarus_ingot>*4, [<ore:decorAtlarus>]);
	}
}
{//BlackSteel
	{//recipe removal
		//vanilla ingot to block is removed in "chiselMetallurgy.zs" using an array
		recipes.removeByRecipeName("metallurgy:ingot_shapeless_blackSteel");
	}
	{//recipe creation
		recipes.addShapedMirrored("blackSteelItB", <metallurgy:black_steel_block>,
		[[<ore:ingotBlackSteel>, <ore:ingotBlackSteel>, null],
		 [<ore:ingotBlackSteel>, <ore:ingotBlackSteel>, null],
		 [null, null, null]]);
		recipes.addShapeless("blackSteelBtI", <metallurgy:black_steel_ingot>*4, [<ore:decorBlackSteel>]);
	}
}
{//Vyroxeres
	{//recipe removal
		//vanilla ingot to block is removed in "chiselMetallurgy.zs" using an array
		recipes.removeByRecipeName("metallurgy:ingot_shapeless_vyroxeres");
	}
	{//recipe creation
		recipes.addShapedMirrored("vyroxeresItB", <metallurgy:vyroxeres_block>,
		[[<ore:ingotVyroxeres>, <ore:ingotVyroxeres>, null],
		 [<ore:ingotVyroxeres>, <ore:ingotVyroxeres>, null],
		 [null, null, null]]);
		recipes.addShapeless("vyroxeresBtI", <metallurgy:vyroxeres_ingot>*4, [<ore:decorVyroxeres>]);
	}
}
{//Lutetium
	{//recipe removal
		//vanilla ingot to block is removed in "chiselMetallurgy.zs" using an array
		recipes.removeByRecipeName("metallurgy:ingot_shapeless_lutetium");
	}
	{//recipe creation
		recipes.addShapedMirrored("lutetiumItB", <metallurgy:lutetium_block>,
		[[<ore:ingotLutetium>, <ore:ingotLutetium>, null],
		 [<ore:ingotLutetium>, <ore:ingotLutetium>, null],
		 [null, null, null]]);
		recipes.addShapeless("lutetiumBtI", <metallurgy:lutetium_ingot>*4, [<ore:decorLutetium>]);
	}
}
{//Osmium
	{//recipe removal
		//vanilla ingot to block is removed in "chiselMetallurgy.zs" using an array
		recipes.removeByRecipeName("metallurgy:ingot_shapeless_osmium");
	}
	{//recipe creation
		recipes.addShapedMirrored("osmiumItB", <metallurgy:osmium_block>,
		[[<ore:ingotOsmium>, <ore:ingotOsmium>, null],
		 [<ore:ingotOsmium>, <ore:ingotOsmium>, null],
		 [null, null, null]]);
		recipes.addShapeless("osmiumBtI", <metallurgy:osmium_ingot>*4, [<ore:decorOsmium>]);
	}
}
{//Oureclase
	{//recipe removal
		//vanilla ingot to block is removed in "chiselMetallurgy.zs" using an array
		recipes.removeByRecipeName("metallurgy:ingot_shapeless_oureclase");
	}
	{//recipe creation
		recipes.addShapedMirrored("oureclaseItB", <metallurgy:oureclase_block>,
		[[<ore:ingotOureclase>, <ore:ingotOureclase>, null],
		 [<ore:ingotOureclase>, <ore:ingotOureclase>, null],
		 [null, null, null]]);
		recipes.addShapeless("oureclaseBtI", <metallurgy:oureclase_ingot>*4, [<ore:decorOureclase>]);
	}
}
{//Inolashite
	{//recipe removal
		//vanilla ingot to block is removed in "chiselMetallurgy.zs" using an array
		recipes.removeByRecipeName("metallurgy:ingot_shapeless_inolashite");
	}
	{//recipe creation
		recipes.addShapedMirrored("inolashiteItB", <metallurgy:inolashite_block>,
		[[<ore:ingotInolashite>, <ore:ingotInolashite>, null],
		 [<ore:ingotInolashite>, <ore:ingotInolashite>, null],
		 [null, null, null]]);
		recipes.addShapeless("inolashiteBtI", <metallurgy:inolashite_ingot>*4, [<ore:decorInolashite>]);
	}
}
{//Meutoite
	{//recipe removal
		//vanilla ingot to block is removed in "chiselMetallurgy.zs" using an array
		recipes.removeByRecipeName("metallurgy:ingot_shapeless_meutoite");
	}
	{//recipe creation
		recipes.addShapedMirrored("meutoiteItB", <metallurgy:meutoite_block>,
		[[<ore:ingotMeutoite>, <ore:ingotMeutoite>, null],
		 [<ore:ingotMeutoite>, <ore:ingotMeutoite>, null],
		 [null, null, null]]);
		recipes.addShapeless("meutoiteBtI", <metallurgy:meutoite_ingot>*4, [<ore:decorMeutoite>]);
	}
}
{//Orichalcum
	{//recipe removal
		//vanilla ingot to block is removed in "chiselMetallurgy.zs" using an array
		recipes.removeByRecipeName("metallurgy:ingot_shapeless_orichalcum");
	}
	{//recipe creation
		recipes.addShapedMirrored("orichalcumItB", <metallurgy:orichalcum_block>,
		[[<ore:ingotOrichalcum>, <ore:ingotOrichalcum>, null],
		 [<ore:ingotOrichalcum>, <ore:ingotOrichalcum>, null],
		 [null, null, null]]);
		recipes.addShapeless("orichalcumBtI", <metallurgy:orichalcum_ingot>*4, [<ore:decorOrichalcum>]);
	}
}
{//Infuscolium
	{//recipe removal
		//vanilla ingot to block is removed in "chiselMetallurgy.zs" using an array
		recipes.removeByRecipeName("metallurgy:ingot_shapeless_infuscolium");
	}
	{//recipe creation
		recipes.addShapedMirrored("infuscoliumItB", <metallurgy:infuscolium_block>,
		[[<ore:ingotInfuscolium>, <ore:ingotInfuscolium>, null],
		 [<ore:ingotInfuscolium>, <ore:ingotInfuscolium>, null],
		 [null, null, null]]);
		recipes.addShapeless("infuscoliumBtI", <metallurgy:infuscolium_ingot>*4, [<ore:decorInfuscolium>]);
	}
}
{//Midasium
	{//recipe removal
		//vanilla ingot to block is removed in "chiselMetallurgy.zs" using an array
		recipes.removeByRecipeName("metallurgy:ingot_shapeless_midasium");
	}
	{//recipe creation
		recipes.addShapedMirrored("midasiumItB", <metallurgy:midasium_block>,
		[[<ore:ingotMidasium>, <ore:ingotMidasium>, null],
		 [<ore:ingotMidasium>, <ore:ingotMidasium>, null],
		 [null, null, null]]);
		recipes.addShapeless("midasiumBtI", <metallurgy:midasium_ingot>*4, [<ore:decorMidasium>]);
	}
}
{//ShadowSteel
	{//recipe removal
		//vanilla ingot to block is removed in "chiselMetallurgy.zs" using an array
		recipes.removeByRecipeName("metallurgy:ingot_shapeless_shadowSteel");
	}
	{//recipe creation
		recipes.addShapedMirrored("shadowSteelItB", <metallurgy:shadow_steel_block>,
		[[<ore:ingotShadowSteel>, <ore:ingotShadowSteel>, null],
		 [<ore:ingotShadowSteel>, <ore:ingotShadowSteel>, null],
		 [null, null, null]]);
		recipes.addShapeless("shadowSteelBtI", <metallurgy:shadow_steel_ingot>*4, [<ore:decorShadowSteel>]);
	}
}
{//Krik
	{//recipe removal
		//vanilla ingot to block is removed in "chiselMetallurgy.zs" using an array
		recipes.removeByRecipeName("metallurgy:ingot_shapeless_krik");
	}
	{//recipe creation
		recipes.addShapedMirrored("krikItB", <metallurgy:krik_block>,
		[[<ore:ingotKrik>, <ore:ingotKrik>, null],
		 [<ore:ingotKrik>, <ore:ingotKrik>, null],
		 [null, null, null]]);
		recipes.addShapeless("krikBtI", <metallurgy:krik_ingot>*4, [<ore:decorKrik>]);
	}
}
{//Rubracium
	{//recipe removal
		//vanilla ingot to block is removed in "chiselMetallurgy.zs" using an array
		recipes.removeByRecipeName("metallurgy:ingot_shapeless_rubracium");
	}
	{//recipe creation
		recipes.addShapedMirrored("rubraciumItB", <metallurgy:rubracium_block>,
		[[<ore:ingotRubracium>, <ore:ingotRubracium>, null],
		 [<ore:ingotRubracium>, <ore:ingotRubracium>, null],
		 [null, null, null]]);
		recipes.addShapeless("rubraciumBtI", <metallurgy:rubracium_ingot>*4, [<ore:decorRubracium>]);
	}
}
{//Iron
	{//recipe creation
		recipes.addShapedMirrored("ironItB", <metallurgy:iron_engraved_block> * 2,
		[[<ore:ingotIron>, <ore:ingotIron>, <ore:ingotIron>],
		 [<ore:ingotIron>, null, <ore:ingotIron>],
		 [<ore:ingotIron>, <ore:ingotIron>, <ore:ingotIron>]]);
		recipes.addShapeless("ironBtI", <minecraft:iron_ingot>*4, [<ore:decorIron>]);
	}
}
{//Gold
	{//recipe creation
		recipes.addShapedMirrored("goldItB", <metallurgy:gold_engraved_block> * 2,
		[[<ore:ingotGold>, <ore:ingotGold>, <ore:ingotGold>],
		 [<ore:ingotGold>, null, <ore:ingotGold>],
		 [<ore:ingotGold>, <ore:ingotGold>, <ore:ingotGold>]]);
		recipes.addShapeless("goldBtI", <minecraft:gold_ingot>*4, [<ore:decorGold>]);
	}
}
print("Success!");
print("Proceeding to create the new chisel variants...");