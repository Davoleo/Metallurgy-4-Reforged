/*==============================================================================
 = Class: SanguiniteToolEffect
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.effect.tool;

import it.hurts.metallurgy_reforged.effect.BaseMetallurgyEffect;
import it.hurts.metallurgy_reforged.effect.EnumEffectCategory;
import it.hurts.metallurgy_reforged.material.ModMetals;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.CooldownTracker;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nonnull;

public class SanguiniteToolEffect extends BaseMetallurgyEffect {

	public SanguiniteToolEffect()
	{
		super(ModMetals.SANGUINITE);
	}

	@Nonnull
	@Override
	public EnumEffectCategory getCategory()
	{
		return EnumEffectCategory.TOOL;
	}

	@Override
	public void rightClickHandler(@Nonnull World worldIn, @Nonnull EntityPlayer playerIn, @Nonnull EnumHand handIn)
	{
		ItemStack toolStack = playerIn.getHeldItemMainhand();
		CooldownTracker cooldowns = playerIn.getCooldownTracker();

		//If previous effect hasn't finished yet
		if (cooldowns.getCooldown(toolStack.getItem(), 0) > 0)
			return;

		playerIn.attackEntityFrom(DamageSource.WITHER, 6);
		playerIn.playSound(SoundEvents.ENTITY_ENDERDRAGON_GROWL, 0.75F, 2F);

		int newDurability = Math.min(toolStack.getItemDamage() + 6, toolStack.getMaxDamage());
		toolStack.setItemDamage(newDurability);

		NBTTagCompound data = toolStack.getTagCompound();
		if (data == null)
			data = new NBTTagCompound();

		data.setInteger("harvest_boost", 1);
		toolStack.setTagCompound(data);

		//16 seconds cooldown
		cooldowns.setCooldown(toolStack.getItem(), 16 * 20);
	}

	@SubscribeEvent
	public void boostHarvest(PlayerEvent.BreakSpeed event)
	{
		ItemStack toolStack = event.getEntityPlayer().getHeldItemMainhand();
		NBTTagCompound toolData = toolStack.getTagCompound();

		if (event.getEntityPlayer().getCooldownTracker().getCooldown(toolStack.getItem(), 0) > 0)
		{
			event.setNewSpeed(event.getOriginalSpeed() * 1.75F);
			spawnParticle(event.getEntityPlayer(), 2F, true, 4);
		}
		else if (toolData != null)
			toolData.setInteger("harvest_boost", 0);
	}

}
