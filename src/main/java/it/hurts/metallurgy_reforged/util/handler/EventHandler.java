package it.hurts.metallurgy_reforged.util.handler;

import java.util.List;
import java.util.UUID;

import com.google.common.collect.Lists;

import it.hurts.metallurgy_reforged.Metallurgy;
import it.hurts.metallurgy_reforged.block.ModBlocks;
import it.hurts.metallurgy_reforged.config.EffectsConfig;
import it.hurts.metallurgy_reforged.item.armor.ModArmors;
import it.hurts.metallurgy_reforged.item.tool.ModTools;
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.util.Utils;
import it.hurts.metallurgy_reforged.util.capabilities.punch.IPunchEffect;
import it.hurts.metallurgy_reforged.util.capabilities.punch.PunchEffectProvider;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.ContainerPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MovementInput;
import net.minecraft.util.MovementInputFromOptions;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import scala.util.Random;


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

	private static MovementInput inputCheck;

	//the speed sword modifier UUID
	public static final UUID SHADOW_STEEL_ARMOR_MODIFIER_UUID =  UUID.fromString("9bfd3581-6559-468f-a5a5-66c46ff7b70c");
	
//	Don't touch this
//	private final static double speed = 0.10000000149011612D;
	//	Mithril Armor (Ultra istinto)
	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public static void glowingArmorEffect(RenderLivingEvent.Pre<EntityLivingBase> ev)
	{
		
		//Get Client Side Player
		EntityPlayer pl = Minecraft.getMinecraft().player;
		if(pl != null && !ev.getEntity().equals(pl)) //Check if player exists and the Rendered Entity isn't the player himself
		{	 	
		 if(isPlayerWearingArmor(pl, new Item[] {ModArmors.mithril_helmet,ModArmors.mithril_chest,ModArmors.mithril_legs,ModArmors.mithril_boots}) && ev.getEntity().getDistance(Minecraft.getMinecraft().player) < 30D && !ev.getEntity().isGlowing() && EffectsConfig.mithrilArmorEffect) //checks if:  the player wears The Mithrill Armor, the rendered entity is not glowing and it's within 30 blocks from the player, the effect is not disabled in the config
		 {
			ev.getEntity().setGlowing(true);
		 } 
		 else 
		 {
			ev.getEntity().setGlowing(false);
		 }		
		 
		}
	}

	@SubscribeEvent
	public static void onArmorTick(PlayerTickEvent event)
	{		
        EntityPlayer pl = event.player; //The Player   
//		Astral Silver Armor (Jump Boost)
        if(isPlayerWearingArmor(event.player, new Item[] {ModArmors.astral_silver_helmet,ModArmors.astral_silver_chest,ModArmors.astral_silver_legs,ModArmors.astral_silver_boots}) && EffectsConfig.astralSilverArmorEffect)
    		event.player.addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST, 100, 1, false, false));
		
//		Celenegil Armor (Resistence)
		if(isPlayerWearingArmor(event.player, new Item[] {ModArmors.celenegil_helmet,ModArmors.celenegil_chest,ModArmors.celenegil_legs,ModArmors.celenegil_boots}) && EffectsConfig.celenegilArmorEffect)
			event.player.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 100, 3, false, false));
		
		
//		Deep Iron Armor (Swimming Speed when the player is in water and on ground)
		if(isPlayerWearingArmor(event.player, new Item[] {ModArmors.deep_iron_helmet,ModArmors.deep_iron_chest,ModArmors.deep_iron_legs,ModArmors.deep_iron_boots}) && event.player.isInWater() && EffectsConfig.deepIronArmorEffect){
			
//			Slot index of Armor : 5 - 6 - 7 - 8	
			 for(int i = 5;i < 9; i++)
			 {
				 if(!(pl.inventoryContainer.inventorySlots.get(i) instanceof CustomSlot) && !pl.isCreative()) {
//					 Inseriamo nello slot dell'inventario in posizione i un custom slot
                     pl.inventoryContainer.inventorySlots.set(i, new CustomSlot(pl, i - 5, true));
                 }
					 
			 }			
//			Add effect to Player
			pl.addPotionEffect(new PotionEffect(MobEffects.WATER_BREATHING, 230, 3, false, false));
			pl.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 230, 1, false, false));
			
//		   Checks if the player is tourching ground
           if(pl.onGround) {
				//adds more motion in his movement
			  if(pl.motionX <= 3D)
			    pl.motionX *= 1.1D;
			  if(pl.motionZ <= 3D)
			    pl.motionZ *= 1.1D;
			}
			else
			{
//			    Stop player motion
				pl.motionX = 0D;
				pl.motionZ = 0D;
			}

//			The player can no longer swim upwards
			pl.motionY = -0.3D;

//			When the player is in the water he can step one block height like a horse
			if(pl.stepHeight != 1.0F)
				pl.stepHeight = 1.0F;
		}
		else //turns the stepHeight to normal if the player isn't wearing the deep iron armor or if he is not in water
		{
		  if(pl.stepHeight != 0.6F)
			pl.stepHeight = 0.6F;
		  	  
		    	 if(pl.inventoryContainer.inventorySlots.get(5) instanceof CustomSlot)
		    	 { 
//		    		 Insert in c the container "vanilla"
		    		 ContainerPlayer c = new ContainerPlayer(pl.inventory, !pl.world.isRemote, pl);
	    			 List<Slot> slots = c.inventorySlots;
		    		 for(int i = 5;i < 9; i++)
					 {
		    			 pl.inventoryContainer.inventorySlots.set(i, slots.get(i));
					 }
		    	 }
		    
		  
		}
		
//		Vulcanite Armor (Fire Immunity) //Removes Fire Render 
		if(isPlayerWearingArmor(event.player, new Item[] {ModArmors.vulcanite_helmet,ModArmors.vulcanite_chest,ModArmors.vulcanite_legs,ModArmors.vulcanite_boots}) && event.player.isBurning() && EffectsConfig.vulcaniteArmorEffect)
			event.player.extinguish();
 
//		Angmallen Armor (Luck I for Vampirism)
		if(isPlayerWearingArmor(event.player, new Item[] {ModArmors.angmallen_helmet,ModArmors.angmallen_chest,ModArmors.angmallen_legs,ModArmors.angmallen_boots}) && EffectsConfig.angmallenArmorEffect)
			event.player.addPotionEffect(new PotionEffect(MobEffects.LUCK, 80, 0, false, false));
		

//		Kalendrite Armor (Strenght I)
		if(isPlayerWearingArmor(event.player, new Item[] {ModArmors.kalendrite_helmet,ModArmors.kalendrite_chest,ModArmors.kalendrite_legs,ModArmors.kalendrite_boots}) && EffectsConfig.kaledriteArmorEffect)
			event.player.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 60, 0, false, false));
		

//		Amordrine Armor (Strenght II)
		if(isPlayerWearingArmor(event.player, new Item[] {ModArmors.amordrine_helmet,ModArmors.amordrine_chest,ModArmors.amordrine_legs,ModArmors.amordrine_boots}) && EffectsConfig.amordrineArmorEffect)
			event.player.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 60, 1, false, false));
		

//		Adamantine Armor (Saturation)
		if(isPlayerWearingArmor(event.player, new Item[] {ModArmors.adamantine_helmet,ModArmors.adamantine_chest,ModArmors.adamantine_legs,ModArmors.adamantine_boots}) && EffectsConfig.adamantineArmorEffect)
			event.player.addPotionEffect(new PotionEffect(MobEffects.SATURATION, 60, 0, false, false));
			
		
//		Platinum Armor (Night Vision, Needed Vanishing Curse)
		if(isPlayerWearingSpecificArmorPiece(event.player, 3,ModArmors.platinum_helmet) && EffectsConfig.platinumArmorEffect)
			event.player.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 220, 0, false, false));
		

//		Carmot Armor (Haste I)
		if(isPlayerWearingArmor(event.player, new Item[] {ModArmors.carmot_helmet,ModArmors.carmot_chest,ModArmors.carmot_legs,ModArmors.carmot_boots}) && EffectsConfig.carmotArmorEffect)
					event.player.addPotionEffect(new PotionEffect(MobEffects.HASTE, 60, 0, false, false));
		

//		Prometheum Armor (No potion, need to implement a new Effect)
		if(isPlayerWearingArmor(event.player, new Item[] {ModArmors.prometheum_helmet,ModArmors.prometheum_chest,ModArmors.prometheum_legs,ModArmors.prometheum_boots}) && EffectsConfig.prometheumArmorEffect)
			event.player.removePotionEffect(MobEffects.POISON);
		
//		Shadow Iron Armor (No Blindness)
		if(isPlayerWearingArmor(event.player, new Item[] {ModArmors.shadow_iron_helmet,ModArmors.shadow_iron_chest,ModArmors.shadow_iron_legs,ModArmors.shadow_iron_boots}))
			event.player.removePotionEffect(MobEffects.BLINDNESS);
		
//		Ceruclase Armor (inflict Slowness on attackers when hit)
		if(isPlayerWearingArmor(event.player, new Item[] {ModArmors.ceruclase_helmet,ModArmors.ceruclase_chest,ModArmors.ceruclase_legs,ModArmors.ceruclase_boots})) {
			DamageSource lastD = event.player.getLastDamageSource();
		}

		//TODO : Particle Effect
		//Quicksilver Armor (Speed + particle effect)
		if (isPlayerWearingArmor(event.player, new Item[] {ModArmors.quicksilver_helmet, ModArmors.quicksilver_chest, ModArmors.quicksilver_legs, ModArmors.quicksilver_boots}))
		{
			event.player.addPotionEffect(new PotionEffect(MobEffects.SPEED, 60, 1, false, false));
		}

		
		
		ItemStack stack =  pl.getHeldItemMainhand();
		IAttributeInstance attackSpeedInstance = pl.getEntityAttribute(SharedMonsterAttributes.ATTACK_SPEED);
		if(stack.isItemEqualIgnoreDurability(new ItemStack(ModTools.shadow_steel_sword)))
		{
		 
			float percentage = Utils.getLightArmorPercentage(pl, 50F);
			//calculate the Speed to add to the sword
			double added_speed = attackSpeedInstance.getBaseValue() * percentage / 100F;
	    	//the modifier UUID
			AttributeModifier shadow_steel_modifier = new AttributeModifier(SHADOW_STEEL_ARMOR_MODIFIER_UUID,"Shadow Steel Armor Modifier", added_speed, 0);
            //checks if player has the modifier
		    if(attackSpeedInstance.getModifier(SHADOW_STEEL_ARMOR_MODIFIER_UUID) == null)
			{
		    	//if not,add the modifier
 				attackSpeedInstance.applyModifier(shadow_steel_modifier);
			}
		    else if(attackSpeedInstance.getModifier(SHADOW_STEEL_ARMOR_MODIFIER_UUID) != null && attackSpeedInstance.getModifier(SHADOW_STEEL_ARMOR_MODIFIER_UUID).getAmount() != added_speed)
		    {
		    	//if  player has already the modifier and there is a light change,this method will update the speed attack
		    	attackSpeedInstance.removeModifier(SHADOW_STEEL_ARMOR_MODIFIER_UUID);
		    	attackSpeedInstance.applyModifier(shadow_steel_modifier);
		    }
		
		}
		else if(attackSpeedInstance.getModifier(SHADOW_STEEL_ARMOR_MODIFIER_UUID) != null)
		{
			//removes the modifier if player doesn't held the sword
			attackSpeedInstance.removeModifier(SHADOW_STEEL_ARMOR_MODIFIER_UUID);
		}
		 
		
//		Speed effect of Road
		if ((event.player.world.getBlockState(new BlockPos(event.player.posX, event.player.posY - 0.5D, event.player.posZ)).getBlock() == ModBlocks.blockRoad
				|| event.player.world.getBlockState(new BlockPos(event.player.posX, event.player.posY - 0.5D, event.player.posZ)).getBlock() == ModBlocks.blockStripedRoad)
				&& event.phase == TickEvent.Phase.START && event.side.isClient() && event.player.onGround)
		{
			if(inputCheck == null)
				inputCheck = new MovementInputFromOptions(Minecraft.getMinecraft().gameSettings);

			inputCheck.updatePlayerMoveState();

			if((inputCheck.moveForward != 0 || inputCheck.moveStrafe != 0))
			{
				event.player.motionX *= 1.20D;
				event.player.motionZ *= 1.20D;
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
			if (player.getHeldItemMainhand().isItemEqualIgnoreDurability(new ItemStack(ModTools.shadow_iron_sword))
					&& EffectsConfig.shadowIronSwordEffect) {

				Entity foe = event.getTarget();
				EntityLivingBase foe2 = (EntityLivingBase) foe;

				if ((int) (Math.random() * 100) <= 25)
					foe2.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 100));
			}

//			Vyroxeres Sword (Potion)
			if (player.getHeldItemMainhand().isItemEqualIgnoreDurability(new ItemStack(ModTools.vyroxeres_sword))
					&& EffectsConfig.vyroxeresSwordEffect)
			{
				Entity foe = event.getTarget();

				if ((int) (Math.random() * 100) <= 25)
					((EntityLivingBase) foe).addPotionEffect(new PotionEffect(MobEffects.POISON, 100));
			}

//			Ignatius Sword (Fire Aspect)
			if (player.getHeldItemMainhand().isItemEqualIgnoreDurability(new ItemStack(ModTools.ignatius_sword))
					&& EffectsConfig.ignatiusSwordEffect) {

				Entity foe = event.getTarget();

				if ((int) (Math.random() * 100) <= 15)
					foe.setFire(5);
			}

//			Vulcanite Sword (Fire Aspect)
			if (player.getHeldItemMainhand().isItemEqualIgnoreDurability(new ItemStack(ModTools.vulcanite_sword))
					&& EffectsConfig.vulcaniteSwordEffect) {

				Entity foe = event.getTarget();

				if ((int) (Math.random() * 100) <= 30)
					foe.setFire(5);
			}

//			Tartarite Sword (Withering II)
			if (player.getHeldItemMainhand().isItemEqualIgnoreDurability(new ItemStack(ModTools.tartarite_sword))
					&& EffectsConfig.tartariteSwordEffect) {

				Entity foe = event.getTarget();

				if ((int) (Math.random() * 100) <= 20)
					((EntityLivingBase) foe).addPotionEffect(new PotionEffect(MobEffects.WITHER, 60, 1, false, false));
			}
			
//			Mithril Sword (Give Glowing to entity Hitted)
			if(player.getHeldItemMainhand().isItemEqualIgnoreDurability(new ItemStack(ModTools.mithril_sword))) {
				Entity foe = event.getTarget();
				
				if ((int) (Math.random() * 100) <= 20)
					((EntityLivingBase) foe).addPotionEffect(new PotionEffect(MobEffects.GLOWING, 200, 1, false, false));
			}


//			Kalendrite sword (Regeneration)
			if (player.getHeldItemMainhand().isItemEqualIgnoreDurability(new ItemStack(ModTools.kalendrite_sword))
					&& EffectsConfig.kalendriteSwordEffect) {

				if ((int) (Math.random() * 100) <= 30)
					player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 100, 1, false, false));
			}
			
//			Ceruclase Sword (Give slowness)
			if (player.getHeldItemMainhand().isItemEqualIgnoreDurability(new ItemStack(ModTools.ceruclase_sword))) {
				Entity foe = event.getTarget();
				
				if ((int) (Math.random() * 100) <= 25)
					((EntityLivingBase) foe).addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 70, 1, false, false));
			}
		}
	}


	@SubscribeEvent
	public static void onBreakBlock(PlayerEvent.BreakSpeed event)
	{
		EntityPlayer pl = event.getEntityPlayer();
		ItemStack mainHandStack = pl.getHeldItemMainhand();
		
		if(pl.isInWater()
				&& mainHandStack.isItemEqualIgnoreDurability(new ItemStack(ModTools.deep_iron_pickaxe))
				&& EffectsConfig.deepIronPickaxeEffect)
			event.setNewSpeed(6F);
		//set tools break speed based on light except for hoe and sword
				if(Utils.isItemStackASpecificToolMaterial(ModMetals.SHADOW_STEEL, mainHandStack,"hoe","sword")) {
					float percentage = Utils.getLightArmorPercentage(pl,100F);
					float speed = event.getNewSpeed()  * percentage / 40F;
					event.setNewSpeed(event.getOriginalSpeed() + speed);
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
		if(event.getEntityLiving() instanceof EntityPlayer)
		{
			EntityPlayer pl = (EntityPlayer) event.getEntityLiving();
			//check if player is wearing the shadow steel armor
			if(isPlayerWearingArmor(pl, new Item[] {ModArmors.shadow_steel_helmet,ModArmors.shadow_steel_chest,ModArmors.shadow_steel_legs,ModArmors.shadow_steel_boots}))
			{
				//get light percentage,maximum 30%
				float percentage = Utils.getLightArmorPercentage(pl,40F);
				float removedDamage = event.getAmount() * percentage / 100F;
				event.setAmount(event.getAmount() - removedDamage);			
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
	

		 
		  
		//punch effect inolashite armor
		@SubscribeEvent
		public static void addPunchEffect(AttackEntityEvent event)
		{	 
			EntityPlayer pl = event.getEntityPlayer();
			Entity entity = event.getTarget();
			//checks if the players isn't holding an item and if he is wearing 
			//apply effect if the player has a minimum food level or if he is in creative
			
			if(EffectsConfig.inolashiteArmorEffect && pl.getHeldItemMainhand().isEmpty() && isPlayerWearingArmor(pl, new Item[] {ModArmors.inolashite_helmet,ModArmors.inolashite_chest,ModArmors.inolashite_legs,ModArmors.inolashite_boots}))
			{		
				
				if(pl.getFoodStats().getFoodLevel() >= 4D || pl.isCreative()){
				
				if(entity instanceof EntityLivingBase) {
					IPunchEffect effect = entity.getCapability(PunchEffectProvider.PUNCH_EFFECT_CAP, null);
					effect.setHitTicks(1);
					effect.setNoClip(entity.noClip);
					entity.noClip = true;
					}
				
				float yaw = pl.getRotationYawHead();
				float pitch = pl.rotationPitch;
						
				double x = -MathHelper.sin(yaw * 0.017453292F) * MathHelper.cos(pitch * 0.017453292F);
				double z = MathHelper.cos(yaw * 0.017453292F) * MathHelper.cos(pitch * 0.017453292F);
				double f = MathHelper.sqrt(x * x + z * z);
				double velocity = 8D;
			    x = x / (double)f;
			    z = z / (double)f;
			    x = x * (double)velocity;
			    z = z * (double)velocity;
				entity.motionX = x;
				entity.motionZ = z;
				//remove food level
				if(!pl.isCreative())
				{
				 pl.getFoodStats().setFoodLevel(pl.getFoodStats().getFoodLevel() - 4);
				 pl.getFoodStats().setFoodSaturationLevel(pl.getFoodStats().getSaturationLevel() - 4);
				}
				pl.playSound(SoundEvents.ENTITY_GENERIC_EXPLODE, 1F, 1F);
				
				entity.attackEntityFrom(DamageSource.causeMobDamage(pl), 6F);
				}
				else
				{
				      pl.sendStatusMessage(new TextComponentTranslation("effect.metallurgy.punch_effect_tired", new Object[0]),true);	                 
				}
			}
		}
		
		
		//event tick entity
		@SubscribeEvent
		public static void applyPunchEffects(LivingUpdateEvent event)
		{
			EntityLivingBase entity = event.getEntityLiving();
			IPunchEffect effect = entity.getCapability(PunchEffectProvider.PUNCH_EFFECT_CAP, null);
			
			//check if entity has been punched
			if(effect.getHitTicks() > 0)
			{
			   Random rand = new Random();		
				
				 for (int i = 0; i < 10; ++i)
		          {
		             entity.world.spawnParticle(EnumParticleTypes.CLOUD, entity.posX + (rand.nextDouble() - 0.5D) * ((double)entity.width * 1.5D), entity.posY + rand.nextDouble() * ((double)entity.height * 1.5D), entity.posZ + (rand.nextDouble() - 0.5D) * ((double)entity.width * 1.5D), 0.0D, 0.0D, 0.0D);
		          }
		
				 AxisAlignedBB axisalignedbb = entity.getEntityBoundingBox().grow(0.5D, 0D, 0.5D);
				
				if(!entity.isDead)
				{
					
					//destroy blocks and damage the punched entity
				for(double i = axisalignedbb.minX;i < axisalignedbb.maxX;i += 0.1D)
				{
					for(double j = axisalignedbb.minY;j < axisalignedbb.maxY;j += 0.1D)
					{
						for(double k = axisalignedbb.minZ;k < axisalignedbb.maxZ;k += 0.1D)
						{
							
							BlockPos pos = new BlockPos(i, j, k);
							if(!entity.world.isAirBlock(pos)) {
								IBlockState state = entity.world.getBlockState(pos);
								float hardness = state.getBlockHardness(entity.world, pos);
								if(hardness >= 0)
								{
									
									if(!state.getMaterial().isLiquid()) {
								  if(!entity.world.isRemote)
							       entity.world.destroyBlock(pos, true);				     
							       entity.attackEntityFrom(DamageSource.causeMobDamage(entity.getLastAttackedEntity()), hardness);								
									}
									}
								else
								{
									entity.noClip = effect.hasNoClip();
									
								}
							}
						}	
					}
				}
				}
				

				//adds the punch effect ticks 
				effect.addHitTicks();
			
				double velocity = entity.getPositionVector().distanceTo(new Vec3d(entity.prevPosX,entity.posY,entity.prevPosZ));
			   //if the velocity of the punched entity is too low,it will lose the "effect"
				if(effect.getHitTicks() > 5 && velocity <= 1D) {
					effect.endEffect(entity);	
				}
				
				//if the punch ticks is over 30 the entity will lose the "effect"
				if(effect.getHitTicks() > 30) { 
					effect.endEffect(entity);	
				}
			}
		}
	
}
