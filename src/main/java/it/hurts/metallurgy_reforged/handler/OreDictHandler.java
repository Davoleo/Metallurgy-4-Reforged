/*
 * -------------------------------------------------------------------------------------------------------
 * Class: OreDictHandler
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2020.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.handler;

import it.hurts.metallurgy_reforged.item.ModItems;
import it.hurts.metallurgy_reforged.util.Utils;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.registries.IForgeRegistryEntry;

public class OreDictHandler {

	public static void init()
	{

		//Additional oreDict values
		OreDictionary.registerOre("globTar", ModItems.tar);
		OreDictionary.registerOre("slimeball", ModItems.tar);
	}

	// TODO: 30/03/2020 Register OreDictionary key for every item and block
	public static void initOreDict(IForgeRegistryEntry.Impl<?> itemOrBlock)
	{
		String[] words = itemOrBlock.getRegistryName().getPath().split("_");
		String oreName = "ore";
		for (String word : words)
		{
			if (!word.equals("ore"))
				oreName = oreName.concat(Utils.capitalize(word));
		}

		if (itemOrBlock instanceof Block)
			OreDictionary.registerOre(oreName, (Block) itemOrBlock);
		else if (itemOrBlock instanceof Item)
			OreDictionary.registerOre(oreName, (Item) itemOrBlock);
	}

}
