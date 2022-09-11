/*==============================================================================
 = Class: MetallurgyExplosion
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2022.
 =============================================================================*/

package it.hurts.metallurgy_reforged.world.explosive;

import com.google.common.collect.Sets;
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.network.PacketManager;
import it.hurts.metallurgy_reforged.network.client.PacketMetallurgyExplosion;
import it.hurts.metallurgy_reforged.particle.ParticleOre;
import it.hurts.metallurgy_reforged.util.Utils;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.enchantment.EnchantmentProtection;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Set;

public class MetallurgyExplosion extends Explosion {

	private final ExplosiveType type;

	@SuppressWarnings("ConstantConditions")
	//<== Author should be nullable but is implicitly marked as Nonnull in the superclass
	@SideOnly(Side.CLIENT)
	public MetallurgyExplosion(World worldIn, @Nullable Entity author, double x, double y, double z, List<BlockPos> affectedPositions, ExplosiveType type)
	{
		super(worldIn, author, x, y, z, type.strength, type.causesFire, type.damagesTerrain, affectedPositions);
		this.type = type;
	}

	public MetallurgyExplosion(World worldIn, Entity author, double x, double y, double z, ExplosiveType type)
	{
		super(worldIn, author, x, y, z, type.strength, type.causesFire, type.damagesTerrain);
		this.type = type;
	}

	public static MetallurgyExplosion newExplosion(World world, @Nullable Entity entityIn, double x, double y, double z, ExplosiveType type)
	{
		MetallurgyExplosion explosion = new MetallurgyExplosion(world, entityIn, x, y, z, type);

		if (net.minecraftforge.event.ForgeEventFactory.onExplosionStart(world, explosion))
			return explosion;

		explosion.doExplosionA();
		explosion.doExplosionB(true);

		//Only on server
		if (!world.isRemote)
		{
			if (!type.damagesTerrain)
				explosion.clearAffectedBlockPositions();

			for (EntityPlayer entityplayer : world.playerEntities)
			{
				if (entityplayer.getDistanceSq(x, y, z) < 4096.0D && entityplayer instanceof EntityPlayerMP)
				{
					PacketMetallurgyExplosion packet = new PacketMetallurgyExplosion(x, y, z, explosion.playerKnockbackMap.get(entityplayer), explosion.getAffectedBlockPositions(), type);
					PacketManager.network.sendTo(packet, (EntityPlayerMP) entityplayer);
				}
			}
		}

		return explosion;
	}

	@Override
	public void doExplosionA()
	{
		//Metallurgy.logger.info("EXPLOSION A");

		Set<BlockPos> set = Sets.newHashSet();

		for (int j = 0; j < 16; ++j)
		{
			for (int k = 0; k < 16; ++k)
			{
				for (int l = 0; l < 16; ++l)
				{
					if (j == 0 || j == 15 || k == 0 || k == 15 || l == 0 || l == 15)
					{
						double d0 = ((float) j / 15.0F * 2.0F - 1.0F);
						double d1 = ((float) k / 15.0F * 2.0F - 1.0F);
						double d2 = ((float) l / 15.0F * 2.0F - 1.0F);
						double d3 = Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
						d0 = d0 / d3;
						d1 = d1 / d3;
						d2 = d2 / d3;
						float actualSize = this.size * (0.7F + this.world.rand.nextFloat() * 0.6F);
						double x = this.x;
						double y = this.y;
						double z = this.z;

						for (; actualSize > 0.0F; actualSize -= 0.22500001F)
						{
							BlockPos blockpos = new BlockPos(x, y, z);
							IBlockState iblockstate = this.world.getBlockState(blockpos);

							if (iblockstate.getMaterial() != Material.AIR)
							{
								float mitigation = this.exploder != null ?
										this.exploder.getExplosionResistance(this, this.world, blockpos, iblockstate) :
										iblockstate.getBlock().getExplosionResistance(world, blockpos, null, this);
								actualSize -= (mitigation + 0.3F) * 0.3F;
							}

							if (actualSize > 0.0F)
								set.add(blockpos);

							x += d0 * 0.30000001192092896D;
							y += d1 * 0.30000001192092896D;
							z += d2 * 0.30000001192092896D;
						}
					}
				}
			}
		}

		this.affectedBlockPositions.addAll(set);
		float sizeX2 = this.size * 2.0F;
		int xLo = MathHelper.floor(this.x - sizeX2 - 1.0D);
		int xHi = MathHelper.floor(this.x + sizeX2 + 1.0D);
		int yLo = MathHelper.floor(this.y - sizeX2 - 1.0D);
		int yHi = MathHelper.floor(this.y + sizeX2 + 1.0D);
		int zLo = MathHelper.floor(this.z - sizeX2 - 1.0D);
		int zHi = MathHelper.floor(this.z + sizeX2 + 1.0D);
		List<Entity> list = this.world.getEntitiesWithinAABBExcludingEntity(this.exploder, new AxisAlignedBB(xLo, yLo, zLo, xHi, yHi, zHi));
		net.minecraftforge.event.ForgeEventFactory.onExplosionDetonate(this.world, this, list, sizeX2);

		for (Entity entity : list)
		{
			if (!entity.isImmuneToExplosions())
			{
				double distance = entity.getDistance(this.x, this.y, this.z) / (double) sizeX2;

				if (distance <= 1.0D)
				{
					double dx = entity.posX - this.x;
					double dyEyes = entity.posY + (double) entity.getEyeHeight() - this.y;
					double dz = entity.posZ - this.z;
					double distanceEyes = MathHelper.sqrt(dx * dx + dyEyes * dyEyes + dz * dz);

					if (distanceEyes != 0.0D)
					{
						dx = dx / distanceEyes;
						dyEyes = dyEyes / distanceEyes;
						dz = dz / distanceEyes;
						double blockDensity = this.world.getBlockDensity(getPosition(), entity.getEntityBoundingBox());
						double d10 = (1.0D - distance) * blockDensity;

						float sizeBasedDamage;
						switch (type)
						{
							case VULCANITE:
								sizeBasedDamage = (float) ((int) ((d10 * d10 + d10) / 2.0D * 7.0D * (double) size + 1.0D));
								break;
							default:
								sizeBasedDamage = (float) ((int) ((d10 * d10 + d10) / 2.0D * 7.0D * (double) sizeX2 + 1.0D));
								break;
						}
						entity.attackEntityFrom(DamageSource.causeExplosionDamage(this), sizeBasedDamage);

						double reducedExplosionDamage = d10;

						if (entity instanceof EntityLivingBase)
						{
							reducedExplosionDamage = EnchantmentProtection.getBlastDamageReduction((EntityLivingBase) entity, d10);
						}

						entity.motionX += dx * reducedExplosionDamage;
						entity.motionY += dyEyes * reducedExplosionDamage;
						entity.motionZ += dz * reducedExplosionDamage;

						if (entity instanceof EntityPlayer)
						{
							EntityPlayer entityplayer = (EntityPlayer) entity;

							if (!entityplayer.isSpectator() && (!entityplayer.isCreative() || !entityplayer.capabilities.isFlying))
							{
								this.playerKnockbackMap.put(entityplayer, new Vec3d(dx * d10, dyEyes * d10, dz * d10));
							}
						}
					}
				}
			}
		}
	}

	@Override
	public void doExplosionB(boolean spawnParticles)
	{
		//Metallurgy.logger.info("EXPLOSION B");

		this.world.playSound(null, this.x, this.y, this.z, SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.BLOCKS, 4.0F, (1.0F + (this.world.rand.nextFloat() - this.world.rand.nextFloat()) * 0.2F) * 0.7F);

		if (this.size >= 2.0F && this.damagesTerrain)
		{
			this.world.spawnParticle(EnumParticleTypes.EXPLOSION_HUGE, this.x, this.y, this.z, 1.0D, 0.0D, 0.0D);
		}
		else
		{
			this.world.spawnParticle(EnumParticleTypes.EXPLOSION_LARGE, this.x, this.y, this.z, 1.0D, 0.0D, 0.0D);
		}

		if (this.damagesTerrain)
		{
			for (BlockPos blockpos : this.affectedBlockPositions)
			{
				IBlockState state = this.world.getBlockState(blockpos);
				Block block = state.getBlock();

				if (spawnParticles)
				{
					double randX = ((float) blockpos.getX() + this.world.rand.nextFloat());
					double randY = ((float) blockpos.getY() + this.world.rand.nextFloat());
					double randZ = ((float) blockpos.getZ() + this.world.rand.nextFloat());
					double dx = randX - this.x;
					double dy = randY - this.y;
					double dz = randZ - this.z;
					double distance = MathHelper.sqrt(dx * dx + dy * dy + dz * dz);
					dx = dx / distance;
					dy = dy / distance;
					dz = dz / distance;
					double d7 = 0.5D / (distance / (double) this.size + 0.1D);
					d7 = d7 * (double) (this.world.rand.nextFloat() * this.world.rand.nextFloat() + 0.3F);
					dx = dx * d7;
					dy = dy * d7;
					dz = dz * d7;

					float[] color = new float[3];
					if (type == ExplosiveType.VULCANITE)
					{
						Utils.getRGBComponents(ModMetals.VULCANITE.getStats().getColorHex(), color);

						if (random.nextInt(3) == 0)
							world.spawnParticle(EnumParticleTypes.FLAME, (randX + this.x) / 2.0D, (randY + this.y) / 2.0D, (randZ + this.z) / 2.0D, dx, dy, dz);
						else if (world.isRemote)
							Minecraft.getMinecraft().effectRenderer.addEffect(new ParticleOre(world, randX, randY, randZ, dx, dy, dz, 3F, color[0], color[1], color[2], true, random.nextInt(2) + 6));

					}
				}

				if (state.getMaterial() != Material.AIR)
				{
					switch (type)
					{
						case VULCANITE:
							if (block.getHarvestLevel(state) <= ModMetals.VULCANITE.getStats().getToolStats().getHarvestLevel())
							{
								block.dropBlockAsItemWithChance(this.world, blockpos, this.world.getBlockState(blockpos), 1F, 0);
								block.onBlockExploded(this.world, blockpos, this);
							}
							break;
						default:
							if (block.canDropFromExplosion(this))
							{
								block.dropBlockAsItemWithChance(this.world, blockpos, this.world.getBlockState(blockpos), 1.0F / this.size, 0);
							}

							block.onBlockExploded(this.world, blockpos, this);
							break;
					}
				}
			}
		}

		if (this.causesFire)
		{
			for (BlockPos pos : this.affectedBlockPositions)
			{
				if (this.world.getBlockState(pos).getMaterial() == Material.AIR && this.world.getBlockState(pos.down()).isFullBlock())
					this.world.setBlockState(pos, Blocks.FIRE.getDefaultState());
			}
		}

	}

}
