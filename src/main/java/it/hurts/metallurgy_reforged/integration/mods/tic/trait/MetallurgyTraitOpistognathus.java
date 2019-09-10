/*
 * -------------------------------------------------------------------------------------------------------
 * Class: MetallurgyTraitOpistognathus
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2019.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.integration.mods.tic.trait;

import it.hurts.metallurgy_reforged.integration.mods.tic.MetallurgyTinkerTraits;
import it.hurts.metallurgy_reforged.util.Utils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerEvent;
import slimeknights.tconstruct.library.traits.AbstractTrait;

import javax.annotation.Nullable;
import java.util.UUID;

public class MetallurgyTraitOpistognathus extends AbstractTrait implements IMetallurgyTrait {

	public static final UUID DEEP_IRON_SWORD_TRAIT_MODIFIER_UUID = UUID.fromString("8bfd3581-6559-468f-a5a5-66c46ff7b70c");

	public MetallurgyTraitOpistognathus()
	{
		super("opistognathus_trait", TextFormatting.DARK_AQUA);
	}

	@Override
	public void miningSpeed(ItemStack tool, PlayerEvent.BreakSpeed event)
	{
		if (event.getEntity().isInWater()
				&& MetallurgyTinkerTraits.isMetallurgyTrait(event.getEntityPlayer(), "opistognathus"))
			event.setNewSpeed(event.getOriginalSpeed() * 3);
	}

	@Override
	public void register(String name, @Nullable String tooltip)
	{
		Utils.localize(String.format(LOC_Name, name));
		if (tooltip != null)
			Utils.localize(String.format(LOC_Name, tooltip));
	}

	@Override
	public void onUpdate(ItemStack tool, World world, Entity entity, int itemSlot, boolean isSelected)
	{
		IAttributeInstance instance;

		if (entity instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) entity;
			instance = player.getEntityAttribute(SharedMonsterAttributes.ATTACK_SPEED);

			if (isSelected && MetallurgyTinkerTraits.isMetallurgyTrait(player, "opistognathus"))
			{
				AttributeModifier deep_iron_trait_modifier = new AttributeModifier(DEEP_IRON_SWORD_TRAIT_MODIFIER_UUID, "Deep Iron SwordTrait Modifier", 2.7, 0);

				if (player.isInWater() && instance.getModifier(DEEP_IRON_SWORD_TRAIT_MODIFIER_UUID) == null)
				{

					instance.applyModifier(deep_iron_trait_modifier);

				}
				else
				{
					if (instance.getModifier(DEEP_IRON_SWORD_TRAIT_MODIFIER_UUID) != null && !player.isInWater())
					{
						instance.removeModifier(DEEP_IRON_SWORD_TRAIT_MODIFIER_UUID);
					}
				}
			}
			else
			{
				if (instance.getModifier(DEEP_IRON_SWORD_TRAIT_MODIFIER_UUID) != null)
					instance.removeModifier(DEEP_IRON_SWORD_TRAIT_MODIFIER_UUID);
			}
		}
	}

}
