/*==============================================================================
 = Class: AIEndermanPlayerSteal
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2020.
 =============================================================================*/

package it.hurts.metallurgy_reforged.entity.ai;

import it.hurts.metallurgy_reforged.capabilities.entity.EntityData;
import it.hurts.metallurgy_reforged.capabilities.entity.EntityDataProvider;
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.util.EventUtils;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.player.EntityPlayer;

public class AIEndermanPlayerSteal extends EntityAIBase {

	private final EntityEnderman enderman;
	private EntityPlayer player;
	private EntityData endermanData;


	public AIEndermanPlayerSteal(EntityEnderman enderman)
	{
		this.enderman = enderman;
	}


	@Override
	public boolean shouldExecute()
	{
		EntityPlayer player = this.enderman.world.getClosestPlayerToEntity(this.enderman, 10);
		EntityData data = this.enderman.getCapability(EntityDataProvider.ENTITY_DATA_CAPABILITY, null);
		if (ModMetals.DESICHALKOS != null && player != null && data != null && !data.wasSnatched)
		{
			this.player = player;
			this.endermanData = data;
			return EventUtils.isEntityWearingArmor(player, ModMetals.DESICHALKOS);
		}
		return false;
	}

	@Override
	public void updateTask()
	{
		this.enderman.getLookHelper().setLookPositionWithEntity(this.player, 180F, 180F);
	}

	@Override
	public void startExecuting()
	{
		if (this.enderman.getHeldBlockState() == null)
		{
			this.enderman.setHeldBlockState(endermanData.snatchableBlock);
		}
	}

	@Override
	public void resetTask()
	{
		this.enderman.setHeldBlockState(null);
	}

}
