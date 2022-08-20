/*==============================================================================
 = Class: MetallurgyPrimedTNT
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2022.
 =============================================================================*/

package it.hurts.metallurgy_reforged.entity;

import it.hurts.metallurgy_reforged.Metallurgy;
import it.hurts.metallurgy_reforged.world.MetallurgyExplosion;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class MetallurgyPrimedTNT extends EntityTNTPrimed {

	@Nullable
	private final EntityLivingBase igniter;
	private final MetallurgyExplosion.Type explosionType;

	public MetallurgyPrimedTNT(World worldIn, double x, double y, double z, EntityLivingBase igniter, MetallurgyExplosion.Type explosionType)
	{
		super(worldIn, x, y, z, igniter);
		this.igniter = igniter;
		this.explosionType = explosionType;
	}

	@Override
	protected void explode()
	{
		Metallurgy.logger.info("Primed TNT creates METALLURGY EXPLOSION");
		MetallurgyExplosion.newExplosion(world, this.igniter, this.posX, this.posY, this.posZ, explosionType);
	}

}
