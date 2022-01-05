/*==============================================================================
 = Class: ShadowIronPickaxeEffect
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
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.util.Utils;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nonnull;

public class ShadowIronPickaxeEffect extends BaseMetallurgyEffect {

	public ShadowIronPickaxeEffect()
	{
		super(ModMetals.SHADOW_IRON);
	}

	@Nonnull
	@Override
	public EnumEffectCategory getCategory()
	{
		return EnumEffectCategory.PICKAXE;
	}

	@SubscribeEvent
	public void harvestShadowIron(BlockEvent.HarvestDropsEvent event)
	{
		//If the mined ore is Shadow Iron and the pick effect can be applied
		if (event.getState().getBlock() == metal.getOre() && canBeApplied(event.getHarvester()))
		{
			//30% 60% 90% 100% depending on fortune level
			if (Utils.random.nextInt(10) < (3 + event.getFortuneLevel() * 3))
			{
				ItemStack newOre = AngmallenPickaxeEffect.getRandomOreStack(
						(BlockOre) event.getState().getBlock(),

						// +1 or +0
						harvestLevel -> harvestLevel == metal.getStats().getOreHarvest() || harvestLevel == metal.getStats().getOreHarvest() + 1
				);

				event.getDrops().clear();
				event.getDrops().add(newOre);

				//TODO : @Matpac you can change the SoundEvent if it makes you KEK too much xD
				for (int i = 0; i < 4; i++)
					event.getWorld().playSound(null, event.getPos(), SoundEvents.BLOCK_NOTE_FLUTE, SoundCategory.BLOCKS, 1.25F, (float) (1 + (Math.random() * 0.5)));
			}
		}
	}

}
