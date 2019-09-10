/*
 * -------------------------------------------------------------------------------------------------------
 * Class: IPunchEffect
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2019.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.capabilities.punch;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public interface IPunchEffect {


	//set entity integer "hit_ticks" which indicates the knockback time of the mob 

	void setPunchingPlayer(EntityPlayer pl);

	@Nullable
	EntityPlayer getPunchingPlayer(World world);

	void setDelayHit(int delay);

	int getDelayHit();

	void setHitTicks(int ticks);

	int getHitTicks();

	void addHitTicks();

	boolean isAIDisabled();

	void setNoAI(boolean ai);

	void endEffect(EntityLivingBase entity);

	void setKnockbackTicks(int ticks);

	int getKnockbackTicks();

	void addKnockbackTTicks();

	// void setKnockbackMotionVec(Vec3d vec);

	Vec3d getKnockbackMotionVec();

	void setRotYawPlayer(float yaw);

	void setRotPitchPlayer(float pitch);

	float getRotYawPlayer();

	float getRotPitchPlayer();

	boolean isGauntletUserDead();

	void setGauntletUserDead();

}
