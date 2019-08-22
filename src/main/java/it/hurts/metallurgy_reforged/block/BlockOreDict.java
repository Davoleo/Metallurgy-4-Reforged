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
import java.util.Objects;

import javax.annotation.Nonnull;

import it.hurts.metallurgy_reforged.data.Drop;
import it.hurts.metallurgy_reforged.material.IOreDict;
import it.hurts.metallurgy_reforged.util.BlockUtils;
import it.hurts.metallurgy_reforged.util.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.world.Explosion;
import net.minecraftforge.common.ToolType;

//Sub-class of the basic block (it implements the OreDict Interface)
public class BlockOreDict extends Block implements IOreDict, IHasModel {

	//Internal state / variables -------------------------------------------------

	//OreDictionary entry value
	private String oreName;
	//Optional custom drops for blocks
	private List<Drop> customDrops;

	//Constructors ---------------------------------------------------------------

	//PickaxeEffectHandler-Mineable Block with oredict value, harvest level of 1, and blast resistance of 5
	public BlockOreDict(String name, String oreName, boolean addToList, ItemGroup tab) {
		this(name, oreName, addToList, "p", 1, 5F, tab);
		//setHardness(3F);
	}

    //OreDicted block with custom properties
	public BlockOreDict(String name, String oreName, boolean addToList, String toolClass, int harvestLevel, float blastResistance, ItemGroup tab) {
		//super(Material.ROCK);
		super(Block.Properties.create(Material.ROCK)
				.hardnessAndResistance(3f, blastResistance)
				.harvestTool(ToolType.PICKAXE)
				.harvestLevel(harvestLevel));
		BlockUtils.initBlock(this, name, tab, addToList);
		//setHardness(3f);
		//setResistance(blastResistance);
		//setHarvestLevel(toolClass, harvestLevel);
		//if (name.contains("block"))
			//setSoundType(SoundType.METAL);
		this.oreName = oreName;
	}

	//Custom Methods ------------------------------------------------------------

	public BlockOreDict setDrops(Drop... drops)
	{
		this.customDrops = Arrays.asList(drops);
		return this;
	}

	//Overridden Methods -------------------------------------------------------------

	@Nonnull
	@Override
	public String getCategory()
	{
		return "";
	}

	//registers the oreDict Value in the Ore Dictionary (Implemented from the Interface)
	@Override
	public void initOreDict()
	{
		//OreDictionary.registerOre(oreName, this);
	}

	
//	@Override
//	public void getDrops(@Nonnull NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, @Nonnull BlockState state, int fortune)
//	{
//		if (customDrops == null)
//			drops.add(new ItemStack(this));
//		else
//		{
//			for (Drop drop : customDrops) {
//				if (Math.random() <= drop.getChance())
//					drops.add(new ItemStack(drop.getItemStack().getItem(), drop.getRandomAmount()));
//			}
//		}
//	}

    //Returns true if the block can be drop from explosions
	@Override
	public boolean canDropFromExplosion(Explosion explosionIn)
	{
		return this.getTranslationKey().contains("_ore");
	}

	public Item createItemBlock(ItemGroup group) {
		return new BlockItem(this, new Item.Properties().group(group)).setRegistryName(Objects.requireNonNull(getRegistryName()));
	}
}
