/*==============================================================================
 = Class: PacketSyncEffectBundle
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.network.client;

import io.netty.buffer.ByteBuf;
import it.hurts.metallurgy_reforged.capabilities.effect.BlockInfoDataBundle;
import it.hurts.metallurgy_reforged.capabilities.effect.EffectDataProvider;
import it.hurts.metallurgy_reforged.capabilities.effect.ExtraFilledDataBundle;
import it.hurts.metallurgy_reforged.capabilities.effect.ProgressiveDataBundle;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

public class PacketSyncEffectBundle implements IMessage {

	private byte bundleType;

	private String prefixKey;
	private int step;
	private long timestamp;
	private boolean paused;

	private ItemStack effectStack;

	@Nullable
	private NBTTagCompound extras;

	@Nullable
	private BlockPos blockPos;

	@SuppressWarnings("unused")
	public PacketSyncEffectBundle()
	{
		//Mandatory Empty Constructor
	}

	public PacketSyncEffectBundle(String prefixKey, ProgressiveDataBundle bundle)
	{
		this.bundleType = bundle.getType();

		this.prefixKey = prefixKey;
		this.step = bundle.getCurrentStep();
		this.timestamp = bundle.getPrevStepTime();
		this.paused = bundle.isPaused();

		this.effectStack = bundle.getEffectStack();

		if (bundle.getType() == 1)
			this.blockPos = ((BlockInfoDataBundle) bundle).getPos();
		else if (bundle.getType() == 2)
			this.extras = ((ExtraFilledDataBundle) bundle).getExtras();
	}

	@Override
	public void fromBytes(ByteBuf buf)
	{
		prefixKey = ByteBufUtils.readUTF8String(buf);

		step = buf.readInt();
		timestamp = buf.readLong();
		paused = buf.readBoolean();

		effectStack = ByteBufUtils.readItemStack(buf);

		if (bundleType == 1)
			blockPos = BlockPos.fromLong(buf.readLong());

		if (bundleType == 2)
			extras = ByteBufUtils.readTag(buf);
	}

	@Override
	public void toBytes(ByteBuf buf)
	{
		ByteBufUtils.writeUTF8String(buf, prefixKey);

		buf.writeInt(step);
		buf.writeLong(timestamp);
		buf.writeBoolean(paused);

		ByteBufUtils.writeItemStack(buf, effectStack);

		if (blockPos != null && bundleType == 1)
			buf.writeLong(blockPos.toLong());

		if (extras != null && bundleType == 2)
			ByteBufUtils.writeTag(buf, extras);
	}

	public static class Handler implements IMessageHandler<PacketSyncEffectBundle, IMessage> {

		@SideOnly(Side.CLIENT)
		@Override
		public IMessage onMessage(PacketSyncEffectBundle message, MessageContext ctx)
		{
			Minecraft minecraft = Minecraft.getMinecraft();
			minecraft.addScheduledTask(() -> {
				ProgressiveDataBundle bundle = minecraft.player.getCapability(EffectDataProvider.PLAYER_EFFECT_DATA_CAPABILITY, null).effectBundles.get(message.prefixKey);
				//Passing null as the player since I don't need things to synchronize on the client, we're already on the client here
				if (bundle.getCurrentStep() != message.step)
					bundle.setCurrentStep(message.step, null);

				if (bundle.getPrevStepTime() == -1)
					bundle.updateTimeStamp(minecraft.player);

				if (bundle.getEffectStack().isEmpty())
					bundle.setEffectStack(message.effectStack, null);

				if (bundle.isPaused() != message.paused)
					bundle.setPaused(message.paused, null);

				if (message.bundleType == 1 && message.blockPos != ((BlockInfoDataBundle) bundle).getPos())
					((BlockInfoDataBundle) bundle).setBlockInfo(message.blockPos, null);

				if (message.bundleType == 2 && message.extras != ((ExtraFilledDataBundle) bundle).getExtras())
					((ExtraFilledDataBundle) bundle).setExtras(message.extras);
			});

			return null;
		}

	}

}
