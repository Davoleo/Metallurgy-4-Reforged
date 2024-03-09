package com.oblivioussp.spartanweaponry.api.weaponproperty;

import com.oblivioussp.spartanweaponry.api.SpartanWeaponryAPI;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;

import java.util.List;

public class WeaponPropertyWithMagnitude extends WeaponProperty {

    public WeaponPropertyWithMagnitude(String propType, String propModId, float propMagnitude) {
        super(propType, propModId, propMagnitude);
    }

    public WeaponPropertyWithMagnitude(String propType, String propModId, int propLevel, float propMagnitude) {
        super(propType, propModId, propLevel, propMagnitude);
    }

    @Override
    protected void addTooltipDescription(ItemStack stack, List<String> tooltip) {
        tooltip.add(TextFormatting.ITALIC + "  " + SpartanWeaponryAPI.internalHandler.translateFormattedString(type + ".desc", "tooltip", modId, magnitude));
    }

}
