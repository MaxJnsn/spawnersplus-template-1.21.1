package com.maxjnsn.spawnersplus.item;

import com.maxjnsn.spawnersplus.SpawnersPlus;
import com.maxjnsn.spawnersplus.blocks.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final ItemGroup SPAWNERS_PLUS = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(SpawnersPlus.MOD_ID, "spawners_plus"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.spawners_plus"))
                    .icon(() -> new ItemStack(ModItems.SPAWNER_FRAGMENT)).entries((displayContext, entries) -> {


                        entries.add(ModBlocks.INACTIVE_SPAWNER);
                        entries.add(ModBlocks.WEEPING_SOUL_SAND);
                        entries.add(ModItems.SPAWNER_FRAGMENT);

                    }).build());


    public static void registerModItemGroups() {
        SpawnersPlus.LOGGER.info("Registering Mod Item Groups for " + SpawnersPlus.MOD_ID);
    }
}
