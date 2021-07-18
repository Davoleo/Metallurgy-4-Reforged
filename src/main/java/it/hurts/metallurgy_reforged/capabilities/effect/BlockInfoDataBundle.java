/*==============================================================================
 = Class: BlockInfoDataBundle
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.capabilities.effect;

import jline.internal.Nullable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class BlockInfoDataBundle extends ExtraFilledDataBundle {

	private BlockPos pos;

	/**
	 * !Not synced with the client!
	 **/
	private IBlockState state;

	public BlockInfoDataBundle(String key, int maxSteps, int stepDelay)
	{
		super(key, maxSteps, stepDelay, null);
		this.pos = null;
		this.state = null;
	}

	@Override
	public boolean isEffectInProgress()
	{
		return currentStep > 0 && !paused && pos != null;
	}

	@Override
	public void resetProgress(EntityPlayer player)
	{
		super.resetProgress(player);
		pos = null;
	}

	@Nullable
	public IBlockState getState()
	{
		return state;
	}

	@Nullable
	public BlockPos getPos()
	{
		return pos;
	}

	public void setBlockInfo(BlockPos pos, IBlockState state)
	{
		this.pos = pos;
		if (FMLCommonHandler.instance().getEffectiveSide().isServer())
			this.state = state;
	}

	@Override
	public byte getType()
	{
		return 1;
	}

	@Override
	public void toNBT(NBTTagCompound compound)
	{
		super.toNBT(compound);
		if (pos != null)
			compound.setLong(prefixKey + "_pos", pos.toLong());
	}

	@Override
	public void fromNBT(NBTTagCompound compound)
	{
		super.fromNBT(compound);

		if (compound.hasKey(prefixKey + "_pos"))
			pos = BlockPos.fromLong(compound.getLong(prefixKey + "_pos"));
		else
			pos = null;
	}

}
