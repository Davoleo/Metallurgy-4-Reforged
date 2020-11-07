/*==============================================================================
 = Class: ItemLemuriteShield
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2020.
 =============================================================================*/

package it.hurts.metallurgy_reforged.item.gadget.shield;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.SPacketParticles;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class ItemLemuriteShield extends ItemShieldBase {

	public ItemLemuriteShield()
	{
		super("lemurite_shield", 250);
	}

	@Override
	public int getItemEnchantability()
	{
		return 17;
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
		spawnParticles(playerIn);
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}

	@Override
	public void onPlayerStoppedUsing(@Nonnull ItemStack stack, @Nonnull World worldIn, @Nonnull EntityLivingBase entityLiving, int timeLeft)
	{
		//Cooldown can't be less than one second (you can't spam to make mobs buffer kek)
		terminateEffect(entityLiving, Math.max(600 - timeLeft, 20));
	}

	@Override
	public void onUsingTick(@Nonnull ItemStack stack, @Nonnull EntityLivingBase player, int count)
	{
		if (count <= 1)
			terminateEffect(player, 600);
	}

	private void terminateEffect(EntityLivingBase player, int cooldown)
	{
		spawnParticles(player);
		player.setInvisible(false);
		((EntityPlayer) player).getCooldownTracker().setCooldown(this, cooldown);
	}

	private void spawnParticles(EntityLivingBase entity)
	{
		if (entity instanceof EntityPlayerMP)
			((EntityPlayerMP) entity).connection.sendPacket(new SPacketParticles(EnumParticleTypes.EXPLOSION_LARGE, true, (float) entity.posX, (float) entity.posY + 1, (float) entity.posZ, 0, 0, 0, 0, 1, 0));
	}

}
