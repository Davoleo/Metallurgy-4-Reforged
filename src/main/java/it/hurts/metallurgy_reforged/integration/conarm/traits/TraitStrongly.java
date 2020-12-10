/*==============================================================================
 = Class: TraitStrongly
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2020.
 =============================================================================*/

package it.hurts.metallurgy_reforged.integration.conarm.traits;

import c4.conarm.lib.traits.AbstractArmorTraitLeveled;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.traits.ITrait;
import slimeknights.tconstruct.library.utils.TagUtil;
import slimeknights.tconstruct.library.utils.ToolHelper;

public class TraitStrongly extends AbstractArmorTraitLeveled implements IConarmMetallurgyTrait {

	private int level;

	public TraitStrongly(int level)
	{
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
		if (hasValidStregthTrait(event.player))
			event.player.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 40, level, false, false));
	}

	private boolean hasValidStregthTrait(EntityPlayer player)
	{
		for (ItemStack armorPiece : player.getArmorInventoryList())
		{
			if (ToolHelper.isBroken(armorPiece))
				continue;

			NBTTagList traits = TagUtil.getTraitsTagList(armorPiece);
			for (int i = 0; i < traits.tagCount(); i++)
			{
				String id = traits.getStringTagAt(i);
				ITrait trait = TinkerRegistry.getTrait(id);

				if (trait instanceof TraitStrongly)
					return true;
			}
		}

		return false;
	}

}
