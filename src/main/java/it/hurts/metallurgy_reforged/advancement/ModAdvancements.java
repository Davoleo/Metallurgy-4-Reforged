/*==============================================================================
 = Class: ModAdvancements
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.advancement;

import it.hurts.metallurgy_reforged.advancement.trigger.*;
import net.minecraft.advancements.CriteriaTriggers;

import java.util.HashSet;
import java.util.Set;

public class ModAdvancements {

	public static class Triggers {

		static final Set<MetallurgyTrigger<?>> ALL = new HashSet<>();

		//Tier Advancements
		public static final HarvestLevelTrigger BREAK_ORE_TIER = new HarvestLevelTrigger();

		public static final JackOfAllTradesTrigger JACK_OF_ALL_TRADES = new JackOfAllTradesTrigger();

		public static final YinYangTrigger YIN_YANG = new YinYangTrigger();

		public static final LowTemperaturesTrigger LOW_TEMPERATURES = new LowTemperaturesTrigger();

		public static final OverclockedToolsTrigger OVERCLOCKED_TOOLS = new OverclockedToolsTrigger();

		public static final LoyalFriendsTrigger LOYAL_FRIENDS = new LoyalFriendsTrigger();

		public static final StrongOpponentTrigger STRONG_OPPONENT = new StrongOpponentTrigger();

		public static final AdvancementGrantedTrigger ADVANCEMENT_GRANTED = new AdvancementGrantedTrigger();

	}

	public static void registerTriggers()
	{
		Triggers.ALL.forEach(CriteriaTriggers::register);
	}

}
