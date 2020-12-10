/*==============================================================================
 = Class: SetTinkerTraits
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2020.
 =============================================================================*/

package it.hurts.metallurgy_reforged.integration.tic;

import it.hurts.metallurgy_reforged.config.GeneralConfig;
import it.hurts.metallurgy_reforged.material.Metal;
import slimeknights.tconstruct.library.materials.Material;
import slimeknights.tconstruct.library.traits.AbstractTrait;
import slimeknights.tconstruct.tools.TinkerTraits;

import static slimeknights.tconstruct.library.materials.MaterialTypes.*;
import static slimeknights.tconstruct.tools.TinkerTraits.*;

public class SetTinkerTraits {

	public static void addTraits(Metal metal, Material material)
	{

		switch (metal.getStats().getName())
		{
			//		TraitSuperheat = Increased damage to enemies that are on fire

			case "midasium":
			{
				addToEveryPart(material, writable2);

				if (GeneralConfig.enableDuplicationTrait)
					addToEveryPart(material, MetallurgyTinkerTraits.duplicaitonTrait);
			}
			break;

			case "ignatius":
			{
				material.addTrait(MetallurgyTinkerTraits.vulcanTrait, HEAD);
			}
			break;

			case "vulcanite":
			{
				material.addTrait(MetallurgyTinkerTraits.vulcanTrait1, HEAD);
				material.addTrait(superheat, HANDLE);
			}
			break;

			case "eximite":
			{
				material.addTrait(enderference, HEAD);
			}
			break;

			case "vyroxeres":
			{
				addToEveryPart(material, poisonous);
			}
			break;

			case "mithril":
			{
				//				TODO Glowing Modifiers
			}
			break;

			case "damascus_steel":
			{
				addToEveryPart(material, duritos);
				//				TODO Reinforced II
			}
			break;

			case "shadow_iron":
			{
				addToEveryPart(material, MetallurgyTinkerTraits.obscureTrait);
			}
			break;

			case "sanguinite":
			{
				material.addTrait(MetallurgyTinkerTraits.lifeStealTrait, HEAD);
				material.addTrait(MetallurgyTinkerTraits.lifeStealTrait, HANDLE);
			}
			break;

			case "tartarite":
			{
				material.addTrait(MetallurgyTinkerTraits.witherTrait, HEAD);
			}
			break;

			case "alduorite":
			{
				material.addTrait(TinkerTraits.unnatural, EXTRA);
				material.addTrait(TinkerTraits.unnatural, HEAD);
			}
			break;

			case "desichalkos":
			{
				material.addTrait(MetallurgyTinkerTraits.kingDiceTrait, EXTRA);
			}
			break;

			case "deep_iron":
			{
				addToEveryPart(material, MetallurgyTinkerTraits.opistognathusTrait);
			}
			break;

			default:
			{
			}
			break;
		}
	}

	private static void addToEveryPart(Material material, AbstractTrait trait)
	{
		material.addTrait(trait, HEAD);
		material.addTrait(trait, HANDLE);
		material.addTrait(trait, EXTRA);
	}

}
