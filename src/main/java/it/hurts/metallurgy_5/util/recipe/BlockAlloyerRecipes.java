package it.hurts.metallurgy_5.util.recipe;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Maps;
import com.google.common.collect.Table;
import it.hurts.metallurgy_5.item.ModItems;
import net.minecraft.item.ItemStack;

import java.util.Map;

/***************************
 *
 * Author : ItHurtsLikeHell
 * Project: Metallurgy-5
 * Date   : 27 set 2018
 * Time   : 20:48:50
 *
 * Adapted by Davoleo
 ***************************/
public class BlockAlloyerRecipes {

	private static final BlockAlloyerRecipes INSTANCE = new BlockAlloyerRecipes();
	
	private final Table<ItemStack, ItemStack, ItemStack> alloyingList = HashBasedTable.<ItemStack, ItemStack, ItemStack>create();
    private final Map<ItemStack, Float> experienceList = Maps.<ItemStack, Float>newHashMap();
    
    public static BlockAlloyerRecipes getInstance() {
    	return INSTANCE;
    }

    private BlockAlloyerRecipes()
	{
		this.addAlloyingRecipe(new ItemStack(ModItems.ingotCopper, 3), new ItemStack(ModItems.ingotTin), new ItemStack(ModItems.ingotBronze, 4), 10F);
	}

    public void addAlloyingRecipe(ItemStack input1, ItemStack input2, ItemStack result, float experience) {
    	
    	if(input1.isEmpty() || input2.isEmpty() || result.isEmpty())
            return;
    	if(getAlloyingResult(input1, input2) != ItemStack.EMPTY)
    		return;

           this.alloyingList.put(input1.copy(), input2.copy(), result);
           this.experienceList.put(result.copy(), experience);
    	
    }
    
    public ItemStack getAlloyingResult(ItemStack input1, ItemStack input2) {
    	for(Map.Entry<ItemStack, Map<ItemStack, ItemStack>> entry : this.alloyingList.columnMap().entrySet())
    	{
    		if(this.compareItemStacks(input1, entry.getKey()))
			{
				for(Map.Entry<ItemStack, ItemStack> ent : entry.getValue().entrySet())
				{
					if(this.compareItemStacks(input2, ent.getKey()))
						return ent.getValue();
				}
			}
    	}
    	return ItemStack.EMPTY;
    }

	private boolean compareItemStacks(ItemStack stack1, ItemStack stack2)
	{
		return stack2.getItem() == stack1.getItem() && (stack2.getMetadata() == 32767 || stack2.getMetadata() == stack1.getMetadata());
	}

	public Table<ItemStack, ItemStack, ItemStack> getAlloyingListTable()
	{
		return this.alloyingList;
	}

	public ItemStack[] getAlloyingListArray()
	{
		for(Map.Entry<ItemStack, Map<ItemStack, ItemStack>> entry : this.alloyingList.columnMap().entrySet())
		{
			return (ItemStack[]) entry.getValue().entrySet().toArray();
		}
		return new ItemStack[] {};
	}
    
    public float getAlloyingExperience(ItemStack stack)
	{
		for(Map.Entry<ItemStack, Float> entry : this.experienceList.entrySet())
		{
			if(this.compareItemStacks(stack, entry.getKey()))
				return entry.getValue();
		}
		return 0F;
    }
}
