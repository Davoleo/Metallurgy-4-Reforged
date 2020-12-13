/*==============================================================================
 = Class: DeepIronShadowSteelWeaponEffect
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2020.
 =============================================================================*/

package it.hurts.metallurgy_reforged.effect.tool;

import it.hurts.metallurgy_reforged.effect.BaseMetallurgyEffect;
import it.hurts.metallurgy_reforged.effect.EnumEffectCategory;
import it.hurts.metallurgy_reforged.material.Metal;
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.model.EnumTools;
import it.hurts.metallurgy_reforged.model.LivingEventHandler;
import it.hurts.metallurgy_reforged.util.Utils;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingEvent;

import java.util.UUID;

@Deprecated
public class DeepIronShadowSteelWeaponEffect extends BaseMetallurgyEffect {

    //The speed sword modifier UUID
    private final UUID SHADOW_STEEL_SWORD_MODIFIER_UUID = UUID.fromString("9bfd3581-6559-468f-a5a5-66c46ff7b70c");
    private final UUID DEEP_IRON_SWORD_MODIFIER_UUID = UUID.fromString("8dfd3581-6559-468f-a5a5-66c46ff7b70b");

    public DeepIronShadowSteelWeaponEffect(Metal metal) {
        super(metal);
    }

    @Override
    public EnumEffectCategory getCategory() {
        return EnumEffectCategory.WEAPON;
    }

    @Override
    public LivingEventHandler<? extends LivingEvent>[] getEvents() {
        return new LivingEventHandler[0];
    }

    public void onPlayerTick(EntityPlayer player) {
        ItemStack stack = player.getHeldItemMainhand();
        IAttributeInstance attackSpeedInstance = player.getEntityAttribute(SharedMonsterAttributes.ATTACK_SPEED);

        Item deepIronSword = null;
        Item shadowSteelSword = null;

        if (ModMetals.DEEP_IRON != null)
			deepIronSword = ModMetals.DEEP_IRON.getTool(EnumTools.SWORD);
		if (ModMetals.SHADOW_STEEL != null)
			shadowSteelSword = ModMetals.SHADOW_STEEL.getTool(EnumTools.SWORD);

		if (stack.getItem() == deepIronSword || stack.getItem() == shadowSteelSword)
		{
			if (metal == ModMetals.SHADOW_STEEL)
			{

				float percentage = Utils.getLightArmorPercentage(player, 50F);
				//calculate the Speed to add to the sword
				double added_speed = attackSpeedInstance.getBaseValue() * percentage / 100F;
				//the modifier UUID
				AttributeModifier shadow_steel_modifier = new AttributeModifier(SHADOW_STEEL_SWORD_MODIFIER_UUID, "Shadow Steel Sword Modifier", added_speed, 0);
				//checks if player has the modifier
				if (attackSpeedInstance.getModifier(SHADOW_STEEL_SWORD_MODIFIER_UUID) == null)
				{
					//if not,add the modifier
					attackSpeedInstance.applyModifier(shadow_steel_modifier);
				}
				else if (attackSpeedInstance.getModifier(SHADOW_STEEL_SWORD_MODIFIER_UUID) != null && attackSpeedInstance.getModifier(SHADOW_STEEL_SWORD_MODIFIER_UUID).getAmount() != added_speed)
				{
					//if  player has already the modifier and there is a light change,this method will update the speed attack
					attackSpeedInstance.removeModifier(SHADOW_STEEL_SWORD_MODIFIER_UUID);
					attackSpeedInstance.applyModifier(shadow_steel_modifier);
				}

			}
			else if (attackSpeedInstance.getModifier(SHADOW_STEEL_SWORD_MODIFIER_UUID) != null)
			{
				//removes the modifier if player doesn't held the sword
				attackSpeedInstance.removeModifier(SHADOW_STEEL_SWORD_MODIFIER_UUID);
			}

			if (metal == ModMetals.DEEP_IRON)
			{
				AttributeModifier deep_iron_trait_modifier = new AttributeModifier(DEEP_IRON_SWORD_MODIFIER_UUID, "Deep Iron SwordTrait Modifier", 2.7, 0);
				if (player.isInWater() && attackSpeedInstance.getModifier(DEEP_IRON_SWORD_MODIFIER_UUID) == null)
				{
					attackSpeedInstance.applyModifier(deep_iron_trait_modifier);
				}
				else
				{
					if (attackSpeedInstance.getModifier(DEEP_IRON_SWORD_MODIFIER_UUID) != null && !player.isInWater())
						attackSpeedInstance.removeModifier(DEEP_IRON_SWORD_MODIFIER_UUID);
				}
			}
			else
			{
				if (attackSpeedInstance.getModifier(DEEP_IRON_SWORD_MODIFIER_UUID) != null)
					attackSpeedInstance.removeModifier(DEEP_IRON_SWORD_MODIFIER_UUID);
			}
		}
	}

}
