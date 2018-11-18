package it.hurts.metallurgy_5.util.recipe;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Maps;
import com.google.common.collect.Table;
import com.google.common.collect.Table.Cell;
import it.hurts.metallurgy_5.item.ModItems;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

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
    private final Table<ItemStack, ItemStack, ItemStack> alloyingList = HashBasedTable.create();

    private final Map<ItemStack, Float> experienceList = Maps.newHashMap();
    private final Map<ItemStack, ItemStack[]> recipeQuants = Maps.newHashMap();

    public static BlockAlloyerRecipes getInstance() {
        return INSTANCE;
    }

    private BlockAlloyerRecipes() {
        this.addAlloyRecipe
                (new ItemStack(ModItems.ingotCopper, 3),
                        new ItemStack(ModItems.ingotTin),
                        new ItemStack(ModItems.ingotBronze, 4), 1.75F);
        this.addAlloyRecipe
                (new ItemStack(ModItems.ingotShadowIron, 2),
                        new ItemStack(ModItems.ingotLemurite),
                        new ItemStack(ModItems.ingotShadowSteel, 3), 1.5F);
        this.addAlloyRecipe
                (new ItemStack(ModItems.ingotAlduorite),
                        new ItemStack(ModItems.ingotCeruclase),
                        new ItemStack(ModItems.ingotInolashite, 2), 1.25F);
        this.addAlloyRecipe
                (new ItemStack(ModItems.ingotCopper, 2),
                        new ItemStack(ModItems.ingotZinc),
                        new ItemStack(ModItems.ingotBrass, 4), 1.75F);
        this.addAlloyRecipe
                (new ItemStack(ModItems.ingotDeepIron, 3),
                        new ItemStack(ModItems.ingotInfuscolium),
                        new ItemStack(ModItems.ingotBlackSteel, 4), 1.75F);
        this.addAlloyRecipe
                (new ItemStack(Items.IRON_INGOT),
                        new ItemStack(ModItems.dustManganese, 3),
                        new ItemStack(ModItems.ingotSteel), 1.5F);
        this.addAlloyRecipe
                (new ItemStack(Items.IRON_INGOT),
                        new ItemStack(ModItems.ingotBronze, 2),
                        new ItemStack(ModItems.ingotDamascusSteel, 3), 1.5F);
        this.addAlloyRecipe
                (new ItemStack(ModItems.ingotSilver),
                        new ItemStack(Items.GOLD_INGOT),
                        new ItemStack(ModItems.ingotElectrum), 1.25F);
        this.addAlloyRecipe
                (new ItemStack(ModItems.ingotOrichalcum),
                        new ItemStack(ModItems.ingotPlatinum),
                        new ItemStack(ModItems.ingotCelenegil, 2), 1.25F);
        this.addAlloyRecipe
                (new ItemStack(ModItems.ingotKalendrite),
                        new ItemStack(ModItems.ingotPlatinum),
                        new ItemStack(ModItems.ingotAmordrine, 2), 1.25F);
        this.addAlloyRecipe(
                new ItemStack(ModItems.ingotMithril),
                new ItemStack(ModItems.ingotRubracium, 2),
                new ItemStack(ModItems.ingotHaderoth, 3), 1.5F);
        this.addAlloyRecipe
                (new ItemStack(ModItems.ingotAdamantine),
                        new ItemStack(ModItems.ingotAtlarus),
                        new ItemStack(ModItems.ingotTartarite), 1.5F); //output decreased by one for balance sake
        this.addAlloyRecipe
                (new ItemStack(ModItems.ingotEximite),
                        new ItemStack(ModItems.ingotMeutoite),
                        new ItemStack(ModItems.ingotDesichalkos, 2), 1.25F);
        this.addAlloyRecipe
                (new ItemStack(Items.IRON_INGOT),
                        new ItemStack(Items.GOLD_INGOT),
                        new ItemStack(ModItems.ingotAngmallen, 2), 1.25F);
        //Original Recipe: [Bronze + Gold] 1:1
        this.addAlloyRecipe
                (new ItemStack(ModItems.ingotInfuscolium),
                        new ItemStack(ModItems.ingotSteel),
                        new ItemStack(ModItems.ingotHepatizon, 2), 1.25F);
        this.addAlloyRecipe
                (new ItemStack(ModItems.ingotSilver),
                        new ItemStack(ModItems.ingotMithril),
                        new ItemStack(ModItems.ingotQuickSilver, 2), 1.25F);
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
}
