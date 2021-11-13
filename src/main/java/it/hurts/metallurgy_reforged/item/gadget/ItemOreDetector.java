/*==============================================================================
 = Class: ItemOreDetector
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.item.gadget;

import com.google.common.base.CaseFormat;
import com.google.common.collect.Lists;
import it.hurts.metallurgy_reforged.handler.GadgetsHandler;
import it.hurts.metallurgy_reforged.item.ItemExtra;
import it.hurts.metallurgy_reforged.material.Metal;
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.sound.ModSounds;
import it.hurts.metallurgy_reforged.util.ItemUtils;
import it.hurts.metallurgy_reforged.util.MetallurgyTabs;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;

public class ItemOreDetector extends ItemExtra {

	public static int indexColor;

	//Updated on client-side only
	private boolean[] leds = new boolean[3];

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
			tooltip.add(TextFormatting.GRAY + "Metal " + i + ": " + CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, metals.get(i).toString()));
	}

	@Override
	public int getMaxDamage(@Nonnull ItemStack stack)
	{
		return getDetectorMetals(stack).size() * 25;
	}

	@Nonnull
	@Override
	public ActionResult<ItemStack> onItemRightClick(@Nonnull World worldIn, @Nonnull EntityPlayer playerIn, @Nonnull EnumHand handIn)
	{
		ItemStack stack = playerIn.getHeldItem(handIn);

		if (getDetectorMetals(stack).isEmpty())
			return new ActionResult<>(EnumActionResult.FAIL, stack);

		//Sends contained metals in chat (DEBUG PURPOSES)
		//ItemOreDetector.getDetectorMetals(stack).forEach(metal ->
		//		playerIn.sendMessage(new TextComponentString(metal.toString() + ": " + metal.getStats().getTemperature())));

		if (playerIn.getCooldownTracker().getCooldown(this, 0) == 0)
		{
			playerIn.playSound(ModSounds.METAL_DETECTOR_PING, 1F, 1F);
			//Detector will detect ores while on cooldown
			playerIn.getCooldownTracker().setCooldown(this, 100);

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
			return getColorShiftFromMetals(metals);
		}
		else
		{
			return 0xFFFFFF;
		}
	}

	private int getColorShiftFromMetals(List<Metal> metals)
	{
		float[][] metalColors = new float[metals.size()][3];

		for (int i = 0; i < metalColors.length; i++)
		{
			metalColors[i] = metals.get(i).getStats().getColorRGBValues();
		}

		int indexFrom = indexColor % metals.size();
		int indexTo = indexFrom < (metals.size() - 1) ? indexFrom + 1 : 0;

		double factor = GadgetsHandler.prevFactorToUse;

		int r = (int) (255 * (metalColors[indexTo][0] * factor + metalColors[indexFrom][0] * (1.0 - factor)));
		int g = (int) (255 * (metalColors[indexTo][1] * factor + metalColors[indexFrom][1] * (1.0 - factor)));
		int b = (int) (255 * (metalColors[indexTo][2] * factor + metalColors[indexFrom][2] * (1.0 - factor)));

		return MathHelper.rgb(r, g, b);
	}

	//Model Highlight ------------------------

	@SideOnly(Side.CLIENT)
	public void setLEDs(boolean[] vals)
	{
		this.leds = vals;
	}

	@SideOnly(Side.CLIENT)
	public boolean isLEDLit(int index)
	{
		return leds[index];
	}

	@SideOnly(Side.CLIENT)
	public void resetLEDs()
	{
		Arrays.fill(leds, false);
	}

	//Stack Utilities ------------------------

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
