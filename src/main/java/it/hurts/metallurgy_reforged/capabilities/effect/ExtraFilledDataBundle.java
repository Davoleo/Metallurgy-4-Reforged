/*==============================================================================
 = Class: ExtraFilledDataBundle
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.capabilities.effect;

import it.hurts.metallurgy_reforged.util.NBTUtils;
import net.minecraft.nbt.NBTTagCompound;

import java.util.function.Predicate;

public class ExtraFilledDataBundle extends ProgressiveDataBundle {

	private NBTTagCompound extra = new NBTTagCompound();
	private final Predicate<ExtraFilledDataBundle> isInProgress;

	public ExtraFilledDataBundle(String key, int maxSteps, int stepDelay, Predicate<ExtraFilledDataBundle> isInProgress)
	{
		super(key, maxSteps, stepDelay);
		this.isInProgress = isInProgress;
	}

	@Override
	public void toNBT(NBTTagCompound compound)
	{
		super.toNBT(compound);
		NBTUtils.injectCompound(prefixKey + "_extra_", compound, extra);
		//compound.setTag(prefixKey + "_extra", extra);
	}

	@Override
	public void fromNBT(NBTTagCompound compound)
	{
		super.fromNBT(compound);
		extra = NBTUtils.ejectCompound(prefixKey + "_extra_", compound);
		//extra = compound.getCompoundTag(prefixKey + "_extra");
	}

	@Override
	public boolean isEffectInProgress()
	{
		return isInProgress.test(this);
	}

	public NBTTagCompound getExtras()
	{
		return extra;
	}

	public void setExtras(NBTTagCompound compound)
	{
		this.extra = compound;
	}

	// ---------- Extra Data Methods ----------
	public String getExtraString(String key)
	{
		return extra.getString(key);
	}

	public int getExtraInt(String key)
	{
		return extra.getInteger(key);
	}

	public boolean getExtraBool(String key)
	{
		return extra.getBoolean(key);
	}

	public float getExtraFloat(String key)
	{
		return extra.getFloat(key);
	}

	public double getExtraDouble(String key)
	{
		return extra.getFloat(key);
	}

	public void setExtra(String key, String value)
	{
		extra.setString(key, value);
	}

	public void setExtra(String key, int value)
	{
		extra.setInteger(key, value);
	}

	public void setExtra(String key, float value)
	{
		extra.setFloat(key, value);
	}

	public void setExtra(String key, double value)
	{
		extra.setDouble(key, value);
	}

	public void setExtra(String key, boolean value)
	{
		extra.setBoolean(key, value);
	}

	public void clearExtras()
	{
		extra = new NBTTagCompound();
	}

	@Override
	public byte getType()
	{
		return 2;
	}

}
