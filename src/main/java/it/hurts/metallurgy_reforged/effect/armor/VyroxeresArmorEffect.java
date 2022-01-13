/*==============================================================================
 = Class: VyroxeresArmorEffect
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.effect.armor;

import com.google.common.collect.Sets;
import it.hurts.metallurgy_reforged.effect.BaseMetallurgyEffect;
import it.hurts.metallurgy_reforged.effect.EnumEffectCategory;
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.util.IntBiFunction;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.event.entity.living.PotionEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nonnull;
import java.util.Set;
import java.util.UUID;

public class VyroxeresArmorEffect extends BaseMetallurgyEffect {

	private static final UUID PROTECTION_MODIFIER_UUID = UUID.fromString("91AEAB56-376B-1298-935B-2F7F68070333");
	private final IntBiFunction<AttributeModifier> generateProtectionMod = (stacks, level) ->
			new AttributeModifier(PROTECTION_MODIFIER_UUID, "Vyroxeres_Protection_Mod", stacks * (level <= 2 ? level : level - 0.5), 0);

	public VyroxeresArmorEffect()
	{
		super(ModMetals.VYROXERES);
	}

	@Nonnull
	@Override
	public EnumEffectCategory getCategory()
	{
		return EnumEffectCategory.ARMOR;
	}

	@SubscribeEvent
	public void potionProtectionBuff(PotionEvent event)
	{
		if (event instanceof PotionEvent.PotionApplicableEvent)
			return;

		String eventType;
		if (event instanceof PotionEvent.PotionRemoveEvent)
			eventType = "REMOVAL";
		else if (event instanceof PotionEvent.PotionExpiryEvent)
			eventType = "EXPIRY";
		else if (event instanceof PotionEvent.PotionAddedEvent)
			eventType = "ADDITION";
		else
			eventType = "BRUH, HOW IS THIS POSSIBLE ASLFKJSFKLAJ";

		System.out.println("Event Type: " + eventType);

		EntityLivingBase entity = event.getEntityLiving();

		//Get the protection attribute instance
		IAttributeInstance userArmor = entity.getEntityAttribute(SharedMonsterAttributes.ARMOR);

		int level = getLevel(entity);
		if (level == 0)
		{
			//If the user has no armor -> make sure protection is removed
			//This may not occur instantly, but it's a good compromise
			userArmor.removeModifier(PROTECTION_MODIFIER_UUID);
			return;
		}

		//The Effect that triggered the event
		PotionEffect eventEffect = event.getPotionEffect();

		int stacks = 0;

		if (event instanceof PotionEvent.PotionAddedEvent)
		{
			PotionEffect oldPotion = ((PotionEvent.PotionAddedEvent) event).getOldPotionEffect();

			//Event effect can't be null if PotionAddedEvent was triggered as the method getPotionEffect is annotated as @Nonnull
			assert eventEffect != null;

			//If the potion wasn't there before or the amplifier is different
			if (oldPotion == null || oldPotion.getAmplifier() != eventEffect.getAmplifier())
			{
				userArmor.removeModifier(PROTECTION_MODIFIER_UUID);
				Set<PotionEffect> currentPotions = Sets.newHashSet(entity.getActivePotionEffects());
				//Add event potion to the current potion list (when the event is called the potion is not yet in the collection)
				currentPotions.add(eventEffect);
				//Remove old potion if it's a level increase
				if (oldPotion != null)
					currentPotions.remove(oldPotion);
				stacks = calculatePotionStacks(currentPotions);
			}
		}
		else if (event instanceof PotionEvent.PotionRemoveEvent || event instanceof PotionEvent.PotionExpiryEvent)
		{
			if (eventEffect != null)
			{
				userArmor.removeModifier(PROTECTION_MODIFIER_UUID);
				Set<PotionEffect> effects = Sets.newHashSet(entity.getActivePotionEffects());
				effects.remove(eventEffect);
				stacks = calculatePotionStacks(effects);
			}
			System.out.println("Remove Event Effect: " + (eventEffect != null ? eventEffect.toString() : "NULL"));
		}


		//If the player has any potion effect Increase PROTECTION POWEEEEEEEEEEEEEEEEER
		if (stacks > 0)
		{
			AttributeModifier protMod = generateProtectionMod.apply(stacks, level);
			userArmor.applyModifier(protMod);
			System.out.println(protMod.getAmount());
		}
	}

	private int calculatePotionStacks(Set<PotionEffect> effects)
	{
		return effects.stream()
				.reduce(0,
						(stackHeap, effect) -> stackHeap + (effect.getAmplifier() + 1),
						Integer::sum
				);
	}

	@SubscribeEvent
	public void equipmentUpdated(LivingEquipmentChangeEvent event)
	{
		if (event.getSlot().getSlotType() == EntityEquipmentSlot.Type.ARMOR)
		{
			if (!canBeApplied(event.getEntityLiving()))
				event.getEntityLiving().getEntityAttribute(SharedMonsterAttributes.ARMOR).removeModifier(PROTECTION_MODIFIER_UUID);
		}

	}

}
