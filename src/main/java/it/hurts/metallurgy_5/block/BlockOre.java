package it.hurts.metallurgy_5.block;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.oredict.OreDictionary;

import javax.annotation.Nonnull;
import java.util.Random;

/***************************
*
* Author : ItHurtsLikeHell
* Project: Metallurgy-5
* Date   : 28 ago 2018
* Time   : 18:24:07
*
***************************/

public class BlockOre extends BlockBase implements IBlockOreDict {

	private String oreName;
	private Item drop;
	
	public BlockOre(String name, String oreName) {
		super(Material.ROCK, name);
	
		setHardness(3f);
		setResistance(5f);

		this.oreName = oreName;
	}

	public BlockOre(String name, String oreName, Item drop)
    {
        this(name, oreName);
        this.drop = drop;
    }

    @Nonnull
	@Override
	public BlockOre setCreativeTab(@Nonnull CreativeTabs tab) {
		super.setCreativeTab(tab);
		return this;
	}

	@Override
	public void initOreDict()
	{
		OreDictionary.registerOre(oreName, this);
	}

	@Nonnull
    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return drop;
    }

    @Override
    public int quantityDropped(Random random)
    {
        return 1 + random.nextInt(3);
    }
}
