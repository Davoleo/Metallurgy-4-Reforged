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
import it.hurts.metallurgy_reforged.material.MetalStats;
import it.hurts.metallurgy_reforged.material.ModMetals;
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
import net.minecraft.util.SoundCategory;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.UseHoeEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nonnull;

public class HaderothEffect extends BaseMetallurgyEffect {

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
	public int getLevel(EntityLivingBase entity)
	{
		return super.getLevel(entity);
	}

	@SubscribeEvent
	public void applyToolMetamorphosis(BlockEvent.BreakEvent event)
	{
		applyItemMetamorphosis(event.getPlayer().getHeldItemMainhand(), event.getPlayer());
	}

	@SubscribeEvent
	public void applyWeaponMetamorphosis(AttackEntityEvent event)
	{
		applyItemMetamorphosis(event.getEntityPlayer().getHeldItemMainhand(), event.getEntityPlayer());
	}

	@SubscribeEvent
	public void applyHoeMetamorphosis(UseHoeEvent event)
	{
		applyItemMetamorphosis(event.getCurrent(), event.getEntityPlayer());
	}

	private void applyItemMetamorphosis(ItemStack stack, EntityPlayer destroyingAgent)
	{
		if (ItemUtils.isMadeOfMetal(metal, stack.getItem()) || TartariteEffect.getParagonMetal(stack) == metal)
		{

			int remainingUses = stack.getMaxDamage() - stack.getItemDamage();
			//If tool isn't about to be broken return out
			if (remainingUses > 1)
				return;

			//If tool was already reborn
			if (stack.getTagCompound() != null && stack.getTagCompound().getBoolean("reborn"))
				return;

			if (stack.getTagCompound() == null)
				stack.setTagCompound(rebornCompound);
			else
				NBTUtils.injectCompound("", stack.getTagCompound(), rebornCompound);

			//Restore full stack durability
			stack.setItemDamage(0);

			destroyingAgent.world.playSound(null, destroyingAgent.getPosition(), SoundEvents.BLOCK_ANVIL_PLACE, SoundCategory.PLAYERS, 0.75F, 1.25F);

		}
	}

	@SubscribeEvent
	public void applyWeaponAttackBoost(LivingHurtEvent event)
	{
		Entity source = event.getSource().getImmediateSource();
		if (source instanceof EntityLivingBase)
		{
			EntityLivingBase attacker = ((EntityLivingBase) source);
			if (getLevel(attacker) < 10)
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

		if (getLevel(player) >= 10)
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

		if (getLevel(entity) == 0)
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

				MetalStats stats;
				//Make sure the item we're inspecting is either haderoth or tartarite infused with haderoth
				if (ItemUtils.isMadeOfMetal(metal, stack.getItem()))
					stats = metal.getStats();
				else if (TartariteEffect.getParagonMetal(stack) == metal)
					stats = ModMetals.TARTARITE.getStats();
				else
					continue;

				//If the item was already reborn
				if (stack.getTagCompound() != null && stack.getTagCompound().getBoolean("reborn"))
					continue;

				//Check if the item would break | This doesn't take Unbreakable into account
				if (stack.getItem() instanceof ItemArmor && stack.getItemDamage() + damage * 3 >= stack.getMaxDamage())
				{
					//Copy the old itemstack
					ItemStack newPiece = stack.copy();

					int newProtection = stats.getArmorStats().getDamageReduction()[slot.getIndex()] + 2;
					float newToughness = stats.getArmorStats().getToughness() + 0.5F;
					final AttributeModifier PROTECTION_MODIFIER = new AttributeModifier("Haderoth Armor Protection Buff", newProtection, 0);
					final AttributeModifier TOUGHNESS_MODIFIER = new AttributeModifier("Haderoth Armor Toughness Buff", newToughness, 0);

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
