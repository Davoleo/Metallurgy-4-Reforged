/*
 * -------------------------------------------------------------------------------------------------------
 * Class: MetallurgyTraitVulcan
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2019.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.integration.mods.tic.trait;

import it.hurts.metallurgy_reforged.util.Utils;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent;
import slimeknights.tconstruct.common.config.Config;
import slimeknights.tconstruct.library.modifiers.IToolMod;
import slimeknights.tconstruct.library.traits.AbstractTraitLeveled;
import slimeknights.tconstruct.library.utils.ToolHelper;
import slimeknights.tconstruct.tools.TinkerModifiers;
import slimeknights.tconstruct.tools.TinkerTraits;

import javax.annotation.Nullable;
import java.util.ListIterator;

public class MetallurgyTraitVulcan extends AbstractTraitLeveled implements IMetallurgyTrait {

	private int levels;
	
	public MetallurgyTraitVulcan(int levels) {
		super("vulcan_trait", String.valueOf(levels), 0xffffff, 2, levels);
		
		this.levels = levels;
	}

	@Override
	public void afterHit(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damageDealt, boolean wasCritical, boolean wasHit) {
		switch(levels) {
		case 0: target.setFire(3);
		break;

		case 1: target.setFire(5);
		break;

		case 2: target.setFire(7);
		break;

		default: target.setFire(3);
		break;
		}
	}
	

	@Override
	public boolean canApplyTogether(Enchantment enchantment) {
		return enchantment != Enchantments.SILK_TOUCH;
	}

	@Override
	public boolean canApplyTogether(IToolMod toolmod) {
		return !toolmod.getIdentifier().equals(TinkerTraits.squeaky.getIdentifier())
				&& !toolmod.getIdentifier().equals(TinkerModifiers.modSilktouch.getIdentifier());
	}

	@Override
	public void blockHarvestDrops(ItemStack tool, BlockEvent.HarvestDropsEvent event) {
		if(ToolHelper.isToolEffective2(tool, event.getState())) {
			// go through the drops and replace them with their furnace'd variant if applicable
			ListIterator<ItemStack> iter = event.getDrops().listIterator();
			while(iter.hasNext()) {
				ItemStack drop = iter.next();
				ItemStack smelted = FurnaceRecipes.instance().getSmeltingResult(drop);
				if(!smelted.isEmpty()) {
					smelted = smelted.copy();
					smelted.setCount(drop.getCount());
					int fortune = EnchantmentHelper.getEnchantmentLevel(Enchantments.FORTUNE, tool);
					if(Config.autosmeltlapis && fortune > 0) {
						smelted.setCount(smelted.getCount() * random.nextInt(fortune + 1) + 1);
					}
					iter.set(smelted);

					// drop XP for it
					float xp = FurnaceRecipes.instance().getSmeltingExperience(smelted);
					if(xp < 1 && Math.random() < xp) {
						xp += 1f;
					}
					if(xp >= 1f) {
						event.getState().getBlock().dropXpOnBlockBreak(event.getWorld(), event.getPos(), (int) xp);
					}
				}
			}
		}
	}

	@Override
	public void afterBlockBreak(ItemStack tool, World world, IBlockState state, BlockPos pos, EntityLivingBase player, boolean wasEffective) {
		if(world.isRemote && wasEffective) {
			for(int i = 0; i < 3; i++) {
				world.spawnParticle(EnumParticleTypes.FLAME,
						pos.getX() + random.nextDouble(),
						pos.getY() + random.nextDouble(), pos.getZ() + random.nextDouble(),
						0.0D, 0.0D, 0.0D);
			}
		}
	}

	@Override
	public void register(String name, @Nullable String tooltip) {
		Utils.localize(String.format(LOC_Name, name));
		if (tooltip != null)
			Utils.localize(String.format(LOC_Name, tooltip));

	}

}
