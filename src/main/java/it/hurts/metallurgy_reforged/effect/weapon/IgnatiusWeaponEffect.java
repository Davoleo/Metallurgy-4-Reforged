/*==============================================================================
 = Class: IgnatiusWeaponEffect
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.effect.weapon;

import it.hurts.metallurgy_reforged.effect.BaseMetallurgyEffect;
import it.hurts.metallurgy_reforged.effect.EnumEffectCategory;
import it.hurts.metallurgy_reforged.effect.MetallurgyEffects;
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.util.Utils;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.stream.Collectors;

public class IgnatiusWeaponEffect extends BaseMetallurgyEffect {

    public IgnatiusWeaponEffect()
    {
        super(ModMetals.IGNATIUS);
    }

    @Nonnull
    @Override
    public EnumEffectCategory getCategory()
    {
        return EnumEffectCategory.WEAPON;
    }

    @SubscribeEvent
    public void smeltLoot(LivingDropsEvent event)
    {
        //Map Entity Items drops to ItemStacks and manipulate them with the function from the other effect
        List<ItemStack> dropStacks = event.getDrops().stream().map(EntityItem::getItem).collect(Collectors.toList());
        boolean hadEffect = MetallurgyEffects.ignatiusToolEffect.dropSmeltedItems(dropStacks, event.getLootingLevel());

        //If auto-smelt was applied -> replace all the stacks of the entity items with the smelted ones
        if (hadEffect)
        {
            for (int i = 0; i < dropStacks.size(); i++)
                event.getDrops().get(i).setItem(dropStacks.get(i));

            Utils.repeat(15, () -> {
                float f1 = (Utils.random.nextFloat() / 16F) - 0.03125F;
                float f2 = (Utils.random.nextFloat() / 16F) - 0.03125F;
                spawnParticle(event.getEntity().world, event.getEntity().getPosition(), 2F, true, 3, f1, 0.04, f2);
            });
        }
    }
}
