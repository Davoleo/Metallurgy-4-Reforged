package it.hurts.metallurgy_reforged.util.handler.event;

import java.util.ArrayList;

import it.hurts.metallurgy_reforged.config.ArmorEffectsConfig;
import it.hurts.metallurgy_reforged.config.ToolEffectsConfig;
import it.hurts.metallurgy_reforged.item.armor.ModArmors;
import it.hurts.metallurgy_reforged.item.tool.ModTools;
import it.hurts.metallurgy_reforged.util.Utils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import scala.util.Random;

public class Sword {
	
	@SubscribeEvent
	public static void onAttack(AttackEntityEvent event)
	{

		EntityPlayer player = event.getEntityPlayer();
		if (!player.world.isRemote) {

			Entity foe = event.getTarget();
//			Shadow Iron Sword (Blindness)
			if (player.getHeldItemMainhand().isItemEqualIgnoreDurability(new ItemStack(ModTools.shadow_iron_sword))
					&& ToolEffectsConfig.shadowIronSwordEffect) {

				EntityLivingBase foe2 = (EntityLivingBase) foe;

				if ((int) (Math.random() * 100) <= 25)
					foe2.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 100));
			}

//			Desichalkos Sword ( Give Random Effect to entity )
			if(player.getHeldItemMainhand().isItemEqualIgnoreDurability(new ItemStack(ModTools.desichalkos_sword))
					&& ToolEffectsConfig.desichalkosSwordEffect){
				((EntityLivingBase)foe).addPotionEffect(new PotionEffect(Utils.getRandomEffect(), 80, 0));
			}

//			Vyroxeres Sword (Potion)
			if (player.getHeldItemMainhand().isItemEqualIgnoreDurability(new ItemStack(ModTools.vyroxeres_sword))
					&& ToolEffectsConfig.vyroxeresSwordEffect)
			{

				if ((int) (Math.random() * 100) <= 25)
					((EntityLivingBase) foe).addPotionEffect(new PotionEffect(MobEffects.POISON, 100));
			}

//			Ignatius Sword (Fire Aspect)
			if (player.getHeldItemMainhand().isItemEqualIgnoreDurability(new ItemStack(ModTools.ignatius_sword))
					&& ToolEffectsConfig.ignatiusSwordEffect) {

				if ((int) (Math.random() * 100) <= 15)
					foe.setFire(5);
			}

//			Vulcanite Sword (Fire Aspect)
			if (player.getHeldItemMainhand().isItemEqualIgnoreDurability(new ItemStack(ModTools.vulcanite_sword))
					&& ToolEffectsConfig.vulcaniteSwordEffect) {

				if ((int) (Math.random() * 100) <= 30)
					foe.setFire(5);
			}

//			Tartarite Sword (Withering II)
			if (player.getHeldItemMainhand().isItemEqualIgnoreDurability(new ItemStack(ModTools.tartarite_sword))
					&& ToolEffectsConfig.tartariteSwordEffect) {

				if ((int) (Math.random() * 100) <= 20)
					((EntityLivingBase) foe).addPotionEffect(new PotionEffect(MobEffects.WITHER, 60, 1, false, false));
			}
			
//			Mithril Sword (Give Glowing to entity Hitted)
			if(player.getHeldItemMainhand().isItemEqualIgnoreDurability(new ItemStack(ModTools.mithril_sword))) {
				if ((int) (Math.random() * 100) <= 20)
					((EntityLivingBase) foe).addPotionEffect(new PotionEffect(MobEffects.GLOWING, 200, 1, false, false));
			}

//			Kalendrite sword (Regeneration)
			if (player.getHeldItemMainhand().isItemEqualIgnoreDurability(new ItemStack(ModTools.kalendrite_sword))
					&& ToolEffectsConfig.kalendriteSwordEffect) {

				if ((int) (Math.random() * 100) <= 30)
					player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 100, 1, false, false));
			}
			
//			Ceruclase Sword (Give slowness)
			if (player.getHeldItemMainhand().isItemEqualIgnoreDurability(new ItemStack(ModTools.ceruclase_sword))) {
				
				if ((int) (Math.random() * 100) <= 25)
					((EntityLivingBase) foe).addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 70, 1, false, false));
			}
		}
	}
	
	
	@SubscribeEvent
	public static void onEntityDeth(LivingDeathEvent event) {
		Entity attacker = event.getSource().getImmediateSource();
		if(attacker instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) attacker;
//			Celenegil Sword ( Give Speed and Strenght on entity kill )
			if(player.getHeldItemMainhand().isItemEqualIgnoreDurability(new ItemStack(ModTools.celenegil_sword)) && 
					(player.isPotionActive(MobEffects.STRENGTH) ? player.getActivePotionEffect(MobEffects.STRENGTH).getDuration() < 8:true && 
							player.isPotionActive(MobEffects.SPEED) ? player.getActivePotionEffect(MobEffects.SPEED).getDuration() < 8:true)) {
				
				player.addPotionEffect(new PotionEffect(MobEffects.SPEED, 140, 0, false, false));
				player.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 140, 0, false, false));
			}

//			Orichalcum Sword ( Give Strenght on entity kill )
			if(player.getHeldItemMainhand().isItemEqualIgnoreDurability(new ItemStack(ModTools.orichalcum_sword)) && 
					(player.isPotionActive(MobEffects.STRENGTH) ? player.getActivePotionEffect(MobEffects.STRENGTH).getDuration() < 8:true))
				
				player.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 140, 0, false, false));
		}
	}
	
//	Midasium Effect
	@SubscribeEvent
    public static void duplicationSwordEffect(LivingDropsEvent ev)
    {

        DamageSource source = ev.getSource();
        Entity entity = source.getTrueSource();
        
        if(entity instanceof EntityPlayer)
        {
            EntityPlayer pl = (EntityPlayer) entity;

            if(pl.getHeldItemMainhand().isItemEqualIgnoreDurability(new ItemStack(ModTools.midasium_sword))&& !(ev.getEntity() instanceof EntityPlayer)){
	            ArrayList<EntityItem> drops = new ArrayList<>();
	
//            	Duplica il drop
	            if(!pl.isCreative()) {
	            	 if((int) (Math.random() * 100) <= 50) {
					        for(EntityItem item : ev.getDrops()){
					            EntityItem clone = new EntityItem(item.world, item.posX, item.posY, item.posZ, item.getItem());
					            drops.add(clone);
					        }
				            ev.getDrops().addAll(drops);
			            }
	            } else {
	            	for(EntityItem item : ev.getDrops()){
			            EntityItem clone = new EntityItem(item.world, item.posX, item.posY, item.posZ, item.getItem());
			            drops.add(clone);
			        }
		            ev.getDrops().addAll(drops);
	            }
            }
        }
    }
	
//	Sanguinite Sword (Vampirism)
	@SubscribeEvent
	public static void entityHurtEvent(LivingHurtEvent event)
	{
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
							float healAmount = event.getAmount() * 0.15F;
							if(pl.getHealth() + healAmount >= pl.getMaxHealth())
								healAmount = 0;
							//set the player health
							pl.setHealth(pl.getHealth() + healAmount);
						}
					}
				}
			}
		}
		if(event.getEntityLiving() instanceof EntityPlayer)
		{
			EntityPlayer pl = (EntityPlayer) event.getEntityLiving();
			//check if player is wearing the shadow steel armor
			if(ArmorEffectsConfig.shadowSteelArmorEffect && 
					EventUtils.isPlayerWearingArmor(pl, new Item[] {ModArmors.shadow_steel_helmet,ModArmors.shadow_steel_chest,ModArmors.shadow_steel_legs,ModArmors.shadow_steel_boots}))
			{
				//get light percentage,maximum 30%
				float percentage = Utils.getLightArmorPercentage(pl,40F);
				float removedDamage = event.getAmount() * percentage / 100F;
				event.setAmount(event.getAmount() - removedDamage);
			}
		}
	}

}
