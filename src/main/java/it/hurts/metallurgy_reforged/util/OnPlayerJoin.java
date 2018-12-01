package it.hurts.metallurgy_reforged.util;

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

		if(!event.getEntity().world.isRemote) {
			player.sendMessage(new TextComponentString("\u00A74\u00A7lMetallurgy 4: Reforged \u00A7r\n\u00A76[Warning: this is an alpha version of the mod]" +
                    "\nFurther integration with common mods (like Tinkers' Construct and JEI) is not currently available \u00A7lbut planned."));
			player.sendMessage(new TextComponentString("\nIf you want more info/take a look at the machine recipes - go to the github repo wiki:"));
			player.sendMessage(GITHUB_REPO);
			player.sendMessage(new TextComponentString("Bug reports go on the github repo aswell"));

		}
	}
}
