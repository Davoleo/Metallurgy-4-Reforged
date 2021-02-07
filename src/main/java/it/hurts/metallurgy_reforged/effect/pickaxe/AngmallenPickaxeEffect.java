/*==============================================================================
 = Class: AngmallenPickaxeEffect
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.effect.pickaxe;

import it.hurts.metallurgy_reforged.block.BlockOre;
import it.hurts.metallurgy_reforged.effect.BaseMetallurgyEffect;
import it.hurts.metallurgy_reforged.effect.EnumEffectCategory;
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.util.Utils;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.stream.Collectors;

public class AngmallenPickaxeEffect extends BaseMetallurgyEffect {

    public AngmallenPickaxeEffect() {
        super(ModMetals.ANGMALLEN);
    }

    @Nonnull
    @Override
    public EnumEffectCategory getCategory() {
        return EnumEffectCategory.PICKAXE;
    }

    /**
     * handles 1/2 Chance to transmute the harvested ore into something else
     */
    @SubscribeEvent
    public void transmuteOre(BlockEvent.HarvestDropsEvent event) {

        if (!canBeApplied(event.getHarvester()))
            return;

        if (!event.getWorld().isRemote && event.getState().getBlock() instanceof BlockOre) {
            if (Utils.random.nextBoolean()) {
                event.getDrops().clear();
                ItemStack stack = getRandomOreStack((BlockOre) event.getState().getBlock());
                NBTTagCompound compound = new NBTTagCompound();
                compound.setBoolean("transmuted", true);
                stack.setTagCompound(compound);
                event.getDrops().add(stack);
                event.getWorld().playSound(null, event.getPos(), SoundEvents.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.BLOCKS, 1F, 1F);

                for (int i = 0; i < 20; i++)
                    spawnParticle(event.getWorld(), event.getPos(), 1.3F, 5, 0, 0.2D, 0);
            }
        }
    }

    /**
     * Get a random ItemStack from a BlockOre that has an harvest level of -1/+0/+1
     *
     * @param ore The broken BlockOre
     * @return a BlockOre ItemStack
     */
    private ItemStack getRandomOreStack(BlockOre ore) {
        //Loop over the metal map and filter for the right ores via Streams
        List<ItemStack> oresDropList = ModMetals.metalMap.values().stream().filter(mettle -> {
            if (ore == mettle.getOre())
                return false;
            int level = mettle.getStats().getOreHarvest();
            IBlockState state = ore.getDefaultState();
            return level >= ore.getHarvestLevel(state) - 1 && level <= ore.getHarvestLevel(state) + 1;
        }).map(mettle -> new ItemStack(mettle.getOre())).collect(Collectors.toList());
        // Map metals to ore itemStacks and collect all of them into a list

        return oresDropList.get(Utils.random.nextInt(oresDropList.size()));
    }

}
