/*
 * -------------------------------------------------------------------------------------------------------
 * Class: MetalStats
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2020.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.material;

import it.hurts.metallurgy_reforged.Metallurgy;
import it.hurts.metallurgy_reforged.block.BlockMetal;
import it.hurts.metallurgy_reforged.block.BlockOre;
import it.hurts.metallurgy_reforged.block.BlockTypes;
import it.hurts.metallurgy_reforged.fluid.FluidMolten;
import it.hurts.metallurgy_reforged.item.ItemMetal;
import it.hurts.metallurgy_reforged.item.ItemTypes;
import it.hurts.metallurgy_reforged.item.armor.ItemArmorBase;
import it.hurts.metallurgy_reforged.item.tool.*;
import it.hurts.metallurgy_reforged.util.Constants;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemTool;
import net.minecraftforge.common.util.EnumHelper;

import java.awt.*;

public class MetalStats {

	private final String name;
	private final int hardness;
	private final int blockHarvest;
	private final float blockBlastResistance;

	//Color
	private final int color;

	private final int oreHarvest;

	private final ArmorStats armor;
	private final ToolStats tool;
	private final GenerationStats genStats;

	private ItemTool.ToolMaterial toolMaterial;
	private ItemArmor.ArmorMaterial armorMaterial;

	public Metal createMetal()
	{
		//name should be in format [snake_case], oreName should be in format [camelCase]

		ItemMetal dust = new ItemMetal(this, ItemTypes.DUST);
		ItemMetal ingot = new ItemMetal(this, ItemTypes.INGOT);
		ItemMetal nugget = new ItemMetal(this, ItemTypes.NUGGET);

		BlockMetal[] blocks = new BlockMetal[5];
		for (int i = 0; i < BlockTypes.values().length; i++)
		{
			blocks[i] = new BlockMetal(this, BlockTypes.values()[i], hardness);
		}

		BlockOre ore = null;
		if (oreHarvest >= 0)
		{
			ore = new BlockOre(name + "_ore", hardness, oreHarvest, blockBlastResistance);
		}

		FluidMolten moltenFluid = new FluidMolten("molten_" + name, color, automaticTemperature());

		ItemArmorBase[] armorPieces = null;
		Item[] tools = null;

		if (armor != null)
		{
			ItemArmor.ArmorMaterial armorMaterial = createArmorMaterial();
			ItemArmorBase helmet = new ItemArmorBase(armorMaterial, EntityEquipmentSlot.HEAD, name + "_helmet");
			ItemArmorBase chestplate = new ItemArmorBase(armorMaterial, EntityEquipmentSlot.CHEST, name + "_chestplate");
			ItemArmorBase leggings = new ItemArmorBase(armorMaterial, EntityEquipmentSlot.LEGS, name + "_leggings");
			ItemArmorBase boots = new ItemArmorBase(armorMaterial, EntityEquipmentSlot.FEET, name + "_boots");

			armorPieces = new ItemArmorBase[]{helmet, chestplate, leggings, boots};
		}

		if (tool != null)
		{
			Item.ToolMaterial toolMaterial = createToolMaterial();
			ItemAxeBase axe = new ItemAxeBase(toolMaterial, name + "_axe");
			ItemHoeBase hoe = new ItemHoeBase(toolMaterial, name + "_hoe");
			ItemPickaxeBase pickaxe = new ItemPickaxeBase(toolMaterial, name + "_pickaxe");
			ItemShovelBase shovel = new ItemShovelBase(toolMaterial, name + "_shovel");
			ItemSwordBase sword = new ItemSwordBase(toolMaterial, name + "_sword");

			tools = new Item[]{axe, hoe, pickaxe, shovel, sword};
		}

		return new Metal(this, ingot, dust, nugget, ore, blocks, moltenFluid, tools, armorPieces);
	}

	private int automaticTemperature()
	{
		float output = 1000F;
		if (blockBlastResistance == Constants.LOW_TIER_BLAST_RESISTANCE)
		{
			output = blockBlastResistance * 60f;
		}
		else if (blockBlastResistance != Constants.UNBREAKABLE_TIER_BLAST_RESISTANCE)
		{
			output = blockBlastResistance * 36F;
		}
		return Math.round(output);
	}

	/**
	 * @param name            name of the metal - snake_case all lowercase with underlines separating words (ex: dark_steel)
	 * @param hardness        the time it takes to break a block made of this metal
	 * @param blockHarvest    the harvest level of the metal block
	 * @param blastResistance the resistance to explosions of the metal block
	 * @param armor           the ArmorStats instance representing this metal's stats, or null if there is no armor
	 * @param tool            the ToolStats instance representing this metal's stats, or null if there are no tools
	 * @param genStats        the GenerationStats instance representing the world generation stats for this metal (is null if this metal is an alloy)
	 * @param oreHarvest      the harvest level of the metal ore or -1 if no ore should be generated
	 * @param color           The representative color of the metal
	 */
	public MetalStats(String name, int hardness, int blockHarvest, float blastResistance, ArmorStats armor, ToolStats tool, GenerationStats genStats, int oreHarvest, int color)
	{
		this.name = name;
		this.hardness = hardness;
		this.blockHarvest = blockHarvest;
		this.blockBlastResistance = blastResistance;
		this.armor = armor;
		this.tool = tool;
		this.genStats = genStats;
		this.oreHarvest = oreHarvest;
		this.color = color;
	}

	public String getName()
	{
		return name;
	}

	public float getBlockBlastResistance()
	{
		return blockBlastResistance;
	}

	public ArmorStats getArmorStats()
	{
		return armor;
	}

	public ToolStats getToolStats()
	{
		return tool;
	}

	public ItemTool.ToolMaterial getToolMaterial()
	{
		return toolMaterial;
	}

	public ItemArmor.ArmorMaterial getArmorMaterial()
	{
		return armorMaterial;
	}

	public int getColorHex()
	{
		return color;
	}

	public long getColorIntWithAlpha()
	{
		String colorWoAlpha = Integer.toHexString(color);
		String colorWAlpha = "0xff" + colorWoAlpha;
		return Long.decode(colorWAlpha);
	}

	public float[] getRGBValues()
	{
		Color rgb = new Color(color);
		return rgb.getRGBColorComponents(null);
	}

	private ItemArmor.ArmorMaterial createArmorMaterial()
	{
		if (armor == null)
			throw new UnsupportedOperationException("No Armor Stats Loaded");

		return armorMaterial = EnumHelper.addArmorMaterial(this.getName(), Metallurgy.MODID + ":" + this.getName(), armor.getDurability(), armor.getDamageReduction(), armor.getArmorMagic(), armor.getEquipSound(), armor.getToughness());
	}

	private Item.ToolMaterial createToolMaterial()
	{
		if (tool == null)
			throw new UnsupportedOperationException("No Tool Stats Loaded");

		return toolMaterial = EnumHelper.addToolMaterial(this.getName(), tool.getHarvestLevel(), tool.getMaxUses(), tool.getEfficiency(), tool.getDamage(), tool.getToolMagic());
	}
}
