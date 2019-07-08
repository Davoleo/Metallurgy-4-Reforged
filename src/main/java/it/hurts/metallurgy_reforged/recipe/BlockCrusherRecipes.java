/*
 * -------------------------------------------------------------------------------------------------------
 * Class: BlockCrusherRecipes
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2019.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.recipe;

import com.google.common.collect.Maps;
import it.hurts.metallurgy_reforged.block.ModBlocks;
import it.hurts.metallurgy_reforged.item.ModItems;
import it.hurts.metallurgy_reforged.material.Metal;
import it.hurts.metallurgy_reforged.material.ModMetals;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.oredict.OreDictionary;

import java.util.List;
import java.util.Map;

public class BlockCrusherRecipes {

    private static final BlockCrusherRecipes INSTANCE = new BlockCrusherRecipes();

    private final Map<ItemStack, ItemStack> crushingList = Maps.newHashMap();
    private final Map<ItemStack, Float> experienceList = Maps.newHashMap();

    public static BlockCrusherRecipes getInstance() {
        return INSTANCE;
    }

    private BlockCrusherRecipes() {
        for(Metal m: ModMetals.metalList) {
            if(m.getOre() != null) {
                // 1 Ore => 2 Dust
                addCrushingRecipe(new ItemStack(m.getOre(), 1), new ItemStack(m.getDust(), 2), 0.75F);
            }
            addCrushingRecipe(new ItemStack(m.getIngot()), new ItemStack(m.getDust()), 0.0F);
        }

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

    private void addCrushingRecipe(ItemStack input, ItemStack result, float experience) {
        if (input.isEmpty() || result.isEmpty())
            return;
        for (ItemStack stack : crushingList.keySet())
            if (input.isItemEqual(stack))
                return;

        this.crushingList.put(input.copy(), result);
        this.experienceList.put(result.copy(), experience);
    }

    public void removeCrushingRecipe(ItemStack output)
    {
        for (Map.Entry<ItemStack, ItemStack> entry : this.crushingList.entrySet())
        {
            if (output == entry.getValue())
                crushingList.remove(entry.getKey());
        }
    }

    public ItemStack getCrushingResult(ItemStack input) {
        for (Map.Entry<ItemStack, ItemStack> entry : this.crushingList.entrySet()) {
            ItemStack in = entry.getKey();
            if (in.isItemEqual(input) || (in.getItem() == input.getItem() && in.getItemDamage() == OreDictionary.WILDCARD_VALUE)) {
                return entry.getValue();
            }
        }
        return ItemStack.EMPTY;

    }

    public float getCrushingExperience(ItemStack stack) {
        for (ItemStack in : this.experienceList.keySet()) {
            if (in.isItemEqual(stack) || (in.getItem() == stack.getItem() && in.getItemDamage() == OreDictionary.WILDCARD_VALUE))
                return experienceList.get(in);
        }
        return 0.0F;
    }

    public Map<ItemStack, ItemStack> getRecipeMap()
    {
        return crushingList;
    }

    public static void registerDefaultOreRecipes() {
        for (String ore : OreDictionary.getOreNames()) {
            add(ore, "ore", "dust", 2, 0.75f);
            add(ore, "ore", "gem", 2, 0.5f);
            add(ore, "ingot", "dust", 1, 0.1f);
        }
        //Removed the flower section
    }

    private static void add(String ore, String in, String out, int amount, float exp) {
        if (ore.length() <= in.length())
            return;
        String mat = ore.substring(in.length());
        List<ItemStack> outs = OreDictionary.getOres(out + mat);
        if (ore.startsWith(in) && !outs.isEmpty())
            for (ItemStack stack : OreDictionary.getOres(ore))
                BlockCrusherRecipes.getInstance().addCrushingRecipe(stack, ItemHandlerHelper.copyStackWithSize(outs.get(0), amount), exp);
    }
}
