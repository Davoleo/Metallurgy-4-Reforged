/*==============================================================================
 = Class: BrassKnucklesModel
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2020.
 =============================================================================*/

package it.hurts.metallurgy_reforged.render;

import it.hurts.metallurgy_reforged.Metallurgy;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class BrassKnucklesModel extends ModelBase {

    private final EntityPlayerSP player;
    private final TextureManager textureManager;

    public ModelRenderer shape1;
    public ModelRenderer arm;
    public ModelRenderer armWear;

    private static final ResourceLocation TEXTURE = new ResourceLocation(Metallurgy.MODID, "textures/items/gadgets/brass_knuckles.png");

    public BrassKnucklesModel(Minecraft mc, ItemCameraTransforms.TransformType transformType)
    {
        this.player = mc.player;
        this.textureManager = mc.getTextureManager();
        boolean isLeft = transformType == ItemCameraTransforms.TransformType.FIRST_PERSON_LEFT_HAND;

        this.textureWidth = 16;
        this.textureHeight = 16;

        if (!isLeft)
            this.arm = new ModelRenderer(this, 40, 16);
        else
            this.arm = new ModelRenderer(this, 32, 48);

        this.arm.setTextureSize(64, 64);
        this.arm.mirror = !isLeft;

        if (!isLeft)
            this.armWear = new ModelRenderer(this, 40, 32);
        else
            this.armWear = new ModelRenderer(this, 48, 48);
        this.armWear.setTextureSize(64, 64);

        this.arm.setRotationPoint(0.0F, -11.0F, 1.0F);
        this.armWear.setRotationPoint(0.0F, -11.0F, 1.0F);

        if (player.getSkinType().equals("slim"))
        {
            this.arm.addBox(0.0F, 0.0F, 0.0F, 3, 12, 4, 0.0F);
            this.armWear.addBox(0.0F, 0.0F, 0.0F, 3, 12, 4, 0.25F);
        }
        else
        {
            this.arm.addBox(0.0F, 0.0F, 0.0F, 4, 12, 4, 0.0F);
            this.armWear.addBox(0.0F, 0.0F, 0.0F, 4, 12, 4, 0.25F);
        }

        this.arm.isHidden = transformType != ItemCameraTransforms.TransformType.FIRST_PERSON_LEFT_HAND && transformType != ItemCameraTransforms.TransformType.FIRST_PERSON_RIGHT_HAND;
        this.armWear.isHidden = this.arm.isHidden;

        this.shape1 = new ModelRenderer(this, 0, 0);
        this.shape1.setRotationPoint(1.0F, 0.0F, 0.5F);
        this.shape1.addBox(0.0F, 0.0F, 0.0F, 1, 2, 5, 0.0F);

    }


    public void render(ItemStack stack)
    {
        //render the player's arm
        textureManager.bindTexture(player.getLocationSkin());
        this.arm.render(0.15F);
        this.armWear.render(0.15F);

        //render the knuckles model
        textureManager.bindTexture(TEXTURE);
        this.shape1.render(0.15F);

        if (stack.hasEffect())
            renderGlint();
    }

    protected static final ResourceLocation ENCHANTED_ITEM_GLINT_RES = new ResourceLocation("textures/misc/enchanted_item_glint.png");

    private void renderGlint()
    {
        float f = (float) player.ticksExisted + Minecraft.getMinecraft().getRenderPartialTicks();
        textureManager.bindTexture(ENCHANTED_ITEM_GLINT_RES);
        Minecraft.getMinecraft().entityRenderer.setupFogColor(true);
        GlStateManager.enableBlend();
        GlStateManager.depthFunc(514);
        GlStateManager.depthMask(false);
        GlStateManager.color(0.5F, 0.5F, 0.5F, 1.0F);

        for (int i = 0; i < 2; ++i)
        {
            GlStateManager.disableLighting();
            GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_COLOR, GlStateManager.DestFactor.ONE);
            GlStateManager.color(0.38F, 0.19F, 0.608F, 1.0F);
            GlStateManager.matrixMode(5890);
            GlStateManager.loadIdentity();
            GlStateManager.scale(0.33333334F, 0.33333334F, 0.33333334F);
            GlStateManager.rotate(30.0F - (float) i * 60.0F, 0.0F, 0.0F, 1.0F);
            GlStateManager.translate(0.0F, f * (0.001F + (float) i * 0.003F) * 20.0F, 0.0F);
            GlStateManager.matrixMode(5888);
            textureManager.bindTexture(TEXTURE);
            this.shape1.render(0.15F);
            GlStateManager.blendFunc(GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        }

        GlStateManager.matrixMode(5890);
        GlStateManager.loadIdentity();
        GlStateManager.matrixMode(5888);
        GlStateManager.enableLighting();
        GlStateManager.depthMask(true);
        GlStateManager.depthFunc(515);
        GlStateManager.disableBlend();
        Minecraft.getMinecraft().entityRenderer.setupFogColor(false);
    }

}
