/**
 * 
 */
package it.hurts.metallurgy_reforged.integration.mods.conarm.traits;

import c4.conarm.lib.traits.AbstractArmorTrait;
import it.hurts.metallurgy_reforged.integration.mods.conarm.MetallurgyConArmorStats;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/***************************
 *
 * Author : ItHurtsLikeHell
 * Project: Metallurgy-4-Reforged
 * Date   : 4 mar 2019
 * Time   : 19:06:32
 *
 ****************************/
public class TraitQuickly extends AbstractArmorTrait{

	public TraitQuickly() {
		super("quickly", TextFormatting.DARK_AQUA);
	}
	
//	Dare check per controllare se l'armatura è di quick ed creare un subevent controllando che la con sia loaddata

	@SubscribeEvent
	public static void increaseVelocity(LivingEntityUseItemEvent.Start ev){
		if(ev.getEntityLiving() instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) ev.getEntityLiving();
			if(MetallurgyConArmorStats.isThatArmorTrait(player, "quickly")) {
				if(ev.getItem().getItem().getItemUseAction(ev.getItem()) == EnumAction.BOW)
					ev.setDuration(ev.getDuration() - 6);
				else
					ev.setDuration(Math.round(ev.getDuration() / 2F));
			}
		}
	}
	
}
