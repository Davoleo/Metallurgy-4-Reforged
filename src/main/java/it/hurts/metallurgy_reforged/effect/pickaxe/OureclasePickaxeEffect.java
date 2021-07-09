/*==============================================================================
 = Class: OureclasePickaxeEffect
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.effect.pickaxe;

import it.hurts.metallurgy_reforged.effect.BaseMetallurgyEffect;
import it.hurts.metallurgy_reforged.effect.EnumEffectCategory;
import it.hurts.metallurgy_reforged.handler.OreDictHandler;
import it.hurts.metallurgy_reforged.material.Metal;
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.sound.ModSounds;
import it.hurts.metallurgy_reforged.util.ItemUtils;
import it.hurts.metallurgy_reforged.util.Utils;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nonnull;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

public class OureclasePickaxeEffect extends BaseMetallurgyEffect {

	public OureclasePickaxeEffect()
	{
		super(ModMetals.OURECLASE);
	}

	@Nonnull
	@Override
	public EnumEffectCategory getCategory()
	{
		return EnumEffectCategory.PICKAXE;
	}

	@SubscribeEvent
	public void harvestDusts(BlockEvent.HarvestDropsEvent event)
	{
		if (!canBeApplied(event.getHarvester()))
			return;

		Set<ItemStack> dusts = new LinkedHashSet<>();
		Iterator<ItemStack> dropIt = event.getDrops().iterator();
		while (dropIt.hasNext())
		{
			//Check if the stack is actually an ore through OreDictionary
			final ItemStack drop = dropIt.next();
			if (!OreDictHandler.containsPrefix(drop, "ore"))
				return;

			//Get the metal the ore is made of (also supports other mods ores)
			Metal metal = ItemUtils.getMetalFromOreDictStack(drop);

			if (metal != null)
			{
				//Removes the ore from the dropped itemstacks
				dropIt.remove();
				//Add the dust to a temporary linked set
				dusts.add(new ItemStack(metal.getDust()));
			}
		}

		if (!dusts.isEmpty())
		{
			//if the set is not empty add all the dust items to the drop list -> play sound and spawn particles
			event.getDrops().addAll(dusts);
			event.getWorld().playSound(null, event.getPos(), ModSounds.CRUSHER_IMPACT, SoundCategory.BLOCKS, 0.8F, 1.2F);
			Utils.repeat(5, () -> spawnParticle(event.getWorld(), event.getPos(), 3F, false, 5, 0, 0, 0));
		}
	}

}
