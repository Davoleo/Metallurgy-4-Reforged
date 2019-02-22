package it.hurts.metallurgy_reforged.util.custom_slot;

import javax.annotation.Nullable;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/***************************
 *
 * Author : ItHurtsLikeHell
 * Project: Metallurgy-4-Reforged
 * Date   : 04 gen 2019
 * Time   : 15:01:29
 *
 ***************************/
public class OffHandCustomSlot extends Slot{

	public EntityPlayer player;
	  
	public OffHandCustomSlot(EntityPlayer pl) {
		super(pl.inventory, 40, 77, 62);
	}
	
	@Nullable
    @SideOnly(Side.CLIENT)
    public String getSlotTexture(){
        return "minecraft:items/empty_armor_slot_shield";
    }
	
	 public boolean canTakeStack(EntityPlayer playerIn){
		 return false;
	}
}
