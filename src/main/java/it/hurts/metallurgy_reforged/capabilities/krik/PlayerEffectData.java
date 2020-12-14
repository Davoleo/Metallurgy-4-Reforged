/*==============================================================================
 = Class: PlayerEffectData
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2020.
 =============================================================================*/

package it.hurts.metallurgy_reforged.capabilities.krik;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;

public class PlayerEffectData {

	//Amordrine Section --------------------------------------
	private int amordineJumps;

	public void setAmordrineJumps(int jumps) {
		amordineJumps = jumps;
	}

	public int getAmordrineJumps() {
		return amordineJumps;
	}

	public void resetAmordrineJumps() {
		amordineJumps = 0;
	}

	// Krik Section ---------------------------------------
	private int krikHeight;

	public void setKrikHeight(int height) {
		this.krikHeight = height;
	}

	public int getKrikHeight() {
		return krikHeight;
	}

	public static int getKrikMaxLevel(EntityPlayer player) {

		int count = 0;

		for (int i = 9; i < 36; i++) {
			Slot k = new Slot(player.inventory, i, 0, 0);
			if (!k.getHasStack())
				count++;
		}

		return count;
	}

}
