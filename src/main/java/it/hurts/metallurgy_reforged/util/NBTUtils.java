/*==============================================================================
 = Class: NBTUtils
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.util;

import net.minecraft.nbt.NBTTagCompound;

import javax.annotation.Nonnull;

public class NBTUtils {

	/**
	 * Injects all the key value pairs from a compound to another one also prefixing all the keys
	 *
	 * @param prefixKey what will be prefixed to each key of the injected compound
	 * @param host      the host tag that will be injected with the other compound
	 * @param injected  the compound that will be injected
	 */
	public static void injectCompound(String prefixKey, @Nonnull NBTTagCompound host, @Nonnull NBTTagCompound injected)
	{
		injected.getKeySet().forEach(key -> host.setTag(prefixKey + key, injected.getTag(key)));
	}

	/**
	 * Ejects the previously injected compound extracting all the key/value pairs that start with {@code prefixKey}
	 *
	 * @param prefixKey the prefix the method is looking for to accept the key/value pair
	 * @param compound  the compound that is analyzed
	 *
	 * @return an ejected compound with all the keys that start with {@code prefixKey}
	 */
	public static NBTTagCompound ejectCompound(String prefixKey, NBTTagCompound compound)
	{
		NBTTagCompound ejected = new NBTTagCompound();
		compound.getKeySet().forEach(key -> {
			if (key.startsWith(prefixKey))
			{
				key = key.replace(prefixKey, "");
				ejected.setTag(key, compound.getTag(prefixKey + key));
			}
		});
		return ejected;
	}

}
