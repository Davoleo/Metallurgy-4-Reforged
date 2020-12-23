/*==============================================================================
 = Class: OreDictHandler
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2020.
 =============================================================================*/

package it.hurts.metallurgy_reforged.handler;

import com.google.common.base.CaseFormat;
import it.hurts.metallurgy_reforged.block.BlockTypes;
import it.hurts.metallurgy_reforged.block.ModBlocks;
import it.hurts.metallurgy_reforged.config.RegistrationConfig;
import it.hurts.metallurgy_reforged.item.ModItems;
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.util.Utils;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.registries.IForgeRegistryEntry;

public class OreDictHandler {

    public static void init()
    {
        ModMetals.metalMap.forEach((name, metal) -> {
            //Items
            if (RegistrationConfig.categoryItems.enableMetalDusts)
                OreDictionary.registerOre("dust" + CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, metal.toString()), metal.getDust());

            OreDictionary.registerOre("ingot" + CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, metal.toString()), metal.getIngot());

            if (RegistrationConfig.categoryItems.enableMetalNuggets)
                OreDictionary.registerOre("nugget" + CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, metal.toString()), metal.getNugget());

            //Blocks
            if (!metal.isAlloy())
                OreDictionary.registerOre("ore" + CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, metal.toString()), metal.getOre());

            if (RegistrationConfig.categoryBlocks.enableRawMetalBlocks)
                OreDictionary.registerOre("block" + CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, metal.toString()), metal.getBlock(BlockTypes.BLOCK));
        });

        //Additional oreDict values
        //Make Tar behave like a slimeball
        OreDictionary.registerOre("slimeball", ModItems.tar);

        //Other Misc Item OreDict Keys
        OreDictionary.registerOre("dustGold", ModItems.dustGold);
        OreDictionary.registerOre("dustIron", ModItems.dustIron);
        OreDictionary.registerOre("dustBitumen", ModItems.bitumen);
        OreDictionary.registerOre("globTar", ModItems.tar);
        OreDictionary.registerOre("dustPotash", ModItems.potash);
        OreDictionary.registerOre("dustSulfur", ModItems.sulfur);
        OreDictionary.registerOre("dustThermite", ModItems.dustThermite);
        OreDictionary.registerOre("dustPhosphorus", ModItems.phosphorus);

        //Misc Blocks
        OreDictionary.registerOre("oreSulfur", ModBlocks.oreSulfur);
        OreDictionary.registerOre("orePhosphorus", ModBlocks.orePhosphorite);
        OreDictionary.registerOre("oreTar", ModBlocks.oreTar);
        OreDictionary.registerOre("orePotash", ModBlocks.orePotash);
        OreDictionary.registerOre("blockCharcoal", ModBlocks.blockCharcoal);
        OreDictionary.registerOre("blockBitumen", ModBlocks.blockBitumen);
        OreDictionary.registerOre("blockSulfur", ModBlocks.blockSulfur);
    }

    public static void initOreDict(IForgeRegistryEntry.Impl<?> itemOrBlock)
    {
        String[] words = itemOrBlock.getRegistryName().getPath().split("_");
        String oreName = "ore";
        for (String word : words)
        {
            if (!"ore".equals(word))
                oreName = oreName.concat(Utils.capitalize(word));
        }

        if (itemOrBlock instanceof Block)
            OreDictionary.registerOre(oreName, (Block) itemOrBlock);
        else if (itemOrBlock instanceof Item)
            OreDictionary.registerOre(oreName, (Item) itemOrBlock);
    }

}
