/*==============================================================================
 = Class: QuicksilverArmorEffect
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2020.
 =============================================================================*/

package it.hurts.metallurgy_reforged.effect.armor;

import it.hurts.metallurgy_reforged.effect.BaseMetallurgyEffect;
import it.hurts.metallurgy_reforged.effect.EnumEffectCategory;
import it.hurts.metallurgy_reforged.handler.LivingEventHandler;
import it.hurts.metallurgy_reforged.integration.tic.IntegrationTIC;
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.util.EventUtils;
import it.hurts.metallurgy_reforged.util.ModChecker;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.living.LivingEvent;

@Deprecated
public class QuicksilverArmorEffect extends BaseMetallurgyEffect {

	public QuicksilverArmorEffect() {
        super(ModMetals.QUICKSILVER);
    }

    @Override
    public EnumEffectCategory getCategory() {
        return EnumEffectCategory.ARMOR;
    }

    @Override
    public LivingEventHandler<? extends LivingEvent>[] getEvents() {
        return new LivingEventHandler[0];
    }

    public void livingEvent(LivingEvent livingEvent) {
        if (livingEvent instanceof LivingEntityUseItemEvent) {
            LivingEntityUseItemEvent event = ((LivingEntityUseItemEvent) livingEvent);

            if (event.getEntityLiving() instanceof EntityPlayer) {
                EntityPlayer player = ((EntityPlayer) event.getEntityLiving());

				if (EventUtils.isEntityWearingArmor(player, metal))
				{
					apply(event);
				}
			}
		}
	}

	private static boolean isItemBlacklisted(Item item)
	{
		return false;
	}

	public static void apply(LivingEntityUseItemEvent event)
	{
		ItemStack stack = event.getItem();
		Item item = stack.getItem();
		if (isItemBlacklisted(item))
			return;

		int duration = event.getDuration();

		if (ModChecker.isTConLoaded && IntegrationTIC.isCrossbow(item))
		{
			if (event instanceof LivingEntityUseItemEvent.Tick)
			{
				event.setDuration(duration - 1);
			}
		}
		else if (event instanceof LivingEntityUseItemEvent.Start)
		{
			if (item.getItemUseAction(stack) == EnumAction.BOW)
			{
				event.setDuration(duration - 7);
			}
			else
			{
				event.setDuration(Math.round(duration / 2F));
			}
		}
	}

}
