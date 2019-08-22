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
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.SwordItem;

public enum EnumToolEffects {

    //Pickaxe Effects
    DEEP_IRON_PICKAXE_EFFECT("tooltip.metallurgy.deep_iron_pickaxe_effect", ToolEffectsConfig.deepIronPickaxeEffect, EnumTools.PICKAXE),
    SHADOW_STEEL_TOOL_EFFECT("tooltip.metallurgy.shadow_steel_tool_effect", ToolEffectsConfig.shadowSteelToolSpeedEffect, EnumTools.PICKAXE),

    //Sword Effects
    CELENEGIL_SWORD_EFFECT("tooltip.metallurgy.celenegil_sword_effect", true, EnumTools.SWORD),
    CERUCLASE_SWORD_EFFECT("tooltip.metallurgy.ceruclase_sword_effect", ToolEffectsConfig.ceruclaseSwordEffect, EnumTools.SWORD),
    DESICHALKOS_SWORD_EFFECT("tooltip.metallurgy.desichalkos_sword_effect", ToolEffectsConfig.desichalkosSwordEffect, EnumTools.SWORD),
    IGNATIUS_SWORD_EFFECT("tooltip.metallurgy.ignatius_sword_effect", ToolEffectsConfig.ignatiusSwordEffect, EnumTools.SWORD),
    KALENDRITE_SWORD_EFFECT("tooltip.metallurgy.kalendrite_sword_effect", ToolEffectsConfig.kalendriteSwordEffect, EnumTools.SWORD),
    MIDASIUM_SWORD_EFFECT("tooltip.metallurgy.midasium_sword_effect", true, EnumTools.SWORD),
    MITHRIL_SWORD_EFFECT("tooltip.metallurgy.mithril_sword_effect", true, EnumTools.SWORD),
    ORICHALCUM_SWORD_EFFECT("tooltip.metallurgy.orichalcum_sword_effect", true, EnumTools.SWORD),
    SANGUINITE_SWORD_EFFECT("tooltip.metallurgy.sanguinite_sword_effect", true, EnumTools.SWORD),
    SHADOW_IRON_SWORD_EFFECT("tooltip.metallurgy.shadow_iron_sword_effect", ToolEffectsConfig.shadowIronSwordEffect, EnumTools.SWORD),
    SHADOW_STEEL_SWORD_EFFECT("tooltip.metallurgy.shadow_steel_sword_effect", ToolEffectsConfig.shadowSteelSwordEffect, EnumTools.SWORD),
    TARTARITE_SWORD_EFFECT("tooltip.metallurgy.tartarite_sword_effect", ToolEffectsConfig.tartariteSwordEffect, EnumTools.SWORD),
    VULCANITE_SWORD_EFFECT("tooltip.metallurgy.vulcanite_sword_effect", ToolEffectsConfig.vulcaniteSwordEffect, EnumTools.SWORD),
    VYROXERES_SWORD_EFFECT("tooltip.metallurgy.vyroxeres_sword_effect", ToolEffectsConfig.vyroxeresSwordEffect, EnumTools.SWORD);

    private String unlocalized;
    private boolean active;
    private EnumTools category;

    /**
     * @param unlocalized unlocalized effect tooltip String
     * @param active if the effect is active (config value)
     * @param category tool class
     */
    EnumToolEffects(String unlocalized, boolean active, EnumTools category)
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

            if (tool instanceof PickaxeItem)
            {
            	PickaxeItem pickaxe = (PickaxeItem) tool;

                //if (effect.name().toLowerCase().contains(pickaxe.getTier()))
                //    return effect;
            }
            else if (tool instanceof SwordItem)
            {
            	SwordItem sword = (SwordItem) tool;
                //if (effect.name().toLowerCase().contains(sword.getTier()))
                //    return effect;
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

    public EnumTools getCategory()
    {
        return category;
    }
}
