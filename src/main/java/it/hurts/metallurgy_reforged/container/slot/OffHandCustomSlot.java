/*
 * -------------------------------------------------------------------------------------------------------
 * Class: OffHandCustomSlot
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2019.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.container.slot;

import javax.annotation.Nullable;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.Slot;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class OffHandCustomSlot extends Slot {

	public PlayerEntity player;
	  
	public OffHandCustomSlot(PlayerEntity pl) {
		super(pl.inventory, 40, 77, 62);
	}
	
	@Nullable
	@OnlyIn(Dist.CLIENT)
    public String getSlotTexture(){
        return "minecraft:items/empty_armor_slot_shield";
    }
	
	 public boolean canTakeStack(PlayerEntity playerIn){
		 return false;
	}
}
