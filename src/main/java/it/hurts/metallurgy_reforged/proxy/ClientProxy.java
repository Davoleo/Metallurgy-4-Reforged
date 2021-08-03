/*==============================================================================
 = Class: ClientProxy
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.proxy;

import it.hurts.metallurgy_reforged.gui.hud.HUDHandler;
import it.hurts.metallurgy_reforged.handler.KeyboardHandler;
import it.hurts.metallurgy_reforged.item.ModItems;
import it.hurts.metallurgy_reforged.item.gadget.ItemOreDetector;
import it.hurts.metallurgy_reforged.material.Metal;
import it.hurts.metallurgy_reforged.sound.ModSounds;
import it.hurts.metallurgy_reforged.util.ModChecker;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.util.List;

@SuppressWarnings("unused")
public class ClientProxy implements IProxy {

	@Override
	public void preInit(FMLPreInitializationEvent e)
	{
        if (ModChecker.isTConLoaded)
            it.hurts.metallurgy_reforged.integration.tic.material.TiCMaterials.initializeRenderInfos();

        MinecraftForge.EVENT_BUS.register(KeyboardHandler.class);
        MinecraftForge.EVENT_BUS.register(HUDHandler.class);
        ModItems.BRASS_KNUCKLES.initTEISR();
    }

	@Override
	public void init(FMLInitializationEvent event)
	{
		//Metal Detector Model metal colors
        Minecraft.getMinecraft().getItemColors().registerItemColorHandler((stack, tintIndex) -> {
            List<Metal> metals = ItemOreDetector.getDetectorMetals(stack);

            if (tintIndex < metals.size())
            {
	            int color = metals.get(tintIndex).getStats().getColorHex();

	            if (ModItems.ORE_DETECTOR.isLEDLit(tintIndex))
	            {
		            if (Minecraft.getMinecraft().world.getTotalWorldTime() % 10 == 5)
			            Minecraft.getMinecraft().player.playSound(ModSounds.METAL_DETECTOR_BEEPS[tintIndex], 1F, 1F);

		            if (Minecraft.getMinecraft().world.getTotalWorldTime() % 10 > 4)
			            return color ^ 0xFFFFFF;
		            else
			            return color;
	            }
	            else
		            return color;
            }
            return -1;

        }, ModItems.ORE_DETECTOR);
	}


}
