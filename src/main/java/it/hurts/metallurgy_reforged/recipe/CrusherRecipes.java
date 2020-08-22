/*
 * -------------------------------------------------------------------------------------------------------
 * Class: CrusherRecipes
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 * Copyright (c) 2020.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.recipe;

import com.google.common.collect.Maps;
import it.hurts.metallurgy_reforged.block.ModBlocks;
import it.hurts.metallurgy_reforged.item.ModItems;
import it.hurts.metallurgy_reforged.material.ModMetals;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class CrusherRecipes {

	private static final CrusherRecipes INSTANCE = new CrusherRecipes();

	private final Map<ItemStack, ItemStack> crushingList = Maps.newHashMap();
	private final Map<ItemStack, Float> experienceList = Maps.newHashMap();

	private static List<ItemStack> stackBlacklist = new ArrayList<>();

	public static CrusherRecipes getInstance()
	{
		return INSTANCE;
	}

	private CrusherRecipes()
	{
		ModMetals.metalMap.forEach((name, m) -> {
			if (m.getOre() != null)
			{
				// 1 Ore => 2 Dust
				addCrushingRecipe(new ItemStack(m.getOre(), 1), new ItemStack(m.getDust(), 2), 0.75F);
			}
			// 1 Ingot => 1 Dust
			addCrushingRecipe(new ItemStack(m.getIngot()), new ItemStack(m.getDust()), 0.0F);
		});

		//Ore2Dust
		addCrushingRecipe(new ItemStack(Blocks.GOLD_ORE), new ItemStack(ModItems.dustGold, 2), 0.75F);
		addCrushingRecipe(new ItemStack(Blocks.IRON_ORE), new ItemStack(ModItems.dustIron, 2), 0.75F);
		addCrushingRecipe(new ItemStack(ModBlocks.oreTar), new ItemStack(ModItems.tar, 4), 0.75F);
		addCrushingRecipe(new ItemStack(ModBlocks.orePhosphorite), new ItemStack(ModItems.phosphorus, 4), 0.75F);
		addCrushingRecipe(new ItemStack(ModBlocks.orePotash), new ItemStack(ModItems.potash, 4), 0.75F);
		addCrushingRecipe(new ItemStack(ModBlocks.oreSulfur), new ItemStack(ModItems.sulfur, 4), 0.75F);
		addCrushingRecipe(new ItemStack(ModItems.potash), new ItemStack(ModItems.dustPotash), 1F);

		//Ingot2Dust
		addCrushingRecipe(new ItemStack(Items.GOLD_INGOT), new ItemStack(ModItems.dustGold), 0.0F);
		addCrushingRecipe(new ItemStack(Items.IRON_INGOT), new ItemStack(ModItems.dustIron), 0.0F);
	}

	public void addCrushingRecipe(ItemStack input, ItemStack result, float experience)
	{
		if (input.isEmpty() || result.isEmpty())
			return;
		for (ItemStack stack : crushingList.keySet())
			if (input.isItemEqual(stack))
				return;

		this.crushingList.put(input.copy(), result.copy());
		this.experienceList.put(result.copy(), experience);
	}

	public void removeCrushingRecipe(ItemStack output)
	{
		List<ItemStack> keysToRemove = new ArrayList<>();

		for (Map.Entry<ItemStack, ItemStack> entry : this.crushingList.entrySet())
		{
			if (output.getItem() == entry.getValue().getItem())
			{
				keysToRemove.add(entry.getKey());
			}
		}

		if (!keysToRemove.isEmpty())
			for (ItemStack stack : keysToRemove)
			{
				crushingList.remove(stack);
				experienceList.remove(output);
				stackBlacklist.add(stack);
			}

	}

	public ItemStack getCrushingResult(ItemStack input)
	{
		for (Map.Entry<ItemStack, ItemStack> entry : this.crushingList.entrySet())
		{
			ItemStack recipeInput = entry.getKey();
			if (isValidInput(input, recipeInput))
			{
				return entry.getValue();
			}
		}
		return ItemStack.EMPTY;
	}

	private boolean isValidInput(ItemStack input, ItemStack recipeInput)
	{
		if (input.isItemEqual(recipeInput) || (input.getItem() == recipeInput.getItem() && recipeInput.getItemDamage() == OreDictionary.WILDCARD_VALUE))
			return true;

		List<ItemStack> otherValidStuff = new ArrayList<>();

		for (int id : OreDictionary.getOreIDs(recipeInput))
		{
			if (Arrays.stream(OreDictionary.getOreIDs(input)).anyMatch(inputId -> inputId == id))
			{
				otherValidStuff.addAll(OreDictionary.getOres(OreDictionary.getOreName(id)));
			}
		}

		return !otherValidStuff.isEmpty();
	}

	public float getCrushingExperience(ItemStack stack)
	{
		for (ItemStack in : this.experienceList.keySet())
		{
			if (in.isItemEqual(stack) || (in.getItem() == stack.getItem() && in.getItemDamage() == OreDictionary.WILDCARD_VALUE))
				return experienceList.get(in);
		}
		return 0.0F;
	}

	public Map<ItemStack, ItemStack> getRecipeMap()
	{
		return crushingList;
	}

	/**
	 * @deprecated - It overrides CraftTweaker recipe removal making it impossible to remove certain recipes
	 */
	@Deprecated
	public static void registerDefaultOreRecipes()
	{
		for (String ore : OreDictionary.getOreNames())
		{
			add(ore, "ore", "dust", 2, 0.75f);
			add(ore, "ore", "gem", 2, 0.5f);
			add(ore, "ingot", "dust", 1, 0.1f);
		}
		//Removed the flower section
	}

	/**
	 * @see CrusherRecipes#registerDefaultOreRecipes()
	 * @deprecated
	 */
	@Deprecated
	private static void add(String ore, String in, String out, int amount, float exp)
	{
		if (ore.length() <= in.length())
			return;

		String mat = ore.substring(in.length());
		List<ItemStack> outs = OreDictionary.getOres(out + mat);

		if (ore.startsWith(in) && !outs.isEmpty())
			for (ItemStack stack : OreDictionary.getOres(ore))
				if (!getInstance().crushingList.containsKey(stack))
					if (stackBlacklist.stream().noneMatch((blacklistStack) -> ItemStack.areItemStacksEqual(blacklistStack, stack)))
						CrusherRecipes.getInstance().addCrushingRecipe(stack, ItemHandlerHelper.copyStackWithSize(outs.get(0), amount), exp);
	}

}
