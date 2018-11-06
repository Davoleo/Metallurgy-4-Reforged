package it.hurts.metallurgy_5.util.recipe;

import com.google.common.collect.Maps;
import it.hurts.metallurgy_5.block.ModBlocks;
import it.hurts.metallurgy_5.item.ModItems;
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
        addCrushingRecipe(new ItemStack(ModBlocks.oreAdamantine), new ItemStack(ModItems.dustAdamantine, 2), 0.75F);
        addCrushingRecipe(new ItemStack(ModBlocks.oreAlduorite), new ItemStack(ModItems.dustAlduorite, 2), 0.75F);
        addCrushingRecipe(new ItemStack(ModBlocks.oreAstralSilver), new ItemStack(ModItems.dustAstralSilver, 2), 0.75F);
        addCrushingRecipe(new ItemStack(ModBlocks.oreAtlarus), new ItemStack(ModItems.dustAtlarus, 2), 0.75F);
        addCrushingRecipe(new ItemStack(ModBlocks.oreCarmot), new ItemStack(ModItems.dustCarmot, 2), 0.75F);
        addCrushingRecipe(new ItemStack(ModBlocks.oreCeruclase), new ItemStack(ModItems.dustCeruclase, 2), 0.75F);
        addCrushingRecipe(new ItemStack(ModBlocks.oreCopper), new ItemStack(ModItems.dustCopper, 2), 0.75F);
        addCrushingRecipe(new ItemStack(ModBlocks.oreDeepIron), new ItemStack(ModItems.dustDeepIron, 2), 0.75F);
        addCrushingRecipe(new ItemStack(ModBlocks.oreEximite), new ItemStack(ModItems.dustEximite, 2), 0.75F);
        addCrushingRecipe(new ItemStack(ModBlocks.oreIgnatius), new ItemStack(ModItems.dustIgnatius, 2), 0.75F);
        addCrushingRecipe(new ItemStack(ModBlocks.oreInfuscolium), new ItemStack(ModItems.dustInfuscolium, 2), 0.75F);
        addCrushingRecipe(new ItemStack(ModBlocks.oreKalendrite), new ItemStack(ModItems.dustKalendrite, 2), 0.75F);
        addCrushingRecipe(new ItemStack(ModBlocks.oreLemurite), new ItemStack(ModItems.dustLemurite, 2), 0.75F);
        addCrushingRecipe(new ItemStack(ModBlocks.oreManganese), new ItemStack(ModItems.dustManganese, 2), 0.75F);
        addCrushingRecipe(new ItemStack(ModBlocks.oreMeutoite), new ItemStack(ModItems.dustMeutoite, 2), 0.75F);
        addCrushingRecipe(new ItemStack(ModBlocks.oreMidasium), new ItemStack(ModItems.dustMidasium, 2), 0.75F);
        addCrushingRecipe(new ItemStack(ModBlocks.oreMithril), new ItemStack(ModItems.dustMithril, 2), 0.75F);
        addCrushingRecipe(new ItemStack(ModBlocks.oreOrichalcum), new ItemStack(ModItems.dustOrichalcum, 2), 0.75F);
        addCrushingRecipe(new ItemStack(ModBlocks.oreOureclase), new ItemStack(ModItems.dustOureclase, 2), 0.75F);
        addCrushingRecipe(new ItemStack(ModBlocks.orePlatinum), new ItemStack(ModItems.dustPlatinum, 2), 0.75F);
        addCrushingRecipe(new ItemStack(ModBlocks.orePrometheum), new ItemStack(ModItems.dustPrometheum, 2), 0.75F);
        addCrushingRecipe(new ItemStack(ModBlocks.oreRubracium), new ItemStack(ModItems.dustRubracium, 2), 0.75F);
        addCrushingRecipe(new ItemStack(ModBlocks.oreSanguinite), new ItemStack(ModItems.dustSanguinite, 2), 0.75F);
        addCrushingRecipe(new ItemStack(ModBlocks.oreShadowIron), new ItemStack(ModItems.dustShadowIron, 2), 0.75F);
        addCrushingRecipe(new ItemStack(ModBlocks.oreSilver), new ItemStack(ModItems.dustSilver, 2), 0.75F);
        addCrushingRecipe(new ItemStack(ModBlocks.oreTin), new ItemStack(ModItems.dustTin, 2), 0.75F);
        addCrushingRecipe(new ItemStack(ModBlocks.oreVulcanite), new ItemStack(ModItems.dustVulcanite, 2), 0.75F);
        addCrushingRecipe(new ItemStack(ModBlocks.oreVyroxeres), new ItemStack(ModItems.dustVyroxeres, 2), 0.75F);
        addCrushingRecipe(new ItemStack(ModBlocks.oreZinc), new ItemStack(ModItems.dustZinc, 2), 0.75F);
        //Ingot2Dust
        addCrushingRecipe(new ItemStack(ModItems.ingotAdamantine), new ItemStack(ModItems.dustAdamantine), 0.25F);
        addCrushingRecipe(new ItemStack(ModItems.ingotAlduorite), new ItemStack(ModItems.dustAlduorite), 0.25F);
        addCrushingRecipe(new ItemStack(ModItems.ingotAmordrine), new ItemStack(ModItems.dustAmordrine), 0.25F);
        addCrushingRecipe(new ItemStack(ModItems.ingotAngmallen), new ItemStack(ModItems.dustAngmallen), 0.25F);
        addCrushingRecipe(new ItemStack(ModItems.ingotAstralSilver), new ItemStack(ModItems.dustAstralSilver), 0.25F);
        addCrushingRecipe(new ItemStack(ModItems.ingotAtlarus), new ItemStack(ModItems.dustAtlarus), 0.25F);
        addCrushingRecipe(new ItemStack(ModItems.ingotBlackSteel), new ItemStack(ModItems.dustBlackSteel), 0.25F);
        addCrushingRecipe(new ItemStack(ModItems.ingotBrass), new ItemStack(ModItems.dustBrass), 0.25F);
        addCrushingRecipe(new ItemStack(ModItems.ingotBronze), new ItemStack(ModItems.dustBronze), 0.25F);
        addCrushingRecipe(new ItemStack(ModItems.ingotCarmot), new ItemStack(ModItems.dustCarmot), 0.25F);
        addCrushingRecipe(new ItemStack(ModItems.ingotCelenegil), new ItemStack(ModItems.dustCelenegil), 0.25F);
        addCrushingRecipe(new ItemStack(ModItems.ingotCeruclase), new ItemStack(ModItems.dustCeruclase), 0.25F);
        addCrushingRecipe(new ItemStack(ModItems.ingotCopper), new ItemStack(ModItems.dustCopper), 0.25F);
        addCrushingRecipe(new ItemStack(ModItems.ingotDamascusSteel), new ItemStack(ModItems.dustDamascusSteel), 0.25F);
        addCrushingRecipe(new ItemStack(ModItems.ingotDeepIron), new ItemStack(ModItems.dustDeepIron), 0.25F);
        addCrushingRecipe(new ItemStack(ModItems.ingotDesichalkos), new ItemStack(ModItems.dustDesichalkos), 0.25F);
        addCrushingRecipe(new ItemStack(ModItems.ingotElectrum), new ItemStack(ModItems.dustElectrum), 0.25F);
        addCrushingRecipe(new ItemStack(ModItems.ingotEximite), new ItemStack(ModItems.dustEximite), 0.25F);
        addCrushingRecipe(new ItemStack(ModItems.ingotHaderoth), new ItemStack(ModItems.dustHaderoth), 0.25F);
        addCrushingRecipe(new ItemStack(ModItems.ingotHepatizon), new ItemStack(ModItems.dustHepatizon), 0.25F);
        addCrushingRecipe(new ItemStack(ModItems.ingotIgnatius), new ItemStack(ModItems.dustIgnatius), 0.25F);
        addCrushingRecipe(new ItemStack(ModItems.ingotInfuscolium), new ItemStack(ModItems.dustInfuscolium), 0.25F);
        addCrushingRecipe(new ItemStack(ModItems.ingotInolashite), new ItemStack(ModItems.dustInolashite), 0.25F);
        addCrushingRecipe(new ItemStack(ModItems.ingotKalendrite), new ItemStack(ModItems.dustKalendrite), 0.25F);
        addCrushingRecipe(new ItemStack(ModItems.ingotLemurite), new ItemStack(ModItems.dustLemurite), 0.25F);
        addCrushingRecipe(new ItemStack(ModItems.ingotManganese), new ItemStack(ModItems.dustManganese), 0.25F);
        addCrushingRecipe(new ItemStack(ModItems.ingotMeutoite), new ItemStack(ModItems.dustMeutoite), 0.25F);
        addCrushingRecipe(new ItemStack(ModItems.ingotMidasium), new ItemStack(ModItems.dustMidasium), 0.25F);
        addCrushingRecipe(new ItemStack(ModItems.ingotMithril), new ItemStack(ModItems.dustMithril), 0.25f);
        addCrushingRecipe(new ItemStack(ModItems.ingotOrichalcum), new ItemStack(ModItems.dustOrichalcum), 0.25F);
        addCrushingRecipe(new ItemStack(ModItems.ingotOureclase), new ItemStack(ModItems.dustOureclase), 0.25F);
        addCrushingRecipe(new ItemStack(ModItems.ingotPlatinum), new ItemStack(ModItems.dustPlatinum), 0.25F);
        addCrushingRecipe(new ItemStack(ModItems.ingotPrometheum), new ItemStack(ModItems.dustPrometheum), 0.25F);
        addCrushingRecipe(new ItemStack(ModItems.ingotQuickSilver), new ItemStack(ModItems.dustQuickSilver), 0.25F);
        addCrushingRecipe(new ItemStack(ModItems.ingotRubracium), new ItemStack(ModItems.dustRubracium), 0.25F);
        addCrushingRecipe(new ItemStack(ModItems.ingotSanguinite), new ItemStack(ModItems.dustSanguinite), 0.25F);
        addCrushingRecipe(new ItemStack(ModItems.ingotShadowIron), new ItemStack(ModItems.dustShadowIron), 0.25F);
        addCrushingRecipe(new ItemStack(ModItems.ingotShadowSteel), new ItemStack(ModItems.dustShadowSteel), 0.25F);
        addCrushingRecipe(new ItemStack(ModItems.ingotSilver), new ItemStack(ModItems.dustSilver), 0.25F);
        addCrushingRecipe(new ItemStack(ModItems.ingotSteel), new ItemStack(ModItems.dustSteel), 0.25F);
        addCrushingRecipe(new ItemStack(ModItems.ingotTartarite), new ItemStack(ModItems.dustTartarite), 0.25F);
        addCrushingRecipe(new ItemStack(ModItems.ingotTin), new ItemStack(ModItems.dustTin), 0.25F);
        addCrushingRecipe(new ItemStack(ModItems.ingotVulcanite), new ItemStack(ModItems.dustVulcanite), 0.25F);
        addCrushingRecipe(new ItemStack(ModItems.ingotVyroxeres), new ItemStack(ModItems.dustVyroxeres), 0.25F);
        addCrushingRecipe(new ItemStack(ModItems.ingotZinc), new ItemStack(ModItems.dustZinc), 0.25F);
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
