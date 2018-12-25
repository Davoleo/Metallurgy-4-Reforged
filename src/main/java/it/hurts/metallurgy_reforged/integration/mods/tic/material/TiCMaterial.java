package it.hurts.metallurgy_reforged.integration.mods.tic.material;

import it.hurts.metallurgy_reforged.material.Metal;
import slimeknights.tconstruct.library.materials.Material;

/***************************
*
* Author : ItHurtsLikeHell
* Helper : PierKnight100
* Project: Metallurgy-5
* Date   : 13 dic 2018
* Time   : 18:33:14
*
***************************/

public class TiCMaterial extends Material{

	private final Metal metal;
	
	public TiCMaterial(Metal metal) {
		super(metal.getStats().getName(), metal.getMolten().getColor(), false);
		this.metal = metal;
		this.setFluid(metal.getMolten());
		this.setCastable(true);
		TiCMaterials.addMaterialStats(metal, this);
		addCommonItems((metal.getStats().getOreDictName()));
	}

	@Override
	public String getLocalizedName() {
		String name = metal.getStats().getName();
		String[] str = name.split("_");
		String[] space = space(str.length);
		name = "";
		for(int i = 0; i < str.length; i++) {
			name = name + str[i].substring(0, 1).toUpperCase() + str[i].substring(1,str[i].length()) + space[i];
		}
		return name;
	}
	
	private String[] space(int len) {
		String[] str = new String [len];
		for(int i = 0; i < len; i++) {
			if(i < len-1) {
				str[i] = " ";
			}else
				str[i] = "";
		}
		return str;
	}
}
