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
import it.hurts.metallurgy_reforged.config.ToolEffectsConfig;
import it.hurts.metallurgy_reforged.effect.armor.*;
import it.hurts.metallurgy_reforged.effect.tool.IgnatiusAxeShovelEffect;
import it.hurts.metallurgy_reforged.effect.tool.KalendriteSwordEffect;
import it.hurts.metallurgy_reforged.effect.tool.SanguiniteSwordEffect;
import it.hurts.metallurgy_reforged.effect.tool.VulcaniteIgnatiusSwordEffect;
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.model.EnumTools;
import it.hurts.metallurgy_reforged.util.Utils;
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

	//Ceruclase Sword (Chance to slow enemies on hit)
	public static final AbstractMetallurgyEffect ceruclaseEffect =
			new SwordHitChanceEffect(ModMetals.CERUCLASE, 75, new PotionEffect(MobEffects.SLOWNESS, 80, 2)) {
				@Override
				protected boolean isEnabled()
				{
					return ToolEffectsConfig.ceruclaseSwordEffect;
				}
			};

	//Deep Iron Armor (Swimming Speed when the player is in water and on ground)
	public static final AbstractMetallurgyEffect deepIronEffect = new DeepIronArmorEffect();

	//Desichalkos Sword Effect (Random Negative Effect)
	public static final AbstractMetallurgyEffect desichalkosSwordEffect =
			new SwordHitChanceEffect(ModMetals.DESICHALKOS, 100, new PotionEffect(Utils.getRandomEffect(), 80)) {
				@Override
				protected boolean isEnabled()
				{
					return ToolEffectsConfig.desichalkosSwordEffect;
				}
			};

	//Eximite Armor (replaces the enderman's AI Eximite Helmet)
	public static final AbstractMetallurgyEffect eximiteEffect = new EximiteHelmetEffect();

	//Ignatius Axe & Shovel (Smelt Harvested Items)
	public static final AbstractMetallurgyEffect ignatiusAxeEffect = new IgnatiusAxeShovelEffect() {
		@Override
		protected EnumTools getToolClass()
		{
			return EnumTools.AXE;
		}
	};
	public static final AbstractMetallurgyEffect ignatiusShovelEffect = new IgnatiusAxeShovelEffect() {
		@Override
		protected EnumTools getToolClass()
		{
			return EnumTools.SHOVEL;
		}
	};

	//Ignatius Sword (25% chance Fire Aspect (5 seconds))
	public static final AbstractMetallurgyEffect ignatiusSwordEffect = new VulcaniteIgnatiusSwordEffect(ModMetals.IGNATIUS);

	//Kalendrite Armor (Strenght I)
	public static final AbstractMetallurgyEffect kalendriteEffect = new ArmorPotionEffect(
			ModMetals.KALENDRITE, new PotionEffect(MobEffects.STRENGTH, 60, 0, false, false)) {
		@Override
		public boolean isEnabled()
		{
			return ArmorEffectsConfig.kaledriteArmorEffect;
		}
	};

	//Kalendrite Sword (50% to give Regeneration II to the player on hit)
	public static final AbstractMetallurgyEffect kalendriteSwordEffect = new KalendriteSwordEffect();

	//Krik effect
	public static final AbstractMetallurgyEffect krikEffect = new KrikArmorEffect();

	//Lutetium effect (Reduced Knockback II)
	public static final AbstractMetallurgyEffect lutetiumEffect = new OsmiumLutetiumArmorEffect(ModMetals.LUTETIUM);

	//Mithril Effect (Entity Glow)
	public static final AbstractMetallurgyEffect mithrilEffect = new MithrilArmorEffect();

	//Mithril Sword (Glowing effect applied on hit entity)
	public static final AbstractMetallurgyEffect mithrilSwordEffect =
			new SwordHitChanceEffect(ModMetals.MITHRIL, 100, new PotionEffect(MobEffects.GLOWING, 200, 1)) {
				@Override
				protected boolean isEnabled()
				{
					return true;
				}
			};

	//Osmium Effect (Reduced Knockback I)
	public static final AbstractMetallurgyEffect osmiumEffect = new OsmiumLutetiumArmorEffect(ModMetals.OSMIUM);

	//Platinum Armor (Night Vision, Needed Vanishing Curse)
	public static final AbstractMetallurgyEffect platinumEffect = new PlatinumHelmetEffect();

	//Prometheum Armor (No potion, need to implement a new Effect)
	public static final AbstractMetallurgyEffect prometheumEffect = new PrometheumArmorEffect();

	//Increase the speed of item action
	public static final AbstractMetallurgyEffect quicksilverEffect = new QuicksilverArmorEffect();

	//Sanguinite Sword (Vampirism based on Luck)
	public static final AbstractMetallurgyEffect sanguiniteSwordEffect = new SanguiniteSwordEffect();

	//Shadow Iron Armor (No Blindness)
	public static final AbstractMetallurgyEffect shadowIronEffect = new ShadowIronArmorEffect();

	//Shadow Iron Sword Effect (50% Chance to give Blindness to enemies)
	public static final AbstractMetallurgyEffect shadowIronSwordEffect =
			new SwordHitChanceEffect(ModMetals.SHADOW_IRON, 50, new PotionEffect(MobEffects.BLINDNESS, 100)) {
				@Override
				protected boolean isEnabled()
				{
					return ToolEffectsConfig.shadowIronSwordEffect;
				}
			};

	//Tartarite Sword (25% chance Wither II)
	public static final AbstractMetallurgyEffect tartariteSwordEffect =
			new SwordHitChanceEffect(ModMetals.TARTARITE, 25, new PotionEffect(MobEffects.WITHER, 80, 1)) {
				@Override
				protected boolean isEnabled()
				{
					return ToolEffectsConfig.tartariteSwordEffect;
				}
			};

	//Vulcanite Armor (Fire Immunity) //Removes Fire Render
	public static final AbstractMetallurgyEffect vulcaniteEffect = new VulcaniteArmorEffect();

	//Vulcanite Sword (50% chance Fire Aspect (5 seconds))
	public static final AbstractMetallurgyEffect vulcaniteSwordEffect = new VulcaniteIgnatiusSwordEffect(ModMetals.VULCANITE);

	//Vyroxeres Sword (Chance to poison the enemy)
	public static final AbstractMetallurgyEffect vyroxeresSwordEffect =
			new SwordHitChanceEffect(ModMetals.VYROXERES, 50, new PotionEffect(MobEffects.POISON, 100, 2)) {
				@Override
				protected boolean isEnabled()
				{
					return ToolEffectsConfig.vyroxeresSwordEffect;
				}
			};

}
