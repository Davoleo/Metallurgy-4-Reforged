/*==============================================================================
 = Class: VulcaniteToolEffect
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.effect.tool;

import it.hurts.metallurgy_reforged.effect.BaseMetallurgyEffect;
import it.hurts.metallurgy_reforged.effect.EnumEffectCategory;
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.util.BlockUtils;
import it.hurts.metallurgy_reforged.util.Utils;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.init.Blocks;
import net.minecraft.init.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nonnull;
import java.util.List;

public class VulcaniteToolEffect extends BaseMetallurgyEffect {

	public VulcaniteToolEffect()
	{
		super(ModMetals.VULCANITE);
	}

	@Nonnull
	@Override
	public EnumEffectCategory getCategory()
	{
		return EnumEffectCategory.TOOL;
	}

	@SubscribeEvent
	public void handleFissure(BlockEvent.BreakEvent event)
	{
		if (!canBeApplied(event.getPlayer()))
			return;

		ItemStack toolStack = event.getPlayer().getHeldItemMainhand();
		int fortune = EnchantmentHelper.getEnchantmentLevel(Enchantments.FORTUNE, toolStack);
		if (Math.random() < 0.4 + (0.2 * fortune))
		{
			List<BlockPos> list = BlockUtils.getAdjacentPosList(event.getWorld(), event.getPos(), pos -> !event.getWorld().isAirBlock(pos));
			if (list.isEmpty())
				return;

			BlockPos posToBreak = list.get(Utils.random.nextInt(list.size()));
			if (event.getWorld().getBlockState(posToBreak).getBlock() != Blocks.BEDROCK)
				event.getWorld().destroyBlock(posToBreak, true);

			for (int i = 0; i < 10; i++)
				spawnParticle(event.getWorld(), posToBreak, 1.5F, true, 5, 0, 0, 0);
		}
	}

}
