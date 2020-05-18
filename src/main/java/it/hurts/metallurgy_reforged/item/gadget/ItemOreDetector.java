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
import it.hurts.metallurgy_reforged.item.ItemExtra;
import it.hurts.metallurgy_reforged.material.Metal;
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.util.ItemUtils;
import it.hurts.metallurgy_reforged.util.MetallurgyTabs;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
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

	@Nonnull
	@Override
	public ActionResult<ItemStack> onItemRightClick(@Nonnull World worldIn, @Nonnull EntityPlayer playerIn, @Nonnull EnumHand handIn)
	{
		ItemStack stack = playerIn.getHeldItem(handIn);

		if (getMaxDamage(stack) > 0)
		{
			if (stack.getItemDamage() >= getMaxDamage(stack))
			{
				ItemStack copy = new ItemStack(this);
				playerIn.setHeldItem(handIn, copy);
			}
			else
			{
				stack.damageItem(1, playerIn);
			}
		}

		return new ActionResult<>(EnumActionResult.PASS, stack);
	}


	@Override
	public boolean shouldCauseReequipAnimation(@Nonnull ItemStack oldStack, @Nonnull ItemStack newStack, boolean slotChanged)
	{
		return slotChanged;
	}


	@Override
	public int getRGBDurabilityForDisplay(@Nonnull ItemStack stack)
	{
		List<Metal> metals = getDetectorMetals(stack);

		if (!metals.isEmpty())
		{
			return metals.get(0).getStats().getColorHex();
		}
		else
		{
			return 0xFFFFFF;
		}
	}

	//private int getColorShiftFromMetals(List<Metal> metals)
	//{
	//	float[][] metalColors = new float[metals.size()][3];
	//
	//	for (int i = 0; i < metalColors.length; i++)
	//	{
	//		metalColors[i] = metals.get(i).getStats().getColorRGBValues();
	//	}
	//
	//	double factor = 0.5F * (Math.sin(6.0F * Math.toRadians(GadgetsHandler.ticks + Minecraft.getMinecraft().getRenderPartialTicks())) + 1.0F);
	//
	//	Math.rint(1237);
	//	//-1 0 1
	//	// TODO: 16/05/2020 Fix 3 Metals Color Shift
	//	if (factor == 1 || factor == 0)
	//	{
	//		System.out.println("test");
	//		colorId1 = colorId1 < metals.size() - 1 ? colorId1 + 1 : 0;
	//		colorId2 = colorId2 < metals.size() - 1 ? colorId2 + 1 : 0;
	//	}
	//
	//	return Utils.intColorFromRGB(
	//			(int) (255 * (metalColors[colorId1][0] * factor + metalColors[colorId2][0] * (1.0 - factor) + metalColors[colorId1][0] * factor)),
	//			(int) (255 * (metalColors[colorId1][1] * factor + metalColors[colorId2][1] * (1.0 - factor))),
	//			(int) (255 * (metalColors[colorId1][2] * factor + metalColors[colorId2][2] * (1.0 - factor)))
	//	);
	//}

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
	 * @param detector The queried scanner
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