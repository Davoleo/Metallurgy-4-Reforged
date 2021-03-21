/*==============================================================================
 = Class: IProgressiveEffect
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.effect;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public interface IProgressiveEffect {

    void onStep(World world, EntityPlayer entity, int maxSteps, int step);

}
