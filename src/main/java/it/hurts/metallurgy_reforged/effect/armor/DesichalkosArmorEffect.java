/*==============================================================================
 = Class: DesichalkosArmorEffect
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2020.
 =============================================================================*/

package it.hurts.metallurgy_reforged.effect.armor;

import it.hurts.metallurgy_reforged.capabilities.entity.EntityData;
import it.hurts.metallurgy_reforged.capabilities.entity.EntityDataProvider;
import it.hurts.metallurgy_reforged.config.ArmorEffectsConfig;
import it.hurts.metallurgy_reforged.effect.BaseMetallurgyEffect;
import it.hurts.metallurgy_reforged.entity.ai.AIEndermanPlayerSteal;
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.model.EnumTools;
import it.hurts.metallurgy_reforged.util.EventUtils;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Objects;

public class DesichalkosArmorEffect extends BaseMetallurgyEffect {

	public static final IBlockState[] borrowableBlocks = Arrays.stream(ArmorEffectsConfig.desichalkosEndermenBlocks)
			.map(regName -> ForgeRegistries.BLOCKS.getValue(new ResourceLocation(regName)))
			.filter(Objects::nonNull)
			.map(Block::getDefaultState)
			.toArray(IBlockState[]::new);

	public DesichalkosArmorEffect()
	{
		super(ModMetals.DESICHALKOS);
	}

	@Override
	public boolean isEnabled()
	{
		return ArmorEffectsConfig.desichalkosArmorEffect && super.isEnabled();
	}

	@Override
	public boolean isToolEffect()
	{
		return false;
	}

	@Nullable
	@Override
	public EnumTools getToolClass()
	{
		return null;
	}

	@Override
	public void onEntityEnteringChunk(Entity entity)
	{
		if (entity instanceof EntityEnderman)
		{
			EntityData entityData = entity.getCapability(EntityDataProvider.ENTITY_DATA_CAPABILITY, null);
			if (entityData != null)
				entityData.initEnderman();
			((EntityEnderman) entity).tasks.addTask(12, new AIEndermanPlayerSteal((EntityEnderman) entity));
		}
	}

	@Override
	public void onPlayerInteract(PlayerInteractEvent event)
	{
		EntityPlayer player = event.getEntityPlayer();
		if (event instanceof PlayerInteractEvent.EntityInteract && EventUtils.isEntityWearingArmor(player, metal))
		{
			Entity target = ((PlayerInteractEvent.EntityInteract) event).getTarget();

			if (target instanceof EntityEnderman)
			{
				EntityEnderman enderman = (EntityEnderman) target;
				if (enderman.getHeldBlockState() != null)
				{
					if (!player.world.isRemote)
					{
						ItemStack snatchedBlock = new ItemStack(enderman.getHeldBlockState().getBlock());
						if (!player.inventory.addItemStackToInventory(snatchedBlock))
							player.dropItem(snatchedBlock, false, false);
						enderman.setHeldBlockState(null);


						EntityData data = enderman.getCapability(EntityDataProvider.ENTITY_DATA_CAPABILITY, null);
						if (data != null)
							data.wasSnatched = true;
						player.world.playSound(null, enderman.posX, enderman.posY, enderman.posZ, SoundEvents.ENTITY_ENDERMEN_HURT, SoundCategory.HOSTILE, 2F, 1.3F);
					}
				}
			}
		}
	}

}
