/*==============================================================================
 = Class: VyroxeresArmorEffect
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
import it.hurts.metallurgy_reforged.util.IntBiFunction;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraftforge.event.entity.living.PotionEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nonnull;
import java.util.UUID;

public class VyroxeresArmorEffect extends BaseMetallurgyEffect {

	private static final UUID PROTECTION_MODIFIER_UUID = UUID.fromString("91AEAB56-376B-1298-935B-2F7F68070333");
	private final IntBiFunction<AttributeModifier> generateProtectionMod = (potionCount, level) ->
			new AttributeModifier(PROTECTION_MODIFIER_UUID, "Vyroxeres_Protection_Mod", Math.min(potionCount * 2, 1 + level * 2), 0);

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
		EntityLivingBase entity = event.getEntityLiving();
		int level = getLevel(entity);
		if (level == 0)
			return;

		//Generate The new correct modifier depending on the count of active potion effects
		int potionCount = entity.getActivePotionEffects().size();
		AttributeModifier mod = generateProtectionMod.apply(potionCount, level);

		//Get the protection attribute instance
		IAttributeInstance userArmor = entity.getEntityAttribute(SharedMonsterAttributes.ARMOR);

		//If the player had already an old protection modifier (hasModifier only takes the id from the passed mod)
		if (userArmor.hasModifier(mod))
			userArmor.removeModifier(PROTECTION_MODIFIER_UUID);

		//If the player has any potion effect Increase PROTECTION POWEEEEEEEEEEEEEEEEER
		if (potionCount > 0)
			userArmor.applyModifier(mod);

	}

}
