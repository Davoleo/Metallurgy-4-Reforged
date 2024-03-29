/*==============================================================================
 = Class: AtlarusHoeEffect
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.effect.hoe;

import it.hurts.metallurgy_reforged.effect.BaseMetallurgyEffect;
import it.hurts.metallurgy_reforged.effect.EnumEffectCategory;
import it.hurts.metallurgy_reforged.item.tool.EnumTools;
import it.hurts.metallurgy_reforged.material.ModMetals;
import net.minecraft.block.BlockBush;
import net.minecraft.block.material.MaterialTransparent;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import javax.annotation.Nonnull;

// TODO: 24/12/2020 @PierKnight comment this class
public class AtlarusHoeEffect extends BaseMetallurgyEffect {

	private static final int MAX_RANGE = 5;

	public AtlarusHoeEffect()
	{
		super(ModMetals.ATLARUS);
	}

	@Nonnull
	@Override
	public EnumEffectCategory getCategory()
	{
		return EnumEffectCategory.HOE;
	}

	@SubscribeEvent
	public void onPlayerTick(TickEvent.PlayerTickEvent event)
	{
		if (!canBeApplied(event.player))
			return;

		World world = event.player.world;
		ItemStack stack = event.player.getHeldItemMainhand();
		if (world.getTotalWorldTime() % 4 == 0)
		{
			NBTTagCompound tag = stack.getTagCompound();
			if (tag != null && tag.hasKey("range"))
			{
				NBTTagCompound posTag = tag.getCompoundTag("startPos");
				BlockPos startPos = new BlockPos(posTag.getInteger("posX"), posTag.getInteger("posY"), posTag.getInteger("posZ"));

				int range = tag.getInteger("range");

				int destroyedBlocks = 0;

				for (int x = -range; x <= range; x++)
				{
					for (int y = -MAX_RANGE; y <= MAX_RANGE; y++)
					{
						for (int z = -range; z <= range; z++)
						{
							if (Math.abs(x) == range || Math.abs(z) == range)
							{
								BlockPos pos = startPos.add(x, y, z);
								if (!world.isRemote)
								{
									if (world.getBlockState(pos).getBlock() instanceof BlockBush)
									{
										world.destroyBlock(pos, true);
										destroyedBlocks++;
									}
								}
								else if (y == getParticleY(world, pos.getX(), startPos.getY(), pos.getZ()))
								{
									for (int i = 0; i < 5; i++)
									{
										double particleX = Math.random();
										double particleZ = Math.random();
										double motionX = MathHelper.clamp(pos.getX() - startPos.getX(), -1, 1) * 0.1D;
										double motionZ = MathHelper.clamp(pos.getZ() - startPos.getZ(), -1, 1) * 0.1D;
										world.spawnParticle(EnumParticleTypes.CLOUD, pos.getX() + particleX, pos.getY(), pos.getZ() + particleZ, motionX, 0.7D, motionZ);
									}
								}
							}
						}
					}
				}

				if (destroyedBlocks > 0)
					stack.damageItem(Math.round(destroyedBlocks * 0.5F), event.player);

				world.playSound(null, startPos.getX(), startPos.getY(), startPos.getZ(), SoundEvents.ENTITY_BLAZE_SHOOT, SoundCategory.AMBIENT, 1.4F, 1F - ((float) range / MAX_RANGE) * 0.3F);

				if (range < MAX_RANGE)
					tag.setInteger("range", range + 1);
				else
				{
					tag.removeTag("range");
					tag.removeTag("startPos");
				}

				stack.setTagCompound(tag);
			}
		}
	}

	private int getParticleY(World world, int x, int y, int z)
	{
		for (int searchY = 0; searchY < MAX_RANGE; searchY++)
		{
			if (world.getBlockState(new BlockPos(x, y + searchY, z)).getMaterial() instanceof MaterialTransparent)
				return searchY;
		}
		return 0;
	}

	@SubscribeEvent
	public void onPlayerInteract(PlayerInteractEvent.RightClickItem event)
	{

		if (!canBeApplied(event.getEntityPlayer()))
			return;

		ItemStack stack = event.getItemStack();

		NBTTagCompound tag = stack.getTagCompound();
		if (tag == null)
			tag = new NBTTagCompound();

		if (!tag.hasKey("range"))
		{
			tag.setInteger("range", 0);

			BlockPos pos = event.getEntityPlayer().getPosition();
			NBTTagCompound posTag = new NBTTagCompound();
			posTag.setInteger("posX", pos.getX());
			posTag.setInteger("posY", pos.getY());
			posTag.setInteger("posZ", pos.getZ());
			tag.setTag("startPos", posTag);
			stack.setTagCompound(tag);

			event.getEntityPlayer().swingArm(event.getHand());
			event.getEntityPlayer().getCooldownTracker().setCooldown(metal.getTool(EnumTools.HOE), 30);
			event.setCanceled(true);
		}

	}

	@SubscribeEvent
	public void livingEvent(LivingEquipmentChangeEvent event)
	{
		ItemStack to = event.getTo();
		if (to.getItem() == metal.getTool(EnumTools.HOE) && event.getFrom().getItem() != metal.getTool(EnumTools.HOE))
		{
			NBTTagCompound tag = to.getTagCompound();
			if (tag != null && tag.hasKey("range"))
				tag.removeTag("range");
			to.setTagCompound(tag);
		}
	}

}
