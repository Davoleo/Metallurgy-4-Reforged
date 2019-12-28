/*
 * -------------------------------------------------------------------------------------------------------
 * Class: PlayerHandler
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2019.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.handler;

import it.hurts.metallurgy_reforged.config.GeneralConfig;
import it.hurts.metallurgy_reforged.util.Utils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class PlayerHandler {

	private static final TextComponentString GITHUB_REPO = new TextComponentString(Utils.localizeIgnoreFormat("util.github_repo_url"));

	@SubscribeEvent
	public static void onPlayerJoin(EntityJoinWorldEvent event)
	{
		if (!(event.getEntity() instanceof EntityPlayer))
		{
			return;
		}
		EntityPlayer player = (EntityPlayer) event.getEntity();

		if (!event.getEntity().world.isRemote && GeneralConfig.warning)
		{
			GITHUB_REPO.getStyle().setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, GITHUB_REPO.getText())).setColor(TextFormatting.BLUE);

			player.sendMessage(new TextComponentString(Utils.localize("util.world_join_message.1")));

			player.sendMessage(new TextComponentString(Utils.localize("util.world_join_message.3")));
			player.sendMessage(GITHUB_REPO);

			player.sendMessage(new TextComponentString(Utils.localize("util.world_join_message.4")));
		}
	}

}
