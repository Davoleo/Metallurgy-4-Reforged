/*==============================================================================
 = Class: AdamantineEffect
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.effect.all;

import it.hurts.metallurgy_reforged.effect.BaseMetallurgyEffect;
import it.hurts.metallurgy_reforged.effect.EnumEffectCategory;
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.util.ItemUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nonnull;
import java.util.Spliterator;
import java.util.stream.StreamSupport;

public class AdamantineEffect extends BaseMetallurgyEffect {

	public AdamantineEffect()
	{
		super(ModMetals.ADAMANTINE);
	}

	@Nonnull
	@Override
	public EnumEffectCategory getCategory()
	{
		return EnumEffectCategory.ALL;
	}

	@SubscribeEvent
	public void onFinishedEating(LivingEntityUseItemEvent.Finish event)
	{
		Item food = event.getItem().getItem();
		EntityLivingBase entity = event.getEntityLiving();
		if (food instanceof ItemFood)
		{
			//Venti: EHE! XP
			//PAIMON: EHE te nandayo! (￣^￣)
			Spliterator<ItemStack> equiperator2000 = entity.getEquipmentAndArmor().spliterator();
			StreamSupport.stream(equiperator2000, false)
					.filter(equip -> equip.getItemDamage() > 0 &&
							(ItemUtils.isMadeOfMetal(metal, equip.getItem()) || TartariteEffect.getParagonMetal(equip) == metal))
					.forEach(stack -> {
						if (stack.getItemDamage() != 0)
						{
							if (!entity.world.isRemote)
								stack.setItemDamage(Math.max(stack.getItemDamage() - ((ItemFood) food).getHealAmount(stack) * 2, 0));
							else
								entity.world.playSound(entity.posX, entity.posY, entity.posZ, SoundEvents.BLOCK_ANVIL_USE, SoundCategory.PLAYERS, 0.5F, 3F, false);
						}
					});

			if (entity instanceof EntityPlayer)
			{
				final NonNullList<ItemStack> inventory = ((EntityPlayer) entity).inventory.mainInventory;
				for (int i = 0; i < inventory.size(); i++)
				{
					if (i == ((EntityPlayer) entity).inventory.currentItem)
						continue;

					ItemStack stack = inventory.get(i);

					if ((ItemUtils.isMadeOfMetal(metal, stack.getItem()) || TartariteEffect.getParagonMetal(stack) == metal) && stack.getItemDamage() != 0)
					{
						if (!entity.world.isRemote)
							stack.setItemDamage(Math.max(stack.getItemDamage() - ((ItemFood) food).getHealAmount(stack) * 2, 0));
						else
							entity.world.playSound(entity.posX, entity.posY, entity.posZ, SoundEvents.BLOCK_ANVIL_USE, SoundCategory.PLAYERS, 0.5F, 3F, false);
					}
				}
			}
		}
	}

	@Override
	public void inventoryTick(@Nonnull ItemStack stack, @Nonnull World worldIn, @Nonnull Entity entityIn, int itemSlot, boolean isSelected)
	{
		if (worldIn.isRemote)
			return;

		if (entityIn instanceof EntityPlayer)
		{
			EntityPlayer player = ((EntityPlayer) entityIn);
			float foodLevelPercentage = player.getFoodStats().getFoodLevel() / 20F;

			if (foodLevelPercentage < 0.85)
			{
				int secondsWait = (1 + MathHelper.floor(8 * foodLevelPercentage)) * 20;

				if (player.ticksExisted % secondsWait == 0)
				{
					if (ItemUtils.isMadeOfMetal(metal, stack.getItem()) || TartariteEffect.getParagonMetal(stack) == metal)
						stack.damageItem(2, player);

					//See if this sound is shy enough to be kept or if we should go for no sound at all
					player.world.playSound(null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_PAINTING_BREAK, SoundCategory.PLAYERS, 0.3F, 0.8F);

					for (int i = 0; i < 5; i++)
						spawnParticle(player, 1F, true, 1);
				}
			}
		}
	}

}
