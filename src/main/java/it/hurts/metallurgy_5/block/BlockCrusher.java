package it.hurts.metallurgy_5.block;

import it.hurts.metallurgy_5.Metallurgy_5;
import it.hurts.metallurgy_5.gui.GuiHandler;
import it.hurts.metallurgy_5.tileentity.TileEntityCrusher;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

/*************************************************
 * Author: Davoleo
 * Date: 01/09/2018
 * Hour: 21.41
 * Project: Metallurgy_5
 * Copyright - © - Davoleo - 2018
 **************************************************/

//VOGLIO MORIRE EDITION

public class BlockCrusher extends BlockTileEntity<TileEntityCrusher> {

    public static final PropertyDirection FACING = BlockHorizontal.FACING;
    public static final PropertyBool BURNING = PropertyBool.create("burning");

    public BlockCrusher(String name){
        super(Material.IRON, name);
        setSoundType(SoundType.METAL);
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(BURNING, false));
    }

//    TODO : Sostituiti con @breakBlock
//    @Override
//    public Item getItemDropped(IBlockState state, Random rand, int fortune)
//    {
//        return null;
//        //return super.getItemDropped(state, rand, fortune);
//    }
//
//    @Override
//    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player)
//    {
//        return ItemStack.EMPTY;
//        //return super.getPickBlock(state, target, world, pos, player);
//    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        if(!world.isRemote)
        {
            player.openGui(Metallurgy_5.instance, GuiHandler.CRUSHER, world, pos.getX(), pos.getY(), pos.getZ());
        }

        return true;
    }

    //sistema la rotazione del blocco appena prima di essere piazzato
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
                        else if (face == EnumFacing.EAST && east.isFullBlock() && ! west.isFullBlock())
                            face = EnumFacing.WEST;

            worldIn.setBlockState(pos, state.withProperty(FACING, face), 2);
        }
    }

    public static void setState(boolean active, World worldIn, BlockPos pos)
    {
        IBlockState state = worldIn.getBlockState(pos);
        TileEntity tileEntity = worldIn.getTileEntity(pos);

        if(active) worldIn.setBlockState(pos, ModBlocks.crusher.getDefaultState().withProperty(FACING, state.getValue(FACING)).withProperty(BURNING, true), 3);
        else worldIn.setBlockState(pos, ModBlocks.crusher.getDefaultState().withProperty(FACING, state.getValue(FACING)).withProperty(BURNING, false), 3);

        if(tileEntity != null)
        {
            tileEntity.validate();
            worldIn.setTileEntity(pos, tileEntity);
        }
    }


    public void breakBlock(World world, BlockPos pos, IBlockState state)
    {
        TileEntityCrusher tile = getTileEntity(world, pos);
        IItemHandler itemHandler = tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.NORTH);
        ItemStack stack0 = itemHandler.getStackInSlot(0);
        ItemStack stack1 = itemHandler.getStackInSlot(1);
        ItemStack stack2 = itemHandler.getStackInSlot(2);
        if(!stack0.isEmpty())
        {
            EntityItem item = new EntityItem(world, pos.getX(), pos.getY(), pos.getZ());
            world.spawnEntity(item);
        }
        if(!stack1.isEmpty())
        {
            EntityItem item = new EntityItem(world, pos.getX(), pos.getY(), pos.getZ());
            world.spawnEntity(item);
        }
        if(!stack2.isEmpty())
        {
            EntityItem item = new EntityItem(world, pos.getX(), pos.getY(), pos.getZ());
            world.spawnEntity(item);
        }
        super.breakBlock(world, pos, state);
    }

    @Override
    public boolean hasTileEntity(IBlockState state)
    {
        return true;
    }

    @Override
    public Class<TileEntityCrusher> getTileEntityClass()
    {
        return TileEntityCrusher.class;
    }

    @Override
    public TileEntityCrusher createTileEntity(World world, IBlockState state)
    {
        return new TileEntityCrusher();
    }

    @Override
    public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand)
    {
        return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
    {
        worldIn.setBlockState(pos, this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);
    }
    //TODO : Deprecated methods
    @Override
    public EnumBlockRenderType getRenderType(IBlockState state)
    {
        return EnumBlockRenderType.MODEL;
    }


    @Override
    public IBlockState withRotation(IBlockState state, Rotation rot)
    {
        return state.withProperty(FACING, rot.rotate(state.getValue(FACING)));
    }

    @Override
    public IBlockState withMirror(IBlockState state, Mirror mirrorIn)
    {
        return state.withRotation(mirrorIn.toRotation(state.getValue(FACING)));
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {BURNING, FACING});
    }

    //TODO : Da sostituire con un blockstate per ogni stato del blocco
    @Override
    public IBlockState getStateFromMeta(int meta)
        {
        EnumFacing facing = EnumFacing.getFront(meta);
        if(facing.getAxis() == EnumFacing.Axis.Y) facing = EnumFacing.NORTH;
        return this.getDefaultState().withProperty(FACING, facing);
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return ((EnumFacing)state.getValue(FACING)).getIndex();
    }

}
