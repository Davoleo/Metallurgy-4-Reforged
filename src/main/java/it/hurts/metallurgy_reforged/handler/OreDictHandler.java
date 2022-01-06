/*==============================================================================
 = Class: OreDictHandler
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.handler;

import com.google.common.base.CaseFormat;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import it.hurts.metallurgy_reforged.block.BlockTypes;
import it.hurts.metallurgy_reforged.block.ModBlocks;
import it.hurts.metallurgy_reforged.config.RegistrationConfig;
import it.hurts.metallurgy_reforged.item.ModItems;
import it.hurts.metallurgy_reforged.material.Metal;
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.util.Utils;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.registries.IForgeRegistryEntry;

import java.util.Arrays;

public class OreDictHandler {

	public static final Multimap<Metal, ItemStack> ALLOYING_CACHE = HashMultimap.create();

	public static void init()
	{
		ModMetals.metalMap.forEach((name, metal) -> {

			String pascalMetal = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, name);

			//Items
			if (RegistrationConfig.categoryItems.enableMetalDusts)
				OreDictionary.registerOre("dust" + pascalMetal, metal.getDust());

			ALLOYING_CACHE.put(metal, new ItemStack(metal.getIngot()));
			ALLOYING_CACHE.put(metal, new ItemStack(metal.getDust()));
			OreDictionary.registerOre("ingot" + pascalMetal, metal.getIngot());

			if (RegistrationConfig.categoryItems.enableMetalNuggets)
				OreDictionary.registerOre("nugget" + pascalMetal, metal.getNugget());

			//Blocks
			if (!metal.isAlloy())
				OreDictionary.registerOre("ore" + pascalMetal, metal.getOre());

			if (RegistrationConfig.categoryBlocks.enableRawMetalBlocks)
				OreDictionary.registerOre("block" + pascalMetal, metal.getBlock(BlockTypes.BLOCK));
		});

        //Additional oreDict values
        //Make Tar behave like a slimeball
        OreDictionary.registerOre("slimeball", ModItems.TAR);

        //Other Misc Item OreDict Keys
        OreDictionary.registerOre("dustGold", ModItems.GOLD_DUST);
        OreDictionary.registerOre("dustIron", ModItems.IRON_DUST);
        OreDictionary.registerOre("dustBitumen", ModItems.BITUMEN);
        OreDictionary.registerOre("globTar", ModItems.TAR);
        OreDictionary.registerOre("dustPotash", ModItems.POTASH);
        OreDictionary.registerOre("dustSulfur", ModItems.SULFUR);
        OreDictionary.registerOre("dustThermite", ModItems.THERMITE_DUST);
        OreDictionary.registerOre("dustPhosphorus", ModItems.PHOSPHORUS);

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

	public static void populateOredictCache()
	{
		ModMetals.metalMap.forEach((name, metal) -> {
			//No need to remove metallurgy duplicates as HashMultimap does not store them as it's based on SetMultimap
			ALLOYING_CACHE.putAll(metal, getOredictedStacksFromMetalAndPrefix("ingot", metal));
			ALLOYING_CACHE.putAll(metal, getOredictedStacksFromMetalAndPrefix("dust", metal));
		});
	}

	public static NonNullList<ItemStack> getOredictedStacksFromMetalAndPrefix(String prefix, Metal metal)
	{
		String camelMetal = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, metal.toString());
		return OreDictionary.getOres(prefix + camelMetal);
	}

	public static boolean containsPrefix(ItemStack stack, String prefix)
	{
		return Arrays.stream(OreDictionary.getOreIDs(stack)).anyMatch(id -> OreDictionary.getOreName(id).startsWith(prefix));
	}

}
