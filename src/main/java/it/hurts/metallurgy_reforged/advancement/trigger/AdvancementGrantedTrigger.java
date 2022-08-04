/*==============================================================================
 = Class: AdvancementGrantedTrigger
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2022.
 =============================================================================*/

package it.hurts.metallurgy_reforged.advancement.trigger;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import it.hurts.metallurgy_reforged.Metallurgy;
import it.hurts.metallurgy_reforged.advancement.BaseCriterionInstance;
import it.hurts.metallurgy_reforged.advancement.MetallurgyTrigger;
import net.minecraft.util.ResourceLocation;

public class AdvancementGrantedTrigger extends MetallurgyTrigger<AdvancementGrantedTrigger.Instance> {

	private static final ResourceLocation ID = new ResourceLocation(Metallurgy.MODID, "advancement_granted");

	public AdvancementGrantedTrigger()
	{
		super(ID);
	}

	@Override
	public Instance deserializeInstance(JsonObject json, JsonDeserializationContext context)
	{
		String advName = json.getAsJsonPrimitive("advancement").getAsString();
		return new Instance(new ResourceLocation(advName));
	}

	public static class Instance extends BaseCriterionInstance<Instance> {

		private final ResourceLocation advancement;

		public Instance(ResourceLocation advancement)
		{
			super(ID);
			this.advancement = advancement;
		}

		@Override
		public boolean test(Instance instance)
		{
			return instance.advancement.equals(this.advancement);
		}

	}

}
