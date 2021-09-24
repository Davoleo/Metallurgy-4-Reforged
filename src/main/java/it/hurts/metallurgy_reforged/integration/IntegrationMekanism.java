/*==============================================================================
 = Class: IntegrationMekanism
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.integration;

import it.hurts.metallurgy_reforged.material.ModMetals;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.event.FMLInterModComms;

public class IntegrationMekanism {

	public static final String MODID = "mekanism";

	public static void init()
	{
		NBTTagCompound lutetiumEnrichmentPatch = new NBTTagCompound();
		assert ModMetals.LUTETIUM.getOre() != null;
		lutetiumEnrichmentPatch.setTag("input", new ItemStack(ModMetals.LUTETIUM.getOre()).writeToNBT(new NBTTagCompound()));
		lutetiumEnrichmentPatch.setTag("output", new ItemStack(ModMetals.LUTETIUM.getDust(), 2).writeToNBT(new NBTTagCompound()));
		FMLInterModComms.sendMessage(MODID, "EnrichmentChamberRecipe", lutetiumEnrichmentPatch);
	}

}
