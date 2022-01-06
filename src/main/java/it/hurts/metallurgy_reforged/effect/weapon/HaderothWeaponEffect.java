/*==============================================================================
 = Class: HaderothWeaponEffect
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.effect.weapon;

import it.hurts.metallurgy_reforged.effect.BaseMetallurgyEffect;
import it.hurts.metallurgy_reforged.effect.EnumEffectCategory;
import it.hurts.metallurgy_reforged.effect.MetallurgyEffects;
import it.hurts.metallurgy_reforged.material.ModMetals;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import org.apache.commons.lang3.tuple.Pair;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class HaderothWeaponEffect extends BaseMetallurgyEffect {

	public HaderothWeaponEffect()
	{
		super(ModMetals.HADEROTH);
	}

	@Nonnull
	@Override
	public EnumEffectCategory getCategory()
	{
		return EnumEffectCategory.WEAPON;
	}

	@Override
	public Pair<String, String> getTooltip()
	{
		Pair<String, String> tooltip = super.getTooltip();
		if (!MetallurgyEffects.HADEROTH_EFFECT.isEnabled())
		{
			int firstBreak = tooltip.getRight().indexOf("\n");
			String trimmed = tooltip.getRight().substring(firstBreak + 1);
			tooltip.setValue(trimmed);
		}

		return tooltip;
	}


	@SubscribeEvent
	public void handleKillStreak(LivingDeathEvent event)
	{
		Entity source = event.getSource().getImmediateSource();
		if (source instanceof EntityLivingBase && canBeApplied((EntityLivingBase) source))
		{
			ItemStack toolStack = ((EntityLivingBase) source).getHeldItemMainhand();
			NBTTagCompound compound = toolStack.getTagCompound();

			if (compound == null)
				compound = new NBTTagCompound();

			//If the main effect is active, Apex should only be enabled if the item is reborn
			if (MetallurgyEffects.HADEROTH_EFFECT.isEnabled() && !compound.getBoolean("reborn"))
				return;

			ResourceLocation targetRegName = getMobType(event.getEntity());
			if (targetRegName != null)
			{
				String killedType = compound.getString("killed_type");
				if (killedType.equals(targetRegName.toString()))
				{
					compound.setInteger("kill_count", compound.getInteger("kill_count") + 1);
				}
				else
				{
					compound.setInteger("kill_count", 1);
					compound.setString("killed_type", targetRegName.toString());
				}
			}

			//logger.info("Entity Type: {} | Kill Count: {}", compound.getString("killed_type"), compound.getInteger("kill_count"));
			toolStack.setTagCompound(compound);
		}
	}

	@SubscribeEvent
	public void applyEffectModifier(LivingHurtEvent event)
	{
		Entity source = event.getSource().getImmediateSource();

		if (source instanceof EntityLivingBase && canBeApplied((EntityLivingBase) source))
		{
			NBTTagCompound toolData = ((EntityLivingBase) source).getHeldItemMainhand().getTagCompound();

			if (toolData != null)
			{
				ResourceLocation targetType = getMobType(event.getEntity());
				int killCount = toolData.getInteger("kill_count");
				if (killCount > 0 && targetType != null && toolData.getString("killed_type").equals(targetType.toString()))
				{
					// TODO: 06/03/2021 Balance: Might be a bit to strong
					event.setAmount(Math.min(event.getAmount() + (killCount), 12F));

					for (int i = 0; i < 15; i++)
						spawnParticle(event.getEntity(), 2F, true, 5);
				}
			}
		}
	}

	/**
	 * @return The Registry name of the entity (null if invalid or some other edge case)
	 */
	@Nullable
	private ResourceLocation getMobType(Entity entity)
	{
		ResourceLocation targetRegName = null;
		for (EntityEntry entry : ForgeRegistries.ENTITIES)
		{
			if (entry.getEntityClass() == entity.getClass())
				targetRegName = ForgeRegistries.ENTITIES.getKey(entry);
		}

		return targetRegName;
	}

}
