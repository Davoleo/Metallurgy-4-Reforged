package it.hurts.metallurgy_reforged.util.handler.event;

import java.util.ArrayList;

import it.hurts.metallurgy_reforged.config.ToolEffectsConfig;
import it.hurts.metallurgy_reforged.item.tool.ModTools;
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.util.Utils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class Pickaxe {
	
	@SubscribeEvent
	public static void onBreakBlock(PlayerEvent.BreakSpeed event)
	{
		EntityPlayer pl = event.getEntityPlayer();
		ItemStack mainHandStack = pl.getHeldItemMainhand();

		if(pl.isInWater()
				&& mainHandStack.isItemEqualIgnoreDurability(new ItemStack(ModTools.deep_iron_pickaxe))
				&& ToolEffectsConfig.deepIronPickaxeEffect)
			event.setNewSpeed(6F);
//				set tools break speed based on light except for hoe and sword
				if(ToolEffectsConfig.shadowSteelToolSpeedEffect && Utils.isItemStackASpecificToolMaterial(ModMetals.SHADOW_STEEL, mainHandStack,"hoe","sword")) {
					float percentage = Utils.getLightArmorPercentage(pl,100F);
					float speed = event.getNewSpeed()  * percentage / 40F;
					event.setNewSpeed(event.getOriginalSpeed() + speed);
				}
	}
	
	@SubscribeEvent
    public static void drop(BlockEvent.HarvestDropsEvent ev)
    {
		if(ev.getHarvester() instanceof EntityPlayer) {
            EntityPlayer pl = ev.getHarvester();
		
	            if(Utils.isItemStackASpecificToolMaterial(ModMetals.MIDASIUM, pl.getHeldItemMainhand())){
		            ArrayList<ItemStack> drops = new ArrayList<>();
		
		            if((int) (Math.random() * 100) <= 50) {
			            for(ItemStack stack : ev.getDrops())
			            {
			            	if(stack != null)
			            		drops.add(stack);
			            }
			            ev.getDrops().addAll(drops);
		            }
	            }
		}
    }

}
