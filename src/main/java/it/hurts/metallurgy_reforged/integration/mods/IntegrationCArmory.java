/**
 * 
 */
package it.hurts.metallurgy_reforged.integration.mods;

import java.util.List;

import c4.conarm.common.ConstructsRegistry;
import c4.conarm.lib.armor.ArmorCore;
import c4.conarm.lib.armor.ArmorPart;
import c4.conarm.lib.materials.ArmorMaterialType;
import c4.conarm.lib.materials.CoreMaterialStats;
import c4.conarm.lib.materials.PlatesMaterialStats;
import c4.conarm.lib.materials.TrimMaterialStats;
import c4.conarm.lib.tinkering.TinkersArmor;
import it.hurts.metallurgy_reforged.integration.mods.conarm.ArmorPiece;
import it.hurts.metallurgy_reforged.integration.mods.conarm.MetallurgyConArmorStats;
import it.hurts.metallurgy_reforged.integration.mods.tic.material.TiCMaterial;
import it.hurts.metallurgy_reforged.material.Metal;
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.util.Utils;
import net.minecraft.inventory.EntityEquipmentSlot;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.materials.Material;

/***************************
 *
 * Author : ItHurtsLikeHell
 * Project: Metallurgy-4-Reforged
 * Date   : 3 mar 2019
 * Time   : 19:39:05
 *
 ****************************/
public class IntegrationCArmory {
	
//	Bisognerà utilizzare questa classe per avere la possibilità di craftare le armor?  public ArmorCore
	private static final EntityEquipmentSlot[] enumSlot = { EntityEquipmentSlot.HEAD, EntityEquipmentSlot.CHEST, EntityEquipmentSlot.LEGS, EntityEquipmentSlot.FEET };
	private static final ArmorPart[] enumCon = { ConstructsRegistry.helmetCore, ConstructsRegistry.chestCore, ConstructsRegistry.leggingsCore, ConstructsRegistry.bootsCore };
	
	public static void preInit() {
		Material.UNKNOWN.addStats(new TrimMaterialStats(0));
        Material.UNKNOWN.addStats(new CoreMaterialStats(0, 0));
        Material.UNKNOWN.addStats(new PlatesMaterialStats(1, 0, 0));
		
		for (Metal metal : ModMetals.metalList) {
			if ((IntegrationTIC.checkMaterial(metal) && !IntegrationTIC.checkMaterialPreInit(metal)) && metal.getStats().getArmorStats() != null) {
				TiCMaterial material = new TiCMaterial(metal);
				String name = metal.getStats().getName();
				
				TinkerRegistry.addMaterialStats(material, 
						MetallurgyConArmorStats.getCoreStats(metal), 
						MetallurgyConArmorStats.getPlatesStats(metal),
						MetallurgyConArmorStats.getTrimStats(metal));
				
//				Questo non va
					for(int i = 0; i < enumSlot.length; i++) {
						System.out.println("QUI IDIOTA " + enumSlot[i].getName() + " " + name);
						ArmorPiece a = new ArmorPiece(enumSlot[i], name, ArmorMaterialType.core(enumCon[i]));
					}
			}
		}
	}
	
	public static void init() {
		
	}
	
}
