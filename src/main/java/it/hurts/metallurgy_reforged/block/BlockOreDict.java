/*
 * -------------------------------------------------------------------------------------------------------
 * Class: BlockOreDict
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2020.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.block;

import it.hurts.metallurgy_reforged.util.BlockUtils;
import it.hurts.metallurgy_reforged.util.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

import javax.annotation.Nonnull;

public class BlockOreDict extends Block implements IHasModel {

	//Constructors ---------------------------------------------------------------

	//Pickaxe Block with oredict value, harvest level of 1, and blast resistance of 5
	public BlockOreDict(String name, String oreName, boolean addToList, CreativeTabs tab)
	{
		this(name, oreName, addToList, "p", 1, 5F, tab);
	}

	//OreDicted block with custom properties
	public BlockOreDict(String name, String oreName, boolean addToList, String toolClass, int harvestLevel, float blastResistance, CreativeTabs tab)
	{
		super(Material.ROCK);
		BlockUtils.initBlock(this, name, tab, addToList);
		setHardness(3f);
		setResistance(blastResistance);
		setHarvestLevel(toolClass, harvestLevel);
		if (name.contains("block"))
			setSoundType(SoundType.METAL);
	}

	//Overridden Methods -------------------------------------------------------------
	@Nonnull
	@Override
	public String getCategory()
	{
		return "";
	}

}
