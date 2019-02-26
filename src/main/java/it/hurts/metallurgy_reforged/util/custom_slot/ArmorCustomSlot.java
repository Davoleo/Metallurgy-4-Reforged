/*
 * -------------------------------------------------------------------------------------------------------
 * Class: ArmorCustomSlot
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2019.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.util.custom_slot;

import javax.annotation.Nullable;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ArmorCustomSlot extends Slot{

	public final boolean locked;
	private final int armorIndex;
	public EntityPlayer player;

	public ArmorCustomSlot(EntityPlayer pl,int armorIndex,boolean locked) {
		super(pl.inventory, 36 + (3 - armorIndex), 8, 8 + armorIndex * 18);
		this.locked = locked;
		this.armorIndex = armorIndex;
	}

	/**
	 * Returns the maximum stack size for a given slot (usually the same as getInventoryStackLimit(), but 1
	 * in the case of armor slots)
	 */
	public int getSlotStackLimit(){
		return 1;
	}
	
	/**
	 * Check if the stack is allowed to be placed in this slot, used for armor slots as well as furnace
	 * fuel.
	 */
	public boolean isItemValid(ItemStack stack){
		return true;
	}
	
	/**
	 * Return whether this slot's stack can be taken from this slot.
	 */
	public boolean canTakeStack(EntityPlayer playerIn){

		if(!this.locked) {
			ItemStack itemstack = this.getStack();
			return !itemstack.isEmpty() && !playerIn.isCreative() && EnchantmentHelper.hasBindingCurse(itemstack) ? false : super.canTakeStack(playerIn);
		}else
			return false;
	}

	public ItemStack decrStackSize(int amount) {
		return this.locked ? ItemStack.EMPTY : super.decrStackSize(amount);
	}

	@Nullable
	@SideOnly(Side.CLIENT)
	public String getSlotTexture(){
		return ItemArmor.EMPTY_SLOT_NAMES[3 - armorIndex];
	}

}
