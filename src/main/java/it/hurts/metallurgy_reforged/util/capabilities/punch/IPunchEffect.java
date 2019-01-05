package it.hurts.metallurgy_reforged.util.capabilities.punch;

import javax.annotation.Nullable;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public interface IPunchEffect {

	
	
	//set entity integer "hit_ticks" which indicates the knockback time of the mob 
	
	public void setPunchingPlayer(EntityPlayer pl);
	@Nullable
	public EntityPlayer getPunchingPlayer(World world);
	
	public void setDelayHit(int delay);
	public int getDelayHit();
	
	public void setHitTicks(int ticks);
	
	public int getHitTicks();
	
	public void addHitTicks();
	
	public boolean isAIDisabled();
	
	public void setNoAI(boolean ai);
	
	public void endEffect(EntityLivingBase entity);
	
	public void setKnockbackTicks(int ticks);
	
	public int getKnockbackTicks();
	
	public void addKnockbackTTicks();
	
	public void setKnockbackMotionVec(Vec3d vec);
	
	public Vec3d getKnockbackMotionVec();
	
}
