package it.hurts.metallurgy_5.util.recipe;

import com.google.common.collect.Maps;
import it.hurts.metallurgy_5.block.ModBlocks;
import it.hurts.metallurgy_5.item.ModItems;
import it.hurts.metallurgy_5.material.Metal;
import it.hurts.metallurgy_5.material.ModMetals;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

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
        for(Metal m: ModMetals.metalList) {
            if(m.getOre() != null) {
                addCrushingRecipe(new ItemStack(m.getOre(), 2), new ItemStack(m.getDust(), 2), 0.75F);
            }
            addCrushingRecipe(new ItemStack(m.getIngot()), new ItemStack(m.getDust()), 0.0F);
        }

        //Ore2Dust
        addCrushingRecipe(new ItemStack(Blocks.GOLD_ORE), new ItemStack(ModItems.dustGold, 2), 0.75F);
        addCrushingRecipe(new ItemStack(Blocks.IRON_ORE), new ItemStack(ModItems.dustIron, 2), 0.75F);
        addCrushingRecipe(new ItemStack(ModBlocks.oreBitumen), new ItemStack(ModItems.dustBitumen, 4), 0.75F);
        addCrushingRecipe(new ItemStack(ModBlocks.orePhosphorite), new ItemStack(ModItems.dustPhosphorus, 4), 0.75F);
        addCrushingRecipe(new ItemStack(ModBlocks.orePotash), new ItemStack(ModItems.dustPotash, 4), 0.75F);
        addCrushingRecipe(new ItemStack(ModBlocks.oreSulfur), new ItemStack(ModItems.dustSulfur, 4), 0.75F);

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
}
