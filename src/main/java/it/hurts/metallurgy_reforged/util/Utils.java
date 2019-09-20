/*
 * -------------------------------------------------------------------------------------------------------
 * Class: Utils
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2019.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.util;

import it.hurts.metallurgy_reforged.material.Metal;
import it.hurts.metallurgy_reforged.material.ModMetals;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.Potion;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.EnumSkyBlock;

public class Utils {

	private static Potion[] randomEffectsList = {
			MobEffects.BLINDNESS,
			MobEffects.LEVITATION,
			MobEffects.HUNGER,
			MobEffects.INSTANT_DAMAGE,
			MobEffects.NAUSEA,
			MobEffects.NIGHT_VISION,
			MobEffects.POISON,
			MobEffects.SLOWNESS,
			MobEffects.REGENERATION
	};

	public static String[] modMaterialNames = {
			"adamantine",
			"amordrine",
			"angmallen",
			"astral_silver",
			"atlarus",
			"black_steel",
			"brass",
			"bronze",
			"carmot",
			"celenegil",
			"ceruclase",
			"copper",
			"damascus_steel",
			"deep_iron",
			"desichalkos",
			"electrum",
			"eximite",
			"haderoth",
			"hepatizon",
			"ignatius",
			"inolashite",
			"kalendrite",
			"krik",
			"lutetium",
			"midasium",
			"mithril",
			"orichalcum",
			"osmium",
			"oureclase",
			"platinum",
			"prometheum",
			"quicksilver",
			"sanguinite",
			"shadow_iron",
			"shadow_steel",
			"silver",
			"steel",
			"tartarite",
			"vulcanite",
			"vyroxeres",
			};

	public static void giveExperience(EntityPlayer thePlayer, float experience)
	{
		int intExp = (int) experience;
		float fractional = experience - intExp;
		if (fractional > 0.0F && (float) Math.random() < fractional)
		{
			intExp++;
		}
		while (intExp > 0)
		{
			int j = EntityXPOrb.getXPSplit(intExp);
			intExp -= j;
			thePlayer.world.spawnEntity(new EntityXPOrb(thePlayer.world, thePlayer.posX, thePlayer.posY + 0.5D, thePlayer.posZ + 0.5D, j));
		}

	}

	//maxPercent is the max percent that can reach when the player is in complete darkness
	public static float getLightArmorPercentage(EntityPlayer pl, float maxPercent)
	{

		BlockPos pos = new BlockPos(pl.posX, pl.posY, pl.posZ);
		//check if it is day
		boolean isDay = (pl.world.getWorldTime() % 23300) <= 12800;
		//get sky light level,if it is night the light will be 0
		float lightSky = Math.min(isDay ? pl.world.getLightFor(EnumSkyBlock.SKY, pos) : 0F, 14F);
		//get light emitted by a block(like a torch)
		float lightBlock = Math.min(pl.world.getLightFor(EnumSkyBlock.BLOCK, pos), 14);
		//get the light based on the lightSky and the lightBlock
		float light = lightSky <= lightBlock ? lightBlock : lightSky;

		//14 is the max Light possible
		return maxPercent - (light * maxPercent / 14F);

	}

	public static String localize(String unlocalized)
	{
		return new TextComponentTranslation(unlocalized).getFormattedText();
	}

	public static String localizeIgnoreFormat(String unlocalized)
	{
		return new TextComponentTranslation(unlocalized).getUnformattedComponentText();
	}

	public static Potion getRandomEffect()
	{
		return randomEffectsList[(int) (Math.random() * Utils.getMaxIndexEffect())];
	}

	private static int getMaxIndexEffect()
	{
		return randomEffectsList.length;
	}

	//TODO : @ItHurtsLikeHell Document this fucking method please
	public static String getName(String name)
	{
		String[] str = name.split("_");
		String[] space = space(str.length);
		name = "";
		for (int i = 0; i < str.length; i++)
		{
			name = name + str[i].substring(0, 1).toUpperCase() + str[i].substring(1) + space[i];
		}
		return name;
	}

	//TODO : @ItHurtsLikeHell Document this fucking method please
	private static String[] space(int len)
	{
		String[] str = new String[len];
		for (int i = 0; i < len; i++)
		{
			if (i < len - 1)
			{
				str[i] = " ";
			}
			else
				str[i] = "";
		}
		return str;
	}

	public static Metal getMetalFromString(String string)
	{
		for (Metal metal : ModMetals.metalList)
			if (string.contains(metal.toString()))
				return metal;
		return null;
	}


}
