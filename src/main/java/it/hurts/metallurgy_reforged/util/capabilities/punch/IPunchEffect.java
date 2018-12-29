package it.hurts.metallurgy_reforged.util.capabilities.punch;

import net.minecraft.entity.EntityLivingBase;

public interface IPunchEffect {

	
	
	//set entity integer "hit_ticks" which indicates the knockback time of the mob 
	public void setHitTicks(int ticks);
	
	public int getHitTicks();
	
	public void addHitTicks();
	
	public boolean hasNoClip();
	
	public void setNoClip(boolean clip);
	
	public void endEffect(EntityLivingBase entity);
	
}
