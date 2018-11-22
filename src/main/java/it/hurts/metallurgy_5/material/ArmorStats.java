package it.hurts.metallurgy_5.material;

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
