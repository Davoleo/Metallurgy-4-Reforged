/*==============================================================================
 = Class: IgnatiusPickaxeEffect
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2020.
 =============================================================================*/

package it.hurts.metallurgy_reforged.effect.tool;

import it.hurts.metallurgy_reforged.config.ToolEffectsConfig;
import it.hurts.metallurgy_reforged.effect.BaseMetallurgyEffect;
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.model.EnumTools;
import it.hurts.metallurgy_reforged.util.Utils;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import javax.annotation.Nullable;

public class IgnatiusPickaxeEffect extends BaseMetallurgyEffect {

	public IgnatiusPickaxeEffect()
	{
		super(ModMetals.IGNATIUS);
	}

	@Override
	public boolean isEnabled()
	{
		return ToolEffectsConfig.ignatiusPickaxeEffect;
	}

	@Override
	public boolean isToolEffect()
	{
		return super.isEnabled();
	}

	@Nullable
	@Override
	public EnumTools getToolClass()
	{
		return EnumTools.PICKAXE;
	}

	@Override
	public void onBlockHarvested(BlockEvent.HarvestDropsEvent event)
	{
		Item heldItem = event.getHarvester().getHeldItemMainhand().getItem();
		World world = event.getWorld();
		BlockPos pos = event.getPos();
		IBlockState blockState = world.getBlockState(pos);

		if (heldItem.equals(metal.getTool(EnumTools.PICKAXE)))
		{
			ResourceLocation regName = blockState.getBlock().getRegistryName();

			if (regName != null && regName.getPath().contains("_ore"))
			{
				String nuggetReg = regName.getPath().replace("_ore", "_nugget");
				Item nugget = ForgeRegistries.ITEMS.getValue(new ResourceLocation(regName.getNamespace(), nuggetReg));
				if (nugget != null && Utils.random.nextBoolean())
				{
					world.spawnEntity(new EntityItem(world, pos.getX(), pos.getY() + 1, pos.getZ(), new ItemStack(nugget, Utils.random.nextInt(3))));
				}
			}
		}
	}

}
