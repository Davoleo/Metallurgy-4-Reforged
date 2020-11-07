/*==============================================================================
 = Class: MonocleModel
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2020.
 =============================================================================*/

package it.hurts.metallurgy_reforged.entity.model;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

import javax.annotation.Nonnull;

public class MonocleModel extends ModelBiped {

	public ModelRenderer e1;

	public MonocleModel()
	{
		textureWidth = 64;
		textureHeight = 64;

		e1 = new ModelRenderer(this, 0, 52);
		e1.setRotationPoint(-5F, 27.5F, -5F);
		e1.addBox(0F, -1.5F, 0F, 10, 2, 10);
		e1.setTextureSize(64, 64);
		e1.mirror = false;
		setRotation(e1, 0F, 0F, 0F);
	}

	public void render(@Nonnull Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);

		e1.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, @Nonnull Entity entity)
	{
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	}

}
