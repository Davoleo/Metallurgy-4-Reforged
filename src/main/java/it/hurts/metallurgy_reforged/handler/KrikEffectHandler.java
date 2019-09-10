/*
 * -------------------------------------------------------------------------------------------------------
 * Class: KrikEffectHandler
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2019.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.handler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;

public class KrikEffectHandler extends Slot {

	public KrikEffectHandler(EntityPlayer player, int index)
	{
		super(player.inventory, index, 0, 0);
	}

}
