/*==============================================================================
 = Class: IProgressiveEffect
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2020.
 =============================================================================*/

package it.hurts.metallurgy_reforged.effect;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

@FunctionalInterface
public interface IProgressiveEffect {

    void onStep(World world, BlockPos pos, int step, int maxSteps);

}
