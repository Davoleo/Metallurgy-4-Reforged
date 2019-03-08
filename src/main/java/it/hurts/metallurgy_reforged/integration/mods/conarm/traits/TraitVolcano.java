package it.hurts.metallurgy_reforged.integration.mods.conarm.traits;

import c4.conarm.lib.traits.AbstractArmorTrait;
import it.hurts.metallurgy_reforged.integration.mods.conarm.MetallurgyConArmorStats;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;

public class TraitVolcano extends AbstractArmorTrait{

	public TraitVolcano() {
		super("volcano", TextFormatting.RED);
	}
	
	@SubscribeEvent
	public void onArmorTick(PlayerTickEvent event){	
		if(MetallurgyConArmorStats.isThatArmorTrait(event.player, "volcano"))
			event.player.extinguish();
	}

}
