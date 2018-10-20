package it.hurts.metallurgy_5.block;

import it.hurts.metallurgy_5.Metallurgy_5;
import it.hurts.metallurgy_5.gui.GuiHandler;
import it.hurts.metallurgy_5.tileentity.TileEntityAlloyer;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.SoundType;
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
import net.minecraft.world.World;

import java.util.Random;

/***************************
 *
 * Author : ItHurtsLikeHell
 * Project: Metallurgy-5
 * Date   : 22 set 2018
 * Time   : 11:04:34
 *
 * Reworked by Davoleo
 ***************************/
public class BlockAlloyer extends BlockTileEntity<TileEntityAlloyer>{

    //Blockstates initialization
	public static final PropertyDirection FACING = BlockHorizontal.FACING;
    public static final PropertyBool BURNING = PropertyBool.create("burning");

    private static boolean keepInventory;

    public BlockAlloyer(String name){
        super(Material.IRON, name);
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(BURNING, false));
        setSoundType(SoundType.METAL);
        setHarvestLevel("pickaxe", 1);
        setHardness(6F);
        setResistance(8F);
    }
	
    //Overrides the dropped item
    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Item.getItemFromBlock(ModBlocks.alloyer);
    }
    
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player){
        return new ItemStack(ModBlocks.alloyer);
    }
    
    //When you right-click the block
    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ){
        if(!world.isRemote){
            
        	TileEntity te = world.getTileEntity(pos);

            if(te instanceof TileEntityAlloyer)
                player.openGui(Metallurgy_5.instance, GuiHandler.ALLOYER, world, pos.getX(), pos.getY(), pos.getZ());
        } else
            return true;

        return true;
    }
    
    //When the block is placed in the world
    @Override
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state){
        if (!worldIn.isRemote){
            IBlockState north = worldIn.getBlockState(pos.north());
            IBlockState south = worldIn.getBlockState(pos.south());
            IBlockState west = worldIn.getBlockState(pos.west());
            IBlockState east = worldIn.getBlockState(pos.east());
            EnumFacing face = (EnumFacing)state.getValue(FACING);

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
    
    //Sets the state of the block
    public static void setState(boolean active, World worldIn, BlockPos pos){
        IBlockState state = worldIn.getBlockState(pos);
        TileEntity tileEntity = worldIn.getTileEntity(pos);
        keepInventory = true;

        if(active)
            worldIn.setBlockState(pos, ModBlocks.alloyer.getDefaultState().withProperty(FACING, state.getValue(FACING)).withProperty(BURNING, true), 3);
        else
            worldIn.setBlockState(pos, ModBlocks.alloyer.getDefaultState().withProperty(FACING, state.getValue(FACING)).withProperty(BURNING, false), 3);

        keepInventory = false;

        if(tileEntity != null){
        	tileEntity.validate();
            worldIn.setTileEntity(pos, tileEntity);
        }
    }
    
    public void breakBlock(World world, BlockPos pos, IBlockState state){
        if(!keepInventory){
            TileEntity te = world.getTileEntity(pos);

            if (te instanceof TileEntityAlloyer){
                InventoryHelper.dropInventoryItems(world, pos, (TileEntityAlloyer)te);
            }
        }
    }
    
    @Override
    public boolean hasTileEntity(IBlockState state){
        return true;
    }
    
    @Override
    public Class<TileEntityAlloyer> getTileEntityClass(){
        return TileEntityAlloyer.class;
    }
    
    @Override
    public TileEntityAlloyer createTileEntity(World world, IBlockState state){
        return new TileEntityAlloyer();
    }
    
    @Override
    public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand){
        return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
    }
    
    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack){
        worldIn.setBlockState(pos, this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);

        if (stack.hasDisplayName()){
            TileEntity te = worldIn.getTileEntity(pos);

            if(te instanceof TileEntityAlloyer){
                ((TileEntityAlloyer)te).setCustomName(stack.getDisplayName());
            }
        }
    }
    
    @Override
    public EnumBlockRenderType getRenderType(IBlockState state){
        return EnumBlockRenderType.MODEL;
    }
    
    @Override
    public IBlockState withRotation(IBlockState state, Rotation rot){
        return state.withProperty(FACING, rot.rotate((EnumFacing) state.getValue(FACING)));
    }
    
    @Override
    public IBlockState withMirror(IBlockState state, Mirror mirrorIn){
        return state.withRotation(mirrorIn.toRotation((EnumFacing) state.getValue(FACING)));
    }
    
    @Override
    protected BlockStateContainer createBlockState(){
        return new BlockStateContainer(this, BURNING, FACING);
    }
    
    @Override
    public IBlockState getStateFromMeta(int meta){
        EnumFacing facing = EnumFacing.getFront(meta);

        if(facing.getAxis() == EnumFacing.Axis.Y)
            facing = EnumFacing.NORTH;

        return this.getDefaultState().withProperty(FACING, facing);
    }
    
    @Override
    public int getMetaFromState(IBlockState state){
        return ((EnumFacing)state.getValue(FACING)).getIndex();
    }

    @Override
    public BlockAlloyer setCreativeTab(CreativeTabs tab) {
        super.setCreativeTab(tab);
        return this;
    }
    
}
