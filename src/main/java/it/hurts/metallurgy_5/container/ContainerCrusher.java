package it.hurts.metallurgy_5.container;


import it.hurts.metallurgy_5.tileentity.TileEntityCrusher;
import it.hurts.metallurgy_5.util.recipe.BlockCrusherRecipes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/*************************************************
 * Author: Davoleo
 * Date: 03/09/2018
 * Hour: 21.55
 * Project: Metallurgy_5
 * Copyright - © - Davoleo - 2018
 **************************************************/

public class ContainerCrusher extends Container {

    private final IInventory crusher;
    private int crushTime, totalCrushTime, burnTime, currentBurnTime;


    public ContainerCrusher(InventoryPlayer playerInv, IInventory crusherInv)
    {
        this.crusher = crusherInv;
        this.addSlotToContainer(new Slot(crusherInv, 0, 57, 35));  //Input
        this.addSlotToContainer(new SlotFurnaceFuel(crusherInv, 1, 7, 35));   //Fuel
        this.addSlotToContainer(new SlotFurnaceOutput(playerInv.player, crusherInv, 2, 120, 36)); //Result

        for(int y = 0; y < 3; y++)
        {
            for(int x = 0; x < 9; x++)
            {
                this.addSlotToContainer(new Slot(playerInv, x + y*9 + 9, 8 + x*18, 84 + y*18));
            }
        }

        for(int x = 0; x < 9; x++)
        {
            this.addSlotToContainer(new Slot(playerInv, x, 8 + x * 18, 142));
        }

    }

    public void addListener(IContainerListener listener)
    {
        super.addListener(listener);
        listener.sendAllWindowProperties(this, this.crusher);
    }

    @Override
    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();

        for(int i=0; i< this.listeners.size(); ++i)
        {
            IContainerListener listener = this.listeners.get(i);

            if(this.crushTime != this.crusher.getField(2))
                listener.sendWindowProperty(this, 2, this.crusher.getField(2));
            if(this.burnTime != this.crusher.getField(0))
                listener.sendWindowProperty(this, 0, this.crusher.getField(0));
            if(this.currentBurnTime != this.crusher.getField(1))
                listener.sendWindowProperty(this, 1, this.crusher.getField(1));
            if(this.totalCrushTime != this.crusher.getField(3))
                listener.sendWindowProperty(this, 3, this.crusher.getField(3));
        }

        this.crushTime = this.crusher.getField(2);
        this.burnTime = this.crusher.getField(0);
        this.currentBurnTime = this.crusher.getField(1);
        this.totalCrushTime = this.crusher.getField(3);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int id, int data)
    {
        this.crusher.setField(id, data);
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn)
    {
        return this.crusher.isUsableByPlayer(playerIn);
    }

    //TODO : Implement Experience [Crusher]
    /*@Override
    public void onContainerClosed(EntityPlayer playerIn)
    {
        playerIn.addExperience(playerIn.get);
    }*/

    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index)
    {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);


        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (index == 2)
            {
                if (!this.mergeItemStack(itemstack1, 3, 39, true))
                {
                    return ItemStack.EMPTY;
                }

                slot.onSlotChange(itemstack1, itemstack);
            }
            else if (index != 1 && index != 0)
            {
                if (!BlockCrusherRecipes.getInstance().getCrushingResult(itemstack1).isEmpty())
                {
                    if (!this.mergeItemStack(itemstack1, 0, 1, false))
                    {
                        return ItemStack.EMPTY;
                    }
                }
                else if (TileEntityCrusher.isItemFuel(itemstack1))
                {
                    if (!this.mergeItemStack(itemstack1, 1, 2, false))
                    {
                        return ItemStack.EMPTY;
                    }
                }
                else if (index >= 3 && index < 30)
                {
                    if (!this.mergeItemStack(itemstack1, 30, 39, false))
                    {
                        return ItemStack.EMPTY;
                    }
                }
                else if (index >= 30 && index < 39 && !this.mergeItemStack(itemstack1, 3, 30, false))
                {
                    return ItemStack.EMPTY;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 3, 39, false))
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

            slot.onTake(playerIn, itemstack1);
        }

        return itemstack;
    }

}