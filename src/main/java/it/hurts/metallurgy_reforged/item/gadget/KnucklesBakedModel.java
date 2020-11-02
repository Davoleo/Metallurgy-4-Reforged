package it.hurts.metallurgy_reforged.item.gadget;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.*;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.client.ForgeHooksClient;
import org.apache.commons.lang3.tuple.Pair;
import org.lwjgl.util.vector.Vector3f;

import javax.annotation.Nullable;
import javax.vecmath.Matrix4f;
import java.util.List;

public class KnucklesBakedModel implements IBakedModel
{
    private final IBakedModel originalModel;

    public KnucklesBakedModel(IBakedModel originalModel) {this.originalModel = originalModel;}

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

    @Override
    public TextureAtlasSprite getParticleTexture()
    {
        return originalModel.getParticleTexture();
    }

    @Override
    public Pair<? extends IBakedModel, Matrix4f> handlePerspective(ItemCameraTransforms.TransformType cameraTransformType)
    {

        KnucklesRenderer.type = cameraTransformType;
        return ForgeHooksClient.handlePerspective(this,cameraTransformType);
    }



    @Override
    public ItemCameraTransforms getItemCameraTransforms()
    {
        ItemCameraTransforms origin = originalModel.getItemCameraTransforms();
        ItemTransformVec3f ground = origin.ground;
        ItemTransformVec3f gui = origin.gui;
        ItemTransformVec3f firstperson_right = new ItemTransformVec3f(new Vector3f(0, 280F, 25F), new Vector3f(0.1F, 0.3F, -0.3F), new Vector3f(0.4F,0.4F,0.4F));
        ItemTransformVec3f firstperson_left = new ItemTransformVec3f(new Vector3f(0, 100F, -25F), new Vector3f(0.1F, 0.3F, -0.3F), new Vector3f(0.4F,0.4F,0.4F));
        ItemTransformVec3f thirdperson_right =  new ItemTransformVec3f(new Vector3f(90f, 0F, 0F), new Vector3f(0.1f,-0.13f,0.15f), new Vector3f(0.375F,0.375F,0.375F));

        ItemTransformVec3f thirdperson_left =  new ItemTransformVec3f(new Vector3f(90f, 180F, 0F), new Vector3f(0.1f,-0.08f,0.15f), new Vector3f(0.375F,0.375F,0.375F));
        ItemTransformVec3f itemframe = origin.fixed;


        return new ItemCameraTransforms(thirdperson_left, thirdperson_right, firstperson_left, firstperson_right, ItemTransformVec3f.DEFAULT, gui, ground, itemframe);
    }

    @Override
    public ItemOverrideList getOverrides()
    {
        return originalModel.getOverrides();
    }
}
