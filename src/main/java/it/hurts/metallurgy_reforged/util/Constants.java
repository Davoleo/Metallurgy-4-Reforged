/*==============================================================================
 = Class: Constants
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2020.
 =============================================================================*/

package it.hurts.metallurgy_reforged.util;

import it.hurts.metallurgy_reforged.material.ArmorStats;
import it.hurts.metallurgy_reforged.material.Metal;
import it.hurts.metallurgy_reforged.material.MetalStats;
import it.hurts.metallurgy_reforged.material.ToolStats;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.init.Enchantments;
import net.minecraft.inventory.EntityEquipmentSlot;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Constants {

	//Metal
	public static final MetalStats EMPTY_METAL_STATS = new MetalStats("", 0, 0,
			new ArmorStats(new int[4], 0, 0, 0),
			new ToolStats(0, 0, 0, 0, 0), 0, 0);

	//Vanilla Metals
	public static final String METAL_IRON = "iron";
	public static final String METAL_GOLD = "gold";

	//Localized
	public static final String BITUMEN = Utils.localize("tooltip.metallurgy.bitumen");
	public static final String GAUNTLET_EFFECT_DISABLED = Utils.localize("tooltip.metallurgy.gauntlet_effect_disabled");
	public static final String POTASH_FERTILIZER = Utils.localize("tooltip.metallurgy.potash_fertilizer");
	public static final String PHOSPHORUS_LAMP = Utils.localize("tooltip.metallurgy.phosphorus_lamp");
	public static final String THERMITE_DUST = Utils.localize("tooltip.metallurgy.thermite");

	//Enchantments
	public static final Enchantment[] GAUNTLET_ENCHANTMENTS = {
			Enchantments.BANE_OF_ARTHROPODS,
			Enchantments.MENDING,
			Enchantments.SMITE,
			Enchantments.VANISHING_CURSE,
			Enchantments.UNBREAKING,
			Enchantments.SHARPNESS
	};


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

		public static final Map<EntityEquipmentSlot, UUID> ARMOR_MAX_HEALTH = new HashMap<>();
		public static final Map<EntityEquipmentSlot, UUID> ARMOR_KNOCKBACK_RESISTANCE = new HashMap<>();
		public static final Map<EntityEquipmentSlot, UUID> ARMOR_MOVEMENT_SPEED = new HashMap<>();

		//Vanilla Item Attributes
		public static final UUID ATTACK_DAMAGE = UUID.fromString("CB3F55D3-645C-4F38-A497-9C13A33DB5CF");
		public static final UUID ATTACK_SPEED = UUID.fromString("FA233E1C-4180-4865-B01B-BCCE9785ACA3");

		static
		{
			for (EntityEquipmentSlot slot : EntityEquipmentSlot.values())
			{
				if (slot.getSlotType() == EntityEquipmentSlot.Type.ARMOR)
				{
					ARMOR_MAX_HEALTH.put(slot, UUID.randomUUID());
					ARMOR_KNOCKBACK_RESISTANCE.put(slot, UUID.randomUUID());
					ARMOR_MOVEMENT_SPEED.put(slot, UUID.randomUUID());
				}
			}
		}
	}

}
