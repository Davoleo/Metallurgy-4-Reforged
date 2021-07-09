/*==============================================================================
 = Class: HaderothArmorEffect
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.effect.armor;

import it.hurts.metallurgy_reforged.effect.BaseMetallurgyEffect;
import it.hurts.metallurgy_reforged.effect.EnumEffectCategory;
import it.hurts.metallurgy_reforged.effect.MetallurgyEffects;
import it.hurts.metallurgy_reforged.item.armor.ItemArmorBase;
import it.hurts.metallurgy_reforged.item.tool.EnumTools;
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.util.ItemUtils;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.commons.lang3.tuple.Pair;
import it.hurts.metallurgy_reforged.util.EventUtils;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.world.GetCollisionBoxesEvent;

import javax.annotation.Nonnull;

public class HaderothArmorEffect extends BaseMetallurgyEffect {

	public HaderothArmorEffect()
	{
		super(ModMetals.HADEROTH);
	}

	@Nonnull
	@Override
	public EnumEffectCategory getCategory()
	{
		return EnumEffectCategory.ARMOR;
	}

	@Override
	public Pair<String, String> getTooltip()
	{
		Pair<String, String> tooltip = super.getTooltip();
		if (!MetallurgyEffects.HADEROTH_EFFECT.isEnabled())
		{
			int firstBreak = tooltip.getRight().indexOf("\n");
			String trimmed = tooltip.getRight().substring(firstBreak + 1);
			tooltip.setValue(trimmed);
		}

		return tooltip;
	}

	@SubscribeEvent
	public void buffWearer(LivingEvent.LivingUpdateEvent event)
	{
		EntityLivingBase entity = event.getEntityLiving();

		if (!MetallurgyEffects.HADEROTH_EFFECT.isEnabled())
			return;

		entity.getArmorInventoryList().forEach(stack -> {
			//If the the armor has not been reborn yet -> terminate adaptability effect
			if ((stack.getTagCompound() == null || !stack.getTagCompound().getBoolean("reborn")))
				return;

			if (ItemUtils.isMadeOfMetal(metal, stack.getItem()))
			{
				ItemArmorBase haderothArmorPiece = ((ItemArmorBase) stack.getItem());
				switch (haderothArmorPiece.armorType)
				{
					case HEAD:
						if (entity.isPotionActive(MobEffects.HUNGER))
							entity.removePotionEffect(MobEffects.HUNGER);
						break;
					case CHEST:
						if (entity.ticksExisted % 40 == 0)
							entity.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, 60, 0, false, false));
						break;
					case LEGS:
						if (entity.isPotionActive(MobEffects.SLOWNESS))
							entity.removePotionEffect(MobEffects.SLOWNESS);
						break;
					case FEET:
						if (entity.isPotionActive(MobEffects.LEVITATION))
							entity.removePotionEffect(MobEffects.LEVITATION);
						break;
				}
			}
		});
	}

}
