/*==============================================================================
 = Class: ShadowSteelArmorEffect
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.effect.armor;

import it.hurts.metallurgy_reforged.capabilities.effect.ExtraFilledDataBundle;
import it.hurts.metallurgy_reforged.effect.BaseMetallurgyEffect;
import it.hurts.metallurgy_reforged.effect.EnumEffectCategory;
import it.hurts.metallurgy_reforged.effect.IProgressiveEffect;
import it.hurts.metallurgy_reforged.item.armor.ItemArmorBase;
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.util.EventUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.function.IntFunction;

public class ShadowSteelArmorEffect extends BaseMetallurgyEffect implements IProgressiveEffect {

	private static final UUID SPEED_MODIFIER_UUID = UUID.fromString("91AEAB56-376B-1298-935B-2F7F68070635");
	private final IntFunction<AttributeModifier> generateSpeedModifier = (level) ->
			new AttributeModifier(SPEED_MODIFIER_UUID, "SHADOW_STEEL_Armor_Movement_Buff", 0.1 * level, 2);

	public ShadowSteelArmorEffect()
	{
		super(ModMetals.SHADOW_STEEL);
	}

	@Nonnull
	@Override
	public EnumEffectCategory getCategory()
	{
		return EnumEffectCategory.ARMOR;
	}

	@SubscribeEvent
	public void triggerEclipse(LivingHurtEvent event)
	{
		EntityLivingBase entity = event.getEntityLiving();
		int level = getLevel(entity);
		if (level == 0)
			return;

		float darknessLevel = EventUtils.getDarknessLevel(entity, 1);

		if (entity instanceof EntityPlayer)
		{
			float cooldown = ((EntityPlayer) entity).getCooldownTracker().getCooldown(getArmorRepr(entity).getItem(), 0);
			if (cooldown > 0)
				return;

			//Evade damage during dark effect duration
			ExtraFilledDataBundle bundle = getEffectCapability((EntityPlayer) entity).shadowSteelArmorBundle;
			boolean darkEffectTookPlace = bundle.getExtraBool("dark");
			if (darkEffectTookPlace && bundle.isEffectInProgress())
			{
				event.setCanceled(true);
				return;
			}
		}

		//Dark Effect
		if (darknessLevel > 0.5F)
		{
			//Generate and apply speed modifier
			AttributeModifier modifier = generateSpeedModifier.apply(level);
			entity.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).applyModifier(modifier);

			if (entity instanceof EntityPlayer)
			{
				final EntityPlayer player = (EntityPlayer) entity;
				ExtraFilledDataBundle bundle = (ExtraFilledDataBundle) getBundle(player, metal, getCategory());
				//Kickstart dark effect timer
				bundle.incrementStep(player);
				bundle.setExtra("dark", true);
			}
		}
		else
		{
			//Light Effect
			//Get Entities within range
			List<Entity> nearbyEntities = entity.world.getEntitiesInAABBexcluding(entity,
					new AxisAlignedBB(entity.posX - 3, entity.posY - 1, entity.posZ - 3,
							entity.posX + 3, entity.posY + 1, entity.posZ + 3),
					ent -> ent instanceof EntityLivingBase);
			//Damage Entities From fire Damage
			nearbyEntities.forEach(nearby -> {
				nearby.attackEntityFrom(DamageSource.IN_FIRE, 3 * level);
				((EntityLivingBase) nearby).addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 20 + 10 * level));
			});
		}
	}

	@Override
	public void onStep(World world, EntityPlayer entity, ItemStack effectStack, int maxSteps, int step)
	{
		if (step == maxSteps)
		{

			if (getEffectCapability(entity).shadowSteelArmorBundle.getExtraBool("dark"))
				entity.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).removeModifier(SPEED_MODIFIER_UUID);

			for (ItemArmorBase armorP : Objects.requireNonNull(metal.getArmorSet()))
				entity.getCooldownTracker().setCooldown(armorP, 20 * (30 - 5 * getLevel(entity)));
		}
	}

}
