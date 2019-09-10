/*
 * -------------------------------------------------------------------------------------------------------
 * Class: MetallurgyTraitDecay
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2019.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.integration.mods.tic.trait;

import it.hurts.metallurgy_reforged.util.Utils;
import net.minecraft.nbt.NBTTagCompound;
import slimeknights.tconstruct.library.traits.AbstractTrait;

import javax.annotation.Nullable;

public class MetallurgyTraitDecay extends AbstractTrait implements IMetallurgyTrait {

	public MetallurgyTraitDecay()
	{
		super("decay_trait", 0xFF575000);
		this.register("metallurgy.trait.decay", "metallurgy.trait.decay.tooltip");
	}

	@Override
	public void applyEffect(NBTTagCompound rootCompound, NBTTagCompound modifierTag)
	{
		super.applyEffect(rootCompound, modifierTag);
	}

	@Override
	public void register(String name, @Nullable String tooltip)
	{
		Utils.localize(String.format(LOC_Name, name));
		if (tooltip != null)
			Utils.localize(String.format(LOC_Name, tooltip));
	}

}
