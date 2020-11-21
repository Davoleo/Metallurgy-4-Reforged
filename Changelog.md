##### 1.2.0-beta.1 (will be merged with 1.2.0 once its out)
- :bug: Fixed Model Issues with Zinc Buckler
- :bug: Fixed Vulcanite Buckler Not going on cooldown after a hit
- :bug: Made player arm correctly invisible in third person
- :dizzy: Improved brass knuckles punch animation
- :bug: Fixed arm rendering for slim player model
- :bento: added Recipes and localized names to Zinc Buckler
- :globe_with_meridians: added localized names for Sanguinite and Vulcanite Shields
- :sparkles: Ceruclase Shield (creates a time based ice shield of a specific radius around the player)
- :bug: Fixed Lemurite shield not handling invisibility properly in some cases
- :bug: Now Player hand are reset properly after blocking a hit with Bucklers
- :sparkles: added a special ice shield block that expires after a custom timespan (default ice texture right now)
- :sparkles: Orichalcum Buckler (reflects 200% of the damage it blocks to the source of the damage)
- :bento: Improvements to non-metal textures
- :bento: Added models and texures for the new shields
- :bento: Improved old textures of Rubracium Gauntlet and Lemurite Shield  

### 1.2.0-alpha.1
- :sparkles: Mob now have a chance to spawn with random Metallurgy equipment ([#284](https://github.com/Davoleo/Metallurgy-4-Reforged/issues/284))
- :dart: Atlarus and Vulcanite Armor effects now works on mobs
- :sparkles: You can now use Metallurgy Metal blocks of metals that have an ore harvest level greater than 1 as base blocks for Beacon Structures ([#294](https://github.com/Davoleo/Metallurgy-4-Reforged/issues/294))
- :sparkles: NEW GADGET - Brass Knuckles: Really Fast Attack speed and low damage level ([#267](https://github.com/Davoleo/Metallurgy-4-Reforged/issues/267))
- :bento: Add Localized name and recipe to Etherium Goggles
- :balance_scale: Reworked Decorative metal blocks recipes to be more balanced (buff) and to avoid recipe conflicts with vanilla
- :sparkles: Added a new Block (Bi-Metal Structure) (as of right now it’s used to create decorative blocks, but it also might be used as a machine frames for tile entities)
- :recycle: Refactor Recipe JSON Files
- :sparkles: EnderIO Integration for alloy recipes in the alloy smelter [#295](https://github.com/Davoleo/Metallurgy-4-Reforged/issues/295)
- :sparkles: Bucklers (a new kind of shield lighter and more active)
	- Allows to run and walk at normal speed wile holding it
	- As well as allowing to hit entities and the air :P
	- They Go on cooldown after a hit
- :sparkles: Zinc Buckler (Tier 1) (no effect)

### 1.1.2
- :bug: Fix Shadow Iron and Lemurite ores having stone filler texture (instead of netherrack)
- :bento: Add Missing ore blocks breaking particle textures ([#292](https://github.com/Davoleo/Metallurgy-4-Reforged/issues/292))
- :balance_scale: Make Atlarus a bit more common by reducing Y range and increasing vein size ([#291](https://github.com/Davoleo/Metallurgy-4-Reforged/issues/291))
- :balance_scale: Fix Decorative Blocks recipes result count (they should give more than one block)
- :bug: Fix Vanilla Metals alloys recipes (previously weren’t craftable in the Alloyer)
- :ambulance: Fix crash when disabling certain materials in the materials.json file
- :bug: Fixed Some Armor effects not working correctly
- :bento: Vanilla Decorative blocks now have recipes
- :bug: Fix a rendering bug where sometimes metal ingot ingredients of some recipes where shown with a size greater than 1
- :bug: Fix Strength I and Strength II traits not disabling correctly when the Armor is broken

### 1.1.1
- :sparkles: Add Gold and Iron Decorative Blocks  ([#286](https://github.com/Davoleo/Metallurgy-4-Reforged/issues/286))
- :globe_with_meridians: Updated Simplified Chinese translations ([#288](https://github.com/Davoleo/Metallurgy-4-Reforged/issues/288))
- :ambulance: Hopefully Fixed all Metal NPEs when you disable a metal from the materials.json file ([#286](https://github.com/Davoleo/Metallurgy-4-Reforged/issues/286))
- :pencil2: Fixed Celenegil Tooltip showing the wrong level of Resistance ([#286](https://github.com/Davoleo/Metallurgy-4-Reforged/issues/286))
- :bug: Fixed Wrong JEI Info about alloys Ingredients count ([#286](https://github.com/Davoleo/Metallurgy-4-Reforged/issues/286))
- :bug: Fixed Vulcanite Fire Immunity protection (previously was just partial now it actually protects you completely from fire and lava damage)
- :bug: Fixed some Conarm Traits from working fine despite the armor being broken
- :bulb: add a new advice comment to ct_examples.zs to address issues when trying to remove ore-dicted crusher recipes through CraftTweaker integration
- :speech_balloon: Rephrased Quicksilver Armor Effect config entry comment and Quicksilver Conarm trait to a nicer form
- :sparkles: Implemented a new ore model system to allow ore textures to be compatible with other resource packs ([#278](https://github.com/Davoleo/Metallurgy-4-Reforged/issues/278))

### 1.1.0
- :bento: Revamped Astral silver textures
- :bento: Improved ore particles
- :balance_scale: Buffed Ignatius Sword Fire Aspect Effect (60% chance to cause fire damage)
- :tada: Improved Fire Aspect II effect on Vulcanite Swords (now it affects enemies in a bigger radius and has a cute visual and audio effect)
- :sparkles: :heavy_plus_sign: Silent Gems Integration
- :lipstick: Fixed Deep Iron Pickaxe Tooltip color
- :dart: Now Tools and Armor Sets anvil repairing is enabled by default
- :sparkles: Atlarus Armor Effect: A Strong gust of wind in a random direction saves you from taking falling damage
- :sparkles: Etherium Armor Effect: Allows you to go through any kind of wall when sneaking
- :bug: Fixed a bug that prevented player from making Tinkers’ tools out of brass
- :sparkles: Desichalkos Armor Effect: When you can take special blocks from endermen
- :sparkles: Desichalkos Sword Effect Reworked: Allows the player to teleport in the direction they’re looking at while right clicking
- :dart: Quicksilver Armor Effect Improved: Now Crossbows are affected as well
- :sparkles: Etherium Goggles: Special Gadget Goggles that allows the player to see redstone related blocks behind other blocks
- :sparkles: Atlarus Sword Effect: Pushes any nearby entity away from the player on right click
- :sparkles: Atlarus Axe Effect: Right Click to break any type of leaves in range of the gust of wind
- :sparkles: Atlarus Hoe Effect: Right Click to break grass blocks around the player
- :sparkles: Haderoth Armor Effect: Allows the player to walk on lava
- :bug: Fixed a bug with Kalendrite, Amordrine, Etherium
- :globe_with_meridians: Added Brazilian Portuguese Translation
- :globe_with_meridians: Added Swedish Translation
- :alien: Reworked the recipes system to be compatible with Crafttweaker
- :bug: Fixed some issues with repairing tinkers tools
- :wrench: Made Thermite fuel value configurable and tweaked the JEI info page
- :wrench: Make the new Desichalkos Armor Effect configurable
- :bento: Revamped Vanilla Metals dust textures
- :bento: Changed the detector main metal from Iron to Angmallen
- :bug: Fixed Eximite Inconsistent generation and made sure it’s generated at negative Z and X coords as well
- :zap: Fix a Memory Leak when you wore Armors that gave you a ticking effect
- :bug: Fixed some issues with particle colors
- :bug: Maybe fix an issue with Ore blocks shadow when using Optifine
- :sparkles: Osmium now only generates near bedrock (Y level: [3-10])
- :sparkles: Sanguinite generates more frequently near Nether Fortresses
- :art: Made the Alloy Recipes handler more flexible
- :dart: Now the alloyer can accept Oredictionary alternatives of Metallurgy Metals
- :wrench: Optimized and made OreDict Crushing recipes loading configurable (in case someone is having issues with removing recipes from the Crusher via CraftTweaker)
- :sparkles: Metallurgy Machines can now be powered by Immersive Engineering’s External Heater
- :wrench: materials.json config is now disabled by default, and you need to enable it in general.cfg to be able to customize and remove materials
- :dart: Fix Flint and Ignatius and Flint and Vulcanite to only stack at amounts of 1 (vanilla flint and steel behaviour)
- :ambulance: Fixed some crashes when you removed a metal from the materials.json config
- :sparkles: Integrate nether ores generation with NetherEX
- :wrench: Add a blacklist to disable Quicksilver armor effect on some items, if they break it
- :wrench: Add Magic Mirror to the blacklist declared before ^^^

### 1.0.2
- Fix a crash when placing the metal detector inside of a Tinkers’ Construct Crafting Station ([#260](https://github.com/Davoleo/Metallurgy-4-Reforged/issues/260))
- Fix an issue that prevented players from crafting different kinds of steel from other mods ([#261](https://github.com/Davoleo/Metallurgy-4-Reforged/issues/261))
- Added the missing dust blending recipes (now you should be able to make alloys even when you don’t have access to an Alloyer)
- Implemented Animated Ore Particles (finally, yay)
- Fixed an issue where ore particles would get other vanilla or modded textures ([#251](https://github.com/Davoleo/Metallurgy-4-Reforged/issues/251))

### 1.0.1
- Fix Flint and Vulcanite Lava block fluid update & wrong position
- Fix inventory rubracium gauntlet check to ignore durability
- Now every hit damages one of the two rubracium gauntlets instead of both at the same time
- Implemented new ore gen system which will help to add special conditions when spawning ores
    - Platinum: Make platinum a bit more abundant in warm zones like jungle or savanna.(@Klazkin idea)
    - Vulcanite: It spawns only at lava sea level
    - Eximite: It Spawns only on the small islands (@Luke616 idea)
    - Shadow iron spawns near the bedrock (top) (@Luke616 idea)
    - Prometheum spawns only in jungle biome
- implemented configurable biome ore spawn
- ProjectE Alloys EMC Values are not calculated dynamically anymore
- Fixed Github Issues:
	- [#248](https://github.com/Davoleo/Metallurgy-4-Reforged/issues/248)
	- [#94](https://github.com/Davoleo/Metallurgy-4-Reforged/issues/94)
	- [#257](https://github.com/Davoleo/Metallurgy-4-Reforged/issues/257)
	- [#256](https://github.com/Davoleo/Metallurgy-4-Reforged/issues/256)
- New Configuration option to cancel registration of metal items and blocks by type

### 1.0.0
- If you’re curious about the actual code changes behind this update check this PR out ([#242](https://github.com/Davoleo/Metallurgy-4-Reforged/pull/242))
- Reworked Most of the codebase!!
- Removed the option to disable items directly from the config (Was extremely limited and caused console errors) [you can use Crafttweaker to hide items and remove recipes while a new system might be implemented if the request is high (if you find that feature “ESSENTIAL” feel free to open a suggestion issue on github)]
- Brand new json configuration that allows you to edit nearly any material property!
- The new json config also allows you to add special attributes to specific metals
- Added the long waited Metal Decorative Blocks! (Bricks, Large Bricks, Reinforced Glass, Engraved Blocks, Hazard Blocks, Crystals)
- Fixed an Experience Duplication Bug with Crusher and Alloyer Machines
- Fixed any possible gauntlet duplication bug (now you get 2 gauntlets on craft automatically [no need to create a new one anymore yay])
- Items and Normal Blocks Texture Rework
- Now Recipes are created through IRecipeFactory (which basically means that adding recipes for things that are made of 50 different metals is not a problem anymore [one JSON Recipe takes care of the same recipe with all the different metals])
- Removed Molten Metal Fluid Item-Blocks (now you can directly find Forge Buckets inside the Fluids Creative Tab)
- Fixed Github Issues:
	- [#197](https://github.com/Davoleo/Metallurgy-4-Reforged/issues/197)
	- [#214](https://github.com/Davoleo/Metallurgy-4-Reforged/issues/214)
	- [#245](https://github.com/Davoleo/Metallurgy-4-Reforged/issues/245)
	- [#247](https://github.com/Davoleo/Metallurgy-4-Reforged/issues/247)
	- [#252](https://github.com/Davoleo/Metallurgy-4-Reforged/issues/252)
- Balance Changes:
- Buffed Etherium Durability and Efficiency


### 0.3.3
- Fixed Armor Effects not working ([#234](https://github.com/Davoleo/Metallurgy-4-Reforged/issues/234))
- Disable Krik Armor Height Level debug Logging
- Add config options to disable ore particles and ore light emission ([#235](https://github.com/Davoleo/Metallurgy-4-Reforged/issues/235))
- Thermite is now powerful fuel for any kind of machine
- Fixed an issue with JEI Integration ([#237](https://github.com/Davoleo/Metallurgy-4-Reforged/issues/237))

### 0.3.2
- Nerf Celenegil Armor Effect ([#221](https://github.com/Davoleo/Metallurgy-4-Reforged/issues/221))
- Fixed some bugs: ([#230](https://github.com/Davoleo/Metallurgy-4-Reforged/issues/230))
	- Fixed Particles being registered on the server
	- Fixed HUD Handler being registered on the server
	- Fixed Wiki Item Model being loaded on the server
- Added Missing Entry in the modMaterials Array that caused an IOOB Exception

### 0.3.1
- New Material: Etherium
- Sublimation Chamber Crafttweaker Integration (Custom effects from custom items)
- New ore Textures (Tweaked by Matpac)
- Added some missing localizations ([#206](https://github.com/Davoleo/Metallurgy-4-Reforged/issues/206))
- Increase Sublimation Chamber effect information ([#207](https://github.com/Davoleo/Metallurgy-4-Reforged/issues/207))
- Added a dimension whitelist filter to control where tar lakes are generated
- Added different Ore particles depending on the ore harvest level ([#204](https://github.com/Davoleo/Metallurgy-4-Reforged/issues/204))
- Fixed inverted jei crusher recipes registration ([#211](https://github.com/Davoleo/Metallurgy-4-Reforged/issues/211))
- Fixed Tool Tooltips getting loaded for every tool class
- Implemented new Ignatius tool effects ([#210](https://github.com/Davoleo/Metallurgy-4-Reforged/issues/210))
	- Tools that got new effects: Axe, Shovel, Pickaxe
- Italian Mod Localization
- Excluded every item that isn’t a bow from the Quicksilver armor effect and trait
- Fixed a bug that blocked the player in middle air with no chance to get back down while wearing Krik Armor
- Added a new Item that links to the Metallurgy wiki (This hopefully helps people on the first experience with this mod)
- Fixed ConcurrentModificationException when removing Sublimation Recipes with Crafttweaker
- Reworked the effect for the Krik Armor


### 0.2.3
- CraftTweaker Integration for Metallurgy Machines
- Fixed infinite xp shift-clicking the machines’ output in your inventory while having full inventory
- Fixed Tar lakes not generating in non-default world generators ([#186](https://github.com/Davoleo/Metallurgy-4-Reforged/issues/186))
- Fixed Console spam when a player is holding a Tinker's tools using Deep Iron ([#294](https://github.com/Davoleo/Metallurgy-4-Reforged/issues/194))
- Implemented a config entry to allow the end user to restore classic vanilla axes damage values
- Improvements concerning sword and tool effects handling ([#189](https://github.com/Davoleo/Metallurgy-4-Reforged/issues/189))
- Fixed a bug that caused tooltip registration failure ([#195](https://github.com/Davoleo/Metallurgy-4-Reforged/issues/195))
- Improved Phosphorus Lantern Spawn Block ([#190](https://github.com/Davoleo/Metallurgy-4-Reforged/issues/190))
- Introducing The Sublimation Chamber
	- new Machine that allows you to sublimate blocks of some materials giving a certain effect to all the players within its area
	- the peculiarity of this machine is that it provides you with a long duration effect, so you can keep the effect also when leaving the area
- Fix molten fluids names to respect conventional naming (Should fix bug of the tinker smeltery not alloying materials)
- Allow the end user to blacklist tinker materials from being loaded in the config
- Fix crusher metadata exploit ([#205](https://github.com/Davoleo/Metallurgy-4-Reforged/issues/205))
- Fix Glowing effect Robotics Part Compatibility ([#203](https://github.com/Davoleo/Metallurgy-4-Reforged/issues/203))

### 0.2.2
- Fixed Deep Iron trait
- Fixed Phosphorus Lantern server crash
- Added entity collision Phosphorus Lantern config
- Fixed Phosphorus Lantern placements
- Fixed angmallen hoe and quicksilver tools recipes
- Repaired Hunger Gauntlet System and fixed slot lock bug
- Fix Null Pointer Exception in Gauntlet capability crash (fixes crash with BWM and LemonLib)
- Change armor registry names to standard Vanilla notation (should fix issues with Material Changer)
- Added Bow tinker parts
- Modified Ore Rarity Config Comment
- Increment tool head durability of Metallurgy Tinker Integration | Increased Ammo bolt/arrow
- Decreased tool head durability of Metallurgy Tinker Integration
- Increased tool extra durability and decreased tool handle durability of Platinum ( Metallurgy Tinker Integration )
- Decreased drastically tar lakes spawnrate chance
- Fixed crash with Lucraft
- Added JEI description for thermite and lemurite shield

### 0.2.1
- Reworked codebase (optimizations and simplifications)
- Added Taiwan and China localizations
- Fixed crash when playing with BWM and HCChickens enabled
- Rephrased and re-formatted trait descriptions
- Fix rubracium gauntlets interfering with second skin layer
- Industrial Foregoing Laser Drill Integration
- Made tools repairable in the anvil (disabled by default in the mod config)
- Fixed Game freeze when wearing certain Construct’s Armory armors
- Converted blend recipes to shapeless from shaped
- Lemurite Shield (Makes you invisible to other players and mobs)
- Added deep iron trait for TiCon
- Fixed Deep Iron Enchanting Bug
- Fixed Shadow Iron max Y level
- Decrease potion expiration delay
- Increased retrogen debug level to INFO
- Fixed Prometheum and Shadow Steel armor trait
- ProjectE EMC Integration (default EMC values for materials)
- Fixed a de-sync bug that allowed saturation to be higher than hunger points
- Reworked road textures
- Fix recipe registration
- Potash Fertilizer
- Fix Gauntlet duplication bug ([#163](https://github.com/Davoleo/Metallurgy-4-Reforged/pull/163))
- Fix Material Changer compat bug ([#165](https://github.com/Davoleo/Metallurgy-4-Reforged/pull/165))
- Phosphorus Lamp
- Remove duplication effect from all tools except swords
- Added null stats check (potentially fix all conarm related crashes)
- Tweak: Gauntlet now depletes saturation before depleting hunger points
- Many others Gauntlet fixes
- New reworked textures by Matpac! :D (hype)

### 0.2.0
Tar rework - Krik Armor - Construct’s Armory Integration - Nuggets

- Fixed Midasium Leggings & Brass Shovel Textures
- Added Fluid Tar
- Tar lakes world generation
- Removed tinkers integration materials (lemurite, meutoite)
- Removed tinkers integration head material (rubracium)
- Added Fluid Tar Bucket
- Reduced Atlarus and Adamantine vein size
- Renamed “Random Effects” trait to “King Dice”
- Added Custom fog for Fluid Tar
- In-world solidification recipe (Fluid Tar :arrow_right: Tar Ore)
- Reworked custom drops system
- Added Tar to furnace/machines fuels
- Re-Balanced road recipes (to match the bitumen rework)
- Added in-game tooltips & JEI information for tar crafting
- Fixed some Typos
- Edited many translation keys (Added the modid)
- Implemented Nuggets + Nuggets Textures (by Matpac)
- Added Nugget2Ingot Recipes and viceversa
- Fixed Platinum Helmet effect tooltip locale key
- Rephrased Quicksilver tooltip to adapt to the new Quicksilver effect
- Partially fixed CRAZY midasium duplication effect
- Lutetium & Osmium Armor & Effect (Knockback resistance)
- Implemented the brand new Krik Armor
- Added new special effect to Krik Armor ()
- Removed curse of binding from Platinum Helmet
- Re-Balanced Tinker tool stats
- Fixed ores not being generated in Aroma1997 Dimension
- Fixed Missing tinker tooltips
- Rephrased Tinker tooltips descriptions
- Fixed Traits being transposed from tiCon to conArm and viceversa
- Rubracium Gauntlet effect now gets force-disabled when Better With Mods is loaded
- Fixed a crash concerning conArm blindness and prometheum traits


- Anticipated OreDict initialization (from preInit to “right after items are loaded”)
	- This fixes some integration issues with mods like JAOPCA or Mekanism

- Construct’s Armory Integration
	- Armor parts
	- Armor Traits
		- “Quickly” Trait
		- “Strongly” Trait
		- “Resistance” Trait
		- “Jump Master” Trait
		- “Cat Eyes” Trait
		- “Deeply” Trait
		- “Foodly” Trait
		- “Prometheum” Trait
		- “Volcano” Trait
		- “Strongly II” Trait
		- “Blindness” Trait
	- Config entry to disable Integration

### 0.1.1
- Fixed Alloyer crash when opening the GUI
- Fixed wrong config name and comment for warning message

### 0.1.0
- Added Thermite dust
- Added Molten Thermite as a Smeltery Fuel
- Added some logging information on game startup
- Added reworked adamantine armor effect
- Fixed game crash when TCon Materials are already registered #98
- Fixed lighters offset, Client/Server Sync, Item Damage, Fire placement
- Fixed Gauntlet crash with LemonLib (NPE) ([#100](https://github.com/Davoleo/Metallurgy-4-Reforged/issues/100))
- Fixed Config GUI Client-Server Synchronization
- Fixed gauntlet bypassing saturation
- Edited Gauntlet durability
- Added Retrogen
- Fixed Ore Generation
- Added Ore Generation config
- Added Config to remove ore generation (set the veinsize to 0 to remove the generation)
- Added Gauntlet config
- Added Config to remove all tool/sword and armor
- Added Config to disable Tinker integration
- Added a representative item to tinker materials (ingot)
- Added Road Config
- Rework of all molten metal colors
- Implemented reworked textures
- Balanced road speed (Once again)
- Re-Organized Config files and categories
- Rephrased some config names and comments
- Creative tab icons become empty if the item is missing
- Updated localizations
- Added tar as a fuel
- Fixed tar Oredict entry
- Added “full set” to tooltips

### 0.0.7
- Fixed Tinkers’ Construct hard dependency
- Fixed Midasium sword player inventory dupe
- French localization update

### 0.0.6
- Flint and Ignatius (3x3 area fire lighter)
- Flint and Vulcanite (enhanced flint and ignatius)
- Fixed road hardness
- Renamed “Machines” creative tab to “Special” creative tab
- Buffed axes damage
- Nerfed swords damage
- Added Rubracium Gauntlet (Punch Effect)
- Added Biome Specific Ore Generation system
- Added a config effect to suppress the alpha warning
- Added French Localization [ Need to Update ]
- Added Desichalkos Sword effect (Random effect on the target)
- Removed Looting Enchantment/Added Midasium custom effect ( 50% chance to duplicate your loot [ 2 diamond - with Midasium pickaxe :arrow_right: 4 diamond ] )
- Now tooltips get automatically hidden when an effect is disabled
- Rephrased tooltips
- Added Comments to effects config
- Fixed a server side crash
- Mod Integration: Tinkers’ Construct [ Not Final - May be edited ]
- Added some Custom traits to TiC integration
    - Obscure Trait
    - Vulcan Trait [ 0 - 1 - 2 ]
    - Wither Trait
    - Life Steal Trait
    - King Dice Trait

### 0.0.5
- Fixed Molten Hepatizon color
- Reworked road to avoid it being too slippery
- Fixed 2 ore = 2 dust JEI Crusher Bug
- Fixed Ore Generation Cascading Lag and Fixed End generation
- Fixed Server Crash for Road Block
- Fixed Crafting of enchanted Midasium tool
- Increased Ore generation in Overworld and in the Nether
- Increased durability of Carmot Tool
- Crusher Improved ( Now you can crush ore of other Mod and Vanilla)
- Fixed molten metal colors being too dark
- Tool durability balancement
- Added Eximite Helmet Effect ( The endermans don't get angry if you look at them If they are already angry, it does not work )
- Added Inolashite Armor Effect ( One Punch Man Style [Hitting enemies by empty hand you will inflict 3 hearts by knocking them away and your food will drop by 1] )
- Shadow Steel Armor Effect ( Grants blindness immunity, increased toughness based on light )
- Quicksilver Armor Effect ( All your actions will be faster [ Bow attack speed, eating speed, drinking speed These are the ones we are aware of] )
- Shadow Steel Sword Effect ( Increased attack speed based on light [ Darker is better ] )
- Orichalcum Sword Effect ( Give Strenght I for 7 sec when you kill an enemy )
- Celenegil Sword Effect ( Gives Speed I and Strenght I for 7 sec when you kill an enemy )
- Shadow Steel Tools Effects ( Increased mining speed based on light [ Darker is better ] )

### 0.0.4
- BACKUP your world before loading it with this version
- Changed ModID from “m5” to “metallurgy” (world-breaking change)
- Added Deep Iron pickaxe effect
- Fixed tooltips useless empty lines ([#65](https://github.com/Davoleo/Metallurgy-4-Reforged/issues/65))
- Added Configuration files
- Updated mappings to “stable_39”
- Added JEI Integration for Metallurgy machines
- Fixed ore generation cascading lag ([#70](https://github.com/Davoleo/Metallurgy-4-Reforged/issues/70))
- Added config to enable/disable armor/tools effects
- Added fortune enchant to all Midasium Tools (except hoe)
- Added "Glowing" effect to Mithril Sword
- Added Shadow Iron Armor Effect (Blindness-proof)
- Added Ceruclase Armor Effect (Inflicts Slowness on attackers when hit)
- Added Ceruclase Sword (Gives slowness)

### 0.0.3
- Fixed Astral Silver typo ([#63](https://github.com/Davoleo/Metallurgy-4-Reforged/issues/63))
- Buffed ore blast resistance ([#62](https://github.com/Davoleo/Metallurgy-4-Reforged/issues/62))
- Fixed Silver ore generation bug ([#60](https://github.com/Davoleo/Metallurgy-4-Reforged/issues/60))
- Fixed Oureclase pickaxe harvest level ([#59](https://github.com/Davoleo/Metallurgy-4-Reforged/issues/59))

### 0.0.2
- Some minor fixes
- Fixed the "unable to climb certain blocks" bug
- Deep Iron Effect rework
- Fixed glowing effect
- Tweaked sanguinite sword effect
- Fixed manganese harvest level bug

### 0.0.1 [First alpha]
- Major features and content
- Nearly every original feature and texture

### Pre-Release Disclaimer: This is a pre-release of X.X.X, you could find bugs in it, if you find them while you play it, please consider reporting them here: https://github.com/Davoleo/Metallurgy-4-Reforged/issues