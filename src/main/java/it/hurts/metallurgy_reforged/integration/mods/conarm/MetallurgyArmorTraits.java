package it.hurts.metallurgy_reforged.integration.mods.conarm;

import it.hurts.metallurgy_reforged.integration.mods.conarm.traits.TraitJumpMaster;
import it.hurts.metallurgy_reforged.integration.mods.conarm.traits.TraitQuickly;
import it.hurts.metallurgy_reforged.integration.mods.conarm.traits.TraitResistence;
import it.hurts.metallurgy_reforged.integration.mods.conarm.traits.TraitStrongly;
import slimeknights.tconstruct.library.traits.AbstractTrait;

public class MetallurgyArmorTraits {

	public static final AbstractTrait quickly = new TraitQuickly();
	public static final AbstractTrait strongly = new TraitStrongly();
	public static final AbstractTrait jumpMaster = new TraitJumpMaster();
	public static final AbstractTrait resistance = new TraitResistence();
	
}
