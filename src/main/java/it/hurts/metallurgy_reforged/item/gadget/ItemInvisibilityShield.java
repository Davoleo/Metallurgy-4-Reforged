/*
 * -------------------------------------------------------------------------------------------------------
 * Class: ItemInvisibilityShield
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 * Copyright (c) 2020.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.item.gadget;

import it.hurts.metallurgy_reforged.item.ItemExtra;
import it.hurts.metallurgy_reforged.util.MetallurgyTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.EnumAction;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.SPacketParticles;
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ItemInvisibilityShield extends ItemExtra {

	public ItemInvisibilityShield()
	{
		super("lemurite_shield", MetallurgyTabs.tabSpecial, "gadget");
		setMaxStackSize(1);
		setMaxDamage(250);

		this.addPropertyOverride(new ResourceLocation("blocking"), new IItemPropertyGetter() {
			@SideOnly(Side.CLIENT)
			public float apply(@Nonnull ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn)
			{
				return entityIn != null && entityIn.isHandActive() && entityIn.getActiveItemStack() == stack ? 1.0F : 0.0F;
			}
		});

	}

	@Override
	public boolean isShield(@Nonnull ItemStack stack, @Nullable EntityLivingBase entity)
	{
		return true;
	}

	@Override
	public int getItemEnchantability()
	{
		return 25;
	}

	@Override
	public int getMaxItemUseDuration(@Nonnull ItemStack stack)
	{
		return 600;
	}

	@Nonnull
	@Override
	public ActionResult<ItemStack> onItemRightClick(@Nonnull World worldIn, EntityPlayer playerIn, @Nonnull EnumHand handIn)
	{
		playerIn.setInvisible(true);
		playerIn.setActiveHand(handIn);
		spawnParticles(playerIn);
		return new ActionResult<>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
	}

	@Override
	public void onPlayerStoppedUsing(@Nonnull ItemStack stack, @Nonnull World worldIn, @Nonnull EntityLivingBase entityLiving, int timeLeft)
	{
		terminateEffect(entityLiving, stack, 600 - timeLeft);
	}

	@Override
	public void onUsingTick(@Nonnull ItemStack stack, @Nonnull EntityLivingBase player, int count)
	{
		if (count <= 1)
			terminateEffect(player, stack, 600);
	}

	private void terminateEffect(EntityLivingBase player, ItemStack stack, int cooldown)
	{
		spawnParticles(player);
		player.setInvisible(false);
		((EntityPlayer) player).getCooldownTracker().setCooldown(stack.getItem(), cooldown);
	}

	private void spawnParticles(EntityLivingBase entity)
	{
		if (entity instanceof EntityPlayerMP)
			((EntityPlayerMP) entity).connection.sendPacket(new SPacketParticles(EnumParticleTypes.EXPLOSION_LARGE, true, (float) entity.posX, (float) entity.posY + 1, (float) entity.posZ, 0, 0, 0, 0, 1, 0));
	}

	@Nonnull
	@Override
	public EnumAction getItemUseAction(@Nonnull ItemStack stack)
	{
		return EnumAction.BLOCK;
	}

}
