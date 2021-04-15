/*==============================================================================
 = Class: IToolEffect
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.item.tool;

import it.hurts.metallurgy_reforged.effect.BaseMetallurgyEffect;
import it.hurts.metallurgy_reforged.material.MetalStats;

public interface IToolEffect {

	MetalStats getMetalStats();

	EnumTools getToolClass();

	void setEffect(BaseMetallurgyEffect effect);

}
