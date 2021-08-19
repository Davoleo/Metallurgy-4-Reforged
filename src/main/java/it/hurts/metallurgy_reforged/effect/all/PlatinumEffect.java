/*==============================================================================
 = Class: PlatinumEffect
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.effect.all;

import it.hurts.metallurgy_reforged.config.EffectsConfig;
import it.hurts.metallurgy_reforged.effect.BaseMetallurgyEffect;
import it.hurts.metallurgy_reforged.item.tool.IToolEffect;
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.render.font.FontColor;
import it.hurts.metallurgy_reforged.util.ItemUtils;
import it.hurts.metallurgy_reforged.util.Utils;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

public abstract class PlatinumEffect extends BaseMetallurgyEffect {

	public PlatinumEffect()
	{
		super(ModMetals.PLATINUM);
	}

	/**
	 * Only check for non-wearable items instead of all items
	 */
	@Override
	public int getLevel(EntityLivingBase entity)
	{
		return ItemUtils.isMadeOfMetal(metal, entity.getHeldItemMainhand().getItem(), IToolEffect.class) ? 1 : 0;
	}

	/**
	 * Makes items unbreakable when you equip them if they weren't before
	 */
	@SubscribeEvent
	public void onItemEquipped(LivingEquipmentChangeEvent event)
	{
		if (!canBeApplied(event.getEntityLiving()))
			return;

		ItemStack stack = event.getTo();
		NBTTagCompound compound = stack.getTagCompound();
		if (compound == null)
			compound = new NBTTagCompound();

		//Add unbreakable Tag
		if (!compound.hasKey("Unbreakable"))
			compound.setInteger("Unbreakable", 1);

		stack.setTagCompound(compound);
	}

	/**
	 * No need to create the config field automatically (manually take the correct one)
	 */
	@Override
	public boolean isEnabled()
	{
		if (metal == null)
			return false;
		return EffectsConfig.platinumEffectAll;
	}

	/**
	 * Override Automatic Tooltip with correct data from custom effect lang key
	 */
	@Override
	public Pair<String, String> getTooltip()
	{
		String effectLangKey = "tooltip.metallurgy.effect." + metal.toString() + "_all";
		String name = FontColor.encodeColor(metal.getStats().getColorHex()) + Utils.localizeEscapingCustomSequences(effectLangKey);
		String description = Utils.localizeEscapingCustomSequences(effectLangKey + ".tooltip");
		return ImmutablePair.of(name, description);
	}

}
