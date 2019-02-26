/*
 * -------------------------------------------------------------------------------------------------------
 * Class: MetallurgyTraits
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2019.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.integration.mods.tic;

import it.hurts.metallurgy_reforged.integration.mods.tic.trait.*;
import slimeknights.tconstruct.library.traits.AbstractTrait;

public class MetallurgyTraits {

    public static final AbstractTrait obscureTrait = new TraitObscure();
    public static final AbstractTrait vulcanTrait = new TraitVulcan(0);
    public static final AbstractTrait vulcanTrait1 = new TraitVulcan(1);
    public static final AbstractTrait vulcanTrait2 = new TraitVulcan(2);
    public static final AbstractTrait witherTrait = new TraitWither();
    public static final AbstractTrait lifeStealTrait = new TraitLifeSteal();
    public static final AbstractTrait kingDiceTrait = new TraitKingDice();
    public static final AbstractTrait duplicaitonTrait = new TraitDuplication();

}
