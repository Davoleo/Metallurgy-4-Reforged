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
import it.hurts.metallurgy_reforged.effect.tool.*;
import it.hurts.metallurgy_reforged.item.armor.ItemArmorBase;
import it.hurts.metallurgy_reforged.item.tool.IToolEffect;
import it.hurts.metallurgy_reforged.material.MetalStats;
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.model.EnumTools;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("unused")
public class MetallurgyEffects {

	public static Set<BaseMetallurgyEffect> effects = new HashSet<>();


	public static void initTooltips()
	{
		for (Item item : ForgeRegistries.ITEMS)
		{
			if (item instanceof IToolEffect)
			{
				IToolEffect tool = ((IToolEffect) item);
				MetalStats metalStats = tool.getMetalStats();
				BaseMetallurgyEffect effect = getToolEffectFromMetalStats(metalStats, tool.getToolClass());
				if (effect != null)
				{
					tool.setEffect(effect);
				}
			}

			if (item instanceof ItemArmorBase)
			{
				ItemArmorBase armor = ((ItemArmorBase) item);
				MetalStats metalStats = armor.getMetalStats();
				BaseMetallurgyEffect effect = getArmorEffectFromMetalStats(metalStats);
				if (effect != null)
				{
					armor.setEffect(effect);
				}
			}
		}
	}

	private static BaseMetallurgyEffect getToolEffectFromMetalStats(MetalStats stats, EnumTools toolClass)
	{
		for (BaseMetallurgyEffect effect : MetallurgyEffects.effects)
		{
			if (effect.getMetal().toString().equals(stats.getName()) && effect.isToolEffect())
			{
				if (effect.getToolClass() != null && effect.getToolClass() == toolClass)
				{
					return effect;
				}

				if (effect.getToolClass() == null)
					return effect;
			}
		}

		return null;
	}

	private static BaseMetallurgyEffect getArmorEffectFromMetalStats(MetalStats stats)
	{
		for (BaseMetallurgyEffect effect : MetallurgyEffects.effects)
		{
			if (effect.getMetal().toString().equals(stats.getName()) && !effect.isToolEffect())
			{
				return effect;
			}
		}

		return null;
	}


	//Adamantine Armor (Saturation)
	public static final BaseMetallurgyEffect adamantineEffect = new AdamantineArmorEffect();

	//Amordrine Armor (Strenght II)
	public static final BaseMetallurgyEffect amordrineEffect = new ArmorPotionEffect(
			ModMetals.AMORDRINE, new PotionEffect(MobEffects.STRENGTH, 60, 1, false, false)) {
		@Override
		public boolean isEnabled()
		{
			return ArmorEffectsConfig.amordrineArmorEffect;
		}
	};

	//Angmallen Armor (Luck I for Vampirism)
	public static final BaseMetallurgyEffect angmallenEffect = new ArmorPotionEffect(
			ModMetals.ANGMALLEN, new PotionEffect(MobEffects.LUCK, 80, 0, false, false)) {
		@Override
		public boolean isEnabled()
		{
			return ArmorEffectsConfig.angmallenArmorEffect;
		}
	};

	//Astral Silver Armor (Jump Boost)
	public static final BaseMetallurgyEffect astralSilverEffect = new ArmorPotionEffect(
			ModMetals.ASTRAL_SILVER, new PotionEffect(MobEffects.JUMP_BOOST, 100, 1, false, false)) {
		@Override
		public boolean isEnabled()
		{
			return ArmorEffectsConfig.astralSilverArmorEffect;
		}
	};

	public static final BaseMetallurgyEffect atlarusArmorEffect = new AtlarusArmorEffect();

	public static final BaseMetallurgyEffect atlarusAxeEffect = new AtlarusAxeEffect();

	public static final BaseMetallurgyEffect atlarusSwordEffect = new AtlarusSwordEffect();

	//Carmot Armor (Haste I)
	public static final BaseMetallurgyEffect carmotEffect = new ArmorPotionEffect(
			ModMetals.CARMOT, new PotionEffect(MobEffects.HASTE, 60, 0, false, false)) {
		@Override
		public boolean isEnabled()
		{
			return ArmorEffectsConfig.carmotArmorEffect;
		}
	};

	//Celenegil Armor (Resistence)
	public static final BaseMetallurgyEffect celenegilEffect = new CelenegilArmorEffect();

	//Celenegil Sword Effect
	public static final BaseMetallurgyEffect celenegilSwordEffect = new CelenegilOrichalcumSwordEffect(ModMetals.CELENEGIL);

	//Ceruclase Sword (Chance to slow enemies on hit)
	public static final BaseMetallurgyEffect ceruclaseEffect =
			new SwordHitChanceEffect(ModMetals.CERUCLASE, 75, new PotionEffect(MobEffects.SLOWNESS, 80, 2)) {
				@Override
				public boolean isEnabled()
				{
					return ToolEffectsConfig.ceruclaseSwordEffect;
				}
			};

	//Deep Iron Armor (Swimming Speed when the player is in water and on ground)
	public static final BaseMetallurgyEffect deepIronEffect = new DeepIronArmorEffect();

	//Deep Iron Pickaxe (Faster mining underwater)
	public static final BaseMetallurgyEffect deepIronPickaxeEffect = new DeepIronPickaxeEffect();

	//Deep Iron Sword
	public static final BaseMetallurgyEffect deepIronSwordEffect = new DeepIronShadowSteelSwordEffect(ModMetals.DEEP_IRON);

	// TODO: 20/07/2020 Broken randomization | Maybe move this effect to another material
	//Desichalkos Sword Effect (Random Negative Effect)
	//public static final BaseMetallurgyEffect desichalkosSwordEffect =
	//		new SwordHitChanceEffect(ModMetals.DESICHALKOS, 100, new PotionEffect(Utils.getRandomEffect(), 80)) {
	//			@Override
	//			public boolean isEnabled()
	//			{
	//				return ToolEffectsConfig.desichalkosSwordEffect;
	//			}
	//		};
	public static final DesichalkosSwordEffect desichalkosSwordEffect = new DesichalkosSwordEffect();

	//Desichalcos Trade Armor Effect
	public static final DesichalkosArmorEffect desichalkosArmorEffect = new DesichalkosArmorEffect();

	//Etherium Armor
	public static EtheriumArmorEffect etheriumArmorEffect = new EtheriumArmorEffect();

	//Eximite Armor
	public static final BaseMetallurgyEffect eximiteEffect = new EximiteArmorEffect();

	//Ignatius Axe & Shovel (Smelt Harvested Items)
	public static final BaseMetallurgyEffect ignatiusAxeEffect = new IgnatiusAxeShovelEffect() {
		@Override
		public EnumTools getToolClass()
		{
			return EnumTools.AXE;
		}
	};
	public static final BaseMetallurgyEffect ignatiusShovelEffect = new IgnatiusAxeShovelEffect() {
		@Override
		public EnumTools getToolClass()
		{
			return EnumTools.SHOVEL;
		}
	};

	//Ignatius Pickaxe (Metal Nuggets drop)
	public static final BaseMetallurgyEffect ignatiusPickaxeEffect = new IgnatiusPickaxeEffect();

	//Ignatius Sword (25% chance Fire Aspect (5 seconds))
	public static final BaseMetallurgyEffect ignatiusSwordEffect = new VulcaniteIgnatiusSwordEffect(ModMetals.IGNATIUS);

	//Kalendrite Armor (Strenght I)
	public static final BaseMetallurgyEffect kalendriteEffect = new ArmorPotionEffect(
			ModMetals.KALENDRITE, new PotionEffect(MobEffects.STRENGTH, 60, 0, false, false)) {
		@Override
		public boolean isEnabled()
		{
			return ArmorEffectsConfig.kaledriteArmorEffect;
		}
	};

	//Kalendrite Sword (50% to give Regeneration II to the player on hit)
	public static final BaseMetallurgyEffect kalendriteSwordEffect = new KalendriteSwordEffect();

	//Krik effect
	public static final BaseMetallurgyEffect krikEffect = new KrikArmorEffect();

	//Lutetium effect (Reduced Knockback II)
	public static final BaseMetallurgyEffect lutetiumEffect = new OsmiumLutetiumArmorEffect(ModMetals.LUTETIUM);

	//Midasium Sword (chance to duplicate drops)
	public static final BaseMetallurgyEffect midasiumSwordEffect = new MidasiumSwordEffect();

	//Mithril Effect (Entity Glow)
	public static final BaseMetallurgyEffect mithrilEffect = new MithrilArmorEffect();

	//Mithril Sword (Glowing effect applied on hit entity)
	public static final BaseMetallurgyEffect mithrilSwordEffect =
			new SwordHitChanceEffect(ModMetals.MITHRIL, 100, new PotionEffect(MobEffects.GLOWING, 200, 1)) {
				@Override
				public boolean isEnabled()
				{
					return true;
				}
			};

	//Orichalcum Effect
	public static final BaseMetallurgyEffect orichalcumSwordEffect = new CelenegilOrichalcumSwordEffect(ModMetals.ORICHALCUM);

	//Osmium Effect (Reduced Knockback I)
	public static final BaseMetallurgyEffect osmiumEffect = new OsmiumLutetiumArmorEffect(ModMetals.OSMIUM);

	//Platinum Armor (Night Vision, Needed Vanishing Curse)
	public static final BaseMetallurgyEffect platinumEffect = new PlatinumArmorEffect();

	//Prometheum Armor (No potion, need to implement a new Effect)
	public static final BaseMetallurgyEffect prometheumEffect = new PrometheumArmorEffect();

	//Increase the speed of item action
	public static final BaseMetallurgyEffect quicksilverEffect = new QuicksilverArmorEffect();

	//Sanguinite Sword (Vampirism based on Luck)
	public static final BaseMetallurgyEffect sanguiniteSwordEffect = new SanguiniteSwordEffect();

	//Shadow Iron Armor (No Blindness)
	public static final BaseMetallurgyEffect shadowIronEffect = new ShadowIronArmorEffect();

	//Shadow Iron Sword Effect (50% Chance to give Blindness to enemies)
	public static final BaseMetallurgyEffect shadowIronSwordEffect =
			new SwordHitChanceEffect(ModMetals.SHADOW_IRON, 50, new PotionEffect(MobEffects.BLINDNESS, 100)) {
				@Override
				public boolean isEnabled()
				{
					return ToolEffectsConfig.shadowIronSwordEffect;
				}
			};

	//Shadow Steel Armor
	public static final BaseMetallurgyEffect shadowSteelArmorEffect = new ShadowIronArmorEffect();

	//Shadow Steel Axe
	//Shadow Steel Pickaxe
	//Shadow Steel Shovel
	public static final BaseMetallurgyEffect shadowSteelToolEffect = new ShadowSteelToolEffect();

	//Shadow Steel Sword
	public static final BaseMetallurgyEffect shadowSteelSwordEffect = new DeepIronShadowSteelSwordEffect(ModMetals.SHADOW_STEEL);


	//Tartarite Sword (25% chance Wither II)
	public static final BaseMetallurgyEffect tartariteSwordEffect =
			new SwordHitChanceEffect(ModMetals.TARTARITE, 25, new PotionEffect(MobEffects.WITHER, 80, 1)) {
				@Override
				public boolean isEnabled()
				{
					return ToolEffectsConfig.tartariteSwordEffect;
				}
			};

	//Vulcanite Armor (Fire Immunity) //Removes Fire Render
	public static final BaseMetallurgyEffect vulcaniteEffect = new VulcaniteArmorEffect();

	//Vulcanite Sword (50% chance Fire Aspect (5 seconds))
	public static final BaseMetallurgyEffect vulcaniteSwordEffect = new VulcaniteIgnatiusSwordEffect(ModMetals.VULCANITE);

	//Vyroxeres Sword (Chance to poison the enemy)
	public static final BaseMetallurgyEffect vyroxeresSwordEffect =
			new SwordHitChanceEffect(ModMetals.VYROXERES, 50, new PotionEffect(MobEffects.POISON, 100, 2)) {
				@Override
				public boolean isEnabled()
				{
					return ToolEffectsConfig.vyroxeresSwordEffect;
				}
			};

}
