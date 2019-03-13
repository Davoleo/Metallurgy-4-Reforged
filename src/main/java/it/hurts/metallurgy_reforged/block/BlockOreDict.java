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

import java.util.Arrays;
import java.util.List;

import javax.annotation.Nonnull;

import it.hurts.metallurgy_reforged.data.Drop;
import it.hurts.metallurgy_reforged.material.IOreDict;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.oredict.OreDictionary;

//Sub-class of the basic block (it implements the OreDict Interface)
public class BlockOreDict extends BlockBase implements IOreDict {

	//Internal state / variables -------------------------------------------------

	//OreDictionary entry value
	private String oreName;
	//Optional custom drops for blocks
	private List<Drop> customDrops;

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

	@Override
	public void getDrops(@Nonnull NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, @Nonnull IBlockState state, int fortune)
	{
		if (customDrops == null)
			drops.add(new ItemStack(this));
		else
		{
			for (Drop drop : customDrops) {
				if (Math.random() <= drop.getChance())
					drops.add(new ItemStack(drop.getItemStack().getItem(), drop.getRandomAmount()));
			}
		}
	}

    //Returns true if the block can be drop from explosions
	@Override
	public boolean canDropFromExplosion(Explosion explosionIn)
	{
		return this.getTranslationKey().contains("_ore");
	}
}
