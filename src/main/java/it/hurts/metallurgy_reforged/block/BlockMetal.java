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
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

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
		this.setLightOpacity(1);

		BlockUtils.initBlock(this, metal.getName() + "_" + type.getPrefix(), MetallurgyTabs.tabBlock, hardness, metal.getBlockBlastResistance(), Constants.Tools.PICKAXE, 2);
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean isFullBlock(@Nonnull IBlockState state)
	{
		return this.type != BlockTypes.GLASS;
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean isOpaqueCube(@Nonnull IBlockState state)
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

	// TODO: 11/05/2020 Find a way to make it look decent
	@SuppressWarnings("deprecation")
	@SideOnly(Side.CLIENT)
	@Override
	public boolean shouldSideBeRendered(@Nonnull IBlockState blockState, @Nonnull IBlockAccess blockAccess, @Nonnull BlockPos pos, @Nonnull EnumFacing side)
	{
		IBlockState neighbourState = blockAccess.getBlockState(pos.offset(side));
		Block neighbourBlock = neighbourState.getBlock();

		if (type == BlockTypes.GLASS)
		{
			if (blockState != neighbourState)
			{
				return true;
			}

			if (neighbourBlock == this)
			{
				return false;
			}
		}

		return super.shouldSideBeRendered(blockState, blockAccess, pos, side);
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
