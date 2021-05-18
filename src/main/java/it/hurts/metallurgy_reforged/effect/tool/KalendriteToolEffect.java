/*==============================================================================
 = Class: KalendriteToolEffect
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.effect.tool;

import it.hurts.metallurgy_reforged.effect.BaseMetallurgyEffect;
import it.hurts.metallurgy_reforged.effect.EnumEffectCategory;
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.util.Utils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.SPacketUpdateHealth;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nonnull;

public class KalendriteToolEffect extends BaseMetallurgyEffect {

    public KalendriteToolEffect()
    {
        super(ModMetals.KALENDRITE);
    }

    @Nonnull
    @Override
    public EnumEffectCategory getCategory()
    {
        return EnumEffectCategory.TOOL;
    }

    @SubscribeEvent(priority = EventPriority.LOW)
    public void blockBreakHeal(BlockEvent.BreakEvent event)
    {
        EntityPlayer player = event.getPlayer();

        if (!canBeApplied(player) || event.isCanceled())
            return;

        if (player.getHealth() < player.getMaxHealth())
        {
            int durabilityDelta = Math.round((player.getMaxHealth() - player.getHealth()) / 2);

            ItemStack toolStack = event.getPlayer().getHeldItemMainhand();
            toolStack.setItemDamage(toolStack.getItemDamage() + durabilityDelta);

            if (player instanceof EntityPlayerMP)
            {
                player.heal(1F);
                //-> Client-side heart blinking
                ((EntityPlayerMP) player).connection.sendPacket(new SPacketUpdateHealth(player.getHealth(), player.getFoodStats().getFoodLevel(), player.getFoodStats().getSaturationLevel()));

                //Old
                //ClientProxy.client.getConnection().getPlayerInfo(player.getName()).setHealthBlinkTime(ClientProxy.client.ingameGUI.getUpdateCounter() + 10);
            }

            Utils.repeat(16, () -> spawnParticle(player, 2.5F, true, 5));
        }

    }

}
