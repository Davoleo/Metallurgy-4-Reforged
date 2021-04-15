/*==============================================================================
 = Class: RecipeJsonGenerator
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.recipe

import com.google.common.base.CaseFormat
import it.hurts.metallurgy_reforged.integration.IntegrationProjectE

class RecipeJsonGenerator {

    static final def RECIPES_DIR_PATH = "src/main/resources/assets/metallurgy/recipes/generated/"

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
            engraved_block  : ["ICI", "CDC", "ICI"],
            crystals        : [" N ", "NIN", "IDI"],
            bricks          : ["III", "IDI", "III"],
            large_bricks    : ["IBI", "BDB", "IBI"],
            hazard_block    : ["ICI", "CDC", "ICI"],
            reinforced_glass: ["IGI", "GDG", "IGI"],

            ingot           : ["NNN", "NNN", "NNN"],
            bucket          : ["I I", " I "]
    ]

    static def markers = [
            adamantine    : RecipeGenHelper.Markers.ALL,
            alduorite     : RecipeGenHelper.Markers.NO_TOOLS_NO_ARMOR,
            amordrine     : RecipeGenHelper.Markers.ALL,
            angmallen     : RecipeGenHelper.Markers.ALL,
            astral_silver : RecipeGenHelper.Markers.ALL,
            atlarus       : RecipeGenHelper.Markers.ALL,
            black_steel   : RecipeGenHelper.Markers.ALL,
            brass         : RecipeGenHelper.Markers.ALL,
            bronze        : RecipeGenHelper.Markers.ALL,
            carmot        : RecipeGenHelper.Markers.ALL,
            celenegil     : RecipeGenHelper.Markers.ALL,
            ceruclase     : RecipeGenHelper.Markers.ALL,
            copper        : RecipeGenHelper.Markers.ALL,
            damascus_steel: RecipeGenHelper.Markers.ALL,
            deep_iron     : RecipeGenHelper.Markers.ALL,
            desichalkos   : RecipeGenHelper.Markers.ALL,
            electrum      : RecipeGenHelper.Markers.ALL,
            etherium      : RecipeGenHelper.Markers.ALL,
            eximite       : RecipeGenHelper.Markers.ALL,
            haderoth      : RecipeGenHelper.Markers.ALL,
            hepatizon     : RecipeGenHelper.Markers.ALL,
            ignatius      : RecipeGenHelper.Markers.ALL,
            infuscolium   : RecipeGenHelper.Markers.NO_TOOLS_NO_ARMOR,
            inolashite    : RecipeGenHelper.Markers.ALL,
            kalendrite    : RecipeGenHelper.Markers.ALL,
            krik          : RecipeGenHelper.Markers.ALL,
            lemurite      : RecipeGenHelper.Markers.NO_TOOLS_NO_ARMOR,
            lutetium      : RecipeGenHelper.Markers.NO_TOOLS,
            manganese     : RecipeGenHelper.Markers.NO_TOOLS_NO_ARMOR,
            meutoite      : RecipeGenHelper.Markers.NO_TOOLS_NO_ARMOR,
            midasium      : RecipeGenHelper.Markers.ALL,
            mithril       : RecipeGenHelper.Markers.ALL,
            orichalcum    : RecipeGenHelper.Markers.ALL,
            osmium        : RecipeGenHelper.Markers.NO_TOOLS,
            oureclase     : RecipeGenHelper.Markers.ALL,
            platinum      : RecipeGenHelper.Markers.ALL,
            prometheum    : RecipeGenHelper.Markers.ALL,
            quicksilver   : RecipeGenHelper.Markers.ALL,
            rubracium     : RecipeGenHelper.Markers.NO_TOOLS_NO_ARMOR,
            sanguinite    : RecipeGenHelper.Markers.ALL,
            shadow_iron   : RecipeGenHelper.Markers.ALL,
            shadow_steel  : RecipeGenHelper.Markers.ALL,
            silver        : RecipeGenHelper.Markers.ALL,
            steel         : RecipeGenHelper.Markers.ALL,
            tartarite     : RecipeGenHelper.Markers.ALL,
            tin           : RecipeGenHelper.Markers.NO_TOOLS_NO_ARMOR,
            vulcanite     : RecipeGenHelper.Markers.ALL,
            vyroxeres     : RecipeGenHelper.Markers.ALL,
            zinc          : RecipeGenHelper.Markers.NO_TOOLS_NO_ARMOR,
    ]

    static def alloys = [
            [
                    new RecipeGenHelper.Stack("copper", 3),
                    new RecipeGenHelper.Stack("tin", 1),
                    new RecipeGenHelper.Stack("bronze", 4)
            ],
            [
                    new RecipeGenHelper.Stack("shadow_iron", 2),
                    new RecipeGenHelper.Stack("lemurite", 1),
                    new RecipeGenHelper.Stack("shadow_steel", 3)
            ],
            [
                    new RecipeGenHelper.Stack("alduorite", 1),
                    new RecipeGenHelper.Stack("ceruclase", 1),
                    new RecipeGenHelper.Stack("inolashite", 2)
            ],
            [
                    new RecipeGenHelper.Stack("copper", 3),
                    new RecipeGenHelper.Stack("zinc", 1),
                    new RecipeGenHelper.Stack("brass", 4)
            ],
            [
                    new RecipeGenHelper.Stack("iron", 1),
                    new RecipeGenHelper.Stack("manganese", 3),
                    new RecipeGenHelper.Stack("steel", 2)
            ],
            [
                    new RecipeGenHelper.Stack("deep_iron", 3),
                    new RecipeGenHelper.Stack("infuscolium", 1),
                    new RecipeGenHelper.Stack("black_steel", 4)
            ],
            [
                    new RecipeGenHelper.Stack("iron", 1),
                    new RecipeGenHelper.Stack("bronze", 2),
                    new RecipeGenHelper.Stack("damascus_steel", 3)
            ],
            [
                    new RecipeGenHelper.Stack("silver", 1),
                    new RecipeGenHelper.Stack("gold", 1),
                    new RecipeGenHelper.Stack("electrum", 2)
            ],
            [
                    new RecipeGenHelper.Stack("orichalcum", 1),
                    new RecipeGenHelper.Stack("platinum", 1),
                    new RecipeGenHelper.Stack("celenegil", 2)
            ],
            [
                    new RecipeGenHelper.Stack("kalendrite", 1),
                    new RecipeGenHelper.Stack("platinum", 1),
                    new RecipeGenHelper.Stack("amordrine", 2)
            ],
            [
                    new RecipeGenHelper.Stack("mithril", 1),
                    new RecipeGenHelper.Stack("rubracium", 2),
                    new RecipeGenHelper.Stack("haderoth", 3)
            ],
            [
                    new RecipeGenHelper.Stack("adamantine", 1),
                    new RecipeGenHelper.Stack("atlarus", 1),
                    new RecipeGenHelper.Stack("tartarite", 1)
            ],
            [
                    new RecipeGenHelper.Stack("eximite", 1),
                    new RecipeGenHelper.Stack("meutoite", 1),
                    new RecipeGenHelper.Stack("desichalkos", 2)
            ],
            [
                    new RecipeGenHelper.Stack("iron", 1),
                    new RecipeGenHelper.Stack("gold", 1),
                    new RecipeGenHelper.Stack("angmallen", 2)
            ],
            [
                    new RecipeGenHelper.Stack("infuscolium", 1),
                    new RecipeGenHelper.Stack("steel", 1),
                    new RecipeGenHelper.Stack("hepatizon", 2)
            ],
            [
                    new RecipeGenHelper.Stack("silver", 1),
                    new RecipeGenHelper.Stack("mithril", 1),
                    new RecipeGenHelper.Stack("quicksilver", 2)
            ],
            [
                    new RecipeGenHelper.Stack("lutetium", 1),
                    new RecipeGenHelper.Stack("osmium", 1),
                    new RecipeGenHelper.Stack("krik", 2)
            ],
            [
                    new RecipeGenHelper.Stack("sanguinite", 1),
                    new RecipeGenHelper.Stack("carmot", 1),
                    new RecipeGenHelper.Stack("etherium", 2)
            ],
    ]

    static void main(String[] args) {
        materials.forEach({ metal ->
            generateShapedRecipes(metal)
            println("$metal Shaped Recipes generated!")
            generateShapelessRecipes(metal)
            println("$metal Shapeless Recipes generated!")
        })
        println("------------------------------------------")

        alloys.each { alloy -> generateAlloyRecipes(alloy) }
        println("Alloy Recipes generated!")
    }

    private static void generateShapedRecipes(String metal) {
        String pascalName = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, metal)

        if (markers[metal] == RecipeGenHelper.Markers.ALL || markers[metal] == RecipeGenHelper.Markers.NO_TOOLS) {
            //-------------------------- ARMOR ----------------------------------            
            //helmet
            def helmetObj = [
                    type      : "forge:ore_shaped",
                    conditions: [
                            [
                                    type      : "metallurgy:registry_condition",
                                    depends_on: "item/armor_set"
                            ]
                    ],
                    pattern   : shapedPatterns["helmet"],
                    key       : [
                            "I": [
                                    type: "forge:ore_dict",
                                    ore : "ingot$pascalName"
                            ]
                    ],
                    result    : [
                            item: "metallurgy:${metal}_helmet"
                    ]
            ]

            //chestplate
            def chestplateObj = [
                    type      : "forge:ore_shaped",
                    conditions: [
                            [
                                    type      : "metallurgy:registry_condition",
                                    depends_on: "item/armor_set"
                            ]
                    ],
                    pattern   : shapedPatterns["chestplate"],
                    key       : [
                            "I": [
                                    type: "forge:ore_dict",
                                    ore : "ingot$pascalName"
                            ]
                    ],
                    result    : [
                            item: "metallurgy:${metal}_chestplate"
                    ]
            ]
            //leggings
            def leggingsObj = [
                    type      : "forge:ore_shaped",
                    conditions: [
                            [
                                    type      : "metallurgy:registry_condition",
                                    depends_on: "item/armor_set"
                            ]
                    ],
                    pattern   : shapedPatterns["leggings"],
                    key       : [
                            "I": [
                                    type: "forge:ore_dict",
                                    ore : "ingot$pascalName"
                            ]
                    ],
                    result    : [
                            item: "metallurgy:${metal}_leggings"
                    ]
            ]
            //boots
            def bootsObj = [
                    type      : "forge:ore_shaped",
                    conditions: [
                            [
                                    type      : "metallurgy:registry_condition",
                                    depends_on: "item/armor_set"
                            ]
                    ],
                    pattern   : shapedPatterns["boots"],
                    key       : [
                            "I": [
                                    type: "forge:ore_dict",
                                    ore : "ingot$pascalName"
                            ]
                    ],
                    result    : [
                            item: "metallurgy:${metal}_boots"
                    ]
            ]

            RecipeGenHelper.writeJson("helmet", metal, helmetObj)
            RecipeGenHelper.writeJson("chestplate", metal, chestplateObj)
            RecipeGenHelper.writeJson("leggings", metal, leggingsObj)
            RecipeGenHelper.writeJson("boots", metal, bootsObj)
        }

        if (markers[metal] != RecipeGenHelper.Markers.NO_TOOLS_NO_ARMOR && markers[metal] != RecipeGenHelper.Markers.NO_TOOLS) {
            //------------------------------- TOOLS --------------------------------

            //axe
            def axeObj = [
                    type      : "forge:ore_shaped",
                    conditions: [
                            [
                                    type      : "metallurgy:registry_condition",
                                    depends_on: "tool/axe"
                            ]
                    ],
                    pattern   : shapedPatterns["axe"],
                    key       : [
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
                    type      : "forge:ore_shaped",
                    conditions: [
                            [
                                    type      : "metallurgy:registry_condition",
                                    depends_on: "tool/hoe"
                            ]
                    ],
                    pattern   : shapedPatterns["hoe"],
                    key       : [
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
                    type      : "forge:ore_shaped",
                    conditions: [
                            [
                                    type      : "metallurgy:registry_condition",
                                    depends_on: "tool/pickaxe"
                            ]
                    ],
                    pattern   : shapedPatterns["pickaxe"],
                    key       : [
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
                    type      : "forge:ore_shaped",
                    conditions: [
                            [
                                    type      : "metallurgy:registry_condition",
                                    depends_on: "tool/shovel"
                            ]
                    ],
                    pattern   : shapedPatterns["shovel"],
                    key       : [
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
                    type      : "forge:ore_shaped",
                    conditions: [
                            [
                                    type      : "metallurgy:registry_condition",
                                    depends_on: "tool/sword"
                            ]
                    ],
                    pattern   : shapedPatterns["sword"],
                    key       : [
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

            //vanilla buckets (can be made of metals that have tools)
            def bucketObj = [
                    type   : "forge:ore_shaped",
                    pattern: shapedPatterns["bucket"],
                    key    : [
                            "I": [
                                    type: "forge:ore_dict",
                                    ore : "ingot$pascalName"
                            ]
                    ],
                    result : [
                            item: "minecraft:bucket"
                    ]
            ]

            RecipeGenHelper.writeJson("axe", metal, axeObj)
            RecipeGenHelper.writeJson("hoe", metal, hoeObj)
            RecipeGenHelper.writeJson("pickaxe", metal, pickaxeObj)
            RecipeGenHelper.writeJson("shovel", metal, shovelObj)
            RecipeGenHelper.writeJson("sword", metal, swordObj)
            RecipeGenHelper.writeJson("bucket", metal, bucketObj)
        }

        //----------------- BLOCKS -----------------------
        def blockObj = [
                type      : "forge:ore_shaped",
                conditions: [
                        [
                                type      : "metallurgy:registry_condition",
                                depends_on: "block/block"
                        ]
                ],
                pattern   : shapedPatterns.block,
                key       : [
                        "I": [
                                type: "forge:ore_dict",
                                ore : "ingot$pascalName"
                        ]
                ],
                result    : [
                        item: "metallurgy:${metal}_block"
                ]
        ]
        def engravedObj = [
                type      : "forge:ore_shaped",
                conditions: [
                        [
                                type      : "metallurgy:registry_condition",
                                depends_on: "block/engraved_block"
                        ]
                ],
                pattern   : shapedPatterns.engraved_block,
                key       : [
                        "I": [
                                type: "forge:ore_dict",
                                ore : "ingot$pascalName"
                        ],
                        "D": [
                                item: "metallurgy:bimetal_structure_block"
                        ],
                        "C": [
                                item: "minecraft:stonebrick",
                                data: 3
                        ]
                ],
                result : [
                        item : "metallurgy:${metal}_engraved_block",
                        count: 32
                ]
        ]
        def crystalsObj = [
                type      : "forge:ore_shaped",
                conditions: [
                        [
                                type      : "metallurgy:registry_condition",
                                depends_on: "block/crystals"
                        ]
                ],
                pattern   : shapedPatterns.crystals,
                key       : [
                        "I": [
                                type: "forge:ore_dict",
                                ore : "ingot$pascalName"
                        ],
                        "N": [
                                type: "forge:ore_dict",
                                ore : "nugget$pascalName"
                        ],
                        "D": [
                                item: "metallurgy:bimetal_structure_block"
                        ]
                ],
                result : [
                        item : "metallurgy:${metal}_crystals",
                        count: 32
                ]
        ]
        def bricksObj = [
                type      : "forge:ore_shaped",
                conditions: [
                        [
                                type      : "metallurgy:registry_condition",
                                depends_on: "block/bricks"
                        ]
                ],
                pattern   : shapedPatterns.bricks,
                key       : [
                        "I": [
                                type: "forge:ore_dict",
                                ore : "ingot$pascalName"
                        ],
                        "D": [
                                item: "metallurgy:bimetal_structure_block"
                        ]
                ],
                result : [
                        item : "metallurgy:${metal}_bricks",
                        count: 48
                ]
        ]
        def largeBricksObj = [
                type      : "forge:ore_shaped",
                conditions: [
                        [
                                type      : "metallurgy:registry_condition",
                                depends_on: "block/large_bricks"
                        ]
                ],
                pattern   : shapedPatterns.large_bricks,
                key       : [
                        "I": [
                                type: "forge:ore_dict",
                                ore : "ingot$pascalName"
                        ],
                        "D": [
                                item: "metallurgy:bimetal_structure_block"
                        ],
                        "B": [
                                item: "minecraft:stonebrick",
                                data: 0
                        ]
                ],
                result : [
                        item : "metallurgy:${metal}_large_bricks",
                        count: 32
                ]
        ]
        def hazardBlocksObj = [
                type      : "forge:ore_shaped",
                conditions: [
                        [
                                type      : "metallurgy:registry_condition",
                                depends_on: "block/hazard_block"
                        ]
                ],
                pattern   : shapedPatterns.hazard_block,
                key       : [
                        "I": [
                                type: "forge:ore_dict",
                                ore : "ingot$pascalName"
                        ],
                        "C": [
                                item: "minecraft:cobblestone"
                        ],
                        "D": [
                                item: "metallurgy:bimetal_structure_block"
                        ]
                ],
                result : [
                        item : "metallurgy:${metal}_hazard_block",
                        count: 32
                ]
        ]
        def reinforcedGlassObj = [
                type      : "forge:ore_shaped",
                conditions: [
                        [
                                type      : "metallurgy:registry_condition",
                                depends_on: "block/reinforced_glass"
                        ]
                ],
                pattern   : shapedPatterns.reinforced_glass,
                key       : [
                        "I": [
                                type: "forge:ore_dict",
                                ore : "ingot$pascalName"
                        ],
                        "G": [
                                item: "minecraft:glass"
                        ],
                        "D": [
                                item: "metallurgy:bimetal_structure_block"
                        ]
                ],
                result : [
                        item : "metallurgy:${metal}_reinforced_glass",
                        count: 32
                ]
        ]

        // -------------------------- INGOT -------------------------
        def ingotObj = [
                type      : "forge:ore_shaped",
                conditions: [
                        [
                                type      : "metallurgy:registry_condition",
                                depends_on: "item/nugget"
                        ]
                ],
                pattern   : shapedPatterns.ingot,
                key       : [
                        "N": [
                                type: "forge:ore_dict",
                                ore : "nugget$pascalName"
                        ]
                ],
                result    : [
                        item: "metallurgy:${metal}_ingot"
                ]
        ]

        RecipeGenHelper.writeJson("block", metal, blockObj)
        RecipeGenHelper.writeJson("engraved_block", metal, engravedObj)
        RecipeGenHelper.writeJson("crystals", metal, crystalsObj)
        RecipeGenHelper.writeJson("bricks", metal, bricksObj)
        RecipeGenHelper.writeJson("large_bricks", metal, largeBricksObj)
        RecipeGenHelper.writeJson("hazard_block", metal, hazardBlocksObj)
        RecipeGenHelper.writeJson("reinforced_glass", metal, reinforcedGlassObj)
        RecipeGenHelper.writeJson("ingot", metal, ingotObj)
    }

    private static void generateShapelessRecipes(String metal) {

        def pascalMetal = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, metal)

        def ingotObj = [
                type       : "forge:ore_shapeless",
                conditions : [
                        [
                                type      : "metallurgy:registry_condition",
                                depends_on: "block/block"
                        ]
                ],
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
                conditions : [
                        [
                                type      : "metallurgy:registry_condition",
                                depends_on: "item/nugget"
                        ]
                ],
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

        def midasiumToGold = [
                type       : "forge:ore_shapeless",
                conditions : [
                        [
                                type      : "metallurgy:registry_condition",
                                depends_on: "item/dust"
                        ]
                ],
                ingredients: [
                        [
                                type: "forge:ore_dict",
                                ore : "dustMidasium"
                        ],
                        [
                                type: "forge:ore_dict",
                                ore : "dust$pascalMetal"
                        ]
                ],
                result     : [
                        item : "metallurgy:gold_dust",
                        count: 2
                ]
        ]

        RecipeGenHelper.writeJson("ingot_shapeless", metal, ingotObj)
        RecipeGenHelper.writeJson("nugget_shapeless", metal, nuggetObj)
        RecipeGenHelper.writeJson("midasium_to_gold", metal, midasiumToGold)
    }

    private static void generateAlloyRecipes(List<RecipeGenHelper.Stack> alloy) {
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
                conditions : [
                        [
                                type      : "metallurgy:registry_condition",
                                depends_on: "item/dust"
                        ]
                ],
                ingredients: ingredients,
                result     : [
                        item : "metallurgy:${alloy.get(2).name}_dust",
                        count: alloy.get(2).count
                ]
        ]

        RecipeGenHelper.writeJson("dust", alloy.get(2).name, alloyRecipeObj)
    }

}