package it.hurts.metallurgy_reforged.handler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;

public class KrickEffectHandler extends Slot{

	public KrickEffectHandler(EntityPlayer player, int index) {
		super(player.inventory, index, 0, 0);
	}
}
