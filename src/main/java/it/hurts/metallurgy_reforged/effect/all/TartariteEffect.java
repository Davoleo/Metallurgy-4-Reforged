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
import it.hurts.metallurgy_reforged.material.Metal;
import it.hurts.metallurgy_reforged.material.ModMetals;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class TartariteEffect extends BaseMetallurgyEffect {

	public TartariteEffect()
	{
		super(ModMetals.TARTARITE);
	}

	@Nonnull
	@Override
	public EnumEffectCategory getCategory()
	{
		return EnumEffectCategory.ALL;
	}

	public static void setParagon(ItemStack tartarStack, @Nonnull Metal metal)
	{
		//Get or Initialize NBTTag
		NBTTagCompound compound = tartarStack.getTagCompound();
		if (compound == null)
			compound = new NBTTagCompound();

		compound.setString("paragon", metal.toString());

		float durabilityRatio = (tartarStack.getMaxDamage() + metal.getStats().getToolStats().getMaxUses()) / (float) tartarStack.getMaxDamage();
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
