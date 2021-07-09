/*==============================================================================
 = Class: SoundHandler
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.sound;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.PositionedSound;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.HashMap;
import java.util.Map;

@SideOnly(Side.CLIENT)
public class SoundHandler {

    private static final Map<Long, ISound> SOUND_MAP = new HashMap<>();

    public static void startSound(BlockPos pos, SoundEvent event, SoundCategory category)
    {
        PositionedSound sound = new PositionedSoundRecord(event, category, 1F, 1F, pos);
        SOUND_MAP.put(pos.toLong(), sound);
        Minecraft.getMinecraft().getSoundHandler().playSound(sound);
    }

    public static void stopSound(BlockPos pos)
    {
        ISound sound = SOUND_MAP.remove(pos.toLong());
        Minecraft.getMinecraft().getSoundHandler().stopSound(sound);
    }

    public static boolean isSoundActive(BlockPos pos)
    {
        return SOUND_MAP.get(pos.toLong()) != null;
    }
}
