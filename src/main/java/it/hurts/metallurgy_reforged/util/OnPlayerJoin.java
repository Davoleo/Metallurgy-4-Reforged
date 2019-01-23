package it.hurts.metallurgy_reforged.util;

import it.hurts.metallurgy_reforged.config.GeneralConfig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/***************************
 *
 * Author : ItHurtsLikeHell
 * Project: Metallurgy-5
 * Date   : 30 nov 2018
 * Time   : 23:49:45
 *
 ***************************/
public class OnPlayerJoin {

	private final TextComponentString GITHUB_REPO = new TextComponentString(Utils.localize("util.github_repo_url"));

	@SubscribeEvent
	public void PlayerJoin(EntityJoinWorldEvent event) {
		if(!(event.getEntity() instanceof EntityPlayer)) {
			return;
		}
		EntityPlayer player = (EntityPlayer) event.getEntity();
		GITHUB_REPO.getStyle().setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, GITHUB_REPO.getText().substring(2, 50)));

		if(!event.getEntity().world.isRemote && GeneralConfig.alphaWarning) {
			player.sendMessage(new TextComponentString(Utils.localize("util.world_join_message.1")));
			player.sendMessage(new TextComponentString(Utils.localize("util.world_join_message.2")));
			player.sendMessage(new TextComponentString(Utils.localize("util.world_join_message.3")));
			player.sendMessage(new TextComponentString(Utils.localize("util.world_join_message.4")));
			player.sendMessage(GITHUB_REPO);

		}
	}
}
