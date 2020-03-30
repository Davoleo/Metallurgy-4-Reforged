/*
 * -------------------------------------------------------------------------------------------------------
 * Class: IHasModel
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2020.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.util;

import javax.annotation.Nonnull;

// TODO: 30/03/2020 Find a way to remove this interface
/**
 * When implemented will allow the item model to be loaded
 */
@FunctionalInterface
public interface IHasModel {

	/**
	 * @return the model subdirectory (empty string means root models dir)
	 */
	@Nonnull
	String getCategory();

}
