package it.hurts.metallurgy_reforged.util;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;
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
	
	@SubscribeEvent
	public void PlayerJoin(EntityJoinWorldEvent event) {
		if(!(event.getEntity() instanceof EntityPlayer)) {
			return;
		}
		EntityPlayer player = (EntityPlayer) event.getEntity();
		if(!event.getEntity().world.isRemote) {
			player.sendMessage(new TextComponentString("\u00A74\u00A7lMetallurgy 4 : Reforged "+"\nFurther integration with common mods (like Tinkers' Construct and JEI) is not currently available \u00A7lbut planned too."));
			player.sendMessage(new TextComponentString("\nIf you want info / see Alloyer crafting, go to the github wiki (\u00A71\u00A7oDavoleo / Metallurgy-4-Reforged / wiki\u00A7r)"));
		}
	}
}
