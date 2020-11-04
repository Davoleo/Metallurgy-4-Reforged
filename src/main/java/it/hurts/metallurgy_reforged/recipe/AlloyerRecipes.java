/*
 * -------------------------------------------------------------------------------------------------------
 * Class: AlloyerRecipes
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 * Copyright (c) 2020.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.recipe;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.google.common.collect.Table.Cell;
import it.hurts.metallurgy_reforged.material.Metal;
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.model.AlloySample;
import it.hurts.metallurgy_reforged.util.ItemUtils;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class AlloyerRecipes {

	private static final AlloyerRecipes INSTANCE = new AlloyerRecipes();
	private final Table<AlloySample, AlloySample, AlloySample> alloyingList = HashBasedTable.create();

	public static AlloyerRecipes getInstance()
	{
		return INSTANCE;
	}

	private AlloyerRecipes()
	{
		this.addAlloyRecipe
				(new AlloySample(ModMetals.COPPER, 3),
						new AlloySample(ModMetals.TIN, 1),
						new AlloySample(ModMetals.BRONZE, 4, 1.75F));
		this.addAlloyRecipe
				(new AlloySample(ModMetals.SHADOW_IRON, 2),
						new AlloySample(ModMetals.LEMURITE, 1),
						new AlloySample(ModMetals.SHADOW_STEEL, 3, 1.5F));
		this.addAlloyRecipe
				(new AlloySample(ModMetals.ALDUORITE, 1),
						new AlloySample(ModMetals.CERUCLASE, 1),
						new AlloySample(ModMetals.INOLASHITE, 2, 1.25F));
		this.addAlloyRecipe
				(new AlloySample(ModMetals.COPPER, 3),
						new AlloySample(ModMetals.ZINC, 1),
						new AlloySample(ModMetals.BRASS, 4, 1.75F));
		this.addAlloyRecipe
				(new AlloySample(ModMetals.DEEP_IRON, 3),
						new AlloySample(ModMetals.INFUSCOLIUM, 1),
						new AlloySample(ModMetals.BLACK_STEEL, 4, 1.75F));
		this.addAlloyRecipe
				(new AlloySample(new ItemStack(Items.IRON_INGOT), 1),
						new AlloySample(ModMetals.MANGANESE, 2),
						new AlloySample(ModMetals.STEEL, 2, 1.5F)); //output decreased by one for balance sake
		this.addAlloyRecipe
				(new AlloySample(new ItemStack(Items.IRON_INGOT), 1),
						new AlloySample(ModMetals.BRONZE, 2),
						new AlloySample(ModMetals.DAMASCUS_STEEL, 3, 1.5F));
		this.addAlloyRecipe
				(new AlloySample(ModMetals.SILVER, 1),
						new AlloySample(new ItemStack(Items.GOLD_INGOT), 1),
						new AlloySample(ModMetals.ELECTRUM, 2, 1.25F));
		this.addAlloyRecipe
				(new AlloySample(ModMetals.ORICHALCUM, 1),
						new AlloySample(ModMetals.PLATINUM, 1),
						new AlloySample(ModMetals.CELENEGIL, 2, 1.25F));
		this.addAlloyRecipe
				(new AlloySample(ModMetals.KALENDRITE, 1),
						new AlloySample(ModMetals.PLATINUM, 1),
						new AlloySample(ModMetals.AMORDRINE, 2, 1.25F));
		this.addAlloyRecipe(
				new AlloySample(ModMetals.MITHRIL, 1),
				new AlloySample(ModMetals.RUBRACIUM, 2),
				new AlloySample(ModMetals.HADEROTH, 3, 1.5F));
		this.addAlloyRecipe
				(new AlloySample(ModMetals.ADAMANTINE, 1),
						new AlloySample(ModMetals.ATLARUS, 1),
						new AlloySample(ModMetals.TARTARITE, 1, 1.5F)); //output decreased by one for balance sake
		this.addAlloyRecipe
				(new AlloySample(ModMetals.EXIMITE, 1),
						new AlloySample(ModMetals.MEUTOITE, 1),
						new AlloySample(ModMetals.DESICHALKOS, 2, 1.25F));
		this.addAlloyRecipe
				(new AlloySample(new ItemStack(Items.GOLD_INGOT), 1),
						new AlloySample(new ItemStack(Items.IRON_INGOT), 1),
						new AlloySample(ModMetals.ANGMALLEN, 2, 1.25F));
		//      Original Recipe: [Bronze + Gold] 1:1 = Hepatizon
		this.addAlloyRecipe
				(new AlloySample(ModMetals.INFUSCOLIUM, 1),
						new AlloySample(ModMetals.STEEL, 1),
						new AlloySample(ModMetals.HEPATIZON, 2, 1.25F));
		this.addAlloyRecipe
				(new AlloySample(ModMetals.SILVER, 1),
						new AlloySample(ModMetals.MITHRIL, 1),
						new AlloySample(ModMetals.QUICKSILVER, 2, 1.25F));
		this.addAlloyRecipe
				(new AlloySample(ModMetals.LUTETIUM, 1),
						new AlloySample(ModMetals.OSMIUM, 1),
						new AlloySample(ModMetals.KRIK, 2, 1.25F));
		this.addAlloyRecipe
				(new AlloySample(ModMetals.SANGUINITE, 1),
						new AlloySample(ModMetals.CARMOT, 1),
						new AlloySample(ModMetals.ETHERIUM, 2, 1.25F));
	}

	public void addCustomAlloyRecipe(ItemStack input1, ItemStack input2, ItemStack result, float xp)
	{
		if (input1.isEmpty() || input2.isEmpty() || result.isEmpty())
			return;
		//Won't add a recipe whose inputs already correspond to an output in existing recipes
		if (getAlloyResult(input1, input2) != ItemStack.EMPTY)
			return;

		this.alloyingList.put(
				new AlloySample(input1, input1.getCount()),
				new AlloySample(input2, input2.getCount()),
				new AlloySample(result, result.getCount()).setXp(xp)
		);
	}

	//Internal Use only
	private void addAlloyRecipe(AlloySample input1, AlloySample input2, AlloySample result)
	{
		//Make sure input1 input2 and result metals were not disabled
		if (!input1.getStack().isEmpty() && !input2.getStack().isEmpty() && !result.getStack().isEmpty())
			this.alloyingList.put(input1, input2, result);
	}

	public void removeAlloyRecipe(ItemStack output)
	{
		Set<Cell<AlloySample, AlloySample, AlloySample>> recipesToRemove = this.alloyingList.cellSet().stream()
				.filter(cell -> compareItemStackToAlloySample(output, cell.getValue()))
				.collect(Collectors.toSet());

		if (!recipesToRemove.isEmpty())
			recipesToRemove.forEach(alloyCell -> alloyingList.remove(alloyCell.getRowKey(), alloyCell.getColumnKey()));
	}

	@SuppressWarnings("ConstantConditions")
	public ItemStack getAlloyResult(ItemStack input1, ItemStack input2)
	{
		Optional<Cell<AlloySample, AlloySample, AlloySample>> result =
				this.alloyingList.cellSet().stream()
						.filter(cell ->
								(compareItemStackToAlloySample(input1, cell.getRowKey()) && compareItemStackToAlloySample(input2, cell.getColumnKey()))
										|| (compareItemStackToAlloySample(input1, cell.getColumnKey()) && compareItemStackToAlloySample(input2, cell.getRowKey()))
						).findFirst();

		//check if there's any recipe that has those inputs (when the optional is not empty)
		if (result.isPresent())
		{
			//get the alloy sample object
			AlloySample alloyResult = result.get().getValue();
			return alloyResult.getStack();
		}
		else
			return ItemStack.EMPTY;
	}

	public boolean isAlloyMetal(ItemStack input1)
	{
		return this.alloyingList.cellSet().stream()
				.anyMatch(cell -> compareItemStackToAlloySample(input1, cell.getRowKey()) || compareItemStackToAlloySample(input1, cell.getColumnKey()));
	}

	private boolean compareItemStackToAlloySample(ItemStack stack, AlloySample alloySample)
	{
		Metal metal = ItemUtils.getMetalFromOreDictStack(stack);

		if (metal != null)
			return metal == alloySample.getMetal();
		else
			return compareItemStacks(stack, alloySample.getFallbackStack());
	}

	private boolean compareItemStacks(ItemStack stack1, ItemStack stack2)
	{
		return stack2.getItem() == stack1.getItem() && (stack2.getMetadata() == 32767 || stack2.getMetadata() == stack1.getMetadata());
	}

	public float getAlloyExperience(ItemStack stack)
	{
		Optional<AlloySample> result = this.alloyingList.cellSet().stream()
				.map(Cell::getValue)
				.filter(resultAlloy -> compareItemStackToAlloySample(stack, resultAlloy))
				.findFirst();

		return result
				.filter(AlloySample::hasXp)
				.map(AlloySample::getXp)
				.orElse(0F);
	}

	public int getItemQuantity(ItemStack result, ItemStack input)
	{
		for (Cell<AlloySample, AlloySample, AlloySample> cell : this.alloyingList.cellSet())
		{
			if (compareItemStackToAlloySample(result, cell.getValue()))
			{

				if (compareItemStackToAlloySample(input, cell.getRowKey()))
				{
					return cell.getRowKey().getAmount();
				}
				else if (compareItemStackToAlloySample(input, cell.getColumnKey()))
				{
					return cell.getColumnKey().getAmount();
				}
			}
		}
		return 0;
	}

	public Table<AlloySample, AlloySample, AlloySample> getRecipeTable()
	{
		return alloyingList;
	}

}
