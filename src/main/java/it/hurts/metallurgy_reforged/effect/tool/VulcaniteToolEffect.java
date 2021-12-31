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
import it.hurts.metallurgy_reforged.util.EventUtils;
import it.hurts.metallurgy_reforged.util.Utils;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.init.Blocks;
import net.minecraft.init.Enchantments;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
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
			final World world = event.getWorld();
			List<BlockPos> list = BlockUtils.getAdjacentPosList(world, event.getPos(), pos -> !world.isAirBlock(pos));
			if (list.isEmpty())
				return;

			BlockPos posToBreak = list.get(Utils.random.nextInt(list.size()));
			if (EventUtils.canHarvest(toolStack, world.getBlockState(posToBreak)))
				world.destroyBlock(posToBreak, true);

			for (int i = 0; i < 10; i++)
				spawnParticle(world, posToBreak, 1.5F, true, 5, 0, 0, 0);

			world.playSound(null, posToBreak, SoundEvents.ENTITY_FIREWORK_BLAST, SoundCategory.BLOCKS, 2F, 1.5F);
		}
	}

}
