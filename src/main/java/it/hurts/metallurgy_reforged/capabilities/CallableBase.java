/*==============================================================================
 = Class: CallableBase
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2020.
 =============================================================================*/

package it.hurts.metallurgy_reforged.capabilities;

import java.util.concurrent.Callable;

public class CallableBase<T> implements Callable<T> {

	private Class<T> clazz;

	@Override
	public T call() throws Exception
	{
		return null;
		//return new clazz.newInstance();
	}

}
