/*==============================================================================
 = Class: BaseMetallurgyEffect
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2020.
 =============================================================================*/

package it.hurts.metallurgy_reforged.effect;

import it.hurts.metallurgy_reforged.material.Metal;
import it.hurts.metallurgy_reforged.model.EnumTools;
import it.hurts.metallurgy_reforged.util.Utils;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.event.world.GetCollisionBoxesEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public abstract class BaseMetallurgyEffect {

	protected Metal metal;

	public BaseMetallurgyEffect(Metal metal)
	{
		this.metal = metal;

		if (isEnabled())
		{
			MetallurgyEffects.effects.add(this);
		}
	}

	//TODO Maybe look for metal config reference automatically
	public boolean isEnabled()
	{
		return metal != null;
	}

	public abstract boolean isToolEffect();

	@Nullable
	public abstract EnumTools getToolClass();

	public String getTooltip()
	{

		if (isToolEffect())
		{
			if (getToolClass() != null)
			{
				return Utils.localize("tooltip.metallurgy." + metal.toString() + "_" + getToolClass().getName() + "_effect");
			}
			else
			{
				return Utils.localize("tooltip.metallurgy." + metal.toString() + "_tool_effect");
			}
		}
		else
		{
			return Utils.localize("tooltip.metallurgy." + metal.toString() + "_armor_effect");
		}
	}

	public Metal getMetal()
	{
		return metal;
	}

	public void onPlayerTick(EntityPlayer player)
	{ }

	@SideOnly(Side.CLIENT)
	public void onEntitiesRender(EntityLivingBase entity, RenderLivingBase<EntityLivingBase> renderer, float partialRenderTicks, double x, double y, double z)
	{ }

	public void onEntityEnteringChunk(Entity entity)
	{ }

	public void onBlockHarvested(BlockEvent.HarvestDropsEvent event)
	{ }

	public void playerBreakSpeed(PlayerEvent.BreakSpeed event)
	{ }

	public void onPlayerAttack(EntityPlayer attacker, Entity target)
	{ }

	public void onPlayerKill(EntityPlayer killer, EntityLivingBase killedEntity)
	{ }

	public void onEntityKillDrop(List<EntityItem> drops, EntityPlayer killer)
	{ }

	public void onPlayerInteract(PlayerInteractEvent event)
	{ }

	public void onPlayerCollision(GetCollisionBoxesEvent event)
	{ }

	public void livingEvent(LivingEvent event)
	{ }

}
