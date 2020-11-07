/*==============================================================================
 = Class: BlockCrusher
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2020.
 =============================================================================*/

package it.hurts.metallurgy_reforged.block;

import it.hurts.metallurgy_reforged.Metallurgy;
import it.hurts.metallurgy_reforged.gui.GuiHandler;
import it.hurts.metallurgy_reforged.tileentity.TileEntityCrusher;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.Random;

public class BlockCrusher extends BlockTileEntity<TileEntityCrusher> {

	//Internal State and Variables -----------------------------------------------------

	//The facing state of the block (Where is the machine front located (Possible values for this block: NORTH, SOUTH, EAST, WEST))
	public static final PropertyDirection FACING = BlockHorizontal.FACING;
	//The functioning state of the block (TRUE if the crusher is active | FALSE if the crusher is idle)
	public static final PropertyBool BURNING = PropertyBool.create("burning");
	private static boolean keepInventory;

	//Constructor -----------------------------------------------------------------------
	//Constructor to create the instance of the Crusher Block
	public BlockCrusher(String name)
	{
		super(Material.IRON, name);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(BURNING, false));
	}

	//Custom Methods --------------------------------------------------------------------

	//Sets a certain state to the block, depends on the parameters
	public static void setState(boolean active, World worldIn, BlockPos pos)
	{
		IBlockState state = worldIn.getBlockState(pos);
		TileEntity tileEntity = worldIn.getTileEntity(pos);
		keepInventory = true;

		if (active)
			worldIn.setBlockState(pos, ModBlocks.crusher.getDefaultState().withProperty(FACING, state.getValue(FACING)).withProperty(BURNING, true), 3);
		else
			worldIn.setBlockState(pos, ModBlocks.crusher.getDefaultState().withProperty(FACING, state.getValue(FACING)).withProperty(BURNING, false), 3);

		keepInventory = false;

		if (tileEntity != null)
		{
			tileEntity.validate();
			worldIn.setTileEntity(pos, tileEntity);
		}
	}

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

	//Overrides the information about the items to drop when the block is broken
	@Nonnull
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		return Item.getItemFromBlock(ModBlocks.crusher);
	}

	//Overrides the ItemStack that the player picks up
	@Nonnull
	@Override
	public ItemStack getPickBlock(@Nonnull IBlockState state, @Nonnull RayTraceResult target, @Nonnull World world, @Nonnull BlockPos pos, EntityPlayer player)
	{
		return new ItemStack(ModBlocks.crusher);
	}

	//Called when the block is right-clicked by a player
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		if (!world.isRemote)
		{
			TileEntity te = world.getTileEntity(pos);

			if (te instanceof TileEntityCrusher)
			{
				player.openGui(Metallurgy.instance, GuiHandler.CRUSHER, world, pos.getX(), pos.getY(), pos.getZ());
			}
		}
		return true;
	}

	//Overrides the light level of this block
	//It returns 0 if the Crusher BURNING state is false, it returns 8 if the Crusher BURNING state is true
	@Override
	public int getLightValue(IBlockState state, IBlockAccess world, BlockPos pos)
	{
		if (state.getValue(BURNING))
		{
			return 8;
		}
		else if (!state.getValue(BURNING))
		{
			return 0;
		}
		else
			return 0;
	}

	//Called serverSide after this block is replaced with another in Chunk, but before the Tile Entity is updated
	@Override
	public void breakBlock(@Nonnull World world, @Nonnull BlockPos pos, @Nonnull IBlockState state)
	{
		if (!keepInventory)
		{
			TileEntity te = world.getTileEntity(pos);

			if (te instanceof TileEntityCrusher)
			{
				InventoryHelper.dropInventoryItems(world, pos, (TileEntityCrusher) te);
			}
		}
	}

	//Overridden to true because this block has a TileEntity Attached
	@Override
	public boolean hasTileEntity(IBlockState state)
	{
		return true;
	}

	//Links the TileEntity Class with the Block Class
	@Override
	public Class<TileEntityCrusher> getTileEntityClass()
	{
		return TileEntityCrusher.class;
	}

	//Returns a new Instance of the TileEntity
	@Override
	public TileEntityCrusher createTileEntity(@Nonnull World world, @Nonnull IBlockState state)
	{
		return new TileEntityCrusher();
	}

	//Gets the state for when the player places the block
	//We getOpposite since we want the front of the Block to be directly facing our side
	@Nonnull
	@Override
	public IBlockState getStateForPlacement(@Nonnull World world, @Nonnull BlockPos pos, @Nonnull EnumFacing facing, float hitX, float hitY, float hitZ, int meta, @Nonnull EntityLivingBase placer, @Nonnull EnumHand hand)
	{
		return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
	}

	//Called by ItemBlocks after a block is set in the world, to allow post-place logic
	//We getOpposite since we want the front of the Block to be directly facing our side
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
	{
		worldIn.setBlockState(pos, this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);

		if (stack.hasDisplayName())
		{
			TileEntity te = worldIn.getTileEntity(pos);

			if (te instanceof TileEntityCrusher)
			{
				((TileEntityCrusher) te).setCustomName(stack.getDisplayName());
			}
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean isFullCube(IBlockState state)
	{
		return false;
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean isOpaqueCube(IBlockState state)
	{
		return false;
	}

	//Overrides the Type of rendering the block has (MODEL means: mixed static-TESR)
	//Calling is deprecated / Overriding is fine
	@SuppressWarnings("deprecation")
	@Nonnull
	@Override
	public EnumBlockRenderType getRenderType(IBlockState state)
	{
		return EnumBlockRenderType.MODEL;
	}

	//Gets the state from how much the block is rotated
	//Calling is deprecated / Overriding is fine
	@SuppressWarnings("deprecation")
	@Nonnull
	@Override
	public IBlockState withRotation(@Nonnull IBlockState state, Rotation rot)
	{
		return state.withProperty(FACING, rot.rotate(state.getValue(FACING)));
	}

	//Gets the state from how much the block is mirrored
	//Calling is deprecated / Overriding is fine
	@SuppressWarnings("deprecation")
	@Nonnull
	@Override
	public IBlockState withMirror(@Nonnull IBlockState state, Mirror mirrorIn)
	{
		return state.withRotation(mirrorIn.toRotation(state.getValue(FACING)));
	}

	//Creates a new BlockStateContainer instance with the Properties of the block
	@Nonnull
	@Override
	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, BURNING, FACING);
	}

	//Gets the state from the metadata value (will probably be gone for 1.13.2)
	@SuppressWarnings("deprecation")
	@Nonnull
	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		return this.getDefaultState().withProperty(FACING, getFacing(meta)).withProperty(BURNING, (meta & 4) != 0);
	}

	//Called after the block is set in the Chunk data, but before the Tile Entity is set
	//Adjusts the rotation at which the block is placed, based on the blocks around the Crusher and the angle of the player while placing the block
	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
	{
		if (!worldIn.isRemote)
		{
			IBlockState north = worldIn.getBlockState(pos.north());
			IBlockState south = worldIn.getBlockState(pos.south());
			IBlockState west = worldIn.getBlockState(pos.west());
			IBlockState east = worldIn.getBlockState(pos.east());
			EnumFacing face = state.getValue(BlockCrusher.FACING);

			if (face == EnumFacing.NORTH && north.isFullBlock() && !south.isFullBlock())
				face = EnumFacing.SOUTH;
			else if (face == EnumFacing.SOUTH && south.isFullBlock() && !north.isFullBlock())
				face = EnumFacing.NORTH;
			else if (face == EnumFacing.WEST && west.isFullBlock() && !east.isFullBlock())
				face = EnumFacing.EAST;
			else if (face == EnumFacing.EAST && east.isFullBlock() && !west.isFullBlock())
				face = EnumFacing.WEST;

			worldIn.setBlockState(pos, state.withProperty(BlockCrusher.FACING, face), 2);
		}
	}

	//Gets the metadata value from the given blockState
	@Override
	public int getMetaFromState(IBlockState state)
	{
		int i = 0;
		i = i | getMetaForFacing(state.getValue(FACING));

		if ((state.getValue(BURNING)))
		{
			i |= 4;
		}

		return i;
	}

	//Overrides the creativeTab
	@Nonnull
	@Override
	public BlockCrusher setCreativeTab(@Nonnull CreativeTabs tab)
	{
		super.setCreativeTab(tab);
		return this;
	}

}
