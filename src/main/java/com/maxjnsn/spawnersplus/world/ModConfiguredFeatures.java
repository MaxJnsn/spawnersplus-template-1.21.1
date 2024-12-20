package com.maxjnsn.spawnersplus.world;

import com.maxjnsn.spawnersplus.SpawnersPlus;
import com.maxjnsn.spawnersplus.blocks.ModBlocks;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.structure.rule.BlockMatchRuleTest;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;

import java.util.List;

public class ModConfiguredFeatures {

    public static final RegistryKey<ConfiguredFeature<?, ?>> WEEPING_SOUL_SAND_KEY =
            registerKey("weeping_soul_sand");


    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context) {

        RuleTest soulSandReplaceables = new BlockMatchRuleTest(Blocks.SOUL_SAND);

        List<OreFeatureConfig.Target> weepingSoulSand =
                List.of(OreFeatureConfig.createTarget(soulSandReplaceables, ModBlocks.WEEPING_SOUL_SAND.getDefaultState()));

        register(context, WEEPING_SOUL_SAND_KEY, Feature.ORE, new OreFeatureConfig(weepingSoulSand, 12));
    }

    public static RegistryKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, Identifier.of(SpawnersPlus.MOD_ID, name));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?, ?>> context,
                                                                                   RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
