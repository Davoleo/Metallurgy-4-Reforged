/*==============================================================================
 = Class: ContainerAlloyer
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2020.
 =============================================================================*/

package it.hurts.metallurgy_reforged.container;

import it.hurts.metallurgy_reforged.container.slot.SlotAlloyerOutput;
import it.hurts.metallurgy_reforged.tileentity.TileEntityAlloyer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;

public class ContainerAlloyer extends Container {

	private final IInventory alloyer;
	private int alloyingTime, burnTime, currentBurnTime;

	public ContainerAlloyer(InventoryPlayer playerInv, IInventory alloyerInv)
	{
		this.alloyer = alloyerInv;
		//input1
		this.addSlotToContainer(new Slot(alloyerInv, 0, 102, -8));
		//input2
		this.addSlotToContainer(new Slot(alloyerInv, 1, 123, -8));
		//Fuel
		this.addSlotToContainer(new SlotFurnaceFuel(alloyerInv, 2, 111, 48));
		//output
		this.addSlotToContainer(new SlotAlloyerOutput(playerInv.player, alloyerInv, 3, 57, 42));

		//Add player main inventory slots
		for (int y = 0; y < 3; y++)
		{
			for (int x = 0; x < 9; x++)
			{
				this.addSlotToContainer(new Slot(playerInv, x + y * 9 + 9, 8 + x * 18, 84 + y * 18));
			}
		}

		//Add player hotbar slots
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
		listener.sendAllWindowProperties(this, this.alloyer);
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
			if (this.alloyingTime != this.alloyer.getField(2))
				listener.sendWindowProperty(this, 2, this.alloyer.getField(2));
			if (this.burnTime != this.alloyer.getField(0))
				listener.sendWindowProperty(this, 0, this.alloyer.getField(0));
			if (this.currentBurnTime != this.alloyer.getField(1))
				listener.sendWindowProperty(this, 1, this.alloyer.getField(1));
		}

		this.alloyingTime = this.alloyer.getField(2);
		this.burnTime = this.alloyer.getField(0);
		this.currentBurnTime = this.alloyer.getField(1);
	}

	/**
	 * Called on the client side when the packet is received
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int id, int data)
	{
		this.alloyer.setField(id, data);
	}

	@Override
	public boolean canInteractWith(@Nonnull EntityPlayer playerIn)
	{
		return this.alloyer.isUsableByPlayer(playerIn);
	}

	/**
	 * Handles shift clicking in the GUI slots
	 */
	@Nonnull
	@Override
	public ItemStack transferStackInSlot(@Nonnull EntityPlayer player, int index)
	{
		//Final Instance of the clicked item -
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
				{
					return ItemStack.EMPTY;
				}

				if (TileEntityAlloyer.Slots.OUTPUT_SLOT.contains(index))
				{
					slot.onTake(player, itemstack);
				}
			}
			else if (!this.mergeItemStack(itemstack1, 0, containerSlots, false))
			{
				return ItemStack.EMPTY;
			}

			if (itemstack1.isEmpty())
			{
				slot.putStack(ItemStack.EMPTY);
			}
			else
			{
				slot.onSlotChanged();
			}

			if (itemstack1.getCount() == itemstack.getCount())
			{
				return ItemStack.EMPTY;
			}

			slot.onTake(player, itemstack1);
		}

		return itemstack;
	}
}
