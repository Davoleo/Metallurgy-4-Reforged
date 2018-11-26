package it.hurts.metallurgy_reforged.block;

import it.hurts.metallurgy_reforged.material.IOreDict;
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

public class BlockOreDict extends BlockBase implements IOreDict {

	private String oreName;
	private Item customDrop;
	
	public BlockOreDict(String name, String oreName) {
		this(name, oreName, null,"p", 1);
		setHardness(3f);
		setResistance(5f);
	}

//	toolClass is the classification of tool (Like showel, axe, pickaxe, hoe)
	public BlockOreDict(String name, String oreName, String toolClass, int harvestLevel){
		this(name, oreName, null, toolClass, harvestLevel);
    }

	public BlockOreDict(String name, String oreName, Item drop, String toolClass, int harvestLevel){
		super(Material.ROCK, name);
		this.oreName = oreName;
		this.customDrop = drop;
		setHardness(3f);
		setResistance(5f);
		this.setHarvestLevel(toolClass, harvestLevel);
	}


	@Nonnull
	@Override
	public BlockOreDict setCreativeTab(@Nonnull CreativeTabs tab) {
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
    public Item getItemDropped(IBlockState state, Random rand, int fortune){
		
//		Check if the drop is an item, else return the same block
        if(customDrop != null)
        	return customDrop;
        else
        	return Item.getItemFromBlock(this);
    }

    @Override
    public int quantityDropped(Random random)
    {
    	if(customDrop != null)
    		return 1 + random.nextInt(4);
    	else
    		return 1;
    }
}
