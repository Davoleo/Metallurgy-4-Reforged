/*==============================================================================
 = Class: IntegrationSW
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2024.
 =============================================================================*/

package it.hurts.metallurgy_reforged.integration;

import com.oblivioussp.spartanweaponry.api.SpartanWeaponryAPI;
import it.hurts.metallurgy_reforged.Metallurgy;
import it.hurts.metallurgy_reforged.integration.spartanweaponry.SpartanMetal;
import it.hurts.metallurgy_reforged.integration.spartanweaponry.SpartanMetallurgyTab;
import it.hurts.metallurgy_reforged.integration.spartanweaponry.SpartanWeaponType;
import it.hurts.metallurgy_reforged.material.ModMetals;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.API;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.ArrayList;
import java.util.List;

public class IntegrationSW {

    public static final String MODID = "spartanweaponry";
    public static final API API = Package.getPackage("com.oblivioussp.spartanweaponry.api").getAnnotation(API.class);

    public static final SpartanMetallurgyTab CREATIVE_TAB = new SpartanMetallurgyTab();

    public static final List<SpartanMetal> spartanMetals = new ArrayList<>();

    public static void registerItems(IForgeRegistry<Item> registry) {

        ModMetals.metalMap.forEach((name, metal) -> {
            if (metal.hasToolSet()) {
                spartanMetals.add(new SpartanMetal(registry, metal.getStats()));
            }
        });

        spartanMetals.forEach(spartanMetal -> {
            SpartanWeaponryAPI.registerColourHandler(
                    spartanMetal.getMaterialEx(),
                    spartanMetal.getItems().values().toArray(new Item[0])
            );

            for (SpartanWeaponType weaponType : SpartanWeaponType.values()) {
                if (weaponType.initializer != null) {
                    SpartanWeaponryAPI.addItemModelToRegistry(spartanMetal.getWeapon(weaponType),
                            Metallurgy.MODID, IntegrationSW.MODID + '/' + weaponType.name().toLowerCase());
                }
            }
        });
    }
}
