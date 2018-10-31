package it.hurts.metallurgy_5.util.recipe;

import com.google.common.collect.Maps;
import it.hurts.metallurgy_5.block.ModBlocks;
import it.hurts.metallurgy_5.item.ModItems;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import java.util.Collections;
import java.util.Map;

/*************************************************
 * Author: Davoleo
 * Date: 03/09/2018
 * Hour: 19.30
 * Project: Metallurgy_5
 * Copyright - © - Davoleo - 2018
 **************************************************/

public class BlockCrusherRecipes {

    private static final BlockCrusherRecipes INSTANCE = new BlockCrusherRecipes();

    //Nel caso si voglia utilizzare un metodo più automatico per le recipe degli ore
    //private static final int ORE_MULTIPLIER = 2;

    private final Map<ItemStack, ItemStack> crushingList = Maps.newHashMap();
    private final Map<ItemStack, Float> experienceList = Maps.newHashMap();


    public static BlockCrusherRecipes getInstance() {
        return INSTANCE;
    }

    private BlockCrusherRecipes() {
        //TODO : Fucking complete this Davoleo
        addCrushingRecipe(new ItemStack(ModBlocks.oreAdamantine), new ItemStack(ModItems.dustAdamantine, 2), 10F);
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

    public ItemStack getCrushingResult(ItemStack input) {
        for (Map.Entry<ItemStack, ItemStack> entry : this.crushingList.entrySet()) {
            ItemStack in = entry.getKey();
            if (in.isItemEqual(input) || (in.getItem() == input.getItem() && in.getItemDamage() == OreDictionary.WILDCARD_VALUE))
                return entry.getValue();
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

    private boolean compareItemStacks(ItemStack stack1, ItemStack stack2) {
        return stack2.getItem() == stack1.getItem() && (stack2.getMetadata() == 32767 || stack2.getMetadata() == stack1.getMetadata());
    }
}
