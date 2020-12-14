/*==============================================================================
 = Class: ParticleOre
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2020.
 =============================================================================*/

package it.hurts.metallurgy_reforged.particle;

import it.hurts.metallurgy_reforged.Metallurgy;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleRedstone;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class ParticleOre extends ParticleRedstone {

	private static final ResourceLocation[] TEXTURES = new ResourceLocation[10];

	static
	{
		for (int i = 0; i < 10; i++)
			TEXTURES[i] = new ResourceLocation(Metallurgy.MODID, "particles/ore_particle_" + (i + 1));
	}

	private int textureIndex = 0;
	private int prevTextureIndex = 0;
	private int level;

	public ParticleOre(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, float scale, float red, float green, float blue, int level) {
		super(worldIn, xCoordIn, yCoordIn, zCoordIn, scale, 0, 0, 0);
		this.particleMaxAge = 40;
		this.particleRed = red;
		this.particleGreen = green;
		this.particleBlue = blue;
		this.level = level;

		this.setParticleTexture(Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(TEXTURES[0].toString()));
	}

	public void setMotionMultiplier(float motion) {
		this.motionX *= motion;
		this.motionY *= motion;
		this.motionZ *= motion;
	}

	@Override
	public int getFXLayer() {
		return 1;
	}

	@Override
	public void onUpdate() {
		prevTextureIndex = textureIndex;

		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;

		if (this.particleAge++ >= this.particleMaxAge)
			this.setExpired();

		this.move(this.motionX, this.motionY, this.motionZ);

		if (this.posY == this.prevPosY)
		{
			this.motionX *= 0.80D;
			this.motionZ *= 0.80D;
		}

		this.motionX *= 0.8599999785423279D;
		this.motionY *= 0.8599999785423279D;
		this.motionZ *= 0.8599999785423279D;

		if (this.onGround)
		{
			this.motionX *= 0.699999988079071D;
			this.motionZ *= 0.699999988079071D;
		}

		int tickStep = (particleMaxAge / 10);
		if (particleAge % tickStep == 0)
		{

			textureIndex++;

			if (textureIndex > Math.min(level * (9 / 4), 9))
				textureIndex = 0;

			if (prevTextureIndex != textureIndex)
				setParticleTexture(Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(TEXTURES[textureIndex].toString()));
		}
	}

}
