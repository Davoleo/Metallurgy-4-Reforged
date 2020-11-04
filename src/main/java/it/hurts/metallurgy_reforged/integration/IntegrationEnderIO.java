package it.hurts.metallurgy_reforged.integration;

import it.hurts.metallurgy_reforged.util.Utils;
import net.minecraftforge.fml.common.event.FMLInterModComms;

public class IntegrationEnderIO {

    private static final String DEFAULT_XML = "/assets/metallurgy/recipes/metallurgy_enderio_alloys.xml";

    public static void init(String recipeXMLPath)
    {
        Utils.copyFile(Utils.getPath(DEFAULT_XML), recipeXMLPath);
        FMLInterModComms.sendMessage("enderio", "recipe:xml:file", recipeXMLPath);
    }

}
