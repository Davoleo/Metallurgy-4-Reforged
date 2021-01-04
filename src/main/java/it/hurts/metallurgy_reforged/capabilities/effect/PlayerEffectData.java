/*==============================================================================
 = Class: PlayerEffectData
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.capabilities.effect;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;

import java.util.HashMap;
import java.util.Map;

public class PlayerEffectData {

    //Progressive Effects Bundles -----------------------------------
    public final Map<String, ProgressiveDataBundle> effectBundles = new HashMap<>();

    public PlayerEffectData()
    {
        effectBundles.put(brassToolBundle.getKey(), brassToolBundle);
    }

    //Amordrine Armor ----------------------------------------
    private int amordineJumps;

    public void setAmordrineJumps(int jumps)
    {
        amordineJumps = jumps;
    }

    public int getAmordrineJumps()
    {
        return amordineJumps;
    }

    public void resetAmordrineJumps()
    {
        amordineJumps = 0;
    }

    //Brass Tools
    public ProgressiveDataBundle brassToolBundle = new ProgressiveDataBundle("brass_tool", null, null, 0, 4);

    // Krik Armor --------------------------------------------
    private int krikHeight;

    public void setKrikHeight(int height)
    {
        this.krikHeight = height;
    }

    public int getKrikHeight()
    {
        return krikHeight;
    }

    public static int getKrikMaxLevel(EntityPlayer player)
    {
        int count = 0;

        for (int i = 9; i < 36; i++)
        {
            Slot k = new Slot(player.inventory, i, 0, 0);
            if (!k.getHasStack())
                count++;
        }

        return count;
    }

}
