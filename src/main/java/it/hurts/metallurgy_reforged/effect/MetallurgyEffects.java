/*==============================================================================
 = Class: MetallurgyEffects
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2020.
 =============================================================================*/

package it.hurts.metallurgy_reforged.effect;

import it.hurts.metallurgy_reforged.config.EffectsConfig;
import it.hurts.metallurgy_reforged.effect.all.AdamantineEffect;
import it.hurts.metallurgy_reforged.effect.armor.*;
import it.hurts.metallurgy_reforged.effect.tool.*;
import it.hurts.metallurgy_reforged.item.armor.ItemArmorBase;
import it.hurts.metallurgy_reforged.item.tool.IToolEffect;
import it.hurts.metallurgy_reforged.material.MetalStats;
import it.hurts.metallurgy_reforged.material.ModMetals;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import org.apache.commons.lang3.ArrayUtils;

import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("unused")
public class MetallurgyEffects {

	public static Set<BaseMetallurgyEffect> effects = new HashSet<>();


	public static void initTooltips()
	{
		for (Item item : ForgeRegistries.ITEMS)
		{
			if (item instanceof IToolEffect) {
                IToolEffect tool = ((IToolEffect) item);
                MetalStats metalStats = tool.getMetalStats();
                MetallurgyEffects.effects.stream()
                        .filter(eff -> ArrayUtils.contains(eff.getCategory().getTools(), tool.getToolClass()) && metalStats.getName().equals(eff.metal.toString()))
                        .findAny()
                        .ifPresent(tool::setEffect);
            }

            if (item instanceof ItemArmorBase) {
                ItemArmorBase armor = ((ItemArmorBase) item);
                MetalStats metalStats = armor.getMetalStats();

                MetallurgyEffects.effects.stream()
                        .filter(eff -> eff.getCategory() == EnumEffectCategory.ARMOR || eff.getCategory() == EnumEffectCategory.ALL)
                        .filter(eff -> eff.metal.toString().equals(metalStats.getName()))
                        .findAny()
                        .ifPresent(armor::setEffect);
            }
        }
    }

    //Symbiosis (I)
    public static final AdamantineEffect adamantineEffect = new AdamantineEffect();

    //Symbiosis (II)
    public static final AdamantineArmorEffect adamantineArmorEffect = new AdamantineArmorEffect();

    //Angmallen Armor (Luck I for Vampirism)
    @Deprecated
    public static final BaseMetallurgyEffect angmallenEffect = new ArmorPotionEffect(
            ModMetals.ANGMALLEN, MobEffects.LUCK, 0) {
        @Override
        public boolean isEnabled() {
            return EffectsConfig.angmallenArmorEffect && super.isEnabled();
        }
    };

    public static final BaseMetallurgyEffect atlarusArmorEffect = new AtlarusArmorEffect();

    public static final BaseMetallurgyEffect atlarusHoeEffect = new AtlarusHoeEffect();

    public static final BaseMetallurgyEffect atlarusWeaponEffect = new AtlarusWeaponEffect();

    //Carmot Armor (Haste I)
    public static final BaseMetallurgyEffect carmotEffect = new ArmorPotionEffect(
            ModMetals.CARMOT, MobEffects.HASTE, 0);

    //Celenegil Armor (Resistence)
    public static final BaseMetallurgyEffect celenegilEffect = new CelenegilArmorEffect();

    //Ceruclase Sword (Chance to slow enemies on hit)
    public static final BaseMetallurgyEffect ceruclaseEffect =
            new WeaponHitChanceEffect(ModMetals.CERUCLASE, 75, () -> new PotionEffect(MobEffects.SLOWNESS, 40, 20));

    //Deep Iron Pickaxe (Faster mining underwater)
    public static final BaseMetallurgyEffect deepIronPickaxeEffect = new DeepIronPickaxeEffect();

    //Deep Iron Sword
    //public static final BaseMetallurgyEffect deepIronSwordEffect = new DeepIronShadowSteelWeaponEffect(ModMetals.DEEP_IRON);

    //public static final DesichalkosSwordEffect desichalkosSwordEffect = new DesichalkosSwordEffect();

    //Desichalcos Trade Armor Effect
    //public static final DesichalkosArmorEffect desichalkosArmorEffect = new DesichalkosArmorEffect();

    //Etherium Armor
    public static EtheriumArmorEffect etheriumArmorEffect = new EtheriumArmorEffect();

    //Eximite Armor
    //public static final BaseMetallurgyEffect eximiteEffect = new EximiteArmorEffect();

    //Haderoth Armor
    //public static final BaseMetallurgyEffect haderothEffect = new HaderothArmorEffect();

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
