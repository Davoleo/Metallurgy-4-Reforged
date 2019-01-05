package it.hurts.metallurgy_reforged.util.capabilities.punch;

import java.util.UUID;

import javax.annotation.Nullable;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class PunchEffect implements IPunchEffect{

	private int hit_ticks = 0;
	private int knockbackTicks = 0;
	private int delayFromHit = 0;
	private boolean hasNoAI = true;
	private UUID plUUID = null;
	private Vec3d vec3dMotion = new Vec3d(0D,0D,0D);
	
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
	public void endEffect(EntityLivingBase entity) {
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
	public void setPunchingPlayer(EntityPlayer pl) {
		this.plUUID = pl == null ? null : pl.getUniqueID();	
	}

	@Override
	@Nullable
	public EntityPlayer getPunchingPlayer(World world) {
		return this.plUUID != null ? world.getPlayerEntityByUUID(plUUID) : null;
	}

	@Override
	public void setKnockbackMotionVec(Vec3d vec) {
		this.vec3dMotion = vec;
	}

	@Override
	public Vec3d getKnockbackMotionVec() {
		return vec3dMotion;
	}

	@Override
	public boolean isAIDisabled() {
		return this.hasNoAI;
	}

	@Override
	public void setNoAI(boolean ai) {
		this.hasNoAI = ai;
	}
	
}
