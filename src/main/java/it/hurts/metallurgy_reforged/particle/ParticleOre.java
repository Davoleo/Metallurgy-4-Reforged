/*
 * -------------------------------------------------------------------------------------------------------
 * Class: ParticleOre
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2019.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.particle;

import it.hurts.metallurgy_reforged.handler.RegistrationHandler;
import net.minecraft.client.particle.ParticleRedstone;
import net.minecraft.world.World;

public class ParticleOre extends ParticleRedstone {

	public ParticleOre(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, float scale, float red, float green, float blue, int type)
	{
		super(worldIn, xCoordIn, yCoordIn, zCoordIn, scale, 0, 0, 0);
		//this.particleMaxAge = 10;
		this.particleRed = red;
		this.particleGreen = green;
		this.particleBlue = blue;
		this.setParticleTexture(RegistrationHandler.oreParticles.get(type));
	}

	@Override
	public int getFXLayer()
	{
		return 1;
	}

	@Override
	public void onUpdate()
	{

		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;

		if (this.particleAge++ >= this.particleMaxAge)
		{
			this.setExpired();
		}

		this.move(this.motionX, this.motionY, this.motionZ);

		if (this.posY == this.prevPosY)
		{
			this.motionX *= 1.1D;
			this.motionZ *= 1.1D;
		}

		this.motionX *= 0.9599999785423279D;
		this.motionY *= 0.9599999785423279D;
		this.motionZ *= 0.9599999785423279D;

		if (this.onGround)
		{
			this.motionX *= 0.699999988079071D;
			this.motionZ *= 0.699999988079071D;
		}
	}

}
