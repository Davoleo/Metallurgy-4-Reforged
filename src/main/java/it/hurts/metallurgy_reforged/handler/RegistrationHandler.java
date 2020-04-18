/*
 * -------------------------------------------------------------------------------------------------------
 * Class: RegistrationHandler
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2020.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.handler;

import it.hurts.metallurgy_reforged.Metallurgy;
import it.hurts.metallurgy_reforged.block.BlockMetal;
import it.hurts.metallurgy_reforged.block.ModBlocks;
import it.hurts.metallurgy_reforged.capabilities.krik.KrikEffectProvider;
import it.hurts.metallurgy_reforged.capabilities.punch.PunchEffectProvider;
import it.hurts.metallurgy_reforged.item.ModItems;
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.render.ModRenderers;
import it.hurts.metallurgy_reforged.util.ItemUtils;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber
public class RegistrationHandler {

	public static final ResourceLocation PUNCH_EFFECT_CAP = new ResourceLocation(Metallurgy.MODID, "punch_effect");
	public static final ResourceLocation KRIK_EFFECT_CAPABILITY = new ResourceLocation(Metallurgy.MODID, "krik_effect");

	public static List<TextureAtlasSprite> oreParticles = new ArrayList<>();


	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event)
	{
		//Misc Itemblocks
		for (Block block : ModBlocks.miscBlocks)
		{
			event.getRegistry().register(ModBlocks.createItemBlock(block));
		}

		ModMetals.metalMap.forEach((s, metal) -> {
			//Ore ItemBlocks
			if (metal.getOre() != null)
			{
				event.getRegistry().register(ModBlocks.createItemBlock(metal.getOre()));
			}

			//Metal ItemBlocks
			for (BlockMetal block : metal.getBlocks())
			{
				event.getRegistry().register(ModBlocks.createItemBlock(block));
			}

			//Items
			event.getRegistry().register(metal.getIngot());
			event.getRegistry().register(metal.getDust());
			event.getRegistry().register(metal.getNugget());

			//Tools
			for (Item tool : metal.getToolSet())
			{
				event.getRegistry().register(tool);
			}

			//Armors
			for (Item armor : metal.getArmorSet())
			{
				event.getRegistry().register(armor);
			}
		});

		//Misc Items
		ModItems.itemList.forEach(item -> {
			event.getRegistry().register(item);
		});

		//OreDict Registration
		OreDictHandler.init();
		Metallurgy.logger.info(Metallurgy.NAME + ": OreDictionary has been initialized");
	}

	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event)
	{
		//Misc Blocks
		for (Block block : ModBlocks.miscBlocks)
		{
			event.getRegistry().register(block);
		}

		ModMetals.metalMap.forEach((s, metal) -> {

			//Ore Blocks
			if (metal.getOre() != null)
			{
				event.getRegistry().register(metal.getOre());
			}

			//Metal Blocks and Deco
			for (BlockMetal block : metal.getBlocks())
			{
				event.getRegistry().register(block);
			}


			metal.getMolten().getBlock();
		});

		// TODO: 30/03/2020 For each block: register the Fluid (FluidRegistry), the FluidBlock, register the FluidItemBlock, register the Model
	}

	@SubscribeEvent
	public static void registerModels(ModelRegistryEvent event)
	{
		for (Block block : ModBlocks.miscBlocks)
		{
			ItemUtils.registerCustomItemModel(Item.getItemFromBlock(block), 0);
		}

		ModItems.registerModels();
		//ModArmors.registerModels();
		//ModTools.registerModels();

		ModRenderers.registerRenderers();
	}

	@SubscribeEvent
	public static void attachCapability(AttachCapabilitiesEvent<Entity> event)
	{
		if (event.getObject() instanceof EntityLivingBase)
			event.addCapability(PUNCH_EFFECT_CAP, new PunchEffectProvider());

		if (event.getObject() instanceof EntityPlayer)
		{
			event.addCapability(KRIK_EFFECT_CAPABILITY, new KrikEffectProvider());
		}
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public static void registerTextures(TextureStitchEvent.Pre event)
	{
		for (int i = 1; i <= 5; i++)
			oreParticles.add(event.getMap().registerSprite(new ResourceLocation(Metallurgy.MODID, "particles/ore_particle_" + i)));
	}

}
