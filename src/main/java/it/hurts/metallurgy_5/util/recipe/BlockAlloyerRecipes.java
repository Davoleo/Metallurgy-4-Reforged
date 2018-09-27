/**
 * 
 */
package it.hurts.metallurgy_5.util.recipe;

import java.util.Map;

import com.google.common.collect.Maps;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

/***************************
 *
 * Author : ItHurtsLikeHell
 * Project: Metallurgy-5
 * Date   : 27 set 2018
 * Time   : 20:48:50
 *
 ***************************/
public class BlockAlloyerRecipes {

	private static final BlockAlloyerRecipes INSTANCE = new BlockAlloyerRecipes();
	
	private final Map<ItemStack, ItemStack> alloyingList = Maps.<ItemStack, ItemStack>newHashMap();
    private final Map<ItemStack, Float> experienceList = Maps.<ItemStack, Float>newHashMap();
    
    public static BlockAlloyerRecipes getInstance() {
    	return INSTANCE;
    }
    
    public void addAlloyingRecipe(ItemStack input, ItemStack result, float experience) {
    	
    	if(input.isEmpty() || result.isEmpty())
            return;
       for(ItemStack stack : alloyingList.keySet())
           if(input.isItemEqual(stack))
               return;

           this.alloyingList.put(input.copy(), result);
           this.experienceList.put(result.copy(), experience);
    	
    }
    
    public ItemStack getAlloyingResult(ItemStack input) {
    	for(Map.Entry<ItemStack, ItemStack> entry : this.alloyingList.entrySet()) {
    		ItemStack in = entry.getKey();
    		if(in.isItemEqual(input) || (in.getItem() == input.getItem() && in.getItemDamage() == OreDictionary.WILDCARD_VALUE))
    			return entry.getValue();
    	}
    	return ItemStack.EMPTY;
    }
    
    public float getAlloyingExperience(ItemStack stack) {
    	for(Map.Entry<ItemStack, Float> entry : this.experienceList.entrySet()) {
    		ItemStack in = entry.getKey();
    		if(in.isItemEqual(stack) || (in.getItem() == stack.getItem() && in.getItemDamage() == OreDictionary.WILDCARD_VALUE))
    			return entry.getValue();
    	}
    	return 0.0F;
    }
	
}
