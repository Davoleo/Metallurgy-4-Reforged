/*==============================================================================
 = Class: AlloyerRecipes
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.recipe;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.google.common.collect.Table.Cell;
import it.hurts.metallurgy_reforged.item.ModItems;
import it.hurts.metallurgy_reforged.material.Metal;
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.model.MetalSample;
import it.hurts.metallurgy_reforged.util.ItemUtils;
import it.hurts.metallurgy_reforged.util.Utils;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import org.apache.commons.lang3.tuple.Pair;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class AlloyerRecipes {

	private static final AlloyerRecipes INSTANCE = new AlloyerRecipes();
	private final Table<MetalSample, MetalSample, MetalSample> alloyingList = HashBasedTable.create();
	private final Map<MetalSample, Pair<MetalSample, MetalSample>> ingredientsCache = new HashMap<>();

	public static AlloyerRecipes getInstance()
	{
		return INSTANCE;
	}

	private AlloyerRecipes()
	{
		this.addAlloyRecipe
				(new MetalSample(ModMetals.COPPER, 3),
						new MetalSample(ModMetals.TIN, 1),
						new MetalSample(ModMetals.BRONZE, 4, 1.75F));

		this.addAlloyRecipe
				(new MetalSample(ModMetals.SHADOW_IRON, 2),
						new MetalSample(ModMetals.LEMURITE, 1),
						new MetalSample(ModMetals.SHADOW_STEEL, 3, 1.5F));

		this.addAlloyRecipe
				(new MetalSample(ModMetals.ALDUORITE, 1),
						new MetalSample(ModMetals.CERUCLASE, 1),
						new MetalSample(ModMetals.INOLASHITE, 2, 1.25F));

		this.addAlloyRecipe
				(new MetalSample(ModMetals.COPPER, 3),
						new MetalSample(ModMetals.ZINC, 1),
						new MetalSample(ModMetals.BRASS, 4, 1.75F));

		this.addAlloyRecipe
				(new MetalSample(ModMetals.DEEP_IRON, 3),
						new MetalSample(ModMetals.INFUSCOLIUM, 1),
						new MetalSample(ModMetals.BLACK_STEEL, 4, 1.75F));

		this.addAlloyRecipe
				(new MetalSample(new ItemStack(Items.IRON_INGOT), 1),
						new MetalSample(ModMetals.MANGANESE, 2),
						new MetalSample(ModMetals.STEEL, 2, 1.5F)); //output decreased by one for balance's sake

		this.addAlloyRecipe
				(new MetalSample(new ItemStack(ModItems.IRON_DUST), 1),
						new MetalSample(ModMetals.MANGANESE, 2),
						new MetalSample(ModMetals.STEEL, 2, 1.5F)); //output decreased by one for balance's sake

		this.addAlloyRecipe
				(new MetalSample(new ItemStack(Items.IRON_INGOT), 1),
						new MetalSample(ModMetals.BRONZE, 2),
						new MetalSample(ModMetals.DAMASCUS_STEEL, 3, 1.5F));
		this.addAlloyRecipe
				(new MetalSample(new ItemStack(ModItems.IRON_DUST), 1),
						new MetalSample(ModMetals.BRONZE, 2),
						new MetalSample(ModMetals.DAMASCUS_STEEL, 3, 1.5F));

		this.addAlloyRecipe
				(new MetalSample(ModMetals.SILVER, 1),
						new MetalSample(new ItemStack(Items.GOLD_INGOT), 1),
						new MetalSample(ModMetals.ELECTRUM, 2, 1.25F));
		this.addAlloyRecipe
				(new MetalSample(ModMetals.SILVER, 1),
						new MetalSample(new ItemStack(ModItems.GOLD_DUST), 1),
						new MetalSample(ModMetals.ELECTRUM, 2, 1.25F));

		this.addAlloyRecipe
				(new MetalSample(ModMetals.ORICHALCUM, 1),
						new MetalSample(ModMetals.PLATINUM, 1),
						new MetalSample(ModMetals.CELENEGIL, 2, 1.25F));

		this.addAlloyRecipe
				(new MetalSample(ModMetals.KALENDRITE, 1),
						new MetalSample(ModMetals.PLATINUM, 1),
						new MetalSample(ModMetals.AMORDRINE, 2, 1.25F));

		this.addAlloyRecipe(
				new MetalSample(ModMetals.MITHRIL, 1),
				new MetalSample(ModMetals.RUBRACIUM, 2),
				new MetalSample(ModMetals.HADEROTH, 3, 1.5F));

		this.addAlloyRecipe
				(new MetalSample(ModMetals.ADAMANTINE, 1),
						new MetalSample(ModMetals.ATLARUS, 1),
						new MetalSample(ModMetals.TARTARITE, 1, 1.5F)); //output decreased by one for balance's sake

		this.addAlloyRecipe
				(new MetalSample(ModMetals.EXIMITE, 1),
						new MetalSample(ModMetals.MEUTOITE, 1),
						new MetalSample(ModMetals.DESICHALKOS, 2, 1.25F));

		this.addAlloyRecipe
				(new MetalSample(new ItemStack(Items.GOLD_INGOT), 1),
						new MetalSample(new ItemStack(Items.IRON_INGOT), 1),
						new MetalSample(ModMetals.ANGMALLEN, 2, 1.25F));
		this.addAlloyRecipe
				(new MetalSample(new ItemStack(ModItems.GOLD_DUST), 1),
						new MetalSample(new ItemStack(ModItems.IRON_DUST), 1),
						new MetalSample(ModMetals.ANGMALLEN, 2, 1.25F));

		//      Original Recipe: [Bronze + Gold] 1:1 = Hepatizon
		this.addAlloyRecipe
				(new MetalSample(ModMetals.INFUSCOLIUM, 1),
						new MetalSample(ModMetals.STEEL, 1),
						new MetalSample(ModMetals.HEPATIZON, 2, 1.25F));

		this.addAlloyRecipe
				(new MetalSample(ModMetals.SILVER, 1),
						new MetalSample(ModMetals.ASTRAL_SILVER, 1),
						new MetalSample(ModMetals.QUICKSILVER, 2, 1.25F));

		this.addAlloyRecipe
				(new MetalSample(ModMetals.LUTETIUM, 1),
						new MetalSample(ModMetals.OSMIUM, 1),
						new MetalSample(ModMetals.KRIK, 2, 1.25F));

		this.addAlloyRecipe
				(new MetalSample(ModMetals.SANGUINITE, 1),
						new MetalSample(ModMetals.ALDUORITE, 1),
						new MetalSample(ModMetals.ETHERIUM, 2, 1.25F));

	}

	public void addCustomAlloyRecipe(ItemStack input1, ItemStack input2, ItemStack result, float xp)
	{
		if (input1.isEmpty() || input2.isEmpty() || result.isEmpty())
			return;
		//Won't add a recipe whose inputs already correspond to an output in existing recipes
		if (getAlloyResult(input1, input2) != ItemStack.EMPTY)
			return;

		this.alloyingList.put(
				new MetalSample(input1, input1.getCount()),
				new MetalSample(input2, input2.getCount()),
				new MetalSample(result, result.getCount()).setXp(xp)
		);
	}

	//Internal Use only
	private void addAlloyRecipe(MetalSample input1, MetalSample input2, MetalSample result)
	{
		//Make sure input1 input2 and result metals were not disabled
		if (!input1.getStack().isEmpty() && !input2.getStack().isEmpty() && !result.getStack().isEmpty())
		{
			this.alloyingList.put(input1, input2, result);
			this.ingredientsCache.put(result, Pair.of(input1, input2));
		}
	}

	public void removeAlloyRecipe(ItemStack output)
	{
		Set<Cell<MetalSample, MetalSample, MetalSample>> recipesToRemove = this.alloyingList.cellSet().stream()
				.filter(cell -> compareItemStackToAlloySample(output, cell.getValue()))
				.collect(Collectors.toSet());

		if (!recipesToRemove.isEmpty())
			recipesToRemove.forEach(alloyCell -> alloyingList.remove(alloyCell.getRowKey(), alloyCell.getColumnKey()));
	}

	@SuppressWarnings("ConstantConditions")
	public ItemStack getAlloyResult(ItemStack input1, ItemStack input2)
	{
		Optional<Cell<MetalSample, MetalSample, MetalSample>> result =
				this.alloyingList.cellSet().stream()
						.filter(cell ->
								(compareItemStackToAlloySample(input1, cell.getRowKey()) && compareItemStackToAlloySample(input2, cell.getColumnKey()))
										|| (compareItemStackToAlloySample(input1, cell.getColumnKey()) && compareItemStackToAlloySample(input2, cell.getRowKey()))
						).findFirst();

		//check if there's any recipe that has those inputs (when the optional is not empty)
		if (result.isPresent())
		{
			//get the alloy sample object
			MetalSample alloyResult = result.get().getValue();
			return alloyResult.getStack();
		}
		else
			return ItemStack.EMPTY;
	}

	/**
	 * @param alloy which you want to retrieve the ingredients of
	 *
	 * @return {@code null} if no ingredients are found
	 */
	@Nullable
	public Pair<MetalSample, MetalSample> getIngredients(ItemStack alloy)
	{
		return ingredientsCache.entrySet().stream()
				.filter(cell -> compareItemStackToAlloySample(alloy, cell.getKey()))
				.findFirst()
				.map(Map.Entry::getValue)
				.orElse(null);
	}

	public boolean isAlloyMetal(ItemStack input1)
	{
		return this.alloyingList.cellSet().stream()
				.anyMatch(cell -> compareItemStackToAlloySample(input1, cell.getRowKey()) || compareItemStackToAlloySample(input1, cell.getColumnKey()));
	}

	private boolean compareItemStackToAlloySample(ItemStack stack, MetalSample alloySample)
	{
		Metal metal = ItemUtils.getMetalFromOreDictStack(stack);

		if (metal == null)
			return false;

		if (alloySample.hasFallenBack())
			return compareItemStacks(stack, alloySample.getFallbackStack());
		else
		{
			return Utils.listContains(alloySample.getOredictedStacks(), stack, this::compareItemStacks);
		}
	}

	private boolean compareItemStacks(ItemStack stack1, ItemStack stack2)
	{
		return stack2.getItem() == stack1.getItem() && (stack2.getMetadata() == 32767 || stack2.getMetadata() == stack1.getMetadata());
	}

	public float getAlloyExperience(ItemStack stack)
	{
		Optional<MetalSample> result = this.alloyingList.cellSet().stream()
				.map(Cell::getValue)
				.filter(resultAlloy -> compareItemStackToAlloySample(stack, resultAlloy))
				.findFirst();

		return result
				.filter(MetalSample::hasXp)
				.map(MetalSample::getXp)
				.orElse(0F);
	}

	public int getItemQuantity(ItemStack result, ItemStack input)
	{
		for (Cell<MetalSample, MetalSample, MetalSample> cell : this.alloyingList.cellSet())
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

	public Table<MetalSample, MetalSample, MetalSample> getRecipeTable()
	{
		return alloyingList;
	}

}
