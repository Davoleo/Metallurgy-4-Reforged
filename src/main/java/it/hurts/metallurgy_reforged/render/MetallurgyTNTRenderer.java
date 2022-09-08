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

		//100 to 0
		float fuse = (float) entity.getFuse() - partialTicks + 1F;

		//TNT about to explode
		if (fuse < 10F)
		{
			float invF = 1F - fuse / 10F;
			invF = MathHelper.clamp(invF, 0.0F, 1.0F);
			invF = invF * invF;
			invF = invF * invF;

			//TNT about to explode so it becomes BIG
			float f1 = 1.0F + invF * 0.3F;
			GlStateManager.scale(f1, f1, f1);
		}

		//fuse 0 to 1 (-20%)
		float f2 = (1.0F - fuse / 100.0F) * 0.8F;

		this.bindEntityTexture(entity);
		GlStateManager.rotate(-90F, 0F, 1F, 0F);
		GlStateManager.translate(-0.5F, -0.5F, 0.5F);
		blockRenderer.renderBlockBrightness(ModBlocks.VTNTBlock.getDefaultState(), entity.getBrightness());
		GlStateManager.translate(0.0F, 0.0F, 1.0F);

		//no idea what this renderOutlines is used for, nor where it's used, so I'll leave it there [see vanilla TNT]
		if (this.renderOutlines)
		{
			GlStateManager.enableColorMaterial();
			GlStateManager.enableOutlineMode(this.getTeamColor(entity));
			blockRenderer.renderBlockBrightness(ModBlocks.VTNTBlock.getDefaultState(), 1.0F);
			GlStateManager.disableOutlineMode();
			GlStateManager.disableColorMaterial();
		}
		else //if (entity.getFuse() / 5 % 2 == 0) <== Vanilla tnt flashing
		{
			//disabling texture2D makes the overlay go completely white instead of making the block texture brighter
			//GlStateManager.disableTexture2D();
			GlStateManager.disableLighting();
			//blend allows merging between TNT texture and white overlay via alpha
			GlStateManager.enableBlend();
			GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.DST_ALPHA);
			GlStateManager.color(1F, 1F, 1F, f2);
			//polygon offset functions seems to avoid weird z-index multilayer style glitching
			GlStateManager.doPolygonOffset(-3F, -3F);
			GlStateManager.enablePolygonOffset();


			float brightness;
			if (fuse < 50)
				brightness = MathHelper.cos(fuse / 2F) * 0.5F + 0.5F;
			else
				brightness = MathHelper.cos(fuse / 4F) * 0.5F + 0.5F;

			//Renders the actual white overlay on the block [1F means completely white]
			blockRenderer.renderBlockBrightness(ModBlocks.VTNTBlock.getDefaultState(), brightness);
			GlStateManager.doPolygonOffset(0F, 0F);
			GlStateManager.disablePolygonOffset();
			GlStateManager.color(1F, 1F, 1F, 1F);
			GlStateManager.disableBlend();
			GlStateManager.enableLighting();
			//GlStateManager.enableTexture2D();
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
