package it.hurts.metallurgy_reforged.util.capabilities.punch;

import net.minecraft.entity.EntityLivingBase;

public class PunchEffect implements IPunchEffect{

	private int hit_ticks = 0;
	private boolean noClip = true;
	
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
	public boolean hasNoClip() {
		return noClip;
	}

	@Override
	public void setNoClip(boolean clip) {
		this.noClip = clip;
	}

	@Override
	public void endEffect(EntityLivingBase entity) {
		this.setHitTicks(0);
		entity.noClip = this.noClip;
		System.out.println("end");
	}



	

}
