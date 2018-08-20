package hurts.metallurgy_5.item.tool;

import hurts.metallurgy_5.Metallurgy_5;
import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;

/*************************************************
 * Author: Davoleo
 * Date: 20/08/2018
 * Hour: 13.40
 * Project: Metallurgy 5
 * Copyright - © - Davoleo - 2018
 **************************************************/

public class ModTools {

    public static ItemAxeBase adamantine_axe = new ItemAxeBase(Metallurgy_5.adamantineToolMaterial, "adamantine_axe");
    public static ItemHoeBase adamantine_hoe = new ItemHoeBase(Metallurgy_5.adamantineToolMaterial, "adamantine_hoe");
    public static ItemPickaxeBase adamantine_pickaxe = new ItemPickaxeBase(Metallurgy_5.adamantineToolMaterial, "adamantine_pickaxe");
    public static ItemShovelBase adamantine_shovel = new ItemShovelBase(Metallurgy_5.adamantineToolMaterial, "adamantine_shovel");
    public static ItemSwordBase adamantine_sword = new ItemSwordBase(Metallurgy_5.adamantineToolMaterial, "adamantine_sword");


    public static void register(IForgeRegistry<Item> registry)
    {
        registry.registerAll(
                adamantine_axe,
                adamantine_hoe,
                adamantine_pickaxe,
                adamantine_shovel,
                adamantine_sword
        );
    }


    public static void registerModels()
    {
        adamantine_axe.registerItemModel(adamantine_axe, 0);
        adamantine_hoe.registerItemModel(adamantine_hoe, 0);
        adamantine_pickaxe.registerItemModel(adamantine_pickaxe, 0);
        adamantine_shovel.registerItemModel(adamantine_shovel, 0);
        adamantine_sword.registerItemModel(adamantine_sword, 0);
    }
}