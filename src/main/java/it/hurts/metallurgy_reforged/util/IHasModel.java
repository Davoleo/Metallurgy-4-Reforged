/*
 * -------------------------------------------------------------------------------------------------------
 * Class: IHasModel
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2019.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.util;

import javax.annotation.Nonnull;

/**
 * When implemented will allow the item model to be loaded
 */
public interface IHasModel {

	/**
	 * @return the model subdirectory (empty string means root models dir)
	 */
	@Nonnull
	String getCategory();

}
