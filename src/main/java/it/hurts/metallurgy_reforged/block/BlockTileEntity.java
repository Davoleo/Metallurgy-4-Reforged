package it.hurts.metallurgy_reforged.block;

import it.hurts.metallurgy_reforged.util.MetallurgyTabs;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/*************************************************
 * Author: Davoleo
 * Date: 01/09/2018
 * Hour: 21.28
 * Project: Metallurgy
 * Copyright - © - Davoleo - 2018
 **************************************************/

//An abstract base class for TileBlocks | The generic parameter links the TileBlock Class with the TileEntity Class
public abstract class BlockTileEntity<TE extends TileEntity> extends BlockBase {

    //Constructor ----------------------------------------------------------------

    public BlockTileEntity(Material material, String name)
    {
        super(material, name);
        setCreativeTab(MetallurgyTabs.tabSpecial);
        setSoundType(SoundType.METAL);
        setHarvestLevel("pickaxe", 1);
        setHardness(6F);
        setResistance(8F);
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
        return (TE)world.getTileEntity(pos);
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
