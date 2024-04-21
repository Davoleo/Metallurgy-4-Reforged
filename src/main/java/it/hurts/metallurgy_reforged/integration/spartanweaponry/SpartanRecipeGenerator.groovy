/*==============================================================================
 = Class: SpartanRecipeGenerator
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2024.
 =============================================================================*/

package it.hurts.metallurgy_reforged.integration.spartanweaponry

import com.google.common.base.CaseFormat
import groovy.json.JsonOutput
import it.hurts.metallurgy_reforged.recipe.RecipeGenHelper
import it.hurts.metallurgy_reforged.recipe.RecipeJsonGenerator

/**
 * ! Needs spartanweaponry available at runtime for this to be executed by main datagen.
 */
class SpartanRecipeGenerator {

    static def spartanPatterns = [
            dagger         : ['I', 'H'],
            longsword      : [' I ', ' I ', 'IHI'],
            katana         : ['  I', ' I ', 'H  '],
            saber          : [' I', ' I', 'IH'],
            rapier         : ['  I', 'II ', 'HI '],
            greatsword     : [' I ', 'III', 'IHI'],
            hammer         : ['III', 'III', ' H '],
            warhammer      : [' I', 'II', ' H'],
            spear          : ['I', 'P'],
            halberd        : [' I', 'II', 'IP'],
            pike           : ['I', 'P', 'P'],
            lance          : ['I', 'P', 'H'],
            longbow        : ['HSI', 'S R', 'IRR'],
            crossbow       : ['BRI', 'RL ', 'I H'],
            throwing_knife : ['HI'],
            throwing_axe   : ['HI', ' I'],
            javelin        : ['PI'],
            bucket         : ['I I', ' I '],
            boomerang      : ['IWW', 'W  ', 'W  '],
            battleaxe      : ['III', 'ISI', ' H '],
            mace           : [' II', ' SI', 'H  '],
            glaive         : [' I', ' I', 'IP'],
            staff: ['  I', ' P ', 'I  '],
            parrying_dagger: [' I', 'IH']
    ]

    static Map spartanKey(char character, String pascalMetal) {
        switch (character) {
            case 'I': return [
                    type: "forge:ore_dict",
                    ore : "ingot$pascalMetal"
            ]
            case 'S': return [
                    type: "forge:ore_dict",
                    ore : "stickWood"
            ]
            case 'H': return [
                    item: "spartanweaponry:material",
                    data: 0
            ]
            case 'P': return [
                    item: "spartanweaponry:material",
                    data: 1
            ]
            case 'R': return [
                    type: "forge:ore_dict",
                    ore : "string"
            ]
            case 'W': return [
                    type: "forge:ore_dict",
                    ore : "plankWood"
            ]
            case 'L': return [
                    type: "forge:ore_dict",
                    ore : "logWood"
            ]
            case 'B': return [
                    item: 'minecraft:bow'
            ]
            default: throw new IllegalStateException("ILLEGAL SPARTAN INGREDIENT KEY '$character'")
        }
    }


    static void generate(String metal) {
        String pascalName = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, metal)

        SpartanWeaponType.values().collect { type -> type.name().toLowerCase() }.each { type ->
            def pattern = spartanPatterns[type]

            Map<Character, Object> recipeKeys = [:]
            pattern.join('').getChars().each { ch ->
                if (ch.letter) {
                    recipeKeys[ch] = spartanKey(ch, pascalName)
                }
            }

            def recipeObj = [
                    type   : "forge:ore_shaped",
                    pattern: spartanPatterns[type],
                    key    : recipeKeys,
                    result : [
                            item: "metallurgy:${type}_${metal}"
                    ]
            ]

            writeJson(type, metal, recipeObj)
        }
    }

    static void writeJson(String type, String metal, Object obj) {
        def json = JsonOutput.toJson(obj)
        def prettyJson = RecipeGenHelper.convert2SpaceIndent(json)

        def file = new File("${RecipeJsonGenerator.RECIPES_DIR_PATH}item/spartanweaponry/$type/${type}_${metal}.json")
        file.parentFile.mkdirs()
        file.createNewFile()
        file.write(prettyJson)
    }
}
