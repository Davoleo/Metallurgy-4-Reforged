package it.hurts.metallurgy_5.util.recipe;

import it.hurts.metallurgy_5.block.ModBlocks;
import it.hurts.metallurgy_5.item.ModItems;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

/*************************************************
 * Author: Davoleo
 * Date: 23/08/2018
 * Hour: 23.10
 * Project: Metallurgy_5
 * Copyright - © - Davoleo - 2018
 **************************************************/

public class ModRecipes {

    //TODO : Complete general recipes

    public static void init()
    {

        // TODO : Finish ingot oredict initialization
        //Ore-dict
        //Ingots
        ModItems.ingotAdamantine.initOreDict();
        ModItems.ingotAlduorite.initOreDict();
        ModItems.ingotAmordrine.initOreDict();
        ModItems.ingotAngmallen.initOreDict();
        ModItems.ingotAstralSilver.initOreDict();
        ModItems.ingotAtlarus.initOreDict();
        ModItems.ingotBlackSteel.initOreDict();
        ModItems.ingotCarmot.initOreDict();
        ModItems.ingotCelenegil.initOreDict();
        ModItems.ingotCeruclase.initOreDict();
        ModItems.ingotCopper.initOreDict();
        ModItems.ingotDamascusSteel.initOreDict();
        ModItems.ingotDeepIron.initOreDict();
        ModItems.ingotDesichalkos.initOreDict();
        ModItems.ingotEximite.initOreDict();
        ModItems.ingotHaderoth.initOreDict();
        ModItems.ingotHepatizon.initOreDict();
        ModItems.ingotIgnatius.initOreDict();
        ModItems.ingotInfuscolium.initOreDict();
        ModItems.ingotInolashite.initOreDict();
        ModItems.ingotKalendrite.initOreDict();
        ModItems.ingotLemurite.initOreDict();
        ModItems.ingotManganese.initOreDict();
        ModItems.ingotMeutoite.initOreDict();
        ModItems.ingotMidasium.initOreDict();
        ModItems.ingotMithril.initOreDict();
        ModItems.ingotOrichalcum.initOreDict();
        ModItems.ingotOureclase.initOreDict();
        ModItems.ingotPlatinum.initOreDict();
        ModItems.ingotPrometheum.initOreDict();
        ModItems.ingotQuickSilver.initOreDict();
        ModItems.ingotRubracium.initOreDict();
        ModItems.ingotSanguinite.initOreDict();
        ModItems.ingotShadowIron.initOreDict();
        ModItems.ingotShadowSteel.initOreDict();
        ModItems.ingotSilver.initOreDict();
        ModItems.ingotSteel.initOreDict();
        ModItems.ingotTartarite.initOreDict();
        ModItems.ingotTin.initOreDict();
        ModItems.ingotVulcanite.initOreDict();
        ModItems.ingotVyroxeres.initOreDict();
        ModItems.ingotZinc.initOreDict();

        //Blocks
        ModBlocks.blockAdamantine.initOreDict();
        ModBlocks.blockAlduorite.initOreDict();
        ModBlocks.blockAngmallen.initOreDict();
        ModBlocks.blockAstralSilver.initOreDict();
        ModBlocks.blockAtlarus.initOreDict();
        ModBlocks.blockAmordrine.initOreDict();
        ModBlocks.blockBlackSteel.initOreDict();
        ModBlocks.blockBrass.initOreDict();
        ModBlocks.blockBronze.initOreDict();
        ModBlocks.blockCarmot.initOreDict();
        ModBlocks.blockCelenegil.initOreDict();
        ModBlocks.blockCeruclase.initOreDict();
        ModBlocks.blockCharcoal.initOreDict();
        ModBlocks.blockCopper.initOreDict();
        ModBlocks.blockDamascusSteel.initOreDict();
        ModBlocks.blockDeepIron.initOreDict();
        ModBlocks.blockDesichalkos.initOreDict();
        ModBlocks.blockEximite.initOreDict();
        ModBlocks.blockHaderoth.initOreDict();
        ModBlocks.blockHepatizon.initOreDict();
        ModBlocks.blockIgnatius.initOreDict();
        ModBlocks.blockInfuscolium.initOreDict();
        ModBlocks.blockInolashite.initOreDict();
        ModBlocks.blockKalendrite.initOreDict();
        ModBlocks.blockLemurite.initOreDict();
        ModBlocks.blockManganese.initOreDict();
        ModBlocks.blockMeutoite.initOreDict();
        ModBlocks.blockMidasium.initOreDict();
        ModBlocks.blockMithril.initOreDict();
        ModBlocks.blockOrichalcum.initOreDict();
        ModBlocks.blockOureclase.initOreDict();
        ModBlocks.blockPlatinum.initOreDict();
        ModBlocks.blockPrometheum.initOreDict();
        ModBlocks.blockQuickSilver.initOreDict();
        ModBlocks.blockRubracium.initOreDict();
        ModBlocks.blockSanguinite.initOreDict();
        ModBlocks.blockShadowIron.initOreDict();
        ModBlocks.blockShadowSteel.initOreDict();
        ModBlocks.blockSilver.initOreDict();
        ModBlocks.blockSteel.initOreDict();
        ModBlocks.blockSulfur.initOreDict();
        ModBlocks.blockTartarite.initOreDict();
        ModBlocks.blockTin.initOreDict();
        ModBlocks.blockVulcanite.initOreDict();
        ModBlocks.blockVyroxeres.initOreDict();
        ModBlocks.blockZinc.initOreDict();



        //Furnace Recipes
        //Ore2Ingot
        GameRegistry.addSmelting(ModBlocks.oreAdamantine, new ItemStack(ModItems.ingotAdamantine), 1f);
        GameRegistry.addSmelting(ModBlocks.oreAlduorite, new ItemStack(ModItems.ingotAlduorite), 1f);
        GameRegistry.addSmelting(ModBlocks.oreAstralSilver, new ItemStack(ModItems.ingotAstralSilver), 1f);
        GameRegistry.addSmelting(ModBlocks.oreAtlarus, new ItemStack(ModItems.ingotAtlarus), 1f);
        GameRegistry.addSmelting(ModBlocks.oreCarmot, new ItemStack(ModItems.ingotCarmot), 1f);
        GameRegistry.addSmelting(ModBlocks.oreCopper, new ItemStack(ModItems.ingotCopper), 1f);
        GameRegistry.addSmelting(ModBlocks.oreDeepIron, new ItemStack(ModItems.ingotDeepIron), 1f);
        GameRegistry.addSmelting(ModBlocks.oreEximite, new ItemStack(ModItems.ingotEximite), 1f);
        GameRegistry.addSmelting(ModBlocks.oreIgnatius, new ItemStack(ModItems.ingotIgnatius), 1f);
        GameRegistry.addSmelting(ModBlocks.oreInfuscolium, new ItemStack(ModItems.ingotInfuscolium), 1f);
        GameRegistry.addSmelting(ModBlocks.oreKalendrite, new ItemStack(ModItems.ingotKalendrite), 1f);
        GameRegistry.addSmelting(ModBlocks.oreLemurite, new ItemStack(ModItems.ingotLemurite), 1f);
        GameRegistry.addSmelting(ModBlocks.oreManganese, new ItemStack(ModItems.ingotManganese), 1f);
        GameRegistry.addSmelting(ModBlocks.oreMeutoite, new ItemStack(ModItems.ingotMeutoite), 1f);
        GameRegistry.addSmelting(ModBlocks.oreMidasium, new ItemStack(ModItems.ingotMidasium), 1f);
        GameRegistry.addSmelting(ModBlocks.oreMithril, new ItemStack(ModItems.ingotMithril), 1f);
        GameRegistry.addSmelting(ModBlocks.oreOrichalcum, new ItemStack(ModItems.ingotOrichalcum), 1f);
        GameRegistry.addSmelting(ModBlocks.oreOureclase, new ItemStack(ModItems.ingotOureclase), 1f);
        GameRegistry.addSmelting(ModBlocks.orePlatinum, new ItemStack(ModItems.ingotPlatinum), 1f);
        GameRegistry.addSmelting(ModBlocks.orePrometheum, new ItemStack(ModItems.ingotPrometheum), 1f);
        GameRegistry.addSmelting(ModBlocks.oreRubracium, new ItemStack(ModItems.ingotRubracium), 1f);
        GameRegistry.addSmelting(ModBlocks.oreSanguinite, new ItemStack(ModItems.ingotSanguinite), 1f);
        GameRegistry.addSmelting(ModBlocks.oreShadowIron, new ItemStack(ModItems.ingotShadowIron), 1f);
        GameRegistry.addSmelting(ModBlocks.oreSilver, new ItemStack(ModItems.ingotSilver), 1f);
        GameRegistry.addSmelting(ModBlocks.oreTin, new ItemStack(ModItems.ingotTin), 1f);
        GameRegistry.addSmelting(ModBlocks.oreVulcanite, new ItemStack(ModItems.ingotVulcanite), 1f);
        GameRegistry.addSmelting(ModBlocks.oreVyroxeres, new ItemStack(ModItems.ingotVyroxeres), 1f);
        GameRegistry.addSmelting(ModBlocks.oreZinc, new ItemStack(ModItems.ingotZinc), 1f);

        //Dust2Ingot
        GameRegistry.addSmelting(ModItems.dustAdamantine, new ItemStack(ModItems.ingotAdamantine), 0.25F);
        GameRegistry.addSmelting(ModItems.dustAlduorite, new ItemStack(ModItems.ingotAlduorite), 0.25F);
        GameRegistry.addSmelting(ModItems.dustAmordrine, new ItemStack(ModItems.ingotAmordrine), 0.25F);
        GameRegistry.addSmelting(ModItems.dustAngmallen, new ItemStack(ModItems.ingotAngmallen), 0.25F);
        GameRegistry.addSmelting(ModItems.dustAstralSilver, new ItemStack(ModItems.ingotAstralSilver), 0.25F);
        GameRegistry.addSmelting(ModItems.dustAtlarus, new ItemStack(ModItems.ingotAtlarus), 0.25F);
        GameRegistry.addSmelting(ModItems.dustBlackSteel, new ItemStack(ModItems.ingotBlackSteel), 0.25F);
        GameRegistry.addSmelting(ModItems.dustBrass, new ItemStack(ModItems.ingotBrass), 0.25F);
        GameRegistry.addSmelting(ModItems.dustBronze, new ItemStack(ModItems.ingotBronze), 0.25F);
        GameRegistry.addSmelting(ModItems.dustCarmot, new ItemStack(ModItems.ingotCarmot), 0.25F);
        GameRegistry.addSmelting(ModItems.dustCelenegil, new ItemStack(ModItems.ingotCelenegil), 0.25F);
        GameRegistry.addSmelting(ModItems.dustCeruclase, new ItemStack(ModItems.ingotCeruclase), 0.25F);
        GameRegistry.addSmelting(ModItems.dustCopper, new ItemStack(ModItems.ingotCopper), 0.25F);
        GameRegistry.addSmelting(ModItems.dustDamascusSteel, new ItemStack(ModItems.ingotDamascusSteel), 0.25F);
        GameRegistry.addSmelting(ModItems.dustDeepIron, new ItemStack(ModItems.ingotDeepIron), 0.25F);
        GameRegistry.addSmelting(ModItems.dustDesichalkos, new ItemStack(ModItems.ingotDesichalkos), 0.25F);
        GameRegistry.addSmelting(ModItems.dustElectrum, new ItemStack(ModItems.ingotElectrum), 0.25F);
        GameRegistry.addSmelting(ModItems.dustEximite, new ItemStack(ModItems.ingotEximite), 0.25F);
        GameRegistry.addSmelting(ModItems.dustHaderoth, new ItemStack(ModItems.ingotHaderoth), 0.25F);
        GameRegistry.addSmelting(ModItems.dustHepatizon, new ItemStack(ModItems.ingotHepatizon), 0.25F);
        GameRegistry.addSmelting(ModItems.dustIgnatius, new ItemStack(ModItems.ingotIgnatius), 0.25F);
        GameRegistry.addSmelting(ModItems.dustInfuscolium, new ItemStack(ModItems.ingotInfuscolium), 0.25F);
        GameRegistry.addSmelting(ModItems.dustInolashite, new ItemStack(ModItems.ingotInolashite), 0.25F);
        GameRegistry.addSmelting(ModItems.dustKalendrite, new ItemStack(ModItems.ingotKalendrite), 0.25F);
        GameRegistry.addSmelting(ModItems.dustLemurite, new ItemStack(ModItems.ingotLemurite), 0.25F);
        GameRegistry.addSmelting(ModItems.dustManganese, new ItemStack(ModItems.ingotManganese), 0.25F);
        GameRegistry.addSmelting(ModItems.dustMeutoite, new ItemStack(ModItems.ingotMeutoite), 0.25F);
        GameRegistry.addSmelting(ModItems.dustMidasium, new ItemStack(ModItems.ingotMidasium), 0.25F);
        GameRegistry.addSmelting(ModItems.dustMithril, new ItemStack(ModItems.ingotMithril), 0.25F);
        GameRegistry.addSmelting(ModItems.dustOrichalcum, new ItemStack(ModItems.ingotOrichalcum), 0.25F);
        GameRegistry.addSmelting(ModItems.dustOureclase, new ItemStack(ModItems.ingotOureclase), 0.25F);
        GameRegistry.addSmelting(ModItems.dustPlatinum, new ItemStack(ModItems.ingotPlatinum), 0.25F);
        GameRegistry.addSmelting(ModItems.dustPrometheum, new ItemStack(ModItems.ingotPrometheum), 0.25F);
        GameRegistry.addSmelting(ModItems.dustQuickSilver, new ItemStack(ModItems.ingotQuickSilver), 0.25F);
        GameRegistry.addSmelting(ModItems.dustRubracium, new ItemStack(ModItems.ingotRubracium), 0.25F);
        GameRegistry.addSmelting(ModItems.dustSanguinite, new ItemStack(ModItems.ingotSanguinite), 0.25F);
        GameRegistry.addSmelting(ModItems.dustShadowIron, new ItemStack(ModItems.ingotShadowIron), 0.25F);
        GameRegistry.addSmelting(ModItems.dustShadowSteel, new ItemStack(ModItems.ingotShadowSteel), 0.25F);
        GameRegistry.addSmelting(ModItems.dustSilver, new ItemStack(ModItems.ingotSilver), 0.25F);
        GameRegistry.addSmelting(ModItems.dustSteel, new ItemStack(ModItems.ingotSteel), 0.25F);
        GameRegistry.addSmelting(ModItems.dustTartarite, new ItemStack(ModItems.ingotTartarite), 0.25F);
        GameRegistry.addSmelting(ModItems.dustTin, new ItemStack(ModItems.ingotTin), 0.25F);
        GameRegistry.addSmelting(ModItems.dustVulcanite, new ItemStack(ModItems.ingotVulcanite), 0.25F);
        GameRegistry.addSmelting(ModItems.dustVyroxeres, new ItemStack(ModItems.ingotVyroxeres), 0.25F);
        GameRegistry.addSmelting(ModItems.dustZinc, new ItemStack(ModItems.ingotZinc), 0.25F);











        
    }

}
