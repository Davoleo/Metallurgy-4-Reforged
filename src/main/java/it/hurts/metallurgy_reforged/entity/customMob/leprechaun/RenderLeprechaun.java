package it.hurts.metallurgy_reforged.entity.customMob.leprechaun;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class RenderLeprechaun extends RenderLiving<EntityLeprechaun> {

    private ResourceLocation mobTexture = new ResourceLocation("metallurgy:textures/entity/leprechaun.png");

    public static final Factory FACTORY = new Factory();

    public RenderLeprechaun(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelZombie(), 0.5F);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(@Nonnull EntityLeprechaun entity) {
        return mobTexture;
    }

    public static class Factory implements IRenderFactory<EntityLeprechaun> {

        @Override
        public Render<? super EntityLeprechaun> createRenderFor(RenderManager manager) {
            return new RenderLeprechaun(manager);
        }

    }
}
