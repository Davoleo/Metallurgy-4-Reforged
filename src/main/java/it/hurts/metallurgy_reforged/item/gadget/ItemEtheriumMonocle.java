/*==============================================================================
 = Class: ItemEtheriumMonocle
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.item.gadget;

import it.hurts.metallurgy_reforged.item.ItemExtra;
import it.hurts.metallurgy_reforged.util.MetallurgyTabs;
import it.hurts.metallurgy_reforged.util.Utils;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

@ParametersAreNonnullByDefault
public class ItemEtheriumMonocle extends ItemExtra {

	public ItemEtheriumMonocle()
	{
		super("etherium_goggles", MetallurgyTabs.tabSpecial, "gadget");
	}

	@Override
	public boolean isValidArmor(ItemStack stack, EntityEquipmentSlot armorType, Entity entity) {
		if (stack.getItem() == this && entity instanceof EntityPlayer) {
			return armorType == EntityEquipmentSlot.HEAD;
		} else
			return false;
	}

	@Override
	public void addInformation(@Nonnull ItemStack stack, @Nullable World worldIn, @Nonnull List<String> tooltip, @Nonnull ITooltipFlag flagIn) {
		tooltip.add(Utils.localizeEscapingCustomSequences("tooltip.metallurgy.etherium_goggles"));
	}
}
