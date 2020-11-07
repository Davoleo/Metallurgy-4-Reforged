/*==============================================================================
 = Class: SublimationRecipes
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2020.
 =============================================================================*/

package it.hurts.metallurgy_reforged.recipe;

import it.hurts.metallurgy_reforged.block.BlockTypes;
import it.hurts.metallurgy_reforged.material.ModMetals;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
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
		if (ModMetals.ADAMANTINE != null)
			this.addSublimationRecipe(new ItemStack(ModMetals.ADAMANTINE.getBlock(BlockTypes.BLOCK), 5), new PotionEffect(MobEffects.SATURATION, 24000));
		if (ModMetals.CARMOT != null)
			this.addSublimationRecipe(new ItemStack(ModMetals.CARMOT.getBlock(BlockTypes.BLOCK), 6), new PotionEffect(MobEffects.HASTE, 24000));
		if (ModMetals.ALDUORITE != null)
			this.addSublimationRecipe(new ItemStack(ModMetals.ALDUORITE.getBlock(BlockTypes.BLOCK), 6), new PotionEffect(MobEffects.FIRE_RESISTANCE, 20000));
		if (ModMetals.QUICKSILVER != null)
			this.addSublimationRecipe(new ItemStack(ModMetals.QUICKSILVER.getBlock(BlockTypes.BLOCK), 4), new PotionEffect(MobEffects.SPEED, 24000));
		if (ModMetals.STEEL != null)
			this.addSublimationRecipe(new ItemStack(ModMetals.STEEL.getBlock(BlockTypes.BLOCK), 4), new PotionEffect(MobEffects.STRENGTH, 20000));
		if (ModMetals.DEEP_IRON != null)
			this.addSublimationRecipe(new ItemStack(ModMetals.DEEP_IRON.getBlock(BlockTypes.BLOCK), 5), new PotionEffect(MobEffects.WATER_BREATHING, 22000));
		if (ModMetals.ELECTRUM != null)
			this.addSublimationRecipe(new ItemStack(ModMetals.ELECTRUM.getBlock(BlockTypes.BLOCK), 5), new PotionEffect(MobEffects.NIGHT_VISION, 24000));
		if (ModMetals.CELENEGIL != null)
			this.addSublimationRecipe(new ItemStack(ModMetals.CELENEGIL.getBlock(BlockTypes.BLOCK), 6), new PotionEffect(MobEffects.RESISTANCE, 24000, 1));
		if (ModMetals.KALENDRITE != null)
			this.addSublimationRecipe(new ItemStack(ModMetals.KALENDRITE.getBlock(BlockTypes.BLOCK), 5), new PotionEffect(MobEffects.REGENERATION, 22000));
		if (ModMetals.MIDASIUM != null)
			this.addSublimationRecipe(new ItemStack(ModMetals.MIDASIUM.getBlock(BlockTypes.BLOCK), 4), new PotionEffect(MobEffects.LUCK, 20000));
		if (ModMetals.HADEROTH != null)
			this.addSublimationRecipe(new ItemStack(ModMetals.HADEROTH.getBlock(BlockTypes.BLOCK), 4), new PotionEffect(MobEffects.HEALTH_BOOST, 20000, 4));
	}

	public void addSublimationRecipe(ItemStack input, PotionEffect potion)
	{

		if (input.isEmpty() || potion == null)
			return;
		if (getSublimationResult(input) != null)
			return;

		this.sublimationList.put(input, potion);
	}

	public void removeSublimationRecipe(Potion output)
	{
		ItemStack keyToRemove = ItemStack.EMPTY;

		for (Map.Entry<ItemStack, PotionEffect> entry : this.sublimationList.entrySet())
		{
			if (output == entry.getValue().getPotion())
			{
				keyToRemove = entry.getKey();
			}
		}

		if (!keyToRemove.isEmpty())
			this.sublimationList.remove(keyToRemove);
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
