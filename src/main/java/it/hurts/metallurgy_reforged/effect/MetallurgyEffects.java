/*
 * -------------------------------------------------------------------------------------------------------
 * Class: MetallurgyEffects
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 * Copyright (c) 2020.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.effect;

import it.hurts.metallurgy_reforged.config.ArmorEffectsConfig;
import it.hurts.metallurgy_reforged.effect.effects.*;
import it.hurts.metallurgy_reforged.material.ModMetals;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;

import java.util.HashSet;
import java.util.Set;

public class MetallurgyEffects {

	public static Set<AbstractMetallurgyEffect> effects = new HashSet<>();

	//Adamantine Armor (Saturation)
	public static final AbstractMetallurgyEffect adamantineEffect = new AdamantineArmorEffect();

	//Amordrine Armor (Strenght II)
	public static final AbstractMetallurgyEffect amordrineEffect = new ArmorPotionEffect(
			ModMetals.AMORDRINE, new PotionEffect(MobEffects.STRENGTH, 60, 1, false, false)) {
		@Override
		public boolean isEnabled()
		{
			return ArmorEffectsConfig.amordrineArmorEffect;
		}
	};

	//Angmallen Armor (Luck I for Vampirism)
	public static final AbstractMetallurgyEffect angmallenEffect = new ArmorPotionEffect(
			ModMetals.ANGMALLEN, new PotionEffect(MobEffects.LUCK, 80, 0, false, false)) {
		@Override
		public boolean isEnabled()
		{
			return ArmorEffectsConfig.angmallenArmorEffect;
		}
	};

	//Astral Silver Armor (Jump Boost)
	public static final AbstractMetallurgyEffect astralSilverEffect = new ArmorPotionEffect(
			ModMetals.ASTRAL_SILVER, new PotionEffect(MobEffects.JUMP_BOOST, 100, 1, false, false)) {
		@Override
		public boolean isEnabled()
		{
			return ArmorEffectsConfig.astralSilverArmorEffect;
		}
	};

	//Carmot Armor (Haste I)
	public static final AbstractMetallurgyEffect carmotEffect = new ArmorPotionEffect(
			ModMetals.CARMOT, new PotionEffect(MobEffects.HASTE, 60, 0, false, false)) {
		@Override
		public boolean isEnabled()
		{
			return ArmorEffectsConfig.carmotArmorEffect;
		}
	};

	//Celenegil Armor (Resistence)
	public static final AbstractMetallurgyEffect celenegilEffect = new CelenegilArmorEffect();

	//Deep Iron Armor (Swimming Speed when the player is in water and on ground)
	public static final AbstractMetallurgyEffect deepIronEffect = new DeepIronArmorEffect();

	//Eximite Armor (replaces the enderman's AI Eximite Helmet)
	public static final AbstractMetallurgyEffect eximiteEffect = new EximiteHelmetEffect();

	//Kalendrite Armor (Strenght I)
	public static final AbstractMetallurgyEffect kalendriteEffect = new ArmorPotionEffect(
			ModMetals.KALENDRITE, new PotionEffect(MobEffects.STRENGTH, 60, 0, false, false)) {
		@Override
		public boolean isEnabled()
		{
			return ArmorEffectsConfig.kaledriteArmorEffect;
		}
	};

	//Krik effect
	public static final AbstractMetallurgyEffect krikEffect = new KrikArmorEffect();

	//Lutetium effect (Reduced Knockback II)
	public static final AbstractMetallurgyEffect lutetiumEffect = new OsmiumLutetiumArmorEffect(ModMetals.LUTETIUM);

	//Mithril Effect (Entity Glow)
	public static final AbstractMetallurgyEffect mithrilEffect = new MithrilArmorEffect();

	//Osmium Effect (Reduced Knockback I)
	public static final AbstractMetallurgyEffect osmiumEffect = new OsmiumLutetiumArmorEffect(ModMetals.OSMIUM);

	//Platinum Armor (Night Vision, Needed Vanishing Curse)
	public static final AbstractMetallurgyEffect platinumEffect = new PlatinumHelmetEffect();

	//Prometheum Armor (No potion, need to implement a new Effect)
	public static final AbstractMetallurgyEffect prometheumEffect = new PrometheumArmorEffect();

	//Increase the speed of item action
	public static final AbstractMetallurgyEffect quicksilverEffect = new QuicksilverArmorEffect();

	//Shadow Iron Armor (No Blindness)
	public static final AbstractMetallurgyEffect shadowIronEffect = new ShadowIronArmorEffect();

	//Vulcanite Armor (Fire Immunity) //Removes Fire Render
	public static final AbstractMetallurgyEffect vulcaniteEffect = new VulcaniteArmorEffect();


}
