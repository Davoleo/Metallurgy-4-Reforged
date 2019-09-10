/*
 * -------------------------------------------------------------------------------------------------------
 * Class: EnumArmorEffects
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2019.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.item.armor;

import it.hurts.metallurgy_reforged.config.ArmorEffectsConfig;
import it.hurts.metallurgy_reforged.util.Utils;

public enum EnumArmorEffects {

	ADAMANTINE_ARMOR_EFFECT("tooltip.metallurgy.adamantine_armor_effect", ArmorEffectsConfig.adamantineArmorEffect),
	AMORDRINE_ARMOR_MATERIAL("tooltip.metallurgy.amordrine_armor_effect", ArmorEffectsConfig.amordrineArmorEffect),
	ANGMALLEN_ARMOR_MATERIAL("tooltip.metallurgy.angmallen_armor_effect", ArmorEffectsConfig.angmallenArmorEffect),
	ASTRAL_SILVER_ARMOR_MATERIAL("tooltip.metallurgy.astral_silver_armor_effect", ArmorEffectsConfig.astralSilverArmorEffect),
	CARMOT_ARMOR_MATERIAL("tooltip.metallurgy.carmot_armor_effect", ArmorEffectsConfig.carmotArmorEffect),
	CELENEGIL_ARMOR_MATERIAL("tooltip.metallurgy.celenegil_armor_effect", ArmorEffectsConfig.celenegilArmorEffect),
	CERUCLASE_ARMOR_MATERIAL("tooltip.metallurgy.ceruclase_armor_effect", true),
	DEEP_IRON_ARMOR_MATERIAL("tooltip.metallurgy.deep_iron_armor_effect", ArmorEffectsConfig.deepIronArmorEffect),
	EXIMITE_ARMOR_MATERIAL("tooltip.metallurgy.eximite_armor_effect", ArmorEffectsConfig.eximiteArmorEffect),
	KALENDRITE_ARMOR_MATERIAL("tooltip.metallurgy.kalendrite_armor_effect", true),
	KRIK_ARMOR_MATERIAL("tooltip.metallurgy.krik_armor_effect", ArmorEffectsConfig.krikArmorEffect),
	LUTETIUM_ARMOR_MATERIAL("tooltip.metallurgy.lutetium_armor_effect", ArmorEffectsConfig.lutetiumArmorEffect),
	MITHRIL_ARMOR_MATERIAL("tooltip.metallurgy.mithril_armor_effect", ArmorEffectsConfig.mithrilArmorEffect),
	OSMIUM_ARMOR_MATERIAL("tooltip.metallurgy.osmium_armor_effect", ArmorEffectsConfig.osmiumArmorEffect),
	PLATINUM_ARMOR_MATERIAL("tooltip.metallurgy.platinum_armor_effect", ArmorEffectsConfig.platinumArmorEffect),
	PROMETHEUM_ARMOR_MATERIAL("tooltip.metallurgy.prometheum_armor_effect", ArmorEffectsConfig.prometheumArmorEffect),
	QUICKSILVER_ARMOR_MATERIAL("tooltip.metallurgy.quicksilver_armor_effect", ArmorEffectsConfig.quicksilverArmorEffect),
	SHADOW_IRON_ARMOR_MATERIAL("tooltip.metallurgy.shadow_iron_armor_effect", true),
	SHADOW_STEEL_ARMOR_MATERIAL("tooltip.metallurgy.shadow_steel_armor_effect", ArmorEffectsConfig.shadowSteelArmorEffect),
	VULCANITE_ARMOR_MATERIAL("tooltip.metallurgy.vulcanite_armor_effect", ArmorEffectsConfig.vulcaniteArmorEffect);


	private String unlocalized;
	private boolean active;

	EnumArmorEffects(String unlocalized, boolean active)
	{
		this.unlocalized = unlocalized;
		this.active = active;
	}

	/**
	 * @param armor The given armor
	 *
	 * @return The armor effect of a given armor (null if there's none)
	 */
	public static EnumArmorEffects getEffect(ItemArmorBase armor)
	{
		for (EnumArmorEffects effect : EnumArmorEffects.values())
		{
			if (effect.name().toLowerCase().contains(armor.getArmorMaterial().name()))
				return effect;
		}

		return null;
	}

	public String getLocalized()
	{
		return Utils.localize(unlocalized);
	}

	public boolean isActive()
	{
		return active;
	}
}
