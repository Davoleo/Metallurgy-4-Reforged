/*
 * -------------------------------------------------------------------------------------------------------
 * Class: IMetallurgyTrait
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2019.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.integration.mods.tic.trait;

import javax.annotation.Nullable;

public interface IMetallurgyTrait {

    void register(String name, @Nullable String tooltip);


    /* {
        Utils.localize(String.format(LOC_Name, getIdentifier(), name));
        if (tooltip != null)
            Utils.localize(String.format(LOC_Name, getIdentifier(), tooltip));
    }*/
}
