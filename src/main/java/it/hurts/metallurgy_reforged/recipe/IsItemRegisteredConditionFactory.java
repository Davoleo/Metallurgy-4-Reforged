/*==============================================================================
 = Class: IsItemRegisteredConditionFactory
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.recipe;

import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import it.hurts.metallurgy_reforged.block.BlockTypes;
import it.hurts.metallurgy_reforged.config.RegistrationConfig;
import it.hurts.metallurgy_reforged.item.ItemTypes;
import it.hurts.metallurgy_reforged.item.tool.EnumTools;
import net.minecraft.util.JsonUtils;
import net.minecraftforge.common.crafting.IConditionFactory;
import net.minecraftforge.common.crafting.JsonContext;

import java.util.function.BooleanSupplier;

@SuppressWarnings("unused")
public class IsItemRegisteredConditionFactory implements IConditionFactory {

    @Override
    public BooleanSupplier parse(JsonContext context, JsonObject json)
    {
        String dependsOn = JsonUtils.getString(json, "depends_on");

        if (dependsOn.startsWith("block/"))
        {
            dependsOn = dependsOn.replaceFirst("block/", "");
            for (BlockTypes type : BlockTypes.values())
            {
                if (dependsOn.equals(type.getPrefix()))
                    return type::isEnabled;
            }
        }
        else if (dependsOn.startsWith("item/"))
        {
            dependsOn = dependsOn.replaceFirst("item/", "");
            for (ItemTypes type : ItemTypes.values())
            {
                if (dependsOn.equals(type.getName()))
                    return type::isEnabled;
            }

            //if we're still here the only item type that is left are armor sets
            return () -> RegistrationConfig.categoryItems.enableMetalArmorSets;
        }
        else if (dependsOn.startsWith("tool/"))
        {
            for (EnumTools tool : EnumTools.values())
            {
                if (dependsOn.equals(tool.getName()))
                    return tool::isEnabled;
            }
        }

        throw new JsonParseException(String.format("Error in %s Recipe conditions are wrong: %s", context.getModId(), '"' + dependsOn + '"'));
    }
}
