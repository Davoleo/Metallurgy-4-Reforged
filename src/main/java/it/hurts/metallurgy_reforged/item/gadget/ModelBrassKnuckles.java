package it.hurts.metallurgy_reforged.item.gadget;

import it.hurts.metallurgy_reforged.Metallurgy;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class ModelBrassKnuckles extends ModelBase
{

    private final EntityPlayerSP player;
    private final TextureManager textureManager;

    public ModelRenderer shape1;
    public ModelRenderer arm;
    public ModelRenderer armWear;

    public ModelBrassKnuckles(Minecraft mc, ItemCameraTransforms.TransformType transformType)
    {
        this.player = mc.player;
        this.textureManager = mc.getTextureManager();
        boolean isLeft = transformType == ItemCameraTransforms.TransformType.FIRST_PERSON_LEFT_HAND;


        this.textureWidth = 16;
        this.textureHeight = 16;


        if(!isLeft)
            this.arm = new ModelRenderer(this, 40, 16);
        else
            this.arm = new ModelRenderer(this, 32, 48);


        this.arm.setTextureSize(64, 64);
        this.arm.mirror = isLeft;

        if(!isLeft)
            this.armWear = new ModelRenderer(this, 40, 32);
        else
            this.armWear = new ModelRenderer(this, 48, 48);
        this.armWear.setTextureSize(64, 64);

        this.arm.setRotationPoint(0.0F, -11.0F, 1.0F);
        this.armWear.setRotationPoint(0.0F, -11.0F, 1.0F);

        if(DefaultPlayerSkin.getSkinType(mc.player.getUniqueID()).equals("slim"))
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
        this.shape1.setRotationPoint(1.0F, -0.5F, 0.4F);
        this.shape1.addBox(0.0F, 0.0F, 0.0F, 1, 2, 4, 0.0F);


    }

    private void renderArm()
    {

        textureManager.bindTexture(player.getLocationSkin());
        this.arm.render(0.15f);
        this.armWear.render(0.15f);
    }

    private void renderKnuckles()
    {
        float f5 = 0.15f;

        GlStateManager.pushMatrix();
        textureManager.bindTexture(new ResourceLocation(Metallurgy.MODID, "textures/items/gadgets/test.png"));

        GlStateManager.translate(this.shape1.offsetX, this.shape1.offsetY, this.shape1.offsetZ);
        GlStateManager.translate(this.shape1.rotationPointX * f5, this.shape1.rotationPointY * f5, this.shape1.rotationPointZ * f5);
        GlStateManager.scale(1.25D, 1.25D, 1.25D);
        GlStateManager.translate(-this.shape1.offsetX, -this.shape1.offsetY, -this.shape1.offsetZ);
        GlStateManager.translate(-this.shape1.rotationPointX * f5, -this.shape1.rotationPointY * f5, -this.shape1.rotationPointZ * f5);
        this.shape1.render(f5);
        GlStateManager.popMatrix();
    }

    public void render(ItemStack stack)
    {
        renderArm();
        renderKnuckles();
        if(stack.hasEffect())
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
            renderKnuckles();
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
