/*
 * -------------------------------------------------------------------------------------------------------
 * Class: ContainerCrusher
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 * Copyright (c) 2020.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.container;


import it.hurts.metallurgy_reforged.container.slot.SlotCrusherOutput;
import it.hurts.metallurgy_reforged.tileentity.TileEntityCrusher;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;

public class ContainerCrusher extends Container {

	private final IInventory crusher;
	private int crushTime, burnTime, totalBurnTime;

	public ContainerCrusher(InventoryPlayer playerInv, IInventory crusherInv)
	{
		this.crusher = crusherInv;

		//Input
		this.addSlotToContainer(new Slot(crusherInv, 0, 61, -4));
		//Fuel
		this.addSlotToContainer(new SlotFurnaceFuel(crusherInv, 1, 129, 48));
		//Output 1
		this.addSlotToContainer(new SlotCrusherOutput(playerInv.player, crusherInv, 2, 67, 36));
		//Output 2
		this.addSlotToContainer(new SlotCrusherOutput(playerInv.player, crusherInv, 3, 48, 36));
		//Output 3
		this.addSlotToContainer(new SlotCrusherOutput(playerInv.player, crusherInv, 4, 29, 36));

		//Player Inventory Slots
		for (int y = 0; y < 3; y++)
		{
			for (int x = 0; x < 9; x++)
			{
				this.addSlotToContainer(new Slot(playerInv, x + y * 9 + 9, 8 + x * 18, 84 + y * 18));
			}
		}

		//Player Hotbar slots
		for (int x = 0; x < 9; x++)
		{
			this.addSlotToContainer(new Slot(playerInv, x, 8 + x * 18, 142));
		}
	}

	/**
	 * Synchronizes TE items
	 */
	public void addListener(@Nonnull IContainerListener listener)
	{
		super.addListener(listener);
		listener.sendAllWindowProperties(this, this.crusher);
	}

	/**
	 * Synchronizes TE fields with the client
	 */
	@Override
	public void detectAndSendChanges()
	{
		super.detectAndSendChanges();

		for (IContainerListener listener : this.listeners)
		{
			if (this.crushTime != this.crusher.getField(2))
				listener.sendWindowProperty(this, 2, this.crusher.getField(2));
			if (this.burnTime != this.crusher.getField(0))
				listener.sendWindowProperty(this, 0, this.crusher.getField(0));
			if (this.totalBurnTime != this.crusher.getField(1))
				listener.sendWindowProperty(this, 1, this.crusher.getField(1));
		}

		this.crushTime = this.crusher.getField(2);
		this.burnTime = this.crusher.getField(0);
		this.totalBurnTime = this.crusher.getField(1);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int id, int data)
	{
		this.crusher.setField(id, data);
	}

	@Override
	public boolean canInteractWith(@Nonnull EntityPlayer playerIn)
	{
		return this.crusher.isUsableByPlayer(playerIn);
	}

	/**
	 * Handles shift clicking in the GUI slots
	 */
	@Nonnull
	@Override
	public ItemStack transferStackInSlot(@Nonnull EntityPlayer player, int index)
	{
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = inventorySlots.get(index);

		if (slot != null && slot.getHasStack())
		{
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();

			int containerSlots = inventorySlots.size() - player.inventory.mainInventory.size();

			if (index < containerSlots)
			{
				if (!this.mergeItemStack(itemstack1, containerSlots, inventorySlots.size(), true))
					return ItemStack.EMPTY;

				if (TileEntityCrusher.Slots.OUTPUT_SLOT.contains(index))
					slot.onTake(player, itemstack);
			}
			else if (!this.mergeItemStack(itemstack1, 0, containerSlots, false))
				return ItemStack.EMPTY;

			if (itemstack1.isEmpty())
				slot.putStack(ItemStack.EMPTY);
			else
				slot.onSlotChanged();

			if (itemstack1.getCount() == itemstack.getCount())
				return ItemStack.EMPTY;

			slot.onTake(player, itemstack1);
		}

		return itemstack;
	}
}