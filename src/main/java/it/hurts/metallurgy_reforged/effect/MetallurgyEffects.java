/*==============================================================================
 = Class: MetallurgyEffects
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.effect;

import it.hurts.metallurgy_reforged.effect.all.*;
import it.hurts.metallurgy_reforged.effect.armor.*;
import it.hurts.metallurgy_reforged.effect.hoe.AtlarusHoeEffect;
import it.hurts.metallurgy_reforged.effect.hoe.MidasiumHoeEffect;
import it.hurts.metallurgy_reforged.effect.hoe.PrometheumHoeEffect;
import it.hurts.metallurgy_reforged.effect.pickaxe.*;
import it.hurts.metallurgy_reforged.effect.tool.*;
import it.hurts.metallurgy_reforged.effect.weapon.*;
import it.hurts.metallurgy_reforged.item.armor.ItemArmorBase;
import it.hurts.metallurgy_reforged.item.tool.IToolEffect;
import it.hurts.metallurgy_reforged.material.MetalStats;
import it.hurts.metallurgy_reforged.material.ModMetals;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import org.apache.commons.lang3.ArrayUtils;

import javax.annotation.Nonnull;
import java.util.HashSet;
import java.util.Set;

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
				MetallurgyEffects.effects.stream()
						.filter(eff -> ArrayUtils.contains(eff.getCategory().getTools(), tool.getToolClass()) && metalStats.getName().equals(eff.metal.toString()))
						.forEach(tool::addEffect);
			}
			else if (item instanceof ItemArmorBase)
			{
				ItemArmorBase armor = ((ItemArmorBase) item);
				MetalStats metalStats = armor.getMetalStats();

				MetallurgyEffects.effects.stream()
						.filter(eff -> eff.getCategory() == EnumEffectCategory.ARMOR || eff.getCategory() == EnumEffectCategory.ALL)
						.filter(eff -> eff.metal.toString().equals(metalStats.getName()))
						.forEach(armor::addEffect);
			}
		}
	}

	//Symbiosis (I)
	public static final AdamantineEffect ADAMANTINE_EFFECT = new AdamantineEffect();
	//Symbiosis (II)
	public static final AdamantineArmorEffect ADAMANTINE_ARMOR_EFFECT = new AdamantineArmorEffect();

	//Sky-High
	public static final AmordrineArmorEffect AMORDRINE_ARMOR_EFFECT = new AmordrineArmorEffect();
	//Soulbound
	public static final AmordrineEffect AMORDRINE_EFFECT = new AmordrineEffect();
	//Coup de grâce
	public static final AmordrineWeaponEffect AMORDRINE_WEAPON_EFFECT = new AmordrineWeaponEffect();

	//Reactive II
	public static final AngmallenWeaponEffect ANGMALLEN_WEAPON_EFFECT = new AngmallenWeaponEffect();
	//Transmute
	public static final AngmallenPickaxeEffect ANGMALLEN_PICKAXE_EFFECT = new AngmallenPickaxeEffect();
	//Reactive III
	public static final AngmallenArmorEffect ANGMALLEN_ARMOR_EFFECT = new AngmallenArmorEffect();

	//Starlight
	public static final AstralSilverArmorEffect ASTRAL_SILVER_ARMOR_EFFECT = new AstralSilverArmorEffect();
	//Extraterrestrial I
	public static final AstralSilverWeaponEffect ASTRAL_SILVER_WEAPON_EFFECT = new AstralSilverWeaponEffect();
	//Extraterrestrial II
	public static final AstralSilverToolEffect ASTRAL_SILVER_TOOL_EFFECT = new AstralSilverToolEffect();

	//Whirlwind
	public static final AtlarusArmorEffect ATLARUS_ARMOR_EFFECT = new AtlarusArmorEffect();
	//Wind Scythe
	public static final AtlarusHoeEffect ATLARUS_HOE_EFFECT = new AtlarusHoeEffect();
	//Gust of Wind
	public static final AtlarusWeaponEffect ATLARUS_WEAPON_EFFECT = new AtlarusWeaponEffect();

	//Mountain I
	public static final BlackSteelArmorEffect BLACK_STEEL_ARMOR_EFFECT = new BlackSteelArmorEffect();
	//Mountain II
	public static final BlackSteelWeaponEffect BLACK_STEEL_WEAPON_EFFECT = new BlackSteelWeaponEffect();

	//Carmot Tool Effect (Cadence)
	public static final CarmotToolEffect CARMOT_TOOL_EFFECT = new CarmotToolEffect();
	//Carmot Armor (Power User)
	public static final CarmotArmorEffect CARMOT_ARMOR_EFFECT = new CarmotArmorEffect();
	//Carmot Weapon (Abattoir)
	public static final CarmotWeaponEffect CARMOT_WEAPON_EFFECT = new CarmotWeaponEffect();

	//Celenegil Tools (Escalation)
	public static final CelenegilToolEffect CELENEGIL_TOOL_EFFECT = new CelenegilToolEffect();
	//Celenegil Armor (Perseverance)
	public static final CelenegilArmorEffect CELENEGIL_ARMOR_EFFECT = new CelenegilArmorEffect();
	//Celenegil Weapons (Glory Seeker)
	public static final CelenegilWeaponEffect CELENEGIL_WEAPON_EFFECT = new CelenegilWeaponEffect();

	//Ceruclase Sword [Flash Freeze] (Chance to freeze enemies in place)
	public static final CeruclaseWeaponEffect ceruclaseWeaponEffect = new CeruclaseWeaponEffect();
	//Ceruclase Armor (Blizzard)
	public static final CeruclaseArmorEffect CERUCLASE_ARMOR_EFFECT = new CeruclaseArmorEffect();
	//Ceruclase Tools (Cold Snap)
	public static final CeruclaseToolEffect CERUCLASE_TOOL_EFFECT = new CeruclaseToolEffect();

	//Damascus Steel Armor (Royal Blood)
	public static final DamascusSteelArmorEffect DAMASCUS_STEEL_ARMOR_EFFECT = new DamascusSteelArmorEffect();
	//Damascus Steel Weapons (Brilliance)
	public static final DamascusSteelWeaponEffect DAMASCUS_STEEL_WEAPON_EFFECT = new DamascusSteelWeaponEffect();
	//Damascus Steel Pickaxe (Brilliance II)
	public static final DamascusSteelPickaxeEffect DAMASCUS_STEEL_PICKAXE_EFFECT = new DamascusSteelPickaxeEffect();

	//Deep Iron Armor (Aquatic)
	public static final DeepIronArmorEffect DEEP_IRON_ARMOR_EFFECT = new DeepIronArmorEffect();
	//Deep Iron Tool (Diver)
	public static final DeepIronToolEffect DEEP_IRON_TOOL_EFFECT = new DeepIronToolEffect();
	//Deep Iron Weapon (Diver)
	public static final DeepIronWeaponEffect DEEP_IRON_WEAPON_EFFECT = new DeepIronWeaponEffect();

	//Desichalkos Tools (Wormhole)
	public static final DesichalkosToolEffect DESICHALKOS_TOOL_EFFECT = new DesichalkosToolEffect();
	//Desichalkos Tools (Nullifier)
	public static final DesichalkosWeaponEffect DESICHALKOS_WEAPON_EFFECT = new DesichalkosWeaponEffect();
	//Desichalkos Armor (Orb)
	public static final DesichalkosArmorEffect DESICHALKOS_ARMOR_EFFECT = new DesichalkosArmorEffect();

	//Electrum Weapons (Magnet)
	public static final ElectrumWeaponEffect ELECTRUM_WEAPON_EFFECT = new ElectrumWeaponEffect();
	//Electrum Tools (Voltage Control)
	public static final ElectrumToolEffect ELECTRUM_TOOL_EFFECT = new ElectrumToolEffect();
	//Electrum Armor (Static)
	public static final ElectrumArmorEffect ELECTRUM_ARMOR_EFFECT = new ElectrumArmorEffect();

	//Etherium Armor
	public static final EtheriumArmorEffect ETHERIUM_ARMOR_EFFECT = new EtheriumArmorEffect();
	//Etherium Weapons
	public static final EtheriumWeaponEffect ETHERIUM_WEAPON_EFFECT = new EtheriumWeaponEffect();

	//Eximite Armor
	public static final EximiteArmorEffect EXIMITE_ARMOR_EFFECT = new EximiteArmorEffect();
	//Eximite Weapon
	public static final EximiteWeaponEffect EXIMITE_WEAPON_EFFECT = new EximiteWeaponEffect();

	//Haderoth Armor
	public static final HaderothArmorEffect HADEROTH_ARMOR_EFFECT = new HaderothArmorEffect();
	//Haderoth Weapon
	public static final HaderothWeaponEffect HADEROTH_WEAPON_EFFECT = new HaderothWeaponEffect();
	//Haderoth
	public static final HaderothEffect HADEROTH_EFFECT = new HaderothEffect();

	//Hepatizon Weapon
	public static final HepatizonWeaponEffect HEPATIZON_WEAPON_EFFECT = new HepatizonWeaponEffect();
	//Hepatizon Armor
	public static final HepatizonArmorEffect HEPATIZON_ARMOR_EFFECT = new HepatizonArmorEffect();

	//Ignatius Tool (Molten Core)
	public static final IgnatiusToolEffect IGNATIUS_TOOL_EFFECT = new IgnatiusToolEffect();
	//Ignatius Weapon (Molten Core)
	public static final IgnatiusWeaponEffect IGNATIUS_WEAPON_EFFECT = new IgnatiusWeaponEffect();
	//Ignatius Armor (Hot-Blooded)
	public static final IgnatiusArmorEffect IGNATIUS_ARMOR_EFFECT = new IgnatiusArmorEffect();

	//Inolashite Weapon (Time Cleave)
	public static final InolashiteWeaponEffect INOLASHITE_WEAPON_EFFECT = new InolashiteWeaponEffect();
	//Inolashite Tool (Time Walk)
	public static final InolashiteToolEffect INOLASHITE_TOOL_EFFECT = new InolashiteToolEffect();
	//Inolashite Armor (Warp)
	public static final InolashiteArmorEffect INOLASHITE_ARMOR_EFFECT = new InolashiteArmorEffect();

	//Kalendrite Armor (Tranquil)
	public static final KalendriteArmorEffect KALENDRITE_ARMOR_EFFECT = new KalendriteArmorEffect();
	//Kalendrite Tool (Restoration)
	public static final KalendriteToolEffect KALENDRITE_TOOL_EFFECT = new KalendriteToolEffect();

	//Krik Armor (Weight-Controlled Flight)
	public static final KrikArmorEffect KRIK_ARMOR_EFFECT = new KrikArmorEffect();
	//Krik Tool (Krik-Drives)
	public static final KrikToolEffect KRIK_TOOL_EFFECT = new KrikToolEffect();
	//Krik Weapon (Flak)
	public static final KrikWeaponEffect KRIK_WEAPON_EFFECT = new KrikWeaponEffect();

	//Midasium Hoe (Midas)
	public static final MidasiumHoeEffect MIDASIUM_HOE_EFFECT = new MidasiumHoeEffect();
	//Midasium Weapon (Greed)
	public static final MidasiumWeaponEffect MIDASIUM_WEAPON_EFFECT = new MidasiumWeaponEffect();
	//Midasium Tool (Greed)
	public static final MidasiumToolEffect MIDASIUM_TOOL_EFFECT = new MidasiumToolEffect();

	//Mithril Pickaxe (Multiply)
	public static final MithrilPickaxeEffect MITHRIL_PICKAXE_EFFECT = new MithrilPickaxeEffect();
	//Mithril Pickaxe (Magic aspect)
	public static final MithrilWeaponEffect MITHRIL_WEAPON_EFFECT = new MithrilWeaponEffect();
	//Mithril (Arcane)
	public static final MithrilEffect MITHRIL_EFFECT = new MithrilEffect();

	//Orichalcum (Champion)
	public static final OrichalcumEffect ORICHALCUM_EFFECT = new OrichalcumEffect();
	//Orichalcum Armor (Berserker)
	public static final OrichalcumArmorEffect ORICHALCUM_ARMOR_EFFECT = new OrichalcumArmorEffect();

	//Osmium Armor (Titan)
	public static final OsmiumArmorEffect OSMIUM_ARMOR_EFFECT = new OsmiumArmorEffect();

	//Oureclase Pickaxe (Crushing)
	public static final OureclasePickaxeEffect OURECLASE_PICKAXE_EFFECT = new OureclasePickaxeEffect();
	//Oureclase Weapon (Pulverize)
	public static final OureclaseWeaponEffect OURECLASE_WEAPON_EFFECT = new OureclaseWeaponEffect();
	//Oureclase Armor
	public static final OureclaseArmorEffect OURECLASE_ARMOR_EFFECT = new OureclaseArmorEffect();

	//Platinum Armor (Purification)
	public static final PlatinumArmorEffect PLATINUM_ARMOR_EFFECT = new PlatinumArmorEffect();
	//Platinum Weapon (Cathartic Strike)
	public static final PlatinumWeaponEffect PLATINUM_WEAPON_EFFECT = new PlatinumWeaponEffect();
	//Platinum (Flawless) [All items except armor]
	public static final PlatinumEffect PLATINUM_ALL_WEAPON_EFFECT = new PlatinumEffect() {
		@Nonnull
		@Override
		public EnumEffectCategory getCategory()
		{
			return EnumEffectCategory.SWORD;
		}
	};
	public static final PlatinumEffect PLATINUM_ALL_HOE_EFFECT = new PlatinumEffect() {
		@Nonnull
		@Override
		public EnumEffectCategory getCategory()
		{
			return EnumEffectCategory.HOE;
		}
	};
	public static final PlatinumEffect PLATINUM_ALL_TOOL_EFFECT = new PlatinumEffect() {
		@Nonnull
		@Override
		public EnumEffectCategory getCategory()
		{
			return EnumEffectCategory.TOOL;
		}
	};

	//Prometheum Armor (Freyr)
	public static final PrometheumArmorEffect PROMETHEUM_ARMOR_EFFECT = new PrometheumArmorEffect();
	//Prometheum (Photosynthesis)
	public static final PrometheumEffect PROMETHEUM_EFFECT = new PrometheumEffect();
	//Prometheum Hoe (Harvest)
	public static final PrometheumHoeEffect PROMETHEUM_HOE_EFFECT = new PrometheumHoeEffect();

	//Quicksilver Weapons (Sleight of blade)
	public static final QuicksilverWeaponEffect QUICKSILVER_WEAPON_EFFECT = new QuicksilverWeaponEffect();
	//Quicksilver (Light)
	public static final BaseMetallurgyEffect QUICKSILVER_EFFECT = new BaseMetallurgyEffect(ModMetals.QUICKSILVER) {
		@Nonnull
		@Override
		public EnumEffectCategory getCategory()
		{
			return EnumEffectCategory.ALL;
		}

		@Override
		public boolean isEnabled()
		{
			return ModMetals.QUICKSILVER != null;
		}
	};
	//Quicksilver Tools (Tailwind)
	public static final QuicksilverToolEffect QUICKSILVER_TOOL_EFFECT = new QuicksilverToolEffect();
	//Quicksilver Armor (Acceleration)
	public static final QuicksilverArmorEffect QUICKSILVER_ARMOR_EFFECT = new QuicksilverArmorEffect();

	//Sanguinite (Deceitful)
	public static final SanguiniteEffect SANGUINITE_EFFECT = new SanguiniteEffect();
	//Sanguinite Weapons (Devour)
	public static final SanguiniteWeaponEffect SANGUINITE_WEAPON_EFFECT = new SanguiniteWeaponEffect();
	//Sanguinite Tools (Blood Tap)
	public static final SanguiniteToolEffect SANGUINITE_TOOL_EFFECT = new SanguiniteToolEffect();
	//Sanguinite Armor (Necromastery)
	public static final SanguiniteArmorEffect SANGUINITE_ARMOR_EFFECT = new SanguiniteArmorEffect();

	//Shadow Iron Pickaxe (Incompatible)
	public static final ShadowIronPickaxeEffect SHADOW_IRON_PICKAXE_EFFECT = new ShadowIronPickaxeEffect();
	//Shadow Iron Weapons (Chaos Crit)
	public static final ShadowIronWeaponEffect SHADOW_IRON_WEAPON_EFFECT = new ShadowIronWeaponEffect();
	//Shadow Iron Armor (Broken Evasion)
	public static final ShadowIronArmorEffect SHADOW_IRON_ARMOR_EFFECT = new ShadowIronArmorEffect();

	public static final ShadowSteelEffect SHADOW_STEEL_EFFECT = new ShadowSteelEffect();

	//Shadow Steel Armor
	//public static final BaseMetallurgyEffect shadowSteelArmorEffect = new ShadowSteelArmorEffect();

	//Shadow Steel Axe
	//Shadow Steel Pickaxe
	//Shadow Steel Shovel
	//public static final BaseMetallurgyEffect shadowSteelToolEffect = new ShadowSteelToolEffect();

	//Shadow Steel Sword
	//public static final BaseMetallurgyEffect shadowSteelSwordEffect = new DeepIronShadowSteelWeaponEffect(ModMetals.SHADOW_STEEL);

	//Vulcanite Armor (Fire Immunity) //Removes Fire Render
	public static final BaseMetallurgyEffect vulcaniteEffect = new VulcaniteArmorEffect();

	//Vulcanite Sword (50% chance Fire Aspect (5 seconds))
	public static final BaseMetallurgyEffect vulcaniteSwordEffect = new VulcaniteIgnatiusSwordEffect(ModMetals.VULCANITE);

}
