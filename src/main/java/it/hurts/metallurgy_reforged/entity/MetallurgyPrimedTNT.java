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
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class MetallurgyPrimedTNT extends EntityTNTPrimed {

	private static final DataParameter<Integer> TYPE = EntityDataManager.createKey(MetallurgyPrimedTNT.class, DataSerializers.VARINT);

	private MetallurgyExplosion.Type explosionType;

	public MetallurgyPrimedTNT(World worldIn)
	{
		super(worldIn);
	}

	public MetallurgyPrimedTNT(World worldIn, double x, double y, double z, EntityLivingBase igniter, MetallurgyExplosion.Type explosionType)
	{
		super(worldIn, x, y, z, igniter);
		this.explosionType = explosionType;
		this.dataManager.set(TYPE, explosionType.ordinal());
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.dataManager.register(TYPE, -1);
	}

	@Override
	protected void writeEntityToNBT(@Nonnull NBTTagCompound compound)
	{
		super.writeEntityToNBT(compound);
		compound.setInteger("tnt_type", this.explosionType.ordinal());
	}

	@Override
	protected void readEntityFromNBT(@Nonnull NBTTagCompound compound)
	{
		super.readEntityFromNBT(compound);
		this.explosionType = MetallurgyExplosion.Type.byIndex(compound.getInteger("tnt_type"));
	}

	@Override
	public void notifyDataManagerChange(@Nonnull DataParameter<?> key)
	{
		super.notifyDataManagerChange(key);
		if (TYPE == key)
		{
			this.explosionType = MetallurgyExplosion.Type.byIndex(dataManager.get(TYPE));
		}
	}

	@Override
	protected void explode()
	{
		Metallurgy.logger.info("Primed TNT creates METALLURGY EXPLOSION");
		MetallurgyExplosion.newExplosion(world, this.getTntPlacedBy(), this.posX, this.posY, this.posZ, explosionType);
	}

	@Nullable
	public MetallurgyExplosion.Type getType()
	{
		return explosionType;
	}

}
