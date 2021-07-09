/*==============================================================================
 = Class: TileEntityHandler
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2020.
 =============================================================================*/

package it.hurts.metallurgy_reforged.handler;

import it.hurts.metallurgy_reforged.Metallurgy;
import it.hurts.metallurgy_reforged.tileentity.TileEntityAlloyer;
import it.hurts.metallurgy_reforged.tileentity.TileEntityChamber;
import it.hurts.metallurgy_reforged.tileentity.TileEntityCrusher;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public abstract class TileEntityHandler {

	public static void registerTileEntities()
	{
		GameRegistry.registerTileEntity(TileEntityCrusher.class, new ResourceLocation(Metallurgy.MODID + ":crusher"));
		GameRegistry.registerTileEntity(TileEntityAlloyer.class, new ResourceLocation(Metallurgy.MODID + ":alloyer"));
		GameRegistry.registerTileEntity(TileEntityChamber.class, new ResourceLocation(Metallurgy.MODID + ":sublimation_chamber"));
	}

	@SubscribeEvent
	public static void rightClick(PlayerInteractEvent.RightClickBlock event)
	{
		EntityPlayer pl = event.getEntityPlayer();
		World world = event.getWorld();

		if (pl.isSneaking())
		{
			TileEntity tileEntity = world.getTileEntity(event.getPos());
			if (tileEntity instanceof TileEntityChamber)
			{
				TileEntityChamber chamber = (TileEntityChamber) tileEntity;
				ItemStack handStack = pl.getHeldItem(event.getHand());
				EnumFacing facing = event.getFace();

				if (facing == EnumFacing.UP)
				{
					ItemStack metalStack = chamber.getStackInSlot(TileEntityChamber.METAL_SLOT);

					if (!metalStack.isEmpty() && (handStack.isEmpty() || handStack.isItemEqualIgnoreDurability(metalStack)) && !chamber.isActive())
					{
						int i = handStack.getMaxStackSize() - handStack.getCount();
						ItemStack copyStack = metalStack.splitStack(i);
						copyStack.setCount(handStack.getCount() + copyStack.getCount());
						pl.setHeldItem(event.getHand(), copyStack);
						event.setCanceled(true);
					}
				}
				else if (facing != EnumFacing.DOWN)
				{
					ItemStack fuelStack = chamber.getStackInSlot(TileEntityChamber.FUEL_SLOT);

					if (!fuelStack.isEmpty() && (handStack.isEmpty() || handStack.isItemEqualIgnoreDurability(fuelStack)))
					{
						int i = handStack.getMaxStackSize() - handStack.getCount();
						ItemStack copyStack = fuelStack.splitStack(i);
						copyStack.setCount(handStack.getCount() + copyStack.getCount());
						pl.setHeldItem(event.getHand(), copyStack);
						event.setCanceled(true);
					}
				}

			}
		}
	}

}
