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

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import it.hurts.metallurgy_reforged.fluid.FluidMolten;
import it.hurts.metallurgy_reforged.item.armor.ItemArmorBase;
import it.hurts.metallurgy_reforged.item.tool.EnumTools;
import net.minecraft.block.Block;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;

public class Metal {
    private final MetalStats stats;

    private final Item ingot, dust, nugget;
    private final Block ore, block;
    private final FluidMolten molten;

    private Item[] toolSet;
    private ItemArmorBase[] armorSet;

    //private FluidBlockBase fluidBlock;

    public Metal(MetalStats stats, Item ingot, Item dust, Item nugget, Block ore, Block block, FluidMolten molten, Item[] toolSet, ItemArmorBase[] armorSet) {
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

    public IItemTier getToolMaterial() {
        return stats.getToolMaterial();
    }

    public IArmorMaterial getArmorMaterial() {
        return stats.getArmorMaterial();
    }

//    public void initFluidBlock() {
//        fluidBlock = new FluidBlockBase(molten, Material.LAVA, "molten_" + stats.getName());
//    }

    /**
     * @return whether the metal has tools
     */
    public boolean hasToolSet()
    {
        return getToolMaterial() != null;
    }

    /**
     * @return whether the metal has armor
     */
    public boolean hasArmorSet()
    {
        return getArmorMaterial() != null;
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
    public Item getIngot() {
        return ingot;
    }

    @Nonnull
    public Item getDust() {
        return dust;
    }

    @Nonnull
    public Item getNugget() {
        return nugget;
    }

    @Nullable
    public Block getOre() {
        return ore;
    }

    @Nonnull
    public Block getBlock() {
        return block;
    }

    @Nonnull
    public FluidMolten getMolten() {
        return molten;
    }

//    @Nonnull
//    public FluidBlockBase getFluidBlock() {
//        return fluidBlock;
//    }

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
    public ItemArmorBase getArmor(EquipmentSlotType armorPiece)
    {
        return armorPiece.getSlotType() == EquipmentSlotType.Group.ARMOR ? armorSet[3 - armorPiece.getIndex()] : null;
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
