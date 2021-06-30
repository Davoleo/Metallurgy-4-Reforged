/*==============================================================================
 = Class: PlayerEffectData
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.capabilities.effect;

import com.google.common.collect.Queues;
import it.hurts.metallurgy_reforged.effect.armor.InolashiteArmorEffect;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

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
        effectBundles.put(inolashiteWeaponBundle.getPrefixKey(), inolashiteWeaponBundle);
        effectBundles.put(inolashiteArmorBundle.getPrefixKey(), inolashiteArmorBundle);
        effectBundles.put(mithrilWeaponBundle.getPrefixKey(), mithrilWeaponBundle);
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
    public BlockInfoDataBundle carmotToolBundle = new BlockInfoDataBundle("carmot_tool", null, null, 3, 14);

    //Carmot Weapons
    public ExtraFilledDataBundle carmotWeaponBundle =
            new ExtraFilledDataBundle("carmot_weapon", 3, 10, bundle -> bundle.currentStep > 0);
    //Celenegil Armor
    public ExtraFilledDataBundle celenegilArmorBundle =
            new ExtraFilledDataBundle("celenegil_armor", 3, 10, (bundle) -> bundle.getExtraBool("active"));
    //Celenegil Tools
    public ExtraFilledDataBundle celenegilToolBundle =
            new ExtraFilledDataBundle("celenegil_tool", 3, 10, bundle -> bundle.getExtraInt("broken_blocks") != 0);

    //Desichalkos Armor
    public int desichalkosAbsorbLevel = 0;
    public int desichalkosTimeWithoutTakingDamage = 200;

    //Etherium Armor
    public ProgressiveDataBundle etheriumArmorBundle = new ProgressiveDataBundle("etherium_armor", 30, 10);

    //Ignatius Armor
    public ProgressiveDataBundle ignatiusArmorBundle = new ProgressiveDataBundle("ignatius_armor", 40, 10);

    //Inolashite Weapon
    public ExtraFilledDataBundle inolashiteWeaponBundle = new ExtraFilledDataBundle("inolashite_weapon", 1, 10, bundle -> bundle.currentStep > 0);

    //Inolashite Armor
    public ExtraFilledDataBundle inolashiteArmorBundle = new ExtraFilledDataBundle("inolashite_armor", 1, 10, bundle -> bundle.currentStep > 0);
    public Queue<InolashiteArmorEffect.WarpData> inolashiteWarpData = Queues.newArrayBlockingQueue(8 * 4);

    // Krik Armor
    public int krikHeight;

    //Mithril Weapons
    public ProgressiveDataBundle mithrilWeaponBundle = new ProgressiveDataBundle("mithril_weapon", 1, 20);
}
