/*
 * -------------------------------------------------------------------------------------------------------
 * Class: TiCMaterial
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2020.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.integration.mods.tic.material;

import com.google.common.base.CaseFormat;
import it.hurts.metallurgy_reforged.material.Metal;
import slimeknights.tconstruct.library.materials.Material;

public class TiCMaterial extends Material {

	private final Metal metal;

	public TiCMaterial(Metal metal)
	{
		super(metal.getStats().getName(), metal.getMolten().getColor(), false);
		this.metal = metal;
		this.setFluid(metal.getMolten());
		this.setCastable(true);
		this.addItem(metal.getIngot());
		this.setRepresentativeItem(metal.getIngot());
		TiCMaterials.addMaterialStats(metal, this);
		addCommonItems(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, metal.getStats().getName()));
	}

	@Override
	public String getLocalizedName()
	{
		String name = metal.getStats().getName();
		String[] str = name.split("_");
		String[] space = space(str.length);
		name = "";
		for (int i = 0; i < str.length; i++)
		{
			name = name + str[i].substring(0, 1).toUpperCase() + str[i].substring(1) + space[i];
		}
		return name;
	}

	private String[] space(int len)
	{
		String[] str = new String[len];
		for (int i = 0; i < len; i++)
		{
			if (i < len - 1)
			{
				str[i] = " ";
			}
			else
				str[i] = "";
		}
		return str;
	}

}
