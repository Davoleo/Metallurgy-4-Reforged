package it.hurts.metallurgy_5.util;

import it.hurts.metallurgy_5.Metallurgy_5;
import it.hurts.metallurgy_5.item.armor.ModArmors;
import it.hurts.metallurgy_5.item.tool.ModTools;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;

import java.util.List;

/***************************
 *
 * Author : ItHurtsLikeHell
 * Project: Metallurgy-5
 * Date   : 28 ago 2018
 * Time   : 18:24:07
 *
 ***************************/

@EventBusSubscriber(modid=Metallurgy_5.MODID)
public class EventHandler {

	@SubscribeEvent
	public static void glowingArmorEffect(PlayerTickEvent event) {
		int radius=32, max=0;
		boolean isArmored=false;

		if (event.player.inventory.armorItemInSlot(3).getItem() == ModArmors.mithril_helmet
			&&event.player.inventory.armorItemInSlot(2).getItem() == ModArmors.mithril_chest
			&&event.player.inventory.armorItemInSlot(1).getItem() == ModArmors.mithril_legs
			&&event.player.inventory.armorItemInSlot(0).getItem() == ModArmors.mithril_boots){
				isArmored=true;
		}

		double xM = event.player.posX + radius, yM = event.player.posY + radius, zM = event.player.posZ + radius; //Definiamo il Massimo di X Y Z
		double xm = event.player.posX - radius, ym = event.player.posY - radius, zm = event.player.posZ - radius; //Definiamo il minimo di X Y Z
		List <Entity> list;	//Creazione lista

		list = event.player.getEntityWorld().getEntitiesWithinAABBExcludingEntity(event.player,new AxisAlignedBB(xM, yM, zM, xm, ym, zm));//Immetiamo in lista tutte le entit� comprese da il minimo e massimo di X Y Z
		Entity a[] = new Entity [list.size()]; // Creiamo un array di entity grande quanto la lista

		for(int i=0; i<list.size();i++) {	// For con controllo se si indossa l'armatura e se i< della grandezza della lista
			max=i;                   //Inseriamo il valore di "I" a singola ripetizione in max
			list.toArray(a);				// Inseriamo il contenuto della lista nell'array "a"
			a[i].setGlowing(isArmored);//Le entità di a che si trovano in posizione "i" riceveranno l'effetto glowing
			for(int k=0;k<=max;k++) {  //per k=0 fino a che k non è <= del massimo della n ripetizione
				if(a[k].getDistance(event.player) > radius) { //controllo fra entità in posizione k e player
					a[k].setGlowing(false); // Rimuoviamo l'effetto Glowing all'entità in posizione "k" di "a"
				}
			}
		}
	}

	@SubscribeEvent
	public static void setToolEffect(AttackEntityEvent event)
	{

		EntityPlayer player = event.getEntityPlayer();
	
		if (player.getHeldItemMainhand().isItemEqualIgnoreDurability(new ItemStack(ModTools.shadow_iron_sword)))
		{
//		Deve avere il 25% di possibilit� di essere attivato
			Entity foe = event.getTarget();
			EntityLivingBase foe2 = (EntityLivingBase)foe;
			if(Math.random() <= 0.30)
				foe2.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 100));
		}
	
//	Possiblit� del 50% di essere attivata
		if (player.getHeldItemMainhand().isItemEqualIgnoreDurability(new ItemStack(ModTools.vyroxeres_sword))){
	
			Entity foe = event.getTarget();
	
	
			if(Math.random() <= 0.50)
				((EntityLivingBase) foe).addPotionEffect(new PotionEffect(MobEffects.POISON, 100));
		}
		
		if (player.getHeldItemMainhand().isItemEqualIgnoreDurability(new ItemStack(ModTools.ignatius_sword))) {
			Entity foe = event.getTarget();
			
			if(Math.random() <= 0.35)
				((EntityLivingBase) foe).setFire(5);
		}
		
		if (player.getHeldItemMainhand().isItemEqualIgnoreDurability(new ItemStack(ModTools.vulcanite_sword))) {
			Entity foe = event.getTarget();
			
			if(Math.random() <= 0.35)
				((EntityLivingBase) foe).setFire(5);
		}
		
		if (player.getHeldItemMainhand().isItemEqualIgnoreDurability(new ItemStack(ModTools.kalendrite_sword))) {
			
			if(Math.random() <= 0.39)
				player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 100));
		}

		if (player.getHeldItemMainhand().isItemEqualIgnoreDurability(new ItemStack(ModTools.midasium_sword)) && !player.getHeldItemMainhand().isItemEnchanted()){

			player.getHeldItemMainhand().addEnchantment(Enchantments.LOOTING, 1);
		}
	}

}
