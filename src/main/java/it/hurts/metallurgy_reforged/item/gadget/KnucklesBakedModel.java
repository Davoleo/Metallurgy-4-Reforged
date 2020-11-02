package it.hurts.metallurgy_reforged.item.gadget;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.*;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.client.ForgeHooksClient;
import org.apache.commons.lang3.tuple.Pair;
import org.lwjgl.util.vector.Vector3f;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.vecmath.Matrix4f;
import java.util.List;

public class KnucklesBakedModel implements IBakedModel
{
    private final IBakedModel originalModel;

    public KnucklesBakedModel(IBakedModel originalModel) {this.originalModel = originalModel;}

    @Nonnull
    @Override
    public List<BakedQuad> getQuads(@Nullable IBlockState state, @Nullable EnumFacing side, long rand)
    {
        return originalModel.getQuads(state,side,rand);
    }

    @Override
    public boolean isAmbientOcclusion()
    {
        return originalModel.isAmbientOcclusion();
    }

    @Override
    public boolean isGui3d()
    {
        return originalModel.isGui3d();
    }

    @Override
    public boolean isBuiltInRenderer()
    {
        return originalModel.isBuiltInRenderer();
    }

    @Nonnull
    @Override
    public TextureAtlasSprite getParticleTexture()
    {
        return originalModel.getParticleTexture();
    }

    @Nonnull
    @Override
    public Pair<? extends IBakedModel, Matrix4f> handlePerspective(@Nonnull ItemCameraTransforms.TransformType cameraTransformType)
    {

        KnucklesRenderer.type = cameraTransformType;
        return ForgeHooksClient.handlePerspective(this,cameraTransformType);
    }

    //Temporary method that allows real-time loading of rendering changes
    @Override
    public ItemCameraTransforms getItemCameraTransforms()
    {
        ItemCameraTransforms origin = originalModel.getItemCameraTransforms();
        ItemTransformVec3f ground = origin.ground;
        ItemTransformVec3f gui = origin.gui;
        ItemTransformVec3f firstpersonRight = new ItemTransformVec3f(new Vector3f(0, 280F, 25F), new Vector3f(0.1F, 0.3F, -0.3F), new Vector3f(0.4F,0.4F,0.4F));
        ItemTransformVec3f firstpersonLeft = new ItemTransformVec3f(new Vector3f(0, 100F, -25F), new Vector3f(0.1F, 0.3F, -0.3F), new Vector3f(0.4F,0.4F,0.4F));
        ItemTransformVec3f thirdpersonRight =  new ItemTransformVec3f(new Vector3f(90f, 0F, 0F), new Vector3f(0.1f,-0.13f,0.15f), new Vector3f(0.375F,0.375F,0.375F));
        ItemTransformVec3f thirdpersonLeft =  new ItemTransformVec3f(new Vector3f(90f, 180F, 0F), new Vector3f(0.1f,-0.08f,0.15f), new Vector3f(0.375F,0.375F,0.375F));
        ItemTransformVec3f itemframe = origin.fixed;


        return new ItemCameraTransforms(thirdpersonLeft, thirdpersonRight, firstpersonLeft, firstpersonRight, ItemTransformVec3f.DEFAULT, gui, ground, itemframe);
    }

    @Nonnull
    @Override
    public ItemOverrideList getOverrides()
    {
        return originalModel.getOverrides();
    }
}
