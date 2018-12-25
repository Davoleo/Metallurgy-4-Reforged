package it.hurts.metallurgy_reforged.integration.mods.tic.material;

import it.hurts.metallurgy_reforged.material.Metal;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

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
 * 		' public static MetallurgyTiCStats NAME = new MetallurgyTiCStats(ModMetals.NAME); '
 * 
 * To add a single part
 * 		' public static MetallurgyTiCStats NAME = new MetallurgyTiCStats(ModMetals.NAME, new HeadMaterialStats / HandleMaterialStats / ExtraMaterialStats '
 * 
 * To add a single part automatically
 *  	' public static MetallurgyTiCStats NAME = new MetallurgyTiCStats(ModMetals.NAME,  MetallurgyTiCStats.getHeadA / getExtraA / getHandleA (ModMetals.NAME)); '
 */

public class TinkerMetals {
	public static List<MetallurgyTiCStats> metalStatsList = new ArrayList<>();

//	 ++ Test ++
//	 public static MetallurgyTiCStats ALDUORITE = new MetallurgyTiCStats(ModMetals.ALDUORITE, new HeadMaterialStats(1, 1, 1, 1),new HandleMaterialStats(1, 1));
//	 public static MetallurgyTiCStats ADAMANTINE = new MetallurgyTiCStats(ModMetals.ADAMANTINE, new HandleMaterialStats(1, 1), MetallurgyTiCStats.getHeadA(ModMetals.ADAMANTINE));
//	 public static MetallurgyTiCStats ANGMALLEN = new MetallurgyTiCStats(ModMetals.ANGMALLEN);

	 @Nullable
	 public static MetallurgyTiCStats getMetal(Metal metal)
	 {
		 for(MetallurgyTiCStats stat : metalStatsList)
		 {
			 if(stat.metal.equals(metal))
				 return stat;
		 }
		 return null;
	 }
}
