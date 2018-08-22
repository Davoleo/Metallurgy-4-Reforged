package it.hurts.metallurgy_5.block;

import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BlockOre extends BlockBase {
	
	public BlockOre(String name, String oreName) {
		super(Material.ROCK, name);
	
		setHardness(3f);
		setResistance(5f);
	}
	
	@Override
	public BlockOre setCreativeTab(CreativeTabs tab) {
		super.setCreativeTab(tab);
		return this;
	}
	
}
