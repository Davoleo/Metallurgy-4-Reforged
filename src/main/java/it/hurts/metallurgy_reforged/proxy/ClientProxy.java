/*==============================================================================
 = Class: ClientProxy
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2020.
 =============================================================================*/

package it.hurts.metallurgy_reforged.proxy;

import it.hurts.metallurgy_reforged.gui.TooltipRenderHandler;
import it.hurts.metallurgy_reforged.gui.hud.HUDHandler;
import it.hurts.metallurgy_reforged.handler.KeyboardHandler;
import it.hurts.metallurgy_reforged.item.ModItems;
import it.hurts.metallurgy_reforged.item.gadget.ItemOreDetector;
import it.hurts.metallurgy_reforged.material.Metal;
import it.hurts.metallurgy_reforged.render.font.ModFontRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.IReloadableResourceManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.util.List;

@SuppressWarnings("unused")
public class ClientProxy implements IProxy {

    public static ModFontRenderer fontRenderer;
    public static Minecraft client = Minecraft.getMinecraft();

    @Override
    public void preInit(FMLPreInitializationEvent e)
    {
        MinecraftForge.EVENT_BUS.register(KeyboardHandler.class);
        MinecraftForge.EVENT_BUS.register(HUDHandler.class);
        MinecraftForge.EVENT_BUS.register(TooltipRenderHandler.class);
        ModItems.brassKnuckles.initTEISR();
    }

    @Override
    public void init(FMLInitializationEvent event)
    {
        client.getItemColors().registerItemColorHandler((stack, tintIndex) -> {
            List<Metal> metals = ItemOreDetector.getDetectorMetals(stack);

            if (tintIndex < metals.size())
            {
                return metals.get(tintIndex).getStats().getColorHex();
            }
            return -1;

        }, ModItems.oreDetector);
    }

    @Override
    public void postInit(FMLPostInitializationEvent event)
    {

        //Load custom Font Renderer
        IReloadableResourceManager resourceManager = (IReloadableResourceManager) client.getResourceManager();

        fontRenderer = new ModFontRenderer(client.gameSettings,
                new ResourceLocation("textures/font/ascii.png"), client.renderEngine);

        if (client.gameSettings.language != null)
        {
            fontRenderer.setUnicodeFlag(client.getLanguageManager().isCurrentLocaleUnicode() || client.gameSettings.forceUnicodeFont);
            fontRenderer.setBidiFlag(client.getLanguageManager().isCurrentLanguageBidirectional());
        }
        resourceManager.registerReloadListener(fontRenderer);
    }
}
