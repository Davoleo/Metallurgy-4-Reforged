package hurts.metallurgy_5.armor;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class ArmorEffect{
	
	 public void EnemyPotion(LivingHurtEvent event) {
		 EntityPlayer attackingPlayer =	(EntityPlayer) event.getEntity();
		 
		 attackingPlayer.addPotionEffect(new PotionEffect(MobEffects.POISON, 3, 2));
	 }
	
}
