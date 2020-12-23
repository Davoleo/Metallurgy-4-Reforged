/*==============================================================================
 = Class: MetallurgyArmorTraits
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2020.
 =============================================================================*/

package it.hurts.metallurgy_reforged.integration.conarm;

import c4.conarm.lib.traits.AbstractArmorTrait;
import it.hurts.metallurgy_reforged.integration.conarm.traits.*;

public class MetallurgyArmorTraits {

    public static final AbstractArmorTrait quickly = new TraitQuickly();
    public static final AbstractArmorTrait stronglyKalendrite = new TraitStrongly(0);
    public static final AbstractArmorTrait stronglyAmordrine = new TraitStrongly(1);
    public static final AbstractArmorTrait jumpMaster = new TraitJumpMaster();
    public static final AbstractArmorTrait resistance = new TraitResistence();
    public static final AbstractArmorTrait deeply = new TraitDeeply();
    public static final AbstractArmorTrait volcano = new TraitVolcano();
    public static final AbstractArmorTrait foodly = new TraitFoodly();
    public static final AbstractArmorTrait catEyes = new TraitCatEyes();
    public static final AbstractArmorTrait prometheum = new TraitPrometheum();
    public static final AbstractArmorTrait blindness = new TraitBlindness();

    public static final AbstractArmorTrait[] metallurgyArmorTrait = { quickly, stronglyAmordrine, stronglyKalendrite,
            jumpMaster, resistance, deeply, volcano, foodly,
            prometheum, blindness };

}
