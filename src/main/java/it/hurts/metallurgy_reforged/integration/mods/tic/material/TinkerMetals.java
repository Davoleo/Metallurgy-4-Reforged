package it.hurts.metallurgy_reforged.integration.mods.tic.material;

import it.hurts.metallurgy_reforged.material.Metal;
import it.hurts.metallurgy_reforged.material.ModMetals;
import slimeknights.tconstruct.library.materials.ExtraMaterialStats;
import slimeknights.tconstruct.library.materials.HandleMaterialStats;
import slimeknights.tconstruct.library.materials.HeadMaterialStats;

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
	
	public static MetallurgyTiCStats ALDUORITE = new MetallurgyTiCStats(ModMetals.ALDUORITE, new HeadMaterialStats(600, 14F, 7, 4), new HandleMaterialStats(0.55F, 215), new ExtraMaterialStats(40));
	public static MetallurgyTiCStats INFUSCOLIUM = new MetallurgyTiCStats(ModMetals.INFUSCOLIUM, new HeadMaterialStats(450, 25F, 6, 3), new HandleMaterialStats(0.45F, 190), new ExtraMaterialStats(15));
	public static MetallurgyTiCStats LEMURITE = new MetallurgyTiCStats(ModMetals.LEMURITE, new HeadMaterialStats(200, 10F, 8, 2), new HandleMaterialStats(0.90F, 150), new ExtraMaterialStats(30));
	public static MetallurgyTiCStats MEUTOITE = new MetallurgyTiCStats(ModMetals.MEUTOITE, new HeadMaterialStats(550, 20F, 15, 5), new HandleMaterialStats(1.5F, 250), new ExtraMaterialStats(75));
	public static MetallurgyTiCStats RUBRACIUM = new MetallurgyTiCStats(ModMetals.RUBRACIUM, new HeadMaterialStats(620, 15F, 10, 5), new HandleMaterialStats(0.75F, 200), new ExtraMaterialStats(47));
	public static MetallurgyTiCStats MIDASIUM = new MetallurgyTiCStats(ModMetals.MIDASIUM, new HeadMaterialStats(400, 10F, 6, 3), new HandleMaterialStats(0.80F, 255), new ExtraMaterialStats(45));
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
