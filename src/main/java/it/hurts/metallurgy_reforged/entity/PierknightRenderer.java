/*==============================================================================
 = Class: PierknightRenderer
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.entity;

import it.hurts.metallurgy_reforged.Metallurgy;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerArrow;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class PierknightRenderer extends RenderLiving<EntityPierKnight> {

    private static final ResourceLocation PIERKNIGHT_SKIN = new ResourceLocation(Metallurgy.MODID, "textures/models/pierknight_skin.png");

    public PierknightRenderer(RenderManager renderManager)
    {
        super(renderManager, new ModelPlayer(0, false), 0.5F);
        this.addLayer(new LayerHeldItem(this));
        this.addLayer(new LayerArrow(this));
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(@Nonnull EntityPierKnight entity)
    {
        return PIERKNIGHT_SKIN;
    }

    public static class Factory implements IRenderFactory<EntityPierKnight> {
        @Override
        public Render<? super EntityPierKnight> createRenderFor(RenderManager manager)
        {
            return new PierknightRenderer(manager);
        }
    }
}
