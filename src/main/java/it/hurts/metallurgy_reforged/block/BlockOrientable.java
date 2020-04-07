/*
 * -------------------------------------------------------------------------------------------------------
 * Class: BlockOrientable
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2020.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.block;

import it.hurts.metallurgy_reforged.util.BlockUtils;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

//A block that has an additional direction property
public class BlockOrientable extends Block {

	//Internal State and Variables -----------------------------------------------------

	//The facing state of the block (Where is the machine front located (Possible values for this block: NORTH, SOUTH, EAST, WEST))
	public static final PropertyDirection FACING = PropertyDirection.create("facing");

	//Constructor -----------------------------------------------------------------------

	//Creates a new Instance of an Orientable block
	public BlockOrientable(Material material, String name, CreativeTabs tab)
	{
		super(material);
		BlockUtils.initBlock(this, name, tab);
		ModBlocks.miscBlocks.add(this);
	}

	//Custom Methods --------------------------------------------------------------------

	//gets the facing from the metadata value
	private static EnumFacing getFacing(int meta)
	{
		switch (meta & 3)
		{
			case 0:
				return EnumFacing.NORTH;
			case 1:
				return EnumFacing.SOUTH;
			case 2:
				return EnumFacing.WEST;
			case 3:
			default:
				return EnumFacing.EAST;
		}
	}

	//gets the metadata value for the facing
	private static int getMetaForFacing(EnumFacing facing)
	{
		switch (facing)
		{
			case NORTH:
				return 0;
			case SOUTH:
				return 1;
			case WEST:
				return 2;
			case EAST:
			default:
				return 3;
		}
	}

	//Overridden Methods ----------------------------------------------------------------

	//Gets the state for when the player places the block
	@Nonnull
	@Override
	public IBlockState getStateForPlacement(@Nonnull World world, @Nonnull BlockPos pos, @Nonnull EnumFacing facing, float hitX, float hitY, float hitZ, int meta, @Nonnull EntityLivingBase placer, EnumHand hand)
	{
		return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing());
	}

	//Creates a new BlockStateContainer instance with the Properties of the block
	@Nonnull
	@Override
	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, FACING);
	}

	//Gets the metadata value from the given blockState
	@Override
	public int getMetaFromState(IBlockState state)
	{
		int i = 0;
		i = i | getMetaForFacing(state.getValue(FACING));

		return i;
	}

	//Gets the state from the metadata value (will probably be gone for 1.13.2)
	@SuppressWarnings("deprecation")
	@Nonnull
	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		return this.getDefaultState().withProperty(FACING, getFacing(meta));
	}

}
