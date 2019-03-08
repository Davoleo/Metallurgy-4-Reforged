package it.hurts.metallurgy_reforged.integration.mods.conarm;

import it.hurts.metallurgy_reforged.integration.mods.conarm.traits.TraitCatEyes;
import it.hurts.metallurgy_reforged.integration.mods.conarm.traits.TraitDeeply;
import it.hurts.metallurgy_reforged.integration.mods.conarm.traits.TraitFoodly;
import it.hurts.metallurgy_reforged.integration.mods.conarm.traits.TraitJumpMaster;
import it.hurts.metallurgy_reforged.integration.mods.conarm.traits.TraitPrometheum;
import it.hurts.metallurgy_reforged.integration.mods.conarm.traits.TraitQuickly;
import it.hurts.metallurgy_reforged.integration.mods.conarm.traits.TraitResistence;
import it.hurts.metallurgy_reforged.integration.mods.conarm.traits.TraitStrongly;
import it.hurts.metallurgy_reforged.integration.mods.conarm.traits.TraitVolcano;
import slimeknights.tconstruct.library.traits.AbstractTrait;

public class MetallurgyArmorTraits {

	public static final AbstractTrait quickly = new TraitQuickly();
	public static final AbstractTrait stronglyKalendrite = new TraitStrongly(0);
	public static final AbstractTrait stronglyAmordrine = new TraitStrongly(1);
	public static final AbstractTrait jumpMaster = new TraitJumpMaster();
	public static final AbstractTrait resistance = new TraitResistence();
	public static final AbstractTrait deeply = new TraitDeeply();
	public static final AbstractTrait volcano = new TraitVolcano();
	public static final AbstractTrait foodly = new TraitFoodly();
	public static final AbstractTrait catEyes = new TraitCatEyes();
	public static final AbstractTrait prometheum = new TraitPrometheum();
	
}
