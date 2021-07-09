/*==============================================================================
 = Class: EntityData
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.capabilities.entity;

import it.hurts.metallurgy_reforged.effect.armor.OldDesichalkosArmorEffect;
import net.minecraft.block.state.IBlockState;

import java.util.Random;

public class EntityData {

	public boolean wasSnatched = false;
	public IBlockState snatchableBlock = null;
	public boolean initialized = false;

	public void initEnderman()
	{
		Random random = new Random();
		if (random.nextInt(4) == 0 && !initialized)
		{
			this.snatchableBlock = OldDesichalkosArmorEffect.borrowableBlocks[random.nextInt(OldDesichalkosArmorEffect.borrowableBlocks.length)];
			this.initialized = true;
		}
	}

}
