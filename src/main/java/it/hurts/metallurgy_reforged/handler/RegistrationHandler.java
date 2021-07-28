/*==============================================================================
 = Class: RegistrationHandler
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.handler;

import it.hurts.metallurgy_reforged.Metallurgy;
import it.hurts.metallurgy_reforged.block.BlockMetal;
import it.hurts.metallurgy_reforged.block.BlockTypes;
import it.hurts.metallurgy_reforged.block.ModBlocks;
import it.hurts.metallurgy_reforged.capabilities.entity.EntityDataProvider;
import it.hurts.metallurgy_reforged.capabilities.krik.KrikEffectProvider;
import it.hurts.metallurgy_reforged.capabilities.punch.PunchEffectProvider;
import it.hurts.metallurgy_reforged.config.RegistrationConfig;
import it.hurts.metallurgy_reforged.fluid.ModFluids;
import it.hurts.metallurgy_reforged.item.ModItems;
import it.hurts.metallurgy_reforged.item.tool.EnumTools;
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.render.BrassKnucklesBakedModel;
import it.hurts.metallurgy_reforged.render.ModRenderers;
import it.hurts.metallurgy_reforged.sound.ModSounds;
import it.hurts.metallurgy_reforged.util.ItemUtils;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod.EventBusSubscriber
public class RegistrationHandler {

	public static final ResourceLocation PUNCH_EFFECT_CAP = new ResourceLocation(Metallurgy.MODID, "punch_effect");
	public static final ResourceLocation KRIK_EFFECT_CAPABILITY = new ResourceLocation(Metallurgy.MODID, "krik_effect");
	public static final ResourceLocation ENTITY_DATA_CAPABILITY = new ResourceLocation(Metallurgy.MODID, "entity_data");

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
			for (BlockTypes type : BlockTypes.values())
			{
				if (type.isEnabled())
					event.getRegistry().register(ModBlocks.createItemBlock(metal.getBlock(type)));
			}

			//Items
			event.getRegistry().register(metal.getIngot());
			if (RegistrationConfig.categoryItems.enableMetalDusts)
				event.getRegistry().register(metal.getDust());
			if (RegistrationConfig.categoryItems.enableMetalNuggets)
				event.getRegistry().register(metal.getNugget());

			//Tools
			if (metal.getToolSet() != null)
			{
				for (EnumTools toolType : EnumTools.values())
				{
					if (toolType.isEnabled())
						event.getRegistry().register(metal.getTool(toolType));
				}
			}

			//Armors
			if (metal.getArmorSet() != null && RegistrationConfig.categoryItems.enableMetalArmorSets)
			{
				for (Item armor : metal.getArmorSet())
				{
					event.getRegistry().register(armor);
				}
			}
		});

		//Misc Items
		ModItems.extraItems.forEach(item -> event.getRegistry().register(item));

		//Init OreDictionary (Register keys)
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
			for (BlockTypes type : BlockTypes.values())
			{
				if (type.isEnabled())
					event.getRegistry().register(metal.getBlock(type));
			}
		});

		//if (RegistrationConfig.categoryBlocks.enableMetalFluidBlocks) {
		for (BlockFluidClassic block : ModFluids.fluidBlocks)
		{
			event.getRegistry().register(block);
		}
		//}
	}

	@SubscribeEvent
	public static void registerModels(ModelRegistryEvent event)
	{
		ModBlocks.miscBlocks.forEach(block -> ItemUtils.registerCustomItemModel(Item.getItemFromBlock(block), 0));

		ModItems.extraItems.forEach(item -> ItemUtils.registerCustomItemModel(item, 0, item.getModelSubDir()));

		ModMetals.metalMap.forEach((name, metal) -> {

			//ItemBlocks
			if (metal.getOre() != null)
				ItemUtils.registerCustomItemModel(Item.getItemFromBlock(metal.getOre()), 0);

			for (BlockMetal block : metal.getBlocks())
			{
				ItemUtils.registerCustomItemModel(Item.getItemFromBlock(block), 0);
			}

			//Items
			ItemUtils.registerCustomItemModel(metal.getIngot(), 0, "ingot");
			ItemUtils.registerCustomItemModel(metal.getDust(), 0, "dust");
			ItemUtils.registerCustomItemModel(metal.getNugget(), 0, "nugget");

			//Tools
			if (metal.getToolSet() != null)
			{
				for (EnumTools tool : EnumTools.values())
				{
					ItemUtils.registerCustomItemModel(metal.getTool(tool), 0, "tool/" + tool.getName());
				}
			}

			//Armors
			if (metal.getArmorSet() != null)
			{
				for (Item armor : metal.getArmorSet())
				{
					ItemUtils.registerCustomItemModel(armor, 0, "armor");
				}
			}
		});

		ItemUtils.registerCustomItemModel(Item.getItemFromBlock(ModBlocks.crusher), 0);
		ItemUtils.registerCustomItemModel(Item.getItemFromBlock(ModBlocks.alloyer), 0);
		ItemUtils.registerCustomItemModel(Item.getItemFromBlock(ModBlocks.chamber), 0);

		ModRenderers.registerRenderers();
	}

	@SubscribeEvent
	public static void registerSounds(RegistryEvent.Register<SoundEvent> register)
	{
		ModSounds.LIST.forEach(event -> register.getRegistry().register(event));
		Metallurgy.logger.info(Metallurgy.NAME + ": " + ModSounds.LIST.size() + " Sounds have been registered!");
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public static void onModelBake(ModelBakeEvent event)
	{
		ModelResourceLocation location = new ModelResourceLocation(new ResourceLocation(Metallurgy.MODID, "gadget/brass_knuckles"), "inventory");
		IBakedModel original = event.getModelRegistry().getObject(location);
		event.getModelRegistry().putObject(location, new BrassKnucklesBakedModel(original));
	}

	@SubscribeEvent
	public static void attachCapability(AttachCapabilitiesEvent<Entity> event)
	{
		event.addCapability(PUNCH_EFFECT_CAP, new PunchEffectProvider());

		if (event.getObject() instanceof EntityPlayer)
		{
			event.addCapability(KRIK_EFFECT_CAPABILITY, new KrikEffectProvider());
		}
		if (event.getObject() instanceof EntityEnderman)
			event.addCapability(ENTITY_DATA_CAPABILITY, new EntityDataProvider());
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public static void registerTextures(TextureStitchEvent.Pre event)
	{
		for (int i = 1; i <= 10; i++)
			event.getMap().registerSprite(new ResourceLocation(Metallurgy.MODID, "particles/ore_particle_" + i));

		//Register brass knuckles item texture
		event.getMap().registerSprite(new ResourceLocation(Metallurgy.MODID, "items/gadgets/brass_knuckles_item"));
	}

}
