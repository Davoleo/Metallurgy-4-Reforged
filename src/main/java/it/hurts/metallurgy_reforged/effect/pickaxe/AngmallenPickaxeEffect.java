/*==============================================================================
 = Class: AngmallenPickaxeEffect
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.effect.pickaxe;

import it.hurts.metallurgy_reforged.block.BlockOre;
import it.hurts.metallurgy_reforged.effect.BaseMetallurgyEffect;
import it.hurts.metallurgy_reforged.effect.EnumEffectCategory;
import it.hurts.metallurgy_reforged.item.ItemBlockOre;
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.util.Utils;
import net.minecraft.block.Block;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.function.IntPredicate;
import java.util.stream.Collectors;

public class AngmallenPickaxeEffect extends BaseMetallurgyEffect {

	public AngmallenPickaxeEffect()
	{
		super(ModMetals.ANGMALLEN);
	}

	@Nonnull
	@Override
	public EnumEffectCategory getCategory()
	{
		return EnumEffectCategory.PICKAXE;
	}

	/**
	 * handles 30% Chance to transmute the harvested ore into something else
	 */
	@SubscribeEvent
	public void transmuteOre(BlockEvent.HarvestDropsEvent event)
	{

		if (!canBeApplied(event.getHarvester()))
			return;

		Block block = event.getState().getBlock();

		if (!event.getWorld().isRemote && block instanceof BlockOre)
		{
			//30% 60% 90% 100% depending on fortune level
			if (Utils.random.nextInt(10) < (3 + event.getFortuneLevel() * 3))
			{
				event.getDrops().clear();
				ItemStack stack = getRandomOreStack((BlockOre) event.getState().getBlock(), hLevel -> {
					int blockHL = block.getHarvestLevel(event.getState());
					return hLevel >= blockHL - 1 && hLevel <= blockHL + 1;
				});
				ItemBlockOre.setLocked(stack, true);
				event.getDrops().add(stack);
				event.getWorld().playSound(null, event.getPos(), SoundEvents.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.BLOCKS, 1F, 1F);

				for (int i = 0; i < 20; i++)
					spawnParticle(event.getWorld(), event.getPos(), 1.3F, true, 5, 0, 0.2D, 0);
			}
		}
	}

	/**
	 * Get a random ItemStack from a BlockOre that has an harvest level of -1/+0/+1
	 *
	 * @param ore                   The broken BlockOre
	 * @param harvestLevelCondition a predicate returning true if the transmutation should happen and false otherwise,
	 *                              parameter of the predicate is the harvest level of the different ores
	 *
	 * @return a BlockOre ItemStack
	 */
	static ItemStack getRandomOreStack(BlockOre ore, IntPredicate harvestLevelCondition)
	{
		//Loop over the metal map and filter for the right ores via Streams
		List<ItemStack> oresDropList = ModMetals.metalMap.values().stream().filter(mettle -> {
			if (mettle.isAlloy() || ore == mettle.getOre())
				return false;

			int level = mettle.getStats().getOreHarvest();
			return harvestLevelCondition.test(level);
		}).map(mettle -> new ItemStack(mettle.getOre())).collect(Collectors.toList());
		// Map metals to ore itemStacks and collect all of them into a list

		//Injects Iron and Gold ores into the pool of possible ores to transmute to
		//DISABLED (probably forever, but keeping it just in case)
		//if (harvestLevelCondition.test(1))
		//	oresDropList.add(new ItemStack(Blocks.IRON_ORE));
		//if (harvestLevelCondition.test(2))
		//	oresDropList.add(new ItemStack(Blocks.GOLD_ORE));

		return oresDropList.get(Utils.random.nextInt(oresDropList.size()));
	}

}
