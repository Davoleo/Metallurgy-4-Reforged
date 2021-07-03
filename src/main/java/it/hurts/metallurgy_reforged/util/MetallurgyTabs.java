/*==============================================================================
 = Class: MetallurgyTabs
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.util;

import it.hurts.metallurgy_reforged.Metallurgy;
import it.hurts.metallurgy_reforged.block.BlockTypes;
import it.hurts.metallurgy_reforged.block.ModBlocks;
import it.hurts.metallurgy_reforged.config.RegistrationConfig;
import it.hurts.metallurgy_reforged.item.ModItems;
import it.hurts.metallurgy_reforged.item.tool.EnumTools;
import it.hurts.metallurgy_reforged.material.Metal;
import it.hurts.metallurgy_reforged.material.ModMetals;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

@SuppressWarnings("OptionalUsedAsFieldOrParameterType")
public class MetallurgyTabs extends CreativeTabs {

	//TODO : Fix Creative tab icon ticking

	public static final CreativeTabs tabArmor, tabBlock, tabDust, tabFluid, tabIngot, tabNugget, tabSpecial, tabOre, tabTool;

	private static final Optional<BlockTypes> firstEnabledBlockType;
	private static final Optional<EnumTools> firstEnabledToolType;

	static
	{
		firstEnabledBlockType = Arrays.stream(BlockTypes.values()).filter(BlockTypes::isEnabled).findFirst();
		firstEnabledToolType = Arrays.stream(EnumTools.values()).filter(EnumTools::isEnabled).findFirst();

		tabArmor = RegistrationConfig.categoryItems.enableMetalArmorSets ? new MetallurgyTabs(0, "armors") : null;
		tabBlock = firstEnabledBlockType.isPresent() ? new MetallurgyTabs(1, "blocks") : null;
		tabDust = new MetallurgyTabs(2, "dusts");
		tabFluid = new MetallurgyTabs(3, "fluids");
		tabIngot = new MetallurgyTabs(4, "ingots");
		tabNugget = RegistrationConfig.categoryItems.enableMetalNuggets ? new MetallurgyTabs(5, "nuggets") : null;
		tabSpecial = new MetallurgyTabs(6, "special");
		tabOre = new MetallurgyTabs(7, "ores");
		tabTool = firstEnabledToolType.isPresent() ? new MetallurgyTabs(8, "tools") : null;
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

	//Suppressing isPresent check because the method will never be called if the creative tab is not initialized above
	@SuppressWarnings("OptionalGetWithoutIsPresent")
	@SideOnly(Side.CLIENT)
	@Nonnull
	@Override
	public ItemStack createIcon()
	{

		if (type == 6)
			return new ItemStack(ModBlocks.crusher);

		Optional<Metal> firstAvailableMetal = ModMetals.metalMap.values().stream().findFirst();

		if (!firstAvailableMetal.isPresent())
			return ItemStack.EMPTY;

		switch (type)
		{
            case 0:
                return new ItemStack(firstAvailableMetal.get().getArmorPiece(EntityEquipmentSlot.CHEST));
            case 1:
                return new ItemStack(firstAvailableMetal.get().getBlock(firstEnabledBlockType.get()));
            case 2:
                return new ItemStack(RegistrationConfig.categoryItems.enableMetalDusts ? firstAvailableMetal.get().getDust() : ModItems.PHOSPHORUS);
            case 3:
                return FluidUtil.getFilledBucket(firstAvailableMetal.get().getMolten().getFluidStack());
            case 4:
                return new ItemStack(firstAvailableMetal.get().getIngot());
            case 5:
                return new ItemStack(firstAvailableMetal.get().getNugget());
            case 7:
                return ModMetals.ADAMANTINE != null ? new ItemStack(Objects.requireNonNull(ModMetals.ADAMANTINE.getOre())) : ItemStack.EMPTY;
            case 8:
                return new ItemStack(firstAvailableMetal.get().getTool(firstEnabledToolType.get()));
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
