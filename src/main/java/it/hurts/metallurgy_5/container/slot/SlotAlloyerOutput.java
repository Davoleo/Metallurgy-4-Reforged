package it.hurts.metallurgy_5.container.slot;

import it.hurts.metallurgy_5.util.Utils;
import it.hurts.metallurgy_5.util.recipe.BlockAlloyerRecipes;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.common.FMLCommonHandler;

/*************************************************
 * Author: Davoleo
 * Date: 10/10/2018
 * Hour: 17.29
 * Project: Metallurgy_5
 * Copyright - © - Davoleo - 2018
 **************************************************/

public class SlotAlloyerOutput extends Slot {

    private final EntityPlayer player;

    public SlotAlloyerOutput(EntityPlayer player, IInventory inventory, int index, int xPos, int yPos) {
        super(inventory, index, xPos, yPos);
        this.player = player;
    }

    @Override
    public ItemStack onTake(EntityPlayer thePlayer, ItemStack stack) {
        onCrafting(stack);
        return super.onTake(thePlayer, stack);
    }

    @Override
    protected void onCrafting(ItemStack output) {
        if (!player.world.isRemote) {
            int i = output.getCount();
            output.onCrafting(player.world, player, i);
            Utils.giveExperience(player, i * BlockAlloyerRecipes.getInstance().getAlloyExperience(output));
        }
        FMLCommonHandler.instance().firePlayerSmeltedEvent(player, output);
    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        return false;
    }

}
