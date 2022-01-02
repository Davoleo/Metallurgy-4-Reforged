## 1.3.0

### Important Notes and Disclaimers:

- This changelog is the continuation of `1.3.0-beta.2`, if you're coming from `1.2.12`, you might want to read those
  beta changelogs first.
- This is a **very big update** which changes many of the default configuration values to better balance them together,
  this has some consequences:
  - On the first launch after updating to this version any custom configuration will be reset to the default values.
  - **However** old configuration files will still be available in `config/metallurgy_reforged/backup_pre-1.3`
  - From there you'll have 3 options:
    1. merge your custom configuration into the new default one manually
    2. restore your custom configuration completely overriding new changes
    3. play with the new default values
  - in any case **DO NOT** delete or edit the file called `config_pack_version`, because if you do, you'll cause all
    your config files to be reset again and another backup to be created possibly overriding the old backup
- No registry changes happened during this update so your worlds should be safe switching back and forth between 1.2.x
  and 1.3.x (however config values might not be safe)
- The very outdated [wiki](https://github.com/Davoleo/Metallurgy-4-Reforged/wiki) has been updated completely to display
  correct values as well as new pages with extra information.

### Changes

- Adjusted Advancement Descriptions (as well as root advancement description)
- Changed Sublimation Chamber recipe to reflect new metal changes
- Fixed Alloyer Recipes checking (was broken in beta.2)
- You can now alloy dusts directly in the alloyer and skip the furnace cooking step.
- Standardize Armor effect cooldowns application so that they won't interfere with other armor effects.
- Removed a debug log from Damascus Knight attack code.
- Added Metal Tier information to ores and ingots item tooltips.
- Shield tooltips displaying general and usage information are back.
- Reworked Flint and Ignatius
  - Previously worked like a Flint and Vulcanite with chance based 3x3 area lighting and without the option to create
    Lava.
  - Now flint and ignatius can be used like a normal flint and steel which only lights the block you click
  - But you can also shift right click which gives you the old behavior of a chance-based 3x3 lit area, this uses up 5
    durability instead of 1.
- Berserker (Orichalcum Armor Effect) Balance Adjustments
- Metal Detector Tweaks
  - Radius increased from 6 to 8
  - Durability decreased from 100 to 25 times the number of loaded metals.
  - Tooltip showing the loaded metals name now are Gray instead of Dark Aqua
- Texture Changes
  - Ceruclase Palette fixes
  - Sanguinite Texture Overhaul
  - Rubracium Palette fixes
  - Vulcanite Texture Overhaul
  - Vyroxeres Texture Overhaul (includes sublimation chamber textures rework)
  - Quicksilver Texture Overhaul
  - Copper Texture consistency fixes
  - Tartarite Texture Overhaul
- Inolashite, Krik and Lutetium have been moved to tier 6 (previously tier 5)
- Adjusted Tar and Thermite Fluid Temperature
- Adjusted Temperature calculations for all metals
- Reworked Ore Y Level limits and behaviour when these limits are exceeded
- Updated Tinkers' Construct Mining Levels localization overrides
- Fix some Tinkers construct special harvest levels (consistency)
- Updated Simplified Chinese Translation (([#343](https://github.com/Davoleo/Metallurgy-4-Reforged/issues/343)))
- Moved Thermite Fuel value config entry to Gadgets Category
- New config pack system that allows backing up of custom config setups after major updates like this one
- Ore Generation Tweaks:
  - Astral Silver Ore:
    - Now generates between Y 40 and Y 140
    - It's more common at Y Levels greater than 80
  - Rubracium Ore: Generates more frequently near lava lakes.
  - Alduorite Ore: Now only generates at Y levels greater or equal to 70
  - Lemurite Ore: Now only generates at Y levels lesser or equal to 50
- Fixed Hot-Blooded (Ignatius Armor Effect) damaging the player when drinking even when they weren't wearing the armor
- Reworked Durability Calculation on Tartarite Armor to output sane values.
- Armor tooltips now display Durability!
- You can't infuse Tartarite items with Tartarite blocks anymore
- Balanced Fuse (Vulcanite Weapon Effect) values
- Adjusted Freyr (Prometheum Armor Effect) cooldown
- Fixed a crash when biome array in worldgen configuration is null
- Reworked and Balanced Life Steal Tinkers Trait (
  Sanguinite) (([#344](https://github.com/Davoleo/Metallurgy-4-Reforged/issues/344))
- Rephrase Fluid Thermite temperature config comment (has created misunderstandings in the past)
- Improve performances when getting alloy result and one or more input slots are empty.
- Fixed alloys with ingredients from other mods or vanilla not working
- Fixed compatibility issues between Paragon Absorb (Tartarite Effect) and Broken Invasion (Shadow Iron Armor)
- Fixed Paragon Absorb (Tartarite Effect) infusion causing crashes with certain items.
- Made sure most effects work with Paragon Absorb (Tartarite Effect)
  - At time of the release of 1.3.0 Arcane has an issue where attack values use mithril base values instead of
    tartarite.
- Fixed Paragon Absorb (Tartarite Effect) not changing state texture when infused with voltage control (Electrum Tools
  Effect)
- Updated Sublimation Chamber name color to match texture
- Brilliance (Damascus Steel Pickaxe Effect) has been renamed to Dazzle
- Molten Core (Ignatius Weapons Effect) has been renamed to Molten Blade
- Greed (Midasium Weapon Effect) has been renamed to Bounty
- Symbiosis I (Adamantine Effect) has been renamed to Inclusions
- New Rarity tier has been added (VERY RARE), and ore rarity categories have been changed
  accordingly [read the wiki about it](https://github.com/Davoleo/Metallurgy-4-Reforged/wiki/Ores)
- Fixed Fissure (Vulcanite Tool Effect) Block Breaking (now makes checks if the tool can harvest the block before doing
  so)
- Added Efficiency information to tool tooltips
- Incompabile (Shadow Iron Pickaxe Effect) now only drops ores 1 tier above Shadow Iron
- Stats tooltip now shouldn't crash anymore if you mess with tool harvest levels and efficiency setting them to extreme
  values.
- fixed Umbral-synthesis durability regeneration (now doesn't regenerate anymore in a lit zone).

#### Balance Changes

- **Adamantine**:
  - Increased armor durability from 36 to 96.

- **Alduorite**:
  - Increased ore hardness from 5 to 5.3.

- **Amordrine**:
  - Decreased armor enchantability from 50 to 40.
  - Increased armor durability from 50 to 55.
  - Decreased tool enchantability from 50 to 40.
  - Decreased tool durability from 1430 to 1230.
  - Decreased tool efficiency from 14 to 13.5.

- **Angmallen**:
  - Rescaled armor damage reduction from 3/5/6/3 to 2/3/4/2.
  - Increased armor toughness from 0 to 0.5.

- **Astral Silver**:
  - Rescaled armor damage reduction from 2/5/6/2 to 2/3/4/3.

- **Atlarus**:
  - Increased armor durability from 35 to 55.

- **Black Steel**:
  - Rescaled armor damage reduction from 1/2/3/2 to 2/3/3/2.
  - Increased armor durability from 11 to 28.
  - Added new attribute to tools, Max Health, which is equal to 1.

- **Brass**:
  - Rescaled armor damage reduction from 1/2/3/2 to 2/3/3/2.

- **Bronze**:
  - Rescaled armor damage reduction from 3/3/4/2 to 2/3/4/3.
  - Decreased armor toughness from 2 to 1.

- **Carmot**:
  - Increased armor durability from 12 to 19.

- **Celenegil**:
  - Rescaled armor damage reduction from 3/4/6/4 to 3/5/6/4.
  - Decreased armor toughness from 5 to 4.

- **Ceruclase**:
  - Updated the color hex value.
  - Rescaled armor damage reduction from 3/6/5/4 to 3/4/5/4.

- **Copper**:
  - Decreased armor toughness from 0.3 to 0.

- **Damascus Steel**:
  - Decreased armor toughness from 3 to 0.

- **Deep Iron**:

- **Desichalkos**:
  - Decreased armor toughness from 2.6 to 2.5.
  - Increased tool efficiency from 7.9 to 8.1.

- **Etherium**:
  - Rescaled armor damage reduction from 3/5/6/2 to 3/5/6/4.
  - Increased armor durability from 75 to 273.
  - Added new attribute to armor, Movement Speed, which is equal to 0.04.
  - Increased tool durability from 750 to 4756.
  - Increased tool attack speed from -0.4 to -0.3.

- **Eximite**:
  - Decreased armor toughness from 3.3 to 3.

- **Haderoth**:
  - Rescaled armor damage reduction from 4/5/7/4 to 4/5/6/4.

- **Ignatius**:
  - Rescaled armor damage reduction from 2/2/2/2 to 2/3/4/2.
  - Increased armor durability from 4 to 5.
  - Decreased armor toughness from 0.2 to 0.

- **Inolashite**:
  - Rescaled armor damage reduction from 3/6/7/4 to 3/6/7/5.
  - Increased armor durability from 31 to 61.
  - Increased armor toughness from 0.7 to 1.

- **Kalendrite**:

- **Krik**:
  - Rescaled armor damage reduction from 1/2/3/2 to 2/3/4/3.
  - Increased armor durability from 25 to 36.

- **Mithril**:
  - Increased armor toughness from 0 to 2.
  - Increased tool durability from 1000 to 1111.
  - Decreased tool damage from 7 to 5.5.

- **Orichalcum**:
  - Rescaled armor damage reduction from 2/6/7/2 to 2/6/7/4.
  - Increased armor enchantability from 4 to 14.
  - Increased armor toughness from 1.4 to 1.5.
  - Decreased tool damage from 7 to 6.2.

- **Oureclase**:
  - Decreased ore hardness from 5.8 to 5.1.
  - Increased armor enchantability from 16 to 26.
  - Increased tool enchantability from 16 to 26.

- **Platinum**:
  - Increased tool damage from 3 to 3.2.
  - Decreased tool efficiency from 13 to 9.9.

- **Prometheum**:
  - Rescaled armor damage reduction from 1/2/2/1 to 1/2/2/2.
  - Increased armor toughness from 0.1 to 0.5.
  - Increased tool efficiency from 4.2 to 4.7.

- **Quicksilver**:
  - Updated the color hex value.
  - Rescaled armor damage reduction from 3/5/4/5 to 5/5/4/4.
  - Increased armor movement speed from 0.04 to 0.08.
  - Decreased tool durability from 652 to 452.
  - Decreased tool efficiency from 14 to 10.
  - Increased tool attack speed from 1.3 to 1.4.
  - Decreased tool reach distance from 2 to 1.

- **Sanguinite**:
  - Updated the color hex value.

- **Shadow Iron**:
  - Decreased armor durability from 32 to 17.
  - Decreased armor toughness from 2 to 0.
  - Decreased tool damage from 6.8 to 5.9.
  - Increased tool efficiency from 6 to 6.4.
  - Decreased tool attack speed from -0.2 to -0.3.

- **Shadow Steel**:
  - Rescaled armor damage reduction from 4/5/6/3 to 4/5/6/4.
  - Decreased armor durability from 40 to 21.
  - Decreased tool durability from 400 to 214.
  - Decreased tool damage from 7 to 6.8.
  - Increased tool efficiency from 6 to 9.

- **Tartarite**:
  - Updated the color hex value.

- **Vulcanite**:
  - Updated the color hex value.
  - Increased ore hardness from 6.4 to 6.5.
  - Rescaled armor damage reduction from 4/6/7/4 to 3/6/6/3.
  - Decreased armor enchantability from 20 to 19.
  - Decreased armor durability from 150 to 111.
  - Decreased tool enchantability from 20 to 19.
  - Decreased tool durability from 1500 to 712.
  - Decreased tool damage from 7 to 3.8.
  - Decreased tool efficiency from 10 to 8.8.

- **Vyroxeres**:
  - Updated the color hex value.
  - Decreased tool durability from 300 to 287.
  - Decreased tool damage from 7 to 6.2.
  - Increased tool efficiency from 7 to 7.2.


## 1.3.0-beta.2

- **Fissure (Vulcanite Tools)**
  - Fixed Block Breaking (Previously created ghost
    blocks) ([#341](https://github.com/Davoleo/Metallurgy-4-Reforged/issues/341))
  - Added Sound Event when the extra block is broken.
- **Voltage Control (Electrum Tools)**
  - Fixed Harvest Level Boost being reset even if the tool mode is "active"
- **Harvest (Prometheum Hoe)**
  - The Hoe is now damaged when the effect occurs
- **Freyr (Prometheum Armor)**
  - Fixed incorrect words in the description of the effect
- **Flawless (Platinum Items)**
  - Fixed Flawless turning armor and other items unbreakable
- **Hot-Blooded (Ignatius Armor)**
  - Nerfed Healing when swimming in lava
  - Added Instant Damage after drinking anything.
- **Acceleration (Quicksilver Armor)**
  - Improved overall UX
  - Removed Damage when running over lava blocks
- **Paragon Absorb (Tartarite Items)**
  - Items durability is now retained instead of reset to max durability when infusing tartarite with another metal
- **Royal Blood (Damascus Steel Armor)**
  - Helper Knight damage has been balanced
- **Magic Aspect (Mithril Weapons)**
  - Fixed wrong damage calculation
- Integration with famous mods like Thermal Expansion and Chisel has now been implemented via IMC (feature from 1.2.12)
- Sublimation Chamber now has a light level of 8 when active.
- Updated Russian translations to contain new small translation and effect names and
  descriptions ([#337](https://github.com/Davoleo/Metallurgy-4-Reforged/pull/337))
- New Feature! :sparkles: **Advancements**
  - 6 advancements: one for each mining tier
  - 1 advancement for each metal in the mod with details on the properties and optionally how to find/use the metal
- Improve performance when checking if effects can be applied (lighter check)
- Sublimation Chamber now shows the active effect and Elapsed Time in the tooltip when you pick it up as an item.
- Fixed Log Spam on certain effects that didn't rely on config options to be disabled
- Fixed Error in log related to broken Metal Detector's Cleaning recipe
- Fixed Metal detector activating when no metals have been provided as samples.
- Patched a crash with Pneumaticcraft's Amadron
  Drone ([#336](https://github.com/Davoleo/Metallurgy-4-Reforged/issues/336))
- More Warnings have been added to the Registry config making the user aware of the conflicts it can create.
- **Quicksilver: Recipe changed** it is now made by alloying Astral Silver and Silver in a 1:1 ratio
- **Lutetium has been moved to the Nether**
- Some Effects have been renamed
- New Effects:
  - **Harmony _(Shadow Steel Weapons)_**: This weapon hits faster in the dark and stronger in the light.
  - **Explosion _(Vulcanite Armor)_**: Receiving damage causes explosions that damage all the entities around the player
    as well as setting them on fire.

#### Balance Changes

- **Alduorite**:
  - Increased ore hardness from 5 to 5.3.
  - Increased ore harvest level from 5 to 6.
- **Astral Silver**:
  - Increased ore hardness from 3.7 to 4.1.
- **Carmot**:
  - Increased ore hardness from 4.8 to 5.1.
- **Ceruclase**:
  - Increased ore hardness from 3.6 to 4.1.
- **Copper**:
  - Increased tool durability from 95 to 120.
  - Decreased tool efficiency from 6.9 to 6.1.
- **Damascus Steel**:
  - Increased armor enchantability from 18 to 27.
  - Decreased armor durability from 23 to 16.
  - Increased tool enchantability from 18 to 27.
  - Decreased tool durability from 932 to 395.
  - Increased tool damage from 2 to 2.5.
- **Ignatius**:
  - Decreased ore hardness from 3.2 to 3.1.
  - Decreased armor durability from 5 to 4.
  - Decreased tool durability from 89 to 71.
- **Kalendrite**:
  - Increased ore hardness from 4.1 to 4.7.
- **Lutetium**:
  - Decreased ore hardness from 5.2 to 5.1.
  - Increased ore harvest level from 5 to 6.
- **Midasium**:
  - Increased ore hardness from 3.9 to 4.2.
- **Mithril**:
  - Increased ore hardness from 5.5 to 6.8.
- **Orichalcum**:
  - Increased ore hardness from 5 to 6.1.
- **Oureclase**:
  - Increased ore hardness from 5.5 to 5.8.
- **Platinum**:
  - Increased ore hardness from 3.4 to 4.4.
- **Quicksilver**:
  - Rescaled armor damage reduction from 4/7/5/4 to 3/5/4/5.
  - Decreased armor enchantability from 50 to 24.
  - Decreased armor durability from 165 to 50.
  - Increased tool enchantability from 20 to 24.
  - Decreased tool durability from 1100 to 652.
- **Rubracium**:
  - Updated the color hex value.
- **Sanguinite**:
  - Updated the color hex value.
  - Increased ore hardness from 5.5 to 6.6.
  - Decreased tool durability from 1750 to 666.
- **Steel**:
  - Decreased armor enchantability from 18 to 14.
  - Decreased armor durability from 40 to 21.
  - Decreased armor toughness from 4 to 3.
  - Decreased tool enchantability from 18 to 14.
  - Decreased tool durability from 750 to 421.
- **Tartarite**:
  - Decreased armor enchantability from 20 to 14.
  - Decreased armor durability from 300 to 298.
  - Added new attribute to armor, Max Health, which is equal to 10.
  - Decreased tool enchantability from 25 to 14.
  - Increased tool durability from 3000 to 3782.
  - Decreased tool efficiency from 14 to 12.
- **Vulcanite**:
  - Decreased ore hardness from 10 to 6.4.
- **Vyroxeres**:
  - Increased ore hardness from 4.3 to 4.7.

## 1.3.0-beta.1

- New Effect System: most of the old effects have been moved/reworked to be
  thematic [Refer to pre-release.md on the repository for more information]
- This update also features reworked textures for armor and tools by our artist matpac (although they're still WIP so
  you might see armor and tool sets that differs a lot in theme) :)
- New Custom tooltips that show more information about effects and tool stats!

#### Balance Changes:

- **Adamantine**:
  - Updated the color hex value.
  - Increased ore hardness from 5 to 11.5.
  - Added new attribute to armor, Max Health, which is equal to 20.
  - Increased tool durability from 1550 to 2943.
- **Alduorite**:
  - Increased ore hardness from 3 to 5.
  - Increased ore harvest level from 3 to 5.
- **Amordrine**:
  - Updated the color hex value.
  - Increased ore hardness from 3 to 10.
  - Increased tool durability from 500 to 1430.
- **Angmallen**:
  - Updated the color hex value.
  - Increased ore hardness from 3 to 10.
  - Increased armor enchantability from 18 to 32.
  - Decreased armor durability from 30 to 8.
  - Increased tool enchantability from 30 to 32.
  - Decreased tool durability from 300 to 153.
  - Decreased tool damage from 3 to 2.7.
  - Increased tool efficiency from 7 to 7.2.
- **Astral Silver**:
  - Increased ore hardness from 3 to 3.7.
  - Decreased ore harvest level from 4 to 3.
  - Increased armor enchantability from 9 to 36.
  - Increased tool enchantability from 30 to 36.
  - Decreased tool durability from 350 to 323.
  - Increased tool damage from 2 to 2.8.
  - Decreased tool efficiency from 12 to 11.2.
- **Atlarus**:
  - Updated the color hex value.
  - Increased ore hardness from 3 to 9.3.
  - Rescaled armor damage reduction from 4/3/3/4 to 4/4/4/4.
  - Decreased armor toughness from 3.5 to 3.3.
  - Added new attribute to armor, Knockback Resistance, which is equal to -0.4.
  - Added new attribute to armor, Movement Speed, which is equal to 0.01.
- **Black Steel**:
  - Updated the color hex value.
  - Increased ore hardness from 3 to 10.
  - Rescaled armor damage reduction from 3/5/6/3 to 1/2/3/2.
  - Decreased armor enchantability from 17 to 2.
  - Decreased armor durability from 50 to 11.
  - Increased armor toughness from 4 to 6.5.
  - Decreased tool enchantability from 17 to 11.
  - Increased tool durability from 500 to 976.
  - Increased tool damage from 3 to 3.9.
  - Decreased tool efficiency from 8 to 6.6.
  - Added new attribute to tools, Attack Speed, which is equal to -0.6.
- **Brass**:
  - Updated the color hex value.
  - Increased ore hardness from 3 to 10.
  - Decreased armor durability from 15 to 14.
  - Decreased armor toughness from 4 to 3.
  - Added new attribute to armor, Knockback Resistance, which is equal to 0.2.
  - Added new attribute to armor, Movement Speed, which is equal to -0.01.
  - Decreased tool enchantability from 18 to 8.
  - Increased tool durability from 15 to 895.
  - Increased tool damage from 2 to 3.2.
  - Decreased tool efficiency from 10 to 6.8.
  - Added new attribute to tools, Attack Speed, which is equal to -0.5.
  - Added new attribute to tools, Reach Distance, which is equal to -1.
- **Bronze**:
  - Increased ore hardness from 3 to 10.
  - Increased tool enchantability from 9 to 16.
  - Decreased tool durability from 250 to 224.
  - Increased tool damage from 2 to 2.2.
  - Increased tool efficiency from 6 to 7.
- **Carmot**:
  - Increased ore hardness from 3 to 4.8.
  - Increased ore harvest level from 4 to 5.
  - Rescaled armor damage reduction from 2/4/5/2 to 3/4/5/3.
  - Decreased armor durability from 28 to 12.
  - Increased armor toughness from 0 to 1.
  - Decreased tool enchantability from 40 to 7.
  - Decreased tool durability from 500 to 414.
  - Decreased tool damage from 2 to 1.4.
  - Decreased tool efficiency from 12 to 4.9.
  - Added new attribute to tools, Attack Speed, which is equal to -0.3.
  - Added new attribute to tools, Reach Distance, which is equal to -1.
- **Celenegil**:
  - Updated the color hex value.
  - Increased ore hardness from 3 to 10.
  - Rescaled armor damage reduction from 4/6/7/5 to 3/4/6/4.
  - Decreased armor enchantability from 50 to 23.
  - Decreased armor durability from 160 to 42.
  - Increased armor toughness from 0 to 5.
  - Decreased tool enchantability from 50 to 23.
  - Decreased tool durability from 1600 to 521.
  - Increased tool damage from 4 to 4.8.
  - Decreased tool efficiency from 14 to 8.2.
- **Ceruclase**:
  - Increased ore hardness from 3 to 3.6.
  - Increased ore harvest level from 3 to 4.
  - Decreased tool enchantability from 18 to 16.
  - Decreased tool durability from 500 to 143.
  - Decreased tool damage from 4 to 3.5.
  - Increased tool efficiency from 7 to 8.6.
  - Added new attribute to tools, Attack Speed, which is equal to -0.1.
- **Copper**:
  - Decreased ore hardness from 3 to 2.2.
  - Rescaled armor damage reduction from 2/3/2/1 to 1/2/3/2.
  - Decreased armor enchantability from 50 to 25.
  - Increased armor toughness from 0 to 0.3.
  - Increased tool enchantability from 5 to 25.
  - Decreased tool durability from 180 to 95.
  - Decreased tool damage from 2 to 1.2.
  - Increased tool efficiency from 5 to 6.9.
  - Added new attribute to tools, Attack Speed, which is equal to 0.4.
- **Damascus Steel**:
  - Updated the color hex value.
  - Increased ore hardness from 3 to 10.
  - Decreased armor durability from 50 to 23.
  - Increased tool durability from 500 to 932.
  - Decreased tool damage from 3 to 2.
- **Deep Iron**:
  - Updated the color hex value.
  - Increased ore hardness from 3 to 3.1.
  - Decreased ore harvest level from 2 to 1.
  - Rescaled armor damage reduction from 2/4/5/2 to 1/3/4/2.
  - Increased armor enchantability from 1 to 20.
  - Decreased armor durability from 38 to 15.
  - Increased armor toughness from 0 to 1.
  - Increased tool enchantability from 14 to 19.
  - Decreased tool durability from 250 to 230.
  - Decreased tool damage from 3 to 1.7.
  - Increased tool efficiency from 6 to 6.2.
- **Desichalkos**:
  - Updated the color hex value.
  - Increased ore hardness from 3 to 10.
  - Rescaled armor damage reduction from 4/5/7/4 to 3/4/5/4.
  - Decreased armor enchantability from 30 to 21.
  - Decreased armor durability from 180 to 112.
  - Decreased armor toughness from 3 to 2.6.
  - Decreased tool enchantability from 30 to 21.
  - Increased tool durability from 1800 to 2232.
  - Decreased tool damage from 5 to 3.7.
  - Decreased tool efficiency from 8 to 7.9.
  - Added new attribute to tools, Attack Speed, which is equal to -0.2.
  - Added new attribute to tools, Reach Distance, which is equal to 3.
- **Electrum**:
  - Increased ore hardness from 3 to 10.
  - Rescaled armor damage reduction from 3/5/6/2 to 2/4/5/3.
  - Decreased armor enchantability from 30 to 19.
  - Decreased armor durability from 51 to 32.
  - Decreased armor toughness from 3 to 1.
  - Decreased tool enchantability from 30 to 17.
  - Increased tool durability from 70 to 366.
  - Increased tool damage from 2 to 2.7.
  - Decreased tool efficiency from 14 to 7.9.
  - Added new attribute to tools, Attack Speed, which is equal to 0.3.
- **Etherium**:
  - Updated the color hex value.
  - Increased ore hardness from 3 to 10.
  - Decreased armor enchantability from 45 to 30.
  - Decreased tool enchantability from 35 to 30.
  - Decreased tool damage from 8 to 6.
  - Added new attribute to tools, Attack Speed, which is equal to -0.4.
  - Added new attribute to tools, Reach Distance, which is equal to 1.
- **Eximite**:
  - Updated the color hex value.
  - Increased ore hardness from 3 to 4.7.
  - Increased ore harvest level from 3 to 4.
  - Rescaled armor damage reduction from 4/5/6/4 to 3/4/4/3.
  - Increased armor enchantability from 25 to 28.
  - Decreased armor durability from 100 to 21.
  - Decreased armor toughness from 5 to 3.3.
  - Increased tool enchantability from 25 to 28.
  - Decreased tool durability from 1000 to 789.
  - Increased tool damage from 4 to 4.9.
  - Decreased tool efficiency from 8 to 7.8.
  - Added new attribute to tools, Reach Distance, which is equal to 1.
- **Haderoth**:
  - Increased ore hardness from 3 to 10.
  - Decreased armor durability from 125 to 5.
  - Decreased tool durability from 1250 to 80.
- **Hepatizon**:
  - Updated the color hex value.
  - Increased ore hardness from 3 to 10.
  - Rescaled armor damage reduction from 3/3/4/2 to 3/4/4/3.
  - Increased armor enchantability from 22 to 29.
  - Decreased armor durability from 57 to 28.
  - Decreased armor toughness from 2 to 1.
  - Increased tool enchantability from 22 to 29.
  - Increased tool durability from 300 to 761.
  - Increased tool damage from 2 to 4.2.
  - Decreased tool efficiency from 8 to 6.6.
  - Added new attribute to tools, Attack Speed, which is equal to 0.2.
- **Ignatius**:
  - Updated the color hex value.
  - Increased ore hardness from 3 to 3.2.
  - Increased ore harvest level from 1 to 3.
  - Rescaled armor damage reduction from 2/5/6/2 to 2/2/2/2.
  - Decreased armor enchantability from 15 to 11.
  - Decreased armor durability from 24 to 5.
  - Increased armor toughness from 0 to 0.2.
  - Decreased tool enchantability from 15 to 11.
  - Decreased tool durability from 200 to 89.
  - Decreased tool damage from 3 to 2.1.
  - Decreased tool efficiency from 4 to 3.6.
  - Added new attribute to tools, Attack Speed, which is equal to -0.1.
- **Infuscolium**:
  - Increased ore hardness from 3 to 3.2.
- **Inolashite**:
  - Updated the color hex value.
  - Increased ore hardness from 3 to 10.
  - Rescaled armor damage reduction from 3/5/7/4 to 3/6/7/4.
  - Decreased armor enchantability from 25 to 22.
  - Decreased armor durability from 70 to 31.
  - Decreased armor toughness from 4 to 0.7.
  - Decreased tool enchantability from 25 to 22.
  - Increased tool durability from 900 to 1028.
  - Increased tool damage from 4 to 6.
  - Decreased tool efficiency from 8 to 7.2.
  - Added new attribute to tools, Attack Speed, which is equal to -0.4.
- **Kalendrite**:
  - Updated the color hex value.
  - Increased ore hardness from 3 to 4.1.
  - Rescaled armor damage reduction from 4/5/6/4 to 1/2/2/1.
  - Decreased armor enchantability from 20 to 16.
  - Decreased armor durability from 40 to 15.
  - Added new attribute to armor, Max Health, which is equal to 8.
  - Decreased tool enchantability from 20 to 19.
  - Decreased tool durability from 1000 to 212.
  - Increased tool efficiency from 8 to 8.1.
- **Krik**:
  - Updated the color hex value.
  - Increased ore hardness from 3 to 10.
  - Rescaled armor damage reduction from 2/4/3/1 to 1/2/3/2.
  - Decreased armor durability from 45 to 25.
  - Decreased armor toughness from 9 to 2.
  - Increased tool durability from 350 to 1652.
  - Decreased tool damage from 7 to 4.1.
  - Increased tool efficiency from 5 to 8.
  - Added new attribute to tools, Attack Speed, which is equal to 0.3.
- **Lemurite**:
  - Increased ore hardness from 3 to 5.5.
  - Increased ore harvest level from 2 to 5.
- **Lutetium**:
  - Increased ore hardness from 3 to 5.2.
  - Increased ore harvest level from 4 to 5.
- **Manganese**:
  - Updated the color hex value.
  - Increased ore hardness from 3 to 3.2.
  - Decreased ore harvest level from 3 to 2.
- **Meutoite**:
  - Increased ore hardness from 3 to 3.6.
  - Increased ore harvest level from 4 to 5.
- **Midasium**:
  - Updated the color hex value.
  - Increased ore hardness from 3 to 3.9.
  - Increased ore harvest level from 3 to 4.
  - Rescaled armor damage reduction from 3/3/5/2 to 4/4/5/4.
  - Decreased armor enchantability from 35 to 28.
  - Decreased armor durability from 16 to 14.
  - Decreased armor toughness from 4 to 2.
  - Decreased tool enchantability from 35 to 28.
  - Decreased tool durability from 100 to 89.
  - Decreased tool damage from 7 to 2.6.
  - Decreased tool efficiency from 10 to 7.
  - Added new attribute to tools, Attack Speed, which is equal to -0.3.
- **Mithril**:
  - Updated the color hex value.
  - Increased ore hardness from 3 to 5.5.
  - Increased ore harvest level from 4 to 5.
- **Orichalcum**:
  - Updated the color hex value.
  - Increased ore hardness from 3 to 5.
  - Decreased ore harvest level from 5 to 4.
- **Osmium**:
  - Updated the color hex value.
  - Increased ore hardness from 3 to 4.
  - Rescaled armor damage reduction from 4/5/5/4 to 5/6/6/5.
  - Decreased armor enchantability from 17 to 8.
  - Decreased armor durability from 100 to 5.
  - Increased armor toughness from 3 to 3.5.
  - Added new attribute to armor, Knockback Resistance, which is equal to 1.
  - Added new attribute to armor, Movement Speed, which is equal to -0.01.
- **Oureclase**:
  - Updated the color hex value.
  - Increased ore hardness from 3 to 5.5.
  - Rescaled armor damage reduction from 3/6/5/4 to 4/6/5/3.
  - Increased armor enchantability from 2 to 16.
  - Increased armor durability from 28 to 36.
  - Increased armor toughness from 1.5 to 2.5.
  - Added new attribute to armor, Knockback Resistance, which is equal to 0.88.
  - Decreased tool enchantability from 18 to 16.
  - Decreased tool durability from 750 to 198.
  - Decreased tool damage from 6 to 4.1.
  - Added new attribute to tools, Attack Speed, which is equal to -0.4.
- **Platinum**:
  - Increased ore hardness from 3 to 3.4.
  - Increased ore harvest level from 2 to 4.
  - Rescaled armor damage reduction from 3/5/6/3 to 3/4/4/3.
  - Increased armor enchantability from 12 to 30.
  - Increased armor durability from 17 to 20.
  - Decreased armor toughness from 0.5 to 0.
  - Added new attribute to armor, Max Health, which is equal to 8.
  - Decreased tool enchantability from 50 to 30.
  - Increased tool durability from 250 to 998.
  - Decreased tool damage from 5 to 3.
  - Decreased tool efficiency from 16 to 13.
  - Added new attribute to tools, Attack Speed, which is equal to 0.2.
- **Prometheum**:
  - Updated the color hex value.
  - Decreased ore hardness from 3 to 2.9.
  - Rescaled armor damage reduction from 1/2/3/2 to 1/2/2/1.
  - Increased armor enchantability from 11 to 14.
  - Decreased armor durability from 30 to 15.
  - Decreased tool enchantability from 16 to 14.
  - Decreased tool durability from 200 to 121.
  - Decreased tool damage from 5 to 2.4.
  - Increased tool efficiency from 4 to 4.2.
- **Quicksilver**:
  - Increased ore hardness from 3 to 10.
  - Added new attribute to armor, Movement Speed, which is equal to 0.04.
  - Decreased tool damage from 7 to 2.5.
  - Added new attribute to tools, Attack Speed, which is equal to 1.3.
  - Added new attribute to tools, Reach Distance, which is equal to 2.
- **Rubracium**:
  - Increased ore hardness from 3 to 8.5.
  - Decreased ore harvest level from 4 to 3.
- **Sanguinite**:
  - Increased ore hardness from 3 to 5.5.
  - Decreased ore harvest level from 6 to 5.
  - Added new attribute to armor, Max Health, which is equal to -10.
- **Shadow Iron**:
  - Updated the color hex value.
  - Increased ore hardness from 3 to 4.2.
  - Increased ore harvest level from 1 to 3.
  - Rescaled armor damage reduction from 4/5/6/3 to 4/5/6/4.
  - Increased armor enchantability from 3 to 9.
  - Decreased armor toughness from 4 to 2.
  - Added new attribute to armor, Knockback Resistance, which is equal to -0.5.
  - Increased tool enchantability from 3 to 9.
  - Decreased tool durability from 300 to 118.
  - Increased tool damage from 6 to 6.8.
  - Increased tool efficiency from 5 to 6.
  - Added new attribute to tools, Attack Speed, which is equal to -0.2.
- **Shadow Steel**:
  - Updated the color hex value.
  - Increased ore hardness from 3 to 10.
  - Decreased armor enchantability from 5 to 3.
- **Silver**:
  - Decreased ore hardness from 3 to 2.9.
  - Increased ore harvest level from 1 to 2.
  - Decreased tool enchantability from 20 to 19.
  - Increased tool durability from 25 to 75.
  - Decreased tool damage from 5 to 3.4.
  - Decreased tool efficiency from 15 to 11.2.
  - Added new attribute to tools, Attack Speed, which is equal to 0.2.
- **Steel**:
  - Updated the color hex value.
  - Increased ore hardness from 3 to 10.
  - Decreased tool damage from 7 to 2.3.
  - Decreased tool efficiency from 8 to 6.4.
- **Tartarite**:
  - Increased ore hardness from 3 to 10.
- **Tin**:
  - Decreased ore hardness from 3 to 2.5.
- **Vulcanite**:
  - Increased ore hardness from 3 to 10.
  - Decreased ore harvest level from 5 to 4.
- **Vyroxeres**:
  - Increased ore hardness from 3 to 4.3.
  - Increased ore harvest level from 4 to 5.
- **Zinc**:
  - Decreased ore hardness from 3 to 2.

## 1.2.12

- Metallurgy Tweaks are now implemented natively in the mod via IMC (CraftTweaker is not needed anymore)
- Sublimation Chamber now has a light level of 8 when it's working.

## 1.2.11

- Compressed Machine Sounds (mod size almost halved!)
- Implement Metallurgy Tweaks by Nebby natively under GPLv3

## 1.2.10

- Fix crash when wearing certain armor sets ([#332](https://github.com/Davoleo/Metallurgy-4-Reforged/issues/332))
- [Armor Chroma](https://www.curseforge.com/minecraft/mc-mods/armor-chroma) Integration (custom armor HUD)
- `materials.json` config now supports custom molten metal temperatures ([#315](https://github.com/Davoleo/Metallurgy-4-Reforged/issues/315) also check
  out [this thread comment](https://github.com/Davoleo/Metallurgy-4-Reforged/issues/315#issuecomment-889904106))
- Fixed a bug that didn't allow cleaning detector via the cleaning recipe if it's durability was less than the max
  durability
- Reworked Metal Detector:
  - You can now just click instead of having to hold right click for ores to show up
  - Ores remain highlighted for a short period of time while the detector goes on cool-down
  - New Sounds providing feedback on different cases!
  - LEDs on the detector now flash when the detector is scanning the area and finds ores nearby

## 1.2.9

- Added Machine Sounds (Thanks to @acemagex#5483 for the epic sounds!)
- Offset sublimation chamber HUD effect texture by one
- Improved Sublimation chamber state handling when fuel finishes

## 1.2.8

- Added a config option to tweak the temperature of fluid Thermite  ([#315](https://github.com/Davoleo/Metallurgy-4-Reforged/issues/315))
- Fixed an exception that caused Flans Mod entities to be invisible when added to the world ([#327](https://github.com/Davoleo/Metallurgy-4-Reforged/issues/327))
- Added config options to tweak attack damage and attack speed of brass knuckles
- Improved Tinkers' Render info parameters for metals (should make tinkers' integrated textures look a bit better)
- Fixed a crash caused by Electrum Magnet when trying to attract empty item stacks

## 1.2.7

- Fixed Forge errors spam in log caused by broken JSON recipes when disabling registration of items and blocks in the mod ([#322](https://github.com/Davoleo/Metallurgy-4-Reforged/issues/322))
- Fixed ore generation biome filters in config files ([#323](https://github.com/Davoleo/Metallurgy-4-Reforged/issues/323))
- Fixed a crash that occured when a non-player entity tried to use Lemurite Shield ([#325](https://github.com/Davoleo/Metallurgy-4-Reforged/issues/325))
- Improved the look of Creative tabs when mod content is disabled via the registry.cfg config file
- *Also check out 1.2.6 changelog as that version was marked as beta because of the ore-gen bug*

## 1.2.6

- Iron and Gold Decorative blocks are now unregistered when disabling a specific block types ([#316](https://github.com/Davoleo/Metallurgy-4-Reforged/issues/316))
- Fixed Celenegil and Orichalcum effects being applied to Deep Iron and Shadow steel instead ([#319](https://github.com/Davoleo/Metallurgy-4-Reforged/issues/319))
- Improved random mob metallurgy equipment generation performance
- Fixed JEI Alloyer Recipes displaying wrong information when you changed some ingredients' oredict names ([#317](https://github.com/Davoleo/Metallurgy-4-Reforged/issues/317))
- Fixed faulty CT integration that automatically converted other mods' materials to their metallurgy equivalents when adding custom recipes ([#318](https://github.com/Davoleo/Metallurgy-4-Reforged/issues/318))

## 1.2.5

- Vanilla Buckets can be now made of any metal that you can also use to make tools ([#300](https://github.com/Davoleo/Metallurgy-4-Reforged/issues/300))
- Fix Crash (NPE) when using Desichalkos Sword Effect and Malisis Core is installed ([#311](https://github.com/Davoleo/Metallurgy-4-Reforged/issues/311))
- Metal dusts can now be paired with Midasium dust in a crafting table to create Gold ([#300](https://github.com/Davoleo/Metallurgy-4-Reforged/issues/300))
- Updates and Fixes to Russian Translation ([#310](https://github.com/Davoleo/Metallurgy-4-Reforged/pull/310))

## 1.2.4

- Metal Detector now detects oredicted variants of Metallurgy ores
- Fix crash and buggy enemies AI in multiplayer when using Lemurite Shield ([#307](https://github.com/Davoleo/Metallurgy-4-Reforged/issues/307))
- Fixed Ignatius Pickaxe effect (it now drops nuggets correctly)
- Fixed Ignatius Shovel and Axe effect:
  - Fixed buggy spawning of entity items that glitched inside blocks
  - Balanced chance for the autosmelt effect to occur
  - Temporarily patched a duplication issue with smelted drops
  - Make the effect work correctly with shovels too

## 1.2.3

- Fixed a model Error with Ceruclase Ice Shield
- Add a localized name to the Ice Shield
- Add a missing recipe for Electrum Magnet
- Fixed a minor issue in Swedish Translation
- Fix a crash when wearing Platinum Armor ([#306](https://github.com/Davoleo/Metallurgy-4-Reforged/issues/306))

## 1.2.2

- New Gadget **Electrum Magnet**
  - Portable Magnet that attracts dropped items towards the player
  - Configurable in 3 different ways
- Fix EMC Exploits introduced with the new Decorative blocks
  recipes ([#302](https://github.com/Davoleo/Metallurgy-4-Reforged/issues/302))
- Updated Russian Translation ([#303](https://github.com/Davoleo/Metallurgy-4-Reforged/pull/303))
- Fixed println pollution on when Endermen spawn ([#304](https://github.com/Davoleo/Metallurgy-4-Reforged/issues/304))

## 1.2.1

- :globe_with_meridians: Added Russian Localization files
- :globe_with_meridians: Updated Swedish Localization files ([#301](https://github.com/Davoleo/Metallurgy-4-Reforged/pull/301))
- :bug: Fixed Ceruclase Shield inconsistent behaviour ([#299](https://github.com/Davoleo/Metallurgy-4-Reforged/issues/299))

## 1.2.0

- :sparkles: Mob now have a chance to spawn with random Metallurgy equipment ([#284](https://github.com/Davoleo/Metallurgy-4-Reforged/issues/284))
- :dart: Atlarus and Vulcanite Armor effects now works on mobs
- :sparkles: You can now use Metallurgy Metal blocks of metals that have an ore harvest level greater than 1 as base blocks for Beacon Structures ([#294](https://github.com/Davoleo/Metallurgy-4-Reforged/issues/294))
- :sparkles: NEW GADGET - Brass Knuckles: Really Fast Attack speed and low damage level ([#267](https://github.com/Davoleo/Metallurgy-4-Reforged/issues/267))
- :bento: Add Localized name and recipe to Etherium Goggles
- :balance_scale: Reworked Decorative metal blocks recipes to be more balanced (buff) and to avoid recipe conflicts with vanilla
- :sparkles: Added a new Block (Bi-Metal Structure) (as of right now it’s used to create decorative blocks, but it also might be used as a machine frames for tile entities)
- :recycle: Refactor Recipe JSON Files
- :sparkles: EnderIO Integration for alloy recipes in the alloy
  smelter [#295](https://github.com/Davoleo/Metallurgy-4-Reforged/issues/295)
- :sparkles: Bucklers (a new kind of shield lighter and more
  dynamic) [#164](https://github.com/Davoleo/Metallurgy-4-Reforged/issues/164)
  - Allows to run and walk at normal speed wile holding it
  - As well as allowing to hit entities and the air :P
  - They Go on cooldown after a hit
- :sparkles: Zinc Buckler (Tier 1) (no effect)
- :sparkles: Vulcanite Buckler (Tier 2) (creates a small explosion that knockbacks enemies away from you, only damaging melee attackers)
- :sparkles: Orichalcum Buckler (Tier 2) (reflects 150% of the damage to ranged-attacking entities teleporting them in
  front of the player at the same time)
- :sparkles: Ceruclase Shield (Tier 2)  (Creates an icy sphere around the player and cushions a portion of the fall
  damage when activated. The ice breaks after withdrawing the shield.)
- :bug: Fixed Lemurite shield not handling invisibility properly in some cases
- :bento: Improvements to non-metal textures
- :bento: Improved old textures of Rubracium Gauntlet and Lemurite Shield (and made Lemurite shield give visual feedback from first person perspective)
- :dart: Now alloy metal blocks can be used in beacon block bases too
- :bento: Improvements to Shadow Steel armor texture
- :alien: Now Sanguinite special ore generation works when NetherEX is installed
- :bug: Fixed thermite item description.

## 1.1.2
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

## 1.1.1
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

## 1.1.0
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

## 1.0.2
- Fix a crash when placing the metal detector inside of a Tinkers’ Construct Crafting Station ([#260](https://github.com/Davoleo/Metallurgy-4-Reforged/issues/260))
- Fix an issue that prevented players from crafting different kinds of steel from other mods ([#261](https://github.com/Davoleo/Metallurgy-4-Reforged/issues/261))
- Added the missing dust blending recipes (now you should be able to make alloys even when you don’t have access to an Alloyer)
- Implemented Animated Ore Particles (finally, yay)
- Fixed an issue where ore particles would get other vanilla or modded textures ([#251](https://github.com/Davoleo/Metallurgy-4-Reforged/issues/251))

## 1.0.1
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

## 1.0.0
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

## 0.3.3
- Fixed Armor Effects not working ([#234](https://github.com/Davoleo/Metallurgy-4-Reforged/issues/234))
- Disable Krik Armor Height Level debug Logging
- Add config options to disable ore particles and ore light emission ([#235](https://github.com/Davoleo/Metallurgy-4-Reforged/issues/235))
- Thermite is now powerful fuel for any kind of machine
- Fixed an issue with JEI Integration ([#237](https://github.com/Davoleo/Metallurgy-4-Reforged/issues/237))

## 0.3.2

- Nerf Celenegil Armor Effect ([#221](https://github.com/Davoleo/Metallurgy-4-Reforged/issues/221))
- Fixed some bugs: ([#230](https://github.com/Davoleo/Metallurgy-4-Reforged/issues/230))
  - Fixed Particles being registered on the server
  - Fixed HUD Handler being registered on the server
  - Fixed Wiki Item Model being loaded on the server
- Added Missing Entry in the modMaterials Array that caused an IOOB Exception

## 0.3.1
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

## 0.2.3
- CraftTweaker Integration for Metallurgy Machines
- Fixed infinite xp shift-clicking the machines’ output in your inventory while having full inventory
- Fixed Tar lakes not generating in non-default world generators ([#186](https://github.com/Davoleo/Metallurgy-4-Reforged/issues/186))
- Fixed Console spam when a player is holding a Tinker's tools using Deep Iron ([#294](https://github.com/Davoleo/Metallurgy-4-Reforged/issues/194))
- Implemented a config entry to allow the end user to restore classic vanilla axes damage values
- Improvements concerning sword and tool effects handling ([#189](https://github.com/Davoleo/Metallurgy-4-Reforged/issues/189))
- Fixed a bug that caused tooltip registration failure ([#195](https://github.com/Davoleo/Metallurgy-4-Reforged/issues/195))
- Improved Phosphorus Lantern Spawn Block ([#190](https://github.com/Davoleo/Metallurgy-4-Reforged/issues/190))
- Introducing The Sublimation Chamber
  - new Machine that allows you to sublimate blocks of some materials giving a certain effect to all the players within
    its area
  - the peculiarity of this machine is that it provides you with a long duration effect, so you can keep the effect also
    when leaving the area
- Fix molten fluids names to respect conventional naming (Should fix bug of the tinker smeltery not alloying materials)
- Allow the end user to blacklist tinker materials from being loaded in the config
- Fix crusher metadata exploit ([#205](https://github.com/Davoleo/Metallurgy-4-Reforged/issues/205))
- Fix Glowing effect Robotics Part Compatibility ([#203](https://github.com/Davoleo/Metallurgy-4-Reforged/issues/203))

## 0.2.2
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

## 0.2.1
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

## 0.2.0
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

## 0.1.1
- Fixed Alloyer crash when opening the GUI
- Fixed wrong config name and comment for warning message

## 0.1.0
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

## 0.0.7
- Fixed Tinkers’ Construct hard dependency
- Fixed Midasium sword player inventory dupe
- French localization update

## 0.0.6
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

## 0.0.5
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

## 0.0.4
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

## 0.0.3
- Fixed Astral Silver typo ([#63](https://github.com/Davoleo/Metallurgy-4-Reforged/issues/63))
- Buffed ore blast resistance ([#62](https://github.com/Davoleo/Metallurgy-4-Reforged/issues/62))
- Fixed Silver ore generation bug ([#60](https://github.com/Davoleo/Metallurgy-4-Reforged/issues/60))
- Fixed Oureclase pickaxe harvest level ([#59](https://github.com/Davoleo/Metallurgy-4-Reforged/issues/59))

## 0.0.2
- Some minor fixes
- Fixed the "unable to climb certain blocks" bug
- Deep Iron Effect rework
- Fixed glowing effect
- Tweaked sanguinite sword effect
- Fixed manganese harvest level bug

## 0.0.1 [First alpha]
- Major features and content
- Nearly every original feature and texture

### Pre-Release Disclaimer: This is a pre-release of X.X.X, you could find bugs in it, if you find them while you play it, please consider reporting them here: https://github.com/Davoleo/Metallurgy-4-Reforged/issues