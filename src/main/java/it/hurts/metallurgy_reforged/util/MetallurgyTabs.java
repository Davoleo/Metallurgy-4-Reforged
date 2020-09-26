/*
 * -------------------------------------------------------------------------------------------------------
 * Class: MetallurgyTabs
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 * Copyright (c) 2020.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.util;

import it.hurts.metallurgy_reforged.Metallurgy;
import it.hurts.metallurgy_reforged.block.BlockTypes;
import it.hurts.metallurgy_reforged.block.ModBlocks;
import it.hurts.metallurgy_reforged.material.Metal;
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.model.EnumTools;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import java.util.Objects;

public class MetallurgyTabs extends CreativeTabs {

	//TODO : Fix Creative tab icon ticking

	public static final CreativeTabs tabArmor, tabBlock, tabDust, tabFluid, tabIngot, tabNugget, tabSpecial, tabOre, tabTool;

	static
	{
		tabArmor = new MetallurgyTabs(0, "armors");
		tabBlock = new MetallurgyTabs(1, "blocks");
		tabDust = new MetallurgyTabs(2, "dusts");
		tabFluid = new MetallurgyTabs(3, "fluids");
		tabIngot = new MetallurgyTabs(4, "ingots");
		tabNugget = new MetallurgyTabs(5, "nuggets");
		tabSpecial = new MetallurgyTabs(6, "special");
		tabOre = new MetallurgyTabs(7, "ores");
		tabTool = new MetallurgyTabs(8, "tools");
	}

	private final int type;

	public MetallurgyTabs(int type, String name)
	{
		super(getUName(name));
		this.type = type;
	}

	private static String getUName(String name)
	{
		return Metallurgy.MODID + "." + name;
	}

	@SideOnly(Side.CLIENT)
	@Nonnull
	@Override
	public ItemStack getTabIconItem()
	{

		if (type == 6)
			return new ItemStack(ModBlocks.crusher);

		Metal adamantine = ModMetals.ADAMANTINE;

		if (adamantine == null)
			return ItemStack.EMPTY;

		switch (type)
		{
			case 0:
				return new ItemStack(adamantine.getArmorPiece(EntityEquipmentSlot.CHEST));
			case 1:
				return new ItemStack(adamantine.getBlock(BlockTypes.BLOCK));
			case 2:
				return new ItemStack(adamantine.getDust());
			case 3:
				return FluidUtil.getFilledBucket(ModMetals.ADAMANTINE.getMolten().getFluidStack());
			case 4:
				return new ItemStack(adamantine.getIngot());
			case 5:
				return new ItemStack(adamantine.getNugget());
			case 7:
				return new ItemStack(Objects.requireNonNull(adamantine.getOre()));
			case 8:
				return new ItemStack(adamantine.getTool(EnumTools.PICKAXE));
			default:
				return ItemStack.EMPTY;
		}
	}

	@Override
	public void displayAllRelevantItems(@Nonnull NonNullList<ItemStack> p_78018_1_)
	{
		if (type == 3)
		{
			ModMetals.metalMap.forEach((s, metal) -> {
				if (FluidRegistry.getFluidName(metal.getMolten()) != null)
					p_78018_1_.add(FluidUtil.getFilledBucket(metal.getMolten().getFluidStack()));
			});
		}
		else
			super.displayAllRelevantItems(p_78018_1_);
	}

}
