/*==============================================================================
 = Class: RecipeGenHelper
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2020.
 =============================================================================*/

package it.hurts.metallurgy_reforged.recipe

import groovy.json.JsonOutput

class RecipeGenHelper {

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

    static void writeJson(String type, String metal, Object obj) {

        String subdir

        switch (type) {
            case "helmet":
            case "chestplate":
            case "leggings":
            case "boots":
                subdir = "item/armor/"
                break
            case "axe":
            case "hoe":
            case "pickaxe":
            case "shovel":
            case "sword":
                subdir = "item/tool/"
                break

            case "block":
            case "engraved_block":
            case "crystals":
            case "bricks":
            case "large_bricks":
            case "hazard_block":
            case "reinforced_glass":
                subdir = "block/"
                break

            case "ingot":
                subdir = "item/"
                break

            case "ingot_shapeless":
            case "nugget_shapeless":
                subdir = "item/shapeless/"
                break

            case "dust":
                subdir = "item/dust/"
                break

            default:
                subdir = ""
                break
        }

        def jsonObj = JsonOutput.toJson(obj)
        def prettyObj = JsonOutput.prettyPrint(jsonObj)
        def file = new File(RecipeJsonGenerator.RECIPES_DIR_PATH + subdir + type + "_" + metal + ".json")
        file.createNewFile()
        file.write(prettyObj)
    }

}
