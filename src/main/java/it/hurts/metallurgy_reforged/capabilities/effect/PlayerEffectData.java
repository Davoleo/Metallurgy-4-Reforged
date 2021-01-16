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
import net.minecraft.nbt.NBTTagByte;
import net.minecraft.nbt.NBTTagCompound;

import java.util.HashMap;
import java.util.Map;

public class PlayerEffectData {

    //Progressive Effects Bundles -----------------------------------
    public final Map<String, ProgressiveDataBundle> effectBundles = new HashMap<>();

    public PlayerEffectData()
    {
        effectBundles.put(carmotToolBundle.getKey(), carmotToolBundle);
        effectBundles.put(celenegilToolBundle.getKey(), celenegilToolBundle);
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

    //Carmot Tools
    public BlockInfoDataBundle carmotToolBundle = new BlockInfoDataBundle("carmot_tool", null, null, 0, 4);

    //Celenegil Armor
    public ExtraFilledDataBundle<NBTTagByte> celenegilArmorBundle =
            new ExtraFilledDataBundle<NBTTagByte>("celenegil_armor", 0, 3, new NBTTagByte((byte) 0)) {
                @Override
                public boolean isEffectInProgress()
                {
                    return getExtra().getByte() > 0;
                }
            };

    //Celenegil Tools ----------------------------------------
    public ExtraFilledDataBundle<NBTTagCompound> celenegilToolBundle =
            new ExtraFilledDataBundle<NBTTagCompound>("celenegil_tool", 0, 3, new NBTTagCompound()) {
                @Override
                public boolean isEffectInProgress()
                {
                    return getExtra().getInteger("broken_blocks") != 0;
                }
            };

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
