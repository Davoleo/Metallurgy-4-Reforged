/*
 * -------------------------------------------------------------------------------------------------------
 * Class: SwordEffectHandler
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2019.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.handler;

import it.hurts.metallurgy_reforged.config.ToolEffectsConfig;
import it.hurts.metallurgy_reforged.item.tool.EnumTools;
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.util.Utils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import scala.util.Random;

import java.util.ArrayList;
import java.util.UUID;

public class SwordEffectHandler {

	//	The speed sword modifier UUID
	private static final UUID SHADOW_STEEL_SWORD_MODIFIER_UUID =  UUID.fromString("9bfd3581-6559-468f-a5a5-66c46ff7b70c");
	private static final UUID DEEP_IRON_SWORD_MODIFIER_UUID =  UUID.fromString("8dfd3581-6559-468f-a5a5-66c46ff7b70b");

	private static final ItemStack DEEP_IRON_SWORD = new ItemStack(ModMetals.DEEP_IRON.getTool(EnumTools.SWORD));
	private static final ItemStack SHADOW_STEEL_SWORD = new ItemStack(ModMetals.SHADOW_STEEL.getTool(EnumTools.SWORD));

	@SubscribeEvent
	public static void onAttack(AttackEntityEvent event)
	{
		EntityPlayer player = event.getEntityPlayer();
		if (!player.world.isRemote) {

			Entity foe = event.getTarget();

//			Shadow Iron Sword (Blindness)
			if (player.getHeldItemMainhand().getItem() == ModMetals.SHADOW_IRON.getTool(EnumTools.SWORD) && ToolEffectsConfig.shadowIronSwordEffect) {

				EntityLivingBase foe2 = (EntityLivingBase) foe;
				if ((int) (Math.random() * 100) <= 25)
					foe2.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 100));
			}

//			Desichalkos Sword ( Give Random Effect to entity )
			if (player.getHeldItemMainhand().getItem() == ModMetals.DESICHALKOS.getTool(EnumTools.SWORD) && ToolEffectsConfig.desichalkosSwordEffect) {
				((EntityLivingBase) foe).addPotionEffect(new PotionEffect(Utils.getRandomEffect(), 80, 0));
			}

//			Vyroxeres Sword (Potion)
			if (player.getHeldItemMainhand().getItem() == ModMetals.VYROXERES.getTool(EnumTools.SWORD) && ToolEffectsConfig.vyroxeresSwordEffect) {
				if ((int) (Math.random() * 100) <= 25)
					((EntityLivingBase) foe).addPotionEffect(new PotionEffect(MobEffects.POISON, 100));
			}

//			Ignatius Sword (Fire Aspect)
			if (player.getHeldItemMainhand().getItem() == ModMetals.IGNATIUS.getTool(EnumTools.SWORD) && ToolEffectsConfig.shadowIronSwordEffect) {
				if ((int) (Math.random() * 100) <= 15)
					foe.setFire(5);
			}

//			Vulcanite Sword (Fire Aspect)
			if (player.getHeldItemMainhand().getItem() == ModMetals.VULCANITE.getTool(EnumTools.SWORD) && ToolEffectsConfig.vulcaniteSwordEffect) {

				if ((int) (Math.random() * 100) <= 30)
					foe.setFire(5);
			}

//			Tartarite Sword (Withering II)
			if (player.getHeldItemMainhand().getItem() == ModMetals.TARTARITE.getTool(EnumTools.SWORD) && ToolEffectsConfig.tartariteSwordEffect) {

				if ((int) (Math.random() * 100) <= 20)
					((EntityLivingBase) foe).addPotionEffect(new PotionEffect(MobEffects.WITHER, 60, 1, false, false));
			}

//			Mithril Sword (Give Glowing to entity Hitted)
			if (player.getHeldItemMainhand().getItem() == ModMetals.MITHRIL.getTool(EnumTools.SWORD)) {
				if ((int) (Math.random() * 100) <= 50)
					((EntityLivingBase) foe).addPotionEffect(new PotionEffect(MobEffects.GLOWING, 200, 1, false, false));
			}

//			Kalendrite sword (Regeneration)
			if (player.getHeldItemMainhand().getItem() == ModMetals.KALENDRITE.getTool(EnumTools.SWORD) && ToolEffectsConfig.kalendriteSwordEffect) {
				if ((int) (Math.random() * 100) <= 30)
					player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 100, 1, false, false));
			}

//			Ceruclase Sword (Give slowness)
			if (player.getHeldItemMainhand().getItem() == ModMetals.CERUCLASE.getTool(EnumTools.SWORD) && ToolEffectsConfig.ceruclaseSwordEffect) {

				if ((int) (Math.random() * 100) <= 25)
					((EntityLivingBase) foe).addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 70, 1, false, false));
			}
		}
	}

	@SubscribeEvent
	public static void onEntityDeath(LivingDeathEvent event) {
		Entity attacker = event.getSource().getImmediateSource();
		if(attacker instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) attacker;

//			Celenegil Sword ( Give Speed and Strenght on entity kill )
			if(player.getHeldItemMainhand().getItem() == ModMetals.CELENEGIL.getTool(EnumTools.SWORD) &&
					(player.isPotionActive(MobEffects.STRENGTH) ? player.getActivePotionEffect(MobEffects.STRENGTH).getDuration() < 8:true &&
							player.isPotionActive(MobEffects.SPEED) ? player.getActivePotionEffect(MobEffects.SPEED).getDuration() < 8:true)) {

				player.addPotionEffect(new PotionEffect(MobEffects.SPEED, 140, 0, false, false));
				player.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 140, 0, false, false));
			}

//			Orichalcum Sword ( Give Strenght on entity kill )
			if (player.getHeldItemMainhand().getItem() == ModMetals.ORICHALCUM.getTool(EnumTools.SWORD) && (!player.isPotionActive(MobEffects.STRENGTH) || player.getActivePotionEffect(MobEffects.STRENGTH).getDuration() < 8))
				player.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 140, 0, false, false));
		}
	}

//	Midasium Effect
	@SubscribeEvent
    public static void duplicationSwordEffect(LivingDropsEvent ev)
    {

        DamageSource source = ev.getSource();
        Entity entity = source.getTrueSource();

        if(entity instanceof EntityPlayer)
        {
            EntityPlayer pl = (EntityPlayer) entity;

            if(pl.getHeldItemMainhand().isItemEqualIgnoreDurability(new ItemStack(ModMetals.MIDASIUM.getTool(EnumTools.SWORD)))&& !(ev.getEntity() instanceof EntityPlayer)){
	            ArrayList<EntityItem> drops = new ArrayList<>();

//            	Duplica il drop
	            if(!pl.isCreative()) {
	            	 if((int) (Math.random() * 100) <= 50) {
					        for(EntityItem item : ev.getDrops()){
					            EntityItem clone = new EntityItem(item.world, item.posX, item.posY, item.posZ, item.getItem());
					            drops.add(clone);
					        }
				            ev.getDrops().addAll(drops);
			            }
	            } else {
	            	for(EntityItem item : ev.getDrops()){
			            EntityItem clone = new EntityItem(item.world, item.posX, item.posY, item.posZ, item.getItem());
			            drops.add(clone);
			        }
		            ev.getDrops().addAll(drops);
	            }
            }
        }
    }

//	Sanguinite Sword (Vampirism)
	@SubscribeEvent
	public static void entityHurtEvent(LivingHurtEvent event)
	{
		//the entity that damaged the event entity
		Entity source = event.getSource().getImmediateSource();
		if(source instanceof EntityPlayer)
		{

			//the player that damaged the event entity
			EntityPlayer pl = (EntityPlayer) source;

			if(pl.getHeldItemMainhand().isItemEqualIgnoreDurability(new ItemStack(ModMetals.SANGUINITE.getTool(EnumTools.SWORD)))) {
				{
					//check if the player is missing hearts.
					if(pl.getHealth() < pl.getMaxHealth())
					{

						int luck_level = Math.round(pl.getLuck());
						//percentage to get healed based on the luck of the player (example: luck 0 = 15%,luck 1 = 20%...)
						int percentage = 15 + (luck_level * 5);
						if(new Random().nextInt(100) < percentage)
						{
							//the heal Amount ,that is the 10% of the damage
							float healAmount = event.getAmount() * 0.15F;
							if(pl.getHealth() + healAmount >= pl.getMaxHealth())
								healAmount = 0;
							//set the player health
							pl.setHealth(pl.getHealth() + healAmount);
						}
					}
				}
			}
		}
	}

	@SubscribeEvent
	public static void onSwordTick(TickEvent.PlayerTickEvent event)
	{
		if (event.phase == TickEvent.Phase.START)
		{
			EntityPlayer pl = event.player;
			ItemStack stack = pl.getHeldItemMainhand();
			IAttributeInstance attackSpeedInstance = pl.getEntityAttribute(SharedMonsterAttributes.ATTACK_SPEED);

			if (ToolEffectsConfig.shadowSteelSwordEffect && stack.isItemEqualIgnoreDurability(SHADOW_STEEL_SWORD)) {

				float percentage = Utils.getLightArmorPercentage(pl, 50F);
				//calculate the Speed to add to the sword
				double added_speed = attackSpeedInstance.getBaseValue() * percentage / 100F;
				//the modifier UUID
				AttributeModifier shadow_steel_modifier = new AttributeModifier(SHADOW_STEEL_SWORD_MODIFIER_UUID, "Shadow Steel Sword Modifier", added_speed, 0);
				//checks if player has the modifier
				if (attackSpeedInstance.getModifier(SHADOW_STEEL_SWORD_MODIFIER_UUID) == null) {
					//if not,add the modifier
					attackSpeedInstance.applyModifier(shadow_steel_modifier);
				} else if (attackSpeedInstance.getModifier(SHADOW_STEEL_SWORD_MODIFIER_UUID) != null && attackSpeedInstance.getModifier(SHADOW_STEEL_SWORD_MODIFIER_UUID).getAmount() != added_speed) {
					//if  player has already the modifier and there is a light change,this method will update the speed attack
					attackSpeedInstance.removeModifier(SHADOW_STEEL_SWORD_MODIFIER_UUID);
					attackSpeedInstance.applyModifier(shadow_steel_modifier);
				}

			} else if (attackSpeedInstance.getModifier(SHADOW_STEEL_SWORD_MODIFIER_UUID) != null) {
				//removes the modifier if player doesn't held the sword
				attackSpeedInstance.removeModifier(SHADOW_STEEL_SWORD_MODIFIER_UUID);
			}

			if (ToolEffectsConfig.deepIronSwordEffect) {
				if (stack.isItemEqualIgnoreDurability(DEEP_IRON_SWORD)) {
					AttributeModifier deep_iron_trait_modifier = new AttributeModifier(DEEP_IRON_SWORD_MODIFIER_UUID, "Deep Iron SwordTrait Modifier", 2.7, 0);
					if (pl.isInWater() && attackSpeedInstance.getModifier(DEEP_IRON_SWORD_MODIFIER_UUID) == null) {
						attackSpeedInstance.applyModifier(deep_iron_trait_modifier);
					} else {
						if (attackSpeedInstance.getModifier(DEEP_IRON_SWORD_MODIFIER_UUID) != null && !pl.isInWater())
							attackSpeedInstance.removeModifier(DEEP_IRON_SWORD_MODIFIER_UUID);
					}
				} else {
					if (attackSpeedInstance.getModifier(DEEP_IRON_SWORD_MODIFIER_UUID) != null)
						attackSpeedInstance.removeModifier(DEEP_IRON_SWORD_MODIFIER_UUID);
				}
			}
		}
	}

}
