package it.hurts.metallurgy_5.block;

import it.hurts.metallurgy_5.fluid.ModFluids;
import it.hurts.metallurgy_5.util.MetallurgyTabs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;

/***************************
*
* Author : ItHurtsLikeHell
* Project: Metallurgy-5
* Date   : 28 ago 2018
* Time   : 18:24:07
*
***************************/

public class ModBlocks {

//	Ore
//	public static BlockOre  = new BlockOre("","").setCreativeTab(MetallurgyTabs.tabOre);
	
	public static BlockOre oreAdamantine = new BlockOre("adamantine_ore","oreAdamantine").setCreativeTab(MetallurgyTabs.tabOre);
	public static BlockOre oreAlduorite = new BlockOre("alduorite_ore","oreAlduorite").setCreativeTab(MetallurgyTabs.tabOre);
	public static BlockOre oreAstralSilver = new BlockOre("astral_silver_ore","oreAstralSilver").setCreativeTab(MetallurgyTabs.tabOre);
	public static BlockOre oreAtlarus = new BlockOre("atlarus_ore","oreAtlarus").setCreativeTab(MetallurgyTabs.tabOre);
	public static BlockOre oreCarmot = new BlockOre("carmot_ore","oreCarmot").setCreativeTab(MetallurgyTabs.tabOre);
	public static BlockOre oreCeruclase = new BlockOre("ceruclase_ore", "oreCeruclase").setCreativeTab(MetallurgyTabs.tabOre);
	public static BlockOre oreCopper = new BlockOre("copper_ore","oreCopper").setCreativeTab(MetallurgyTabs.tabOre);
	public static BlockOre oreDeepIron = new BlockOre("deep_iron_ore","oreDeepIron").setCreativeTab(MetallurgyTabs.tabOre);
	public static BlockOre oreEximite = new BlockOre("eximite_ore","oreEximite").setCreativeTab(MetallurgyTabs.tabOre);
	public static BlockOre oreIgnatius = new BlockOre("ignatius_ore","oreIgnatius").setCreativeTab(MetallurgyTabs.tabOre);
	public static BlockOre oreInfuscolium = new BlockOre("infuscolium_ore","oreInfuscolium").setCreativeTab(MetallurgyTabs.tabOre);
	public static BlockOre oreKalendrite = new BlockOre("kalendrite_ore","oreKalendrite").setCreativeTab(MetallurgyTabs.tabOre);
	public static BlockOre oreLemurite = new BlockOre("lemurite_ore","oreLemurite").setCreativeTab(MetallurgyTabs.tabOre);
	public static BlockOre oreManganese = new BlockOre("manganese_ore","oreManganese").setCreativeTab(MetallurgyTabs.tabOre);
	public static BlockOre oreMeutoite = new BlockOre("meutoite_ore","oreMeutoite").setCreativeTab(MetallurgyTabs.tabOre);
	public static BlockOre oreMidasium = new BlockOre("midasium_ore","oreMidasium").setCreativeTab(MetallurgyTabs.tabOre);
	public static BlockOre oreMithril = new BlockOre("mithril_ore","oreMithril").setCreativeTab(MetallurgyTabs.tabOre);
	public static BlockOre oreOrichalcum = new BlockOre("orichalcum_ore","oreOrichalcum").setCreativeTab(MetallurgyTabs.tabOre);
	public static BlockOre oreOureclase = new BlockOre("oureclase_ore","oreOureclase").setCreativeTab(MetallurgyTabs.tabOre);
	public static BlockOre orePlatinum = new BlockOre("platinum_ore","orePlatinum").setCreativeTab(MetallurgyTabs.tabOre);
	public static BlockOre orePrometheum = new BlockOre("prometheum_ore","orePrometheum").setCreativeTab(MetallurgyTabs.tabOre);
	public static BlockOre oreRubracium = new BlockOre("rubracium_ore","oreRubracium").setCreativeTab(MetallurgyTabs.tabOre);
	public static BlockOre oreSanguinite = new BlockOre("sanguinite_ore","oreSanguinite").setCreativeTab(MetallurgyTabs.tabOre);
	public static BlockOre oreShadowIron = new BlockOre("shadow_iron_ore","oreShadowIron").setCreativeTab(MetallurgyTabs.tabOre);
	public static BlockOre oreSilver = new BlockOre("silver_ore","oreSilver").setCreativeTab(MetallurgyTabs.tabOre);
	public static BlockOre oreTin = new BlockOre("tin_ore","oreTin").setCreativeTab(MetallurgyTabs.tabOre);
	public static BlockOre oreVulcanite = new BlockOre("vulcanite_ore","oreVulcanite").setCreativeTab(MetallurgyTabs.tabOre);
	public static BlockOre oreVyroxeres = new BlockOre("vyroxeres_ore","oreVyroxeres").setCreativeTab(MetallurgyTabs.tabOre);
	public static BlockOre oreZinc = new BlockOre("zinc_ore","oreZinc").setCreativeTab(MetallurgyTabs.tabOre);
	
	public static BlockOre oreSulfur = new BlockOre("sulfur_ore","oreSulfur").setCreativeTab(MetallurgyTabs.tabOre);
	public static BlockOre orePhosphorite = new BlockOre("phosphorite_ore","orePhosphorite").setCreativeTab(MetallurgyTabs.tabOre);
	public static BlockOre oreBitumen = new BlockOre("bitumen_ore","oreBitumen").setCreativeTab(MetallurgyTabs.tabOre);
	public static BlockOre orePotash = new BlockOre("potash_ore","orePotash").setCreativeTab(MetallurgyTabs.tabOre);
	
//	Block
//	public static BlockOre  = new BlockOre("","").setCreativeTab(MetallurgyTabs.tabBlock);

	//Metal Blocks
	public static BlockOre blockAdamantine = new BlockOre("adamantine_block","blockAdamantine").setCreativeTab(MetallurgyTabs.tabBlock);
	public static BlockOre blockAlduorite = new BlockOre("alduorite_block","blockAlduorite").setCreativeTab(MetallurgyTabs.tabBlock);
	public static BlockOre blockAngmallen = new BlockOre("angmallen_block","blockAngmallen").setCreativeTab(MetallurgyTabs.tabBlock);
	public static BlockOre blockAstralSilver = new BlockOre("astral_silver_block","blockAstralSilver").setCreativeTab(MetallurgyTabs.tabBlock);
	public static BlockOre blockAtlarus = new BlockOre("atlarus_block","blockAtlarus").setCreativeTab(MetallurgyTabs.tabBlock);
	public static BlockOre blockAmordrine = new BlockOre("amordrine_block","blockAmordrine").setCreativeTab(MetallurgyTabs.tabBlock);
	public static BlockOre blockBlackSteel = new BlockOre("black_steel_block","blockBlackSteel").setCreativeTab(MetallurgyTabs.tabBlock);
	public static BlockOre blockBrass = new BlockOre("brass_block","blockBrass").setCreativeTab(MetallurgyTabs.tabBlock);
	public static BlockOre blockBronze = new BlockOre("bronze_block","blockBronze").setCreativeTab(MetallurgyTabs.tabBlock);
	public static BlockOre blockCarmot = new BlockOre("carmot_block","blockCarmot").setCreativeTab(MetallurgyTabs.tabBlock);
	public static BlockOre blockCelenegil = new BlockOre("celenegil_block","blockCelenegil").setCreativeTab(MetallurgyTabs.tabBlock);
	public static BlockOre blockCeruclase = new BlockOre("ceruclase_block","blockCeruclase").setCreativeTab(MetallurgyTabs.tabBlock);
	public static BlockOre blockCopper = new BlockOre("copper_block","blockCopper").setCreativeTab(MetallurgyTabs.tabBlock);
	public static BlockOre blockDamascusSteel= new BlockOre("damascus_steel_block","blockDamascusSteel").setCreativeTab(MetallurgyTabs.tabBlock);
	public static BlockOre blockDeepIron = new BlockOre("deep_iron_block","blockDeepIron").setCreativeTab(MetallurgyTabs.tabBlock);
	public static BlockOre blockDesichalkos = new BlockOre("desichalkos_block","blockDesichalkos").setCreativeTab(MetallurgyTabs.tabBlock);
	public static BlockOre blockElectrum = new BlockOre("electrum_block", "blockElectrum").setCreativeTab(MetallurgyTabs.tabBlock);
	public static BlockOre blockEximite = new BlockOre("eximite_block","blockEximite").setCreativeTab(MetallurgyTabs.tabBlock);
	public static BlockOre blockHaderoth = new BlockOre("haderoth_block","blockHaderoth").setCreativeTab(MetallurgyTabs.tabBlock);
	public static BlockOre blockHepatizon = new BlockOre("hepatizon_block","blockHepatizon").setCreativeTab(MetallurgyTabs.tabBlock);
	public static BlockOre blockIgnatius = new BlockOre("ignatius_block","blockIgnatius").setCreativeTab(MetallurgyTabs.tabBlock);
	public static BlockOre blockInfuscolium = new BlockOre("infuscolium_block","blockInfuscolium").setCreativeTab(MetallurgyTabs.tabBlock);
	public static BlockOre blockInolashite = new BlockOre("inolashite_block","blockInolashite").setCreativeTab(MetallurgyTabs.tabBlock);
	public static BlockOre blockKalendrite = new BlockOre("kalendrite_block","blockKalendrite").setCreativeTab(MetallurgyTabs.tabBlock);
	public static BlockOre blockLemurite = new BlockOre("lemurite_block","blockLemurite").setCreativeTab(MetallurgyTabs.tabBlock);
	public static BlockOre blockManganese = new BlockOre("manganese_block","blockManganese").setCreativeTab(MetallurgyTabs.tabBlock);
	public static BlockOre blockMeutoite = new BlockOre("meutoite_block","blockMeutoite").setCreativeTab(MetallurgyTabs.tabBlock);
	public static BlockOre blockMidasium = new BlockOre("midasium_block","blockMidasium").setCreativeTab(MetallurgyTabs.tabBlock);
	public static BlockOre blockMithril = new BlockOre("mithril_block","blockMithril").setCreativeTab(MetallurgyTabs.tabBlock);
	public static BlockOre blockOrichalcum = new BlockOre("orichalcum_block","blockOrichalcum").setCreativeTab(MetallurgyTabs.tabBlock);
	public static BlockOre blockOureclase = new BlockOre("oureclase_block","blockOureclase").setCreativeTab(MetallurgyTabs.tabBlock);
	public static BlockOre blockPlatinum = new BlockOre("platinum_block","blockPlatinum").setCreativeTab(MetallurgyTabs.tabBlock);
	public static BlockOre blockPrometheum = new BlockOre("prometheum_block","blockPrometheum").setCreativeTab(MetallurgyTabs.tabBlock);
	public static BlockOre blockQuickSilver = new BlockOre("quicksilver_block","blockQuickSilver").setCreativeTab(MetallurgyTabs.tabBlock);
	public static BlockOre blockRubracium = new BlockOre("rubracium_block","blockRubracium").setCreativeTab(MetallurgyTabs.tabBlock);
	public static BlockOre blockSanguinite = new BlockOre("sanguinite_block","blockSanguinite").setCreativeTab(MetallurgyTabs.tabBlock);
	public static BlockOre blockShadowIron = new BlockOre("shadow_iron_block","blockShadowIron").setCreativeTab(MetallurgyTabs.tabBlock);
	public static BlockOre blockShadowSteel = new BlockOre("shadow_steel_block","blockShadowSteel").setCreativeTab(MetallurgyTabs.tabBlock);
	public static BlockOre blockSilver = new BlockOre("silver_block","blockSilver").setCreativeTab(MetallurgyTabs.tabBlock);
	public static BlockOre blockSteel = new BlockOre("steel_block","blockSteel").setCreativeTab(MetallurgyTabs.tabBlock);
	public static BlockOre blockTartarite = new BlockOre("tartarite_block","blockTartarite").setCreativeTab(MetallurgyTabs.tabBlock);
	public static BlockOre blockTin= new BlockOre("tin_block","blockTin").setCreativeTab(MetallurgyTabs.tabBlock);
	public static BlockOre blockVulcanite = new BlockOre("vulcanite_block","blockVulcanite").setCreativeTab(MetallurgyTabs.tabBlock);
	public static BlockOre blockVyroxeres = new BlockOre("vyroxeres_block","blockVyroxeres").setCreativeTab(MetallurgyTabs.tabBlock);
	public static BlockOre blockZinc = new BlockOre("zinc_block","blockZinc").setCreativeTab(MetallurgyTabs.tabBlock);

	//FluidBlocks
	public static FluidBlockBase blockMoltenAdamantine = new FluidBlockBase(ModFluids.MOLTEN_ADAMANTINE, Material.LAVA, "molten_adamantine");
    public static FluidBlockBase blockMoltenAlduorite = new FluidBlockBase(ModFluids.MOLTEN_ALDUORITE, Material.LAVA, "molten_alduorite");
    public static FluidBlockBase blockMoltenAmordrine = new FluidBlockBase(ModFluids.MOLTEN_AMORDRINE, Material.LAVA, "molten_amordrine");
    public static FluidBlockBase blockMoltenAngmallen = new FluidBlockBase(ModFluids.MOLTEN_ANGMALLEN, Material.LAVA, "molten_angmallen");
    public static FluidBlockBase blockMoltenAstralSilver = new FluidBlockBase(ModFluids.MOLTEN_ASTRAL_SILVER, Material.LAVA, "molten_astral_silver");
    public static FluidBlockBase blockMoltenAtlarus = new FluidBlockBase(ModFluids.MOLTEN_ATLARUS, Material.LAVA, "molten_atlarus");
    public static FluidBlockBase blockMoltenBlackSteel = new FluidBlockBase(ModFluids.MOLTEN_BLACK_STEEL, Material.LAVA, "molten_black_steel");
    public static FluidBlockBase blockMoltenBrass = new FluidBlockBase(ModFluids.MOLTEN_BRASS, Material.LAVA, "molten_brass");
    public static FluidBlockBase blockMoltenBronze = new FluidBlockBase(ModFluids.MOLTEN_BRONZE, Material.LAVA, "molten_bronze");
    public static FluidBlockBase blockMoltenCarmot = new FluidBlockBase(ModFluids.MOLTEN_CARMOT, Material.LAVA, "molten_carmot");
    public static FluidBlockBase blockMoltenCelenegil = new FluidBlockBase(ModFluids.MOLTEN_CELENEGIL, Material.LAVA, "molten_celenegil");
    public static FluidBlockBase blockMoltenCeruclase = new FluidBlockBase(ModFluids.MOLTEN_CERUCLASE, Material.LAVA, "molten_ceruclase");
    public static FluidBlockBase blockMoltenCopper = new FluidBlockBase(ModFluids.MOLTEN_COPPER, Material.LAVA, "molten_copper");
    public static FluidBlockBase blockMoltenDamascusSteel = new FluidBlockBase(ModFluids.MOLTEN_DAMASCUS_STEEL, Material.LAVA, "molten_damascus_steel");
    public static FluidBlockBase blockMoltenDeepIron = new FluidBlockBase(ModFluids.MOLTEN_DEEP_IRON, Material.LAVA, "molten_deep_iron");
    public static FluidBlockBase blockMoltenDesichalkos = new FluidBlockBase(ModFluids.MOLTEN_DESICHALKOS, Material.LAVA, "molten_desichalkos");
    public static FluidBlockBase blockMoltenElectrum = new FluidBlockBase(ModFluids.MOLTEN_ELECTRUM, Material.LAVA, "molten_electrum");
    public static FluidBlockBase blockMoltenEximite = new FluidBlockBase(ModFluids.MOLTEN_EXIMITE, Material.LAVA, "molten_eximite");
    public static FluidBlockBase blockMoltenHaderoth = new FluidBlockBase(ModFluids.MOLTEN_HADEROTH, Material.LAVA, "molten_haderoth");
    public static FluidBlockBase blockMoltenHepatizon = new FluidBlockBase(ModFluids.MOLTEN_HEPATIZON, Material.LAVA, "molten_hepatizon");
    public static FluidBlockBase blockMoltenIgnatius = new FluidBlockBase(ModFluids.MOLTEN_IGNATIUS, Material.LAVA, "molten_ignatius");
    public static FluidBlockBase blockMoltenInfuscolium = new FluidBlockBase(ModFluids.MOLTEN_INFUSCOLIUM, Material.LAVA, "molten_infuscolium");
    public static FluidBlockBase blockMoltenInolashite = new FluidBlockBase(ModFluids.MOLTEN_INOLASHITE, Material.LAVA, "molten_inolashite");
    public static FluidBlockBase blockMoltenKalendrite = new FluidBlockBase(ModFluids.MOLTEN_KALENDRITE, Material.LAVA, "molten_kalendrite");
    public static FluidBlockBase blockMoltenLemurite = new FluidBlockBase(ModFluids.MOLTEN_LEMURITE, Material.LAVA, "molten_lemurite");
    public static FluidBlockBase blockMoltenManganese = new FluidBlockBase(ModFluids.MOLTEN_MANGANESE, Material.LAVA, "molten_manganese");
    public static FluidBlockBase blockMoltenMeutoite = new FluidBlockBase(ModFluids.MOLTEN_MEUTOITE, Material.LAVA, "molten_meutoite");
    public static FluidBlockBase blockMoltenMidasium = new FluidBlockBase(ModFluids.MOLTEN_MIDASIUM, Material.LAVA, "molten_midasium");
    public static FluidBlockBase blockMoltenMithril = new FluidBlockBase(ModFluids.MOLTEN_MITHRIL, Material.LAVA, "molten_mithril");
    public static FluidBlockBase blockMoltenOrichalcum = new FluidBlockBase(ModFluids.MOLTEN_ORICHALCUM, Material.LAVA, "molten_orichalcum");
    public static FluidBlockBase blockMoltenOureclase = new FluidBlockBase(ModFluids.MOLTEN_OURECLASE, Material.LAVA, "molten_oureclase");
    public static FluidBlockBase blockMoltenPlatinum = new FluidBlockBase(ModFluids.MOLTEN_PLATINUM, Material.LAVA, "molten_platinum");
    public static FluidBlockBase blockMoltenPrometheum = new FluidBlockBase(ModFluids.MOLTEN_PROMETHEUM, Material.LAVA, "molten_prometheum");
    public static FluidBlockBase blockMoltenQuicksilver = new FluidBlockBase(ModFluids.MOLTEN_QUICKSILVER, Material.LAVA, "molten_quicksilver");
    public static FluidBlockBase blockMoltenRubracium = new FluidBlockBase(ModFluids.MOLTEN_RUBRACIUM, Material.LAVA, "molten_rubracium");
    public static FluidBlockBase blockMoltenSanguinite = new FluidBlockBase(ModFluids.MOLTEN_SANGUINITE, Material.LAVA, "molten_sanguinite");
    public static FluidBlockBase blockMoltenShadowIron = new FluidBlockBase(ModFluids.MOLTEN_SHADOW_IRON, Material.LAVA, "molten_shadow_iron");
    public static FluidBlockBase blockMoltenShadowSteel = new FluidBlockBase(ModFluids.MOLTEN_SHADOW_STEEL, Material.LAVA, "molten_shadow_steel");
    public static FluidBlockBase blockMoltenSilver = new FluidBlockBase(ModFluids.MOLTEN_SILVER, Material.LAVA, "molten_silver");
    public static FluidBlockBase blockMoltenSteel = new FluidBlockBase(ModFluids.MOLTEN_STEEL, Material.LAVA, "molten_steel");
    public static FluidBlockBase blockMoltenTartarite = new FluidBlockBase(ModFluids.MOLTEN_TARTARITE, Material.LAVA, "molten_tartarite");
    public static FluidBlockBase blockMoltenTin = new FluidBlockBase(ModFluids.MOLTEN_TIN, Material.LAVA, "molten_tin");
    public static FluidBlockBase blockMoltenVulcanite = new FluidBlockBase(ModFluids.MOLTEN_VULCANITE, Material.LAVA, "molten_vulcanite");
    public static FluidBlockBase blockMoltenVyroxeres = new FluidBlockBase(ModFluids.MOLTEN_VYROXERES, Material.LAVA, "molten_vyroxeres");
    public static FluidBlockBase blockMoltenZinc = new FluidBlockBase(ModFluids.MOLTEN_ZINC, Material.LAVA, "molten_zinc");

	//Other Blocks
	public static BlockOre blockBitumen = new BlockOre("bitumen_block","blockBitumen").setCreativeTab(MetallurgyTabs.tabBlock);
	public static BlockOre blockCharcoal = new BlockOre("charcoal_block","blockCharcoal").setCreativeTab(MetallurgyTabs.tabBlock);
	public static BlockOre blockSulfur = new BlockOre("sulfur_block","blockSulfur").setCreativeTab(MetallurgyTabs.tabBlock);

	//Tile Entities
	public static BlockCrusher crusher = new BlockCrusher("crusher").setCreativeTab(MetallurgyTabs.tabMachine);
	public static BlockAlloyer alloyer = new BlockAlloyer("alloyer").setCreativeTab(MetallurgyTabs.tabMachine);
	
//	Tile
	
	public static void register(IForgeRegistry<Block> registry) {
		registry.registerAll(
				//ores
				  oreAdamantine,
				  oreAstralSilver,
				  oreAtlarus,
				  oreCarmot,
				  oreCopper,
				  oreDeepIron,
				  oreEximite,
				  oreInfuscolium,
				  oreManganese,
				  oreMeutoite,	
				  oreMithril,
				  oreOrichalcum,
				  oreOureclase,
				  orePlatinum,
				  orePrometheum,
				  oreRubracium,
				  oreSilver,
				  oreTin,
				  oreZinc,
				  
				  oreAlduorite,
				  oreCeruclase,
				  oreIgnatius,
				  oreKalendrite,
				  oreLemurite,
				  oreMidasium,
				  oreSanguinite,
				  oreShadowIron,
				  oreVulcanite,
				  oreVyroxeres,
				  
				  oreSulfur,
				  orePhosphorite,
				  oreBitumen,
				  orePotash,
				
//				Block
				 blockAdamantine,
				 blockAlduorite,
				 blockAngmallen,
				 blockAstralSilver,
				 blockAtlarus,
				 blockAmordrine,
				 blockBlackSteel,
				 blockBitumen,
				 blockBrass,
				 blockBronze,
				 blockCarmot,
				 blockCelenegil,
				 blockCeruclase,
				 blockCharcoal,
				 blockCopper,
				 blockDamascusSteel,
				 blockDeepIron,
				 blockDesichalkos,
				 blockElectrum,
				 blockEximite,
				 blockHaderoth,
				 blockHepatizon,
				 blockIgnatius,
				 blockInfuscolium,
				 blockInolashite,
				 blockKalendrite,
				 blockLemurite,
				 blockManganese,
				 blockMeutoite,
				 blockMidasium,
				 blockMithril,
				 blockOrichalcum,
				 blockOureclase,
				 blockPlatinum,
				 blockPrometheum,
				 blockQuickSilver,
				 blockRubracium,
				 blockSanguinite,
				 blockShadowIron,
				 blockShadowSteel,
				 blockSilver,
				 blockSteel,
				 blockSulfur,
				 blockTartarite,
				 blockTin,
				 blockVulcanite,
				 blockVyroxeres,
				 blockZinc,

				//FluidBlocks
				blockMoltenAdamantine,
				blockMoltenAlduorite,
				blockMoltenAmordrine,
				blockMoltenAngmallen,
				blockMoltenAstralSilver,
				blockMoltenAtlarus,
				blockMoltenBlackSteel,
				blockMoltenBrass,
				blockMoltenBronze,
				blockMoltenCarmot,
				blockMoltenCelenegil,
				blockMoltenCeruclase,
				blockMoltenCopper,
				blockMoltenDamascusSteel,
				blockMoltenDeepIron,
				blockMoltenDesichalkos,
				blockMoltenElectrum,
				blockMoltenEximite,
				blockMoltenHaderoth,
				blockMoltenHepatizon,
				blockMoltenIgnatius,
				blockMoltenInfuscolium,
				blockMoltenInolashite,
				blockMoltenKalendrite,
				blockMoltenLemurite,
				blockMoltenManganese,
				blockMoltenMeutoite,
				blockMoltenMidasium,
				blockMoltenMithril,
				blockMoltenOrichalcum,
				blockMoltenOureclase,
				blockMoltenPlatinum,
				blockMoltenPrometheum,
				blockMoltenQuicksilver,
				blockMoltenRubracium,
				blockMoltenSanguinite,
				blockMoltenShadowIron,
				blockMoltenShadowSteel,
				blockMoltenSilver,
				blockMoltenSteel,
				blockMoltenTartarite,
				blockMoltenTin,
				blockMoltenVulcanite,
				blockMoltenVyroxeres,
				blockMoltenZinc,

				//Tile Entities
				crusher,
				alloyer
				
		);
		
	}

	public static void registerItemBlocks(IForgeRegistry<Item> registry) {
		registry.registerAll(
//				.createItemBlock()
//				Ore
				  oreAdamantine.createItemBlock(),
				  oreAstralSilver.createItemBlock(),
				  oreAtlarus.createItemBlock(),
				  oreCarmot.createItemBlock(),
				  oreCopper.createItemBlock(),
				  oreDeepIron.createItemBlock(),
				  oreEximite.createItemBlock(),
				  oreInfuscolium.createItemBlock(),
				  oreManganese.createItemBlock(),
				  oreMeutoite.createItemBlock(),
				  oreMithril.createItemBlock(),
				  oreOrichalcum.createItemBlock(),
				  oreOureclase.createItemBlock(),
				  orePlatinum.createItemBlock(),
				  orePrometheum.createItemBlock(),
				  oreRubracium.createItemBlock(),
				  oreSilver.createItemBlock(),
				  oreTin.createItemBlock(),
				  oreZinc.createItemBlock(),
				  
				  oreAlduorite.createItemBlock(),
				  oreCeruclase.createItemBlock(),
				  oreIgnatius.createItemBlock(),
				  oreKalendrite.createItemBlock(),
				  oreLemurite.createItemBlock(),
				  oreMidasium.createItemBlock(),
				  oreSanguinite.createItemBlock(),
				  oreShadowIron.createItemBlock(),
				  oreVulcanite.createItemBlock(),
				  oreVyroxeres.createItemBlock(),
				
				  oreSulfur.createItemBlock(),
				  orePhosphorite.createItemBlock(),
				  oreBitumen.createItemBlock(),
				  orePotash.createItemBlock(),
				
//				Block
				 blockAdamantine.createItemBlock(),
				 blockAlduorite.createItemBlock(),
				 blockAngmallen.createItemBlock(),
				 blockAstralSilver.createItemBlock(),
				 blockAtlarus.createItemBlock(),
				 blockAmordrine.createItemBlock(),
				 blockBitumen.createItemBlock(),
				 blockBlackSteel.createItemBlock(),
				 blockBrass.createItemBlock(),
				 blockBronze.createItemBlock(),
				 blockCarmot.createItemBlock(),
				 blockCelenegil.createItemBlock(),
				 blockCeruclase.createItemBlock(),
				 blockCharcoal.createItemBlock(),
				 blockCopper.createItemBlock(),
				 blockDamascusSteel.createItemBlock(),
				 blockDeepIron.createItemBlock(),
				 blockDesichalkos.createItemBlock(),
				 blockElectrum.createItemBlock(),
				 blockEximite.createItemBlock(),
				 blockHaderoth.createItemBlock(),
				 blockHepatizon.createItemBlock(),
				 blockIgnatius.createItemBlock(),
				 blockInfuscolium.createItemBlock(),
				 blockInolashite.createItemBlock(),
				 blockKalendrite.createItemBlock(),
				 blockLemurite.createItemBlock(),
				 blockManganese.createItemBlock(),
				 blockMeutoite.createItemBlock(),
				 blockMidasium.createItemBlock(),
				 blockMithril.createItemBlock(),
				 blockOrichalcum.createItemBlock(),
				 blockOureclase.createItemBlock(),
				 blockPlatinum.createItemBlock(),
				 blockPrometheum.createItemBlock(),
				 blockQuickSilver.createItemBlock(),
				 blockRubracium.createItemBlock(),
				 blockSanguinite.createItemBlock(),
				 blockShadowIron.createItemBlock(),
				 blockShadowSteel.createItemBlock(),
				 blockSilver.createItemBlock(),
				 blockSteel.createItemBlock(),
				 blockSulfur.createItemBlock(),
				 blockTartarite.createItemBlock(),
				 blockTin.createItemBlock(),
				 blockVulcanite.createItemBlock(),
				 blockVyroxeres.createItemBlock(),
				 blockZinc.createItemBlock(),

				//FluidBlocks
				blockMoltenAdamantine.createItemBlock(),
                blockMoltenAlduorite.createItemBlock(),
                blockMoltenAmordrine.createItemBlock(),
                blockMoltenAngmallen.createItemBlock(),
                blockMoltenAstralSilver.createItemBlock(),
                blockMoltenAtlarus.createItemBlock(),
                blockMoltenBlackSteel.createItemBlock(),
                blockMoltenBrass.createItemBlock(),
                blockMoltenBronze.createItemBlock(),
                blockMoltenCarmot.createItemBlock(),
                blockMoltenCelenegil.createItemBlock(),
                blockMoltenCeruclase.createItemBlock(),
                blockMoltenCopper.createItemBlock(),
                blockMoltenDamascusSteel.createItemBlock(),
                blockMoltenDeepIron.createItemBlock(),
                blockMoltenDesichalkos.createItemBlock(),
                blockMoltenElectrum.createItemBlock(),
                blockMoltenEximite.createItemBlock(),
                blockMoltenHaderoth.createItemBlock(),
                blockMoltenHepatizon.createItemBlock(),
                blockMoltenIgnatius.createItemBlock(),
                blockMoltenInfuscolium.createItemBlock(),
                blockMoltenInolashite.createItemBlock(),
                blockMoltenKalendrite.createItemBlock(),
                blockMoltenLemurite.createItemBlock(),
                blockMoltenManganese.createItemBlock(),
                blockMoltenMeutoite.createItemBlock(),
                blockMoltenMidasium.createItemBlock(),
                blockMoltenMithril.createItemBlock(),
                blockMoltenOrichalcum.createItemBlock(),
                blockMoltenOureclase.createItemBlock(),
                blockMoltenPlatinum.createItemBlock(),
                blockMoltenPrometheum.createItemBlock(),
                blockMoltenQuicksilver.createItemBlock(),
                blockMoltenRubracium.createItemBlock(),
                blockMoltenSanguinite.createItemBlock(),
                blockMoltenShadowIron.createItemBlock(),
                blockMoltenShadowSteel.createItemBlock(),
                blockMoltenSilver.createItemBlock(),
                blockMoltenSteel.createItemBlock(),
                blockMoltenTartarite.createItemBlock(),
                blockMoltenTin.createItemBlock(),
                blockMoltenVulcanite.createItemBlock(),
                blockMoltenVyroxeres.createItemBlock(),
                blockMoltenZinc.createItemBlock(),

				//Tile Entities
				crusher.createItemBlock(),
				alloyer.createItemBlock()

				
		);
	}

	public static void registerModels() {
//		Ore
		  oreAdamantine.registerItemModel(Item.getItemFromBlock(oreAdamantine));
		  oreAstralSilver.registerItemModel(Item.getItemFromBlock(oreAstralSilver));
		  oreAtlarus.registerItemModel(Item.getItemFromBlock(oreAtlarus));
		  oreCarmot.registerItemModel(Item.getItemFromBlock(oreCarmot));
		  oreCopper.registerItemModel(Item.getItemFromBlock(oreCopper));
		  oreDeepIron.registerItemModel(Item.getItemFromBlock(oreDeepIron));
		  oreEximite.registerItemModel(Item.getItemFromBlock(oreEximite));
		  oreInfuscolium.registerItemModel(Item.getItemFromBlock(oreInfuscolium));
		  oreManganese.registerItemModel(Item.getItemFromBlock(oreManganese));
		  oreMeutoite.registerItemModel(Item.getItemFromBlock(oreMeutoite));
		  oreMithril.registerItemModel(Item.getItemFromBlock(oreMithril));
		  oreOrichalcum.registerItemModel(Item.getItemFromBlock(oreOrichalcum));
		  oreOureclase.registerItemModel(Item.getItemFromBlock(oreOureclase));
		  orePlatinum.registerItemModel(Item.getItemFromBlock(orePlatinum));
		  orePrometheum.registerItemModel(Item.getItemFromBlock(orePrometheum));
		  oreRubracium.registerItemModel(Item.getItemFromBlock(oreRubracium));
		  oreSilver.registerItemModel(Item.getItemFromBlock(oreSilver));
		  oreTin.registerItemModel(Item.getItemFromBlock(oreTin));
		  oreZinc.registerItemModel(Item.getItemFromBlock(oreZinc));
		  
		  oreAlduorite.registerItemModel(Item.getItemFromBlock(oreAlduorite));
		  oreCeruclase.registerItemModel(Item.getItemFromBlock(oreCeruclase));
		  oreIgnatius.registerItemModel(Item.getItemFromBlock(oreIgnatius));
		  oreKalendrite.registerItemModel(Item.getItemFromBlock(oreKalendrite));
		  oreLemurite.registerItemModel(Item.getItemFromBlock(oreLemurite));
		  oreMidasium.registerItemModel(Item.getItemFromBlock(oreMidasium));
		  oreSanguinite.registerItemModel(Item.getItemFromBlock(oreSanguinite));
		  oreShadowIron.registerItemModel(Item.getItemFromBlock(oreShadowIron));
		  oreVulcanite.registerItemModel(Item.getItemFromBlock(oreVulcanite));
		  oreVyroxeres.registerItemModel(Item.getItemFromBlock(oreVyroxeres));
		
		  oreSulfur.registerItemModel(Item.getItemFromBlock(oreSulfur));
		  orePhosphorite.registerItemModel(Item.getItemFromBlock(orePhosphorite));
		  oreBitumen.registerItemModel(Item.getItemFromBlock(oreBitumen));
		  orePotash.registerItemModel(Item.getItemFromBlock(orePotash));
		
//		Block
		 blockAdamantine.registerItemModel(Item.getItemFromBlock(blockAdamantine));
		 blockAlduorite.registerItemModel(Item.getItemFromBlock(blockAlduorite));
		 blockAngmallen.registerItemModel(Item.getItemFromBlock(blockAngmallen));
		 blockAstralSilver.registerItemModel(Item.getItemFromBlock(blockAstralSilver));
		 blockAtlarus.registerItemModel(Item.getItemFromBlock(blockAtlarus));
		 blockAmordrine.registerItemModel(Item.getItemFromBlock(blockAmordrine));
		 blockBitumen.registerItemModel(Item.getItemFromBlock(blockBitumen));
		 blockBlackSteel.registerItemModel(Item.getItemFromBlock(blockBlackSteel));
		 blockBrass.registerItemModel(Item.getItemFromBlock(blockBrass));
		 blockBronze.registerItemModel(Item.getItemFromBlock(blockBronze));
		 blockCarmot.registerItemModel(Item.getItemFromBlock(blockCarmot));
		 blockCelenegil.registerItemModel(Item.getItemFromBlock(blockCelenegil));
		 blockCeruclase.registerItemModel(Item.getItemFromBlock(blockCeruclase));
		 blockCharcoal.registerItemModel(Item.getItemFromBlock(blockCharcoal));
		 blockCopper.registerItemModel(Item.getItemFromBlock(blockCopper));
		 blockDamascusSteel.registerItemModel(Item.getItemFromBlock(blockDamascusSteel));
		 blockDeepIron.registerItemModel(Item.getItemFromBlock(blockDeepIron));
		 blockDesichalkos.registerItemModel(Item.getItemFromBlock(blockDesichalkos));
		 blockElectrum.registerItemModel(Item.getItemFromBlock(blockElectrum));
		 blockEximite.registerItemModel(Item.getItemFromBlock(blockEximite));
		 blockHaderoth.registerItemModel(Item.getItemFromBlock(blockHaderoth));
		 blockHepatizon.registerItemModel(Item.getItemFromBlock(blockHepatizon));
		 blockIgnatius.registerItemModel(Item.getItemFromBlock(blockIgnatius));
		 blockInfuscolium.registerItemModel(Item.getItemFromBlock(blockInfuscolium));
		 blockInolashite.registerItemModel(Item.getItemFromBlock(blockInolashite));
		 blockKalendrite.registerItemModel(Item.getItemFromBlock(blockKalendrite));
		 blockLemurite.registerItemModel(Item.getItemFromBlock(blockLemurite));
		 blockManganese.registerItemModel(Item.getItemFromBlock(blockManganese));
		 blockMeutoite.registerItemModel(Item.getItemFromBlock(blockMeutoite));
		 blockMidasium.registerItemModel(Item.getItemFromBlock(blockMidasium));
		 blockMithril.registerItemModel(Item.getItemFromBlock(blockMithril));
		 blockOrichalcum.registerItemModel(Item.getItemFromBlock(blockOrichalcum));
		 blockOureclase.registerItemModel(Item.getItemFromBlock(blockOureclase));
		 blockPlatinum.registerItemModel(Item.getItemFromBlock(blockPlatinum));
		 blockPrometheum.registerItemModel(Item.getItemFromBlock(blockPrometheum));
		 blockQuickSilver.registerItemModel(Item.getItemFromBlock(blockQuickSilver));
		 blockRubracium.registerItemModel(Item.getItemFromBlock(blockRubracium));
		 blockSanguinite.registerItemModel(Item.getItemFromBlock(blockSanguinite));
		 blockShadowIron.registerItemModel(Item.getItemFromBlock(blockShadowIron));
		 blockShadowSteel.registerItemModel(Item.getItemFromBlock(blockShadowSteel));
		 blockSilver.registerItemModel(Item.getItemFromBlock(blockSilver));
		 blockSteel.registerItemModel(Item.getItemFromBlock(blockSteel));
		 blockSulfur.registerItemModel(Item.getItemFromBlock(blockSulfur));
		 blockTartarite.registerItemModel(Item.getItemFromBlock(blockTartarite));
		 blockTin.registerItemModel(Item.getItemFromBlock(blockTin));
		 blockVulcanite.registerItemModel(Item.getItemFromBlock(blockVulcanite));
		 blockVyroxeres.registerItemModel(Item.getItemFromBlock(blockVyroxeres));
		 blockZinc.registerItemModel(Item.getItemFromBlock(blockZinc));

		 //FluidBlocks
		blockMoltenAdamantine.registerItemModel(Item.getItemFromBlock(blockMoltenAdamantine));
        blockMoltenAlduorite.registerItemModel(Item.getItemFromBlock(blockMoltenAlduorite));
        blockMoltenAmordrine.registerItemModel(Item.getItemFromBlock(blockMoltenAmordrine));
        blockMoltenAngmallen.registerItemModel(Item.getItemFromBlock(blockMoltenAngmallen));
        blockMoltenAstralSilver.registerItemModel(Item.getItemFromBlock(blockMoltenAstralSilver));
        blockMoltenAtlarus.registerItemModel(Item.getItemFromBlock(blockMoltenAtlarus));
        blockMoltenBlackSteel.registerItemModel(Item.getItemFromBlock(blockMoltenBlackSteel));
        blockMoltenBrass.registerItemModel(Item.getItemFromBlock(blockMoltenBrass));
        blockMoltenBronze.registerItemModel(Item.getItemFromBlock(blockMoltenBronze));
        blockMoltenCarmot.registerItemModel(Item.getItemFromBlock(blockMoltenCarmot));
        blockMoltenCelenegil.registerItemModel(Item.getItemFromBlock(blockMoltenCelenegil));
        blockMoltenCeruclase.registerItemModel(Item.getItemFromBlock(blockMoltenCeruclase));
        blockMoltenCopper.registerItemModel(Item.getItemFromBlock(blockMoltenCopper));
        blockMoltenDamascusSteel.registerItemModel(Item.getItemFromBlock(blockMoltenDamascusSteel));
        blockMoltenDeepIron.registerItemModel(Item.getItemFromBlock(blockMoltenDeepIron));
        blockMoltenDesichalkos.registerItemModel(Item.getItemFromBlock(blockMoltenDesichalkos));
        blockMoltenElectrum.registerItemModel(Item.getItemFromBlock(blockMoltenElectrum));
        blockMoltenEximite.registerItemModel(Item.getItemFromBlock(blockMoltenEximite));
        blockMoltenHaderoth.registerItemModel(Item.getItemFromBlock(blockMoltenHaderoth));
        blockMoltenHepatizon.registerItemModel(Item.getItemFromBlock(blockMoltenHepatizon));
        blockMoltenIgnatius.registerItemModel(Item.getItemFromBlock(blockMoltenIgnatius));
        blockMoltenInfuscolium.registerItemModel(Item.getItemFromBlock(blockMoltenInfuscolium));
        blockMoltenInolashite.registerItemModel(Item.getItemFromBlock(blockMoltenInolashite));
        blockMoltenKalendrite.registerItemModel(Item.getItemFromBlock(blockMoltenKalendrite));
        blockMoltenLemurite.registerItemModel(Item.getItemFromBlock(blockMoltenLemurite));
        blockMoltenManganese.registerItemModel(Item.getItemFromBlock(blockMoltenManganese));
        blockMoltenMeutoite.registerItemModel(Item.getItemFromBlock(blockMoltenMeutoite));
        blockMoltenMidasium.registerItemModel(Item.getItemFromBlock(blockMoltenMidasium));
        blockMoltenMithril.registerItemModel(Item.getItemFromBlock(blockMoltenMithril));
        blockMoltenOrichalcum.registerItemModel(Item.getItemFromBlock(blockMoltenOrichalcum));
        blockMoltenOureclase.registerItemModel(Item.getItemFromBlock(blockMoltenOureclase));
        blockMoltenPlatinum.registerItemModel(Item.getItemFromBlock(blockMoltenPlatinum));
        blockMoltenPrometheum.registerItemModel(Item.getItemFromBlock(blockMoltenPrometheum));
        blockMoltenQuicksilver.registerItemModel(Item.getItemFromBlock(blockMoltenQuicksilver));
        blockMoltenRubracium.registerItemModel(Item.getItemFromBlock(blockMoltenRubracium));
        blockMoltenSanguinite.registerItemModel(Item.getItemFromBlock(blockMoltenSanguinite));
        blockMoltenShadowIron.registerItemModel(Item.getItemFromBlock(blockMoltenShadowIron));
        blockMoltenShadowSteel.registerItemModel(Item.getItemFromBlock(blockMoltenShadowSteel));
        blockMoltenSilver.registerItemModel(Item.getItemFromBlock(blockMoltenSilver));
        blockMoltenSteel.registerItemModel(Item.getItemFromBlock(blockMoltenSteel));
        blockMoltenTartarite.registerItemModel(Item.getItemFromBlock(blockMoltenTartarite));
        blockMoltenTin.registerItemModel(Item.getItemFromBlock(blockMoltenTin));
        blockMoltenVulcanite.registerItemModel(Item.getItemFromBlock(blockMoltenVulcanite));
        blockMoltenVyroxeres.registerItemModel(Item.getItemFromBlock(blockMoltenVyroxeres));
        blockMoltenZinc.registerItemModel(Item.getItemFromBlock(blockMoltenZinc));


		//Tile Entities
		crusher.registerItemModel(Item.getItemFromBlock(crusher));
		alloyer.registerItemModel(Item.getItemFromBlock(alloyer));
	}
	
}