/*
 * -------------------------------------------------------------------------------------------------------
 * Class: Metal
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2020.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.material;

import it.hurts.metallurgy_reforged.block.BlockMetal;
import it.hurts.metallurgy_reforged.block.BlockOre;
import it.hurts.metallurgy_reforged.block.BlockTypes;
import it.hurts.metallurgy_reforged.fluid.FluidMolten;
import it.hurts.metallurgy_reforged.item.ItemMetal;
import it.hurts.metallurgy_reforged.item.armor.ItemArmorBase;
import it.hurts.metallurgy_reforged.item.tool.EnumTools;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemTool;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class Metal {

	//Metal Properties
	private final MetalStats stats;

	//Items
	private final ItemMetal ingot, dust, nugget;
	//Blocks
	private final BlockOre ore;
	private final BlockMetal[] blocks;

	//Tools & Armor
	private final Item[] toolSet;
	private final ItemArmorBase[] armorSet;

	//Fluid
	private final FluidMolten molten;

	public Metal(MetalStats stats, ItemMetal ingot, ItemMetal dust, ItemMetal nugget, BlockOre ore, BlockMetal[] blocks, FluidMolten molten, Item[] toolSet, ItemArmorBase[] armorSet)
	{
		this.stats = stats;
		this.ingot = ingot;
		this.dust = dust;
		this.nugget = nugget;
		this.ore = ore;
		this.blocks = blocks;
		this.molten = molten;
		this.toolSet = toolSet;
		this.armorSet = armorSet;

		ModMetals.metalMap.put(stats.getName(), this);
	}

	public ItemTool.ToolMaterial getToolMaterial()
	{
		return stats.getToolMaterial();
	}

	public ItemArmor.ArmorMaterial getArmorMaterial()
	{
		return stats.getArmorMaterial();
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
	public MetalStats getStats()
	{
		return stats;
	}

	@Nonnull
	public ItemMetal getIngot()
	{
		return ingot;
	}

	@Nonnull
	public ItemMetal getDust()
	{
		return dust;
	}

	@Nonnull
	public ItemMetal getNugget()
	{
		return nugget;
	}

	@Nullable
	public BlockOre getOre()
	{
		return ore;
	}

	@Nonnull
	public BlockMetal[] getBlocks()
	{
		return blocks;
	}

	public BlockMetal getBlock(BlockTypes type)
	{
		return blocks[type.ordinal()];
	}

	@Nonnull
	public FluidMolten getMolten()
	{
		return molten;
	}

	/**
	 * @param toolClass The kind of tool you want from a set of tools
	 *
	 * @return one of the tools in the toolSet
	 */
	public Item getTool(EnumTools toolClass)
	{
		return toolSet[toolClass.ordinal()];
	}

	/**
	 * @param armorPiece The armor piece
	 *
	 * @return one of the armor pieces in the armorSet
	 */
	public ItemArmorBase getArmorPiece(EntityEquipmentSlot armorPiece)
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

	/**
	 * @return The metal name in snake_case
	 */
	@Override
	public String toString()
	{
		return stats.getName();
	}

}
