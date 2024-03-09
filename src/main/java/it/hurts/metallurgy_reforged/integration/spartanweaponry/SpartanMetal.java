/*==============================================================================
 = Class: SpartanMetal
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2024.
 =============================================================================*/

package it.hurts.metallurgy_reforged.integration.spartanweaponry;

import com.google.common.base.CaseFormat;
import com.google.common.collect.Maps;
import com.oblivioussp.spartanweaponry.api.ToolMaterialEx;
import it.hurts.metallurgy_reforged.Metallurgy;
import it.hurts.metallurgy_reforged.integration.IntegrationSW;
import it.hurts.metallurgy_reforged.material.MetalStats;
import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.Map;

public class SpartanMetal {

    private final MetalStats stats;

    private final ToolMaterialEx toolMaterial;

    private final Map<SpartanWeaponType, Item> items = Maps.newEnumMap(SpartanWeaponType.class);

    public SpartanMetal(IForgeRegistry<Item> registry, MetalStats stats) {
        this.stats = stats;

        final String ingotOre = "ingot" + CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, stats.getName());
        final int metalColor = stats.getColorHex();
        this.toolMaterial = new ToolMaterialEx(
                stats.getName(), ingotOre, Metallurgy.MODID, metalColor, metalColor,
                stats.getToolMaterial().getHarvestLevel(), stats.getToolMaterial().getMaxUses(), stats.getToolMaterial().getEfficiency(),
                stats.getToolMaterial().getAttackDamage(), stats.getToolMaterial().getEnchantability()
        );

        for (SpartanWeaponType type : SpartanWeaponType.values()) {
            final Item weapon = type.initializer.create(toolMaterial, Metallurgy.MODID, IntegrationSW.CREATIVE_TAB);
            items.put(type, weapon);

            if (weapon != null) {
                registry.register(weapon);
            }
        }
    }

    public MetalStats getStats() {
        return stats;
    }

    public ToolMaterialEx getMaterialEx() {
        return toolMaterial;
    }

    public Map<SpartanWeaponType, Item> getItems() {
        return items;
    }

    public Item getWeapon(SpartanWeaponType type) {
        return items.get(type);
    }

    public boolean isWeaponEnabled(SpartanWeaponType type) {
        return items.get(type) != null;
    }
}
