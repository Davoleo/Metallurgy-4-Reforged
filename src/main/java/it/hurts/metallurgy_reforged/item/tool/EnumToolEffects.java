/*
 * -------------------------------------------------------------------------------------------------------
 * Class: EnumToolEffects
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2019.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.item.tool;

import it.hurts.metallurgy_reforged.config.ToolEffectsConfig;
import it.hurts.metallurgy_reforged.util.Utils;
import net.minecraft.item.Item;

public enum EnumToolEffects {

    //Pickaxe Effects
    DEEP_IRON_PICKAXE_EFFECT("tooltip.metallurgy.deep_iron_pickaxe_effect", ToolEffectsConfig.deepIronPickaxeEffect, "pickaxe"),

    //Sword Effects
    CELENEGIL_SWORD_EFFECT("tooltip.metallurgy.celenegil_sword_effect", true, "sword"),
    CERUCLASE_SWORD_EFFECT("tooltip.metallurgy.ceruclase_sword_effect", true, "sword"),
    DESICHALKOS_SWORD_EFFECT("tooltip.metallurgy.desichalkos_sword_effect", ToolEffectsConfig.desichalkosSwordEffect, "sword"),
    IGNATIUS_SWORD_EFFECT("tooltip.metallurgy.ignatius_sword_effect", ToolEffectsConfig.ignatiusSwordEffect, "sword"),
    KALENDRITE_SWORD_EFFECT("tooltip.metallurgy.kalendrite_sword_effect", ToolEffectsConfig.kalendriteSwordEffect, "sword"),
    MITHRIL_SWORD_EFFECT("tooltip.metallurgy.mithril_sword_effect", true, "sword"),
    ORICHALCUM_SWORD_EFFECT("tooltip.metallurgy.orichalcum_sword_effect", true, "sword"),
    SANGUINITE_SWORD_EFFECT("tooltip.metallurgy.sanguinite_sword_effect", true, "sword"),
    SHADOW_IRON_SWORD_EFFECT("tooltip.metallurgy.shadow_iron_sword_effect", ToolEffectsConfig.shadowIronSwordEffect, "sword"),
    SHADOW_STEEL_SWORD_EFFECT("tooltip.metallurgy.shadow_steel_sword_effect", ToolEffectsConfig.shadowSteelSwordEffect, "sword"),
    TARTARITE_SWORD_EFFECT("tooltip.metallurgy.tartarite_sword_effect", ToolEffectsConfig.tartariteSwordEffect, "sword"),
    VULCANITE_SWORD_EFFECT("tooltip.metallurgy.vulcanite_sword_effect", ToolEffectsConfig.vulcaniteSwordEffect, "sword"),
    VYROXERES_SWORD_EFFECT("tooltip.metallurgy.vyroxeres_sword_effect", ToolEffectsConfig.vyroxeresSwordEffect, "sword"),

    //Tool Effects
    MIDASIUM_TOOL_EFFECT("tooltip.metallurgy.midasium_tool_effect", true, "tools"),
    SHADOW_STEEL_TOOL_EFFECT("tooltip.metallurgy.shadow_steel_tool_effect", ToolEffectsConfig.shadowSteelToolSpeedEffect, "tools");


    private String unlocalized;
    private boolean active;
    private String category;

    /**
     * @param unlocalized unlocalized effect tooltip String
     * @param active if the effect is active (config value)
     * @param category tool class (can be 'axe', 'hoe', 'pickaxe', 'shovel', 'sword', 'tools')
     */
    EnumToolEffects(String unlocalized, boolean active, String category)
    {
        this.unlocalized = unlocalized;
        this.active = active;
        this.category = category;
    }

    /**
     * @param tool The given tool
     * @return The tool effect of a given tool (null if there's none)
     */
    public static EnumToolEffects getEffect(Item tool)
    {
        for (EnumToolEffects effect : EnumToolEffects.values())
        {
            if (tool instanceof ItemPickaxeBase)
            {
                ItemPickaxeBase pickaxe = (ItemPickaxeBase) tool;
                System.out.println(pickaxe.getToolMaterialName());
                if (effect.name().contains(pickaxe.getToolMaterialName()))
                    return effect;
            }
            else if (tool instanceof ItemSwordBase)
            {
                ItemSwordBase sword = (ItemSwordBase) tool;
                System.out.println(sword.getToolMaterialName());
                if (effect.name().contains(sword.getToolMaterialName()))
                    return effect;
            }
        }

        return null;
    }

    public String getLocalized()
    {
        return Utils.localize(unlocalized);
    }

    public String getUnlocalized()
    {
        return unlocalized;
    }

    public boolean isActive()
    {
        return active;
    }

    public String getCategory()
    {
        return category;
    }
}
