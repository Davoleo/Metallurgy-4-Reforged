/*==============================================================================
 = Class: TartariteEffect
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.effect.all;

import it.hurts.metallurgy_reforged.effect.BaseMetallurgyEffect;
import it.hurts.metallurgy_reforged.effect.EnumEffectCategory;
import it.hurts.metallurgy_reforged.effect.MetallurgyEffects;
import it.hurts.metallurgy_reforged.item.tool.EnumTools;
import it.hurts.metallurgy_reforged.material.Metal;
import it.hurts.metallurgy_reforged.material.ModMetals;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import org.apache.commons.lang3.ArrayUtils;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class TartariteEffect extends BaseMetallurgyEffect {

	public TartariteEffect()
	{
		super(ModMetals.TARTARITE);
		setupModelOverrides((stack, worldIn, entityIn) -> {
			if (stack.getTagCompound() == null)
				return 0F;

			Metal paragonMetal = getParagonMetal(stack);
			if (paragonMetal == null)
				return 0F;

			switch (paragonMetal.toString())
			{
				case "celenegil":
					return stack.getTagCompound().getBoolean("glory_seeker") ? 1F : 0F;
			}

			return 0F;
		});
	}

	@Nonnull
	@Override
	public EnumEffectCategory getCategory()
	{
		return EnumEffectCategory.ALL;
	}

	@Override
	public void inventoryTick(@Nonnull ItemStack stack, @Nonnull World worldIn, @Nonnull Entity entityIn, int itemSlot, boolean isSelected)
	{
		Metal metal = getParagonMetal(stack);
		if (metal == null)
			return;

		EnumTools toolType = EnumTools.byInstance(stack.getItem());

		if (toolType != null)
		{
			MetallurgyEffects.effects.row(metal).forEach((category, effect) -> {
				if (ArrayUtils.contains(category.getTools(), toolType))
					effect.inventoryTick(stack, worldIn, entityIn, itemSlot, isSelected);
			});
		}
		else
			MetallurgyEffects.effects.get(metal, EnumEffectCategory.ARMOR)
					.inventoryTick(stack, worldIn, entityIn, itemSlot, isSelected);
	}

	@Override
	public void rightClickHandler(@Nonnull World worldIn, @Nonnull EntityPlayer playerIn, @Nonnull EnumHand handIn)
	{
		ItemStack stack = playerIn.getHeldItem(handIn);

		Metal metal = getParagonMetal(stack);
		if (metal == null)
			return;

		EnumTools toolType = EnumTools.byInstance(stack.getItem());
		if (toolType == null)
			return;

		MetallurgyEffects.effects.row(metal).forEach((category, effect) -> {
			if (ArrayUtils.contains(category.getTools(), toolType))
				effect.rightClickHandler(worldIn, playerIn, handIn);
		});
	}

	public static void setParagon(ItemStack tartarStack, @Nonnull Metal metal)
	{
		//Get or Initialize NBTTag
		NBTTagCompound compound = tartarStack.getTagCompound();
		if (compound == null)
			compound = new NBTTagCompound();

		compound.setString("paragon", metal.toString());

		int infusedDurability;
		if (metal.getStats().getToolStats() != null)
			infusedDurability = metal.getStats().getToolStats().getMaxUses();
		else
			infusedDurability = metal.getStats().getArmorStats().getDurability() * 16; // *16 -> Chestplate value
		//(infusedDurability >= 1000 ? 3000 : 750)

		float durabilityRatio = (
				((float) tartarStack.getMaxDamage() + infusedDurability)
						/ (tartarStack.getMaxDamage() - infusedDurability));
		compound.setFloat("durability_boost", durabilityRatio);

		tartarStack.setTagCompound(compound);
	}

	@Nullable
	public static Metal getParagonMetal(ItemStack tartarStack)
	{
		NBTTagCompound compound = tartarStack.getTagCompound();
		if (compound != null)
			return ModMetals.metalMap.get(compound.getString("paragon"));
		else
			return null;
	}

}
