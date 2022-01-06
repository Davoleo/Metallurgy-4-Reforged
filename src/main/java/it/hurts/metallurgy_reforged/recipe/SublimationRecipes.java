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
import it.hurts.metallurgy_reforged.material.Metal;
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
		addSublimationRecipe(ModMetals.ADAMANTINE, 3, new PotionEffect(MobEffects.HEALTH_BOOST, 20 * 60 * 15, 3));
		addSublimationRecipe(ModMetals.AMORDRINE, 3, new PotionEffect(MobEffects.JUMP_BOOST, 20 * 60 * 15));
		addSublimationRecipe(ModMetals.ASTRAL_SILVER, 3, new PotionEffect(MobEffects.NIGHT_VISION, 20 * 60 * 15));
		addSublimationRecipe(ModMetals.BLACK_STEEL, 3, new PotionEffect(MobEffects.RESISTANCE, 20 * 60 * 15));
		addSublimationRecipe(ModMetals.CARMOT, 4, new PotionEffect(MobEffects.HASTE, 20 * 60 * 18));
		addSublimationRecipe(ModMetals.DEEP_IRON, 4, new PotionEffect(MobEffects.WATER_BREATHING, 20 * 60 * 20));
		addSublimationRecipe(ModMetals.IGNATIUS, 4, new PotionEffect(MobEffects.FIRE_RESISTANCE, 20 * 60 * 15));
		addSublimationRecipe(ModMetals.KALENDRITE, 4, new PotionEffect(MobEffects.REGENERATION, 20 * 60 * 20, 1));
		addSublimationRecipe(ModMetals.LEMURITE, 3, new PotionEffect(MobEffects.INVISIBILITY, 20 * 60 * 16));
		addSublimationRecipe(ModMetals.LUTETIUM, 4, new PotionEffect(MobEffects.LEVITATION, 20 * 60 * 20));
		addSublimationRecipe(ModMetals.MIDASIUM, 3, new PotionEffect(MobEffects.LUCK, 20 * 60 * 18));
		addSublimationRecipe(ModMetals.ORICHALCUM, 3, new PotionEffect(MobEffects.STRENGTH, 20 * 60 * 18, 1));
		addSublimationRecipe(ModMetals.QUICKSILVER, 4, new PotionEffect(MobEffects.SPEED, 20 * 60 * 20, 1));
		addSublimationRecipe(ModMetals.SANGUINITE, 3, new PotionEffect(MobEffects.SATURATION, 20 * 60 * 16));
	}

	private void addSublimationRecipe(Metal input, int amount, PotionEffect potionEffect)
	{
		if (input == null)
			return;

		addSublimationRecipe(new ItemStack(input.getBlock(BlockTypes.BLOCK), amount), potionEffect);
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
