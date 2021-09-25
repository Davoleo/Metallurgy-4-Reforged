/*==============================================================================
 = Class: IntegrationChisel
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.integration;

import com.google.common.base.CaseFormat;
import it.hurts.metallurgy_reforged.block.BlockMetal;
import it.hurts.metallurgy_reforged.block.BlockTypes;
import it.hurts.metallurgy_reforged.block.ModBlocks;
import it.hurts.metallurgy_reforged.material.ModMetals;
import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.event.FMLInterModComms;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class IntegrationChisel {

	public static final String MODID = "chisel";

	public static void init()
	{
		ModMetals.metalMap.forEach((name, metal) -> {
			String pascalName = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, name);

			for (BlockMetal decoBlock : metal.getBlocks())
			{
				if (ForgeRegistries.BLOCKS.containsKey(decoBlock.getRegistryName()) && decoBlock.getType() != BlockTypes.BLOCK)
					addChiselVariation(pascalName + "Decor", decoBlock);
			}
		});

		ModBlocks.miscBlocks.forEach(block -> {
			if (block instanceof BlockMetal)
			{
				if (((BlockMetal) block).getType() != BlockTypes.BLOCK)
				{
					String group = block.getRegistryName().getPath().contains("iron") ? "IronDecor" : "GoldDecor";
					addChiselVariation(group, block);
				}
			}
		});
	}

	private static void addChiselVariation(String chiselGroup, Block block)
	{
		//Build Chisel Variation Information compound Tag
		NBTTagCompound variationTag = new NBTTagCompound();
		variationTag.setString("group", chiselGroup);
		variationTag.setString("block", String.valueOf(block.getRegistryName()));

		FMLInterModComms.sendMessage(MODID, "add_variation", variationTag);
	}

}
