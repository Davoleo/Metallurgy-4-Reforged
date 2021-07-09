/*==============================================================================
 = Class: ElectrumArmorEffect
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.effect.armor;

import it.hurts.metallurgy_reforged.effect.BaseMetallurgyEffect;
import it.hurts.metallurgy_reforged.effect.EnumEffectCategory;
import it.hurts.metallurgy_reforged.material.ModMetals;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nonnull;

public class ElectrumArmorEffect extends BaseMetallurgyEffect {

	public ElectrumArmorEffect()
	{
		super(ModMetals.ELECTRUM);
	}

	@Nonnull
	@Override
	public EnumEffectCategory getCategory()
	{
		return EnumEffectCategory.ARMOR;
	}

	@SubscribeEvent
	public void chargeItemsOnHurt(LivingHurtEvent event)
	{
		if (event.getEntityLiving() instanceof EntityPlayer)
		{
			EntityPlayer player = ((EntityPlayer) event.getEntityLiving());
			float level = getLevel(player);

			if (level == 0)
				return;

			NonNullList<ItemStack> energyStacks = NonNullList.create();

			//Add Items that support the Energy Capability to a list
			player.inventory.mainInventory.forEach(stack -> {
				IEnergyStorage itemCap = stack.getCapability(CapabilityEnergy.ENERGY, null);
				if (itemCap != null && itemCap.canReceive())
				{
					energyStacks.add(stack);
				}
			});

			// max energy is 7000 + 0..3 * 3000
			//with 0..3 being dependant on how much armor the player is wearing
			final int maxEnergy = 7000 + ((int) (level * 4 - 1) * 3000);

			//Energy proportional to the the health remaining after the pure amount of damage is taken away
			float energy = (1 - (player.getHealth() - event.getAmount()) / player.getMaxHealth()) * maxEnergy;
			float distributedEnergy = energy / energyStacks.size();

			// TODO: 21/02/2021 Maybe add support for partial energy recharge
			energyStacks.forEach(stack -> {
				IEnergyStorage storage = stack.getCapability(CapabilityEnergy.ENERGY, null);
				assert storage != null;
				storage.receiveEnergy(MathHelper.ceil(distributedEnergy), false);
			});
		}
	}

}
