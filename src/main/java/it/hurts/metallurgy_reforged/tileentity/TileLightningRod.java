/*
 * -------------------------------------------------------------------------------------------------------
 * Class: TileLightningRod
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2019.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.tileentity;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;

public class TileLightningRod extends TileEntity implements ITickable {

    private int delay = 0;
    private boolean isActive;


    public TileLightningRod()
    {

    }

    @Override
    public void update()
    {

    }

    public boolean isActive()
    {
        return isActive;
    }
}
