/*
 * -------------------------------------------------------------------------------------------------------
 * Class: ModTools
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2019.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.item.tool;

import it.hurts.metallurgy_reforged.config.GeneralConfig;
import it.hurts.metallurgy_reforged.config.ToolConfig;
import it.hurts.metallurgy_reforged.util.IHasModel;
import it.hurts.metallurgy_reforged.util.ItemUtils;
import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.ArrayList;
import java.util.List;

public class ModTools {

    public static final List<Item> toolList = new ArrayList<>();

    public static void register(IForgeRegistry<Item> registry){

        if (!GeneralConfig.disableAllTools) {

            int c = 0;
            for (int i = 0; i < ToolConfig.allTools.length; i++) {

                for (int j = 0; j < 5; j++) {

                    if (ToolConfig.allTools[i][j])
                    {
                        Item tool = toolList.get(c);
                        registry.register(tool);

                        EnumToolEffects effect = EnumToolEffects.getEffect(tool);
                        if (effect != null)
                        {
                            if (tool instanceof ItemAxeBase)
                                ((ItemAxeBase) tool).setEffect(effect);
                            if (tool instanceof ItemPickaxeBase)
                                ((ItemPickaxeBase) tool).setEffect(effect);
                            if (tool instanceof ItemSwordBase)
                                ((ItemSwordBase) tool).setEffect(effect);
                            if (tool instanceof ItemShovelBase)
                                ((ItemShovelBase) tool).setEffect(effect);
                        }
                    }

                    c++;
                }
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public static void registerModels()
    {
        if (!GeneralConfig.disableAllTools)
        {
            int c = 0;

            for (int i = 0; i < ToolConfig.allTools.length; i++)
            {
                for (int j = 0; j < 5; j++)
                {
                    IHasModel item = (IHasModel) toolList.get(c);

                    if (ToolConfig.allTools[i][j])
                        ItemUtils.registerCustomItemModel((Item) item, 0, item.getCategory());

                    c++;

                }
            }
        }
    }
}