package it.hurts.metallurgy_5.container.slot;

import it.hurts.metallurgy_5.util.recipe.BlockAlloyerRecipes;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;

/*************************************************
 * Author: Davoleo
 * Date: 10/10/2018
 * Hour: 17.29
 * Project: Metallurgy_5
 * Copyright - © - Davoleo - 2018
 **************************************************/

public class SlotAlloyerOutput extends Slot {


	private final EntityPlayer player;

    public SlotAlloyerOutput(EntityPlayer player, IInventory inventory, int index, int xPos, int yPos)
    {
        super(inventory, index, xPos, yPos);
        this.player = player;
    }

    @Override
    public ItemStack onTake(EntityPlayer thePlayer, ItemStack stack)
    {
        if (!thePlayer.world.isRemote)
        {
            int i = stack.getCount();
            float f = BlockAlloyerRecipes.getInstance().getAlloyExperience(stack);

            if (f == 0.0F)
            {
                i = 0;
            }
            else if (f < 1.0F)
            {
                int j = MathHelper.floor((float)i * f);

                if (j < MathHelper.ceil((float)i * f) && Math.random() < (double)((float)i * f - (float)j))
                {
                    ++j;
                }

                i = j;
            }

            while (i > 0)
            {
                int k = EntityXPOrb.getXPSplit(i);
                i -= k;
                player.world.spawnEntity(new EntityXPOrb(player.world, player.posX, player.posY + 0.5D, player.posZ + 0.5D, k));
            }
        }

        onSlotChanged();
        net.minecraftforge.fml.common.FMLCommonHandler.instance().firePlayerSmeltedEvent(player, stack);
        return super.onTake(thePlayer, stack);
    }

    @Override
    public boolean isItemValid(ItemStack stack)
    {
        return false;
    }

}
