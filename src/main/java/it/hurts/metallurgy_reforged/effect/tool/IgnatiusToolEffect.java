/*==============================================================================
 = Class: IgnatiusToolEffect
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2020.
 =============================================================================*/

package it.hurts.metallurgy_reforged.effect.tool;

import it.hurts.metallurgy_reforged.effect.BaseMetallurgyEffect;
import it.hurts.metallurgy_reforged.effect.EnumEffectCategory;
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.model.EnumTools;
import it.hurts.metallurgy_reforged.util.Utils;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent;

import javax.annotation.Nonnull;

public class IgnatiusToolEffect extends BaseMetallurgyEffect {

    public IgnatiusToolEffect() {
        super(ModMetals.IGNATIUS);
    }

    @Nonnull
    @Override
    public EnumEffectCategory getCategory()
    {
        return EnumEffectCategory.TOOL;
    }

    public void onBlockHarvested(BlockEvent.HarvestDropsEvent event)
    {
        if (event.getHarvester().getHeldItemMainhand().getItem().equals(metal.getTool(EnumTools.AXE)))
        {
            dropSmeltedItems(event);
        }
    }

    private void dropSmeltedItems(BlockEvent.HarvestDropsEvent event)
    {
        World world = event.getWorld();
        BlockPos pos = event.getPos();

        for (ItemStack drop : event.getDrops())
        {
            ItemStack smeltedItem = FurnaceRecipes.instance().getSmeltingResult(drop);

            if (!smeltedItem.isEmpty())
            {
                switch (event.getFortuneLevel())
                {
                    //25% chance
                    case 0:
                        if (Utils.random.nextInt(4) == 0)
                        {
                            event.setDropChance(0);
                            world.spawnEntity(new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), smeltedItem));
                        }
                        break;

                    //50% chance
                    case 1:
                        if (Utils.random.nextInt(2) == 0)
                        {
                            event.setDropChance(0);
                            world.spawnEntity(new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), smeltedItem));
                        }
                        break;

                    //75% chance
                    case 2:
                        if (Utils.random.nextInt(4) != 0)
                        {
                            event.setDropChance(0);
                            world.spawnEntity(new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), smeltedItem));
                        }
                        break;

                    //100% chance
                    case 3:
                    default:
                        event.setDropChance(0);
                        world.spawnEntity(new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), smeltedItem));
                        break;
                }
            }
        }
    }

}
