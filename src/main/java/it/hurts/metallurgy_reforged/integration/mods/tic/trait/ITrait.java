package it.hurts.metallurgy_reforged.integration.mods.tic.trait;

import javax.annotation.Nullable;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 25/12/2018 / 12:07
 * Interface: ITrait
 * Project: Metallurgy 4 Reforged
 * Copyright - © - Davoleo - 2018
 *
 * Info:
 * To create custom traits for TCon:
 * - Implement this interface
 * - Override the method applyEffect from AbstractTrait
 **************************************************/

public interface ITrait{

    void register(String name, @Nullable String tooltip);


    /* {
        Utils.localize(String.format(LOC_Name, getIdentifier(), name));
        if (tooltip != null)
            Utils.localize(String.format(LOC_Name, getIdentifier(), tooltip));
    }*/
}
