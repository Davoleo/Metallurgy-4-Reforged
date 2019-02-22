package it.hurts.metallurgy_reforged.util.handler;

import it.hurts.metallurgy_reforged.Metallurgy;
import it.hurts.metallurgy_reforged.block.ModBlocks;
import it.hurts.metallurgy_reforged.fluid.ModFluids;
import it.hurts.metallurgy_reforged.item.ModItems;
import it.hurts.metallurgy_reforged.item.armor.ModArmors;
import it.hurts.metallurgy_reforged.item.tool.ModTools;
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.util.capabilities.punch.PunchEffectProvider;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 21/11/2018 / 01:39
 * Class: RegistrationHandler
 * Project: Metallurgy
 * Copyright - © - Davoleo - 2018
 **************************************************/

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
    }
    @SubscribeEvent
    public static void attachCapability(AttachCapabilitiesEvent<Entity> event)
    {
        if(event.getObject() instanceof EntityLivingBase && !(event.getObject() instanceof EntityPlayer))
    	  event.addCapability(PUNCH_EFFECT_CAP, new PunchEffectProvider());
    }
}
