package it.hurts.metallurgy_5.container;

import it.hurts.metallurgy_5.container.slot.SlotAlloyerOutput;
import it.hurts.metallurgy_5.tileentity.TileEntityAlloyer;
import it.hurts.metallurgy_5.util.recipe.BlockAlloyerRecipes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

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

    public ContainerAlloyer(InventoryPlayer playerInv, IInventory alloyerInv) {
        this.alloyer = alloyerInv;
        this.addSlotToContainer(new Slot(alloyerInv, 0, 57, 18));	//Input
        this.addSlotToContainer(new Slot(alloyerInv, 1, 57, 52));	//Input
        this.addSlotToContainer(new SlotFurnaceFuel(alloyerInv, 2, 7, 35));   //Fuel
        this.addSlotToContainer(new SlotAlloyerOutput(playerInv.player, alloyerInv, 3, 120, 36)); //Result

//        Collegamento all'inventario del player
        for(int y = 0; y < 3; y++){
            for(int x = 0; x < 9; x++){
                this.addSlotToContainer(new Slot(playerInv, x + y*9 + 9, 8 + x*18, 84 + y*18));
            }
        }

//        Collegamento all'inventario della hotbar
        for(int x = 0; x < 9; x++){
            this.addSlotToContainer(new Slot(playerInv, x, 8 + x * 18, 142));
        }

    }

    //    Errore all'add Listener
    public void addListener(IContainerListener listener){
        super.addListener(listener);
        listener.sendAllWindowProperties(this, this.alloyer);
    }

    //    Da qui non so come gestirmi
    @Override
    public void detectAndSendChanges(){
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
    public void updateProgressBar(int id, int data){
        this.alloyer.setField(id, data);
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn){
        return this.alloyer.isUsableByPlayer(playerIn);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index){
        ItemStack stack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);

        if(slot != null && slot.getHasStack())
        {
            ItemStack stack1 = slot.getStack();
            stack = stack1.copy();

            if(index == 3)
            {
                slot.onTake(playerIn, stack1); //XP
                if(!this.mergeItemStack(stack1, 4, 40, true)) return ItemStack.EMPTY;
                slot.onSlotChange(stack1, stack);
            }
            else if(index != 2 && index != 1 && index != 0)
            {
                Slot slot1 = (Slot)this.inventorySlots.get(index + 1);

                if(!BlockAlloyerRecipes.getInstance().getAlloyResult(stack1, slot1.getStack()).isEmpty())
                {
                    if(!this.mergeItemStack(stack1, 0, 2, false))
                    {
                        return ItemStack.EMPTY;
                    }
                    else if(TileEntityAlloyer.isItemFuel(stack1))
                    {
                        if(!this.mergeItemStack(stack1, 2, 3, false)) return ItemStack.EMPTY;
                    }
                    else if(TileEntityAlloyer.isItemFuel(stack1))
                    {
                        if(!this.mergeItemStack(stack1, 2, 3, false)) return ItemStack.EMPTY;
                    }
                    else if(TileEntityAlloyer.isItemFuel(stack1))
                    {
                        if(!this.mergeItemStack(stack1, 2, 3, false)) return ItemStack.EMPTY;
                    }
                    else if(index >= 4 && index < 31)
                    {
                        if(!this.mergeItemStack(stack1, 31, 40, false)) return ItemStack.EMPTY;
                    }
                    else if(index >= 31 && index < 40 && !this.mergeItemStack(stack1, 4, 31, false))
                    {
                        return ItemStack.EMPTY;
                    }
                }
            }
            else if(!this.mergeItemStack(stack1, 4, 40, false))
            {
                return ItemStack.EMPTY;
            }
            if(stack1.isEmpty())
            {
                slot.putStack(ItemStack.EMPTY);
            }
            else
            {
                slot.onSlotChanged();

            }
            if(stack1.getCount() == stack.getCount()) return ItemStack.EMPTY;
            slot.onTake(playerIn, stack1);
        }
        return stack;
    }

}
