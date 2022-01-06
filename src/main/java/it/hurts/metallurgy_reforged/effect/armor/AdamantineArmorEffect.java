/*==============================================================================
 = Class: AdamantineArmorEffect
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.effect.armor;

import it.hurts.metallurgy_reforged.effect.BaseMetallurgyEffect;
import it.hurts.metallurgy_reforged.effect.EnumEffectCategory;
import it.hurts.metallurgy_reforged.effect.all.TartariteEffect;
import it.hurts.metallurgy_reforged.item.armor.ItemArmorBase;
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.network.PacketManager;
import it.hurts.metallurgy_reforged.network.client.PacketAdamantineRenderDeathProtection;
import it.hurts.metallurgy_reforged.util.ItemUtils;
import it.hurts.metallurgy_reforged.util.Utils;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nonnull;

public class AdamantineArmorEffect extends BaseMetallurgyEffect {


	public AdamantineArmorEffect()
	{
		super(ModMetals.ADAMANTINE);
	}

	@Nonnull
	@Override
	public EnumEffectCategory getCategory()
	{
		return EnumEffectCategory.ARMOR;
	}


	/**
	 * If the player is wearing Adamantine armor they have a chance to survive death with an armor piece sacrificing itself
	 *
	 * @param event Fired when the player is about to die
	 */
	@SubscribeEvent
	public void onEntityDeath(LivingDeathEvent event)
	{
		EntityLivingBase entity = event.getEntityLiving();

		if (!canBeApplied(entity))
			return;

		if (!entity.world.isRemote)
		{
			//Get a random slot from the Armor slots (the "2 +" skips main hand and off hand slots)
			EntityEquipmentSlot randomArmorSlot = EntityEquipmentSlot.values()[2 + Utils.random.nextInt(4)];

			ItemStack armorPiece = entity.getItemStackFromSlot(randomArmorSlot);

			if (entity instanceof EntityPlayerMP)
			{
				// if any of the piece is on cooldown the effect is cancelled
				if (((EntityPlayerMP) entity).getCooldownTracker().getCooldown(armorPiece.getItem(), 0) != 0)
					return;
			}

			//Check whether the itemStack inside the random slot is an Adamantine armor piece
			if (ItemUtils.isMadeOfMetal(metal, armorPiece.getItem()) || TartariteEffect.getParagonMetal(armorPiece) == metal)
			{
				//Remove armor piece from the slot
				entity.setItemStackToSlot(randomArmorSlot, ItemStack.EMPTY);
				//Play Totem of Undying sound
				entity.world.playSound(null, entity.getPosition(), SoundEvents.ITEM_TOTEM_USE, SoundCategory.PLAYERS, 1, 1);

				//Set the player health to half a heart
				// (if this line is removed the player dies even when cancelling the death event because they have 0 health)
				entity.setHealth(1F);
				//Cancel Player Death
				event.setCanceled(true);

				if (entity instanceof EntityPlayerMP)
				{
					EntityPlayerMP player = ((EntityPlayerMP) entity);

					//Set cooldown on the remaining armor pieces
					assert metal.getArmorSet() != null;
					for (ItemArmorBase armorItem : metal.getArmorSet())
						player.getCooldownTracker().setCooldown(armorItem, 100);
					//Send a packet to emit particles and render the Totem item overlay
					PacketAdamantineRenderDeathProtection packet = new PacketAdamantineRenderDeathProtection(entity.getEntityId(), armorPiece);
					//This criteria needs to be triggered in order to the totem overlay to work
					CriteriaTriggers.USED_TOTEM.trigger(player, armorPiece);

					//Send two packets to the client (one to self and many to the other players that are watching the entity)
					PacketManager.network.sendTo(packet, player);
					PacketManager.network.sendToAllTracking(packet, entity);
				}

				//Give absorption and regeneration III to the player so that they don't die instantly after being revived
				entity.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 20 * 25, 2));
				entity.addPotionEffect(new PotionEffect(MobEffects.ABSORPTION, 60, 1));
			}
		}

	}

}
