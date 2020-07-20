/*
 * -------------------------------------------------------------------------------------------------------
 * Class: ToolEffectsConfig
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 * Copyright (c) 2020.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.config;

import it.hurts.metallurgy_reforged.Metallurgy;
import net.minecraftforge.common.config.Config;

@Config.LangKey("config.metallurgy.category.tool_effects")
@Config(modid = Metallurgy.MODID, name = "metallurgy_reforged/effects", category = "tools")
public class ToolEffectsConfig {

	//Axes
	@Config.Name("Ignatius Pickaxe Effect")
	@Config.Comment("Has a chance of smelting the block drops depending on fortune applied to the tool")
	public static boolean ignatiusAxeEffect = true;

	//Pickaxes
	@Config.Name("Deep Iron Pickaxe Effect")
	@Config.Comment("Underwater mining is not slowed down")
	public static boolean deepIronPickaxeEffect = true;

	@Config.Name("Ignatius Pickaxe Effect")
	@Config.Comment("Randomly Spawns nuggets of the ore you're breaking")
	public static boolean ignatiusPickaxeEffect = true;

	//Shovels
	@Config.Name("Ignatius Shovel Effect")
	@Config.Comment("Has a chance of smelting the block drops depending on fortune applied to the tool")
	public static boolean ignatiusShovelEffect = true;

	//Tools
	@Config.Name("Shadow Steel Tools Effect")
	@Config.Comment("The tool speed is proportional to the darkness")
	public static boolean shadowSteelToolSpeedEffect = true;

	//Swords
	@Config.Name("Ceruclase Sword Effect")
	@Config.Comment("Has a Chance to slow down enemies on hit")
	public static boolean ceruclaseSwordEffect = true;
	@Config.Name("Deep Iron Sword Effect")
	@Config.Comment("Increase attack speed in water")
	public static boolean deepIronSwordEffect = true;
	@Config.Name("Desichalkos Sword Effect")
	@Config.Comment("Allows the player to teleport in the direction they're looking on right-click")
	public static boolean desichalkosSwordEffect = true;
	@Config.Name("Desichalkos Sword Blink Radius")
	@Config.Comment("The maximum number of blocks that you can teleport away through this effect")
	public static int desichalkosSwordBlinkRadius = 10;
	@Config.Name("Ignatius Sword Effect")
	@Config.Comment("Fire aspect")
	public static boolean ignatiusSwordEffect = true;
	@Config.Name("Kalendrite Sword Effect")
	@Config.Comment("Chance to regenerate your life on hit")
	public static boolean kalendriteSwordEffect = true;
	@Config.Name("Shadow Iron Sword Effect")
	@Config.Comment("Chance to blind the target")
	public static boolean shadowIronSwordEffect = true;
	@Config.Name("Shadow Steel Sword Effect")
	@Config.Comment("Speed and Damage is proportional to the darkness")
	public static boolean shadowSteelSwordEffect = true;
	@Config.Name("Tartarite Sword Effect")
	@Config.Comment("Withers the target")
	public static boolean tartariteSwordEffect = true;
	@Config.Name("Vulcanite Sword Effect")
	@Config.Comment("Fire Aspect")
	public static boolean vulcaniteSwordEffect = true;
	@Config.Name("Vyroxeres Sword Effect")
	@Config.Comment("Poisons the target")
	public static boolean vyroxeresSwordEffect = true;

}
