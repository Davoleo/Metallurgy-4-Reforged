/*==============================================================================
 = Class: TiCMaterial
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.integration.tic.material;

import com.google.common.base.CaseFormat;
import it.hurts.metallurgy_reforged.material.Metal;
import net.minecraftforge.fml.common.thread.SidedThreadGroups;
import slimeknights.tconstruct.library.materials.Material;

public class TiCMaterial extends Material {

	private final Metal metal;

	public TiCMaterial(Metal metal)
	{
		super(metal.toString(), metal.getMolten().getColor(), false);
		this.metal = metal;

		this.setFluid(metal.getMolten());
		this.setCastable(true);
		this.addCommonItems(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, metal.toString()));
		this.setRepresentativeItem("ingot" + CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, metal.toString()));
		if (Thread.currentThread().getThreadGroup() == SidedThreadGroups.CLIENT)
			this.setRenderInfo(TiCMaterials.renderInfos.get(metal));

		TiCMaterials.addMaterialStats(metal, this);
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
