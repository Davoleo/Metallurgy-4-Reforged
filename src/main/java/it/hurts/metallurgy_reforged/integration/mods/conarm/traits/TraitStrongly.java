/*
 * -------------------------------------------------------------------------------------------------------
 * Class: TraitStrongly
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2019.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.integration.mods.conarm.traits;

import c4.conarm.lib.traits.AbstractArmorTraitLeveled;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.traits.ITrait;
import slimeknights.tconstruct.library.utils.TagUtil;

public class TraitStrongly extends AbstractArmorTraitLeveled implements IConarmMetallurgyTrait{

	private int level;

	public TraitStrongly(int level) {
		super("strongly", String.valueOf(level), 0xffffff, 2, level);
		this.level = level;
	}

	public int getLevel()
	{
		return level;
	}

	@SubscribeEvent
	public void onArmorTick(PlayerTickEvent event)
	{
		for (ItemStack armorPiece : event.player.getArmorInventoryList()) {
			NBTTagList traits = TagUtil.getTraitsTagList(armorPiece);
			for (int i = 0; i < traits.tagCount(); i++) {
				String id = traits.getStringTagAt(i);
				ITrait trait = TinkerRegistry.getTrait(id);

				if (trait instanceof TraitStrongly)
					event.player.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 40, ((TraitStrongly) trait).getLevel(), false, false));
			}
		}
	}

}
