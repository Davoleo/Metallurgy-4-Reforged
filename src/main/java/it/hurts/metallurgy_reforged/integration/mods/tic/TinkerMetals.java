package it.hurts.metallurgy_reforged.integration.mods.tic;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import it.hurts.metallurgy_reforged.material.Metal;
import it.hurts.metallurgy_reforged.material.ModMetals;
import slimeknights.tconstruct.library.materials.HandleMaterialStats;
import slimeknights.tconstruct.library.materials.HeadMaterialStats;

/***************************
 *
 * Author : ItHurtsLikeHell, PierKnight100
 * Project: Metallurgy-5
 * Date   : 23 dic 2018
 * Time   : 19:41:33
 *
 ***************************/

/*	## INFO ##
 * 
 * To delete all tool parts :
 * 		' public static MetallurgyTinkerStats NAME = new MetallurgyTinkerStats(ModMetals.NAME); '
 * 
 * To add a single part
 * 		' public static MetallurgyTinkerStats NAME = new MetallurgyTinkerStats(ModMetals.NAME, new HeadMaterialStats / HandleMaterialStats / ExtraMaterialStats '
 * 
 * To add a single part automatically
 *  	' public static MetallurgyTinkerStats NAME = new MetallurgyTinkerStats(ModMetals.NAME,  MetallurgyTinkerStats.getHeadA / getExtraA / getHandleA (ModMetals.NAME)); '
 */

public class TinkerMetals {
	public static List<MetallurgyTinkerStats> metalStatsList = new ArrayList<>();

//	 ++ Test ++
	 public static MetallurgyTinkerStats ALDUORITE = new MetallurgyTinkerStats(ModMetals.ALDUORITE, new HeadMaterialStats(1, 1, 1, 1),new HandleMaterialStats(1, 1));
	 public static MetallurgyTinkerStats ADAMANTINE = new MetallurgyTinkerStats(ModMetals.ADAMANTINE, new HandleMaterialStats(1, 1), MetallurgyTinkerStats.getHeadA(ModMetals.ADAMANTINE));
	 
	 public static MetallurgyTinkerStats ANGMALLEN = new MetallurgyTinkerStats(ModMetals.ANGMALLEN);

	 @Nullable
	 public static MetallurgyTinkerStats getMetal(Metal metal)
	 {
		 for(MetallurgyTinkerStats stat : metalStatsList)
		 {
			 if(stat.metal.equals(metal))
				 return stat;
		 }
		 return null;
	 }
}
