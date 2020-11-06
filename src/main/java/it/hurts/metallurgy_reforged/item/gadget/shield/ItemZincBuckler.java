package it.hurts.metallurgy_reforged.item.gadget.shield;

import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

public class ItemZincBuckler extends ItemBuckler {

    public ItemZincBuckler() {
        super("zinc_buckler", 150, 30);
    }

    @Override
    public int getMaxItemUseDuration(@Nonnull ItemStack stack) {
        return 60;
    }

    @Override
    public int getItemEnchantability() {
        return 10;
    }
}
