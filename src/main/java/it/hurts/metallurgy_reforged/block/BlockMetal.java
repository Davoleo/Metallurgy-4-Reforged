/*
 * -------------------------------------------------------------------------------------------------------
 * Class: BlockMetal
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2020.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.block;

import it.hurts.metallurgy_reforged.material.MetalStats;
import it.hurts.metallurgy_reforged.util.BlockUtils;
import it.hurts.metallurgy_reforged.util.Constants;
import it.hurts.metallurgy_reforged.util.MetallurgyTabs;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

import java.util.Objects;

public class BlockMetal extends Block {

	private MetalStats metal;
	private BlockTypes type;

	public BlockMetal(MetalStats metal, BlockTypes type, int hardness)
	{
		super(Material.IRON);
		this.metal = metal;
		this.type = type;
		this.setSoundType(SoundType.METAL);

		BlockUtils.initBlock(this, metal.toString() + "_" + type.getPrefix(), MetallurgyTabs.tabBlock, hardness, metal.getBlockBlastResistance(), Constants.PICKAXE, 1);
		ModBlocks.metalBlocks.get(type).add(this);
	}

	/**
	 * @return A new ItemBlock of this block
	 */
	public Item createItemBlock()
	{
		return new ItemBlock(this).setRegistryName(Objects.requireNonNull(getRegistryName()));
	}


}
