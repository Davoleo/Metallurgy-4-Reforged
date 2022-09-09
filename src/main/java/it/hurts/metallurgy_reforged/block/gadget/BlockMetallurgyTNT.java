/*==============================================================================
 = Class: BlockVTNT
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2022.
 =============================================================================*/

package it.hurts.metallurgy_reforged.block.gadget;

import it.hurts.metallurgy_reforged.Metallurgy;
import it.hurts.metallurgy_reforged.entity.MetallurgyPrimedTNT;
import it.hurts.metallurgy_reforged.util.MetallurgyTabs;
import it.hurts.metallurgy_reforged.util.Utils;
import it.hurts.metallurgy_reforged.world.explosive.ExplosiveType;
import net.minecraft.block.BlockTNT;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

@ParametersAreNonnullByDefault
public class BlockMetallurgyTNT extends BlockTNT {

	private final ExplosiveType explosiveType;

	public BlockMetallurgyTNT(ExplosiveType explosiveType)
	{
		this.explosiveType = explosiveType;

		setRegistryName(Metallurgy.MODID, explosiveType + "_tnt");
		setTranslationKey(Metallurgy.MODID + '.' + explosiveType + "_tnt");
		setCreativeTab(MetallurgyTabs.tabSpecial);
	}

	@Override
	public void explode(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase igniter)
	{
		if (!worldIn.isRemote && state.getValue(EXPLODE))
		{
			MetallurgyPrimedTNT primedTNT = new MetallurgyPrimedTNT(worldIn,
					pos.getX() + 0.5F, pos.getY(), pos.getZ() + 0.5F,
					igniter, explosiveType
			);
			worldIn.spawnEntity(primedTNT);
			worldIn.playSound(null, primedTNT.posX, primedTNT.posY, primedTNT.posZ, SoundEvents.ENTITY_TNT_PRIMED, SoundCategory.BLOCKS, 1.0F, 1.0F);
		}
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
	{
		super.addInformation(stack, worldIn, tooltip, flagIn);
		tooltip.add(Utils.localizeEscapingCustomSequences("tooltip.metallurgy." + explosiveType + "_tnt"));
	}

}
