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

import it.hurts.metallurgy_reforged.data.Drop;
import it.hurts.metallurgy_reforged.item.ModItems;
import it.hurts.metallurgy_reforged.material.IOreDict;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

//Sub-class of the basic block (it implements the OreDict Interface)
public class BlockOreDict extends BlockBase implements IOreDict {

	//Internal state / variables -------------------------------------------------

	//OreDictionary entry value
	private String oreName;
	//Optional custom drops for blocks
	private List<Drop> customDrops;

	private int dropsNumber = 0;

	//Constructors ---------------------------------------------------------------

	//PickaxeEffectHandler-Mineable Block with oredict value, harvest level of 1, and blast resistance of 5
	public BlockOreDict(String name, String oreName) {
		this(name, oreName, "p", 1, 5F);
		setHardness(3F);
	}

    //OreDicted block with custom properties
	public BlockOreDict(String name, String oreName, String toolClass, int harvestLevel, float blastResistance){
		super(Material.ROCK, name);
		this.oreName = oreName;
		setHardness(3f);
		setResistance(blastResistance);
		this.setHarvestLevel(toolClass, harvestLevel);
	}

	//Custom Methods ------------------------------------------------------------

	public BlockOreDict setDrops(Drop... drops)
	{
		this.customDrops = Arrays.asList(drops);
		//this.dropsNumber = customDrops.size();
		return this;
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

	// FIXME: 10/03/2019 Funziona a cazzo
	//Returns the item that is dropped by the block
	//Check if the drop is customized, otherwise return the same block
	@Nonnull
    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		if (customDrops == null)
			return Item.getItemFromBlock(this);
		else
		{
			if (dropsNumber == customDrops.size())
			{
				while (dropsNumber > 0) {
					dropsNumber--;
					//Temporary Printout
					System.out.println(dropsNumber + " asakljdaksdjakdjsw");
					getItemDropped(state, rand, fortune);
				}
			}
			else
			{
				if (Math.random()  < customDrops.get(dropsNumber).getChance())
				{
					//Temporary Printout
					System.out.println("sono entrato " + customDrops.get(dropsNumber).getItemStack().getDisplayName());
					return customDrops.get(dropsNumber).getItemStack().getItem();
				}
			}
		}
		//Temporary Variable 
		return new ItemStack(ModItems.dustThermite).getItem();
    }

	@Override
	public void breakBlock(@Nonnull World worldIn, @Nonnull BlockPos pos, @Nonnull IBlockState state)
	{
		if (customDrops != null && dropsNumber == 0)
			dropsNumber = customDrops.size();
	}

	//Overrides the quantity of the drop
    @Override
    public int quantityDropped(Random random)
    {
    	if(customDrops == null)
    		return 1;
    	else
    		return random.nextInt(customDrops.get(dropsNumber > 0 ? dropsNumber - 1 : dropsNumber).getAmount()) + 1;

    }

    //Returns true if the block can be drop from explosions
	@Override
	public boolean canDropFromExplosion(Explosion explosionIn)
	{
		return this.getTranslationKey().contains("_ore");
	}
}
