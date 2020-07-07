/*
 * -------------------------------------------------------------------------------------------------------
 * Class: BlockTypes
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 * Copyright (c) 2020.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.block;

import it.hurts.metallurgy_reforged.config.RegistrationConfig;

public enum BlockTypes {

	BLOCK("block", RegistrationConfig.categoryBlocks.enableRawMetalBlocks),
	ENGRAVED_BLOCK("engraved_block", RegistrationConfig.categoryBlocks.enableEngravedMetalBlocks),
	LARGE_BRICKS("large_bricks", RegistrationConfig.categoryBlocks.enableLargeBricksMetalBlocks),
	BRICKS("bricks", RegistrationConfig.categoryBlocks.enableBricksMetalBlocks),
	CRYSTAL("crystals", RegistrationConfig.categoryBlocks.enableCrystalMetalBlocks),
	HAZARD_BLOCK("hazard_block", RegistrationConfig.categoryBlocks.enableHazardMetalBlocks),
	GLASS("reinforced_glass", RegistrationConfig.categoryBlocks.enableReinforcedGlassBlocks);

	private String prefix;
	private boolean isEnabled;

	BlockTypes(String prefix, boolean enabled)
	{
		this.prefix = prefix;
		this.isEnabled = enabled;
	}

	public String getPrefix()
	{
		return prefix;
	}

	public boolean isEnabled()
	{
		return isEnabled;
	}
}
