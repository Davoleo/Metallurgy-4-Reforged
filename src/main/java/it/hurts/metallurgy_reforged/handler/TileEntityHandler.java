/*
 * -------------------------------------------------------------------------------------------------------
 * Class: TileEntityHandler
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2019.
 * --------------------------------------------------------------------------------------------------------
 */

//package it.hurts.metallurgy_reforged.handler;
//
//import it.hurts.metallurgy_reforged.tileentity.TileEntityChamber;
//import net.minecraft.entity.player.PlayerEntity;
//import net.minecraft.item.ItemStack;
//import net.minecraft.tileentity.TileEntity;
//import net.minecraft.world.World;
//import net.minecraftforge.event.entity.player.PlayerInteractEvent;
//import net.minecraftforge.eventbus.api.SubscribeEvent;
//
//public class TileEntityHandler {
//
//	public static void registerTileEntities()
//	{
////		GameRegistry.registerTileEntity(TileEntityCrusher.class, new ResourceLocation(Metallurgy.MODID + ":crusher"));
////		GameRegistry.registerTileEntity(TileEntityAlloyer.class, new ResourceLocation(Metallurgy.MODID + ":alloyer"));
////		GameRegistry.registerTileEntity(TileEntityChamber.class, new ResourceLocation(Metallurgy.MODID + ":sublimation_chamber"));
//	}
//
//	@SubscribeEvent
//	public static void rightClick(PlayerInteractEvent.RightClickBlock ev)
//	{
//		PlayerEntity pl = ev.getPlayer();
//		World world = ev.getWorld();
//
//		if(pl.isSneaking())
//		{
//			TileEntity tileEntity = world.getTileEntity(ev.getPos());
//			if(tileEntity instanceof TileEntityChamber)
//			{
//				TileEntityChamber chamber = (TileEntityChamber) tileEntity;
//				ItemStack handStack = pl.getHeldItem(ev.getHand());
//				ItemStack fuelStack = chamber.getStackInSlot(TileEntityChamber.FUEL_SLOT);
//
//				if(!fuelStack.isEmpty() && (handStack.isEmpty() || handStack.isItemEqualIgnoreDurability(fuelStack)))
//				{
//					int i = handStack.getMaxStackSize() - handStack.getCount();
//					ItemStack copyStack = fuelStack.split(i);
//					copyStack.setCount(handStack.getCount() + copyStack.getCount());
//					pl.setHeldItem(ev.getHand(), copyStack);
//					ev.setCanceled(true);
//				}
//
//
//			}
//		}
//	}
//}
