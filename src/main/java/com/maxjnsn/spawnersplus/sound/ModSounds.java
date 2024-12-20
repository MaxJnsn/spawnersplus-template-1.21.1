package com.maxjnsn.spawnersplus.sound;

import com.maxjnsn.spawnersplus.SpawnersPlus;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSounds {
    public static final SoundEvent WEEPING_SOUL_SAND = registerSoundEvent("weeping_soul_sand");

    private static SoundEvent registerSoundEvent(String name) {
        return Registry.register(Registries.SOUND_EVENT, Identifier.of(SpawnersPlus.MOD_ID, name),
                SoundEvent.of(Identifier.of(SpawnersPlus.MOD_ID, name)));
    }

    public static void registerSounds() {
        SpawnersPlus.LOGGER.info("Registering Mod Sounds for " + SpawnersPlus.MOD_ID);
    }
}
