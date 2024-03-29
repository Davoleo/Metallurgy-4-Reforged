/*==============================================================================
 = Class: ModSounds
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.sound;

import it.hurts.metallurgy_reforged.Metallurgy;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.registry.RegistrySimple;

public class ModSounds {

    public static final RegistrySimple<ResourceLocation, SoundEvent> REGISTRY = new RegistrySimple<>();

    //Machines ---
    public static SoundEvent ALLOYER_AMBIENCE;
    public static SoundEvent ALLOYER_IMPACT;
    public static SoundEvent ALLOYER_WINDUP;

    public static SoundEvent CRUSHER_AMBIENCE;
    public static SoundEvent CRUSHER_IMPACT;
    public static SoundEvent CRUSHER_WINDUP;

    public static SoundEvent SUBLIMATION_CHAMBER_AMBIENCE;
    public static SoundEvent SUBLIMATION_CHAMBER_WINDUP;

    //Gadgets ---
    public static SoundEvent METAL_DETECTOR_PING;
    public static SoundEvent[] METAL_DETECTOR_BEEPS = new SoundEvent[3];

    static
    {
        ALLOYER_AMBIENCE = buildSoundEvent("alloyer_ambience");
        ALLOYER_IMPACT = buildSoundEvent("alloyer_impact");
        ALLOYER_WINDUP = buildSoundEvent("alloyer_windup");
        CRUSHER_AMBIENCE = buildSoundEvent("crusher_ambience");
        CRUSHER_IMPACT = buildSoundEvent("crusher_impact");
        CRUSHER_WINDUP = buildSoundEvent("crusher_windup");
        SUBLIMATION_CHAMBER_AMBIENCE = buildSoundEvent("sublimation_chamber_ambience");
        SUBLIMATION_CHAMBER_WINDUP = buildSoundEvent("sublimation_chamber_windup");

        METAL_DETECTOR_PING = buildSoundEvent("metal_detector_ping");
        for (int i = 0; i < 3; i++)
            METAL_DETECTOR_BEEPS[i] = buildSoundEvent("metal_detector_beep_" + (i + 1));
    }

    private static SoundEvent buildSoundEvent(String name)
    {
        ResourceLocation location = new ResourceLocation(Metallurgy.MODID, name);
        SoundEvent event = new SoundEvent(location).setRegistryName(location);
        REGISTRY.putObject(location, event);
        return event;
    }

}
