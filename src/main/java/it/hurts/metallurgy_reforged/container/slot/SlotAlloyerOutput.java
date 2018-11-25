package it.hurts.metallurgy_reforged.container.slot;

import it.hurts.metallurgy_reforged.util.Utils;
import it.hurts.metallurgy_reforged.util.recipe.BlockAlloyerRecipes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.FMLCommonHandler;

import javax.annotation.Nonnull;

/*************************************************
 * Author: Davoleo
 * Date: 10/10/2018
 * Hour: 17.29
 * Project: Metallurgy
 * Copyright - © - Davoleo - 2018
 **************************************************/

public class SlotAlloyerOutput extends Slot {

    private final EntityPlayer player;

    public SlotAlloyerOutput(EntityPlayer player, IInventory inventory, int index, int xPos, int yPos)
    {
        super(inventory, index, xPos, yPos);
        this.player = player;
    }

    @Nonnull
    @Override
    public ItemStack onTake(EntityPlayer thePlayer, @Nonnull ItemStack stack)
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
            Utils.giveExperience(player, i * BlockAlloyerRecipes.getInstance().getAlloyExperience(output));
        }
        FMLCommonHandler.instance().firePlayerSmeltedEvent(player, output);
    }

    @Override
    public boolean isItemValid(ItemStack stack)
    {
        return false;
    }

}
