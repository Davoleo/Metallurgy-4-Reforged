/*==============================================================================
 = Class: ModAdvancements
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.advancement;

import net.minecraft.advancements.CriteriaTriggers;

import java.util.HashSet;
import java.util.Set;

public class ModAdvancements {

	public static class Triggers {

		static final Set<MetallurgyTrigger<?>> ALL = new HashSet<>();

		public static final HarvestLevelTrigger BREAK_ORE_TIER_TRIGGER = new HarvestLevelTrigger();

	}

	public static void registerTriggers()
	{
		Triggers.ALL.forEach(CriteriaTriggers::register);
	}

	public static void setupTierAdvancementIcons()
	{

		//ItemPickaxeBase copperPick = ModMetals.COPPER.getTool(EnumTools.PICKAXE);
		//copperPick.addPropertyOverride(new ResourceLocation("tier_showcase"), (stack, worldIn, entityIn) -> {
		//
		//});
	}

}
