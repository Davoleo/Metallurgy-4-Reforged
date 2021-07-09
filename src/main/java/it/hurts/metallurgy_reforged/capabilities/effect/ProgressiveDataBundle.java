/*==============================================================================
 = Class: ProgressiveDataBundle
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.capabilities.effect;

import it.hurts.metallurgy_reforged.network.PacketManager;
import it.hurts.metallurgy_reforged.network.client.PacketSyncEffectBundle;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.OverridingMethodsMustInvokeSuper;

public class ProgressiveDataBundle {

	public final int STEP_TICK_DELAY;
	protected final int maxSteps;

	protected String prefixKey;
	protected int currentStep = 0;
	protected boolean paused = false;

	protected long timestamp = -1;

	protected ItemStack effectStack = ItemStack.EMPTY;

	public ProgressiveDataBundle(String prefixKey, int maxSteps, int stepTickDelay)
	{
		this.prefixKey = prefixKey;
		this.maxSteps = maxSteps;
		this.STEP_TICK_DELAY = stepTickDelay;
	}

	/**
	 * If the player arg is not null sends a packet to the player client to update values on his side
	 *
	 * @param player player to sync with or null
	 */
	private void sync(@Nullable EntityPlayer player)
	{
		if (player != null && !player.world.isRemote && player instanceof EntityPlayerMP)
			PacketManager.network.sendTo(new PacketSyncEffectBundle(prefixKey, this), (EntityPlayerMP) player);
	}

	public String getPrefixKey()
	{
		return prefixKey;
	}

	public int getCurrentStep()
	{
		return currentStep;
	}

	public long getPrevStepTime()
	{
		return timestamp;
	}

	/**
	 * @return Whether the effect was set as paused
	 */
	public boolean isPaused()
	{
		return paused;
	}

	public ItemStack getEffectStack()
	{
		return effectStack;
	}

	/**
	 * Doesn't call {@link ProgressiveDataBundle#sync(EntityPlayer)} hence it should be called both on server and client sides
	 */
	public void updateTimeStamp(@Nonnull EntityPlayer player)
	{
		timestamp = player.world.getTotalWorldTime();
	}

	/**
	 * @param player the player this change needs to be synced with or null in case we're already on the client
	 */
	public void setCurrentStep(int currentStep, @Nullable EntityPlayerMP player)
	{
		this.currentStep = currentStep;
		sync(player);
	}

	/**
	 * @param player the player this change needs to be synced with or null in case we're already on the client
	 */
	public void setEffectStack(ItemStack effectStack, @Nullable EntityPlayer player)
	{
		this.effectStack = effectStack;
		sync(player);
	}

	/**
	 * Sets whether this effect should be paused or not
	 *
	 * @param paused true or falls to set paused or !paused
	 * @param player the player this change needs to be synced with or null in case we're already on the client
	 */
	public void setPaused(boolean paused, @Nullable EntityPlayer player)
	{
		this.paused = paused;
		sync(player);
	}

	public int getMaxSteps()
	{
		return maxSteps;
	}

	/**
	 * @return whether this effect should be considered "in progress"
	 */
	public boolean isEffectInProgress()
	{
		return currentStep > 0 && !paused;
	}

	/**
	 * Increments {@code currentStep} if it's below {@code maxSteps};<br>
	 * Updates the {@code timestamp}
	 * If this method is called when {@code currentStep} >= than {@code maxSteps} the effect is reset
	 *
	 * @param player the player these changes needs to be synced with or null in case we're already on the client
	 */
	public void incrementStep(@Nullable EntityPlayer player)
	{
		if (player != null && currentStep == 0)
			updateTimeStamp(player);

		if (currentStep < maxSteps)
		{
			currentStep++;
			sync(player);
		}
		else
			resetProgress(player);

		//Debug Log
		//LOGGER.info(getPrefixKey() + ": Current Step " + getCurrentStep() + " Paused: " + isPaused());
	}

	/**
	 * Resets the effect internal state so that it can start from the beginning again later
	 *
	 * @param player the player this change needs to be synced with or null in case we're already on the client
	 */
	public void resetProgress(EntityPlayer player)
	{
		timestamp = -1;
		currentStep = 0;
		sync(player);
	}

	/**
	 * TODO Maybe introduce an ENUM or CONSTANTS for this
	 *
	 * @return a type identifier that differs between the base bundle class and the children classes
	 */
	public byte getType()
	{
		return 0;
	}

	/**
	 * Serializes data into the NBT (on capability data save on the server)
	 */
	@OverridingMethodsMustInvokeSuper
	public void toNBT(NBTTagCompound compound)
	{
		compound.setInteger(prefixKey + "_current_step", currentStep);
		compound.setLong(prefixKey + "_timestamp", timestamp);
		compound.setBoolean(prefixKey + "_paused", paused);
	}

	/**
	 * Deserializes data from the NBT (on capability data load on the server)
	 */
	@OverridingMethodsMustInvokeSuper
	public void fromNBT(NBTTagCompound compound)
	{
		currentStep = compound.getInteger(prefixKey + "_current_step");
		timestamp = compound.getLong(prefixKey + "_timestamp");
		paused = compound.getBoolean(prefixKey + "_paused");
	}

}
