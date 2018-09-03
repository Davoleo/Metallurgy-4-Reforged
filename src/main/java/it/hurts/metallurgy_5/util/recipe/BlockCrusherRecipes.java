package it.hurts.metallurgy_5.util.recipe;

import java.util.Map;

import com.google.common.collect.Maps;
import it.hurts.metallurgy_5.block.ModBlocks;
import it.hurts.metallurgy_5.item.ModItems;
import net.minecraft.item.ItemStack;

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

    private final Map<ItemStack, ItemStack> smeltingList = Maps.<ItemStack, ItemStack>newHashMap();
    private final Map<ItemStack, Float> experienceList = Maps.<ItemStack, Float>newHashMap();


    public static BlockCrusherRecipes getInstance()
    {
        return INSTANCE;
    }

    private BlockCrusherRecipes()
    {
        addCrushingRecipe(new ItemStack(ModBlocks.oreAdamantine), new ItemStack(ModItems.dustAdamantine, 2), 10F);
    }

    public void addCrushingRecipe(ItemStack input, ItemStack result, float experience)
    {
        if(getCrushingResult(input) != ItemStack.EMPTY) return;
        this.smeltingList.put(input, result);
        this.experienceList.put(result, experience);
    }

    public ItemStack getCrushingResult(ItemStack input)
    {
        for(Map.Entry<ItemStack, ItemStack> entry : this.smeltingList.entrySet())
        {
            if(this.compareItemStacks(input, (ItemStack)entry.getKey()))
            {
                        return (ItemStack)entry.getValue();
            }
        }
        return ItemStack.EMPTY;

    }

    private boolean compareItemStacks(ItemStack stack1, ItemStack stack2)
    {
        return stack2.getItem() == stack1.getItem() && (stack2.getMetadata() == 32767 || stack2.getMetadata() == stack1.getMetadata());
    }

    public Map<ItemStack, ItemStack> getSmeltingList()
    {
        return this.smeltingList;
    }

    public float getCrushingExperience(ItemStack stack)
    {
        for(Map.Entry<ItemStack, Float> entry : this.experienceList.entrySet())
        {
            if(this.compareItemStacks(stack, (ItemStack)entry.getKey()))
            {
                return ((Float)entry.getValue()).floatValue();
            }

        }
        return 0.0F;
    }

}
