/*
 * -------------------------------------------------------------------------------------------------------
 * Class: Metal
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2019.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.material;

import it.hurts.metallurgy_reforged.Metallurgy;
import it.hurts.metallurgy_reforged.block.BlockOreDict;
import it.hurts.metallurgy_reforged.block.fluid.FluidBlockBase;
import it.hurts.metallurgy_reforged.fluid.FluidMolten;
import it.hurts.metallurgy_reforged.item.ItemOreDict;
import it.hurts.metallurgy_reforged.item.armor.ItemArmorBase;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemTool;
import net.minecraftforge.common.util.EnumHelper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class Metal {
    private final MetalStats stats;

    private final ItemOreDict ingot, dust, nugget;
    private final BlockOreDict ore, block;
    private final FluidMolten molten;

    private Item[] toolSet;
    private ItemArmorBase[] armorSet;

    private FluidBlockBase fluidBlock;
    private ItemTool.ToolMaterial toolMat;
    private ItemArmor.ArmorMaterial armorMat;

    public Metal(MetalStats stats, ItemOreDict ingot, ItemOreDict dust, ItemOreDict nugget, BlockOreDict ore, BlockOreDict block, FluidMolten molten, Item[] toolSet, ItemArmorBase[] armorSet) {
        this.stats = stats;
        this.ingot = ingot;
        this.dust = dust;
        this.nugget = nugget;
        this.ore = ore;
        this.block = block;
        this.molten = molten;
        this.toolSet = toolSet;
        this.armorSet = armorSet;
        ModMetals.metalList.add(this);
    }

    public ItemTool.ToolMaterial getToolMaterial() {
        if (toolMat == null) {
            ToolStats tStats = stats.getToolStats();
            if(tStats == null) {
                throw new UnsupportedOperationException("No Tool Stats Loaded");
            }
            this.toolMat = EnumHelper.addToolMaterial(stats.getName().toUpperCase(), tStats.getHarvestLevel(), tStats.getMaxUses(), tStats.getEfficiency(), tStats.getDamage(), tStats.getToolMagic());
        }
        return toolMat;
    }

    public ItemArmor.ArmorMaterial getArmorMaterial() {
        if (armorMat == null) {
            ArmorStats aStats = stats.getArmorStats();
            if(aStats == null) {
                throw new UnsupportedOperationException("No Armor Stats Loaded");
            }
            this.armorMat = EnumHelper.addArmorMaterial(stats.getName().toUpperCase(), Metallurgy.MODID + ":" + stats.getName(), aStats.getDurability(), aStats.getDamageReduction(), aStats.getArmorMagic(), aStats.getEquipSound(), aStats.getToughness());
        }
        return armorMat;
    }

    public void initFluidBlock() {
        fluidBlock = new FluidBlockBase(molten, Material.LAVA, "molten_" + stats.getName());
    }

    public boolean hasToolSet()
    {
        return stats.getToolStats() != null;
    }

    public boolean hasArmorSet()
    {
        return stats.getArmorStats() != null;
    }

    @Nonnull
    public MetalStats getStats() {
        return stats;
    }

    @Nonnull
    public ItemOreDict getIngot() {
        return ingot;
    }

    @Nonnull
    public ItemOreDict getDust() {
        return dust;
    }

    @Nonnull
    public ItemOreDict getNugget() {
        return nugget;
    }

    @Nullable
    public BlockOreDict getOre() {
        return ore;
    }

    @Nonnull
    public BlockOreDict getBlock() {
        return block;
    }

    @Nonnull
    public FluidMolten getMolten() {
        return molten;
    }

    @Nonnull
    public FluidBlockBase getFluidBlock() {
        return fluidBlock;
    }

    /**
     * 0 = axe <br>
     * 1 = hoe <br>
     * 2 = pickaxe <br>
     * 3 = shovel <br>
     * 4 = sword <br>
     * @param key the index of the tool in the toolSet Array
     * @return one of the tools in the toolSet
     */
    public Item getTool(int key)
    {
        return toolSet[key];
    }

    /**
     * 0 = helmet <br>
     * 1 = chestPlate <br>
     * 2 = leggings <br>
     * 3 = boots <br>
     * @param key the index of the armor Piece in the armorSet Array
     * @return one of the armor pieces in the armorSet
     */
    public ItemArmorBase getArmor(int key)
    {
        return armorSet[key];
    }

    public Item[] getToolSet()
    {
        return toolSet;
    }

    public ItemArmorBase[] getArmorSet()
    {
        return armorSet;
    }


    @Override
    public String toString()
    {
        return stats.getName();
    }
}
