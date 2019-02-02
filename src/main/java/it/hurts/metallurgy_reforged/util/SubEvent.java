package it.hurts.metallurgy_reforged.util;

import it.hurts.metallurgy_reforged.config.GeneralConfig;
import it.hurts.metallurgy_reforged.item.gadgets.gauntlet.util.GauntletEffect;
import it.hurts.metallurgy_reforged.item.gadgets.gauntlet.util.GauntletOperation;
import net.minecraftforge.common.MinecraftForge;

/***************************
 *
 * Author : ItHurtsLikeHell
 * Project: Metallurgy-4-Reforged
 * Date   : 05 gen 2019
 * Time   : 13:53:41
 *
 ***************************/
public class SubEvent {

	public static void init() {
		MinecraftForge.EVENT_BUS.register(new GauntletOperation());
		MinecraftForge.EVENT_BUS.register(new GauntletEffect());
		MinecraftForge.EVENT_BUS.register(new GeneralConfig.ChangeListener());
	}
	
}
