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
import it.hurts.metallurgy_reforged.item.tool.EnumTools;
import net.minecraft.block.material.Material;
import net.minecraft.inventory.EntityEquipmentSlot;
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
        return stats.getToolMaterial();
    }

    public ItemArmor.ArmorMaterial getArmorMaterial() {
        return stats.getArmorMaterial();
    }

    public void initFluidBlock() {
        fluidBlock = new FluidBlockBase(molten, Material.LAVA, "molten_" + stats.getName());
    }

    /**
     * @return whether the metal has tools
     */
    public boolean hasToolSet()
    {
        return stats.getToolStats() != null;
    }

    /**
     * @return whether the metal has armor
     */
    public boolean hasArmorSet()
    {
        return stats.getArmorStats() != null;
    }

    /**
     * @return whether the metal is an alloy
     */
    public boolean isAlloy()
    {
        return ore == null;
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
     * @param toolClass The kind of tool you want from a set of tools
     * @return one of the tools in the toolSet
     */
    public Item getTool(EnumTools toolClass)
    {
        return toolSet[toolClass.ordinal()];
    }

    /**
     * @param armorPiece The armor piece
     * @return one of the armor pieces in the armorSet
     */
    public ItemArmorBase getArmor(EntityEquipmentSlot armorPiece)
    {
        return armorPiece.getSlotType() == EntityEquipmentSlot.Type.ARMOR ? armorSet[3 - armorPiece.getIndex()] : null;
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
