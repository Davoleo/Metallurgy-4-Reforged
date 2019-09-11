/*
 * -------------------------------------------------------------------------------------------------------
 * Class: SublimationRecipes
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2019.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.recipe;

import it.hurts.metallurgy_reforged.material.ModMetals;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class SublimationRecipes {

	private static final SublimationRecipes INSTANCE = new SublimationRecipes();
	private final Map<ItemStack, PotionEffect> sublimationList = new HashMap<>();

	public static SublimationRecipes getInstance()
	{
		return INSTANCE;
	}

	private SublimationRecipes()
	{

		this.addSublimationRecipe(new ItemStack(ModMetals.ADAMANTINE.getBlock(), 6), new PotionEffect(MobEffects.SATURATION, 16000));
		this.addSublimationRecipe(new ItemStack(ModMetals.CARMOT.getBlock(), 7), new PotionEffect(MobEffects.HASTE, 16000));
		this.addSublimationRecipe(new ItemStack(ModMetals.ALDUORITE.getBlock(), 7), new PotionEffect(MobEffects.FIRE_RESISTANCE, 12000));
		this.addSublimationRecipe(new ItemStack(ModMetals.QUICKSILVER.getBlock(), 5), new PotionEffect(MobEffects.SPEED, 16000));
		this.addSublimationRecipe(new ItemStack(ModMetals.STEEL.getBlock(), 5), new PotionEffect(MobEffects.STRENGTH, 12000));

	}

	private void addSublimationRecipe(ItemStack input, PotionEffect potion)
	{

		if (input.isEmpty() || potion == null)
			return;
		if (getSublimationResult(input) != null)
			return;

		this.sublimationList.put(input, potion);
	}

	public PotionEffect getSublimationResult(ItemStack input)
	{
		for (Entry<ItemStack, PotionEffect> entry : this.sublimationList.entrySet())
		{
			if (this.compareItemStacks(entry.getKey(), input) && entry.getKey().getCount() == input.getCount())
				return entry.getValue();
		}

		return null;
	}

	//    public boolean isSublimationBlock(ItemStack input) {
	//    	for(Entry<ItemStack, PotionEffect> entry : this.sublimationList.entrySet()) {
	//            if(this.compareItemStacks(entry.getKey(), input))
	//                return true;
	//        }
	//        return false;
	//    }

	public int getSublimationBlockAmount(ItemStack input)
	{
		for (Entry<ItemStack, PotionEffect> entry : this.sublimationList.entrySet())
		{
			if (this.compareItemStacks(entry.getKey(), input))
				return entry.getKey().getCount();
		}

		return 0;
	}

	public Map<ItemStack, PotionEffect> recipesMap()
	{
		return this.sublimationList;
	}

	private boolean compareItemStacks(ItemStack stack1, ItemStack stack2)
	{
		return stack2.getItem() == stack1.getItem() && (stack2.getMetadata() == 32767 || stack2.getMetadata() == stack1.getMetadata());
	}

}
