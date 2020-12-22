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
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;

// TODO: 14/12/2020 Generify this
public class ParticleOreEmitter extends ParticleOre {

    private final AxisAlignedBB attachedBox;
    private int age;
    private final int lifetime;
    private final int level;

    public ParticleOreEmitter(World worldIn, AxisAlignedBB box, int lifetime, float red, float green, float blue, int level) {
        super(worldIn, box.getCenter().x, box.getCenter().y, box.getCenter().z, 1F, red, green, blue, 5);
        attachedBox = box;
        this.lifetime = lifetime;
        this.level = level;
    }

    @Override
    public void onUpdate() {
        for (int i = 0; i < 16; ++i) {
            double x = attachedBox.minX + (attachedBox.maxX - attachedBox.minX) * rand.nextFloat();
            double y = attachedBox.minY + (attachedBox.maxY - attachedBox.minY) * rand.nextFloat();
            double z = attachedBox.minZ + (attachedBox.maxZ - attachedBox.minZ) * rand.nextFloat();
            ParticleOre particle = new ParticleOre(world, x, y, z, rand.nextFloat() + 0.5F, this.particleRed, this.particleGreen, this.particleBlue, this.level);
            particle.setMotionMultiplier(10);
            Minecraft.getMinecraft().effectRenderer.addEffect(particle);
        }

        ++this.age;

        if (this.age >= this.lifetime) {
            this.setExpired();
        }
    }
}
