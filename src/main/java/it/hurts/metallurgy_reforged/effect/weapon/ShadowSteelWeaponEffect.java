/*==============================================================================
 = Class: ShadowSteelWeaponEffect
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.effect.weapon;

import it.hurts.metallurgy_reforged.capabilities.effect.ProgressiveDataBundle;
import it.hurts.metallurgy_reforged.effect.BaseMetallurgyEffect;
import it.hurts.metallurgy_reforged.effect.EnumEffectCategory;
import it.hurts.metallurgy_reforged.effect.IProgressiveEffect;
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.util.EventUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nonnull;
import java.util.UUID;
import java.util.function.IntFunction;

public class ShadowSteelWeaponEffect extends BaseMetallurgyEffect implements IProgressiveEffect {

	private static final UUID ATTACK_SPEED_UUID = UUID.fromString("91AEAB56-376B-1298-935B-2F7F68070666");

	private final IntFunction<AttributeModifier> generateAttackSpeedMod =
			darkness -> new AttributeModifier(ATTACK_SPEED_UUID, "Shadow_Steel_Attack_Speed_mod", darkness - 8, 0);

	public ShadowSteelWeaponEffect()
	{
		super(ModMetals.SHADOW_STEEL);
	}

	@Nonnull
	@Override
	public EnumEffectCategory getCategory()
	{
		return EnumEffectCategory.WEAPON;
	}

	@SubscribeEvent
	public void buffAttackDmgSpeed(LivingHurtEvent event)
	{
		Entity sourceEnt = event.getSource().getImmediateSource();
		if (sourceEnt instanceof EntityLivingBase)
		{
			EntityLivingBase attacker = ((EntityLivingBase) sourceEnt);
			if (!canBeApplied(attacker))
				return;

			float darkness = EventUtils.getDarknessLevel(attacker, 15);

			if (darkness >= 8)
			{
				if (attacker instanceof EntityPlayer)
				{
					EntityPlayer player = ((EntityPlayer) attacker);
					ProgressiveDataBundle bundle = getBundle(player, metal, getCategory());
					if (!bundle.isEffectInProgress())
					{
						attacker.getEntityAttribute(SharedMonsterAttributes.ATTACK_SPEED).removeModifier(ATTACK_SPEED_UUID);

						bundle.incrementStep(player);
						attacker.getEntityAttribute(SharedMonsterAttributes.ATTACK_SPEED)
								.applyModifier(generateAttackSpeedMod.apply(Math.round(darkness)));
					}
					player.resetCooldown();
				}


				//Unfortunately it can't be used because this event is only called server-side (for this to work I'd need to send a custom packet)
				//ObfuscationReflectionHelper.setPrivateValue(EntityLivingBase.class, attacker, Math.round(darkness), "field_184617_aD");
				//System.out.println("DARKY");
			}
			else if (darkness <= 6)
			{
				event.setAmount(event.getAmount() + (6 - darkness));
				//System.out.println("LIGHTY");
				//System.out.println("Darkness: " + darkness + " | amount: " + event.getAmount());
			}
		}
	}

	@Override
	public void onStep(World world, EntityPlayer entity, ItemStack effectStack, int maxSteps, int step)
	{
		if (step == maxSteps)
			entity.getEntityAttribute(SharedMonsterAttributes.ATTACK_SPEED).removeModifier(ATTACK_SPEED_UUID);
	}

}
