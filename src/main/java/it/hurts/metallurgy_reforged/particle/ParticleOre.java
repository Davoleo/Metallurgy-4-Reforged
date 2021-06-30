/*==============================================================================
 = Class: ParticleOre
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.particle;

import it.hurts.metallurgy_reforged.Metallurgy;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;

@SideOnly(Side.CLIENT)
public class ParticleOre extends Particle {

	private static final ResourceLocation[] TEXTURES = new ResourceLocation[10];

	static
	{
		for (int i = 0; i < 10; i++)
			TEXTURES[i] = new ResourceLocation(Metallurgy.MODID, "particles/ore_particle_" + (i + 1));
	}

	private int textureIndex = 0;
	private final int level;
	private final float particleOreScale;
	private final boolean dynamic;

	/**
	 * @param level value from 1 to 10, it specifies the texture progression of this particle,the higher it is , the more detailed the texture gets
	 */
	public ParticleOre(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double motionX, double motionY, double motionZ, float scale, float red, float green, float blue, boolean dynamic, int level)
	{
		this(worldIn, xCoordIn, yCoordIn, zCoordIn, scale, red, green, blue, dynamic, level);
		this.motionX = motionX;
		this.motionY = motionY;
		this.motionZ = motionZ;
	}

	/**
	 * particle with random direction
	 */
	public ParticleOre(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, float scale, float red, float green, float blue, boolean dynamic, int level)
	{
		super(worldIn, xCoordIn, yCoordIn, zCoordIn, 0.0D, 0.0D, 0.0D);
		this.particleMaxAge = (int) (40 * 0.8D + Math.random() * 0.4D);
		this.particleRed = red;
		this.particleGreen = green;
		this.particleBlue = blue;
		if (level > 9)
		{
			Metallurgy.logger.error("Level Particle Too High, Max 9");
			level = 9;
		}

		this.level = level;
		float f = (float) Math.random() * 0.4F + 0.6F;
		this.particleRed = ((float) (Math.random() * 0.20000000298023224D) + 0.8F) * red * f;
		this.particleGreen = ((float) (Math.random() * 0.20000000298023224D) + 0.8F) * green * f;
		this.particleBlue = ((float) (Math.random() * 0.20000000298023224D) + 0.8F) * blue * f;
		this.particleScale *= 0.75F;
		this.particleScale *= scale;
		this.particleOreScale = this.particleScale;

		this.motionX *= 0.10000000149011612D;
		this.motionY *= 0.10000000149011612D;
		this.motionZ *= 0.10000000149011612D;

		this.setParticleTexture(Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(TEXTURES[dynamic ? 0 : level].toString()));

		this.dynamic = dynamic;
	}

	public void setMotionMultiplier(float motion)
	{
		this.motionX *= motion;
		this.motionY *= motion;
		this.motionZ *= motion;
	}

	public void renderParticle(@Nonnull BufferBuilder buffer, @Nonnull Entity entityIn, float partialTicks, float rotationX, float rotationZ, float rotationYZ, float rotationXY, float rotationXZ)
	{
		float scaleStartTimeStamp = this.particleMaxAge * 0.7F;

		float currentAge = ((float) this.particleAge + partialTicks);

		if (currentAge >= scaleStartTimeStamp)
		{
			float scaledMaxAge = this.particleMaxAge - scaleStartTimeStamp;
			float scaledCurrentAge = currentAge - scaleStartTimeStamp;
			float particleAnimator = 1F - MathHelper.clamp(scaledCurrentAge / scaledMaxAge, 0.0F, 1F);
			this.particleScale = this.particleOreScale * particleAnimator;
		}
		super.renderParticle(buffer, entityIn, partialTicks, rotationX, rotationZ, rotationYZ, rotationXY, rotationXZ);
	}

	@Override
	public int getFXLayer()
	{
		return 1;
	}

	@Override
	public void onUpdate()
	{
		int prevTextureIndex = textureIndex;

		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;

		if (this.particleAge++ >= this.particleMaxAge)
			this.setExpired();

		this.move(this.motionX, this.motionY, this.motionZ);

		if (this.onGround)
		{
			this.motionX *= 0.699999988079071D;
			this.motionZ *= 0.699999988079071D;
		}
		int tickStep = (particleMaxAge / 7);
		if (dynamic && particleAge % tickStep == 0)
		{
			textureIndex++;

			if (textureIndex > level)
				textureIndex = 0;

			if (prevTextureIndex != textureIndex)
				setParticleTexture(Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(TEXTURES[textureIndex].toString()));
		}


	}

}
