/*
 * -------------------------------------------------------------------------------------------------------
 * Class: RecipesJsonGenHelper
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 * Copyright (c) 2020.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.recipe

class RecipesJsonGenHelper {

    static enum Markers {
        ALL,
        NO_TOOLS_NO_ARMOR,
        NO_TOOLS,
        NO_ARMOR,
    }

    static class Stack {
        String name
        int count

        Stack(String name, int count) {
            this.name = name
            this.count = count
        }
    }

}
