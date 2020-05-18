/*
 * -------------------------------------------------------------------------------------------------------
 * Class: CompatSublimationChamber
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 * Copyright (c) 2020.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.integration.mods.crafttweaker;

import crafttweaker.CraftTweakerAPI;
import crafttweaker.IAction;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.potions.IPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import static it.hurts.metallurgy_reforged.recipe.SublimationRecipes.getInstance;

@ZenClass("mods.metallurgyreforged.SublimationChamber")
public class CompatSublimationChamber {

	@ZenMethod
	public static void addRecipe(IItemStack input, IPotion output, int duration, int amplifier)
	{
		CraftTweakerAPI.apply(new Add(input, output, duration, amplifier));
	}

	public static class Add implements IAction {

		private IItemStack input;
		private IPotion output;
		private int duration;
		private int amplifier;

		public Add(IItemStack input, IPotion output, int duration, int amplifier)
		{
			this.input = input;
			this.output = output;
			this.duration = duration;
			this.amplifier = amplifier;
		}

		@Override
		public void apply()
		{
			ItemStack input = IntegrationCT.toStack(this.input);
			Potion output = IntegrationCT.getInternalPotion(this.output);

			PotionEffect outputEffect = new PotionEffect(output, this.duration, this.amplifier);

			getInstance().addSublimationRecipe(input, outputEffect);
		}

		@Override
		public String describe()
		{
			return "Adding Metallurgy-Reforged Sublimation Recipe for " + output.name();
		}

	}

	@ZenMethod
	public static void removeRecipe(IPotion output)
	{
		CraftTweakerAPI.apply(new Remove(output));
	}

	public static class Remove implements IAction {

		private IPotion output;

		public Remove(IPotion output)
		{
			this.output = output;
		}

		@Override
		public void apply()
		{
			Potion output = IntegrationCT.getInternalPotion(this.output);

			getInstance().removeSublimationRecipe(output);
		}

		@Override
		public String describe()
		{
			return "Removing Metallurgy-Reforged Sublimation Recipe for " + output.name();
		}

	}

}
