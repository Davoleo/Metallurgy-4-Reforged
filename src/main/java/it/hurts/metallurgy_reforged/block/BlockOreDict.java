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
import it.hurts.metallurgy_reforged.material.IOreDict;
import it.hurts.metallurgy_reforged.material.MetalColors;
import it.hurts.metallurgy_reforged.particle.ParticleOre;
import it.hurts.metallurgy_reforged.util.BlockUtils;
import it.hurts.metallurgy_reforged.util.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Random;

//Sub-class of the basic block (it implements the OreDict Interface)
public class BlockOreDict extends Block implements IOreDict, IHasModel {

	//Internal state / variables -------------------------------------------------

	//OreDictionary entry value
	private String oreName;
	//Optional custom drops for blocks
	private List<Drop> customDrops;

	//Constructors ---------------------------------------------------------------

	//PickaxeEffectHandler-Mineable Block with oredict value, harvest level of 1, and blast resistance of 5
	public BlockOreDict(String name, String oreName, boolean addToList, CreativeTabs tab)
	{
		this(name, oreName, addToList, "p", 1, 5F, tab);
		setHardness(3F);
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
		OreDictionary.registerOre(oreName, this);
	}


	@Override
	public void getDrops(@Nonnull NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, @Nonnull IBlockState state, int fortune)
	{
		if (customDrops == null)
			drops.add(new ItemStack(this));
		else
		{
			for (Drop drop : customDrops)
			{
				if (Math.random() <= drop.getChance())
					drops.add(new ItemStack(drop.getItemStack().getItem(), drop.getRandomAmount()));
			}
		}
	}

	@Override
	public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand)
	{
		if (this.oreName.contains("ore"))
		{
			spawnParticles(worldIn, pos, rand);
		}
	}

	private void spawnParticles(World worldIn, BlockPos pos, Random random)
	{
		double d0 = 0.0625D;
		String metalName = this.getRegistryName().getPath().replace("_ore", "");
		MetalColors color = MetalColors.byName(metalName.toUpperCase());
		if (color != null)
		{
			float[] colorComponents = color.getRGBValues();

			for (int i = 0; i < 6; ++i)
			{

				double d1 = (float) pos.getX() + random.nextFloat();
				double d2 = (float) pos.getY() + random.nextFloat();
				double d3 = (float) pos.getZ() + random.nextFloat();

				if (i == 0 && !worldIn.getBlockState(pos.up()).isOpaqueCube())
					d2 = (double) pos.getY() + 0.0625D + 1.0D;

				if (i == 1 && !worldIn.getBlockState(pos.down()).isOpaqueCube())
					d2 = (double) pos.getY() - 0.0625D;

				if (i == 2 && !worldIn.getBlockState(pos.south()).isOpaqueCube())
					d3 = (double) pos.getZ() + 0.0625D + 1.0D;

				if (i == 3 && !worldIn.getBlockState(pos.north()).isOpaqueCube())
					d3 = (double) pos.getZ() - 0.0625D;

				if (i == 4 && !worldIn.getBlockState(pos.east()).isOpaqueCube())
					d1 = (double) pos.getX() + 0.0625D + 1.0D;

				if (i == 5 && !worldIn.getBlockState(pos.west()).isOpaqueCube())
					d1 = (double) pos.getX() - 0.0625D;

				if (d1 < (double) pos.getX() || d1 > (double) (pos.getX() + 1) || d2 < 0.0D || d2 > (double) (pos.getY() + 1) || d3 < (double) pos.getZ() || d3 > (double) (pos.getZ() + 1))
				{
					int harvestLevel = this.getHarvestLevel(this.getDefaultState());
					if (harvestLevel > 1)
						Minecraft.getMinecraft().effectRenderer.addEffect(new ParticleOre(worldIn, d1, d2, d3, 1.5F, colorComponents[0], colorComponents[1], colorComponents[2], harvestLevel - 2));
				}
			}
		}
	}

	//Returns true if the block can be drop from explosions
	@Override
	public boolean canDropFromExplosion(Explosion explosionIn)
	{
		return this.getTranslationKey().contains("_ore");
	}

	public Item createItemBlock()
	{
		return new ItemBlock(this).setRegistryName(Objects.requireNonNull(getRegistryName()));
	}

}
