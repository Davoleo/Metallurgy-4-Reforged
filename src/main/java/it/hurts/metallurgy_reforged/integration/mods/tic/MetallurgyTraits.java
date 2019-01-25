package it.hurts.metallurgy_reforged.integration.mods.tic;

import it.hurts.metallurgy_reforged.integration.mods.tic.trait.*;
import slimeknights.tconstruct.library.traits.AbstractTrait;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 25/12/2018 / 17:42
 * Class: MetallurgyTraits
 * Project: Metallurgy 4 Reforged
 * Copyright - © - Davoleo - 2018
 **************************************************/

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
