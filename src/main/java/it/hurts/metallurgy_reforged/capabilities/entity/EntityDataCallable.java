/*==============================================================================
 = Class: EntityDataCallable
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2020.
 =============================================================================*/

package it.hurts.metallurgy_reforged.capabilities.entity;

import java.util.concurrent.Callable;

public class EntityDataCallable implements Callable<EntityData> {

	@Override
	public EntityData call() throws Exception
	{
		return new EntityData();
	}

}
