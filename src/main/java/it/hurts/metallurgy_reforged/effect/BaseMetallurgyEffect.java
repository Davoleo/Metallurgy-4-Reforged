/*==============================================================================
 = Class: BaseMetallurgyEffect
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2020.
 =============================================================================*/

package it.hurts.metallurgy_reforged.effect;

import com.google.common.base.CaseFormat;
import it.hurts.metallurgy_reforged.Metallurgy;
import it.hurts.metallurgy_reforged.config.EffectsConfig;
import it.hurts.metallurgy_reforged.handler.LivingEventHandler;
import it.hurts.metallurgy_reforged.item.tool.IToolEffect;
import it.hurts.metallurgy_reforged.material.Metal;
import it.hurts.metallurgy_reforged.util.EventUtils;
import it.hurts.metallurgy_reforged.util.ItemUtils;
import it.hurts.metallurgy_reforged.util.Utils;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.awt.*;
import java.lang.reflect.Field;

public abstract class BaseMetallurgyEffect {

	public final String name;
	protected Metal metal;

	public BaseMetallurgyEffect(Metal metal)
	{
		this.metal = metal;

		TextFormatting format = Utils.getSimilarMinecraftColor(new Color(metal.getStats().getColorHex()));
		this.name = format.toString() + Utils.localize("tooltip.metallurgy.effect." + metal.toString() + "_" + getCategory().toString() + ".name");

		if (isEnabled())
		{
			MetallurgyEffects.effects.add(this);
		}
	}

	public boolean isEnabled()
	{
		if (metal == null)
			return false;
		else
		{
			String camelMetal = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, metal.toString());

			try
			{
				Field enabledField = EffectsConfig.class.getDeclaredField(camelMetal + "Effect" + Utils.capitalize(getCategory().toString()));
				return enabledField.getBoolean(EffectsConfig.class);
			}
			catch (NoSuchFieldException | IllegalAccessException e)
			{
				Metallurgy.logger.warn("IF YOU SEE THIS MESSAGE | THERE'S SOMETHING WRONG WITH EFFECT CONFIG NAMES, GO FIX IT DAVOLEO");
				e.printStackTrace();
				return true;
			}
		}
	}

	public abstract EnumEffectCategory getCategory();

	public float getLevel(EntityLivingBase entity)
	{

		EnumEffectCategory category = getCategory();

		Item mainItem = entity.getHeldItemMainhand().getItem();
		Item offItem = entity.getHeldItemOffhand().getItem();

		if (category == EnumEffectCategory.ALL)
		{
			if (EventUtils.getArmorPiecesCount(entity, metal) > 0 || ItemUtils.isMadeOfMetal(metal, mainItem) || ItemUtils.isMadeOfMetal(metal, offItem))
				return 1;
			return 0;
		}

		if (category == EnumEffectCategory.ARMOR)
		{
			return EventUtils.getArmorPiecesCount(entity, metal) * 0.25F;
		}
		else if (ItemUtils.isMadeOfMetal(metal, mainItem))
		{
			IToolEffect tool = ((IToolEffect) mainItem);
			if (ArrayUtils.contains(category.getTools(), tool.getToolClass()))
			{
				if (tool.getMetalStats().getName().equals(metal.toString()))
				{
					return 1;
				}
			}
		}
		else if (ItemUtils.isMadeOfMetal(metal, offItem))
		{
			IToolEffect tool = ((IToolEffect) offItem);
			if (ArrayUtils.contains(category.getTools(), tool.getToolClass()))
			{
				if (tool.getMetalStats().getName().equals(metal.toString()))
				{
					return 1;
				}
			}
		}

		return 0;
	}


	public abstract LivingEventHandler<? extends LivingEvent>[] getEvents();


	public boolean canBeApplied(EntityLivingBase entity)
	{
		return getLevel(entity) > 0;
	}

	/**
	 * @return the entity which should have the equip metal depending on the event
	 * @see it.hurts.metallurgy_reforged.effect.weapon.AmordrineWeaponEffect
	 */
	public EntityLivingBase getEquipUserFromEvent(Event event)
	{
		if (event instanceof LivingEvent)
			return ((LivingEvent) event).getEntityLiving();
		return null;
	}

	/**
	 * @return A pair of Strings, the first containing the effect name and the second containing its description
	 */
	public Pair<String, String> getTooltip()
	{
		String description = Utils.localize("tooltip.metallurgy.effect." + metal.toString() + "_" + getCategory().toString() + ".desc");
		return ImmutablePair.of(name, description);
	}

	public Metal getMetal()
	{
		return metal;
	}

	public final String getName() {
		return name;
	}
}
