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
        effectBundles.put(carmotWeaponBundle.getPrefixKey(), carmotWeaponBundle);
        effectBundles.put(carmotToolBundle.getPrefixKey(), carmotToolBundle);
        effectBundles.put(celenegilArmorBundle.getPrefixKey(), celenegilArmorBundle);
        effectBundles.put(celenegilToolBundle.getPrefixKey(), celenegilToolBundle);
        effectBundles.put(etheriumArmorBundle.getPrefixKey(), etheriumArmorBundle);
        effectBundles.put(ignatiusArmorBundle.getPrefixKey(), ignatiusArmorBundle);
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

    //Carmot Weapons
    public ExtraFilledDataBundle carmotWeaponBundle =
            new ExtraFilledDataBundle("carmot_weapon", 0, 3, bundle -> bundle.currentStep > 0);
    //Celenegil Armor
    public ExtraFilledDataBundle celenegilArmorBundle =
            new ExtraFilledDataBundle("celenegil_armor", 0, 3, (bundle) -> bundle.getExtraBool("active"));
    //Celenegil Tools
    public ExtraFilledDataBundle celenegilToolBundle =
            new ExtraFilledDataBundle("celenegil_tool", 0, 3, bundle -> bundle.getExtraInt("broken_blocks") != 0);

    //Desichalkos Armor
    public int desichalkosAbsorbLevel = 0;
    public int desichalkosTimeWithoutTakingDamage = 200;

    //Etherium Armor
    public ProgressiveDataBundle etheriumArmorBundle = new ProgressiveDataBundle("etherium_armor", 0, 30);

    //Ignatius Armor
    public ProgressiveDataBundle ignatiusArmorBundle = new ProgressiveDataBundle("ignatius_armor", 0, 40);

    // Krik Armor
    private int krikHeight;

    public void setKrikHeight(int height)
    {
        this.krikHeight = height;
    }

    public int getKrikHeight()
    {
        return krikHeight;
    }

    // TODO: 30/01/2021 Move This to the krik effect class
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
