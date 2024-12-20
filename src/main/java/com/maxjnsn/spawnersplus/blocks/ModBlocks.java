package com.maxjnsn.spawnersplus.blocks;

import com.maxjnsn.spawnersplus.SpawnersPlus;
import com.maxjnsn.spawnersplus.blocks.custom.InactiveSpawnerBlock;
import com.maxjnsn.spawnersplus.blocks.custom.SoulAltarBlock;
import com.maxjnsn.spawnersplus.blocks.custom.WeepingSoulSandBlock;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class ModBlocks {
    public static final Block INACTIVE_SPAWNER = registerBlock("inactive_spawner",
           new InactiveSpawnerBlock(FabricBlockSettings.copyOf(Blocks.SPAWNER)));

    public static final Block WEEPING_SOUL_SAND = registerBlock("weeping_soul_sand",
            new WeepingSoulSandBlock(FabricBlockSettings.copyOf(Blocks.SOUL_SAND)));

    public static final Block SOUL_ALTAR = registerBlock("soul_altar",
            new SoulAltarBlock(FabricBlockSettings.copyOf(Blocks.DARK_OAK_PLANKS).nonOpaque()));

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(SpawnersPlus.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(SpawnersPlus.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));
    }

    public static void registerModBlocks() {
        SpawnersPlus.LOGGER.info("Registering Mod Blocks for " + SpawnersPlus.MOD_ID);
    }
}
