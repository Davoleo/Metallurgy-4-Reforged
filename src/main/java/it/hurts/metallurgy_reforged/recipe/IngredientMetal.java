/*==============================================================================
 = Class: IngredientMetal
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.recipe;

import com.google.common.base.CaseFormat;
import com.google.gson.JsonObject;
import it.hurts.metallurgy_reforged.material.Metal;
import it.hurts.metallurgy_reforged.material.ModMetals;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.crafting.IIngredientFactory;
import net.minecraftforge.common.crafting.JsonContext;
import net.minecraftforge.oredict.OreDictionary;
import org.apache.commons.lang3.ArrayUtils;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class IngredientMetal extends Ingredient {

	/**
	 * Can be: ingot, dust, nugget, block
	 */
	String type;

	public IngredientMetal(String ingredientType)
	{
		super(0);
		type = ingredientType;
	}

	@Override
	public boolean apply(@Nullable ItemStack craftingStack)
	{
		if (craftingStack == null || craftingStack.isEmpty())
			return false;

		return isMetal(type, craftingStack);
	}

	private boolean isMetal(String type, ItemStack stack)
	{
		int[] ids = OreDictionary.getOreIDs(stack);

		for (int id : ids)
		{
			String ore = OreDictionary.getOreName(id);

			if (ore.startsWith(type))
			{
				String snakeOre = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, ore);
				String[] snakeArray = snakeOre.split("_");
				String metalName = String.join("_", ArrayUtils.removeElement(snakeArray, snakeArray[0]));
				return ModMetals.metalMap.containsKey(metalName);
			}
		}

		return false;
	}

	public NonNullList<ItemStack> getOreDictStacks(Metal metal)
	{
		return OreDictionary.getOres(type + CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, metal.toString()), false);
	}

	@SuppressWarnings("unused")
	public static class Factory implements IIngredientFactory {

		@Nonnull
		@Override
		public Ingredient parse(JsonContext context, JsonObject json)
		{
			String type = JsonUtils.getString(json, "item_type");
			return new IngredientMetal(type);
		}

	}

}
