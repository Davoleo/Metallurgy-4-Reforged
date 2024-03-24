/*==============================================================================
 = Class: SpartanWeaponTypes
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2024.
 =============================================================================*/

package it.hurts.metallurgy_reforged.integration.spartanweaponry;

import com.oblivioussp.spartanweaponry.api.SpartanWeaponryAPI;
import it.hurts.metallurgy_reforged.integration.IntegrationSW;


public enum SpartanWeaponType {
    DAGGER(SpartanWeaponryAPI::createDagger),
    LONGSWORD(SpartanWeaponryAPI::createLongsword),
    KATANA(SpartanWeaponryAPI::createKatana),
    SABER(SpartanWeaponryAPI::createSaber),
    RAPIER(SpartanWeaponryAPI::createRapier),
    GREATSWORD(SpartanWeaponryAPI::createGreatsword),
    HAMMER(SpartanWeaponryAPI::createHammer),
    WARHAMMER(SpartanWeaponryAPI::createWarhammer),
    SPEAR(SpartanWeaponryAPI::createSpear),
    HALBERD(SpartanWeaponryAPI::createHalberd),
    PIKE(SpartanWeaponryAPI::createPike),
    LANCE(SpartanWeaponryAPI::createLance),
    THROWING_KNIFE(SpartanWeaponryAPI::createThrowingKnife),
    THROWING_AXE(SpartanWeaponryAPI::createThrowingAxe),
    JAVELIN(SpartanWeaponryAPI::createJavelin),
    BOOMERANG(SpartanWeaponryAPI::createBoomerang),
    BATTLEAXE(SpartanWeaponryAPI::createBattleaxe),
    MACE(SpartanWeaponryAPI::createMace),
    GLAIVE(SpartanWeaponryAPI::createGlaive),
    QUARTERSTAFF(SpartanWeaponryAPI::createQuarterstaff),
    PARRYING_DAGGER(IntegrationSW.API.apiVersion().equals("6") ? SpartanWeaponryAPI::createParryingDagger : null),
    //Ranged
    LONGBOW((toolMaterialEx, modid, creativeTab, properties) -> SpartanWeaponryAPI.createLongbow(toolMaterialEx, modid, creativeTab, IntegrationSW.NOOP)),
    CROSSBOW((toolMaterialEx, modid, creativeTab, properties) -> SpartanWeaponryAPI.createCrossbow(toolMaterialEx, modid, creativeTab, IntegrationSW.NOOP)),
    ;
    public final ISpartanWeaponInitalizer initializer;

    SpartanWeaponType(ISpartanWeaponInitalizer initializer) {
        this.initializer = initializer;
    }
}
