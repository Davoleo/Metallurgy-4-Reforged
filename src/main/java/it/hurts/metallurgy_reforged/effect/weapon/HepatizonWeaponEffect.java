/*==============================================================================
 = Class: HepatizonWeaponEffect
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.effect.weapon;

import com.google.common.collect.Lists;
import it.hurts.metallurgy_reforged.effect.BaseMetallurgyEffect;
import it.hurts.metallurgy_reforged.effect.EnumEffectCategory;
import it.hurts.metallurgy_reforged.material.ModMetals;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nonnull;
import java.util.List;

public class HepatizonWeaponEffect extends BaseMetallurgyEffect {

	public HepatizonWeaponEffect()
	{
		super(ModMetals.HEPATIZON);
	}

	@Nonnull
	@Override
	public EnumEffectCategory getCategory()
	{
		return EnumEffectCategory.WEAPON;
	}

	@SubscribeEvent
	public void disarmEnemy(LivingHurtEvent event)
	{
		Entity esource = event.getSource().getImmediateSource();
		if (esource instanceof EntityLivingBase)
		{
			EntityLivingBase attacker = ((EntityLivingBase) esource);
			if (!canBeApplied(attacker))
				return;

			final EntityLivingBase attacked = event.getEntityLiving();
			if (attacked.getRNG().nextInt(8) == 0)
			{
				List<EntityEquipmentSlot> equippedSlots = Lists.newArrayList();

				//Add all the equipped slots to a cached list
				for (EntityEquipmentSlot slot : EntityEquipmentSlot.values())
				{
					if (!attacked.getItemStackFromSlot(slot).isEmpty())
						equippedSlots.add(slot);
				}

				if (equippedSlots.isEmpty())
					return;

				//Random Equipped slot
				EntityEquipmentSlot randSlot = equippedSlots.get(attacked.getRNG().nextInt(equippedSlots.size()));
				//Stack from that random slot
				ItemStack randomEquip = attacked.getItemStackFromSlot(randSlot);
				//Empty the random slot
				attacked.setItemStackToSlot(randSlot, ItemStack.EMPTY);
				//durability heal amount: 1 to 50% of the total durability
				int healAmount = attacked.getRNG().nextInt(randomEquip.getMaxDamage() / 2) + 1;
				//Damage the equipment that is going to be dropped
				randomEquip.setItemDamage(randomEquip.getMaxDamage() - healAmount);
				//Drop the item that was stored in it
				attacked.entityDropItem(randomEquip, attacked.height / 2);

				for (int i = 0; i < 10; i++)
					spawnParticle(attacked, 2.5F, false, 3);
			}
		}
	}

}
