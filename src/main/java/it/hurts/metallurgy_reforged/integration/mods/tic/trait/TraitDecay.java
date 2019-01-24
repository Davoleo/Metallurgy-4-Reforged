package it.hurts.metallurgy_reforged.integration.mods.tic.trait;

import it.hurts.metallurgy_reforged.util.Utils;
import net.minecraft.nbt.NBTTagCompound;
import slimeknights.tconstruct.library.traits.AbstractTrait;

import javax.annotation.Nullable;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 25/12/2018 / 17:47
 * Class: TraitDecay
 * Project: Metallurgy 4 Reforged
 * Copyright - © - Davoleo - 2018
 **************************************************/

public class TraitDecay extends AbstractTrait implements ITrait {

    public TraitDecay()
    {
        super("decay_trait", 0xFF575000);
        this.register("metallurgy.trait.decay", "metallurgy.trait.decay.tooltip");
    }

    @Override
    public void applyEffect(NBTTagCompound rootCompound, NBTTagCompound modifierTag)
    {
        super.applyEffect(rootCompound, modifierTag);
    }

    @Override
    public void register(String name, @Nullable String tooltip)
    {
        Utils.localize(String.format(LOC_Name, name));
        if (tooltip != null)
            Utils.localize(String.format(LOC_Name, tooltip));
    }
}
