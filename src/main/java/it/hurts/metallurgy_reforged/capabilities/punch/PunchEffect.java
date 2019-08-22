/*
 * -------------------------------------------------------------------------------------------------------
 * Class: PunchEffect
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2019.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.capabilities.punch;

import java.util.UUID;

import javax.annotation.Nullable;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class PunchEffect implements IPunchEffect{

	private int hit_ticks = 0;
	private int knockbackTicks = 0;
	private int delayFromHit = 0;
	private boolean hasNoAI = true;
	private UUID plUUID = null;
	private float rotYaw = 0F;
	private float rotPitch = 0F;
	private boolean isGauntletUserDead = false;
	
	@Override
	public void setHitTicks(int ticks) {
		this.hit_ticks = ticks;
	}

	@Override
	public int getHitTicks() {
		return hit_ticks;
	}

	@Override
	public void addHitTicks() {
		this.hit_ticks++;
	}

	@Override
	public void endEffect(LivingEntity entity) {
		this.setKnockbackTicks(0);
		this.setHitTicks(0);
		entity.noClip = false;
	}

	@Override
	public void setKnockbackTicks(int ticks) {
		knockbackTicks = ticks;
	}

	@Override
	public int getKnockbackTicks() {
		return knockbackTicks;
	}

	@Override
	public void addKnockbackTTicks() {
		knockbackTicks += 1;
	}

	@Override
	public void setDelayHit(int delay) {
		delayFromHit = delay;
	}

	@Override
	public int getDelayHit() {
		return delayFromHit;
	}

	@Override
	public void setPunchingPlayer(PlayerEntity pl) {
		this.plUUID = pl == null ? null : pl.getUniqueID();	
	}

	@Override
	@Nullable
	public PlayerEntity getPunchingPlayer(World world) {
		return this.plUUID != null ? world.getPlayerByUuid(plUUID) : null;
	}

	//public void setKnockbackMotionVec(Vec3d vec) {
	//	this.vec3dMotion = vec;
	//}

	@Override
	public Vec3d getKnockbackMotionVec() {
		float yaw = this.getRotYawPlayer();
		float pitch = this.getRotPitchPlayer();

		double x = -MathHelper.sin(yaw * 0.017453292F) * MathHelper.cos(pitch * 0.017453292F);
		double y = -MathHelper.sin(pitch * 0.017453292F);		      
		double z = MathHelper.cos(yaw * 0.017453292F) * MathHelper.cos(pitch * 0.017453292F);
		double f = MathHelper.sqrt(x * x + y * y + z * z);
		double velocity = this.getHitTicks() > 10 ? 2D : 1D;
		x = x / f;
	    y = y / f;
		z = z / f;
		x = x * velocity;
		y = y * velocity;
		z = z * velocity;
		return new Vec3d(x, y, z);
	}

	@Override
	public boolean isAIDisabled() {
		return this.hasNoAI;
	}

	@Override
	public void setNoAI(boolean ai) {
		this.hasNoAI = ai;
	}

	@Override
	public void setRotYawPlayer(float yaw) {
		this.rotYaw = yaw;
	}

	@Override
	public void setRotPitchPlayer(float pitch) {
		this.rotPitch = pitch;
	}

	@Override
	public float getRotYawPlayer() {
		return this.rotYaw;
	}

	@Override
	public float getRotPitchPlayer() {
		return this.rotPitch;
	}

	@Override
	public boolean isGauntletUserDead()
	{
		return isGauntletUserDead;
	}

	@Override
	public void setGauntletUserDead()
	{
          this.isGauntletUserDead = true;
	}

}
