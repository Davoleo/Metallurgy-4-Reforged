/*
 * -------------------------------------------------------------------------------------------------------
 * Class: RegistrationHandler
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2019.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.handler;

import it.hurts.metallurgy_reforged.Metallurgy;
import it.hurts.metallurgy_reforged.block.ModBlocks;
import it.hurts.metallurgy_reforged.capabilities.punch.PunchEffectProvider;
import it.hurts.metallurgy_reforged.fluid.ModFluids;
import it.hurts.metallurgy_reforged.item.ModItems;
import it.hurts.metallurgy_reforged.item.armor.ModArmors;
import it.hurts.metallurgy_reforged.item.tool.ModTools;
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.render.ModRenderers;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class RegistrationHandler {
	
	public static final ResourceLocation PUNCH_EFFECT_CAP = new ResourceLocation(Metallurgy.MODID, "punch_effect");

	
    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        ModMetals.registerItems(event.getRegistry());
        ModItems.register(event.getRegistry());
        ModBlocks.registerItemBlocks(event.getRegistry());
        ModArmors.register(event.getRegistry());
        ModTools.register(event.getRegistry());
        ModFluids.registerItem(event.getRegistry());

        //OreDict Registration
        OreDictHandler.init();
        Metallurgy.logger.info(Metallurgy.NAME + ": OreDictionary has been initialized");
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        ModMetals.registerBlocks(event.getRegistry());
        ModBlocks.register(event.getRegistry());
        ModFluids.registerBlocks(event.getRegistry());
    }

    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        ModMetals.registerModels();
        ModItems.registerModels();
        ModBlocks.registerModels();
        ModArmors.registerModels();
        ModTools.registerModels();
        ModFluids.registerModels();

        ModRenderers.registerRenderers();
    }
    @SubscribeEvent
    public static void attachCapability(AttachCapabilitiesEvent<Entity> event)
    {
        if(event.getObject() instanceof EntityLivingBase)
    	  event.addCapability(PUNCH_EFFECT_CAP, new PunchEffectProvider());
    }
}
