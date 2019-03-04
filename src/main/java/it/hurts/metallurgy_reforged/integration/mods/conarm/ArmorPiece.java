/**
 * 
 */
package it.hurts.metallurgy_reforged.integration.mods.conarm;

import c4.conarm.common.ConstructsRegistry;
import c4.conarm.lib.armor.ArmorCore;
import c4.conarm.lib.armor.ArmorPart;
import c4.conarm.lib.materials.ArmorMaterialType;
import c4.conarm.lib.tinkering.TinkersArmor;
import net.minecraft.inventory.EntityEquipmentSlot;
import slimeknights.tconstruct.library.tinkering.PartMaterialType;

/***************************
 *
 * Author : ItHurtsLikeHell
 * Project: Metallurgy-4-Reforged
 * Date   : 4 mar 2019
 * Time   : 02:38:34
 *
 ****************************/
public class ArmorPiece extends ArmorCore{
	
	/**
	 * @param slotIn
	 * @param appearanceName
	 * @param enumcon
	 */
	public ArmorPiece(EntityEquipmentSlot slotIn, String appearanceName, PartMaterialType enumcon) {
		super(slotIn, appearanceName, enumcon);
		// TODO Auto-generated constructor stub
	}

}
