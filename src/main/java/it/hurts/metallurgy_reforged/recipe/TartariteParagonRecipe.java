/*==============================================================================
 = Class: TartariteParagonRecipe
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.recipe;

import com.google.gson.JsonObject;
import it.hurts.metallurgy_reforged.effect.MetallurgyEffects;
import it.hurts.metallurgy_reforged.effect.all.TartariteEffect;
import it.hurts.metallurgy_reforged.material.Metal;
import it.hurts.metallurgy_reforged.util.ItemUtils;
import it.hurts.metallurgy_reforged.util.Utils;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.IRecipeFactory;
import net.minecraftforge.common.crafting.JsonContext;
import net.minecraftforge.oredict.ShapedOreRecipe;

import javax.annotation.Nonnull;

public class TartariteParagonRecipe extends ShapedOreRecipe {

	private TartariteParagonRecipe(@Nonnull ItemStack result, CraftingHelper.ShapedPrimer primer)
	{
		super(null, result, primer);
	}

	@Override
	public boolean matches(@Nonnull InventoryCrafting inv, @Nonnull World world)
	{
		return super.matches(inv, world);
	}

	@Nonnull
	@Override
	public ItemStack getCraftingResult(@Nonnull InventoryCrafting craftInv)
	{
		if (!MetallurgyEffects.TARTARITE_EFFECT.isEnabled())
			return ItemStack.EMPTY;

		ItemStack tartariteItem = craftInv.getStackInSlot(4);
		//If tartarite item already has a paragon metal
		if (tartariteItem.getTagCompound() != null && tartariteItem.getTagCompound().hasKey("paragon"))
			return ItemStack.EMPTY;

		//Check if the metal matches on top center slot
		Metal metal = ItemUtils.getMetalFromItem(craftInv.getStackInSlot(1).getItem());
		assert metal != null;

		ItemStack result = super.getCraftingResult(craftInv);

		result.setItemDamage(tartariteItem.getItemDamage());
		//Set durability and effects
		TartariteEffect.setParagon(result, metal);

		return result;
	}

	@Override
	public boolean isDynamic()
	{
		return true;
	}

	//Used to parse Json (_factories.json)
	@SuppressWarnings("unused")
	public static class Factory implements IRecipeFactory {

		@Override
		public IRecipe parse(JsonContext context, JsonObject json)
		{
			//Parse Shaped Recipe inputs
			CraftingHelper.ShapedPrimer primer = Utils.parseShapedRecipe(context, json);
			//Parse Shaped Recipe output
			ItemStack result = CraftingHelper.getItemStack(json.getAsJsonObject("result"), context);
			//Build recipe from json
			return new TartariteParagonRecipe(result, primer);
		}

	}

}
