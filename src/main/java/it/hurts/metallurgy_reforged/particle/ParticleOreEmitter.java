/*==============================================================================
 = Class: ParticleOreEmitter
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2020.
 =============================================================================*/

package it.hurts.metallurgy_reforged.particle;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

// TODO: 14/12/2020 Generify this
public class ParticleOreEmitter extends ParticleOre {

    private final Entity attachedEntity;
    private int age;
    private final int lifetime;

    public ParticleOreEmitter(World worldIn, Entity entity, int lifetime, float red, float green, float blue) {
        super(worldIn, entity.posX, entity.posY + (entity.height / 2F), entity.posZ, 1F, red, green, blue, 5);
        attachedEntity = entity;
        this.lifetime = lifetime;
    }

    @Override
    public void onUpdate() {
        for (int i = 0; i < 16; ++i) {
            double rand1 = this.rand.nextFloat() * 2F - 1F;
            double rand2 = this.rand.nextFloat() * 2F - 1F;
            double rand3 = this.rand.nextFloat() * 2F - 1F;

            if (rand1 * rand1 + rand2 * rand2 + rand3 * rand3 <= 1D) {
                double x = this.attachedEntity.posX + rand1 * this.attachedEntity.width / 4D;
                double y = this.attachedEntity.getEntityBoundingBox().minY + this.attachedEntity.height * rand.nextFloat();
                double z = this.attachedEntity.posZ + rand3 * this.attachedEntity.width / 4D;
                ParticleOre particle = new ParticleOre(world, x, y, z, rand.nextFloat() + 0.5F, this.particleRed, this.particleGreen, this.particleBlue, 5);
                particle.setMotionMultiplier(10);
                Minecraft.getMinecraft().effectRenderer.addEffect(particle);
            }
        }

        ++this.age;

        if (this.age >= this.lifetime) {
            this.setExpired();
        }
    }
}
