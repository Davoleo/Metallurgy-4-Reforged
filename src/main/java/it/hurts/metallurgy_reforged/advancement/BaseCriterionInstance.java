/*==============================================================================
 = Class: BaseCriterionInstance
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.advancement;

import net.minecraft.advancements.critereon.AbstractCriterionInstance;
import net.minecraft.util.ResourceLocation;

import java.util.function.Predicate;

public abstract class BaseCriterionInstance<T extends AbstractCriterionInstance> extends AbstractCriterionInstance implements Predicate<T> {

	public BaseCriterionInstance(ResourceLocation criterionIn)
	{
		super(criterionIn);
	}

	/**
	 * @param instance information from the game concerning the trigger
	 *
	 * @return whether the trigger was accomplished
	 */
	public abstract boolean test(T instance);

}
