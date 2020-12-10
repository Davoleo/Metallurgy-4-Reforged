/*==============================================================================
 = Class: SetArmorTraits
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2020.
 =============================================================================*/

package it.hurts.metallurgy_reforged.integration.conarm;

import c4.conarm.lib.materials.ArmorMaterialType;
import c4.conarm.lib.traits.AbstractArmorTrait;
import it.hurts.metallurgy_reforged.material.Metal;
import slimeknights.tconstruct.library.materials.Material;

public class SetArmorTraits {

	public static void addArmorTrait(Metal metal, Material material)
	{
		switch (metal.getStats().getName())
		{
			case "quicksilver":
				addToEveryPart(material, MetallurgyArmorTraits.quickly);
				break;
			case "amordrine":
				addToEveryPart(material, MetallurgyArmorTraits.stronglyAmordrine);
				break;
			case "kalendrite":
				addToEveryPart(material, MetallurgyArmorTraits.stronglyKalendrite);
				break;
			case "astral_silver":
				addToEveryPart(material, MetallurgyArmorTraits.jumpMaster);
				break;
			case "celenegil":
				addToEveryPart(material, MetallurgyArmorTraits.resistance);
				break;
			case "deep_iron":
				addToEveryPart(material, MetallurgyArmorTraits.deeply);
				break;
			case "vulcanite":
				addToEveryPart(material, MetallurgyArmorTraits.volcano);
				break;
			case "adamantine":
				addToEveryPart(material, MetallurgyArmorTraits.foodly);
				break;
			case "platinum":
				material.addTrait(MetallurgyArmorTraits.catEyes, ArmorMaterialType.CORE);
				break;
			case "prometheum":
				material.addTrait(MetallurgyArmorTraits.prometheum, ArmorMaterialType.PLATES);
				break;
			case "shadow_steel":
				addToEveryPart(material, MetallurgyArmorTraits.blindness);
				break;
			default:
		}
	}

	private static void addToEveryPart(Material material, AbstractArmorTrait trait)
	{
		material.addTrait(trait, ArmorMaterialType.CORE);
		material.addTrait(trait, ArmorMaterialType.PLATES);
		material.addTrait(trait, ArmorMaterialType.TRIM);
	}

}
