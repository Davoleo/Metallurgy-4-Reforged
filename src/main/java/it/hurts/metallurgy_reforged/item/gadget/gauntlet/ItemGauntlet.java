/*==============================================================================
 = Class: ItemGauntlet
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.item.gadget.gauntlet;

import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import it.hurts.metallurgy_reforged.config.GadgetsConfig;
import it.hurts.metallurgy_reforged.item.ItemExtra;
import it.hurts.metallurgy_reforged.util.Constants;
import it.hurts.metallurgy_reforged.util.MetallurgyTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.SPacketAnimation;
import net.minecraft.util.EnumHand;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import java.util.List;

public class ItemGauntlet extends ItemExtra {

	//	private final double attack_damage = 1.05;
	//	private final double attack_speed;

	public ItemGauntlet(String name)
	{
		super(name, MetallurgyTabs.tabSpecial, "gadget");
		this.setMaxDamage(GadgetsConfig.Gauntlet.gauntletMaxDamage);
		this.setNoRepair();
		this.setMaxStackSize(1);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public boolean hasEffect(@Nonnull ItemStack stack)
	{
		return super.hasEffect(stack) || stack.hasTagCompound() && stack.getTagCompound().getBoolean("hasEffect");
	}

	@Override
	public boolean canApplyAtEnchantingTable(@Nonnull ItemStack stack, @Nonnull Enchantment enchantment)
	{
		List<Enchantment> list = Lists.newArrayList(Constants.GAUNTLET_ENCHANTMENTS);

		return list.contains(enchantment);
	}

	@Nonnull
	@Override
	public Multimap<String, AttributeModifier> getAttributeModifiers(@Nonnull EntityEquipmentSlot equipmentSlot, @Nonnull ItemStack stack)
	{
		Multimap<String, AttributeModifier> multimap = super.getAttributeModifiers(equipmentSlot, stack);

		if (equipmentSlot == EntityEquipmentSlot.MAINHAND)
		{
			multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Gauntlet Damage modifier", GadgetsConfig.Gauntlet.gauntletAttackDamage, 0));
			multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Gauntlet Speed modifier", GadgetsConfig.Gauntlet.gauntletAttackSpeed, 0));
		}
		return multimap;
	}

	@Override
	public boolean onEntitySwing(EntityLivingBase entityLiving, @Nonnull ItemStack stack)
	{

		ItemStack mainHand = entityLiving.getHeldItemMainhand();
		ItemStack offHand = entityLiving.getHeldItemOffhand();

		if (offHand.getItem().equals(this) && mainHand.getItem().equals(this) && entityLiving.swingingHand == EnumHand.MAIN_HAND)
		{
			if (!entityLiving.isSwingInProgress || entityLiving.swingProgressInt >= getArmSwingAnimationEnd(entityLiving) / 2 || entityLiving.swingProgressInt < 0)
			{
				entityLiving.swingProgressInt = -1;
				entityLiving.isSwingInProgress = true;
				entityLiving.swingingHand = EnumHand.OFF_HAND;

				if (entityLiving.world instanceof WorldServer)
				{
					((WorldServer) entityLiving.world).getEntityTracker().sendToTracking(entityLiving, new SPacketAnimation(entityLiving, 3));
				}
			}
			return true;
		}

		return super.onEntitySwing(entityLiving, stack);
	}

	private int getArmSwingAnimationEnd(EntityLivingBase entity)
	{

		if (entity.isPotionActive(MobEffects.HASTE))
		{
			return 6 - (1 + entity.getActivePotionEffect(MobEffects.HASTE).getAmplifier());
		}
		else
		{
			return entity.isPotionActive(MobEffects.MINING_FATIGUE) ? 6 + (1 + entity.getActivePotionEffect(MobEffects.MINING_FATIGUE).getAmplifier()) * 2 : 6;
		}
	}

	@Override
	public boolean hitEntity(@Nonnull ItemStack stack, @Nonnull EntityLivingBase target, @Nonnull EntityLivingBase attacker)
	{
		if (attacker instanceof EntityPlayerMP)
		{
			if (Item.itemRand.nextBoolean())
				stack.damageItem(1, attacker);
			else
				attacker.getHeldItemOffhand().damageItem(1, attacker);
		}
		return super.hitEntity(stack, target, attacker);
	}

}
