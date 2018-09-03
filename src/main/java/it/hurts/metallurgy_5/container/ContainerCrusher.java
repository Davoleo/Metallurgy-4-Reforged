package it.hurts.metallurgy_5.container;


import it.hurts.metallurgy_5.tileentity.TileEntityCrusher;
import it.hurts.metallurgy_5.util.recipe.BlockCrusherRecipes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

/*************************************************
 * Author: Davoleo
 * Date: 03/09/2018
 * Hour: 21.55
 * Project: Metallurgy_5
 * Copyright - © - Davoleo - 2018
 **************************************************/

public class ContainerCrusher extends Container {

    private TileEntityCrusher crusher;
    private int crushTime, totalCrushTime, burnTime, currentBurnTime;

    public ContainerCrusher(InventoryPlayer playerInv, final TileEntityCrusher crusher)
    {
        this.crusher = crusher;
        IItemHandler inventory = crusher.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.NORTH);

        addSlotToContainer(new SlotItemHandler(inventory, 0, 26, 11));  //Input
        addSlotToContainer(new SlotItemHandler(inventory, 1, 7, 35));   //Fuel
        addSlotToContainer(new SlotItemHandler(inventory, 2, 81, 36)); //Result

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
        return true;
    }


    //TODO : quick stack transfer
    /*@Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index)
    {
        ItemStack stack = ItemStack.EMPTY;
        Slot slot = (Slot)this.inventorySlots.get(index);

        if(slot != null && slot.getHasStack())
        {
            ItemStack stack1 = slot.getStack();
            stack = stack1.copy();

            if(index == 3)
            {
                if(!this.mergeItemStack(stack1, 4, 40, true)) return ItemStack.EMPTY;
                slot.onSlotChange(stack1, stack);
            }
            else if(index != 2 && index != 1 && index != 0)
            {
                Slot slot1 = (Slot)this.inventorySlots.get(index + 1);

                if(!BlockCrusherRecipes.getInstance().getCrushingResult(stack1, slot1.getStack()).isEmpty())
                {
                    if(!this.mergeItemStack(stack1, 0, 2, false))
                    {
                        return ItemStack.EMPTY;
                    }
                    else if(BlockCrusherRecipes.isItemFuel(stack1))
                    {
                        if(!this.mergeItemStack(stack1, 2, 3, false)) return ItemStack.EMPTY;
                    }
                    else if(TileEntitySinteringFurnace.isItemFuel(stack1))
                    {
                        if(!this.mergeItemStack(stack1, 2, 3, false)) return ItemStack.EMPTY;
                    }
                    else if(TileEntitySinteringFurnace.isItemFuel(stack1))
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
*/
}
