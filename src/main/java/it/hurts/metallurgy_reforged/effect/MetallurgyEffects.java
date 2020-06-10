/*
 * -------------------------------------------------------------------------------------------------------
 * Class: MetallurgyEffects
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 * Copyright (c) 2020.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.effect;

import it.hurts.metallurgy_reforged.config.ArmorEffectsConfig;
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.model.EnumTools;
import it.hurts.metallurgy_reforged.util.EventUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.entity.living.LivingAttackEvent;

import java.util.HashSet;
import java.util.Set;

public class MetallurgyEffects {

	public static Set<AbstractMetallurgyEffect> effects = new HashSet<>();

	//Amordrine Armor (Strenght II)
	public static final AbstractMetallurgyEffect amordrineEffect = new ArmorPotionEffect(
			ModMetals.AMORDRINE, new PotionEffect(MobEffects.STRENGTH, 60, 1, false, false)) {
		@Override
		public boolean isEnabled()
		{
			return ArmorEffectsConfig.amordrineArmorEffect;
		}
	};

	//Angmallen Armor (Luck I for Vampirism)
	public static final AbstractMetallurgyEffect angmallenEffect = new ArmorPotionEffect(
			ModMetals.ANGMALLEN, new PotionEffect(MobEffects.LUCK, 80, 0, false, false)) {
		@Override
		public boolean isEnabled()
		{
			return ArmorEffectsConfig.angmallenArmorEffect;
		}
	};

	//Astral Silver Armor (Jump Boost)
	public static final AbstractMetallurgyEffect astralSilverEffect = new ArmorPotionEffect(
			ModMetals.ASTRAL_SILVER, new PotionEffect(MobEffects.JUMP_BOOST, 100, 1, false, false)) {
		@Override
		public boolean isEnabled()
		{
			return ArmorEffectsConfig.astralSilverArmorEffect;
		}
	};

	//Carmot Armor (Haste I)
	public static final AbstractMetallurgyEffect carmotEffect = new ArmorPotionEffect(
			ModMetals.CARMOT, new PotionEffect(MobEffects.HASTE, 60, 0, false, false)) {
		@Override
		public boolean isEnabled()
		{
			return ArmorEffectsConfig.carmotArmorEffect;
		}
	};

	//Celenegil Armor (Resistence)
	public static final CelenegilArmorEffect celenegilEffect = new CelenegilArmorEffect();

	//Kalendrite Armor (Strenght I)
	public static final AbstractMetallurgyEffect kalendriteEffect = new ArmorPotionEffect(
			ModMetals.KALENDRITE, new PotionEffect(MobEffects.STRENGTH, 60, 0, false, false)) {
		@Override
		public boolean isEnabled()
		{
			return ArmorEffectsConfig.kaledriteArmorEffect;
		}
	};

	// TODO: 10/06/2020 MOVE THESE ANONYMOUS CLASSES IN THEIR OWN FILES
	//Vulcanite Armor (Fire Immunity) //Removes Fire Render
	public static final AbstractMetallurgyEffect vulcaniteEffect = new AbstractMetallurgyEffect(ModMetals.VULCANITE) {
		@Override
		protected boolean isEnabled()
		{
			return ArmorEffectsConfig.vulcaniteArmorEffect;
		}

		@Override
		protected boolean isToolEffect()
		{
			return false;
		}

		@Override
		protected EnumTools getToolClass()
		{
			return null;
		}

		@Override
		public void onPlayerTick(EntityPlayer player)
		{
			if (EventUtils.isPlayerWearingArmor(player, metal) && player.isBurning())
			{
				player.extinguish();
			}
		}

		@Override
		public void onPlayerAttacked(LivingAttackEvent event)
		{
			if (event.getEntity() instanceof EntityPlayer && event.getSource().isFireDamage() && EventUtils.isPlayerWearingArmor((EntityPlayer) event.getEntity(), metal))
				event.setCanceled(true);
		}
	};

	//Prometheum Armor (No potion, need to implement a new Effect)
	public static final AbstractMetallurgyEffect prometheumEffect = new AbstractMetallurgyEffect(ModMetals.PROMETHEUM) {
		@Override
		protected boolean isEnabled()
		{
			return ArmorEffectsConfig.prometheumArmorEffect;
		}

		@Override
		protected boolean isToolEffect()
		{
			return false;
		}

		@Override
		protected EnumTools getToolClass()
		{
			return null;
		}

		@Override
		public void onPlayerTick(EntityPlayer player)
		{
			if (EventUtils.isPlayerWearingArmor(player, metal) && player.isPotionActive(MobEffects.POISON))
			{
				player.removePotionEffect(MobEffects.POISON);
			}
		}
	};

	//Shadow Iron Armor (No Blindness)
	public static final AbstractMetallurgyEffect shadowIronEffect = new AbstractMetallurgyEffect(ModMetals.SHADOW_IRON) {
		@Override
		protected boolean isEnabled()
		{
			return true;
		}

		@Override
		protected boolean isToolEffect()
		{
			return false;
		}

		@Override
		protected EnumTools getToolClass()
		{
			return null;
		}

		@Override
		public void onPlayerTick(EntityPlayer player)
		{
			if (EventUtils.isPlayerWearingArmor(player, metal) && player.isPotionActive(MobEffects.BLINDNESS))
			{
				player.removePotionEffect(MobEffects.BLINDNESS);
			}
		}
	};


}
