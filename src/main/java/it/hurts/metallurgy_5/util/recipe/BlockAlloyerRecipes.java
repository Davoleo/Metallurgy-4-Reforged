package it.hurts.metallurgy_5.util.recipe;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Maps;
import com.google.common.collect.Table;
import it.hurts.metallurgy_5.item.ModItems;
import net.minecraft.item.ItemStack;

import java.util.Map;
import java.util.Map.Entry;

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
	private final Map<ItemStack, ItemStack[]> recipeQuants = Maps.<ItemStack, ItemStack[]>newHashMap();
    
    public static BlockAlloyerRecipes getInstance() {
    	return INSTANCE;
    }

    private BlockAlloyerRecipes()
	{
		this.addAlloyRecipe(new ItemStack(ModItems.ingotCopper, 3), new ItemStack(ModItems.ingotTin), new ItemStack(ModItems.ingotBronze, 4), 10F);
	}

    public void addAlloyRecipe(ItemStack input1, ItemStack input2, ItemStack result, float experience) {
    	
    	if(input1.isEmpty() || input2.isEmpty() || result.isEmpty())
            return;
    	if(getAlloyResult(input1, input2) != ItemStack.EMPTY)
    		return;

        this.alloyingList.put(input1, input2, result);
        this.experienceList.put(result, Float.valueOf(experience));
        this.recipeQuants.put(result, new ItemStack[] {input1, input2});
	}
    
    public ItemStack getAlloyResult(ItemStack input1, ItemStack input2)
	{
            for (Entry<ItemStack, Map<ItemStack, ItemStack>> entry : this.alloyingList.columnMap().entrySet())
            {
                if (this.compareItemStacks(input1, entry.getKey()))
                {
                    for (Entry<ItemStack, ItemStack> ent : entry.getValue().entrySet())
                    {
                        if (this.compareItemStacks(input2, ent.getKey()))
                        {
                            return ent.getValue();
                        }
                    }
                }
            }

            for (Entry<ItemStack, Map<ItemStack, ItemStack>> entry : this.alloyingList.columnMap().entrySet())
        {
            if (this.compareItemStacks(input2, entry.getKey()))
            {
                for (Entry<ItemStack, ItemStack> ent : entry.getValue().entrySet())
                {
                    if (this.compareItemStacks(input1, ent.getKey()))
                    {
                        return ent.getValue();
                    }
                }
            }
        }

        return ItemStack.EMPTY;
	}

	private boolean compareItemStacks(ItemStack stack1, ItemStack stack2)
	{
		return stack2.getItem() == stack1.getItem() && (stack2.getMetadata() == 32767 || stack2.getMetadata() == stack1.getMetadata());
	}

	public Table<ItemStack, ItemStack, ItemStack> getAlloyListTable()
	{
		return alloyingList;
	}

	public Map<ItemStack, Float> getExperienceList()
    {
        return experienceList;
    }
    
    public float getAlloyExperience(ItemStack stack)
	{
	    if(this.experienceList.containsKey(stack))
	        return this.experienceList.get(stack).floatValue();
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

    public Map<ItemStack, ItemStack[]> getRecipeQuants()
    {
        return recipeQuants;
    }

}
