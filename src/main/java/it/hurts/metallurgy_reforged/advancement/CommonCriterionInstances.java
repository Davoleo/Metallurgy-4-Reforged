/*==============================================================================
 = Class: CommonCriterionInstances
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2022.
 =============================================================================*/

package it.hurts.metallurgy_reforged.advancement;

import net.minecraft.util.ResourceLocation;

public class CommonCriterionInstances {

	public static class AlwaysTrue extends BaseCriterionInstance<AlwaysTrue> {

		public AlwaysTrue(ResourceLocation triggerId)
		{
			super(triggerId);
		}

		@Override
		public boolean test(AlwaysTrue instance)
		{
			return true;
		}

	}

}
