package it.hurts.metallurgy_5.util.handler;

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
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
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
	
	private static boolean fire = false;
	
//	Mithril Armor (Ultra istinto)
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
		List <Entity> list;						//Creazione lista

		list = event.player.getEntityWorld().getEntitiesWithinAABBExcludingEntity(event.player,new AxisAlignedBB(xM, yM, zM, xm, ym, zm)); //Immetiamo in lista tutte le entitï¿½ comprese da il minimo e massimo di X Y Z
		Entity a[] = new Entity [list.size()]; 	// Creiamo un array di entity grande quanto la lista

		for(int i=0; i<list.size();i++) {		// For con controllo se si indossa l'armatura e se i< della grandezza della lista
			max=i;                   			//Inseriamo il valore di "I" a singola ripetizione in max
			list.toArray(a);					// Inseriamo il contenuto della lista nell'array "a"
			a[i].setGlowing(isArmored);			//Le entita'  di a che si trovano in posizione "i" riceveranno l'effetto glowing
			for(int k=0;k<=max;k++) {  			//per k=0 fino a che k non Ã¨ <= del massimo della n ripetizione
				if(a[k].getDistance(event.player) > radius) { 	//controllo fra entitÃ  in posizione k e player
					a[k].setGlowing(false); 					// Rimuoviamo l'effetto Glowing all'entitÃ  in posizione "k" di "a"
				}
			}
		}
	}
	
	@SubscribeEvent
	public static void armorEffectBase(PlayerTickEvent event) {
//		Astral Silver Armor (Jump Boost)
		if (event.player.inventory.armorItemInSlot(3).getItem() == ModArmors.astral_silver_helmet
				&&event.player.inventory.armorItemInSlot(2).getItem() == ModArmors.astral_silver_chest
				&&event.player.inventory.armorItemInSlot(1).getItem() == ModArmors.astral_silver_legs
				&&event.player.inventory.armorItemInSlot(0).getItem() == ModArmors.astral_silver_boots){
			event.player.addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST, 100, 1));
		}
		
//		Celenegil Armor (Resistence)
		if (event.player.inventory.armorItemInSlot(3).getItem() == ModArmors.celenegil_helmet
				&&event.player.inventory.armorItemInSlot(2).getItem() == ModArmors.celenegil_chest
				&&event.player.inventory.armorItemInSlot(1).getItem() == ModArmors.celenegil_legs
				&&event.player.inventory.armorItemInSlot(0).getItem() == ModArmors.celenegil_boots){
			event.player.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 100, 3));
		}
		
//		Deep Iron Armor (Swimming Speed)
		if (event.player.inventory.armorItemInSlot(3).getItem() == ModArmors.deep_iron_helmet
				&&event.player.inventory.armorItemInSlot(2).getItem() == ModArmors.deep_iron_chest
				&&event.player.inventory.armorItemInSlot(1).getItem() == ModArmors.deep_iron_legs
				&&event.player.inventory.armorItemInSlot(0).getItem() == ModArmors.deep_iron_boots
				&&event.player.isInWater()){
			event.player.addPotionEffect(new PotionEffect(MobEffects.SPEED, 60, 2));
		}
//		Vulcanite Armor (Fire Immunity)
		if (event.player.inventory.armorItemInSlot(3).getItem() == ModArmors.vulcanite_helmet
				&&event.player.inventory.armorItemInSlot(2).getItem() == ModArmors.vulcanite_chest
				&&event.player.inventory.armorItemInSlot(1).getItem() == ModArmors.vulcanite_legs
				&&event.player.inventory.armorItemInSlot(0).getItem() == ModArmors.vulcanite_boots){
			 fire = true;
		}else {
			fire = false;
		}
	}	

	@SubscribeEvent
	public static void setToolEffect(AttackEntityEvent event){

		EntityPlayer player = event.getEntityPlayer();
	
//		Shadow Iron Sword (Blindness [cecità])
		if (player.getHeldItemMainhand().isItemEqualIgnoreDurability(new ItemStack(ModTools.shadow_iron_sword)))
		{
			Entity foe = event.getTarget();
			EntityLivingBase foe2 = (EntityLivingBase)foe;
			
			if((int)Math.random()*100 <= 25)
				foe2.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 100));
		}
	
//		Vyroxeres Sword (Potion)
		if (player.getHeldItemMainhand().isItemEqualIgnoreDurability(new ItemStack(ModTools.vyroxeres_sword))){
			Entity foe = event.getTarget();
	
			if((int)Math.random()*100 <= 25)
				((EntityLivingBase) foe).addPotionEffect(new PotionEffect(MobEffects.POISON, 100));
		}
		
//		Ignatius Sword (Fire Aspect)
		if (player.getHeldItemMainhand().isItemEqualIgnoreDurability(new ItemStack(ModTools.ignatius_sword))) {
			Entity foe = event.getTarget();
			
			if((int)Math.random()*100 <= 15)
				((EntityLivingBase) foe).setFire(5);
		}
		
//		Vulcanite Sword (Fire Aspect)
		if (player.getHeldItemMainhand().isItemEqualIgnoreDurability(new ItemStack(ModTools.vulcanite_sword))) {
			Entity foe = event.getTarget();
			
			if((int)Math.random()*100 <= 30)
				((EntityLivingBase) foe).setFire(5);
		}
		
//		kalendrite sword (Regeneration)
		if (player.getHeldItemMainhand().isItemEqualIgnoreDurability(new ItemStack(ModTools.kalendrite_sword))) {
			
			if((int)Math.random()*100 <= 30)
				player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 100));
		}
		
//		TODO migliorare questo effetto
//		Sanguinite Sword (Vampirism)
		if(player.getHeldItemMainhand().isItemEqualIgnoreDurability(new ItemStack(ModTools.sanguinite_sword))) {
			
			int luck = (int) player.getLuck();
			
			switch(luck) {
				
				case 0 :{
					if((int)Math.random()*100 <=15)
						player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 60, 4));
				}
				break;
				
				case 1 :{
					if((int)Math.random()*100 <=25)
						player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 100, 4));
				}
				break;
				
				case 2 :{
					if((int)Math.random()*100 <35)
						player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 60, 5));
				}
				break;
				
				case 3 : {
					if((int)Math.random()*100 <50) {
						player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 100, 5));
						player.addPotionEffect(new PotionEffect(MobEffects.HEALTH_BOOST, 60));
					}
				}
				break;
				
				default: {
					if((int)Math.random()*100 <=15)
						player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 60, 4));
				}
			}
		}
		
		
//		Enchantment
		
//		Midasium Sword (Looting I)
		if (player.getHeldItemMainhand().isItemEqualIgnoreDurability(new ItemStack(ModTools.midasium_sword)) && !player.getHeldItemMainhand().isItemEnchanted()){

			player.getHeldItemMainhand().addEnchantment(Enchantments.LOOTING, 1);
		}
	}
	
	
//	Effects	
	
//	FireImmunity
	@SubscribeEvent
	public static void FireImmunity(LivingAttackEvent event) {
		if(event.getEntity() instanceof EntityPlayer) {
			 if (event.getSource().equals (DamageSource.LAVA) 
			 ||  event.getSource().equals (DamageSource.IN_FIRE) 
			 ||  event.getSource().equals (DamageSource.ON_FIRE))
				 event.setCanceled(fire);
		}
		
	}
	
}
