package com.maxjnsn.spawnersplus;

import com.maxjnsn.spawnersplus.blocks.ModBlocks;
import com.maxjnsn.spawnersplus.enchantment.ModEnchantmentEffects;
import com.maxjnsn.spawnersplus.item.ModItemGroups;
import com.maxjnsn.spawnersplus.item.ModItems;
import com.maxjnsn.spawnersplus.sound.ModSounds;
import com.maxjnsn.spawnersplus.world.gen.ModWorldGeneration;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SpawnersPlus implements ModInitializer {
	public static final String MOD_ID = "spawnersplus";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItemGroups.registerModItemGroups();

		ModItems.registerModItems();
		ModBlocks.registerModBlocks();

		ModWorldGeneration.generateModWorldGeneration();

		ModSounds.registerSounds();

		ModEnchantmentEffects.registerEnchantmentEffects();

	}
}