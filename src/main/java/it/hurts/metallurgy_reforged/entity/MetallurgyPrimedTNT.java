/*==============================================================================
 = Class: MetallurgyPrimedTNT
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2022.
 =============================================================================*/

package it.hurts.metallurgy_reforged.entity;

import it.hurts.metallurgy_reforged.world.explosive.ExplosiveType;
import it.hurts.metallurgy_reforged.world.explosive.MetallurgyExplosion;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

public class MetallurgyPrimedTNT extends EntityTNTPrimed {

	private static final DataParameter<Integer> TYPE = EntityDataManager.createKey(MetallurgyPrimedTNT.class, DataSerializers.VARINT);

	private ExplosiveType explosionType;

	public MetallurgyPrimedTNT(World worldIn)
	{
		super(worldIn);
	}

	public MetallurgyPrimedTNT(World worldIn, double x, double y, double z, EntityLivingBase igniter, ExplosiveType explosionType)
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
		this.explosionType = ExplosiveType.byIndex(compound.getInteger("tnt_type"));
	}

	@Override
	public void notifyDataManagerChange(@Nonnull DataParameter<?> key)
	{
		super.notifyDataManagerChange(key);
		if (TYPE == key)
		{
			this.explosionType = ExplosiveType.byIndex(dataManager.get(TYPE));
		}
	}

	@Override
	protected void explode()
	{
		//Metallurgy.logger.info("Primed TNT creates METALLURGY EXPLOSION");
		MetallurgyExplosion.newExplosion(world, this, this.posX, this.posY, this.posZ, explosionType);
	}

	/**
	 * Blocks that are of a rock material should have 1/4 of resistance to VTNT
	 */
	@ParametersAreNonnullByDefault
	@Override
	public float getExplosionResistance(Explosion explosionIn, World worldIn, BlockPos pos, IBlockState blockStateIn)
	{
		if (explosionType == ExplosiveType.VULCANITE && blockStateIn.getMaterial() == Material.ROCK)
			return blockStateIn.getBlock().getExplosionResistance(world, pos, this, explosionIn) / 7F;

		return super.getExplosionResistance(explosionIn, worldIn, pos, blockStateIn);
	}

	@Nullable
	public ExplosiveType getType()
	{
		return explosionType;
	}

}
