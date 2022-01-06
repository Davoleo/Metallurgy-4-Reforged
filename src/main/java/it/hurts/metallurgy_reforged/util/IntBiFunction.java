/*==============================================================================
 = Class: IntBiFunction
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.util;

/**
 * An {@link java.util.function.IntFunction} that takes two primitive ints instead of one to give a custom result
 */
@FunctionalInterface
public interface IntBiFunction<R> {

	R apply(int param1, int param2);

}
