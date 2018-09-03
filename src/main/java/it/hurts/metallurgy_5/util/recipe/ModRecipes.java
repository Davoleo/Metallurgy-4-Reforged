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
    }

}
