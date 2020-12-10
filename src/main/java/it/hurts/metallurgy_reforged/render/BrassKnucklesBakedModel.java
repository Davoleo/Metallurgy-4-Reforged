/*==============================================================================
 = Class: BrassKnucklesBakedModel
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2020.
 =============================================================================*/

package it.hurts.metallurgy_reforged.render;

import it.hurts.metallurgy_reforged.Metallurgy;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.*;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.common.model.TRSRTransformation;
import org.apache.commons.lang3.tuple.Pair;
import org.lwjgl.util.vector.Vector3f;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.vecmath.Matrix4f;
import java.util.List;

public class BrassKnucklesBakedModel implements IBakedModel {

	private final IBakedModel originalModel;

	public BrassKnucklesBakedModel(IBakedModel originalModel)
	{
		this.originalModel = originalModel;
	}

	@Nonnull
	@Override
	public List<BakedQuad> getQuads(@Nullable IBlockState state, @Nullable EnumFacing side, long rand)
	{
		if (!isBuiltInRenderer())
			return getItemModel().getQuads(state, side, rand);
		return originalModel.getQuads(state, side, rand);
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
		return BrassKnucklesTEISR.type != ItemCameraTransforms.TransformType.GUI
				&& BrassKnucklesTEISR.type != ItemCameraTransforms.TransformType.FIXED
				&& BrassKnucklesTEISR.type != ItemCameraTransforms.TransformType.GROUND;
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

		BrassKnucklesTEISR.type = cameraTransformType;

		//GUI and itemFrame Contexts
		if (!isBuiltInRenderer())
			return ForgeHooksClient.handlePerspective(getItemModel(), cameraTransformType);

		return ForgeHooksClient.handlePerspective(this, cameraTransformType);
	}

	@Override
	@SuppressWarnings("deprecation")
	@Nonnull
	public ItemCameraTransforms getItemCameraTransforms()
	{
		ItemTransformVec3f firstpersonRight = new ItemTransformVec3f(new Vector3f(0, 280F, 50F), new Vector3f(0.15F, 0.3F, -0.3F), new Vector3f(0.4F, 0.50F, 0.4F));
		ItemTransformVec3f firstpersonLeft = new ItemTransformVec3f(new Vector3f(0, 100F, -50F), new Vector3f(0.15F, 0.3F, -0.3F), new Vector3f(0.4F, 0.50F, 0.4F));
		ItemTransformVec3f thirdpersonRight = new ItemTransformVec3f(new Vector3f(-90f, 0F, 0F), new Vector3f(0.08f, -0.11f, -0.13f), new Vector3f(0.375F, 0.375F, 0.375F));
		ItemTransformVec3f thirdpersonLeft = new ItemTransformVec3f(new Vector3f(-90f, 180F, 0F), new Vector3f(0.06f, -0.13f, -0.13f), new Vector3f(0.375F, 0.375F, 0.375F));

		return new ItemCameraTransforms(thirdpersonLeft, thirdpersonRight, firstpersonLeft, firstpersonRight, ItemTransformVec3f.DEFAULT, ItemTransformVec3f.DEFAULT, ItemTransformVec3f.DEFAULT, ItemTransformVec3f.DEFAULT);
	}

	@Nonnull
	@Override
	public ItemOverrideList getOverrides()
	{
		return originalModel.getOverrides();
	}

	private IBakedModel getItemModel()
	{
		ResourceLocation itemModel = new ResourceLocation(Metallurgy.MODID, "item/gadget/brass_knuckles_item");
		return ModelLoaderRegistry.getModelOrMissing(itemModel)
				.bake(TRSRTransformation.identity(),
						DefaultVertexFormats.ITEM, location -> Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(location.toString())
				);
	}

}
