package it.hurts.metallurgy_reforged.block;

import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.annotation.Nonnull;

import com.google.common.collect.Lists;

import it.hurts.metallurgy_reforged.recipe.BlockSublimationRecipes;
import it.hurts.metallurgy_reforged.tileentity.TileEntityChamber;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionUtils;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockChamber extends BlockTileEntity<TileEntityChamber>{

	public static final PropertyBool ACTIVE = PropertyBool.create("lit");
	public static final PropertyDirection FACING = BlockHorizontal.FACING;


	public BlockChamber(String name) {
		super(Material.IRON, name);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(ACTIVE, false));
	}

	@Override
	public Class<TileEntityChamber> getTileEntityClass() {
		return TileEntityChamber.class;
	}

	@Override
	public TileEntityChamber createTileEntity(World world, IBlockState state) {
		return new TileEntityChamber();
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {

		ItemStack stack = playerIn.getHeldItem(hand);

		TileEntity te = worldIn.getTileEntity(pos);

		if (te instanceof TileEntityChamber)
		{
			TileEntityChamber chamber = (TileEntityChamber) te;

			BlockSublimationRecipes recipes = BlockSublimationRecipes.getInstance();		

			int recipeAmount = recipes.getSublimationBlockAmount(stack);

			int currentMetalAmount = chamber.getStackInSlot(TileEntityChamber.METAL_SLOT).getCount();


			ItemStack fuelStack = chamber.getStackInSlot(TileEntityChamber.FUEL_SLOT);
			int currentFuelAmount = fuelStack.getCount();


			if(facing == EnumFacing.UP)
			{
				if(recipeAmount > 0 && currentMetalAmount < recipeAmount)
				{
					int i = recipeAmount - currentMetalAmount;

					ItemStack copyStack = stack.splitStack(i);
					copyStack.setCount(currentMetalAmount + copyStack.getCount());
					chamber.setInventorySlotContents(TileEntityChamber.METAL_SLOT, copyStack);

					worldIn.playSound((EntityPlayer)null, pos, SoundEvents.BLOCK_METAL_PLACE, SoundCategory.BLOCKS, 1F, 1F);

					return true;

				}
			}
			else if(facing != EnumFacing.DOWN && (fuelStack.isEmpty() || stack.isItemEqualIgnoreDurability(fuelStack)) && chamber.isItemValidForSlot(TileEntityChamber.FUEL_SLOT, stack))
			{
				int i = fuelStack.getMaxStackSize() - currentFuelAmount;
				ItemStack copyStack = stack.splitStack(i);
				copyStack.setCount(currentFuelAmount + copyStack.getCount());
				chamber.setInventorySlotContents(TileEntityChamber.FUEL_SLOT, copyStack);
				return true;

			}

		}


		return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
	}

	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer,ItemStack stack) {
		if(stack.hasTagCompound())
		{
			TileEntity te = worldIn.getTileEntity(pos);

			if (te instanceof TileEntityChamber)
			{
				TileEntityChamber chamber = (TileEntityChamber) te;
				NBTTagCompound tag = stack.getTagCompound();

				chamber.readChamberFromNBT(tag);

				if(chamber.potionEffect != null)
					worldIn.setBlockState(pos, state.withProperty(ACTIVE, true));
			}
		}
	}

	//Called serverSide after this block is replaced with another in Chunk, but before the Tile Entity is updated
	@Override
	public void breakBlock(@Nonnull World world, @Nonnull BlockPos pos, @Nonnull IBlockState state)
	{
		TileEntity te = world.getTileEntity(pos);

		if (te instanceof TileEntityChamber)
		{

			ItemStack itemStack = new ItemStack(this);

			TileEntityChamber chamber = (TileEntityChamber) te;
			NBTTagCompound tag = new NBTTagCompound();
			chamber.writeChamberToNBT(tag);

			itemStack.setTagCompound(tag);

			spawnAsEntity(world, pos, itemStack);
			world.updateComparatorOutputLevel(pos, state.getBlock());

			if(!world.isRemote && chamber.potionEffect != null)
			{
				for(WorldServer worldServer : world.getMinecraftServer().worlds)	
				{
					for(UUID uuid : chamber.affectedPlayers)
					{
						EntityPlayer player = worldServer.getPlayerEntityByUUID(uuid);
						if(player != null) 
							player.removePotionEffect(chamber.potionEffect.getPotion());		
					}
				}
			}			
	}



	super.breakBlock(world, pos, state);
}

public void dropBlockAsItemWithChance(World worldIn, BlockPos pos, IBlockState state, float chance, int fortune)
{
}

@SideOnly(Side.CLIENT)
public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand)
{
	TileEntity tileEntity = worldIn.getTileEntity(pos);
	if(tileEntity instanceof TileEntityChamber)
	{

		TileEntityChamber chamber = (TileEntityChamber) tileEntity;

		if(chamber.potionEffect != null && chamber.fuelTime > 0)
		{
			for(EnumFacing facing : EnumFacing.HORIZONTALS)
			{
				for(int i = 0;i < 15;i++)
				{
					double d0 = (double)((float)pos.getX() + 0.3F + rand.nextFloat() * 0.8F);
					double d1 = (double)((float)pos.getY() + 0.1F + rand.nextFloat() * 0.3F);
					double d2 = (double)((float)pos.getZ() + 0.3F + rand.nextFloat() * 0.8F);

					List<PotionEffect> effect = Lists.newArrayList();
					effect.add(chamber.potionEffect);

					int c = PotionUtils.getPotionColorFromEffectList(effect);

					double c0 = (double)(c >> 16 & 255) / 255.0D;
					double c1 = (double)(c >> 8 & 255) / 255.0D;
					double c2 = (double)(c >> 0 & 255) / 255.0D;

					worldIn.spawnParticle(EnumParticleTypes.SPELL_MOB, d0 + facing.getXOffset() * 0.5F, d1, d2 + facing.getZOffset() * 0.5F, c0,c1,c2);
				}
			}
		}
	}


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
	return new BlockStateContainer(this, ACTIVE, FACING);
}

//TODO Remove for 1.13.2
//Gets the state from the metadata value (will probably be gone for 1.13.2)
@Nonnull
@Override
public IBlockState getStateFromMeta(int meta)
{
	return this.getDefaultState().withProperty(FACING, getFacing(meta)).withProperty(ACTIVE, (meta & 4) != 0);
}

//Gets the metadata value from the given blockState
@Override
public int getMetaFromState(IBlockState state)
{
	int i = 0;
	i = i | getMetaForFacing(state.getValue(FACING));

	if ((state.getValue(ACTIVE)))
	{
		i |= 4;
	}

	return i;
}

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

@Nonnull
@Override
public IBlockState getStateForPlacement(@Nonnull World world, @Nonnull BlockPos pos, @Nonnull EnumFacing facing, float hitX, float hitY, float hitZ, int meta, @Nonnull EntityLivingBase placer, @Nonnull EnumHand hand)
{
	return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
}

@Override
public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
{
	if (!worldIn.isRemote)
	{
		IBlockState north = worldIn.getBlockState(pos.north());
		IBlockState south = worldIn.getBlockState(pos.south());
		IBlockState west = worldIn.getBlockState(pos.west());
		IBlockState east = worldIn.getBlockState(pos.east());
		EnumFacing face = state.getValue(FACING);

		if (face == EnumFacing.NORTH && north.isFullBlock() && !south.isFullBlock())
			face = EnumFacing.SOUTH;
		else if (face == EnumFacing.SOUTH && south.isFullBlock() && !north.isFullBlock())
			face = EnumFacing.NORTH;
		else if (face == EnumFacing.WEST && west.isFullBlock() && !east.isFullBlock())
			face = EnumFacing.EAST;
		else if (face == EnumFacing.EAST && east.isFullBlock() && !west.isFullBlock())
			face = EnumFacing.WEST;

		worldIn.setBlockState(pos, state.withProperty(FACING, face), 2);
	}
}
}
