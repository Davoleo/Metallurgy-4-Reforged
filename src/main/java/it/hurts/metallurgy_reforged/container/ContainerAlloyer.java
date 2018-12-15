package it.hurts.metallurgy_reforged.container;

import it.hurts.metallurgy_reforged.container.slot.SlotAlloyerOutput;
import it.hurts.metallurgy_reforged.tileentity.TileEntityAlloyer;
import it.hurts.metallurgy_reforged.util.recipe.BlockAlloyerRecipes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;

/***************************
 *
 * Author : ItHurtsLikeHell
 * Project: Metallurgy-5
 * Date   : 24 set 2018
 * Time   : 19:12:47
 *
 * Reworked by Davoleo
 ***************************/
public class ContainerAlloyer extends Container {

    private final IInventory alloyer;
    private int alloyingTime, totalAlloyingTime, burnTime, currentBurnTime;

    //Default values for player inventory, edit iStart only
    public static final int iStart = 4, iEnd = iStart + 26, hStart = iEnd + 1, hEnd = hStart + 8;

    public ContainerAlloyer(InventoryPlayer playerInv, IInventory alloyerInv) {
        this.alloyer = alloyerInv;
//    	playerInventory, Invenotry, Index, X, Y
        this.addSlotToContainer(new Slot(alloyerInv, 0, 102, -8));    //Input
        this.addSlotToContainer(new Slot(alloyerInv, 1, 123, -8));    //Input
        this.addSlotToContainer(new SlotFurnaceFuel(alloyerInv, 2, 111, 48));   //Fuel
        this.addSlotToContainer(new SlotAlloyerOutput(playerInv.player, alloyerInv, 3, 57, 42)); //Result 1

//        Collegamento all'inventario del player
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 9; x++) {
                this.addSlotToContainer(new Slot(playerInv, x + y * 9 + 9, 8 + x * 18, 84 + y * 18));
            }
        }

//        Collegamento all'inventario della hotbar
        for (int x = 0; x < 9; x++) {
            this.addSlotToContainer(new Slot(playerInv, x, 8 + x * 18, 142));
        }
    }

    //    Errore all'add Listener
    public void addListener(IContainerListener listener) {
        super.addListener(listener);
        listener.sendAllWindowProperties(this, this.alloyer);
    }

    //    Da qui non so come gestirmi
    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();

        for (IContainerListener listener : this.listeners) {
            if (this.alloyingTime != this.alloyer.getField(2))
                listener.sendWindowProperty(this, 2, this.alloyer.getField(2));
            if (this.burnTime != this.alloyer.getField(0))
                listener.sendWindowProperty(this, 0, this.alloyer.getField(0));
            if (this.currentBurnTime != this.alloyer.getField(1))
                listener.sendWindowProperty(this, 1, this.alloyer.getField(1));
            if (this.totalAlloyingTime != this.alloyer.getField(3))
                listener.sendWindowProperty(this, 3, this.alloyer.getField(3));
        }

        this.alloyingTime = this.alloyer.getField(2);
        this.burnTime = this.alloyer.getField(0);
        this.currentBurnTime = this.alloyer.getField(1);
        this.totalAlloyingTime = this.alloyer.getField(3);

    }

    @Override
    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int id, int data) {
        this.alloyer.setField(id, data);
    }

    @Override
    public boolean canInteractWith(@Nonnull EntityPlayer playerIn) {
        return this.alloyer.isUsableByPlayer(playerIn);
    }

    @Nonnull
    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
        Slot slot = this.inventorySlots.get(index);
        ItemStack ret = slot.getStack();

        if (/*slot != null &&*/ slot.getHasStack()) {
            ItemStack itemstack = slot.getStack();
            ret = itemstack.copy();

            if (TileEntityAlloyer.SlotEnum.OUTPUT_SLOT.contains(index)) {
                slot.onTake(playerIn, itemstack); //XP
                if (!this.mergeItemStack(itemstack, iStart, hEnd + 1, true)) {
                    return ItemStack.EMPTY;
                }
                slot.onSlotChange(itemstack, ret);
            } else if (index >= iStart) {
                //Check for valid alloy recipes
                if (BlockAlloyerRecipes.getInstance().isAlloyMetal(itemstack)) {
                    if(this.inventorySlots.get(0).getHasStack() == this.inventorySlots.get(1).getHasStack()) {
                        if (!this.mergeItemStack(itemstack, 0, 2, false)) {
                            return ItemStack.EMPTY;
                        }
                    } else if(this.inventorySlots.get(0).getHasStack()) {
                        if (!this.mergeItemStack(itemstack, 0, 1, false)) {
                            if (!BlockAlloyerRecipes.getInstance().getAlloyResult(itemstack, this.inventorySlots.get(0).getStack()).isEmpty()) {
                                if (!this.mergeItemStack(itemstack, 1, 2, false)) {
                                    return ItemStack.EMPTY;
                                }
                            }
                        }
                    } else if(this.inventorySlots.get(1).getHasStack()) {
                        if (!this.mergeItemStack(itemstack, 1, 2, false)) {
                            if (!BlockAlloyerRecipes.getInstance().getAlloyResult(itemstack, this.inventorySlots.get(1).getStack()).isEmpty()) {
                                if (!this.mergeItemStack(itemstack, 0, 1, false)) {
                                    return ItemStack.EMPTY;
                                }
                            }
                        }
                    }
                } else if (TileEntityAlloyer.isItemFuel(itemstack)) {
                    if (!this.mergeItemStack(itemstack, 2, 3, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (/*index >= iStart && */index <= iEnd) {
                    if (!this.mergeItemStack(itemstack, hStart, hEnd + 1, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (/*index >= hStart &&*/ index < hEnd + 1 && !this.mergeItemStack(itemstack, iStart, iEnd + 1, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.mergeItemStack(itemstack, iStart, hEnd + 1, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack.isEmpty()) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }

            if (itemstack.getCount() == ret.getCount()) {
                return ItemStack.EMPTY;
            }
            slot.onTake(playerIn, itemstack);
        }
        return ret;
    }
}
