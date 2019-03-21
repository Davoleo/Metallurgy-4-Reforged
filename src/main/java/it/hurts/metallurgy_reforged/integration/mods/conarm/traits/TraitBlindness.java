package it.hurts.metallurgy_reforged.integration.mods.conarm.traits;

import c4.conarm.lib.traits.AbstractArmorTrait;
import it.hurts.metallurgy_reforged.integration.mods.conarm.MetallurgyConArmorStats;
import net.minecraft.init.MobEffects;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;

public class TraitBlindness extends AbstractArmorTrait{
	
	public TraitBlindness() {
		super("blindness", TextFormatting.BLACK);
	}
	
	@SubscribeEvent
	public void onArmorTick(PlayerTickEvent event){
		if(MetallurgyConArmorStats.isThatArmorTrait(event.player, "blindness")) {
			event.player.removePotionEffect(MobEffects.BLINDNESS);
		}
	}

}
