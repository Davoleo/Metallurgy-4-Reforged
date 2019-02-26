/*
 * -------------------------------------------------------------------------------------------------------
 * Class: ArmorStats
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2019.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.material;

import net.minecraft.util.SoundEvent;

public class ArmorStats {
    private final int[] damageReduction;
    private final int armorMagic, durability;
    private final float toughness;
    private final SoundEvent equipSound;

    public ArmorStats(int[] damageReduction, int armorMagic, int durability, float toughness, SoundEvent equipSound) {
        this.damageReduction = damageReduction;
        this.armorMagic = armorMagic;
        this.durability = durability;
        this.toughness = toughness;
        this.equipSound = equipSound;
    }

    public int[] getDamageReduction() {
        return damageReduction;
    }

    public int getArmorMagic() {
        return armorMagic;
    }

    public int getDurability() {
        return durability;
    }

    public float getToughness() {
        return toughness;
    }

    public SoundEvent getEquipSound() {
        return equipSound;
    }
}
