/**
 * 
 */
package it.hurts.metallurgy_5.container;

import it.hurts.metallurgy_5.tileentity.TileEntityAlloyer;
import it.hurts.metallurgy_5.util.recipe.BlockAlloyerRecipes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnaceFuel;
import net.minecraft.inventory.SlotFurnaceOutput;
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
 ***************************/
public class ContainerAlloyer extends Container {

	private final IInventory alloyer;
    private int alloyingTime, totalAlloyingTime, burnTime, currentBurnTime;
    
    public ContainerAlloyer(InventoryPlayer playerInv, IInventory alloyerInv) {
    	this.alloyer = alloyerInv;
        this.addSlotToContainer(new Slot(alloyerInv, 0, 57, 35));	//Input
        this.addSlotToContainer(new Slot(alloyerInv, 3, 100, 35));	//Input
        this.addSlotToContainer(new SlotFurnaceFuel(alloyerInv, 1, 7, 35));   //Fuel
        this.addSlotToContainer(new SlotFurnaceOutput(playerInv.player, alloyerInv, 2, 120, 36)); //Result
        
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

        for(int i=0; i< this.listeners.size(); ++i){
            IContainerListener listener = this.listeners.get(i);

            if(this.alloyingTime != this.alloyer.getField(2))
                listener.sendWindowProperty(this, 2, this.alloyer.getField(2));
            if(this.burnTime != this.alloyer.getField(0))
                listener.sendWindowProperty(this, 0, this.alloyer.getField(0));
            if(this.currentBurnTime != this.alloyer.getField(1))
                listener.sendWindowProperty(this, 1, this.alloyer.getField(1));
            if(this.totalAlloyingTime != this.alloyer.getField(3))
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
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack()){
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (index == 2) {
                if (!this.mergeItemStack(itemstack1, 3, 39, true)){
                    return ItemStack.EMPTY;
                }

                slot.onSlotChange(itemstack1, itemstack);
            }
            else if (index != 1 && index != 0){
                if (!BlockAlloyerRecipes.getInstance().getAlloyingResult(itemstack1).isEmpty()){
                    if (!this.mergeItemStack(itemstack1, 0, 1, false)){
                        return ItemStack.EMPTY;
                    }
                }
                else if (TileEntityAlloyer.isItemFuel(itemstack1)){
                    if (!this.mergeItemStack(itemstack1, 1, 2, false)){
                        return ItemStack.EMPTY;
                    }
                }
                else if (index >= 3 && index < 30){
                    if (!this.mergeItemStack(itemstack1, 30, 39, false)){
                        return ItemStack.EMPTY;
                    }
                }
                else if (index >= 30 && index < 39 && !this.mergeItemStack(itemstack1, 3, 30, false)){
                    return ItemStack.EMPTY;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 3, 39, false)){
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()){
                slot.putStack(ItemStack.EMPTY);
            }
            else{
                slot.onSlotChanged();
            }

            if (itemstack1.getCount() == itemstack.getCount()){
                return ItemStack.EMPTY;
            }

            slot.onTake(playerIn, itemstack1);
        }

        return itemstack;
    }
    
}
