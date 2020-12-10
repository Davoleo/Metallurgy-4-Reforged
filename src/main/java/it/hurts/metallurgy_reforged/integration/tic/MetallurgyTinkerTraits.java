/*==============================================================================
 = Class: MetallurgyTinkerTraits
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2020.
 =============================================================================*/

package it.hurts.metallurgy_reforged.integration.tic;

import it.hurts.metallurgy_reforged.integration.tic.trait.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumHand;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.tinkering.TinkersItem;
import slimeknights.tconstruct.library.traits.AbstractTrait;
import slimeknights.tconstruct.library.traits.ITrait;
import slimeknights.tconstruct.library.utils.TagUtil;

public class MetallurgyTinkerTraits {

	public static final AbstractTrait obscureTrait = new MetallurgyTraitObscure();
	public static final AbstractTrait vulcanTrait = new MetallurgyTraitVulcan(0);
	public static final AbstractTrait vulcanTrait1 = new MetallurgyTraitVulcan(1);
	public static final AbstractTrait vulcanTrait2 = new MetallurgyTraitVulcan(2);
	public static final AbstractTrait witherTrait = new MetallurgyTraitWither();
	public static final AbstractTrait lifeStealTrait = new MetallurgyTraitLifeSteal();
	public static final AbstractTrait kingDiceTrait = new MetallurgyTraitKingDice();
	public static final AbstractTrait duplicaitonTrait = new MetallurgyTraitDuplication();
	public static final AbstractTrait opistognathusTrait = new MetallurgyTraitOpistognathus();

	public static boolean isMetallurgyTrait(EntityPlayer player, String traitToCheck)
	{
		boolean flag = false;

		Item item = player.getHeldItem(EnumHand.MAIN_HAND).getItem();

		if (item instanceof TinkersItem)
		{
			NBTTagList list = TagUtil.getTraitsTagList(player.getHeldItem(EnumHand.MAIN_HAND));

			for (int i = 0; i < list.tagCount(); i++)
			{
				ITrait trait = TinkerRegistry.getTrait(list.getStringTagAt(i));

				if (trait instanceof IMetallurgyTrait)
				{
					IMetallurgyTrait metallurgyTrait = (IMetallurgyTrait) trait;

					if (metallurgyTrait.getIdentifier().equals(traitToCheck + "_trait"))
						flag = true;
				}
			}
		}

		return flag;
	}

}
