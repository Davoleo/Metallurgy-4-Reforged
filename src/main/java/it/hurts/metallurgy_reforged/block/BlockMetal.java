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
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;

import javax.annotation.Nonnull;

public class BlockMetal extends Block {

	private MetalStats metal;
	private BlockTypes type;

	public BlockMetal(MetalStats metal, BlockTypes type, float hardness)
	{
		super(Material.IRON);
		this.metal = metal;
		this.type = type;
		this.setSoundType(SoundType.METAL);

		BlockUtils.initBlock(this, metal.getName() + "_" + type.getPrefix(), MetallurgyTabs.tabBlock, hardness, metal.getBlockBlastResistance(), Constants.Tools.PICKAXE, 2);
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean isFullBlock(@Nonnull IBlockState state)
	{
		return this.type != BlockTypes.GLASS;
	}

	@Nonnull
	@Override
	public BlockRenderLayer getRenderLayer()
	{
		if (type == BlockTypes.GLASS)
			return BlockRenderLayer.CUTOUT;
		else
			return BlockRenderLayer.SOLID;

	}

	public MetalStats getMetalStats()
	{
		return metal;
	}

	public BlockTypes getType()
	{
		return type;
	}

}