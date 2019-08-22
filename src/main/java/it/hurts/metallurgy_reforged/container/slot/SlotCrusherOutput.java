/*
 * -------------------------------------------------------------------------------------------------------
 * Class: SlotCrusherOutput
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2019.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.container.slot;

import it.hurts.metallurgy_reforged.util.Utils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.hooks.BasicEventHooks;

import javax.annotation.Nonnull;

public class SlotCrusherOutput extends Slot {

    private final PlayerEntity player;

    public SlotCrusherOutput(PlayerEntity player, IInventory inventory, int index, int xPos, int yPos)
    {
        super(inventory, index, xPos, yPos);
        this.player = player;
    }

    @Override
    public boolean isItemValid(ItemStack stack)
    {
        return false;
    }

    @Nonnull
    @Override
    public ItemStack onTake(PlayerEntity thePlayer, @Nonnull ItemStack stack)
    {
        onCrafting(stack);
        return super.onTake(thePlayer, stack);
    }

    @Override
    protected void onCrafting(ItemStack output)
    {
        if (!player.world.isRemote)
        {
            int i = output.getCount();
            output.onCrafting(player.world, player, i);
            //Utils.giveExperience(player, i * BlockCrusherRecipes.getInstance().getCrushingExperience(output));
        }
        BasicEventHooks.firePlayerSmeltedEvent(this.player, output);
    }
}