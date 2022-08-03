/*==============================================================================
 = Class: JackOfAllTradesTrigger
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
import it.hurts.metallurgy_reforged.advancement.CommonCriterionInstances;
import it.hurts.metallurgy_reforged.advancement.MetallurgyTrigger;
import net.minecraft.util.ResourceLocation;

public class JackOfAllTradesTrigger extends MetallurgyTrigger<CommonCriterionInstances.AlwaysTrue> {

	private static final ResourceLocation ID = new ResourceLocation(Metallurgy.MODID, "jack_of_all_trades");

	public JackOfAllTradesTrigger()
	{
		super(ID);
	}

	@Override
	public CommonCriterionInstances.AlwaysTrue deserializeInstance(JsonObject json, JsonDeserializationContext context)
	{
		return new CommonCriterionInstances.AlwaysTrue(ID);
	}

}
