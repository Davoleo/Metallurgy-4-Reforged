package it.hurts.metallurgy_reforged.recipe;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.google.common.collect.Table.Cell;

import it.hurts.metallurgy_reforged.material.ModMetals;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;

public class BlockSublimationRecipes {
	
	private static final BlockSublimationRecipes INSTANCE = new BlockSublimationRecipes();
    private final Map<ItemStack, Potion> sublimationList = new HashMap<>();
    
    public static BlockSublimationRecipes getInstance() {
        return INSTANCE;
    }

    private BlockSublimationRecipes() {
    	
    	this.addSublimationRecipe(new ItemStack(ModMetals.ADAMANTINE.getBlock(), 3), MobEffects.SATURATION);
    	
    }
    
    private void addSublimationRecipe(ItemStack input, Potion potion) {

        if (input.isEmpty() || potion != null)
            return;
        if (getSublimationResult(input) != null)
            return;

        this.sublimationList.put(input, potion);
    }
    
    public Potion getSublimationResult(ItemStack input) {
        for(Entry<ItemStack, Potion> entry : this.sublimationList.entrySet()) {
            if(this.compareItemStacks(entry.getKey(), input))
                return entry.getValue();
        }
        
        return null;
    }
    
    public boolean isSublimationBlock(ItemStack input) {
    	for(Entry<ItemStack, Potion> entry : this.sublimationList.entrySet()) {
            if(this.compareItemStacks(entry.getKey(), input))
                return true;
        }
    	
        return false;
    }
    
    public Map<ItemStack, Potion> getRecipeTable()
    {
        return sublimationList;
    }
    
    private boolean compareItemStacks(ItemStack stack1, ItemStack stack2) {
        return stack2.getItem() == stack1.getItem() && (stack2.getMetadata() == 32767 || stack2.getMetadata() == stack1.getMetadata());
    }
    
}
