/*
 * -------------------------------------------------------------------------------------------------------
 * Class: BlockAlloyerRecipes
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2019.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.recipe;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.google.common.collect.Table.Cell;
import it.hurts.metallurgy_reforged.material.ModMetals;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import java.util.HashMap;
import java.util.Map;

public class BlockAlloyerRecipes {

    private static final BlockAlloyerRecipes INSTANCE = new BlockAlloyerRecipes();
    private final Table<ItemStack, ItemStack, ItemStack> alloyingList = HashBasedTable.create();

    private final Map<ItemStack, Float> experienceList = new HashMap<>();
    private final Map<ItemStack, ItemStack[]> recipeQuants = new HashMap<>();
    

    public static BlockAlloyerRecipes getInstance() {
        return INSTANCE;
    }

    private BlockAlloyerRecipes() {
//    	( [Input 1, quantit�], [Input 2, quantit�], [Output, quantit�, exp] ) 
        this.addAlloyRecipe
                (new ItemStack(ModMetals.COPPER.getIngot(), 3),
                        new ItemStack(ModMetals.TIN.getIngot()),
                        new ItemStack(ModMetals.BRONZE.getIngot(), 4), 1.75F);
        this.addAlloyRecipe
                (new ItemStack(ModMetals.SHADOW_IRON.getIngot(), 2),
                        new ItemStack(ModMetals.LEMURITE.getIngot()),
                        new ItemStack(ModMetals.SHADOW_STEEL.getIngot(), 3), 1.5F);
        this.addAlloyRecipe
                (new ItemStack(ModMetals.ALDUORITE.getIngot()),
                        new ItemStack(ModMetals.CERUCLASE.getIngot()),
                        new ItemStack(ModMetals.INOLASHITE.getIngot(), 2), 1.25F);
        this.addAlloyRecipe
                (new ItemStack(ModMetals.COPPER.getIngot(), 3),
                        new ItemStack(ModMetals.ZINC.getIngot()),
                        new ItemStack(ModMetals.BRASS.getIngot(), 4), 1.75F);
        this.addAlloyRecipe
                (new ItemStack(ModMetals.DEEP_IRON.getIngot(), 3),
                        new ItemStack(ModMetals.INFUSCOLIUM.getIngot()),
                        new ItemStack(ModMetals.BLACK_STEEL.getIngot(), 4), 1.75F);
        this.addAlloyRecipe
                (new ItemStack(Items.IRON_INGOT),
                        new ItemStack(ModMetals.MANGANESE.getIngot(), 3),
                        new ItemStack(ModMetals.STEEL.getIngot()), 1.5F);
        this.addAlloyRecipe
                (new ItemStack(Items.IRON_INGOT),
                        new ItemStack(ModMetals.BRONZE.getIngot(), 2),
                        new ItemStack(ModMetals.DAMASCUS_STEEL.getIngot(), 3), 1.5F);
        this.addAlloyRecipe
                (new ItemStack(ModMetals.SILVER.getIngot()),
                        new ItemStack(Items.GOLD_INGOT),
                        new ItemStack(ModMetals.ELECTRUM.getIngot(), 2), 1.25F);
        this.addAlloyRecipe
                (new ItemStack(ModMetals.ORICHALCUM.getIngot()),
                        new ItemStack(ModMetals.PLATINUM.getIngot()),
                        new ItemStack(ModMetals.CELENEGIL.getIngot(), 2), 1.25F);
        this.addAlloyRecipe
                (new ItemStack(ModMetals.KALENDRITE.getIngot()),
                        new ItemStack(ModMetals.PLATINUM.getIngot()),
                        new ItemStack(ModMetals.AMORDRINE.getIngot(), 2), 1.25F);
        this.addAlloyRecipe(
                new ItemStack(ModMetals.MITHRIL.getIngot()),
                new ItemStack(ModMetals.RUBRACIUM.getIngot(), 2),
                new ItemStack(ModMetals.HADEROTH.getIngot(), 3), 1.5F);
        this.addAlloyRecipe
                (new ItemStack(ModMetals.ADAMANTINE.getIngot()),
                        new ItemStack(ModMetals.ATLARUS.getIngot()),
                        new ItemStack(ModMetals.TARTARITE.getIngot()), 1.5F); //output decreased by one for balance sake
        this.addAlloyRecipe
                (new ItemStack(ModMetals.EXIMITE.getIngot()),
                        new ItemStack(ModMetals.MEUTOITE.getIngot()),
                        new ItemStack(ModMetals.DESICHALKOS.getIngot(), 2), 1.25F);
        this.addAlloyRecipe
                (new ItemStack(Items.IRON_INGOT),
                        new ItemStack(Items.GOLD_INGOT),
                        new ItemStack(ModMetals.ANGMALLEN.getIngot(), 2), 1.25F);
//      Original Recipe: [Bronze + Gold] 1:1 = Hepatizon
        this.addAlloyRecipe
                (new ItemStack(ModMetals.INFUSCOLIUM.getIngot()),
                        new ItemStack(ModMetals.STEEL.getIngot()),
                        new ItemStack(ModMetals.HEPATIZON.getIngot(), 2), 1.25F);
        this.addAlloyRecipe
                (new ItemStack(ModMetals.SILVER.getIngot()),
                        new ItemStack(ModMetals.MITHRIL.getIngot()),
                        new ItemStack(ModMetals.QUICKSILVER.getIngot(), 2), 1.25F);
        this.addAlloyRecipe
                (new ItemStack(ModMetals.LUTETIUM.getIngot()),
                        new ItemStack(ModMetals.OSMIUM.getIngot()),
                        new ItemStack(ModMetals.KRIK.getIngot(),  2), 1.25F);
    }

    private void addAlloyRecipe(ItemStack input1, ItemStack input2, ItemStack result, float experience) {

        if (input1.isEmpty() || input2.isEmpty() || result.isEmpty())
            return;
        if (getAlloyResult(input1, input2) != ItemStack.EMPTY)
            return;

        this.alloyingList.put(input1, input2, result);
        this.experienceList.put(result, experience);
        this.recipeQuants.put(result, new ItemStack[]{input1, input2});
    }

    public ItemStack getAlloyResult(ItemStack input1, ItemStack input2) {
        for(Cell<ItemStack, ItemStack, ItemStack> cell : this.alloyingList.cellSet()) {
            if(this.compareItemStacks(cell.getColumnKey(), input1) && this.compareItemStacks(cell.getRowKey(), input2)) {
                return cell.getValue();
            }

            if(this.compareItemStacks(cell.getRowKey(), input1) && this.compareItemStacks(cell.getColumnKey(), input2)) {
                return cell.getValue();
            }
        }
        return ItemStack.EMPTY;
    }

    public boolean isAlloyMetal(ItemStack input1) {
        for(Cell<ItemStack, ItemStack, ItemStack> cell : this.alloyingList.cellSet()) {
            if(this.compareItemStacks(cell.getColumnKey(), input1) || this.compareItemStacks(cell.getRowKey(), input1)) {
                return true;
            }
        }
        return false;
    }

    private boolean compareItemStacks(ItemStack stack1, ItemStack stack2) {
        return stack2.getItem() == stack1.getItem() && (stack2.getMetadata() == 32767 || stack2.getMetadata() == stack1.getMetadata());
    }

    public float getAlloyExperience(ItemStack stack) {
        for (ItemStack in : this.experienceList.keySet()) {
            if (in.isItemEqual(stack) || (in.getItem() == stack.getItem() && in.getItemDamage() == OreDictionary.WILDCARD_VALUE))
                return experienceList.get(in);
        }
        return 0.0F;
    }

    public int getItemQuantity(ItemStack result, ItemStack input)
    {
        if(this.recipeQuants.containsKey(result))
        {
            for(ItemStack stack : this.recipeQuants.get(result))
            {
                if(this.compareItemStacks(stack, input))
                    return stack.getCount();
            }
        }
        return 0;
    }

    public Table<ItemStack, ItemStack, ItemStack> getRecipeTable()
    {
        return alloyingList;
    }
}
