/*==============================================================================
 = Class: MetallurgyEffects
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.effect;

import it.hurts.metallurgy_reforged.effect.all.AdamantineEffect;
import it.hurts.metallurgy_reforged.effect.all.AmordrineEffect;
import it.hurts.metallurgy_reforged.effect.armor.*;
import it.hurts.metallurgy_reforged.effect.hoe.AtlarusHoeEffect;
import it.hurts.metallurgy_reforged.effect.pickaxe.AngmallenPickaxeEffect;
import it.hurts.metallurgy_reforged.effect.pickaxe.DamascusSteelPickaxeEffect;
import it.hurts.metallurgy_reforged.effect.tool.*;
import it.hurts.metallurgy_reforged.effect.weapon.*;
import it.hurts.metallurgy_reforged.item.armor.ItemArmorBase;
import it.hurts.metallurgy_reforged.item.tool.IToolEffect;
import it.hurts.metallurgy_reforged.material.MetalStats;
import it.hurts.metallurgy_reforged.material.ModMetals;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import org.apache.commons.lang3.ArrayUtils;

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
                        .forEach(armor::setEffect);
            }
        }
    }

    //Symbiosis (I)
    public static final AdamantineEffect adamantineEffect = new AdamantineEffect();
    //Symbiosis (II)
    public static final AdamantineArmorEffect adamantineArmorEffect = new AdamantineArmorEffect();

    //Sky-High
    public static final AmordrineArmorEffect amordrineArmorEffect = new AmordrineArmorEffect();
    //Soulbound
    public static final AmordrineEffect amordrineEffect = new AmordrineEffect();
    //Coup de grâce
    public static final AmordrineWeaponEffect amordrineWeaponEffect = new AmordrineWeaponEffect();

    //Reactive II
    public static final AngmallenWeaponEffect angmallenWeaponEffect = new AngmallenWeaponEffect();
    //Transmute
    public static final AngmallenPickaxeEffect angmallenPickaxeEffect = new AngmallenPickaxeEffect();
    //Reactive III
    public static final AngmallenArmorEffect angmallenArmorEffect = new AngmallenArmorEffect();

    //Starlight
    public static final AstralSilverArmorEffect astralSilverArmorEffect = new AstralSilverArmorEffect();
    //Extraterrestrial I
    public static final AstralSilverWeaponEffect astralSilverWeaponEffect = new AstralSilverWeaponEffect();
    //Extraterrestrial II
    public static final AstralSilverToolEffect astralSilverToolEffect = new AstralSilverToolEffect();

    //Whirlwind
    public static final AtlarusArmorEffect atlarusArmorEffect = new AtlarusArmorEffect();
    //Wind Scythe
    public static final AtlarusHoeEffect atlarusHoeEffect = new AtlarusHoeEffect();
    //Gust of Wind
    public static final AtlarusWeaponEffect atlarusWeaponEffect = new AtlarusWeaponEffect();

    //Mountain I
    public static final BlackSteelArmorEffect blackSteelArmorEffect = new BlackSteelArmorEffect();
    //Mountain II
    public static final BlackSteelWeaponEffect blackSteelWeaponEffect = new BlackSteelWeaponEffect();

    //Carmot Tool Effect (Cadence)
    public static final CarmotToolEffect carmotToolEffect = new CarmotToolEffect();
    //Carmot Armor (Power User)
    public static final CarmotArmorEffect carmotArmorEffect = new CarmotArmorEffect();
    //Carmot Weapon (Abattoir)
    public static final CarmotWeaponEffect carmotWeaponEffect = new CarmotWeaponEffect();

    //Celenegil Tools (Escalation)
    public static final CelenegilToolEffect celenegilToolEffect = new CelenegilToolEffect();
    //Celenegil Armor (Perseverance)
    public static final CelenegilArmorEffect celenegilArmorEffect = new CelenegilArmorEffect();
    //Celenegil Weapons (Glory Seeker)
    public static final CelenegilWeaponEffect celenegilWeaponEffect = new CelenegilWeaponEffect();

    //Ceruclase Sword [Flash Freeze] (Chance to freeze enemies in place)
    public static final CeruclaseWeaponEffect ceruclaseWeaponEffect = new CeruclaseWeaponEffect();
    //Ceruclase Armor (Blizzard)
    public static final CeruclaseArmorEffect ceruclaseArmorEffect = new CeruclaseArmorEffect();
    //Ceruclase Tools (Cold Snap)
    public static final CeruclaseToolEffect ceruclaseToolEffect = new CeruclaseToolEffect();

    //Damascus Steel Armor (Royal Blood)
    public static final DamascusSteelArmorEffect damascusSteelArmorEffect = new DamascusSteelArmorEffect();
    //Damascus Steel Weapons (Brilliance)
    public static final DamascusSteelWeaponEffect damascusSteelWeaponEffect = new DamascusSteelWeaponEffect();
    //Damascus Steel Pickaxe (Brilliance II)
    public static final DamascusSteelPickaxeEffect damascusSteelPickaxeEffect = new DamascusSteelPickaxeEffect();

    //Deep Iron Armor (Aquatic)
    public static final DeepIronArmorEffect deepIronArmorEffect = new DeepIronArmorEffect();
    //Deep Iron Tool (Diver)
    public static final DeepIronToolEffect deepIronToolEffect = new DeepIronToolEffect();
    //Deep Iron Weapon (Diver)
    public static final DeepIronWeaponEffect deepIronWeaponEffect = new DeepIronWeaponEffect();

    //Desichalkos Tools (Wormhole)
    public static final DesichalkosToolEffect desichalkosToolEffect = new DesichalkosToolEffect();
    //Desichalkos Tools (Nullifier)
    public static final DesichalkosWeaponEffect desichalkosWeaponEffect = new DesichalkosWeaponEffect();
    //Desichalkos Armor (Orb)
    public static final DesichalkosArmorEffect desichalkosArmorEffect = new DesichalkosArmorEffect();

    //Electrum Weapons (Magnet)
    public static ElectrumWeaponEffect electrumWeaponEffect = new ElectrumWeaponEffect();
    //Electrum Tools (Voltage Control)
    public static ElectrumToolEffect electrumToolEffect = new ElectrumToolEffect();
    //Electrum Armor (Static)
    public static ElectrumArmorEffect electrumArmorEffect = new ElectrumArmorEffect();

    //Etherium Armor
    public static EtheriumArmorEffect etheriumArmorEffect = new EtheriumArmorEffect();
    //Etherium Weapons
    public static EtheriumWeaponEffect etheriumWeaponEffect = new EtheriumWeaponEffect();

    //Eximite Armor
    public static final EximiteArmorEffect eximiteArmorEffect = new EximiteArmorEffect();
    //Eximite Weapon
    public static final EximiteWeaponEffect eximiteWeaponEffect = new EximiteWeaponEffect();

    //Haderoth Armor
    public static final BaseMetallurgyEffect haderothEffect = new HaderothArmorEffect();

    //Ignatius Tool (Smelt Harvested Items)
    public static final BaseMetallurgyEffect ignatiusToolEffect = new IgnatiusToolEffect();

    //Ignatius Sword (25% chance Fire Aspect (5 seconds))
    //public static final BaseMetallurgyEffect ignatiusSwordEffect = new VulcaniteIgnatiusSwordEffect(ModMetals.IGNATIUS);

    //Krik effect
    public static final BaseMetallurgyEffect krikEffect = new KrikArmorEffect();

    //Prometheum Armor (No potion, need to implement a new Effect)
    //public static final BaseMetallurgyEffect prometheumEffect = new PrometheumArmorEffect();

    //Increase the speed of item action
    //public static final BaseMetallurgyEffect quicksilverEffect = new QuicksilverArmorEffect();

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
