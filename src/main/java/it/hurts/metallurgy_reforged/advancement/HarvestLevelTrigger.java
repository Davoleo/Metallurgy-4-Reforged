/*==============================================================================
 = Class: HarvestLevelTrigger
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.advancement;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import it.hurts.metallurgy_reforged.Metallurgy;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;

public class HarvestLevelTrigger extends MetallurgyTrigger<HarvestLevelTrigger.Instance> {

	private static final ResourceLocation ID = new ResourceLocation(Metallurgy.MODID, "harvest_level");

	public HarvestLevelTrigger()
	{
		super(ID);
	}

	@Nonnull
	@Override
	public Instance deserializeInstance(@Nonnull JsonObject json, @Nonnull JsonDeserializationContext context)
	{
		int jsonLevel = JsonUtils.getInt(json, "level", 100);
		return new Instance(jsonLevel);
	}

	public static class Instance extends BaseCriterionInstance<Instance> {

		private final int level;

		public Instance(int level)
		{
			super(ID);
			this.level = level;
		}

		@Override
		public boolean test(Instance instance)
		{
			return instance.level == level;
		}

	}

}
