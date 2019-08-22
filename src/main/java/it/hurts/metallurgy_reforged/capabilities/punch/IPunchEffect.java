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

import javax.annotation.Nullable;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public interface IPunchEffect {

	
	
	//set entity integer "hit_ticks" which indicates the knockback time of the mob 
	
	void setPunchingPlayer(PlayerEntity pl);
	@Nullable
	PlayerEntity getPunchingPlayer(World world);
	
	void setDelayHit(int delay);
	int getDelayHit();
	
	void setHitTicks(int ticks);
	
	int getHitTicks();
	
	void addHitTicks();
	
	boolean isAIDisabled();
	
	void setNoAI(boolean ai);
	
	void endEffect(LivingEntity entity);
	
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
