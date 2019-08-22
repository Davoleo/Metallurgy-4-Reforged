package it.hurts.metallurgy_reforged.material;

import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;

public enum ModToolTier implements IItemTier {

	ADAMANTINE(22, 7, 1550, 10F, 5F),
	AMORDRINE(50, 5, 500, 14F, 4F);
	
	private final int toolMagic, harvestLevel, maxUses;
    private final float efficiency, damage;
	
    private ModToolTier(int toolMagic, int harvestLevel, int maxUses, float efficiency, float damage) {
    	this.toolMagic = toolMagic;
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
		return toolMagic;
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
		// TODO Auto-generated method stub
		return null;
	}
}
