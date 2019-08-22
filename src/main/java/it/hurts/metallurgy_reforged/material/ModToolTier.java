package it.hurts.metallurgy_reforged.material;

import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;

public enum ModToolTier implements IItemTier {

	ADAMANTINE(22, 7, 1550, 10F, 5F),
	AMORDRINE(50, 5, 500, 14F, 4F),
	ANGMALLEN(30, 3, 300, 7F, 3F),
	ASTRAL_SILVER(30, 5, 350, 12F, 2F),
	ATLARUS(22, 7, 1750, 10F, 5F),
	BLACK_STEEL(17, 3, 500, 8.0F, 3.0F),
	BRASS(18, 1, 15, 10.0F, 2.0F),
	BRONZE(9, 3, 250, 6.0F, 2.0F),
	CARMOT(40, 5, 500, 12.0F, 2.0F),
	CELENEGIL(50, 6, 1600, 14.0F, 4.0F),
	CERUCLASE(18, 4, 500, 7.0F, 4.0F),
	COPPER(5, 2, 180, 5.0F, 2.0F),
	DAMASCUS_STEEL(18, 4, 500, 6.0F, 3.0F),
	DEEP_IRON(14, 3, 250, 6.0F, 3.0F),
	DESICHALKOS(30, 4, 1800, 8.0F, 5.0F),
	ELECTRUM(30, 2, 70, 14.0F, 2.0F),
	EXIMITE(25, 7, 1000, 8.0F, 4.0F),
	HADEROTH(19, 5, 1250, 12.0F, 4.0F),
	HEPATIZON(22, 3, 300, 8.0F, 2.0F),
	IGNATIUS(15, 2, 200, 4.0F, 3.0F),
	INOLASHITE(25, 5, 900, 8.0F, 4.0F),
	KALENDRITE(20, 5, 1000, 8.0F, 4.0F),
	KRIK(17, 4, 350, 5.0F, 7.0F),
	MIDASIUM(35, 4, 100, 10.0F, 7.0F),
	MITHRIL(18, 5, 1000, 9.0F, 7.0F),
	ORICHALCUM(20, 6, 1350, 9.0F, 7.0F),
	OURECLASE(18, 3, 750, 8.0F, 6.0F),
	PLATINUM(50, 3, 250, 16.0F, 5.0F),
	PROMETHEUM(16, 2, 200, 4.0F, 5.0F),
	QUICKSILVER(20, 5, 1100, 14.0F, 7.0F),
	SANGUINITE(25, 7, 1750, 11.0F, 8.0F),
	SHADOW_IRON(3, 2, 300, 5.0F, 6.0F),
	SHADOW_STEEL(5, 3, 400, 6.0F, 7.0F),
	SILVER(20, 1, 25, 15.0F, 5.0F),
	STEEL(18, 4, 750, 8.0F, 7.0F),
	TARTARITE(25, 8, 3000, 14.0F, 9.0F),
	VULCANITE(20, 6, 1500, 10.0F, 7.0F),
	VYROXERES(16, 4, 300, 7.0F, 7.0F);
	
	private final int enchantability, harvestLevel, maxUses;
    private final float efficiency, damage;
	
    private ModToolTier(int enchantability, int harvestLevel, int maxUses, float efficiency, float damage) {
    	this.enchantability = enchantability;
        this.harvestLevel = harvestLevel;
        this.maxUses = maxUses;
        this.efficiency = efficiency;
        this.damage = damage;
	}
    
	@Override
	public float getAttackDamage() {
		return damage;
	}

	@Override
	public float getEfficiency() {
		return efficiency;
	}

	@Override
	public int getEnchantability() {
		return enchantability;
	}

	@Override
	public int getHarvestLevel() {
		return harvestLevel;
	}

	@Override
	public int getMaxUses() {
		return maxUses;
	}

	@Override
	public Ingredient getRepairMaterial() {
		return null;
	}
}
