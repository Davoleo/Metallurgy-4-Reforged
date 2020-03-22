/*
 * -------------------------------------------------------------------------------------------------------
 * Class: BlockTypes
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2020.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.block;

public enum BlockTypes {

	BLOCK("block"),
	ENGRAVED_BLOCK("engravedBlock"),
	LARGE_BRICKS("largeBricks"),
	BRICKS("bricks"),
	CRYSTAL("crystals");

	private String prefix;

	BlockTypes(String prefix)
	{
		this.prefix = prefix;
	}

	public String getPrefix()
	{
		return prefix;
	}
}
