/*==============================================================================
 = Class: WorldUtils
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.util;

import com.google.common.collect.Sets;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

import java.util.Set;

public class WorldUtils {

    public static Set<BlockPos> getAllColliding(Vec3d start, Vec3d target)
    {
        Set<BlockPos> list = Sets.newLinkedHashSet();
        Vec3d directionVec = target.subtract(start);
        double length = directionVec.length();
        final double xBit = directionVec.x / (10 * length);
        final double yBit = directionVec.y / (10 * length);
        final double zBit = directionVec.z / (10 * length);

        for (int i = 1; i <= length * 10; i++)
        {
            double x = start.x + xBit * i;
            double y = start.y + yBit * i;
            double z = start.z + zBit * i;
            list.add(new BlockPos(x, y, z));
        }

        return list;
    }

}
