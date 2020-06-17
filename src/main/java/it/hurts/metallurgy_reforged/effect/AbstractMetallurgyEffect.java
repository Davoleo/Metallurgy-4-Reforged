/*
 * -------------------------------------------------------------------------------------------------------
 * Class: AbstractMetallurgyEffect
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 * Copyright (c) 2020.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.effect;

import it.hurts.metallurgy_reforged.material.Metal;
import it.hurts.metallurgy_reforged.model.EnumTools;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public abstract class AbstractMetallurgyEffect {

	protected Metal metal;

	public AbstractMetallurgyEffect(Metal metal)
	{
		this.metal = metal;
		if (isEnabled())
		{
			MetallurgyEffects.effects.add(this);
		}
	}

	protected abstract boolean isEnabled();

	protected abstract boolean isToolEffect();

	@Nullable
	protected abstract EnumTools getToolClass();

	public String getTooltip()
	{

		if (isToolEffect())
		{
			if (getToolClass() != null)
			{
				return "tooltip.metallurgy." + metal.toString() + "_" + getToolClass().getName() + "_effect";
			}
			else
			{
				return "tooltip.metallurgy." + metal.toString() + "_tool_effect";
			}
		}
		else
		{
			return "tooltip.metallurgy." + metal.toString() + "_armor_effect";
		}
	}

	public void onPlayerTick(EntityPlayer player)
	{ }

	public void onPlayerUseItem(LivingEntityUseItemEvent event)
	{ }

	@SideOnly(Side.CLIENT)
	public void onEntitiesRender(EntityLivingBase entity, RenderLivingBase<EntityLivingBase> renderer, float partialRenderTicks, double x, double y, double z)
	{ }

	public void onEntityEnteringChunk(Entity entity)
	{ }

	public void onPlayerAttacked(LivingAttackEvent event)
	{ }

	public void onPlayerFalling(LivingFallEvent event)
	{ }

	public void onPlayerKnockback(LivingKnockBackEvent event)
	{ }

	public void onBlockHarvested(EntityPlayer harvester, List<ItemStack> drops, int fortuneLevel, float dropChance, boolean isSilkTouching)
	{ }

	public void playerBreakSpeed(PlayerEvent.BreakSpeed event)
	{ }

	public void onPlayerAttack(EntityPlayer attacker, Entity target)
	{ }

	public void onPlayerKill(EntityPlayer killer, Entity killedEntity)
	{ }

	public void onEntityKillDrop(List<EntityItem> drops, EntityPlayer killer)
	{ }

	public void onEntityHurt(LivingHurtEvent event)
	{ }

}
