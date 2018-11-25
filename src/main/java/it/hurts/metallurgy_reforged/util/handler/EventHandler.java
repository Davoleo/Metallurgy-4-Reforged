package it.hurts.metallurgy_reforged.util.handler;

import com.google.common.collect.Lists;
import it.hurts.metallurgy_reforged.Metallurgy;
import it.hurts.metallurgy_reforged.block.ModBlocks;
import it.hurts.metallurgy_reforged.item.armor.ModArmors;
import it.hurts.metallurgy_reforged.item.tool.ModTools;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import scala.util.Random;

import java.util.List;

/***************************
 *
 * Author : ItHurtsLikeHell
 * Project: Metallurgy-5
 * Date   : 28 ago 2018
 * Time   : 18:24:07
 *
 ***************************/

@EventBusSubscriber(modid= Metallurgy.MODID)
public class EventHandler {

//	Don't touch this
//	private final static double speed = 0.10000000149011612D;

//	Mithril Armor (Ultra istinto)
	@SubscribeEvent
	public static void glowingArmorEffect(PlayerTickEvent event)
	{
		int radius = 32;
		boolean isArmored = isPlayerWearingArmor(event.player, new Item[] {ModArmors.mithril_helmet,ModArmors.mithril_chest,ModArmors.mithril_legs,ModArmors.mithril_boots});

//		Definiamo un raggio massimo di azione
		double xM = event.player.posX + radius, yM = event.player.posY + radius, zM = event.player.posZ + radius;
		
//		Definiamo un raggio minimo di azione
		double xm = event.player.posX - radius, ym = event.player.posY - radius, zm = event.player.posZ - radius;
		
//		Creiamo una lista di entita'
		List<Entity> list;

//		Inseriamo nella lista tutte le entit� presenti nel ragglio minimo e nel raggio massimo
		list = event.player.getEntityWorld().getEntitiesWithinAABBExcludingEntity(event.player,new AxisAlignedBB(xM, yM, zM, xm, ym, zm));

//		Creiamo un vector di entite' grande quanto la lista
		Entity entity[] = new Entity [list.size()];

		int max;
		
//		Ciclo per scorrere il vector
		for(int i = 0; i < list.size(); i++) {
			max = i;          //Inseriamo il valore di "I" a singola ripetizione in max

//			Convertiamo la lista 'list' in un vector 'entity'
			list.toArray(entity);

//			Impostiamo ad ogni entity di 'i' l'effetto 'Glowing'
			entity[i].setGlowing(isArmored);

//			Per ogni entity controlliamo se si trovano all'interno del raggio, altrimenti rimuoviamo l'effetto
			for(int k = 0;k <= max; k++) {  			
				if(entity[k].getDistance(event.player) > radius)
					entity[k].setGlowing(false);
			}
		}
	}

	@SubscribeEvent
	public static void onArmorTick(PlayerTickEvent event)
	{
        EntityPlayer pl = event.player; //The Player

//		Astral Silver Armor (Jump Boost)
        if(isPlayerWearingArmor(event.player, new Item[] {ModArmors.astral_silver_helmet,ModArmors.astral_silver_chest,ModArmors.astral_silver_legs,ModArmors.astral_silver_boots}))
    		event.player.addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST, 100, 1, false, false));
		
//		Celenegil Armor (Resistence)
		if(isPlayerWearingArmor(event.player, new Item[] {ModArmors.celenegil_helmet,ModArmors.celenegil_chest,ModArmors.celenegil_legs,ModArmors.celenegil_boots}))
			event.player.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 100, 3, false, false));
		
		
//		Deep Iron Armor (Swimming Speed when the player is in water and on ground)
		if(isPlayerWearingArmor(event.player, new Item[] {ModArmors.deep_iron_helmet,ModArmors.deep_iron_chest,ModArmors.deep_iron_legs,ModArmors.deep_iron_boots}) && event.player.isInWater()){
			
			pl.addPotionEffect(new PotionEffect(MobEffects.WATER_BREATHING, 230, 3, false, false));
			pl.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 230, 1, false, false));
			//checks if the player is tourching ground
           if(pl.onGround) {
				//adds more motion in his movement
			  if(pl.motionX <= 3D)
			    pl.motionX *= 1.1D;
			  if(pl.motionZ <= 3D)
			    pl.motionZ *= 1.1D;
			}
			else
			{
			    //stop player motion
				pl.motionX = 0D;
				pl.motionZ = 0D;
			}

			//The player can no longer swim upwards
			pl.motionY = -0.3D;

			//when the player is in the water he can step one block height like a horse
			if(pl.stepHeight != 1.0F)
				pl.stepHeight = 1.0F;
		}
		else if(pl.stepHeight > 0.0F) //turns the stepHeight to normal if the player isn't wearing the deep iron armor or if he is not in water
			pl.stepHeight = 0.0F;
		
//		Vulcanite Armor (Fire Immunity) //Removes Fire Render 
		if(isPlayerWearingArmor(event.player, new Item[] {ModArmors.vulcanite_helmet,ModArmors.vulcanite_chest,ModArmors.vulcanite_legs,ModArmors.vulcanite_boots}) && event.player.isBurning())
			event.player.extinguish();
 
//		Angmallen Armor (Luck I for Vampirism)
		if(isPlayerWearingArmor(event.player, new Item[] {ModArmors.angmallen_helmet,ModArmors.angmallen_chest,ModArmors.angmallen_legs,ModArmors.angmallen_boots}))
			event.player.addPotionEffect(new PotionEffect(MobEffects.LUCK, 80, 0, false, false));
		

//		Kalendrite Armor (Strenght I)
		if(isPlayerWearingArmor(event.player, new Item[] {ModArmors.kalendrite_helmet,ModArmors.kalendrite_chest,ModArmors.kalendrite_legs,ModArmors.kalendrite_boots}))
			event.player.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 60, 0, false, false));
		

//		Amordrine Armor (Strenght II)
		if(isPlayerWearingArmor(event.player, new Item[] {ModArmors.amordrine_helmet,ModArmors.amordrine_chest,ModArmors.amordrine_legs,ModArmors.amordrine_boots}))
			event.player.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 60, 1, false, false));
		

//		Adamantine Armor (Saturation)
		if(isPlayerWearingArmor(event.player, new Item[] {ModArmors.adamantine_helmet,ModArmors.adamantine_chest,ModArmors.adamantine_legs,ModArmors.adamantine_boots}))	
			event.player.addPotionEffect(new PotionEffect(MobEffects.SATURATION, 60, 0, false, false));
		

//		Astral Silver Armor (Jump Boost II)
		if(isPlayerWearingArmor(event.player, new Item[] {ModArmors.astral_silver_helmet,ModArmors.astral_silver_chest,ModArmors.astral_silver_legs,ModArmors.astral_silver_boots}))	
			event.player.addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST, 60, 1, false, false));
						

//		Platinum Armor (Night Vision, Needed Vanishing Curse)
		if(isPlayerWearingSpecificArmorPiece(event.player, 3,ModArmors.platinum_helmet))
			event.player.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 220, 0, false, false));
		

//		Carmot Armor (Haste I)
		if(isPlayerWearingArmor(event.player, new Item[] {ModArmors.carmot_helmet,ModArmors.carmot_chest,ModArmors.carmot_legs,ModArmors.carmot_boots}))	
					event.player.addPotionEffect(new PotionEffect(MobEffects.HASTE, 60, 0, false, false));
		

//		Prometheum Armor (No potion, need to implement a new Effect)
		if(isPlayerWearingArmor(event.player, new Item[] {ModArmors.prometheum_helmet,ModArmors.prometheum_chest,ModArmors.prometheum_legs,ModArmors.prometheum_boots}))	
			event.player.removePotionEffect(MobEffects.POISON);
	

//		Speed effect of Road
		if (event.player.world.getBlockState(new BlockPos(event.player.posX, event.player.posY - 0.5D, event.player.posZ)).getBlock() == ModBlocks.blockRoad
				|| event.player.world.getBlockState(new BlockPos(event.player.posX, event.player.posY - 0.5D, event.player.posZ)).getBlock() == ModBlocks.blockStripedRoad)
		{
			if(event.player.onGround)
			{
				event.player.motionX *= 1.13D;
				event.player.motionZ *= 1.13D;
			}
		}
	}

	
	//method to check if player wears the complete Armor.
	public static boolean isPlayerWearingArmor(EntityPlayer pl,Item[] armor)
	{
			
		boolean flag = true;
			
		  List<ItemStack> list = Lists.newArrayList(pl.getArmorInventoryList().iterator());
		  for(int i = 0; i < list.size();i++) {
		  if(!list.get(i).getItem().equals(armor[3 - i]))
           flag = false;
		   }
		 return flag;
		}
		
	//get Specific Armor Equip [3 = helmet,2 = chest,1 = legs, boots = 0]
	public static boolean isPlayerWearingSpecificArmorPiece(EntityPlayer pl,int index,Item armorEquip)
	{			
		List<ItemStack> list = Lists.newArrayList(pl.getArmorInventoryList().iterator());	      
	    return list.get(index).getItem().equals(armorEquip);
	}
	
	@SubscribeEvent
	public static void onAttack(AttackEntityEvent event)
	{

		EntityPlayer player = event.getEntityPlayer();
		if (!player.world.isRemote) {

//			Shadow Iron Sword (Blindness)
			if (player.getHeldItemMainhand().isItemEqualIgnoreDurability(new ItemStack(ModTools.shadow_iron_sword))) {
				Entity foe = event.getTarget();
				EntityLivingBase foe2 = (EntityLivingBase) foe;

				if ((int) (Math.random() * 100) <= 25)
					foe2.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 100));
			}

//			Vyroxeres Sword (Potion)
			if (player.getHeldItemMainhand().isItemEqualIgnoreDurability(new ItemStack(ModTools.vyroxeres_sword))) {
				Entity foe = event.getTarget();

				if ((int) (Math.random() * 100) <= 25)
					((EntityLivingBase) foe).addPotionEffect(new PotionEffect(MobEffects.POISON, 100));
			}

//			Ignatius Sword (Fire Aspect)
			if (player.getHeldItemMainhand().isItemEqualIgnoreDurability(new ItemStack(ModTools.ignatius_sword))) {
				Entity foe = event.getTarget();

				if ((int) (Math.random() * 100) <= 15)
					foe.setFire(5);
			}

//			Vulcanite Sword (Fire Aspect)
			if (player.getHeldItemMainhand().isItemEqualIgnoreDurability(new ItemStack(ModTools.vulcanite_sword))) {
				Entity foe = event.getTarget();

				if ((int) (Math.random() * 100) <= 30)
					foe.setFire(5);
			}

//			Tartarite Sword (Withering II)
			if (player.getHeldItemMainhand().isItemEqualIgnoreDurability(new ItemStack(ModTools.tartarite_sword))) {
				Entity foe = event.getTarget();

				if ((int) (Math.random() * 100) <= 20)
					((EntityLivingBase) foe).addPotionEffect(new PotionEffect(MobEffects.WITHER, 60, 1, false, false));
			}


//			Kalendrite sword (Regeneration)
			if (player.getHeldItemMainhand().isItemEqualIgnoreDurability(new ItemStack(ModTools.kalendrite_sword))) {

				if ((int) (Math.random() * 100) <= 30)
					player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 100, 1, false, false));
			}
		}
	}



	
	
//	Sanguinite Sword (Vampirism)
	@SubscribeEvent
	public static void entityHurtEvent(LivingHurtEvent event)
	{
		EntityLivingBase eventEntity = event.getEntityLiving();
		//the entity that damaged the event entity
		Entity source = event.getSource().getImmediateSource();
		if(source instanceof EntityPlayer)
		{

			//the player that damaged the event entity
			EntityPlayer pl = (EntityPlayer) source;

			if(pl.getHeldItemMainhand().isItemEqualIgnoreDurability(new ItemStack(ModTools.sanguinite_sword))) {
				{
					//check if the player is missing hearts.
					if(pl.getHealth() < pl.getMaxHealth())
					{

						int luck_level = Math.round(pl.getLuck());
						//percentage to get healed based on the luck of the player (example: luck 0 = 15%,luck 1 = 20%...)
						int percentage = 15 + (luck_level * 5);
						if(new Random().nextInt(100) < percentage)
						{
							//the heal Amount ,that is the 10% of the damage
							float healAmount = event.getAmount() * 10F / 100F;
							if(pl.getHealth() + healAmount >= pl.getMaxHealth())
								healAmount = 0;
							//set the player health
							pl.setHealth(pl.getHealth() + healAmount);
						}
					}
				}
			}
		}
	}


//	Effects


//	FireImmunity
		@SubscribeEvent
		public static void cancelFireDamage (LivingAttackEvent event){
		if (event.getEntity() instanceof EntityPlayer) {
			if(event.getSource().isFireDamage()) {
             if(isPlayerWearingArmor((EntityPlayer) event.getEntity(), new Item[] {ModArmors.vulcanite_helmet,ModArmors.vulcanite_chest,ModArmors.vulcanite_legs,ModArmors.vulcanite_boots}))
				 event.setCanceled(true);
			}
		}
	}
	

	
}
