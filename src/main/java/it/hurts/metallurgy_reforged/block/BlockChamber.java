package it.hurts.metallurgy_reforged.block;

import it.hurts.metallurgy_reforged.tileentity.TileEntityChamber;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.world.World;

public class BlockChamber extends BlockTileEntity<TileEntityChamber>{

	public BlockChamber(String name) {
		super(Material.IRON, name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Class<TileEntityChamber> getTileEntityClass() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TileEntityChamber createTileEntity(World world, IBlockState state) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
	
	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

}
