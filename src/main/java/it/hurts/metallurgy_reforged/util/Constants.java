/*
 * -------------------------------------------------------------------------------------------------------
 * Class: Constants
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2020.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.util;

import java.util.UUID;

public class Constants {

	//Localized
	public static final String BITUMEN = Utils.localize("tooltip.metallurgy.bitumen");
	public static final String GAUNTLET_EFFECT_DISABLED = Utils.localize("tooltip.metallurgy.gauntlet_effect_disabled");
	public static final String POTASH_FERTILIZER = Utils.localize("tooltip.metallurgy.potash_fertilizer");
	public static final String PHOSPHORUS_LAMP = Utils.localize("tooltip.metallurgy.phosphorus_lamp");
	public static final String THERMITE_DUST = Utils.localize("tooltip.metallurgy.thermite");


	/**
	 * Code constants: Tool Categories
	 */
	public static final class Tools {

		public static final String AXE = "axe";
		public static final String HOE = "hoe";
		public static final String PICKAXE = "pickaxe";
		public static final String SHOVEL = "shovel";
		public static final String SWORD = "sword";

	}

	/**
	 * Blast Resistance Constants<br>
	 * Disclaimer: These variables might need a balance update
	 * <br><br>
	 * (Davoleo isn't responsible for any blast resistance level complains)
	 */
	public static final class BlastResistance {

		public static final float LOW_TIER = 6F;                   //or maybe 3, I don't remember
		public static final float MID_TIER = 10F;                  //Cobblestone Level
		public static final float HIGH_TIER = 15F;
		public static final float EXTREME_TIER = 20F;              //Obsidian Level
		public static final float UNBREAKABLE_TIER = 18000000F;    //Bedrock Level

	}

	public static final class ModAttributes {

		public static final UUID MAX_HEALTH = UUID.fromString("CB3F55D3-645C-4F38-A497-7777733DB5CF");
		public static final UUID MOVEMENT_SPEED = UUID.fromString("CB3F55D3-645C-4F38-A497-8888833DB5CF");
		public static final UUID REACH_DISTANCE = UUID.fromString("CB3F55D3-645C-4F38-A497-9999933DB5CF");

	}

}
