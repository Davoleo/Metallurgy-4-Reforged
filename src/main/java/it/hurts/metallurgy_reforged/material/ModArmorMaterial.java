package it.hurts.metallurgy_reforged.material;

import it.hurts.metallurgy_reforged.Metallurgy;
import it.hurts.metallurgy_reforged.util.Utils;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyLoadBase;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public enum ModArmorMaterial implements IArmorMaterial {

	ADAMANTINE("adamantine", new int[]{3, 4, 5, 3}, 8, 36, 3.5F, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND),
	AMORDRINE("amordrine", new int[]{3, 4, 5, 3}, 50, 50, 2F, SoundEvents.ITEM_ARMOR_EQUIP_IRON);
	
	private String name;
	private int durability;
	private int[] damageReduction;
	private int enchantability;
	private SoundEvent equipSound;
	private float toughness;
	private LazyLoadBase<Ingredient> repairMaterial;
	
	private ModArmorMaterial(String name, int[] damageReduction, int enchantability, int durability, float toughness, SoundEvent equipSound) {
		this.name = name;
		this.damageReduction = damageReduction;
        this.enchantability = enchantability;
        this.durability = durability;
        this.toughness = toughness;
        this.equipSound = equipSound;
        
        repairMaterial = new LazyLoadBase<Ingredient>(() -> Ingredient.fromItems(Utils.getMetalFromString(name).getIngot().asItem()));
	}
	
	@Override
	public int getDamageReductionAmount(EquipmentSlotType slot) {
		return this.damageReduction[slot.getIndex()];
	}

	@Override
	public int getDurability(EquipmentSlotType slot) {
		return durability;
	}

	@Override
	public int getEnchantability() {
		return enchantability;
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public String getName() {
		return Metallurgy.MODID + ":" + name;
	}

	@Override
	public Ingredient getRepairMaterial() {
		return repairMaterial.getValue();
	}

	@Override
	public SoundEvent getSoundEvent() {
		return equipSound;
	}

	@Override
	public float getToughness() {
		return toughness;
	}

}
