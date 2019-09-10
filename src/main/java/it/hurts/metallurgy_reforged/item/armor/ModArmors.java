/*
 * -------------------------------------------------------------------------------------------------------
 * Class: ModArmors
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2019.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.item.armor;

import it.hurts.metallurgy_reforged.config.ArmorConfig;
import it.hurts.metallurgy_reforged.config.GeneralConfig;
import it.hurts.metallurgy_reforged.util.ItemUtils;
import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.ArrayList;
import java.util.List;

public class ModArmors {

	public static final List<ItemArmorBase> armorList = new ArrayList<>();

	public static void register(IForgeRegistry<Item> registry)
	{
		if (!GeneralConfig.disableAllArmors)
		{
			for (int i = 0, c = 0; i < ArmorConfig.allArmor.length; i++)
			{
				if (ArmorConfig.allArmor[i])
				{
					for (int j = 0; j < 4; j++)
					{
						ItemArmorBase armorPiece = armorList.get(c);
						registry.register(armorPiece);

						EnumArmorEffects effect = EnumArmorEffects.getEffect(armorPiece);
						if (effect != null)
							armorPiece.setEffect(effect);

						c++;
					}
				}
			}
		}

	}

	public static void registerModels()
	{
		if (!GeneralConfig.disableAllArmors)
		{
			for (int i = 0,
			     c = 0; i < ArmorConfig.allArmor.length; i++)
			{
				for (int j = 0; j < 4; j++)
				{
					if (ArmorConfig.allArmor[i])
						ItemUtils.registerCustomItemModel(armorList.get(c), 0, armorList.get(c).getCategory());

					c++;
				}
			}
		}
	}

}
