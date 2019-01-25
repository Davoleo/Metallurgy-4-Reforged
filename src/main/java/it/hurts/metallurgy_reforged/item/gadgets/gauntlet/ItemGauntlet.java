package it.hurts.metallurgy_reforged.item.gadgets.gauntlet;

import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import it.hurts.metallurgy_reforged.item.ItemBase;
import it.hurts.metallurgy_reforged.util.MetallurgyTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.init.Enchantments;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.SPacketAnimation;
import net.minecraft.util.EnumHand;
import net.minecraft.world.WorldServer;

import java.util.List;

/***************************
 *
 * Author : ItHurtsLikeHell
 * Project: Metallurgy-4-Reforged
 * Date   : 04 gen 2019
 * Time   : 14:10:47
 *
 ***************************/

public class ItemGauntlet extends ItemBase{
	
	private Enchantment[] e = {
			Enchantments.BANE_OF_ARTHROPODS,
			Enchantments.MENDING,
			Enchantments.SMITE,
			Enchantments.VANISHING_CURSE,
			Enchantments.UNBREAKING,
			Enchantments.SHARPNESS
	};
//	private final double attack_damage = 1.05;
//	private final double attack_speed;

	public ItemGauntlet(String name) {
		super(name);
		this.setMaxDamage(1000);
		this.setCreativeTab(MetallurgyTabs.tabSpecial);
		this.setNoRepair();
		this.setMaxStackSize(1);
	}

	@Override
    public void registerItemModel()
    {
        super.registerItemModel("gadget");
    }

    @Override
	public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
		List<Enchantment> list = Lists.newArrayList(e);
		
		return list.contains(enchantment);
	}
	
	@SuppressWarnings("deprecation")
	public Multimap<String, AttributeModifier> getItemAttributeModifiers(EntityEquipmentSlot equipmentSlot)
	{
		Multimap<String, AttributeModifier> multimap = super.getItemAttributeModifiers(equipmentSlot);

		if (equipmentSlot == EntityEquipmentSlot.MAINHAND)
		{
			multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Gauntlet modifier", (double)3D, 0));;
			multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Tool modifier", (double)30D, 0));
		}
		return multimap;
	}

	@Override
	public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack) {
		
		ItemStack mainHand = entityLiving.getHeldItemMainhand();
		ItemStack offHand = entityLiving.getHeldItemOffhand();
		
		if(offHand.getItem().equals(this) && mainHand.getItem().equals(this) && entityLiving.swingingHand != null && entityLiving.swingingHand == EnumHand.MAIN_HAND)
		{
			if (!entityLiving.isSwingInProgress || entityLiving.swingProgressInt >= getArmSwingAnimationEnd(entityLiving) / 2 || entityLiving.swingProgressInt < 0)
	        {
				entityLiving.swingProgressInt = -1;
				entityLiving.isSwingInProgress = true;
				entityLiving.swingingHand = EnumHand.OFF_HAND;

	            if (entityLiving.world instanceof WorldServer)
	            {
	                ((WorldServer)entityLiving.world).getEntityTracker().sendToTracking(entityLiving, new SPacketAnimation(entityLiving, 3));
	            }
	        }
			return true;
		}
		
		return super.onEntitySwing(entityLiving, stack);
	}
	
	private int getArmSwingAnimationEnd(EntityLivingBase entity){
		
        if (entity.isPotionActive(MobEffects.HASTE))
        {
            return 6 - (1 + entity.getActivePotionEffect(MobEffects.HASTE).getAmplifier());
        }
        else
        {
            return entity.isPotionActive(MobEffects.MINING_FATIGUE) ? 6 + (1 + entity.getActivePotionEffect(MobEffects.MINING_FATIGUE).getAmplifier()) * 2 : 6;
        }
    }
	
//	Questo metodo controlla se l'attacker è in creative, altrimenti danneggia l'oggetto di 1
	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
		stack.damageItem(1, attacker);
		return super.hitEntity(stack, target, attacker);
	}
}
