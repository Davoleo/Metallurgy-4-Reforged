/*
 * -------------------------------------------------------------------------------------------------------
 * Class: BlockOreDict
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2019.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.block;

import it.hurts.metallurgy_reforged.material.IOreDict;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.world.Explosion;
import net.minecraftforge.oredict.OreDictionary;

import javax.annotation.Nonnull;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//Sub-class of the basic block (it implements the OreDict Interface)
public class BlockOreDict extends BlockBase implements IOreDict {

	//Internal state / variables -------------------------------------------------

	//OreDictionary entry value
	private String oreName;
	//Optional custom drops for blocks
	private Item customDrop;
	private List<Item> customDrops = new ArrayList<Item>();
	
	private int dropFlag = 0;

	//Constructors ---------------------------------------------------------------

	//Pickaxe-Mineable Block with oredict value, harvest level of 1, and blast resistance of 5
	public BlockOreDict(String name, String oreName) {
		this(name, oreName, null,"p", 1, 5F);
		setHardness(3F);
	}

	//OreDicted block with custom properties
	public BlockOreDict(String name, String oreName, String toolClass, int harvestLevel, float blastResistance){
		this(name, oreName, null, toolClass, harvestLevel, blastResistance);
    }

    //OreDicted block with custom properties and custom drops
    //TODO Make them more efficient - @Davoleo
	public BlockOreDict(String name, String oreName, Item drop, String toolClass, int harvestLevel, float blastResistance){
		super(Material.ROCK, name);
		this.oreName = oreName;
		this.customDrop = drop;
		setHardness(3f);
		setResistance(blastResistance);
		this.setHarvestLevel(toolClass, harvestLevel);
	}
	
	public BlockOreDict(String name, String oreName, String toolClass, int harvestLevel, float blastResistance, Item...drops){
		super(Material.ROCK, name);
		this.oreName = oreName;
		setHardness(3f);
		setResistance(blastResistance);
		this.setHarvestLevel(toolClass, harvestLevel);
		
		for(Item i : drops) {
			System.out.println("QUi entro " + i.getRegistryName());
			customDrops.add(i);
		}
		
	}
	//Overridden Methods -------------------------------------------------------------

	//Overrides the creativeTab
	@Nonnull
	@Override
	public BlockOreDict setCreativeTab(@Nonnull CreativeTabs tab) {
		super.setCreativeTab(tab);
		return this;
	}

	//registers the oreDict Value in the Ore Dictionary (Implemented from the Interface)
	@Override
	public void initOreDict()
	{
		OreDictionary.registerOre(oreName, this);
	}

	//Returns the item that is dropped by the block
	//Check if the drop is an item, otherwise return the same block
	@Nonnull
    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune){
		Item item;
		if(customDrop != null)
        	return customDrop;
        else
//        	Droppa solo il primo
        	if(customDrops.size() > 0 && customDrops.get(0) != null) {
        		if(dropFlag == 0) {
        			item = customDrops.get(dropFlag);
        			dropFlag = 1;	
        			getItemDropped(state, rand, fortune);
        			System.out.println("Dovrei essere entrato " + item.getRegistryName());
        			return item;
        		}else
        			if(dropFlag == 1) {
        				Item item1 = customDrops.get(dropFlag);
        				System.out.println("Sono entratooo " + item1.getRegistryName());
            			dropFlag = 0;
            			return item1;
        			}
        			
        	}
		return Item.getItemFromBlock(this);
    }

    //Overrides the quantity of the drop
    @Override
    public int quantityDropped(Random random)
    {
    	if(customDrop != null)
    		return 1 + random.nextInt(4);
    	else
    		return 1;
    }

    //Returns true if the block can be drop from explosions
	@Override
	public boolean canDropFromExplosion(Explosion explosionIn)
	{
		return this.getTranslationKey().contains("_ore");
	}
}
