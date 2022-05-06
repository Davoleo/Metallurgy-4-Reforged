/*==============================================================================
 = Class: MetallurgyTrigger
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.advancement;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import net.minecraft.advancements.ICriterionTrigger;
import net.minecraft.advancements.PlayerAdvancements;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public abstract class MetallurgyTrigger<T extends BaseCriterionInstance<T>> implements ICriterionTrigger<T> {

	private final ResourceLocation resourceLocation;
	private final Map<PlayerAdvancements, Listeners<T>> listenersMap = Maps.newHashMap();

	public MetallurgyTrigger(ResourceLocation parRL)
	{
		resourceLocation = parRL;
		ModAdvancements.Triggers.ALL.add(this);
	}

	@Override
	public ResourceLocation getId()
	{
		return resourceLocation;
	}

	@Override
	public void addListener(PlayerAdvancements playerAdvancementsIn, ICriterionTrigger.Listener<T> listener)
	{
		MetallurgyTrigger.Listeners<T> metallurgyTrigger$listeners = listenersMap.get(playerAdvancementsIn);

		if (metallurgyTrigger$listeners == null)
		{
			metallurgyTrigger$listeners = new MetallurgyTrigger.Listeners<>(playerAdvancementsIn);
			listenersMap.put(playerAdvancementsIn, metallurgyTrigger$listeners);
		}

		metallurgyTrigger$listeners.add(listener);
	}

	@Override
	public void removeListener(PlayerAdvancements playerAdvancementsIn, ICriterionTrigger.Listener<T> listener)
	{
		MetallurgyTrigger.Listeners<T> metallurgyListeners = listenersMap.get(playerAdvancementsIn);

		if (metallurgyListeners != null)
		{
			metallurgyListeners.remove(listener);

			if (metallurgyListeners.isEmpty())
				listenersMap.remove(playerAdvancementsIn);
		}
	}

	@Override
	public void removeAllListeners(PlayerAdvancements playerAdvancementsIn)
	{
		listenersMap.remove(playerAdvancementsIn);
	}

	/**
	 * Deserialize a ICriterionInstance of this trigger from the data in the JSON.
	 *
	 * @param json    the json
	 * @param context the context
	 */
	@Override
	public abstract T deserializeInstance(JsonObject json, JsonDeserializationContext context);

	public void trigger(EntityPlayerMP parPlayer, T instance)
	{
		MetallurgyTrigger.Listeners<T> triggerListeners = listenersMap.get(parPlayer.getAdvancements());

		if (triggerListeners != null)
			triggerListeners.trigger(instance);
	}

	static class Listeners<T extends BaseCriterionInstance<T>> {

		private final PlayerAdvancements playerAdvancements;
		private final Set<ICriterionTrigger.Listener<T>> listeners = Sets.newHashSet();

		public Listeners(PlayerAdvancements playerAdvancementsIn)
		{
			playerAdvancements = playerAdvancementsIn;
		}

		public boolean isEmpty()
		{
			return listeners.isEmpty();
		}

		public void add(ICriterionTrigger.Listener<T> listener)
		{
			listeners.add(listener);
		}

		public void remove(ICriterionTrigger.Listener<T> listener)
		{
			listeners.remove(listener);
		}

		public void trigger(T instance)
		{
			ArrayList<ICriterionTrigger.Listener<T>> list = null;

			for (ICriterionTrigger.Listener<T> listener : listeners)
			{
				if (listener.getCriterionInstance().test(instance))
				{
					if (list == null)
					{
						list = Lists.newArrayList();
					}

					list.add(listener);
				}
			}

			if (list != null)
			{
				for (ICriterionTrigger.Listener<T> listener1 : list)
				{
					listener1.grantCriterion(playerAdvancements);
				}
			}
		}

	}

}
