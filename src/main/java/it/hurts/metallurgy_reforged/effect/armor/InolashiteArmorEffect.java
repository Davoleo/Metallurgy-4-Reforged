/*==============================================================================
 = Class: InolashiteArmorEffect
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.effect.armor;

import it.hurts.metallurgy_reforged.capabilities.effect.EffectDataProvider;
import it.hurts.metallurgy_reforged.capabilities.effect.ExtraFilledDataBundle;
import it.hurts.metallurgy_reforged.capabilities.effect.PlayerEffectData;
import it.hurts.metallurgy_reforged.effect.BaseMetallurgyEffect;
import it.hurts.metallurgy_reforged.effect.EnumEffectCategory;
import it.hurts.metallurgy_reforged.effect.IProgressiveEffect;
import it.hurts.metallurgy_reforged.item.armor.ItemArmorBase;
import it.hurts.metallurgy_reforged.material.ModMetals;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.FoodStats;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import javax.annotation.Nonnull;
import java.util.Queue;

public class InolashiteArmorEffect extends BaseMetallurgyEffect implements IProgressiveEffect {

	public InolashiteArmorEffect()
	{
		super(ModMetals.INOLASHITE);
	}

	@Nonnull
	@Override
	public EnumEffectCategory getCategory()
	{
		return EnumEffectCategory.ARMOR;
	}

	private void addScrollQueue(WarpData newItem, Queue<WarpData> queue, int backtrackExtension)
	{
		while (!queue.isEmpty() && backtrackExtension * 4 <= queue.size())
			queue.poll();

		queue.add(newItem);
	}

	@SubscribeEvent
	public void recallLoop(TickEvent.PlayerTickEvent event)
	{

		// In seconds (should be multiplied by 4 to get the actual number of warpDatas)
		int backTrackExtension = getLevel(event.player) * 2;

		if (event.phase != TickEvent.Phase.START || backTrackExtension == 0)
			return;

		EntityPlayer player = event.player;

		if (player.ticksExisted % 5 == 0)
		{
			Queue<WarpData> warpQueue = player.getCapability(EffectDataProvider.PLAYER_EFFECT_DATA_CAPABILITY, null).inolashiteWarpData;

			WarpData data = new WarpData(player.getPosition(), player.getHealth(), player.getFoodStats());
			addScrollQueue(data, warpQueue, backTrackExtension);
			//Metallurgy.logger.info(warpQueue.toString());
			//Debug Visual Effect Representation
			//for (WarpData warpData : warpQueue)
			//    spawnParticle(player.world, warpData.position.getX(), warpData.position.getY(), warpData.position.getZ(), 8, false, 4);
		}

		//Don't spawn particles if the effect is on cooldown
		float cooldown = player.getCooldownTracker().getCooldown(getArmorRepr(player).getItem(), 0);
		if (cooldown > 0)
			return;

		if (player.isSneaking() && player.ticksExisted % 2 == 0 && !player.world.isRemote)
		{
			for (double i = 0; i < Math.PI * 2; i += (Math.PI / 10D))
				spawnParticle(player.world, player.posX + Math.sin(i), player.posY + 2, player.posZ + Math.cos(i), 1, true, 4);
		}
	}

	@SubscribeEvent
	public void triggerWarp(LivingEvent.LivingJumpEvent event)
	{

		//Trigger Warp effect and set data
		if (event.getEntityLiving() instanceof EntityPlayer && canBeApplied(event.getEntityLiving()) && event.getEntityLiving().isSneaking())
		{
			EntityPlayer player = ((EntityPlayer) event.getEntityLiving());

			//If armor is on cooldown don't trigger the effect
			if (player.getCooldownTracker().getCooldown(getArmorRepr(event.getEntityLiving()).getItem(), 0) > 0)
				return;

			//Set the first
			PlayerEffectData capability = getEffectCapability(player);
			if (!capability.inolashiteWarpData.isEmpty())
			{
				capability.inolashiteArmorBundle.setExtras(capability.inolashiteWarpData.element().serializeNBT());
				capability.inolashiteArmorBundle.incrementStep(player);
			}

			//Set armor on cooldown after jumping
			//int cooldown = (getLevel(player) > 0.5F ? 32 : 16) * 20;
			int level = getLevel(player);
			int cooldown = (int) (Math.pow(2, 7 - level) * 20);
			for (ItemArmorBase armor : metal.getArmorSet())
				player.getCooldownTracker().setCooldown(armor, cooldown);
		}
	}

	@Override
	public void onStep(World world, EntityPlayer entity, ItemStack effectStack, int maxSteps, int step)
	{
		WarpData data = new WarpData();
		data.deserializeNBT(((ExtraFilledDataBundle) getBundle(entity, metal, getCategory())).getExtras());

		//Restore original position health and food stats
		entity.setPosition(data.position.getX(), data.position.getY() + 0.1, data.position.getZ());
		entity.setHealth(data.health);
		entity.getFoodStats().addStats(data.hungerStats.getFoodLevel(), data.hungerStats.getSaturationLevel());

		for (int i = 0; i < 25; i++)
			spawnParticle(entity, 1, true, 4);
	}

	public static class WarpData implements INBTSerializable<NBTTagCompound> {

		private BlockPos position;
		private float health;
		private FoodStats hungerStats;

		public WarpData()
		{
		}

		public WarpData(BlockPos position, float health, FoodStats hungerStats)
		{
			this.position = position;
			this.health = health;
			this.hungerStats = hungerStats;
		}

		@Override
		public NBTTagCompound serializeNBT()
		{
			NBTTagCompound compound = new NBTTagCompound();
			compound.setLong("pos", position.toLong());
			compound.setFloat("health", health);
			compound.setInteger("food_level", hungerStats.getFoodLevel());
			compound.setFloat("saturation", hungerStats.getSaturationLevel());
			return compound;
		}

		@Override
		public void deserializeNBT(NBTTagCompound nbt)
		{
			this.position = BlockPos.fromLong(nbt.getLong("pos"));
			this.health = nbt.getFloat("health");
			FoodStats food = new FoodStats();
			food.addStats(nbt.getInteger("food_level"), nbt.getFloat("saturation"));
			this.hungerStats = food;
		}


		@Override
		public String toString()
		{
			return "WarpData:" + position;
		}

	}

}
