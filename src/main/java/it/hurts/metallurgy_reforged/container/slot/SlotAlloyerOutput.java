/*==============================================================================
 = Class: SlotAlloyerOutput
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2020.
 =============================================================================*/

package it.hurts.metallurgy_reforged.container.slot;

import it.hurts.metallurgy_reforged.recipe.AlloyerRecipes;
import it.hurts.metallurgy_reforged.util.Utils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.FMLCommonHandler;

import javax.annotation.Nonnull;

public class SlotAlloyerOutput extends Slot {

	private final EntityPlayer player;

	public SlotAlloyerOutput(EntityPlayer player, IInventory inventory, int index, int xPos, int yPos)
	{
		super(inventory, index, xPos, yPos);
		this.player = player;
	}

	@Nonnull
	@Override
	public ItemStack onTake(@Nonnull EntityPlayer thePlayer, @Nonnull ItemStack stack)
	{
		onCrafting(stack);
		return super.onTake(thePlayer, stack);
	}

	@Override
	protected void onCrafting(@Nonnull ItemStack output)
	{
		if (!player.world.isRemote)
		{
			int i = output.getCount();
			output.onCrafting(player.world, player, i);
			Utils.giveExperience(player, i * AlloyerRecipes.getInstance().getAlloyExperience(output));
		}
		FMLCommonHandler.instance().firePlayerSmeltedEvent(player, output);
	}

	@Override
	public boolean isItemValid(@Nonnull ItemStack stack)
	{
		return false;
	}

}
