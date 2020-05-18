/*
 * -------------------------------------------------------------------------------------------------------
 * Class: BlockTileEntity
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 * Copyright (c) 2020.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.block;

import it.hurts.metallurgy_reforged.util.BlockUtils;
import it.hurts.metallurgy_reforged.util.Constants;
import it.hurts.metallurgy_reforged.util.MetallurgyTabs;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

//An abstract base class for TileBlocks | The generic parameter links the TileBlock Class with the TileEntity Class
public abstract class BlockTileEntity<TE extends TileEntity> extends Block {

	//Constructor ----------------------------------------------------------------

	public BlockTileEntity(Material material, String name)
	{
		super(material);
		BlockUtils.initBlock(this, name, MetallurgyTabs.tabSpecial, 6F, 8F, Constants.Tools.PICKAXE, 1);
		setSoundType(SoundType.METAL);
		ModBlocks.miscBlocks.add(this);
	}

	//Custom Methods -------------------------------------------------------------

	//abstract AKA unimplemented in the base class
	//gets the linked TileEntity class
	@SuppressWarnings("unused")
	public abstract Class<TE> getTileEntityClass();

	//gets the Linked tileEntity
	@SuppressWarnings({"unchecked", "unused"})
	public TE getTileEntity(IBlockAccess world, BlockPos pos)
	{
		return (TE) world.getTileEntity(pos);
	}

	//Overridden Methods -------------------------------------------------------------

	//Returns true if the block is linked to a tile entity
	@Override
	public boolean hasTileEntity(IBlockState state)
	{
		return true;
	}

	//creates a new instance of the linked Tile Entity
	@Nullable
	@Override
	public abstract TE createTileEntity(@Nonnull World world, @Nonnull IBlockState state);

}
