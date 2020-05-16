/*
 * -------------------------------------------------------------------------------------------------------
 * Class: ItemOreDetector
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2020.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.item.gadget;

import com.google.common.base.CaseFormat;
import com.google.common.collect.Lists;
import it.hurts.metallurgy_reforged.handler.GadgetsHandler;
import it.hurts.metallurgy_reforged.item.ItemExtra;
import it.hurts.metallurgy_reforged.material.Metal;
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.util.ItemUtils;
import it.hurts.metallurgy_reforged.util.MetallurgyTabs;
import it.hurts.metallurgy_reforged.util.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class ItemOreDetector extends ItemExtra {

	public ItemOreDetector()
	{
		super("ore_detector", MetallurgyTabs.tabSpecial, "gadget");

		this.setMaxStackSize(1);
	}

	@Override
	public void addInformation(@Nonnull ItemStack stack, @Nullable World worldIn, @Nonnull List<String> tooltip, @Nonnull ITooltipFlag flagIn)
	{
		List<Metal> metals = getDetectorMetals(stack);
		for (int i = 0; i < metals.size(); i++)
			tooltip.add(TextFormatting.DARK_AQUA + "Metal " + i + ": " + CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, metals.get(i).toString()));
	}

	@Override
	public int getMaxDamage(@Nonnull ItemStack stack)
	{
		return getDetectorMetals(stack).size() * 100;
	}

	@Override
	public void onUsingTick(@Nonnull ItemStack stack, @Nonnull EntityLivingBase player, int count)
	{
		if (stack.getMaxDamage() == 0 || !stack.hasTagCompound())
			return;

		if (count % 4 == 0)
		{
			stack.damageItem(1, player);

			if (stack.getItemDamage() >= stack.getMaxDamage())
			{
				for (int i = 0; i < 3; i++)
				{
					stack.removeSubCompound("ingot_" + i);
				}
			}
		}
	}

	@Override
	public int getRGBDurabilityForDisplay(@Nonnull ItemStack stack)
	{
		List<Metal> metals = getDetectorMetals(stack);

		switch (metals.size())
		{
			case 1:
				return metals.get(0).getStats().getColorHex();
			case 2:
			case 3:
				return getColorShiftFromMetals(metals);
			default:
				return 0xFFFFFF;

		}
	}

	private int getColorShiftFromMetals(List<Metal> metals)
	{

		float[][] metalColors = new float[][]{
				metals.get(0).getStats().getColorRGBValues(),
				metals.get(1).getStats().getColorRGBValues(),
				metals.size() > 2 ? metals.get(2).getStats().getColorRGBValues() : new float[]{1, 1, 1},
				};

		double factor = 0.5f * (Math.sin(6.0f * Math.toRadians(GadgetsHandler.ticks + Minecraft.getMinecraft().getRenderPartialTicks())) + 1.0f);

		if (metals.size() < 3)
		{
			return Utils.intColorFromRGB(
					(int) (255 * (metalColors[0][0] * factor + metalColors[1][0] * (1.0 - factor))),
					(int) (255 * (metalColors[0][1] * factor + metalColors[1][1] * (1.0 - factor))),
					(int) (255 * (metalColors[0][2] * factor + metalColors[1][2] * (1.0 - factor)))
			);
		}
		else
		{
			int i = ((int) (GadgetsHandler.ticks % 3));
			int j = i != 2 ? i + 1 : 0;

			return Utils.intColorFromRGB(
					(int) (255 * (metalColors[i][0] * factor + metalColors[j][0] * (1.0 - factor))),
					(int) (255 * (metalColors[i][1] * factor + metalColors[j][1] * (1.0 - factor))),
					(int) (255 * (metalColors[i][2] * factor + metalColors[j][2] * (1.0 - factor)))
			);
		}
	}

	public static void addIngotsToDetector(ItemStack detector, List<ItemStack> ingots)
	{
		NBTTagCompound nbt = detector.getTagCompound();

		if (nbt == null)
			nbt = new NBTTagCompound();

		for (int i = 0; i < ingots.size(); i++)
		{
			Metal metal = ItemUtils.getMetalFromOreDictStack(ingots.get(i));
			String metalName = metal.toString();
			nbt.setString("metal_" + (i + 1), metalName);
		}

		detector.setTagCompound(nbt);
	}

	/**
	 * Returns the current Scanner Metals
	 *
	 * @param detector The queried scanner
	 *
	 * @return The Hash Set of the ores the scanner has in the NBT Tag
	 */
	public static List<Metal> getDetectorMetals(ItemStack detector)
	{
		NBTTagCompound nbt = detector.getTagCompound();

		List<Metal> metalSet = Lists.newArrayList();

		if (nbt != null && !nbt.isEmpty())
		{
			for (int i = 1; i <= 3; i++)
			{
				String metalName = nbt.getString("metal_" + i);
				Metal metal = ModMetals.metalMap.get(metalName);
				if (metal != null)
					metalSet.add(metal);
			}
		}

		return metalSet;
	}

}
