## Current Release: 1.3.0-beta.1

### Features

- New Effect System: most of the old effects have been moved/reworked to be thematic
  - **Adamantine: The Living Metal**
    - **Inclusions _(Any Equipment)_**: Eating food regenerates equipment durability, not eating any food slowly destroys equipment. Durability repair amount depends on how many food points the item gives. Durability decreases with a frequence of 1 to 6 seconds depending on how hungry the player is
    - **Symbiosis _(Armor)_**: Receiving fatal damage will cause one of the armor pieces to sacrifice itself. Every Armor piece will increase the chance of surviving death by 25% (with a maximum of 100% when wearing full set)
  - **Amordrine: The Angelic Metal**
    - **Skyhigh _(Armor)_**: Grants extra jumps (from 1 to 4) depending on how many armor pieces the player is wearing
    - **Soulbound _(Any Equipment)_**: Items that have this effect are kept in your inventory after death
    - **Divine Grace _(Weapons)_**: Bonus Damage depending on target's health,the more the target has the less health the more damage the weapon does (up to 200% damage boost)
  - **Angmallen: The Alchemical Metal**
    - **Transmute _(Pickaxe)_**: Mining an ore sometimes drops another ore of +1/-1/+0 harvest level (50% chance)
    - **Reactive I _(Weapons)_**: Deals Bonus Damage to armored enemies
    - **Reactive II _(Armor)_**: Plays a sound sometimes when you're near some rare ore, every armor piece increases the chance to detect rare ores
  - **Astral Silver: The Star Metal**
    - **Starlight _(Armor)_**: Gives Night Vision and Speed during night time or in the End if the player has the sky above their head depending on how many armor pieces are worn
    - **Extraterrestrial _(Waeapons)_**: 45% more damage if the player is in another dimension
    - **Extraterrestrail _(Tools)_**: Increased speed when breaking blocks in dimensions that aren't the Overworld
  - **Atlarus: The Wind Metal**
    - **Whirlwind _(Armor)_**: A whirlwind will save you from taking fall damage pushing you in a random direction
    - **Gust of Wind _(Weapons)_**: A strong gust of wind pushes enemies away from you and breaks leaves and vines in a range of 5 blocks around you
    - **Wind Scythe _(Hoe)_**: Wind helps you remove bushes and crops around you
  - **Black Steel: The Fortress Metal**
    - **Reinforcement _(Armor)_**: Receiving damage sometimes grants resistance but also slowness, at the same time. (Repeated strikes stack the effect, aka slowness I and resistance I become slowness II and resistance II. Can stack up to 3 times, chance based)
    - **Mountain _(Weapons)_**: Tools & weapons act like armor, for example holding a black steel sword while getting attacked will damage the sword but absorb a small portion of the damage.
  - **Brass: The Reverbing Metal**
  - **Carmot: The Clockwork Metal**
    - **Autonomous _(Tools)_**: Harvesting a block will mine surrounding blocks of the same type in a spheric range
    - **Power User _(Armor)_**: You can comsume and use items faster depending on how many armor pieces you wear; if you wear a full set you also get a Haste III buff
  - **Celenegil: The Exalted Metal**
    - **Escalation _(Tools)_**: Continuously breaking blocks without stopping doesn't use durability and increases mining speed. (Resets after pausing for more than a second, the unbreakable effect is enabled after 5 blocks mined)
    - **Perseverance _(Armor)_**: After being attacked five times over a short period, all the enemies are knocked back away from the player and the player receives a very short regen buff and all negative effects are cleared.
    - **Glory Seeker _(Weapons)_**: On right click, Grants a small damage buff to the weapon damage but when you damage an entity without killing it, it'll go on a short cooldown while it will give the player some short speed and strength buff if they manage to kill the entity
  - **Ceruclase: The Frost Metal**
    - **Flash Freeze _(Weapons)_**: Has a 75% chance to completely freeze an enemy in place on hit for 3 seconds.
    - **Cold-snap _(Tools)_**: Blocks with harvest level of 0 are harvested instantly. (Dirt, sand, Wood)
    - **Blizzard _(Armor)_**: Mobs are slowed, fatigued and weakened depending on how much armor you wear, both players and entities are extinguished if they were on fire.
  - **Damascus Steel - The Regal Metal**
    - **Royal blood _(Armor)_**: A minion will be spawned to aid you in your fights against enemies as soon as you take damage (minion health will scale with the armor count)
    - **Brilliance _(Weapons)_**: Mobs drop significantly more experience.
    - **Brilliance _(Pickaxe)_**: Mining ores sometimes grants a bit of experience depending on the harvest level.
  - **Deep Iron - The Oceanic Metal**
    - **Aquatic _(armor)_**: Grants Water Breathing and improved Mobility in water depending on how many armor pieces are worn (it also grants night vision with 2 or more armor pieces).
    - **Diver _(tools)_**: Improved tool harvest speed when underwater (3 times the normal underwater speed)
    - **Diver _(weapons)_**: Enhanced weapon damage when underwater (+3 hearts of damage)
  - **Desichalkos - The Void Metal**
    - **Wormhole _(tools)_**: Tool Reach is 3 blocks higher than normal (Metal trait) - Block Drops are teleported to the player inventory directly
    - **Nullifier _(weapons)_**: When attacking, the damage is dealt straight to the opponent's health bar, unaffected by armor or any resistance the opponent might have.
  - **Electrum - The Lightning Metal**
    - **Magnet _(weapons)_**: Activating the effect with a right click on the weapon will cause entities in line of sight of the player to be moved towards the player.
    - **Voltage Control _(tools)_**: Harvesting blocks while the effect is active (right-click) will make the tool use 10x durability but mine significantly faster and gain a higher harvest level.
    - **Static _(armor)_**: Receiving damage charges items in the player inventory with Energy. Energy is distributed equally among all items. Energy value scales based on damage received prior to armor reduction calculations.
  - **Etherium - The Ghost Metal**
    - **Siphon _(weapons)_**: When attacking, sometimes extra damage is dealt and the user regenerates health equal to the extra damage. If the player has full HP they gain an absorption buff for that amount of hp (Absorption doesn’t stack).
    - **Etheral _(armor)_**: Allows the player to clip through walls when sneaking for at most 15 seconds after which the player remains stuck in the wall
  - **Eximite - The Emder Metal**
    - **Outworlder _(weapons)_**: Grants bonus damage and a looting effect to mobs in the End.
    - **End Domestic _(armor)_**: Shulkers become neutral, but you're not immune to the effect of lingering bullets.
  - **Haderoth - The Shape-Shifting Metal**
    - **Apex _(weapons)_**: Killing the same mob over and over again grants bonus damage, but killing a different one resets it
    - **Metamorph _WIP (all equipment)_**: Items have a second life, once they reach 0 durability they're replaced by a new and better version of themselves; <NL> Armor pieces will be reborn with higher durability and protection and toughness values; <NL> All items will will be reborn higher durability and increased efficiency when breaking blocks, weapons also get a 1 heart permanent bonus damage.<NL> (all the other Haderoth effects are enabled after items are reborn, if you disable this the
      effects will work out of the box).
    - **Adaptability _(armor)_**: Each armor piece gives has a different: helmet: immunity to hunger effect, chestplate: fire resistence, leggings: immunity to slowness effect, boots: immunity to levitation effect
  - **Hepatizon - The Metal of Theft**
    - **Disarm _(weapons)_**: When attacking you have a chance to rob the enemy of some of their equipment.
    - **Cloak & Dagger _(armor)_**: Grants AI invisibility outside of a certain radius depending on how much armor you're wearing.
  - **Ignatius - The Fire Metal**
    - **Molten Core _(weapons)_**: Auto-smelts entity loot if they can be smelted.<NL>Auto-smelt chance is based on the Fortune enchantment
    - **Molten Core _(tools)_**: Auto-smelts block drops if they can be smelted.<NL>Auto-smelt chance is based on the Fortune enchantment
    - **Hot-Blooded _(armor)_**: Fire and lava heals you proportionally to the damage it would otherwise deal, but water and rain damages you as if it were lava and fire; <NL>The effect of Fire Protection is inverted, to protect water damage
  - **Inolashite - The Time Metal**
    - **Time Cleave _(weapons)_**: When attacking, sometimes an extra attack can occur, the player will swing his arm
      twice in quick succession.
    - **Time Leap _(tools)_**: Upon activation, transports the player 7 blocks in the direction they are looking
      breaking all the blocks the tool can break that get in the way
    - **Warp _(armor)_**: Shift + Jump will teleport the player back in time of 2..8 seconds depending on how much armor
      is worn
  - **Kalendrite - The Healing Metal**
    - **Restoration _(tools)_**: Breaking blocks restores half a heart but tools lose more durability the lower the
      user's health is.
    - **Tranquil _(armor)_**: If the player has not received any damage for the past 10 seconds and they don't have full
      hp, they gain a regeneration buff.
  - **Krik - The Gravity Control Metal**
    - **Flak _(weapons)_**: Deals double damage to airborne entities and gives a short levitation debuff.
    - **Krik-Drives _(tools)_**: Activating the item launches the player in the general direction of where they are
      looking.
    - **Warp _(armor)_**: Shift + Jump will teleport the player back in time of 2..8 seconds depending on how much armor
      is worn
  - **Midasium - The Metal of Greed**
    - **Midas Touch _(hoe)_**: Harvesting crops with this hoe will turn the crop drops into gold items (ingots, dusts and
      nuggets) instead.
    - **Greed _(weapons)_**: Chance based looting effect that stacks with the looting enchantment. All drops have a
      small chance to be turned into gold ingots.
    - **Greed _(tools)_**: Chance based fortune effect that stacks with the fortune enchantment. All drops have a small
      chance to be turned into gold ingots.
  - **Mithril - The Magical Metal**
    - **Multiply _(pickaxe)_**: Breaking ore sometimes drops an additional ore along with the one mined. Effect chance
      scales with Fortune Enchantment.
    - **Arcane _(any equipment)_**: The more enchantments the item has the better it performs. (damage for weapons,
      mining speed for tools and more protection from armor)
    - **Magic Aspect _(weapons)_**: Deals additional magic damage 1 second after the last entity was attacked
  - **Orichalcum - The War Metal**
    - **Berserker _(armor)_**: Killing a mob grants a strength buff, level of which is equal to the count of every other
      remaining hostile mob around the wearer. (Max Potion Level: 6) (Armor scales the radius and duration of the bonus)
    - **Champion _(any equipment)_**: Killing monsters restores durability to Orichalcum Items (Amount is equal to the
      max health of the killed monster)
  - **Osmium - Extremely Heavy Metal**
    - **Titan _(armor)_**: Enemies are knocked back when they attack you
  - **Oureclase - The Crushing Metal**
    - **Crush _(pickaxe)_**: Mining ores drops 1 dust of that metal instead of the ore.
    - **Stampede _(armor)_**: Sprinting slightly damages and knocks back nearby entities.
    - **Pulverize _(weapons)_**: Deals 2x the original damage to enemies with full health.
  - **Platinum - The Pure Metal**
    - **Purification _(armor)_**: Purifies negative effects like Weakness, Slowness and Poison to their positive
      counterparts.
    - **Cathartic Strike _(weapons)_**: Extra cathartic damage will be dealt to undead enemies as well as setting them
      on fire.
    - **Unbreakable _(any equipment)_**: This tool is unbreakable
  - **Prometheum - The Floral Metal**
    - **Freyr _(armor)_**: Sprinting affects nearby crops and grass growth giving it a boost at the cost of a bit of
      armor durability.
    - **Harvest II _(hoe)_**: When harvested with this hoe: leaves, crops and grass drops are increased.
    - **Photosynthesis _(any equipment)_**: Item durability regenerates slowly overtime when under direct sunlight.
  - **Quicksilver - The Swift Metal**
    - **Sleight of Blade _(weapons)_**: On every successful attack you're launched through the enemy damaging them more.
    - **Acceleration _(armor)_**: Sprinting will allow you to build up momentum, once full speed is reached you'll be
      able to run on liquids, but you will sink as soon as you stop or slow down.
    - **Tailwind _(tools)_**: Mining speed will remain the same over any block independently from harvest level or block
      hardness
    - **Aerodynamic _(any equipment)_**: Equipping this item gives you a speed boost.
  - **Sanguinite - The Demonic Metal**
    - **Deceitful _(any equipment)_**: This item will disappear completely after death.
    - **Devour _(weapons)_**: You deal more damage as you grow hungry, killing enemies appeases your hunger depending on
      the max health of the enemy but also makes you hungry for more.
    - **Blood Tap _(tools)_**: Activating the tool will exchange some of your life for increased harvest speed and level
      also restoring a bit of durability
    - **Necromastery _(armor)_**: Killing a hostile mob might revive its corpse with lower health allowing it to be
      killed again for more drops and experience.
  - **Shadow Iron - Incomplete Metal**
    - **Chaos Crit _(weapons)_**: Attacks occasionally become critical hits dealing random increased damage up to more
      than 3 times the base attack value.
    - **Incompatible _(pickaxe)_**: Mining Shadow Iron ore will sometimes drop a different ore.
    - **Broken Evasion _(tools)_**: Has a chance of absorbing attack damage completely at the cost of durability from
      one of the armor pieces
  - **Shadow Steel - Light Absorbing Metal**
    - **Umbral-synthesis _(any equipment)_**: (WIP DESC) This item regenerates durability in the darkness, regen speed
      is relative to the darkness level
    - **Eclipse _(armor)_**: Whether if you're attacked in light or in darkness you either blind and damage enemies with
      fire or evade anymore damage and gain speed boost for a few seconds
    - **Harmony _(weapons)_**: This weapon hits faster in the dark and stronger in the light.
  - **Tartarite - The Paragon of Metals**
    - **Paragon Absorb _(any equipment)_**: Surrounding the item with 4 blocks of another metal will allow it to absorb
      its effects in an increased ratio while also increasing item durability.
  - **Vulcanite - The Explosive Metal**
    - **Fissure _(tools)_**: Breaking a block has a chance of breaking another one adjacent to it, chance scales with
      fortune.
    - **Explosion _(armor)_**: Receiving damage causes explosions that damage all the entities around the player as well
      as setting them on fire.
    - **Fuse _(weapons)_**: Right clicking the weapon ignites it, and on the next strike within 5 seconds will create an
      explosion whose strength depends on how much time is left on the fuse, if 5 seconds run out before you attack any
      enemy the explosion will affect the user.
  - **Vyroxeres - Venomous Metal**
    - **Toxin _(weapons)_**: Poisons the target on every hit, if the target is already poisoned, the level of poison
      debuff increases.
    - **Dissolve _(armor)_**: Additional Damage Reduction for every active potion effect on the user.

### Balance Changes:

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



- This update also features reworked textures for armor and tools by our artist matpac (although they're still WIP so you might see armor and tool sets that differs a lot in theme) :)
- New Custom tooltips that show more information about effects and tool stats!