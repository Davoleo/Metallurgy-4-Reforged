package it.hurts.metallurgy_5.block;

import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.oredict.OreDictionary;

public class BlockOre extends BlockBase implements IBlockOreDict {

	private String oreName;
	
	public BlockOre(String name, String oreName) {
		super(Material.ROCK, name);
	
		setHardness(3f);
		setResistance(5f);

		this.oreName = oreName;
	}
	
	@Override
	public BlockOre setCreativeTab(CreativeTabs tab) {
		super.setCreativeTab(tab);
		return this;
	}

	@Override
	public void initOreDict()
	{
		OreDictionary.registerOre(oreName, this);
	}
	
}
