/*
 * -------------------------------------------------------------------------------------------------------
 * Class: MetallurgyTinkerTraits
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

public class MetallurgyTinkerTraits {

    public static final AbstractTrait obscureTrait = new MetallurgyTraitObscure();
    public static final AbstractTrait vulcanTrait = new MetallurgyTraitVulcan(0);
    public static final AbstractTrait vulcanTrait1 = new MetallurgyTraitVulcan(1);
    public static final AbstractTrait vulcanTrait2 = new MetallurgyTraitVulcan(2);
    public static final AbstractTrait witherTrait = new MetallurgyTraitWither();
    public static final AbstractTrait lifeStealTrait = new MetallurgyTraitLifeSteal();
    public static final AbstractTrait kingDiceTrait = new MetallurgyTraitKingDice();
    public static final AbstractTrait duplicaitonTrait = new MetallurgyTraitDuplication();
    public static final AbstractTrait opistognathusTrait = new MetallurgyTraitOpistognathus();

}
