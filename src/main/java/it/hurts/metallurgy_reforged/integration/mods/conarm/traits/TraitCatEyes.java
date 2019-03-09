package it.hurts.metallurgy_reforged.integration.mods.conarm.traits;

import c4.conarm.lib.traits.AbstractArmorTrait;
import it.hurts.metallurgy_reforged.integration.mods.conarm.MetallurgyConArmorStats;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;

public class TraitCatEyes extends AbstractArmorTrait{
	
	private boolean flag = false;

	public TraitCatEyes() {
		super("cat_eyes", TextFormatting.GREEN);
	}

	@SubscribeEvent
	public void onArmorTick(PlayerTickEvent event){	
		if(MetallurgyConArmorStats.isThatArmorTrait(event.player, "cat_eyes")) {
			event.player.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 220, 0, false, false));
			flag = true;
		}
		
		if(flag && event.player.isPotionActive(MobEffects.NIGHT_VISION) && !MetallurgyConArmorStats.isThatArmorTrait(event.player, "cat_eyes")) {
			flag = false;
			event.player.removeActivePotionEffect(MobEffects.NIGHT_VISION);
		}
			
	}

}
