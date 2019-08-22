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
	AMORDRINE("amordrine", new int[]{3, 4, 5, 3}, 50, 50, 2F, SoundEvents.ITEM_ARMOR_EQUIP_IRON),
	ANGMALLEN("angmallen", new int[]{3, 5, 6, 3}, 18, 30, 0F, SoundEvents.ITEM_ARMOR_EQUIP_IRON),
	ASTRAL_SILVER("astral_silver", new int[]{2, 5, 6, 2}, 9, 15, 0.0F, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC),
	ATLARUS("atlarus", new int[]{4, 3, 3, 4}, 2, 35, 3.5F, SoundEvents.ITEM_ARMOR_EQUIP_IRON),
	BLACK_STEEL("black_steel", new int[]{3, 5, 6, 3}, 17, 50, 4.0F, SoundEvents.ITEM_ARMOR_EQUIP_IRON),
	BRASS("brass", new int[]{1, 2, 3, 2}, 18, 15, 4.0F, SoundEvents.ITEM_ARMOR_EQUIP_IRON),
	BRONZE("bronze", new int[]{3, 3, 4, 2}, 9, 25, 2.0F, SoundEvents.ITEM_ARMOR_EQUIP_IRON),
	CARMOT("carmot", new int[]{2, 4, 5, 2}, 7, 28, 0.0F, SoundEvents.ITEM_ARMOR_EQUIP_IRON),
	CELENEGIL("celenegil", new int[]{4, 6, 7, 5}, 50, 160, 0.0F, SoundEvents.ITEM_ARMOR_EQUIP_IRON),
	CERUCLASE("ceruclase", new int[]{3, 6, 5, 4}, 50, 137, 0.0F, SoundEvents.ITEM_ARMOR_EQUIP_IRON),
	COPPER("copper", new int[]{2, 3, 2, 1}, 50, 5, 0.0F, SoundEvents.ITEM_ARMOR_EQUIP_IRON),
	DAMASCUS_STEEL("damascus_steel", new int[]{3, 5, 6, 3}, 18, 50, 3.0F, SoundEvents.ITEM_ARMOR_EQUIP_IRON),
	DEEP_IRON("deep_iron", new int[]{2, 4, 5, 2}, 1, 38, 0.0F, SoundEvents.ITEM_ARMOR_EQUIP_IRON),
	DESICHALKOS("desichalkos", new int[]{4, 5, 7, 4}, 30, 180, 3.0F, SoundEvents.ITEM_ARMOR_EQUIP_IRON),
	ELECTRUM("electrum", new int[]{3, 5, 6, 2}, 30, 51, 3.0F, SoundEvents.ENTITY_LIGHTNING_BOLT_IMPACT),
	EXIMITE("eximite", new int[]{4, 5, 6, 4}, 25, 100, 5.0F, SoundEvents.ENTITY_ENDER_DRAGON_GROWL),
	HADEROTH("haderoth", new int[]{4, 5, 7, 4}, 19, 125, 6.0F, SoundEvents.ITEM_ARMOR_EQUIP_IRON),
	HEPATIZON("hepatizon", new int[]{3, 3, 4, 2}, 22, 57, 2.0F, SoundEvents.ITEM_ARMOR_EQUIP_IRON),
	IGNATIUS("ignatius", new int[]{2, 5, 6, 2}, 15, 24, 0.0F, SoundEvents.ITEM_ARMOR_EQUIP_IRON),
	INOLASHITE("inolashite", new int[]{3, 5, 7, 4}, 25, 70, 4.0F, SoundEvents.ITEM_ARMOR_EQUIP_IRON),
	KALENDRITE("kalendrite", new int[]{4, 5, 6, 4}, 20, 40, 3.0F, SoundEvents.ITEM_ARMOR_EQUIP_IRON),
	KRIK("krik", new int[]{2, 4, 3, 1}, 17, 45, 9.0F, SoundEvents.ITEM_ARMOR_EQUIP_IRON),
	LUTETIUM("lutetium", new int[]{5, 6, 5, 4}, 17, 500, 3.0F, SoundEvents.ITEM_ARMOR_EQUIP_IRON),
	MIDASIUM("midasium", new int[]{3, 3, 5, 2}, 35, 16, 4.0F, SoundEvents.ITEM_ARMOR_EQUIP_GOLD),
	MITHRIL("mithril", new int[]{2, 4, 5, 3}, 20, 21, 0.0F, SoundEvents.ITEM_ARMOR_EQUIP_CHAIN),
	ORICHALCUM("orichalcum", new int[]{2, 6, 7, 2}, 4, 20, 1.4F, SoundEvents.ITEM_ARMOR_EQUIP_IRON),
	OSMIUM("osmium", new int[]{4, 5, 5, 4}, 17, 500, 3.0F, SoundEvents.ITEM_ARMOR_EQUIP_IRON),
	OURECLASE("oureclase", new int[]{3, 6, 5, 4}, 2, 28, 1.5F, SoundEvents.ITEM_ARMOR_EQUIP_IRON),
	PLATINUM("platinum", new int[]{3, 5, 6, 3}, 12, 17, 0.5F, SoundEvents.ITEM_ARMOR_EQUIP_IRON),
	PROMETHEUM("prometheum", new int[]{1, 2, 3, 2}, 11, 30, 0.1F, SoundEvents.ITEM_ARMOR_EQUIP_IRON),
	QUICKSILVER("quicksilver", new int[]{4, 7, 5, 4}, 50, 165, 0.0F, SoundEvents.ITEM_ARMOR_EQUIP_IRON),
	SANGUINITE("sanguinite", new int[]{4, 6, 7, 5}, 25, 175, 4.0F, SoundEvents.ITEM_ARMOR_EQUIP_IRON),
	SHADOW_IRON("shadow_iron", new int[]{4, 5, 6, 3}, 3, 32, 4.0F, SoundEvents.ITEM_ARMOR_EQUIP_IRON),
	SHADOW_STEEL("shadow_steel", new int[]{4, 5, 6, 3}, 5, 40, 3.0F, SoundEvents.ITEM_ARMOR_EQUIP_IRON),
	SILVER("silver", new int[]{2, 3, 4, 2}, 20, 8, 2.1F, SoundEvents.ITEM_ARMOR_EQUIP_IRON),
	STEEL("steel", new int[]{3, 5, 6, 3}, 18, 40, 4.0F, SoundEvents.ITEM_ARMOR_EQUIP_IRON),
	TARTARITE("tartarite", new int[]{5, 6, 7, 5}, 20, 300, 6.0F, SoundEvents.ENTITY_WITHER_SKELETON_AMBIENT),
	VULCANITE("vulcanite", new int[]{4, 6, 7, 4}, 20, 150, 4.0F, SoundEvents.ITEM_ARMOR_EQUIP_IRON),
	VYROXERES("vyroxeres", new int[]{4, 5, 6, 3}, 16, 37, 3.0F, SoundEvents.ITEM_ARMOR_EQUIP_IRON);
	
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
