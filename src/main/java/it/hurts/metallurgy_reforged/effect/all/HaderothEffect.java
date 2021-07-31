/*==============================================================================
 = Class: HaderothEffect
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
import it.hurts.metallurgy_reforged.util.EventUtils;
import it.hurts.metallurgy_reforged.util.ItemUtils;
import it.hurts.metallurgy_reforged.util.NBTUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerDestroyItemEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nonnull;

public class HaderothEffect extends BaseMetallurgyEffect {

	private final AttributeModifier PROTECTION_MODIFIER = new AttributeModifier("Haderoth Armor Protection Buff", 9F, 0);
	private final AttributeModifier TOUGHNESS_MODIFIER = new AttributeModifier("Haderoth Armor Toughness Buff", 7F, 0);
	private final AttributeModifier ATTACK_DAMAGE_MODIFIER = new AttributeModifier("Haderoth Weapon Attack Buff", 9F, 0);
	private final AttributeModifier AXE_ATTACK_SPEED_MODIFIER = new AttributeModifier("Haderoth attack speed restore", 1.1F, 0);
	private final AttributeModifier SWORD_ATTACK_SPEED_MODIFIER = new AttributeModifier("Haderoth Attack speed Restore", 1.6F, 0);

	private final NBTTagCompound rebornCompound;

	public HaderothEffect()
	{
		super(ModMetals.HADEROTH);
		rebornCompound = new NBTTagCompound();
		rebornCompound.setBoolean("reborn", true);
		//Increases the maxdamage by 200
		rebornCompound.setFloat("durability_boost", 25F);
		rebornCompound.setFloat("attack_boost", 2F);
		// TODO: 14/03/2021 Maybe...?
		//setupModelOverrides((stack, worldIn, entityIn) -> stack.getTagCompound() != null && stack.getTagCompound().getBoolean("reborn") ? 1F : 0F);
	}

	@Nonnull
	@Override
	public EnumEffectCategory getCategory()
	{
		return EnumEffectCategory.ALL;
	}

	@Override
	public float getLevel(EntityLivingBase entity)
	{
		int armor = EventUtils.getArmorPiecesCount(entity, metal);
		int hand = ItemUtils.isMadeOfMetal(metal, entity.getHeldItemMainhand().getItem()) ? 5 : -1;
		return Math.max(armor / 4F, hand);
	}

	@SubscribeEvent
	public void applyItemMetamorphosis(PlayerDestroyItemEvent event)
	{
		ItemStack stack = event.getOriginal();
		if (ItemUtils.isMadeOfMetal(metal, stack.getItem()))
		{
			if (stack.getTagCompound() != null && stack.getTagCompound().getBoolean("reborn"))
				return;

			//Copy the old stack
			ItemStack newItem = stack.copy();

			//Set the reborn compound (increase durability)
			if (newItem.getTagCompound() == null)
				newItem.setTagCompound(rebornCompound);
			else
				NBTUtils.injectCompound("", newItem.getTagCompound(), rebornCompound);

			//Restore full stack durability
			newItem.setItemDamage(0);

			//Apply Attack damage buff if the item is part of the WEAPON category
			//if (stack.getItem().getClass() == EnumTools.AXE.getToolClass() || stack.getItem().getClass() == EnumTools.SWORD.getToolClass()) {
			//    newItem.addAttributeModifier(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), ATTACK_DAMAGE_MODIFIER, EntityEquipmentSlot.MAINHAND);
			//    //Should restore the original attack speed
			//    newItem.addAttributeModifier(
			//            SharedMonsterAttributes.ATTACK_SPEED.getName(),
			//            stack.getItem() instanceof ItemAxeBase ? AXE_ATTACK_SPEED_MODIFIER : SWORD_ATTACK_SPEED_MODIFIER,
			//            EntityEquipmentSlot.MAINHAND);
			//    newItem.getAttributeModifiers(EntityEquipmentSlot.MAINHAND).clear();
			//}

			//Set new tool to the main hand (the hand should be empty or containing the old tool in theory)
			event.getEntityPlayer().setHeldItem(EnumHand.MAIN_HAND, newItem);

			event.getEntityPlayer().world.playSound(null, event.getEntityPlayer().getPosition(), SoundEvents.BLOCK_ANVIL_PLACE, SoundCategory.PLAYERS, 0.75F, 1.25F);
		}
	}

	@SubscribeEvent
	public void applyWeaponAttackBoost(LivingHurtEvent event)
	{
		Entity source = event.getSource().getImmediateSource();
		if (source instanceof EntityLivingBase)
		{
			EntityLivingBase attacker = ((EntityLivingBase) source);
			if (getLevel(attacker) < 5)
				return;

			NBTTagCompound weaponData = attacker.getHeldItemMainhand().getTagCompound();
			if (weaponData != null && weaponData.getBoolean("reborn"))
			{
				event.setAmount(event.getAmount() + weaponData.getFloat("attack_boost"));
			}
		}
	}

	@SubscribeEvent
	public void applyToolEfficiencyBoost(PlayerEvent.BreakSpeed event)
	{
		EntityPlayer player = event.getEntityPlayer();

		if (getLevel(player) == 5)
		{
			ItemStack toolStack = player.getHeldItemMainhand();

			if (toolStack.getTagCompound() != null && toolStack.getTagCompound().getBoolean("reborn"))
				event.setNewSpeed(event.getOriginalSpeed() * 2F);
		}
	}

	@SubscribeEvent
	public void applyArmorMetamorphosis(LivingHurtEvent event)
	{
		EntityLivingBase entity = event.getEntityLiving();
		if (!canBeApplied(entity))
			return;

		//The damage applied is computed by dividing the pure damage amount by 4
		float damage = event.getAmount() / 4.0F;
		if (damage < 1.0F)
			damage = 1F;

		for (EntityEquipmentSlot slot : EntityEquipmentSlot.values())
		{
			if (slot.getSlotType() == EntityEquipmentSlot.Type.ARMOR)
			{
				ItemStack stack = entity.getItemStackFromSlot(slot);

				//If the item was already reborn
				if (stack.getTagCompound() != null && stack.getTagCompound().getBoolean("reborn"))
					continue;

				//Check if the item would break | This doesn't take Unbreakable into account
				if (stack.getItem() instanceof ItemArmor && stack.getItemDamage() + damage >= stack.getMaxDamage())
				{
					//Copy the old itemstack
					ItemStack newPiece = stack.copy();

					//Apply armor buff I guess
					newPiece.addAttributeModifier(SharedMonsterAttributes.ARMOR.getName(), PROTECTION_MODIFIER, slot);
					newPiece.addAttributeModifier(SharedMonsterAttributes.ARMOR_TOUGHNESS.getName(), TOUGHNESS_MODIFIER, slot);

					if (newPiece.getTagCompound() == null)
						newPiece.setTagCompound(rebornCompound);
					else
						NBTUtils.injectCompound("", newPiece.getTagCompound(), rebornCompound);

					//and set the current stack damage to 0
					newPiece.setItemDamage(0);

					//Set the new stack to the slot of the entity
					entity.setItemStackToSlot(slot, newPiece);

					//Play item resurrection sounds
					entity.world.playSound(null, entity.getPosition(), SoundEvents.ITEM_TOTEM_USE, SoundCategory.PLAYERS, 0.75F, 1);
					entity.world.playSound(null, entity.getPosition(), SoundEvents.BLOCK_ANVIL_PLACE, SoundCategory.PLAYERS, 0.5F, 1.25F);
				}
			}
		}
	}

}
