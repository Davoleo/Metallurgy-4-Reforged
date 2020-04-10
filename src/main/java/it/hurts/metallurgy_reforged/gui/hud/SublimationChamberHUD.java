/*
 * -------------------------------------------------------------------------------------------------------
 * Class: SublimationChamberHUD
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2020.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.gui.hud;

import it.hurts.metallurgy_reforged.item.ItemMetal;
import it.hurts.metallurgy_reforged.material.Metal;
import it.hurts.metallurgy_reforged.recipe.SublimationRecipes;
import it.hurts.metallurgy_reforged.tileentity.TileEntityChamber;
import it.hurts.metallurgy_reforged.util.ItemUtils;
import it.hurts.metallurgy_reforged.util.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

import java.util.Map;

public class SublimationChamberHUD {

	public static void render(RenderGameOverlayEvent.Post event, Minecraft minecraft, BlockPos pos)
	{

		World world = minecraft.world;

		if (!(world.getTileEntity(pos) instanceof TileEntityChamber))
			return;

		ScaledResolution resolution = event.getResolution();
		int x = resolution.getScaledWidth() / 2;
		int y = resolution.getScaledHeight() / 2;

		TileEntityChamber te = ((TileEntityChamber) world.getTileEntity(pos));
		Map<ItemStack, PotionEffect> recipes = SublimationRecipes.getInstance().recipesMap();

		ItemStack metalStack = te.getStackInSlot(TileEntityChamber.METAL_SLOT);

		if (!metalStack.isEmpty())
		{
			RenderHelper.enableStandardItemLighting();
			minecraft.getRenderItem().renderItemIntoGUI(metalStack, x - 40, y - 7);
			RenderHelper.disableStandardItemLighting();
			minecraft.fontRenderer.drawStringWithShadow(String.valueOf(metalStack.getCount()), x - 50, y - 3, 0xFFFFFF);
		}

		ItemStack fuel = te.getStackInSlot(TileEntityChamber.FUEL_SLOT);
		if (!fuel.isEmpty())
		{
			RenderHelper.enableStandardItemLighting();
			minecraft.getRenderItem().renderItemIntoGUI(fuel, x - 5, y + 17);
			RenderHelper.disableStandardItemLighting();
			minecraft.fontRenderer.drawStringWithShadow(String.valueOf(fuel.getCount()), x - 15, y + 20, 0xFFFFFF);
		}

		if (te.potionEffect != null)
		{
			PotionEffect effect = null;

			for (Map.Entry<ItemStack, PotionEffect> recipe : recipes.entrySet())
			{
				if (recipe.getKey().getItem() == metalStack.getItem())
				{
					effect = recipe.getValue();
					metalStack.setCount(recipe.getKey().getCount());
				}
			}

			if (effect != null)
			{
				int effectIndex = effect.getPotion().getStatusIconIndex();

				GlStateManager.pushMatrix();

				minecraft.getTextureManager().bindTexture(GuiContainer.INVENTORY_BACKGROUND);
				Utils.drawTexturedModalRect(x + 30, y - 10, 141, 166, 24, 24);
				Utils.drawTexturedModalRect(x + 33, y - 8, effectIndex % 8 * 18, 198 + effectIndex / 8 * 18, 18, 18);

				Metal metal = ItemUtils.getMetalFromItem(((ItemMetal) metalStack.getItem()));
				int color = metal != null ? metal.getStats().getMetalColor() : 0xFFFFFF;
				minecraft.fontRenderer.drawStringWithShadow((te.activeTime / 20) + " " + Utils.localizeIgnoreFormat("gui.jei_compat.sublimation_chamber.seconds"), x - 33, y - 30, color);
				minecraft.fontRenderer.drawStringWithShadow(Utils.localizeIgnoreFormat(effect.getEffectName()), x + 30, y + 25, color);

				GlStateManager.popMatrix();
			}

		}
	}

}
