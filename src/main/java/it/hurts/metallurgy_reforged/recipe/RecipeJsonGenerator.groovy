/*
 * -------------------------------------------------------------------------------------------------------
 * Class: RecipeJsonGenerator
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 * Copyright (c) 2020.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.recipe

import com.google.common.base.CaseFormat
import groovy.json.JsonOutput
import it.hurts.metallurgy_reforged.integration.IntegrationProjectE

class RecipeJsonGenerator {

    static final def RECIPES_DIR_PATH = "src/main/resources/assets/metallurgy/recipes/"

    static def materials = IntegrationProjectE.emcMap.keySet()

    //static def pascalMaterials = materials.each { CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, it) }

    //JsonOutput.prettyPrint()
    //BufferedWriter

    static def shapedPatterns = [
            axe             : ["II", "IS", " S"],
            hoe             : ["II", " S", " S"],
            pickaxe         : ["III", " S ", " S "],
            shovel          : ["I", "S", "S"],
            sword           : ["I", "I", "S"],

            helmet          : ["III", "I I"],
            chestplate      : ["I I", "III", "III"],
            leggings        : ["III", "I I", "I I"],
            boots           : ["I I", "I I"],

            block           : ["III", "III", "III"],
            engraved_block  : ["III", "I I", "III"],
            crystals        : [" N ", "NIN", "III"],
            bricks          : ["III", "III"],
            large_bricks    : ["II", "II"],
            hazard_block    : ["NIN", "INI", "NIN"],
            reinforced_glass: ["GIG", "III", "GIG"],

            ingot           : ["NNN", "NNN", "NNN"]
    ]

    static def markers = [
            adamantine    : RecipesJsonGenHelper.Markers.ALL,
            alduorite     : RecipesJsonGenHelper.Markers.NO_TOOLS_NO_ARMOR,
            amordrine     : RecipesJsonGenHelper.Markers.ALL,
            angmallen     : RecipesJsonGenHelper.Markers.ALL,
            astral_silver : RecipesJsonGenHelper.Markers.ALL,
            atlarus       : RecipesJsonGenHelper.Markers.ALL,
            black_steel   : RecipesJsonGenHelper.Markers.ALL,
            brass         : RecipesJsonGenHelper.Markers.ALL,
            bronze        : RecipesJsonGenHelper.Markers.ALL,
            carmot        : RecipesJsonGenHelper.Markers.ALL,
            celenegil     : RecipesJsonGenHelper.Markers.ALL,
            ceruclase     : RecipesJsonGenHelper.Markers.ALL,
            copper        : RecipesJsonGenHelper.Markers.ALL,
            damascus_steel: RecipesJsonGenHelper.Markers.ALL,
            deep_iron     : RecipesJsonGenHelper.Markers.ALL,
            desichalkos   : RecipesJsonGenHelper.Markers.ALL,
            electrum      : RecipesJsonGenHelper.Markers.ALL,
            etherium      : RecipesJsonGenHelper.Markers.ALL,
            eximite       : RecipesJsonGenHelper.Markers.ALL,
            haderoth      : RecipesJsonGenHelper.Markers.ALL,
            hepatizon     : RecipesJsonGenHelper.Markers.ALL,
            ignatius      : RecipesJsonGenHelper.Markers.ALL,
            infuscolium   : RecipesJsonGenHelper.Markers.NO_TOOLS_NO_ARMOR,
            inolashite    : RecipesJsonGenHelper.Markers.ALL,
            kalendrite    : RecipesJsonGenHelper.Markers.ALL,
            krik          : RecipesJsonGenHelper.Markers.ALL,
            lemurite      : RecipesJsonGenHelper.Markers.NO_TOOLS_NO_ARMOR,
            lutetium      : RecipesJsonGenHelper.Markers.NO_TOOLS,
            manganese     : RecipesJsonGenHelper.Markers.NO_TOOLS_NO_ARMOR,
            meutoite      : RecipesJsonGenHelper.Markers.NO_TOOLS_NO_ARMOR,
            midasium      : RecipesJsonGenHelper.Markers.ALL,
            mithril       : RecipesJsonGenHelper.Markers.ALL,
            orichalcum    : RecipesJsonGenHelper.Markers.ALL,
            osmium        : RecipesJsonGenHelper.Markers.NO_TOOLS,
            oureclase     : RecipesJsonGenHelper.Markers.ALL,
            platinum      : RecipesJsonGenHelper.Markers.ALL,
            prometheum    : RecipesJsonGenHelper.Markers.ALL,
            quicksilver   : RecipesJsonGenHelper.Markers.ALL,
            rubracium     : RecipesJsonGenHelper.Markers.NO_TOOLS_NO_ARMOR,
            sanguinite    : RecipesJsonGenHelper.Markers.ALL,
            shadow_iron   : RecipesJsonGenHelper.Markers.ALL,
            shadow_steel  : RecipesJsonGenHelper.Markers.ALL,
            silver        : RecipesJsonGenHelper.Markers.ALL,
            steel         : RecipesJsonGenHelper.Markers.ALL,
            tartarite     : RecipesJsonGenHelper.Markers.ALL,
            tin           : RecipesJsonGenHelper.Markers.NO_TOOLS_NO_ARMOR,
            vulcanite     : RecipesJsonGenHelper.Markers.ALL,
            vyroxeres     : RecipesJsonGenHelper.Markers.ALL,
            zinc          : RecipesJsonGenHelper.Markers.NO_TOOLS_NO_ARMOR,
    ]

    static def alloys = [
            [
                    new RecipesJsonGenHelper.Stack("copper", 3),
                    new RecipesJsonGenHelper.Stack("tin", 1),
                    new RecipesJsonGenHelper.Stack("bronze", 4)
            ],
            [
                    new RecipesJsonGenHelper.Stack("shadow_iron", 2),
                    new RecipesJsonGenHelper.Stack("lemurite", 1),
                    new RecipesJsonGenHelper.Stack("shadow_steel", 3)
            ],
            [
                    new RecipesJsonGenHelper.Stack("alduorite", 1),
                    new RecipesJsonGenHelper.Stack("ceruclase", 1),
                    new RecipesJsonGenHelper.Stack("inolashite", 2)
            ],
            [
                    new RecipesJsonGenHelper.Stack("copper", 3),
                    new RecipesJsonGenHelper.Stack("zinc", 1),
                    new RecipesJsonGenHelper.Stack("brass", 4)
            ],
            [
                    new RecipesJsonGenHelper.Stack("iron", 1),
                    new RecipesJsonGenHelper.Stack("manganese", 3),
                    new RecipesJsonGenHelper.Stack("steel", 2)
            ],
            [
                    new RecipesJsonGenHelper.Stack("deep_iron", 3),
                    new RecipesJsonGenHelper.Stack("infuscolium", 1),
                    new RecipesJsonGenHelper.Stack("black_steel", 4)
            ],
            [
                    new RecipesJsonGenHelper.Stack("iron", 1),
                    new RecipesJsonGenHelper.Stack("bronze", 2),
                    new RecipesJsonGenHelper.Stack("damascus_steel", 3)
            ],
            [
                    new RecipesJsonGenHelper.Stack("silver", 1),
                    new RecipesJsonGenHelper.Stack("gold", 1),
                    new RecipesJsonGenHelper.Stack("electrum", 2)
            ],
            [
                    new RecipesJsonGenHelper.Stack("orichalcum", 1),
                    new RecipesJsonGenHelper.Stack("platinum", 1),
                    new RecipesJsonGenHelper.Stack("celenegil", 2)
            ],
            [
                    new RecipesJsonGenHelper.Stack("kalendrite", 1),
                    new RecipesJsonGenHelper.Stack("platinum", 1),
                    new RecipesJsonGenHelper.Stack("amordrine", 2)
            ],
            [
                    new RecipesJsonGenHelper.Stack("mithril", 1),
                    new RecipesJsonGenHelper.Stack("rubracium", 2),
                    new RecipesJsonGenHelper.Stack("haderoth", 3)
            ],
            [
                    new RecipesJsonGenHelper.Stack("adamantine", 1),
                    new RecipesJsonGenHelper.Stack("atlarus", 1),
                    new RecipesJsonGenHelper.Stack("tartarite", 1)
            ],
            [
                    new RecipesJsonGenHelper.Stack("eximite", 1),
                    new RecipesJsonGenHelper.Stack("meutoite", 1),
                    new RecipesJsonGenHelper.Stack("desichalkos", 2)
            ],
            [
                    new RecipesJsonGenHelper.Stack("iron", 1),
                    new RecipesJsonGenHelper.Stack("gold", 1),
                    new RecipesJsonGenHelper.Stack("angmallen", 2)
            ],
            [
                    new RecipesJsonGenHelper.Stack("infuscolium", 1),
                    new RecipesJsonGenHelper.Stack("steel", 1),
                    new RecipesJsonGenHelper.Stack("hepatizon", 2)
            ],
            [
                    new RecipesJsonGenHelper.Stack("silver", 1),
                    new RecipesJsonGenHelper.Stack("mithril", 1),
                    new RecipesJsonGenHelper.Stack("quicksilver", 2)
            ],
            [
                    new RecipesJsonGenHelper.Stack("lutetium", 1),
                    new RecipesJsonGenHelper.Stack("osmium", 1),
                    new RecipesJsonGenHelper.Stack("krik", 2)
            ],
            [
                    new RecipesJsonGenHelper.Stack("sanguinite", 1),
                    new RecipesJsonGenHelper.Stack("carmot", 1),
                    new RecipesJsonGenHelper.Stack("etherium", 2)
            ],
    ]

    static void main(String[] args) {
        materials.forEach({ metal ->
            generateShapedRecipes(metal)
            generateShapelessRecipes(metal)
        })

        alloys.each { alloy -> generateAlloyRecipes(alloy) }
    }

    private static void generateShapedRecipes(String metal) {
        String pascalName = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, metal)

        if (markers[metal] == RecipesJsonGenHelper.Markers.ALL || markers[metal] == RecipesJsonGenHelper.Markers.NO_TOOLS) {
            //-------------------------- ARMOR ----------------------------------            
            //helmet
            def helmetObj = [
                    type   : "forge:ore_shaped",
                    pattern: shapedPatterns["helmet"],
                    key    : [
                            "I": [
                                    type: "forge:ore_dict",
                                    ore : "ingot$pascalName"
                            ]
                    ],
                    result : [
                            item: "metallurgy:${metal}_helmet"
                    ]
            ]

            //chestplate
            def chestplateObj = [
                    type   : "forge:ore_shaped",
                    pattern: shapedPatterns["chestplate"],
                    key    : [
                            "I": [
                                    type: "forge:ore_dict",
                                    ore : "ingot$pascalName"
                            ]
                    ],
                    result : [
                            item: "metallurgy:${metal}_chestplate"
                    ]
            ]
            //leggings
            def leggingsObj = [
                    type   : "forge:ore_shaped",
                    pattern: shapedPatterns["leggings"],
                    key    : [
                            "I": [
                                    type: "forge:ore_dict",
                                    ore : "ingot$pascalName"
                            ]
                    ],
                    result : [
                            item: "metallurgy:${metal}_leggings"
                    ]
            ]
            //boots
            def bootsObj = [
                    type   : "forge:ore_shaped",
                    pattern: shapedPatterns["boots"],
                    key    : [
                            "I": [
                                    type: "forge:ore_dict",
                                    ore : "ingot$pascalName"
                            ]
                    ],
                    result : [
                            item: "metallurgy:${metal}_boots"
                    ]
            ]

            writeJson("helmet", metal, helmetObj)
            writeJson("chestplate", metal, chestplateObj)
            writeJson("leggings", metal, leggingsObj)
            writeJson("boots", metal, bootsObj)
        }

        if (markers[metal] != RecipesJsonGenHelper.Markers.NO_TOOLS_NO_ARMOR && markers[metal] != RecipesJsonGenHelper.Markers.NO_TOOLS) {
            //------------------------------- TOOLS --------------------------------

            //axe
            def axeObj = [
                    type   : "forge:ore_shaped",
                    pattern: shapedPatterns["axe"],
                    key    : [
                            "I": [
                                    type: "forge:ore_dict",
                                    ore : "ingot$pascalName"
                            ],
                            "S": [
                                    item: "minecraft:stick"
                            ]
                    ],
                    result : [
                            item: "metallurgy:${metal}_axe"
                    ]
            ]
            //hoe
            def hoeObj = [
                    type   : "forge:ore_shaped",
                    pattern: shapedPatterns["hoe"],
                    key    : [
                            "I": [
                                    type: "forge:ore_dict",
                                    ore : "ingot$pascalName"
                            ],
                            "S": [
                                    item: "minecraft:stick"
                            ]
                    ],
                    result : [
                            item: "metallurgy:${metal}_hoe"
                    ]
            ]
            //pickaxe
            def pickaxeObj = [
                    type   : "forge:ore_shaped",
                    pattern: shapedPatterns["pickaxe"],
                    key    : [
                            "I": [
                                    type: "forge:ore_dict",
                                    ore : "ingot$pascalName"
                            ],
                            "S": [
                                    item: "minecraft:stick"
                            ]
                    ],
                    result : [
                            item: "metallurgy:${metal}_pickaxe"
                    ]
            ]
            //shovel
            def shovelObj = [
                    type   : "forge:ore_shaped",
                    pattern: shapedPatterns["shovel"],
                    key    : [
                            "I": [
                                    type: "forge:ore_dict",
                                    ore : "ingot$pascalName"
                            ],
                            "S": [
                                    item: "minecraft:stick"
                            ]
                    ],
                    result : [
                            item: "metallurgy:${metal}_shovel"
                    ]
            ]
            //sword
            def swordObj = [
                    type   : "forge:ore_shaped",
                    pattern: shapedPatterns["sword"],
                    key    : [
                            "I": [
                                    type: "forge:ore_dict",
                                    ore : "ingot$pascalName"
                            ],
                            "S": [
                                    item: "minecraft:stick"
                            ]
                    ],
                    result : [
                            item: "metallurgy:${metal}_sword"
                    ]
            ]

            writeJson("axe", metal, axeObj)
            writeJson("hoe", metal, hoeObj)
            writeJson("pickaxe", metal, pickaxeObj)
            writeJson("shovel", metal, shovelObj)
            writeJson("sword", metal, swordObj)
        }

        //----------------- BLOCKS -----------------------
        def blockObj = [
                type   : "forge:ore_shaped",
                pattern: shapedPatterns.block,
                key    : [
                        "I": [
                                type: "forge:ore_dict",
                                ore : "ingot$pascalName"
                        ]
                ],
                result : [
                        item: "metallurgy:${metal}_block"
                ]
        ]
        def engravedObj = [
                type   : "forge:ore_shaped",
                pattern: shapedPatterns.engraved_block,
                key    : [
                        "I": [
                                type: "forge:ore_dict",
                                ore : "ingot$pascalName"
                        ]
                ],
                result : [
                        item: "metallurgy:${metal}_engraved_block",
                        count: 8
                ]
        ]
        def crystalsObj = [
                type   : "forge:ore_shaped",
                pattern: shapedPatterns.crystals,
                key    : [
                        "I": [
                                type: "forge:ore_dict",
                                ore : "ingot$pascalName"
                        ],
                        "N": [
                                type: "forge:ore_dict",
                                ore : "nugget$pascalName"
                        ]
                ],
                result : [
                        item: "metallurgy:${metal}_crystals",
                        count: 4
                ]
        ]
        def bricksObj = [
                type   : "forge:ore_shaped",
                pattern: shapedPatterns.bricks,
                key    : [
                        "I": [
                                type: "forge:ore_dict",
                                ore : "ingot$pascalName"
                        ]
                ],
                result : [
                        item: "metallurgy:${metal}_bricks",
                        count: 6
                ]
        ]
        def largeBricksObj = [
                type   : "forge:ore_shaped",
                pattern: shapedPatterns.large_bricks,
                key    : [
                        "I": [
                                type: "forge:ore_dict",
                                ore : "ingot$pascalName"
                        ]
                ],
                result : [
                        item: "metallurgy:${metal}_large_bricks",
                        count: 4
                ]
        ]
        def hazardBlocksObj = [
                type   : "forge:ore_shaped",
                pattern: shapedPatterns.hazard_block,
                key    : [
                        "I": [
                                type: "forge:ore_dict",
                                ore : "ingot$pascalName"
                        ],
                        "N": [
                                type: "forge:ore_dict",
                                ore : "nugget$pascalName"
                        ]
                ],
                result : [
                        item: "metallurgy:${metal}_hazard_block",
                        count: 4
                ]
        ]
        def reinforcedGlassObj = [
                type   : "forge:ore_shaped",
                pattern: shapedPatterns.reinforced_glass,
                key    : [
                        "I": [
                                type: "forge:ore_dict",
                                ore : "ingot$pascalName"
                        ],
                        "G": [
                                item: "minecraft:glass"
                        ]
                ],
                result : [
                        item: "metallurgy:${metal}_reinforced_glass",
                        count: 5
                ]
        ]

        // -------------------------- INGOT -------------------------
        def ingotObj = [
                type   : "forge:ore_shaped",
                pattern: shapedPatterns.ingot,
                key    : [
                        "N": [
                                type: "forge:ore_dict",
                                ore : "nugget$pascalName"
                        ]
                ],
                result : [
                        item: "metallurgy:${metal}_ingot"
                ]
        ]

        writeJson("block", metal, blockObj)
        writeJson("engraved_block", metal, engravedObj)
        writeJson("crystals", metal, crystalsObj)
        writeJson("bricks", metal, bricksObj)
        writeJson("large_bricks", metal, largeBricksObj)
        writeJson("hazard_block", metal, hazardBlocksObj)
        writeJson("reinforced_glass", metal, reinforcedGlassObj)

        writeJson("ingot", metal, ingotObj)
    }

    private static void generateShapelessRecipes(String metal) {

        def pascalMetal = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, metal)

        def ingotObj = [
                type       : "forge:ore_shapeless",
                ingredients: [
                        [
                                type: "forge:ore_dict",
                                ore : "block$pascalMetal"
                        ]
                ],
                result     : [
                        item : "metallurgy:${metal}_ingot",
                        count: 9
                ]
        ]

        def nuggetObj = [
                type       : "forge:ore_shapeless",
                ingredients: [
                        [
                                type: "forge:ore_dict",
                                ore : "ingot$pascalMetal"
                        ]
                ],
                result     : [
                        item : "metallurgy:${metal}_nugget",
                        count: 9
                ]
        ]

        writeJson("ingot_shapeless", metal, ingotObj)
        writeJson("nugget_shapeless", metal, nuggetObj)
    }

    private static void generateAlloyRecipes(List<RecipesJsonGenHelper.Stack> alloy) {
        def ingredients = []

        for (i in 0..1) {
            for (j in 0..<alloy.get(i).count) {
                ingredients.push([
                        type: "forge:ore_dict",
                        ore : "dust${CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, alloy.get(i).name)}"
                ])
            }
        }

        def alloyRecipeObj = [
                type       : "forge:ore_shapeless",
                ingredients: ingredients,
                result     : [
                        item : "metallurgy:${alloy.get(2).name}_dust",
                        count: alloy.get(2).count
                ]
        ]

        writeJson("dust", alloy.get(2).name, alloyRecipeObj)
    }

    private static void writeJson(String type, String metal, Object obj) {
        def jsonObj = JsonOutput.toJson(obj)
        def prettyObj = JsonOutput.prettyPrint(jsonObj)
        def file = new File(RECIPES_DIR_PATH + type + "_" + metal + ".json")
        file.createNewFile()
        file.write(prettyObj)
    }
}