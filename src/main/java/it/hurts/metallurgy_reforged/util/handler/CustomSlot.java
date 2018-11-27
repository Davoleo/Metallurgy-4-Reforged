package it.hurts.metallurgy_reforged.util.handler;

import javax.annotation.Nullable;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class CustomSlot extends Slot{

	 private static final EntityEquipmentSlot[] VALID_EQUIPMENT_SLOTS = new EntityEquipmentSlot[] {EntityEquipmentSlot.HEAD, EntityEquipmentSlot.CHEST, EntityEquipmentSlot.LEGS, EntityEquipmentSlot.FEET};
		
	public final boolean locked;
	private final int armorIndex;
	public EntityPlayer player;
	  
	public CustomSlot(EntityPlayer pl,int armorIndex,boolean locked) {
		super(pl.inventory, 36 + (3 - armorIndex), 8, 8 + armorIndex * 18);
		this.locked = locked;
		this.armorIndex = armorIndex;
	}
	
	/**
     * Returns the maximum stack size for a given slot (usually the same as getInventoryStackLimit(), but 1
     * in the case of armor slots)
     */
    public int getSlotStackLimit()
    {
        return 1;
    }
    /**
     * Check if the stack is allowed to be placed in this slot, used for armor slots as well as furnace
     * fuel.
     */
    public boolean isItemValid(ItemStack stack)
    {
    	 return stack.getItem().isValidArmor(stack, VALID_EQUIPMENT_SLOTS[armorIndex], player);
    }
    /**
     * Return whether this slot's stack can be taken from this slot.
     */
    public boolean canTakeStack(EntityPlayer playerIn)
    {
    	
    	if(!this.locked)
    	{
        ItemStack itemstack = this.getStack();
        return !itemstack.isEmpty() && !playerIn.isCreative() && EnchantmentHelper.hasBindingCurse(itemstack) ? false : super.canTakeStack(playerIn);
    	}
    	else
    	{
    		return false;
    	}
    	}

	
}
