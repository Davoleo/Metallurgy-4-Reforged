/*==============================================================================
 = Class: MetallurgyTNTRenderer
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2022.
 =============================================================================*/

package it.hurts.metallurgy_reforged.render;

import it.hurts.metallurgy_reforged.block.ModBlocks;
import it.hurts.metallurgy_reforged.entity.MetallurgyPrimedTNT;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public class MetallurgyTNTRenderer extends Render<MetallurgyPrimedTNT> {

	public MetallurgyTNTRenderer(RenderManager renderManager)
	{
		super(renderManager);
		this.shadowSize = 0.5F;
	}

	@Override
	public void doRender(@Nonnull MetallurgyPrimedTNT entity, double x, double y, double z, float entityYaw, float partialTicks)
	{
		BlockRendererDispatcher blockRenderer = Minecraft.getMinecraft().getBlockRendererDispatcher();
		GlStateManager.pushMatrix();
		GlStateManager.translate((float) x, (float) y + 0.5F, (float) z);

		if ((float) entity.getFuse() - partialTicks + 1.0F < 10.0F)
		{
			float f = 1.0F - ((float) entity.getFuse() - partialTicks + 1.0F) / 10.0F;
			f = MathHelper.clamp(f, 0.0F, 1.0F);
			f = f * f;
			f = f * f;
			float f1 = 1.0F + f * 0.3F;
			GlStateManager.scale(f1, f1, f1);
		}

		float f2 = (1.0F - ((float) entity.getFuse() - partialTicks + 1.0F) / 100.0F) * 0.8F;
		this.bindEntityTexture(entity);
		GlStateManager.rotate(-90F, 0F, 1F, 0F);
		GlStateManager.translate(-0.5F, -0.5F, 0.5F);
		blockRenderer.renderBlockBrightness(ModBlocks.VTNTBlock.getDefaultState(), entity.getBrightness());
		GlStateManager.translate(0.0F, 0.0F, 1.0F);

		if (this.renderOutlines)
		{
			GlStateManager.enableColorMaterial();
			GlStateManager.enableOutlineMode(this.getTeamColor(entity));
			blockRenderer.renderBlockBrightness(ModBlocks.VTNTBlock.getDefaultState(), 1.0F);
			GlStateManager.disableOutlineMode();
			GlStateManager.disableColorMaterial();
		}
		else if (entity.getFuse() / 5 % 2 == 0)
		{
			GlStateManager.disableTexture2D();
			GlStateManager.disableLighting();
			GlStateManager.enableBlend();
			GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.DST_ALPHA);
			GlStateManager.color(1.0F, 1.0F, 1.0F, f2);
			GlStateManager.doPolygonOffset(-3.0F, -3.0F);
			GlStateManager.enablePolygonOffset();
			blockRenderer.renderBlockBrightness(ModBlocks.VTNTBlock.getDefaultState(), 1.0F);
			GlStateManager.doPolygonOffset(0.0F, 0.0F);
			GlStateManager.disablePolygonOffset();
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			GlStateManager.disableBlend();
			GlStateManager.enableLighting();
			GlStateManager.enableTexture2D();
		}

		GlStateManager.popMatrix();
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
	}

	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(@Nonnull MetallurgyPrimedTNT entity)
	{
		return TextureMap.LOCATION_BLOCKS_TEXTURE;
	}

}
