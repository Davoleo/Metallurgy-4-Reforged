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
import it.hurts.metallurgy_reforged.item.tool.IToolEffect;
import it.hurts.metallurgy_reforged.material.Metal;
import it.hurts.metallurgy_reforged.util.EventUtils;
import it.hurts.metallurgy_reforged.util.Utils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.entity.living.LivingEvent;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.awt.*;
import java.lang.reflect.Field;
import java.util.function.Consumer;

public abstract class BaseMetallurgyEffect {

	protected Metal metal;

	public BaseMetallurgyEffect(Metal metal)
	{
		this.metal = metal;

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

	public float getLevel(EntityPlayer player)
	{

		EnumEffectCategory category = getCategory();

		if (category == EnumEffectCategory.ALL)
		{
			if (EventUtils.getArmorPiecesCount(player, metal.getArmorSet()) > 0 || player.getHeldItemMainhand().getItem() instanceof IToolEffect)
			{
				return 1;
			}
		}

		if (category == EnumEffectCategory.ARMOR)
		{
			return EventUtils.getArmorPiecesCount(player, metal.getArmorSet()) * 0.25F;
		}
		else if (player.getHeldItemMainhand().getItem() instanceof IToolEffect)
		{
			IToolEffect tool = ((IToolEffect) player.getHeldItemMainhand().getItem());

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


	public abstract EventInstance<? extends LivingEvent>[] getEvents();


	public boolean canBeApplied(EntityPlayer player)
	{
		return getLevel(player) > 0;
	}

	/**
	 * @return A pair of Strings, the first containing the effect name and the second containing its description
	 */
	public Pair<String, String> getTooltip()
	{
		TextFormatting format = Utils.getSimilarMinecraftColor(new Color(metal.getStats().getColorHex()));
		String name = format.toString() + Utils.localize("tooltip.metallurgy.effect." + metal.toString() + "_" + getCategory().toString() + ".name");
		String description = Utils.localize("tooltip.metallurgy.effect." + metal.toString() + "_" + getCategory().toString() + ".desc");
		return ImmutablePair.of(name, description);
	}

	public Metal getMetal()
	{
		return metal;
	}


	public static class EventInstance<E extends LivingEvent> {

		private final Consumer<E> consumer;
		private final Class<E> tClass;

		public EventInstance(Consumer<E> consumer, Class<E> tClass)
		{
			this.consumer = consumer;
			this.tClass = tClass;
		}

		public Consumer<E> getConsumer()
		{
			return consumer;
		}

		public boolean canApply(LivingEvent event)
		{
			return event.getClass().equals(tClass);
		}

	}


}
