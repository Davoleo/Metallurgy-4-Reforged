/*
 * -------------------------------------------------------------------------------------------------------
 * Class: ContainerAlloyer
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2019.
 * --------------------------------------------------------------------------------------------------------
 */

//package it.hurts.metallurgy_reforged.container;
//
//import it.hurts.metallurgy_reforged.container.slot.SlotAlloyerOutput;
//import it.hurts.metallurgy_reforged.recipe.BlockAlloyerRecipes;
//import it.hurts.metallurgy_reforged.util.Utils;
//import net.minecraft.entity.player.PlayerEntity;
//import net.minecraft.inventory.*;
//import net.minecraft.inventory.container.Container;
//import net.minecraft.inventory.container.FurnaceFuelSlot;
//import net.minecraft.inventory.container.Slot;
//import net.minecraft.item.ItemStack;
//import net.minecraftforge.api.distmarker.Dist;
//import net.minecraftforge.api.distmarker.OnlyIn;
//
//import javax.annotation.Nonnull;
//
//public class ContainerAlloyer extends Container {
//
//    private final IInventory alloyer;
//    private int alloyingTime, totalAlloyingTime, burnTime, currentBurnTime;
//
//    //Default values for player inventory, edit iStart only
//    public static final int iStart = 4, iEnd = iStart + 26, hStart = iEnd + 1, hEnd = hStart + 8;
//
//    public ContainerAlloyer(InventoryPlayer playerInv, IInventory alloyerInv) {
//        this.alloyer = alloyerInv;
////    	playerInventory, Invenotry, Index, X, Y
//        this.addSlot(new Slot(alloyerInv, 0, 102, -8));    //Input
//        this.addSlot(new Slot(alloyerInv, 1, 123, -8));    //Input
//        this.addSlot(new FurnaceFuelSlot(null, alloyerInv, 2, 111, 48));   //Fuel
//        this.addSlot(new SlotAlloyerOutput(playerInv.player, alloyerInv, 3, 57, 42)); //Result 1
//
////        Collegamento all'inventario del player
//        for (int y = 0; y < 3; y++) {
//            for (int x = 0; x < 9; x++) {
//                this.addSlot(new Slot(playerInv, x + y * 9 + 9, 8 + x * 18, 84 + y * 18));
//            }
//        }
//
////        Collegamento all'inventario della hotbar
//        for (int x = 0; x < 9; x++) {
//            this.addSlot(new Slot(playerInv, x, 8 + x * 18, 142));
//        }
//    }
//
//    //    Errore all'add Listener
//    public void addListener(IContainerListener listener) {
//        super.addListener(listener);
//        listener.sendAllWindowProperties(this, this.alloyer);
//    }
//
//    //    Da qui non so come gestirmi
//    @Override
//    public void detectAndSendChanges() {
//        super.detectAndSendChanges();
//
//        for (IContainerListener listener : this.listeners) {
//            if (this.alloyingTime != this.alloyer.getField(2))
//                listener.sendWindowProperty(this, 2, this.alloyer.getField(2));
//            if (this.burnTime != this.alloyer.getField(0))
//                listener.sendWindowProperty(this, 0, this.alloyer.getField(0));
//            if (this.currentBurnTime != this.alloyer.getField(1))
//                listener.sendWindowProperty(this, 1, this.alloyer.getField(1));
//            if (this.totalAlloyingTime != this.alloyer.getField(3))
//                listener.sendWindowProperty(this, 3, this.alloyer.getField(3));
//        }
//
//        this.alloyingTime = this.alloyer.getField(2);
//        this.burnTime = this.alloyer.getField(0);
//        this.currentBurnTime = this.alloyer.getField(1);
//        this.totalAlloyingTime = this.alloyer.getField(3);
//
//    }
//
//    @Override
//    @OnlyIn(Dist.CLIENT)
//    public void updateProgressBar(int id, int data) {
//        this.alloyer.setField(id, data);
//    }
//
//    @Override
//    public boolean canInteractWith(@Nonnull PlayerEntity playerIn) {
//        return this.alloyer.isUsableByPlayer(playerIn);
//    }
//
//
//    //-------------------- GENERAL SHIFT-CLICK METHODS --------------------
//    @Nonnull
//    @Override
//    public ItemStack transferStackInSlot(PlayerEntity player, int index) {
//        //Final Instance of the clicked item -
//        ItemStack itemstack = ItemStack.EMPTY;
//        Slot slot = inventorySlots.get(index);
//
//        if (slot != null && slot.getHasStack()) {
//            ItemStack itemstack1 = slot.getStack();
//
//            itemstack = itemstack1.copy();
//
//            int containerSlots = inventorySlots.size() - player.inventory.mainInventory.size();
//
//            if (index < containerSlots) {
//                if (!this.mergeItemStack(itemstack1, containerSlots, inventorySlots.size(), true)) {
//                    return ItemStack.EMPTY;
//                } else
//                    Utils.giveExperience(player, itemstack.getCount() * BlockAlloyerRecipes.getInstance().getAlloyExperience(itemstack));
//
//            } else if (!this.mergeItemStack(itemstack1, 0, containerSlots, false)) {
//                return ItemStack.EMPTY;
//            }
//
//            if (itemstack1.isEmpty()) {
//                slot.putStack(ItemStack.EMPTY);
//            } else {
//                slot.onSlotChanged();
//            }
//
//            if (itemstack1.getCount() == itemstack.getCount()) {
//                return ItemStack.EMPTY;
//            }
//
//            slot.onTake(player, itemstack1);
//        }
//
//        return itemstack;
//    }
//
//    @Override
//    protected boolean mergeItemStack(ItemStack stack, int startIndex, int endIndex, boolean reverseDirection) {
//        boolean flag = false;
//        int i = startIndex;
//
//        if (reverseDirection) {
//            i = endIndex - 1;
//        }
//
//        if (stack.isStackable()) {
//            while (stack.getCount() > 0 && (!reverseDirection && i < endIndex || reverseDirection && i >= startIndex)) {
//                Slot slot = this.inventorySlots.get(i);
//                ItemStack itemstack = slot.getStack();
//
//                if (slot.isItemValid(stack)) {
//                    if (!itemstack.isEmpty() && itemstack.getItem() == stack.getItem() && (!stack.getHasSubtypes() || stack.getMetadata() == itemstack.getMetadata()) && ItemStack.areItemStackTagsEqual(stack, itemstack)) {
//                        int j = itemstack.getCount() + stack.getCount();
//
//                        if (j <= stack.getMaxStackSize()) {
//                            stack.setCount(0);
//                            itemstack.setCount(j);
//                            slot.onSlotChanged();
//                            flag = true;
//                        } else if (itemstack.getCount() < stack.getMaxStackSize()) {
//                            stack.shrink(stack.getMaxStackSize() - itemstack.getCount());
//                            itemstack.setCount(stack.getMaxStackSize());
//                            slot.onSlotChanged();
//                            flag = true;
//                        }
//                    }
//                }
//
//                if (reverseDirection) {
//                    --i;
//                } else {
//                    ++i;
//                }
//            }
//        }
//
//        if (stack.getCount() > 0) {
//            if (reverseDirection) {
//                i = endIndex - 1;
//            } else {
//                i = startIndex;
//            }
//
//            while (!reverseDirection && i < endIndex || reverseDirection && i >= startIndex) {
//                Slot slot1 = (Slot)this.inventorySlots.get(i);
//                ItemStack itemstack1 = slot1.getStack();
//
//                // Forge: Make sure to respect isItemValid in the slot.
//                if (itemstack1.isEmpty() && slot1.isItemValid(stack)) {
//                    slot1.putStack(stack.copy());
//                    slot1.onSlotChanged();
//                    stack.setCount(0);
//                    flag = true;
//                    break;
//                }
//
//                if (reverseDirection) {
//                    --i;
//                } else {
//                    ++i;
//                }
//            }
//        }
//
//        return flag;
//    }
//
//
//}
